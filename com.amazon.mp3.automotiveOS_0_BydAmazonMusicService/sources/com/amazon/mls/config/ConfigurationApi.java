package com.amazon.mls.config;

import com.amazon.mls.config.loggers.LoggerFactory;
import com.amazon.mls.config.metadata.EventMetadata;
import com.amazon.mls.config.settings.RuntimeSettings;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ConfigurationApi {
    private EventMetadata eventMetadata;
    private final CopyOnWriteArrayList<Object> initializationListeners;
    private final Object initializationLock;
    private boolean isConfigured;
    private LoggerFactory loggerFactory;
    private RuntimeSettings runtimeSettings;

    private ConfigurationApi() {
        this.isConfigured = false;
        this.initializationListeners = new CopyOnWriteArrayList<>();
        this.initializationLock = new Object();
        this.loggerFactory = null;
        this.runtimeSettings = null;
        this.eventMetadata = null;
    }

    public static ConfigurationApi getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public RuntimeSettings getRuntimeSettings() {
        if (this.isConfigured) {
            return this.runtimeSettings;
        }
        return null;
    }

    public EventMetadata getEventMetadata() {
        if (this.isConfigured) {
            return this.eventMetadata;
        }
        return null;
    }

    private static final class InstanceHolder {
        private static final ConfigurationApi INSTANCE = new ConfigurationApi();

        private InstanceHolder() {
        }
    }
}
