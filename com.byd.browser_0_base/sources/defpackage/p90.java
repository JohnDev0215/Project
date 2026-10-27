package defpackage;

import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: DexFixer.java */
/* JADX INFO: loaded from: classes.dex */
public final class p90 {
    public static void a(Runtime runtime) {
        String str = xz.a.getApplicationInfo().sourceDir;
        String str2 = Process.is64Bit() ? "arm64" : "arm";
        File file = new File(str);
        String name = file.getName();
        int i = 0;
        try {
            i = (Os.stat(String.format("%s/oat/%s/%s.odex", file.getParent(), str2, name.substring(0, name.lastIndexOf(46)))).st_mode & 7) == 0 ? 8 : 2;
        } catch (ErrnoException unused) {
        }
        if (i > 2) {
            fa1.k("DexFixer", "Triggering dex compile. Reason=%d", Integer.valueOf(i));
            try {
                runtime.exec("/system/bin/cmd package compile -r shared " + xz.a.getPackageName());
            } catch (IOException unused2) {
            }
        }
    }
}
