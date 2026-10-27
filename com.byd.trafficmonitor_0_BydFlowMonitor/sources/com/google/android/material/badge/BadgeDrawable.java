package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import b.h.m.t;
import c.b.a.a.b;
import c.b.a.a.d;
import c.b.a.a.d0.i;
import c.b.a.a.f0.c;
import c.b.a.a.i0.h;
import c.b.a.a.j;
import c.b.a.a.k;
import c.b.a.a.l;
import c.b.a.a.o.a;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements i.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4489b = k.Widget_MaterialComponents_Badge;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f4490c = b.badgeStyle;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<Context> f4491d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f4492e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f4493f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f4494g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f4495h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f4496i;
    public final float j;
    public final SavedState k;
    public float l;
    public float m;
    public int n;
    public float o;
    public float p;
    public float q;
    public WeakReference<View> r;
    public WeakReference<ViewGroup> s;

    public BadgeDrawable(Context context) {
        this.f4491d = new WeakReference<>(context);
        c.b.a.a.d0.k.c(context);
        Resources resources = context.getResources();
        this.f4494g = new Rect();
        this.f4492e = new h();
        this.f4495h = resources.getDimensionPixelSize(d.mtrl_badge_radius);
        this.j = resources.getDimensionPixelSize(d.mtrl_badge_long_text_horizontal_padding);
        this.f4496i = resources.getDimensionPixelSize(d.mtrl_badge_with_text_radius);
        i iVar = new i(this);
        this.f4493f = iVar;
        iVar.e().setTextAlign(Paint.Align.CENTER);
        this.k = new SavedState(context);
        w(k.TextAppearance_MaterialComponents_Badge);
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f4490c, f4489b);
    }

    public static BadgeDrawable d(Context context, AttributeSet attributeSet, int i2, int i3) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.m(context, attributeSet, i2, i3);
        return badgeDrawable;
    }

    public static BadgeDrawable e(Context context, SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.o(savedState);
        return badgeDrawable;
    }

    public static int n(Context context, TypedArray typedArray, int i2) {
        return c.a(context, typedArray, i2).getDefaultColor();
    }

    public final void A() {
        this.n = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    @Override // c.b.a.a.d0.i.b
    public void a() {
        invalidateSelf();
    }

    public final void b(Context context, Rect rect, View view) {
        int i2 = this.k.j;
        if (i2 == 8388691 || i2 == 8388693) {
            this.m = rect.bottom - this.k.l;
        } else {
            this.m = rect.top + this.k.l;
        }
        if (j() <= 9) {
            float f2 = !l() ? this.f4495h : this.f4496i;
            this.o = f2;
            this.q = f2;
            this.p = f2;
        } else {
            float f3 = this.f4496i;
            this.o = f3;
            this.q = f3;
            this.p = (this.f4493f.f(g()) / 2.0f) + this.j;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(l() ? d.mtrl_badge_text_horizontal_edge_offset : d.mtrl_badge_horizontal_edge_offset);
        int i3 = this.k.j;
        if (i3 == 8388659 || i3 == 8388691) {
            this.l = t.z(view) == 0 ? (rect.left - this.p) + dimensionPixelSize + this.k.k : ((rect.right + this.p) - dimensionPixelSize) - this.k.k;
        } else {
            this.l = t.z(view) == 0 ? ((rect.right + this.p) - dimensionPixelSize) - this.k.k : (rect.left - this.p) + dimensionPixelSize + this.k.k;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f4492e.draw(canvas);
        if (l()) {
            f(canvas);
        }
    }

    public final void f(Canvas canvas) {
        Rect rect = new Rect();
        String strG = g();
        this.f4493f.e().getTextBounds(strG, 0, strG.length(), rect);
        canvas.drawText(strG, this.l, this.m + (rect.height() / 2), this.f4493f.e());
    }

    public final String g() {
        if (j() <= this.n) {
            return Integer.toString(j());
        }
        Context context = this.f4491d.get();
        return context == null ? "" : context.getString(j.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.n), "+");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.k.f4499d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f4494g.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f4494g.width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public CharSequence h() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        if (!l()) {
            return this.k.f4502g;
        }
        if (this.k.f4503h <= 0 || (context = this.f4491d.get()) == null) {
            return null;
        }
        return j() <= this.n ? context.getResources().getQuantityString(this.k.f4503h, j(), Integer.valueOf(j())) : context.getString(this.k.f4504i, Integer.valueOf(this.n));
    }

    public int i() {
        return this.k.f4501f;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (l()) {
            return this.k.f4500e;
        }
        return 0;
    }

    public SavedState k() {
        return this.k;
    }

    public boolean l() {
        return this.k.f4500e != -1;
    }

    public final void m(Context context, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context, attributeSet, l.Badge, i2, i3, new int[0]);
        t(typedArrayH.getInt(l.Badge_maxCharacterCount, 4));
        int i4 = l.Badge_number;
        if (typedArrayH.hasValue(i4)) {
            u(typedArrayH.getInt(i4, 0));
        }
        p(n(context, typedArrayH, l.Badge_backgroundColor));
        int i5 = l.Badge_badgeTextColor;
        if (typedArrayH.hasValue(i5)) {
            r(n(context, typedArrayH, i5));
        }
        q(typedArrayH.getInt(l.Badge_badgeGravity, 8388661));
        s(typedArrayH.getDimensionPixelOffset(l.Badge_horizontalOffset, 0));
        x(typedArrayH.getDimensionPixelOffset(l.Badge_verticalOffset, 0));
        typedArrayH.recycle();
    }

    public final void o(SavedState savedState) {
        t(savedState.f4501f);
        if (savedState.f4500e != -1) {
            u(savedState.f4500e);
        }
        p(savedState.f4497b);
        r(savedState.f4498c);
        q(savedState.j);
        s(savedState.k);
        x(savedState.l);
    }

    @Override // android.graphics.drawable.Drawable, c.b.a.a.d0.i.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i2) {
        this.k.f4497b = i2;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i2);
        if (this.f4492e.x() != colorStateListValueOf) {
            this.f4492e.X(colorStateListValueOf);
            invalidateSelf();
        }
    }

    public void q(int i2) {
        if (this.k.j != i2) {
            this.k.j = i2;
            WeakReference<View> weakReference = this.r;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.r.get();
            WeakReference<ViewGroup> weakReference2 = this.s;
            y(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    public void r(int i2) {
        this.k.f4498c = i2;
        if (this.f4493f.e().getColor() != i2) {
            this.f4493f.e().setColor(i2);
            invalidateSelf();
        }
    }

    public void s(int i2) {
        this.k.k = i2;
        z();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.k.f4499d = i2;
        this.f4493f.e().setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void t(int i2) {
        if (this.k.f4501f != i2) {
            this.k.f4501f = i2;
            A();
            this.f4493f.i(true);
            z();
            invalidateSelf();
        }
    }

    public void u(int i2) {
        int iMax = Math.max(0, i2);
        if (this.k.f4500e != iMax) {
            this.k.f4500e = iMax;
            this.f4493f.i(true);
            z();
            invalidateSelf();
        }
    }

    public final void v(c.b.a.a.f0.d dVar) {
        Context context;
        if (this.f4493f.d() == dVar || (context = this.f4491d.get()) == null) {
            return;
        }
        this.f4493f.h(dVar, context);
        z();
    }

    public final void w(int i2) {
        Context context = this.f4491d.get();
        if (context == null) {
            return;
        }
        v(new c.b.a.a.f0.d(context, i2));
    }

    public void x(int i2) {
        this.k.l = i2;
        z();
    }

    public void y(View view, ViewGroup viewGroup) {
        this.r = new WeakReference<>(view);
        this.s = new WeakReference<>(viewGroup);
        z();
        invalidateSelf();
    }

    public final void z() {
        Context context = this.f4491d.get();
        WeakReference<View> weakReference = this.r;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f4494g);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.s;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || a.f4058a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        a.f(this.f4494g, this.l, this.m, this.p, this.q);
        this.f4492e.V(this.o);
        if (rect.equals(this.f4494g)) {
            return;
        }
        this.f4492e.setBounds(this.f4494g);
    }

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4497b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4498c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4499d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4500e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f4501f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public CharSequence f4502g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f4503h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f4504i;
        public int j;
        public int k;
        public int l;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Context context) {
            this.f4499d = 255;
            this.f4500e = -1;
            this.f4498c = new c.b.a.a.f0.d(context, k.TextAppearance_MaterialComponents_Badge).f3817b.getDefaultColor();
            this.f4502g = context.getString(j.mtrl_badge_numberless_content_description);
            this.f4503h = c.b.a.a.i.mtrl_badge_content_description;
            this.f4504i = j.mtrl_exceed_max_badge_number_content_description;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f4497b);
            parcel.writeInt(this.f4498c);
            parcel.writeInt(this.f4499d);
            parcel.writeInt(this.f4500e);
            parcel.writeInt(this.f4501f);
            parcel.writeString(this.f4502g.toString());
            parcel.writeInt(this.f4503h);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }

        public SavedState(Parcel parcel) {
            this.f4499d = 255;
            this.f4500e = -1;
            this.f4497b = parcel.readInt();
            this.f4498c = parcel.readInt();
            this.f4499d = parcel.readInt();
            this.f4500e = parcel.readInt();
            this.f4501f = parcel.readInt();
            this.f4502g = parcel.readString();
            this.f4503h = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }
    }
}
