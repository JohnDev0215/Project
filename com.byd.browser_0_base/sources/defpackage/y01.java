package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.HashSet;

/* JADX INFO: compiled from: InstalledWebappDataRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class y01 {
    public final PackageManager a;
    public final z01 b;
    public final HashSet c = new HashSet();

    public y01(Context context, z01 z01Var) {
        this.a = context.getPackageManager();
        this.b = z01Var;
    }
}
