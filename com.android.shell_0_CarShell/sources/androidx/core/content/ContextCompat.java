package androidx.core.content;

import android.content.Context;
import android.os.Build;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class ContextCompat {
    public static File[] getExternalFilesDirs(Context context, String str) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(str) : new File[]{context.getExternalFilesDir(str)};
    }

    public static File[] getExternalCacheDirs(Context context) {
        return Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
    }
}
