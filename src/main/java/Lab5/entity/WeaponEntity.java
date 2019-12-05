package Lab5.entity;

import Lab5.model.Weapon;

public class WeaponEntity {

    public enum WeaponType1 {
        PISTOL,
        RIFLE,
        SNIPER_RIFLE
    }

    private String name;
    private Weapon.WeaponType weaponType;
    private Integer weight;
    private Integer damage;
    private Integer ammo; //патрони
    private Integer rateOfFire; //задержка перед наступним вистрелом
    private Integer maxRange; //максимальна дальність

    public WeaponEntity(Weapon weapon, Integer ammo) {
        this.name = weapon.getName();
        this.weaponType = weapon.getWeaponType();
        this.ammo = ammo;
    }

}
