package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.DateUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes5.dex */
public final class Session implements JsonStream.Streamable {
    private App app;
    private final AtomicBoolean autoCaptured;
    private Device device;
    private final File file;
    private final AtomicInteger handledCount;
    private String id;
    final AtomicBoolean isPaused;
    private final Logger logger;
    private final Notifier notifier;
    private Date startedAt;
    private final AtomicBoolean tracked;
    private final AtomicInteger unhandledCount;
    private User user;

    static Session copySession(Session session) {
        Session session2 = new Session(session.id, session.startedAt, session.user, session.unhandledCount.get(), session.handledCount.get(), session.notifier, session.logger);
        session2.tracked.set(session.tracked.get());
        session2.autoCaptured.set(session.isAutoCaptured());
        return session2;
    }

    Session(Map<String, Object> map, Logger logger) {
        this(null, null, logger);
        setId((String) map.get("id"));
        setStartedAt(DateUtils.fromIso8601((String) map.get("startedAt")));
        Map map2 = (Map) map.get("events");
        this.handledCount.set(((Number) map2.get("handled")).intValue());
        this.unhandledCount.set(((Number) map2.get("unhandled")).intValue());
    }

    Session(String str, Date date, User user, boolean z, Notifier notifier, Logger logger) {
        this(null, notifier, logger);
        this.id = str;
        this.startedAt = new Date(date.getTime());
        this.user = user;
        this.autoCaptured.set(z);
    }

    Session(String str, Date date, User user, int i, int i2, Notifier notifier, Logger logger) {
        this(str, date, user, false, notifier, logger);
        this.unhandledCount.set(i);
        this.handledCount.set(i2);
        this.tracked.set(true);
    }

    Session(File file, Notifier notifier, Logger logger) {
        this.autoCaptured = new AtomicBoolean(false);
        this.unhandledCount = new AtomicInteger();
        this.handledCount = new AtomicInteger();
        this.tracked = new AtomicBoolean(false);
        this.isPaused = new AtomicBoolean(false);
        this.file = file;
        this.logger = logger;
        if (notifier != null) {
            Notifier notifier2 = new Notifier(notifier.getName(), notifier.getVersion(), notifier.getUrl());
            notifier2.setDependencies(new ArrayList(notifier.getDependencies()));
            this.notifier = notifier2;
            return;
        }
        this.notifier = null;
    }

    private void logNull(String str) {
        this.logger.e("Invalid null value supplied to session." + str + ", ignoring");
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public void setId(@NonNull String str) {
        if (str != null) {
            this.id = str;
        } else {
            logNull("id");
        }
    }

    @NonNull
    public Date getStartedAt() {
        return this.startedAt;
    }

    public void setStartedAt(@NonNull Date date) {
        if (date != null) {
            this.startedAt = date;
        } else {
            logNull("startedAt");
        }
    }

    void setApp(App app) {
        this.app = app;
    }

    void setDevice(Device device) {
        this.device = device;
    }

    int getUnhandledCount() {
        return this.unhandledCount.intValue();
    }

    int getHandledCount() {
        return this.handledCount.intValue();
    }

    Session incrementHandledAndCopy() {
        this.handledCount.incrementAndGet();
        return copySession(this);
    }

    Session incrementUnhandledAndCopy() {
        this.unhandledCount.incrementAndGet();
        return copySession(this);
    }

    AtomicBoolean isTracked() {
        return this.tracked;
    }

    boolean isAutoCaptured() {
        return this.autoCaptured.get();
    }

    boolean isV2Payload() {
        File file = this.file;
        return file != null && file.getName().endsWith("_v2.json");
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws Throwable {
        if (this.file != null) {
            if (isV2Payload()) {
                serializeV2Payload(jsonStream);
                return;
            } else {
                serializeV1Payload(jsonStream);
                return;
            }
        }
        jsonStream.beginObject();
        jsonStream.name("notifier").value(this.notifier);
        jsonStream.name("app").value(this.app);
        jsonStream.name("device").value(this.device);
        jsonStream.name("sessions").beginArray();
        serializeSessionInfo(jsonStream);
        jsonStream.endArray();
        jsonStream.endObject();
    }

    private void serializeV2Payload(@NonNull JsonStream jsonStream) throws Throwable {
        jsonStream.value(this.file);
    }

    private void serializeV1Payload(@NonNull JsonStream jsonStream) throws Throwable {
        jsonStream.beginObject();
        jsonStream.name("notifier").value(this.notifier);
        jsonStream.name("app").value(this.app);
        jsonStream.name("device").value(this.device);
        jsonStream.name("sessions").beginArray();
        jsonStream.value(this.file);
        jsonStream.endArray();
        jsonStream.endObject();
    }

    void serializeSessionInfo(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject();
        jsonStream.name("id").value(this.id);
        jsonStream.name("startedAt").value(this.startedAt);
        jsonStream.name("user").value(this.user);
        jsonStream.endObject();
    }
}
