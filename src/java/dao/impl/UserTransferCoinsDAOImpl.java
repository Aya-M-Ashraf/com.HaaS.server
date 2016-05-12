/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.GenericHibernateDAO;
import dao.interfaces.UserTransferCoinsDAO;
import entity.UserTransferCoinsUser;
import java.io.Serializable;
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
public class UserTransferCoinsDAOImpl extends GenericHibernateDAO<UserTransferCoinsUser, String> implements UserTransferCoinsDAO{
    
}
