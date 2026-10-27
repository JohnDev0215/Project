package com.google.android.setupdesign.template;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$id;

/* JADX INFO: loaded from: classes.dex */
public class ProgressBarMixin implements Mixin {
    private ColorStateList color;
    private final TemplateLayout templateLayout;

    public ProgressBarMixin(TemplateLayout templateLayout) {
        this.templateLayout = templateLayout;
    }

    public boolean isShown() {
        View viewFindManagedViewById = this.templateLayout.findManagedViewById(R$id.sud_layout_progress);
        return viewFindManagedViewById != null && viewFindManagedViewById.getVisibility() == 0;
    }

    public void setShown(boolean z) {
        if (z) {
            ProgressBar progressBar = getProgressBar();
            if (progressBar != null) {
                progressBar.setVisibility(0);
                return;
            }
            return;
        }
        ProgressBar progressBarPeekProgressBar = peekProgressBar();
        if (progressBarPeekProgressBar != null) {
            progressBarPeekProgressBar.setVisibility(8);
        }
    }

    private ProgressBar getProgressBar() {
        if (peekProgressBar() == null) {
            ViewStub viewStub = (ViewStub) this.templateLayout.findManagedViewById(R$id.sud_layout_progress_stub);
            if (viewStub != null) {
                viewStub.inflate();
            }
            setColor(this.color);
        }
        return peekProgressBar();
    }

    public ProgressBar peekProgressBar() {
        return (ProgressBar) this.templateLayout.findManagedViewById(R$id.sud_layout_progress);
    }

    public void setColor(ColorStateList colorStateList) {
        ProgressBar progressBarPeekProgressBar;
        int i = Build.VERSION.SDK_INT;
        this.color = colorStateList;
        if (i < 21 || (progressBarPeekProgressBar = peekProgressBar()) == null) {
            return;
        }
        progressBarPeekProgressBar.setIndeterminateTintList(colorStateList);
        if (i >= 23 || colorStateList != null) {
            progressBarPeekProgressBar.setProgressBackgroundTintList(colorStateList);
        }
    }

    public ColorStateList getColor() {
        return this.color;
    }
}
