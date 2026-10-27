package b.y.a.a;

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
    public C0067b f3345c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3346d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArgbEvaluator f3347e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Animator.AnimatorListener f3348f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<?> f3349g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Drawable.Callback f3350h;

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

    /* JADX INFO: renamed from: b.y.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class C0067b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f3353b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public AnimatorSet f3354c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Animator> f3355d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b.e.a<Animator, String> f3356e;

        public C0067b(Context context, C0067b c0067b, Drawable.Callback callback, Resources resources) {
            if (c0067b != null) {
                this.f3352a = c0067b.f3352a;
                h hVar = c0067b.f3353b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f3353b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f3353b = (h) constantState.newDrawable();
                    }
                    h hVar2 = (h) this.f3353b.mutate();
                    this.f3353b = hVar2;
                    hVar2.setCallback(callback);
                    this.f3353b.setBounds(c0067b.f3353b.getBounds());
                    this.f3353b.h(false);
                }
                ArrayList<Animator> arrayList = c0067b.f3355d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f3355d = new ArrayList<>(size);
                    this.f3356e = new b.e.a<>(size);
                    for (int i2 = 0; i2 < size; i2++) {
                        Animator animator = c0067b.f3355d.get(i2);
                        Animator animatorClone = animator.clone();
                        String str = c0067b.f3356e.get(animator);
                        animatorClone.setTarget(this.f3353b.d(str));
                        this.f3355d.add(animatorClone);
                        this.f3356e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f3354c == null) {
                this.f3354c = new AnimatorSet();
            }
            this.f3354c.playTogether(this.f3355d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3352a;
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

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.a(drawable, theme);
        }
    }

    public final void b(String str, Animator animator) {
        animator.setTarget(this.f3345c.f3353b.d(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0067b c0067b = this.f3345c;
        if (c0067b.f3355d == null) {
            c0067b.f3355d = new ArrayList<>();
            this.f3345c.f3356e = new b.e.a<>();
        }
        this.f3345c.f3355d.add(animator);
        this.f3345c.f3356e.put(animator, str);
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
                if (this.f3347e == null) {
                    this.f3347e = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f3347e);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            return b.h.f.l.a.b(drawable);
        }
        return false;
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f3345c.f3353b.draw(canvas);
        if (this.f3345c.f3354c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f3362b;
        return drawable != null ? b.h.f.l.a.d(drawable) : this.f3345c.f3353b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f3345c.f3352a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f3362b;
        return drawable != null ? b.h.f.l.a.e(drawable) : this.f3345c.f3353b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f3362b;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(drawable.getConstantState());
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f3345c.f3353b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f3345c.f3353b.getIntrinsicWidth();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.getOpacity() : this.f3345c.f3353b.getOpacity();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayK = b.h.e.c.g.k(resources, theme, attributeSet, b.y.a.a.a.f3340e);
                    int resourceId = typedArrayK.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.h(false);
                        hVarB.setCallback(this.f3350h);
                        h hVar = this.f3345c.f3353b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.f3345c.f3353b = hVarB;
                    }
                    typedArrayK.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, b.y.a.a.a.f3341f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f3346d;
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
        this.f3345c.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f3362b;
        return drawable != null ? b.h.f.l.a.h(drawable) : this.f3345c.f3353b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f3362b;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f3345c.f3354c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.isStateful() : this.f3345c.f3353b.isStateful();
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f3345c.f3353b.setBounds(rect);
        }
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.setLevel(i2) : this.f3345c.f3353b.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f3362b;
        return drawable != null ? drawable.setState(iArr) : this.f3345c.f3353b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else {
            this.f3345c.f3353b.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.j(drawable, z);
        } else {
            this.f3345c.f3353b.setAutoMirrored(z);
        }
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // b.y.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTint(int i2) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.n(drawable, i2);
        } else {
            this.f3345c.f3353b.setTint(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.o(drawable, colorStateList);
        } else {
            this.f3345c.f3353b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            b.h.f.l.a.p(drawable, mode);
        } else {
            this.f3345c.f3353b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f3345c.f3353b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f3345c.f3354c.isStarted()) {
                return;
            }
            this.f3345c.f3354c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f3345c.f3354c.end();
        }
    }

    public b(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f3362b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f3345c.f3353b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f3357a;

        public c(Drawable.ConstantState constantState) {
            this.f3357a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f3357a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3357a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f3357a.newDrawable();
            bVar.f3362b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f3350h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f3357a.newDrawable(resources);
            bVar.f3362b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f3350h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f3357a.newDrawable(resources, theme);
            bVar.f3362b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f3350h);
            return bVar;
        }
    }

    public b(Context context, C0067b c0067b, Resources resources) {
        this.f3347e = null;
        this.f3348f = null;
        this.f3349g = null;
        a aVar = new a();
        this.f3350h = aVar;
        this.f3346d = context;
        if (c0067b != null) {
            this.f3345c = c0067b;
        } else {
            this.f3345c = new C0067b(context, c0067b, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
