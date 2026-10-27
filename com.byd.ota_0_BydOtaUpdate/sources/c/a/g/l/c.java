package c.a.g.l;

import android.hardware.bydauto.ota.DiagnosticInfo;
import android.text.TextUtils;
import com.byd.repository.bean.DiagnoseEcuInfo;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DiagnoseUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2762a = "c";

    public static List<DiagnoseEcuInfo> a() {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.f2321b + "diagnoseEcuInfo.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return h.a(strQ, DiagnoseEcuInfo.class);
    }

    public static boolean b(List<DiagnoseEcuInfo> list) {
        if (list == null) {
            return false;
        }
        h.e(list);
        try {
            return c.a.b.d.d.t(c.a.b.b.a.f2321b, "diagnoseEcuInfo.json", h.f(list));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2762a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }

    public static boolean c(List<DiagnosticInfo> list) {
        List<DiagnoseEcuInfo> listA;
        if (list == null || (listA = a()) == null || listA.size() <= 0) {
            return false;
        }
        for (DiagnoseEcuInfo diagnoseEcuInfo : listA) {
            String str = diagnoseEcuInfo.ecuId;
            Iterator<DiagnosticInfo> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    DiagnosticInfo next = it.next();
                    if (str.equals(next.ecuId)) {
                        diagnoseEcuInfo.diagnosticTroubleCode = next.ecuDtcInfo;
                        break;
                    }
                }
            }
        }
        return b(listA);
    }
}
