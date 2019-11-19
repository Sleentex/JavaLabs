package Lab3;

import Lab3.model.Character;
import Lab3.model.Weapon;
import Lab3.service.CharacterService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class TestCharacterService {
    private CharacterService characterService;
    private Character character;
    private Weapon weaponEagle;
    private Weapon weaponM4A1;
    private Weapon weaponAWP;
    private Weapon weaponAK47;

    {
        weaponEagle = new Weapon.Builder()
                .setName("Desert Eagle")
                .setWeaponType(Weapon.WeaponType.PISTOL)
                .setDamage(8)
                .setWeight(3)
                .setAmmo(30)
                .setMaxRange(200)
                .setRateOfFire(2)
                .build();

        weaponAK47 = new Weapon.Builder()
                .setName("AK-47")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(5)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(450)
                .setRateOfFire(1)
                .build();

        weaponM4A1 = new Weapon.Builder()
                .setName("M4A1")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(4)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(400)
                .setRateOfFire(1)
                .build();

        weaponAWP = new Weapon.Builder()
                .setName("AWP")
                .setWeaponType(Weapon.WeaponType.SNIPER_RIFLE)
                .setDamage(12)
                .setWeight(5)
                .setAmmo(30)
                .setMaxRange(600)
                .setRateOfFire(3)
                .build();
    }

    @BeforeMethod
    public void createCharacter() {
        character = new Character("Naruto");
    }

    @BeforeMethod
    public void createCharacterService() {
        characterService = new CharacterService(character);
    }

    @Test
    public void sortWeaponsByDamage(){
        character.addWeapon(weaponAK47);
        character.addWeapon(weaponAWP);

        SortedSet<Weapon> sortedWeapons = characterService.sortWeaponsByDamage();
        SortedSet<Weapon> expectedSortedWeapons = new TreeSet<>();
        expectedSortedWeapons.add(weaponAWP);
        expectedSortedWeapons.add(weaponAK47);

        Assert.assertEquals(expectedSortedWeapons, sortedWeapons);
       /* SortedSet<Weapon> sortedWeapons = characterService.sortWeaponsByDamage();
        List<Object> actual = new ArrayList<>(sortedWeapons);
        System.out.println(actual);*/
    }
    @Test
    public void sortWeaponsByMaxRange() {
        character.addWeapon(weaponAK47);
        character.addWeapon(weaponM4A1);

        Object[] sortedWeapons = characterService.sortedWeaponsByMaxRange().toArray();
        Object[] expectedSortedWeapons = { weaponM4A1, weaponAK47 };
        Assert.assertEquals(expectedSortedWeapons, sortedWeapons);
    }

    @Test
    public void countWeaponTypeTest() {
        character.addWeapon(weaponAK47);
        character.addWeapon(weaponM4A1);

        long expectedCountWeaponType = 2;
        long countWeaponType = characterService.countWeaponType(Weapon.WeaponType.RIFLE);
        Assert.assertEquals(expectedCountWeaponType, countWeaponType);
    }

    @Test
    public void searchWeaponsByName() {
        character.addWeapon(weaponEagle);
        character.addWeapon(weaponAK47);

        boolean actual = characterService.searchWeaponByName("Desert Eagle");
        Assert.assertTrue(actual);
    }
}
