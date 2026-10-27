package defpackage;

import android.util.Log;
import java.io.File;

/* JADX INFO: compiled from: CustomTabTabPersistencePolicy.java */
/* JADX INFO: loaded from: classes.dex */
public final class f40 extends ac<Void> {
    public final /* synthetic */ h40 h;

    public f40(h40 h40Var) {
        this.h = h40Var;
    }

    @Override // defpackage.i9
    public final Object b() {
        h40 h40Var = this.h;
        File file = new File(h40Var.n(), h40Var.d());
        if (!file.exists()) {
            return null;
        }
        if (h40Var.b) {
            if (file.setLastModified(System.currentTimeMillis())) {
                return null;
            }
            Log.e("cr_tabmodel", "Unable to update last modified time: " + file);
            return null;
        }
        if (file.delete()) {
            return null;
        }
        Log.e("cr_tabmodel", "Failed to delete file: " + file);
        return null;
    }
}
