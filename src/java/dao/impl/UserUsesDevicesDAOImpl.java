package dao.impl;

import dao.GenericHibernateDAO;
import dao.interfaces.UserUsesDevicesDAO;
import entity.UserUsesDevice;
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
public class UserUsesDevicesDAOImpl extends GenericHibernateDAO<UserUsesDevice, String> implements UserUsesDevicesDAO {

}
