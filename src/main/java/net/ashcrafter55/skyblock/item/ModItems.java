package net.ashcrafter55.skyblock.item;

import net.ashcrafter55.skyblock.Skyblock;
import net.ashcrafter55.skyblock.item.custom.AspectOfTheVoidItem;
import net.ashcrafter55.skyblock.item.custom.RogueSwordItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Skyblock.MOD_ID);

    public static final RegistryObject<Item> MITHRIL = ITEMS.register("mithril",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKYBLOCK_TAB)));
    public static final RegistryObject<Item> TITANIUM = ITEMS.register("titanium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKYBLOCK_TAB)));

    public static final RegistryObject<Item> ASPECT_OF_THE_VOID = ITEMS.register("aspect_of_the_void",
            () -> new AspectOfTheVoidItem(Tiers.DIAMOND, 2, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.SKYBLOCK_TAB).durability(-1)));
    public static final RegistryObject<Item> ROGUE_SWORD = ITEMS.register("rogue_sword",
            () -> new RogueSwordItem(Tiers.GOLD, 2, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.SKYBLOCK_TAB).durability(-1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
