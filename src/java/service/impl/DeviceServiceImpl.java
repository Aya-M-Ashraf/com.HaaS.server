package service.impl;

import dao.interfaces.DeviceDAO;
import dao.interfaces.UserDao;
import dao.interfaces.UserUsesDevicesDAO;
import entity.Device;
import entity.User;
import entity.UserUsesDevice;
import entity.key.UserUsesDevicePK;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.interfaces.DeviceService;
import utils.EntityMapper;

/**
 *
 * @author Aya M. Ashraf
 */
public class DeviceServiceImpl implements DeviceService {

    DeviceDAO deviceDAO;
    UserDao userDAO;
    UserUsesDevicesDAO userUsesDevicesDAO;
    ApplicationContext context;
    EntityMapper mapper;

    public DeviceServiceImpl() {
        context = new FileSystemXmlApplicationContext("C:\\Users\\Shall\\Documents\\NetBeansProjects\\com.HaaS.server\\src\\java\\beans.xml");
        deviceDAO = (DeviceDAO) context.getBean("deviceDAOImpl");
        userDAO = (UserDao) context.getBean("userDAOImpl");
        userUsesDevicesDAO = (UserUsesDevicesDAO) context.getBean("userUsesDevicesDAOImpl");
        mapper = new EntityMapper();
    }

    @Override
    public boolean linkDevice(String email, String serialNumber) {

        Device device = deviceDAO.getDeviceBySerialNumber(serialNumber);
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            if (device != null) { //this means that the device is registered and needs only to be linked
                System.out.println("this means that the device is registered and needs only to be linked");
                UserUsesDevice userUsesDevice = new UserUsesDevice(new UserUsesDevicePK(device.getDeviceId(), user.getUserId()), new Date());
                userUsesDevicesDAO.makePersistent(userUsesDevice);

                return true;
            } else {

                //----------- No device is registered with this serial number
                System.out.println("No device is registered with this serial number");
                Device registerDevice = new Device(serialNumber);
                deviceDAO.makePersistent(registerDevice);
                Device addedDevice = deviceDAO.getDeviceBySerialNumber(serialNumber);
                UserUsesDevice userUsesDevice = new UserUsesDevice(new UserUsesDevicePK(addedDevice.getDeviceId(), user.getUserId()), new Date());
                userUsesDevicesDAO.makePersistent(userUsesDevice);
                return true;
            }
        } else {
            //No such user in the system
            System.out.println("No such user in the system");
            return false;
        }

    }

}
