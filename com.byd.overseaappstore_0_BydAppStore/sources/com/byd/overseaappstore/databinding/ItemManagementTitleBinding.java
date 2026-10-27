package com.byd.overseaappstore.databinding;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemManagementTitleBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    private ItemManagementTitleBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinAppCompatImageView skinAppCompatImageView2, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinView skinView) {
        this.a = constraintLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
