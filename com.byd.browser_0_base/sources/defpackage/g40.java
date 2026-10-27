package defpackage;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: compiled from: CustomTabTabPersistencePolicy.java */
/* JADX INFO: loaded from: classes.dex */
public final class g40 implements Comparator<File> {
    @Override // java.util.Comparator
    public final int compare(File file, File file2) {
        return Long.compare(file2.lastModified(), file.lastModified());
    }
}
