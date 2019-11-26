package Lab5.model;


import java.util.HashSet;
import java.util.Set;

public class Character {
    private Long id;
    private Set<Weapon> weapons;
    private Integer health;
    private String name;
    private static Integer maxWeight = 10;
    private static Integer maxHealth = 100;

    {
        weapons = new HashSet<Weapon>();
        health = 100;
    }

    public Character() {}

    public Character(String name) {
        this.name = name;
    }

    public void setHealth(Integer health) {
        if(health > maxHealth)
            this.health = maxHealth;
        else
            this.health = health;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     *
     * @return sum of all weapon weight
     */
    public Integer getAllWeaponWeight() {
        int sum = 0;
        for (Weapon x : weapons) {
            sum += x.getWeight();
        }
        return sum;
    }

    /**
     *
     * @param weapon Weapon
     * @return true if can add weapon, false if weight of weapon is more than maxWeight
     */
    public boolean addWeapon(Weapon weapon) {
        if(getAllWeaponWeight() + weapon.getWeight() > maxWeight)
            return false;

        weapons.add(weapon);
        return true;
    }
}
