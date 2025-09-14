package cc.unilock.sortedenchantments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;

public class SortedEnchantmentsFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		SortedEnchantments.init();

		CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
			if (client) {
				SortedEnchantments.reload(registries);
			}
		});
	}
}
