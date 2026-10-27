package com.facebook.react.uimanager;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes6.dex */
public class ViewProps {
    public static final int[] BORDER_SPACING_TYPES = {8, 4, 5, 1, 3, 0, 2};
    public static final int[] PADDING_MARGIN_SPACING_TYPES = {8, 7, 6, 4, 5, 1, 3, 0, 2};
    public static final int[] POSITION_SPACING_TYPES = {4, 5, 1, 3};
    private static final HashSet<String> LAYOUT_ONLY_PROPS = new HashSet<>(Arrays.asList("alignSelf", "alignItems", "collapsable", "flex", "flexBasis", "flexDirection", "flexGrow", "rowGap", "columnGap", "gap", "flexShrink", "flexWrap", "justifyContent", "alignContent", "display", "position", "right", "top", "bottom", "left", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "end", "width", "height", "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "marginStart", "marginEnd", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "paddingStart", "paddingEnd"));

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isLayoutOnly(com.facebook.react.bridge.ReadableMap r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ViewProps.isLayoutOnly(com.facebook.react.bridge.ReadableMap, java.lang.String):boolean");
    }
}
