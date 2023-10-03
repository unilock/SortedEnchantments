package cc.unilock.sorted_enchantments.mixin;

import cc.unilock.sorted_enchantments.NBTUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "getEnchantments", at = @At("RETURN"), cancellable = true)
	private void injected(CallbackInfoReturnable<NbtList> cir) {
		cir.setReturnValue(NBTUtils.toListTag(NBTUtils.sort(cir.getReturnValue())));
	}
}
