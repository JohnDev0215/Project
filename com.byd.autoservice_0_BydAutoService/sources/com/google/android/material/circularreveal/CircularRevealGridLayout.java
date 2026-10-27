package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import b2.b;
import b2.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealGridLayout extends GridLayout implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f2220b;

    public CircularRevealGridLayout(Context context) {
        this(context, null);
    }

    @Override // b2.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // b2.c
    public void b() {
        this.f2220b.a();
    }

    @Override // b2.c
    public void c() {
        this.f2220b.b();
    }

    @Override // b2.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.f2220b;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f2220b.e();
    }

    @Override // b2.c
    public int getCircularRevealScrimColor() {
        return this.f2220b.f();
    }

    @Override // b2.c
    public c.e getRevealInfo() {
        return this.f2220b.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f2220b;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // b2.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f2220b.k(drawable);
    }

    @Override // b2.c
    public void setCircularRevealScrimColor(int i3) {
        this.f2220b.l(i3);
    }

    @Override // b2.c
    public void setRevealInfo(c.e eVar) {
        this.f2220b.m(eVar);
    }

    public CircularRevealGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2220b = new b(this);
    }
}
