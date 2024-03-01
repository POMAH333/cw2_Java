package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import Controller.Interfaces.iGetController;
import Controller.Interfaces.iGetModel;
import Controller.Interfaces.iGetView;
import Model.Domain.Toy;

/**
 * Класс контроллера
 */
public class ControllerClass implements iGetController {

    private iGetModel model;
    private iGetView view;

    private Deque<Toy> prizToys = new ArrayDeque<>();

    /**
     * Конструктор класса.
     * Добавляет переданные отображение и модель.
     * 
     * @param model - Модель
     * @param view  - Отображение
     */
    public ControllerClass(iGetModel model, iGetView view) {
        this.model = model;
        this.view = view;
    }

    // Метод запуска контроллера
    public void run() {
        boolean getNewIter = true;

        // Выбор действия в зависимости от команды введённой пользователем
        while (getNewIter) {
            System.out.println("------------------------------");
            String com = view.prompt("Введите команду: ");
            com = com.toUpperCase();
            switch (com) {
                case "EXIT": // Выход из программы
                    getNewIter = false;
                    System.out.println("Программа завершена");
                    System.out.println("______________________________");
                    break;
                case "LIST": // Просмотр списка игрушек
                    view.printAllToys(model.getToys());
                    break;
                case "PUT":
                    putToy();
                    break;
                case "RAT":
                    freqToys();
                    break;
                case "PLAY":
                    toyChoice();
                    break;
                case "GET":
                    deliveryToy();
                    break;
                default:
                    System.out.println("Команда не поддерживается");
                    System.out.println("______________________________");
            }
        }
    }

    public void putToy() {
        String strToy = view.prompt("Введите параметры игрушки: ");
        String[] fieldToy = strToy.split(" ");
        Toy toy;
        try {
            toy = new Toy(Integer.parseInt(fieldToy[0]),
                    fieldToy[2],
                    Integer.parseInt(fieldToy[1]), 0);
            model.setToys(toy);
            System.out.println("");
            view.printAllToys(model.getToys());
        } catch (Exception e) {
            System.out.println("Введены неверные параметры");
            System.out.println("______________________________");
        }
    }

    public void freqToys() {
        double maxFreq = 1.0;
        String s = "";
        double freq;
        int end = model.getToys().size();
        try {
            for (Toy t : model.getToys().values()) {
                if (end > 1) {
                    if (maxFreq > 0) {
                        s = view.prompt("Введите весовой коэффициент в %, для " + t.getName() +
                                " (максимум " + (maxFreq * 100) + ")   ");
                        freq = Double.parseDouble(s) / 100;
                        if (freq < maxFreq) {
                            t.setFreq(freq);
                            model.setToys(t);
                            maxFreq -= freq;
                            end--;
                        } else {
                            System.out.println("Введённое значение больше максимального.");
                            System.out.println("Данному элементу присвоено максимальное значение.");
                            System.out.println("Прочие элементы получат весовой коэффициент равный нулю.");
                            t.setFreq(maxFreq);
                            model.setToys(t);
                            maxFreq = 0;
                            end--;
                        }
                    } else {
                        t.setFreq(0);
                        model.setToys(t);
                    }
                } else {
                    System.out.println("Весовой коэффициент для " + t.getName() +
                            " установлен " + (maxFreq * 100));
                    t.setFreq(maxFreq);
                    model.setToys(t);
                    maxFreq = 0;
                }
            }
            System.out.println("");
            view.printAllToys(model.getToys());
        } catch (Exception e) {
            System.out.println("Введены неверные параметры");
            System.out.println("______________________________");
        }
    }

    public void toyChoice() {
        Random r = new Random();
        Double randToy = r.nextDouble();
        Double freq = 0.0;
        for (Toy t : model.getToys().values()) {
            freq += t.getFreq();
            if (randToy < freq && t.getCount() > 0) {
                prizToys.push(t);
                t.setCount(t.getCount() - 1);
                System.out.println("");
                System.out.println("Добавлена игрушка: " + t.getId() + " " + t.getName());
                return;
            }
        }
    }

    public void deliveryToy() {
        String file = "delivery.txt";
        if (prizToys.size() > 0) {
            Toy t = prizToys.pollLast();
            String dataString = t.getId() + "   " + t.getName();
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(dataString);
                fileWriter.write("\n");
                System.out.println("");
                System.out.println("Игрушка: " + t.getName() + ", получена");
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл");
            }
        } else {
            System.out.println("Отсутствуют игрушки для получения");
        }
    }

}
