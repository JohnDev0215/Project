package com.byd.secretcode.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.e7;
import defpackage.kg;
import defpackage.mg;
import defpackage.qg;
import defpackage.rg;

/* JADX INFO: loaded from: classes.dex */
public class AdbActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public static long b;
    public Switch d;
    public Switch e;
    public Switch f;
    public TextView g;
    public TextView h;
    public Button i;
    public Button j;
    public TextView k;
    public AlertDialog l;
    public ContentResolver r;
    public c s;
    public b t;
    public BYDAutoTestDevice u;
    public String c = "AdbActivity";
    public String m = "YXNkZkA0MzIx";
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q = false;

    public final class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                AdbActivity.this.p();
            } else if (i == 2) {
                AdbActivity.this.r();
            } else {
                if (i != 3) {
                    return;
                }
                AdbActivity.this.q();
            }
        }
    }

    public final class c extends ContentObserver {
        public c(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            try {
                Log.d("ban", " -- SettingsObserver is onChange and uuid == " + Settings.Global.getInt(AdbActivity.this.getContentResolver(), "adb_enabled"));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean k(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = Math.abs(jCurrentTimeMillis - b) < ((long) i);
        b = jCurrentTimeMillis;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m(EditText editText, View view) {
        String string = editText.getText().toString();
        if (Build.VERSION.SDK_INT >= 26) {
            string = rg.a(string);
        } else {
            this.m = "asdf@4321";
        }
        BydLogHelper.logW(this.c, "btnConfirm password = " + string);
        if (!this.m.equals(string)) {
            Toast.makeText(this, getString(R.string.str_password_error), 0).show();
            editText.setText("");
            return;
        }
        BydLogHelper.logW(this.c, "password is ok!");
        SystemProperties.set("persist.sys.start.energy.test", "true");
        this.u.setEnergyTest(0);
        editText.setText("");
        this.l.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(View view) {
        this.l.dismiss();
    }

    public String i() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            mg.b(getApplicationContext(), getString(R.string.text_4));
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            mg.b(getApplicationContext(), getString(R.string.text_1));
            return null;
        }
        if (activeNetworkInfo.getType() != 1) {
            return null;
        }
        WifiInfo connectionInfo = ((WifiManager) getSystemService("wifi")).getConnectionInfo();
        String strJ = j(connectionInfo.getIpAddress());
        this.g.setText(strJ);
        mg.b(getApplicationContext(), getString(R.string.text_2) + connectionInfo.getPasspointProviderFriendlyName() + getString(R.string.text_3));
        return strJ;
    }

    public final void initData() {
        int i = Settings.Global.getInt(getContentResolver(), "adb_enabled", 0);
        int i2 = Settings.System.getInt(getContentResolver(), "show_touches", 0);
        if (i2 == 0) {
            this.d.setChecked(false);
        } else {
            this.d.setChecked(true);
        }
        int i3 = Settings.System.getInt(getContentResolver(), "pointer_location", 0);
        if (i3 == 0) {
            this.e.setChecked(false);
        } else {
            this.e.setChecked(true);
        }
        BydLogHelper.logE("enableAdb / " + i + ", touchMode / " + i2 + ", pointer_location / " + i3);
    }

    public final void initView() {
        Log.d("ban", "enableAdb / " + Settings.Global.getInt(getContentResolver(), "adb_enabled", 0));
        this.i = (Button) findViewById(R.id.bt_linkusb);
        this.d = (Switch) findViewById(R.id.sw_visual_effect);
        this.e = (Switch) findViewById(R.id.sw_touch_data);
        this.f = (Switch) findViewById(R.id.sw_wireless_adb);
        this.g = (TextView) findViewById(R.id.tv_wireless_ip);
        this.h = (TextView) findViewById(R.id.tv_factory_service_interface);
        this.j = (Button) findViewById(R.id.bt_link_sv_usb);
        this.k = (TextView) findViewById(R.id.tvRollBench);
        this.d.setOnCheckedChangeListener(this);
        this.f.setOnCheckedChangeListener(this);
        this.e.setOnCheckedChangeListener(this);
        this.i.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.password_dialog, (ViewGroup) null);
        builder.setView(viewInflate);
        this.l = builder.create();
        final EditText editText = (EditText) viewInflate.findViewById(R.id.password_edit);
        viewInflate.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() { // from class: wf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.b.m(editText, view);
            }
        });
        viewInflate.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() { // from class: xf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.b.o(view);
            }
        });
    }

    public String j(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    @SuppressLint({"NewApi"})
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        BydLogHelper.logE("ban", "buttonView.getId() / " + compoundButton.getId() + ", isChecked / " + z);
        if (id != R.id.sw_wireless_adb) {
            return;
        }
        s(z);
        qg.a(this, z);
        StringBuilder sb = new StringBuilder();
        sb.append("setprop persist.sys.adb.debug.wiress.enable ");
        sb.append(z ? "true" : "false");
        SystemProperties.set("persist.sys.adb.wiress.enable", z ? "true" : "false");
        SystemProperties.set("sys.connect.adb.wiress", z ? "1" : "0");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_link_sv_usb /* 2131296367 */:
                if (!k(3000)) {
                    kg.b(this, getApplicationContext());
                }
                break;
            case R.id.bt_linkusb /* 2131296368 */:
                if (!k(3000)) {
                    kg.a(this, getApplicationContext());
                }
                break;
            case R.id.sw_touch_data /* 2131296818 */:
                q();
                break;
            case R.id.sw_visual_effect /* 2131296819 */:
                r();
                break;
            case R.id.tvRollBench /* 2131296891 */:
                this.l.show();
                break;
            case R.id.tv_factory_service_interface /* 2131296926 */:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this, (Class<?>) CanActivity.class));
                startActivity(intent);
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_adb);
        if (e7.a(this, "android.permission.WRITE_SECURE_SETTINGS") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.WRITE_SECURE_SETTINGS"}, 0);
            return;
        }
        if (e7.a(this, "android.permission.WRITE_SETTINGS") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.WRITE_SETTINGS"}, 0);
            return;
        }
        this.u = BYDAutoTestDevice.getInstance(getApplicationContext());
        this.r = getContentResolver();
        this.s = new c(null);
        this.t = new b();
        initView();
        initData();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.r.unregisterContentObserver(this.s);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.r.registerContentObserver(Settings.Global.getUriFor("adb_enabled"), true, this.s);
    }

    public final void p() {
        if (Settings.Global.getInt(getContentResolver(), "adb_enabled", 0) == 1) {
            Settings.Global.putInt(getContentResolver(), "adb_enabled", 0);
        } else {
            Settings.Global.putInt(getContentResolver(), "adb_enabled", 1);
        }
    }

    public final void q() {
        int i = Settings.System.getInt(getContentResolver(), "pointer_location", 0);
        BydLogHelper.logE(", pointer_location / " + i + ", sw_touch_data.isChecked() / " + this.e.isChecked());
        if (this.e.isChecked() || i != 1) {
            Settings.System.putInt(getContentResolver(), "pointer_location", 1);
            this.e.setChecked(true);
        } else {
            Settings.System.putInt(getContentResolver(), "pointer_location", 0);
            this.e.setChecked(false);
        }
    }

    public final void r() {
        int i = Settings.System.getInt(getContentResolver(), "show_touches", 0);
        BydLogHelper.logE(", touchMode / " + i + ", sw_visual_effect.isChecked() / " + this.d.isChecked());
        if (this.d.isChecked() || i != 1) {
            Settings.System.putInt(getContentResolver(), "show_touches", 1);
            this.d.setChecked(true);
        } else {
            Settings.System.putInt(getContentResolver(), "show_touches", 0);
            this.d.setChecked(false);
        }
    }

    public final void s(boolean z) {
        if (z) {
            i();
        }
    }
}
