package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogInstallSuccessBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ImageView b;

    @NonNull
    public final ConstraintLayout c;

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

    @NonNull
    public final SkinView k;

    private DialogInstallSuccessBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull ConstraintLayout constraintLayout2, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinTextView skinTextView3, @NonNull SkinView skinView, @NonNull SkinView skinView2, @NonNull SkinView skinView3, @NonNull SkinView skinView4, @NonNull SkinView skinView5) {
        this.a = constraintLayout;
        this.b = imageView;
        this.c = constraintLayout2;
        this.d = skinTextView;
        this.e = skinTextView2;
        this.f = skinTextView3;
        this.g = skinView;
        this.h = skinView2;
        this.i = skinView3;
        this.j = skinView4;
        this.k = skinView5;
    }

    @NonNull
    public static DialogInstallSuccessBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_install_success, (ViewGroup) null, false);
        int i = R.id.iv_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_icon);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
            i = R.id.tv_dialog_content;
            SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_dialog_content);
            if (skinTextView != null) {
                i = R.id.tv_dialog_negative_button;
                SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_dialog_negative_button);
                if (skinTextView2 != null) {
                    i = R.id.tv_dialog_positive_button;
                    SkinTextView skinTextView3 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_dialog_positive_button);
                    if (skinTextView3 != null) {
                        i = R.id.v_dialog_negative_button;
                        SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_negative_button);
                        if (skinView != null) {
                            i = R.id.v_dialog_positive_button;
                            SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_positive_button);
                            if (skinView2 != null) {
                                i = R.id.v_horizontal_line;
                                SkinView skinView3 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_horizontal_line);
                                if (skinView3 != null) {
                                    i = R.id.v_install_success_dialog;
                                    SkinView skinView4 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_install_success_dialog);
                                    if (skinView4 != null) {
                                        i = R.id.v_vertical_line;
                                        SkinView skinView5 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_vertical_line);
                                        if (skinView5 != null) {
                                            return new DialogInstallSuccessBinding(constraintLayout, imageView, constraintLayout, skinTextView, skinTextView2, skinTextView3, skinView, skinView2, skinView3, skinView4, skinView5);
                                        }
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
