package dao.impl;

import dao.GenericHibernateDAO;
import dao.interfaces.UserDao;
import entity.User;
import entity.UserTransferCoinsUser;
import entity.UserTransferCoinsUserPK;
import java.util.Date;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends GenericHibernateDAO<User, String> implements UserDao {

    @Override
    public User getUserByEmail(String email) {
        Query query = getSession().createQuery("from User where email= :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public boolean transferCoinsToUser(String coinsType, double coinsCount, String senderEmail, String receiverEmail) {

        User lenderUser = getUserByEmail(senderEmail);
        User borrowerUser = getUserByEmail(receiverEmail);

        //----------- if any of the users is null then the transaction can't be done--------------
        if (lenderUser != null && borrowerUser != null) {

            if (coinsType.equalsIgnoreCase("golden")) {

                double senderCoinsAfterDeduction = lenderUser.getGoldenCoins() - coinsCount;
                //-------------- if the sender coins after transferring the coins are less than zero then its a fraud operation ------------------
                if (senderCoinsAfterDeduction >= 0) {
                    borrowerUser.setGoldenCoins(borrowerUser.getGoldenCoins() + coinsCount);
                    lenderUser.setGoldenCoins(lenderUser.getGoldenCoins() - coinsCount);

                    //---------- update both sender and receiver users-------------------------
                    try {
                        getSession().update(lenderUser);
                        getSession().update(borrowerUser);
                        UserTransferCoinsUserPK userTransferCoinsUserPK = new UserTransferCoinsUserPK();
                        userTransferCoinsUserPK.setBorrowerUserId(borrowerUser.getUserId());
                        userTransferCoinsUserPK.setLenderUserId(lenderUser.getUserId());
                        userTransferCoinsUserPK.setTransactionTimestamp(new Date());

                        UserTransferCoinsUser userTransferCoinsUser = new UserTransferCoinsUser();
                        userTransferCoinsUser.setLenderUser(lenderUser);
                        userTransferCoinsUser.setBorrowerUser(borrowerUser);
                        userTransferCoinsUser.setUserTransferCoinsUserPK(userTransferCoinsUserPK);
                        userTransferCoinsUser.setCoinsAmount(coinsCount);
                        getSession().persist(userTransferCoinsUser);
//                    getSession().per

                        System.out.println("Successful coins transferring");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                } else {
                    System.out.println("Sender golden coins are too few to make this transaction");
                    return false;
                }
            } else if (coinsType.equalsIgnoreCase("silver")) {
                borrowerUser.setSilverCoins(borrowerUser.getSilverCoins() + coinsCount);

                double senderCoinsAfterDeduction = lenderUser.getSilverCoins() - coinsCount;
                //-------------- if the sender coins after transferring the coins are less than zero then its a fraud operation ------------------
                if (senderCoinsAfterDeduction >= 0) {
                    lenderUser.setSilverCoins(lenderUser.getSilverCoins() - coinsCount);

                    //---------- update both sender and receiver users-------------------------
                    getSession().merge(lenderUser);
                    getSession().merge(borrowerUser);
                    System.out.println("Successful coins transferring");
                    return true;
                } else {
                    System.out.println("Sender silver coins are too few to make this transaction");
                    return false;
                }
            }
            //------- if any of the above conditions is not fullfilled then this is a transaction failure---------------
            System.out.println("Not defined coins type");
            return false;
        } else {
            System.out.println("Sender and receivers both must be an existing real users on the system");
            return false;
        }

    }

}
