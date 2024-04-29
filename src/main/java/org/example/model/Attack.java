package org.example.model;

public class Attack {
    private final int id;
    private final String name;
    private final String effect;
    private final String type;
    private final String kind;
    private final int power;
    private final String accuracy;
    private final int pp;

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
