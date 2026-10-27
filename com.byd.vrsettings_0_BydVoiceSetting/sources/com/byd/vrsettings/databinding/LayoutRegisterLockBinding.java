package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutRegisterLockBinding implements ViewBinding {
    public final ConstraintLayout clRegisterLock;
    public final Button ivFrontLeft;
    public final Button ivFrontRight;
    public final Button ivRearLeft;
    public final Button ivRearRight;
    public final ImageView ivRegisterLock;
    public final ConstraintLayout registerLockBackground;
    public final View registerLockLine;
    private final ConstraintLayout rootView;
    public final AppTextView tvBtnYes;
    public final AppTextView tvFourRegisterLocked;
    public final AppTextView tvTitle;
    public final View vBackground;

    private LayoutRegisterLockBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Button button, Button button2, Button button3, Button button4, ImageView imageView, ConstraintLayout constraintLayout3, View view, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, View view2) {
        this.rootView = constraintLayout;
        this.clRegisterLock = constraintLayout2;
        this.ivFrontLeft = button;
        this.ivFrontRight = button2;
        this.ivRearLeft = button3;
        this.ivRearRight = button4;
        this.ivRegisterLock = imageView;
        this.registerLockBackground = constraintLayout3;
        this.registerLockLine = view;
        this.tvBtnYes = appTextView;
        this.tvFourRegisterLocked = appTextView2;
        this.tvTitle = appTextView3;
        this.vBackground = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutRegisterLockBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutRegisterLockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_register_lock, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutRegisterLockBinding bind(View view) {
        int i = R.id.cl_register_lock;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_register_lock);
        if (constraintLayout != null) {
            i = R.id.iv_front_left;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.iv_front_left);
            if (button != null) {
                i = R.id.iv_front_right;
                Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.iv_front_right);
                if (button2 != null) {
                    i = R.id.iv_rear_left;
                    Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.iv_rear_left);
                    if (button3 != null) {
                        i = R.id.iv_rear_right;
                        Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.iv_rear_right);
                        if (button4 != null) {
                            i = R.id.iv_register_lock;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_register_lock);
                            if (imageView != null) {
                                ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                                i = R.id.register_lock_line;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.register_lock_line);
                                if (viewFindChildViewById != null) {
                                    i = R.id.tv_btn_yes;
                                    AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_yes);
                                    if (appTextView != null) {
                                        i = R.id.tv_four_register_locked;
                                        AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_four_register_locked);
                                        if (appTextView2 != null) {
                                            i = R.id.tv_title;
                                            AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (appTextView3 != null) {
                                                i = R.id.v_background;
                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_background);
                                                if (viewFindChildViewById2 != null) {
                                                    return new LayoutRegisterLockBinding(constraintLayout2, constraintLayout, button, button2, button3, button4, imageView, constraintLayout2, viewFindChildViewById, appTextView, appTextView2, appTextView3, viewFindChildViewById2);
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
