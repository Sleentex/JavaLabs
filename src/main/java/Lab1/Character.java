package Lab1;

import java.util.HashSet;
import java.util.Set;

public class Character {
    private static float maxWeight;
    private static int maxHealth;
    private Set<Weapon> weapons;
    private int health;
    private String name;


    {
        weapons = new HashSet<Weapon>();
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }
//інший метод   тут міняти серий знає)))
    public boolean addWeapon(Weapon weapon) {
        int sum = 0;
        for (Weapon x : weapons) {
            sum += x.getWeight();
        }
        if(sum + weapon.getWeight() > maxWeight)
            return false;

        weapons.add(weapon);

        return true;
    }
//чи є такого типу і скільки   сортувати по дамадж разом

}
