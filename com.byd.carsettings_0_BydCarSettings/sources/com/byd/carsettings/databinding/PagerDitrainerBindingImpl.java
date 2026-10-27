package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class PagerDitrainerBindingImpl extends PagerDitrainerBinding {

    @Nullable
    public static final ViewDataBinding.IncludedLayouts b0 = null;

    @Nullable
    public static final SparseIntArray c0;

    @NonNull
    public final ConstraintLayout d0;
    public long e0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        c0 = sparseIntArray;
        sparseIntArray.put(R.id.ll_carsetting_log_out_container, 2);
        sparseIntArray.put(R.id.tv_ditrainer, 3);
        sparseIntArray.put(R.id.iv_ditrainer_notice, 4);
        sparseIntArray.put(R.id.cl_new_intelligent_escort_function, 5);
        sparseIntArray.put(R.id.switch_new_intelligent_escort_function, 6);
        sparseIntArray.put(R.id.tv_new_intelligent_escort_function, 7);
        sparseIntArray.put(R.id.iv_new_intelligent_escort_function_notice, 8);
        sparseIntArray.put(R.id.cl_ditrainer_dialog_driving_level, 9);
        sparseIntArray.put(R.id.switch_ditrainer_dialog_driving_level, 10);
        sparseIntArray.put(R.id.tv_ditrainer_dialog_driving_level, 11);
        sparseIntArray.put(R.id.iv_ditrainer_dialog_driving_level_notice, 12);
        sparseIntArray.put(R.id.tv_ditrainer_dialog_driving_level_tips, 13);
        sparseIntArray.put(R.id.rl_driving_level, 14);
        sparseIntArray.put(R.id.iv_driving_level, 15);
        sparseIntArray.put(R.id.tv_driving_level, 16);
        sparseIntArray.put(R.id.iv_driving_level_more, 17);
        sparseIntArray.put(R.id.cl_new_intelligent_escort_function_open, 18);
        sparseIntArray.put(R.id.cl_ditrainer_cities_with_driving, 19);
        sparseIntArray.put(R.id.iv_ditrainer_cities_with_driving, 20);
        sparseIntArray.put(R.id.tv_ditrainer_cities_with_driving, 21);
        sparseIntArray.put(R.id.iv_ditrainer_cities_with_driving_tips, 22);
        sparseIntArray.put(R.id.switch_car_ahead_start_warning, 23);
        sparseIntArray.put(R.id.tv_car_ahead_start_warning, 24);
        sparseIntArray.put(R.id.switch_turn_signal_assist_warning, 25);
        sparseIntArray.put(R.id.tv_turn_signal_assist_warning, 26);
        sparseIntArray.put(R.id.switch_rain_fog_lamps_warning, 27);
        sparseIntArray.put(R.id.tv_rain_fog_lamps_warning, 28);
        sparseIntArray.put(R.id.view_divider, 29);
        sparseIntArray.put(R.id.cl_ditrainer_high_speed_escort, 30);
        sparseIntArray.put(R.id.iv_ditrainer_high_speed_escort, 31);
        sparseIntArray.put(R.id.tv_ditrainer_high_speed_escort, 32);
        sparseIntArray.put(R.id.iv_ditrainer_high_speed_escort_tips, 33);
        sparseIntArray.put(R.id.switch_safety_departure_warning, 34);
        sparseIntArray.put(R.id.tv_safety_departure_warning, 35);
        sparseIntArray.put(R.id.switch_ditrainer_high_speed_window, 36);
        sparseIntArray.put(R.id.tv_ditrainer_high_speed_window, 37);
        sparseIntArray.put(R.id.view_divider1, 38);
        sparseIntArray.put(R.id.cl_ditrainer_car_care, 39);
        sparseIntArray.put(R.id.iv_ditrainer_car_care, 40);
        sparseIntArray.put(R.id.tv_ditrainer_car_care, 41);
        sparseIntArray.put(R.id.iv_ditrainer_car_care_tips, 42);
        sparseIntArray.put(R.id.switch_fault_hand_warning, 43);
        sparseIntArray.put(R.id.tv_fault_hand_warning, 44);
        sparseIntArray.put(R.id.switch_charge_cover_warning, 45);
        sparseIntArray.put(R.id.tv_charge_cover_warning, 46);
        sparseIntArray.put(R.id.tv_notice_mode, 47);
        sparseIntArray.put(R.id.sbg_notice_mode, 48);
        sparseIntArray.put(R.id.tv_custom_function_combinations, 49);
        sparseIntArray.put(R.id.iv_custom_function_combinations_tips, 50);
        sparseIntArray.put(R.id.sbg_custom_function_combinations, 51);
        sparseIntArray.put(R.id.btn_reset, 52);
    }

    public PagerDitrainerBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 53, b0, c0));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.e0 = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.e0 != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.e0 = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public PagerDitrainerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[52], (ConstraintLayout) objArr[39], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[30], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[18], (ImageView) objArr[50], (ImageView) objArr[40], (ImageView) objArr[42], (ImageView) objArr[20], (ImageView) objArr[22], (ImageView) objArr[12], (ImageView) objArr[31], (ImageView) objArr[33], (ImageView) objArr[4], (ImageView) objArr[15], (ImageView) objArr[17], (ImageView) objArr[8], (View) objArr[2], (DampingScrollView) objArr[0], (RelativeLayout) objArr[14], (SegmentedButtonGroup) objArr[51], (SegmentedButtonGroup) objArr[48], (BydSwitch) objArr[23], (BydSwitch) objArr[45], (BydSwitch) objArr[10], (BydSwitch) objArr[36], (BydSwitch) objArr[43], (BydSwitch) objArr[6], (BydSwitch) objArr[27], (BydSwitch) objArr[34], (BydSwitch) objArr[25], (TextView) objArr[24], (TextView) objArr[46], (TextView) objArr[49], (TextView) objArr[3], (TextView) objArr[41], (TextView) objArr[21], (TextView) objArr[11], (TextView) objArr[13], (TextView) objArr[32], (TextView) objArr[37], (TextView) objArr[16], (TextView) objArr[44], (TextView) objArr[7], (TextView) objArr[47], (TextView) objArr[28], (TextView) objArr[35], (TextView) objArr[26], (View) objArr[29], (View) objArr[38]);
        this.e0 = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[1];
        this.d0 = constraintLayout;
        constraintLayout.setTag(null);
        this.u.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
