package com.airbnb.lottie.u;

/* JADX INFO: loaded from: classes.dex */
public enum a {
    JSON(".json"),
    ZIP(".zip");

    public final String d;

    a(String str) {
        this.d = str;
    }

    public String a() {
        return ".temp" + this.d;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.d;
    }
}
