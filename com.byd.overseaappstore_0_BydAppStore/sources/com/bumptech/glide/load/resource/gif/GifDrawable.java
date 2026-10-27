package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.bumptech.glide.load.resource.gif.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import spi.bd;
import spi.dl;
import spi.qt;
import spi.w1;

/* JADX INFO: loaded from: classes.dex */
public class GifDrawable extends Drawable implements a.b, Animatable, Animatable2Compat {
    public final a a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public int f;
    public int g;
    public boolean h;
    public Paint i;
    public Rect j;
    public List<Animatable2Compat.AnimationCallback> k;

    public static final class a extends Drawable.ConstantState {

        @VisibleForTesting
        public final com.bumptech.glide.load.resource.gif.a a;

        public a(com.bumptech.glide.load.resource.gif.a aVar) {
            this.a = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public final Drawable newDrawable() {
            return new GifDrawable(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public final Drawable newDrawable(Resources resources) {
            return new GifDrawable(this);
        }
    }

    public GifDrawable(Context context, bd bdVar, qt<Bitmap> qtVar, int i, int i2, Bitmap bitmap) {
        this(new a(new com.bumptech.glide.load.resource.gif.a(com.bumptech.glide.a.b(context), bdVar, i, i2, qtVar, bitmap)));
    }

    @Deprecated
    public GifDrawable(Context context, bd bdVar, w1 w1Var, qt<Bitmap> qtVar, int i, int i2, Bitmap bitmap) {
        this(context, bdVar, qtVar, i, i2, bitmap);
    }

    public GifDrawable(a aVar) {
        this.e = true;
        this.g = -1;
        Objects.requireNonNull(aVar, "Argument must not be null");
        this.a = aVar;
    }

    @VisibleForTesting
    public GifDrawable(com.bumptech.glide.load.resource.gif.a aVar, Paint paint) {
        this(new a(aVar));
        this.i = paint;
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.ArrayList, java.util.List<androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback>] */
    @Override // com.bumptech.glide.load.resource.gif.a.b
    public final void a() {
        Object callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        if (callback == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        a.C0020a c0020a = this.a.a.i;
        if ((c0020a != null ? c0020a.e : -1) == r0.a.c() - 1) {
            this.f++;
        }
        int i = this.g;
        if (i == -1 || this.f < i) {
            return;
        }
        ?? r0 = this.k;
        if (r0 != 0) {
            int size = r0.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Animatable2Compat.AnimationCallback) this.k.get(i2)).onAnimationEnd(this);
            }
        }
        stop();
    }

    public final Bitmap b() {
        return this.a.a.l;
    }

    public final Paint c() {
        if (this.i == null) {
            this.i = new Paint(2);
        }
        return this.i;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback>] */
    @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public final void clearAnimationCallbacks() {
        ?? r0 = this.k;
        if (r0 != 0) {
            r0.clear();
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.bumptech.glide.load.resource.gif.GifDrawable for r4v1 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public final void d() {
        /*
            r4 = this;
            boolean r0 = r4.d
            r1 = 1
            r0 = r0 ^ r1
            java.lang.String r2 = "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request."
            spi.dl.a(r0, r2)
            com.bumptech.glide.load.resource.gif.GifDrawable$a r0 = r4.a
            com.bumptech.glide.load.resource.gif.a r0 = r0.a
            spi.bd r0 = r0.a
            int r0 = r0.c()
            if (r0 != r1) goto L16
            goto L46
        L16:
            boolean r0 = r4.b
            if (r0 != 0) goto L5a
            r4.b = r1
            com.bumptech.glide.load.resource.gif.GifDrawable$a r0 = r4.a
            com.bumptech.glide.load.resource.gif.a r0 = r0.a
            boolean r2 = r0.j
            if (r2 != 0) goto L52
            java.util.List<com.bumptech.glide.load.resource.gif.a$b> r2 = r0.c
            boolean r2 = r2.contains(r4)
            if (r2 != 0) goto L4a
            java.util.List<com.bumptech.glide.load.resource.gif.a$b> r2 = r0.c
            boolean r2 = r2.isEmpty()
            java.util.List<com.bumptech.glide.load.resource.gif.a$b> r3 = r0.c
            r3.add(r4)
            if (r2 == 0) goto L46
            boolean r2 = r0.f
            if (r2 == 0) goto L3e
            goto L46
        L3e:
            r0.f = r1
            r1 = 0
            r0.j = r1
            r0.a()
        L46:
            r4.invalidateSelf()
            goto L5a
        L4a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Cannot subscribe twice in a row"
            r0.<init>(r1)
            throw r0
        L52:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Cannot subscribe to a cleared frame loader"
            r0.<init>(r1)
            throw r0
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.gif.GifDrawable.d():void");
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(@NonNull Canvas canvas) {
        if (this.d) {
            return;
        }
        if (this.h) {
            int intrinsicWidth = getIntrinsicWidth();
            int intrinsicHeight = getIntrinsicHeight();
            Rect bounds = getBounds();
            if (this.j == null) {
                this.j = new Rect();
            }
            Gravity.apply(119, intrinsicWidth, intrinsicHeight, bounds, this.j);
            this.h = false;
        }
        com.bumptech.glide.load.resource.gif.a aVar = this.a.a;
        a.C0020a c0020a = aVar.i;
        Bitmap bitmap = c0020a != null ? c0020a.g : aVar.l;
        if (this.j == null) {
            this.j = new Rect();
        }
        canvas.drawBitmap(bitmap, (Rect) null, this.j, c());
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<com.bumptech.glide.load.resource.gif.a$b>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<com.bumptech.glide.load.resource.gif.a$b>] */
    public final void e() {
        this.b = false;
        com.bumptech.glide.load.resource.gif.a aVar = this.a.a;
        aVar.c.remove(this);
        if (aVar.c.isEmpty()) {
            aVar.f = false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.a;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.a.a.r;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.a.a.q;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.h = true;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback>] */
    @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public final void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (animationCallback == null) {
            return;
        }
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(animationCallback);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        c().setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        c().setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        dl.a(!this.d, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.e = z;
        if (!z) {
            e();
        } else if (this.c) {
            d();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.c = true;
        this.f = 0;
        if (this.e) {
            d();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.c = false;
        e();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback>] */
    @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public final boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        ?? r0 = this.k;
        if (r0 == 0 || animationCallback == null) {
            return false;
        }
        return r0.remove(animationCallback);
    }
}
