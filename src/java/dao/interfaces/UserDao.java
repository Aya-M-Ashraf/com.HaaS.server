package dao.interfaces;


import entity.User;


public interface UserDao extends GenericDAO<User, String> {
    public User getUserByEmail(String email);
}
