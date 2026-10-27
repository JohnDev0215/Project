package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ke {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f411a = -1;
    public long b = -1;

    public double a() {
        long j = this.f411a;
        if (j < 0) {
            return 0.0d;
        }
        return this.b > j ? r2 - j : System.currentTimeMillis() - this.f411a;
    }

    public double b() {
        this.b = System.currentTimeMillis();
        return a();
    }
}
