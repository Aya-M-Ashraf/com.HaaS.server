package ws;

import commons.dto.UserDTO;
import commons.ws.Constants;
import commons.ws.Result;
import javax.faces.application.Application;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import service.impl.UserServiceImpl;
import service.interfaces.UserService;

/**
 *
 * @author Aya M. Ashraf
 */
@Path("/register")
public class RegisterWS {

   
    private UserService userServiceImpl;

    @GET
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Result register(@QueryParam(Constants.EMAIL) String email, @QueryParam(Constants.F_NAME) String fName, @QueryParam(Constants.L_NAME) String lName, @QueryParam(Constants.PHONE) String phone, @QueryParam(Constants.PASSWORD) String password) {
        userServiceImpl = new UserServiceImpl();
        Result result = new Result();
        UserDTO userDto = new UserDTO();
        userDto.setEmail(email);
        userDto.setFirstName(fName);
        userDto.setLastName(lName);
        userDto.setPhone(phone);
        userDto.setPassword(password);
        userDto.setGoldenCoins(100);
        userDto.setSilverCoins(100);
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
