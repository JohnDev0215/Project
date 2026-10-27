package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentCarouselBinding implements ViewBinding {
    public final TextView itemAction;
    public final ConstraintLayout itemActionBackground;
    public final TextView itemCarouselTime;
    public final TextView itemCarouselTips;
    public final ImageView itemClose;
    public final TextView itemEdit;
    public final TextView itemLine;
    public final TextView itemTitle;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;

    private FragmentCarouselBinding(ConstraintLayout constraintLayout, TextView textView, ConstraintLayout constraintLayout2, TextView textView2, TextView textView3, ImageView imageView, TextView textView4, TextView textView5, TextView textView6, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.itemAction = textView;
        this.itemActionBackground = constraintLayout2;
        this.itemCarouselTime = textView2;
        this.itemCarouselTips = textView3;
        this.itemClose = imageView;
        this.itemEdit = textView4;
        this.itemLine = textView5;
        this.itemTitle = textView6;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCarouselBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCarouselBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_carousel, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCarouselBinding bind(View view) {
        int i = R.id.item_action;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.item_action);
        if (textView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.item_action_background);
            i = R.id.item_carousel_time;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.item_carousel_time);
            if (textView2 != null) {
                i = R.id.item_carousel_tips;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.item_carousel_tips);
                if (textView3 != null) {
                    i = R.id.item_close;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.item_close);
                    if (imageView != null) {
                        i = R.id.item_edit;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.item_edit);
                        if (textView4 != null) {
                            i = R.id.item_line;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.item_line);
                            if (textView5 != null) {
                                i = R.id.item_title;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.item_title);
                                if (textView6 != null) {
                                    i = R.id.recycler_view;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                                    if (recyclerView != null) {
                                        return new FragmentCarouselBinding((ConstraintLayout) view, textView, constraintLayout, textView2, textView3, imageView, textView4, textView5, textView6, recyclerView);
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
