package p;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class g7 {
    public static final g7 a = new g7();

    public final File a(Context context) {
        v03.j(context, "context");
        File noBackupFilesDir = context.getNoBackupFilesDir();
        v03.i(noBackupFilesDir, "context.noBackupFilesDir");
        return noBackupFilesDir;
    }
}
