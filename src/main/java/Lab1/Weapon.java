package Lab1;

public class Weapon {

    public enum WeaponType {
        PISTOL,
        RIFLE,
        SNIPER_RIFLE
    }

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

        public Builder setWeight(Integer weight) throws IllegalArgumentException {
            if(weight < 0) throw new IllegalArgumentException("jdnvdfnjdfvnj");
            weapon.weight = weight;
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
