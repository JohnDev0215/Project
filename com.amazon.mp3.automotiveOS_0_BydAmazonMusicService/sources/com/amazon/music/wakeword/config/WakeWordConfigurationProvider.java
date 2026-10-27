package com.amazon.music.wakeword.config;

import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class WakeWordConfigurationProvider {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) WakeWordConfigurationProvider.class);
    private static WakeWordConfiguration configuration;

    private RemoteWakeWordConfiguration create() {
        Configuration configuration2 = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        return new RemoteWakeWordConfiguration(configuration2 != null ? configuration2.getIntForKey("detection_sensitivity") : 1);
    }

    public static synchronized void init() {
        configuration = new WakeWordConfigurationProvider().create();
    }

    public static synchronized WakeWordConfiguration getConfiguration() {
        if (configuration == null) {
            LOG.warn("Using NoOp WakeWord Configuration");
            configuration = new NoOpWakeWordConfiguration();
        }
        return configuration;
    }
}
