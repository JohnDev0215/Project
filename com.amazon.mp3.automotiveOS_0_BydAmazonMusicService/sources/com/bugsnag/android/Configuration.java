package com.bugsnag.android;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class Configuration {
    final ConfigInternal impl;

    public Configuration(@NonNull String str) {
        validateApiKey(str);
        this.impl = new ConfigInternal(str);
    }

    private void validateApiKey(String str) {
        if (isInvalidApiKey(str)) {
            DebugLogger.INSTANCE.w("Invalid configuration. apiKey should be a 32-character hexademical string, got " + str);
        }
    }

    @VisibleForTesting
    static boolean isInvalidApiKey(String str) {
        if (Intrinsics.isEmpty(str)) {
            throw new IllegalArgumentException("No Bugsnag API Key set");
        }
        if (str.length() != 32) {
            return true;
        }
        for (int i = 0; i < 32; i++) {
            char cCharAt = str.charAt(i);
            if (!Character.isDigit(cCharAt) && (cCharAt < 'a' || cCharAt > 'f')) {
                return true;
            }
        }
        return false;
    }

    private void logNull(String str) {
        getLogger().e("Invalid null value supplied to config." + str + ", ignoring");
    }

    @NonNull
    public String getApiKey() {
        return this.impl.getApiKey();
    }

    @Nullable
    public String getAppVersion() {
        return this.impl.getAppVersion();
    }

    @Nullable
    public Integer getVersionCode() {
        return this.impl.getVersionCode();
    }

    public void setVersionCode(@Nullable Integer num) {
        this.impl.setVersionCode(num);
    }

    @Nullable
    public String getReleaseStage() {
        return this.impl.getReleaseStage();
    }

    public void setReleaseStage(@Nullable String str) {
        this.impl.setReleaseStage(str);
    }

    @NonNull
    public ThreadSendPolicy getSendThreads() {
        return this.impl.getSendThreads();
    }

    public boolean getPersistUser() {
        return this.impl.getPersistUser();
    }

    @Nullable
    public File getPersistenceDirectory() {
        return this.impl.getPersistenceDirectory();
    }

    public boolean getSendLaunchCrashesSynchronously() {
        return this.impl.getSendLaunchCrashesSynchronously();
    }

    public long getLaunchDurationMillis() {
        return this.impl.getLaunchDurationMillis();
    }

    public boolean getAutoTrackSessions() {
        return this.impl.getAutoTrackSessions();
    }

    @NonNull
    public ErrorTypes getEnabledErrorTypes() {
        return this.impl.getEnabledErrorTypes();
    }

    public boolean getAutoDetectErrors() {
        return this.impl.getAutoDetectErrors();
    }

    @Nullable
    public String getAppType() {
        return this.impl.getAppType();
    }

    @Nullable
    public Logger getLogger() {
        return this.impl.getLogger();
    }

    public void setLogger(@Nullable Logger logger) {
        this.impl.setLogger(logger);
    }

    @NonNull
    public Delivery getDelivery() {
        return this.impl.getDelivery();
    }

    public void setDelivery(@NonNull Delivery delivery) {
        if (delivery != null) {
            this.impl.setDelivery(delivery);
        } else {
            logNull("delivery");
        }
    }

    @NonNull
    public EndpointConfiguration getEndpoints() {
        return this.impl.getEndpoints();
    }

    public void setEndpoints(@NonNull EndpointConfiguration endpointConfiguration) {
        if (endpointConfiguration != null) {
            this.impl.setEndpoints(endpointConfiguration);
        } else {
            logNull("endpoints");
        }
    }

    public int getMaxBreadcrumbs() {
        return this.impl.getMaxBreadcrumbs();
    }

    public void setMaxBreadcrumbs(@IntRange(from = 0, to = 500) int i) {
        if (i >= 0 && i <= 500) {
            this.impl.setMaxBreadcrumbs(i);
            return;
        }
        getLogger().e("Invalid configuration value detected. Option maxBreadcrumbs should be an integer between 0-500. Supplied value is " + i);
    }

    public int getMaxPersistedEvents() {
        return this.impl.getMaxPersistedEvents();
    }

    public void setMaxPersistedEvents(@IntRange(from = 0) int i) {
        if (i >= 0) {
            this.impl.setMaxPersistedEvents(i);
            return;
        }
        getLogger().e("Invalid configuration value detected. Option maxPersistedEvents should be a positive integer.Supplied value is " + i);
    }

    public int getMaxReportedThreads() {
        return this.impl.getMaxReportedThreads();
    }

    public void setMaxReportedThreads(@IntRange(from = 0) int i) {
        if (i >= 0) {
            this.impl.setMaxReportedThreads(i);
            return;
        }
        getLogger().e("Invalid configuration value detected. Option maxReportedThreads should be a positive integer.Supplied value is " + i);
    }

    public int getMaxPersistedSessions() {
        return this.impl.getMaxPersistedSessions();
    }

    public void setMaxPersistedSessions(@IntRange(from = 0) int i) {
        if (i >= 0) {
            this.impl.setMaxPersistedSessions(i);
            return;
        }
        getLogger().e("Invalid configuration value detected. Option maxPersistedSessions should be a positive integer.Supplied value is " + i);
    }

    public int getMaxStringValueLength() {
        return this.impl.getMaxStringValueLength();
    }

    public void setMaxStringValueLength(@IntRange(from = 0) int i) {
        if (i >= 0) {
            this.impl.setMaxStringValueLength(i);
            return;
        }
        getLogger().e("Invalid configuration value detected. Option maxStringValueLength should be a positive integer.Supplied value is " + i);
    }

    @Nullable
    public String getContext() {
        return this.impl.getContext();
    }

    @NonNull
    public Set<String> getRedactedKeys() {
        return this.impl.getRedactedKeys();
    }

    @NonNull
    public Set<String> getDiscardClasses() {
        return this.impl.getDiscardClasses();
    }

    @Nullable
    public Set<String> getEnabledReleaseStages() {
        return this.impl.getEnabledReleaseStages();
    }

    @Nullable
    public Set<BreadcrumbType> getEnabledBreadcrumbTypes() {
        return this.impl.getEnabledBreadcrumbTypes();
    }

    public void setEnabledBreadcrumbTypes(@Nullable Set<BreadcrumbType> set) {
        this.impl.setEnabledBreadcrumbTypes(set);
    }

    @NonNull
    public Set<Telemetry> getTelemetry() {
        return this.impl.getTelemetry();
    }

    @NonNull
    public Set<String> getProjectPackages() {
        return this.impl.getProjectPackages();
    }

    public void setProjectPackages(@NonNull Set<String> set) {
        if (CollectionUtils.containsNullElements(set)) {
            logNull("projectPackages");
        } else {
            this.impl.setProjectPackages(set);
        }
    }

    @NonNull
    public User getUser() {
        return this.impl.getUser();
    }

    public void addPlugin(@NonNull Plugin plugin) {
        if (plugin != null) {
            this.impl.addPlugin(plugin);
        } else {
            logNull("addPlugin");
        }
    }

    public boolean isAttemptDeliveryOnCrash() {
        return this.impl.getAttemptDeliveryOnCrash();
    }

    Set<Plugin> getPlugins() {
        return this.impl.getPlugins();
    }

    Notifier getNotifier() {
        return this.impl.getNotifier();
    }
}
