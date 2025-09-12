package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.SortedEnchantments;
import cc.unilock.sortedenchantments.mixinsupport.BookHolder;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEnchantments.class)
public class ItemEnchantmentsMixin {
	@WrapOperation(method = "addToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/ItemEnchantments;getTagOrEmpty(Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/tags/TagKey;)Lnet/minecraft/core/HolderSet;"))
	private HolderSet<Enchantment> addToTooltip$getTagOrEmpty(HolderLookup.Provider registries, ResourceKey<Registry<Enchantment>> registryKey, TagKey<Enchantment> key, Operation<HolderSet<Enchantment>> original, @Local(argsOnly = true) Item.TooltipContext context) {
		return (((BookHolder) context).sorted_enchantments$getBook() ? SortedEnchantments.CONFIG.enableBooks : SortedEnchantments.CONFIG.enable).value() ? SortedEnchantments.SORTED_ENCHANTMENTS : original.call(registries, registryKey, key);
	}
}
