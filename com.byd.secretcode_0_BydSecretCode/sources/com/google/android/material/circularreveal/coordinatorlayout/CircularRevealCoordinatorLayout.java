package com.google.android.material.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.ai;
import defpackage.zh;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements ai {
    public final zh A;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // zh.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.ai
    public void b() {
        this.A.a();
    }

    @Override // defpackage.ai
    public void c() {
        this.A.b();
    }

    @Override // zh.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        zh zhVar = this.A;
        if (zhVar != null) {
            zhVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.A.e();
    }

    @Override // defpackage.ai
    public int getCircularRevealScrimColor() {
        return this.A.f();
    }

    @Override // defpackage.ai
    public ai.e getRevealInfo() {
        return this.A.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        zh zhVar = this.A;
        return zhVar != null ? zhVar.j() : super.isOpaque();
    }

    @Override // defpackage.ai
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.A.k(drawable);
    }

    @Override // defpackage.ai
    public void setCircularRevealScrimColor(int i) {
        this.A.l(i);
    }

    @Override // defpackage.ai
    public void setRevealInfo(ai.e eVar) {
        this.A.m(eVar);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = new zh(this);
    }
}
