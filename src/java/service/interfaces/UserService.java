package service.interfaces;

import commons.dto.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Aya M. Ashraf
 */
public interface UserService {
    
    public UserDTO addUser(UserDTO user);
    public UserDTO getUserByEmail(String email);
    public UserDTO updateUserPassword(String email, String phone);
    
    public ArrayList transferCoinsToUser(String coinsType, double coinsCount, String senderEmail, String receiverEmail);
    
}
