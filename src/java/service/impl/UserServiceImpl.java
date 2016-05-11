package service.impl;

import commons.dto.UserDTO;
import dao.impl.UserDaoImpl;
import entity.User;
import service.interfaces.UserService;
import utils.EntityMapper;

/**
 *
 * @author Aya M. Ashraf
 */
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;
    private EntityMapper mapper;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        mapper = new EntityMapper();
    }

    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = mapper.mapUserDtoToUser(userDto);
        user = userDao.makePersistent(user);
        return mapper.mapUserToUserDto(user);
    }
}
