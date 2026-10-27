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
import b.g.k.t;
import com.google.android.material.internal.j;
import com.google.android.material.internal.l;
import d.b.a.a.b;
import d.b.a.a.d;
import d.b.a.a.i;
import d.b.a.a.k;
import d.b.a.a.u.c;
import d.b.a.a.x.h;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements j.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3698b = k.Widget_MaterialComponents_Badge;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3699c = b.badgeStyle;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakReference<Context> f3700d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h f3701e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final j f3702f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Rect f3703g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float f3704h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final float f3705i;
    private final float j;
    private final SavedState k;
    private float l;
    private float m;
    private int n;
    private float o;
    private float p;
    private float q;
    private WeakReference<View> r;
    private WeakReference<ViewGroup> s;

    private BadgeDrawable(Context context) {
        this.f3700d = new WeakReference<>(context);
        l.c(context);
        Resources resources = context.getResources();
        this.f3703g = new Rect();
        this.f3701e = new h();
        this.f3704h = resources.getDimensionPixelSize(d.mtrl_badge_radius);
        this.j = resources.getDimensionPixelSize(d.mtrl_badge_long_text_horizontal_padding);
        this.f3705i = resources.getDimensionPixelSize(d.mtrl_badge_with_text_radius);
        j jVar = new j(this);
        this.f3702f = jVar;
        jVar.e().setTextAlign(Paint.Align.CENTER);
        this.k = new SavedState(context);
        w(k.TextAppearance_MaterialComponents_Badge);
    }

    private void A() {
        this.n = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    private void b(Context context, Rect rect, View view) {
        int i2 = this.k.j;
        if (i2 == 8388691 || i2 == 8388693) {
            this.m = rect.bottom - this.k.l;
        } else {
            this.m = rect.top + this.k.l;
        }
        if (j() <= 9) {
            float f2 = !l() ? this.f3704h : this.f3705i;
            this.o = f2;
            this.q = f2;
            this.p = f2;
        } else {
            float f3 = this.f3705i;
            this.o = f3;
            this.q = f3;
            this.p = (this.f3702f.f(g()) / 2.0f) + this.j;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(l() ? d.mtrl_badge_text_horizontal_edge_offset : d.mtrl_badge_horizontal_edge_offset);
        int i3 = this.k.j;
        if (i3 == 8388659 || i3 == 8388691) {
            this.l = t.z(view) == 0 ? (rect.left - this.p) + dimensionPixelSize + this.k.k : ((rect.right + this.p) - dimensionPixelSize) - this.k.k;
        } else {
            this.l = t.z(view) == 0 ? ((rect.right + this.p) - dimensionPixelSize) - this.k.k : (rect.left - this.p) + dimensionPixelSize + this.k.k;
        }
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f3699c, f3698b);
    }

    private static BadgeDrawable d(Context context, AttributeSet attributeSet, int i2, int i3) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.m(context, attributeSet, i2, i3);
        return badgeDrawable;
    }

    static BadgeDrawable e(Context context, SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.o(savedState);
        return badgeDrawable;
    }

    private void f(Canvas canvas) {
        Rect rect = new Rect();
        String strG = g();
        this.f3702f.e().getTextBounds(strG, 0, strG.length(), rect);
        canvas.drawText(strG, this.l, this.m + (rect.height() / 2), this.f3702f.e());
    }

    private String g() {
        if (j() <= this.n) {
            return Integer.toString(j());
        }
        Context context = this.f3700d.get();
        return context == null ? "" : context.getString(d.b.a.a.j.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.n), "+");
    }

    private void m(Context context, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayH = l.h(context, attributeSet, d.b.a.a.l.Badge, i2, i3, new int[0]);
        t(typedArrayH.getInt(d.b.a.a.l.Badge_maxCharacterCount, 4));
        int i4 = d.b.a.a.l.Badge_number;
        if (typedArrayH.hasValue(i4)) {
            u(typedArrayH.getInt(i4, 0));
        }
        p(n(context, typedArrayH, d.b.a.a.l.Badge_backgroundColor));
        int i5 = d.b.a.a.l.Badge_badgeTextColor;
        if (typedArrayH.hasValue(i5)) {
            r(n(context, typedArrayH, i5));
        }
        q(typedArrayH.getInt(d.b.a.a.l.Badge_badgeGravity, 8388661));
        s(typedArrayH.getDimensionPixelOffset(d.b.a.a.l.Badge_horizontalOffset, 0));
        x(typedArrayH.getDimensionPixelOffset(d.b.a.a.l.Badge_verticalOffset, 0));
        typedArrayH.recycle();
    }

    private static int n(Context context, TypedArray typedArray, int i2) {
        return c.a(context, typedArray, i2).getDefaultColor();
    }

    private void o(SavedState savedState) {
        t(savedState.f3710f);
        if (savedState.f3709e != -1) {
            u(savedState.f3709e);
        }
        p(savedState.f3706b);
        r(savedState.f3707c);
        q(savedState.j);
        s(savedState.k);
        x(savedState.l);
    }

    private void v(d.b.a.a.u.d dVar) {
        Context context;
        if (this.f3702f.d() == dVar || (context = this.f3700d.get()) == null) {
            return;
        }
        this.f3702f.h(dVar, context);
        z();
    }

    private void w(int i2) {
        Context context = this.f3700d.get();
        if (context == null) {
            return;
        }
        v(new d.b.a.a.u.d(context, i2));
    }

    private void z() {
        Context context = this.f3700d.get();
        WeakReference<View> weakReference = this.r;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f3703g);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.s;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || a.f3714a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        a.f(this.f3703g, this.l, this.m, this.p, this.q);
        this.f3701e.V(this.o);
        if (rect.equals(this.f3703g)) {
            return;
        }
        this.f3701e.setBounds(this.f3703g);
    }

    @Override // com.google.android.material.internal.j.b
    public void a() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f3701e.draw(canvas);
        if (l()) {
            f(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.k.f3708d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f3703g.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f3703g.width();
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
            return this.k.f3711g;
        }
        if (this.k.f3712h <= 0 || (context = this.f3700d.get()) == null) {
            return null;
        }
        return j() <= this.n ? context.getResources().getQuantityString(this.k.f3712h, j(), Integer.valueOf(j())) : context.getString(this.k.f3713i, Integer.valueOf(this.n));
    }

    public int i() {
        return this.k.f3710f;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (l()) {
            return this.k.f3709e;
        }
        return 0;
    }

    public SavedState k() {
        return this.k;
    }

    public boolean l() {
        return this.k.f3709e != -1;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.j.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i2) {
        this.k.f3706b = i2;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i2);
        if (this.f3701e.x() != colorStateListValueOf) {
            this.f3701e.X(colorStateListValueOf);
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
        this.k.f3707c = i2;
        if (this.f3702f.e().getColor() != i2) {
            this.f3702f.e().setColor(i2);
            invalidateSelf();
        }
    }

    public void s(int i2) {
        this.k.k = i2;
        z();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.k.f3708d = i2;
        this.f3702f.e().setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void t(int i2) {
        if (this.k.f3710f != i2) {
            this.k.f3710f = i2;
            A();
            this.f3702f.i(true);
            z();
            invalidateSelf();
        }
    }

    public void u(int i2) {
        int iMax = Math.max(0, i2);
        if (this.k.f3709e != iMax) {
            this.k.f3709e = iMax;
            this.f3702f.i(true);
            z();
            invalidateSelf();
        }
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

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3706b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3707c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3708d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3709e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f3710f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private CharSequence f3711g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f3712h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f3713i;
        private int j;
        private int k;
        private int l;

        static class a implements Parcelable.Creator<SavedState> {
            a() {
            }

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
            this.f3708d = 255;
            this.f3709e = -1;
            this.f3707c = new d.b.a.a.u.d(context, k.TextAppearance_MaterialComponents_Badge).f4712b.getDefaultColor();
            this.f3711g = context.getString(d.b.a.a.j.mtrl_badge_numberless_content_description);
            this.f3712h = i.mtrl_badge_content_description;
            this.f3713i = d.b.a.a.j.mtrl_exceed_max_badge_number_content_description;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f3706b);
            parcel.writeInt(this.f3707c);
            parcel.writeInt(this.f3708d);
            parcel.writeInt(this.f3709e);
            parcel.writeInt(this.f3710f);
            parcel.writeString(this.f3711g.toString());
            parcel.writeInt(this.f3712h);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }

        protected SavedState(Parcel parcel) {
            this.f3708d = 255;
            this.f3709e = -1;
            this.f3706b = parcel.readInt();
            this.f3707c = parcel.readInt();
            this.f3708d = parcel.readInt();
            this.f3709e = parcel.readInt();
            this.f3710f = parcel.readInt();
            this.f3711g = parcel.readString();
            this.f3712h = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }
    }
}
