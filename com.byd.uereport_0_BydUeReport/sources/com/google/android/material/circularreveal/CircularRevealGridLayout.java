package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import s5.b;
import s5.c;

/* JADX INFO: loaded from: classes2.dex */
public class CircularRevealGridLayout extends GridLayout implements c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f3803d;

    public CircularRevealGridLayout(Context context) {
        this(context, null);
    }

    @Override // s5.c
    public void a() {
        this.f3803d.b();
    }

    @Override // s5.b.a
    public void b(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // s5.c
    public void c() {
        this.f3803d.a();
    }

    @Override // s5.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.f3803d;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f3803d.e();
    }

    @Override // s5.c
    public int getCircularRevealScrimColor() {
        return this.f3803d.f();
    }

    @Override // s5.c
    public c.e getRevealInfo() {
        return this.f3803d.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f3803d;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // s5.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f3803d.k(drawable);
    }

    @Override // s5.c
    public void setCircularRevealScrimColor(int i8) {
        this.f3803d.l(i8);
    }

    @Override // s5.c
    public void setRevealInfo(c.e eVar) {
        this.f3803d.m(eVar);
    }

    public CircularRevealGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3803d = new b(this);
    }
}
