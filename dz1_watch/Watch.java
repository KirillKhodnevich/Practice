import java.util.ArrayList;
import java.util.List;

public class Watch {
    private Module activeModule;
    private List<Module> modules = new ArrayList<>();

    public Watch(Module module1, Module module2) {
        this.activeModule = module2;
        modules.add(module1);
        modules.add(module2);
    }

    /*Уровень 1: Базовый
    Отображение времени*/
    public void functionA() {
        String time = activeModule.currentTime();
        System.out.println(time);
    }

    //Смена модуля
    public void functionB(Module newModule) {
        this.activeModule = newModule;
    }

    /*Уровень 2: Продвинутый
    Циклическое переключение модулей (1 уровень)*/
    public void functionC1(Module module1, Module module2) {
        if (this.activeModule == module1) {
            this.activeModule = module2;
        }
        else if (this.activeModule == module2) {
            this.activeModule = module1;
        }
    }

    //Циклическое переключение модулей (2 уровень)
    public void functionC2() {
        for (int i = 0; i < this.modules.size(); i++) {
            if (this.activeModule == this.modules.get(i)) {
                if (i + 1 == this.modules.size()) {
                    this.activeModule = this.modules.get(0);
                    break;
                } else {
                    this.activeModule = this.modules.get(i + 1);
                    break;
                }
            }
        }
    }
}