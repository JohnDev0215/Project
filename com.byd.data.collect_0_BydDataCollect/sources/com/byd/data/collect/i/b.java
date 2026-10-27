package com.byd.data.collect.i;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.data.collect.R;

/* JADX INFO: compiled from: FragmentFirstBinding.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f3142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Button f3143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f3144c;

    private b(ConstraintLayout constraintLayout, Button button, TextView textView) {
        this.f3142a = constraintLayout;
        this.f3143b = button;
        this.f3144c = textView;
    }

    public static b a(View view) {
        int i2 = R.id.button_first;
        Button button = (Button) view.findViewById(R.id.button_first);
        if (button != null) {
            i2 = R.id.textview_first;
            TextView textView = (TextView) view.findViewById(R.id.textview_first);
            if (textView != null) {
                return new b((ConstraintLayout) view, button, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static b c(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_first, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return a(viewInflate);
    }

    public ConstraintLayout b() {
        return this.f3142a;
    }
}
