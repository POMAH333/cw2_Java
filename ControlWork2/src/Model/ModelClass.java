package Model;

import java.util.HashMap;
import java.util.Map;

import Controller.Interfaces.iGetModel;
import Model.Domain.Toy;

public class ModelClass implements iGetModel {

    private Map<Integer, Toy> toys = new HashMap<Integer, Toy>();

    public ModelClass() {
    }

    public Map<Integer, Toy> getToys() {
        return toys;
    }

    public void setToys(Toy toy) {
        this.toys.put(toy.getId(), toy);
    }

    public boolean delToys(Integer num) {

        if (toys.containsKey(num)) {
            toys.remove(num);
            return true;
        }
        return false;
    }

}
