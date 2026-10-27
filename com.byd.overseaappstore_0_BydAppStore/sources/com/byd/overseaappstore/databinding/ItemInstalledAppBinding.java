package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemInstalledAppBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ImageView b;

    @NonNull
    public final SkinAppCompatTextView c;

    @NonNull
    public final SkinAppCompatTextView d;

    @NonNull
    public final SkinAppCompatTextView e;

    @NonNull
    public final SkinAppCompatTextView f;

    private ItemInstalledAppBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3, @NonNull SkinAppCompatTextView skinAppCompatTextView4, @NonNull SkinView skinView, @NonNull SkinView skinView2) {
        this.a = constraintLayout;
        this.b = imageView;
        this.c = skinAppCompatTextView;
        this.d = skinAppCompatTextView2;
        this.e = skinAppCompatTextView3;
        this.f = skinAppCompatTextView4;
    }

    @NonNull
    public static ItemInstalledAppBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_installed_app, viewGroup, false);
        int i = R.id.iv_uninstall_app_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_uninstall_app_icon);
        if (imageView != null) {
            i = R.id.s_uninstall_app_bottom_end;
            Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_uninstall_app_bottom_end);
            if (space != null) {
                i = R.id.s_uninstall_app_panel;
                Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_uninstall_app_panel);
                if (space2 != null) {
                    i = R.id.s_uninstall_app_top_start;
                    Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_uninstall_app_top_start);
                    if (space3 != null) {
                        i = R.id.tv_uninstall_app_button;
                        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_uninstall_app_button);
                        if (skinAppCompatTextView != null) {
                            i = R.id.tv_uninstall_app_name;
                            SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_uninstall_app_name);
                            if (skinAppCompatTextView2 != null) {
                                i = R.id.tv_uninstall_app_size;
                                SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_uninstall_app_size);
                                if (skinAppCompatTextView3 != null) {
                                    i = R.id.tv_uninstall_app_theme;
                                    SkinAppCompatTextView skinAppCompatTextView4 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_uninstall_app_theme);
                                    if (skinAppCompatTextView4 != null) {
                                        i = R.id.v_uninstall_app_divider;
                                        SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_uninstall_app_divider);
                                        if (skinView != null) {
                                            i = R.id.v_uninstall_line;
                                            SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_uninstall_line);
                                            if (skinView2 != null) {
                                                return new ItemInstalledAppBinding((ConstraintLayout) viewInflate, imageView, space, space2, space3, skinAppCompatTextView, skinAppCompatTextView2, skinAppCompatTextView3, skinAppCompatTextView4, skinView, skinView2);
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
