package com.byd.secretcode.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.collectdata.AbsBYDAutoCollectDataListener;
import android.hardware.bydauto.collectdata.BYDAutoCollectDataDevice;
import android.hardware.bydauto.key.BYDAutoKeyDevice;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.byd.secretcode.Satellite.SatelliteActivity;
import com.byd.secretcode.audiopcmdebug.TestMicActivity;
import com.byd.secretcode.clusterdebug.ClusterDebugActivity;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import com.ts.avm.sdk.AvmManager;
import defpackage.jg;
import defpackage.ll;
import defpackage.mg;
import defpackage.tg;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AvmManager.c, AvmManager.d, ll {
    public static Dialog b;
    public TextView A;
    public TextView B;
    public TextView C;
    public TextView D;
    public TextView E;
    public TextView F;
    public TextView G;
    public TextView H;
    public TextView I;
    public CheckBox J;
    public CheckBox K;
    public CheckBox L;
    public CheckBox M;
    public CheckBox N;
    public CheckBox O;
    public CheckBox P;
    public CheckBox Q;
    public TextView R;
    public TextView S;
    public TextView T;
    public TextView U;
    public TextView V;
    public TextView W;
    public TextView X;
    public TextView Y;
    public TextView Z;
    public BYDAutoVersionDevice d;
    public BYDAutoTestDevice e;
    public BYDAutoTBoxDevice f;
    public BYDAutoPowerDevice g;
    public Context h;
    public AbsBYDAutoTestListener i;
    public BYDAutoCollectDataDevice l0;
    public TextView o;
    public TextView p;
    public TextView q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public TextView w;
    public TextView x;
    public TextView y;
    public TextView z;
    public String c = "byd_pad://byd_car_settings:2933/launcher?action=sys_language_settings";
    public boolean j = false;
    public boolean k = false;
    public AvmManager l = null;
    public final int m = 0;
    public final int n = 1;
    public BYDAutoKeyDevice a0 = null;
    public final String b0 = "com.byd.avm";
    public final String c0 = "com.byd.avm.MainActivity";
    public final String d0 = "bydavmmode";
    public final String e0 = "com.neusoft.na.ehp";
    public final String f0 = "com.neusoft.na.ehp.MainActivity";
    public final int g0 = 7;
    public CarSettings h0 = null;
    public final String i0 = "com.byd.traffic_monitor.SIM_INFO_UPLOAD";
    public final String j0 = "com.byd.trafficmonitor";
    public volatile boolean k0 = false;
    public AbsBYDAutoCollectDataListener m0 = new a();

    @SuppressLint({"HandlerLeak"})
    public Handler n0 = new c();

    public class a extends AbsBYDAutoCollectDataListener {
        public a() {
        }

        @Override // android.hardware.bydauto.collectdata.AbsBYDAutoCollectDataListener
        public void onCollision(int i) {
            super.onCollision(i);
            BydLogHelper.logE(AbsBYDAutoCollectDataListener.TAG, "onCollision value = " + i);
        }
    }

    public class b extends AbsBYDAutoTestListener {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.k = true;
                MainActivity.this.K.setChecked(true);
            }
        }

        /* JADX INFO: renamed from: com.byd.secretcode.activity.MainActivity$b$b, reason: collision with other inner class name */
        public class RunnableC0006b implements Runnable {
            public RunnableC0006b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.j = true;
                MainActivity.this.J.setChecked(true);
            }
        }

        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onQNXLogStatusChanged(int i, boolean z) {
            super.onQNXLogStatusChanged(i, z);
            BydLogHelper.logW(AbsBYDAutoTestListener.TAG, "onQNXLogStatusChanged logtype = " + i + ", status = " + z);
            if (i == 1 && z) {
                MainActivity.this.runOnUiThread(new a());
            } else if (i == 2 && z) {
                MainActivity.this.runOnUiThread(new RunnableC0006b());
            }
        }
    }

    public class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 10001:
                    MainActivity.this.o.setText(((Object) MainActivity.this.o.getText()) + MainActivity.this.u());
                    break;
                case 10002:
                    if (MainActivity.this.e != null) {
                        MainActivity.this.e.getConsoleSuspandState();
                        MainActivity.this.e.getTCPDumpState();
                    }
                    break;
                case 10003:
                    if (MainActivity.this.l == null) {
                        MainActivity.this.l = AvmManager.c();
                    }
                    BydLogHelper.logD("MainActivity", "MSG_CLICK_AVM mAvmAvailable =" + MainActivity.this.k0);
                    MainActivity.this.l.a(AvmManager.Calibration.START);
                    MainActivity.this.n0.removeMessages(10004);
                    break;
                case 10004:
                    MainActivity mainActivity = MainActivity.this;
                    mg.b(mainActivity, mainActivity.getString(R.string.device_loading_timeout));
                    break;
                case 10005:
                    if (MainActivity.this.g != null) {
                        MainActivity.this.g.mcuReset();
                    }
                    break;
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainActivity.b.dismiss();
            MainActivity.this.A();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainActivity.b.dismiss();
        }
    }

    public class f implements DialogInterface.OnDismissListener {
        public f() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
        }
    }

    public final void A() {
        SystemProperties.set("persist.vendor.byd.gnss.mode.user", String.valueOf(1));
        String str = SystemProperties.get("persist.vendor.byd.gnss.mode");
        BydLogHelper.logE("MainActivity", "openInertialNavigation str = " + str);
        if ("0".equals(str)) {
            BydLogHelper.logE("MainActivity", "openInertialNavigation open");
            SystemProperties.set("persist.vendor.byd.gnss.mode", String.valueOf(1));
        } else {
            BydLogHelper.logE("MainActivity", "openInertialNavigation close");
            SystemProperties.set("persist.vendor.byd.gnss.mode", String.valueOf(0));
        }
        this.n0.sendEmptyMessageDelayed(10005, 1000L);
    }

    public final void B(Boolean bool) {
        if (this.e == null) {
            return;
        }
        if (bool.booleanValue()) {
            int iEnableTCPDump = this.e.enableTCPDump(true);
            int tcpDumpState = this.f.setTcpDumpState(true);
            BydLogHelper.logD("MainActivity", "open iviTcpDump = " + iEnableTCPDump + ", tBoxTcpDump = " + tcpDumpState);
            if (iEnableTCPDump == 0 && tcpDumpState == 0) {
                mg.a(this, getString(R.string.str_open_all_tcpdump_logs));
                return;
            }
            if (iEnableTCPDump == 0) {
                mg.a(this, getString(R.string.str_open_ivi_tcpdump_logs));
                return;
            } else if (tcpDumpState == 0) {
                mg.a(this, getString(R.string.str_open_tbox_tcpdump_logs));
                return;
            } else {
                mg.a(this, getString(R.string.str_open_tcpdump_logs_fail));
                return;
            }
        }
        int iEnableTCPDump2 = this.e.enableTCPDump(false);
        int tcpDumpState2 = this.f.setTcpDumpState(false);
        BydLogHelper.logD("MainActivity", "close iviTcpDump = " + iEnableTCPDump2 + ", tBoxTcpDump = " + tcpDumpState2);
        if (iEnableTCPDump2 == 0 && tcpDumpState2 == 0) {
            mg.a(this, getString(R.string.str_close_all_tcpdump_logs));
            return;
        }
        if (iEnableTCPDump2 == 0) {
            mg.a(this, getString(R.string.str_close_ivi_tcpdump_logs));
        } else if (tcpDumpState2 == 0) {
            mg.a(this, getString(R.string.str_close_tbox_tcpdump_logs));
        } else {
            mg.a(this, getString(R.string.str_close_tcpdump_logs_fail));
        }
    }

    public void C() {
        mg.b(this.h, getString(R.string.str_app_not_exist));
    }

    public final void D(Class cls) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, (Class<?>) cls));
        startActivity(intent);
    }

    public final void E(String str) {
        PackageManager packageManager = this.h.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        BydLogHelper.logE("MainActivity", "----switchLanguage----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.h.startService(intent);
        BydLogHelper.logE("MainActivity", "---- mContext.startService(in); = ");
    }

    public void ShowDialog() {
        b = new Dialog(this, R.style.NormalDialogStyle);
        View viewInflate = View.inflate(this, R.layout.dialog_guandao, null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_guandao_message);
        Button button = (Button) viewInflate.findViewById(R.id.bt_guandao_open);
        Button button2 = (Button) viewInflate.findViewById(R.id.bt_guandao_close);
        if ("1".equals(SystemProperties.get("persist.vendor.byd.gnss.mode"))) {
            textView.setText(getResources().getString(R.string.str_guandao_close_message));
        } else {
            textView.setText(getResources().getString(R.string.str_guandao_open_message));
        }
        b.setContentView(viewInflate);
        Window window = b.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        button.setOnClickListener(new d());
        button2.setOnClickListener(new e());
        b.setOnDismissListener(new f());
        b.show();
    }

    @Override // com.ts.avm.sdk.AvmManager.d
    public void a(int i) {
        BydLogHelper.logD("MainActivity", "mode " + i);
    }

    @Override // com.ts.avm.sdk.AvmManager.c
    public void b(int i, String str) {
        BydLogHelper.logD("MainActivity", "onStatusChanged: " + i + " " + str);
        if (i == 1) {
            this.k0 = true;
        }
    }

    @Override // defpackage.ll
    public void c() {
        BydLogHelper.logD("MainActivity", "onServiceConnected: ");
    }

    @Override // defpackage.ll
    public void d() {
        BydLogHelper.logD("MainActivity", "onServiceDisConnected: ");
    }

    public void f(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName(str, str2));
        intent.putExtra("flag", "Jump");
        context.startActivity(intent);
    }

    public final void initData() {
        jg.a();
        String strU = u();
        if (strU == null || strU.equals("null")) {
            this.n0.sendEmptyMessageDelayed(10001, 1000L);
        } else {
            this.o.setText(((Object) this.o.getText()) + strU);
        }
        this.q.setText(((Object) getResources().getText(R.string.str_ecu)) + t());
        BydLogHelper.logE("MainActivity", "app = " + SystemProperties.get("persist.vendor.byd.cfg.audio.anc.app.version") + ", data = " + SystemProperties.get("persist.vendor.byd.cfg.audio.anc.data.version") + ", String = " + getString(R.string.str_anc_app_version));
        TextView textView = this.Y;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.str_anc_app_version));
        sb.append(" ");
        sb.append(q());
        textView.setText(sb.toString());
        this.Z.setText(getString(R.string.str_anc_data_version) + " " + s());
        this.L.setChecked(SystemProperties.get("persist.sys.cloudlog", "1").equals("1"));
        this.Q.setChecked(tg.b().a(getApplicationContext()));
        b bVar = new b();
        this.i = bVar;
        this.e.registerListener((AbsBYDAutoTestListener) bVar);
        this.e.getConsoleSuspandState();
        this.e.getTCPDumpState();
        AvmManager avmManagerC = AvmManager.c();
        this.l = avmManagerC;
        avmManagerC.e(this);
        this.l.f(this);
        this.l.d(this.h, this);
    }

    public final void initView() {
        this.G = (TextView) findViewById(R.id.tv_countrycode);
        this.o = (TextView) findViewById(R.id.tv_version);
        this.p = (TextView) findViewById(R.id.tv_dsp_version_name);
        this.q = (TextView) findViewById(R.id.tv_ecu_version_name);
        this.r = (TextView) findViewById(R.id.tv_bydlogtool);
        this.s = (TextView) findViewById(R.id.tv_otatool);
        this.t = (TextView) findViewById(R.id.tv_materiallnfotool);
        this.u = (TextView) findViewById(R.id.tv_audiopcmdebugtool);
        this.v = (TextView) findViewById(R.id.tv_devtools);
        this.w = (TextView) findViewById(R.id.tv_cloud_services);
        this.x = (TextView) findViewById(R.id.tv_byddms);
        this.y = (TextView) findViewById(R.id.tv_btlogtools);
        this.z = (TextView) findViewById(R.id.tv_qtlogkit);
        this.A = (TextView) findViewById(R.id.tv_autostudy);
        this.B = (TextView) findViewById(R.id.tv_offline);
        this.C = (TextView) findViewById(R.id.tv_stresstest);
        this.D = (TextView) findViewById(R.id.tv_instrumenttest);
        this.E = (TextView) findViewById(R.id.tv_bydwirelesstools);
        this.F = (TextView) findViewById(R.id.tv_satellite);
        this.X = (TextView) findViewById(R.id.tv_devicenet);
        this.H = (TextView) findViewById(R.id.tv_language);
        this.I = (TextView) findViewById(R.id.tv_shujucaiji);
        this.J = (CheckBox) findViewById(R.id.rb_tcpdump);
        this.K = (CheckBox) findViewById(R.id.rb_consolesuspand);
        this.L = (CheckBox) findViewById(R.id.rb_cloudservicelog);
        this.M = (CheckBox) findViewById(R.id.rb_alarmlog);
        this.N = (CheckBox) findViewById(R.id.rb_enableramdumps);
        this.O = (CheckBox) findViewById(R.id.rb_enabledexopt);
        this.P = (CheckBox) findViewById(R.id.rb_autohallog);
        this.Q = (CheckBox) findViewById(R.id.rb_flymode);
        this.R = (TextView) findViewById(R.id.tv_guandao);
        this.S = (TextView) findViewById(R.id.tv_clusterdebug);
        this.T = (TextView) findViewById(R.id.tv_avm);
        this.V = (TextView) findViewById(R.id.tv_isanavi);
        this.W = (TextView) findViewById(R.id.tv_amplifier);
        this.U = (TextView) findViewById(R.id.tv_network);
        this.Y = (TextView) findViewById(R.id.tv_anc_app_version);
        this.Z = (TextView) findViewById(R.id.tv_anc_data_version);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.D.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.F.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.V.setOnClickListener(this);
        this.U.setOnClickListener(this);
        this.W.setOnClickListener(this);
        this.X.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.L.setChecked(SystemProperties.get("persist.sys.cloudlog", "1").equals("1"));
        this.Q.setChecked(tg.b().a(getApplicationContext()));
        this.J.setOnCheckedChangeListener(this);
        this.K.setOnCheckedChangeListener(this);
        this.L.setOnCheckedChangeListener(this);
        this.M.setOnCheckedChangeListener(this);
        this.N.setOnCheckedChangeListener(this);
        this.O.setOnCheckedChangeListener(this);
        this.P.setOnCheckedChangeListener(this);
        this.Q.setOnCheckedChangeListener(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.rb_tcpdump) {
            BydLogHelper.logI("MainActivity", "rb_tcpdump  INIT_TCPDUMP_LOG" + this.j);
            if (this.j) {
                this.j = false;
                return;
            }
            B(Boolean.valueOf(z));
        }
        switch (id) {
            case R.id.rb_alarmlog /* 2131296699 */:
                w(Boolean.valueOf(z));
                break;
            case R.id.rb_autohallog /* 2131296700 */:
                x(Boolean.valueOf(z));
                break;
            case R.id.rb_cloudservicelog /* 2131296701 */:
                SystemProperties.set("persist.sys.cloudlog", z ? "1" : "0");
                break;
            case R.id.rb_consolesuspand /* 2131296702 */:
                if (!this.k) {
                    y(Boolean.valueOf(z));
                } else {
                    this.k = false;
                }
                break;
            case R.id.rb_enabledexopt /* 2131296703 */:
                v(Boolean.valueOf(z));
                break;
            case R.id.rb_enableramdumps /* 2131296704 */:
                z(Boolean.valueOf(z));
                break;
            case R.id.rb_flymode /* 2131296705 */:
                tg.b().d(getApplicationContext(), z);
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_audiopcmdebugtool /* 2131296908 */:
                D(TestMicActivity.class);
                break;
            case R.id.tv_autostudy /* 2131296909 */:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.byd.autoservice", "com.byd.autoservice.study.AutoStudyActivity"));
                startActivity(intent);
                break;
            case R.id.tv_avm /* 2131296910 */:
                if (r()) {
                    mg.b(this, getString(R.string.device_loading));
                    this.n0.removeMessages(10003);
                    this.n0.sendEmptyMessageDelayed(10003, 50L);
                    this.n0.sendEmptyMessageDelayed(10004, 5500L);
                }
                break;
            case R.id.tv_btlogtools /* 2131296912 */:
                D(BTLogToolSettingActivity.class);
                break;
            case R.id.tv_byddms /* 2131296913 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.byddms")) {
                    C();
                    break;
                }
                break;
            case R.id.tv_bydlogtool /* 2131296914 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.logtools")) {
                    C();
                } else {
                    f(this, "com.byd.logtools", "com.byd.logtools.activity.LogToolsActivity");
                }
                break;
            case R.id.tv_bydwirelesstools /* 2131296915 */:
                D(WirelessToolsActivity.class);
                break;
            case R.id.tv_cloud_services /* 2131296917 */:
                startActivity(new Intent(this, (Class<?>) YunLogActivity.class));
                break;
            case R.id.tv_clusterdebug /* 2131296918 */:
                D(ClusterDebugActivity.class);
                break;
            case R.id.tv_countrycode /* 2131296920 */:
                D(CountryCodeActivity.class);
                break;
            case R.id.tv_devicenet /* 2131296921 */:
                BydLogHelper.logD("MainActivity", "tv_devicenet");
                Intent intent2 = new Intent("com.byd.traffic_monitor.SIM_INFO_UPLOAD");
                intent2.setPackage("com.byd.trafficmonitor");
                startService(intent2);
                break;
            case R.id.tv_devtools /* 2131296922 */:
                D(AdbActivity.class);
                if (!tg.b().c(getApplicationContext(), "com.byd.devtool")) {
                    C();
                    break;
                }
                break;
            case R.id.tv_guandao /* 2131296929 */:
                ShowDialog();
                break;
            case R.id.tv_instrumenttest /* 2131296932 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.instrumenttest")) {
                    C();
                    break;
                }
                break;
            case R.id.tv_isanavi /* 2131296933 */:
                if (!tg.b().c(getApplicationContext(), "com.neusoft.na.ehp")) {
                    C();
                } else {
                    f(this, "com.neusoft.na.ehp", "com.neusoft.na.ehp.MainActivity");
                }
                break;
            case R.id.tv_language /* 2131296935 */:
                E(this.c);
                break;
            case R.id.tv_materiallnfotool /* 2131296943 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.materiallnfotool")) {
                    C();
                    break;
                }
                break;
            case R.id.tv_network /* 2131296945 */:
                D(AutomaticallyActivity.class);
                break;
            case R.id.tv_offline /* 2131296946 */:
                D(OFFLineConfigActivity.class);
                break;
            case R.id.tv_otatool /* 2131296947 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.ota")) {
                    C();
                } else {
                    f(this, "com.byd.ota", "com.byd.ota.view.OtaToolActivity");
                }
                break;
            case R.id.tv_qtlogkit /* 2131296948 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.qtlogkit")) {
                    C();
                    break;
                }
                break;
            case R.id.tv_satellite /* 2131296951 */:
                D(SatelliteActivity.class);
                BydLogHelper.logE("MainActivity", "registerListener(mAbsBYDAutoCollectDataListener) getCollision = " + this.l0.getCollision());
                break;
            case R.id.tv_shujucaiji /* 2131296953 */:
                BydLogHelper.logD("MainActivity", "shujucaiji sendBroadcast");
                sendBroadcast(new Intent("com.byd.data.collect.server.cleardata"));
                break;
            case R.id.tv_stresstest /* 2131296956 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.stresstest")) {
                    C();
                    break;
                }
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().clearFlags(1024);
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
        this.h = this;
        this.d = BYDAutoVersionDevice.getInstance(this);
        this.e = BYDAutoTestDevice.getInstance(getApplicationContext());
        this.f = BYDAutoTBoxDevice.getInstance(getApplicationContext());
        this.g = BYDAutoPowerDevice.getInstance(getApplicationContext());
        BYDAutoCollectDataDevice bYDAutoCollectDataDevice = BYDAutoCollectDataDevice.getInstance(getApplicationContext());
        this.l0 = bYDAutoCollectDataDevice;
        bYDAutoCollectDataDevice.registerListener(this.m0);
        BydLogHelper.logE("MainActivity", "registerListener(mAbsBYDAutoCollectDataListener) getCollision = " + this.l0.getCollision());
        BYDAutoYunDevice.getInstance(this);
        initView();
        initData();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BYDAutoTestDevice bYDAutoTestDevice = this.e;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.unregisterListener(this.i);
        }
        AvmManager avmManager = this.l;
        if (avmManager != null) {
            avmManager.g(this);
            this.l.h(this);
            this.l.b(this);
        }
    }

    @Override // defpackage.ll
    public void onError(int i, String str) {
        BydLogHelper.logD("MainActivity", "onError: ");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    public final String q() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = SystemProperties.get("persist.vendor.byd.cfg.audio.anc.app.version");
        if (!"0".equals(str) && !"".equals(str)) {
            String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            int length = strArrSplit.length;
            BydLogHelper.logE("MainActivity", "apps length = " + length);
            for (int i = 0; i < length - 2; i++) {
                if (i == length - 3) {
                    try {
                        stringBuffer.append(".");
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    } catch (NumberFormatException unused) {
                    }
                } else if (i == 0) {
                    if (!"".equals(strArrSplit[i])) {
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    }
                } else if (i == 1) {
                    if (!"".equals(strArrSplit[i])) {
                        String upperCase = Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase();
                        if (upperCase.length() == 1) {
                            stringBuffer.append("0");
                        }
                        stringBuffer.append(upperCase);
                    }
                } else if (strArrSplit[i].length() == 1) {
                    stringBuffer.append("0");
                    stringBuffer.append(strArrSplit[i]);
                } else {
                    stringBuffer.append(strArrSplit[i]);
                }
            }
        }
        BydLogHelper.logE("MainActivity", "version = " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public final boolean r() {
        if (!"-1".equals(SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.isp", "-1"))) {
            return true;
        }
        mg.b(this.h, getString(R.string.str_app_not_exist));
        return false;
    }

    public final String s() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = SystemProperties.get("persist.vendor.byd.cfg.audio.anc.data.version");
        if (!"0".equals(str) && !"".equals(str)) {
            String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            int length = strArrSplit.length;
            BydLogHelper.logE("MainActivity", "datas length = " + length);
            for (int i = 0; i < length - 2; i++) {
                if (i == length - 3) {
                    try {
                        stringBuffer.append(".");
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    } catch (NumberFormatException unused) {
                    }
                } else if (i == 0) {
                    if (!"".equals(strArrSplit[i])) {
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    }
                } else if (i == 1) {
                    if (!"".equals(strArrSplit[i])) {
                        String upperCase = Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase();
                        if (upperCase.length() == 1) {
                            stringBuffer.append("0");
                        }
                        stringBuffer.append(upperCase);
                    }
                } else if (strArrSplit[i].length() == 1) {
                    stringBuffer.append("0");
                    stringBuffer.append(strArrSplit[i]);
                } else {
                    stringBuffer.append(strArrSplit[i]);
                }
            }
        }
        BydLogHelper.logE("MainActivity", "version = " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public final String t() {
        return (this.d.getMcuVersion() == null || this.d.getMcuVersion().length <= 0) ? "" : this.d.getMcuVersion()[0];
    }

    public String u() {
        return this.d.getBydSystemVersion();
    }

    public final void v(Boolean bool) {
    }

    public final void w(Boolean bool) {
    }

    public final void x(Boolean bool) {
    }

    public final void y(Boolean bool) {
        if (this.e == null) {
            return;
        }
        if (bool.booleanValue()) {
            this.e.enableConsoleSuspand(true);
        } else {
            this.e.enableConsoleSuspand(false);
        }
    }

    public final void z(Boolean bool) {
    }
}
