package io.sentry;

/* JADX INFO: renamed from: io.sentry.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1469h {
    All("__all__"),
    Default("default"),
    Error("error"),
    Session("session"),
    Attachment("attachment"),
    Monitor("monitor"),
    Profile("profile"),
    MetricBucket("metric_bucket"),
    Transaction("transaction"),
    Replay("replay"),
    Span("span"),
    Security("security"),
    UserReport("user_report"),
    Unknown("unknown");

    private final String category;

    EnumC1469h(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }
}
