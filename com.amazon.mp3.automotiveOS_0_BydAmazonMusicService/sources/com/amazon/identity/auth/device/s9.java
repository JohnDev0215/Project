package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class s9 {
    public static s9 e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f544a;
    public final int b;
    public final int c;
    public final String d;

    public s9(int i, int i2, String str) {
        this.f544a = i;
        this.b = i2;
        this.d = str;
        this.c = (i * 10000000) + i2;
    }

    public static synchronized s9 a() {
        s9 s9Var = e;
        if (s9Var != null) {
            return s9Var;
        }
        s9 s9Var2 = new s9(13, 50002, "MAPAndroidLib-1.3.13387.0");
        e = s9Var2;
        return s9Var2;
    }

    public static String b() {
        return String.valueOf(a().c);
    }

    public String toString() {
        return this.c + " / " + this.d;
    }
}
