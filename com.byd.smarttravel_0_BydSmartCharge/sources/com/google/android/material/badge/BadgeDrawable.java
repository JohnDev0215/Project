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
import b.g.j.t;
import c.b.a.a.c.a;
import c.b.a.a.q.i;
import c.b.a.a.q.k;
import c.b.a.a.s.c;
import c.b.a.a.s.d;
import c.b.a.a.v.h;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$plurals;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements i.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2045b = R$style.Widget_MaterialComponents_Badge;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2046c = R$attr.badgeStyle;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<Context> f2047d;
    public final h e;
    public final i f;
    public final Rect g;
    public final float h;
    public final float i;
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
        this.f2047d = new WeakReference<>(context);
        k.c(context);
        Resources resources = context.getResources();
        this.g = new Rect();
        this.e = new h();
        this.h = resources.getDimensionPixelSize(R$dimen.mtrl_badge_radius);
        this.j = resources.getDimensionPixelSize(R$dimen.mtrl_badge_long_text_horizontal_padding);
        this.i = resources.getDimensionPixelSize(R$dimen.mtrl_badge_with_text_radius);
        i iVar = new i(this);
        this.f = iVar;
        iVar.e().setTextAlign(Paint.Align.CENTER);
        this.k = new SavedState(context);
        w(R$style.TextAppearance_MaterialComponents_Badge);
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f2046c, f2045b);
    }

    public static BadgeDrawable d(Context context, AttributeSet attributeSet, int i, int i2) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.m(context, attributeSet, i, i2);
        return badgeDrawable;
    }

    public static BadgeDrawable e(Context context, SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.o(savedState);
        return badgeDrawable;
    }

    public static int n(Context context, TypedArray typedArray, int i) {
        return c.a(context, typedArray, i).getDefaultColor();
    }

    public final void A() {
        this.n = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    @Override // c.b.a.a.q.i.b
    public void a() {
        invalidateSelf();
    }

    public final void b(Context context, Rect rect, View view) {
        int i = this.k.j;
        if (i == 8388691 || i == 8388693) {
            this.m = rect.bottom - this.k.l;
        } else {
            this.m = rect.top + this.k.l;
        }
        if (j() <= 9) {
            float f = !l() ? this.h : this.i;
            this.o = f;
            this.q = f;
            this.p = f;
        } else {
            float f2 = this.i;
            this.o = f2;
            this.q = f2;
            this.p = (this.f.f(g()) / 2.0f) + this.j;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(l() ? R$dimen.mtrl_badge_text_horizontal_edge_offset : R$dimen.mtrl_badge_horizontal_edge_offset);
        int i2 = this.k.j;
        if (i2 == 8388659 || i2 == 8388691) {
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
        this.e.draw(canvas);
        if (l()) {
            f(canvas);
        }
    }

    public final void f(Canvas canvas) {
        Rect rect = new Rect();
        String strG = g();
        this.f.e().getTextBounds(strG, 0, strG.length(), rect);
        canvas.drawText(strG, this.l, this.m + (rect.height() / 2), this.f.e());
    }

    public final String g() {
        if (j() <= this.n) {
            return Integer.toString(j());
        }
        Context context = this.f2047d.get();
        return context == null ? "" : context.getString(R$string.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.n), "+");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.k.f2050d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.g.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.g.width();
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
            return this.k.g;
        }
        if (this.k.h <= 0 || (context = this.f2047d.get()) == null) {
            return null;
        }
        return j() <= this.n ? context.getResources().getQuantityString(this.k.h, j(), Integer.valueOf(j())) : context.getString(this.k.i, Integer.valueOf(this.n));
    }

    public int i() {
        return this.k.f;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (l()) {
            return this.k.e;
        }
        return 0;
    }

    public SavedState k() {
        return this.k;
    }

    public boolean l() {
        return this.k.e != -1;
    }

    public final void m(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Badge, i, i2, new int[0]);
        t(typedArrayH.getInt(R$styleable.Badge_maxCharacterCount, 4));
        int i3 = R$styleable.Badge_number;
        if (typedArrayH.hasValue(i3)) {
            u(typedArrayH.getInt(i3, 0));
        }
        p(n(context, typedArrayH, R$styleable.Badge_backgroundColor));
        int i4 = R$styleable.Badge_badgeTextColor;
        if (typedArrayH.hasValue(i4)) {
            r(n(context, typedArrayH, i4));
        }
        q(typedArrayH.getInt(R$styleable.Badge_badgeGravity, 8388661));
        s(typedArrayH.getDimensionPixelOffset(R$styleable.Badge_horizontalOffset, 0));
        x(typedArrayH.getDimensionPixelOffset(R$styleable.Badge_verticalOffset, 0));
        typedArrayH.recycle();
    }

    public final void o(SavedState savedState) {
        t(savedState.f);
        if (savedState.e != -1) {
            u(savedState.e);
        }
        p(savedState.f2048b);
        r(savedState.f2049c);
        q(savedState.j);
        s(savedState.k);
        x(savedState.l);
    }

    @Override // android.graphics.drawable.Drawable, c.b.a.a.q.i.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i) {
        this.k.f2048b = i;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        if (this.e.x() != colorStateListValueOf) {
            this.e.X(colorStateListValueOf);
            invalidateSelf();
        }
    }

    public void q(int i) {
        if (this.k.j != i) {
            this.k.j = i;
            WeakReference<View> weakReference = this.r;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.r.get();
            WeakReference<ViewGroup> weakReference2 = this.s;
            y(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    public void r(int i) {
        this.k.f2049c = i;
        if (this.f.e().getColor() != i) {
            this.f.e().setColor(i);
            invalidateSelf();
        }
    }

    public void s(int i) {
        this.k.k = i;
        z();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.k.f2050d = i;
        this.f.e().setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void t(int i) {
        if (this.k.f != i) {
            this.k.f = i;
            A();
            this.f.i(true);
            z();
            invalidateSelf();
        }
    }

    public void u(int i) {
        int iMax = Math.max(0, i);
        if (this.k.e != iMax) {
            this.k.e = iMax;
            this.f.i(true);
            z();
            invalidateSelf();
        }
    }

    public final void v(d dVar) {
        Context context;
        if (this.f.d() == dVar || (context = this.f2047d.get()) == null) {
            return;
        }
        this.f.h(dVar, context);
        z();
    }

    public final void w(int i) {
        Context context = this.f2047d.get();
        if (context == null) {
            return;
        }
        v(new d(context, i));
    }

    public void x(int i) {
        this.k.l = i;
        z();
    }

    public void y(View view, ViewGroup viewGroup) {
        this.r = new WeakReference<>(view);
        this.s = new WeakReference<>(viewGroup);
        z();
        invalidateSelf();
    }

    public final void z() {
        Context context = this.f2047d.get();
        WeakReference<View> weakReference = this.r;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.g);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.s;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || a.f1683a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        a.f(this.g, this.l, this.m, this.p, this.q);
        this.e.V(this.o);
        if (rect.equals(this.g)) {
            return;
        }
        this.e.setBounds(this.g);
    }

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2048b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2049c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2050d;
        public int e;
        public int f;
        public CharSequence g;
        public int h;
        public int i;
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Context context) {
            this.f2050d = 255;
            this.e = -1;
            this.f2049c = new d(context, R$style.TextAppearance_MaterialComponents_Badge).f1826b.getDefaultColor();
            this.g = context.getString(R$string.mtrl_badge_numberless_content_description);
            this.h = R$plurals.mtrl_badge_content_description;
            this.i = R$string.mtrl_exceed_max_badge_number_content_description;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f2048b);
            parcel.writeInt(this.f2049c);
            parcel.writeInt(this.f2050d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeString(this.g.toString());
            parcel.writeInt(this.h);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }

        public SavedState(Parcel parcel) {
            this.f2050d = 255;
            this.e = -1;
            this.f2048b = parcel.readInt();
            this.f2049c = parcel.readInt();
            this.f2050d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readString();
            this.h = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }
    }
}
