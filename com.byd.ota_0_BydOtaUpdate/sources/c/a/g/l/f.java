package c.a.g.l;

import android.text.TextUtils;

/* JADX INFO: compiled from: FotaUpdateUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2765a = "f";

    public static boolean a() {
        return c.a.b.d.d.i("/data/vendor/nfs/nfs_ota/fota_update.txt");
    }

    public static String b(int i2) {
        if (i2 == 21) {
            return "ota_model=1";
        }
        switch (i2) {
        }
        return "ota_model=1";
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return c.a.b.d.d.t("/data/vendor/nfs/nfs_ota/", "fota_update.txt", str);
        } catch (Exception e2) {
            c.a.b.d.e.b(f2765a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }
}
