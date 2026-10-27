package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import c.b.a.a.j.b;
import c.b.a.a.j.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealGridLayout extends GridLayout implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f2136b;

    public CircularRevealGridLayout(Context context) {
        this(context, null);
    }

    @Override // c.b.a.a.j.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // c.b.a.a.j.c
    public void b() {
        this.f2136b.a();
    }

    @Override // c.b.a.a.j.c
    public void c() {
        this.f2136b.b();
    }

    @Override // c.b.a.a.j.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.f2136b;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f2136b.e();
    }

    @Override // c.b.a.a.j.c
    public int getCircularRevealScrimColor() {
        return this.f2136b.f();
    }

    @Override // c.b.a.a.j.c
    public c.e getRevealInfo() {
        return this.f2136b.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f2136b;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // c.b.a.a.j.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f2136b.k(drawable);
    }

    @Override // c.b.a.a.j.c
    public void setCircularRevealScrimColor(int i) {
        this.f2136b.l(i);
    }

    @Override // c.b.a.a.j.c
    public void setRevealInfo(c.e eVar) {
        this.f2136b.m(eVar);
    }

    public CircularRevealGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2136b = new b(this);
    }
}
