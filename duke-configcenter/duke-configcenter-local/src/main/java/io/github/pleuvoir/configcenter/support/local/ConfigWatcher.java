package io.github.pleuvoir.configcenter.support.local;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.pleuvoir.core.util.NamedThreadFactory;

/**
 * 本地文件变更监听线程，10秒检测一次
 *
 */
public class ConfigWatcher {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigWatcher.class);
	
	private final CopyOnWriteArrayList<Watch> watches;
	private final ScheduledExecutorService watcherExecutor;

	ConfigWatcher() {
		this.watches = new CopyOnWriteArrayList<>();
		this.watcherExecutor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("local-config-watcher", true));
		start();
	}

	private void start() {
		watcherExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				checkAllWatches();
			}
		}, 10, 10, TimeUnit.SECONDS);
	}

	private void checkAllWatches() {
		for (Watch watch : watches) {
			try {
				checkWatch(watch);
			} catch (Exception e) {
				LOGGER.error("check config failed. config: {}", watch.getConfig(), e);
			}
		}
	}

	private void checkWatch(final Watch watch) {
		final LocalDynamicConfig config = watch.getConfig();
		final long lastModified = config.getLastModified();
		if (lastModified == watch.getLastModified()) {
			return;
		}
		
		watch.setLastModified(lastModified);
		config.onConfigModified();
	}

	void addWatch(final LocalDynamicConfig config) {
		final Watch watch = new Watch(config);
		watch.setLastModified(config.getLastModified());
		watches.add(watch);
	}

	private static final class Watch {
		private final LocalDynamicConfig config;
		private volatile long lastModified;

		private Watch(final LocalDynamicConfig config) {
			this.config = config;
		}

		public LocalDynamicConfig getConfig() {
			return config;
		}

		long getLastModified() {
			return lastModified;
		}

		void setLastModified(final long lastModified) {
			this.lastModified = lastModified;
		}
	}
}
