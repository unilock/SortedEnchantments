package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.NBTUtils;
import cc.unilock.sortedenchantments.SortedEnchantments;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin {
    @ModifyExpressionValue(method = "appendTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/EnchantedBookItem;getEnchantmentNbt(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NbtList;"))
    private static NbtList sortStoredEnchantments(NbtList enchantments) {
        if (SortedEnchantments.enableBooks) {
            return NBTUtils.sort(enchantments);
        } else {
            return enchantments;
        }
    }
}
