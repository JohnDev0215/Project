package c.a.b.b;

import java.io.File;

/* JADX INFO: compiled from: FilePathConsts.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2322c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f2323d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f2324e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f2325f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f2326g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f2327h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f2328i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    public static final String n;
    public static final String o;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("/data/vendor/nfs/nfs_ota/update");
        String str = File.separator;
        sb.append(str);
        String string = sb.toString();
        f2320a = string;
        f2321b = string + "result" + str;
        String str2 = string + "package" + str;
        f2322c = str2;
        f2323d = str2 + "targetECU" + str;
        f2324e = str2 + "sourceECU" + str;
        f2325f = str2 + "media" + str;
        f2326g = string + "RECOVERY" + str;
        f2327h = string + "recoveryModel" + str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("/data/vendor/nfs/nfs_ota/eculog");
        sb2.append(str);
        String string2 = sb2.toString();
        f2328i = string2;
        j = string2 + "diagnose" + str;
        k = string2 + "upgrade" + str;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("/data/vendor/nfs/nfs_ota/upgrade");
        sb3.append(str);
        l = sb3.toString();
        m = "/data/vendor/nfs/nfs_ota/ecuota" + str;
        n = str + "collect2" + str;
        o = string + "translate" + str;
    }
}
