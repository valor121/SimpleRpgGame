public abstract class BattleSystem {
    private String name;
    private int level;
    private int HP;
    private int XP;
    private int attack;
    private int defend;
    private int goldDrop;
    private int itemDrop;
    private int expDrop;

    public BattleSystem(String name, int XP, int HP, int attack, int defend, int goldDrop, int itemDrop, int expDrop) {
        this.name = name;
        this.level = 1;
        this.HP = HP;
        this.XP = XP;
        this.attack = attack;
        this.defend = defend;
        this.goldDrop = goldDrop;
        this.itemDrop = itemDrop;
        this.expDrop = expDrop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefend() {
        return defend;
    }
}
