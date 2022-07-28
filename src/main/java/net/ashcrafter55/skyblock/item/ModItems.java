package net.ashcrafter55.skyblock.item;

import net.ashcrafter55.skyblock.Skyblock;
import net.minecraft.world.item.Item;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
