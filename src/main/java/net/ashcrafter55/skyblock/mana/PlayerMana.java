package net.ashcrafter55.skyblock.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private int max_mana = 100;
    private final int MIN_MANA = 0;

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return max_mana;
    }

    public void addMana(int toAdd) {
        this.mana = Math.min(mana + toAdd, max_mana);
    }

    public void subMana(int toSub) {
        this.mana = Math.max(mana - toSub, MIN_MANA);
    }

    public void setMana(int toSet) {
        this.mana = toSet;
    }
    public void setMaxMana(int toSet) {
        this.max_mana = toSet;
    }

    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", mana);
    }

    public void loadNBTData(CompoundTag nbt) {
        mana = nbt.getInt("mana");
    }
}
