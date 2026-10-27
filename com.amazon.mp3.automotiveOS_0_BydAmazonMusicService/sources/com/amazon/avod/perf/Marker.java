package com.amazon.avod.perf;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class Marker {
    private final String mName;

    public Marker(String str) {
        this.mName = str;
    }

    public String toString() {
        return String.format(Locale.US, "Marker[%s]", this.mName);
    }
}
