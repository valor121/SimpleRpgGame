public class Monster extends BattleSystem {
    private final int goldDrop;

    public Monster(String name, int level, int HP, int attack, int defend, int goldDrop, int itemDrop, int xpDrop) {
        super(name, level, HP, attack, defend, goldDrop, itemDrop, xpDrop);
        this.goldDrop = goldDrop;
    }

    public int getGoldDrop() {
        return goldDrop;
    }

    // No need to override getXP and setAttack methods
}
