package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutDriverRegisterLockBinding implements ViewBinding {
    public final ImageView ivDriverWakeupLocked;
    public final ImageView ivFrontPassengerWakeupLocked;
    public final ImageView ivLockingBackground;
    private final ConstraintLayout rootView;
    public final AppTextView tvBtnNoRegister;
    public final AppTextView tvBtnYesRegister;
    public final AppTextView tvContentRegister;
    public final AppTextView tvDriverWakeupLocked;
    public final AppTextView tvDriverWakeupLockedTip;
    public final AppTextView tvFrontPassengerWakeupLocked;
    public final AppTextView tvFrontPassengerWakeupLockedTip;
    public final AppTextView tvTitleRegister;
    public final ConstraintLayout vDriverRegisterBack;
    public final ConstraintLayout vDriverRegisterLock;

    private LayoutDriverRegisterLockBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, AppTextView appTextView6, AppTextView appTextView7, AppTextView appTextView8, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3) {
        this.rootView = constraintLayout;
        this.ivDriverWakeupLocked = imageView;
        this.ivFrontPassengerWakeupLocked = imageView2;
        this.ivLockingBackground = imageView3;
        this.tvBtnNoRegister = appTextView;
        this.tvBtnYesRegister = appTextView2;
        this.tvContentRegister = appTextView3;
        this.tvDriverWakeupLocked = appTextView4;
        this.tvDriverWakeupLockedTip = appTextView5;
        this.tvFrontPassengerWakeupLocked = appTextView6;
        this.tvFrontPassengerWakeupLockedTip = appTextView7;
        this.tvTitleRegister = appTextView8;
        this.vDriverRegisterBack = constraintLayout2;
        this.vDriverRegisterLock = constraintLayout3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDriverRegisterLockBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutDriverRegisterLockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_driver_register_lock, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDriverRegisterLockBinding bind(View view) {
        int i = R.id.iv_driver_wakeup_locked;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_driver_wakeup_locked);
        if (imageView != null) {
            i = R.id.iv_front_passenger_wakeup_locked;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_front_passenger_wakeup_locked);
            if (imageView2 != null) {
                i = R.id.iv_locking_background;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_locking_background);
                if (imageView3 != null) {
                    i = R.id.tv_btn_no_register;
                    AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_no_register);
                    if (appTextView != null) {
                        i = R.id.tv_btn_yes_register;
                        AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_yes_register);
                        if (appTextView2 != null) {
                            i = R.id.tv_content_register;
                            AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_content_register);
                            if (appTextView3 != null) {
                                i = R.id.tv_driver_wakeup_locked;
                                AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_driver_wakeup_locked);
                                if (appTextView4 != null) {
                                    i = R.id.tv_driver_wakeup_locked_tip;
                                    AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_driver_wakeup_locked_tip);
                                    if (appTextView5 != null) {
                                        i = R.id.tv_front_passenger_wakeup_locked;
                                        AppTextView appTextView6 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_front_passenger_wakeup_locked);
                                        if (appTextView6 != null) {
                                            i = R.id.tv_front_passenger_wakeup_locked_tip;
                                            AppTextView appTextView7 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_front_passenger_wakeup_locked_tip);
                                            if (appTextView7 != null) {
                                                i = R.id.tv_title_register;
                                                AppTextView appTextView8 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_register);
                                                if (appTextView8 != null) {
                                                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                                    i = R.id.v_driver_register_lock;
                                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.v_driver_register_lock);
                                                    if (constraintLayout2 != null) {
                                                        return new LayoutDriverRegisterLockBinding(constraintLayout, imageView, imageView2, imageView3, appTextView, appTextView2, appTextView3, appTextView4, appTextView5, appTextView6, appTextView7, appTextView8, constraintLayout, constraintLayout2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
