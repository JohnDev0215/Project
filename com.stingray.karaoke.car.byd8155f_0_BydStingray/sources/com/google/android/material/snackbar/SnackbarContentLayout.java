package com.google.android.material.snackbar;

import F1.H;
import K4.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout {
    public TextView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Button f12861m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f12862n;
    public int o;

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f3599s);
        this.f12862n = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, -1);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final boolean a(int i7, int i10, int i11) {
        boolean z6;
        if (i7 != getOrientation()) {
            setOrientation(i7);
            z6 = true;
        } else {
            z6 = false;
        }
        if (this.l.getPaddingTop() == i10 && this.l.getPaddingBottom() == i11) {
            return z6;
        }
        TextView textView = this.l;
        int[] iArr = H.f2303a;
        if (textView.isPaddingRelative()) {
            textView.setPaddingRelative(textView.getPaddingStart(), i10, textView.getPaddingEnd(), i11);
            return true;
        }
        textView.setPadding(textView.getPaddingLeft(), i10, textView.getPaddingRight(), i11);
        return true;
    }

    public Button getActionView() {
        return this.f12861m;
    }

    public TextView getMessageView() {
        return this.l;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.l = (TextView) findViewById(R.id.snackbar_text);
        this.f12861m = (Button) findViewById(R.id.snackbar_action);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        super.onMeasure(i7, i10);
        int i11 = this.f12862n;
        if (i11 > 0 && getMeasuredWidth() > i11) {
            i7 = View.MeasureSpec.makeMeasureSpec(i11, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            super.onMeasure(i7, i10);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        boolean z6 = this.l.getLayout().getLineCount() > 1;
        if (!z6 || this.o <= 0 || this.f12861m.getMeasuredWidth() <= this.o) {
            if (!z6) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!a(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!a(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i7, i10);
    }

    public void setMaxInlineActionWidth(int i7) {
        this.o = i7;
    }
}
