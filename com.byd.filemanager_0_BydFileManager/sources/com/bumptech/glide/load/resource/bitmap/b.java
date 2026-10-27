package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.load.EncodeStrategy;
import java.io.File;

/* JADX INFO: compiled from: BitmapDrawableEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements com.bumptech.glide.load.g<BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.g<Bitmap> f3350b;

    public b(com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.g<Bitmap> gVar) {
        this.f3349a = eVar;
        this.f3350b = gVar;
    }

    @Override // com.bumptech.glide.load.g
    public EncodeStrategy b(com.bumptech.glide.load.e eVar) {
        return this.f3350b.b(eVar);
    }

    @Override // com.bumptech.glide.load.a
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(com.bumptech.glide.load.engine.s<BitmapDrawable> sVar, File file, com.bumptech.glide.load.e eVar) {
        return this.f3350b.a((Bitmap) new e(sVar.get().getBitmap(), this.f3349a), file, eVar);
    }
}
