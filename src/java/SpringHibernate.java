import dao.interfaces.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class SpringHibernate {

    public static void main(String[] args) {
       
        ApplicationContext context = new FileSystemXmlApplicationContext("src/java/beans.xml");
        
        UserDao dao = (UserDao) context.getBean("userDaoImpl");
        System.out.println(dao.findAll().size());
    }
    
}
