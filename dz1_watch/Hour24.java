import java.text.SimpleDateFormat;
import java.util.Date;

public class Hour24 implements Module {
    public String currentTime() {
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        return date.format(new Date());
    }
}