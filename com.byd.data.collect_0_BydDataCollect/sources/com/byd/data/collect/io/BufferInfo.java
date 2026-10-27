package com.byd.data.collect.io;

import java.io.File;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes.dex */
public class BufferInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    BufferedSink f3150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    File f3151b;

    public BufferInfo(BufferedSink bufferedSink, File file) {
        this.f3150a = bufferedSink;
        this.f3151b = file;
    }

    public BufferedSink a() {
        return this.f3150a;
    }

    public File b() {
        return this.f3151b;
    }
}
