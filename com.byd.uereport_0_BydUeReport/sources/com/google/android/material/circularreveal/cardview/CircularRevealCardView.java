package com.google.android.material.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.material.card.MaterialCardView;
import s5.b;
import s5.c;

/* JADX INFO: loaded from: classes2.dex */
public class CircularRevealCardView extends MaterialCardView implements c {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final b f3806v;

    public CircularRevealCardView(Context context) {
        this(context, null);
    }

    @Override // s5.c
    public void a() {
        this.f3806v.b();
    }

    @Override // s5.b.a
    public void b(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // s5.c
    public void c() {
        this.f3806v.a();
    }

    @Override // s5.b.a
    public boolean d() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        b bVar = this.f3806v;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f3806v.e();
    }

    @Override // s5.c
    public int getCircularRevealScrimColor() {
        return this.f3806v.f();
    }

    @Override // s5.c
    public c.e getRevealInfo() {
        return this.f3806v.h();
    }

    @Override // android.view.View
    public boolean isOpaque() {
        b bVar = this.f3806v;
        return bVar != null ? bVar.j() : super.isOpaque();
    }

    @Override // s5.c
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f3806v.k(drawable);
    }

    @Override // s5.c
    public void setCircularRevealScrimColor(int i8) {
        this.f3806v.l(i8);
    }

    @Override // s5.c
    public void setRevealInfo(c.e eVar) {
        this.f3806v.m(eVar);
    }

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3806v = new b(this);
    }
}
