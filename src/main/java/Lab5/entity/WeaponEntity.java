package Lab5.entity;

import Lab5.model.Weapon;

public class WeaponEntity {
    private Weapon weapon;
    private Integer ammo;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Integer getAmmo() {
        return ammo;
    }

    public void setAmmo(Integer ammo) {
        this.ammo = ammo;
    }

    @Override
    public String toString() {
        return "CharacterWeapon{" +
                "weapon=" + weapon +
                ", ammo=" + ammo +
                '}';
    }
}
