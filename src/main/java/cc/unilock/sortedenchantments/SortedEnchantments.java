package cc.unilock.sortedenchantments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class SortedEnchantments implements ClientModInitializer {
    public static final ModConfig CONFIG = ModConfig.createToml(FabricLoader.getInstance().getConfigDir(), "", "sorted_enchantments", ModConfig.class);
    
    @Override
    public void onInitializeClient() {
        // NO-OP
    }
}
