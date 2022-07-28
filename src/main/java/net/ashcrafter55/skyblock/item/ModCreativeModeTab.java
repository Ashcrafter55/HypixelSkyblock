package net.ashcrafter55.skyblock.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab SKYBLOCK_TAB = new CreativeModeTab("skyblocktab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MITHRIL.get());
        }
    };
}
