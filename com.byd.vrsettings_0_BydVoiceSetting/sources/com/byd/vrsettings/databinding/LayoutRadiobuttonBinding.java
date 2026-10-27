package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.viewbinding.ViewBinding;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutRadiobuttonBinding implements ViewBinding {
    private final RadioButton rootView;

    private LayoutRadiobuttonBinding(RadioButton radioButton) {
        this.rootView = radioButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RadioButton getRoot() {
        return this.rootView;
    }

    public static LayoutRadiobuttonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutRadiobuttonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_radiobutton, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutRadiobuttonBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new LayoutRadiobuttonBinding((RadioButton) view);
    }
}
