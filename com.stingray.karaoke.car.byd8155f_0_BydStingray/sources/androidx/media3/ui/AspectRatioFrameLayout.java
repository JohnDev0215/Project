package androidx.media3.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import m3.AbstractC1797C;
import m3.InterfaceC1801a;
import m3.RunnableC1802b;

/* JADX INFO: loaded from: classes.dex */
public final class AspectRatioFrameLayout extends FrameLayout {
    public static final /* synthetic */ int o = 0;
    public final RunnableC1802b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f12019m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f12020n;

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12020n = 0;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, AbstractC1797C.f18961a, 0, 0);
            try {
                this.f12020n = typedArrayObtainStyledAttributes.getInt(0, 0);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        this.l = new RunnableC1802b(this);
    }

    public int getResizeMode() {
        return this.f12020n;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        float f10;
        float f11;
        super.onMeasure(i7, i10);
        if (this.f12019m <= 0.0f) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f12 = measuredWidth;
        float f13 = measuredHeight;
        float f14 = (this.f12019m / (f12 / f13)) - 1.0f;
        float fAbs = Math.abs(f14);
        RunnableC1802b runnableC1802b = this.l;
        if (fAbs <= 0.01f) {
            if (runnableC1802b.f19007m) {
                return;
            }
            runnableC1802b.f19007m = true;
            ((AspectRatioFrameLayout) runnableC1802b.f19008n).post(runnableC1802b);
            return;
        }
        int i11 = this.f12020n;
        if (i11 != 0) {
            if (i11 != 1) {
                if (i11 == 2) {
                    f10 = this.f12019m;
                } else if (i11 == 4) {
                    if (f14 > 0.0f) {
                        f10 = this.f12019m;
                    } else {
                        f11 = this.f12019m;
                    }
                }
                measuredWidth = (int) (f13 * f10);
            } else {
                f11 = this.f12019m;
            }
            measuredHeight = (int) (f12 / f11);
        } else if (f14 > 0.0f) {
            f11 = this.f12019m;
            measuredHeight = (int) (f12 / f11);
        } else {
            f10 = this.f12019m;
            measuredWidth = (int) (f13 * f10);
        }
        if (!runnableC1802b.f19007m) {
            runnableC1802b.f19007m = true;
            ((AspectRatioFrameLayout) runnableC1802b.f19008n).post(runnableC1802b);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    public void setAspectRatio(float f10) {
        if (this.f12019m != f10) {
            this.f12019m = f10;
            requestLayout();
        }
    }

    public void setAspectRatioListener(InterfaceC1801a interfaceC1801a) {
    }

    public void setResizeMode(int i7) {
        if (this.f12020n != i7) {
            this.f12020n = i7;
            requestLayout();
        }
    }
}
