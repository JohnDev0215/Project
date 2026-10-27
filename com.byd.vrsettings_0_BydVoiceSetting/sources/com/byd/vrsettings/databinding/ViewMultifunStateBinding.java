package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewMultifunStateBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView textviewState;
    public final TextView viewDesc;

    private ViewMultifunStateBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.textviewState = textView;
        this.viewDesc = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ViewMultifunStateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewMultifunStateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_multifun_state, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewMultifunStateBinding bind(View view) {
        int i = R.id.textview_state;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textview_state);
        if (textView != null) {
            i = R.id.view_desc;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.view_desc);
            if (textView2 != null) {
                return new ViewMultifunStateBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
