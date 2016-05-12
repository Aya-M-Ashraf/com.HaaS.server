package service.impl;

import commons.dto.UserDTO;
import dao.interfaces.UserDao;
import dao.interfaces.UserTransferCoinsDAO;
import entity.User;
import entity.UserTransferCoinsUser;
import entity.UserTransferCoinsUserPK;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.UserService;
import utils.EntityMapper;

/**
 *
 * @author Aya M. Ashraf
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDaoImpl;
    @Autowired
    private UserTransferCoinsDAO userTransferCoinsDAO;
    @Autowired
    private EntityMapper entityMapper;

    public UserServiceImpl() {
        System.out.println("------------- userServiceImpl is created");
    }

    @PostConstruct
    public void init() {
        System.out.println("--------------  in the init of UserServiceImpl");
        userDaoImpl.findAll();
    }

    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = entityMapper.mapUserDtoToUser(userDto);
        user = userDaoImpl.makePersistent(user);
        return entityMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userDaoImpl.getUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            return entityMapper.mapUserToUserDto(user);
        }
    }

    @Override
    public UserDTO updateUserPassword(String email, String phone) {
        User user = userDaoImpl.getUserByEmail(email);
        if (user == null) {
            return null;
        } else if (user.getPhone().equals(phone)) {
            user.setPassword("ChangeThisPassword");
            userDaoImpl.update(user);
            return entityMapper.mapUserToUserDto(user);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList transferCoinsToUser(String coinsType, double coinsCount, String senderEmail, String receiverEmail) {
        ArrayList result = new ArrayList();
        User lenderUser = userDaoImpl.getUserByEmail(senderEmail);
        User borrowerUser = userDaoImpl.getUserByEmail(receiverEmail);

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
                        userDaoImpl.update(lenderUser);
                        userDaoImpl.update(borrowerUser);

                        //------------ Update table user_transfer_coins_user table -------------------------------
                        UserTransferCoinsUserPK userTransferCoinsUserPK = new UserTransferCoinsUserPK();
                        userTransferCoinsUserPK.setBorrowerUserId(borrowerUser.getUserId());
                        userTransferCoinsUserPK.setLenderUserId(lenderUser.getUserId());
                        userTransferCoinsUserPK.setTransactionTimestamp(new Date());

                        UserTransferCoinsUser userTransferCoinsUser = new UserTransferCoinsUser();
                        userTransferCoinsUser.setLenderUser(lenderUser);
                        userTransferCoinsUser.setBorrowerUser(borrowerUser);
                        userTransferCoinsUser.setUserTransferCoinsUserPK(userTransferCoinsUserPK);
                        userTransferCoinsUser.setCoinsAmount(coinsCount);
                        userTransferCoinsDAO.makePersistent(userTransferCoinsUser);

//                    getSession().per
                        System.out.println("Successful coins transferring");
                        result.add("Successful coins transferring");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    result.add(true);
                    return result;
                } else {

                    System.out.println("Sender golden coins are too few to make this transaction");
                    result.add("Sender golden coins are too few to make this transaction");
                    result.add(false);
                    return result;
                }
            } else if (coinsType.equalsIgnoreCase("silver")) {

                double senderCoinsAfterDeduction = lenderUser.getSilverCoins() - coinsCount;
                //-------------- if the sender coins after transferring the coins are less than zero then its a fraud operation ------------------
                if (senderCoinsAfterDeduction >= 0) {
                    borrowerUser.setSilverCoins(borrowerUser.getSilverCoins() + coinsCount);
                    lenderUser.setSilverCoins(lenderUser.getSilverCoins() - coinsCount);

                    //---------- update both sender and receiver users-------------------------
                    userDaoImpl.update(lenderUser);
                    userDaoImpl.update(borrowerUser);

                    //------------ Update table user_transfer_coins_user table -------------------------------
                    UserTransferCoinsUserPK userTransferCoinsUserPK = new UserTransferCoinsUserPK();
                    userTransferCoinsUserPK.setBorrowerUserId(borrowerUser.getUserId());
                    userTransferCoinsUserPK.setLenderUserId(lenderUser.getUserId());
                    userTransferCoinsUserPK.setTransactionTimestamp(new Date());

                    UserTransferCoinsUser userTransferCoinsUser = new UserTransferCoinsUser();
                    userTransferCoinsUser.setLenderUser(lenderUser);
                    userTransferCoinsUser.setBorrowerUser(borrowerUser);
                    userTransferCoinsUser.setUserTransferCoinsUserPK(userTransferCoinsUserPK);
                    userTransferCoinsUser.setCoinsAmount(coinsCount);
                    userTransferCoinsDAO.makePersistent(userTransferCoinsUser);

                    System.out.println("Successful coins transferring");
                    result.add("Successful coins transferring");
                    result.add(true);
                    return result;
                } else {
                    System.out.println("Sender silver coins are too few to make this transaction");
                    result.add("Sender silver coins are too few to make this transaction");
                    result.add(false);
                    return result;
                }
            }
            //------- if any of the above conditions is not fullfilled then this is a transaction failure---------------
            System.out.println("Not defined coins type");
            result.add("Not defined coins type");
            result.add(false);
            return result;
        } else {
            System.out.println("Sender and receivers both must be an existing real users on the system");
            result.add("Sender and receivers both must be an existing real users on the system");
            result.add(false);
            return result;
        }

    }

    public void setUserDaoImpl(UserDao userDaoImpl) {
        System.out.println("---------------- in the setter of userDaoImpl");
        this.userDaoImpl = userDaoImpl;
    }

    public void setUserTransferCoinsDAO(UserTransferCoinsDAO userTransferCoinsDAO) {
        this.userTransferCoinsDAO = userTransferCoinsDAO;
    }

    public void setEntityMapper(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

}
