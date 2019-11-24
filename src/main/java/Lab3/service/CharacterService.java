package Lab3.service;

import Lab3.model.Character;
import Lab3.model.Weapon;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharacterService {
    private Character character;

    public CharacterService(Character character) {
        this.character = character;
    }

    /**
     *
     * @param weaponType type of weapon
     * @return count of a certain type
     */
    public long countWeaponType(Weapon.WeaponType weaponType) {
        return character.getWeapons().stream()
                .filter(c -> c.getWeaponType().equals(weaponType))
                .count();
    }

    /**
     *
     * @return sorted weapons by damage
     */
    public SortedSet<Weapon> sortWeaponsByDamage() {
        SortedSet<Weapon> sortedWeapons = new TreeSet<>((a, b) -> b.getDamage().compareTo(a.getDamage()));
        sortedWeapons.addAll(character.getWeapons());
        return sortedWeapons;
    }

    /**
     *
     * @return sorted weapons by MaxRange
     */
    public SortedSet<Weapon> sortedWeaponsByMaxRange() {
      /*  SortedSet<Weapon> sortedWeapons = new TreeSet<>(new Comparator<Weapon>() {
            @Override
            public int compare(Weapon a, Weapon b) {
                return  a.getMaxRange().compareTo(b.getMaxRange());
            }
        });*/
        SortedSet<Weapon> sortedWeapons = new TreeSet<>(Comparator.comparing(Weapon::getMaxRange));
        sortedWeapons.addAll(character.getWeapons());
        return sortedWeapons;
    }

    /**
     *
     * @param name we search weapon by this name
     * @return true if weapon exist, false if one does not exist
     *///is exist
    public boolean searchWeaponByName(String name) {
        Character result = new Character();
        character.getWeapons().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .forEach(result::addWeapon);

        return !result.getWeapons().isEmpty();
    }















}
