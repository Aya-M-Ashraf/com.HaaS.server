package service.impl;

import dao.impl.UserDaoImpl;
import entity.User;
import service.interfaces.UserService;

/**
 *
 * @author Aya M. Ashraf
 */
public class UserServiceImpl implements UserService{
    
    private UserDaoImpl userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User registerUser(User user) {
       return userDao.makePersistent(user);
    }
    
    
    
}
