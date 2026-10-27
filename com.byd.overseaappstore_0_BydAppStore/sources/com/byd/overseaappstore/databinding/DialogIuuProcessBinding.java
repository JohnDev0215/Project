package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogIuuProcessBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinTextView b;

    @NonNull
    public final SkinView c;

    private DialogIuuProcessBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinProgressBar skinProgressBar, @NonNull ConstraintLayout constraintLayout2, @NonNull SkinTextView skinTextView, @NonNull SkinView skinView) {
        this.a = constraintLayout;
        this.b = skinTextView;
        this.c = skinView;
    }

    @NonNull
    public static DialogIuuProcessBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_iuu_process, (ViewGroup) null, false);
        int i = R.id.pb_loading_bar;
        SkinProgressBar skinProgressBar = (SkinProgressBar) ViewBindings.findChildViewById(viewInflate, R.id.pb_loading_bar);
        if (skinProgressBar != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
            i = R.id.tv_process_content;
            SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_process_content);
            if (skinTextView != null) {
                i = R.id.v_iuu_success_dialog;
                SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_iuu_success_dialog);
                if (skinView != null) {
                    return new DialogIuuProcessBinding(constraintLayout, skinProgressBar, constraintLayout, skinTextView, skinView);
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
