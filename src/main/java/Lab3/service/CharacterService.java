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
        SortedSet<Weapon> sortedWeapons = new TreeSet<Weapon>((a, b) -> {
            return b.getDamage().compareTo(a.getDamage());
        });
        sortedWeapons.addAll(character.getWeapons());
        return sortedWeapons;
    }

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

    public boolean searchWeaponsByName(String name) {
        Character result = new Character();
        character.getWeapons().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .forEach(result::addWeapon);

        return !result.getWeapons().isEmpty();
    }















}
