package com.facebook.react.uimanager;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public enum PointerEvents {
    NONE,
    BOX_NONE,
    BOX_ONLY,
    AUTO;

    public static PointerEvents parsePointerEvents(String str) {
        if (str == null) {
            return AUTO;
        }
        return valueOf(str.toUpperCase(Locale.US).replace("-", Attributes.PREDEFINED_ATTRIBUTE_PREFIX));
    }

    public static boolean canBeTouchTarget(PointerEvents pointerEvents) {
        return pointerEvents == AUTO || pointerEvents == BOX_ONLY;
    }

    public static boolean canChildrenBeTouchTarget(PointerEvents pointerEvents) {
        return pointerEvents == AUTO || pointerEvents == BOX_NONE;
    }
}
