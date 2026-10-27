package z0;

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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0078b f6023c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f6024d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArgbEvaluator f6025e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Animator.AnimatorListener f6026f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<?> f6027g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Drawable.Callback f6028h;

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public class a implements Drawable.Callback {
        public a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            b.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
            b.this.scheduleSelf(runnable, j3);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            b.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: z0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class C0078b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6030a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f6031b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public AnimatorSet f6032c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Animator> f6033d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public s.a<Animator, String> f6034e;

        public C0078b(Context context, C0078b c0078b, Drawable.Callback callback, Resources resources) {
            if (c0078b != null) {
                this.f6030a = c0078b.f6030a;
                h hVar = c0078b.f6031b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f6031b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f6031b = (h) constantState.newDrawable();
                    }
                    h hVar2 = (h) this.f6031b.mutate();
                    this.f6031b = hVar2;
                    hVar2.setCallback(callback);
                    this.f6031b.setBounds(c0078b.f6031b.getBounds());
                    this.f6031b.h(false);
                }
                ArrayList<Animator> arrayList = c0078b.f6033d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f6033d = new ArrayList<>(size);
                    this.f6034e = new s.a<>(size);
                    for (int i3 = 0; i3 < size; i3++) {
                        Animator animator = c0078b.f6033d.get(i3);
                        Animator animatorClone = animator.clone();
                        String str = c0078b.f6034e.get(animator);
                        animatorClone.setTarget(this.f6031b.d(str));
                        this.f6033d.add(animatorClone);
                        this.f6034e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f6032c == null) {
                this.f6032c = new AnimatorSet();
            }
            this.f6032c.playTogether(this.f6033d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6030a;
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

    @Override // z0.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.a(drawable, theme);
        }
    }

    public final void b(String str, Animator animator) {
        animator.setTarget(this.f6023c.f6031b.d(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0078b c0078b = this.f6023c;
        if (c0078b.f6033d == null) {
            c0078b.f6033d = new ArrayList<>();
            this.f6023c.f6034e = new s.a<>();
        }
        this.f6023c.f6033d.add(animator);
        this.f6023c.f6034e.put(animator, str);
    }

    public final void c(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i3 = 0; i3 < childAnimations.size(); i3++) {
                c(childAnimations.get(i3));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f6025e == null) {
                    this.f6025e = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f6025e);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            return e0.a.b(drawable);
        }
        return false;
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f6023c.f6031b.draw(canvas);
        if (this.f6023c.f6032c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f6040b;
        return drawable != null ? e0.a.d(drawable) : this.f6023c.f6031b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f6023c.f6030a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f6040b;
        return drawable != null ? e0.a.e(drawable) : this.f6023c.f6031b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f6040b;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(drawable.getConstantState());
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f6023c.f6031b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f6023c.f6031b.getIntrinsicWidth();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.getOpacity() : this.f6023c.f6031b.getOpacity();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayK = c0.g.k(resources, theme, attributeSet, z0.a.f6015e);
                    int resourceId = typedArrayK.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.h(false);
                        hVarB.setCallback(this.f6028h);
                        h hVar = this.f6023c.f6031b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.f6023c.f6031b = hVarB;
                    }
                    typedArrayK.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, z0.a.f6016f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f6024d;
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
        this.f6023c.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f6040b;
        return drawable != null ? e0.a.h(drawable) : this.f6023c.f6031b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f6040b;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f6023c.f6032c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.isStateful() : this.f6023c.f6031b.isStateful();
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f6023c.f6031b.setBounds(rect);
        }
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i3) {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.setLevel(i3) : this.f6023c.f6031b.setLevel(i3);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f6040b;
        return drawable != null ? drawable.setState(iArr) : this.f6023c.f6031b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.setAlpha(i3);
        } else {
            this.f6023c.f6031b.setAlpha(i3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.j(drawable, z3);
        } else {
            this.f6023c.f6031b.setAutoMirrored(z3);
        }
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i3) {
        super.setChangingConfigurations(i3);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i3, PorterDuff.Mode mode) {
        super.setColorFilter(i3, mode);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z3) {
        super.setFilterBitmap(z3);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f3, float f4) {
        super.setHotspot(f3, f4);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i3, int i4, int i5, int i6) {
        super.setHotspotBounds(i3, i4, i5, i6);
    }

    @Override // z0.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTint(int i3) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.n(drawable, i3);
        } else {
            this.f6023c.f6031b.setTint(i3);
        }
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.o(drawable, colorStateList);
        } else {
            this.f6023c.f6031b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            e0.a.p(drawable, mode);
        } else {
            this.f6023c.f6031b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z3, boolean z4) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            return drawable.setVisible(z3, z4);
        }
        this.f6023c.f6031b.setVisible(z3, z4);
        return super.setVisible(z3, z4);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f6023c.f6032c.isStarted()) {
                return;
            }
            this.f6023c.f6032c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f6023c.f6032c.end();
        }
    }

    public b(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f6040b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f6023c.f6031b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f6035a;

        public c(Drawable.ConstantState constantState) {
            this.f6035a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f6035a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6035a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f6035a.newDrawable();
            bVar.f6040b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f6028h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f6035a.newDrawable(resources);
            bVar.f6040b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f6028h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f6035a.newDrawable(resources, theme);
            bVar.f6040b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f6028h);
            return bVar;
        }
    }

    public b(Context context, C0078b c0078b, Resources resources) {
        this.f6025e = null;
        this.f6026f = null;
        this.f6027g = null;
        a aVar = new a();
        this.f6028h = aVar;
        this.f6024d = context;
        if (c0078b != null) {
            this.f6023c = c0078b;
        } else {
            this.f6023c = new C0078b(context, c0078b, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
