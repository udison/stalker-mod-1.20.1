package net.udison999.stalker.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;


public class WeaponItem extends Item {
    
    private WeaponProperties properties; 
    
    public WeaponItem(WeaponProperties weaponProperties) {
        super(new Properties()
                .durability(weaponProperties.magazineSize)
                .fireResistant());

        properties = weaponProperties;
    }
    
    public WeaponItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void verifyTagAfterLoad(CompoundTag pCompoundTag) {
        if (!pCompoundTag.contains("bullets"))
            pCompoundTag.putInt("bullets", properties.magazineSize);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        if (pPlayer.isSecondaryUseActive()) {
            reload(pPlayer, itemStack);
        }
        
        else {
            shoot(itemStack, pLevel, pPlayer);
        }
        
        return InteractionResultHolder.pass(itemStack);
    }
    
    private void shoot(ItemStack itemStack, Level pLevel, Player pPlayer) {
        if (itemStack.getTag() == null) return;
            
        int remainingBullets = itemStack.getTag().getInt("bullets");
        
        if (remainingBullets == 0) return;

        pLevel.playSound(
                (Player)null,
                pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.NEUTRAL,
                1F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!pLevel.isClientSide) {
            Snowball bullet = new Snowball(pLevel, pPlayer);
            bullet.setItem(itemStack);
            bullet.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0F, 5F, 0.1F);

            pLevel.addFreshEntity(bullet);
        }

        remainingBullets--;
        itemStack.getTag().putInt("bullets", remainingBullets);
    }
    
    private void reload(Player player, ItemStack itemStack) {
        if (itemStack.getTag() == null) itemStack.setTag(new CompoundTag());

        itemStack.getTag().putInt("bullets", properties.magazineSize);
    }
    
    public static class WeaponProperties {
        int magazineSize = 16;
        
        public WeaponProperties setMagazineSize(int size) {
            magazineSize = size;
            return this;
        }
    }
}
