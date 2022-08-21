package net.ashcrafter55.skyblock.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private final int MIN_MANA = 0;
    private final int MAX_MANA = 100;

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public void addMana(int toAdd) {
        this.mana = Math.min(mana + toAdd, MAX_MANA);
    }

    public void subMana(int toSub) {
        this.mana = Math.max(mana - toSub, MIN_MANA);
    }

    public void setMana(int toSet) {
        this.mana = toSet;
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
