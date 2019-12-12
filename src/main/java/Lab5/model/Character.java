package Lab5.model;


import Lab5.entity.WeaponEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Character {
    private Long id;
    private String name;
    private Integer health;
    private Set<WeaponEntity> weaponEntities;

    private static Integer maxWeight = 10;
    private static Integer maxHealth = 100;

    {
        weaponEntities = new HashSet<WeaponEntity>();
        health = 100;
    }

    public Character() {}

    public Character(String name) {
        this.name = name;
    }

    public Character(Long id, String name, Integer health) {
        this.id = id;
        this.name = name;
        this.health = health;
    }

    public Character(String name, Integer health) {
        this.name = name;
        this.health = health;
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

    public void setName(String name) { this.name = name; }

    public static void setMaxWeight(int maxWeight) {
        Character.maxWeight = maxWeight;
    }

    public static Integer getMaxWeight() {
        return maxWeight;
    }

    public Set<Weapon> getWeapons() {
        return weaponEntities.stream().map(WeaponEntity::getWeapon).collect(Collectors.toSet());
    }

    public Set<WeaponEntity> getWeaponEntities() {
        return weaponEntities;
    }

    public void setWeaponEntities(Set<WeaponEntity> weaponEntities) {
        this.weaponEntities = weaponEntities;
    }

    public Optional<WeaponEntity> getCharacterInfo(Character character) {
        return weaponEntities.stream()
                .filter(e -> e.getWeapon().equals(character))
                .findAny();
    }

    /**
     *
     * @return sum of all weapon weight
     */
    public Integer getAllWeaponWeight() {
        int sum = 0;
        for (Weapon x : getWeapons()) {
            sum += x.getWeight();
        }
        return sum;
    }

    /**
     *
     * @param weapon Weapon
     * @return true if can add weapon, false if weight of weapon is more than maxWeight
     */
    public boolean addWeapon(Weapon weapon, Integer ammo) {
        if(getAllWeaponWeight() + weapon.getWeight() > maxWeight)
            return false;

        WeaponEntity weaponEntity = new WeaponEntity();
        weaponEntity.setWeapon(weapon);
        weaponEntity.setAmmo(ammo);
        weaponEntities.add(weaponEntity);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) &&
                Objects.equals(name, character.name) &&
                Objects.equals(health, character.health) &&
                Objects.equals(weaponEntities, character.weaponEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, health, weaponEntities);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", characterWeapons=" + weaponEntities +
                '}';
    }
}
