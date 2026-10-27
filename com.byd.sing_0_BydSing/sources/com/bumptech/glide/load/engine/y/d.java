package com.bumptech.glide.load.engine.y;

import com.bumptech.glide.load.engine.y.a;
import java.io.File;

/* JADX INFO: compiled from: DiskLruCacheFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements a.InterfaceC0089a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f2881a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f2882b;

    /* JADX INFO: compiled from: DiskLruCacheFactory.java */
    public interface a {
        File a();
    }

    public d(a aVar, long j) {
        this.f2881a = j;
        this.f2882b = aVar;
    }

    @Override // com.bumptech.glide.load.engine.y.a.InterfaceC0089a
    public com.bumptech.glide.load.engine.y.a a() {
        File fileA = this.f2882b.a();
        if (fileA == null) {
            return null;
        }
        if (fileA.mkdirs() || (fileA.exists() && fileA.isDirectory())) {
            return e.c(fileA, this.f2881a);
        }
        return null;
    }
}
