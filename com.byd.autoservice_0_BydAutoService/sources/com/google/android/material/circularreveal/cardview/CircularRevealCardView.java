package com.google.android.material.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import b2.b;
import b2.c;
import com.google.android.material.card.MaterialCardView;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCardView extends MaterialCardView implements c {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b f2223t;

    public CircularRevealCardView(Context context) {
        this(context, null);
    }

    @Override // b2.b.a
    public void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // b2.c
    public void b() {
        this.f2223t.a();
    }

    @Override // b2.c
    public void c() {
        this.f2223t.b();
    }

    @Override // b2.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.f2223t;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f2223t.e();
    }

    @Override // b2.c
    public int getCircularRevealScrimColor() {
        return this.f2223t.f();
    }

    @Override // b2.c
    public c.e getRevealInfo() {
        return this.f2223t.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f2223t;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // b2.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f2223t.k(drawable);
    }

    @Override // b2.c
    public void setCircularRevealScrimColor(int i3) {
        this.f2223t.l(i3);
    }

    @Override // b2.c
    public void setRevealInfo(c.e eVar) {
        this.f2223t.m(eVar);
    }

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2223t = new b(this);
    }
}
