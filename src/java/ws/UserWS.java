package ws;

import commons.dto.UserDTO;
import commons.ws.Constants;
import commons.ws.Result;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.interfaces.UserService;

@Path("/user")
@Component
public class UserWS {

    @Autowired
    private UserService userServiceImpl;

    public UserWS() {
        System.out.println("------------- UserWS is crested");
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
    @Path("/transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public Result transferCoins(@QueryParam(Constants.COINS_TYPE) String coinsType, @QueryParam(Constants.COINS_COUNT) double coinsCount, @QueryParam(Constants.SENDER_EMAIL) String senderEmail, @QueryParam(Constants.RECEIVER_EMAIL) String receiverEmail) {
        Result result = new Result();        
        ArrayList resultArrayList = userServiceImpl.transferCoinsToUser(coinsType, coinsCount, senderEmail, receiverEmail);
        if (resultArrayList.size() > 0) {
            String message = (String) resultArrayList.get(0);
            boolean success = (boolean) resultArrayList.get(1);
            result.setMsg(message);
            result.setSuccess(success);
        }
        
        return result;
    }

    @GET
    @Path("/retrievePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Result retrievePassword(@QueryParam(Constants.EMAIL) String email, @QueryParam(Constants.PHONE) String phone) {
        Result result = new Result();
        UserDTO user = userServiceImpl.updateUserPassword(email, phone);
        if (user == null) {
            result.setSuccess(false);
            result.setMsg("The Entered Values are not Valid");
            result.setObj(null);
            result.setCode("retrievePassword");
        } else {
            result.setSuccess(true);
            result.setMsg(null);
            result.setObj(user);
            result.setCode("retrievePassword");
        }
        return result;
    }
}