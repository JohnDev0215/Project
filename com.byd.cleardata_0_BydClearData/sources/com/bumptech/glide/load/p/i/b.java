package com.bumptech.glide.load.p.i;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.n.v;
import com.bumptech.glide.load.p.d.q;
import com.bumptech.glide.t.j;

/* JADX INFO: loaded from: classes.dex */
public class b implements e<Bitmap, BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f2411a;

    public b(Resources resources) {
        this.f2411a = (Resources) j.d(resources);
    }

    @Override // com.bumptech.glide.load.p.i.e
    public v<BitmapDrawable> a(v<Bitmap> vVar, i iVar) {
        return q.g(this.f2411a, vVar);
    }
}
