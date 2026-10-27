package com.bumptech.glide.k;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ImageHeaderParserRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ImageHeaderParser> f2705a = new ArrayList();

    public synchronized void a(ImageHeaderParser imageHeaderParser) {
        this.f2705a.add(imageHeaderParser);
    }

    public synchronized List<ImageHeaderParser> b() {
        return this.f2705a;
    }
}
