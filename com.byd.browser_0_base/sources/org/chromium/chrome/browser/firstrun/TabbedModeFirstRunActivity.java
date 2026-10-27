package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class TabbedModeFirstRunActivity extends FirstRunActivity {

    public static class ContentLayout extends FrameLayout {
        public final TypedValue e;
        public final TypedValue f;
        public final TypedValue g;
        public final TypedValue h;

        public ContentLayout(Context context) {
            super(context);
            TypedValue typedValue = new TypedValue();
            this.e = typedValue;
            TypedValue typedValue2 = new TypedValue();
            this.f = typedValue2;
            TypedValue typedValue3 = new TypedValue();
            this.g = typedValue3;
            TypedValue typedValue4 = new TypedValue();
            this.h = typedValue4;
            a(R$dimen.dialog_fixed_width_minor, typedValue2);
            a(R$dimen.dialog_fixed_width_major, typedValue);
            a(R$dimen.dialog_fixed_height_minor, typedValue4);
            a(R$dimen.dialog_fixed_height_major, typedValue3);
        }

        public final void a(int i, TypedValue typedValue) {
            try {
                getContext().getResources().getValue(i, typedValue, true);
            } catch (Resources.NotFoundException unused) {
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        public final void onMeasure(int i, int i2) {
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            boolean z = displayMetrics.widthPixels < displayMetrics.heightPixels;
            View.MeasureSpec.getMode(i);
            TypedValue typedValue = z ? this.f : this.e;
            int size = View.MeasureSpec.getSize(i);
            if (typedValue.type != 0) {
                int i3 = displayMetrics.widthPixels;
                size = Math.min((int) typedValue.getFraction(i3, i3), size);
            }
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            View.MeasureSpec.getMode(i2);
            TypedValue typedValue2 = z ? this.g : this.h;
            int size2 = View.MeasureSpec.getSize(i2);
            if (typedValue2.type != 0) {
                int i4 = displayMetrics.heightPixels;
                size2 = Math.min((int) typedValue2.getFraction(i4, i4), size2);
            }
            super.onMeasure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(size2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivity
    public final View k2() {
        View viewK2 = super.k2();
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView(viewK2);
        FrameLayout frameLayout2 = new FrameLayout(this);
        frameLayout2.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1, 17));
        frameLayout2.setBackgroundResource(R$color.modal_dialog_scrim_color);
        return frameLayout2;
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivity, defpackage.jn0
    public final boolean r() {
        return false;
    }
}
