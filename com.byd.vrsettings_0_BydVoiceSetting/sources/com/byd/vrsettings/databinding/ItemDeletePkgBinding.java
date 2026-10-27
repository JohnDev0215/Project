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
public final class ItemDeletePkgBinding implements ViewBinding {
    public final Guideline guidelineData;
    public final Guideline guidelineLanguage;
    public final Guideline guidelineState;
    public final ImageView imageviewDelete;
    public final ImageView imageviewSelect;
    public final ConstraintLayout itemRoot;
    public final MultiFunProgressBar multifunProgressbar;
    private final ConstraintLayout rootView;
    public final AppTextView textviewCountry;
    public final AppTextView textviewLanguage;
    public final AppTextView textviewSizeData;
    public final AppTextView textviewSizeLabel;
    public final AppTextView textviewVersionData;
    public final AppTextView textviewVersionLabel;
    public final View viewLine;

    private ItemDeletePkgBinding(ConstraintLayout constraintLayout, Guideline guideline, Guideline guideline2, Guideline guideline3, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout2, MultiFunProgressBar multiFunProgressBar, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, AppTextView appTextView6, View view) {
        this.rootView = constraintLayout;
        this.guidelineData = guideline;
        this.guidelineLanguage = guideline2;
        this.guidelineState = guideline3;
        this.imageviewDelete = imageView;
        this.imageviewSelect = imageView2;
        this.itemRoot = constraintLayout2;
        this.multifunProgressbar = multiFunProgressBar;
        this.textviewCountry = appTextView;
        this.textviewLanguage = appTextView2;
        this.textviewSizeData = appTextView3;
        this.textviewSizeLabel = appTextView4;
        this.textviewVersionData = appTextView5;
        this.textviewVersionLabel = appTextView6;
        this.viewLine = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemDeletePkgBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDeletePkgBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_delete_pkg, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemDeletePkgBinding bind(View view) {
        int i = R.id.guideline_data;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_data);
        if (guideline != null) {
            i = R.id.guideline_language;
            Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_language);
            if (guideline2 != null) {
                i = R.id.guideline_state;
                Guideline guideline3 = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline_state);
                if (guideline3 != null) {
                    i = R.id.imageview_delete;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_delete);
                    if (imageView != null) {
                        i = R.id.imageview_select;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_select);
                        if (imageView2 != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) view;
                            i = R.id.multifun_progressbar;
                            MultiFunProgressBar multiFunProgressBar = (MultiFunProgressBar) ViewBindings.findChildViewById(view, R.id.multifun_progressbar);
                            if (multiFunProgressBar != null) {
                                i = R.id.textview_country;
                                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_country);
                                if (appTextView != null) {
                                    i = R.id.textview_language;
                                    AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_language);
                                    if (appTextView2 != null) {
                                        i = R.id.textview_size_data;
                                        AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_size_data);
                                        if (appTextView3 != null) {
                                            i = R.id.textview_size_label;
                                            AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_size_label);
                                            if (appTextView4 != null) {
                                                i = R.id.textview_version_data;
                                                AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_version_data);
                                                if (appTextView5 != null) {
                                                    i = R.id.textview_version_label;
                                                    AppTextView appTextView6 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_version_label);
                                                    if (appTextView6 != null) {
                                                        i = R.id.view_line;
                                                        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.view_line);
                                                        if (viewFindChildViewById != null) {
                                                            return new ItemDeletePkgBinding(constraintLayout, guideline, guideline2, guideline3, imageView, imageView2, constraintLayout, multiFunProgressBar, appTextView, appTextView2, appTextView3, appTextView4, appTextView5, appTextView6, viewFindChildViewById);
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
