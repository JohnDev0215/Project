package com.bumptech.glide.load.k.e;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.o;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.m.j;

/* JADX INFO: compiled from: DrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<T extends Drawable> implements s<T>, o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final T f3063b;

    public b(T t) {
        this.f3063b = (T) j.d(t);
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final T get() {
        Drawable.ConstantState constantState = this.f3063b.getConstantState();
        return constantState == null ? this.f3063b : (T) constantState.newDrawable();
    }

    @Override // com.bumptech.glide.load.engine.o
    public void initialize() {
        T t = this.f3063b;
        if (t instanceof BitmapDrawable) {
            ((BitmapDrawable) t).getBitmap().prepareToDraw();
        } else if (t instanceof com.bumptech.glide.load.k.g.c) {
            ((com.bumptech.glide.load.k.g.c) t).e().prepareToDraw();
        }
    }
}
