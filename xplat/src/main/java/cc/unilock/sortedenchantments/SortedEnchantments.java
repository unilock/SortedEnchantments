package cc.unilock.sortedenchantments;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Comparator;

public class SortedEnchantments {
	public static final ModConfig CONFIG = ModConfig.createToml(Agnos.getConfigDirectory(), "", "sorted_enchantments", ModConfig.class);
	private static final Comparator<Holder.Reference<Enchantment>> COMPARATOR = Comparator
			.<Holder.Reference<Enchantment>, Boolean>comparing( ref -> CONFIG.sortCursesBelow.value() && ref.is(EnchantmentTags.CURSE))
			.<String>thenComparing(ref -> ref.value().description().getString());

	public static HolderSet<Enchantment> sortedSet;

	public static void init() {
	}

	public static void reload(RegistryAccess registries) {
		sortedSet = HolderSet.direct(registries.lookupOrThrow(Registries.ENCHANTMENT).listElements().sorted(COMPARATOR).toList());
	}
}
