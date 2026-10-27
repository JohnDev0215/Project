package io.sentry.android.core;

import io.sentry.EnumC1495p1;
import java.io.File;

/* JADX INFO: renamed from: io.sentry.android.core.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1436l implements io.sentry.util.c {
    public final /* synthetic */ SentryAndroidOptions l;

    @Override // io.sentry.util.c
    public Object g() {
        int i7 = io.sentry.android.core.cache.a.f16156t;
        SentryAndroidOptions sentryAndroidOptions = this.l;
        String outboxPath = sentryAndroidOptions.getOutboxPath();
        boolean z6 = false;
        if (outboxPath == null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Outbox path is null, the startup crash marker file does not exist", new Object[0]);
        } else {
            File file = new File(outboxPath, "startup_crash");
            try {
                boolean zExists = file.exists();
                if (zExists && !file.delete()) {
                    sentryAndroidOptions.getLogger().k(EnumC1495p1.ERROR, "Failed to delete the startup crash marker file. %s.", file.getAbsolutePath());
                }
                z6 = zExists;
            } catch (Throwable th) {
                sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Error reading/deleting the startup crash marker file on the disk", th);
            }
        }
        return Boolean.valueOf(z6);
    }
}
