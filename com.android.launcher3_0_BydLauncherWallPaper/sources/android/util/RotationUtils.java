package android.util;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes3.dex */
public class RotationUtils {
    public static Insets rotateInsets(Insets insets, int i) {
        if (insets == null || insets == Insets.NONE || i == 0) {
            return insets;
        }
        if (i == 1) {
            return Insets.of(insets.top, insets.right, insets.bottom, insets.left);
        }
        if (i == 2) {
            return Insets.of(insets.right, insets.bottom, insets.left, insets.top);
        }
        if (i == 3) {
            return Insets.of(insets.bottom, insets.left, insets.top, insets.right);
        }
        throw new IllegalArgumentException("unknown rotation: " + i);
    }
}
