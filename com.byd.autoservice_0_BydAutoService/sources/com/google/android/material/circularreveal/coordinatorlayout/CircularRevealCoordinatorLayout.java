package com.google.android.material.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b2.b;
import b2.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements c {
    public final b A;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // b2.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // b2.c
    public void b() {
        this.A.a();
    }

    @Override // b2.c
    public void c() {
        this.A.b();
    }

    @Override // b2.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.A;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.A.e();
    }

    @Override // b2.c
    public int getCircularRevealScrimColor() {
        return this.A.f();
    }

    @Override // b2.c
    public c.e getRevealInfo() {
        return this.A.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.A;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // b2.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.A.k(drawable);
    }

    @Override // b2.c
    public void setCircularRevealScrimColor(int i3) {
        this.A.l(i3);
    }

    @Override // b2.c
    public void setRevealInfo(c.e eVar) {
        this.A.m(eVar);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = new b(this);
    }
}
