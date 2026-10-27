package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class CustomVoiceSkillsPopupViewBinding implements ViewBinding {
    public final ConstraintLayout clCustomVoiceSkillsPopupView;
    public final ViewPager customViewpager;
    public final ImageView ivLeftBtn;
    public final ImageView ivRightBtn;
    public final ImageView ivSkills;
    private final ConstraintLayout rootView;
    public final TextView tvSkills;

    private CustomVoiceSkillsPopupViewBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ViewPager viewPager, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView) {
        this.rootView = constraintLayout;
        this.clCustomVoiceSkillsPopupView = constraintLayout2;
        this.customViewpager = viewPager;
        this.ivLeftBtn = imageView;
        this.ivRightBtn = imageView2;
        this.ivSkills = imageView3;
        this.tvSkills = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static CustomVoiceSkillsPopupViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomVoiceSkillsPopupViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_voice_skills_popup_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomVoiceSkillsPopupViewBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.custom_viewpager;
        ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.custom_viewpager);
        if (viewPager != null) {
            i = R.id.iv_left_btn;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_left_btn);
            if (imageView != null) {
                i = R.id.iv_right_btn;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_right_btn);
                if (imageView2 != null) {
                    i = R.id.iv_skills;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_skills);
                    if (imageView3 != null) {
                        i = R.id.tv_skills;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_skills);
                        if (textView != null) {
                            return new CustomVoiceSkillsPopupViewBinding(constraintLayout, constraintLayout, viewPager, imageView, imageView2, imageView3, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
