package WebService;

import File.Xfile;
import Model.Account;
import Model.Classdb;
import Model.Database;
import Xdatabase.MyDatabase;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@WebService(endpointInterface = "WebService.ClassWebService")
public class ClassWebServiceImpl implements ClassWebService{
    private  static final Logger logger = Logger.getLogger(ClassWebServiceImpl.class);
    private Xfile xfile = new Xfile();
    private Account account = new Account();
    private Database database = new Database();

    @Override
    public String addClass(String username, String password, String name, String code)  {
        account = xfile.readFileApp();
        if(!username.equalsIgnoreCase(account.getUsername())&&!password.equalsIgnoreCase(account.getPassword())){
            return "Username và Password không hợp lệ";
        }
        else {
            try {
                Classdb classdb = new Classdb(name,code);
                database = xfile.readFileDB();
                Connection connection = DriverManager.getConnection(database.getConnection()
                                        ,database.getUsername(),database.getPassword());
                String sql = "INSERT INTO Class(name,code) VALUES(?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,classdb.getName());
                preparedStatement.setString(2,classdb.getCode());
                int check = preparedStatement.executeUpdate();
                if (check > 0){
                    return "0";
                }
                else return "1";
            }
             catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return "";
    }
}
