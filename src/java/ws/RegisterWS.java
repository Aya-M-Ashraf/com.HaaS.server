package ws;

import commons.dto.UserDTO;
import commons.ws.Constants;
import commons.ws.Result;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import service.interfaces.UserService;

/**
 *
 * @author Aya M. Ashraf
 */
@Path("/")
public class RegisterWS {

    @Autowired
    UserService userServiceImpl;

    @GET
    @Path("/register")
    public Result register(@QueryParam(Constants.EMAIL) String email) {
        Result result = new Result();
        UserDTO userDto = new UserDTO();
        userDto.setEmail(email);
        userDto = userServiceImpl.addUser(userDto);

        if (userDto != null) {
            result.setSuccess(true);
            result.setObj(userDto);
        } else {
            result.setSuccess(false);
            result.setMsg("user can't be added");
        }
        
        return result;
    }

}
