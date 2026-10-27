package com.google.android.material.circularreveal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import c.b.a.a.j.b;
import c.b.a.a.j.c;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealFrameLayout extends FrameLayout implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f2135b;

    public CircularRevealFrameLayout(Context context) {
        this(context, null);
    }

    @Override // c.b.a.a.j.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // c.b.a.a.j.c
    public void b() {
        this.f2135b.a();
    }

    @Override // c.b.a.a.j.c
    public void c() {
        this.f2135b.b();
    }

    @Override // c.b.a.a.j.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
        b bVar = this.f2135b;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f2135b.e();
    }

    @Override // c.b.a.a.j.c
    public int getCircularRevealScrimColor() {
        return this.f2135b.f();
    }

    @Override // c.b.a.a.j.c
    public c.e getRevealInfo() {
        return this.f2135b.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f2135b;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // c.b.a.a.j.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f2135b.k(drawable);
    }

    @Override // c.b.a.a.j.c
    public void setCircularRevealScrimColor(int i) {
        this.f2135b.l(i);
    }

    @Override // c.b.a.a.j.c
    public void setRevealInfo(c.e eVar) {
        this.f2135b.m(eVar);
    }

    public CircularRevealFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2135b = new b(this);
    }
}
