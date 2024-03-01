package Model.Domain;

public class Toy {
    private int id;
    private String name;
    private int count;
    private double freq;

    public Toy(int id, String name, int count, double freq) {
        this.id = id;
        this.name = name;
        if (count >= 0) {
            this.count = count;
        } else {
            this.count = 0;
        }
        this.freq = freq;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Toy: " + id + " " + name + " " + count + " " + freq;
    }

}
