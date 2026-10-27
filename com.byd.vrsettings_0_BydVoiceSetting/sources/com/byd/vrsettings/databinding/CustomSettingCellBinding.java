package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class CustomSettingCellBinding implements ViewBinding {
    public final View cellBottomLine;
    private final ConstraintLayout rootView;

    private CustomSettingCellBinding(ConstraintLayout constraintLayout, View view) {
        this.rootView = constraintLayout;
        this.cellBottomLine = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static CustomSettingCellBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomSettingCellBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_setting_cell, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomSettingCellBinding bind(View view) {
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.cell_bottomLine);
        if (viewFindChildViewById != null) {
            return new CustomSettingCellBinding((ConstraintLayout) view, viewFindChildViewById);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.cell_bottomLine)));
    }
}
