package defpackage;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Nx implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        File file = (File) obj;
        File file2 = (File) obj2;
        return file.lastModified() == file2.lastModified() ? file.compareTo(file2) : file.lastModified() < file2.lastModified() ? 1 : -1;
    }
}
