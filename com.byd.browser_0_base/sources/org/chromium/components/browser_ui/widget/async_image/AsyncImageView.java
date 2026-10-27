package org.chromium.components.browser_ui.widget.async_image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import defpackage.gp;
import defpackage.ny;
import defpackage.uh3;
import defpackage.v9;
import defpackage.yq1;
import org.chromium.components.browser_ui.widget.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class AsyncImageView extends ForegroundRoundedCornerImageView {
    public Drawable q;
    public Drawable r;
    public a s;
    public b t;
    public Runnable u;
    public boolean v;
    public Object w;

    @FunctionalInterface
    public interface a {
        Runnable a(int i, int i2, gp gpVar);
    }

    public interface b {
    }

    public AsyncImageView(Context context) {
        this(context, null, 0);
    }

    public final void f() {
        a aVar;
        if (getWidth() <= 0 || getHeight() <= 0 || (aVar = this.s) == null) {
            return;
        }
        this.v = true;
        this.u = aVar.a(getWidth(), getHeight(), new gp(this, 24, this.w));
        if (!this.v) {
            this.u = null;
        }
        this.s = null;
    }

    public final void g(a aVar, ny nyVar) {
        Object obj = this.w;
        if (obj == null || nyVar == null || !obj.equals(nyVar)) {
            setImageDrawable(null);
            setForegroundDrawableCompat(this.r);
            this.w = nyVar;
            this.s = aVar;
            f();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        f();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        b bVar;
        super.onSizeChanged(i, i2, i3, i4);
        if ((i == i3 && i2 == i4) || (bVar = this.t) == null) {
            return;
        }
        ((yq1.a) bVar).a(getDrawable());
    }

    @Override // org.chromium.components.browser_ui.widget.RoundedCornerImageView, androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.s = null;
        this.w = null;
        if (this.v) {
            Runnable runnable = this.u;
            if (runnable != null) {
                runnable.run();
            }
            this.u = null;
            this.v = false;
        }
        b bVar = this.t;
        if (bVar != null) {
            ((yq1.a) bVar).a(drawable);
        }
        setForegroundDrawableCompat(null);
        super.setImageDrawable(drawable);
    }

    public void setImageResizer(b bVar) {
        this.t = bVar;
    }

    public void setUnavailableDrawable(Drawable drawable) {
        boolean z = getForegroundDrawableCompat() == this.q && !this.v;
        Drawable drawableB = v9.b(drawable);
        this.q = drawableB;
        if (z) {
            setForegroundDrawableCompat(drawableB);
        }
    }

    public void setWaitingDrawable(Drawable drawable) {
        Drawable drawableB = v9.b(drawable);
        this.r = drawableB;
        if (this.v) {
            setForegroundDrawableCompat(drawableB);
        }
    }

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = attributeSet == null ? null : context.obtainStyledAttributes(attributeSet, R$styleable.AsyncImageView, 0, 0);
        this.q = v9.b(uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.AsyncImageView_unavailableSrc));
        this.r = v9.b(uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.AsyncImageView_waitingSrc));
        if (typedArrayObtainStyledAttributes != null) {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
