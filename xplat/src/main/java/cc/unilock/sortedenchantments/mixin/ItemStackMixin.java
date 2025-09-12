package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.mixinsupport.BookHolder;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @ModifyArg(method = "getTooltipLines", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 0))
    private <T extends TooltipProvider> DataComponentType<T> addToTooltip(DataComponentType<T> component, @Local(argsOnly = true) Item.TooltipContext tooltipContext) {
		((BookHolder) tooltipContext).sorted_enchantments$setBook(DataComponents.STORED_ENCHANTMENTS.equals(component));
		return component;
	}
}
