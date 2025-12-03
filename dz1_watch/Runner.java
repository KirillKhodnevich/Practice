import java.util.ArrayList;

public class Runner {
    public static void main() {
        //1 уровень
        Module module12 = new Hour12();
        Module module24 = new Hour24();
        //2 уровень
        Watch watch = new Watch(module12, module24);

        watch.functionA();//20:44
        watch.functionB(module12);
        watch.functionA();//8:44 PM

        watch.functionC1(module12, module24);
        watch.functionA();//20:44

        watch.functionC2();
        watch.functionA();//8:44 PM
    }
}