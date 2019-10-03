package Lab1;

public class Weapon {

    public enum WeaponType {
        PISTOL,
        RIFLE,
        SNIPER_RIFLE
    }

    private String name;
    private WeaponType weaponType;
    private float weight;
    private int damage;
    private Integer ammo; //патрони
    private Integer rateOfFire; //скорострільність
    private Integer maxRange; //максимальна дальність

    private Weapon() {
        //Private constructor to deny creating new instance outside by constructor
    }

    public String getName() { return name; }
    public WeaponType getWeaponType() { return weaponType; }
    public float getWeight() { return weight; }
    public Integer getDamage() { return damage; }
    public Integer getAmmo() { return ammo; }
    public Integer getRateOfFire() { return rateOfFire; }
    public Integer getMaxRange() { return maxRange; }


    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", weaponType=" + weaponType +
                ", weight=" + weight +
                ", damage=" + damage +
                ", ammo=" + ammo +
                ", rateOfFire=" + rateOfFire +
                ", maxRange=" + maxRange +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weapon weapon = (Weapon) o;

        if (Float.compare(weapon.weight, weight) != 0) return false;
        if (Float.compare(weapon.damage, damage) != 0) return false;
        if (name != null ? !name.equals(weapon.name) : weapon.name != null) return false;
        if (weaponType != weapon.weaponType) return false;
        if (ammo != null ? !ammo.equals(weapon.ammo) : weapon.ammo != null) return false;
        if (rateOfFire != null ? !rateOfFire.equals(weapon.rateOfFire) : weapon.rateOfFire != null) return false;
        return maxRange != null ? maxRange.equals(weapon.maxRange) : weapon.maxRange == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (weaponType != null ? weaponType.hashCode() : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + (damage != +0.0f ? Float.floatToIntBits(damage) : 0);
        result = 31 * result + (ammo != null ? ammo.hashCode() : 0);
        result = 31 * result + (rateOfFire != null ? rateOfFire.hashCode() : 0);
        result = 31 * result + (maxRange != null ? maxRange.hashCode() : 0);
        return result;
    }

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

        public Builder setMaxWeight(float maxWeight) {
            weapon.weight = maxWeight;
            return this;
        }

        public Builder setDamage(Integer damage) {
            weapon.damage = damage;
            return this;
        }

        public Builder setAmmo(Integer ammo) {
            weapon.ammo = ammo;
            return this;
        }

        public Builder setRateOfFire(Integer rateOfFire) {
            weapon.rateOfFire = rateOfFire;
            return this;
        }

        public Builder setMaxRange(Integer maxRange) {
            weapon.maxRange = maxRange;
            return this;
        }

        public Weapon build() {
            return weapon;
        }
    }
}
