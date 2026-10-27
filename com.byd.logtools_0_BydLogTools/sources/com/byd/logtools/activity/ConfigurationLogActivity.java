package com.byd.logtools.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemProperties;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.byd.logtools.R;
import com.byd.logtools.logservice.BYDLogService;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class ConfigurationLogActivity extends BydBaseActivity implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BYDLogService f2976b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b f2977c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f2978d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f2979e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f2980f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f2981g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextView f2982h;
    private Switch i;
    private Switch j;
    private Switch k;
    private Switch l;
    private Switch m;
    private String n;
    private String o;
    private final ServiceConnection p = new a();

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BYDLogService unused = ConfigurationLogActivity.f2976b = ((BYDLogService.p) iBinder).a();
            ConfigurationLogActivity.this.f2978d.setText(ConfigurationLogActivity.f2976b.b0() ? ConfigurationLogActivity.this.n : ConfigurationLogActivity.this.o);
            ConfigurationLogActivity.this.f2979e.setText(ConfigurationLogActivity.f2976b.Z() ? ConfigurationLogActivity.this.n : ConfigurationLogActivity.this.o);
            ConfigurationLogActivity.this.f2980f.setText(ConfigurationLogActivity.f2976b.c0() ? ConfigurationLogActivity.this.n : ConfigurationLogActivity.this.o);
            ConfigurationLogActivity.this.f2981g.setText(ConfigurationLogActivity.f2976b.d0() ? ConfigurationLogActivity.this.n : ConfigurationLogActivity.this.o);
            ConfigurationLogActivity.this.f2982h.setText(ConfigurationLogActivity.f2976b.S() ? ConfigurationLogActivity.this.n : ConfigurationLogActivity.this.o);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BYDLogService unused = ConfigurationLogActivity.f2976b = null;
        }
    }

    private static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<ConfigurationLogActivity> f2984a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ConfigurationLogActivity f2985b;

        b(ConfigurationLogActivity configurationLogActivity) {
            this.f2984a = new WeakReference<>(configurationLogActivity);
            this.f2985b = configurationLogActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (ConfigurationLogActivity.f2976b == null) {
                    ConfigurationLogActivity.f2977c.sendEmptyMessageDelayed(1, 1000L);
                    return;
                }
                if (ConfigurationLogActivity.f2976b.c0) {
                    ConfigurationLogActivity.f2976b.I("action.upload.crash.log.info");
                }
                ConfigurationLogActivity.f2976b.D0(ConfigurationLogActivity.f2976b.V);
                return;
            }
            if (i != 2) {
                return;
            }
            if (ConfigurationLogActivity.f2976b == null) {
                ConfigurationLogActivity.f2977c.sendEmptyMessageDelayed(2, 1000L);
            } else if (ConfigurationLogActivity.f2976b.c0) {
                ConfigurationLogActivity.f2976b.I("action.upload.crash.log.info");
            }
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    @SuppressLint({"NonConstantResourceId"})
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        BYDLogService bYDLogService;
        BYDLogService bYDLogService2;
        BYDLogService bYDLogService3;
        BYDLogService bYDLogService4;
        BYDLogService bYDLogService5;
        switch (compoundButton.getId()) {
            case R.id.save_crashlog_switch /* 2131296594 */:
                com.byd.logtools.d.a.b().d("save_crashlog_switch", z);
                BydLogHelper.logD("save_crashlog_switch = " + z);
                if (!z && ((bYDLogService = f2976b) == null || !bYDLogService.Z())) {
                    SystemProperties.set("persist.save.crashlog.enable", "0");
                } else {
                    SystemProperties.set("persist.save.crashlog.enable", "1");
                }
                break;
            case R.id.upload_crashinfo_switch /* 2131296714 */:
                com.byd.logtools.d.a.b().d("upload_crashinfo_switch", z);
                BydLogHelper.logD("upload_crashinfo_switch = " + z);
                if (!z && ((bYDLogService2 = f2976b) == null || !bYDLogService2.U())) {
                    f2977c.sendEmptyMessage(2);
                } else {
                    f2977c.sendEmptyMessage(1);
                }
                break;
            case R.id.upload_ota_log_switch /* 2131296716 */:
                com.byd.logtools.d.a.b().d("upload_ota_log_switch", z);
                BydLogHelper.logD("upload_ota_log_switch = " + z);
                if (!z && (bYDLogService3 = f2976b) != null) {
                    bYDLogService3.S();
                    break;
                }
                break;
            case R.id.upload_realtimelog_switch /* 2131296719 */:
                com.byd.logtools.d.a.b().d("upload_realtimelog_switch", z);
                BydLogHelper.logD("upload_realtimelog_switch = " + z);
                if (!z && (bYDLogService4 = f2976b) != null) {
                    bYDLogService4.c0();
                    break;
                }
                break;
            case R.id.upload_recoverylog_switch /* 2131296721 */:
                com.byd.logtools.d.a.b().d("upload_recoverylog_switch", z);
                BydLogHelper.logD("upload_recoverylog_switch = " + z);
                if (!z && (bYDLogService5 = f2976b) != null) {
                    bYDLogService5.d0();
                    break;
                }
                break;
        }
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (i("ConfigurationLogActivity")) {
            finish();
            return;
        }
        requestWindowFeature(1);
        getWindow().clearFlags(1024);
        setContentView(R.layout.activity_configration_log);
        f2977c = new b(this);
        com.byd.logtools.d.b.a(this);
        this.n = getResources().getString(R.string.switch_open);
        this.o = getResources().getString(R.string.switch_close);
        this.f2978d = (TextView) findViewById(R.id.upload_crashlog_service_value);
        this.f2979e = (TextView) findViewById(R.id.save_crashlog_service_value);
        this.f2980f = (TextView) findViewById(R.id.upload_realtimelog_service_value);
        this.f2981g = (TextView) findViewById(R.id.upload_recoverylog_service_value);
        this.f2982h = (TextView) findViewById(R.id.upload_otalog_service_value);
        bindService(new Intent(this, (Class<?>) BYDLogService.class), this.p, 1);
        this.i = (Switch) findViewById(R.id.upload_crashinfo_switch);
        this.j = (Switch) findViewById(R.id.save_crashlog_switch);
        this.k = (Switch) findViewById(R.id.upload_realtimelog_switch);
        this.l = (Switch) findViewById(R.id.upload_recoverylog_switch);
        this.m = (Switch) findViewById(R.id.upload_ota_log_switch);
        this.i.setOnCheckedChangeListener(this);
        this.j.setOnCheckedChangeListener(this);
        this.k.setOnCheckedChangeListener(this);
        this.l.setOnCheckedChangeListener(this);
        this.m.setOnCheckedChangeListener(this);
        this.i.setChecked(com.byd.logtools.d.a.b().a("upload_crashinfo_switch", false));
        this.j.setChecked(com.byd.logtools.d.a.b().a("save_crashlog_switch", false));
        this.k.setChecked(com.byd.logtools.d.a.b().a("upload_realtimelog_switch", false));
        this.l.setChecked(com.byd.logtools.d.a.b().a("upload_recoverylog_switch", false));
        this.m.setChecked(com.byd.logtools.d.a.b().a("upload_ota_log_switch", false));
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (i("ConfigurationLogActivity")) {
            unbindService(this.p);
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (i("ConfigurationLogActivity")) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (i("ConfigurationLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (i("ConfigurationLogActivity")) {
            finish();
        }
    }
}
