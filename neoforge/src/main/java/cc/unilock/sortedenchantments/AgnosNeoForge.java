package cc.unilock.sortedenchantments;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class AgnosNeoForge extends Agnos {
	static {
		Agnos.delegate = new AgnosNeoForge();
	}

	@Override
	protected Path getConfigDirectoryAgnos() {
		return FMLPaths.CONFIGDIR.get();
	}
}
