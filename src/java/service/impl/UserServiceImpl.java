package service.impl;

import commons.dto.UserDTO;
import dao.impl.UserDaoImpl;
import dao.interfaces.UserDao;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.interfaces.UserService;
import utils.EntityMapper;

/**
 *
 * @author Aya M. Ashraf
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private EntityMapper mapper;
    ApplicationContext context;

    public UserServiceImpl() {
        context = new FileSystemXmlApplicationContext("C:\\Users\\Hossam\\Documents\\NetBeansWebProjects\\com.HaaS.server\\src\\java\\beans.xml");
        userDao = (UserDao) context.getBean("userDaoImpl");
        mapper = new EntityMapper();
    }

    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = mapper.mapUserDtoToUser(userDto);
        user = userDao.makePersistent(user);
        return mapper.mapUserToUserDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            return mapper.mapUserToUserDto(user);
        }
    }

    @Override
    public UserDTO updateUserPassword(String email, String phone) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            if(user.getPhone().equals(phone)){
            user.setPassword("ChangeThisPassword");
            userDao.update(user);
            return mapper.mapUserToUserDto(user);
            }
            else
                return null;
        }
    }
}
