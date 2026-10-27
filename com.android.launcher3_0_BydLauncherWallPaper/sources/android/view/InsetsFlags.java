package android.view;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.ViewDebug;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsFlags {

    @ViewDebug.ExportedProperty(flagMapping = {@ViewDebug.FlagToString(equals = 1, mask = 1, name = "OPAQUE_STATUS_BARS"), @ViewDebug.FlagToString(equals = 2, mask = 2, name = "OPAQUE_NAVIGATION_BARS"), @ViewDebug.FlagToString(equals = 4, mask = 4, name = "LOW_PROFILE_BARS"), @ViewDebug.FlagToString(equals = 8, mask = 8, name = "LIGHT_STATUS_BARS"), @ViewDebug.FlagToString(equals = 16, mask = 16, name = "LIGHT_NAVIGATION_BARS")})
    public int appearance;

    @ViewDebug.ExportedProperty(flagMapping = {@ViewDebug.FlagToString(equals = 1, mask = 1, name = "SHOW_BARS_BY_SWIPE"), @ViewDebug.FlagToString(equals = 2, mask = 2, name = "SHOW_TRANSIENT_BARS_BY_SWIPE")})
    public int behavior;

    private static int convertFlag(int i, int i2, int i3) {
        if ((i & i2) != 0) {
            return i3;
        }
        return 0;
    }

    private static int convertNoFlag(int i, int i2, int i3) {
        if ((i & i2) == 0) {
            return i3;
        }
        return 0;
    }

    public static int getAppearance(int i) {
        return convertNoFlag(i, -2147450880, 2) | convertNoFlag(i, TestOtaManagerImplCallback.ONDISCHARGEMAINCONTACTORSTATECHANGED, 1) | convertFlag(i, 1, 4) | 0 | convertFlag(i, 8192, 8) | convertFlag(i, 16, 16);
    }
}
