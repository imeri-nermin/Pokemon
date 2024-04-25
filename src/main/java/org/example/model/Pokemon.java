package org.example;

public class Pokemon {
    private int id;
    private String name;
    private String type1;
    private String type2;
    private int total;
    private int hp;
    private int attack;
    private int defense;
    private int spAtk;
    private int spDef;
    private int speed;
    private Attack attack1;
    private Attack attack2;
    private int level;

    public Pokemon(int id, String name, String type1, String type2, int total, int hp, int attack, int defense, int spAtk, int spDef, int speed, int level) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.level = level;
        this.hp = scaleStatHP(hp, level);
        this.attack = scaleStat(attack, level);
        this.defense = defense;
        this.spAtk = scaleStat(spAtk, level);
        this.spDef = spDef;
        this.speed = scaleStat(speed, level);
    }
    private int scaleStat(int baseStat, int level) {
        double scalingFactor = 2;
        return (int) (baseStat * scalingFactor * level);
    }
    private int scaleStatHP(int baseStat, int level) {
        double scalingFactor = 1.05;
        return (int) (baseStat * scalingFactor * level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHP() {
        return hp;
    }

    public void setAttack1(Attack attack1) {
        this.attack1 = attack1;
    }

    public void setAttack2(Attack attack2) {
        this.attack2 = attack2;
    }

    public Attack getAttack1() {
        return attack1;
    }

    public Attack getAttack2() {
        return attack2;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public void reduceHP(double damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage values can not be negative");
        }
        this.hp -= (int)damage;

        if (this.hp < 0) {
            this.hp = 0;
        }
    }


    @Override
    public String toString() {
        return "Pokemon: [ID=" + id + ", Name=" + name + ", Type 1=" + type1 + ", Type 2=" + type2 + ", Total=" + total +
                ", HP=" + hp + ", Attack=" + attack + ", Defense=" + defense + ", Sp. Atk=" + spAtk + ", Sp. Def=" +
                spDef + ", Speed=" + speed + "]";
    }
}