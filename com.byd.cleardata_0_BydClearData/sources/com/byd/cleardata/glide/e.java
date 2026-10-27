package com.byd.cleardata.glide;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.o.n;
import com.bumptech.glide.load.o.o;
import com.bumptech.glide.load.o.r;

/* JADX INFO: loaded from: classes.dex */
public class e implements n<f, Bitmap> {

    public static class a implements o<f, Bitmap> {
        @Override // com.bumptech.glide.load.o.o
        public n<f, Bitmap> b(r rVar) {
            return new e();
        }
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Bitmap> a(f fVar, int i, int i2, i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(fVar.a()), new d(fVar));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(f fVar) {
        return !TextUtils.isEmpty(fVar.a());
    }
}
