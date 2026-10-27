package com.amazon.music.skyfire.ui.ktx;

import android.content.Context;
import android.view.WindowManager;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.ScreenUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StyleSheet.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"columnWidth", "", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class StyleSheetKt {
    public static final int columnWidth(StyleSheet styleSheet, Context context) {
        Intrinsics.checkNotNullParameter(styleSheet, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return 0;
        }
        ScreenUtil screenUtil = ScreenUtil.INSTANCE;
        Object systemService = context.getSystemService("window");
        if (systemService != null) {
            int screenWidthPixels = screenUtil.getScreenWidthPixels((WindowManager) systemService);
            int columnSpace = gridSize.getColumnSpace();
            return ((screenWidthPixels - ((gridSize.getMarginSpace() + columnSpace) * 2)) - (columnSpace * 11)) / 12;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
    }
}
