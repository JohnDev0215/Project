package com.byd.secretcode.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.byd.secretcode.audiopcmdebug.TestMicActivity;
import com.byd.secretcode.clusterdebug.ClusterDebugActivity;
import com.byd.secretcode.study.SecretCodeApplication;
import com.byd.secretcode.view.BydSwitch;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.jg;
import defpackage.mg;
import defpackage.rf;
import defpackage.sg;
import defpackage.tg;

/* JADX INFO: loaded from: classes.dex */
public class PasswordActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public BydSwitch A;
    public BydSwitch B;
    public BydSwitch C;
    public BydSwitch D;
    public CheckBox E;
    public CheckBox F;
    public CheckBox G;
    public CheckBox H;
    public TextView I;
    public TextView J;
    public TextView K;
    public TextView L;
    public TextView M;
    public RelativeLayout N;
    public TextView O;
    public BYDAutoTBoxDevice Q;
    public AbsBYDAutoTestListener R;
    public BYDAutoSettingDevice S;
    public BYDAutoStudy T;
    public BYDAutoPowerDevice a0;
    public EditText c;
    public EditText d;
    public LinearLayout e;
    public ScrollView f;
    public TextView g;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public int k0;
    public TextView l;
    public TextView m;
    public TextView n;
    public CheckBox o;
    public CheckBox p;
    public TextView q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public Button v;
    public BydSwitch w;
    public BydSwitch x;
    public BydSwitch y;
    public BydSwitch z;
    public final String b = "PasswordActivity";
    public BYDAutoTestDevice P = null;
    public boolean U = false;
    public boolean V = false;
    public final String W = "captive_portal_mode";
    public final String X = "persist.sys.cpaa.status";
    public final int Y = 1;
    public final int Z = 0;
    public final int b0 = 1001;
    public final int c0 = 1002;
    public final int d0 = 1003;
    public final int e0 = 60;
    public final long f0 = 60000;
    public int g0 = 0;
    public String h0 = "";
    public final int i0 = 6;
    public int j0 = -1;
    public int l0 = 0;
    public String m0 = "0";
    public AbsBYDAutoSettingListener n0 = new c();
    public Handler o0 = new d();

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b extends AbsBYDAutoTestListener {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PasswordActivity.this.V = true;
                PasswordActivity.this.F.setChecked(true);
            }
        }

        /* JADX INFO: renamed from: com.byd.secretcode.activity.PasswordActivity$b$b, reason: collision with other inner class name */
        public class RunnableC0008b implements Runnable {
            public RunnableC0008b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PasswordActivity.this.U = true;
                PasswordActivity.this.E.setChecked(true);
            }
        }

        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onQNXLogStatusChanged(int i, boolean z) {
            super.onQNXLogStatusChanged(i, z);
            BydLogHelper.logW(AbsBYDAutoTestListener.TAG, "onQNXLogStatusChanged logtype = " + i + ", status = " + z);
            if (i == 1 && z) {
                PasswordActivity.this.runOnUiThread(new a());
            } else if (i == 2 && z) {
                PasswordActivity.this.runOnUiThread(new RunnableC0008b());
            }
        }
    }

    public class c extends AbsBYDAutoSettingListener {
        public c() {
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onRncStateChanged(int i) {
            super.onRncStateChanged(i);
            PasswordActivity.this.l0 = i;
        }
    }

    public class d extends Handler {
        public d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    PasswordActivity.this.s();
                    break;
                case 1002:
                    BydLogHelper.logI("PasswordActivity", "MESSAGE_CHECK_DAB_DUMP_TUNER_STATUS CHECK_DAB_DUMP_TIME = " + PasswordActivity.this.g0);
                    if (PasswordActivity.this.g0 <= 60) {
                        PasswordActivity.m(PasswordActivity.this);
                        PasswordActivity.this.o0.removeMessages(1002);
                        PasswordActivity.this.o0.sendEmptyMessageDelayed(1002, 1000L);
                    }
                    if (PasswordActivity.this.H != null && "0".equals(SystemProperties.get("sbr.audio.log.rt", ""))) {
                        PasswordActivity.this.o0.removeMessages(1002);
                        PasswordActivity.this.H.setChecked(false);
                        break;
                    }
                    break;
                case 1003:
                    PasswordActivity.this.e.setVisibility(0);
                    PasswordActivity.this.f.setVisibility(8);
                    PasswordActivity.this.d.setText("");
                    PasswordActivity.this.f.scrollTo(0, 0);
                    break;
            }
        }
    }

    public static /* synthetic */ int m(PasswordActivity passwordActivity) {
        int i = passwordActivity.g0;
        passwordActivity.g0 = i + 1;
        return i;
    }

    public void A() {
        Toast.makeText(this, R.string.str_app_not_exist, 0).show();
    }

    public final void B(Class cls) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, (Class<?>) cls));
        intent.putExtra("name", true);
        startActivityForResult(intent, 100);
    }

    public final void C() {
        boolean zEquals = SystemProperties.get("sys.connect.adb.wiress", "0").equals("1");
        this.z.setChecked(zEquals);
        z(zEquals);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (r(currentFocus, motionEvent)) {
                p(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void f(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName(str, str2));
        intent.putExtra("name", true);
        startActivityForResult(intent, 100);
    }

    public void g(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName(str, str2));
        intent.putExtra("flag", "Jump");
        context.startActivity(intent);
    }

    public final void initData() {
        BydLogHelper.logD("PasswordActivity", "initData");
        jg.a();
        this.P = BYDAutoTestDevice.getInstance(this);
        this.Q = BYDAutoTBoxDevice.getInstance(this);
        this.S = BYDAutoSettingDevice.getInstance(getApplicationContext());
        this.T = BYDAutoStudy.getInstance(this);
        this.o.setChecked(SystemProperties.get("persist.sys.cloudlog", "1").equals("1"));
        this.p.setChecked(tg.b().a(getApplicationContext()));
        boolean z = Settings.Global.getInt(getContentResolver(), "captive_portal_mode", 1) == 0;
        if (z) {
            this.A.b(false, false);
        } else {
            this.A.b(true, false);
        }
        BydLogHelper.logD("PasswordActivity", "initData mode =" + z);
        if (Settings.Global.getInt(getContentResolver(), "adb_enabled", 0) > 0) {
            this.w.b(true, false);
        } else {
            this.w.b(false, false);
        }
        if (Settings.System.getInt(getContentResolver(), "show_touches", 0) == 0) {
            this.x.b(false, false);
        } else {
            this.x.b(true, false);
        }
        if (Settings.System.getInt(getContentResolver(), "pointer_location", 0) == 0) {
            this.y.b(false, false);
        } else {
            this.y.b(true, false);
        }
        this.m0 = SystemProperties.get("persist.sys.cpaa.status");
        BydLogHelper.logE("PasswordActivity", "initData cpaa =" + this.m0);
        if ("1".equals(this.m0)) {
            this.B.b(true, false);
            this.C.b(false, false);
        } else if ("2".equals(this.m0)) {
            this.B.b(false, false);
            this.C.b(true, false);
        } else if ("3".equals(this.m0)) {
            this.B.b(true, false);
            this.C.b(true, false);
        } else {
            this.B.b(false, false);
            this.C.b(false, false);
        }
        this.B.setOnCheckedChangeListener(this);
        this.C.setOnCheckedChangeListener(this);
        C();
        this.A.setOnCheckedChangeListener(this);
        this.z.setOnCheckedChangeListener(this);
        this.D.setOnCheckedChangeListener(this);
        this.o.setOnCheckedChangeListener(this);
        this.p.setOnCheckedChangeListener(this);
        this.a0 = BYDAutoPowerDevice.getInstance(this);
        b bVar = new b();
        this.R = bVar;
        this.P.registerListener((AbsBYDAutoTestListener) bVar);
        this.S.registerListener(this.n0);
        this.P.getConsoleSuspandState();
        this.P.getTCPDumpState();
        if (this.T.getSupportRnc() == 1) {
            this.N.setVisibility(0);
        } else {
            this.N.setVisibility(8);
        }
    }

    public void initView() {
        this.g = (TextView) findViewById(R.id.tv_log_switch_bydlogtool);
        this.h = (TextView) findViewById(R.id.tv_log_switch_cloud_services);
        this.i = (TextView) findViewById(R.id.tv_log_switch_vr_log);
        this.j = (TextView) findViewById(R.id.tv_log_switch_btlogtools);
        this.k = (TextView) findViewById(R.id.tv_log_switch_tbox_Qxdm);
        this.l = (TextView) findViewById(R.id.tv_log_switch_clusterdebug);
        this.n = (TextView) findViewById(R.id.tv_camera_test);
        this.m = (TextView) findViewById(R.id.tv_test_wireless_ip);
        this.u = (TextView) findViewById(R.id.tv_anc);
        this.o = (CheckBox) findViewById(R.id.rb_log_switch_cloudservicelog);
        this.p = (CheckBox) findViewById(R.id.rb_switch_flymode);
        this.E = (CheckBox) findViewById(R.id.rb_log_switch_tcpdump);
        this.F = (CheckBox) findViewById(R.id.rb_log_switch_consolesuspand);
        this.G = (CheckBox) findViewById(R.id.rb_log_dabTuner);
        this.H = (CheckBox) findViewById(R.id.rb_log_dabDumpTuner);
        this.q = (TextView) findViewById(R.id.tv_offline);
        this.w = (BydSwitch) findViewById(R.id.sw_test_str_linkusb);
        this.x = (BydSwitch) findViewById(R.id.sw_test_visual_effect);
        this.y = (BydSwitch) findViewById(R.id.sw_test_touch_data);
        this.z = (BydSwitch) findViewById(R.id.sw_test_wireless_adb);
        this.A = (BydSwitch) findViewById(R.id.sw_network_detection);
        this.D = (BydSwitch) findViewById(R.id.sw_test_rnc);
        this.r = (TextView) findViewById(R.id.tv_countrycode);
        this.s = (TextView) findViewById(R.id.imei_tv);
        this.t = (TextView) findViewById(R.id.cpaa_install);
        this.B = (BydSwitch) findViewById(R.id.sw_cp_install);
        this.C = (BydSwitch) findViewById(R.id.sw_aa_install);
        this.v = (Button) findViewById(R.id.ok_btn);
        this.I = (TextView) findViewById(R.id.tv_audiopcmdebugtool);
        this.J = (TextView) findViewById(R.id.tv_isanavi);
        this.K = (TextView) findViewById(R.id.tv_network);
        this.L = (TextView) findViewById(R.id.tv_sound_calibration);
        this.M = (TextView) findViewById(R.id.tv_otatool);
        this.N = (RelativeLayout) findViewById(R.id.rl_rnc);
        this.O = (TextView) findViewById(R.id.tv_yunhua);
        this.G.setChecked("VERBOSE".equalsIgnoreCase(SystemProperties.get("persist.log.tag.ars", "")));
        boolean zEqualsIgnoreCase = "1".equalsIgnoreCase(SystemProperties.get("sbr.audio.log.rt", ""));
        this.H.setChecked(zEqualsIgnoreCase);
        if (zEqualsIgnoreCase) {
            this.o0.removeMessages(1002);
            this.o0.sendEmptyMessageDelayed(1002, 1000L);
        }
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.E.setOnCheckedChangeListener(this);
        this.F.setOnCheckedChangeListener(this);
        this.G.setOnCheckedChangeListener(this);
        this.H.setOnCheckedChangeListener(this);
        this.r.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.O.setOnClickListener(this);
        this.d = (EditText) findViewById(R.id.edt_state_hold);
        this.c = (EditText) findViewById(R.id.et_password);
        this.e = (LinearLayout) findViewById(R.id.ll_password);
        this.f = (ScrollView) findViewById(R.id.sv_secretview);
        this.c.addTextChangedListener(new a());
    }

    public String o() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_4), 1).show();
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.text_1), 1).show();
            return null;
        }
        if (activeNetworkInfo.getType() != 1) {
            return null;
        }
        String strQ = q(((WifiManager) getSystemService("wifi")).getConnectionInfo().getIpAddress());
        this.m.setText(((Object) this.m.getText()) + strQ);
        if (Build.VERSION.SDK_INT >= 29) {
            Toast.makeText(getApplicationContext(), ((Object) getResources().getText(R.string.text_2)) + strQ, 1).show();
        }
        return strQ;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        BydLogHelper.logD("PasswordActivity", "onActivityResult requestCode = " + i + ", resultCode = " + i2);
        if (i != 100) {
            return;
        }
        BydLogHelper.logD("PasswordActivity", "onActivityResult");
        this.j0 = 100;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_log_dabDumpTuner /* 2131296706 */:
                BydLogHelper.logE("PasswordActivity", "rb_log_dabDumpTuner isChecked =" + z);
                if (z) {
                    mg.b(this, getString(R.string.str_dab_dump_tuner_toast));
                    SecretCodeApplication.c(60000L);
                    this.o0.removeMessages(1002);
                    this.g0 = 0;
                    this.o0.sendEmptyMessageDelayed(1002, 1000L);
                } else {
                    SecretCodeApplication.d();
                    this.o0.removeMessages(1002);
                }
                SystemProperties.set("sbr.audio.log.rt", z ? "1" : "0");
                break;
            case R.id.rb_log_dabTuner /* 2131296707 */:
                BydLogHelper.logI("PasswordActivity", "rb_log_dabTuner isChecked =" + z);
                SystemProperties.set("persist.log.tag.ars", z ? "VERBOSE" : "");
                SystemProperties.set("persist.log.tag.rbs", z ? "VERBOSE" : "");
                break;
            case R.id.rb_log_switch_cloudservicelog /* 2131296709 */:
                SystemProperties.set("persist.sys.cloudlog", z ? "1" : "0");
                break;
            case R.id.rb_log_switch_consolesuspand /* 2131296710 */:
                if (!this.V) {
                    t(Boolean.valueOf(z));
                } else {
                    this.V = false;
                }
                break;
            case R.id.rb_log_switch_tcpdump /* 2131296714 */:
                BydLogHelper.logI("PasswordActivity", "rb_log_switch_tcpdump  INIT_TCPDUMP_LOG" + this.U);
                if (!this.U) {
                    u(Boolean.valueOf(z));
                } else {
                    this.U = false;
                }
                break;
            case R.id.rb_switch_flymode /* 2131296715 */:
                tg.b().d(getApplicationContext(), z);
                break;
            case R.id.sw_aa_install /* 2131296806 */:
                String str = SystemProperties.get("persist.sys.cpaa.status");
                if (z || "2".equals(str) || "2".equals(str)) {
                    v("2", z);
                } else {
                    v("2", z);
                }
                break;
            case R.id.sw_cp_install /* 2131296807 */:
                String str2 = SystemProperties.get("persist.sys.cpaa.status");
                if (z || "1".equals(str2) || "3".equals(str2)) {
                    v("1", z);
                } else {
                    v("1", z);
                }
                break;
            case R.id.sw_network_detection /* 2131296808 */:
                int i = Settings.Global.getInt(getContentResolver(), "captive_portal_mode", 1);
                if (z && i == 0) {
                    Settings.Global.putInt(getContentResolver(), "captive_portal_mode", 1);
                    mg.b(this, getString(R.string.network_settings_open));
                    this.o0.removeMessages(1001);
                    this.o0.sendEmptyMessageDelayed(1001, 1000L);
                    break;
                } else if (!z && i == 1) {
                    Settings.Global.putInt(getContentResolver(), "captive_portal_mode", 0);
                    mg.b(this, getString(R.string.network_settings_close));
                    this.o0.removeMessages(1001);
                    this.o0.sendEmptyMessageDelayed(1001, 1000L);
                    break;
                }
                break;
            case R.id.sw_test_rnc /* 2131296813 */:
                BYDAutoSettingDevice bYDAutoSettingDevice = this.S;
                if (bYDAutoSettingDevice != null && this.l0 == 1) {
                    bYDAutoSettingDevice.setRncState(2);
                    break;
                } else if (bYDAutoSettingDevice != null && this.l0 == 2) {
                    bYDAutoSettingDevice.setRncState(1);
                    break;
                }
                break;
            case R.id.sw_test_str_linkusb /* 2131296814 */:
                w(z);
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String strSubstring;
        switch (view.getId()) {
            case R.id.ok_btn /* 2131296673 */:
                String upperCase = this.c.getText().toString().trim().toUpperCase();
                if (upperCase != null && upperCase.isEmpty()) {
                    mg.b(this, getString(R.string.offline_config_password_err));
                } else if (upperCase.length() != 6) {
                    mg.b(this, getString(R.string.offline_config_password_err));
                } else {
                    if (TextUtils.isEmpty(this.h0) || this.h0.length() < 6) {
                        strSubstring = "112233";
                    } else {
                        String str = this.h0;
                        strSubstring = str.substring(str.length() - 6, this.h0.length());
                    }
                    boolean zJ = rf.k().j(strSubstring, upperCase);
                    BydLogHelper.logD("PasswordActivity", "onTextChanged check =" + zJ + ",imei =" + strSubstring);
                    if (!zJ) {
                        mg.b(this, getString(R.string.offline_config_password_err));
                    } else {
                        this.c.setText("");
                        this.d.setText("inspection_imei_passed");
                        this.e.setVisibility(8);
                        this.f.setVisibility(0);
                    }
                }
                break;
            case R.id.sw_test_touch_data /* 2131296815 */:
                x();
                break;
            case R.id.sw_test_visual_effect /* 2131296816 */:
                y();
                break;
            case R.id.tv_anc /* 2131296900 */:
                B(AncActivity.class);
                break;
            case R.id.tv_audiopcmdebugtool /* 2131296908 */:
                B(TestMicActivity.class);
                break;
            case R.id.tv_camera_test /* 2131296916 */:
                B(CameraActivity.class);
                break;
            case R.id.tv_countrycode /* 2131296920 */:
                B(CountryCodeActivity.class);
                break;
            case R.id.tv_isanavi /* 2131296933 */:
                if (!tg.b().c(getApplicationContext(), "com.neusoft.na.ehp")) {
                    A();
                } else {
                    f(this, "com.neusoft.na.ehp", "com.neusoft.na.ehp.MainActivity");
                }
                break;
            case R.id.tv_log_switch_btlogtools /* 2131296937 */:
                B(BTLogToolSettingActivity.class);
                break;
            case R.id.tv_log_switch_bydlogtool /* 2131296938 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.logtools")) {
                    A();
                } else {
                    g(this, "com.byd.logtools", "com.byd.logtools.activity.LogToolsActivity");
                }
                break;
            case R.id.tv_log_switch_cloud_services /* 2131296939 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.carservice")) {
                    A();
                } else {
                    f(this, "com.byd.carservice", "com.byd.carservice.LogSwitchSetting");
                }
                break;
            case R.id.tv_log_switch_clusterdebug /* 2131296940 */:
                B(ClusterDebugActivity.class);
                break;
            case R.id.tv_log_switch_tbox_Qxdm /* 2131296941 */:
                B(TboxQxdmLogActivity.class);
                break;
            case R.id.tv_log_switch_vr_log /* 2131296942 */:
                if (tg.b().c(getApplicationContext(), "com.byd.vrsettings")) {
                    Intent intent = new Intent();
                    intent.setAction("com.byd.action.OpenVRSettingsLogSwitch");
                    intent.setPackage("com.byd.vrsettings");
                    sendBroadcast(intent);
                    BydLogHelper.logD("PasswordActivity", "switch_vr_log ACTION_NAME_VRLOG  = com.byd.action.OpenVRSettingsLogSwitch, PACKAGE_NAME_VRLOG = com.byd.vrsettings");
                } else if (!tg.b().c(getApplicationContext(), "com.byd.vrassistant.xf")) {
                    A();
                } else {
                    Intent intent2 = new Intent();
                    intent2.setAction("com.byd.action.OpenVRSettingsLogSwitch");
                    intent2.setPackage("com.byd.vrassistant.xf");
                    sendBroadcast(intent2);
                    BydLogHelper.logD("PasswordActivity", "switch_vr_log ACTION_NAME_VRLOG  = com.byd.action.OpenVRSettingsLogSwitch, PACKAGE_NAME_VRLOG_XF = com.byd.vrassistant.xf");
                }
                break;
            case R.id.tv_network /* 2131296945 */:
                B(AutomaticallyActivity.class);
                break;
            case R.id.tv_offline /* 2131296946 */:
                B(OFFLineConfigActivity.class);
                break;
            case R.id.tv_otatool /* 2131296947 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.ota")) {
                    A();
                } else {
                    f(this, "com.byd.ota", "com.byd.ota.view.OtaToolActivity");
                }
                break;
            case R.id.tv_sound_calibration /* 2131296955 */:
                B(SoundSourceActivity.class);
                break;
            case R.id.tv_yunhua /* 2131296973 */:
                if (!tg.b().c(getApplicationContext(), "com.byd.cota")) {
                    A();
                } else {
                    f(this, "com.byd.cota", "com.byd.yqzx.test.TestMainActivity");
                }
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        BydLogHelper.logD("PasswordActivity", "onConfigurationChanged newConfig.uiMode = " + configuration.uiMode);
        if (configuration.uiMode != this.k0 && this.d.getText().toString().equals("inspection_imei_passed")) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
        }
        configuration.uiMode = this.k0;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_password);
        BydLogHelper.logD("PasswordActivity", "onCreate");
        initView();
        initData();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logI("PasswordActivity", "onDestroy ");
        BYDAutoTestDevice bYDAutoTestDevice = this.P;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.unregisterListener(this.R);
        }
        if (this.a0 != null) {
            this.a0 = null;
        }
        Handler handler = this.o0;
        if (handler == null || !handler.hasMessages(1002)) {
            return;
        }
        this.o0.removeMessages(1002);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        BydLogHelper.logI("PasswordActivity", "onPause ");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BydLogHelper.logD("PasswordActivity", "onResume mActivityFlag = " + this.j0);
        String strA = sg.a();
        this.h0 = strA;
        if (strA != null) {
            this.s.setText(getString(R.string.imei) + this.h0);
        }
        if (this.d.getText().toString().equals("inspection_imei_passed") || this.j0 == 100) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
        }
        if (this.j0 == 100) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
        } else {
            this.e.setVisibility(0);
            this.f.setVisibility(8);
        }
        this.j0 = -1;
    }

    public final void p(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public String q(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public final boolean r(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public final void s() {
        BYDAutoPowerDevice bYDAutoPowerDevice = this.a0;
        if (bYDAutoPowerDevice != null) {
            bYDAutoPowerDevice.mcuReset();
        }
    }

    public final void t(Boolean bool) {
        if (bool.booleanValue()) {
            this.P.enableConsoleSuspand(true);
        } else {
            this.P.enableConsoleSuspand(false);
        }
    }

    public final void u(Boolean bool) {
        if (this.P == null) {
            return;
        }
        if (bool.booleanValue()) {
            int iEnableTCPDump = this.P.enableTCPDump(true);
            int tcpDumpState = this.Q.setTcpDumpState(true);
            BydLogHelper.logD("PasswordActivity", "open iviTcpDump = " + iEnableTCPDump + ", tBoxTcpDump = " + tcpDumpState);
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
        int iEnableTCPDump2 = this.P.enableTCPDump(false);
        int tcpDumpState2 = this.Q.setTcpDumpState(false);
        BydLogHelper.logD("PasswordActivity", "close iviTcpDump = " + iEnableTCPDump2 + ", tBoxTcpDump = " + tcpDumpState2);
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

    public final void v(String str, boolean z) {
        BydLogHelper.logE("PasswordActivity", "setSw_cpaa_insatll0 type =" + str + ", isCheck = " + z);
        String str2 = SystemProperties.get("persist.sys.cpaa.status");
        this.m0 = str2;
        if ("0".equals(str2) || "".equals(this.m0)) {
            if (z && "1".equals(str)) {
                SystemProperties.set("persist.sys.cpaa.status", "1");
            } else if (z && "2".equals(str)) {
                SystemProperties.set("persist.sys.cpaa.status", "2");
            }
        } else if ("1".equals(this.m0)) {
            if (!z && "1".equals(str)) {
                SystemProperties.set("persist.sys.cpaa.status", "0");
                BydLogHelper.logE("PasswordActivity", "CPAA_INSTALL_STATE_CP1111 type =" + str + ", isCheck = " + z);
            } else if (z && "2".equals(str)) {
                SystemProperties.set("persist.sys.cpaa.status", "3");
                BydLogHelper.logE("PasswordActivity", "CPAA_INSTALL_STATE_CP2222 type =" + str + ", isCheck = " + z);
            }
        } else if ("2".equals(this.m0)) {
            if (z && "1".equals(str)) {
                BydLogHelper.logE("PasswordActivity", "CPAA_INSTALL_STATE_AA3333 type =" + str + ", isCheck = " + z);
                SystemProperties.set("persist.sys.cpaa.status", "3");
            } else if (!z && "2".equals(str)) {
                BydLogHelper.logE("PasswordActivity", "CPAA_INSTALL_STATE_AA4444 type =" + str + ", isCheck = " + z);
                SystemProperties.set("persist.sys.cpaa.status", "0");
            }
        } else if ("3".equals(this.m0)) {
            BydLogHelper.logE("PasswordActivity", "setSw_cpaa_insatll5555 CPAA_INSTALL_STATE_CPAA");
            if (!z && "1".equals(str)) {
                BydLogHelper.logE("PasswordActivity", "setSw_cpaa_insatll5555 type =" + str + ", isCheck = " + z);
                SystemProperties.set("persist.sys.cpaa.status", "2");
            } else if (!z && "2".equals(str)) {
                BydLogHelper.logE("PasswordActivity", "setSw_cpaa_insatll6666 type =" + str + ", isCheck = " + z);
                SystemProperties.set("persist.sys.cpaa.status", "1");
            }
        }
        int cpaaInstall = BYDAutoTestDevice.getInstance(this).setCpaaInstall(Integer.parseInt(str), z);
        BydLogHelper.logD("PasswordActivity", "setSw_cpaa_insatll cpaaState = " + cpaaInstall);
        if (cpaaInstall == 0) {
            mg.b(this, getString(R.string.cpass_settings_ok));
            this.o0.removeMessages(1001);
            this.o0.sendEmptyMessageDelayed(1001, 1000L);
        }
    }

    public final void w(boolean z) {
        if (Settings.Global.getInt(getContentResolver(), "adb_enabled", 0) > 0) {
            Settings.Global.putInt(getContentResolver(), "adb_enabled", 0);
        } else {
            Settings.Global.putInt(getContentResolver(), "adb_enabled", 1);
        }
    }

    public final void x() {
        if (Settings.System.getInt(getContentResolver(), "pointer_location", 0) == 0) {
            Settings.System.putInt(getContentResolver(), "pointer_location", 1);
            this.y.setChecked(true);
        } else {
            Settings.System.putInt(getContentResolver(), "pointer_location", 0);
            this.y.setChecked(false);
        }
    }

    public final void y() {
        if (Settings.System.getInt(getContentResolver(), "show_touches", 0) == 0) {
            Settings.System.putInt(getContentResolver(), "show_touches", 1);
            this.x.setChecked(true);
        } else {
            Settings.System.putInt(getContentResolver(), "show_touches", 0);
            this.x.setChecked(false);
        }
    }

    public final void z(boolean z) {
        if (z) {
            SystemProperties.set("persist.adb.tcp.port", "5555");
            o();
        } else {
            SystemProperties.set("persist.adb.tcp.port", "-1");
            this.m.setText(R.string.str_ip);
        }
    }
}
