package cc.unilock.sortedenchantments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class SortedEnchantments implements ClientModInitializer {
    public static final ModConfig CONFIG = ModConfig.createToml(FabricLoader.getInstance().getConfigDir(), "", "sorted_enchantments", ModConfig.class);

	public static boolean enable = CONFIG.enable.value();
	public static boolean enableBooks = CONFIG.enableBooks.value();
	public static boolean sortCursesBelow = CONFIG.sortCursesBelow.value();

    @Override
    public void onInitializeClient() {
		CONFIG.registerCallback(config -> {
			enable = ((ModConfig) config).enable.value();
			enableBooks = ((ModConfig) config).enableBooks.value();
			sortCursesBelow = ((ModConfig) config).sortCursesBelow.value();
		});
    }
}
