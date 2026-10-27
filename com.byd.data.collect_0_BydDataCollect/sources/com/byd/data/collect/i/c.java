package com.byd.data.collect.i;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.data.collect.R;

/* JADX INFO: compiled from: FragmentSecondBinding.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f3145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Button f3146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f3147c;

    private c(ConstraintLayout constraintLayout, Button button, TextView textView) {
        this.f3145a = constraintLayout;
        this.f3146b = button;
        this.f3147c = textView;
    }

    public static c a(View view) {
        int i2 = R.id.button_second;
        Button button = (Button) view.findViewById(R.id.button_second);
        if (button != null) {
            i2 = R.id.textview_second;
            TextView textView = (TextView) view.findViewById(R.id.textview_second);
            if (textView != null) {
                return new c((ConstraintLayout) view, button, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static c c(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_second, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return a(viewInflate);
    }

    public ConstraintLayout b() {
        return this.f3145a;
    }
}
