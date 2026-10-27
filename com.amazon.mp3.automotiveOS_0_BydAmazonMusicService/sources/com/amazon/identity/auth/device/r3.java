package com.amazon.identity.auth.device;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class r3 {
    public static final String b = "com.amazon.identity.auth.device.r3";
    public static volatile r3 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, String> f523a = new ConcurrentHashMap<>();

    public static r3 a() {
        if (c == null) {
            c = new r3();
        }
        return c;
    }

    public void b() {
        ga.a(b);
        this.f523a.clear();
    }
}
