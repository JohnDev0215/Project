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
public final class FragmentWallpaperDetailBinding implements ViewBinding {
    public final ImageView backView;
    private final RelativeLayout rootView;
    public final RelativeLayout titleLayout;
    public final TextView titleView;
    public final TextView useNowBtn;
    public final RecyclerView wallpaperList;

    private FragmentWallpaperDetailBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.backView = imageView;
        this.titleLayout = relativeLayout2;
        this.titleView = textView;
        this.useNowBtn = textView2;
        this.wallpaperList = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWallpaperDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentWallpaperDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_wallpaper_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWallpaperDetailBinding bind(View view) {
        int i = R.id.back_view;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.back_view);
        if (imageView != null) {
            i = R.id.title_layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.title_layout);
            if (relativeLayout != null) {
                i = R.id.title_view;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title_view);
                if (textView != null) {
                    i = R.id.use_now_btn;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.use_now_btn);
                    if (textView2 != null) {
                        i = R.id.wallpaper_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.wallpaper_list);
                        if (recyclerView != null) {
                            return new FragmentWallpaperDetailBinding((RelativeLayout) view, imageView, relativeLayout, textView, textView2, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
