package l1;

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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0079b f6078e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f6079f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArgbEvaluator f6080g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Animator.AnimatorListener f6081h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<?> f6082i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Drawable.Callback f6083j;

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public class a implements Drawable.Callback {
        public a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            b.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
            b.this.scheduleSelf(runnable, j8);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            b.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: l1.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class C0079b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6085a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public h f6086b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public AnimatorSet f6087c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Animator> f6088d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public v.a<Animator, String> f6089e;

        public C0079b(Context context, C0079b c0079b, Drawable.Callback callback, Resources resources) {
            if (c0079b != null) {
                this.f6085a = c0079b.f6085a;
                h hVar = c0079b.f6086b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f6086b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f6086b = (h) constantState.newDrawable();
                    }
                    h hVar2 = (h) this.f6086b.mutate();
                    this.f6086b = hVar2;
                    hVar2.setCallback(callback);
                    this.f6086b.setBounds(c0079b.f6086b.getBounds());
                    this.f6086b.h(false);
                }
                ArrayList<Animator> arrayList = c0079b.f6088d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f6088d = new ArrayList<>(size);
                    this.f6089e = new v.a<>(size);
                    for (int i8 = 0; i8 < size; i8++) {
                        Animator animator = c0079b.f6088d.get(i8);
                        Animator animatorClone = animator.clone();
                        String str = c0079b.f6089e.get(animator);
                        animatorClone.setTarget(this.f6086b.d(str));
                        this.f6088d.add(animatorClone);
                        this.f6089e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f6087c == null) {
                this.f6087c = new AnimatorSet();
            }
            this.f6087c.playTogether(this.f6088d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6085a;
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

    @Override // l1.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.a(drawable, theme);
        }
    }

    public final void b(String str, Animator animator) {
        animator.setTarget(this.f6078e.f6086b.d(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0079b c0079b = this.f6078e;
        if (c0079b.f6088d == null) {
            c0079b.f6088d = new ArrayList<>();
            this.f6078e.f6089e = new v.a<>();
        }
        this.f6078e.f6088d.add(animator);
        this.f6078e.f6089e.put(animator, str);
    }

    public final void c(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i8 = 0; i8 < childAnimations.size(); i8++) {
                c(childAnimations.get(i8));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f6080g == null) {
                    this.f6080g = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f6080g);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            return h0.a.b(drawable);
        }
        return false;
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f6078e.f6086b.draw(canvas);
        if (this.f6078e.f6087c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f6095d;
        return drawable != null ? h0.a.d(drawable) : this.f6078e.f6086b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f6078e.f6085a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f6095d;
        return drawable != null ? h0.a.e(drawable) : this.f6078e.f6086b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f6095d;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(drawable.getConstantState());
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f6078e.f6086b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f6078e.f6086b.getIntrinsicWidth();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.getOpacity() : this.f6078e.f6086b.getOpacity();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, l1.a.f6070e);
                    int resourceId = typedArrayK.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.h(false);
                        hVarB.setCallback(this.f6083j);
                        h hVar = this.f6078e.f6086b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.f6078e.f6086b = hVarB;
                    }
                    typedArrayK.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, l1.a.f6071f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f6079f;
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
        this.f6078e.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f6095d;
        return drawable != null ? h0.a.h(drawable) : this.f6078e.f6086b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f6095d;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f6078e.f6087c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.isStateful() : this.f6078e.f6086b.isStateful();
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f6078e.f6086b.setBounds(rect);
        }
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i8) {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.setLevel(i8) : this.f6078e.f6086b.setLevel(i8);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f6095d;
        return drawable != null ? drawable.setState(iArr) : this.f6078e.f6086b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.setAlpha(i8);
        } else {
            this.f6078e.f6086b.setAlpha(i8);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z7) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.j(drawable, z7);
        } else {
            this.f6078e.f6086b.setAutoMirrored(z7);
        }
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i8) {
        super.setChangingConfigurations(i8);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i8, PorterDuff.Mode mode) {
        super.setColorFilter(i8, mode);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z7) {
        super.setFilterBitmap(z7);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f8, float f9) {
        super.setHotspot(f8, f9);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i8, int i9, int i10, int i11) {
        super.setHotspotBounds(i8, i9, i10, i11);
    }

    @Override // l1.g, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTint(int i8) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.n(drawable, i8);
        } else {
            this.f6078e.f6086b.setTint(i8);
        }
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.o(drawable, colorStateList);
        } else {
            this.f6078e.f6086b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            h0.a.p(drawable, mode);
        } else {
            this.f6078e.f6086b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z7, boolean z8) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            return drawable.setVisible(z7, z8);
        }
        this.f6078e.f6086b.setVisible(z7, z8);
        return super.setVisible(z7, z8);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f6078e.f6087c.isStarted()) {
                return;
            }
            this.f6078e.f6087c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f6078e.f6087c.end();
        }
    }

    public b(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f6095d;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f6078e.f6086b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f6090a;

        public c(Drawable.ConstantState constantState) {
            this.f6090a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f6090a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6090a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f6090a.newDrawable();
            bVar.f6095d = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f6083j);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f6090a.newDrawable(resources);
            bVar.f6095d = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f6083j);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f6090a.newDrawable(resources, theme);
            bVar.f6095d = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.f6083j);
            return bVar;
        }
    }

    public b(Context context, C0079b c0079b, Resources resources) {
        this.f6080g = null;
        this.f6081h = null;
        this.f6082i = null;
        a aVar = new a();
        this.f6083j = aVar;
        this.f6079f = context;
        if (c0079b != null) {
            this.f6078e = c0079b;
        } else {
            this.f6078e = new C0079b(context, c0079b, aVar, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
