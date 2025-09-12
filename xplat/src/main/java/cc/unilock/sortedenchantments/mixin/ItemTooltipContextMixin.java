package cc.unilock.sortedenchantments.mixin;

import cc.unilock.sortedenchantments.mixinsupport.BookHolder;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.TooltipContext.class)
public class ItemTooltipContextMixin implements BookHolder {
	@Unique
	private boolean book = false;

	@Override
	public boolean sorted_enchantments$getBook() {
		return book;
	}

	@Override
	public void sorted_enchantments$setBook(boolean value) {
		book = value;
	}
}
