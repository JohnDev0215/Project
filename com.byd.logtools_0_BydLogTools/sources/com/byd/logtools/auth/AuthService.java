package com.byd.logtools.auth;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Window;
import com.byd.logtools.R;
import com.byd.logtools.a;
import com.byd.logtools.auth.data.CarInfo;
import com.byd.logtools.auth.data.QrCodeData;
import com.byd.logtools.auth.data.QrCodePostData;
import com.byd.logtools.auth.data.QrCodeResponse;
import com.byd.logtools.auth.utils.Constant$Status;
import com.byd.logtools.auth.utils.f;
import com.byd.logtools.auth.utils.g;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.TreeMap;
import java.util.UUID;
import retrofit2.r;
import retrofit2.s;

/* JADX INFO: loaded from: classes.dex */
public class AuthService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3110c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CountDownTimer f3111d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private QrCodePostData f3112e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private s f3113f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private com.byd.logtools.b f3114g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private d f3115h;
    private Handler i;
    private com.byd.logtools.auth.f.b j;
    private f k;
    private d.a.a.a l;
    private com.byd.logtools.auth.utils.c m;

    class a implements com.byd.logtools.auth.f.a {
        a() {
        }

        @Override // com.byd.logtools.auth.f.a
        public void a() {
            BydLogHelper.logD("cancelRequestFromUser");
            AuthService.this.o();
            AuthService.this.C(0, "Authentication is canceled by user");
        }

        @Override // com.byd.logtools.auth.f.a
        public void b(int i) {
            AuthService.this.z(i);
        }
    }

    class b extends CountDownTimer {
        b(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            BydLogHelper.logD("onFinish: ");
            if (AuthService.this.j != null) {
                AuthService.this.j.n(Constant$Status.EXPIRE);
            }
            AuthService.this.f3111d = null;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (AuthService.this.j != null) {
                AuthService.this.j.j(j);
            }
            int i = (((int) j) / 1000) + 1;
            if (i == 300 || i == 0 || i % 5 != 0) {
                return;
            }
            AuthService.this.A();
        }
    }

    class c implements retrofit2.f<QrCodeResponse> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3118a;

        c(int i) {
            this.f3118a = i;
        }

        @Override // retrofit2.f
        public void a(retrofit2.d<QrCodeResponse> dVar, Throwable th) {
            BydLogHelper.logE("request onFailure: " + th.getMessage());
            AuthService.this.r(this.f3118a, Constant$Status.FAIL);
        }

        @Override // retrofit2.f
        public void b(retrofit2.d<QrCodeResponse> dVar, r<QrCodeResponse> rVar) {
            if (rVar == null || rVar.a() == null) {
                BydLogHelper.logD("onResponse: response is null ");
                return;
            }
            BydLogHelper.logW("AuthService", "onResponse: " + rVar.a() + " countDownTimer:" + AuthService.this.f3111d);
            if (AuthService.this.f3111d == null) {
                return;
            }
            int code = rVar.a().getCode();
            if (code == 0) {
                AuthService.this.r(this.f3118a, Constant$Status.SUCCESS);
            } else if (code != 4) {
                AuthService.this.r(this.f3118a, Constant$Status.FAIL);
            }
        }
    }

    public class d extends a.AbstractBinderC0053a {
        public d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void k(com.byd.logtools.b bVar, int i, String str) {
            int i2 = Settings.System.getInt(AuthService.this.getContentResolver(), "sys.byd.safety.warn", 0);
            int i3 = SystemProperties.getInt("persist.byd.privacy.policy", 0);
            BydLogHelper.logD("warnState:" + i2 + ", privacyState:" + i3);
            if (bVar == null) {
                return;
            }
            if (i2 == 0 || i3 == 0) {
                try {
                    bVar.d(i, 0, "Not allow to show dialog");
                    return;
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (AuthService.this.q() != 1) {
                BydLogHelper.logD("Navibar mode power off or screen saver, screen state:" + AuthService.this.q());
                try {
                    bVar.d(i, 0, "Not allow to show dialog");
                    return;
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            if (i < 0 || i > 3) {
                BydLogHelper.logD("Invalid business type");
                try {
                    bVar.d(i, 0, "Invalid business type");
                    return;
                } catch (RemoteException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            if (AuthService.this.f3114g == null) {
                AuthService.this.f3114g = bVar;
                AuthService.this.f3110c = i;
                AuthService.this.D(str);
            } else {
                BydLogHelper.logD("There is already a request in progress");
                try {
                    bVar.d(i, 0, "There is already a request in progress");
                } catch (RemoteException e5) {
                    e5.printStackTrace();
                }
            }
        }

        @Override // com.byd.logtools.a
        public void b(final int i, final String str, final com.byd.logtools.b bVar) throws RemoteException {
            BydLogHelper.logI("AuthService", "requestAuth , type = " + i + ", name = " + str + ", callback " + bVar + ", flavor = common");
            AuthService.this.i.post(new Runnable() { // from class: com.byd.logtools.auth.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3124b.k(bVar, i, str);
                }
            });
        }

        @Override // com.byd.logtools.a
        public void c(int i) throws RemoteException {
            BydLogHelper.logI("AuthService", "cancelRequest, businessType:" + i + ", currentType:" + AuthService.this.f3110c);
            if (AuthService.this.f3110c == i) {
                if (AuthService.this.j != null) {
                    AuthService.this.j.dismiss();
                }
                AuthService.this.o();
                AuthService.this.C(0, "Authentication is canceled by user");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        com.byd.logtools.auth.f.b bVar = this.j;
        if (bVar == null || !bVar.isShowing()) {
            return;
        }
        B(this.j.q);
    }

    private void B(int i) {
        String str = f3109b;
        if (!s(i, str)) {
            r(i, Constant$Status.FAIL);
        } else {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (this.f3113f == null) {
                this.f3113f = com.byd.logtools.auth.e.a.a(str);
            }
            ((com.byd.logtools.auth.e.b) this.f3113f.b(com.byd.logtools.auth.e.b.class)).a(this.f3112e).e(new c(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(int i, String str) {
        BydLogHelper.logD("sendAuthResult: code = " + i);
        com.byd.logtools.b bVar = this.f3114g;
        if (bVar != null) {
            try {
                bVar.d(this.f3110c, i, str);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        this.f3110c = -1;
        this.f3114g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(String str) {
        if (this.j == null) {
            this.j = new com.byd.logtools.auth.f.b(this, R.style.QrCodeDialogS);
        }
        Window window = this.j.getWindow();
        this.j.getWindow().setType(2001);
        this.j.show();
        this.j.getWindow().setLayout(-1, -1);
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setNavigationBarColor(0);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(5889);
        this.j.g(str);
        z(this.j.q);
        this.j.f(new a());
        this.j.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.logtools.auth.c
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f3128b.y(dialogInterface);
            }
        });
    }

    private void E() {
        BydLogHelper.logD("startRequest");
        o();
        this.f3111d = new b(300000L, 1000L).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CountDownTimer countDownTimer = this.f3111d;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f3111d = null;
        }
    }

    private String p(int i) {
        BydLogHelper.logW("AuthService", "initQrCodeData: scanType" + i);
        String strH = com.byd.logtools.auth.utils.a.h();
        String strG = com.byd.logtools.auth.utils.a.g(16);
        String strR = this.k.r();
        String strB = g.b(this);
        if (strR != null && strR.length() >= 6) {
            strB = strB + strR.substring(strR.length() - 6);
        }
        String strR2 = new com.google.gson.d().r(new CarInfo(g.a(this), strR));
        String strD = com.byd.logtools.auth.utils.a.d(strH, "byd_auth", 2047, 256, strR2, strG);
        String strJ = com.byd.logtools.auth.utils.a.j(strR2, false);
        QrCodeData qrCodeData = new QrCodeData(strD, strB, System.currentTimeMillis(), UUID.randomUUID().toString(), this.f3110c, "V1");
        this.f3112e = new QrCodePostData(qrCodeData.getVid(), qrCodeData.getQrTime(), qrCodeData.getTaskId(), strH, strG, strJ, qrCodeData.getType());
        BydLogHelper.logD("initQrCodeData: " + qrCodeData + "\n " + this.f3112e);
        TreeMap treeMap = new TreeMap();
        treeMap.put("qrValue", strD);
        treeMap.put("vid", qrCodeData.getVid());
        treeMap.put("qrTime", Long.valueOf(qrCodeData.getQrTime()));
        treeMap.put("taskId", qrCodeData.getTaskId());
        treeMap.put("type", Integer.valueOf(this.f3110c));
        treeMap.put("version", "V1");
        if (i == 1) {
            treeMap.put("countryCode", String.valueOf(this.m.a()));
        }
        return new com.google.gson.d().r(treeMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int q() {
        return this.l.k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(final int i, final Constant$Status constant$Status) {
        if (this.f3111d == null) {
            return;
        }
        o();
        com.byd.logtools.auth.f.b bVar = this.j;
        if (bVar != null) {
            bVar.n(constant$Status);
        }
        this.i.postDelayed(new Runnable() { // from class: com.byd.logtools.auth.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f3121b.u(constant$Status, i);
            }
        }, 1000L);
    }

    private boolean s(int i, String str) {
        boolean zB = com.byd.logtools.auth.utils.d.b();
        boolean zK = this.k.K();
        BydLogHelper.logD("request: type = " + i + " url = " + str + " isConnect = " + zB + " isApn3Enable = " + zK);
        return zB || zK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void u(Constant$Status constant$Status, int i) {
        if (constant$Status == Constant$Status.SUCCESS) {
            C(i != 1 ? 2 : 1, "Success");
        } else if (constant$Status == Constant$Status.FAIL) {
            C(0, "Network unavailable");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w() {
        this.m.d();
        f3109b = this.m.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y(DialogInterface dialogInterface) {
        this.j = null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        d dVar = new d();
        this.f3115h = dVar;
        return dVar;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.uiMode & 48;
        String str = "Unknown";
        String str2 = i != 16 ? i != 32 ? "Unknown" : "Dark" : "Light";
        String languageTag = configuration.getLocales().get(0).toLanguageTag();
        int i2 = configuration.orientation;
        if (i2 == 1) {
            str = "Portrait";
        } else if (i2 == 2) {
            str = "Landscape";
        }
        BydLogHelper.logD("onConfigurationChanged uiMode = " + str2 + ", language = " + languageTag + ", orientation = " + str + ", mDialog = " + this.j);
        com.byd.logtools.auth.f.b bVar = this.j;
        if (bVar != null) {
            bVar.d();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        BydLogHelper.logD("onCreate");
        super.onCreate();
        BYDAutoBodyworkDevice.getInstance(this);
        this.i = new Handler();
        this.m = com.byd.logtools.auth.utils.c.b(this);
        new Thread(new Runnable() { // from class: com.byd.logtools.auth.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f3129b.w();
            }
        }).start();
        f fVar = new f();
        this.k = fVar;
        fVar.L();
        this.l = d.a.a.a.i(this, null);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.byd.logtools.auth.f.b bVar = this.j;
        if (bVar != null && bVar.isShowing()) {
            this.j.dismiss();
        }
        f fVar = this.k;
        if (fVar != null) {
            fVar.M();
            this.k = null;
        }
        if (this.l != null) {
            this.l = null;
        }
    }

    public void z(int i) {
        o();
        String strP = p(i);
        com.byd.logtools.auth.f.b bVar = this.j;
        if (bVar != null) {
            bVar.k(strP);
        }
        E();
    }
}
