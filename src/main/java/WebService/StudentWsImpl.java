package WebService;

import File.Xfile;
import Model.Account;
import Model.Database;
import Model.Student;
import Xdatabase.MyDatabase;
import Thread.threadOne;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebService(endpointInterface = "WebService.StudentWs")
public class StudentWsImpl implements StudentWs{
    private Account account = new Account();
    private Xfile xfile = new Xfile();
    private Database database;
    private MyDatabase myDatabase = new MyDatabase() ;
    private ClassWebServiceImpl classWebServiceImpl = new ClassWebServiceImpl();
    private static final Logger LOGGER = Logger.getLogger(StudentWsImpl.class);

    @Override
    public String addStudentReal(String username, String password, String name, int age, String code, String className, String address, double mark) {
        account = xfile.readFileApp();
        int i = 0;
        if(account.getUsername().equalsIgnoreCase(username) && account.getPassword().equalsIgnoreCase(password)){
            database = xfile.readFileDB();
            try {
//                Class.forName(database.getDriver());
                Connection connection = DriverManager.getConnection(database.getConnection(),database.getUsername(),
                        database.getPassword());
                if(myDatabase.checkClassName(connection,className)){
                     i = 1;
                }
                else {
                    String them = classWebServiceImpl.addClass(account.getUsername(),account.getPassword(),className,code);
                    if(them.equalsIgnoreCase("0")){
                        i = 1;
                    }
                    else {
                        i = -1;
                    }
                }
                if(i == 1){
                    Student student = new Student(name,code,className,address,age,mark);
                    threadOne threadOne = new threadOne(student);
                    threadOne.start();
                }


            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            return "";
        }
        else return "Username và password không hợp lệ";
    }
}
