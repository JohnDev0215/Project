package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.badge.BadgeState;
import defpackage.c83;
import defpackage.in3;
import defpackage.om3;
import defpackage.r83;
import defpackage.s73;
import defpackage.sc1;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: BadgeDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends Drawable implements c83.b {
    public static final int r = R$style.Widget_MaterialComponents_Badge;
    public static final int s = R$attr.badgeStyle;
    public final WeakReference<Context> e;
    public final sc1 f;
    public final c83 g;
    public final Rect h;
    public final BadgeState i;
    public float j;
    public float k;
    public int l;
    public float m;
    public float n;
    public float o;
    public WeakReference<View> p;
    public WeakReference<FrameLayout> q;

    public a(Context context, BadgeState.State state) {
        s73 s73Var;
        Context context2;
        WeakReference<Context> weakReference = new WeakReference<>(context);
        this.e = weakReference;
        r83.c(context, r83.b, "Theme.MaterialComponents");
        this.h = new Rect();
        sc1 sc1Var = new sc1();
        this.f = sc1Var;
        c83 c83Var = new c83(this);
        this.g = c83Var;
        TextPaint textPaint = c83Var.a;
        textPaint.setTextAlign(Paint.Align.CENTER);
        int i = R$style.TextAppearance_MaterialComponents_Badge;
        Context context3 = weakReference.get();
        if (context3 != null && c83Var.f != (s73Var = new s73(context3, i)) && (context2 = weakReference.get()) != null) {
            c83Var.b(s73Var, context2);
            h();
        }
        BadgeState badgeState = new BadgeState(context, state);
        this.i = badgeState;
        BadgeState.State state2 = badgeState.b;
        this.l = ((int) Math.pow(10.0d, ((double) state2.j) - 1.0d)) - 1;
        c83Var.d = true;
        h();
        invalidateSelf();
        c83Var.d = true;
        h();
        invalidateSelf();
        textPaint.setAlpha(getAlpha());
        invalidateSelf();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(state2.f.intValue());
        if (sc1Var.e.c != colorStateListValueOf) {
            sc1Var.n(colorStateListValueOf);
            invalidateSelf();
        }
        textPaint.setColor(state2.g.intValue());
        invalidateSelf();
        WeakReference<View> weakReference2 = this.p;
        if (weakReference2 != null && weakReference2.get() != null) {
            View view = this.p.get();
            WeakReference<FrameLayout> weakReference3 = this.q;
            g(view, weakReference3 != null ? weakReference3.get() : null);
        }
        h();
        setVisible(state2.p.booleanValue(), false);
    }

    @Override // c83.b
    public final void a() {
        invalidateSelf();
    }

    public final String b() {
        int iE = e();
        int i = this.l;
        BadgeState badgeState = this.i;
        if (iE <= i) {
            return NumberFormat.getInstance(badgeState.b.k).format(e());
        }
        Context context = this.e.get();
        return context == null ? "" : String.format(badgeState.b.k, context.getString(R$string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.l), "+");
    }

    public final CharSequence c() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        boolean zF = f();
        BadgeState badgeState = this.i;
        if (!zF) {
            return badgeState.b.l;
        }
        if (badgeState.b.m == 0 || (context = this.e.get()) == null) {
            return null;
        }
        int iE = e();
        int i = this.l;
        BadgeState.State state = badgeState.b;
        return iE <= i ? context.getResources().getQuantityString(state.m, e(), Integer.valueOf(e())) : context.getString(state.n, Integer.valueOf(i));
    }

    public final FrameLayout d() {
        WeakReference<FrameLayout> weakReference = this.q;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f.draw(canvas);
        if (f()) {
            Rect rect = new Rect();
            String strB = b();
            c83 c83Var = this.g;
            c83Var.a.getTextBounds(strB, 0, strB.length(), rect);
            canvas.drawText(strB, this.j, this.k + (rect.height() / 2), c83Var.a);
        }
    }

    public final int e() {
        if (f()) {
            return this.i.b.i;
        }
        return 0;
    }

    public final boolean f() {
        return this.i.b.i != -1;
    }

    public final void g(View view, FrameLayout frameLayout) {
        this.p = new WeakReference<>(view);
        this.q = new WeakReference<>(frameLayout);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        h();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.i.b.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.h.height();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.h.width();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final void h() {
        Context context = this.e.get();
        WeakReference<View> weakReference = this.p;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        Rect rect2 = this.h;
        rect.set(rect2);
        Rect rect3 = new Rect();
        view.getDrawingRect(rect3);
        WeakReference<FrameLayout> weakReference2 = this.q;
        FrameLayout frameLayout = weakReference2 != null ? weakReference2.get() : null;
        if (frameLayout != null) {
            frameLayout.offsetDescendantRectToMyCoords(view, rect3);
        }
        boolean zF = f();
        BadgeState badgeState = this.i;
        int iIntValue = badgeState.b.v.intValue() + (zF ? badgeState.b.t.intValue() : badgeState.b.r.intValue());
        BadgeState.State state = badgeState.b;
        int iIntValue2 = state.o.intValue();
        if (iIntValue2 == 8388691 || iIntValue2 == 8388693) {
            this.k = rect3.bottom - iIntValue;
        } else {
            this.k = rect3.top + iIntValue;
        }
        int iE = e();
        float f = badgeState.d;
        if (iE <= 9) {
            if (!f()) {
                f = badgeState.c;
            }
            this.m = f;
            this.o = f;
            this.n = f;
        } else {
            this.m = f;
            this.o = f;
            this.n = (this.g.a(b()) / 2.0f) + badgeState.e;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(f() ? R$dimen.mtrl_badge_text_horizontal_edge_offset : R$dimen.mtrl_badge_horizontal_edge_offset);
        int iIntValue3 = state.u.intValue() + (f() ? state.s.intValue() : state.q.intValue());
        int iIntValue4 = state.o.intValue();
        if (iIntValue4 == 8388659 || iIntValue4 == 8388691) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            this.j = om3.e.c(view) == 0 ? (rect3.left - this.n) + dimensionPixelSize + iIntValue3 : ((rect3.right + this.n) - dimensionPixelSize) - iIntValue3;
        } else {
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            this.j = om3.e.c(view) == 0 ? ((rect3.right + this.n) - dimensionPixelSize) - iIntValue3 : (rect3.left - this.n) + dimensionPixelSize + iIntValue3;
        }
        float f2 = this.j;
        float f3 = this.k;
        float f4 = this.n;
        float f5 = this.o;
        rect2.set((int) (f2 - f4), (int) (f3 - f5), (int) (f2 + f4), (int) (f3 + f5));
        float f6 = this.m;
        sc1 sc1Var = this.f;
        sc1Var.setShapeAppearanceModel(sc1Var.e.a.f(f6));
        if (rect.equals(rect2)) {
            return;
        }
        sc1Var.setBounds(rect2);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable, c83.b
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        BadgeState badgeState = this.i;
        badgeState.a.h = i;
        badgeState.b.h = i;
        this.g.a.setAlpha(getAlpha());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
