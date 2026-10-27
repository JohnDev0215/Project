package c.a.g.i;

import android.content.Context;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.DiagnosticInfo;
import android.hardware.bydauto.ota.EcuInfo;
import android.hardware.bydauto.ota.Info;
import android.hardware.bydauto.pki.PkiManager;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.text.TextUtils;
import c.a.b.d.c;
import com.byd.common.constants.ResultCodeConsts;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.repository.bean.DiagnoseEcuInfo;
import com.byd.repository.bean.EcuVersionInfo;
import com.byd.repository.bean.RepairUpgradeInfo;
import com.byd.repository.bean.request.RequestCheckVersionInfo;
import com.byd.repository.bean.request.RequestGetDTCInfo;
import com.byd.repository.bean.request.RequestGetEcuListInfo;
import com.byd.repository.bean.request.RequestLoginInfo;
import com.byd.repository.bean.request.RequestRegisterInfo;
import com.byd.repository.bean.request.RequestVersionSyncInfo;
import com.byd.repository.bean.response.ResponseCheckVersionInfo;
import com.byd.repository.bean.response.ResponseGetEcuListInfo;
import com.byd.repository.bean.response.ResponseLoginInfo;
import com.byd.repository.bean.response.ResponseStatusCodeInfo;
import com.byd.repository.bean.response.ResponseVersionSyncInfo;
import com.byd.repository.exception.NoRegisterException;
import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import retrofit2.HttpException;

/* JADX INFO: compiled from: GcyPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements c.a.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2709a = "f";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f2710b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile boolean f2711c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f2712d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile int f2713e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ResponseCheckVersionInfo f2714f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Context f2715g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.g.d f2716h;
    public c.a.g.k.a j;
    public c.a.g.j.a k;
    public BYDAutoOtaDevice m;
    public BYDAutoVersionDevice n;
    public List<DiagnoseEcuInfo> o;
    public List<EcuVersionInfo> p;
    public v q;
    public u r;
    public c.b s;
    public c.b t;
    public c.a.b.d.c u;
    public s w;
    public Map<String, Integer> v = new HashMap();
    public c.a.g.h.c x = null;
    public int y = 0;
    public boolean z = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c.a.b.c.a f2717i = c.a.b.c.a.k();
    public c.a.g.b l = c.a.g.g.a.l();

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class a implements c.a.g.h.d<ResponseStatusCodeInfo, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2718a;

        public a(String str) {
            this.f2718a = str;
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "messageReport->onFail:msg = " + str + ", e = " + th.toString());
            if (th instanceof HttpException) {
                int iCode = ((HttpException) th).code();
                c.a.b.d.e.c(f.f2709a, "httpErrorCode = " + iCode);
                if (iCode != 400) {
                    return;
                }
                if (this.f2718a.equals("otadiagnose")) {
                    c.a.g.l.j.c();
                    c.a.b.d.d.g(c.a.b.b.a.j);
                } else if (this.f2718a.equals("otaupgrade")) {
                    c.a.g.l.j.d();
                    c.a.b.d.d.g(c.a.b.b.a.k);
                }
            }
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseStatusCodeInfo responseStatusCodeInfo) {
            c.a.b.d.e.c(f.f2709a, "messageReport onSuccess->status = " + responseStatusCodeInfo.status + ", messageType = " + this.f2718a);
            if (this.f2718a.equals("otadiagnose")) {
                c.a.g.l.j.c();
                c.a.b.d.d.g(c.a.b.b.a.j);
            } else if (this.f2718a.equals("otaupgrade")) {
                c.a.g.l.j.d();
                c.a.b.d.d.g(c.a.b.b.a.k);
            }
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class b implements c.a.g.h.d<ResponseStatusCodeInfo, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2720a;

        public b(String str) {
            this.f2720a = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(String str) {
            c.a.b.d.e.b(f.f2709a, "postDelayed monitorDataReport");
            f.this.m(str);
            f.this.z = false;
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "monitorDataReport->onFail:msg = " + str + ", e = " + th.toString() + ",mMonitorStatus =" + f.this.z);
            if (f.this.z) {
                return;
            }
            f.this.z = true;
            if (!(th instanceof UnknownHostException)) {
                f.this.v.remove(this.f2720a);
                f.this.z = false;
                return;
            }
            c.a.b.d.e.b(f.f2709a, "monitorDataReport->1= " + f.this.v.get(this.f2720a));
            if (f.this.v.get(this.f2720a) == null) {
                f.this.v.put(this.f2720a, 0);
            }
            c.a.b.d.e.b(f.f2709a, "monitorDataReport->2= " + f.this.v.get(this.f2720a));
            if (((Integer) f.this.v.get(this.f2720a)).intValue() >= 6) {
                f.this.v.remove(this.f2720a);
                f.this.z = false;
                return;
            }
            c.a.b.d.e.b(f.f2709a, "monitorDataReport->3= " + f.this.v.get(this.f2720a));
            f.this.v.put(this.f2720a, Integer.valueOf(((Integer) f.this.v.get(this.f2720a)).intValue() + 1));
            c.a.b.d.l lVarD = c.a.b.d.l.d();
            final String str2 = this.f2720a;
            lVarD.c(new Runnable() { // from class: c.a.g.i.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2700c.d(str2);
                }
            }, 10000L);
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void b(ResponseStatusCodeInfo responseStatusCodeInfo) {
            c.a.b.d.e.c(f.f2709a, "monitorDataReport onSuccess->status = " + responseStatusCodeInfo.status);
            f.this.v.remove(this.f2720a);
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class c implements c.a.g.h.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EcuVersionInfo f2722a;

        public c(EcuVersionInfo ecuVersionInfo) {
            this.f2722a = ecuVersionInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(EcuVersionInfo ecuVersionInfo) {
            f.this.d0(ecuVersionInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h() {
            f fVar = f.this;
            fVar.d0((EcuVersionInfo) fVar.p.get(f.f2713e));
        }

        @Override // c.a.g.h.b
        public void a() {
            c.a.b.d.e.c(f.f2709a, "downloadFile->onDownloadSuccess:ecuId = " + this.f2722a.ecuId + ", curIndex = " + f.f2713e + ", maxIndex = " + f.this.p.size());
            this.f2722a.downEndTime = c.a.b.d.n.a();
            c.a.b.d.e.c(f.f2709a, "set DOWNLOAD_PACKAGES_SUCCESS start");
            ResultCodeConsts.EcuCodeEnum ecuCodeEnum = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS;
            this.f2722a.statusCode = String.valueOf(ecuCodeEnum.ecuCode);
            this.f2722a.statusCodeMsg = f.this.f2715g.getString(ecuCodeEnum.ecuCodeMsg);
            c.a.b.d.e.c(f.f2709a, "set DOWNLOAD_PACKAGES_SUCCESS end");
            if (f.f2713e < f.this.p.size() - 1) {
                c.a.b.d.e.c(f.f2709a, "downloadFile next");
                f.E();
                c.a.b.d.l.d().a(new Runnable() { // from class: c.a.g.i.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2702c.h();
                    }
                });
            } else {
                c.a.b.d.e.c(f.f2709a, "downloadFile finish");
                f.this.c0();
                f.this.f2716h.h();
            }
        }

        @Override // c.a.g.h.b
        public void b(long j, long j2) {
            c.a.b.d.e.c(f.f2709a, "downloadFile->onDownloadStart:ecuId = " + this.f2722a.ecuId + ", progress = " + j + ", contentLength = " + j2 + "(" + c.a.b.d.d.k(j2) + "), curIndex = " + f.f2713e + ", maxIndex = " + f.this.p.size());
            this.f2722a.downStartTime = c.a.b.d.n.a();
            f.this.k.d(this.f2722a);
        }

        @Override // c.a.g.h.b
        public void c(long j, long j2) {
            f.this.f2716h.A(this.f2722a, f.f2713e, f.this.p.size(), j, j2);
        }

        @Override // c.a.g.h.b
        public void d(int i2, String str) {
            c.a.b.d.e.b(f.f2709a, "downloadFile->onDownloadError:ecuId = " + this.f2722a.ecuId + ", code = " + i2 + ", msg = " + str + ", curIndex = " + f.f2713e + ", maxIndex = " + f.this.p.size());
            if (i2 == 1001 || i2 == 1004) {
                c.a.b.d.d.g(this.f2722a.fileDir);
            }
            if (i2 == 1001) {
                return;
            }
            EcuVersionInfo ecuVersionInfo = this.f2722a;
            int i3 = ecuVersionInfo.downloadTimes + 1;
            ecuVersionInfo.downloadTimes = i3;
            if (i3 <= 2) {
                c.a.b.d.l lVarD = c.a.b.d.l.d();
                final EcuVersionInfo ecuVersionInfo2 = this.f2722a;
                lVarD.c(new Runnable() { // from class: c.a.g.i.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2703c.f(ecuVersionInfo2);
                    }
                }, 3000L);
                return;
            }
            ecuVersionInfo.downloadTimes = 0;
            f.this.i(false);
            f.this.f2716h.c(str);
            f.this.f2716h.d("downloadFile->onDownloadError:ecuId = " + this.f2722a.ecuId + ", msg = " + str);
            if (i2 == 1004) {
                ResultCodeConsts.EcuCodeEnum ecuCodeEnum = ResultCodeConsts.EcuCodeEnum.FAIL_VERIFY_FILE_MD5;
                this.f2722a.statusCode = String.valueOf(ecuCodeEnum.ecuCode);
                this.f2722a.statusCodeMsg = f.this.f2715g.getString(ecuCodeEnum.ecuCodeMsg);
            } else {
                ResultCodeConsts.EcuCodeEnum ecuCodeEnum2 = ResultCodeConsts.EcuCodeEnum.FAIL_DOWNLOAD_PACKAGES;
                this.f2722a.statusCode = String.valueOf(ecuCodeEnum2.ecuCode);
                this.f2722a.statusCodeMsg = f.this.f2715g.getString(ecuCodeEnum2.ecuCodeMsg);
            }
            f.this.r();
            f.this.k.b(f.this.f2714f);
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class d implements c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ResultCodeConsts.NormalCodeEnum f2724a;

        public d(ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
            this.f2724a = normalCodeEnum;
        }

        @Override // c.a.b.d.c.b
        public void a() {
            c.a.b.d.e.c(f.f2709a, "reportSubmitResult net onAvailable");
            c.a.b.d.l.d().i(f.this.r);
            f.this.u.h(f.this.t);
            c.a.b.d.e.c(f.f2709a, "reportSubmitResult net onAvailable reportSubmitResult");
            f.this.k.o(this.f2724a);
        }

        @Override // c.a.b.d.c.b
        public void b() {
            c.a.b.d.e.c(f.f2709a, "reportSubmitResult net onLost");
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class e extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ r f2726a;

        public e(r rVar) {
            this.f2726a = rVar;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onClearDiagnosticTroubleCode(List<DiagnosticInfo> list) {
            super.onClearDiagnosticTroubleCode(list);
            c.a.b.d.l.d().i(this.f2726a);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onClearDiagnosticTroubleCode->infos.size() = " + list.size());
            f.this.f2716h.a();
        }
    }

    /* JADX INFO: renamed from: c.a.g.i.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GcyPresenter.java */
    public class C0053f extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t f2728a;

        public C0053f(t tVar) {
            this.f2728a = tVar;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onGetDiagnosticTroubleCode(List<DiagnosticInfo> list) {
            super.onGetDiagnosticTroubleCode(list);
            c.a.b.d.l.d().i(this.f2728a);
            c.a.b.d.e.c(AbsMessageCallback.TAG, "onGetDiagnosticTroubleCode->infos.size() = " + list.size());
            c.a.g.l.c.c(list);
            f.this.f2716h.y();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class g implements c.b {
        public g() {
        }

        @Override // c.a.b.d.c.b
        public void a() {
            c.a.b.d.l.d().i(f.this.q);
            f.this.u.h(f.this.s);
            f.this.o0();
        }

        @Override // c.a.b.d.c.b
        public void b() {
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            fVar.d0((EcuVersionInfo) fVar.p.get(f.f2713e));
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class i implements c.a.g.h.d<ResponseStatusCodeInfo, String> {
        public i() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "register->onFail:msg = " + str + ", e = " + th.toString());
            f.this.f2716h.d("register->onFail:msg = " + str + ", e = " + th.toString());
            f.this.i(false);
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseStatusCodeInfo responseStatusCodeInfo) {
            f.this.d();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class j implements c.a.g.h.d<ResponseLoginInfo, String> {
        public j() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "loginIn->onFail:msg = " + str + ", e = " + th.toString());
            if (th instanceof NoRegisterException) {
                f.this.n0();
                return;
            }
            f.this.f2716h.d("loginIn->onFail:msg = " + str + ", e = " + th.toString());
            f.this.i(false);
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseLoginInfo responseLoginInfo) {
            f.this.f2717i.h0(responseLoginInfo.token);
            f.this.g0();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class k implements c.a.g.h.d<ResponseGetEcuListInfo, String> {

        /* JADX INFO: compiled from: GcyPresenter.java */
        public class a extends AbsMessageCallback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f2735a;

            public a(List list) {
                this.f2735a = list;
            }

            @Override // android.hardware.bydauto.ota.AbsMessageCallback
            public void onEcuInfo(List<EcuInfo> list) throws Throwable {
                List<RepairUpgradeInfo.RepairUpgradeEcuInfo> list2;
                super.onEcuInfo(list);
                if (c.a.b.d.l.d().f(f.this.w)) {
                    c.a.b.d.l.d().i(f.this.w);
                    if (list == null || list.size() < 0) {
                        c.a.b.d.e.e(AbsMessageCallback.TAG, "onEcuInfo->ecuInfoList is null or size < 0");
                        f.this.i(false);
                        return;
                    }
                    c.a.b.d.e.c(AbsMessageCallback.TAG, "onEcuInfo->ecuInfoList.size() = " + list.size());
                    if (list.size() == 1) {
                        EcuInfo ecuInfo = list.get(0);
                        c.a.b.d.e.c(AbsMessageCallback.TAG, "oneEcuInfo.ecuId = " + ecuInfo.getEcuId());
                        if (ecuInfo.getEcuId().equals("MediaAutolink") && !c.a.g.l.e.b()) {
                            c.a.b.d.e.b(AbsMessageCallback.TAG, "close ecu diag func switch!");
                            f.this.i(false);
                            if (f.this.k != null) {
                                f.this.k.o(ResultCodeConsts.NormalCodeEnum.OTA_ECU_DIAG_FUNC_SWITCH_CLOSE);
                                return;
                            } else {
                                c.a.b.d.e.b(AbsMessageCallback.TAG, "close ecu diag func switch!mReportDataHelper == null");
                                return;
                            }
                        }
                    }
                    for (EcuInfo ecuInfo2 : list) {
                        DiagnoseEcuInfo diagnoseEcuInfo = new DiagnoseEcuInfo();
                        diagnoseEcuInfo.ecuId = ecuInfo2.getEcuId();
                        diagnoseEcuInfo.ecuDiaId = ecuInfo2.getEcuDiaId();
                        diagnoseEcuInfo.ecuDId = ecuInfo2.getEcuDId();
                        diagnoseEcuInfo.ecuHardDid = ecuInfo2.getEcuHardDid();
                        diagnoseEcuInfo.ecuConfigDid = ecuInfo2.getEcuConfigDid();
                        String strC = "7F22";
                        diagnoseEcuInfo.ecuSoftCode = TextUtils.isEmpty(ecuInfo2.getEcuSoftCode()) ? "000000000000000000" : ecuInfo2.getEcuSoftCode().startsWith("7F22") ? "7F22" : c.a.b.d.q.b(ecuInfo2.getEcuSoftCode());
                        diagnoseEcuInfo.ecuSoftVer = TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) ? "0.00.00" : ecuInfo2.getEcuSoftVer().startsWith("7F22") ? "7F22" : c.a.b.d.q.c(ecuInfo2.getEcuSoftVer());
                        if (!TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) && !ecuInfo2.getEcuSoftVer().startsWith("7F22") && !"6.55.35".equals(c.a.b.d.q.c(ecuInfo2.getEcuSoftVer()))) {
                            diagnoseEcuInfo.ecuSoftVer = c.a.b.d.q.c(ecuInfo2.getEcuSoftVer());
                        } else if (c.a.g.l.m.b()) {
                            RepairUpgradeInfo repairUpgradeInfoA = c.a.g.l.m.a();
                            if (repairUpgradeInfoA != null && (list2 = repairUpgradeInfoA.repairUpgradeEcuInfoList) != null && list2.size() > 0) {
                                Iterator<RepairUpgradeInfo.RepairUpgradeEcuInfo> it = repairUpgradeInfoA.repairUpgradeEcuInfoList.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    RepairUpgradeInfo.RepairUpgradeEcuInfo next = it.next();
                                    if (ecuInfo2.getEcuId().equals(next.ecuId)) {
                                        c.a.b.d.e.c(AbsMessageCallback.TAG, "repairUpgradeEcuInfo.ecuId =" + next.ecuId + "repairUpgradeEcuInfo.ecuOriginSVer =" + next.ecuOriginSVer + ",ecuInfo.getEcuId() =" + ecuInfo2.getEcuId() + ",diagnoseEcuInfo.ecuSoftVer =" + diagnoseEcuInfo.ecuSoftVer);
                                        diagnoseEcuInfo.ecuSoftVer = next.ecuOriginSVer;
                                        break;
                                    }
                                }
                            } else if (!TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) && ecuInfo2.getEcuSoftVer().startsWith("7F22")) {
                                diagnoseEcuInfo.ecuSoftVer = "7F22";
                            } else {
                                diagnoseEcuInfo.ecuSoftVer = "0.00.00";
                            }
                        } else if (!TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) && ecuInfo2.getEcuSoftVer().startsWith("7F22")) {
                            diagnoseEcuInfo.ecuSoftVer = "7F22";
                        } else {
                            diagnoseEcuInfo.ecuSoftVer = "0.00.00";
                        }
                        diagnoseEcuInfo.ecuHardVer = TextUtils.isEmpty(ecuInfo2.getEcuHardVer()) ? "0.0.0" : ecuInfo2.getEcuHardVer().startsWith("7F22") ? "7F22" : c.a.b.d.q.a(ecuInfo2.getEcuHardVer());
                        diagnoseEcuInfo.ecuConfigCode = TextUtils.isEmpty(ecuInfo2.getEcuConfigCode()) ? "000000000000000000" : ecuInfo2.getEcuConfigCode().startsWith("7F22") ? "7F22" : c.a.b.d.q.b(ecuInfo2.getEcuConfigCode());
                        if (TextUtils.isEmpty(ecuInfo2.getEcuConfigVer())) {
                            strC = "0.00.00";
                        } else if (!ecuInfo2.getEcuConfigVer().startsWith("7F22")) {
                            strC = c.a.b.d.q.c(ecuInfo2.getEcuConfigVer());
                        }
                        diagnoseEcuInfo.ecuConfigVer = strC;
                        if (diagnoseEcuInfo.ecuId.contains("_Media_")) {
                            diagnoseEcuInfo.ecuSoftVer = f.this.n.getBydSystemVersion();
                        }
                        f.this.o.add(diagnoseEcuInfo);
                    }
                    for (DiagnoseEcuInfo diagnoseEcuInfo2 : f.this.o) {
                        String str = diagnoseEcuInfo2.ecuId;
                        Iterator it2 = this.f2735a.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                Info info = (Info) it2.next();
                                if (str.equals(info.getEcuId())) {
                                    diagnoseEcuInfo2.ecuDiaId = info.getEcuDiaId();
                                    diagnoseEcuInfo2.ecuDId = info.getEcuDId();
                                    diagnoseEcuInfo2.ecuHardDid = info.getEcuHardDid();
                                    diagnoseEcuInfo2.ecuConfigDid = info.getEcuConfigDid();
                                    break;
                                }
                            }
                        }
                    }
                    c.a.g.l.c.b(f.this.o);
                    f.this.q0();
                }
            }
        }

        /* JADX INFO: compiled from: GcyPresenter.java */
        public class b extends AbsMessageCallback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f2737a;

            public b(List list) {
                this.f2737a = list;
            }

            @Override // android.hardware.bydauto.ota.AbsMessageCallback
            public void onEcuInfo(List<EcuInfo> list) throws Throwable {
                List<RepairUpgradeInfo.RepairUpgradeEcuInfo> list2;
                super.onEcuInfo(list);
                if (c.a.b.d.l.d().f(f.this.w)) {
                    c.a.b.d.l.d().i(f.this.w);
                    if (list == null || list.size() < 0) {
                        c.a.b.d.e.e(AbsMessageCallback.TAG, "onEcuInfo->ecuInfoList is null or size < 0");
                        f.this.i(false);
                        return;
                    }
                    c.a.b.d.e.c(AbsMessageCallback.TAG, "onEcuInfo->ecuInfoList.size() = " + list.size());
                    if (list.size() == 1) {
                        EcuInfo ecuInfo = list.get(0);
                        c.a.b.d.e.c(AbsMessageCallback.TAG, "oneEcuInfo.ecuId = " + ecuInfo.getEcuId());
                        if (ecuInfo.getEcuId().equals("MediaAutolink") && !c.a.g.l.e.b()) {
                            c.a.b.d.e.b(AbsMessageCallback.TAG, "close ecu diag func switch!");
                            f.this.i(false);
                            if (f.this.k != null) {
                                f.this.k.o(ResultCodeConsts.NormalCodeEnum.OTA_ECU_DIAG_FUNC_SWITCH_CLOSE);
                                return;
                            } else {
                                c.a.b.d.e.b(AbsMessageCallback.TAG, "close ecu diag func switch!mReportDataHelper == null");
                                return;
                            }
                        }
                    }
                    for (EcuInfo ecuInfo2 : list) {
                        DiagnoseEcuInfo diagnoseEcuInfo = new DiagnoseEcuInfo();
                        diagnoseEcuInfo.ecuId = ecuInfo2.getEcuId();
                        diagnoseEcuInfo.ecuDiaId = ecuInfo2.getEcuDiaId();
                        diagnoseEcuInfo.ecuDId = ecuInfo2.getEcuDId();
                        diagnoseEcuInfo.ecuHardDid = ecuInfo2.getEcuHardDid();
                        diagnoseEcuInfo.ecuConfigDid = ecuInfo2.getEcuConfigDid();
                        String strC = "7F22";
                        diagnoseEcuInfo.ecuSoftCode = TextUtils.isEmpty(ecuInfo2.getEcuSoftCode()) ? "000000000000000000" : ecuInfo2.getEcuSoftCode().startsWith("7F22") ? "7F22" : c.a.b.d.q.b(ecuInfo2.getEcuSoftCode());
                        diagnoseEcuInfo.ecuSoftVer = TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) ? "0.00.00" : ecuInfo2.getEcuSoftVer().startsWith("7F22") ? "7F22" : c.a.b.d.q.c(ecuInfo2.getEcuSoftVer());
                        if (!TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) && !ecuInfo2.getEcuSoftVer().startsWith("7F22") && !"6.55.35".equals(c.a.b.d.q.c(ecuInfo2.getEcuSoftVer()))) {
                            diagnoseEcuInfo.ecuSoftVer = c.a.b.d.q.c(ecuInfo2.getEcuSoftVer());
                        } else if (c.a.g.l.m.b()) {
                            RepairUpgradeInfo repairUpgradeInfoA = c.a.g.l.m.a();
                            if (repairUpgradeInfoA != null && (list2 = repairUpgradeInfoA.repairUpgradeEcuInfoList) != null && list2.size() > 0) {
                                Iterator<RepairUpgradeInfo.RepairUpgradeEcuInfo> it = repairUpgradeInfoA.repairUpgradeEcuInfoList.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    RepairUpgradeInfo.RepairUpgradeEcuInfo next = it.next();
                                    if (ecuInfo2.getEcuId().equals(next.ecuId)) {
                                        c.a.b.d.e.c(AbsMessageCallback.TAG, "repairUpgradeEcuInfo.ecuId =" + next.ecuId + "repairUpgradeEcuInfo.ecuOriginSVer =" + next.ecuOriginSVer + ",ecuInfo.getEcuId() =" + ecuInfo2.getEcuId() + ",diagnoseEcuInfo.ecuSoftVer =" + diagnoseEcuInfo.ecuSoftVer);
                                        diagnoseEcuInfo.ecuSoftVer = next.ecuOriginSVer;
                                        break;
                                    }
                                }
                            } else if (!TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) && ecuInfo2.getEcuSoftVer().startsWith("7F22")) {
                                diagnoseEcuInfo.ecuSoftVer = "7F22";
                            } else {
                                diagnoseEcuInfo.ecuSoftVer = "0.00.00";
                            }
                        } else if (!TextUtils.isEmpty(ecuInfo2.getEcuSoftVer()) && ecuInfo2.getEcuSoftVer().startsWith("7F22")) {
                            diagnoseEcuInfo.ecuSoftVer = "7F22";
                        } else {
                            diagnoseEcuInfo.ecuSoftVer = "0.00.00";
                        }
                        diagnoseEcuInfo.ecuHardVer = TextUtils.isEmpty(ecuInfo2.getEcuHardVer()) ? "0.0.0" : ecuInfo2.getEcuHardVer().startsWith("7F22") ? "7F22" : c.a.b.d.q.a(ecuInfo2.getEcuHardVer());
                        diagnoseEcuInfo.ecuConfigCode = TextUtils.isEmpty(ecuInfo2.getEcuConfigCode()) ? "000000000000000000" : ecuInfo2.getEcuConfigCode().startsWith("7F22") ? "7F22" : c.a.b.d.q.b(ecuInfo2.getEcuConfigCode());
                        if (TextUtils.isEmpty(ecuInfo2.getEcuConfigVer())) {
                            strC = "0.00.00";
                        } else if (!ecuInfo2.getEcuConfigVer().startsWith("7F22")) {
                            strC = c.a.b.d.q.c(ecuInfo2.getEcuConfigVer());
                        }
                        diagnoseEcuInfo.ecuConfigVer = strC;
                        if (diagnoseEcuInfo.ecuId.contains("_Media_")) {
                            diagnoseEcuInfo.ecuSoftVer = f.this.n.getBydSystemVersion();
                        }
                        f.this.o.add(diagnoseEcuInfo);
                    }
                    for (DiagnoseEcuInfo diagnoseEcuInfo2 : f.this.o) {
                        String str = diagnoseEcuInfo2.ecuId;
                        Iterator it2 = this.f2737a.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                Info info = (Info) it2.next();
                                if (str.equals(info.getEcuId())) {
                                    diagnoseEcuInfo2.ecuDiaId = info.getEcuDiaId();
                                    diagnoseEcuInfo2.ecuDId = info.getEcuDId();
                                    diagnoseEcuInfo2.ecuHardDid = info.getEcuHardDid();
                                    diagnoseEcuInfo2.ecuConfigDid = info.getEcuConfigDid();
                                    break;
                                }
                            }
                        }
                    }
                    c.a.g.l.c.b(f.this.o);
                    f.this.q0();
                }
            }
        }

        public k() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "getEcuList->onFail:msg = " + str + ", e = " + th.toString());
            f.this.f2716h.d("getEcuList->onFail:msg = " + str + ", e = " + th.toString());
            f.this.i(false);
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseGetEcuListInfo responseGetEcuListInfo) {
            if (!c.a.g.l.l.e()) {
                f.this.o = new ArrayList();
                ArrayList arrayList = new ArrayList();
                for (Iterator<ResponseGetEcuListInfo.EcuInfo> it = responseGetEcuListInfo.ecuList.iterator(); it.hasNext(); it = it) {
                    ResponseGetEcuListInfo.EcuInfo next = it.next();
                    arrayList.add(new Info(next.ecuId, next.ecuDiaId, next.ecuDId, next.ecuHardDid, next.ecuConfigDid, next.ecuReprogramType, next.ecuReprogramWay, next.ecuUpdateStyle, next.ecuIp, next.ecuPort));
                }
                if (f.this.w != null && c.a.b.d.l.d().f(f.this.w)) {
                    c.a.b.d.e.c(f.f2709a, "isMainThreadHasCallbacks diagnosticTimeOutRunnable");
                    return;
                }
                f fVar = f.this;
                fVar.w = new s(fVar, null);
                c.a.b.d.l.d().c(f.this.w, (arrayList.size() * 7 * 1000) + BYDAutoEngineDevice.ENGINE_SPEED_MAX);
                c.a.b.d.e.c(f.f2709a, "getEcuInfo->infoList.size() = " + arrayList.size());
                f.this.m.getEcuInfo(arrayList, new b(arrayList));
                return;
            }
            f.this.o = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (ResponseGetEcuListInfo.EcuInfo ecuInfo : responseGetEcuListInfo.ecuList) {
                if (ecuInfo.ecuId.contains("_Media_")) {
                    arrayList2.add(new Info(ecuInfo.ecuId, ecuInfo.ecuDiaId, ecuInfo.ecuDId, ecuInfo.ecuHardDid, ecuInfo.ecuConfigDid, ecuInfo.ecuReprogramType, ecuInfo.ecuReprogramWay, ecuInfo.ecuUpdateStyle, ecuInfo.ecuIp, ecuInfo.ecuPort));
                } else if (ecuInfo.ecuId.contains("_TBOX_")) {
                    arrayList2.add(new Info(ecuInfo.ecuId, ecuInfo.ecuDiaId, ecuInfo.ecuDId, ecuInfo.ecuHardDid, ecuInfo.ecuConfigDid, ecuInfo.ecuReprogramType, ecuInfo.ecuReprogramWay, ecuInfo.ecuUpdateStyle, ecuInfo.ecuIp, ecuInfo.ecuPort));
                }
            }
            if (f.this.w != null && c.a.b.d.l.d().f(f.this.w)) {
                c.a.b.d.e.c(f.f2709a, "isMainThreadHasCallbacks diagnosticTimeOutRunnable");
                return;
            }
            f fVar2 = f.this;
            fVar2.w = new s(fVar2, null);
            c.a.b.d.l.d().c(f.this.w, (arrayList2.size() * 7 * 1000) + BYDAutoEngineDevice.ENGINE_SPEED_MAX);
            c.a.b.d.e.c(f.f2709a, "getEcuInfo->infoList.size() = " + arrayList2.size());
            f.this.m.getEcuInfo(arrayList2, new a(arrayList2));
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class l implements c.a.g.h.d<ResponseVersionSyncInfo, String> {
        public l() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "versionSync->onFail:msg = " + str + ", e = " + th.toString());
            f.this.a0();
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseVersionSyncInfo responseVersionSyncInfo) {
            f.this.f2717i.J(responseVersionSyncInfo.latestVersion);
            if (!TextUtils.isEmpty(responseVersionSyncInfo.functionAddition)) {
                f.this.f2717i.N(responseVersionSyncInfo.functionAddition);
            }
            if (!TextUtils.isEmpty(responseVersionSyncInfo.functionOptimization)) {
                f.this.f2717i.O(responseVersionSyncInfo.functionOptimization);
            }
            if (!TextUtils.isEmpty(responseVersionSyncInfo.translatePath) && !TextUtils.isEmpty(responseVersionSyncInfo.translateSecretKey) && !TextUtils.isEmpty(responseVersionSyncInfo.translateEncZipSHA256)) {
                f.this.f0(responseVersionSyncInfo);
            } else {
                f.this.a0();
                f.this.k.e(3, Locale.getDefault().getLanguage(), 2);
            }
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class m implements c.a.g.h.d<ResponseCheckVersionInfo, String> {
        public m() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "checkVersion->onFail:msg = " + str + ", e = " + th.toString());
            boolean unused = f.f2711c = true;
            f.this.f2716h.d("checkVersion->onFail:msg = " + str + ", e = " + th.toString());
            f.this.i(false);
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseCheckVersionInfo responseCheckVersionInfo) {
            ResponseCheckVersionInfo.StrategyBean strategyBean = responseCheckVersionInfo.strategy;
            if (strategyBean != null && !TextUtils.isEmpty(strategyBean.translatePath) && !TextUtils.isEmpty(responseCheckVersionInfo.strategy.translateSecretKey) && !TextUtils.isEmpty(responseCheckVersionInfo.strategy.translateEncZipSHA256)) {
                f.this.e0(responseCheckVersionInfo);
            } else {
                f.this.b0(responseCheckVersionInfo);
                f.this.k.e(3, Locale.getDefault().getLanguage(), 1);
            }
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class n implements c.a.g.h.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ResponseVersionSyncInfo f2741a;

        public n(ResponseVersionSyncInfo responseVersionSyncInfo) {
            this.f2741a = responseVersionSyncInfo;
        }

        @Override // c.a.g.h.b
        public void a() {
            c.a.b.d.e.c(f.f2709a, "getAdditionVersionSync onDownloadSuccess -> translatePath = " + this.f2741a.translatePath);
            f.this.p0(this.f2741a.translateSecretKey, 2);
            f.this.a0();
        }

        @Override // c.a.g.h.b
        public void b(long j, long j2) {
            c.a.b.d.e.c(f.f2709a, "getAdditionVersionSync onDownloadStart -> translatePath = " + this.f2741a.translatePath);
        }

        @Override // c.a.g.h.b
        public void c(long j, long j2) {
        }

        @Override // c.a.g.h.b
        public void d(int i2, String str) {
            c.a.b.d.e.c(f.f2709a, "getAdditionVersionSync onDownloadError -> translatePath = " + this.f2741a.translatePath + ",code = " + i2 + ",msg = " + str);
            f.this.a0();
            f.this.k.e(1, Locale.getDefault().getLanguage(), 2);
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class o implements c.a.g.h.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ResponseCheckVersionInfo f2743a;

        public o(ResponseCheckVersionInfo responseCheckVersionInfo) {
            this.f2743a = responseCheckVersionInfo;
        }

        @Override // c.a.g.h.b
        public void a() {
            c.a.b.d.e.c(f.f2709a, "getAdditionCheckVersion onDownloadSuccess -> translatePath = " + this.f2743a.strategy.translatePath);
            f.this.p0(this.f2743a.strategy.translateSecretKey, 1);
            f.this.b0(this.f2743a);
        }

        @Override // c.a.g.h.b
        public void b(long j, long j2) {
            c.a.b.d.e.c(f.f2709a, "getAdditionCheckVersion onDownloadStart -> translatePath = " + this.f2743a.strategy.translatePath);
        }

        @Override // c.a.g.h.b
        public void c(long j, long j2) {
        }

        @Override // c.a.g.h.b
        public void d(int i2, String str) {
            c.a.b.d.e.c(f.f2709a, "getAdditionCheckVersion onDownloadError -> translatePath = " + this.f2743a.strategy.translatePath + ",code = " + i2 + ",msg = " + str);
            f.this.b0(this.f2743a);
            f.this.k.e(1, Locale.getDefault().getLanguage(), 1);
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class p implements c.a.g.h.d<ResponseStatusCodeInfo, String> {
        public p() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "downloadReport->onFail:msg = " + str + ", e = " + th.toString());
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseStatusCodeInfo responseStatusCodeInfo) {
            c.a.b.d.e.c(f.f2709a, "downloadReport onSuccess->status = " + responseStatusCodeInfo.status);
            c.a.g.l.d.c();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class q implements c.a.g.h.d<ResponseStatusCodeInfo, String> {
        public q() {
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.a.b.d.e.b(f.f2709a, "submitReport->onFail:msg = " + str + ", e = " + th.toString());
            f.this.f2716h.b();
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseStatusCodeInfo responseStatusCodeInfo) {
            c.a.b.d.e.c(f.f2709a, "submitReport onSuccess->status = " + responseStatusCodeInfo.status);
            c.a.g.l.p.d();
            f.this.f2716h.k();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class r implements Runnable {
        public r() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2709a, "clearDiagnosticTroubleCode timeout!!!");
            f.this.f2716h.a();
        }

        public /* synthetic */ r(f fVar, i iVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class s implements Runnable {
        public s() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2709a, "getEcuInfo timeout!!!");
            f.this.f2716h.d("getEcuInfo timeout!!!");
            f.this.i(false);
        }

        public /* synthetic */ s(f fVar, i iVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class t implements Runnable {
        public t() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2709a, "getDiagnosticTroubleCode timeout!!!");
            f.this.f2716h.y();
        }

        public /* synthetic */ t(f fVar, i iVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class u implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ResultCodeConsts.NormalCodeEnum f2750c;

        public u(ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
            this.f2750c = normalCodeEnum;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2709a, "report submit result ethernet listener timeout!!!");
            f.this.u.h(f.this.t);
            f.this.k.o(this.f2750c);
        }
    }

    /* JADX INFO: compiled from: GcyPresenter.java */
    public class v implements Runnable {
        public v() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2709a, "start download file ethernet listener timeout!!!");
            f.this.u.h(f.this.s);
            f.this.o0();
        }

        public /* synthetic */ v(f fVar, i iVar) {
            this();
        }
    }

    public f(Context context, c.a.g.d dVar) {
        this.f2715g = context;
        this.f2716h = dVar;
        this.j = new c.a.g.k.a(PkiManager.getInstance(context));
        this.k = new c.a.g.j.a(context, this);
        this.m = BYDAutoOtaDevice.getInstance(context);
        this.n = BYDAutoVersionDevice.getInstance(context);
        this.u = c.a.b.d.c.e(context);
    }

    public static /* synthetic */ int E() {
        int i2 = f2713e;
        f2713e = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void k0(ResponseCheckVersionInfo responseCheckVersionInfo) {
        this.l.d(new c.a.g.h.f(responseCheckVersionInfo.strategy.translateEncZipSHA256, new o(responseCheckVersionInfo)), responseCheckVersionInfo.strategy.translatePath, c.a.g.l.g.a(c.a.b.b.a.o, "translate.json.enc.zip"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m0(ResponseVersionSyncInfo responseVersionSyncInfo) {
        String str = c.a.b.b.a.o;
        File fileL = c.a.b.d.d.l(str);
        c.a.b.d.e.c(f2709a, "translate save path = " + fileL.getAbsolutePath());
        this.l.d(new c.a.g.h.f(responseVersionSyncInfo.translateEncZipSHA256, new n(responseVersionSyncInfo)), responseVersionSyncInfo.translatePath, c.a.g.l.g.a(str, "translate.json.enc.zip"));
    }

    public final void Z(ResponseCheckVersionInfo responseCheckVersionInfo) {
        List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list = responseCheckVersionInfo.strategy.sequence;
        if (list == null || list.isEmpty()) {
            i(false);
            return;
        }
        List<EcuVersionInfo> list2 = responseCheckVersionInfo.targetPackageInfo;
        if (list2 != null && list2.size() > 0) {
            for (EcuVersionInfo ecuVersionInfo : responseCheckVersionInfo.targetPackageInfo) {
                File file = new File(ecuVersionInfo.fileDir, ecuVersionInfo.fileName);
                if (file.exists() && String.valueOf(file.length()).equals(ecuVersionInfo.fileSize)) {
                    ResultCodeConsts.EcuCodeEnum ecuCodeEnum = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS;
                    ecuVersionInfo.statusCode = String.valueOf(ecuCodeEnum.ecuCode);
                    ecuVersionInfo.statusCodeMsg = this.f2715g.getString(ecuCodeEnum.ecuCodeMsg);
                }
            }
        }
        List<EcuVersionInfo> list3 = responseCheckVersionInfo.sourcePackageInfo;
        if (list3 != null && list3.size() > 0) {
            for (EcuVersionInfo ecuVersionInfo2 : responseCheckVersionInfo.sourcePackageInfo) {
                File file2 = new File(ecuVersionInfo2.fileDir, ecuVersionInfo2.fileName);
                if (file2.exists() && String.valueOf(file2.length()).equals(ecuVersionInfo2.fileSize)) {
                    ResultCodeConsts.EcuCodeEnum ecuCodeEnum2 = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS;
                    ecuVersionInfo2.statusCode = String.valueOf(ecuCodeEnum2.ecuCode);
                    ecuVersionInfo2.statusCodeMsg = this.f2715g.getString(ecuCodeEnum2.ecuCodeMsg);
                }
            }
        }
        List<EcuVersionInfo> list4 = responseCheckVersionInfo.configPackageInfo;
        if (list4 != null && list4.size() > 0) {
            for (EcuVersionInfo ecuVersionInfo3 : responseCheckVersionInfo.configPackageInfo) {
                File file3 = new File(ecuVersionInfo3.fileDir, ecuVersionInfo3.fileName);
                if (file3.exists() && String.valueOf(file3.length()).equals(ecuVersionInfo3.fileSize)) {
                    ResultCodeConsts.EcuCodeEnum ecuCodeEnum3 = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS;
                    ecuVersionInfo3.statusCode = String.valueOf(ecuCodeEnum3.ecuCode);
                    ecuVersionInfo3.statusCodeMsg = this.f2715g.getString(ecuCodeEnum3.ecuCodeMsg);
                }
            }
        }
        List<EcuVersionInfo> list5 = responseCheckVersionInfo.configSourceInfo;
        if (list5 != null && list5.size() > 0) {
            for (EcuVersionInfo ecuVersionInfo4 : responseCheckVersionInfo.configSourceInfo) {
                File file4 = new File(ecuVersionInfo4.fileDir, ecuVersionInfo4.fileName);
                if (file4.exists() && String.valueOf(file4.length()).equals(ecuVersionInfo4.fileSize)) {
                    ResultCodeConsts.EcuCodeEnum ecuCodeEnum4 = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_SUCCESS;
                    ecuVersionInfo4.statusCode = String.valueOf(ecuCodeEnum4.ecuCode);
                    ecuVersionInfo4.statusCodeMsg = this.f2715g.getString(ecuCodeEnum4.ecuCodeMsg);
                }
            }
        }
        r();
        f2713e = 0;
        List<EcuVersionInfo> listE = c.a.g.l.o.e(responseCheckVersionInfo);
        this.p = listE;
        if (listE.size() <= 0) {
            c0();
            return;
        }
        i iVar = null;
        if (this.q != null) {
            c.a.b.d.l.d().i(this.q);
            this.q = null;
        }
        this.q = new v(this, iVar);
        c.a.b.d.l.d().c(this.q, 60000L);
        if (this.s != null) {
            this.s = null;
        }
        g gVar = new g();
        this.s = gVar;
        this.u.f(gVar);
    }

    @Override // c.a.g.c
    public void a() {
        if (this.f2715g != null) {
            this.f2715g = null;
        }
        r();
    }

    public void a0() {
        this.f2716h.r(5);
        c.a.g.h.e eVar = new c.a.g.h.e(ResponseCheckVersionInfo.class, new m());
        RequestCheckVersionInfo requestCheckVersionInfo = new RequestCheckVersionInfo();
        requestCheckVersionInfo.version = this.f2717i.e();
        requestCheckVersionInfo.uuid = this.f2717i.B();
        ArrayList arrayList = new ArrayList();
        for (DiagnoseEcuInfo diagnoseEcuInfo : this.o) {
            RequestCheckVersionInfo.EcuInfo ecuInfo = new RequestCheckVersionInfo.EcuInfo();
            ecuInfo.ecuId = diagnoseEcuInfo.ecuId;
            ecuInfo.ecuSoftCode = diagnoseEcuInfo.ecuSoftCode;
            ecuInfo.ecuSoftVer = diagnoseEcuInfo.ecuSoftVer;
            ecuInfo.ecuHardVer = diagnoseEcuInfo.ecuHardVer;
            ecuInfo.ecuConfigCode = diagnoseEcuInfo.ecuConfigCode;
            ecuInfo.ecuConfigVer = diagnoseEcuInfo.ecuConfigVer;
            arrayList.add(ecuInfo);
        }
        requestCheckVersionInfo.ecuInfo = arrayList;
        this.l.f(eVar, c.a.g.l.h.f(requestCheckVersionInfo));
    }

    @Override // c.a.g.c
    public void b(ResultCodeConsts.NormalCodeEnum normalCodeEnum) {
        c.a.b.d.e.c(f2709a, "reportSubmitResult");
        if (this.r != null) {
            c.a.b.d.l.d().i(this.r);
            this.r = null;
        }
        this.r = new u(normalCodeEnum);
        c.a.b.d.l.d().c(this.r, 60000L);
        if (this.t != null) {
            this.t = null;
        }
        d dVar = new d(normalCodeEnum);
        this.t = dVar;
        this.u.f(dVar);
    }

    public final void b0(ResponseCheckVersionInfo responseCheckVersionInfo) {
        ResponseCheckVersionInfo responseCheckVersionInfoB;
        String str = f2709a;
        c.a.b.d.e.c(str, "continueCheckVersion");
        f2711c = false;
        if (!c.a.b.d.d.o(c.a.b.b.a.f2321b, "strategy.json") || (responseCheckVersionInfoB = c.a.g.l.o.b()) == null) {
            this.f2714f = responseCheckVersionInfo;
        } else {
            c.a.b.d.e.c(str, "checkVersion->savedStrategyVer = " + responseCheckVersionInfoB.strategy.strategyVer + ", responseStrategyVer = " + responseCheckVersionInfo.strategy.strategyVer);
            if (responseCheckVersionInfoB.strategy.strategyVer.equals(responseCheckVersionInfo.strategy.strategyVer)) {
                if (this.f2717i.w()) {
                    i(false);
                    return;
                }
                this.f2714f = responseCheckVersionInfo;
            } else if (!responseCheckVersionInfoB.strategy.isForce.equals(VehicleVersionInfo.FORCE_UPGRADE)) {
                this.f2716h.f(false);
                c.a.g.l.o.a();
                c.a.b.d.d.g(c.a.b.b.a.f2322c);
                this.f2714f = responseCheckVersionInfo;
            } else {
                if (this.f2717i.w()) {
                    i(false);
                    return;
                }
                this.f2714f = responseCheckVersionInfoB;
            }
        }
        i0(this.f2714f);
    }

    @Override // c.a.g.c
    public c.a.g.j.a c() {
        return this.k;
    }

    public final void c0() {
        r();
        this.f2716h.f(true);
        if (c.a.g.l.l.d()) {
            this.f2717i.X(2);
            this.f2717i.W(10);
        } else {
            this.f2717i.X(0);
            this.f2717i.W(0);
        }
        i(false);
        this.k.b(this.f2714f);
    }

    @Override // c.a.g.c
    public void d() {
        this.f2716h.r(2);
        c.a.g.h.e eVar = new c.a.g.h.e(ResponseLoginInfo.class, new j());
        if (TextUtils.isEmpty(this.f2717i.c()) || TextUtils.isEmpty(this.f2717i.E())) {
            this.f2716h.u();
        }
        RequestLoginInfo requestLoginInfo = new RequestLoginInfo();
        requestLoginInfo.vin = this.f2717i.c();
        requestLoginInfo.iccid = this.f2717i.E();
        requestLoginInfo.uuid = this.f2717i.B();
        this.l.a(eVar, c.a.g.l.h.f(requestLoginInfo));
    }

    public void d0(EcuVersionInfo ecuVersionInfo) {
        this.f2716h.r(11);
        this.x = new c.a.g.h.c(ecuVersionInfo, new c(ecuVersionInfo));
        String str = ecuVersionInfo.versionPath;
        if (!TextUtils.isEmpty(ecuVersionInfo.dcdnVersionPath)) {
            str = ecuVersionInfo.dcdnVersionPath;
        }
        this.l.k(this.x, str, c.a.g.l.g.a(ecuVersionInfo.fileDir, ecuVersionInfo.fileName));
    }

    @Override // c.a.g.c
    public boolean e() {
        c.a.b.d.e.c(f2709a, "getUserClickUpgradeFlag = " + f2712d);
        return f2712d;
    }

    public final void e0(final ResponseCheckVersionInfo responseCheckVersionInfo) {
        c.a.b.d.e.c(f2709a, "getAdditionCheckVersion");
        if (c.a.g.l.q.e()) {
            String strF = this.f2717i.f();
            String strB = c.a.b.d.n.b("yyyy-MM-dd");
            if (!TextUtils.isEmpty(strF) && strB.equals(strF)) {
                b0(responseCheckVersionInfo);
                return;
            }
        }
        c.a.b.d.d.g(c.a.b.b.a.o);
        c.a.b.d.l.d().a(new Runnable() { // from class: c.a.g.i.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f2705c.k0(responseCheckVersionInfo);
            }
        });
    }

    @Override // c.a.g.c
    public void f() {
        String str = f2709a;
        c.a.b.d.e.a(str, "stopDownload = " + this.y);
        if (this.x == null || this.y != 11) {
            return;
        }
        c.a.b.d.e.a(str, "mFileDownloadObserver.interrupt ");
        this.x.h();
        this.x = null;
    }

    public final void f0(final ResponseVersionSyncInfo responseVersionSyncInfo) {
        c.a.b.d.e.c(f2709a, "getAdditionVersionSync");
        if (c.a.g.l.q.e()) {
            String strF = this.f2717i.f();
            String strB = c.a.b.d.n.b("yyyy-MM-dd");
            if (!TextUtils.isEmpty(strF) && strB.equals(strF)) {
                a0();
                return;
            }
        }
        c.a.b.d.d.g(c.a.b.b.a.o);
        c.a.b.d.l.d().a(new Runnable() { // from class: c.a.g.i.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f2707c.m0(responseVersionSyncInfo);
            }
        });
    }

    @Override // c.a.g.c
    public void g() throws Throwable {
        ResponseCheckVersionInfo.StrategyBean strategyBean;
        List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list;
        c.a.b.d.e.c(f2709a, "continueDownload");
        ResponseCheckVersionInfo responseCheckVersionInfoB = c.a.g.l.o.b();
        if (responseCheckVersionInfoB == null || (strategyBean = responseCheckVersionInfoB.strategy) == null || (list = strategyBean.sequence) == null || list.size() <= 0 || TextUtils.isEmpty(responseCheckVersionInfoB.strategy.strategyVer)) {
            i(false);
        } else {
            this.f2714f = responseCheckVersionInfoB;
            i0(responseCheckVersionInfoB);
        }
    }

    public void g0() {
        this.f2716h.r(3);
        c.a.g.h.e eVar = new c.a.g.h.e(ResponseGetEcuListInfo.class, new k());
        RequestGetEcuListInfo requestGetEcuListInfo = new RequestGetEcuListInfo();
        requestGetEcuListInfo.version = this.f2717i.e();
        requestGetEcuListInfo.uuid = this.f2717i.B();
        this.l.c(eVar, c.a.g.l.h.f(requestGetEcuListInfo));
    }

    @Override // c.a.g.c
    public void h(String str) {
        this.k.c(str);
    }

    public final void h0(List<ResponseCheckVersionInfo.StrategyBean.SequenceBean> list, List<EcuVersionInfo> list2, boolean z, boolean z2) {
        String str;
        String str2;
        if (list == null || list.isEmpty() || list2 == null || list2.isEmpty()) {
            return;
        }
        for (EcuVersionInfo ecuVersionInfo : list2) {
            if (ecuVersionInfo != null) {
                if (!TextUtils.isEmpty(ecuVersionInfo.ecuId) && (TextUtils.isEmpty(ecuVersionInfo.fileDir) || TextUtils.isEmpty(ecuVersionInfo.fileName))) {
                    ecuVersionInfo.isTargetPackage = z;
                    ecuVersionInfo.isPrimaryPart = z2;
                    if (z2) {
                        if (!TextUtils.isEmpty(ecuVersionInfo.ecuSoftCode) && !TextUtils.isEmpty(ecuVersionInfo.ecuSoftVer)) {
                            Iterator<ResponseCheckVersionInfo.StrategyBean.SequenceBean> it = list.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    str = "";
                                    break;
                                }
                                ResponseCheckVersionInfo.StrategyBean.SequenceBean next = it.next();
                                if (!TextUtils.isEmpty(next.ecuDiaId) && TextUtils.equals(next.ecuId, ecuVersionInfo.ecuId)) {
                                    str = next.ecuDiaId.split("/")[0];
                                    break;
                                }
                            }
                            String str3 = ecuVersionInfo.ecuId + "_" + str + "_" + ecuVersionInfo.ecuSoftCode + "_" + ecuVersionInfo.ecuSoftVer.replace(".", "");
                            ecuVersionInfo.fileDir = z ? c.a.b.b.a.f2323d + str3 + File.separator : c.a.b.b.a.f2324e + str3 + File.separator;
                            ecuVersionInfo.fileName = String.format("target_part.zip", str, ecuVersionInfo.ecuSoftCode, ecuVersionInfo.ecuSoftVer);
                            ecuVersionInfo.targetPath = ecuVersionInfo.fileDir + ecuVersionInfo.fileName;
                            ecuVersionInfo.decodeTargetPath = ecuVersionInfo.fileDir + "target.part";
                            ecuVersionInfo.targetZipPath = ecuVersionInfo.fileDir + "target.zip";
                            ecuVersionInfo.targetUNZipPath = ecuVersionInfo.fileDir + "target" + File.separator;
                            ResultCodeConsts.EcuCodeEnum ecuCodeEnum = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_UNLOAD;
                            ecuVersionInfo.statusCode = String.valueOf(ecuCodeEnum.ecuCode);
                            ecuVersionInfo.statusCodeMsg = this.f2715g.getString(ecuCodeEnum.ecuCodeMsg);
                        }
                    } else if (!TextUtils.isEmpty(ecuVersionInfo.ecuConfigCode)) {
                        if (!TextUtils.isEmpty(ecuVersionInfo.ecuConfigVer)) {
                            Iterator<ResponseCheckVersionInfo.StrategyBean.SequenceBean> it2 = list.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    str2 = "";
                                    break;
                                }
                                ResponseCheckVersionInfo.StrategyBean.SequenceBean next2 = it2.next();
                                if (!TextUtils.isEmpty(next2.ecuDiaId) && TextUtils.equals(next2.ecuId, ecuVersionInfo.ecuId)) {
                                    str2 = next2.ecuDiaId.split("/")[0];
                                    break;
                                }
                            }
                            String str4 = ecuVersionInfo.ecuId + "_" + str2 + "_" + ecuVersionInfo.ecuConfigCode + "_" + ecuVersionInfo.ecuConfigVer.replace(".", "");
                            ecuVersionInfo.fileDir = z ? c.a.b.b.a.f2323d + str4 + File.separator : c.a.b.b.a.f2324e + str4 + File.separator;
                            ecuVersionInfo.fileName = String.format("target_part.zip", str2, ecuVersionInfo.ecuConfigCode, ecuVersionInfo.ecuConfigVer);
                            ecuVersionInfo.targetPath = ecuVersionInfo.fileDir + ecuVersionInfo.fileName;
                            ecuVersionInfo.decodeTargetPath = ecuVersionInfo.fileDir + "target.part";
                            ecuVersionInfo.targetZipPath = ecuVersionInfo.fileDir + "target.zip";
                            ecuVersionInfo.targetUNZipPath = ecuVersionInfo.fileDir + "target" + File.separator;
                            ResultCodeConsts.EcuCodeEnum ecuCodeEnum2 = ResultCodeConsts.EcuCodeEnum.DOWNLOAD_PACKAGES_UNLOAD;
                            ecuVersionInfo.statusCode = String.valueOf(ecuCodeEnum2.ecuCode);
                            ecuVersionInfo.statusCodeMsg = this.f2715g.getString(ecuCodeEnum2.ecuCodeMsg);
                        }
                    }
                }
            }
        }
    }

    @Override // c.a.g.c
    public void i(boolean z) {
        f2710b = z;
        c.a.b.d.e.c(f2709a, "setLoadingDataFlag = " + f2710b);
        if (z) {
            return;
        }
        o(false);
        this.f2716h.r(0);
    }

    public final void i0(ResponseCheckVersionInfo responseCheckVersionInfo) {
        if (!c.a.g.l.o.f(responseCheckVersionInfo)) {
            c.a.b.d.e.b(f2709a, "responseCheckVersionInfo is not legal!");
            this.f2716h.d("responseCheckVersionInfo is not legal!");
            i(false);
            c.a.g.l.o.h(responseCheckVersionInfo);
            return;
        }
        h0(responseCheckVersionInfo.strategy.sequence, responseCheckVersionInfo.targetPackageInfo, true, true);
        h0(responseCheckVersionInfo.strategy.sequence, responseCheckVersionInfo.sourcePackageInfo, false, true);
        h0(responseCheckVersionInfo.strategy.sequence, responseCheckVersionInfo.configPackageInfo, true, false);
        h0(responseCheckVersionInfo.strategy.sequence, responseCheckVersionInfo.configSourceInfo, false, false);
        Z(responseCheckVersionInfo);
    }

    @Override // c.a.g.c
    public void j(String str) {
        this.l.b(new c.a.g.h.e(ResponseStatusCodeInfo.class, new q()), str);
    }

    @Override // c.a.g.c
    public void k() {
        c.a.b.d.e.c(f2709a, "remotePre");
    }

    @Override // c.a.g.c
    public void l(String str) {
        this.l.g(new c.a.g.h.e(ResponseStatusCodeInfo.class, new p()), str);
    }

    @Override // c.a.g.c
    public void m(String str) {
        this.l.h(new c.a.g.h.e(ResponseStatusCodeInfo.class, new b(str)), str);
    }

    @Override // c.a.g.c
    public boolean n() {
        c.a.b.d.e.c(f2709a, "getLoadingDataFlag = " + f2710b);
        return f2710b;
    }

    public void n0() {
        this.f2716h.r(1);
        c.a.g.h.e eVar = new c.a.g.h.e(ResponseStatusCodeInfo.class, new i());
        RequestRegisterInfo requestRegisterInfo = new RequestRegisterInfo();
        requestRegisterInfo.vin = this.f2717i.c();
        requestRegisterInfo.iccid = this.f2717i.E();
        this.l.i(eVar, c.a.g.l.h.f(requestRegisterInfo));
    }

    @Override // c.a.g.c
    public void o(boolean z) {
        f2712d = z;
        c.a.b.d.e.c(f2709a, "setUserClickUpgradeFlag = " + f2712d);
    }

    public final void o0() {
        this.f2716h.x(f2713e, this.p.size());
        c.a.b.d.l.d().a(new h());
    }

    @Override // c.a.g.c
    public void p(String str, Map<String, Object> map, Map<String, File> map2) {
        this.l.e(new c.a.g.h.e(ResponseStatusCodeInfo.class, new a(str)), map, map2, this.f2717i.m());
    }

    public final void p0(String str, int i2) {
        String str2 = f2709a;
        c.a.b.d.e.c(str2, "translateDownLoadSuccess, translateSecretKey = " + str + ", downloadType=" + i2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String str3 = c.a.b.b.a.o;
        sb.append(str3);
        sb.append("translate.json.enc.zip");
        File file = new File(sb.toString());
        c.a.b.d.e.c(str2, "packageFile = " + file.getAbsolutePath());
        if (!file.exists()) {
            c.a.b.d.e.c(str2, "packageFile not exists");
            return;
        }
        c.a.b.d.e.c(str2, "解压路径 unzipPath = " + str3);
        try {
            c.a.f.a.a.b(file, str3, null);
            if (new File(str3).exists()) {
                boolean zC = this.j.c(str3, str);
                c.a.b.d.e.c(str2, "verifyFlag = " + zC);
                if (zC) {
                    this.f2717i.K(c.a.b.d.n.b("yyyy-MM-dd"));
                }
            } else {
                c.a.b.d.e.c(str2, "unzipFile not exists");
            }
        } catch (Exception e2) {
            c.a.b.d.e.c(f2709a, "translateDownLoadSuccess 解密失败");
            this.k.e(2, Locale.getDefault().getLanguage(), i2);
            e2.printStackTrace();
        }
    }

    @Override // c.a.g.c
    public void q(int i2) {
        c.a.b.d.e.a(f2709a, "setDownloadState = " + i2);
        this.y = i2;
    }

    public void q0() {
        this.f2716h.r(4);
        c.a.g.h.e eVar = new c.a.g.h.e(ResponseVersionSyncInfo.class, new l());
        RequestVersionSyncInfo requestVersionSyncInfo = new RequestVersionSyncInfo();
        requestVersionSyncInfo.version = this.f2717i.e();
        requestVersionSyncInfo.uuid = this.f2717i.B();
        ArrayList arrayList = new ArrayList();
        for (DiagnoseEcuInfo diagnoseEcuInfo : this.o) {
            RequestVersionSyncInfo.EcuInfo ecuInfo = new RequestVersionSyncInfo.EcuInfo();
            ecuInfo.ecuId = diagnoseEcuInfo.ecuId;
            ecuInfo.ecuSoftCode = diagnoseEcuInfo.ecuSoftCode;
            ecuInfo.ecuSoftVer = diagnoseEcuInfo.ecuSoftVer;
            arrayList.add(ecuInfo);
        }
        requestVersionSyncInfo.ecuInfo = arrayList;
        this.l.j(eVar, c.a.g.l.h.f(requestVersionSyncInfo));
    }

    @Override // c.a.g.c
    public void r() {
        ResponseCheckVersionInfo responseCheckVersionInfo = this.f2714f;
        if (responseCheckVersionInfo != null) {
            c.a.g.l.o.g(responseCheckVersionInfo);
        }
    }

    @Override // c.a.g.c
    public boolean s() {
        c.a.b.d.e.c(f2709a, "getNoNewVersionFlag = " + f2711c);
        return f2711c;
    }

    @Override // c.a.g.c
    public void t() {
        ArrayList arrayList = new ArrayList();
        r rVar = new r(this, null);
        c.a.b.d.l.d().c(rVar, 5000L);
        c.a.b.d.e.c(f2709a, "clearDiagnosticTroubleCode->clearDtcList.size() = " + arrayList.size());
        this.m.clearDiagnosticTroubleCode(arrayList, new e(rVar));
    }

    @Override // c.a.g.c
    public void u() {
        List<DiagnoseEcuInfo> listA = c.a.g.l.c.a();
        if (listA == null || listA.size() <= 0) {
            this.f2716h.y();
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (DiagnoseEcuInfo diagnoseEcuInfo : listA) {
            arrayList.add(new RequestGetDTCInfo(diagnoseEcuInfo.ecuId, diagnoseEcuInfo.ecuDiaId));
        }
        t tVar = new t(this, null);
        c.a.b.d.l.d().c(tVar, 120000L);
        c.a.b.d.e.c(f2709a, "getDiagnosticTroubleCode->getDtcList.size() = " + arrayList.size());
        this.m.getDiagnosticTroubleCode(arrayList, new C0053f(tVar));
    }
}
