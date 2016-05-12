package dao.impl;

import dao.GenericHibernateDAO;
import dao.interfaces.DeviceDAO;
import entity.Device;
import org.hibernate.Query;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shall
 */
@Repository
@Transactional
@ComponentScan
public class DeviceDAOImpl extends GenericHibernateDAO<Device, String> implements DeviceDAO {

    @Override
    public Device getDeviceBySerialNumber(String serialNumber) {
        Query query = getSession().createQuery("from Device where serial_number= :serialNumber");
        query.setParameter("serialNumber", serialNumber);
        Device device = (Device) query.uniqueResult();
        return device;
    }

}
