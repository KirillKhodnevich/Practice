import java.text.SimpleDateFormat;
import java.util.Date;

public class Hour12 implements Module {
    public String currentTime() {
        SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss a");
        return date.format(new Date());
    }
}