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
public final class LayoutNotifyRemoteNightBinding implements ViewBinding {
    public final ImageView imageviewIcon;
    public final RelativeLayout rootRemoteview;
    private final RelativeLayout rootView;
    public final TextView textviewContent;
    public final TextView textviewState;
    public final TextView textviewTime;

    private LayoutNotifyRemoteNightBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.imageviewIcon = imageView;
        this.rootRemoteview = relativeLayout2;
        this.textviewContent = textView;
        this.textviewState = textView2;
        this.textviewTime = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LayoutNotifyRemoteNightBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutNotifyRemoteNightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_notify_remote_night, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutNotifyRemoteNightBinding bind(View view) {
        int i = R.id.imageview_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageview_icon);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.textview_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textview_content);
            if (textView != null) {
                i = R.id.textview_state;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_state);
                if (textView2 != null) {
                    i = R.id.textview_time;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_time);
                    if (textView3 != null) {
                        return new LayoutNotifyRemoteNightBinding(relativeLayout, imageView, relativeLayout, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
