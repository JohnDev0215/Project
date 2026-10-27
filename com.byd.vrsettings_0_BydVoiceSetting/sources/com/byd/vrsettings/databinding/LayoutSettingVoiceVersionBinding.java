package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutSettingVoiceVersionBinding implements ViewBinding {
    public final ConstraintLayout clVersion;
    public final ImageView ivSmartVoice;
    public final ImageView ivVersionBack;
    private final ConstraintLayout rootView;
    public final View settingVoiceLine;
    public final TextView tv;
    public final TextView tv3;
    public final TextView tv4;
    public final AppTextView tvBtnDisclaimer;
    public final AppTextView tvCopyright;
    public final TextView tvDownloadVersion;
    public final TextView tvSettingSDKVersion3;
    public final TextView tvSettingVersion;
    public final AppTextView tvSmartVoice;
    public final TextView tvSmartVoiceVersion;
    public final AppTextView tvVersionTitle;
    public final View vVersionBackground;
    public final View versionBackView;

    private LayoutSettingVoiceVersionBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, View view, TextView textView, TextView textView2, TextView textView3, AppTextView appTextView, AppTextView appTextView2, TextView textView4, TextView textView5, TextView textView6, AppTextView appTextView3, TextView textView7, AppTextView appTextView4, View view2, View view3) {
        this.rootView = constraintLayout;
        this.clVersion = constraintLayout2;
        this.ivSmartVoice = imageView;
        this.ivVersionBack = imageView2;
        this.settingVoiceLine = view;
        this.tv = textView;
        this.tv3 = textView2;
        this.tv4 = textView3;
        this.tvBtnDisclaimer = appTextView;
        this.tvCopyright = appTextView2;
        this.tvDownloadVersion = textView4;
        this.tvSettingSDKVersion3 = textView5;
        this.tvSettingVersion = textView6;
        this.tvSmartVoice = appTextView3;
        this.tvSmartVoiceVersion = textView7;
        this.tvVersionTitle = appTextView4;
        this.vVersionBackground = view2;
        this.versionBackView = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSettingVoiceVersionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSettingVoiceVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_setting_voice_version, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutSettingVoiceVersionBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.iv_smart_voice;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_smart_voice);
        if (imageView != null) {
            i = R.id.iv_version_back;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_version_back);
            if (imageView2 != null) {
                i = R.id.setting_voice_line;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.setting_voice_line);
                if (viewFindChildViewById != null) {
                    i = R.id.tv;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv);
                    if (textView != null) {
                        i = R.id.tv3;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv3);
                        if (textView2 != null) {
                            i = R.id.tv4;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv4);
                            if (textView3 != null) {
                                i = R.id.tv_btn_disclaimer;
                                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_disclaimer);
                                if (appTextView != null) {
                                    i = R.id.tv_copyright;
                                    AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_copyright);
                                    if (appTextView2 != null) {
                                        i = R.id.tv_download_version;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_download_version);
                                        if (textView4 != null) {
                                            i = R.id.tv_settingSDK_version3;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_settingSDK_version3);
                                            if (textView5 != null) {
                                                i = R.id.tv_setting_version;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_setting_version);
                                                if (textView6 != null) {
                                                    i = R.id.tv_smart_voice;
                                                    AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_smart_voice);
                                                    if (appTextView3 != null) {
                                                        i = R.id.tv_smart_voice_version;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_smart_voice_version);
                                                        if (textView7 != null) {
                                                            i = R.id.tv_version_title;
                                                            AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_version_title);
                                                            if (appTextView4 != null) {
                                                                i = R.id.v_versionBackground;
                                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_versionBackground);
                                                                if (viewFindChildViewById2 != null) {
                                                                    i = R.id.version_backView;
                                                                    View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.version_backView);
                                                                    if (viewFindChildViewById3 != null) {
                                                                        return new LayoutSettingVoiceVersionBinding(constraintLayout, constraintLayout, imageView, imageView2, viewFindChildViewById, textView, textView2, textView3, appTextView, appTextView2, textView4, textView5, textView6, appTextView3, textView7, appTextView4, viewFindChildViewById2, viewFindChildViewById3);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
