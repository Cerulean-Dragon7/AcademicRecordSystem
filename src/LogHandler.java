import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHandler {
    private static Logger logger = Logger.getLogger("MyLog");

    public static void init(){
        FileHandler fh;
        try{
            String userDirectory = System.getProperty("user.dir");
            fh = new FileHandler(userDirectory+"log.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


            logger.info("Logger Initialized");
        } catch (Exception e) {
            logger.log(Level.WARNING,"Exception", e);
        }
    }
    public static  void error(Exception e){
        logger.log(Level.WARNING,"Exception", e);
    }
}
