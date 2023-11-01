package net.udison999.stalker.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WeaponItem extends Item {

    public WeaponItem() {
        super(new Properties()
                .stacksTo(1)
                .fireResistant());
    }
    
    public WeaponItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

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
        
        return InteractionResultHolder.pass(itemStack);
    }
}
