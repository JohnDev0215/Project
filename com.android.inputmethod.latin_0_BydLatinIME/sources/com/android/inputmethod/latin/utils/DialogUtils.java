package com.android.inputmethod.latin.utils;

import android.content.Context;
import android.view.ContextThemeWrapper;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogUtils {
    private DialogUtils() {
    }

    public static Context getPlatformDialogThemeContext(final Context context) {
        return new ContextThemeWrapper(context, R.style.platformDialogTheme);
    }
}
