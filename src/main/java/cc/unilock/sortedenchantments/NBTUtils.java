package cc.unilock.sortedenchantments;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.stream.Stream;

public final class NBTUtils {
    private static final boolean cursesBelow = false;

    public static Stream<EnchantmentCompound> sort(NbtList listTag) {
        Comparator<EnchantmentCompound> comparator;

        if (cursesBelow) {
            comparator = Comparator.comparing(EnchantmentCompound::isCursed);
        } else {
            comparator = Comparator.comparing(e -> 0); // Preserve existing order
        }

        comparator = comparator.thenComparing(EnchantmentCompound::getTranslatedName);

        return listTag.stream().map(EnchantmentCompound::new).sorted(comparator);
    }

    public static NbtList toListTag(Stream<EnchantmentCompound> stream) {
        NbtList listTag = new NbtList();
        stream.forEachOrdered(tag -> listTag.add(tag.asCompoundTag()));
        return listTag;
    }

    public static class EnchantmentCompound {
        @NotNull private final NbtCompound compound;
        private final Enchantment enchantment;
        private String translatedName = null;
        private boolean isCursed = false;

        public EnchantmentCompound(@NotNull NbtElement tag) {
            if (tag.getType() != NbtElement.COMPOUND_TYPE) {
                throw new AssertionError("tag is not a CompoundTag");
            }

            this.compound = (NbtCompound) tag;

            Identifier identifier = Identifier.tryParse(compound.getString("id"));
            this.enchantment = Registries.ENCHANTMENT.get(identifier);

            // Items can have non-registered enchantment tags on them
            if (identifier == null || enchantment == null) {
                // dummy comparison values
                this.translatedName = "";
                return;
            }

            this.isCursed = this.enchantment.isCursed();
        }

        private void lazyInit() {
            this.translatedName = I18n.translate(this.enchantment.getTranslationKey());
        }

        @NotNull
        public NbtCompound asCompoundTag() {
            return compound;
        }

        public boolean isCursed() {
            return isCursed;
        }

        @NotNull
        public String getTranslatedName() {
            if (this.translatedName == null) {
                lazyInit();
            }

            return translatedName;
        }
    }
}
