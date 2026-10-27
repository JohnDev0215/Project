package com.bumptech.glide.load.n.b0;

import com.bumptech.glide.load.n.b0.a;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class d implements a.InterfaceC0067a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f2067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f2068b;

    public interface a {
        File a();
    }

    public d(a aVar, long j) {
        this.f2067a = j;
        this.f2068b = aVar;
    }

    @Override // com.bumptech.glide.load.n.b0.a.InterfaceC0067a
    public com.bumptech.glide.load.n.b0.a a() {
        File fileA = this.f2068b.a();
        if (fileA == null) {
            return null;
        }
        if (fileA.mkdirs() || (fileA.exists() && fileA.isDirectory())) {
            return e.c(fileA, this.f2067a);
        }
        return null;
    }
}
