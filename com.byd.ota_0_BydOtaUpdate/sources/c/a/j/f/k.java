package c.a.j.f;

import android.text.TextUtils;
import com.byd.repository.bean.EcuOtaResult;
import java.util.List;

/* JADX INFO: compiled from: UsbUpdateResultUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2881a = "k";

    public static boolean a() throws Throwable {
        List<EcuOtaResult.EcuUpdateInfos> list;
        EcuOtaResult ecuOtaResultC = c();
        if (ecuOtaResultC == null || (list = ecuOtaResultC.ecuUpdateInfos) == null || list.size() <= 0) {
            return false;
        }
        for (EcuOtaResult.EcuUpdateInfos ecuUpdateInfos : ecuOtaResultC.ecuUpdateInfos) {
            if (TextUtils.isEmpty(ecuUpdateInfos.errorCode) || !ecuUpdateInfos.errorCode.endsWith("1000")) {
                c.a.b.d.e.b(f2881a, "checkUpdateResult->ecuId = " + ecuUpdateInfos.ecuId + ", errorCode = " + ecuUpdateInfos.errorCode);
                return false;
            }
        }
        return true;
    }

    public static boolean b() {
        return c.a.b.d.d.i(d.f2857b + "ecuOtaResult_usb.json");
    }

    public static EcuOtaResult c() throws Throwable {
        String strQ = c.a.b.d.d.q(d.f2857b + "ecuOtaResult_usb.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        try {
            return (EcuOtaResult) c.a.g.l.h.d(strQ, EcuOtaResult.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean d() {
        return c.a.b.d.d.n(d.f2857b + "ecuOtaResult_usb.json");
    }
}
