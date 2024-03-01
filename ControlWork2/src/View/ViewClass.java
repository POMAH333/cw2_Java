package View;

import java.util.Map;
import java.util.Scanner;

import Controller.Interfaces.iGetController;
import Controller.Interfaces.iGetView;
import Model.Domain.Toy;

/**
 * Класс отображения
 */
public class ViewClass implements iGetView {

    private iGetController contr; // Подключение контроллера

    public void setContr(iGetController contr) {
        this.contr = contr;
    }

    // Вывод на экран списка игрушек
    public void printAllToys(Map<Integer, Toy> toys) {
        System.out.println("-------------Список игрушек------------");
        for (Toy t : toys.values()) {
            System.out.println(t);
        }
        System.out.println("----------------------------------------");
        System.out.println("");
    }

    // Выполнение программы
    public void ViewRun() {
        contr.run();
    }

    // Запрос строки из консоли с выводом заданного сообщения
    public String prompt(String msg) {
        Scanner in = new Scanner(System.in);
        System.out.println(msg);
        return in.nextLine();
    }

}
