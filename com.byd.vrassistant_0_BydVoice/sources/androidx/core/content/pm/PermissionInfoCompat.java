package androidx.core.content.pm;

import android.content.pm.PermissionInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class PermissionInfoCompat {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Protection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProtectionFlags {
    }

    private PermissionInfoCompat() {
    }

    public static int getProtection(PermissionInfo permissionInfo) {
        return permissionInfo.getProtection();
    }

    public static int getProtectionFlags(PermissionInfo permissionInfo) {
        return permissionInfo.getProtectionFlags();
    }
}
