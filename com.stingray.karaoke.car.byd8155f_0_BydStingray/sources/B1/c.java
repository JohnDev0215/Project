package B1;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import k.C1581a;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static Object a(String str, Bundle bundle) {
        return bundle.getParcelable(str, C1581a.class);
    }

    public static String b(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getUniqueId();
    }

    public static boolean c(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isTextSelectable();
    }
}
