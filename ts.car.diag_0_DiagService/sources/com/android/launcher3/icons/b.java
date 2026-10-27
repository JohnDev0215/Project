package com.android.launcher3.icons;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.UserHandle;
import androidx.annotation.NonNull;
import com.android.launcher3.icons.c;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class b implements AutoCloseable {
    static final boolean p;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Rect f1299c;
    protected final Context d;
    private final Canvas e;
    private final PackageManager f;
    private final d g;
    private boolean h;
    private boolean i;
    protected final int j;
    private g k;
    private h l;
    private final boolean m;
    private Drawable n;
    private int o;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a extends BitmapDrawable {
        public a(Bitmap bitmap) {
            super((Resources) null, bitmap);
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return getBitmap().getWidth();
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return getBitmap().getWidth();
        }
    }

    static {
        p = Build.VERSION.SDK_INT >= 26;
    }

    protected b(Context context, int i, int i2, boolean z) {
        this.f1299c = new Rect();
        this.i = false;
        this.o = -1;
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        this.m = z;
        this.j = i2;
        this.f = applicationContext.getPackageManager();
        this.g = new d();
        Canvas canvas = new Canvas();
        this.e = canvas;
        canvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
        c();
    }

    private Bitmap f(Drawable drawable, float f) {
        return g(drawable, f, this.j);
    }

    private int h(Bitmap bitmap) {
        if (this.h) {
            return 0;
        }
        return this.g.a(bitmap);
    }

    public static int i(int i) {
        return (int) (i * 0.444f);
    }

    private Drawable l(@NonNull Drawable drawable, boolean z, RectF rectF, float[] fArr) {
        float fC;
        if (drawable == null) {
            return null;
        }
        if (z && p) {
            if (this.n == null) {
                this.n = this.d.getDrawable(R$drawable.adaptive_icon_drawable_wrapper).mutate();
            }
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) this.n;
            adaptiveIconDrawable.setBounds(0, 0, 1, 1);
            boolean[] zArr = new boolean[1];
            fC = j().c(drawable, rectF, adaptiveIconDrawable.getIconMask(), zArr);
            if (!(drawable instanceof AdaptiveIconDrawable) && !zArr[0]) {
                FixedScaleDrawable fixedScaleDrawable = (FixedScaleDrawable) adaptiveIconDrawable.getForeground();
                fixedScaleDrawable.setDrawable(drawable);
                fixedScaleDrawable.a(fC);
                fC = j().c(adaptiveIconDrawable, rectF, null, null);
                ((ColorDrawable) adaptiveIconDrawable.getBackground()).setColor(this.o);
                drawable = adaptiveIconDrawable;
            }
        } else {
            fC = j().c(drawable, rectF, null, null);
        }
        fArr[0] = fC;
        return drawable;
    }

    public void a(Bitmap bitmap, Drawable drawable) {
        this.e.setBitmap(bitmap);
        b(this.e, drawable);
        this.e.setBitmap(null);
    }

    public void b(Canvas canvas, Drawable drawable) {
        int i = i(this.j);
        if (this.i) {
            int i2 = this.j;
            drawable.setBounds(0, i2 - i, i, i2);
        } else {
            int i3 = this.j;
            drawable.setBounds(i3 - i, i3 - i, i3, i3);
        }
        drawable.draw(canvas);
    }

    protected void c() {
        this.o = -1;
        this.h = false;
        this.i = false;
    }

    public c d(Drawable drawable, UserHandle userHandle, boolean z) {
        return e(drawable, userHandle, z, false, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c e(@NonNull Drawable drawable, UserHandle userHandle, boolean z, boolean z2, float[] fArr) {
        if (fArr == null) {
            fArr = new float[1];
        }
        Drawable drawableL = l(drawable, z, null, fArr);
        Bitmap bitmapF = f(drawableL, fArr[0]);
        if (p && (drawableL instanceof AdaptiveIconDrawable)) {
            this.e.setBitmap(bitmapF);
            k().b(Bitmap.createBitmap(bitmapF), this.e);
            this.e.setBitmap(null);
        }
        if (z2) {
            a(bitmapF, this.d.getDrawable(R$drawable.ic_instant_app_badge));
        }
        if (userHandle != null) {
            Drawable userBadgedIcon = this.f.getUserBadgedIcon(new a(bitmapF), userHandle);
            bitmapF = userBadgedIcon instanceof BitmapDrawable ? ((BitmapDrawable) userBadgedIcon).getBitmap() : f(userBadgedIcon, 1.0f);
        }
        int iH = h(bitmapF);
        return drawableL instanceof c.a ? ((c.a) drawableL).a(bitmapF, iH, this) : c.b(bitmapF, iH);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap g(@androidx.annotation.NonNull android.graphics.drawable.Drawable r6, float r7, int r8) {
        /*
            r5 = this;
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r8, r8, r0)
            if (r6 != 0) goto L9
            return r0
        L9:
            android.graphics.Canvas r1 = r5.e
            r1.setBitmap(r0)
            android.graphics.Rect r1 = r5.f1299c
            android.graphics.Rect r2 = r6.getBounds()
            r1.set(r2)
            boolean r1 = com.android.launcher3.icons.b.p
            if (r1 == 0) goto L43
            boolean r1 = r6 instanceof android.graphics.drawable.AdaptiveIconDrawable
            if (r1 == 0) goto L43
            r1 = 1009429163(0x3c2aaaab, float:0.010416667)
            float r2 = (float) r8
            float r1 = r1 * r2
            double r3 = (double) r1
            double r3 = java.lang.Math.ceil(r3)
            int r1 = (int) r3
            r3 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 - r7
            float r2 = r2 * r3
            r7 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r7
            int r7 = java.lang.Math.round(r2)
            int r7 = java.lang.Math.max(r1, r7)
            int r8 = r8 - r7
            r6.setBounds(r7, r7, r8, r8)
            android.graphics.Canvas r7 = r5.e
            r6.draw(r7)
            goto La7
        L43:
            boolean r1 = r6 instanceof android.graphics.drawable.BitmapDrawable
            if (r1 == 0) goto L63
            r1 = r6
            android.graphics.drawable.BitmapDrawable r1 = (android.graphics.drawable.BitmapDrawable) r1
            android.graphics.Bitmap r2 = r1.getBitmap()
            if (r0 == 0) goto L63
            int r2 = r2.getDensity()
            if (r2 != 0) goto L63
            android.content.Context r2 = r5.d
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            r1.setTargetDensity(r2)
        L63:
            int r1 = r6.getIntrinsicWidth()
            int r2 = r6.getIntrinsicHeight()
            if (r1 <= 0) goto L81
            if (r2 <= 0) goto L81
            float r3 = (float) r1
            float r4 = (float) r2
            float r3 = r3 / r4
            if (r1 <= r2) goto L7a
            float r1 = (float) r8
            float r1 = r1 / r3
            int r1 = (int) r1
            r2 = r1
            r1 = r8
            goto L83
        L7a:
            if (r2 <= r1) goto L81
            float r1 = (float) r8
            float r1 = r1 * r3
            int r1 = (int) r1
            r2 = r8
            goto L83
        L81:
            r1 = r8
            r2 = r1
        L83:
            int r3 = r8 - r1
            int r3 = r3 / 2
            int r4 = r8 - r2
            int r4 = r4 / 2
            int r1 = r1 + r3
            int r2 = r2 + r4
            r6.setBounds(r3, r4, r1, r2)
            android.graphics.Canvas r1 = r5.e
            r1.save()
            android.graphics.Canvas r1 = r5.e
            int r8 = r8 / 2
            float r8 = (float) r8
            r1.scale(r7, r7, r8, r8)
            android.graphics.Canvas r7 = r5.e
            r6.draw(r7)
            android.graphics.Canvas r7 = r5.e
            r7.restore()
        La7:
            android.graphics.Rect r7 = r5.f1299c
            r6.setBounds(r7)
            android.graphics.Canvas r5 = r5.e
            r6 = 0
            r5.setBitmap(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.b.g(android.graphics.drawable.Drawable, float, int):android.graphics.Bitmap");
    }

    public g j() {
        if (this.k == null) {
            this.k = new g(this.d, this.j, this.m);
        }
        return this.k;
    }

    public h k() {
        if (this.l == null) {
            this.l = new h(this.j);
        }
        return this.l;
    }

    protected b(Context context, int i, int i2) {
        this(context, i, i2, false);
    }
}
