package androidx.autofill.inline;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class VersionUtils {
    public static boolean isVersionSupported(String str) {
        return UiVersions.getUiVersions().contains(str);
    }

    public static Bundle readStyleByVersion(Bundle bundle, String str) {
        return bundle.getBundle(str);
    }
}
