package com.byd.filemanager.glide;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.bumptech.glide.load.i.n;
import com.bumptech.glide.load.i.o;
import com.bumptech.glide.load.i.r;

/* JADX INFO: compiled from: VideoLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements n<f, Bitmap> {

    /* JADX INFO: compiled from: VideoLoader.java */
    public static class a implements o<f, Bitmap> {
        @Override // com.bumptech.glide.load.i.o
        public n<f, Bitmap> b(r rVar) {
            return new e();
        }
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<Bitmap> a(f fVar, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.n.b(fVar.a()), new d(fVar));
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(f fVar) {
        return !TextUtils.isEmpty(fVar.a());
    }
}
