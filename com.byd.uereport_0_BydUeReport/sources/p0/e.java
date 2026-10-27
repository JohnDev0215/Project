package p0;

import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;

/* JADX INFO: compiled from: AccessibilityRecordCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static void a(AccessibilityRecord accessibilityRecord, int i8) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i8);
        }
    }

    public static void b(AccessibilityRecord accessibilityRecord, int i8) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i8);
        }
    }

    public static void c(AccessibilityRecord accessibilityRecord, View view, int i8) {
        if (Build.VERSION.SDK_INT >= 16) {
            accessibilityRecord.setSource(view, i8);
        }
    }
}
