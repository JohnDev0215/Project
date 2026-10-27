package n;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.resources.R$styleable;
import java.io.IOException;
import n.b;
import n.d;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import r.v;
import v.h;

/* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class a extends n.d implements h0.b {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f6448r = a.class.getSimpleName();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public c f6449s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public g f6450t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f6451u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f6452v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f6453w;

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animatable f6454a;

        public b(Animatable animatable) {
            super();
            this.f6454a = animatable;
        }

        @Override // n.a.g
        public void c() {
            this.f6454a.start();
        }

        @Override // n.a.g
        public void d() {
            this.f6454a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class c extends d.a {
        public v.d<Long> K;
        public h<Integer> L;

        public c(c cVar, a aVar, Resources resources) {
            super(cVar, aVar, resources);
            if (cVar != null) {
                this.K = cVar.K;
                this.L = cVar.L;
            } else {
                this.K = new v.d<>();
                this.L = new h<>();
            }
        }

        public static long E(int i8, int i9) {
            return ((long) i9) | (((long) i8) << 32);
        }

        public int C(int[] iArr, Drawable drawable, int i8) {
            int iA = super.A(iArr, drawable);
            this.L.j(iA, Integer.valueOf(i8));
            return iA;
        }

        public int D(int i8, int i9, Drawable drawable, boolean z7) {
            int iA = super.a(drawable);
            long jE = E(i8, i9);
            long j8 = z7 ? 8589934592L : 0L;
            long j9 = iA;
            this.K.a(jE, Long.valueOf(j9 | j8));
            if (z7) {
                this.K.a(E(i9, i8), Long.valueOf(4294967296L | j9 | j8));
            }
            return iA;
        }

        public int F(int i8) {
            if (i8 < 0) {
                return 0;
            }
            return this.L.f(i8, 0).intValue();
        }

        public int G(int[] iArr) {
            int iB = super.B(iArr);
            return iB >= 0 ? iB : super.B(StateSet.WILD_CARD);
        }

        public int H(int i8, int i9) {
            return (int) this.K.g(E(i8, i9), -1L).longValue();
        }

        public boolean I(int i8, int i9) {
            return (this.K.g(E(i8, i9), -1L).longValue() & 4294967296L) != 0;
        }

        public boolean J(int i8, int i9) {
            return (this.K.g(E(i8, i9), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // n.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // n.d.a, n.b.c
        public void s() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }

        @Override // n.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class d extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l1.b f6455a;

        public d(l1.b bVar) {
            super();
            this.f6455a = bVar;
        }

        @Override // n.a.g
        public void c() {
            this.f6455a.start();
        }

        @Override // n.a.g
        public void d() {
            this.f6455a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class e extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ObjectAnimator f6456a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f6457b;

        public e(AnimationDrawable animationDrawable, boolean z7, boolean z8) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i8 = z7 ? numberOfFrames - 1 : 0;
            int i9 = z7 ? 0 : numberOfFrames - 1;
            f fVar = new f(animationDrawable, z7);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i8, i9);
            if (Build.VERSION.SDK_INT >= 18) {
                objectAnimatorOfInt.setAutoCancel(true);
            }
            objectAnimatorOfInt.setDuration(fVar.a());
            objectAnimatorOfInt.setInterpolator(fVar);
            this.f6457b = z8;
            this.f6456a = objectAnimatorOfInt;
        }

        @Override // n.a.g
        public boolean a() {
            return this.f6457b;
        }

        @Override // n.a.g
        public void b() {
            this.f6456a.reverse();
        }

        @Override // n.a.g
        public void c() {
            this.f6456a.start();
        }

        @Override // n.a.g
        public void d() {
            this.f6456a.cancel();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class f implements TimeInterpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f6458a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f6459b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6460c;

        public f(AnimationDrawable animationDrawable, boolean z7) {
            b(animationDrawable, z7);
        }

        public int a() {
            return this.f6460c;
        }

        public int b(AnimationDrawable animationDrawable, boolean z7) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f6459b = numberOfFrames;
            int[] iArr = this.f6458a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f6458a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f6458a;
            int i8 = 0;
            for (int i9 = 0; i9 < numberOfFrames; i9++) {
                int duration = animationDrawable.getDuration(z7 ? (numberOfFrames - i9) - 1 : i9);
                iArr2[i9] = duration;
                i8 += duration;
            }
            this.f6460c = i8;
            return i8;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f8) {
            int i8 = (int) ((f8 * this.f6460c) + 0.5f);
            int i9 = this.f6459b;
            int[] iArr = this.f6458a;
            int i10 = 0;
            while (i10 < i9 && i8 >= iArr[i10]) {
                i8 -= iArr[i10];
                i10++;
            }
            return (i10 / i9) + (i10 < i9 ? i8 / this.f6460c : 0.0f);
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static abstract class g {
        public g() {
        }

        public boolean a() {
            return false;
        }

        public void b() {
        }

        public abstract void c();

        public abstract void d();
    }

    public a() {
        this(null, null);
    }

    public static a l(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            a aVar = new a();
            aVar.m(context, resources, xmlPullParser, attributeSet, theme);
            return aVar;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    @Override // n.d, n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // n.d, n.b
    public void clearMutated() {
        super.clearMutated();
        this.f6453w = false;
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // n.d, n.b
    public void g(b.c cVar) {
        super.g(cVar);
        if (cVar instanceof c) {
            this.f6449s = (c) cVar;
        }
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // n.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // n.d, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        g gVar = this.f6450t;
        if (gVar != null) {
            gVar.d();
            this.f6450t = null;
            f(this.f6451u);
            this.f6451u = -1;
            this.f6452v = -1;
        }
    }

    @Override // n.d
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public c b() {
        return new c(this.f6449s, this, null);
    }

    public void m(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableCompat);
        setVisible(typedArrayK.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        s(typedArrayK);
        h(resources);
        typedArrayK.recycle();
        n(context, resources, xmlPullParser, attributeSet, theme);
        o();
    }

    @Override // n.d, n.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f6453w && super.mutate() == this) {
            this.f6449s.s();
            this.f6453w = true;
        }
        return this;
    }

    public final void n(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals("item")) {
                    p(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals("transition")) {
                    q(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }

    public final void o() {
        onStateChange(getState());
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i8) {
        return super.onLayoutDirectionChanged(i8);
    }

    @Override // n.d, n.b, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int iG = this.f6449s.G(iArr);
        boolean z7 = iG != c() && (r(iG) || f(iG));
        Drawable current = getCurrent();
        return current != null ? z7 | current.setState(iArr) : z7;
    }

    public final int p(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableItem);
        int resourceId = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable drawableJ = resourceId2 > 0 ? v.h().j(context, resourceId2) : null;
        typedArrayK.recycle();
        int[] iArrJ = j(attributeSet);
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("vector") ? l1.h.c(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableJ != null) {
            return this.f6449s.C(iArrJ, drawableJ, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    public final int q(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableTransition);
        int resourceId = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawableJ = resourceId3 > 0 ? v.h().j(context, resourceId3) : null;
        boolean z7 = typedArrayK.getBoolean(R$styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArrayK.recycle();
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("animated-vector") ? l1.b.a(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableJ == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.f6449s.D(resourceId, resourceId2, drawableJ, z7);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    public final boolean r(int i8) {
        int iC;
        int iH;
        g bVar;
        g gVar = this.f6450t;
        if (gVar == null) {
            iC = c();
        } else {
            if (i8 == this.f6451u) {
                return true;
            }
            if (i8 == this.f6452v && gVar.a()) {
                gVar.b();
                this.f6451u = this.f6452v;
                this.f6452v = i8;
                return true;
            }
            iC = this.f6451u;
            gVar.d();
        }
        this.f6450t = null;
        this.f6452v = -1;
        this.f6451u = -1;
        c cVar = this.f6449s;
        int iF = cVar.F(iC);
        int iF2 = cVar.F(i8);
        if (iF2 == 0 || iF == 0 || (iH = cVar.H(iF, iF2)) < 0) {
            return false;
        }
        boolean zJ = cVar.J(iF, iF2);
        f(iH);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            bVar = new e((AnimationDrawable) current, cVar.I(iF, iF2), zJ);
        } else {
            if (!(current instanceof l1.b)) {
                if (current instanceof Animatable) {
                    bVar = new b((Animatable) current);
                }
                return false;
            }
            bVar = new d((l1.b) current);
        }
        bVar.c();
        this.f6450t = bVar;
        this.f6452v = iC;
        this.f6451u = i8;
        return true;
    }

    public final void s(TypedArray typedArray) {
        c cVar = this.f6449s;
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.f6478d |= typedArray.getChangingConfigurations();
        }
        cVar.y(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_variablePadding, cVar.f6483i));
        cVar.u(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_constantSize, cVar.f6486l));
        cVar.v(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, cVar.A));
        cVar.w(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, cVar.B));
        setDither(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_dither, cVar.f6498x));
    }

    @Override // n.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
        super.scheduleDrawable(drawable, runnable, j8);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i8) {
        super.setAlpha(i8);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z7) {
        super.setAutoMirrored(z7);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setDither(boolean z7) {
        super.setDither(z7);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f8, float f9) {
        super.setHotspot(f8, f9);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i8, int i9, int i10, int i11) {
        super.setHotspotBounds(i8, i9, i10, i11);
    }

    @Override // n.b, android.graphics.drawable.Drawable, h0.b
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // n.b, android.graphics.drawable.Drawable, h0.b
    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // n.b, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z7, boolean z8) {
        boolean visible = super.setVisible(z7, z8);
        g gVar = this.f6450t;
        if (gVar != null && (visible || z8)) {
            if (z7) {
                gVar.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // n.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    public a(c cVar, Resources resources) {
        super(null);
        this.f6451u = -1;
        this.f6452v = -1;
        g(new c(cVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }
}
