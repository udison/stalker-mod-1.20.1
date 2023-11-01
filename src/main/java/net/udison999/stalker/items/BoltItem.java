package net.udison999.stalker.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoltItem extends Item {
    public BoltItem() {
        super(new Item.Properties()
                .fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        boolean isClientSide = pLevel.isClientSide;
        
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.DYE_USE, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        
        if (!isClientSide) {
            Snowball bolt = new Snowball(pLevel, pPlayer);
            bolt.setItem(itemStack);
            bolt.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0F, .8F, 1.0F);
            pLevel.addFreshEntity(bolt);
        }
        
        itemStack.shrink(1);
        
        return InteractionResultHolder.sidedSuccess(itemStack, isClientSide);
    }
}
