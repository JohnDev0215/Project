package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewPickListBinding implements ViewBinding {
    public final TextView btnCancel;
    public final TextView btnOk;
    public final ConstraintLayout clVoiceBarList;
    public final Group groupThree;
    public final Group groupTwo;
    public final ImageView ivImg;
    private final ConstraintLayout rootView;
    public final RecyclerView rvVoiceBarList;
    public final TextView tvExit;
    public final TextView tvFirst;
    public final TextView tvNext;
    public final TextView tvPage;
    public final TextView tvResultFrom;

    private ViewPickListBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, ConstraintLayout constraintLayout2, Group group, Group group2, ImageView imageView, RecyclerView recyclerView, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = constraintLayout;
        this.btnCancel = textView;
        this.btnOk = textView2;
        this.clVoiceBarList = constraintLayout2;
        this.groupThree = group;
        this.groupTwo = group2;
        this.ivImg = imageView;
        this.rvVoiceBarList = recyclerView;
        this.tvExit = textView3;
        this.tvFirst = textView4;
        this.tvNext = textView5;
        this.tvPage = textView6;
        this.tvResultFrom = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewPickListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewPickListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_pick_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewPickListBinding bind(View view) {
        int i = R.id.btn_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.btn_ok;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.group_three;
                Group group = (Group) ViewBindings.findChildViewById(view, i);
                if (group != null) {
                    i = R.id.group_two;
                    Group group2 = (Group) ViewBindings.findChildViewById(view, i);
                    if (group2 != null) {
                        i = R.id.iv_img;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.rv_voice_bar_list;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.tv_exit;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    i = R.id.tv_first;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView4 != null) {
                                        i = R.id.tv_next;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView5 != null) {
                                            i = R.id.tv_page;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView6 != null) {
                                                i = R.id.tv_result_from;
                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView7 != null) {
                                                    return new ViewPickListBinding(constraintLayout, textView, textView2, constraintLayout, group, group2, imageView, recyclerView, textView3, textView4, textView5, textView6, textView7);
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
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
