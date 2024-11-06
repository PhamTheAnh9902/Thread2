package Main;

import WebService.StudentWsImpl;
import org.apache.log4j.BasicConfigurator;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Endpoint.publish("http://localhost:9999/Model/Student", new StudentWsImpl());
    }
}
