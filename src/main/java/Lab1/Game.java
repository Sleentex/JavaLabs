package Lab1;

public class Game {
    private Character firstCharacter;
    private Character secondCharacter;

    public Game(Character firstCharacter, Character secondCharacter) {
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
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
            Weapon firstWeapon = firstCharacter.getBestWeapon(currentDistance);
            Weapon secondWeapon = secondCharacter.getBestWeapon(currentDistance);
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
