package Lab1;

import java.util.HashSet;
import java.util.Set;

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

    public Weapon getBestWeapon(Integer distance) {
        Weapon result = null;
        for(Weapon x : weapons) {
            if(x.getMaxRange() >= distance) {
                if(result == null) {
                    result = x;
                    continue;
                }
                float efficiency1 = x.getDamage() / (float) x.getRateOfFire();
                float efficiency2 = result.getDamage() / (float) result.getRateOfFire();
                if(efficiency1 > efficiency2) result = x;
            }
        }
        return result;
    }
//чи є такого типу і скільки   сортувати по дамадж разом

}
