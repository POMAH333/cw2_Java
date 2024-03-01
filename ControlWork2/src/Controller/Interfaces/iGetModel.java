package Controller.Interfaces;

import java.util.Map;

import Model.Domain.Toy;

public interface iGetModel {
   public Map<Integer, Toy> getToys();

   public void setToys(Toy toy);

   public boolean delToys(Integer num);
}
