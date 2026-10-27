package D3;

import L3.f;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import java.util.ArrayList;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Drawable implements Drawable.Callback, Animatable {
    public final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1170m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f1171n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f1172p = new ArrayList();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f1173q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f1174r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f1175s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1176t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1177u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Drawable f1178v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Drawable f1179w;

    public a(Drawable drawable, f fVar, int i7, boolean z6, boolean z10) {
        this.l = fVar;
        this.f1170m = i7;
        this.f1171n = z6;
        this.o = z10;
        this.f1173q = a(null, drawable != null ? Integer.valueOf(drawable.getIntrinsicWidth()) : null);
        this.f1174r = a(null, drawable != null ? Integer.valueOf(drawable.getIntrinsicHeight()) : null);
        this.f1176t = 255;
        this.f1178v = null;
        Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
        this.f1179w = drawableMutate;
        if (i7 <= 0) {
            throw new IllegalArgumentException("durationMillis must be > 0.");
        }
        Drawable drawable2 = this.f1178v;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        if (drawableMutate != null) {
            drawableMutate.setCallback(this);
        }
    }

    public final int a(Integer num, Integer num2) {
        if (this.o || ((num == null || num.intValue() != -1) && (num2 == null || num2.intValue() != -1))) {
            return Math.max(num != null ? num.intValue() : -1, num2 != null ? num2.intValue() : -1);
        }
        return -1;
    }

    public final void b(Drawable drawable, Rect rect) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            drawable.setBounds(rect);
            return;
        }
        int iWidth = rect.width();
        int iHeight = rect.height();
        double dZ = AbstractC2673a.z(intrinsicWidth, intrinsicHeight, iWidth, iHeight, this.l);
        double d = 2;
        int iV = C7.a.V((((double) iWidth) - (((double) intrinsicWidth) * dZ)) / d);
        int iV2 = C7.a.V((((double) iHeight) - (dZ * ((double) intrinsicHeight))) / d);
        drawable.setBounds(rect.left + iV, rect.top + iV2, rect.right - iV, rect.bottom - iV2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int iSave;
        Drawable drawable;
        int i7 = this.f1177u;
        if (i7 == 0) {
            Drawable drawable2 = this.f1178v;
            if (drawable2 != null) {
                drawable2.setAlpha(this.f1176t);
                iSave = canvas.save();
                try {
                    drawable2.draw(canvas);
                    return;
                } finally {
                }
            }
            return;
        }
        Drawable drawable3 = this.f1179w;
        if (i7 == 2) {
            if (drawable3 != null) {
                drawable3.setAlpha(this.f1176t);
                iSave = canvas.save();
                try {
                    drawable3.draw(canvas);
                    return;
                } finally {
                }
            }
            return;
        }
        double dUptimeMillis = (SystemClock.uptimeMillis() - this.f1175s) / ((double) this.f1170m);
        double dT = AbstractC2673a.t(dUptimeMillis, 0.0d, 1.0d);
        int i10 = this.f1176t;
        int i11 = (int) (dT * ((double) i10));
        if (this.f1171n) {
            i10 -= i11;
        }
        boolean z6 = dUptimeMillis >= 1.0d;
        if (!z6 && (drawable = this.f1178v) != null) {
            drawable.setAlpha(i10);
            iSave = canvas.save();
            try {
                drawable.draw(canvas);
            } finally {
            }
        }
        if (drawable3 != null) {
            drawable3.setAlpha(i11);
            iSave = canvas.save();
            try {
                drawable3.draw(canvas);
            } finally {
            }
        }
        if (!z6) {
            invalidateSelf();
            return;
        }
        this.f1177u = 2;
        this.f1178v = null;
        ArrayList arrayList = this.f1172p;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f1176t;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        ColorFilter colorFilter;
        int i7 = this.f1177u;
        if (i7 == 0) {
            Drawable drawable = this.f1178v;
            if (drawable != null) {
                return drawable.getColorFilter();
            }
            return null;
        }
        Drawable drawable2 = this.f1179w;
        if (i7 != 1) {
            if (i7 == 2 && drawable2 != null) {
                return drawable2.getColorFilter();
            }
            return null;
        }
        if (drawable2 != null && (colorFilter = drawable2.getColorFilter()) != null) {
            return colorFilter;
        }
        Drawable drawable3 = this.f1178v;
        if (drawable3 != null) {
            return drawable3.getColorFilter();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f1174r;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f1173q;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f1178v;
        int i7 = this.f1177u;
        if (i7 == 0) {
            if (drawable != null) {
                return drawable.getOpacity();
            }
            return -2;
        }
        Drawable drawable2 = this.f1179w;
        if (i7 == 2) {
            if (drawable2 != null) {
                return drawable2.getOpacity();
            }
            return -2;
        }
        if (drawable != null && drawable2 != null) {
            return Drawable.resolveOpacity(drawable.getOpacity(), drawable2.getOpacity());
        }
        if (drawable != null) {
            return drawable.getOpacity();
        }
        if (drawable2 != null) {
            return drawable2.getOpacity();
        }
        return -2;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.f1177u == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1178v;
        if (drawable != null) {
            b(drawable, rect);
        }
        Drawable drawable2 = this.f1179w;
        if (drawable2 != null) {
            b(drawable2, rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i7) {
        Drawable drawable = this.f1178v;
        boolean level = drawable != null ? drawable.setLevel(i7) : false;
        Drawable drawable2 = this.f1179w;
        return level || (drawable2 != null ? drawable2.setLevel(i7) : false);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1178v;
        boolean state = drawable != null ? drawable.setState(iArr) : false;
        Drawable drawable2 = this.f1179w;
        return state || (drawable2 != null ? drawable2.setState(iArr) : false);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        scheduleSelf(runnable, j3);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        if (i7 < 0 || i7 >= 256) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Invalid alpha: ").toString());
        }
        this.f1176t = i7;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1178v;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
        Drawable drawable2 = this.f1179w;
        if (drawable2 != null) {
            drawable2.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i7) {
        Drawable drawable = this.f1178v;
        if (drawable != null) {
            drawable.setTint(i7);
        }
        Drawable drawable2 = this.f1179w;
        if (drawable2 != null) {
            drawable2.setTint(i7);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintBlendMode(BlendMode blendMode) {
        Drawable drawable = this.f1178v;
        if (drawable != null) {
            drawable.setTintBlendMode(blendMode);
        }
        Drawable drawable2 = this.f1179w;
        if (drawable2 != null) {
            drawable2.setTintBlendMode(blendMode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1178v;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
        }
        Drawable drawable2 = this.f1179w;
        if (drawable2 != null) {
            drawable2.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1178v;
        if (drawable != null) {
            drawable.setTintMode(mode);
        }
        Drawable drawable2 = this.f1179w;
        if (drawable2 != null) {
            drawable2.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        Object obj = this.f1178v;
        Animatable animatable = obj instanceof Animatable ? (Animatable) obj : null;
        if (animatable != null) {
            animatable.start();
        }
        Object obj2 = this.f1179w;
        Animatable animatable2 = obj2 instanceof Animatable ? (Animatable) obj2 : null;
        if (animatable2 != null) {
            animatable2.start();
        }
        if (this.f1177u != 0) {
            return;
        }
        this.f1177u = 1;
        this.f1175s = SystemClock.uptimeMillis();
        ArrayList arrayList = this.f1172p;
        if (arrayList.size() <= 0) {
            invalidateSelf();
        } else {
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        Object obj = this.f1178v;
        Animatable animatable = obj instanceof Animatable ? (Animatable) obj : null;
        if (animatable != null) {
            animatable.stop();
        }
        Object obj2 = this.f1179w;
        Animatable animatable2 = obj2 instanceof Animatable ? (Animatable) obj2 : null;
        if (animatable2 != null) {
            animatable2.stop();
        }
        if (this.f1177u != 2) {
            this.f1177u = 2;
            this.f1178v = null;
            ArrayList arrayList = this.f1172p;
            if (arrayList.size() <= 0) {
                return;
            }
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
