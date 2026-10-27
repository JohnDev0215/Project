package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import defpackage.ai;
import defpackage.zh;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealGridLayout extends GridLayout implements ai {
    public final zh b;

    public CircularRevealGridLayout(Context context) {
        this(context, null);
    }

    @Override // zh.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.ai
    public void b() {
        this.b.a();
    }

    @Override // defpackage.ai
    public void c() {
        this.b.b();
    }

    @Override // zh.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        zh zhVar = this.b;
        if (zhVar != null) {
            zhVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.b.e();
    }

    @Override // defpackage.ai
    public int getCircularRevealScrimColor() {
        return this.b.f();
    }

    @Override // defpackage.ai
    public ai.e getRevealInfo() {
        return this.b.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        zh zhVar = this.b;
        return zhVar != null ? zhVar.j() : super.isOpaque();
    }

    @Override // defpackage.ai
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.b.k(drawable);
    }

    @Override // defpackage.ai
    public void setCircularRevealScrimColor(int i) {
        this.b.l(i);
    }

    @Override // defpackage.ai
    public void setRevealInfo(ai.e eVar) {
        this.b.m(eVar);
    }

    public CircularRevealGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new zh(this);
    }
}
