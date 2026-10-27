package com.bumptech.glide.load.n;

import com.bumptech.glide.load.n.b0.a;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class e<DataType> implements a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.d<DataType> f2110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DataType f2111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.i f2112c;

    e(com.bumptech.glide.load.d<DataType> dVar, DataType datatype, com.bumptech.glide.load.i iVar) {
        this.f2110a = dVar;
        this.f2111b = datatype;
        this.f2112c = iVar;
    }

    @Override // com.bumptech.glide.load.n.b0.a.b
    public boolean a(File file) {
        return this.f2110a.a(this.f2111b, file, this.f2112c);
    }
}
