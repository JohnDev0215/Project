package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentThemeDetailBinding implements ViewBinding {
    public final ImageView backView;
    private final RelativeLayout rootView;
    public final RecyclerView themeList;
    public final RelativeLayout titleLayout;
    public final TextView titleView;
    public final TextView useNowBtn;

    private FragmentThemeDetailBinding(RelativeLayout relativeLayout, ImageView imageView, RecyclerView recyclerView, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.backView = imageView;
        this.themeList = recyclerView;
        this.titleLayout = relativeLayout2;
        this.titleView = textView;
        this.useNowBtn = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentThemeDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentThemeDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_theme_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentThemeDetailBinding bind(View view) {
        int i = R.id.back_view;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.back_view);
        if (imageView != null) {
            i = R.id.theme_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.theme_list);
            if (recyclerView != null) {
                i = R.id.title_layout;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.title_layout);
                if (relativeLayout != null) {
                    i = R.id.title_view;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title_view);
                    if (textView != null) {
                        i = R.id.use_now_btn;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.use_now_btn);
                        if (textView2 != null) {
                            return new FragmentThemeDetailBinding((RelativeLayout) view, imageView, recyclerView, relativeLayout, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
