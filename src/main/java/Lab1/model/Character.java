package Lab1.model;

import java.util.*;

public class Character {
    private static Integer maxWeight = 10;
    private static Integer maxHealth = 100;
    private Set<Weapon> weapons;
    private Integer health;
    private String name;

    {
        weapons = new HashSet<Weapon>();
        health = 100;
    }

    public Character(String name) {
        this.name = name;
    }

    public void setHealth(Integer health) {
        if(health > maxHealth)
            this.health = maxHealth;
        else
            this.health = health;
    }

    public Integer getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public static void setMaxWeight(int maxWeight) {
        Character.maxWeight = maxWeight;
    }

    public static Integer getMaxWeight() {
        return maxWeight;
    }

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public Integer getAllWeaponWeight() {
        int sum = 0;
        for (Weapon x : weapons) {
            sum += x.getWeight();
        }
        return sum;
    }

    public boolean addWeapon(Weapon weapon) {
        if(getAllWeaponWeight() + weapon.getWeight() > maxWeight)
            return false;

        weapons.add(weapon);
        return true;
    }
}
