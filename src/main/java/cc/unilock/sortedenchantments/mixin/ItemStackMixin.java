package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.NBTUtils;
import cc.unilock.sortedenchantments.SortedEnchantments;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @ModifyExpressionValue(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getEnchantments()Lnet/minecraft/nbt/NbtList;"))
    private NbtList sortEnchantments(NbtList enchantments) {
        if (SortedEnchantments.enable) {
            return NBTUtils.sort(enchantments);
        } else {
            return enchantments;
        }
    }
}
