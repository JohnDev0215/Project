package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.engine.y.a;
import java.io.File;

/* JADX INFO: compiled from: DataCacheWriter.java */
/* JADX INFO: loaded from: classes.dex */
class d<DataType> implements a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.a<DataType> f3008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DataType f3009b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.e f3010c;

    d(com.bumptech.glide.load.a<DataType> aVar, DataType datatype, com.bumptech.glide.load.e eVar) {
        this.f3008a = aVar;
        this.f3009b = datatype;
        this.f3010c = eVar;
    }

    @Override // com.bumptech.glide.load.engine.y.a.b
    public boolean a(File file) {
        return this.f3008a.a(this.f3009b, file, this.f3010c);
    }
}
