package com.airbnb.lottie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2810d = LottieAnimationView.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final h<Throwable> f2811e = new a();
    private final h<com.airbnb.lottie.d> f;
    private final h<Throwable> g;
    private h<Throwable> h;
    private int i;
    private final f j;
    private boolean k;
    private String l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private RenderMode q;
    private Set<j> r;
    private int s;
    private m<com.airbnb.lottie.d> t;
    private com.airbnb.lottie.d u;

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2812b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2813c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f2814d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2815e;
        String f;
        int g;
        int h;

        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        /* synthetic */ SavedState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f2812b);
            parcel.writeFloat(this.f2814d);
            parcel.writeInt(this.f2815e ? 1 : 0);
            parcel.writeString(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f2812b = parcel.readString();
            this.f2814d = parcel.readFloat();
            this.f2815e = parcel.readInt() == 1;
            this.f = parcel.readString();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
        }
    }

    class a implements h<Throwable> {
        a() {
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            if (!com.airbnb.lottie.u.h.k(th)) {
                throw new IllegalStateException("Unable to parse composition", th);
            }
            com.airbnb.lottie.u.d.c("Unable to load composition.", th);
        }
    }

    class b implements h<com.airbnb.lottie.d> {
        b() {
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.airbnb.lottie.d dVar) {
            LottieAnimationView.this.setComposition(dVar);
        }
    }

    class c implements h<Throwable> {
        c() {
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            if (LottieAnimationView.this.i != 0) {
                LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                lottieAnimationView.setImageResource(lottieAnimationView.i);
            }
            (LottieAnimationView.this.h == null ? LottieAnimationView.f2811e : LottieAnimationView.this.h).a(th);
        }
    }

    static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2818a;

        static {
            int[] iArr = new int[RenderMode.values().length];
            f2818a = iArr;
            try {
                iArr[RenderMode.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2818a[RenderMode.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2818a[RenderMode.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        this.f = new b();
        this.g = new c();
        this.i = 0;
        this.j = new f();
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = RenderMode.AUTOMATIC;
        this.r = new HashSet();
        this.s = 0;
        l(null);
    }

    private void h() {
        m<com.airbnb.lottie.d> mVar = this.t;
        if (mVar != null) {
            mVar.k(this.f);
            this.t.j(this.g);
        }
    }

    private void i() {
        this.u = null;
        this.j.f();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void k() {
        /*
            r5 = this;
            int[] r0 = com.airbnb.lottie.LottieAnimationView.d.f2818a
            com.airbnb.lottie.RenderMode r1 = r5.q
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 2
            r2 = 1
            if (r0 == r2) goto L3d
            if (r0 == r1) goto L13
            r3 = 3
            if (r0 == r3) goto L15
        L13:
            r1 = r2
            goto L3d
        L15:
            com.airbnb.lottie.d r0 = r5.u
            r3 = 0
            if (r0 == 0) goto L27
            boolean r0 = r0.p()
            if (r0 == 0) goto L27
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r0 >= r4) goto L27
            goto L3b
        L27:
            com.airbnb.lottie.d r0 = r5.u
            if (r0 == 0) goto L33
            int r0 = r0.l()
            r4 = 4
            if (r0 <= r4) goto L33
            goto L3b
        L33:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 21
            if (r0 >= r4) goto L3a
            goto L3b
        L3a:
            r3 = r2
        L3b:
            if (r3 == 0) goto L13
        L3d:
            int r0 = r5.getLayerType()
            if (r1 == r0) goto L47
            r0 = 0
            r5.setLayerType(r1, r0)
        L47:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieAnimationView.k():void");
    }

    private void l(AttributeSet attributeSet) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, o.C);
        if (!isInEditMode()) {
            int i = o.L;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i);
            int i2 = o.H;
            boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i2);
            int i3 = o.R;
            boolean zHasValue3 = typedArrayObtainStyledAttributes.hasValue(i3);
            if (zHasValue && zHasValue2) {
                throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
            }
            if (zHasValue) {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(i, 0);
                if (resourceId != 0) {
                    setAnimation(resourceId);
                }
            } else if (zHasValue2) {
                String string2 = typedArrayObtainStyledAttributes.getString(i2);
                if (string2 != null) {
                    setAnimation(string2);
                }
            } else if (zHasValue3 && (string = typedArrayObtainStyledAttributes.getString(i3)) != null) {
                setAnimationFromUrl(string);
            }
            setFallbackResource(typedArrayObtainStyledAttributes.getResourceId(o.G, 0));
        }
        if (typedArrayObtainStyledAttributes.getBoolean(o.D, false)) {
            this.o = true;
            this.p = true;
        }
        if (typedArrayObtainStyledAttributes.getBoolean(o.J, false)) {
            this.j.X(-1);
        }
        int i4 = o.O;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            setRepeatMode(typedArrayObtainStyledAttributes.getInt(i4, 1));
        }
        int i5 = o.N;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            setRepeatCount(typedArrayObtainStyledAttributes.getInt(i5, -1));
        }
        int i6 = o.Q;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            setSpeed(typedArrayObtainStyledAttributes.getFloat(i6, 1.0f));
        }
        setImageAssetsFolder(typedArrayObtainStyledAttributes.getString(o.I));
        setProgress(typedArrayObtainStyledAttributes.getFloat(o.K, 0.0f));
        j(typedArrayObtainStyledAttributes.getBoolean(o.F, false));
        int i7 = o.E;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            f(new com.airbnb.lottie.model.d("**"), k.B, new com.airbnb.lottie.v.c(new p(typedArrayObtainStyledAttributes.getColor(i7, 0))));
        }
        int i8 = o.P;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            this.j.Z(typedArrayObtainStyledAttributes.getFloat(i8, 1.0f));
        }
        int i9 = o.M;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            RenderMode renderMode = RenderMode.AUTOMATIC;
            int iOrdinal = typedArrayObtainStyledAttributes.getInt(i9, renderMode.ordinal());
            if (iOrdinal >= RenderMode.values().length) {
                iOrdinal = renderMode.ordinal();
            }
            setRenderMode(RenderMode.values()[iOrdinal]);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.j.b0(Boolean.valueOf(com.airbnb.lottie.u.h.f(getContext()) != 0.0f));
        k();
        this.k = true;
    }

    private void setCompositionTask(m<com.airbnb.lottie.d> mVar) {
        i();
        h();
        this.t = mVar.f(this.f).e(this.g);
    }

    @Override // android.view.View
    public void buildDrawingCache(boolean z) {
        com.airbnb.lottie.c.a("buildDrawingCache");
        this.s++;
        super.buildDrawingCache(z);
        if (this.s == 1 && getWidth() > 0 && getHeight() > 0 && getLayerType() == 1 && getDrawingCache(z) == null) {
            setRenderMode(RenderMode.HARDWARE);
        }
        this.s--;
        com.airbnb.lottie.c.b("buildDrawingCache");
    }

    public <T> void f(com.airbnb.lottie.model.d dVar, T t, com.airbnb.lottie.v.c<T> cVar) {
        this.j.c(dVar, t, cVar);
    }

    public void g() {
        this.n = false;
        this.j.e();
        k();
    }

    public com.airbnb.lottie.d getComposition() {
        return this.u;
    }

    public long getDuration() {
        com.airbnb.lottie.d dVar = this.u;
        if (dVar != null) {
            return (long) dVar.d();
        }
        return 0L;
    }

    public int getFrame() {
        return this.j.m();
    }

    public String getImageAssetsFolder() {
        return this.j.p();
    }

    public float getMaxFrame() {
        return this.j.q();
    }

    public float getMinFrame() {
        return this.j.s();
    }

    public n getPerformanceTracker() {
        return this.j.t();
    }

    public float getProgress() {
        return this.j.u();
    }

    public int getRepeatCount() {
        return this.j.v();
    }

    public int getRepeatMode() {
        return this.j.w();
    }

    public float getScale() {
        return this.j.x();
    }

    public float getSpeed() {
        return this.j.y();
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        f fVar = this.j;
        if (drawable2 == fVar) {
            super.invalidateDrawable(fVar);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void j(boolean z) {
        this.j.g(z);
    }

    public boolean m() {
        return this.j.B();
    }

    public void n() {
        this.p = false;
        this.o = false;
        this.n = false;
        this.j.D();
        k();
    }

    public void o() {
        if (!isShown()) {
            this.n = true;
        } else {
            this.j.E();
            k();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.p || this.o) {
            o();
            this.p = false;
            this.o = false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            onVisibilityChanged(this, getVisibility());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        if (m()) {
            g();
            this.o = true;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        String str = savedState.f2812b;
        this.l = str;
        if (!TextUtils.isEmpty(str)) {
            setAnimation(this.l);
        }
        int i = savedState.f2813c;
        this.m = i;
        if (i != 0) {
            setAnimation(i);
        }
        setProgress(savedState.f2814d);
        if (savedState.f2815e) {
            o();
        }
        this.j.M(savedState.f);
        setRepeatMode(savedState.g);
        setRepeatCount(savedState.h);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2812b = this.l;
        savedState.f2813c = this.m;
        savedState.f2814d = this.j.u();
        savedState.f2815e = this.j.B();
        savedState.f = this.j.p();
        savedState.g = this.j.w();
        savedState.h = this.j.v();
        return savedState;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        if (this.k) {
            if (isShown()) {
                if (this.n) {
                    p();
                    this.n = false;
                    return;
                }
                return;
            }
            if (m()) {
                n();
                this.n = true;
            }
        }
    }

    public void p() {
        if (!isShown()) {
            this.n = true;
        } else {
            this.j.G();
            k();
        }
    }

    public void q(InputStream inputStream, String str) {
        setCompositionTask(e.f(inputStream, str));
    }

    public void r(String str, String str2) {
        q(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setAnimation(int i) {
        this.m = i;
        this.l = null;
        setCompositionTask(e.k(getContext(), i));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        r(str, null);
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(e.m(getContext(), str));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.j.H(z);
    }

    public void setComposition(com.airbnb.lottie.d dVar) {
        if (com.airbnb.lottie.c.f2820a) {
            Log.v(f2810d, "Set Composition \n" + dVar);
        }
        this.j.setCallback(this);
        this.u = dVar;
        boolean zI = this.j.I(dVar);
        k();
        if (getDrawable() != this.j || zI) {
            setImageDrawable(null);
            setImageDrawable(this.j);
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator<j> it = this.r.iterator();
            while (it.hasNext()) {
                it.next().a(dVar);
            }
        }
    }

    public void setFailureListener(h<Throwable> hVar) {
        this.h = hVar;
    }

    public void setFallbackResource(int i) {
        this.i = i;
    }

    public void setFontAssetDelegate(com.airbnb.lottie.a aVar) {
        this.j.J(aVar);
    }

    public void setFrame(int i) {
        this.j.K(i);
    }

    public void setImageAssetDelegate(com.airbnb.lottie.b bVar) {
        this.j.L(bVar);
    }

    public void setImageAssetsFolder(String str) {
        this.j.M(str);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        h();
        super.setImageBitmap(bitmap);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        h();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        h();
        super.setImageResource(i);
    }

    public void setMaxFrame(int i) {
        this.j.N(i);
    }

    public void setMaxProgress(float f) {
        this.j.P(f);
    }

    public void setMinAndMaxFrame(String str) {
        this.j.R(str);
    }

    public void setMinFrame(int i) {
        this.j.S(i);
    }

    public void setMinProgress(float f) {
        this.j.U(f);
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.j.V(z);
    }

    public void setProgress(float f) {
        this.j.W(f);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.q = renderMode;
        k();
    }

    public void setRepeatCount(int i) {
        this.j.X(i);
    }

    public void setRepeatMode(int i) {
        this.j.Y(i);
    }

    public void setScale(float f) {
        this.j.Z(f);
        if (getDrawable() == this.j) {
            setImageDrawable(null);
            setImageDrawable(this.j);
        }
    }

    public void setSpeed(float f) {
        this.j.a0(f);
    }

    public void setTextDelegate(q qVar) {
        this.j.c0(qVar);
    }

    public void setMaxFrame(String str) {
        this.j.O(str);
    }

    public void setMinFrame(String str) {
        this.j.T(str);
    }

    public void setAnimation(String str) {
        this.l = str;
        this.m = 0;
        setCompositionTask(e.d(getContext(), str));
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new b();
        this.g = new c();
        this.i = 0;
        this.j = new f();
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = RenderMode.AUTOMATIC;
        this.r = new HashSet();
        this.s = 0;
        l(attributeSet);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new b();
        this.g = new c();
        this.i = 0;
        this.j = new f();
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = RenderMode.AUTOMATIC;
        this.r = new HashSet();
        this.s = 0;
        l(attributeSet);
    }
}
