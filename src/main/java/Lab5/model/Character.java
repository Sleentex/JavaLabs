package Lab5.model;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Character {
    private Long id;
    private String name;
    private Integer health;
    private Set<CharacterWeapon> characterWeapons;

    private static Integer maxWeight = 10;
    private static Integer maxHealth = 100;

    {
        characterWeapons = new HashSet<CharacterWeapon>();
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
        return characterWeapons.stream().map(CharacterWeapon::getWeapon).collect(Collectors.toSet());
    }

    public Set<CharacterWeapon> getCharacterWeapons() {
        return characterWeapons;
    }

    public void setCharacterWeapons(Set<CharacterWeapon> characterWeapons) {
        this.characterWeapons = characterWeapons;
    }

    public Optional<CharacterWeapon> getCharacterInfo(Character character) {
        return characterWeapons.stream()
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

        CharacterWeapon characterWeapon = new CharacterWeapon();
        characterWeapon.setWeapon(weapon);
        characterWeapon.setAmmo(ammo);
        characterWeapons.add(characterWeapon);
        return true;
    }
}
