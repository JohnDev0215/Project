package c.a.j.f;

import java.io.File;

/* JADX INFO: compiled from: CommonConsts.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2856a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2857b;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("/data/vendor/nfs/nfs_ota/update");
        String str = File.separator;
        sb.append(str);
        String string = sb.toString();
        f2856a = string;
        f2857b = string + "result" + str;
    }
}
