package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.engine.y.a;
import java.io.File;

/* JADX INFO: compiled from: DataCacheWriter.java */
/* JADX INFO: loaded from: classes.dex */
class d<DataType> implements a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.a<DataType> f2773a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DataType f2774b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.e f2775c;

    d(com.bumptech.glide.load.a<DataType> aVar, DataType datatype, com.bumptech.glide.load.e eVar) {
        this.f2773a = aVar;
        this.f2774b = datatype;
        this.f2775c = eVar;
    }

    @Override // com.bumptech.glide.load.engine.y.a.b
    public boolean a(File file) {
        return this.f2773a.a(this.f2774b, file, this.f2775c);
    }
}
