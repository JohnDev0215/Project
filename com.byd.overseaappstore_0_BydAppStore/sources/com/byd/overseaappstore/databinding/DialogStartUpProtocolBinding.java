package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;

/* JADX INFO: loaded from: classes.dex */
public final class DialogStartUpProtocolBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final CheckBox b;

    @NonNull
    public final AppCompatTextView c;

    @NonNull
    public final AppCompatTextView d;

    @NonNull
    public final AppCompatTextView e;

    @NonNull
    public final ConstraintLayout f;

    @NonNull
    public final AppCompatTextView g;

    @NonNull
    public final View h;

    @NonNull
    public final View i;

    @NonNull
    public final View j;

    private DialogStartUpProtocolBinding(@NonNull ConstraintLayout constraintLayout, @NonNull CheckBox checkBox, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull NestedScrollView nestedScrollView, @NonNull AppCompatTextView appCompatTextView3, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatTextView appCompatTextView4, @NonNull View view, @NonNull View view2, @NonNull View view3, @NonNull View view4) {
        this.a = constraintLayout;
        this.b = checkBox;
        this.c = appCompatTextView;
        this.d = appCompatTextView2;
        this.e = appCompatTextView3;
        this.f = constraintLayout2;
        this.g = appCompatTextView4;
        this.h = view;
        this.i = view2;
        this.j = view4;
    }

    @NonNull
    public static DialogStartUpProtocolBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_start_up_protocol, (ViewGroup) null, false);
        int i = R.id.checkbox;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(viewInflate, R.id.checkbox);
        if (checkBox != null) {
            i = R.id.content;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.content);
            if (appCompatTextView != null) {
                i = R.id.negative;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.negative);
                if (appCompatTextView2 != null) {
                    i = R.id.nsv_content;
                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(viewInflate, R.id.nsv_content);
                    if (nestedScrollView != null) {
                        i = R.id.positive;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.positive);
                        if (appCompatTextView3 != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                            i = R.id.title;
                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.title);
                            if (appCompatTextView4 != null) {
                                i = R.id.v_dialog_protocol_confirm_button;
                                View viewFindChildViewById = ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_protocol_confirm_button);
                                if (viewFindChildViewById != null) {
                                    i = R.id.v_dialog_protocol_exit_button;
                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(viewInflate, R.id.v_dialog_protocol_exit_button);
                                    if (viewFindChildViewById2 != null) {
                                        i = R.id.v_horizontal_line;
                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(viewInflate, R.id.v_horizontal_line);
                                        if (viewFindChildViewById3 != null) {
                                            i = R.id.v_protocol_dialog;
                                            View viewFindChildViewById4 = ViewBindings.findChildViewById(viewInflate, R.id.v_protocol_dialog);
                                            if (viewFindChildViewById4 != null) {
                                                return new DialogStartUpProtocolBinding(constraintLayout, checkBox, appCompatTextView, appCompatTextView2, nestedScrollView, appCompatTextView3, constraintLayout, appCompatTextView4, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4);
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
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(i)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
