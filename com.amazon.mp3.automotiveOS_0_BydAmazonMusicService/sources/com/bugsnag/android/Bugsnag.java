package com.bugsnag.android;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class Bugsnag {

    @SuppressLint({"StaticFieldLeak"})
    static Client client;
    private static final Object lock = new Object();

    private Bugsnag() {
    }

    @NonNull
    public static Client start(@NonNull Context context, @NonNull Configuration configuration) {
        synchronized (lock) {
            if (client == null) {
                client = new Client(context, configuration);
            } else {
                logClientInitWarning();
            }
        }
        return client;
    }

    public static boolean isStarted() {
        return client != null;
    }

    private static void logClientInitWarning() {
        getClient().logger.w("Multiple Bugsnag.start calls detected. Ignoring.");
    }

    @NonNull
    public static User getUser() {
        return getClient().getUser();
    }

    public static void leaveBreadcrumb(@NonNull String str) {
        getClient().leaveBreadcrumb(str);
    }

    public static void addFeatureFlag(@NonNull String str, @Nullable String str2) {
        getClient().addFeatureFlag(str, str2);
    }

    @NonNull
    public static Client getClient() {
        if (client == null) {
            synchronized (lock) {
                if (client == null) {
                    throw new IllegalStateException("You must call Bugsnag.start before any other Bugsnag methods");
                }
            }
        }
        return client;
    }
}
