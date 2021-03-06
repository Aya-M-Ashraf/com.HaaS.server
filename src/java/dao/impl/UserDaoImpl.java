package dao.impl;

import dao.GenericHibernateDAO;
import dao.interfaces.UserDao;
import entity.User;
import org.hibernate.Query;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends GenericHibernateDAO<User, String> implements UserDao {

    public UserDaoImpl() {
         System.out.println("------------- userDaoImpl is created");
    }
    
    

    @Override
    public User getUserByEmail(String email) {
        Query query = getSession().createQuery("from User where email= :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        return user;
    } 

}
