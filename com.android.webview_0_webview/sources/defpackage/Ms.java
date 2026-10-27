package defpackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Ms {
    public static Ns a = new Ns() { // from class: Ls
    };

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static boolean b(File file, Ns ns) {
        File[] fileArrListFiles;
        if (!file.exists()) {
            file.delete();
            return true;
        }
        if (ns != null) {
            file.getPath();
            if (!Boolean.TRUE.booleanValue()) {
                return true;
            }
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                b(file2, ns);
            }
        }
        boolean zDelete = file.delete();
        if (!zDelete) {
            Ts.a("FileUtils", "Failed to delete: %s", file);
        }
        return zDelete;
    }
}
