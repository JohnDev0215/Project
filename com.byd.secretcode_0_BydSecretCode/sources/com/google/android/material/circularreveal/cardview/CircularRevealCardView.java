package com.google.android.material.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.material.card.MaterialCardView;
import defpackage.ai;
import defpackage.zh;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCardView extends MaterialCardView implements ai {
    public final zh t;

    public CircularRevealCardView(Context context) {
        this(context, null);
    }

    @Override // zh.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.ai
    public void b() {
        this.t.a();
    }

    @Override // defpackage.ai
    public void c() {
        this.t.b();
    }

    @Override // zh.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        zh zhVar = this.t;
        if (zhVar != null) {
            zhVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.t.e();
    }

    @Override // defpackage.ai
    public int getCircularRevealScrimColor() {
        return this.t.f();
    }

    @Override // defpackage.ai
    public ai.e getRevealInfo() {
        return this.t.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        zh zhVar = this.t;
        return zhVar != null ? zhVar.j() : super.isOpaque();
    }

    @Override // defpackage.ai
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.t.k(drawable);
    }

    @Override // defpackage.ai
    public void setCircularRevealScrimColor(int i) {
        this.t.l(i);
    }

    @Override // defpackage.ai
    public void setRevealInfo(ai.e eVar) {
        this.t.m(eVar);
    }

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = new zh(this);
    }
}
