package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.byd.automultipletheme.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ViewCustomToastBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tvToast;

    private ViewCustomToastBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.tvToast = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static ViewCustomToastBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewCustomToastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_custom_toast, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewCustomToastBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        TextView textView = (TextView) view;
        return new ViewCustomToastBinding(textView, textView);
    }
}
