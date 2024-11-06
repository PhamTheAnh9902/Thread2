package WebService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.sql.SQLException;

@WebService
public interface ClassWebService {
    @WebMethod
    String addClass(@WebParam String username,@WebParam String password,
                    @WebParam String name,@WebParam String code) throws IOException, ClassNotFoundException, SQLException;
}
