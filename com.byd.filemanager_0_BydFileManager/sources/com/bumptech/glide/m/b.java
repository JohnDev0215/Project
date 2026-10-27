package com.bumptech.glide.m;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ImageHeaderParserRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ImageHeaderParser> f3407a = new ArrayList();

    public synchronized void a(ImageHeaderParser imageHeaderParser) {
        this.f3407a.add(imageHeaderParser);
    }

    public synchronized List<ImageHeaderParser> b() {
        return this.f3407a;
    }
}
