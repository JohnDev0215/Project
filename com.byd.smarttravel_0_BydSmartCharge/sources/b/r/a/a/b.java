package b.r.a.a;

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
    public C0041b f1534c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f1535d;
    public ArgbEvaluator e;
    public Animator.AnimatorListener f;
    public ArrayList<?> g;
    public final Drawable.Callback h;

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

    /* JADX INFO: renamed from: b.r.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class C0041b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1537a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f1538b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public AnimatorSet f1539c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Animator> f1540d;
        public b.e.a<Animator, String> e;

        public C0041b(Context context, C0041b c0041b, Drawable.Callback callback, Resources resources) {
            if (c0041b != null) {
                this.f1537a = c0041b.f1537a;
                h hVar = c0041b.f1538b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f1538b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f1538b = (h) constantState.newDrawable();
                    }
                    h hVar2 = (h) this.f1538b.mutate();
                    this.f1538b = hVar2;
                    hVar2.setCallback(callback);
                    this.f1538b.setBounds(c0041b.f1538b.getBounds());
                    this.f1538b.h(false);
                }
                ArrayList<Animator> arrayList = c0041b.f1540d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f1540d = new ArrayList<>(size);
                    this.e = new b.e.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = c0041b.f1540d.get(i);
                        Animator animatorClone = animator.clone();
                        String str = c0041b.e.get(animator);
                        animatorClone.setTarget(this.f1538b.d(str));
                        this.f1540d.add(animatorClone);
                        this.e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f1539c == null) {
                this.f1539c = new AnimatorSet();
            }
            this.f1539c.playTogether(this.f1540d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1537a;
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

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            b.g.c.l.a.a(drawable, theme);
        }
    }

    public final void b(String str, Animator animator) {
        animator.setTarget(this.f1534c.f1538b.d(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0041b c0041b = this.f1534c;
        if (c0041b.f1540d == null) {
            c0041b.f1540d = new ArrayList<>();
            this.f1534c.e = new b.e.a<>();
        }
        this.f1534c.f1540d.add(animator);
        this.f1534c.e.put(animator, str);
    }

    public final void c(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                c(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.e == null) {
                    this.e = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.e);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            return b.g.c.l.a.b(drawable);
        }
        return false;
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f1534c.f1538b.draw(canvas);
        if (this.f1534c.f1539c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1546b;
        return drawable != null ? b.g.c.l.a.d(drawable) : this.f1534c.f1538b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f1534c.f1537a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f1546b;
        return drawable != null ? b.g.c.l.a.e(drawable) : this.f1534c.f1538b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f1546b == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(this.f1546b.getConstantState());
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f1534c.f1538b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f1534c.f1538b.getIntrinsicWidth();
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.getOpacity() : this.f1534c.f1538b.getOpacity();
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            b.g.c.l.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayK = b.g.b.c.g.k(resources, theme, attributeSet, b.r.a.a.a.e);
                    int resourceId = typedArrayK.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.h(false);
                        hVarB.setCallback(this.h);
                        h hVar = this.f1534c.f1538b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.f1534c.f1538b = hVarB;
                    }
                    typedArrayK.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, b.r.a.a.a.f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f1535d;
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
        this.f1534c.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1546b;
        return drawable != null ? b.g.c.l.a.h(drawable) : this.f1534c.f1538b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f1546b;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f1534c.f1539c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.isStateful() : this.f1534c.f1538b.isStateful();
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f1534c.f1538b.setBounds(rect);
        }
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.setLevel(i) : this.f1534c.f1538b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1546b;
        return drawable != null ? drawable.setState(iArr) : this.f1534c.f1538b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f1534c.f1538b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            b.g.c.l.a.j(drawable, z);
        } else {
            this.f1534c.f1538b.setAutoMirrored(z);
        }
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // b.r.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTint(int i) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            b.g.c.l.a.n(drawable, i);
        } else {
            this.f1534c.f1538b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            b.g.c.l.a.o(drawable, colorStateList);
        } else {
            this.f1534c.f1538b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            b.g.c.l.a.p(drawable, mode);
        } else {
            this.f1534c.f1538b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f1534c.f1538b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f1534c.f1539c.isStarted()) {
                return;
            }
            this.f1534c.f1539c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f1534c.f1539c.end();
        }
    }

    public b(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1546b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f1534c.f1538b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f1541a;

        public c(Drawable.ConstantState constantState) {
            this.f1541a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1541a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1541a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f1541a.newDrawable();
            bVar.f1546b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f1541a.newDrawable(resources);
            bVar.f1546b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f1541a.newDrawable(resources, theme);
            bVar.f1546b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.h);
            return bVar;
        }
    }

    public b(Context context, C0041b c0041b, Resources resources) {
        this.e = null;
        this.f = null;
        this.g = null;
        a aVar = new a();
        this.h = aVar;
        this.f1535d = context;
        if (c0041b != null) {
            this.f1534c = c0041b;
        } else {
            this.f1534c = new C0041b(context, c0041b, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
