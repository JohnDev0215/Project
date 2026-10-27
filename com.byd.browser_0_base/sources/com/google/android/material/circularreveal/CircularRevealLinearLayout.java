package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.material.circularreveal.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealLinearLayout extends LinearLayout implements c {
    public final b e;

    public CircularRevealLinearLayout(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.circularreveal.c
    public final void a() {
        this.e.getClass();
    }

    @Override // com.google.android.material.circularreveal.c
    public final void b() {
        this.e.getClass();
    }

    @Override // com.google.android.material.circularreveal.b.a
    public final void c(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // com.google.android.material.circularreveal.b.a
    public final boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.a(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.e.e;
    }

    @Override // com.google.android.material.circularreveal.c
    public int getCircularRevealScrimColor() {
        return this.e.b();
    }

    @Override // com.google.android.material.circularreveal.c
    public c.d getRevealInfo() {
        return this.e.c();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        b bVar = this.e;
        return bVar != null ? bVar.d() : super.isOpaque();
    }

    @Override // com.google.android.material.circularreveal.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.e.e(drawable);
    }

    @Override // com.google.android.material.circularreveal.c
    public void setCircularRevealScrimColor(int i) {
        this.e.f(i);
    }

    @Override // com.google.android.material.circularreveal.c
    public void setRevealInfo(c.d dVar) {
        this.e.g(dVar);
    }

    public CircularRevealLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new b(this);
    }
}
