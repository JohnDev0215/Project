package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import defpackage.ei;
import defpackage.r83;
import defpackage.yn3;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends NavigationBarView {

    @Deprecated
    public interface a extends NavigationBarView.b {
    }

    @Deprecated
    public interface b extends NavigationBarView.c {
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public final NavigationBarMenuView a(Context context) {
        return new BottomNavigationMenuView(context);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 5;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        if (View.MeasureSpec.getMode(i2) != 1073741824 && suggestedMinimumHeight > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i2), getPaddingBottom() + getPaddingTop() + suggestedMinimumHeight), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i, i2);
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) getMenuView();
        if (bottomNavigationMenuView.M != z) {
            bottomNavigationMenuView.setItemHorizontalTranslationEnabled(z);
            getPresenter().d(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(a aVar) {
        setOnItemReselectedListener(aVar);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(b bVar) {
        setOnItemSelectedListener(bVar);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        int i2 = R$style.Widget_Design_BottomNavigationView;
        super(context, attributeSet, i, i2);
        TintTypedArray tintTypedArrayE = r83.e(getContext(), attributeSet, R$styleable.BottomNavigationView, i, i2, new int[0]);
        setItemHorizontalTranslationEnabled(tintTypedArrayE.a(R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int i3 = R$styleable.BottomNavigationView_android_minHeight;
        if (tintTypedArrayE.l(i3)) {
            setMinimumHeight(tintTypedArrayE.d(i3, 0));
        }
        tintTypedArrayE.n();
        yn3.a(this, new ei());
    }
}
