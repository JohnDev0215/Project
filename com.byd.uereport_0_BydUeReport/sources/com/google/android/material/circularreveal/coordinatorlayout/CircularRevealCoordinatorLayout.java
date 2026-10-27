package com.google.android.material.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import s5.b;
import s5.c;

/* JADX INFO: loaded from: classes2.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements c {
    public final b C;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // s5.c
    public void a() {
        this.C.b();
    }

    @Override // s5.b.a
    public void b(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // s5.c
    public void c() {
        this.C.a();
    }

    @Override // s5.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.C;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.C.e();
    }

    @Override // s5.c
    public int getCircularRevealScrimColor() {
        return this.C.f();
    }

    @Override // s5.c
    public c.e getRevealInfo() {
        return this.C.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.C;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // s5.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.C.k(drawable);
    }

    @Override // s5.c
    public void setCircularRevealScrimColor(int i8) {
        this.C.l(i8);
    }

    @Override // s5.c
    public void setRevealInfo(c.e eVar) {
        this.C.m(eVar);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = new b(this);
    }
}
