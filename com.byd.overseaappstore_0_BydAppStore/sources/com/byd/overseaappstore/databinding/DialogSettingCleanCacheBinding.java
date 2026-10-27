package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogSettingCleanCacheBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ConstraintLayout b;

    @NonNull
    public final SkinTextView c;

    @NonNull
    public final SkinTextView d;

    @NonNull
    public final SkinTextView e;

    @NonNull
    public final SkinTextView f;

    @NonNull
    public final SkinView g;

    @NonNull
    public final SkinView h;

    @NonNull
    public final SkinView i;

    @NonNull
    public final SkinView j;

    private DialogSettingCleanCacheBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinTextView skinTextView3, @NonNull SkinTextView skinTextView4, @NonNull SkinView skinView, @NonNull SkinView skinView2, @NonNull SkinView skinView3, @NonNull SkinView skinView4) {
        this.a = constraintLayout;
        this.b = constraintLayout2;
        this.c = skinTextView;
        this.d = skinTextView2;
        this.e = skinTextView3;
        this.f = skinTextView4;
        this.g = skinView;
        this.h = skinView2;
        this.i = skinView3;
        this.j = skinView4;
    }

    @NonNull
    public static DialogSettingCleanCacheBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_setting_clean_cache, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
        int i = R.id.tv_dialog_setting_cancel_button;
        SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_dialog_setting_cancel_button);
        if (skinTextView != null) {
            i = R.id.tv_dialog_setting_clean_button;
            SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_dialog_setting_clean_button);
            if (skinTextView2 != null) {
                i = R.id.tv_setting_dialog_content;
                SkinTextView skinTextView3 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_dialog_content);
                if (skinTextView3 != null) {
                    i = R.id.tv_setting_dialog_title;
                    SkinTextView skinTextView4 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_dialog_title);
                    if (skinTextView4 != null) {
                        i = R.id.v_dialog_setting_cancel_button;
                        SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_setting_cancel_button);
                        if (skinView != null) {
                            i = R.id.v_dialog_setting_clean_button;
                            SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_setting_clean_button);
                            if (skinView2 != null) {
                                i = R.id.v_HorizontalLine;
                                SkinView skinView3 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_HorizontalLine);
                                if (skinView3 != null) {
                                    i = R.id.v_setting_dialog;
                                    SkinView skinView4 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_dialog);
                                    if (skinView4 != null) {
                                        return new DialogSettingCleanCacheBinding(constraintLayout, constraintLayout, skinTextView, skinTextView2, skinTextView3, skinTextView4, skinView, skinView2, skinView3, skinView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(i)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
