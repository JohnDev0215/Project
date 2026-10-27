package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinCheckBox;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentProtocolBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinCheckBox b;

    @NonNull
    public final Group c;

    @NonNull
    public final Group d;

    @NonNull
    public final Group e;

    @NonNull
    public final NestedScrollView f;

    @NonNull
    public final Space g;

    @NonNull
    public final SkinTextView h;

    @NonNull
    public final SkinView i;

    @NonNull
    public final SkinView j;

    @NonNull
    public final SkinView k;

    @NonNull
    public final WebView l;

    private FragmentProtocolBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Barrier barrier, @NonNull Barrier barrier2, @NonNull Barrier barrier3, @NonNull SkinCheckBox skinCheckBox, @NonNull Group group, @NonNull Group group2, @NonNull Group group3, @NonNull NestedScrollView nestedScrollView, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull androidx.legacy.widget.Space space4, @NonNull Space space5, @NonNull androidx.legacy.widget.Space space6, @NonNull Space space7, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinTextView skinTextView3, @NonNull SkinView skinView, @NonNull SkinView skinView2, @NonNull SkinView skinView3, @NonNull SkinView skinView4, @NonNull SkinView skinView5, @NonNull WebView webView) {
        this.a = constraintLayout;
        this.b = skinCheckBox;
        this.c = group;
        this.d = group2;
        this.e = group3;
        this.f = nestedScrollView;
        this.g = space;
        this.h = skinTextView2;
        this.i = skinView2;
        this.j = skinView3;
        this.k = skinView5;
        this.l = webView;
    }

    @NonNull
    public static FragmentProtocolBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_protocol, (ViewGroup) null, false);
        int i = R.id.b_buttons_top_barrier;
        Barrier barrier = (Barrier) ViewBindings.findChildViewById(viewInflate, R.id.b_buttons_top_barrier);
        if (barrier != null) {
            i = R.id.b_setting_fragment_reset_button_end;
            Barrier barrier2 = (Barrier) ViewBindings.findChildViewById(viewInflate, R.id.b_setting_fragment_reset_button_end);
            if (barrier2 != null) {
                i = R.id.b_setting_fragment_reset_button_start;
                Barrier barrier3 = (Barrier) ViewBindings.findChildViewById(viewInflate, R.id.b_setting_fragment_reset_button_start);
                if (barrier3 != null) {
                    i = R.id.cb_checkbox;
                    SkinCheckBox skinCheckBox = (SkinCheckBox) ViewBindings.findChildViewById(viewInflate, R.id.cb_checkbox);
                    if (skinCheckBox != null) {
                        i = R.id.g_checkbox;
                        Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_checkbox);
                        if (group != null) {
                            i = R.id.g_setting_fragment_reset_button;
                            Group group2 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_setting_fragment_reset_button);
                            if (group2 != null) {
                                i = R.id.g_start_up_fragment_buttons;
                                Group group3 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_start_up_fragment_buttons);
                                if (group3 != null) {
                                    i = R.id.nsv_string;
                                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(viewInflate, R.id.nsv_string);
                                    if (nestedScrollView != null) {
                                        i = R.id.s_between_two_button;
                                        Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_between_two_button);
                                        if (space != null) {
                                            i = R.id.s_bottom_state_bar;
                                            Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_bottom_state_bar);
                                            if (space2 != null) {
                                                i = R.id.s_button_top_space;
                                                Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_button_top_space);
                                                if (space3 != null) {
                                                    i = R.id.s_checkbox_top_space;
                                                    androidx.legacy.widget.Space space4 = (androidx.legacy.widget.Space) ViewBindings.findChildViewById(viewInflate, R.id.s_checkbox_top_space);
                                                    if (space4 != null) {
                                                        i = R.id.s_reset_button_top_space;
                                                        Space space5 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_reset_button_top_space);
                                                        if (space5 != null) {
                                                            i = R.id.s_setting_fragment_reset_button;
                                                            androidx.legacy.widget.Space space6 = (androidx.legacy.widget.Space) ViewBindings.findChildViewById(viewInflate, R.id.s_setting_fragment_reset_button);
                                                            if (space6 != null) {
                                                                i = R.id.s_top_state_bar;
                                                                Space space7 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_top_state_bar);
                                                                if (space7 != null) {
                                                                    i = R.id.tv_negative_button_text;
                                                                    SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_negative_button_text);
                                                                    if (skinTextView != null) {
                                                                        i = R.id.tv_positive_button_text;
                                                                        SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_positive_button_text);
                                                                        if (skinTextView2 != null) {
                                                                            i = R.id.tv_setting_fragment_reset_button;
                                                                            SkinTextView skinTextView3 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_setting_fragment_reset_button);
                                                                            if (skinTextView3 != null) {
                                                                                i = R.id.v_checkbox_bg;
                                                                                SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_checkbox_bg);
                                                                                if (skinView != null) {
                                                                                    i = R.id.v_negative_button_bg;
                                                                                    SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_negative_button_bg);
                                                                                    if (skinView2 != null) {
                                                                                        i = R.id.v_positive_button_bg;
                                                                                        SkinView skinView3 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_positive_button_bg);
                                                                                        if (skinView3 != null) {
                                                                                            i = R.id.v_protocol_background;
                                                                                            SkinView skinView4 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_protocol_background);
                                                                                            if (skinView4 != null) {
                                                                                                i = R.id.v_setting_fragment_reset_button_bg;
                                                                                                SkinView skinView5 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_setting_fragment_reset_button_bg);
                                                                                                if (skinView5 != null) {
                                                                                                    i = R.id.wv_protocol_content;
                                                                                                    WebView webView = (WebView) ViewBindings.findChildViewById(viewInflate, R.id.wv_protocol_content);
                                                                                                    if (webView != null) {
                                                                                                        return new FragmentProtocolBinding((ConstraintLayout) viewInflate, barrier, barrier2, barrier3, skinCheckBox, group, group2, group3, nestedScrollView, space, space2, space3, space4, space5, space6, space7, skinTextView, skinTextView2, skinTextView3, skinView, skinView2, skinView3, skinView4, skinView5, webView);
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
