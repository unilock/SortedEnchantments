package cc.unilock.sortedenchantments;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Comparator;

public final class NBTUtils {
    public static NbtList sort(NbtList unsorted) {
        Comparator<EnchantmentCompound> comparator;

        if (SortedEnchantments.sortCursesBelow) {
            comparator = Comparator.comparing(EnchantmentCompound::cursed);
        } else {
            comparator = Comparator.comparing(e -> 0); // Preserve existing order
        }

        comparator = comparator.thenComparing(EnchantmentCompound::translatedName);

        NbtList sorted = new NbtList();
        unsorted.stream().map(EnchantmentCompound::new).sorted(comparator).forEachOrdered(e -> sorted.add(e.compound()));
        return sorted;
    }

    public static class EnchantmentCompound {
        private final NbtCompound compound;
        private final boolean cursed;
        private final String translatedName;

        public EnchantmentCompound(NbtElement nbt) {
            if (nbt.getType() != NbtElement.COMPOUND_TYPE) {
                throw new AssertionError("NbtElement is not a CompoundTag");
            }

            this.compound = (NbtCompound) nbt; 

            Identifier id = Identifier.tryParse(this.compound.getString("id"));
            Enchantment enchantment = Registries.ENCHANTMENT.get(id);

            // Items can have unregistered enchantments
            if (id == null || enchantment == null) {
                this.cursed = false;
                this.translatedName = "";
                return;
            }

            this.cursed = enchantment.isCursed();
            this.translatedName = I18n.translate(enchantment.getTranslationKey());
        }

        public NbtCompound compound() {
            return this.compound;
        }

        public boolean cursed() {
            return this.cursed;
        }

        public String translatedName() {
            return this.translatedName;
        }
    }
}
