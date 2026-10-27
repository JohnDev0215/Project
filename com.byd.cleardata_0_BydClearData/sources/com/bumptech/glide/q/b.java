package com.bumptech.glide.q;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ImageHeaderParser> f2481a = new ArrayList();

    public synchronized void a(ImageHeaderParser imageHeaderParser) {
        this.f2481a.add(imageHeaderParser);
    }

    public synchronized List<ImageHeaderParser> b() {
        return this.f2481a;
    }
}
