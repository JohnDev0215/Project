package com.google.android.datatransport.runtime.backends;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BackendResponse$Status {
    public static final BackendResponse$Status c;
    public static final BackendResponse$Status e;
    public static final BackendResponse$Status k;
    public static final BackendResponse$Status l;
    public static final /* synthetic */ BackendResponse$Status[] m;

    static {
        BackendResponse$Status backendResponse$Status = new BackendResponse$Status("OK", 0);
        c = backendResponse$Status;
        BackendResponse$Status backendResponse$Status2 = new BackendResponse$Status("TRANSIENT_ERROR", 1);
        e = backendResponse$Status2;
        BackendResponse$Status backendResponse$Status3 = new BackendResponse$Status("FATAL_ERROR", 2);
        k = backendResponse$Status3;
        BackendResponse$Status backendResponse$Status4 = new BackendResponse$Status("INVALID_PAYLOAD", 3);
        l = backendResponse$Status4;
        m = new BackendResponse$Status[]{backendResponse$Status, backendResponse$Status2, backendResponse$Status3, backendResponse$Status4};
    }

    public static BackendResponse$Status valueOf(String str) {
        return (BackendResponse$Status) Enum.valueOf(BackendResponse$Status.class, str);
    }

    public static BackendResponse$Status[] values() {
        return (BackendResponse$Status[]) m.clone();
    }
}
