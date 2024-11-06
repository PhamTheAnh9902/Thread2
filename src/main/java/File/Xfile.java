package File;

import Model.Account;
import Model.Database;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Xfile {
    private static final String filedb = "db.conf";
    private static final String fileapp = "app.conf";
    private final Database database = new Database();
    private final Account account = new Account();
    private static final Logger LOGGER = Logger.getLogger(Xfile.class);

    public Database readFileDB(){
        try{
            Properties propertiesdb = new Properties();
            propertiesdb.load(Files.newInputStream(Paths.get(filedb)));
            database.setDriver(propertiesdb.getProperty("driver"));
            database.setConnection(propertiesdb.getProperty("connection"));
            database.setUsername(propertiesdb.getProperty("username"));
            database.setPassword(propertiesdb.getProperty("password"));
            return database;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public Account readFileApp(){
        try{
            Properties propertiesapp = new Properties();
            propertiesapp.load(Files.newInputStream(Paths.get(fileapp)));
            account.setUsername(propertiesapp.getProperty("username"));
            account.setPassword(propertiesapp.getProperty("password"));
            return account;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }




}
