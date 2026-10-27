package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentSettingBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinImageView b;

    @NonNull
    public final SkinImageView c;

    @NonNull
    public final ConstraintLayout d;

    @NonNull
    public final Space e;

    @NonNull
    public final Space f;

    @NonNull
    public final SkinTextView g;

    @NonNull
    public final SkinTextView h;

    @NonNull
    public final SkinView i;

    @Nullable
    public final SkinView j;

    @NonNull
    public final SkinView k;

    private FragmentSettingBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinImageView skinImageView, @NonNull SkinImageView skinImageView2, @NonNull SkinImageView skinImageView3, @NonNull SkinImageView skinImageView4, @NonNull SkinImageView skinImageView5, @Nullable SkinImageView skinImageView6, @NonNull ConstraintLayout constraintLayout2, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull Space space4, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinTextView skinTextView3, @NonNull SkinTextView skinTextView4, @NonNull SkinTextView skinTextView5, @NonNull SkinTextView skinTextView6, @NonNull SkinTextView skinTextView7, @NonNull SkinTextView skinTextView8, @NonNull SkinView skinView, @NonNull SkinView skinView2, @Nullable SkinView skinView3, @NonNull SkinView skinView4) {
        this.a = constraintLayout;
        this.b = skinImageView2;
        this.c = skinImageView3;
        this.d = constraintLayout2;
        this.e = space;
        this.f = space2;
        this.g = skinTextView;
        this.h = skinTextView4;
        this.i = skinView2;
        this.j = skinView3;
        this.k = skinView4;
    }

    @NonNull
    public static FragmentSettingBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_setting, (ViewGroup) null, false);
        int i = R.id.im_setting_check_update;
        SkinImageView skinImageView = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.im_setting_check_update);
        if (skinImageView != null) {
            i = R.id.iv_setting_app_icon;
            SkinImageView skinImageView2 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_setting_app_icon);
            if (skinImageView2 != null) {
                i = R.id.iv_setting_button_only_wifi;
                SkinImageView skinImageView3 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_setting_button_only_wifi);
                if (skinImageView3 != null) {
                    i = R.id.iv_setting_clear_cache;
                    SkinImageView skinImageView4 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_setting_clear_cache);
                    if (skinImageView4 != null) {
                        i = R.id.iv_setting_double;
                        SkinImageView skinImageView5 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_setting_double);
                        if (skinImageView5 != null) {
                            SkinImageView skinImageView6 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_setting_only_wifi);
                            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                            i = R.id.s_anchor_bottom_end;
                            Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_bottom_end);
                            if (space != null) {
                                i = R.id.s_anchor_top_start;
                                Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_start);
                                if (space2 != null) {
                                    i = R.id.s_bottom_state_bar;
                                    Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_bottom_state_bar);
                                    if (space3 != null) {
                                        i = R.id.s_top_state_bar;
                                        Space space4 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_top_state_bar);
                                        if (space4 != null) {
                                            i = R.id.tv_cache_size;
                                            SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_cache_size);
                                            if (skinTextView != null) {
                                                i = R.id.tv_setting_app_name;
                                                SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_app_name);
                                                if (skinTextView2 != null) {
                                                    i = R.id.tv_setting_app_power;
                                                    SkinTextView skinTextView3 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_app_power);
                                                    if (skinTextView3 != null) {
                                                        i = R.id.tv_setting_app_version;
                                                        SkinTextView skinTextView4 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_app_version);
                                                        if (skinTextView4 != null) {
                                                            i = R.id.tv_setting_check_update;
                                                            SkinTextView skinTextView5 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_check_update);
                                                            if (skinTextView5 != null) {
                                                                i = R.id.tv_setting_clear_cache;
                                                                SkinTextView skinTextView6 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_clear_cache);
                                                                if (skinTextView6 != null) {
                                                                    i = R.id.tv_setting_clear_cache2;
                                                                    SkinTextView skinTextView7 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_clear_cache2);
                                                                    if (skinTextView7 != null) {
                                                                        i = R.id.tv_setting_only_wifi;
                                                                        SkinTextView skinTextView8 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_only_wifi);
                                                                        if (skinTextView8 != null) {
                                                                            i = R.id.v_setting_background;
                                                                            SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_background);
                                                                            if (skinView != null) {
                                                                                i = R.id.v_setting_clear_cache;
                                                                                SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_clear_cache);
                                                                                if (skinView2 != null) {
                                                                                    SkinView skinView3 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_only_wifi);
                                                                                    i = R.id.v_setting_protocol;
                                                                                    SkinView skinView4 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_protocol);
                                                                                    if (skinView4 != null) {
                                                                                        return new FragmentSettingBinding(constraintLayout, skinImageView, skinImageView2, skinImageView3, skinImageView4, skinImageView5, skinImageView6, constraintLayout, space, space2, space3, space4, skinTextView, skinTextView2, skinTextView3, skinTextView4, skinTextView5, skinTextView6, skinTextView7, skinTextView8, skinView, skinView2, skinView3, skinView4);
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
