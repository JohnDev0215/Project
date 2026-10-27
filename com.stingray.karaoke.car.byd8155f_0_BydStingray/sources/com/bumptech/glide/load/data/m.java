package com.bumptech.glide.load.data;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class m implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F.l f12732a;

    public m(F.l lVar) {
        this.f12732a = lVar;
    }

    @Override // com.bumptech.glide.load.data.f
    public final Class a() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.data.f
    public final g b(Object obj) {
        return new i((InputStream) obj, this.f12732a);
    }
}
