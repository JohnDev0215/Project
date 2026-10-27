package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinConstraintLayout;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemHotSearchBinding implements ViewBinding {

    @NonNull
    public final SkinConstraintLayout a;

    @NonNull
    public final SkinTextView b;

    private ItemHotSearchBinding(@NonNull SkinConstraintLayout skinConstraintLayout, @NonNull SkinTextView skinTextView) {
        this.a = skinConstraintLayout;
        this.b = skinTextView;
    }

    @NonNull
    public static ItemHotSearchBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_hot_search, viewGroup, false);
        SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_hot_search_name);
        if (skinTextView != null) {
            return new ItemHotSearchBinding((SkinConstraintLayout) viewInflate, skinTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(R.id.tv_hot_search_name)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
