package org.chromium.chrome.browser.download.home.list.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import defpackage.a8;
import defpackage.ap0;
import defpackage.uh3;
import defpackage.v9;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$styleable;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes.dex */
public class CircularProgressView extends ChromeImageButton {
    public final Drawable h;
    public final Drawable i;
    public final Drawable j;
    public final Drawable k;
    public final Drawable l;
    public final ap0 m;

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ap0 ap0Var = new ap0(this);
        this.m = ap0Var;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
        if (ap0Var.k != scaleType) {
            ap0Var.k = scaleType;
            ap0Var.i = true;
            if (ap0Var.j != null) {
                ap0Var.h.invalidate();
            }
        }
        TypedArray typedArrayObtainStyledAttributes = attributeSet == null ? null : context.obtainStyledAttributes(attributeSet, R$styleable.CircularProgressView, 0, 0);
        this.h = v9.b(uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressView_indeterminateProgress));
        this.i = v9.b(uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressView_determinateProgress));
        this.j = uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressView_resumeSrc);
        this.k = uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressView_pauseSrc);
        this.l = uh3.d(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressView_retrySrc);
        if (typedArrayObtainStyledAttributes != null) {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        this.m.a(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        ap0 ap0Var = this.m;
        Drawable drawable = ap0Var.j;
        if (drawable == null) {
            return;
        }
        View view = ap0Var.h;
        if (drawable.setState(view.getDrawableState())) {
            view.invalidate();
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.m.b(view, i);
    }

    public void setProgress(int i) {
        ap0 ap0Var = this.m;
        if (i == -1) {
            ap0Var.c(this.h);
            return;
        }
        Drawable drawable = this.i;
        if (drawable != null) {
            drawable.setLevel((a8.q(i, 0, 100) * TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE) / 100);
        }
        ap0Var.c(drawable);
    }

    public void setState(int i) {
        int i2;
        Drawable drawable;
        if (i == 0) {
            i2 = R$string.download_notification_pause_button;
            drawable = this.k;
        } else if (i != 1) {
            i2 = R$string.download_notification_resume_button;
            drawable = this.l;
        } else {
            i2 = R$string.download_notification_resume_button;
            drawable = this.j;
        }
        setImageDrawable(drawable);
        setContentDescription(getContext().getText(i2));
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
            ap0 r2 = r2.m
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
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.download.home.list.view.CircularProgressView.verifyDrawable(android.graphics.drawable.Drawable):boolean");
    }
}
