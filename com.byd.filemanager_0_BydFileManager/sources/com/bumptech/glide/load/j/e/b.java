package com.bumptech.glide.load.j.e;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.o;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.o.j;

/* JADX INFO: compiled from: DrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends Drawable> implements s<T>, o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final T f3292b;

    public b(T t) {
        this.f3292b = (T) j.d(t);
    }

    @Override // com.bumptech.glide.load.engine.o
    public void b() {
        T t = this.f3292b;
        if (t instanceof BitmapDrawable) {
            ((BitmapDrawable) t).getBitmap().prepareToDraw();
        } else if (t instanceof com.bumptech.glide.load.j.g.c) {
            ((com.bumptech.glide.load.j.g.c) t).e().prepareToDraw();
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final T get() {
        Drawable.ConstantState constantState = this.f3292b.getConstantState();
        return constantState == null ? this.f3292b : (T) constantState.newDrawable();
    }
}
