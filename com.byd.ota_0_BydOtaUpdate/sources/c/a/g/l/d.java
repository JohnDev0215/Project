package c.a.g.l;

import android.text.TextUtils;
import com.byd.repository.bean.request.RequestDownloadReportInfo;
import java.io.File;

/* JADX INFO: compiled from: DownloadReportUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2763a = "d";

    public static RequestDownloadReportInfo a() throws Throwable {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.f2321b + "downloadReportInfo.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (RequestDownloadReportInfo) h.d(strQ, RequestDownloadReportInfo.class);
    }

    public static boolean b() {
        return new File(c.a.b.b.a.f2321b + "downloadReportInfo.json").exists();
    }

    public static boolean c() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("downloadReportInfo.json");
        return new File(sb.toString()).renameTo(new File(str + "downloadReportInfo.json.bak"));
    }

    public static boolean d(RequestDownloadReportInfo requestDownloadReportInfo) {
        if (requestDownloadReportInfo == null) {
            return false;
        }
        h.e(requestDownloadReportInfo);
        try {
            return c.a.b.d.d.t(c.a.b.b.a.f2321b, "downloadReportInfo.json", h.f(requestDownloadReportInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2763a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }
}
