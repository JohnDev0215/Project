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
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.appcompat.widget.AppCompatImageView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f648a = LottieAnimationView.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final h<Throwable> f649b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final h<com.airbnb.lottie.d> f650c;
    private final h<Throwable> d;

    @Nullable
    private h<Throwable> e;

    @DrawableRes
    private int f;
    private final f g;
    private boolean h;
    private String i;

    @RawRes
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private o n;
    private Set<j> o;
    private int p;

    @Nullable
    private m<com.airbnb.lottie.d> q;

    @Nullable
    private com.airbnb.lottie.d r;

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f651a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f652b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f653c;
        boolean d;
        String e;
        int f;
        int g;

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

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f651a = parcel.readString();
            this.f653c = parcel.readFloat();
            this.d = parcel.readInt() == 1;
            this.e = parcel.readString();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
        }

        /* synthetic */ SavedState(Parcel parcel, a aVar) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f651a);
            parcel.writeFloat(this.f653c);
            parcel.writeInt(this.d ? 1 : 0);
            parcel.writeString(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
        }
    }

    class a implements h<Throwable> {
        a() {
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
            if (!com.airbnb.lottie.w.h.k(th)) {
                throw new IllegalStateException("Unable to parse composition", th);
            }
            com.airbnb.lottie.w.d.c("Unable to load composition.", th);
        }
    }

    class b implements h<com.airbnb.lottie.d> {
        b() {
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(com.airbnb.lottie.d dVar) {
            LottieAnimationView.this.setComposition(dVar);
        }
    }

    class c implements h<Throwable> {
        c() {
        }

        @Override // com.airbnb.lottie.h
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
            if (LottieAnimationView.this.f != 0) {
                LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                lottieAnimationView.setImageResource(lottieAnimationView.f);
            }
            (LottieAnimationView.this.e == null ? LottieAnimationView.f649b : LottieAnimationView.this.e).onResult(th);
        }
    }

    static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f656a;

        static {
            int[] iArr = new int[o.values().length];
            f656a = iArr;
            try {
                iArr[o.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f656a[o.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f656a[o.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f650c = new b();
        this.d = new c();
        this.f = 0;
        this.g = new f();
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = o.AUTOMATIC;
        this.o = new HashSet();
        this.p = 0;
        j(attributeSet);
    }

    private void f() {
        m<com.airbnb.lottie.d> mVar = this.q;
        if (mVar != null) {
            mVar.k(this.f650c);
            this.q.j(this.d);
        }
    }

    private void g() {
        this.r = null;
        this.g.f();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void i() {
        /*
            r5 = this;
            int[] r0 = com.airbnb.lottie.LottieAnimationView.d.f656a
            com.airbnb.lottie.o r1 = r5.n
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
            com.airbnb.lottie.d r0 = r5.r
            r3 = 0
            if (r0 == 0) goto L27
            boolean r0 = r0.p()
            if (r0 == 0) goto L27
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r0 >= r4) goto L27
            goto L3b
        L27:
            com.airbnb.lottie.d r0 = r5.r
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
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieAnimationView.i():void");
    }

    private void j(@Nullable AttributeSet attributeSet) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.LottieAnimationView);
        if (!isInEditMode()) {
            int i = R$styleable.LottieAnimationView_lottie_rawRes;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i);
            int i2 = R$styleable.LottieAnimationView_lottie_fileName;
            boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i2);
            int i3 = R$styleable.LottieAnimationView_lottie_url;
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
            setFallbackResource(typedArrayObtainStyledAttributes.getResourceId(R$styleable.LottieAnimationView_lottie_fallbackRes, 0));
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.l = true;
            this.m = true;
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_loop, false)) {
            this.g.X(-1);
        }
        int i4 = R$styleable.LottieAnimationView_lottie_repeatMode;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            setRepeatMode(typedArrayObtainStyledAttributes.getInt(i4, 1));
        }
        int i5 = R$styleable.LottieAnimationView_lottie_repeatCount;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            setRepeatCount(typedArrayObtainStyledAttributes.getInt(i5, -1));
        }
        int i6 = R$styleable.LottieAnimationView_lottie_speed;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            setSpeed(typedArrayObtainStyledAttributes.getFloat(i6, 1.0f));
        }
        setImageAssetsFolder(typedArrayObtainStyledAttributes.getString(R$styleable.LottieAnimationView_lottie_imageAssetsFolder));
        setProgress(typedArrayObtainStyledAttributes.getFloat(R$styleable.LottieAnimationView_lottie_progress, 0.0f));
        h(typedArrayObtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        int i7 = R$styleable.LottieAnimationView_lottie_colorFilter;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            d(new com.airbnb.lottie.t.e("**"), k.B, new com.airbnb.lottie.x.c(new p(typedArrayObtainStyledAttributes.getColor(i7, 0))));
        }
        int i8 = R$styleable.LottieAnimationView_lottie_scale;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            this.g.Z(typedArrayObtainStyledAttributes.getFloat(i8, 1.0f));
        }
        int i9 = R$styleable.LottieAnimationView_lottie_renderMode;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            o oVar = o.AUTOMATIC;
            int iOrdinal = typedArrayObtainStyledAttributes.getInt(i9, oVar.ordinal());
            if (iOrdinal >= o.values().length) {
                iOrdinal = oVar.ordinal();
            }
            setRenderMode(o.values()[iOrdinal]);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.g.b0(Boolean.valueOf(com.airbnb.lottie.w.h.f(getContext()) != 0.0f));
        i();
        this.h = true;
    }

    private void setCompositionTask(m<com.airbnb.lottie.d> mVar) {
        g();
        f();
        this.q = mVar.f(this.f650c).e(this.d);
    }

    @Override // android.view.View
    public void buildDrawingCache(boolean z) {
        com.airbnb.lottie.c.a("buildDrawingCache");
        this.p++;
        super.buildDrawingCache(z);
        if (this.p == 1 && getWidth() > 0 && getHeight() > 0 && getLayerType() == 1 && getDrawingCache(z) == null) {
            setRenderMode(o.HARDWARE);
        }
        this.p--;
        com.airbnb.lottie.c.b("buildDrawingCache");
    }

    public <T> void d(com.airbnb.lottie.t.e eVar, T t, com.airbnb.lottie.x.c<T> cVar) {
        this.g.c(eVar, t, cVar);
    }

    @MainThread
    public void e() {
        this.k = false;
        this.g.e();
        i();
    }

    @Nullable
    public com.airbnb.lottie.d getComposition() {
        return this.r;
    }

    public long getDuration() {
        com.airbnb.lottie.d dVar = this.r;
        if (dVar != null) {
            return (long) dVar.d();
        }
        return 0L;
    }

    public int getFrame() {
        return this.g.m();
    }

    @Nullable
    public String getImageAssetsFolder() {
        return this.g.p();
    }

    public float getMaxFrame() {
        return this.g.q();
    }

    public float getMinFrame() {
        return this.g.s();
    }

    @Nullable
    public n getPerformanceTracker() {
        return this.g.t();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.g.u();
    }

    public int getRepeatCount() {
        return this.g.v();
    }

    public int getRepeatMode() {
        return this.g.w();
    }

    public float getScale() {
        return this.g.x();
    }

    public float getSpeed() {
        return this.g.y();
    }

    public void h(boolean z) {
        this.g.g(z);
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable drawable2 = getDrawable();
        f fVar = this.g;
        if (drawable2 == fVar) {
            super.invalidateDrawable(fVar);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public boolean k() {
        return this.g.B();
    }

    @MainThread
    public void l() {
        this.m = false;
        this.l = false;
        this.k = false;
        this.g.D();
        i();
    }

    @MainThread
    public void m() {
        if (!isShown()) {
            this.k = true;
        } else {
            this.g.E();
            i();
        }
    }

    @MainThread
    public void n() {
        if (!isShown()) {
            this.k = true;
        } else {
            this.g.G();
            i();
        }
    }

    public void o(InputStream inputStream, @Nullable String str) {
        setCompositionTask(e.f(inputStream, str));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.m || this.l) {
            m();
            this.m = false;
            this.l = false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            onVisibilityChanged(this, getVisibility());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        if (k()) {
            e();
            this.l = true;
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
        String str = savedState.f651a;
        this.i = str;
        if (!TextUtils.isEmpty(str)) {
            setAnimation(this.i);
        }
        int i = savedState.f652b;
        this.j = i;
        if (i != 0) {
            setAnimation(i);
        }
        setProgress(savedState.f653c);
        if (savedState.d) {
            m();
        }
        this.g.M(savedState.e);
        setRepeatMode(savedState.f);
        setRepeatCount(savedState.g);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f651a = this.i;
        savedState.f652b = this.j;
        savedState.f653c = this.g.u();
        savedState.d = this.g.B();
        savedState.e = this.g.p();
        savedState.f = this.g.w();
        savedState.g = this.g.v();
        return savedState;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i) {
        boolean z;
        if (this.h) {
            if (isShown()) {
                if (!this.k) {
                    return;
                }
                n();
                z = false;
            } else {
                if (!k()) {
                    return;
                }
                l();
                z = true;
            }
            this.k = z;
        }
    }

    public void p(String str, @Nullable String str2) {
        o(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setAnimation(@RawRes int i) {
        this.j = i;
        this.i = null;
        setCompositionTask(e.k(getContext(), i));
    }

    public void setAnimation(String str) {
        this.i = str;
        this.j = 0;
        setCompositionTask(e.d(getContext(), str));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        p(str, null);
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(e.m(getContext(), str));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.g.H(z);
    }

    public void setComposition(@NonNull com.airbnb.lottie.d dVar) {
        if (com.airbnb.lottie.c.f657a) {
            Log.v(f648a, "Set Composition \n" + dVar);
        }
        this.g.setCallback(this);
        this.r = dVar;
        boolean zI = this.g.I(dVar);
        i();
        if (getDrawable() != this.g || zI) {
            setImageDrawable(null);
            setImageDrawable(this.g);
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator<j> it = this.o.iterator();
            while (it.hasNext()) {
                it.next().a(dVar);
            }
        }
    }

    public void setFailureListener(@Nullable h<Throwable> hVar) {
        this.e = hVar;
    }

    public void setFallbackResource(@DrawableRes int i) {
        this.f = i;
    }

    public void setFontAssetDelegate(com.airbnb.lottie.a aVar) {
        this.g.J(aVar);
    }

    public void setFrame(int i) {
        this.g.K(i);
    }

    public void setImageAssetDelegate(com.airbnb.lottie.b bVar) {
        this.g.L(bVar);
    }

    public void setImageAssetsFolder(String str) {
        this.g.M(str);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        f();
        super.setImageBitmap(bitmap);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        f();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        f();
        super.setImageResource(i);
    }

    public void setMaxFrame(int i) {
        this.g.N(i);
    }

    public void setMaxFrame(String str) {
        this.g.O(str);
    }

    public void setMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.g.P(f);
    }

    public void setMinAndMaxFrame(String str) {
        this.g.R(str);
    }

    public void setMinFrame(int i) {
        this.g.S(i);
    }

    public void setMinFrame(String str) {
        this.g.T(str);
    }

    public void setMinProgress(float f) {
        this.g.U(f);
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.g.V(z);
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.g.W(f);
    }

    public void setRenderMode(o oVar) {
        this.n = oVar;
        i();
    }

    public void setRepeatCount(int i) {
        this.g.X(i);
    }

    public void setRepeatMode(int i) {
        this.g.Y(i);
    }

    public void setScale(float f) {
        this.g.Z(f);
        if (getDrawable() == this.g) {
            setImageDrawable(null);
            setImageDrawable(this.g);
        }
    }

    public void setSpeed(float f) {
        this.g.a0(f);
    }

    public void setTextDelegate(q qVar) {
        this.g.c0(qVar);
    }
}
