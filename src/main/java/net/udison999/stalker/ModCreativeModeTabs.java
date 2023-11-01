package net.udison999.stalker;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.udison999.stalker.items.ModItems;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StalkerMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FOOD_N_STUFF_TAB = CREATIVE_MODE_TABS.register(
            "stalker_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STALKERS_BADGE.get()))
                    .title(Component.translatable("creativetab.stalker_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // Badges
                        pOutput.accept(ModItems.STALKERS_BADGE.get());
                        pOutput.accept(ModItems.BANDITS_BADGE.get());
                        
                        // Items
                        pOutput.accept(ModItems.BOLT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
