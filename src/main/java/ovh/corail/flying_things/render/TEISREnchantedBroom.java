package ovh.corail.flying_things.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ovh.corail.flying_things.entity.EntityEnchantedBroom;
import ovh.corail.flying_things.item.ItemEnchantedBroom;
import ovh.corail.flying_things.registry.ModEntities;

@OnlyIn(Dist.CLIENT)
public class TEISREnchantedBroom extends ItemStackTileEntityRenderer {
    private final EntityEnchantedBroom entity = ModEntities.enchanted_broom.create(null);

    @Override
    public void render(ItemStack stack, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        assert entity != null;
        entity.setModelType(ItemEnchantedBroom.getModelType(stack));
        entity.setHeadType(ItemEnchantedBroom.getHeadType(stack));
        ClientPlayerEntity player = Minecraft.getInstance().player;
        RenderEnchantedBroom.render(entity, 0f, player != null ? player.ticksExisted : 0, Minecraft.getInstance().getRenderPartialTicks(), matrixStack, iRenderTypeBuffer, combinedLightIn, true);
    }
}
