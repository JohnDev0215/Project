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
import b6.c;
import b6.d;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$plurals;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import e6.h;
import java.lang.ref.WeakReference;
import l5.a;
import o0.t;
import z5.i;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class BadgeDrawable extends Drawable implements i.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3531d = R$style.Widget_MaterialComponents_Badge;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3532e = R$attr.badgeStyle;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final WeakReference<Context> f3533f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h f3534g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final i f3535h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f3536i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f3537j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f3538k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f3539l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SavedState f3540m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f3541n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f3542o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3543p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f3544q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f3545r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f3546s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public WeakReference<View> f3547t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public WeakReference<ViewGroup> f3548u;

    public BadgeDrawable(Context context) {
        this.f3533f = new WeakReference<>(context);
        k.c(context);
        Resources resources = context.getResources();
        this.f3536i = new Rect();
        this.f3534g = new h();
        this.f3537j = resources.getDimensionPixelSize(R$dimen.mtrl_badge_radius);
        this.f3539l = resources.getDimensionPixelSize(R$dimen.mtrl_badge_long_text_horizontal_padding);
        this.f3538k = resources.getDimensionPixelSize(R$dimen.mtrl_badge_with_text_radius);
        i iVar = new i(this);
        this.f3535h = iVar;
        iVar.e().setTextAlign(Paint.Align.CENTER);
        this.f3540m = new SavedState(context);
        w(R$style.TextAppearance_MaterialComponents_Badge);
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f3532e, f3531d);
    }

    public static BadgeDrawable d(Context context, AttributeSet attributeSet, int i8, int i9) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.m(context, attributeSet, i8, i9);
        return badgeDrawable;
    }

    public static BadgeDrawable e(Context context, SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.o(savedState);
        return badgeDrawable;
    }

    public static int n(Context context, TypedArray typedArray, int i8) {
        return c.a(context, typedArray, i8).getDefaultColor();
    }

    public final void A() {
        this.f3543p = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    @Override // z5.i.b
    public void a() {
        invalidateSelf();
    }

    public final void b(Context context, Rect rect, View view) {
        int i8 = this.f3540m.f3557l;
        if (i8 == 8388691 || i8 == 8388693) {
            this.f3542o = rect.bottom - this.f3540m.f3559n;
        } else {
            this.f3542o = rect.top + this.f3540m.f3559n;
        }
        if (j() <= 9) {
            float f8 = !l() ? this.f3537j : this.f3538k;
            this.f3544q = f8;
            this.f3546s = f8;
            this.f3545r = f8;
        } else {
            float f9 = this.f3538k;
            this.f3544q = f9;
            this.f3546s = f9;
            this.f3545r = (this.f3535h.f(g()) / 2.0f) + this.f3539l;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(l() ? R$dimen.mtrl_badge_text_horizontal_edge_offset : R$dimen.mtrl_badge_horizontal_edge_offset);
        int i9 = this.f3540m.f3557l;
        if (i9 == 8388659 || i9 == 8388691) {
            this.f3541n = t.A(view) == 0 ? (rect.left - this.f3545r) + dimensionPixelSize + this.f3540m.f3558m : ((rect.right + this.f3545r) - dimensionPixelSize) - this.f3540m.f3558m;
        } else {
            this.f3541n = t.A(view) == 0 ? ((rect.right + this.f3545r) - dimensionPixelSize) - this.f3540m.f3558m : (rect.left - this.f3545r) + dimensionPixelSize + this.f3540m.f3558m;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f3534g.draw(canvas);
        if (l()) {
            f(canvas);
        }
    }

    public final void f(Canvas canvas) {
        Rect rect = new Rect();
        String strG = g();
        this.f3535h.e().getTextBounds(strG, 0, strG.length(), rect);
        canvas.drawText(strG, this.f3541n, this.f3542o + (rect.height() / 2), this.f3535h.e());
    }

    public final String g() {
        if (j() <= this.f3543p) {
            return Integer.toString(j());
        }
        Context context = this.f3533f.get();
        return context == null ? "" : context.getString(R$string.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.f3543p), "+");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f3540m.f3551f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f3536i.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f3536i.width();
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
            return this.f3540m.f3554i;
        }
        if (this.f3540m.f3555j <= 0 || (context = this.f3533f.get()) == null) {
            return null;
        }
        return j() <= this.f3543p ? context.getResources().getQuantityString(this.f3540m.f3555j, j(), Integer.valueOf(j())) : context.getString(this.f3540m.f3556k, Integer.valueOf(this.f3543p));
    }

    public int i() {
        return this.f3540m.f3553h;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (l()) {
            return this.f3540m.f3552g;
        }
        return 0;
    }

    public SavedState k() {
        return this.f3540m;
    }

    public boolean l() {
        return this.f3540m.f3552g != -1;
    }

    public final void m(Context context, AttributeSet attributeSet, int i8, int i9) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Badge, i8, i9, new int[0]);
        t(typedArrayH.getInt(R$styleable.Badge_maxCharacterCount, 4));
        int i10 = R$styleable.Badge_number;
        if (typedArrayH.hasValue(i10)) {
            u(typedArrayH.getInt(i10, 0));
        }
        p(n(context, typedArrayH, R$styleable.Badge_backgroundColor));
        int i11 = R$styleable.Badge_badgeTextColor;
        if (typedArrayH.hasValue(i11)) {
            r(n(context, typedArrayH, i11));
        }
        q(typedArrayH.getInt(R$styleable.Badge_badgeGravity, 8388661));
        s(typedArrayH.getDimensionPixelOffset(R$styleable.Badge_horizontalOffset, 0));
        x(typedArrayH.getDimensionPixelOffset(R$styleable.Badge_verticalOffset, 0));
        typedArrayH.recycle();
    }

    public final void o(SavedState savedState) {
        t(savedState.f3553h);
        if (savedState.f3552g != -1) {
            u(savedState.f3552g);
        }
        p(savedState.f3549d);
        r(savedState.f3550e);
        q(savedState.f3557l);
        s(savedState.f3558m);
        x(savedState.f3559n);
    }

    @Override // android.graphics.drawable.Drawable, z5.i.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i8) {
        this.f3540m.f3549d = i8;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i8);
        if (this.f3534g.x() != colorStateListValueOf) {
            this.f3534g.X(colorStateListValueOf);
            invalidateSelf();
        }
    }

    public void q(int i8) {
        if (this.f3540m.f3557l != i8) {
            this.f3540m.f3557l = i8;
            WeakReference<View> weakReference = this.f3547t;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.f3547t.get();
            WeakReference<ViewGroup> weakReference2 = this.f3548u;
            y(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    public void r(int i8) {
        this.f3540m.f3550e = i8;
        if (this.f3535h.e().getColor() != i8) {
            this.f3535h.e().setColor(i8);
            invalidateSelf();
        }
    }

    public void s(int i8) {
        this.f3540m.f3558m = i8;
        z();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f3540m.f3551f = i8;
        this.f3535h.e().setAlpha(i8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void t(int i8) {
        if (this.f3540m.f3553h != i8) {
            this.f3540m.f3553h = i8;
            A();
            this.f3535h.i(true);
            z();
            invalidateSelf();
        }
    }

    public void u(int i8) {
        int iMax = Math.max(0, i8);
        if (this.f3540m.f3552g != iMax) {
            this.f3540m.f3552g = iMax;
            this.f3535h.i(true);
            z();
            invalidateSelf();
        }
    }

    public final void v(d dVar) {
        Context context;
        if (this.f3535h.d() == dVar || (context = this.f3533f.get()) == null) {
            return;
        }
        this.f3535h.h(dVar, context);
        z();
    }

    public final void w(int i8) {
        Context context = this.f3533f.get();
        if (context == null) {
            return;
        }
        v(new d(context, i8));
    }

    public void x(int i8) {
        this.f3540m.f3559n = i8;
        z();
    }

    public void y(View view, ViewGroup viewGroup) {
        this.f3547t = new WeakReference<>(view);
        this.f3548u = new WeakReference<>(viewGroup);
        z();
        invalidateSelf();
    }

    public final void z() {
        Context context = this.f3533f.get();
        WeakReference<View> weakReference = this.f3547t;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f3536i);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.f3548u;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || a.f6289a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        a.f(this.f3536i, this.f3541n, this.f3542o, this.f3545r, this.f3546s);
        this.f3534g.V(this.f3544q);
        if (rect.equals(this.f3536i)) {
            return;
        }
        this.f3534g.setBounds(this.f3536i);
    }

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3549d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3550e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3551f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3552g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f3553h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public CharSequence f3554i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f3555j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f3556k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f3557l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f3558m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f3559n;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Context context) {
            this.f3551f = 255;
            this.f3552g = -1;
            this.f3550e = new d(context, R$style.TextAppearance_MaterialComponents_Badge).f2106b.getDefaultColor();
            this.f3554i = context.getString(R$string.mtrl_badge_numberless_content_description);
            this.f3555j = R$plurals.mtrl_badge_content_description;
            this.f3556k = R$string.mtrl_exceed_max_badge_number_content_description;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeInt(this.f3549d);
            parcel.writeInt(this.f3550e);
            parcel.writeInt(this.f3551f);
            parcel.writeInt(this.f3552g);
            parcel.writeInt(this.f3553h);
            parcel.writeString(this.f3554i.toString());
            parcel.writeInt(this.f3555j);
            parcel.writeInt(this.f3557l);
            parcel.writeInt(this.f3558m);
            parcel.writeInt(this.f3559n);
        }

        public SavedState(Parcel parcel) {
            this.f3551f = 255;
            this.f3552g = -1;
            this.f3549d = parcel.readInt();
            this.f3550e = parcel.readInt();
            this.f3551f = parcel.readInt();
            this.f3552g = parcel.readInt();
            this.f3553h = parcel.readInt();
            this.f3554i = parcel.readString();
            this.f3555j = parcel.readInt();
            this.f3557l = parcel.readInt();
            this.f3558m = parcel.readInt();
            this.f3559n = parcel.readInt();
        }
    }
}
