package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.mixinsupport.ITooltipProvider;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "addToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/TooltipProvider;addToTooltip(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V"))
	private <T extends TooltipProvider> void addToTooltip$addToTooltip(CallbackInfo ci, @Local(argsOnly = true) DataComponentType<T> component, @Local T tooltipProvider) {
		if (tooltipProvider instanceof ITooltipProvider i) i.sortedEnchantments$setStoredEnchantments(DataComponents.STORED_ENCHANTMENTS.equals(component));
	}
}
