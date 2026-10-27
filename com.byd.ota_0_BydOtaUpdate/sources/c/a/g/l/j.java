package c.a.g.l;

import java.io.File;

/* JADX INFO: compiled from: MessageReportUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j {
    public static boolean a() {
        File file = new File(c.a.b.b.a.j);
        return file.exists() && file.list().length > 0;
    }

    public static boolean b() {
        File file = new File(c.a.b.b.a.k);
        return file.exists() && file.list().length > 0;
    }

    public static boolean c() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2328i;
        sb.append(str);
        sb.append("otadiagnose.zip");
        return new File(sb.toString()).renameTo(new File(str + "otadiagnose.zip.bak"));
    }

    public static boolean d() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2328i;
        sb.append(str);
        sb.append("otaupgrade.zip");
        return new File(sb.toString()).renameTo(new File(str + "otaupgrade.zip.bak"));
    }
}
