package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentTopicDetailBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinAppCompatImageView b;

    @NonNull
    public final SkinProgressBar c;

    @NonNull
    public final RecyclerView d;

    @NonNull
    public final SkinTextView e;

    @NonNull
    public final SkinAppCompatTextView f;

    private FragmentTopicDetailBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinProgressBar skinProgressBar, @NonNull RecyclerView recyclerView, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull Space space4, @NonNull SkinTextView skinTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinView skinView) {
        this.a = constraintLayout;
        this.b = skinAppCompatImageView;
        this.c = skinProgressBar;
        this.d = recyclerView;
        this.e = skinTextView;
        this.f = skinAppCompatTextView;
    }

    @NonNull
    public static FragmentTopicDetailBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_topic_detail, (ViewGroup) null, false);
        int i = R.id.iv_topic_detail_net_error;
        SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_topic_detail_net_error);
        if (skinAppCompatImageView != null) {
            i = R.id.pb_loading_bar;
            SkinProgressBar skinProgressBar = (SkinProgressBar) ViewBindings.findChildViewById(viewInflate, R.id.pb_loading_bar);
            if (skinProgressBar != null) {
                i = R.id.rv_topic_detail;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_topic_detail);
                if (recyclerView != null) {
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
                                    i = R.id.tv_loading;
                                    SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_loading);
                                    if (skinTextView != null) {
                                        i = R.id.tv_topic_detail_net_error;
                                        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_topic_detail_net_error);
                                        if (skinAppCompatTextView != null) {
                                            i = R.id.v_topic_detaile_background;
                                            SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_topic_detaile_background);
                                            if (skinView != null) {
                                                return new FragmentTopicDetailBinding((ConstraintLayout) viewInflate, skinAppCompatImageView, skinProgressBar, recyclerView, space, space2, space3, space4, skinTextView, skinAppCompatTextView, skinView);
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
