package cc.unilock.sortedenchantments;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

@Mod("sortedenchantments")
public class SortedEnchantmentsNeoForge {
	public SortedEnchantmentsNeoForge() {
		SortedEnchantments.init();

		NeoForge.EVENT_BUS.addListener(TagsUpdatedEvent.class, event -> {
			if (TagsUpdatedEvent.UpdateCause.CLIENT_PACKET_RECEIVED.equals(event.getUpdateCause())) {
				SortedEnchantments.reload(event.getRegistryAccess());
			}
		});
	}
}
