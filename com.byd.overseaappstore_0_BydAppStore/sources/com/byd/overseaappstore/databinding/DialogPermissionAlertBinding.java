package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogPermissionAlertBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinAppCompatTextView b;

    @NonNull
    public final SkinView c;

    @NonNull
    public final SkinAppCompatTextView d;

    @NonNull
    public final SkinAppCompatTextView e;

    @NonNull
    public final ConstraintLayout f;

    @NonNull
    public final SkinAppCompatTextView g;

    @NonNull
    public final SkinView h;

    @NonNull
    public final SkinView i;

    @NonNull
    public final SkinView j;

    private DialogPermissionAlertBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinView skinView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3, @NonNull ConstraintLayout constraintLayout2, @NonNull SkinAppCompatTextView skinAppCompatTextView4, @NonNull SkinView skinView2, @NonNull SkinView skinView3, @NonNull SkinView skinView4) {
        this.a = constraintLayout;
        this.b = skinAppCompatTextView;
        this.c = skinView;
        this.d = skinAppCompatTextView2;
        this.e = skinAppCompatTextView3;
        this.f = constraintLayout2;
        this.g = skinAppCompatTextView4;
        this.h = skinView2;
        this.i = skinView3;
        this.j = skinView4;
    }

    @NonNull
    public static DialogPermissionAlertBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_permission_alert, (ViewGroup) null, false);
        int i = R.id.content;
        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.content);
        if (skinAppCompatTextView != null) {
            i = R.id.dialog_background;
            SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.dialog_background);
            if (skinView != null) {
                i = R.id.negative;
                SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.negative);
                if (skinAppCompatTextView2 != null) {
                    i = R.id.positive;
                    SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.positive);
                    if (skinAppCompatTextView3 != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                        i = R.id.title;
                        SkinAppCompatTextView skinAppCompatTextView4 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.title);
                        if (skinAppCompatTextView4 != null) {
                            i = R.id.v_dialog_two_button_negative_button;
                            SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_two_button_negative_button);
                            if (skinView2 != null) {
                                i = R.id.v_dialog_two_button_positive_button;
                                SkinView skinView3 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_two_button_positive_button);
                                if (skinView3 != null) {
                                    i = R.id.v_horizontal_line;
                                    SkinView skinView4 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_horizontal_line);
                                    if (skinView4 != null) {
                                        return new DialogPermissionAlertBinding(constraintLayout, skinAppCompatTextView, skinView, skinAppCompatTextView2, skinAppCompatTextView3, constraintLayout, skinAppCompatTextView4, skinView2, skinView3, skinView4);
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
