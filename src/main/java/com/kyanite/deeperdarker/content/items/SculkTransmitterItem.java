package com.kyanite.deeperdarker.content.items;

import com.kyanite.deeperdarker.DeeperDarker;
import com.kyanite.deeperdarker.util.DDTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class SculkTransmitterItem extends Item {
    public SculkTransmitterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getItemInHand().hasTag()) {
            return transmit(pContext.getLevel(), pContext.getPlayer(), pContext.getHand(), pContext.getClickedPos());
        }

        if(!pContext.getLevel().getBlockState(pContext.getClickedPos()).is(DDTags.Blocks.TRANSMITTABLE)) {
            pContext.getPlayer().displayClientMessage(Component.translatable("block." + DeeperDarker.MOD_ID + ".not_transmittable"), true);
            actionBarMessage(pContext.getPlayer(), "not_transmittable");
            return InteractionResult.FAIL;
        }

        formConnection(pContext.getItemInHand(), pContext.getClickedPos());
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.getMainHandItem().hasTag()) {
            transmit(pLevel, pPlayer, pUsedHand, null);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) pTooltipComponents.add(Component.translatable("tooltips." + DeeperDarker.MOD_ID + ".sculk_transmitter.linked"));
        else pTooltipComponents.add(Component.translatable("tooltips." + DeeperDarker.MOD_ID + ".sculk_transmitter.not_linked"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private InteractionResult transmit(Level level, Player player, InteractionHand hand, BlockPos clickedPos) {
        int[] pos = player.getMainHandItem().getTag().getIntArray("blockPos");
        BlockPos linkedBlockPos = new BlockPos(pos[0], pos[1], pos[2]);
        ItemStack transmitter = player.getItemInHand(hand);

        if(player.isCrouching()) {
            if(clickedPos != null && level.getBlockState(clickedPos).is(DDTags.Blocks.TRANSMITTABLE)) {
                actionBarMessage(player, "linked");
                formConnection(transmitter, clickedPos);
                return InteractionResult.SUCCESS;
            }

            actionBarMessage(player, "unlinked");
            formConnection(transmitter, null);
            return InteractionResult.FAIL;
        }

        if(!level.getBlockState(linkedBlockPos).is(DDTags.Blocks.TRANSMITTABLE)) {
            actionBarMessage(player, "not_found");
            formConnection(transmitter, null);
            return InteractionResult.FAIL;
        }

        level.gameEvent(GameEvent.ENTITY_INTERACT, player.blockPosition(), GameEvent.Context.of(player));

        MenuProvider menu = level.getBlockState(linkedBlockPos).getMenuProvider(level, linkedBlockPos);
        if(menu != null) {
            player.openMenu(menu);
            if(level.getBlockEntity(linkedBlockPos) instanceof ChestBlockEntity chestBlockEntity) chestBlockEntity.startOpen(player);
        }

        return InteractionResult.SUCCESS;
    }

    private void formConnection(ItemStack stack, BlockPos pos) {
        CompoundTag tag = new CompoundTag();
        if(pos == null) {
            stack.removeTagKey("blockPos");
            return;
        }

        tag.putIntArray("blockPos", List.of(pos.getX(), pos.getY(), pos.getZ()));
        stack.setTag(tag);
    }

    private void actionBarMessage(Player player, String key) {
        player.displayClientMessage(Component.translatable("block." + DeeperDarker.MOD_ID + "." + key), true);
    }
}