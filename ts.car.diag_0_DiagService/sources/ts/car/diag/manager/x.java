package ts.car.diag.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.google.gson.Gson;
import java.io.File;
import java.util.List;
import ts.car.diag.bean.VrPackageBean;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    c f1639a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1640b;
    Context d;
    private Gson f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1641c = 0;
    private String e = "";

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 101) {
                x.this.g();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            q.h("VrManager", "onReceive: action= " + action);
            if ("persist.sys.factory.voice.check.result".equals(action)) {
                String stringExtra = intent.getStringExtra("result");
                if (!TextUtils.isEmpty(stringExtra)) {
                    x.this.e = stringExtra;
                }
                q.h("VrManager", "onReceive: get result, result= " + stringExtra + " ,mVrCheckResult = " + x.this.e);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        void a(byte b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g() {
        /*
            r8 = this;
            java.lang.String r0 = "persist.sys.factory.voice.check.result"
            java.lang.String r1 = ""
            java.lang.String r0 = android.os.SystemProperties.get(r0, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "getResult: vrResult="
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "VrManager"
            ts.car.diag.manager.q.h(r2, r1)
            ts.car.diag.manager.x$a r1 = r8.f1640b
            r3 = 101(0x65, float:1.42E-43)
            if (r1 == 0) goto L27
            r1.removeMessages(r3)
        L27:
            int r1 = r0.hashCode()
            r4 = 2150174(0x20cf1e, float:3.013036E-39)
            r5 = 0
            r6 = -1
            r7 = 1
            if (r1 == r4) goto L43
            r4 = 2448401(0x255c11, float:3.43094E-39)
            if (r1 == r4) goto L39
            goto L4d
        L39:
            java.lang.String r1 = "PASS"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L4d
            r1 = r5
            goto L4e
        L43:
            java.lang.String r1 = "FAIL"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L4d
            r1 = r7
            goto L4e
        L4d:
            r1 = r6
        L4e:
            if (r1 == 0) goto L98
            if (r1 == r7) goto L90
            int r1 = r8.f1641c
            int r1 = r1 + r7
            r8.f1641c = r1
            r4 = 10
            if (r1 <= r4) goto L68
            java.lang.String r0 = "getResult: retryTime >  10, return invalid"
            ts.car.diag.manager.q.b(r2, r0)
            ts.car.diag.manager.x$c r8 = r8.f1639a
            if (r8 == 0) goto L9f
            r8.a(r6)
            goto L9f
        L68:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "getResult: vrResult = "
            r1.append(r4)
            r1.append(r0)
            java.lang.String r0 = ",invalid and retry "
            r1.append(r0)
            int r0 = r8.f1641c
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            ts.car.diag.manager.q.b(r2, r0)
            ts.car.diag.manager.x$a r8 = r8.f1640b
            if (r8 == 0) goto L9f
            r0 = 1000(0x3e8, double:4.94E-321)
            r8.sendEmptyMessageDelayed(r3, r0)
            goto L9f
        L90:
            ts.car.diag.manager.x$c r8 = r8.f1639a
            if (r8 == 0) goto L9f
            r8.a(r7)
            goto L9f
        L98:
            ts.car.diag.manager.x$c r8 = r8.f1639a
            if (r8 == 0) goto L9f
            r8.a(r5)
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ts.car.diag.manager.x.g():void");
    }

    private void j() {
        if (this.f1640b == null) {
            HandlerThread handlerThread = new HandlerThread("VrManager");
            handlerThread.start();
            this.f1640b = new a(handlerThread.getLooper());
        }
        this.f1640b.removeMessages(101);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String k(java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            if (r2 != 0) goto L23
            java.lang.String r1 = "VrManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r2.<init>()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r3 = "readFileContent file is not exists "
            r2.append(r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r2.append(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            ts.car.diag.manager.q.b(r1, r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            return r0
        L23:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            r1.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
        L37:
            java.lang.String r2 = r5.readLine()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            if (r2 == 0) goto L41
            r1.append(r2)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            goto L37
        L41:
            r5.close()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            r5.close()     // Catch: java.lang.Exception -> L4c
            goto L50
        L4c:
            r5 = move-exception
            r5.printStackTrace()
        L50:
            return r0
        L51:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L6f
        L56:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L5f
        L5b:
            r5 = move-exception
            goto L6f
        L5d:
            r5 = move-exception
            r1 = r0
        L5f:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            if (r1 == 0) goto L6c
            r1.close()     // Catch: java.lang.Exception -> L68
            goto L6c
        L68:
            r5 = move-exception
            r5.printStackTrace()
        L6c:
            return r0
        L6d:
            r5 = move-exception
            r0 = r1
        L6f:
            if (r0 == 0) goto L79
            r0.close()     // Catch: java.lang.Exception -> L75
            goto L79
        L75:
            r0 = move-exception
            r0.printStackTrace()
        L79:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: ts.car.diag.manager.x.k(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(ts.car.diag.manager.x.c r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "checkVrResult mVrCheckResult= "
            r0.append(r1)
            java.lang.String r1 = r4.e
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "VrManager"
            ts.car.diag.manager.q.h(r1, r0)
            r4.f1639a = r5
            r5 = 0
            r4.f1641c = r5
            r4.j()
            java.lang.String r0 = r4.e
            int r1 = r0.hashCode()
            r2 = 2150174(0x20cf1e, float:3.013036E-39)
            r3 = 1
            if (r1 == r2) goto L3c
            r2 = 2448401(0x255c11, float:3.43094E-39)
            if (r1 == r2) goto L32
            goto L46
        L32:
            java.lang.String r1 = "PASS"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L46
            r0 = r5
            goto L47
        L3c:
            java.lang.String r1 = "FAIL"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L46
            r0 = r3
            goto L47
        L46:
            r0 = -1
        L47:
            if (r0 == 0) goto L5d
            if (r0 == r3) goto L55
            ts.car.diag.manager.x$a r4 = r4.f1640b
            r5 = 101(0x65, float:1.42E-43)
            r0 = 5000(0x1388, double:2.4703E-320)
            r4.sendEmptyMessageDelayed(r5, r0)
            goto L64
        L55:
            ts.car.diag.manager.x$c r4 = r4.f1639a
            if (r4 == 0) goto L64
            r4.a(r3)
            goto L64
        L5d:
            ts.car.diag.manager.x$c r4 = r4.f1639a
            if (r4 == 0) goto L64
            r4.a(r5)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ts.car.diag.manager.x.d(ts.car.diag.manager.x$c):void");
    }

    public String e() {
        String str = SystemProperties.get("ro.product.version.soc.name", "");
        q.h("VrManager", "getCurrentSocVersion " + str);
        return str;
    }

    public String f() {
        String str = SystemProperties.get("sys.DSV.factory.countrycode", "");
        q.h("VrManager", "getSystemCountryCode COUNTRY_CODE_DSV :" + str);
        if (TextUtils.isEmpty(str) || "-1".equals(str)) {
            str = SystemProperties.get("sys.byd.countrycode", "");
        }
        q.h("VrManager", "getCurrentSystemCountryCode " + str);
        return str;
    }

    public String h() {
        List<VrPackageBean.ComponentsBean> components;
        File file = new File("/vr_ext/BYDAssistant_CR/data/");
        if (!file.exists()) {
            q.b("VrManager", "getVrInfo file not exists /vr_ext/BYDAssistant_CR/data/");
            return "";
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            q.b("VrManager", "getVrInfo file list is empty /vr_ext/BYDAssistant_CR/data/");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : list) {
            q.h("VrManager", "getVrInfo fileName " + str);
            if (str.startsWith("data_")) {
                stringBuffer.append(str);
                File file2 = new File("/vr_ext/BYDAssistant_CR/data/" + str + "/version/version.json");
                String version = " ";
                if (file2.exists()) {
                    String strK = k(file2.getAbsolutePath());
                    q.h("VrManager", "getVrInfo packageVersionJson " + strK);
                    VrPackageBean vrPackageBean = (VrPackageBean) this.f.fromJson(strK, VrPackageBean.class);
                    if (vrPackageBean != null && (components = vrPackageBean.getComponents()) != null && components.size() > 0) {
                        int i = 0;
                        while (true) {
                            if (i >= components.size()) {
                                break;
                            }
                            VrPackageBean.ComponentsBean componentsBean = components.get(i);
                            if (componentsBean != null && "DATAPACK_VERSION".equals(componentsBean.getName())) {
                                version = componentsBean.getVersion();
                                break;
                            }
                            i++;
                        }
                    }
                    stringBuffer.append(version);
                } else {
                    q.b("VrManager", "getVrInfo " + str + " versionJsonFile is not exit");
                    stringBuffer.append(" ");
                }
            }
        }
        String string = stringBuffer.toString();
        q.h("VrManager", "vrInfo " + string);
        return string;
    }

    public void i(Context context) {
        this.d = context;
        this.f = new Gson();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("persist.sys.factory.voice.check.result");
        this.d.getApplicationContext().registerReceiver(new b(), intentFilter);
    }

    public boolean l() {
        try {
            this.e = "";
            q.h("VrManager", "startRequestDiagVr");
            Intent intent = new Intent();
            intent.setAction("intent.action.bydvoice.factory.check");
            this.d.sendBroadcast(intent);
            q.h("VrManager", "startRequestDiagVr send intent.action.bydvoice.factory.check");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            q.b("VrManager", "startRequestDiagVr error");
            return false;
        }
    }
}
