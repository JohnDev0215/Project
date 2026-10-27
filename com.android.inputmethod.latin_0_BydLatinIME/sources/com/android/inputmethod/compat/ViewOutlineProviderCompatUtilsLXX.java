package com.android.inputmethod.compat;

import android.graphics.Outline;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.android.inputmethod.compat.ViewOutlineProviderCompatUtils;

/* JADX INFO: loaded from: classes2.dex */
class ViewOutlineProviderCompatUtilsLXX {
    private ViewOutlineProviderCompatUtilsLXX() {
    }

    static ViewOutlineProviderCompatUtils.InsetsUpdater setInsetsOutlineProvider(final View view) {
        InsetsOutlineProvider insetsOutlineProvider = new InsetsOutlineProvider(view);
        view.setOutlineProvider(insetsOutlineProvider);
        return insetsOutlineProvider;
    }

    private static class InsetsOutlineProvider extends ViewOutlineProvider implements ViewOutlineProviderCompatUtils.InsetsUpdater {
        private static final int NO_DATA = -1;
        private int mLastVisibleTopInsets = -1;
        private final View mView;

        public InsetsOutlineProvider(final View view) {
            this.mView = view;
            view.setOutlineProvider(this);
        }

        @Override // com.android.inputmethod.compat.ViewOutlineProviderCompatUtils.InsetsUpdater
        public void setInsets(final InputMethodService.Insets insets) {
            int i = insets.visibleTopInsets;
            if (this.mLastVisibleTopInsets != i) {
                this.mLastVisibleTopInsets = i;
                this.mView.invalidateOutline();
            }
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(final View view, final Outline outline) {
            if (this.mLastVisibleTopInsets == -1) {
                ViewOutlineProvider.BACKGROUND.getOutline(view, outline);
            } else {
                outline.setRect(view.getLeft(), this.mLastVisibleTopInsets, view.getRight(), view.getBottom());
            }
        }
    }
}
