package com.bumptech.glide.load;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class e extends IOException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2002b;

    public e(int i) {
        this("Http request failed with status code: " + i, i);
    }

    public e(String str) {
        this(str, -1);
    }

    public e(String str, int i) {
        this(str, i, null);
    }

    public e(String str, int i, Throwable th) {
        super(str, th);
        this.f2002b = i;
    }
}
