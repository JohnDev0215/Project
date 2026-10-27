package org.chromium.chrome.browser.feed.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import defpackage.ii2;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes.dex */
public class MaterialSpinnerView extends AppCompatImageView {
    public final CircularProgressDrawable h;

    public MaterialSpinnerView(Context context) {
        this(context, null);
    }

    public final void c(boolean z) {
        CircularProgressDrawable circularProgressDrawable = this.h;
        if (circularProgressDrawable == null) {
            return;
        }
        boolean z2 = isShown() && z;
        if (circularProgressDrawable.isRunning() && !z2) {
            circularProgressDrawable.stop();
        } else {
            if (circularProgressDrawable.isRunning() || !z2) {
                return;
            }
            circularProgressDrawable.start();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        c(true);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        c(false);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        c(isAttachedToWindow());
    }

    public MaterialSpinnerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialSpinnerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TraceEvent.q("MaterialSpinnerView", null);
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        this.h = circularProgressDrawable;
        circularProgressDrawable.d(1);
        setImageDrawable(circularProgressDrawable);
        int[] iArr = {ii2.c(context)};
        CircularProgressDrawable.a aVar = circularProgressDrawable.e;
        aVar.i = iArr;
        aVar.a(0);
        aVar.a(0);
        circularProgressDrawable.invalidateSelf();
        c(isAttachedToWindow());
        TraceEvent.C("MaterialSpinnerView");
    }
}
