package defpackage;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class G7 extends K7 implements Animatable {
    public Context u;
    public ArgbEvaluator v = null;
    public final Drawable.Callback w = new D7(this);
    public E7 t = new E7(null, this.w, null);

    public G7(Context context, E7 e7, Resources resources) {
        this.u = context;
    }

    @Override // defpackage.K7, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.applyTheme(theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.canApplyTheme();
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.t.b.draw(canvas);
        if (this.t.c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getAlpha() : this.t.b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.t.a;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.s == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new F7(this.s.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getIntrinsicHeight() : this.t.b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getIntrinsicWidth() : this.t.b.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.getOpacity() : this.t.b.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        String str;
        XmlResourceParser animation;
        Animator animatorLoadAnimator;
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        for (int i = 1; eventType != i && (xmlPullParser.getDepth() >= depth || eventType != 3); i = 1) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                XmlResourceParser xmlResourceParser = null;
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayG = AbstractC1195n2.g(resources, theme, attributeSet, C7.e);
                    int resourceId = typedArrayG.getResourceId(0, 0);
                    if (resourceId != 0) {
                        U7 u7B = U7.b(resources, resourceId, theme);
                        u7B.x = false;
                        u7B.setCallback(this.w);
                        U7 u7 = this.t.b;
                        if (u7 != null) {
                            u7.setCallback(null);
                        }
                        this.t.b = u7B;
                    }
                    typedArrayG.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, C7.f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(i, 0);
                    if (resourceId2 != 0) {
                        Context context = this.u;
                        if (context == null) {
                            typedArrayObtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        if (Build.VERSION.SDK_INT >= 24) {
                            animatorLoadAnimator = AnimatorInflater.loadAnimator(context, resourceId2);
                        } else {
                            Resources resources2 = context.getResources();
                            Resources.Theme theme2 = context.getTheme();
                            try {
                                try {
                                    animation = resources2.getAnimation(resourceId2);
                                    str = "Can't load animation resource ID #0x";
                                } catch (IOException e) {
                                    e = e;
                                    str = "Can't load animation resource ID #0x";
                                } catch (XmlPullParserException e2) {
                                    e = e2;
                                    str = "Can't load animation resource ID #0x";
                                }
                                try {
                                    Animator animatorA = I7.a(context, resources2, theme2, animation, Xml.asAttributeSet(animation), null, 0, 1.0f);
                                    animation.close();
                                    animatorLoadAnimator = animatorA;
                                } catch (IOException e3) {
                                    e = e3;
                                    Resources.NotFoundException notFoundException = new Resources.NotFoundException(str + Integer.toHexString(resourceId2));
                                    notFoundException.initCause(e);
                                    throw notFoundException;
                                } catch (XmlPullParserException e4) {
                                    e = e4;
                                    Resources.NotFoundException notFoundException2 = new Resources.NotFoundException(str + Integer.toHexString(resourceId2));
                                    notFoundException2.initCause(e);
                                    throw notFoundException2;
                                }
                            } catch (Throwable th) {
                                if (0 != 0) {
                                    xmlResourceParser.close();
                                }
                                throw th;
                            }
                        }
                        animatorLoadAnimator.setTarget(this.t.b.t.b.p.get(string));
                        E7 e7 = this.t;
                        if (e7.d == null) {
                            e7.d = new ArrayList();
                            this.t.e = new C0971j1();
                        }
                        this.t.d.add(animatorLoadAnimator);
                        this.t.e.put(animatorLoadAnimator, string);
                    }
                    typedArrayObtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        E7 e72 = this.t;
        if (e72.c == null) {
            e72.c = new AnimatorSet();
        }
        e72.c.playTogether(e72.d);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.isAutoMirrored() : this.t.b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.s;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.t.c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.s;
        return drawable != null ? drawable.isStateful() : this.t.b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.t.b.setBounds(rect);
        }
    }

    @Override // defpackage.K7, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        Drawable drawable = this.s;
        return drawable != null ? drawable.setLevel(i) : this.t.b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.s;
        return drawable != null ? drawable.setState(iArr) : this.t.b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.t.b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
            return;
        }
        U7 u7 = this.t.b;
        Drawable drawable2 = u7.s;
        if (drawable2 != null) {
            drawable2.setAutoMirrored(z);
        } else {
            u7.t.e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        U7 u7 = this.t.b;
        Drawable drawable2 = u7.s;
        if (drawable2 != null) {
            drawable2.setColorFilter(colorFilter);
        } else {
            u7.v = colorFilter;
            u7.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        Drawable drawable = this.s;
        if (drawable != null) {
            B2.a(drawable, i);
        } else {
            this.t.b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.s;
        if (drawable != null) {
            B2.b(drawable, colorStateList);
        } else {
            this.t.b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.s;
        if (drawable != null) {
            B2.c(drawable, mode);
        } else {
            this.t.b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.t.b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.s;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.t.c.isStarted()) {
                return;
            }
            this.t.c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.s;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.t.c.end();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
