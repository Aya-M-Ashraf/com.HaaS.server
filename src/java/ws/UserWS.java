package ws;

import commons.dto.UserDTO;
import commons.ws.Constants;
import commons.ws.Result;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import service.impl.UserServiceImpl;
import service.interfaces.UserService;

/**
 *
 * @author Aya M. Ashraf
 */
@Path("/user")
public class UserWS {

    private UserService userServiceImpl;

    public UserWS() {
        userServiceImpl = new UserServiceImpl();
    }

    @GET
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Result register(@QueryParam(Constants.EMAIL) String email, @QueryParam(Constants.F_NAME) String fName, @QueryParam(Constants.L_NAME) String lName, @QueryParam(Constants.PHONE) String phone, @QueryParam(Constants.PASSWORD) String password) {

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

    @GET
    @Path("/viewProfile")
    @Produces(MediaType.APPLICATION_JSON)
    public Result viewProfile(@QueryParam(Constants.EMAIL) String email) {
        Result result = new Result();
        UserDTO user = userServiceImpl.getUserByEmail(email);
        if (user == null) {
            result.setSuccess(false);
            result.setMsg("This Email doesn't belong to anyone");
            result.setObj(null);
            result.setCode("viewProfile");
        } else {
            result.setSuccess(true);
            result.setMsg(null);
            result.setObj(user);
            result.setCode("viewProfile");
        }
        return result;
    }

    @GET
    @Path("/transfareCoins")
    @Produces(MediaType.APPLICATION_JSON)
    public Result transfareCoins(@QueryParam(Constants.EMAIL) String email) {
        Result result = new Result();
        //to be continued 
        return result;
    }

}
