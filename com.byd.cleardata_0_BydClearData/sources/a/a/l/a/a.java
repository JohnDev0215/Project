package a.a.l.a;

import a.a.l.a.b;
import a.a.l.a.d;
import a.d.h;
import a.o.a.a.i;
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
import androidx.appcompat.widget.l0;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class a extends a.a.l.a.d implements androidx.core.graphics.drawable.b {
    private static final String p = a.class.getSimpleName();
    private c q;
    private g r;
    private int s;
    private int t;
    private boolean u;

    private static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Animatable f42a;

        b(Animatable animatable) {
            super();
            this.f42a = animatable;
        }

        @Override // a.a.l.a.a.g
        public void c() {
            this.f42a.start();
        }

        @Override // a.a.l.a.a.g
        public void d() {
            this.f42a.stop();
        }
    }

    static class c extends d.a {
        a.d.d<Long> K;
        h<Integer> L;

        c(c cVar, a aVar, Resources resources) {
            h<Integer> hVar;
            super(cVar, aVar, resources);
            if (cVar != null) {
                this.K = cVar.K;
                hVar = cVar.L;
            } else {
                this.K = new a.d.d<>();
                hVar = new h<>();
            }
            this.L = hVar;
        }

        private static long E(int i, int i2) {
            return ((long) i2) | (((long) i) << 32);
        }

        int C(int[] iArr, Drawable drawable, int i) {
            int iA = super.A(iArr, drawable);
            this.L.j(iA, Integer.valueOf(i));
            return iA;
        }

        int D(int i, int i2, Drawable drawable, boolean z) {
            int iA = super.a(drawable);
            long jE = E(i, i2);
            long j = z ? 8589934592L : 0L;
            long j2 = iA;
            this.K.a(jE, Long.valueOf(j2 | j));
            if (z) {
                this.K.a(E(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return iA;
        }

        int F(int i) {
            if (i < 0) {
                return 0;
            }
            return this.L.f(i, 0).intValue();
        }

        int G(int[] iArr) {
            int iB = super.B(iArr);
            return iB >= 0 ? iB : super.B(StateSet.WILD_CARD);
        }

        int H(int i, int i2) {
            return (int) this.K.f(E(i, i2), -1L).longValue();
        }

        boolean I(int i, int i2) {
            return (this.K.f(E(i, i2), -1L).longValue() & 4294967296L) != 0;
        }

        boolean J(int i, int i2) {
            return (this.K.f(E(i, i2), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // a.a.l.a.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new a(this, null);
        }

        @Override // a.a.l.a.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }

        @Override // a.a.l.a.d.a, a.a.l.a.b.c
        void s() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }
    }

    private static class d extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a.o.a.a.c f43a;

        d(a.o.a.a.c cVar) {
            super();
            this.f43a = cVar;
        }

        @Override // a.a.l.a.a.g
        public void c() {
            this.f43a.start();
        }

        @Override // a.a.l.a.a.g
        public void d() {
            this.f43a.stop();
        }
    }

    private static class e extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ObjectAnimator f44a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f45b;

        e(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            f fVar = new f(animationDrawable, z);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            if (Build.VERSION.SDK_INT >= 18) {
                objectAnimatorOfInt.setAutoCancel(true);
            }
            objectAnimatorOfInt.setDuration(fVar.a());
            objectAnimatorOfInt.setInterpolator(fVar);
            this.f45b = z2;
            this.f44a = objectAnimatorOfInt;
        }

        @Override // a.a.l.a.a.g
        public boolean a() {
            return this.f45b;
        }

        @Override // a.a.l.a.a.g
        public void b() {
            this.f44a.reverse();
        }

        @Override // a.a.l.a.a.g
        public void c() {
            this.f44a.start();
        }

        @Override // a.a.l.a.a.g
        public void d() {
            this.f44a.cancel();
        }
    }

    private static class f implements TimeInterpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int[] f46a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f47b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f48c;

        f(AnimationDrawable animationDrawable, boolean z) {
            b(animationDrawable, z);
        }

        int a() {
            return this.f48c;
        }

        int b(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f47b = numberOfFrames;
            int[] iArr = this.f46a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f46a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f46a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.f48c = i;
            return i;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int i = (int) ((f * this.f48c) + 0.5f);
            int i2 = this.f47b;
            int[] iArr = this.f46a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.f48c : 0.0f);
        }
    }

    private static abstract class g {
        private g() {
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

    a(c cVar, Resources resources) {
        super(null);
        this.s = -1;
        this.t = -1;
        g(new c(cVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
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

    private void n(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
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

    private void o() {
        onStateChange(getState());
    }

    private int p(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.a.m.b.h);
        int resourceId = typedArrayK.getResourceId(a.a.m.b.i, 0);
        int resourceId2 = typedArrayK.getResourceId(a.a.m.b.j, -1);
        Drawable drawableJ = resourceId2 > 0 ? l0.h().j(context, resourceId2) : null;
        typedArrayK.recycle();
        int[] iArrJ = j(attributeSet);
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("vector") ? i.c(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableJ != null) {
            return this.q.C(iArrJ, drawableJ, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    private int q(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.a.m.b.k);
        int resourceId = typedArrayK.getResourceId(a.a.m.b.n, -1);
        int resourceId2 = typedArrayK.getResourceId(a.a.m.b.m, -1);
        int resourceId3 = typedArrayK.getResourceId(a.a.m.b.l, -1);
        Drawable drawableJ = resourceId3 > 0 ? l0.h().j(context, resourceId3) : null;
        boolean z = typedArrayK.getBoolean(a.a.m.b.o, false);
        typedArrayK.recycle();
        if (drawableJ == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableJ = xmlPullParser.getName().equals("animated-vector") ? a.o.a.a.c.a(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableJ == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.q.D(resourceId, resourceId2, drawableJ, z);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    private boolean r(int i) {
        int iC;
        int iH;
        g bVar;
        g gVar = this.r;
        if (gVar == null) {
            iC = c();
        } else {
            if (i == this.s) {
                return true;
            }
            if (i == this.t && gVar.a()) {
                gVar.b();
                this.s = this.t;
                this.t = i;
                return true;
            }
            iC = this.s;
            gVar.d();
        }
        this.r = null;
        this.t = -1;
        this.s = -1;
        c cVar = this.q;
        int iF = cVar.F(iC);
        int iF2 = cVar.F(i);
        if (iF2 == 0 || iF == 0 || (iH = cVar.H(iF, iF2)) < 0) {
            return false;
        }
        boolean zJ = cVar.J(iF, iF2);
        f(iH);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            bVar = new e((AnimationDrawable) current, cVar.I(iF, iF2), zJ);
        } else {
            if (!(current instanceof a.o.a.a.c)) {
                if (current instanceof Animatable) {
                    bVar = new b((Animatable) current);
                }
                return false;
            }
            bVar = new d((a.o.a.a.c) current);
        }
        bVar.c();
        this.r = bVar;
        this.t = iC;
        this.s = i;
        return true;
    }

    private void s(TypedArray typedArray) {
        c cVar = this.q;
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.f57d |= typedArray.getChangingConfigurations();
        }
        cVar.y(typedArray.getBoolean(a.a.m.b.f63d, cVar.i));
        cVar.u(typedArray.getBoolean(a.a.m.b.e, cVar.l));
        cVar.v(typedArray.getInt(a.a.m.b.f, cVar.A));
        cVar.w(typedArray.getInt(a.a.m.b.g, cVar.B));
        setDither(typedArray.getBoolean(a.a.m.b.f61b, cVar.x));
    }

    @Override // a.a.l.a.d, a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    @Override // a.a.l.a.d, a.a.l.a.b
    void clearMutated() {
        super.clearMutated();
        this.u = false;
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // a.a.l.a.d, a.a.l.a.b
    void g(b.c cVar) {
        super.g(cVar);
        if (cVar instanceof c) {
            this.q = (c) cVar;
        }
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    @Override // a.a.l.a.d, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        g gVar = this.r;
        if (gVar != null) {
            gVar.d();
            this.r = null;
            f(this.s);
            this.s = -1;
            this.t = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a.a.l.a.d
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public c b() {
        return new c(this.q, this, null);
    }

    public void m(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.a.m.b.f60a);
        setVisible(typedArrayK.getBoolean(a.a.m.b.f62c, true), true);
        s(typedArrayK);
        h(resources);
        typedArrayK.recycle();
        n(context, resources, xmlPullParser, attributeSet, theme);
        o();
    }

    @Override // a.a.l.a.d, a.a.l.a.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.u && super.mutate() == this) {
            this.q.s();
            this.u = true;
        }
        return this;
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    @Override // a.a.l.a.d, a.a.l.a.b, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int iG = this.q.G(iArr);
        boolean z = iG != c() && (r(iG) || f(iG));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        g gVar = this.r;
        if (gVar != null && (visible || z2)) {
            if (z) {
                gVar.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    @Override // a.a.l.a.b, android.graphics.drawable.Drawable.Callback
    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }
}
