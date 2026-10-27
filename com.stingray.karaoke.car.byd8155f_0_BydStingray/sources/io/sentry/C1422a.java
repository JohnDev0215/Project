package io.sentry;

/* JADX INFO: renamed from: io.sentry.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1422a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f15998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.protocol.F f15999b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f16000e;

    public C1422a(String str, String str2, byte[] bArr) {
        this.f15998a = bArr;
        this.f15999b = null;
        this.c = str;
        this.d = str2;
        this.f16000e = "event.attachment";
    }

    public C1422a(io.sentry.protocol.F f10) {
        this.f15998a = null;
        this.f15999b = f10;
        this.c = "view-hierarchy.json";
        this.d = "application/json";
        this.f16000e = "event.view_hierarchy";
    }
}
