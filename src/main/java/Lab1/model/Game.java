package Lab1.model;

import Lab1.service.CharacterService;

public class Game {
    private CharacterService characterService1;
    private CharacterService characterService2;
    private Character firstCharacter;
    private Character secondCharacter;

    public Game(Character firstCharacter, Character secondCharacter) {
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
        characterService1 = new CharacterService(firstCharacter);
        characterService2 = new CharacterService(secondCharacter);
    }

    /**
     *
     * @param distance distance between players
     * @return winner player
     */
    public Character fight(Integer distance) {
        Integer currentDistance = distance;
        Integer i = 0;

        while(true) {
            Weapon firstWeapon = characterService1.getEffectiveWeapon(currentDistance);
            Weapon secondWeapon = characterService2.getEffectiveWeapon(currentDistance);
            if(firstWeapon != null && i % firstWeapon.getRateOfFire() == 0) {
                secondCharacter.setHealth(secondCharacter.getHealth() - firstWeapon.getDamage());//змешити хп у 2перонажа і  перевірити чи у нього стало не менше 0
                if(secondCharacter.getHealth() <= 0) return firstCharacter;
            }

            if(secondWeapon != null && i % secondWeapon.getRateOfFire() == 0) {
                firstCharacter.setHealth(firstCharacter.getHealth() - secondWeapon.getDamage());
                if(firstCharacter.getHealth() <= 0) return secondCharacter;
            }
            currentDistance--;
            i++;
        }

    }
}
