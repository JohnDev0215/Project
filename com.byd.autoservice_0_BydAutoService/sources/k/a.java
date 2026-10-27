package k;

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
import k.b;
import k.d;
import o.v;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import s.h;

/* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class a extends k.d implements e0.b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f3521p = a.class.getSimpleName();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f3522q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public g f3523r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3524s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3525t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f3526u;

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animatable f3527a;

        public b(Animatable animatable) {
            super();
            this.f3527a = animatable;
        }

        @Override // k.a.g
        public void c() {
            this.f3527a.start();
        }

        @Override // k.a.g
        public void d() {
            this.f3527a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class c extends d.a {
        public s.d<Long> K;
        public h<Integer> L;

        public c(c cVar, a aVar, Resources resources) {
            super(cVar, aVar, resources);
            if (cVar != null) {
                this.K = cVar.K;
                this.L = cVar.L;
            } else {
                this.K = new s.d<>();
                this.L = new h<>();
            }
        }

        public static long E(int i3, int i4) {
            return ((long) i4) | (((long) i3) << 32);
        }

        public int C(int[] iArr, Drawable drawable, int i3) {
            int iA = super.A(iArr, drawable);
            this.L.j(iA, Integer.valueOf(i3));
            return iA;
        }

        public int D(int i3, int i4, Drawable drawable, boolean z3) {
            int iA = super.a(drawable);
            long jE = E(i3, i4);
            long j3 = z3 ? 8589934592L : 0L;
            long j4 = iA;
            this.K.a(jE, Long.valueOf(j4 | j3));
            if (z3) {
                this.K.a(E(i4, i3), Long.valueOf(4294967296L | j4 | j3));
            }
            return iA;
        }

        public int F(int i3) {
            if (i3 < 0) {
                return 0;
            }
            return this.L.f(i3, 0).intValue();
        }

        public int G(int[] iArr) {
            int iB = super.B(iArr);
            return iB >= 0 ? iB : super.B(StateSet.WILD_CARD);
        }

        public int H(int i3, int i4) {
            return (int) this.K.f(E(i3, i4), -1L).longValue();
        }

        public boolean I(int i3, int i4) {
            return (this.K.f(E(i3, i4), -1L).longValue() & 4294967296L) != 0;
        }

        public boolean J(int i3, int i4) {
            return (this.K.f(E(i3, i4), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // k.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // k.d.a, k.b.c
        public void s() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }

        @Override // k.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class d extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final z0.b f3528a;

        public d(z0.b bVar) {
            super();
            this.f3528a = bVar;
        }

        @Override // k.a.g
        public void c() {
            this.f3528a.start();
        }

        @Override // k.a.g
        public void d() {
            this.f3528a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class e extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ObjectAnimator f3529a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f3530b;

        public e(AnimationDrawable animationDrawable, boolean z3, boolean z4) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i3 = z3 ? numberOfFrames - 1 : 0;
            int i4 = z3 ? 0 : numberOfFrames - 1;
            f fVar = new f(animationDrawable, z3);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i3, i4);
            if (Build.VERSION.SDK_INT >= 18) {
                objectAnimatorOfInt.setAutoCancel(true);
            }
            objectAnimatorOfInt.setDuration(fVar.a());
            objectAnimatorOfInt.setInterpolator(fVar);
            this.f3530b = z4;
            this.f3529a = objectAnimatorOfInt;
        }

        @Override // k.a.g
        public boolean a() {
            return this.f3530b;
        }

        @Override // k.a.g
        public void b() {
            this.f3529a.reverse();
        }

        @Override // k.a.g
        public void c() {
            this.f3529a.start();
        }

        @Override // k.a.g
        public void d() {
            this.f3529a.cancel();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class f implements TimeInterpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f3531a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3532b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3533c;

        public f(AnimationDrawable animationDrawable, boolean z3) {
            b(animationDrawable, z3);
        }

        public int a() {
            return this.f3533c;
        }

        public int b(AnimationDrawable animationDrawable, boolean z3) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f3532b = numberOfFrames;
            int[] iArr = this.f3531a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f3531a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f3531a;
            int i3 = 0;
            for (int i4 = 0; i4 < numberOfFrames; i4++) {
                int duration = animationDrawable.getDuration(z3 ? (numberOfFrames - i4) - 1 : i4);
                iArr2[i4] = duration;
                i3 += duration;
            }
            this.f3533c = i3;
            return i3;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f3) {
            int i3 = (int) ((f3 * this.f3533c) + 0.5f);
            int i4 = this.f3532b;
            int[] iArr = this.f3531a;
            int i5 = 0;
            while (i5 < i4 && i3 >= iArr[i5]) {
                i3 -= iArr[i5];
                i5++;
            }
            return (i5 / i4) + (i5 < i4 ? i3 / this.f3533c : 0.0f);
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

    @Override // k.d, k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // k.d, k.b
    public void clearMutated() {
        super.clearMutated();
        this.f3526u = false;
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // k.d, k.b
    public void g(b.c cVar) {
        super.g(cVar);
        if (cVar instanceof c) {
            this.f3522q = (c) cVar;
        }
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // k.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // k.d, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        g gVar = this.f3523r;
        if (gVar != null) {
            gVar.d();
            this.f3523r = null;
            f(this.f3524s);
            this.f3524s = -1;
            this.f3525t = -1;
        }
    }

    @Override // k.d
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public c b() {
        return new c(this.f3522q, this, null);
    }

    public void m(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableCompat);
        setVisible(typedArrayK.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        s(typedArrayK);
        h(resources);
        typedArrayK.recycle();
        n(context, resources, xmlPullParser, attributeSet, theme);
        o();
    }

    @Override // k.d, k.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f3526u && super.mutate() == this) {
            this.f3522q.s();
            this.f3526u = true;
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

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i3) {
        return super.onLayoutDirectionChanged(i3);
    }

    @Override // k.d, k.b, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int iG = this.f3522q.G(iArr);
        boolean z3 = iG != c() && (r(iG) || f(iG));
        Drawable current = getCurrent();
        return current != null ? z3 | current.setState(iArr) : z3;
    }

    public final int p(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableItem);
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
            drawableJ = xmlPullParser.getName().equals("vector") ? z0.h.c(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableJ != null) {
            return this.f3522q.C(iArrJ, drawableJ, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    public final int q(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableTransition);
        int resourceId = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = typedArrayK.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawableJ = resourceId3 > 0 ? v.h().j(context, resourceId3) : null;
        boolean z3 = typedArrayK.getBoolean(R$styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArrayK.recycle();
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("animated-vector") ? z0.b.a(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableJ == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.f3522q.D(resourceId, resourceId2, drawableJ, z3);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    public final boolean r(int i3) {
        int iC;
        int iH;
        g bVar;
        g gVar = this.f3523r;
        if (gVar == null) {
            iC = c();
        } else {
            if (i3 == this.f3524s) {
                return true;
            }
            if (i3 == this.f3525t && gVar.a()) {
                gVar.b();
                this.f3524s = this.f3525t;
                this.f3525t = i3;
                return true;
            }
            iC = this.f3524s;
            gVar.d();
        }
        this.f3523r = null;
        this.f3525t = -1;
        this.f3524s = -1;
        c cVar = this.f3522q;
        int iF = cVar.F(iC);
        int iF2 = cVar.F(i3);
        if (iF2 == 0 || iF == 0 || (iH = cVar.H(iF, iF2)) < 0) {
            return false;
        }
        boolean zJ = cVar.J(iF, iF2);
        f(iH);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            bVar = new e((AnimationDrawable) current, cVar.I(iF, iF2), zJ);
        } else {
            if (!(current instanceof z0.b)) {
                if (current instanceof Animatable) {
                    bVar = new b((Animatable) current);
                }
                return false;
            }
            bVar = new d((z0.b) current);
        }
        bVar.c();
        this.f3523r = bVar;
        this.f3525t = iC;
        this.f3524s = i3;
        return true;
    }

    public final void s(TypedArray typedArray) {
        c cVar = this.f3522q;
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.f3551d |= typedArray.getChangingConfigurations();
        }
        cVar.y(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_variablePadding, cVar.f3556i));
        cVar.u(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_constantSize, cVar.f3559l));
        cVar.v(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, cVar.A));
        cVar.w(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, cVar.B));
        setDither(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_dither, cVar.f3571x));
    }

    @Override // k.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        super.scheduleDrawable(drawable, runnable, j3);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i3) {
        super.setAlpha(i3);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z3) {
        super.setAutoMirrored(z3);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setDither(boolean z3) {
        super.setDither(z3);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f3, float f4) {
        super.setHotspot(f3, f4);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i3, int i4, int i5, int i6) {
        super.setHotspotBounds(i3, i4, i5, i6);
    }

    @Override // k.b, android.graphics.drawable.Drawable, e0.b
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // k.b, android.graphics.drawable.Drawable, e0.b
    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // k.b, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z3, boolean z4) {
        boolean visible = super.setVisible(z3, z4);
        g gVar = this.f3523r;
        if (gVar != null && (visible || z4)) {
            if (z3) {
                gVar.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // k.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    public a(c cVar, Resources resources) {
        super(null);
        this.f3524s = -1;
        this.f3525t = -1;
        g(new c(cVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }
}
