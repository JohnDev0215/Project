package b.p.a.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends g implements Animatable {
    public ArrayList<?> E3;
    public final Drawable.Callback F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0042b f2133d;
    public Context s;
    public ArgbEvaluator x;
    public Animator.AnimatorListener y;

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public class a implements Drawable.Callback {
        public a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            b.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            b.this.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            b.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: b.p.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class C0042b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f2136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public AnimatorSet f2137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Animator> f2138d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b.d.a<Animator, String> f2139e;

        public C0042b(Context context, C0042b c0042b, Drawable.Callback callback, Resources resources) {
            if (c0042b != null) {
                this.f2135a = c0042b.f2135a;
                h hVar = c0042b.f2136b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f2136b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f2136b = (h) constantState.newDrawable();
                    }
                    h hVar2 = (h) this.f2136b.mutate();
                    this.f2136b = hVar2;
                    hVar2.setCallback(callback);
                    this.f2136b.setBounds(c0042b.f2136b.getBounds());
                    this.f2136b.h(false);
                }
                ArrayList<Animator> arrayList = c0042b.f2138d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f2138d = new ArrayList<>(size);
                    this.f2139e = new b.d.a<>(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        Animator animator = c0042b.f2138d.get(i2);
                        Animator animatorClone = animator.clone();
                        String str = c0042b.f2139e.get(animator);
                        animatorClone.setTarget(this.f2136b.d(str));
                        this.f2138d.add(animatorClone);
                        this.f2139e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f2137c == null) {
                this.f2137c = new AnimatorSet();
            }
            this.f2137c.playTogether(this.f2138d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2135a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    public b() {
        this(null, null, null);
    }

    public static b a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        b bVar = new b(context);
        bVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return bVar;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.a(drawable, theme);
        }
    }

    public final void b(String str, Animator animator) {
        animator.setTarget(this.f2133d.f2136b.d(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0042b c0042b = this.f2133d;
        if (c0042b.f2138d == null) {
            c0042b.f2138d = new ArrayList<>();
            this.f2133d.f2139e = new b.d.a<>();
        }
        this.f2133d.f2138d.add(animator);
        this.f2133d.f2139e.put(animator, str);
    }

    public final void c(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i2 = 0; i2 < childAnimations.size(); i2++) {
                c(childAnimations.get(i2));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.x == null) {
                    this.x = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.x);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            return b.f.g.l.a.b(drawable);
        }
        return false;
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f2133d.f2136b.draw(canvas);
        if (this.f2133d.f2137c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f2145c;
        return drawable != null ? b.f.g.l.a.c(drawable) : this.f2133d.f2136b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f2133d.f2135a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f2145c;
        return drawable != null ? b.f.g.l.a.d(drawable) : this.f2133d.f2136b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f2145c;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(drawable.getConstantState());
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f2133d.f2136b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f2133d.f2136b.getIntrinsicWidth();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.getOpacity() : this.f2133d.f2136b.getOpacity();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.f(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayK = b.f.f.c.g.k(resources, theme, attributeSet, b.p.a.a.a.f2128e);
                    int resourceId = typedArrayK.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.h(false);
                        hVarB.setCallback(this.F3);
                        h hVar = this.f2133d.f2136b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.f2133d.f2136b = hVarB;
                    }
                    typedArrayK.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, b.p.a.a.a.f2129f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.s;
                        if (context == null) {
                            typedArrayObtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        b(string, d.i(context, resourceId2));
                    }
                    typedArrayObtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        this.f2133d.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f2145c;
        return drawable != null ? b.f.g.l.a.g(drawable) : this.f2133d.f2136b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f2145c;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f2133d.f2137c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.isStateful() : this.f2133d.f2136b.isStateful();
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f2133d.f2136b.setBounds(rect);
        }
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.setLevel(i2) : this.f2133d.f2136b.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f2145c;
        return drawable != null ? drawable.setState(iArr) : this.f2133d.f2136b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else {
            this.f2133d.f2136b.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.i(drawable, z);
        } else {
            this.f2133d.f2136b.setAutoMirrored(z);
        }
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // b.p.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTint(int i2) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.m(drawable, i2);
        } else {
            this.f2133d.f2136b.setTint(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.n(drawable, colorStateList);
        } else {
            this.f2133d.f2136b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            b.f.g.l.a.o(drawable, mode);
        } else {
            this.f2133d.f2136b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f2133d.f2136b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f2133d.f2137c.isStarted()) {
                return;
            }
            this.f2133d.f2137c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f2133d.f2137c.end();
        }
    }

    public b(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f2145c;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f2133d.f2136b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f2140a;

        public c(Drawable.ConstantState constantState) {
            this.f2140a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f2140a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2140a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f2140a.newDrawable();
            bVar.f2145c = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.F3);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f2140a.newDrawable(resources);
            bVar.f2145c = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.F3);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f2140a.newDrawable(resources, theme);
            bVar.f2145c = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.F3);
            return bVar;
        }
    }

    public b(Context context, C0042b c0042b, Resources resources) {
        this.x = null;
        this.y = null;
        this.E3 = null;
        a aVar = new a();
        this.F3 = aVar;
        this.s = context;
        if (c0042b != null) {
            this.f2133d = c0042b;
        } else {
            this.f2133d = new C0042b(context, c0042b, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
