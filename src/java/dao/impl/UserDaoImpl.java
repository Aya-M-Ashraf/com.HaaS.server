package dao.impl;

import dao.GenericHibernateDAO;
import dao.interfaces.UserDao;
import entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends GenericHibernateDAO<User, String> implements UserDao {

}
