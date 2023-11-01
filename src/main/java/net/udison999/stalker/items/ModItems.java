package net.udison999.stalker.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.udison999.stalker.StalkerMod;

public class ModItems {
    
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StalkerMod.MOD_ID);

    /*
     * Badges
     */
    public static final RegistryObject<Item> STALKERS_BADGE = ITEMS.register("stalkers_badge",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BANDITS_BADGE = ITEMS.register("bandits_badge",
            () -> new Item(new Item.Properties()));

    /*
     * Items
     */
    public static final RegistryObject<Item> BOLT = ITEMS.register("bolt",
            BoltItem::new);

    /*
     * Weapons
     */
    public static final RegistryObject<Item> FORT12 = ITEMS.register("fort12",
            WeaponItem::new);
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    
}
