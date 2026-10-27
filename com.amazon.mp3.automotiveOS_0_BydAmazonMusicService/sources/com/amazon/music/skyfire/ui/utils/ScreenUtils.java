package com.amazon.music.skyfire.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/ScreenUtils;", "", "()V", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ScreenUtils {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final int getAvailableScreenHeight(Context context) {
        return INSTANCE.getAvailableScreenHeight(context);
    }

    @JvmStatic
    public static final int getNavbarSize(Context context) {
        return INSTANCE.getNavbarSize(context);
    }

    @JvmStatic
    public static final int getSafeAreaHeight(Context context) {
        return INSTANCE.getSafeAreaHeight(context);
    }

    @JvmStatic
    public static final int getScreenWidth(Context context) {
        return INSTANCE.getScreenWidth(context);
    }

    @JvmStatic
    public static final int getStatusBarHeight(Context context) {
        return INSTANCE.getStatusBarHeight(context);
    }

    @JvmStatic
    public static final boolean isLandscape(Context context) {
        return INSTANCE.isLandscape(context);
    }

    /* JADX INFO: compiled from: ScreenUtils.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/ScreenUtils$Companion;", "", "()V", "getAvailableScreenHeight", "", "context", "Landroid/content/Context;", "getNavbarSize", "getSafeAreaHeight", "getScreenWidth", "getStatusBarHeight", "isLandscape", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int getScreenWidth(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }

        @JvmStatic
        public final int getSafeAreaHeight(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }

        @JvmStatic
        public final int getAvailableScreenHeight(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Rect rect = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return rect.bottom;
        }

        @JvmStatic
        public final boolean isLandscape(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getResources().getConfiguration().orientation == 2;
        }

        @JvmStatic
        public final int getNavbarSize(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @JvmStatic
        public final int getStatusBarHeight(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Rect rect = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return rect.top;
        }
    }
}
