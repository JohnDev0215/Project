package defpackage;

import android.util.Log;
import defpackage.s33;
import java.io.File;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: TabbedModeTabPersistencePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u53 extends ac<Void> {
    public final /* synthetic */ boolean h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ v53 j;

    public u53(v53 v53Var, boolean z, boolean z2) {
        this.j = v53Var;
        this.h = z;
        this.i = z2;
    }

    @Override // defpackage.i9
    public final Object b() {
        boolean z = this.h;
        v53 v53Var = this.j;
        if (!z) {
            Object obj = v53.h;
            v53Var.getClass();
            Log.w("cr_tabmodel", "Starting to perform legacy migration.");
            File fileA = s33.a();
            File[] fileArrListFiles = fileA.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                File filesDir = xz.a.getFilesDir();
                File file = new File(filesDir, "tab_state");
                if (file.exists() && !file.renameTo(new File(fileA, v53Var.d()))) {
                    Log.e("cr_tabmodel", "Failed to rename file: " + file);
                }
                File[] fileArrListFiles2 = filesDir.listFiles();
                if (fileArrListFiles2 != null) {
                    for (File file2 : fileArrListFiles2) {
                        if (t33.b(file2.getName()) != null && !file2.renameTo(new File(fileA, file2.getName()))) {
                            Log.e("cr_tabmodel", "Failed to rename file: " + file2);
                        }
                    }
                }
            }
            SharedPreferencesManager.getInstance().l("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_FILE_MIGRATION", true);
            Log.w("cr_tabmodel", "Finished performing legacy migration.");
        }
        if (this.i) {
            return null;
        }
        Object obj2 = v53.h;
        v53Var.getClass();
        Log.w("cr_tabmodel", "Starting to perform multi-instance migration.");
        File fileA2 = s33.a();
        File file3 = new File(fileA2, v53Var.d());
        File file4 = new File(fileA2, "tab_state");
        if (file3.exists()) {
            Log.e("cr_tabmodel", "New metadata file already exists");
        } else if (file4.exists() && !file4.renameTo(file3)) {
            Log.e("cr_tabmodel", "Failed to rename file: " + file4);
        }
        int i = -1;
        int i2 = -1;
        while (i2 < v53Var.e) {
            if (i2 != 0) {
                File file5 = new File(s33.a.a, Integer.toString(i2));
                if (file5.exists()) {
                    File file6 = new File(file5, "tab_state");
                    if (file6.exists() && !file6.renameTo(new File(fileA2, v53.q(i2)))) {
                        Log.e("cr_tabmodel", "Failed to rename file: " + file6);
                    }
                    File[] fileArrListFiles3 = file5.listFiles();
                    if (fileArrListFiles3 != null) {
                        int length = fileArrListFiles3.length;
                        int i3 = 0;
                        while (i3 < length) {
                            File file7 = fileArrListFiles3[i3];
                            if (t33.b(file7.getName()) != null) {
                                if (i2 != i) {
                                    File file8 = new File(fileA2, file7.getName());
                                    if (!file8.exists() || file8.lastModified() <= file7.lastModified()) {
                                        if (!file7.renameTo(file8)) {
                                            Log.e("cr_tabmodel", "Failed to rename file: " + file7);
                                        }
                                    } else if (!file7.delete()) {
                                        Log.e("cr_tabmodel", "Failed to delete file: " + file7);
                                    }
                                } else if (!file7.delete()) {
                                    Log.e("cr_tabmodel", "Failed to delete file: " + file7);
                                }
                            }
                            i3++;
                            i = -1;
                        }
                    }
                    if (!file5.delete()) {
                        Log.e("cr_tabmodel", "Failed to delete directory: " + file5);
                    }
                }
            }
            i2++;
            i = -1;
        }
        SharedPreferencesManager.getInstance().l("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_MULTI_INSTANCE_FILE_MIGRATION", true);
        Log.w("cr_tabmodel", "Finished performing multi-instance migration.");
        return null;
    }
}
