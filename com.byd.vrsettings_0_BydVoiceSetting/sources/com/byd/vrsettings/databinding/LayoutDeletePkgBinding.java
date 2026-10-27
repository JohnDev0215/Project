package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutDeletePkgBinding implements ViewBinding {
    public final Group groupOther;
    public final Group groupProgress;
    public final Guideline guideline;
    public final ImageView imageviewBack;
    public final ImageView imageviewIcon;
    public final ImageView loadingIcon;
    public final ProgressBar progressLoading;
    public final RecyclerView recyclerview;
    private final ConstraintLayout rootView;
    public final AppTextView textviewLocalData;
    public final TextView textviewVersion;
    public final AppTextView textviewVoice;
    public final AppTextView tvJson;
    public final AppTextView tvLoading;
    public final View vPlaceTop;
    public final View viewLine;
    public final ConstraintLayout viewOtaRoot;

    private LayoutDeletePkgBinding(ConstraintLayout constraintLayout, Group group, Group group2, Guideline guideline, ImageView imageView, ImageView imageView2, ImageView imageView3, ProgressBar progressBar, RecyclerView recyclerView, AppTextView appTextView, TextView textView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, View view, View view2, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.groupOther = group;
        this.groupProgress = group2;
        this.guideline = guideline;
        this.imageviewBack = imageView;
        this.imageviewIcon = imageView2;
        this.loadingIcon = imageView3;
        this.progressLoading = progressBar;
        this.recyclerview = recyclerView;
        this.textviewLocalData = appTextView;
        this.textviewVersion = textView;
        this.textviewVoice = appTextView2;
        this.tvJson = appTextView3;
        this.tvLoading = appTextView4;
        this.vPlaceTop = view;
        this.viewLine = view2;
        this.viewOtaRoot = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDeletePkgBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutDeletePkgBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_delete_pkg, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDeletePkgBinding bind(View view) {
        int i = R.id.group_other;
        Group group = (Group) ViewBindings.findChildViewById(view, R.id.group_other);
        if (group != null) {
            i = R.id.group_progress;
            Group group2 = (Group) ViewBindings.findChildViewById(view, R.id.group_progress);
            if (group2 != null) {
                i = R.id.guideline;
                Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline);
                if (guideline != null) {
                    i = R.id.imageview_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_back);
                    if (imageView != null) {
                        i = R.id.imageview_icon;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_icon);
                        if (imageView2 != null) {
                            i = R.id.loading_icon;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.loading_icon);
                            if (imageView3 != null) {
                                i = R.id.progress_loading;
                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progress_loading);
                                if (progressBar != null) {
                                    i = R.id.recyclerview;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerview);
                                    if (recyclerView != null) {
                                        i = R.id.textview_local_data;
                                        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_local_data);
                                        if (appTextView != null) {
                                            i = R.id.textview_version;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textview_version);
                                            if (textView != null) {
                                                i = R.id.textview_voice;
                                                AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_voice);
                                                if (appTextView2 != null) {
                                                    i = R.id.tv_json;
                                                    AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_json);
                                                    if (appTextView3 != null) {
                                                        i = R.id.tv_loading;
                                                        AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_loading);
                                                        if (appTextView4 != null) {
                                                            i = R.id.v_place_top;
                                                            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_place_top);
                                                            if (viewFindChildViewById != null) {
                                                                i = R.id.view_line;
                                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.view_line);
                                                                if (viewFindChildViewById2 != null) {
                                                                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                                                    return new LayoutDeletePkgBinding(constraintLayout, group, group2, guideline, imageView, imageView2, imageView3, progressBar, recyclerView, appTextView, textView, appTextView2, appTextView3, appTextView4, viewFindChildViewById, viewFindChildViewById2, constraintLayout);
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
