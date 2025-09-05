package cc.unilock.sortedenchantments;

import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class ModConfig extends ReflectiveConfig {
    @Comment("Whether to affect all items except Enchanted Books")
    public final TrackedValue<Boolean> enable = value(true);

    @Comment("Whether to affect Enchanted Books")
    public final TrackedValue<Boolean> enableBooks = value(false);

    @Comment("Whether to sort curses below all other enchantments")
    public final TrackedValue<Boolean> sortCursesBelow = value(true);
}
