import dao.interfaces.UserDao;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class SpringHibernate {

    public static void main(String[] args) {
       
        ApplicationContext context = new FileSystemXmlApplicationContext("src/java/beans.xml");
        
        UserDao dao = (UserDao) context.getBean("userDaoImpl");
        User user = new User();
        user.setEmail("Aya@ay7aga");
        user.setFirstName("Ayaaaa");
        user.setGoldenCoins(1200);
        user.setPassword("password");
        user.setPhone("0111444444");
        user.setLastName("Ashraaaaf");
        user.setSilverCoins(13000);
        
        dao.makePersistent(user);
        System.out.println(dao.findAll().size());
    }
    
}
