package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutNotifyRemoteLightBinding implements ViewBinding {
    public final ImageView imageviewBg;
    public final ImageView imageviewIcon;
    public final RelativeLayout rootRemoteview;
    private final RelativeLayout rootView;
    public final TextView textviewContent;
    public final TextView textviewNum;
    public final TextView textviewState;
    public final TextView textviewTime;
    public final TextView textviewTitle;

    private LayoutNotifyRemoteLightBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.imageviewBg = imageView;
        this.imageviewIcon = imageView2;
        this.rootRemoteview = relativeLayout2;
        this.textviewContent = textView;
        this.textviewNum = textView2;
        this.textviewState = textView3;
        this.textviewTime = textView4;
        this.textviewTitle = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LayoutNotifyRemoteLightBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutNotifyRemoteLightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_notify_remote_light, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutNotifyRemoteLightBinding bind(View view) {
        int i = R.id.imageview_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_bg);
        if (imageView != null) {
            i = R.id.imageview_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_icon);
            if (imageView2 != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.textview_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textview_content);
                if (textView != null) {
                    i = R.id.textview_num;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_num);
                    if (textView2 != null) {
                        i = R.id.textview_state;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_state);
                        if (textView3 != null) {
                            i = R.id.textview_time;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_time);
                            if (textView4 != null) {
                                i = R.id.textview_title;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_title);
                                if (textView5 != null) {
                                    return new LayoutNotifyRemoteLightBinding(relativeLayout, imageView, imageView2, relativeLayout, textView, textView2, textView3, textView4, textView5);
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
