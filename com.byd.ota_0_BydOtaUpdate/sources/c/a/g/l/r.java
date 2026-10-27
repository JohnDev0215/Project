package c.a.g.l;

import android.text.TextUtils;
import com.byd.repository.bean.EcuOtaResult;
import com.byd.repository.bean.RepairUpgradeInfo;
import com.byd.repository.bean.UpdateResultInfo;
import com.byd.repository.bean.request.RequestSubmitReportInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: UpdateResultUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2775a = "r";

    public static boolean a(String str) throws Throwable {
        List<UpdateResultInfo.UpdateResultEcuInfo> list;
        String str2 = f2775a;
        c.a.b.d.e.c(str2, "checkUpdateResult->actualMediaVer = " + str);
        UpdateResultInfo updateResultInfoD = d();
        if (updateResultInfoD == null || (list = updateResultInfoD.updateResultEcuInfoList) == null || list.size() <= 0) {
            c.a.b.d.e.b(str2, "updateResultInfo == null || updateResultInfo.updateResultEcuInfoList == null || updateResultInfo.updateResultEcuInfoList.size() <= 0");
            return false;
        }
        boolean z = true;
        for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo : updateResultInfoD.updateResultEcuInfoList) {
            if (updateResultEcuInfo.ecuId.contains("_Media_")) {
                if (TextUtils.isEmpty(updateResultEcuInfo.ecuSoftVer) || TextUtils.isEmpty(str) || !updateResultEcuInfo.ecuSoftVer.equals(str)) {
                    c.a.b.d.e.b(f2775a, "updateFail->ecuId = " + updateResultEcuInfo.ecuId + ", ecuSoftVer = " + updateResultEcuInfo.ecuSoftVer + ", ecuOriginSVer = " + updateResultEcuInfo.ecuOriginSVer + ", actualMediaVer = " + str);
                    updateResultEcuInfo.isUpdateSuccess = false;
                    z = false;
                } else {
                    updateResultEcuInfo.isUpdateSuccess = true;
                }
            } else if (!updateResultEcuInfo.isUpdateSuccess) {
                c.a.b.d.e.b(f2775a, "updateFail->ecuId = " + updateResultEcuInfo.ecuId + ", ecuSoftVer = " + updateResultEcuInfo.ecuSoftVer + ", ecuOriginSVer = " + updateResultEcuInfo.ecuOriginSVer + ", errorCode = " + updateResultEcuInfo.errorCode);
                z = false;
            }
        }
        boolean zN = n(updateResultInfoD);
        c.a.b.d.e.c(f2775a, "isSaveUpdateResultInfo = " + zN);
        return z;
    }

    public static boolean b() {
        return c.a.b.d.d.i(c.a.b.b.a.f2321b + "upgradeInfo.json");
    }

    public static EcuOtaResult c() throws Throwable {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.f2321b + "ecuOtaResult.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        try {
            return (EcuOtaResult) h.d(strQ, EcuOtaResult.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static UpdateResultInfo d() {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.f2321b + "updateResultInfo.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (UpdateResultInfo) h.d(strQ, UpdateResultInfo.class);
    }

    public static void e() {
        List<EcuOtaResult.EcuUpdateInfos> list;
        RepairUpgradeInfo repairUpgradeInfo;
        List<RequestSubmitReportInfo.EcuModel> list2;
        RepairUpgradeInfo repairUpgradeInfo2;
        Iterator<EcuOtaResult.EcuUpdateInfos> it;
        RequestSubmitReportInfo requestSubmitReportInfo;
        List<RequestSubmitReportInfo.EcuModel> list3;
        String str = f2775a;
        c.a.b.d.e.c(str, "handleUpdateResultInfo");
        if (!f()) {
            c.a.b.d.e.c(str, "ecuOtaResult file not exist!");
            return;
        }
        EcuOtaResult ecuOtaResultC = c();
        if (ecuOtaResultC == null || (list = ecuOtaResultC.ecuUpdateInfos) == null || list.size() <= 0) {
            c.a.b.d.e.c(str, "ecuOtaResult == null || ecuOtaResult.ecuUpdateInfos == null || ecuOtaResult.ecuUpdateInfos.size() <= 0");
            return;
        }
        if (!p.c()) {
            c.a.b.d.e.c(str, "submitReportInfo file not exist!");
            p.b();
        }
        RequestSubmitReportInfo requestSubmitReportInfoA = p.a();
        if (requestSubmitReportInfoA == null || (list3 = requestSubmitReportInfoA.ecuModel) == null || list3.size() <= 0) {
            c.a.b.d.e.c(str, "submitReportInfo == null || submitReportInfo.ecuModel == null || submitReportInfo.ecuModel.size() <= 0");
            requestSubmitReportInfoA = p.b();
        }
        if (requestSubmitReportInfoA == null || (list2 = requestSubmitReportInfoA.ecuModel) == null || list2.size() <= 0) {
            c.a.b.d.e.b(str, "get submitReportInfo fail!");
            UpdateResultInfo updateResultInfo = new UpdateResultInfo();
            updateResultInfo.normalOriginVer = c.a.b.c.a.k().e();
            updateResultInfo.normalVersion = c.a.b.c.a.k().l();
            updateResultInfo.updateMode = c.a.b.c.a.k().A();
            ArrayList<UpdateResultInfo.UpdateResultEcuInfo> arrayList = new ArrayList();
            ArrayList<UpdateResultInfo.UpdateResultEcuInfo> arrayList2 = new ArrayList();
            for (EcuOtaResult.EcuUpdateInfos ecuUpdateInfos : ecuOtaResultC.ecuUpdateInfos) {
                UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo = new UpdateResultInfo.UpdateResultEcuInfo();
                updateResultEcuInfo.ecuId = ecuUpdateInfos.ecuId;
                String str2 = ecuUpdateInfos.errorCode;
                updateResultEcuInfo.errorCode = str2;
                updateResultEcuInfo.ecuTime = ecuUpdateInfos.ecuTime;
                if (TextUtils.isEmpty(str2) || !ecuUpdateInfos.errorCode.endsWith("1000")) {
                    c.a.b.d.e.b(f2775a, "updateFail->ecuId = " + ecuUpdateInfos.ecuId + ", errorCode = " + ecuUpdateInfos.errorCode);
                    updateResultEcuInfo.isUpdateSuccess = false;
                } else {
                    updateResultEcuInfo.isUpdateSuccess = true;
                }
                if (TextUtils.isEmpty(ecuUpdateInfos.errorCode) || !ecuUpdateInfos.errorCode.endsWith("1003")) {
                    updateResultEcuInfo.isRollbackFail = false;
                } else {
                    c.a.b.d.e.b(f2775a, "rollbackFail->ecuId = " + ecuUpdateInfos.ecuId + ", errorCode = " + ecuUpdateInfos.errorCode);
                    updateResultEcuInfo.isRollbackFail = true;
                }
                arrayList.add(updateResultEcuInfo);
            }
            for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo2 : arrayList) {
                if (updateResultEcuInfo2.isRollbackFail) {
                    arrayList2.add(updateResultEcuInfo2);
                }
            }
            if (arrayList2.size() > 0) {
                if (m.b()) {
                    repairUpgradeInfo = m.a();
                    repairUpgradeInfo.time = c.a.b.d.n.a();
                    repairUpgradeInfo.isNeedRepairUpgrade = !repairUpgradeInfo.isNeedRepairUpgrade;
                } else {
                    repairUpgradeInfo = new RepairUpgradeInfo();
                    repairUpgradeInfo.time = c.a.b.d.n.a();
                    repairUpgradeInfo.isNeedRepairUpgrade = true;
                }
                ArrayList arrayList3 = new ArrayList();
                for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo3 : arrayList2) {
                    RepairUpgradeInfo.RepairUpgradeEcuInfo repairUpgradeEcuInfo = new RepairUpgradeInfo.RepairUpgradeEcuInfo();
                    repairUpgradeEcuInfo.ecuId = updateResultEcuInfo3.ecuId;
                    repairUpgradeEcuInfo.ecuSoftCode = updateResultEcuInfo3.ecuSoftCode;
                    repairUpgradeEcuInfo.ecuSoftVer = updateResultEcuInfo3.ecuSoftVer;
                    repairUpgradeEcuInfo.ecuOriginSVer = updateResultEcuInfo3.ecuOriginSVer;
                    repairUpgradeEcuInfo.ecuHardVer = updateResultEcuInfo3.ecuHardVer;
                    repairUpgradeEcuInfo.ecuConfigCode = updateResultEcuInfo3.ecuConfigCode;
                    repairUpgradeEcuInfo.ecuConfigVer = updateResultEcuInfo3.ecuConfigVer;
                    repairUpgradeEcuInfo.errorCode = updateResultEcuInfo3.errorCode;
                    arrayList3.add(repairUpgradeEcuInfo);
                }
                repairUpgradeInfo.repairUpgradeEcuInfoList = arrayList3;
                m.c(repairUpgradeInfo);
            } else {
                RepairUpgradeInfo repairUpgradeInfoA = m.b() ? m.a() : new RepairUpgradeInfo();
                repairUpgradeInfoA.time = c.a.b.d.n.a();
                repairUpgradeInfoA.isNeedRepairUpgrade = false;
                repairUpgradeInfoA.repairUpgradeEcuInfoList = null;
                m.c(repairUpgradeInfoA);
            }
            updateResultInfo.updateResultEcuInfoList = arrayList;
            updateResultInfo.rollbackFailEcuInfoList = arrayList2;
            boolean zN = n(updateResultInfo);
            c.a.b.d.e.c(f2775a, "isSaveUpdateResultInfo = " + zN);
            return;
        }
        c.a.b.d.e.c(str, "submitReportInfo.ecuModel.size() > 0");
        UpdateResultInfo updateResultInfo2 = new UpdateResultInfo();
        RequestSubmitReportInfo.NormalModel normalModel = requestSubmitReportInfoA.normalModel;
        updateResultInfo2.normalOriginVer = normalModel.normalOriginVer;
        updateResultInfo2.normalVersion = normalModel.normalVersion;
        updateResultInfo2.updateMode = requestSubmitReportInfoA.updateMode;
        ArrayList<UpdateResultInfo.UpdateResultEcuInfo> arrayList4 = new ArrayList();
        ArrayList<UpdateResultInfo.UpdateResultEcuInfo> arrayList5 = new ArrayList();
        Iterator<EcuOtaResult.EcuUpdateInfos> it2 = ecuOtaResultC.ecuUpdateInfos.iterator();
        while (it2.hasNext()) {
            EcuOtaResult.EcuUpdateInfos next = it2.next();
            String str3 = next.ecuId;
            c.a.b.d.e.c(f2775a, "handleUpdateResultInfo, 循环遍历每个ecu升级结果, ecuId = " + str3);
            for (RequestSubmitReportInfo.EcuModel ecuModel : requestSubmitReportInfoA.ecuModel) {
                if (str3.equals(ecuModel.ecuId) || str3.contains(ecuModel.ecuId)) {
                    String str4 = f2775a;
                    StringBuilder sb = new StringBuilder();
                    it = it2;
                    sb.append("handleUpdateResultInfo, ecuId = ");
                    sb.append(str3);
                    sb.append(", ecuModel.ecuId = ");
                    sb.append(ecuModel.ecuId);
                    sb.append(", ecuModel.ecuSoftCode = ");
                    sb.append(ecuModel.ecuSoftCode);
                    c.a.b.d.e.c(str4, sb.toString());
                    UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo4 = new UpdateResultInfo.UpdateResultEcuInfo();
                    String str5 = ecuModel.ecuId;
                    updateResultEcuInfo4.ecuId = str5;
                    requestSubmitReportInfo = requestSubmitReportInfoA;
                    updateResultEcuInfo4.errorCode = next.errorCode;
                    updateResultEcuInfo4.ecuTime = next.ecuTime;
                    if (str3.equals(str5)) {
                        c.a.b.d.e.c(str4, "handleUpdateResultInfo, ecuId.equals(ecuModel.ecuId)");
                        List<RequestSubmitReportInfo.EcuModel.SubModel> list4 = ecuModel.subModel;
                        if (list4 != null && list4.size() > 0) {
                            Iterator<RequestSubmitReportInfo.EcuModel.SubModel> it3 = ecuModel.subModel.iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    break;
                                }
                                RequestSubmitReportInfo.EcuModel.SubModel next2 = it3.next();
                                if (next2.subModelId.equals(ecuModel.ecuId)) {
                                    c.a.b.d.e.c(f2775a, "只升级配置文件，没有主程序 subModel = " + next2.toString());
                                    updateResultEcuInfo4.ecuSoftCode = next2.ecuSoftCode;
                                    updateResultEcuInfo4.ecuSoftVer = next2.ecuSoftVer;
                                    updateResultEcuInfo4.ecuOriginSVer = next2.ecuOriginSVer;
                                    break;
                                }
                            }
                        } else {
                            updateResultEcuInfo4.ecuSoftCode = ecuModel.ecuSoftCode;
                            updateResultEcuInfo4.ecuSoftVer = ecuModel.ecuSoftVer;
                            updateResultEcuInfo4.ecuOriginSVer = ecuModel.ecuOriginSVer;
                        }
                    } else if (str3.contains(ecuModel.ecuId)) {
                        c.a.b.d.e.c(str4, "handleUpdateResultInfo, ecuId.contains(ecuModel.ecuId)");
                        if (str3.contains(ecuModel.ecuSoftCode)) {
                            c.a.b.d.e.c(str4, "handleUpdateResultInfo, deal ecuModel");
                            updateResultEcuInfo4.ecuSoftCode = ecuModel.ecuSoftCode;
                            updateResultEcuInfo4.ecuSoftVer = ecuModel.ecuSoftVer;
                            updateResultEcuInfo4.ecuOriginSVer = ecuModel.ecuOriginSVer;
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("handleUpdateResultInfo, deal subModel");
                            List<RequestSubmitReportInfo.EcuModel.SubModel> list5 = ecuModel.subModel;
                            sb2.append(list5 != null ? list5.size() : -1);
                            c.a.b.d.e.c(str4, sb2.toString());
                            List<RequestSubmitReportInfo.EcuModel.SubModel> list6 = ecuModel.subModel;
                            if (list6 != null && list6.size() > 0) {
                                Iterator<RequestSubmitReportInfo.EcuModel.SubModel> it4 = ecuModel.subModel.iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        break;
                                    }
                                    RequestSubmitReportInfo.EcuModel.SubModel next3 = it4.next();
                                    if (next3.subModelId.equals(ecuModel.ecuId)) {
                                        c.a.b.d.e.c(f2775a, "handleUpdateResultInfo, deal subModel, subModel = " + next3.toString());
                                        updateResultEcuInfo4.ecuSoftCode = next3.ecuSoftCode;
                                        updateResultEcuInfo4.ecuSoftVer = next3.ecuSoftVer;
                                        updateResultEcuInfo4.ecuOriginSVer = next3.ecuOriginSVer;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (TextUtils.isEmpty(next.errorCode) || !next.errorCode.endsWith("1000")) {
                        c.a.b.d.e.b(f2775a, "updateFail->ecuId = " + next.ecuId + ", errorCode = " + next.errorCode);
                        updateResultEcuInfo4.isUpdateSuccess = false;
                    } else {
                        updateResultEcuInfo4.isUpdateSuccess = true;
                    }
                    if (TextUtils.isEmpty(next.errorCode) || !next.errorCode.endsWith("1003")) {
                        updateResultEcuInfo4.isRollbackFail = false;
                    } else {
                        c.a.b.d.e.b(f2775a, "rollbackFail->ecuId = " + next.ecuId + ", errorCode = " + next.errorCode);
                        updateResultEcuInfo4.isRollbackFail = true;
                    }
                    arrayList4.add(updateResultEcuInfo4);
                    it2 = it;
                    requestSubmitReportInfoA = requestSubmitReportInfo;
                }
            }
            it = it2;
            requestSubmitReportInfo = requestSubmitReportInfoA;
            it2 = it;
            requestSubmitReportInfoA = requestSubmitReportInfo;
        }
        for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo5 : arrayList4) {
            if (updateResultEcuInfo5.isRollbackFail) {
                arrayList5.add(updateResultEcuInfo5);
            }
        }
        if (arrayList5.size() > 0) {
            if (m.b()) {
                repairUpgradeInfo2 = m.a();
                repairUpgradeInfo2.time = c.a.b.d.n.a();
                repairUpgradeInfo2.isNeedRepairUpgrade = !repairUpgradeInfo2.isNeedRepairUpgrade;
            } else {
                repairUpgradeInfo2 = new RepairUpgradeInfo();
                repairUpgradeInfo2.time = c.a.b.d.n.a();
                repairUpgradeInfo2.isNeedRepairUpgrade = true;
            }
            ArrayList arrayList6 = new ArrayList();
            for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo6 : arrayList5) {
                RepairUpgradeInfo.RepairUpgradeEcuInfo repairUpgradeEcuInfo2 = new RepairUpgradeInfo.RepairUpgradeEcuInfo();
                repairUpgradeEcuInfo2.ecuId = updateResultEcuInfo6.ecuId;
                repairUpgradeEcuInfo2.ecuSoftCode = updateResultEcuInfo6.ecuSoftCode;
                repairUpgradeEcuInfo2.ecuSoftVer = updateResultEcuInfo6.ecuSoftVer;
                repairUpgradeEcuInfo2.ecuOriginSVer = updateResultEcuInfo6.ecuOriginSVer;
                repairUpgradeEcuInfo2.ecuHardVer = updateResultEcuInfo6.ecuHardVer;
                repairUpgradeEcuInfo2.ecuConfigCode = updateResultEcuInfo6.ecuConfigCode;
                repairUpgradeEcuInfo2.ecuConfigVer = updateResultEcuInfo6.ecuConfigVer;
                repairUpgradeEcuInfo2.errorCode = updateResultEcuInfo6.errorCode;
                arrayList6.add(repairUpgradeEcuInfo2);
            }
            repairUpgradeInfo2.repairUpgradeEcuInfoList = arrayList6;
            m.c(repairUpgradeInfo2);
        } else {
            RepairUpgradeInfo repairUpgradeInfoA2 = m.b() ? m.a() : new RepairUpgradeInfo();
            repairUpgradeInfoA2.time = c.a.b.d.n.a();
            repairUpgradeInfoA2.isNeedRepairUpgrade = false;
            repairUpgradeInfoA2.repairUpgradeEcuInfoList = null;
            m.c(repairUpgradeInfoA2);
        }
        updateResultInfo2.updateResultEcuInfoList = arrayList4;
        updateResultInfo2.rollbackFailEcuInfoList = arrayList5;
        boolean zN2 = n(updateResultInfo2);
        c.a.b.d.e.c(f2775a, "isSaveUpdateResultInfo = " + zN2);
    }

    public static boolean f() {
        return c.a.b.d.d.n(c.a.b.b.a.f2321b + "ecuOtaResult.json");
    }

    public static boolean g() {
        return c.a.b.d.d.n(c.a.b.b.a.f2321b + "updateResultInfo.json");
    }

    public static boolean h() {
        return c.a.b.d.d.n(c.a.b.b.a.f2321b + "upgradeInfo.json");
    }

    public static boolean i() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("ecuOtaResult.json");
        return new File(sb.toString()).renameTo(new File(str + "ecuOtaResult.json.bak"));
    }

    public static boolean j() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("ecuOtaResult.json");
        return new File(sb.toString()).renameTo(new File(str + "ecuOtaResult.json.bak." + c.a.b.d.n.b("yyyy-MM-dd-HH-mm-ss")));
    }

    public static boolean k() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("updateResultInfo.json");
        return new File(sb.toString()).renameTo(new File(str + "updateResultInfo.json.bak"));
    }

    public static boolean l() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("updateResultInfo.json");
        return new File(sb.toString()).renameTo(new File(str + "updateResultInfo.json.bak." + c.a.b.d.n.b("yyyy-MM-dd-HH-mm-ss")));
    }

    public static void m(String str) {
        List<UpdateResultInfo.UpdateResultEcuInfo> list;
        StringBuilder sb = new StringBuilder();
        sb.append("升级失败，结果如下：");
        sb.append("\n");
        UpdateResultInfo updateResultInfoD = d();
        if (updateResultInfoD == null || (list = updateResultInfoD.updateResultEcuInfoList) == null || list.size() <= 0) {
            sb.append("updateResultInfo == null || updateResultInfo.updateResultEcuInfoList == null || updateResultInfo.updateResultEcuInfoList.size() <= 0");
            sb.append("\n");
        } else {
            for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo : updateResultInfoD.updateResultEcuInfoList) {
                if (updateResultEcuInfo.ecuId.contains("_Media_")) {
                    if (TextUtils.isEmpty(updateResultEcuInfo.ecuSoftVer) || TextUtils.isEmpty(str) || !updateResultEcuInfo.ecuSoftVer.equals(str)) {
                        sb.append("ecuId = ");
                        sb.append(updateResultEcuInfo.ecuId);
                        sb.append(", ecuSoftVer = ");
                        sb.append(updateResultEcuInfo.ecuSoftVer);
                        sb.append(", ecuOriginSVer = ");
                        sb.append(updateResultEcuInfo.ecuOriginSVer);
                        sb.append(", actualMediaVer = ");
                        sb.append(str);
                        sb.append(", errorCode = ");
                        sb.append(updateResultEcuInfo.errorCode);
                        sb.append("\n");
                    }
                } else if (!updateResultEcuInfo.isUpdateSuccess) {
                    sb.append("ecuId = ");
                    sb.append(updateResultEcuInfo.ecuId);
                    sb.append(", ecuSoftVer = ");
                    sb.append(updateResultEcuInfo.ecuSoftVer);
                    sb.append(", ecuOriginSVer = ");
                    sb.append(updateResultEcuInfo.ecuOriginSVer);
                    sb.append(", errorCode = ");
                    sb.append(updateResultEcuInfo.errorCode);
                    sb.append("\n");
                }
            }
        }
        l.o(sb.toString());
    }

    public static boolean n(UpdateResultInfo updateResultInfo) throws Throwable {
        boolean zT = false;
        if (updateResultInfo == null) {
            return false;
        }
        try {
            zT = c.a.b.d.d.t(c.a.b.b.a.f2321b, "updateResultInfo.json", h.f(updateResultInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2775a, "writeDataWithProcessBuilder error：" + e2.getMessage());
        }
        if (!zT) {
            c.a.b.d.e.b(f2775a, "saveUpdateResultInfo again!");
            try {
                zT = c.a.b.d.d.t(c.a.b.b.a.f2321b, "updateResultInfo.json", h.f(updateResultInfo));
            } catch (Exception e3) {
                c.a.b.d.e.b(f2775a, "writeDataWithProcessBuilder error：" + e3.getMessage());
            }
        }
        h.e(updateResultInfo);
        return zT;
    }
}
