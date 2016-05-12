/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import entity.UserUsesDevice;

/**
 *
 * @author Shall
 */
@Repository
@Transactional
public interface UserUsesDevicesDAO extends GenericDAO<UserUsesDevice, String> {

}
