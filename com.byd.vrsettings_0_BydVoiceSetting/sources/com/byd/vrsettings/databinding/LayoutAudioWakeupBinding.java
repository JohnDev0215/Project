package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutAudioWakeupBinding implements ViewBinding {
    public final View audioWakeupLine;
    public final ConstraintLayout clAudioWakeup;
    private final ConstraintLayout rootView;
    public final AppTextView tvAudioWakeup;
    public final AppTextView tvBtnNoAudio;
    public final AppTextView tvBtnYesAudio;
    public final AppTextView tvTitleAudio;
    public final View vAudio;
    public final View vAudioBackground;

    private LayoutAudioWakeupBinding(ConstraintLayout constraintLayout, View view, ConstraintLayout constraintLayout2, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, View view2, View view3) {
        this.rootView = constraintLayout;
        this.audioWakeupLine = view;
        this.clAudioWakeup = constraintLayout2;
        this.tvAudioWakeup = appTextView;
        this.tvBtnNoAudio = appTextView2;
        this.tvBtnYesAudio = appTextView3;
        this.tvTitleAudio = appTextView4;
        this.vAudio = view2;
        this.vAudioBackground = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioWakeupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutAudioWakeupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_audio_wakeup, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioWakeupBinding bind(View view) {
        int i = R.id.audio_wakeup_line;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.audio_wakeup_line);
        if (viewFindChildViewById != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.tv_audio_wakeup;
            AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_audio_wakeup);
            if (appTextView != null) {
                i = R.id.tv_btn_no_audio;
                AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_no_audio);
                if (appTextView2 != null) {
                    i = R.id.tv_btn_yes_audio;
                    AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_yes_audio);
                    if (appTextView3 != null) {
                        i = R.id.tv_title_audio;
                        AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_audio);
                        if (appTextView4 != null) {
                            i = R.id.v_audio;
                            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_audio);
                            if (viewFindChildViewById2 != null) {
                                i = R.id.v_audioBackground;
                                View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_audioBackground);
                                if (viewFindChildViewById3 != null) {
                                    return new LayoutAudioWakeupBinding(constraintLayout, viewFindChildViewById, constraintLayout, appTextView, appTextView2, appTextView3, appTextView4, viewFindChildViewById2, viewFindChildViewById3);
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
