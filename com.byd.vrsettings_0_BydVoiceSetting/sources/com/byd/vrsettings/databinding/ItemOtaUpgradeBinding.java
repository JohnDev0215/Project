package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;
import com.byd.vrsettings.view.MultiFunProgressBar;

/* JADX INFO: loaded from: classes.dex */
public final class ItemOtaUpgradeBinding implements ViewBinding {
    public final ConstraintLayout clVersion;
    public final Guideline guidelineData;
    public final Guideline guidelineLanguage;
    public final Guideline guidelineState;
    public final Guideline guidelineVersion;
    public final ImageView imageviewDelete;
    public final ImageView imageviewSelect;
    public final ConstraintLayout itemRoot;
    public final MultiFunProgressBar multifunProgressbar;
    private final ConstraintLayout rootView;
    public final AppTextView textviewCountry;
    public final AppTextView textviewLanguage;
    public final AppTextView textviewLocalVersionAndSize;
    public final ImageView textviewUpdateLabel;
    public final AppTextView textviewUpdateVersionAndSize;
    public final AppTextView viewDesc;
    public final View viewHotClick;
    public final View viewLine;
    public final View viewUpdatePoint;

    private ItemOtaUpgradeBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout3, MultiFunProgressBar multiFunProgressBar, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, ImageView imageView3, AppTextView appTextView4, AppTextView appTextView5, View view, View view2, View view3) {
        this.rootView = constraintLayout;
        this.clVersion = constraintLayout2;
        this.guidelineData = guideline;
        this.guidelineLanguage = guideline2;
        this.guidelineState = guideline3;
        this.guidelineVersion = guideline4;
        this.imageviewDelete = imageView;
        this.imageviewSelect = imageView2;
        this.itemRoot = constraintLayout3;
        this.multifunProgressbar = multiFunProgressBar;
        this.textviewCountry = appTextView;
        this.textviewLanguage = appTextView2;
        this.textviewLocalVersionAndSize = appTextView3;
        this.textviewUpdateLabel = imageView3;
        this.textviewUpdateVersionAndSize = appTextView4;
        this.viewDesc = appTextView5;
        this.viewHotClick = view;
        this.viewLine = view2;
        this.viewUpdatePoint = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemOtaUpgradeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemOtaUpgradeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_ota_upgrade, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemOtaUpgradeBinding bind(View view) {
        int i = R.id.cl_version;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_version);
        if (constraintLayout != null) {
            i = R.id.guideline_data;
            Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_data);
            if (guideline != null) {
                i = R.id.guideline_language;
                Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_language);
                if (guideline2 != null) {
                    i = R.id.guideline_state;
                    Guideline guideline3 = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_state);
                    if (guideline3 != null) {
                        i = R.id.guideline_version;
                        Guideline guideline4 = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_version);
                        if (guideline4 != null) {
                            i = R.id.imageview_delete;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_delete);
                            if (imageView != null) {
                                i = R.id.imageview_select;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_select);
                                if (imageView2 != null) {
                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
                                    i = R.id.multifun_progressbar;
                                    MultiFunProgressBar multiFunProgressBar = (MultiFunProgressBar) ViewBindings.findChildViewById(view, R.id.multifun_progressbar);
                                    if (multiFunProgressBar != null) {
                                        i = R.id.textview_country;
                                        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_country);
                                        if (appTextView != null) {
                                            i = R.id.textview_language;
                                            AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_language);
                                            if (appTextView2 != null) {
                                                i = R.id.textview_local_version_and_size;
                                                AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_local_version_and_size);
                                                if (appTextView3 != null) {
                                                    i = R.id.textview_update_label;
                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.textview_update_label);
                                                    if (imageView3 != null) {
                                                        i = R.id.textview_update_version_and_size;
                                                        AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_update_version_and_size);
                                                        if (appTextView4 != null) {
                                                            i = R.id.view_desc;
                                                            AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.view_desc);
                                                            if (appTextView5 != null) {
                                                                i = R.id.view_hot_click;
                                                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.view_hot_click);
                                                                if (viewFindChildViewById != null) {
                                                                    i = R.id.view_line;
                                                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.view_line);
                                                                    if (viewFindChildViewById2 != null) {
                                                                        i = R.id.view_update_point;
                                                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.view_update_point);
                                                                        if (viewFindChildViewById3 != null) {
                                                                            return new ItemOtaUpgradeBinding(constraintLayout2, constraintLayout, guideline, guideline2, guideline3, guideline4, imageView, imageView2, constraintLayout2, multiFunProgressBar, appTextView, appTextView2, appTextView3, imageView3, appTextView4, appTextView5, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3);
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
