package Lab4.model;

import Lab4.service.IntSize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(builder = Weapon.Builder.class)
public class Weapon implements Serializable {

    public enum WeaponType {
        PISTOL,
        RIFLE,
        SNIPER_RIFLE
    }

    // all fields cant be changed and assigned once in builder

    @NotNull(message = "must be not null")
    private Integer id;


    @NotNull(message = "must be not null")
    @Size(min = 1, max = 20, message = "must be not empty")
    private String name;

    @NotNull(message = "must be not null")
    private WeaponType weaponType;

    @NotNull(message = "must be not null")
    @IntSize(min = 1, max = 10)
    private Integer weight;

    @NotNull(message = "must be not null")
    @IntSize(min = 1, max = 100)
    private Integer damage;

    @NotNull(message = "must be not null")
    @Min(value = 0, message = "can`t be less than 0")
    @Max(value = 90, message = "can`t be more than 90")
    private Integer ammo; //патрони

    @NotNull(message = "must be not null")
    @IntSize(min = 0, max = 10)
    private Integer rateOfFire; //задержка перед наступним вистрелом

    @NotNull(message = "must be not null")
    @IntSize(min = 0, max = 1000)
    private Integer maxRange; //максимальна дальність

    private Weapon() {
        //Private constructor to deny creating new instance outside by constructor
    }

    public Integer getId() { return id; }
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", weaponType=" + weaponType +
                ", weight=" + weight + "kg" +
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

        if (id != null ? !id.equals(weapon.id) : weapon.id != null) return false;
        if (name != null ? !name.equals(weapon.name) : weapon.name != null) return false;
        if (weaponType != weapon.weaponType) return false;
        if (weight != null ? !weight.equals(weapon.weight) : weapon.weight != null) return false;
        if (damage != null ? !damage.equals(weapon.damage) : weapon.damage != null) return false;
        if (ammo != null ? !ammo.equals(weapon.ammo) : weapon.ammo != null) return false;
        if (rateOfFire != null ? !rateOfFire.equals(weapon.rateOfFire) : weapon.rateOfFire != null) return false;
        return maxRange != null ? maxRange.equals(weapon.maxRange) : weapon.maxRange == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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

        public Builder setId(Integer id) {
            weapon.id = id;
            return this;
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
         */
        public Builder setWeight(Integer weight) {
            weapon.weight = weight;
            return this;
        }

        /**
         *
         * @param damage Integer
         * @return instance of Builder
         */
        public Builder setDamage(Integer damage) throws IllegalArgumentException {
            weapon.damage = damage;
            return this;
        }

        /**
         *
         * @param ammo Integer
         * @return instance of Builder
         */
        public Builder setAmmo(Integer ammo) throws IllegalArgumentException {
            weapon.ammo = ammo;
            return this;
        }

        /**
         *
         * @param rateOfFire Integer
         * @return instance of Builder
         */
        public Builder setRateOfFire(Integer rateOfFire) throws IllegalArgumentException {
            weapon.rateOfFire = rateOfFire;
            return this;
        }

        /**
         *
         * @param maxRange Integer
         * @return instance of Builder
         */
        public Builder setMaxRange(Integer maxRange) throws IllegalArgumentException {
            weapon.maxRange = maxRange;
            return this;
        }

        /**
         *
         * @return instance of Weapon
         * @throws IllegalStateException if some fields do not set
         */
        public Weapon build() throws IllegalStateException {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Weapon>> constraintViolations = validator.validate(weapon); // проверка всех ограничений

            if (constraintViolations.size() > 0) {
                Set<String> exceptionDetails = new HashSet<>();
                for (ConstraintViolation<Weapon> violation : constraintViolations) {
                    exceptionDetails.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
                }
                throw new IllegalStateException(exceptionDetails.toString());
            }
            return weapon;
            /*Set<String> emptyFields = new HashSet<String>();

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
            return weapon;*/
        }
    }
}
