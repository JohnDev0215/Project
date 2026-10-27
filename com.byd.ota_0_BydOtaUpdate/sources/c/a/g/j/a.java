package c.a.g.j;

import android.content.Context;
import android.text.TextUtils;
import c.a.b.d.e;
import c.a.b.d.n;
import c.a.g.c;
import c.a.g.l.d;
import c.a.g.l.h;
import c.a.g.l.k;
import c.a.g.l.l;
import c.a.g.l.p;
import c.a.g.l.r;
import com.byd.common.constants.ResultCodeConsts;
import com.byd.repository.bean.DiagnoseEcuInfo;
import com.byd.repository.bean.EcuVersionInfo;
import com.byd.repository.bean.UpdateResultInfo;
import com.byd.repository.bean.request.RequestDownloadReportInfo;
import com.byd.repository.bean.request.RequestMonitorDataReportInfo;
import com.byd.repository.bean.request.RequestSubmitReportInfo;
import com.byd.repository.bean.response.ResponseCheckVersionInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: compiled from: ReportDataHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2753a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f2754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2755c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.b.c.a f2756d = c.a.b.c.a.k();

    public a(Context context, c cVar) {
        this.f2755c = context;
        this.f2754b = cVar;
    }

    public final void a(int i2, RequestMonitorDataReportInfo.Data data) {
        RequestMonitorDataReportInfo requestMonitorDataReportInfo = new RequestMonitorDataReportInfo();
        requestMonitorDataReportInfo.fuctionId = i2;
        requestMonitorDataReportInfo.batchId = this.f2756d.d();
        requestMonitorDataReportInfo.uuid = "";
        if (i2 == 3) {
            if (data.cancelUpgrade == 2) {
                requestMonitorDataReportInfo.uuid = this.f2756d.B();
            }
        } else if (i2 == 4 && data.operationType == 1) {
            requestMonitorDataReportInfo.uuid = this.f2756d.B();
        }
        requestMonitorDataReportInfo.data = data;
        h.e(requestMonitorDataReportInfo);
        this.f2754b.m(h.f(requestMonitorDataReportInfo));
        this.f2756d.f0("");
    }

    public void b(ResponseCheckVersionInfo responseCheckVersionInfo) {
        ArrayList<EcuVersionInfo> arrayList = new ArrayList();
        arrayList.addAll(responseCheckVersionInfo.targetPackageInfo);
        arrayList.addAll(responseCheckVersionInfo.configPackageInfo);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(responseCheckVersionInfo.sourcePackageInfo);
        arrayList2.addAll(responseCheckVersionInfo.configSourceInfo);
        RequestDownloadReportInfo requestDownloadReportInfo = new RequestDownloadReportInfo();
        requestDownloadReportInfo.batchId = responseCheckVersionInfo.strategy.batchId;
        requestDownloadReportInfo.uuid = this.f2756d.B();
        ArrayList<RequestDownloadReportInfo.EcuModel> arrayList3 = new ArrayList();
        for (EcuVersionInfo ecuVersionInfo : arrayList) {
            RequestDownloadReportInfo.EcuModel ecuModel = new RequestDownloadReportInfo.EcuModel();
            ecuModel.ecuId = ecuVersionInfo.ecuId;
            ecuModel.ecuSoftCode = ecuVersionInfo.ecuSoftCode;
            ecuModel.ecuSoftVer = ecuVersionInfo.ecuSoftVer;
            ecuModel.ecuDestStatus = ecuVersionInfo.statusCode;
            ecuModel.ecuDestReason = ecuVersionInfo.statusCodeMsg;
            ecuModel.ecuDestTime = String.format("%1$s-%2$s", ecuVersionInfo.downStartTime, ecuVersionInfo.downEndTime);
            arrayList3.add(ecuModel);
        }
        for (RequestDownloadReportInfo.EcuModel ecuModel2 : arrayList3) {
            String str = ecuModel2.ecuSoftCode;
            Iterator it = arrayList2.iterator();
            while (true) {
                if (it.hasNext()) {
                    EcuVersionInfo ecuVersionInfo2 = (EcuVersionInfo) it.next();
                    if (str.equals(ecuVersionInfo2.ecuSoftCode)) {
                        ecuModel2.ecuOriginSVer = ecuVersionInfo2.ecuSoftVer;
                        ecuModel2.ecuOriginStatus = ecuVersionInfo2.statusCode;
                        ecuModel2.ecuOriginReason = ecuVersionInfo2.statusCodeMsg;
                        ecuModel2.ecuOriginTime = String.format("%1$s-%2$s", ecuVersionInfo2.downStartTime, ecuVersionInfo2.downEndTime);
                        break;
                    }
                }
            }
        }
        requestDownloadReportInfo.ecuModel = arrayList3;
        d.d(requestDownloadReportInfo);
        h.e(requestDownloadReportInfo);
        this.f2754b.l(h.f(requestDownloadReportInfo));
    }

    public void c(String str) {
        boolean zF;
        if (str.equals("otadiagnose")) {
            StringBuilder sb = new StringBuilder();
            String str2 = c.a.b.b.a.f2328i;
            sb.append(str2);
            sb.append("otadiagnose.zip");
            c.a.b.d.d.g(sb.toString());
            zF = c.a.b.d.d.f(c.a.b.b.a.j, str2, "otadiagnose.zip");
        } else if (str.equals("otaupgrade")) {
            StringBuilder sb2 = new StringBuilder();
            String str3 = c.a.b.b.a.f2328i;
            sb2.append(str3);
            sb2.append("otaupgrade.zip");
            c.a.b.d.d.g(sb2.toString());
            zF = c.a.b.d.d.f(c.a.b.b.a.k, str3, "otaupgrade.zip");
        } else {
            zF = false;
        }
        if (zF) {
            File file = null;
            if (str.equals("otadiagnose")) {
                file = new File(c.a.b.b.a.f2328i, "otadiagnose.zip");
            } else if (str.equals("otaupgrade")) {
                file = new File(c.a.b.b.a.f2328i, "otaupgrade.zip");
            }
            HashMap map = new HashMap();
            map.put("batchId", this.f2756d.d());
            map.put("uuid", this.f2756d.B());
            map.put("messageType", str);
            HashMap map2 = new HashMap(1);
            map2.put(file.getName(), file);
            this.f2754b.p(str, map, map2);
        }
    }

    public void d(EcuVersionInfo ecuVersionInfo) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.ecuId = ecuVersionInfo.ecuId;
        data.ecuSoftCode = ecuVersionInfo.ecuSoftCode;
        data.ecuSoftVer = ecuVersionInfo.ecuSoftVer;
        data.downloadTime = ecuVersionInfo.downStartTime;
        a(5, data);
    }

    public void e(int i2, String str, int i3) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.translateResult = i2;
        data.language = str;
        data.downloadType = i3;
        a(12, data);
    }

    public void f(int i2) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.isSupportOta = i2;
        a(6, data);
    }

    public void g(String str) {
        h(str, "", 0);
    }

    public void h(String str, String str2, int i2) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.timeZone = str;
        data.countryCode = str2;
        data.languageCode = i2;
        a(11, data);
    }

    public void i(int i2, String str) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.operationType = i2;
        data.appointmnet = str;
        data.timeZone = TimeZone.getDefault().getID();
        a(4, data);
    }

    public void j(int i2) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.cancelUpgrade = i2;
        a(3, data);
    }

    public void k(int i2, boolean z) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.updatePath = i2;
        if (z) {
            data.updateStartTime = n.a();
        } else {
            data.updateEndTime = n.a();
        }
        a(8, data);
    }

    public void l(int i2) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.updatePath = i2;
        data.updateTime = n.a();
        a(7, data);
    }

    public void m(int i2, int i3) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.updateType = i2;
        data.updatePath = i3;
        data.updateTime = n.a();
        a(1, data);
    }

    public void n(int i2, String str, String str2, String str3, String str4, String str5, String str6) {
        RequestMonitorDataReportInfo.Data data = new RequestMonitorDataReportInfo.Data();
        data.exitUpgrade = i2;
        data.description = str;
        data.functionAddition = str2;
        data.functionOptimization = str3;
        data.acknowledgements = str4;
        data.estimate = str5;
        data.strategyVer = str6;
        data.timeZone = TimeZone.getDefault().getID();
        a(2, data);
    }

    public void o(ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
        String str = f2753a;
        e.c(str, "ReportDataHelper reportSubmitResult");
        if (!p.c()) {
            p.b();
        }
        RequestSubmitReportInfo requestSubmitReportInfoA = p.a();
        if (requestSubmitReportInfoA == null) {
            return;
        }
        requestSubmitReportInfoA.normalModel.normalStatus = String.valueOf(normalCodeEnum.normalCode);
        requestSubmitReportInfoA.normalModel.normalReason = this.f2755c.getString(normalCodeEnum.normalCodeMsg);
        requestSubmitReportInfoA.normalModel.normalTime = n.a();
        requestSubmitReportInfoA.normalModel.normalUiInfo = k.a(this.f2755c, this.f2756d, normalCodeEnum.normalCode, null);
        UpdateResultInfo updateResultInfoD = r.d();
        e.c(str, "reportSubmitResult updateResultInfo -> " + updateResultInfoD);
        if (updateResultInfoD != null) {
            for (RequestSubmitReportInfo.EcuModel ecuModel : requestSubmitReportInfoA.ecuModel) {
                for (UpdateResultInfo.UpdateResultEcuInfo updateResultEcuInfo : updateResultInfoD.updateResultEcuInfoList) {
                    if (!TextUtils.isEmpty(updateResultEcuInfo.ecuId) && !TextUtils.isEmpty(ecuModel.ecuId) && updateResultEcuInfo.ecuId.equals(ecuModel.ecuId)) {
                        String str2 = f2753a;
                        e.c(str2, "updateResultEcuInfo ecuSoftCode = " + updateResultEcuInfo.ecuSoftCode + ", ecuModel ecuSoftCode = " + ecuModel.ecuSoftCode);
                        if (!TextUtils.isEmpty(updateResultEcuInfo.ecuSoftCode) && !TextUtils.isEmpty(ecuModel.ecuSoftCode)) {
                            if (updateResultEcuInfo.ecuSoftCode.equals(ecuModel.ecuSoftCode)) {
                                e.c(str2, "是主程序");
                                ecuModel.ecuStatus = updateResultEcuInfo.errorCode;
                                ecuModel.ecuTime = updateResultEcuInfo.ecuTime;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("是配置文件 -> ecuModel.subModel = ");
                                List<RequestSubmitReportInfo.EcuModel.SubModel> list = ecuModel.subModel;
                                sb.append(list != null ? list.size() : -1);
                                e.c(str2, sb.toString());
                                List<RequestSubmitReportInfo.EcuModel.SubModel> list2 = ecuModel.subModel;
                                if (list2 != null && list2.size() > 0) {
                                    for (RequestSubmitReportInfo.EcuModel.SubModel subModel : ecuModel.subModel) {
                                        e.c(f2753a, "subModel ecuSoftCode = " + subModel.ecuSoftCode);
                                        if (updateResultEcuInfo.ecuSoftCode.equals(subModel.ecuSoftCode)) {
                                            subModel.subModelStatus = updateResultEcuInfo.errorCode;
                                            subModel.ecuTime = updateResultEcuInfo.ecuTime;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        List<DiagnoseEcuInfo> listA = c.a.g.l.c.a();
        ArrayList arrayList = new ArrayList();
        if (listA != null) {
            for (RequestSubmitReportInfo.EcuModel ecuModel2 : requestSubmitReportInfoA.ecuModel) {
                String str3 = ecuModel2.ecuId;
                Iterator<DiagnoseEcuInfo> it = listA.iterator();
                while (true) {
                    if (it.hasNext()) {
                        DiagnoseEcuInfo next = it.next();
                        if (str3.equals(next.ecuId)) {
                            ecuModel2.diagnosticTroubleCode = next.diagnosticTroubleCode;
                            arrayList.add(next);
                            break;
                        }
                    }
                }
            }
        }
        if (listA != null && listA.size() > 0) {
            ArrayList<DiagnoseEcuInfo> arrayList2 = new ArrayList();
            for (DiagnoseEcuInfo diagnoseEcuInfo : listA) {
                if (!TextUtils.isEmpty(diagnoseEcuInfo.diagnosticTroubleCode) && !arrayList.contains(diagnoseEcuInfo)) {
                    arrayList2.add(diagnoseEcuInfo);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (DiagnoseEcuInfo diagnoseEcuInfo2 : arrayList2) {
                RequestSubmitReportInfo.EcuModel ecuModel3 = new RequestSubmitReportInfo.EcuModel();
                ecuModel3.ecuId = diagnoseEcuInfo2.ecuId;
                ecuModel3.ecuSoftCode = diagnoseEcuInfo2.ecuSoftCode;
                ecuModel3.ecuSoftVer = diagnoseEcuInfo2.ecuSoftVer;
                ecuModel3.ecuUpdateFlag = String.valueOf(ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_UNLOAD.ecuCode);
                ecuModel3.diagnosticTroubleCode = diagnoseEcuInfo2.diagnosticTroubleCode;
                arrayList3.add(ecuModel3);
            }
            requestSubmitReportInfoA.ecuModel.addAll(arrayList3);
        }
        boolean zE = p.e(requestSubmitReportInfoA);
        e.c(f2753a, "reportSubmitResult->isSaveSubmitReportInfo = " + zE);
        if (r.f()) {
            if (l.d()) {
                r.j();
            } else {
                r.i();
            }
        }
        if (r.g()) {
            if (l.d()) {
                r.l();
            } else {
                r.k();
            }
        }
        h.e(requestSubmitReportInfoA);
        this.f2754b.j(h.f(requestSubmitReportInfoA));
    }
}
