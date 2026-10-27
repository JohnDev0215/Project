package com.bumptech.glide.load.p.h;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.m;
import com.bumptech.glide.load.n.v;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class f implements m<c> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<Bitmap> f2397b;

    public f(m<Bitmap> mVar) {
        this.f2397b = (m) com.bumptech.glide.t.j.d(mVar);
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        this.f2397b.a(messageDigest);
    }

    @Override // com.bumptech.glide.load.m
    public v<c> b(Context context, v<c> vVar, int i, int i2) {
        c cVarB = vVar.b();
        v<Bitmap> eVar = new com.bumptech.glide.load.p.d.e(cVarB.e(), com.bumptech.glide.c.c(context).f());
        v<Bitmap> vVarB = this.f2397b.b(context, eVar, i, i2);
        if (!eVar.equals(vVarB)) {
            eVar.f();
        }
        cVarB.m(this.f2397b, vVarB.b());
        return vVar;
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f2397b.equals(((f) obj).f2397b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        return this.f2397b.hashCode();
    }
}
