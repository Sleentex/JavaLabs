package Lab1.model;

import java.util.HashSet;
import java.util.Set;

public class Weapon {

    public enum WeaponType {
        PISTOL,
        RIFLE,
        SNIPER_RIFLE
    }

    // all fields cant be changed and assigned once in builder
    private String name;
    private WeaponType weaponType;
    private Integer weight;
    private Integer damage;
    private Integer ammo; //патрони
    private Integer rateOfFire; //задержка перед наступним вистрелом
    private Integer maxRange; //максимальна дальність

    private Weapon() {
        //Private constructor to deny creating new instance outside by constructor
    }

    public String getName() { return name; }
    public WeaponType getWeaponType() { return weaponType; }
    public Integer getWeight() { return weight; }
    public Integer getDamage() { return damage; }
    public Integer getAmmo() { return ammo; }
    public Integer getRateOfFire() { return rateOfFire; }
    public Integer getMaxRange() { return maxRange; }

    @Override
    public String toString() {
        return  name + " " +
                weaponType + " " +
                weight + "kg " +
                damage + "damage " +
                ammo + "ammo " +
                rateOfFire + "rateOfFire " +
                maxRange + "maxRange";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weapon weapon = (Weapon) o;

        if (name != null ? !name.equals(weapon.name) : weapon.name != null) return false;
        if (weaponType != weapon.weaponType) return false;
        return maxRange != null ? maxRange.equals(weapon.maxRange) : weapon.maxRange == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (weaponType != null ? weaponType.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (damage != null ? damage.hashCode() : 0);
        result = 31 * result + (ammo != null ? ammo.hashCode() : 0);
        result = 31 * result + (rateOfFire != null ? rateOfFire.hashCode() : 0);
        result = 31 * result + (maxRange != null ? maxRange.hashCode() : 0);
        return result;
    }

    /**
     * Use this to build new Weapon
     */
    public static class Builder {

        Weapon weapon;

        public Builder() {
            weapon = new Weapon();
        }

        public Builder setName(String name) {
            weapon.name = name;
            return this;
        }

        public Builder setWeaponType(WeaponType weaponType) {
            weapon.weaponType = weaponType;
            return this;
        }

        /**
         *
         * @param weight Integer
         * @return instance of this Builder
         * @throws IllegalArgumentException when try set weight lower than zero
         */
        public Builder setWeight(Integer weight) throws IllegalArgumentException {
            if(weight <= 0) throw new IllegalArgumentException("Weight must be greater than zero");
            weapon.weight = weight;
            return this;
        }

        /**
         *
         * @param damage Integer
         * @return instance of Builder
         * @throws IllegalArgumentException when try set damage lower than zero
         */
        public Builder setDamage(Integer damage) throws IllegalArgumentException {
            if(damage <= 0) throw new IllegalArgumentException("Damage must be greater than zero");
            weapon.damage = damage;
            return this;
        }

        /**
         *
         * @param ammo Integer
         * @return instance of Builder
         * @throws IllegalArgumentException when try set ammo lower than zero
         */
        public Builder setAmmo(Integer ammo) throws IllegalArgumentException {
            if(ammo <= 0) throw new IllegalArgumentException("Ammo must be greater than zero");
            weapon.ammo = ammo;
            return this;
        }

        /**
         *
         * @param rateOfFire Integer
         * @return instance of Builder
         * @throws IllegalArgumentException when try set rateOfFire lower than zero
         */
        public Builder setRateOfFire(Integer rateOfFire) throws IllegalArgumentException {
            if(rateOfFire < 0) throw new IllegalArgumentException("RateOfFire must be greater than zero");
            weapon.rateOfFire = rateOfFire;
            return this;
        }

        /**
         *
         * @param maxRange Integer
         * @return instance of Builder
         * @throws IllegalArgumentException when try set maxRange lower than zero
         */
        public Builder setMaxRange(Integer maxRange) throws IllegalArgumentException {
            if(maxRange < 0) throw new IllegalArgumentException("MaxRange must be greater than zero");
            weapon.maxRange = maxRange;
            return this;
        }

        /**
         *
         * @return instance of Weapon
         * @throws IllegalArgumentException if some fields do not set
         */
        public Weapon build() throws IllegalArgumentException {
            Set<String> emptyFields = new HashSet<String>();

            if(weapon.name == null) emptyFields.add("name");
            if(weapon.ammo == null) emptyFields.add("ammo");
            if(weapon.damage == null) emptyFields.add("damage");
            if(weapon.weight == null) emptyFields.add("weight");
            if(weapon.maxRange == null) emptyFields.add("maxRange");
            if(weapon.rateOfFire == null) emptyFields.add("rateOfFire");
            if(weapon.weaponType == null) emptyFields.add("weaponType");

            if(emptyFields.size() > 0) {
                String exceptionMessage = "The following fields must be initialized: " + emptyFields.toString();
                throw new IllegalArgumentException(exceptionMessage);
            }

            return weapon;
        }
    }
}
