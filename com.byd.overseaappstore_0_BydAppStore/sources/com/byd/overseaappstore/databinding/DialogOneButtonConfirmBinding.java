package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogOneButtonConfirmBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinTextView b;

    @NonNull
    public final SkinTextView c;

    @NonNull
    public final SkinView d;

    @NonNull
    public final SkinView e;

    private DialogOneButtonConfirmBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull Space space, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinView skinView, @NonNull SkinView skinView2) {
        this.a = constraintLayout;
        this.b = skinTextView;
        this.c = skinTextView2;
        this.d = skinView;
        this.e = skinView2;
    }

    @NonNull
    public static DialogOneButtonConfirmBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_one_button_confirm, (ViewGroup) null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
        int i = R.id.s_dialog_one_button_confirm_bottom_space;
        Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_dialog_one_button_confirm_bottom_space);
        if (space != null) {
            i = R.id.title;
            SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.title);
            if (skinTextView != null) {
                i = R.id.tv_dialog_one_button;
                SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_dialog_one_button);
                if (skinTextView2 != null) {
                    i = R.id.v_dialog_one_button;
                    SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_one_button);
                    if (skinView != null) {
                        i = R.id.v_setting_dialog;
                        SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_dialog);
                        if (skinView2 != null) {
                            return new DialogOneButtonConfirmBinding(constraintLayout, constraintLayout, space, skinTextView, skinTextView2, skinView, skinView2);
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
