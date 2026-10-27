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
import com.google.android.material.internal.i;
import d.b.a.a.b;
import d.b.a.a.d;
import d.b.a.a.j;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.t.c;
import d.b.a.a.w.h;
import java.lang.ref.WeakReference;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements i.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3317b = k.q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3318c = b.f4689c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakReference<Context> f3319d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h f3320e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final i f3321f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Rect f3322g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float f3323h;
    private final float i;
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
        this.f3319d = new WeakReference<>(context);
        com.google.android.material.internal.k.c(context);
        Resources resources = context.getResources();
        this.f3322g = new Rect();
        this.f3320e = new h();
        this.f3323h = resources.getDimensionPixelSize(d.v);
        this.j = resources.getDimensionPixelSize(d.u);
        this.i = resources.getDimensionPixelSize(d.x);
        i iVar = new i(this);
        this.f3321f = iVar;
        iVar.e().setTextAlign(Paint.Align.CENTER);
        this.k = new SavedState(context);
        w(k.f4748e);
    }

    private void A() {
        this.n = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    private void b(Context context, Rect rect, View view) {
        int i = this.k.j;
        if (i == 8388691 || i == 8388693) {
            this.m = rect.bottom - this.k.l;
        } else {
            this.m = rect.top + this.k.l;
        }
        if (j() <= 9) {
            float f2 = !l() ? this.f3323h : this.i;
            this.o = f2;
            this.q = f2;
            this.p = f2;
        } else {
            float f3 = this.i;
            this.o = f3;
            this.q = f3;
            this.p = (this.f3321f.f(g()) / 2.0f) + this.j;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(l() ? d.w : d.t);
        int i2 = this.k.j;
        if (i2 == 8388659 || i2 == 8388691) {
            this.l = t.z(view) == 0 ? (rect.left - this.p) + dimensionPixelSize + this.k.k : ((rect.right + this.p) - dimensionPixelSize) - this.k.k;
        } else {
            this.l = t.z(view) == 0 ? ((rect.right + this.p) - dimensionPixelSize) - this.k.k : (rect.left - this.p) + dimensionPixelSize + this.k.k;
        }
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f3318c, f3317b);
    }

    private static BadgeDrawable d(Context context, AttributeSet attributeSet, int i, int i2) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.m(context, attributeSet, i, i2);
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
        this.f3321f.e().getTextBounds(strG, 0, strG.length(), rect);
        canvas.drawText(strG, this.l, this.m + (rect.height() / 2), this.f3321f.e());
    }

    private String g() {
        if (j() <= this.n) {
            return Integer.toString(j());
        }
        Context context = this.f3319d.get();
        return context == null ? HttpUrl.FRAGMENT_ENCODE_SET : context.getString(j.m, Integer.valueOf(this.n), "+");
    }

    private void m(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context, attributeSet, l.C, i, i2, new int[0]);
        t(typedArrayH.getInt(l.H, 4));
        int i3 = l.I;
        if (typedArrayH.hasValue(i3)) {
            u(typedArrayH.getInt(i3, 0));
        }
        p(n(context, typedArrayH, l.D));
        int i4 = l.F;
        if (typedArrayH.hasValue(i4)) {
            r(n(context, typedArrayH, i4));
        }
        q(typedArrayH.getInt(l.E, 8388661));
        s(typedArrayH.getDimensionPixelOffset(l.G, 0));
        x(typedArrayH.getDimensionPixelOffset(l.J, 0));
        typedArrayH.recycle();
    }

    private static int n(Context context, TypedArray typedArray, int i) {
        return c.a(context, typedArray, i).getDefaultColor();
    }

    private void o(SavedState savedState) {
        t(savedState.f3328f);
        if (savedState.f3327e != -1) {
            u(savedState.f3327e);
        }
        p(savedState.f3324b);
        r(savedState.f3325c);
        q(savedState.j);
        s(savedState.k);
        x(savedState.l);
    }

    private void v(d.b.a.a.t.d dVar) {
        Context context;
        if (this.f3321f.d() == dVar || (context = this.f3319d.get()) == null) {
            return;
        }
        this.f3321f.h(dVar, context);
        z();
    }

    private void w(int i) {
        Context context = this.f3319d.get();
        if (context == null) {
            return;
        }
        v(new d.b.a.a.t.d(context, i));
    }

    private void z() {
        Context context = this.f3319d.get();
        WeakReference<View> weakReference = this.r;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f3322g);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<ViewGroup> weakReference2 = this.s;
        ViewGroup viewGroup = weakReference2 != null ? weakReference2.get() : null;
        if (viewGroup != null || a.f3331a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        a.f(this.f3322g, this.l, this.m, this.p, this.q);
        this.f3320e.V(this.o);
        if (rect.equals(this.f3322g)) {
            return;
        }
        this.f3320e.setBounds(this.f3322g);
    }

    @Override // com.google.android.material.internal.i.b
    public void a() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f3320e.draw(canvas);
        if (l()) {
            f(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.k.f3326d;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f3322g.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f3322g.width();
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
            return this.k.f3329g;
        }
        if (this.k.f3330h <= 0 || (context = this.f3319d.get()) == null) {
            return null;
        }
        return j() <= this.n ? context.getResources().getQuantityString(this.k.f3330h, j(), Integer.valueOf(j())) : context.getString(this.k.i, Integer.valueOf(this.n));
    }

    public int i() {
        return this.k.f3328f;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (l()) {
            return this.k.f3327e;
        }
        return 0;
    }

    public SavedState k() {
        return this.k;
    }

    public boolean l() {
        return this.k.f3327e != -1;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.i.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i) {
        this.k.f3324b = i;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        if (this.f3320e.x() != colorStateListValueOf) {
            this.f3320e.X(colorStateListValueOf);
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
        this.k.f3325c = i;
        if (this.f3321f.e().getColor() != i) {
            this.f3321f.e().setColor(i);
            invalidateSelf();
        }
    }

    public void s(int i) {
        this.k.k = i;
        z();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.k.f3326d = i;
        this.f3321f.e().setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void t(int i) {
        if (this.k.f3328f != i) {
            this.k.f3328f = i;
            A();
            this.f3321f.i(true);
            z();
            invalidateSelf();
        }
    }

    public void u(int i) {
        int iMax = Math.max(0, i);
        if (this.k.f3327e != iMax) {
            this.k.f3327e = iMax;
            this.f3321f.i(true);
            z();
            invalidateSelf();
        }
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

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3324b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3325c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3326d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3327e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f3328f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private CharSequence f3329g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f3330h;
        private int i;
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Context context) {
            this.f3326d = 255;
            this.f3327e = -1;
            this.f3325c = new d.b.a.a.t.d(context, k.f4748e).f4794b.getDefaultColor();
            this.f3329g = context.getString(j.j);
            this.f3330h = d.b.a.a.i.f4735a;
            this.i = j.l;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f3324b);
            parcel.writeInt(this.f3325c);
            parcel.writeInt(this.f3326d);
            parcel.writeInt(this.f3327e);
            parcel.writeInt(this.f3328f);
            parcel.writeString(this.f3329g.toString());
            parcel.writeInt(this.f3330h);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }

        protected SavedState(Parcel parcel) {
            this.f3326d = 255;
            this.f3327e = -1;
            this.f3324b = parcel.readInt();
            this.f3325c = parcel.readInt();
            this.f3326d = parcel.readInt();
            this.f3327e = parcel.readInt();
            this.f3328f = parcel.readInt();
            this.f3329g = parcel.readString();
            this.f3330h = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }
    }
}
