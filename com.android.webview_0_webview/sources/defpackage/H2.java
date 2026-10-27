package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class H2 extends F2 {
    public static Method z;

    public H2(Drawable drawable) {
        super(drawable);
        e();
    }

    @Override // defpackage.F2
    public boolean a() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.x;
        return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
    }

    @Override // defpackage.F2
    public E2 b() {
        return new G2(this.v, null);
    }

    public final void e() {
        if (z == null) {
            try {
                z = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", e);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.x.getDirtyBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.x.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        Drawable drawable = this.x;
        if (drawable != null && (method = z) != null) {
            try {
                return ((Boolean) method.invoke(drawable, new Object[0])).booleanValue();
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", e);
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        this.x.setHotspot(f, f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.x.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // defpackage.F2, android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    @Override // defpackage.F2, android.graphics.drawable.Drawable
    public void setTint(int i) {
        if (a()) {
            super.setTint(i);
        } else {
            this.x.setTint(i);
        }
    }

    @Override // defpackage.F2, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (!a()) {
            this.x.setTintList(colorStateList);
        } else {
            this.v.c = colorStateList;
            d(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (!a()) {
            this.x.setTintMode(mode);
        } else {
            this.v.d = mode;
            d(getState());
        }
    }

    public H2(E2 e2, Resources resources) {
        super(e2, resources);
        e();
    }
}
