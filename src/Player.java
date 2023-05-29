public class Player extends BattleSystem {
    private int gold;

    public Player(String name, int level, int HP, int attack, int defend, int goldDrop, int itemDrop, int xpDrop, int gold) {
        super(name, level, HP, attack, defend, goldDrop, itemDrop, xpDrop);
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void resetHP() {
        setHP(100);
    }

    @Override
    public void setHP(int HP) {
        super.setHP(HP);
        if (HP <= 0) {
            System.out.println("You died! \nGame over!");
            System.exit(0);
        }
    }
}
