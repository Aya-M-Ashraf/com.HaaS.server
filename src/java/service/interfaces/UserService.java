package service.interfaces;

import commons.dto.UserDTO;

/**
 *
 * @author Aya M. Ashraf
 */
public interface UserService {
    
    public UserDTO addUser(UserDTO user);
    public UserDTO getUserByEmail(String email);
    public UserDTO updateUserPassword(String email, String phone);
    
}
