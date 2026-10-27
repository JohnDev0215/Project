package org.chromium.components.browser_ui.widget.async_image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import defpackage.ap0;
import defpackage.uh3;
import defpackage.v9;
import org.chromium.components.browser_ui.widget.R$styleable;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;

/* JADX INFO: loaded from: classes2.dex */
public class ForegroundRoundedCornerImageView extends RoundedCornerImageView {
    public final ap0 p;

    public ForegroundRoundedCornerImageView(Context context) {
        this(context, null, 0);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        this.p.a(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        ap0 ap0Var = this.p;
        Drawable drawable = ap0Var.j;
        if (drawable == null) {
            return;
        }
        View view = ap0Var.h;
        if (drawable.setState(view.getDrawableState())) {
            view.invalidate();
        }
    }

    public Drawable getForegroundDrawableCompat() {
        return this.p.j;
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.p.b(view, i);
    }

    public void setForegroundDrawableCompat(Drawable drawable) {
        this.p.c(drawable);
    }

    public void setForegroundScaleTypeCompat(ImageView.ScaleType scaleType) {
        ap0 ap0Var = this.p;
        if (ap0Var.k == scaleType) {
            return;
        }
        ap0Var.k = scaleType;
        ap0Var.i = true;
        if (ap0Var.j != null) {
            ap0Var.h.invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.ImageView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean verifyDrawable(android.graphics.drawable.Drawable r3) {
        /*
            r2 = this;
            boolean r0 = super.verifyDrawable(r3)
            r1 = 1
            if (r0 != 0) goto L1a
            r0 = 0
            ap0 r2 = r2.p
            if (r3 == 0) goto L12
            android.graphics.drawable.Drawable r2 = r2.j
            if (r2 != r3) goto L15
            r2 = r1
            goto L16
        L12:
            r2.getClass()
        L15:
            r2 = r0
        L16:
            if (r2 == 0) goto L19
            goto L1a
        L19:
            r1 = r0
        L1a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.widget.async_image.ForegroundRoundedCornerImageView.verifyDrawable(android.graphics.drawable.Drawable):boolean");
    }

    public ForegroundRoundedCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ForegroundRoundedCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ap0 ap0Var = new ap0(this);
        this.p = ap0Var;
        TypedArray typedArrayObtainStyledAttributes = attributeSet == null ? null : context.obtainStyledAttributes(attributeSet, R$styleable.ForegroundRoundedCornerImageView, 0, 0);
        ap0Var.c(v9.b(uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.ForegroundRoundedCornerImageView_foregroundCompat)));
        if (typedArrayObtainStyledAttributes != null) {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
