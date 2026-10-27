package com.amazon.ui.foundations.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000f"}, d2 = {"Lcom/amazon/ui/foundations/utils/ScreenUtil;", "", "()V", "getFullScreenHeightPixels", "", "windowManager", "Landroid/view/WindowManager;", "getFullScreenWidthPixels", "getScreenHeightPixels", "getScreenWidthPixels", "isLandscape", "", "context", "Landroid/content/Context;", "isPortrait", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ScreenUtil {
    public static final ScreenUtil INSTANCE = new ScreenUtil();

    private ScreenUtil() {
    }

    public final int getScreenWidthPixels(WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(windowManager, "windowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public final int getScreenHeightPixels(WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(windowManager, "windowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final int getFullScreenWidthPixels(WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(windowManager, "windowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public final int getFullScreenHeightPixels(WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(windowManager, "windowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final boolean isPortrait(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getResources().getConfiguration().orientation == 1;
    }

    public final boolean isLandscape(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getResources().getConfiguration().orientation == 2;
    }
}
