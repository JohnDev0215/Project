package defpackage;

import android.util.Log;
import defpackage.s33;
import java.io.File;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x23 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        Object obj = s33.a;
        File[] fileArrListFiles = s33.a.a.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.isDirectory()) {
                File[] fileArrListFiles2 = file.listFiles();
                if (fileArrListFiles2 != null) {
                    for (File file2 : fileArrListFiles2) {
                        if (!file2.delete()) {
                            Log.e("cr_tabmodel", "Failed to delete file: " + file2);
                        }
                    }
                }
            } else if (!file.delete()) {
                Log.e("cr_tabmodel", "Failed to delete file: " + file);
            }
        }
    }
}
