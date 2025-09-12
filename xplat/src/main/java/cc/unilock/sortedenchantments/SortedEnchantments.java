package cc.unilock.sortedenchantments;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;

import java.nio.file.Path;
import java.util.Comparator;

public class SortedEnchantments {
	public static ModConfig CONFIG;
	public static HolderSet<Enchantment> SORTED_ENCHANTMENTS;
	private static final Comparator<Holder.Reference<Enchantment>> COMPARATOR = Comparator
			.<Holder.Reference<Enchantment>, Boolean>comparing( ref -> CONFIG.sortCursesBelow.value() && ref.is(EnchantmentTags.CURSE))
			.<String>thenComparing(ref -> ref.value().description().getString());

	public static void init(Path configDir) {
		CONFIG = ModConfig.createToml(configDir, "", "sorted_enchantments", ModConfig.class);
	}

	public static void reload(RegistryAccess registries) {
		SORTED_ENCHANTMENTS = HolderSet.direct(registries.lookupOrThrow(Registries.ENCHANTMENT).listElements().sorted(COMPARATOR).toList());
	}
}
