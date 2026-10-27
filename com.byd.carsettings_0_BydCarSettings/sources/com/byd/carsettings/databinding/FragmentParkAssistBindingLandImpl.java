package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentParkAssistBindingLandImpl extends FragmentParkAssistBinding {

    @Nullable
    public static final ViewDataBinding.IncludedLayouts m = null;

    @Nullable
    public static final SparseIntArray n;
    public long o;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        n = sparseIntArray;
        sparseIntArray.put(R.id.out_container, 1);
        sparseIntArray.put(R.id.dynamic_container1, 2);
        sparseIntArray.put(R.id.tv_park_assist_func_entrance, 3);
        sparseIntArray.put(R.id.rc_park_assist_func_entrance, 4);
        sparseIntArray.put(R.id.dynamic_container2, 5);
        sparseIntArray.put(R.id.tv_park_assist_radar_monitor, 6);
        sparseIntArray.put(R.id.cl_park_assist_container, 7);
        sparseIntArray.put(R.id.tv_assist_title, 8);
        sparseIntArray.put(R.id.switch_recognition_intelligent_speed_control, 9);
        sparseIntArray.put(R.id.iv_assist, 10);
    }

    public FragmentParkAssistBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, m, n));
    }

    public void b(@Nullable BaseAdasViewModel baseAdasViewModel) {
        this.l = baseAdasViewModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.o = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.o != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.o = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (12 != i) {
            return false;
        }
        b((BaseAdasViewModel) obj);
        return true;
    }

    public FragmentParkAssistBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[5], (LottieAnimationView) objArr[10], (DampingScrollView) objArr[0], (ConstraintLayout) objArr[1], (RecyclerView) objArr[4], (BydSwitch) objArr[9], (TextView) objArr[8], (TextView) objArr[3], (TextView) objArr[6]);
        this.o = -1L;
        this.f2829e.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
