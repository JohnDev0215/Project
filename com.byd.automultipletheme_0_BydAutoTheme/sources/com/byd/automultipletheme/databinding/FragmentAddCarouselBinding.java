package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.byd.automultipletheme.R;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentAddCarouselBinding implements ViewBinding {
    public final ImageView itemClose;
    public final TextView itemEdit;
    public final TextView itemLine;
    public final TextView itemTitle;
    private final ConstraintLayout rootView;
    public final TextView selectCountText;
    public final TabLayout tabLayout;
    public final LinearLayout tabTopLayout;
    public final ViewPager viewPager;

    private FragmentAddCarouselBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TabLayout tabLayout, LinearLayout linearLayout, ViewPager viewPager) {
        this.rootView = constraintLayout;
        this.itemClose = imageView;
        this.itemEdit = textView;
        this.itemLine = textView2;
        this.itemTitle = textView3;
        this.selectCountText = textView4;
        this.tabLayout = tabLayout;
        this.tabTopLayout = linearLayout;
        this.viewPager = viewPager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAddCarouselBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAddCarouselBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_add_carousel, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAddCarouselBinding bind(View view) {
        int i = R.id.item_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.item_close);
        if (imageView != null) {
            i = R.id.item_edit;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.item_edit);
            if (textView != null) {
                i = R.id.item_line;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.item_line);
                if (textView2 != null) {
                    i = R.id.item_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.item_title);
                    if (textView3 != null) {
                        i = R.id.select_count_text;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.select_count_text);
                        if (textView4 != null) {
                            i = R.id.tab_layout;
                            TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, R.id.tab_layout);
                            if (tabLayout != null) {
                                i = R.id.tab_top_layout;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.tab_top_layout);
                                if (linearLayout != null) {
                                    i = R.id.view_pager;
                                    ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.view_pager);
                                    if (viewPager != null) {
                                        return new FragmentAddCarouselBinding((ConstraintLayout) view, imageView, textView, textView2, textView3, textView4, tabLayout, linearLayout, viewPager);
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
