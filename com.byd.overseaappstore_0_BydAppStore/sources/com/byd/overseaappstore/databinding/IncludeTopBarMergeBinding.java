package com.byd.overseaappstore.databinding;

import android.view.View;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;

/* JADX INFO: loaded from: classes.dex */
public final class IncludeTopBarMergeBinding implements ViewBinding {

    @NonNull
    public final View a;

    @NonNull
    public final SkinImageView b;

    @NonNull
    public final SkinTextView c;

    private IncludeTopBarMergeBinding(@NonNull View view, @NonNull SkinImageView skinImageView, @NonNull Space space, @NonNull SkinTextView skinTextView) {
        this.a = view;
        this.b = skinImageView;
        this.c = skinTextView;
    }

    @NonNull
    public static IncludeTopBarMergeBinding a(@NonNull View view) {
        int i = R.id.iv_include_back;
        SkinImageView skinImageView = (SkinImageView) ViewBindings.findChildViewById(view, R.id.iv_include_back);
        if (skinImageView != null) {
            i = R.id.s_title_top_start;
            Space space = (Space) ViewBindings.findChildViewById(view, R.id.s_title_top_start);
            if (space != null) {
                i = R.id.tv_include_text;
                SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(view, R.id.tv_include_text);
                if (skinTextView != null) {
                    return new IncludeTopBarMergeBinding(view, skinImageView, space, skinTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
