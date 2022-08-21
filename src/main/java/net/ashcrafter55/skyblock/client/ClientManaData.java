package net.ashcrafter55.skyblock.client;

public class ClientManaData {
    public static int playerMana;
    public static int playerMaxMana;

    public static void set(int mana) {
        ClientManaData.playerMana = mana;
    }

    public static void setMax(int max) {
        ClientManaData.playerMaxMana = max;
    }

    public static int getPlayerMana() {
        return playerMana;
    }

    public static int getPlayerMaxMana() {
        return playerMaxMana;
    }
}
