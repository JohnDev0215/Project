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
import android.widget.FrameLayout;
import b3.c;
import com.google.android.material.internal.h;
import com.google.android.material.internal.j;
import e3.g;
import i0.u;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import o2.b;
import o2.d;
import o2.f;
import o2.i;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements h.b {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static final int f4087v = k.Widget_MaterialComponents_Badge;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static final int f4088w = b.badgeStyle;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final WeakReference<Context> f4089e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final g f4090g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final h f4091h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Rect f4092i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final float f4093j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final float f4094k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final float f4095l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final SavedState f4096m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f4097n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float f4098o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f4099p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f4100q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f4101r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f4102s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private WeakReference<View> f4103t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private WeakReference<FrameLayout> f4104u;

    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f4119e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ FrameLayout f4120g;

        a(View view, FrameLayout frameLayout) {
            this.f4119e = view;
            this.f4120g = frameLayout;
        }

        @Override // java.lang.Runnable
        public void run() {
            BadgeDrawable.this.y(this.f4119e, this.f4120g);
        }
    }

    private BadgeDrawable(Context context) {
        this.f4089e = new WeakReference<>(context);
        j.c(context);
        Resources resources = context.getResources();
        this.f4092i = new Rect();
        this.f4090g = new g();
        this.f4093j = resources.getDimensionPixelSize(d.mtrl_badge_radius);
        this.f4095l = resources.getDimensionPixelSize(d.mtrl_badge_long_text_horizontal_padding);
        this.f4094k = resources.getDimensionPixelSize(d.mtrl_badge_with_text_radius);
        h hVar = new h(this);
        this.f4091h = hVar;
        hVar.e().setTextAlign(Paint.Align.CENTER);
        this.f4096m = new SavedState(context);
        u(k.TextAppearance_MaterialComponents_Badge);
    }

    private void A() {
        this.f4099p = ((int) Math.pow(10.0d, ((double) i()) - 1.0d)) - 1;
    }

    private void b(Context context, Rect rect, View view) {
        int i7 = this.f4096m.f4116q + this.f4096m.f4118s;
        int i8 = this.f4096m.f4113n;
        if (i8 == 8388691 || i8 == 8388693) {
            this.f4098o = rect.bottom - i7;
        } else {
            this.f4098o = rect.top + i7;
        }
        if (j() <= 9) {
            float f7 = !k() ? this.f4093j : this.f4094k;
            this.f4100q = f7;
            this.f4102s = f7;
            this.f4101r = f7;
        } else {
            float f8 = this.f4094k;
            this.f4100q = f8;
            this.f4102s = f8;
            this.f4101r = (this.f4091h.f(f()) / 2.0f) + this.f4095l;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(k() ? d.mtrl_badge_text_horizontal_edge_offset : d.mtrl_badge_horizontal_edge_offset);
        int i9 = this.f4096m.f4115p + this.f4096m.f4117r;
        int i10 = this.f4096m.f4113n;
        if (i10 == 8388659 || i10 == 8388691) {
            this.f4097n = u.y(view) == 0 ? (rect.left - this.f4101r) + dimensionPixelSize + i9 : ((rect.right + this.f4101r) - dimensionPixelSize) - i9;
        } else {
            this.f4097n = u.y(view) == 0 ? ((rect.right + this.f4101r) - dimensionPixelSize) - i9 : (rect.left - this.f4101r) + dimensionPixelSize + i9;
        }
    }

    public static BadgeDrawable c(Context context) {
        return d(context, null, f4088w, f4087v);
    }

    private static BadgeDrawable d(Context context, AttributeSet attributeSet, int i7, int i8) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.l(context, attributeSet, i7, i8);
        return badgeDrawable;
    }

    private void e(Canvas canvas) {
        Rect rect = new Rect();
        String strF = f();
        this.f4091h.e().getTextBounds(strF, 0, strF.length(), rect);
        canvas.drawText(strF, this.f4097n, this.f4098o + (rect.height() / 2), this.f4091h.e());
    }

    private String f() {
        if (j() <= this.f4099p) {
            return NumberFormat.getInstance().format(j());
        }
        Context context = this.f4089e.get();
        return context == null ? "" : context.getString(o2.j.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.f4099p), "+");
    }

    private void l(Context context, AttributeSet attributeSet, int i7, int i8) {
        TypedArray typedArrayH = j.h(context, attributeSet, l.Badge, i7, i8, new int[0]);
        r(typedArrayH.getInt(l.Badge_maxCharacterCount, 4));
        int i9 = l.Badge_number;
        if (typedArrayH.hasValue(i9)) {
            s(typedArrayH.getInt(i9, 0));
        }
        n(m(context, typedArrayH, l.Badge_backgroundColor));
        int i10 = l.Badge_badgeTextColor;
        if (typedArrayH.hasValue(i10)) {
            p(m(context, typedArrayH, i10));
        }
        o(typedArrayH.getInt(l.Badge_badgeGravity, 8388661));
        q(typedArrayH.getDimensionPixelOffset(l.Badge_horizontalOffset, 0));
        v(typedArrayH.getDimensionPixelOffset(l.Badge_verticalOffset, 0));
        typedArrayH.recycle();
    }

    private static int m(Context context, TypedArray typedArray, int i7) {
        return c.a(context, typedArray, i7).getDefaultColor();
    }

    private void t(b3.d dVar) {
        Context context;
        if (this.f4091h.d() == dVar || (context = this.f4089e.get()) == null) {
            return;
        }
        this.f4091h.h(dVar, context);
        z();
    }

    private void u(int i7) {
        Context context = this.f4089e.get();
        if (context == null) {
            return;
        }
        t(new b3.d(context, i7));
    }

    private void w(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null || viewGroup.getId() != f.mtrl_anchor_parent) {
            WeakReference<FrameLayout> weakReference = this.f4104u;
            if (weakReference == null || weakReference.get() != viewGroup) {
                x(view);
                FrameLayout frameLayout = new FrameLayout(view.getContext());
                frameLayout.setId(f.mtrl_anchor_parent);
                frameLayout.setClipChildren(false);
                frameLayout.setClipToPadding(false);
                frameLayout.setLayoutParams(view.getLayoutParams());
                frameLayout.setMinimumWidth(view.getWidth());
                frameLayout.setMinimumHeight(view.getHeight());
                int iIndexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(iIndexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.addView(view);
                viewGroup.addView(frameLayout, iIndexOfChild);
                this.f4104u = new WeakReference<>(frameLayout);
                frameLayout.post(new a(view, frameLayout));
            }
        }
    }

    private static void x(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    private void z() {
        Context context = this.f4089e.get();
        WeakReference<View> weakReference = this.f4103t;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f4092i);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<FrameLayout> weakReference2 = this.f4104u;
        FrameLayout frameLayout = weakReference2 != null ? weakReference2.get() : null;
        if (frameLayout != null || com.google.android.material.badge.a.f4122a) {
            if (frameLayout == null) {
                frameLayout = (ViewGroup) view.getParent();
            }
            frameLayout.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        com.google.android.material.badge.a.d(this.f4092i, this.f4097n, this.f4098o, this.f4101r, this.f4102s);
        this.f4090g.U(this.f4100q);
        if (rect.equals(this.f4092i)) {
            return;
        }
        this.f4090g.setBounds(this.f4092i);
    }

    @Override // com.google.android.material.internal.h.b
    public void a() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f4090g.draw(canvas);
        if (k()) {
            e(canvas);
        }
    }

    public CharSequence g() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        if (!k()) {
            return this.f4096m.f4110k;
        }
        if (this.f4096m.f4111l <= 0 || (context = this.f4089e.get()) == null) {
            return null;
        }
        return j() <= this.f4099p ? context.getResources().getQuantityString(this.f4096m.f4111l, j(), Integer.valueOf(j())) : context.getString(this.f4096m.f4112m, Integer.valueOf(this.f4099p));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f4096m.f4107h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f4092i.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f4092i.width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public FrameLayout h() {
        WeakReference<FrameLayout> weakReference = this.f4104u;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int i() {
        return this.f4096m.f4109j;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public int j() {
        if (k()) {
            return this.f4096m.f4108i;
        }
        return 0;
    }

    public boolean k() {
        return this.f4096m.f4108i != -1;
    }

    public void n(int i7) {
        this.f4096m.f4105e = i7;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i7);
        if (this.f4090g.x() != colorStateListValueOf) {
            this.f4090g.X(colorStateListValueOf);
            invalidateSelf();
        }
    }

    public void o(int i7) {
        if (this.f4096m.f4113n != i7) {
            this.f4096m.f4113n = i7;
            WeakReference<View> weakReference = this.f4103t;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.f4103t.get();
            WeakReference<FrameLayout> weakReference2 = this.f4104u;
            y(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.h.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i7) {
        this.f4096m.f4106g = i7;
        if (this.f4091h.e().getColor() != i7) {
            this.f4091h.e().setColor(i7);
            invalidateSelf();
        }
    }

    public void q(int i7) {
        this.f4096m.f4115p = i7;
        z();
    }

    public void r(int i7) {
        if (this.f4096m.f4109j != i7) {
            this.f4096m.f4109j = i7;
            A();
            this.f4091h.i(true);
            z();
            invalidateSelf();
        }
    }

    public void s(int i7) {
        int iMax = Math.max(0, i7);
        if (this.f4096m.f4108i != iMax) {
            this.f4096m.f4108i = iMax;
            this.f4091h.i(true);
            z();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        this.f4096m.f4107h = i7;
        this.f4091h.e().setAlpha(i7);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void v(int i7) {
        this.f4096m.f4116q = i7;
        z();
    }

    public void y(View view, FrameLayout frameLayout) {
        this.f4103t = new WeakReference<>(view);
        boolean z6 = com.google.android.material.badge.a.f4122a;
        if (z6 && frameLayout == null) {
            w(view);
        } else {
            this.f4104u = new WeakReference<>(frameLayout);
        }
        if (!z6) {
            x(view);
        }
        z();
        invalidateSelf();
    }

    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f4105e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f4106g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f4107h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f4108i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f4109j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private CharSequence f4110k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private int f4111l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private int f4112m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private int f4113n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private boolean f4114o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private int f4115p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private int f4116q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private int f4117r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private int f4118s;

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Context context) {
            this.f4107h = 255;
            this.f4108i = -1;
            this.f4106g = new b3.d(context, k.TextAppearance_MaterialComponents_Badge).f3530a.getDefaultColor();
            this.f4110k = context.getString(o2.j.mtrl_badge_numberless_content_description);
            this.f4111l = i.mtrl_badge_content_description;
            this.f4112m = o2.j.mtrl_exceed_max_badge_number_content_description;
            this.f4114o = true;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f4105e);
            parcel.writeInt(this.f4106g);
            parcel.writeInt(this.f4107h);
            parcel.writeInt(this.f4108i);
            parcel.writeInt(this.f4109j);
            parcel.writeString(this.f4110k.toString());
            parcel.writeInt(this.f4111l);
            parcel.writeInt(this.f4113n);
            parcel.writeInt(this.f4115p);
            parcel.writeInt(this.f4116q);
            parcel.writeInt(this.f4117r);
            parcel.writeInt(this.f4118s);
            parcel.writeInt(this.f4114o ? 1 : 0);
        }

        protected SavedState(Parcel parcel) {
            this.f4107h = 255;
            this.f4108i = -1;
            this.f4105e = parcel.readInt();
            this.f4106g = parcel.readInt();
            this.f4107h = parcel.readInt();
            this.f4108i = parcel.readInt();
            this.f4109j = parcel.readInt();
            this.f4110k = parcel.readString();
            this.f4111l = parcel.readInt();
            this.f4113n = parcel.readInt();
            this.f4115p = parcel.readInt();
            this.f4116q = parcel.readInt();
            this.f4117r = parcel.readInt();
            this.f4118s = parcel.readInt();
            this.f4114o = parcel.readInt() != 0;
        }
    }
}
