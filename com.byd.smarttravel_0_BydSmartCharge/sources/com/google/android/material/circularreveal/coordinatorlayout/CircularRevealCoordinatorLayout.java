package com.google.android.material.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.b.a.a.j.b;
import c.b.a.a.j.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements c {
    public final b A;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // c.b.a.a.j.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // c.b.a.a.j.c
    public void b() {
        this.A.a();
    }

    @Override // c.b.a.a.j.c
    public void c() {
        this.A.b();
    }

    @Override // c.b.a.a.j.b.a
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

    @Override // c.b.a.a.j.c
    public int getCircularRevealScrimColor() {
        return this.A.f();
    }

    @Override // c.b.a.a.j.c
    public c.e getRevealInfo() {
        return this.A.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.A;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // c.b.a.a.j.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.A.k(drawable);
    }

    @Override // c.b.a.a.j.c
    public void setCircularRevealScrimColor(int i) {
        this.A.l(i);
    }

    @Override // c.b.a.a.j.c
    public void setRevealInfo(c.e eVar) {
        this.A.m(eVar);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = new b(this);
    }
}
