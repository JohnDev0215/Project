package com.amazon.android.os;

import android.content.Context;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class SharedAssetStorage {
    @SuppressFBWarnings(justification = "This is a stub for a platform library method, code will not be executed at runtime.", value = {"DMI_UNSUPPORTED_METHOD"})
    public static boolean shareFileOrDirectory(String str, boolean z, boolean z2, boolean z3) {
        return aivImplementationUnsupported();
    }

    @SuppressFBWarnings(justification = "This is a stub for a platform library method, code will not be executed at runtime.", value = {"DMI_UNSUPPORTED_METHOD"})
    public static boolean setGidForDirectory(String str, boolean z) {
        return aivImplementationUnsupported();
    }

    @SuppressFBWarnings(justification = "This is a stub for a platform library method, code will not be executed at runtime.", value = {"DMI_UNSUPPORTED_METHOD"})
    public static File getSecuredStorageDirectory(Context context) {
        aivImplementationUnsupported();
        return null;
    }

    @SuppressFBWarnings(justification = "This is a stub for a platform library method, code will not be executed at runtime.", value = {"DMI_UNSUPPORTED_METHOD"})
    public static boolean forceFixPermissions(Context context) {
        return aivImplementationUnsupported();
    }

    private static boolean aivImplementationUnsupported() {
        throw new UnsupportedOperationException("Expected to use system version of class");
    }
}
