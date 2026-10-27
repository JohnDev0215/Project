package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.InternalMetrics;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class Event implements JsonStream.Streamable {
    private final EventInternal impl;
    private final Logger logger;

    Event(@Nullable Throwable th, @NonNull ImmutableConfig immutableConfig, @NonNull SeverityReason severityReason, @NonNull Logger logger) {
        this(th, immutableConfig, severityReason, new Metadata(), new FeatureFlags(), logger);
    }

    Event(@Nullable Throwable th, @NonNull ImmutableConfig immutableConfig, @NonNull SeverityReason severityReason, @NonNull Metadata metadata, @NonNull FeatureFlags featureFlags, @NonNull Logger logger) {
        this(new EventInternal(th, immutableConfig, severityReason, metadata, featureFlags), logger);
    }

    Event(@NonNull EventInternal eventInternal, @NonNull Logger logger) {
        this.impl = eventInternal;
        this.logger = logger;
    }

    private void logNull(String str) {
        this.logger.e("Invalid null value supplied to config." + str + ", ignoring");
    }

    @NonNull
    public List<Error> getErrors() {
        return this.impl.getErrors();
    }

    @NonNull
    public List<Thread> getThreads() {
        return this.impl.getThreads();
    }

    @NonNull
    public AppWithState getApp() {
        return this.impl.getApp();
    }

    @NonNull
    public String getApiKey() {
        return this.impl.getApiKey();
    }

    @NonNull
    public Severity getSeverity() {
        return this.impl.getSeverity();
    }

    public void setContext(@Nullable String str) {
        this.impl.setContext(str);
    }

    public void setUser(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.impl.setUser(str, str2, str3);
    }

    public void addMetadata(@NonNull String str, @NonNull Map<String, ?> map) {
        if (str != null && map != null) {
            this.impl.addMetadata(str, map);
        } else {
            logNull("addMetadata");
        }
    }

    public void addMetadata(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        if (str != null && str2 != null) {
            this.impl.addMetadata(str, str2, obj);
        } else {
            logNull("addMetadata");
        }
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        this.impl.toStream(jsonStream);
    }

    public boolean isUnhandled() {
        return this.impl.getUnhandled();
    }

    protected void updateSeverityInternal(@NonNull Severity severity) {
        this.impl.updateSeverityInternal(severity);
    }

    void setApp(@NonNull AppWithState appWithState) {
        this.impl.setApp(appWithState);
    }

    void setDevice(@NonNull DeviceWithState deviceWithState) {
        this.impl.setDevice(deviceWithState);
    }

    void setBreadcrumbs(@NonNull List<Breadcrumb> list) {
        this.impl.setBreadcrumbs(list);
    }

    @Nullable
    Session getSession() {
        return this.impl.session;
    }

    void setSession(@Nullable Session session) {
        this.impl.session = session;
    }

    EventInternal getImpl() {
        return this.impl;
    }

    void setRedactedKeys(Collection<String> collection) {
        this.impl.setRedactedKeys(collection);
    }

    void setInternalMetrics(InternalMetrics internalMetrics) {
        this.impl.setInternalMetrics(internalMetrics);
    }
}
