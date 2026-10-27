package com.byd.overseaappstore.databinding;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar;

/* JADX INFO: loaded from: classes.dex */
public final class ViewDownloadProgressBarBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    private ViewDownloadProgressBarBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinContentLoadingProgressBar skinContentLoadingProgressBar, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull View view) {
        this.a = constraintLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
