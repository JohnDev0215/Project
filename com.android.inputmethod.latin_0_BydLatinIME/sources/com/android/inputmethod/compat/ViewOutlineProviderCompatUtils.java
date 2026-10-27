package com.android.inputmethod.compat;

import android.inputmethodservice.InputMethodService;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class ViewOutlineProviderCompatUtils {
    private static final InsetsUpdater EMPTY_INSETS_UPDATER = new InsetsUpdater() { // from class: com.android.inputmethod.compat.ViewOutlineProviderCompatUtils.1
        @Override // com.android.inputmethod.compat.ViewOutlineProviderCompatUtils.InsetsUpdater
        public void setInsets(final InputMethodService.Insets insets) {
        }
    };

    public interface InsetsUpdater {
        void setInsets(final InputMethodService.Insets insets);
    }

    private ViewOutlineProviderCompatUtils() {
    }

    public static InsetsUpdater setInsetsOutlineProvider(final View view) {
        if (BuildCompatUtils.EFFECTIVE_SDK_INT < 21) {
            return EMPTY_INSETS_UPDATER;
        }
        return ViewOutlineProviderCompatUtilsLXX.setInsetsOutlineProvider(view);
    }
}
