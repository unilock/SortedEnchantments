package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.NBTUtils;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin {
    @Inject(method = "getEnchantmentNbt", at = @At("RETURN"), cancellable = true)
    private static void injected(ItemStack stack, CallbackInfoReturnable<NbtList> cir) {
        cir.setReturnValue(NBTUtils.toListTag(NBTUtils.sort(cir.getReturnValue())));
    }
}
