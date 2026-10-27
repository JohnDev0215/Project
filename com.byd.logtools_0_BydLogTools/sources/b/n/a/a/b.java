package b.n.a.a;

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
    private C0051b f2798c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArgbEvaluator f2800e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Animator.AnimatorListener f2801f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ArrayList<?> f2802g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Drawable.Callback f2803h;

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    class a implements Drawable.Callback {
        a() {
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

    /* JADX INFO: renamed from: b.n.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    private static class C0051b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2805a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        h f2806b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        AnimatorSet f2807c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        ArrayList<Animator> f2808d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        b.d.a<Animator, String> f2809e;

        public C0051b(Context context, C0051b c0051b, Drawable.Callback callback, Resources resources) {
            if (c0051b != null) {
                this.f2805a = c0051b.f2805a;
                h hVar = c0051b.f2806b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f2806b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f2806b = (h) constantState.newDrawable();
                    }
                    h hVar2 = (h) this.f2806b.mutate();
                    this.f2806b = hVar2;
                    hVar2.setCallback(callback);
                    this.f2806b.setBounds(c0051b.f2806b.getBounds());
                    this.f2806b.h(false);
                }
                ArrayList<Animator> arrayList = c0051b.f2808d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f2808d = new ArrayList<>(size);
                    this.f2809e = new b.d.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = c0051b.f2808d.get(i);
                        Animator animatorClone = animator.clone();
                        String str = c0051b.f2809e.get(animator);
                        animatorClone.setTarget(this.f2806b.d(str));
                        this.f2808d.add(animatorClone);
                        this.f2809e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f2807c == null) {
                this.f2807c = new AnimatorSet();
            }
            this.f2807c.playTogether(this.f2808d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2805a;
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

    b() {
        this(null, null, null);
    }

    public static b a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        b bVar = new b(context);
        bVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return bVar;
    }

    private void b(String str, Animator animator) {
        animator.setTarget(this.f2798c.f2806b.d(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0051b c0051b = this.f2798c;
        if (c0051b.f2808d == null) {
            c0051b.f2808d = new ArrayList<>();
            this.f2798c.f2809e = new b.d.a<>();
        }
        this.f2798c.f2808d.add(animator);
        this.f2798c.f2809e.put(animator, str);
    }

    private void c(Animator animator) {
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
                if (this.f2800e == null) {
                    this.f2800e = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f2800e);
            }
        }
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.b(drawable);
        }
        return false;
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f2798c.f2806b.draw(canvas);
        if (this.f2798c.f2807c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f2815b;
        return drawable != null ? androidx.core.graphics.drawable.a.d(drawable) : this.f2798c.f2806b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f2798c.f2805a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f2815b;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f2798c.f2806b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f2815b;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(drawable.getConstantState());
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f2798c.f2806b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f2798c.f2806b.getIntrinsicWidth();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.getOpacity() : this.f2798c.f2806b.getOpacity();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, b.n.a.a.a.f2794e);
                    int resourceId = typedArrayK.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.h(false);
                        hVarB.setCallback(this.f2803h);
                        h hVar = this.f2798c.f2806b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.f2798c.f2806b = hVarB;
                    }
                    typedArrayK.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, b.n.a.a.a.f2795f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f2799d;
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
        this.f2798c.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f2815b;
        return drawable != null ? androidx.core.graphics.drawable.a.h(drawable) : this.f2798c.f2806b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f2815b;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f2798c.f2807c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.isStateful() : this.f2798c.f2806b.isStateful();
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f2798c.f2806b.setBounds(rect);
        }
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.setLevel(i) : this.f2798c.f2806b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f2815b;
        return drawable != null ? drawable.setState(iArr) : this.f2798c.f2806b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f2798c.f2806b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.j(drawable, z);
        } else {
            this.f2798c.f2806b.setAutoMirrored(z);
        }
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // b.n.a.a.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.n(drawable, i);
        } else {
            this.f2798c.f2806b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, colorStateList);
        } else {
            this.f2798c.f2806b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.p(drawable, mode);
        } else {
            this.f2798c.f2806b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f2798c.f2806b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f2798c.f2807c.isStarted()) {
                return;
            }
            this.f2798c.f2807c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f2798c.f2807c.end();
        }
    }

    private b(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f2815b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f2798c.f2806b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    private static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f2810a;

        public c(Drawable.ConstantState constantState) {
            this.f2810a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f2810a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f2810a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f2810a.newDrawable();
            bVar.f2815b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f2803h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f2810a.newDrawable(resources);
            bVar.f2815b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f2803h);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f2810a.newDrawable(resources, theme);
            bVar.f2815b = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f2803h);
            return bVar;
        }
    }

    private b(Context context, C0051b c0051b, Resources resources) {
        this.f2800e = null;
        this.f2801f = null;
        this.f2802g = null;
        a aVar = new a();
        this.f2803h = aVar;
        this.f2799d = context;
        if (c0051b != null) {
            this.f2798c = c0051b;
        } else {
            this.f2798c = new C0051b(context, c0051b, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
