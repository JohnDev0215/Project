package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.view.IvTaiji;
import com.byd.vrassistant.view.IvWave;
import com.byd.vrassistant.view.ResTextView;
import com.byd.vrassistant.view.VrTextView;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewVoiceInteractBinding implements ViewBinding {
    public final ConstraintLayout constraintLayout;
    public final ConstraintLayout flContainer;
    public final FrameLayout flNetErr;
    public final ImageView ivClose;
    public final ImageView ivNotice;
    public final IvTaiji ivTaiji;
    public final IvWave ivTxtBg;
    private final ConstraintLayout rootView;
    public final VrTextView tvInput;
    public final ResTextView tvResponse;
    public final View vClick;
    public final View viewDivider;

    private ViewVoiceInteractBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, IvTaiji ivTaiji, IvWave ivWave, VrTextView vrTextView, ResTextView resTextView, View view, View view2) {
        this.rootView = constraintLayout;
        this.constraintLayout = constraintLayout2;
        this.flContainer = constraintLayout3;
        this.flNetErr = frameLayout;
        this.ivClose = imageView;
        this.ivNotice = imageView2;
        this.ivTaiji = ivTaiji;
        this.ivTxtBg = ivWave;
        this.tvInput = vrTextView;
        this.tvResponse = resTextView;
        this.vClick = view;
        this.viewDivider = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewVoiceInteractBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewVoiceInteractBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_voice_interact, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewVoiceInteractBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.fl_container;
        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
        if (constraintLayout2 != null) {
            i = R.id.fl_net_err;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.iv_close;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.iv_notice;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.iv_taiji;
                        IvTaiji ivTaiji = (IvTaiji) ViewBindings.findChildViewById(view, i);
                        if (ivTaiji != null) {
                            i = R.id.iv_txt_bg;
                            IvWave ivWave = (IvWave) ViewBindings.findChildViewById(view, i);
                            if (ivWave != null) {
                                i = R.id.tv_input;
                                VrTextView vrTextView = (VrTextView) ViewBindings.findChildViewById(view, i);
                                if (vrTextView != null) {
                                    i = R.id.tv_response;
                                    ResTextView resTextView = (ResTextView) ViewBindings.findChildViewById(view, i);
                                    if (resTextView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_click))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.view_divider))) != null) {
                                        return new ViewVoiceInteractBinding(constraintLayout, constraintLayout, constraintLayout2, frameLayout, imageView, imageView2, ivTaiji, ivWave, vrTextView, resTextView, viewFindChildViewById, viewFindChildViewById2);
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
