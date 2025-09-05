package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.NBTUtils;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sortedenchantments.SortedEnchantments.CONFIG;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @ModifyExpressionValue(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getEnchantments()Lnet/minecraft/nbt/NbtList;"))
    private NbtList sortEnchantments(NbtList enchantments) {
        if (CONFIG.enable.value()) {
            return NBTUtils.sort(enchantments);
        } else {
            return enchantments;
        }
    }
}
