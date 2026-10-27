package b.a.a.a.n;

import a.g.k.t;
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
import b.a.a.a.b0.g;
import b.a.a.a.i;
import b.a.a.a.j;
import b.a.a.a.k;
import b.a.a.a.l;
import b.a.a.a.y.c;
import b.a.a.a.y.d;
import com.google.android.material.internal.h;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class a extends Drawable implements h.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f1753b = k.k;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f1754c = b.a.a.a.b.f1643b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakReference<Context> f1755d;
    private final g e;
    private final h f;
    private final Rect g;
    private final float h;
    private final float i;
    private final float j;
    private final C0049a k;
    private float l;
    private float m;
    private int n;
    private float o;
    private float p;
    private float q;
    private WeakReference<View> r;
    private WeakReference<ViewGroup> s;

    /* JADX INFO: renamed from: b.a.a.a.n.a$a, reason: collision with other inner class name */
    public static final class C0049a implements Parcelable {
        public static final Parcelable.Creator<C0049a> CREATOR = new C0050a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1756b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1757c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1758d;
        private int e;
        private int f;
        private CharSequence g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int l;

        /* JADX INFO: renamed from: b.a.a.a.n.a$a$a, reason: collision with other inner class name */
        static class C0050a implements Parcelable.Creator<C0049a> {
            C0050a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public C0049a createFromParcel(Parcel parcel) {
                return new C0049a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public C0049a[] newArray(int i) {
                return new C0049a[i];
            }
        }

        public C0049a(Context context) {
            this.f1758d = 255;
            this.e = -1;
            this.f1757c = new d(context, k.f1726c).f1778b.getDefaultColor();
            this.g = context.getString(j.g);
            this.h = i.f1719a;
            this.i = j.i;
        }

        protected C0049a(Parcel parcel) {
            this.f1758d = 255;
            this.e = -1;
            this.f1756b = parcel.readInt();
            this.f1757c = parcel.readInt();
            this.f1758d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readString();
            this.h = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1756b);
            parcel.writeInt(this.f1757c);
            parcel.writeInt(this.f1758d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeString(this.g.toString());
            parcel.writeInt(this.h);
            parcel.writeInt(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }
    }

    private a(Context context) {
        this.f1755d = new WeakReference<>(context);
        com.google.android.material.internal.i.c(context);
        Resources resources = context.getResources();
        this.g = new Rect();
        this.e = new g();
        this.h = resources.getDimensionPixelSize(b.a.a.a.d.m);
        this.j = resources.getDimensionPixelSize(b.a.a.a.d.l);
        this.i = resources.getDimensionPixelSize(b.a.a.a.d.o);
        h hVar = new h(this);
        this.f = hVar;
        hVar.e().setTextAlign(Paint.Align.CENTER);
        this.k = new C0049a(context);
        t(k.f1726c);
    }

    private void b(Context context, Rect rect, View view) {
        float f;
        int i = this.k.j;
        this.m = (i == 8388691 || i == 8388693) ? rect.bottom - this.k.l : rect.top + this.k.l;
        if (i() <= 9) {
            f = !j() ? this.h : this.i;
            this.o = f;
            this.q = f;
        } else {
            float f2 = this.i;
            this.o = f2;
            this.q = f2;
            f = (this.f.f(f()) / 2.0f) + this.j;
        }
        this.p = f;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(j() ? b.a.a.a.d.n : b.a.a.a.d.k);
        int i2 = this.k.j;
        this.l = (i2 == 8388659 || i2 == 8388691 ? t.y(view) != 0 : t.y(view) == 0) ? ((rect.right + this.p) - dimensionPixelSize) - this.k.k : (rect.left - this.p) + dimensionPixelSize + this.k.k;
    }

    public static a c(Context context) {
        return d(context, null, f1754c, f1753b);
    }

    private static a d(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context);
        aVar.k(context, attributeSet, i, i2);
        return aVar;
    }

    private void e(Canvas canvas) {
        Rect rect = new Rect();
        String strF = f();
        this.f.e().getTextBounds(strF, 0, strF.length(), rect);
        canvas.drawText(strF, this.l, this.m + (rect.height() / 2), this.f.e());
    }

    private String f() {
        if (i() <= this.n) {
            return Integer.toString(i());
        }
        Context context = this.f1755d.get();
        return context == null ? "" : context.getString(j.j, Integer.valueOf(this.n), "+");
    }

    private void k(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = com.google.android.material.internal.i.h(context, attributeSet, l.u, i, i2, new int[0]);
        q(typedArrayH.getInt(l.z, 4));
        int i3 = l.A;
        if (typedArrayH.hasValue(i3)) {
            r(typedArrayH.getInt(i3, 0));
        }
        m(l(context, typedArrayH, l.v));
        int i4 = l.x;
        if (typedArrayH.hasValue(i4)) {
            o(l(context, typedArrayH, i4));
        }
        n(typedArrayH.getInt(l.w, 8388661));
        p(typedArrayH.getDimensionPixelOffset(l.y, 0));
        u(typedArrayH.getDimensionPixelOffset(l.B, 0));
        typedArrayH.recycle();
    }

    private static int l(Context context, TypedArray typedArray, int i) {
        return c.a(context, typedArray, i).getDefaultColor();
    }

    private void s(d dVar) {
        Context context;
        if (this.f.d() == dVar || (context = this.f1755d.get()) == null) {
            return;
        }
        this.f.h(dVar, context);
        w();
    }

    private void t(int i) {
        Context context = this.f1755d.get();
        if (context == null) {
            return;
        }
        s(new d(context, i));
    }

    private void w() {
        Context context = this.f1755d.get();
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
        if (viewGroup != null || b.f1759a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        b(context, rect2, view);
        b.d(this.g, this.l, this.m, this.p, this.q);
        this.e.U(this.o);
        if (rect.equals(this.g)) {
            return;
        }
        this.e.setBounds(this.g);
    }

    private void x() {
        this.n = ((int) Math.pow(10.0d, ((double) h()) - 1.0d)) - 1;
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
        this.e.draw(canvas);
        if (j()) {
            e(canvas);
        }
    }

    public CharSequence g() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        if (!j()) {
            return this.k.g;
        }
        if (this.k.h <= 0 || (context = this.f1755d.get()) == null) {
            return null;
        }
        return i() <= this.n ? context.getResources().getQuantityString(this.k.h, i(), Integer.valueOf(i())) : context.getString(this.k.i, Integer.valueOf(this.n));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.k.f1758d;
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

    public int h() {
        return this.k.f;
    }

    public int i() {
        if (j()) {
            return this.k.e;
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public boolean j() {
        return this.k.e != -1;
    }

    public void m(int i) {
        this.k.f1756b = i;
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        if (this.e.x() != colorStateListValueOf) {
            this.e.W(colorStateListValueOf);
            invalidateSelf();
        }
    }

    public void n(int i) {
        if (this.k.j != i) {
            this.k.j = i;
            WeakReference<View> weakReference = this.r;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.r.get();
            WeakReference<ViewGroup> weakReference2 = this.s;
            v(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    public void o(int i) {
        this.k.f1757c = i;
        if (this.f.e().getColor() != i) {
            this.f.e().setColor(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.h.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void p(int i) {
        this.k.k = i;
        w();
    }

    public void q(int i) {
        if (this.k.f != i) {
            this.k.f = i;
            x();
            this.f.i(true);
            w();
            invalidateSelf();
        }
    }

    public void r(int i) {
        int iMax = Math.max(0, i);
        if (this.k.e != iMax) {
            this.k.e = iMax;
            this.f.i(true);
            w();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.k.f1758d = i;
        this.f.e().setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void u(int i) {
        this.k.l = i;
        w();
    }

    public void v(View view, ViewGroup viewGroup) {
        this.r = new WeakReference<>(view);
        this.s = new WeakReference<>(viewGroup);
        w();
        invalidateSelf();
    }
}
