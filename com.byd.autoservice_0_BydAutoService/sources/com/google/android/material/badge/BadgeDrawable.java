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
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$plurals;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.i;
import i2.k;
import java.lang.ref.WeakReference;
import k2.c;
import k2.d;
import l0.t;
import n2.h;
import u1.a;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements i.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1948b = R$style.Widget_MaterialComponents_Badge;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f1949c = R$attr.badgeStyle;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<Context> f1950d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f1951e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f1952f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f1953g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f1954h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f1955i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f1956j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final SavedState f1957k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f1958l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f1959m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1960n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f1961o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f1962p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f1963q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public WeakReference<View> f1964r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public WeakReference<ViewGroup> f1965s;

    public BadgeDrawable(Context context) {
        this.f1950d = new WeakReference<>(context);
        k.c(context);
        Resources resources = context.getResources();
        this.f1953g = new Rect();
        this.f1951e = new h();
        this.f1954h = resources.getDimensionPixelSize(R$dimen.mtrl_badge_radius);
        this.f1956j = resources.getDimensionPixelSize(R$dimen.mtrl_badge_long_text_horizontal_padding);
        this.f1955i = resources.getDimensionPixelSize(R$dimen.mtrl_badge_with_text_radius);
        i iVar = new i(this);
        this.f1952f = iVar;
        iVar.e().setTextAlign(Paint.Align.CENTER);
        this.f1957k = new SavedState(context);
        w(R$style.TextAppearance_MaterialComponents_Badge);
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f1949c, f1948b);
    }

    public static BadgeDrawable d(Context context, AttributeSet attributeSet, int i3, int i4) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.m(context, attributeSet, i3, i4);
        return badgeDrawable;
    }

    public static BadgeDrawable e(Context context, SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.o(savedState);
        return badgeDrawable;
    }

    public static int n(Context context, TypedArray typedArray, int i3) {
        return c.a(context, typedArray, i3).getDefaultColor();
    }

    public final void A() {
        this.f1960n = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    @Override // i2.i.b
    public void a() {
        invalidateSelf();
    }

    public final void b(Context context, Rect rect, View view) {
        int i3 = this.f1957k.f1974j;
        if (i3 == 8388691 || i3 == 8388693) {
            this.f1959m = rect.bottom - this.f1957k.f1976l;
        } else {
            this.f1959m = rect.top + this.f1957k.f1976l;
        }
        if (j() <= 9) {
            float f3 = !l() ? this.f1954h : this.f1955i;
            this.f1961o = f3;
            this.f1963q = f3;
            this.f1962p = f3;
        } else {
            float f4 = this.f1955i;
            this.f1961o = f4;
            this.f1963q = f4;
            this.f1962p = (this.f1952f.f(g()) / 2.0f) + this.f1956j;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(l() ? R$dimen.mtrl_badge_text_horizontal_edge_offset : R$dimen.mtrl_badge_horizontal_edge_offset);
        int i4 = this.f1957k.f1974j;
        if (i4 == 8388659 || i4 == 8388691) {
            this.f1958l = t.z(view) == 0 ? (rect.left - this.f1962p) + dimensionPixelSize + this.f1957k.f1975k : ((rect.right + this.f1962p) - dimensionPixelSize) - this.f1957k.f1975k;
        } else {
            this.f1958l = t.z(view) == 0 ? ((rect.right + this.f1962p) - dimensionPixelSize) - this.f1957k.f1975k : (rect.left - this.f1962p) + dimensionPixelSize + this.f1957k.f1975k;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f1951e.draw(canvas);
        if (l()) {
            f(canvas);
        }
    }

    public final void f(Canvas canvas) {
        Rect rect = new Rect();
        String strG = g();
        this.f1952f.e().getTextBounds(strG, 0, strG.length(), rect);
        canvas.drawText(strG, this.f1958l, this.f1959m + (rect.height() / 2), this.f1952f.e());
    }

    public final String g() {
        if (j() <= this.f1960n) {
            return Integer.toString(j());
        }
        Context context = this.f1950d.get();
        return context == null ? "" : context.getString(R$string.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.f1960n), "+");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f1957k.f1968d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f1953g.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f1953g.width();
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
            return this.f1957k.f1971g;
        }
        if (this.f1957k.f1972h <= 0 || (context = this.f1950d.get()) == null) {
            return null;
        }
        return j() <= this.f1960n ? context.getResources().getQuantityString(this.f1957k.f1972h, j(), Integer.valueOf(j())) : context.getString(this.f1957k.f1973i, Integer.valueOf(this.f1960n));
    }

    public int i() {
        return this.f1957k.f1970f;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (l()) {
            return this.f1957k.f1969e;
        }
        return 0;
    }

    public SavedState k() {
        return this.f1957k;
    }

    public boolean l() {
        return this.f1957k.f1969e != -1;
    }

    public final void m(Context context, AttributeSet attributeSet, int i3, int i4) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Badge, i3, i4, new int[0]);
        t(typedArrayH.getInt(R$styleable.Badge_maxCharacterCount, 4));
        int i5 = R$styleable.Badge_number;
        if (typedArrayH.hasValue(i5)) {
            u(typedArrayH.getInt(i5, 0));
        }
        p(n(context, typedArrayH, R$styleable.Badge_backgroundColor));
        int i6 = R$styleable.Badge_badgeTextColor;
        if (typedArrayH.hasValue(i6)) {
            r(n(context, typedArrayH, i6));
        }
        q(typedArrayH.getInt(R$styleable.Badge_badgeGravity, 8388661));
        s(typedArrayH.getDimensionPixelOffset(R$styleable.Badge_horizontalOffset, 0));
        x(typedArrayH.getDimensionPixelOffset(R$styleable.Badge_verticalOffset, 0));
        typedArrayH.recycle();
    }

    public final void o(SavedState savedState) {
        t(savedState.f1970f);
        if (savedState.f1969e != -1) {
            u(savedState.f1969e);
        }
        p(savedState.f1966b);
        r(savedState.f1967c);
        q(savedState.f1974j);
        s(savedState.f1975k);
        x(savedState.f1976l);
    }

    @Override // android.graphics.drawable.Drawable, i2.i.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i3) {
        this.f1957k.f1966b = i3;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i3);
        if (this.f1951e.x() != colorStateListValueOf) {
            this.f1951e.X(colorStateListValueOf);
            invalidateSelf();
        }
    }

    public void q(int i3) {
        if (this.f1957k.f1974j != i3) {
            this.f1957k.f1974j = i3;
            WeakReference<View> weakReference = this.f1964r;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.f1964r.get();
            WeakReference<ViewGroup> weakReference2 = this.f1965s;
            y(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    public void r(int i3) {
        this.f1957k.f1967c = i3;
        if (this.f1952f.e().getColor() != i3) {
            this.f1952f.e().setColor(i3);
            invalidateSelf();
        }
    }

    public void s(int i3) {
        this.f1957k.f1975k = i3;
        z();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        this.f1957k.f1968d = i3;
        this.f1952f.e().setAlpha(i3);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void t(int i3) {
        if (this.f1957k.f1970f != i3) {
            this.f1957k.f1970f = i3;
            A();
            this.f1952f.i(true);
            z();
            invalidateSelf();
        }
    }

    public void u(int i3) {
        int iMax = Math.max(0, i3);
        if (this.f1957k.f1969e != iMax) {
            this.f1957k.f1969e = iMax;
            this.f1952f.i(true);
            z();
            invalidateSelf();
        }
    }

    public final void v(d dVar) {
        Context context;
        if (this.f1952f.d() == dVar || (context = this.f1950d.get()) == null) {
            return;
        }
        this.f1952f.h(dVar, context);
        z();
    }

    public final void w(int i3) {
        Context context = this.f1950d.get();
        if (context == null) {
            return;
        }
        v(new d(context, i3));
    }

    public void x(int i3) {
        this.f1957k.f1976l = i3;
        z();
    }

    public void y(View view, ViewGroup viewGroup) {
        this.f1964r = new WeakReference<>(view);
        this.f1965s = new WeakReference<>(viewGroup);
        z();
        invalidateSelf();
    }

    public final void z() {
        Context context = this.f1950d.get();
        WeakReference<View> weakReference = this.f1964r;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f1953g);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.f1965s;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || a.f5348a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        a.f(this.f1953g, this.f1958l, this.f1959m, this.f1962p, this.f1963q);
        this.f1951e.V(this.f1961o);
        if (rect.equals(this.f1953g)) {
            return;
        }
        this.f1951e.setBounds(this.f1953g);
    }

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1966b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1967c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1968d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1969e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1970f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public CharSequence f1971g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1972h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1973i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1974j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1975k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1976l;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Context context) {
            this.f1968d = 255;
            this.f1969e = -1;
            this.f1967c = new d(context, R$style.TextAppearance_MaterialComponents_Badge).f3588b.getDefaultColor();
            this.f1971g = context.getString(R$string.mtrl_badge_numberless_content_description);
            this.f1972h = R$plurals.mtrl_badge_content_description;
            this.f1973i = R$string.mtrl_exceed_max_badge_number_content_description;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            parcel.writeInt(this.f1966b);
            parcel.writeInt(this.f1967c);
            parcel.writeInt(this.f1968d);
            parcel.writeInt(this.f1969e);
            parcel.writeInt(this.f1970f);
            parcel.writeString(this.f1971g.toString());
            parcel.writeInt(this.f1972h);
            parcel.writeInt(this.f1974j);
            parcel.writeInt(this.f1975k);
            parcel.writeInt(this.f1976l);
        }

        public SavedState(Parcel parcel) {
            this.f1968d = 255;
            this.f1969e = -1;
            this.f1966b = parcel.readInt();
            this.f1967c = parcel.readInt();
            this.f1968d = parcel.readInt();
            this.f1969e = parcel.readInt();
            this.f1970f = parcel.readInt();
            this.f1971g = parcel.readString();
            this.f1972h = parcel.readInt();
            this.f1974j = parcel.readInt();
            this.f1975k = parcel.readInt();
            this.f1976l = parcel.readInt();
        }
    }
}
