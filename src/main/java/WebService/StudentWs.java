package WebService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface StudentWs {
    @WebMethod
    String addStudentReal(@WebParam String username,@WebParam String password,@WebParam String name,
                          @WebParam int age,@WebParam String code,@WebParam String className
                        ,@WebParam String address,@WebParam double mark);
}
