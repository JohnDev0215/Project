package com.bumptech.glide.load.p.f;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.n.r;
import com.bumptech.glide.load.n.v;
import com.bumptech.glide.t.j;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends Drawable> implements v<T>, r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final T f2384b;

    public b(T t) {
        this.f2384b = (T) j.d(t);
    }

    @Override // com.bumptech.glide.load.n.r
    public void a() {
        Bitmap bitmapE;
        T t = this.f2384b;
        if (t instanceof BitmapDrawable) {
            bitmapE = ((BitmapDrawable) t).getBitmap();
        } else if (!(t instanceof com.bumptech.glide.load.p.h.c)) {
            return;
        } else {
            bitmapE = ((com.bumptech.glide.load.p.h.c) t).e();
        }
        bitmapE.prepareToDraw();
    }

    @Override // com.bumptech.glide.load.n.v
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final T b() {
        Drawable.ConstantState constantState = this.f2384b.getConstantState();
        return constantState == null ? this.f2384b : (T) constantState.newDrawable();
    }
}
