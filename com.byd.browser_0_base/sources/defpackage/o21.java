package defpackage;

import android.os.Environment;
import java.io.File;
import org.chromium.base.ContentUriUtils;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: InvalidStateOfflineItemFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class o21 extends pq1 {
    public o21(cq1 cq1Var) {
        super(cq1Var);
        k();
    }

    @Override // defpackage.pq1
    public final boolean j(OfflineItem offlineItem) {
        boolean zContains;
        String absolutePath;
        int i;
        String str = offlineItem.u;
        if (ContentUriUtils.d(str)) {
            zContains = true;
        } else {
            ku2 ku2VarT = ku2.t();
            try {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                ku2VarT.close();
                zContains = (externalStorageDirectory == null || str == null || (absolutePath = externalStorageDirectory.getAbsolutePath()) == null) ? false : str.contains(absolutePath);
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        return (offlineItem.p && zContains) || offlineItem.i || (i = offlineItem.A) == 3 || i == 5;
    }
}
