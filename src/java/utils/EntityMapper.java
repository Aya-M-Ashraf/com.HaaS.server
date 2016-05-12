package utils;

import commons.dto.UserDTO;
import entity.User;
import org.springframework.stereotype.Component;


/**
 *
 * @author Aya M. Ashraf
 */
@Component
public class EntityMapper {

    public EntityMapper() {
        System.out.println("------------- EntityMapper is created");
    }
      
    public User mapUserDtoToUser (UserDTO userDto){
        User user = new User(userDto.getEmail(),userDto.getFirstName(),userDto.getLastName(),userDto.getPhone(),userDto.getPassword(),userDto.getGoldenCoins(),userDto.getSilverCoins());
        return user;
    }
    
    public UserDTO mapUserToUserDto (User user){
        UserDTO userDto = new UserDTO(user.getUserId(),user.getEmail(),user.getFirstName(),user.getLastName(),user.getPhone(),user.getPassword(),user.getGoldenCoins(),user.getSilverCoins());
        return userDto;
    }
    
}
