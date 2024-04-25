package org.example;

public class Attack {
    private int id;
    private String name;
    private String effect;
    private String type;
    private String kind;
    private int power;
    private String accuracy;
    private int pp;

    public Attack(int id, String name, String effect, String type, String kind, int power, String accuracy, int pp) {
        this.id = id;
        this.name = name;
        this.effect = effect;
        this.type = type;
        this.kind = kind;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", effect='" + effect + '\'' +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", power=" + power +
                ", accuracy='" + accuracy + '\'' +
                ", pp=" + pp +
                '}';
    }
}
