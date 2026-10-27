package androidx.vectordrawable.a.a;

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
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class b extends g implements Animatable {
    private C0042b d;
    private Context e;
    private ArgbEvaluator f;
    final Drawable.Callback g;

    /* JADX INFO: compiled from: caradapter-bug */
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

    /* JADX INFO: renamed from: androidx.vectordrawable.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private static class C0042b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1237a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        h f1238b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        AnimatorSet f1239c;
        ArrayList<Animator> d;
        b.a.a<Animator, String> e;

        C0042b(Context context, C0042b c0042b, Drawable.Callback callback, Resources resources) {
            if (c0042b != null) {
                this.f1237a = c0042b.f1237a;
                h hVar = c0042b.f1238b;
                if (hVar != null) {
                    Drawable.ConstantState constantState = hVar.getConstantState();
                    if (resources != null) {
                        this.f1238b = (h) constantState.newDrawable(resources);
                    } else {
                        this.f1238b = (h) constantState.newDrawable();
                    }
                    h hVar2 = this.f1238b;
                    hVar2.mutate();
                    h hVar3 = hVar2;
                    this.f1238b = hVar3;
                    hVar3.setCallback(callback);
                    this.f1238b.setBounds(c0042b.f1238b.getBounds());
                    this.f1238b.i(false);
                }
                ArrayList<Animator> arrayList = c0042b.d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.d = new ArrayList<>(size);
                    this.e = new b.a.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = c0042b.d.get(i);
                        Animator animatorClone = animator.clone();
                        String str = c0042b.e.get(animator);
                        animatorClone.setTarget(this.f1238b.e(str));
                        this.d.add(animatorClone);
                        this.e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f1239c == null) {
                this.f1239c = new AnimatorSet();
            }
            this.f1239c.playTogether(this.d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1237a;
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
        animator.setTarget(this.d.f1238b.e(str));
        if (Build.VERSION.SDK_INT < 21) {
            c(animator);
        }
        C0042b c0042b = this.d;
        if (c0042b.d == null) {
            c0042b.d = new ArrayList<>();
            this.d.e = new b.a.a<>();
        }
        this.d.d.add(animator);
        this.d.e.put(animator, str);
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
                if (this.f == null) {
                    this.f = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f);
            }
        }
    }

    @Override // androidx.vectordrawable.a.a.g, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.b(drawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.d.f1238b.draw(canvas);
        if (this.d.f1239c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1245c;
        return drawable != null ? androidx.core.graphics.drawable.a.c(drawable) : this.d.f1238b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.d.f1237a | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f1245c;
        return drawable != null ? androidx.core.graphics.drawable.a.d(drawable) : this.d.f1238b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f1245c == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new c(this.f1245c.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.getIntrinsicHeight() : this.d.f1238b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.getIntrinsicWidth() : this.d.f1238b.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.getOpacity() : this.d.f1238b.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.f(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayS = androidx.core.content.res.f.s(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.e);
                    int resourceId = typedArrayS.getResourceId(0, 0);
                    if (resourceId != 0) {
                        h hVarB = h.b(resources, resourceId, theme);
                        hVarB.i(false);
                        hVarB.setCallback(this.g);
                        h hVar = this.d.f1238b;
                        if (hVar != null) {
                            hVar.setCallback(null);
                        }
                        this.d.f1238b = hVarB;
                    }
                    typedArrayS.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.vectordrawable.a.a.a.f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.e;
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
        this.d.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1245c;
        return drawable != null ? androidx.core.graphics.drawable.a.g(drawable) : this.d.f1238b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f1245c;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.d.f1239c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.isStateful() : this.d.f1238b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.d.f1238b.setBounds(rect);
        }
    }

    @Override // androidx.vectordrawable.a.a.g, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.setLevel(i) : this.d.f1238b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1245c;
        return drawable != null ? drawable.setState(iArr) : this.d.f1238b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.d.f1238b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.i(drawable, z);
        } else {
            this.d.f1238b.setAutoMirrored(z);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.m(drawable, i);
        } else {
            this.d.f1238b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.n(drawable, colorStateList);
        } else {
            this.d.f1238b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, mode);
        } else {
            this.d.f1238b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.d.f1238b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.d.f1239c.isStarted()) {
                return;
            }
            this.d.f1239c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.d.f1239c.end();
        }
    }

    private b(@Nullable Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1245c;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.d.f1238b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(24)
    private static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f1240a;

        c(Drawable.ConstantState constantState) {
            this.f1240a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1240a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1240a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f1240a.newDrawable();
            bVar.f1245c = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.g);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f1240a.newDrawable(resources);
            bVar.f1245c = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.g);
            return bVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            b bVar = new b();
            Drawable drawableNewDrawable = this.f1240a.newDrawable(resources, theme);
            bVar.f1245c = drawableNewDrawable;
            drawableNewDrawable.setCallback(bVar.g);
            return bVar;
        }
    }

    private b(@Nullable Context context, @Nullable C0042b c0042b, @Nullable Resources resources) {
        this.f = null;
        this.g = new a();
        this.e = context;
        if (c0042b != null) {
            this.d = c0042b;
        } else {
            this.d = new C0042b(context, c0042b, this.g, resources);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
