package net.udison999.stalker.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.udison999.stalker.StalkerMod;
import net.udison999.stalker.entity.projectile.Bolt;

public class ModEntities {
    
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITY_TYPES, StalkerMod.MOD_ID);
    
    public static final RegistryObject<EntityType<Bolt>> BOLT_ENTITY = ENTITIES.register("bolt",
            () -> EntityType.Builder.<Bolt>of(Bolt::new, MobCategory.MISC).build(
                    new ResourceLocation(StalkerMod.MOD_ID, "bolt").toString()));
                    
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
    
}
