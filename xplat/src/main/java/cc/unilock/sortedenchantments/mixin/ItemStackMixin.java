package cc.unilock.sortedenchantments.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @WrapOperation(method = "getTooltipLines", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V"))
    private <T extends TooltipProvider> void addToTooltip(ItemStack instance, DataComponentType<T> component, Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag, Operation<Void> original, @Local List<Component> localList, @Share("sorted") LocalRef<List<Component>> shareList) {
        if (DataComponents.STORED_ENCHANTMENTS.equals(component)) {
            List<Component> fakeList = new ArrayList<>();
            Consumer<Component> consumer = fakeList::add;
            original.call(instance, component, context, consumer, tooltipFlag);

            shareList.set(fakeList);
        } else if (DataComponents.ENCHANTMENTS.equals(component)) {
            List<Component> fakeList = shareList.get();
            Consumer<Component> consumer = fakeList::add;
            original.call(instance, component, context, consumer, tooltipFlag);

            fakeList.sort(Comparator.comparing(Component::getString));

            localList.addAll(fakeList);
        } else {
            original.call(instance, component, context, tooltipAdder, tooltipFlag);
        }
    }
}
