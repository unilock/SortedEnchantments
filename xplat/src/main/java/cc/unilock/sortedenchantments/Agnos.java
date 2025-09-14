package cc.unilock.sortedenchantments;

import java.nio.file.Path;

public abstract class Agnos {
	public static Agnos delegate;

	static {
		try {
			Class.forName("cc.unilock.sortedenchantments.AgnosFabric");
		} catch (Throwable ignored) {
		}
		try {
			Class.forName("cc.unilock.sortedenchantments.AgnosNeoForge");
		} catch (Throwable ignored) {
		}
	}
	public static Path getConfigDirectory() {
		return delegate.getConfigDirectoryAgnos();
	}

	protected abstract Path getConfigDirectoryAgnos();
}
