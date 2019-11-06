package Lab1.service;

import Lab1.model.Weapon;
import Lab1.model.Character;

import java.util.SortedSet;
import java.util.TreeSet;

public class CharacterService {
    private Character character;

    public CharacterService(Character character) {
        this.character = character;
    }
    // best = effect
    public Weapon getEffectWeapon(Integer distance) {
        Weapon result = null;
        for(Weapon x : character.getWeapons()) {
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

    public SortedSet<Weapon> sortWeaponsByDamage() {
        SortedSet<Weapon> sortedWeapons = new TreeSet<Weapon>((a, b) -> {
            return b.getDamage().compareTo(a.getDamage());
        });
        sortedWeapons.addAll(character.getWeapons());
        return sortedWeapons;
    }

    public Integer countWeaponType(Weapon.WeaponType weaponType) {
        Integer countWeaponType = 0;
        for(Weapon x: character.getWeapons()) {
            if(x.getWeaponType().equals(weaponType))  countWeaponType++;
        }
        return countWeaponType;
    }
}
