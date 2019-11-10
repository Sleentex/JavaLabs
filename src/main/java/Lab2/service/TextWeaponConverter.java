package Lab2.service;

import Lab2.exception.ConvertException;
import Lab2.model.Weapon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextWeaponConverter implements Converter<Weapon> {
    private final Integer COUNT_FIELDS = 7;

    private Object[] getWeaponFields(Weapon weapon) {
        return new Object[] {
                weapon.getName(), weapon.getWeaponType(), weapon.getWeight(), weapon.getDamage(), weapon.getAmmo(),
                weapon.getRateOfFire(), weapon.getMaxRange()
        };
    }

    @Override
    public String serializeToString(Weapon weapon) throws ConvertException {
        try {
            Object[] weaponFields = getWeaponFields(weapon);
            List<String> fields = Arrays.stream(weaponFields)
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return String.join("||", fields);
        }
        catch (Exception e){
            throw new ConvertException(e.getMessage());
        }
    }

    @Override
    public Weapon deserializeString(String txtString) throws ConvertException {
        try {
            String[] arrTxt = txtString.split("\\|\\|");

            if(arrTxt.length != COUNT_FIELDS)
                throw new Exception("Length don`t equal 7!");

            Weapon weapon = new Weapon.Builder()
                    .setName(arrTxt[0])
                    .setWeaponType(Weapon.WeaponType.valueOf(arrTxt[1]))
                    .setWeight(Integer.parseInt(arrTxt[2]))
                    .setDamage(Integer.parseInt(arrTxt[3]))
                    .setAmmo(Integer.parseInt(arrTxt[4]))
                    .setRateOfFire(Integer.parseInt(arrTxt[5]))
                    .setMaxRange(Integer.parseInt(arrTxt[6]))
                    .build();

            return weapon;
        }
        catch (Exception e) {
            throw new ConvertException(e.getMessage());
        }
    }
}
