package c.a.g.l;

import android.text.TextUtils;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.repository.bean.EcuVersionInfo;
import com.byd.repository.bean.response.ResponseCheckVersionInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: StrategyUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2772a = "o";

    public static void a() {
        c.a.b.d.d.g(c.a.b.b.a.f2321b + "strategy.json");
    }

    public static ResponseCheckVersionInfo b() throws Throwable {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.f2321b + "strategy.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (ResponseCheckVersionInfo) h.d(strQ, ResponseCheckVersionInfo.class);
    }

    public static int c(ResponseCheckVersionInfo responseCheckVersionInfo) {
        ArrayList<EcuVersionInfo> arrayList = new ArrayList();
        arrayList.addAll(responseCheckVersionInfo.targetPackageInfo);
        arrayList.addAll(responseCheckVersionInfo.configPackageInfo);
        int iIntValue = 0;
        for (EcuVersionInfo ecuVersionInfo : arrayList) {
            if (!ecuVersionInfo.ecuId.contains("_Media_")) {
                try {
                    iIntValue += Integer.valueOf(ecuVersionInfo.estimate).intValue();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return iIntValue;
    }

    public static int d(ResponseCheckVersionInfo responseCheckVersionInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(responseCheckVersionInfo.targetPackageInfo);
        arrayList.addAll(responseCheckVersionInfo.configPackageInfo);
        Iterator it = arrayList.iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            try {
                iIntValue += Integer.valueOf(((EcuVersionInfo) it.next()).estimate).intValue();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return iIntValue;
    }

    public static List<EcuVersionInfo> e(ResponseCheckVersionInfo responseCheckVersionInfo) {
        ArrayList<EcuVersionInfo> arrayList = new ArrayList();
        arrayList.addAll(responseCheckVersionInfo.targetPackageInfo);
        arrayList.addAll(responseCheckVersionInfo.configPackageInfo);
        List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list = responseCheckVersionInfo.strategy.sequence;
        for (EcuVersionInfo ecuVersionInfo : responseCheckVersionInfo.sourcePackageInfo) {
            if (list != null && list.size() > 0) {
                Iterator<ResponseCheckVersionInfo.StrategyBean.SequenceBean> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        ResponseCheckVersionInfo.StrategyBean.SequenceBean next = it.next();
                        if (next.ecuId.equals(ecuVersionInfo.ecuId)) {
                            c.a.b.d.e.c(f2772a, "getUnDownloadPackageList sequenceBean.needSourcePkg = " + next.needSourcePkg);
                            if (TextUtils.isEmpty(next.needSourcePkg)) {
                                arrayList.add(ecuVersionInfo);
                            } else if (next.needSourcePkg.equals(VehicleVersionInfo.FORCE_UPGRADE)) {
                                arrayList.add(ecuVersionInfo);
                            }
                        }
                    }
                }
            }
        }
        for (EcuVersionInfo ecuVersionInfo2 : responseCheckVersionInfo.configSourceInfo) {
            if (list != null && list.size() > 0) {
                Iterator<ResponseCheckVersionInfo.StrategyBean.SequenceBean> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        ResponseCheckVersionInfo.StrategyBean.SequenceBean next2 = it2.next();
                        if (next2.ecuId.equals(ecuVersionInfo2.ecuId)) {
                            c.a.b.d.e.c(f2772a, "getUnDownloadPackageList sequenceBean.configSourceFlag = " + next2.configSourceFlag);
                            if (TextUtils.isEmpty(next2.configSourceFlag)) {
                                arrayList.add(ecuVersionInfo2);
                            } else if (next2.configSourceFlag.equals(VehicleVersionInfo.FORCE_UPGRADE)) {
                                arrayList.add(ecuVersionInfo2);
                            }
                        }
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (EcuVersionInfo ecuVersionInfo3 : arrayList) {
            File file = new File(ecuVersionInfo3.fileDir, ecuVersionInfo3.fileName);
            if (!file.exists() || (file.exists() && !String.valueOf(file.length()).equals(ecuVersionInfo3.fileSize))) {
                arrayList2.add(ecuVersionInfo3);
            }
        }
        return arrayList2;
    }

    public static boolean f(ResponseCheckVersionInfo responseCheckVersionInfo) {
        ResponseCheckVersionInfo.StrategyBean strategyBean;
        List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list;
        return (responseCheckVersionInfo == null || (strategyBean = responseCheckVersionInfo.strategy) == null || (list = strategyBean.sequence) == null || list.size() <= 0 || TextUtils.isEmpty(responseCheckVersionInfo.strategy.strategyVer)) ? false : true;
    }

    public static boolean g(ResponseCheckVersionInfo responseCheckVersionInfo) {
        if (responseCheckVersionInfo == null) {
            return false;
        }
        h.e(responseCheckVersionInfo);
        try {
            return c.a.b.d.d.t(c.a.b.b.a.f2321b, "strategy.json", h.f(responseCheckVersionInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2772a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }

    public static boolean h(ResponseCheckVersionInfo responseCheckVersionInfo) {
        if (responseCheckVersionInfo == null) {
            return false;
        }
        h.e(responseCheckVersionInfo);
        try {
            return c.a.b.d.d.t(c.a.b.b.a.f2321b, "illegal_strategy.json", h.f(responseCheckVersionInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2772a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }
}
