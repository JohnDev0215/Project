package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class WrapHeightNestedScrollView extends NestedScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3467a;

    public WrapHeightNestedScrollView(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        this.f3467a = context;
    }

    @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        try {
            i2 = View.MeasureSpec.makeMeasureSpec(this.f3467a.getResources().getDimensionPixelSize(R.dimen.dp_312), Integer.MIN_VALUE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onMeasure(i, i2);
    }

    public WrapHeightNestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public WrapHeightNestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }
}
