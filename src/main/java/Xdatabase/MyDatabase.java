package Xdatabase;

import File.Xfile;
import Model.Database;
import Model.Student;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDatabase {
    private Database database = new Database();
    private Xfile xfile = new Xfile();
    private static final Logger LOGGER = Logger.getLogger(MyDatabase.class);

    public boolean checkClassName(Connection connection, String className)  {
        String sql = "SELECT class.name FROM class WHERE class.name = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,className);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else return false;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public boolean insertStudent(Connection connection, Student student){
        try{
            String sqlinsert = "INSERT INTO student(name,code,age,className,address,mark) VALUES(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlinsert);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getCode());
            preparedStatement.setInt(3,student.getAge());
            preparedStatement.setString(4,student.getClassName());
            preparedStatement.setString(5,student.getAddress());
            preparedStatement.setDouble(6,student.getMark());
            int check = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            if(check > 0){
                return true;
            }
            else return false;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
