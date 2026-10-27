package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.carsettings.R;
import com.byd.dipilot.viewmodel.AdasViewModel;
import com.byd.dipilot.widget.InterceptTouchRelativeLayout;
import com.bydux.api.common.views.window.DampingScrollView;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentDipilotBindingImpl extends FragmentDipilotBinding {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    public static final ViewDataBinding.IncludedLayouts f2813f = null;

    @Nullable
    public static final SparseIntArray g;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        g = sparseIntArray;
        sparseIntArray.put(R.id.adas_damping_horizontal_scrollview, 1);
        sparseIntArray.put(R.id.adas_car_tablayout, 2);
        sparseIntArray.put(R.id.adas_car_viewpager, 3);
    }

    public FragmentDipilotBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, f2813f, g));
    }

    @Override // com.byd.carsettings.databinding.FragmentDipilotBinding
    public void c(@Nullable AdasViewModel adasViewModel) {
        this.f2812e = adasViewModel;
        synchronized (this) {
            this.h |= 2;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    public final boolean d(ObservableBoolean observableBoolean, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.h |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.h;
            this.h = 0L;
        }
        float f2 = 0.0f;
        AdasViewModel adasViewModel = this.f2812e;
        long j2 = j & 7;
        if (j2 != 0) {
            ObservableBoolean observableBooleanA = adasViewModel != null ? adasViewModel.a() : null;
            updateRegistration(0, observableBooleanA);
            z = observableBooleanA != null ? observableBooleanA.get() : false;
            if (j2 != 0) {
                j |= z ? 16L : 8L;
            }
            f2 = z ? 0.45f : 1.0f;
        }
        if ((j & 7) != 0) {
            if (ViewDataBinding.getBuildSdkInt() >= 11) {
                this.f2811d.setAlpha(f2);
            }
            this.f2811d.setInterceptTouch(z);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return d((ObservableBoolean) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (12 != i) {
            return false;
        }
        c((AdasViewModel) obj);
        return true;
    }

    public FragmentDipilotBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (TabLayout) objArr[2], (ViewPager2) objArr[3], (DampingScrollView) objArr[1], (InterceptTouchRelativeLayout) objArr[0]);
        this.h = -1L;
        this.f2811d.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
