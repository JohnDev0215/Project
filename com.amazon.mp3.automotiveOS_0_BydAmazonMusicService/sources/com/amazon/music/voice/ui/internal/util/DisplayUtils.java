package com.amazon.music.voice.ui.internal.util;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class DisplayUtils {
    public static int transformDpiToPx(Context context, int i) {
        return (int) Math.ceil(i * context.getResources().getDisplayMetrics().density);
    }

    public static int transformPxToDpi(Context context, int i) {
        return (int) Math.ceil(i / context.getResources().getDisplayMetrics().density);
    }
}
