package Controller.Interfaces;

import java.util.Map;

import Model.Domain.Toy;

public interface iGetView {

    public void printAllToys(Map<Integer, Toy> toys); // Вывод на экран списка игрушек

    public void ViewRun(); // Выполнение программы

    public String prompt(String msg); // Запрос строки из консоли с выводом заданного сообщения

    public void setContr(iGetController contr);
}
