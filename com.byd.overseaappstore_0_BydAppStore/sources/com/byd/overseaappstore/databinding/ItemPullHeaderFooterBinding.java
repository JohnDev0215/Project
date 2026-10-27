package com.byd.overseaappstore.databinding;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemPullHeaderFooterBinding implements ViewBinding {

    @NonNull
    public final LinearLayout a;

    private ItemPullHeaderFooterBinding(@NonNull LinearLayout linearLayout, @NonNull SkinImageView skinImageView, @NonNull SkinProgressBar skinProgressBar, @NonNull SkinTextView skinTextView) {
        this.a = linearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
