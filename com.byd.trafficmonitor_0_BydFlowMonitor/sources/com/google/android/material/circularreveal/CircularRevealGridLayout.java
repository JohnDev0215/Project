package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import c.b.a.a.v.b;
import c.b.a.a.v.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealGridLayout extends GridLayout implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f4651b;

    public CircularRevealGridLayout(Context context) {
        this(context, null);
    }

    @Override // c.b.a.a.v.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // c.b.a.a.v.c
    public void b() {
        this.f4651b.a();
    }

    @Override // c.b.a.a.v.c
    public void c() {
        this.f4651b.b();
    }

    @Override // c.b.a.a.v.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.f4651b;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f4651b.e();
    }

    @Override // c.b.a.a.v.c
    public int getCircularRevealScrimColor() {
        return this.f4651b.f();
    }

    @Override // c.b.a.a.v.c
    public c.e getRevealInfo() {
        return this.f4651b.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f4651b;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // c.b.a.a.v.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f4651b.k(drawable);
    }

    @Override // c.b.a.a.v.c
    public void setCircularRevealScrimColor(int i2) {
        this.f4651b.l(i2);
    }

    @Override // c.b.a.a.v.c
    public void setRevealInfo(c.e eVar) {
        this.f4651b.m(eVar);
    }

    public CircularRevealGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4651b = new b(this);
    }
}
