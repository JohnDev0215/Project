package com.airbnb.lottie;

import a2.h;
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
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import o0.t;
import o1.e;
import o1.f;
import o1.g;
import o1.i;
import o1.k;
import o1.l;
import o1.n;
import o1.p;
import o1.q;
import o1.r;

/* JADX INFO: loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f2416f = LottieAnimationView.class.getSimpleName();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final i<Throwable> f2417g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final i<e> f2418h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i<Throwable> f2419i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public i<Throwable> f2420j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2421k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final g f2422l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2423m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f2424n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2425o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2426p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2427q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2428r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f2429s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f2430t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public RenderMode f2431u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Set<k> f2432v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f2433w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public n<e> f2434x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public e f2435y;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2436d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2437e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f2438f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f2439g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f2440h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f2441i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f2442j;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public /* synthetic */ SavedState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeString(this.f2436d);
            parcel.writeFloat(this.f2438f);
            parcel.writeInt(this.f2439g ? 1 : 0);
            parcel.writeString(this.f2440h);
            parcel.writeInt(this.f2441i);
            parcel.writeInt(this.f2442j);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f2436d = parcel.readString();
            this.f2438f = parcel.readFloat();
            this.f2439g = parcel.readInt() == 1;
            this.f2440h = parcel.readString();
            this.f2441i = parcel.readInt();
            this.f2442j = parcel.readInt();
        }
    }

    public class a implements i<Throwable> {
        @Override // o1.i
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            if (!h.k(th)) {
                throw new IllegalStateException("Unable to parse composition", th);
            }
            a2.d.d("Unable to load composition.", th);
        }
    }

    public class b implements i<e> {
        public b() {
        }

        @Override // o1.i
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(e eVar) {
            LottieAnimationView.this.setComposition(eVar);
        }
    }

    public class c implements i<Throwable> {
        public c() {
        }

        @Override // o1.i
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            if (LottieAnimationView.this.f2421k != 0) {
                LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                lottieAnimationView.setImageResource(lottieAnimationView.f2421k);
            }
            (LottieAnimationView.this.f2420j == null ? LottieAnimationView.f2417g : LottieAnimationView.this.f2420j).a(th);
        }
    }

    public static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2445a;

        static {
            int[] iArr = new int[RenderMode.values().length];
            f2445a = iArr;
            try {
                iArr[RenderMode.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2445a[RenderMode.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2445a[RenderMode.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        this.f2418h = new b();
        this.f2419i = new c();
        this.f2421k = 0;
        this.f2422l = new g();
        this.f2426p = false;
        this.f2427q = false;
        this.f2428r = false;
        this.f2429s = false;
        this.f2430t = true;
        this.f2431u = RenderMode.AUTOMATIC;
        this.f2432v = new HashSet();
        this.f2433w = 0;
        l(null);
    }

    private void setCompositionTask(n<e> nVar) {
        i();
        h();
        this.f2434x = nVar.f(this.f2418h).e(this.f2419i);
    }

    @Override // android.view.View
    public void buildDrawingCache(boolean z7) {
        o1.d.a("buildDrawingCache");
        this.f2433w++;
        super.buildDrawingCache(z7);
        if (this.f2433w == 1 && getWidth() > 0 && getHeight() > 0 && getLayerType() == 1 && getDrawingCache(z7) == null) {
            setRenderMode(RenderMode.HARDWARE);
        }
        this.f2433w--;
        o1.d.b("buildDrawingCache");
    }

    public <T> void f(t1.d dVar, T t7, b2.c<T> cVar) {
        this.f2422l.c(dVar, t7, cVar);
    }

    public void g() {
        this.f2428r = false;
        this.f2427q = false;
        this.f2426p = false;
        this.f2422l.e();
        k();
    }

    public e getComposition() {
        return this.f2435y;
    }

    public long getDuration() {
        e eVar = this.f2435y;
        if (eVar != null) {
            return (long) eVar.d();
        }
        return 0L;
    }

    public int getFrame() {
        return this.f2422l.p();
    }

    public String getImageAssetsFolder() {
        return this.f2422l.s();
    }

    public float getMaxFrame() {
        return this.f2422l.t();
    }

    public float getMinFrame() {
        return this.f2422l.v();
    }

    public p getPerformanceTracker() {
        return this.f2422l.w();
    }

    public float getProgress() {
        return this.f2422l.x();
    }

    public int getRepeatCount() {
        return this.f2422l.y();
    }

    public int getRepeatMode() {
        return this.f2422l.z();
    }

    public float getScale() {
        return this.f2422l.A();
    }

    public float getSpeed() {
        return this.f2422l.B();
    }

    public final void h() {
        n<e> nVar = this.f2434x;
        if (nVar != null) {
            nVar.k(this.f2418h);
            this.f2434x.j(this.f2419i);
        }
    }

    public final void i() {
        this.f2435y = null;
        this.f2422l.f();
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        g gVar = this.f2422l;
        if (drawable2 == gVar) {
            super.invalidateDrawable(gVar);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void j(boolean z7) {
        this.f2422l.j(z7);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k() {
        /*
            r5 = this;
            int[] r0 = com.airbnb.lottie.LottieAnimationView.d.f2445a
            com.airbnb.lottie.RenderMode r1 = r5.f2431u
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
            o1.e r0 = r5.f2435y
            r3 = 0
            if (r0 == 0) goto L27
            boolean r0 = r0.p()
            if (r0 == 0) goto L27
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r0 >= r4) goto L27
            goto L3b
        L27:
            o1.e r0 = r5.f2435y
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

    public final void l(AttributeSet attributeSet) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.LottieAnimationView);
        if (!isInEditMode()) {
            this.f2430t = typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_cacheComposition, true);
            int i8 = R$styleable.LottieAnimationView_lottie_rawRes;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i8);
            int i9 = R$styleable.LottieAnimationView_lottie_fileName;
            boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i9);
            int i10 = R$styleable.LottieAnimationView_lottie_url;
            boolean zHasValue3 = typedArrayObtainStyledAttributes.hasValue(i10);
            if (zHasValue && zHasValue2) {
                throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
            }
            if (zHasValue) {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(i8, 0);
                if (resourceId != 0) {
                    setAnimation(resourceId);
                }
            } else if (zHasValue2) {
                String string2 = typedArrayObtainStyledAttributes.getString(i9);
                if (string2 != null) {
                    setAnimation(string2);
                }
            } else if (zHasValue3 && (string = typedArrayObtainStyledAttributes.getString(i10)) != null) {
                setAnimationFromUrl(string);
            }
            setFallbackResource(typedArrayObtainStyledAttributes.getResourceId(R$styleable.LottieAnimationView_lottie_fallbackRes, 0));
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.f2428r = true;
            this.f2429s = true;
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_loop, false)) {
            this.f2422l.a0(-1);
        }
        int i11 = R$styleable.LottieAnimationView_lottie_repeatMode;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            setRepeatMode(typedArrayObtainStyledAttributes.getInt(i11, 1));
        }
        int i12 = R$styleable.LottieAnimationView_lottie_repeatCount;
        if (typedArrayObtainStyledAttributes.hasValue(i12)) {
            setRepeatCount(typedArrayObtainStyledAttributes.getInt(i12, -1));
        }
        int i13 = R$styleable.LottieAnimationView_lottie_speed;
        if (typedArrayObtainStyledAttributes.hasValue(i13)) {
            setSpeed(typedArrayObtainStyledAttributes.getFloat(i13, 1.0f));
        }
        setImageAssetsFolder(typedArrayObtainStyledAttributes.getString(R$styleable.LottieAnimationView_lottie_imageAssetsFolder));
        setProgress(typedArrayObtainStyledAttributes.getFloat(R$styleable.LottieAnimationView_lottie_progress, 0.0f));
        j(typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        int i14 = R$styleable.LottieAnimationView_lottie_colorFilter;
        if (typedArrayObtainStyledAttributes.hasValue(i14)) {
            f(new t1.d("**"), l.C, new b2.c(new q(typedArrayObtainStyledAttributes.getColor(i14, 0))));
        }
        int i15 = R$styleable.LottieAnimationView_lottie_scale;
        if (typedArrayObtainStyledAttributes.hasValue(i15)) {
            this.f2422l.d0(typedArrayObtainStyledAttributes.getFloat(i15, 1.0f));
        }
        int i16 = R$styleable.LottieAnimationView_lottie_renderMode;
        if (typedArrayObtainStyledAttributes.hasValue(i16)) {
            RenderMode renderMode = RenderMode.AUTOMATIC;
            int iOrdinal = typedArrayObtainStyledAttributes.getInt(i16, renderMode.ordinal());
            if (iOrdinal >= RenderMode.values().length) {
                iOrdinal = renderMode.ordinal();
            }
            setRenderMode(RenderMode.values()[iOrdinal]);
        }
        if (getScaleType() != null) {
            this.f2422l.e0(getScaleType());
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f2422l.g0(Boolean.valueOf(h.f(getContext()) != 0.0f));
        k();
        this.f2423m = true;
    }

    public boolean m() {
        return this.f2422l.E();
    }

    public void n() {
        this.f2429s = false;
        this.f2428r = false;
        this.f2427q = false;
        this.f2426p = false;
        this.f2422l.G();
        k();
    }

    public void o() {
        if (!isShown()) {
            this.f2426p = true;
        } else {
            this.f2422l.H();
            k();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f2429s || this.f2428r) {
            o();
            this.f2429s = false;
            this.f2428r = false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            onVisibilityChanged(this, getVisibility());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        if (m()) {
            g();
            this.f2428r = true;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        String str = savedState.f2436d;
        this.f2424n = str;
        if (!TextUtils.isEmpty(str)) {
            setAnimation(this.f2424n);
        }
        int i8 = savedState.f2437e;
        this.f2425o = i8;
        if (i8 != 0) {
            setAnimation(i8);
        }
        setProgress(savedState.f2438f);
        if (savedState.f2439g) {
            o();
        }
        this.f2422l.P(savedState.f2440h);
        setRepeatMode(savedState.f2441i);
        setRepeatCount(savedState.f2442j);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2436d = this.f2424n;
        savedState.f2437e = this.f2425o;
        savedState.f2438f = this.f2422l.x();
        savedState.f2439g = this.f2422l.E() || (!t.P(this) && this.f2428r);
        savedState.f2440h = this.f2422l.s();
        savedState.f2441i = this.f2422l.z();
        savedState.f2442j = this.f2422l.y();
        return savedState;
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i8) {
        if (this.f2423m) {
            if (!isShown()) {
                if (m()) {
                    n();
                    this.f2427q = true;
                    return;
                }
                return;
            }
            if (this.f2427q) {
                p();
            } else if (this.f2426p) {
                o();
            }
            this.f2427q = false;
            this.f2426p = false;
        }
    }

    public void p() {
        if (isShown()) {
            this.f2422l.J();
            k();
        } else {
            this.f2426p = false;
            this.f2427q = true;
        }
    }

    public void q(InputStream inputStream, String str) {
        setCompositionTask(f.g(inputStream, str));
    }

    public void r(String str, String str2) {
        q(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setAnimation(int i8) {
        this.f2425o = i8;
        this.f2424n = null;
        setCompositionTask(this.f2430t ? f.l(getContext(), i8) : f.m(getContext(), i8, null));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        r(str, null);
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(this.f2430t ? f.p(getContext(), str) : f.q(getContext(), str, null));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z7) {
        this.f2422l.K(z7);
    }

    public void setCacheComposition(boolean z7) {
        this.f2430t = z7;
    }

    public void setComposition(e eVar) {
        if (o1.d.f6733a) {
            Log.v(f2416f, "Set Composition \n" + eVar);
        }
        this.f2422l.setCallback(this);
        this.f2435y = eVar;
        boolean zL = this.f2422l.L(eVar);
        k();
        if (getDrawable() != this.f2422l || zL) {
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator<k> it = this.f2432v.iterator();
            while (it.hasNext()) {
                it.next().onCompositionLoaded(eVar);
            }
        }
    }

    public void setFailureListener(i<Throwable> iVar) {
        this.f2420j = iVar;
    }

    public void setFallbackResource(int i8) {
        this.f2421k = i8;
    }

    public void setFontAssetDelegate(o1.b bVar) {
        this.f2422l.M(bVar);
    }

    public void setFrame(int i8) {
        this.f2422l.N(i8);
    }

    public void setImageAssetDelegate(o1.c cVar) {
        this.f2422l.O(cVar);
    }

    public void setImageAssetsFolder(String str) {
        this.f2422l.P(str);
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
    public void setImageResource(int i8) {
        h();
        super.setImageResource(i8);
    }

    public void setMaxFrame(int i8) {
        this.f2422l.Q(i8);
    }

    public void setMaxProgress(float f8) {
        this.f2422l.S(f8);
    }

    public void setMinAndMaxFrame(String str) {
        this.f2422l.U(str);
    }

    public void setMinFrame(int i8) {
        this.f2422l.V(i8);
    }

    public void setMinProgress(float f8) {
        this.f2422l.X(f8);
    }

    public void setPerformanceTrackingEnabled(boolean z7) {
        this.f2422l.Y(z7);
    }

    public void setProgress(float f8) {
        this.f2422l.Z(f8);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.f2431u = renderMode;
        k();
    }

    public void setRepeatCount(int i8) {
        this.f2422l.a0(i8);
    }

    public void setRepeatMode(int i8) {
        this.f2422l.b0(i8);
    }

    public void setSafeMode(boolean z7) {
        this.f2422l.c0(z7);
    }

    public void setScale(float f8) {
        this.f2422l.d0(f8);
        if (getDrawable() == this.f2422l) {
            setImageDrawable(null);
            setImageDrawable(this.f2422l);
        }
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        super.setScaleType(scaleType);
        g gVar = this.f2422l;
        if (gVar != null) {
            gVar.e0(scaleType);
        }
    }

    public void setSpeed(float f8) {
        this.f2422l.f0(f8);
    }

    public void setTextDelegate(r rVar) {
        this.f2422l.h0(rVar);
    }

    public void setMaxFrame(String str) {
        this.f2422l.R(str);
    }

    public void setMinFrame(String str) {
        this.f2422l.W(str);
    }

    public void setAnimation(String str) {
        this.f2424n = str;
        this.f2425o = 0;
        setCompositionTask(this.f2430t ? f.d(getContext(), str) : f.e(getContext(), str, null));
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2418h = new b();
        this.f2419i = new c();
        this.f2421k = 0;
        this.f2422l = new g();
        this.f2426p = false;
        this.f2427q = false;
        this.f2428r = false;
        this.f2429s = false;
        this.f2430t = true;
        this.f2431u = RenderMode.AUTOMATIC;
        this.f2432v = new HashSet();
        this.f2433w = 0;
        l(attributeSet);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2418h = new b();
        this.f2419i = new c();
        this.f2421k = 0;
        this.f2422l = new g();
        this.f2426p = false;
        this.f2427q = false;
        this.f2428r = false;
        this.f2429s = false;
        this.f2430t = true;
        this.f2431u = RenderMode.AUTOMATIC;
        this.f2432v = new HashSet();
        this.f2433w = 0;
        l(attributeSet);
    }
}
