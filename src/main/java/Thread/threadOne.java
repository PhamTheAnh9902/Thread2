package Thread;

import File.Xfile;
import Model.Database;
import Model.Student;
import Xdatabase.MyDatabase;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class threadOne extends Thread{
    private  final Student student ;
    private final Queue<Student> studentQueue = new LinkedList<>();
    private Database database = new Database();
    private final Xfile xfile = new Xfile();
    private static final Logger LOGGER = Logger.getLogger(threadOne.class);
    private final MyDatabase myDatabase = new MyDatabase();

    public threadOne(Student student1) {
        student = student1;
    }

    @Override
    public void run() {
        studentQueue.add(student);
        try{
            database = xfile.readFileDB();
            Class.forName(database.getDriver());
            Connection connection = DriverManager.getConnection(database.getConnection()
                    ,database.getUsername(),database.getPassword());
            if(myDatabase.insertStudent(connection,student)){
                LOGGER.info("Thanh cong");
            }
            else {
                LOGGER.info("That Bai");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
