package c.a.g.l;

import android.text.TextUtils;
import com.byd.common.constants.ResultCodeConsts;
import com.byd.repository.bean.DiagnoseEcuInfo;
import com.byd.repository.bean.EcuVersionInfo;
import com.byd.repository.bean.request.RequestSubmitReportInfo;
import com.byd.repository.bean.response.ResponseCheckVersionInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SubmitReportUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2773a = "p";

    public static RequestSubmitReportInfo a() {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.f2321b + "submitReportInfo.json");
        c.a.b.d.e.a(f2773a, "getSubmitReportInfo = " + strQ);
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (RequestSubmitReportInfo) h.d(strQ, RequestSubmitReportInfo.class);
    }

    public static RequestSubmitReportInfo b() {
        ResponseCheckVersionInfo.StrategyBean strategyBean;
        List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list;
        ResponseCheckVersionInfo.StrategyBean strategyBean2;
        List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list2;
        c.a.b.d.e.c(f2773a, "initSubmitReportInfo");
        RequestSubmitReportInfo requestSubmitReportInfo = new RequestSubmitReportInfo();
        requestSubmitReportInfo.batchId = c.a.b.c.a.k().d();
        requestSubmitReportInfo.uuid = c.a.b.c.a.k().B();
        requestSubmitReportInfo.priorityType = c.a.b.c.a.k().q();
        requestSubmitReportInfo.updateMode = c.a.b.c.a.k().A();
        RequestSubmitReportInfo.NormalModel normalModel = new RequestSubmitReportInfo.NormalModel();
        normalModel.normalVersion = c.a.b.c.a.k().l();
        normalModel.normalOriginVer = c.a.b.c.a.k().e();
        normalModel.normalStrategyVersion = c.a.b.c.a.k().v();
        normalModel.strategyType = c.a.b.c.a.k().u();
        ResponseCheckVersionInfo responseCheckVersionInfoB = o.b();
        List<DiagnoseEcuInfo> listA = c.a();
        ArrayList<RequestSubmitReportInfo.EcuModel> arrayList = new ArrayList();
        if (responseCheckVersionInfoB != null && (strategyBean2 = responseCheckVersionInfoB.strategy) != null && (list2 = strategyBean2.sequence) != null && responseCheckVersionInfoB.targetPackageInfo != null) {
            for (ResponseCheckVersionInfo.StrategyBean.SequenceBean sequenceBean : list2) {
                RequestSubmitReportInfo.EcuModel ecuModel = new RequestSubmitReportInfo.EcuModel();
                ecuModel.ecuId = sequenceBean.ecuId;
                Iterator<EcuVersionInfo> it = responseCheckVersionInfoB.targetPackageInfo.iterator();
                while (true) {
                    if (it.hasNext()) {
                        EcuVersionInfo next = it.next();
                        if (sequenceBean.ecuId.equals(next.ecuId)) {
                            ecuModel.ecuSoftCode = next.ecuSoftCode;
                            ecuModel.ecuSoftVer = next.ecuSoftVer;
                            ecuModel.ecuUpdateFlag = String.valueOf(ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS.ecuCode);
                            arrayList.add(ecuModel);
                            break;
                        }
                    }
                }
            }
        }
        if (listA != null) {
            for (RequestSubmitReportInfo.EcuModel ecuModel2 : arrayList) {
                String str = ecuModel2.ecuId;
                Iterator<DiagnoseEcuInfo> it2 = listA.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        DiagnoseEcuInfo next2 = it2.next();
                        if (str.equals(next2.ecuId)) {
                            ecuModel2.ecuOriginSVer = next2.ecuSoftVer;
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<RequestSubmitReportInfo.EcuModel.SubModel> arrayList2 = new ArrayList();
        if (responseCheckVersionInfoB != null && (strategyBean = responseCheckVersionInfoB.strategy) != null && (list = strategyBean.sequence) != null && responseCheckVersionInfoB.configPackageInfo != null) {
            for (ResponseCheckVersionInfo.StrategyBean.SequenceBean sequenceBean2 : list) {
                RequestSubmitReportInfo.EcuModel.SubModel subModel = new RequestSubmitReportInfo.EcuModel.SubModel();
                subModel.subModelId = sequenceBean2.ecuId;
                Iterator<EcuVersionInfo> it3 = responseCheckVersionInfoB.configPackageInfo.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        EcuVersionInfo next3 = it3.next();
                        if (sequenceBean2.ecuId.equals(next3.ecuId)) {
                            subModel.ecuSoftVer = next3.ecuConfigVer;
                            subModel.ecuSoftCode = next3.ecuConfigCode;
                            subModel.subModelUpdateFlag = String.valueOf(ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS.ecuCode);
                            arrayList2.add(subModel);
                            break;
                        }
                    }
                }
            }
        }
        if (listA != null) {
            for (RequestSubmitReportInfo.EcuModel.SubModel subModel2 : arrayList2) {
                String str2 = subModel2.subModelId;
                Iterator<DiagnoseEcuInfo> it4 = listA.iterator();
                while (true) {
                    if (it4.hasNext()) {
                        DiagnoseEcuInfo next4 = it4.next();
                        if (str2.equals(next4.ecuId)) {
                            subModel2.ecuOriginSVer = next4.ecuConfigVer;
                            break;
                        }
                    }
                }
            }
        }
        String str3 = f2773a;
        c.a.b.d.e.c(str3, "initSubmitReportInfo->ecuModelList.size" + arrayList.size());
        c.a.b.d.e.c(str3, "initSubmitReportInfo->configModelList.size" + arrayList2.size());
        for (RequestSubmitReportInfo.EcuModel.SubModel subModel3 : arrayList2) {
            boolean z = false;
            Iterator it5 = arrayList.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    break;
                }
                RequestSubmitReportInfo.EcuModel ecuModel3 = (RequestSubmitReportInfo.EcuModel) it5.next();
                if (ecuModel3.ecuId.equals(subModel3.subModelId)) {
                    z = true;
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(subModel3);
                    ecuModel3.subModel = arrayList3;
                    break;
                }
            }
            if (!z) {
                RequestSubmitReportInfo.EcuModel ecuModel4 = new RequestSubmitReportInfo.EcuModel();
                ecuModel4.ecuId = subModel3.subModelId;
                ecuModel4.ecuUpdateFlag = String.valueOf(ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_UNLOAD.ecuCode);
                if (listA != null) {
                    Iterator<DiagnoseEcuInfo> it6 = listA.iterator();
                    while (true) {
                        if (!it6.hasNext()) {
                            break;
                        }
                        DiagnoseEcuInfo next5 = it6.next();
                        if (ecuModel4.ecuId.equals(next5.ecuId)) {
                            ecuModel4.ecuSoftCode = next5.ecuSoftCode;
                            ecuModel4.ecuSoftVer = next5.ecuSoftVer;
                            break;
                        }
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(subModel3);
                ecuModel4.subModel = arrayList4;
                arrayList.add(ecuModel4);
            }
        }
        requestSubmitReportInfo.ecuModel = arrayList;
        requestSubmitReportInfo.normalModel = normalModel;
        boolean zE = e(requestSubmitReportInfo);
        c.a.b.d.e.c(f2773a, "initSubmitReportInfo->isSaveSubmitReportInfo = " + zE);
        return requestSubmitReportInfo;
    }

    public static boolean c() {
        return new File(c.a.b.b.a.f2321b + "submitReportInfo.json").exists();
    }

    public static boolean d() {
        StringBuilder sb = new StringBuilder();
        String str = c.a.b.b.a.f2321b;
        sb.append(str);
        sb.append("submitReportInfo.json");
        return new File(sb.toString()).renameTo(new File(str + "submitReportInfo.json.bak"));
    }

    public static boolean e(RequestSubmitReportInfo requestSubmitReportInfo) {
        if (requestSubmitReportInfo == null) {
            return false;
        }
        h.e(requestSubmitReportInfo);
        try {
            return c.a.b.d.d.t(c.a.b.b.a.f2321b, "submitReportInfo.json", h.f(requestSubmitReportInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2773a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }
}
