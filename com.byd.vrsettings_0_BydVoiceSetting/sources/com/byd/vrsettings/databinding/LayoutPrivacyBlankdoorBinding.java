package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutPrivacyBlankdoorBinding implements ViewBinding {
    public final Button EsBtn;
    public final Button LanguageTestBtn;
    public final Button NoBtn;
    public final Button PtBtn;
    public final Button VRInfoBtn;
    public final AppTextView VRInfoText;
    public final View backgroundView;
    public final View blankdoorBackView;
    public final ConstraintLayout blankdoorBackViewLayout;
    public final ImageView blankdoorBtnX;
    public final Button deBtn;
    public final AppTextView languageBackView;
    public final ConstraintLayout languageBackView1;
    public final RecyclerView mRecyclerView;
    public final Button privacyLogBtn;
    private final ConstraintLayout rootView;
    public final Button soundBeginBtn;
    public final Button soundRecordingBtn;
    public final Button weatherBtn;
    public final Button weatherSwitch;

    private LayoutPrivacyBlankdoorBinding(ConstraintLayout constraintLayout, Button button, Button button2, Button button3, Button button4, Button button5, AppTextView appTextView, View view, View view2, ConstraintLayout constraintLayout2, ImageView imageView, Button button6, AppTextView appTextView2, ConstraintLayout constraintLayout3, RecyclerView recyclerView, Button button7, Button button8, Button button9, Button button10, Button button11) {
        this.rootView = constraintLayout;
        this.EsBtn = button;
        this.LanguageTestBtn = button2;
        this.NoBtn = button3;
        this.PtBtn = button4;
        this.VRInfoBtn = button5;
        this.VRInfoText = appTextView;
        this.backgroundView = view;
        this.blankdoorBackView = view2;
        this.blankdoorBackViewLayout = constraintLayout2;
        this.blankdoorBtnX = imageView;
        this.deBtn = button6;
        this.languageBackView = appTextView2;
        this.languageBackView1 = constraintLayout3;
        this.mRecyclerView = recyclerView;
        this.privacyLogBtn = button7;
        this.soundBeginBtn = button8;
        this.soundRecordingBtn = button9;
        this.weatherBtn = button10;
        this.weatherSwitch = button11;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutPrivacyBlankdoorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutPrivacyBlankdoorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_privacy_blankdoor, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutPrivacyBlankdoorBinding bind(View view) {
        int i = R.id.Es_btn;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.Es_btn);
        if (button != null) {
            i = R.id.Language_test_Btn;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.Language_test_Btn);
            if (button2 != null) {
                i = R.id.No_btn;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.No_btn);
                if (button3 != null) {
                    i = R.id.Pt_btn;
                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.Pt_btn);
                    if (button4 != null) {
                        i = R.id.VRInfoBtn;
                        Button button5 = (Button) ViewBindings.findChildViewById(view, R.id.VRInfoBtn);
                        if (button5 != null) {
                            i = R.id.VRInfoText;
                            AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.VRInfoText);
                            if (appTextView != null) {
                                i = R.id.background_view;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.background_view);
                                if (viewFindChildViewById != null) {
                                    i = R.id.blankdoorBackView;
                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.blankdoorBackView);
                                    if (viewFindChildViewById2 != null) {
                                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                        i = R.id.blankdoor_BtnX;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.blankdoor_BtnX);
                                        if (imageView != null) {
                                            i = R.id.de_btn;
                                            Button button6 = (Button) ViewBindings.findChildViewById(view, R.id.de_btn);
                                            if (button6 != null) {
                                                i = R.id.language_BackView;
                                                AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.language_BackView);
                                                if (appTextView2 != null) {
                                                    i = R.id.language_BackView1;
                                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.language_BackView1);
                                                    if (constraintLayout2 != null) {
                                                        i = R.id.mRecyclerView;
                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.mRecyclerView);
                                                        if (recyclerView != null) {
                                                            i = R.id.privacyLogBtn;
                                                            Button button7 = (Button) ViewBindings.findChildViewById(view, R.id.privacyLogBtn);
                                                            if (button7 != null) {
                                                                i = R.id.sound_begin_btn;
                                                                Button button8 = (Button) ViewBindings.findChildViewById(view, R.id.sound_begin_btn);
                                                                if (button8 != null) {
                                                                    i = R.id.soundRecordingBtn;
                                                                    Button button9 = (Button) ViewBindings.findChildViewById(view, R.id.soundRecordingBtn);
                                                                    if (button9 != null) {
                                                                        i = R.id.weatherBtn;
                                                                        Button button10 = (Button) ViewBindings.findChildViewById(view, R.id.weatherBtn);
                                                                        if (button10 != null) {
                                                                            i = R.id.weatherSwitch;
                                                                            Button button11 = (Button) ViewBindings.findChildViewById(view, R.id.weatherSwitch);
                                                                            if (button11 != null) {
                                                                                return new LayoutPrivacyBlankdoorBinding(constraintLayout, button, button2, button3, button4, button5, appTextView, viewFindChildViewById, viewFindChildViewById2, constraintLayout, imageView, button6, appTextView2, constraintLayout2, recyclerView, button7, button8, button9, button10, button11);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
