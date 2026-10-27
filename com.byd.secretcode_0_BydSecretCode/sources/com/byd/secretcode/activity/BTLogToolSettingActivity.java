package com.byd.secretcode.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.mg;
import defpackage.og;
import defpackage.ug;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BTLogToolSettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    public static String b = "/mnt/media_rw/63B6-453C/";
    public static String c = "usbotg";
    public static String d = "sdcard";
    public static final String e = Environment.getExternalStorageDirectory().getPath() + "/btlogs/";
    public static String f = null;
    public static boolean g = false;
    public File i;
    public CheckBoxPreference j;
    public Preference k;
    public CheckBoxPreference l;
    public CheckBoxPreference m;
    public Preference n;
    public TextView o;
    public boolean p;
    public boolean q;
    public boolean r;
    public WifiManager s;
    public List<File> h = new ArrayList();
    public boolean t = false;
    public BluetoothAdapter u = BluetoothAdapter.getDefaultAdapter();
    public final BroadcastReceiver v = new a();
    public Comparator<File> w = new b();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED") && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10) == 10 && BTLogToolSettingActivity.this.t) {
                BTLogToolSettingActivity.this.u.enable();
                BTLogToolSettingActivity.this.t = false;
            }
        }
    }

    public class b implements Comparator<File> {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            if (file.lastModified() > file2.lastModified()) {
                return 1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : -1;
        }
    }

    public class c implements Runnable {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BTLogToolSettingActivity bTLogToolSettingActivity = BTLogToolSettingActivity.this;
                mg.a(bTLogToolSettingActivity, bTLogToolSettingActivity.getApplicationContext().getString(R.string.str_start_save_log));
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BTLogToolSettingActivity bTLogToolSettingActivity = BTLogToolSettingActivity.this;
                mg.a(bTLogToolSettingActivity, bTLogToolSettingActivity.getResources().getString(R.string.str_save_log_finish));
            }
        }

        /* JADX INFO: renamed from: com.byd.secretcode.activity.BTLogToolSettingActivity$c$c, reason: collision with other inner class name */
        public class RunnableC0004c implements Runnable {
            public RunnableC0004c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BTLogToolSettingActivity bTLogToolSettingActivity = BTLogToolSettingActivity.this;
                mg.a(bTLogToolSettingActivity, bTLogToolSettingActivity.getResources().getString(R.string.str_save_log_fail));
            }
        }

        public /* synthetic */ c(BTLogToolSettingActivity bTLogToolSettingActivity, a aVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean unused = BTLogToolSettingActivity.g = true;
            File file = new File(BTLogToolSettingActivity.f);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (file.isFile()) {
                file.delete();
                file.mkdirs();
            }
            if (BTLogToolSettingActivity.f.contains("/storage/emulated/0")) {
                BTLogToolSettingActivity.this.i();
            }
            BTLogToolSettingActivity.this.runOnUiThread(new a());
            ug.b("/data/misc/bluetooth/logs/", BTLogToolSettingActivity.f);
            if (ug.c()) {
                BTLogToolSettingActivity.this.runOnUiThread(new b());
            } else {
                BTLogToolSettingActivity.this.runOnUiThread(new RunnableC0004c());
            }
            boolean unused2 = BTLogToolSettingActivity.g = false;
        }

        public c() {
        }
    }

    public String a(String str) {
        File file = new File(str);
        boolean zExists = file.exists();
        Log.i("BTLogToolSettingActivity", "ReadTxtFromFile mLinkKey exists = " + zExists);
        String str2 = "";
        if (!zExists) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (file.isDirectory()) {
            Log.e("BTLogToolSettingActivity", "ReadTxtFromFile The File doesn't not exist.");
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    String line = bufferedReader.readLine();
                    Log.d("BTLogToolSettingActivity", "ReadTxtFromFile mLinkKey readLine = " + line);
                    if (line == null) {
                        break;
                    }
                    str2 = str2 + line + BydAdbHelper.COMMAND_LINE_END;
                    if (line.startsWith("[") && line.contains(":")) {
                        sb.append(line);
                        sb.append(BydAdbHelper.COMMAND_LINE_END);
                    } else if (line.contains("Name =")) {
                        sb.append(line);
                        sb.append(BydAdbHelper.COMMAND_LINE_END);
                    } else if (line.contains("LinkKey = ")) {
                        sb.append(line);
                        sb.append("\n\n");
                    }
                }
                Log.d("BTLogToolSettingActivity", "ReadTxtFromFile mLinkKey = " + sb.toString());
                TextView textView = this.o;
                if (textView != null) {
                    textView.setText(sb.toString());
                }
                fileInputStream.close();
            } catch (FileNotFoundException e2) {
                Log.e("BTLogToolSettingActivity", "FileNotFoundException unused = " + e2.getMessage());
            } catch (IOException e3) {
                Log.e("BTLogToolSettingActivity", "IOException , e = " + e3.getMessage());
            }
        }
        return str2;
    }

    public final void b() {
        this.h.clear();
        File[] fileArrListFiles = this.i.listFiles();
        if (fileArrListFiles == null) {
            Log.e("BTLogToolSettingActivity", "UpdateBtLogFile: There is no log at " + e);
            return;
        }
        for (File file : fileArrListFiles) {
            this.h.add(file);
        }
        Collections.sort(this.h, this.w);
    }

    public final void i() {
        if (this.h == null) {
            this.h = new ArrayList();
        }
        File file = new File(e);
        this.i = file;
        if (file.exists()) {
            b();
            while (this.h.size() > 5) {
                Log.i("BTLogToolSettingActivity", "file length size more over 5, mBTLogList.get(0) = " + this.h.get(0));
                j(this.h.get(0));
                b();
            }
        }
    }

    public final void j(File file) {
        if (file != null) {
            try {
                if (!file.isDirectory()) {
                    Log.d("BTLogToolSettingActivity", "delet file = " + file.getName());
                    file.delete();
                    return;
                }
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        file2.delete();
                        Log.d("BTLogToolSettingActivity", " : delet, fileson = " + file2.getName());
                    }
                }
                Log.d("BTLogToolSettingActivity", "delet dectory = " + file.getName());
                file.delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final boolean k() {
        boolean zEquals = Environment.getExternalStorageState().equals("mounted");
        boolean zL = l(this);
        Log.i("BTLogToolSettingActivity", "isFileSystemAvailable mediaMounted = " + zEquals + "  isUDiskMount = " + zL);
        if (!zL) {
            if (!zEquals) {
                return false;
            }
            f = e;
            return false;
        }
        f = b + "/btlogs";
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.util.Iterator] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public boolean l(Context context) {
        try {
            List volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            boolean z = SystemProperties.getBoolean("sys.byd.isUSBExist", false);
            BydLogHelper.logD("isUDiskMount isUSBExist = " + z);
            if (volumes != null && z) {
                BydLogHelper.logD("volumes size = " + volumes.size());
                Iterator it = volumes.iterator();
                while (it.hasNext()) {
                    VolumeInfo volumeInfo = (VolumeInfo) it.next();
                    try {
                        BydLogHelper.logD("addDisk volumeInfo = " + volumeInfo);
                        if (c.equals(volumeInfo.getDeviceType())) {
                            b = volumeInfo.internalPath;
                            BydLogHelper.logD("USBOTG BLUETOOTH_LOG_NEW_USB_PATH = " + b);
                            it = 1;
                            return true;
                        }
                        if (d.equals(volumeInfo.getDeviceType())) {
                            BydLogHelper.logD("SDCARD BLUETOOTH_LOG_NEW_USB_PATH ");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            BydLogHelper.logE("initVolume failed" + th2);
        }
        return false;
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_btlog_setting);
        addPreferencesFromResource(R.xml.bt_log);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) getPreferenceScreen().findPreference("BYDBluetoothLogCheckBox");
        this.j = checkBoxPreference;
        checkBoxPreference.setChecked(SystemProperties.get("persist.bluetooth.btsnooplog.enable", "false").equals("true"));
        this.j.setOnPreferenceChangeListener(this);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference("BYDSaveBluetoothLog");
        this.k = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(this);
        this.q = SystemProperties.get("persist.bluetooth.open.error", "true").equals("true");
        CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) getPreferenceScreen().findPreference("BYDBluetoothOpenErrorBox");
        this.l = checkBoxPreference2;
        checkBoxPreference2.setChecked(this.q);
        this.l.setOnPreferenceChangeListener(this);
        this.r = SystemProperties.get("persist.wifi.verbose.logs.enable", "false").equals("true");
        CheckBoxPreference checkBoxPreference3 = (CheckBoxPreference) getPreferenceScreen().findPreference("BYDWifiVerboseLogBox");
        this.m = checkBoxPreference3;
        checkBoxPreference3.setChecked(this.r);
        this.m.setOnPreferenceChangeListener(this);
        Preference preferenceFindPreference2 = getPreferenceScreen().findPreference("BYDGetLinkKey");
        this.n = preferenceFindPreference2;
        preferenceFindPreference2.setOnPreferenceClickListener(this);
        TextView textView = (TextView) findViewById(R.id.bt_link_key);
        this.o = textView;
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.s = (WifiManager) getSystemService("wifi");
        Log.i("BTLogToolSettingActivity", "getInitValue, BlueTooth =" + this.p);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.v);
        this.t = false;
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference.getKey().equals("BYDBluetoothLogCheckBox")) {
            this.j.setChecked(!r4.isChecked());
            boolean zIsChecked = this.j.isChecked();
            this.p = zIsChecked;
            SystemProperties.set("persist.bluetooth.btsnooplog.enable", Boolean.toString(zIsChecked));
            if (this.p) {
                SystemProperties.set("persist.bluetooth.btsnooplogmode", "full");
                mg.a(this, getResources().getString(R.string.str_reopen_bt));
                if (this.u.isEnabled()) {
                    this.t = true;
                    Log.i("BTLogToolSettingActivity", "bluetooth disable by log tool");
                    this.u.disable();
                }
            } else {
                mg.a(this, getResources().getString(R.string.str_have_no_btlog));
            }
        } else if (preference.getKey().equals("BYDBluetoothOpenErrorBox")) {
            this.l.setChecked(!r4.isChecked());
            boolean zIsChecked2 = this.l.isChecked();
            this.q = zIsChecked2;
            SystemProperties.set("persist.bluetooth.open.error", Boolean.toString(zIsChecked2));
        } else if (preference.getKey().equals("BYDWifiVerboseLogBox")) {
            this.m.setChecked(!r4.isChecked());
            boolean zIsChecked3 = this.m.isChecked();
            this.r = zIsChecked3;
            SystemProperties.set("persist.wifi.verbose.logs.enable", Boolean.toString(zIsChecked3));
            if (this.r) {
                mg.a(this, getResources().getString(R.string.str_have_wifi_verbose_log));
            } else {
                mg.a(this, getResources().getString(R.string.str_have_no_wifi_verbose_log));
            }
            try {
                og.a(this.s, "enableVerboseLogging", Integer.TYPE, Integer.valueOf(this.m.isChecked() ? 1 : 0));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return true;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        Log.i("BTLogToolSettingActivity", "onPreferenceClick : " + preference.getKey());
        if (!preference.getKey().equals("BYDSaveBluetoothLog")) {
            if (!preference.getKey().equals("BYDGetLinkKey")) {
                return true;
            }
            a("/data/misc/bluedroid/bt_config.conf");
            return true;
        }
        if (!g && k() && f != null) {
            new Thread(new c(this, null)).start();
        }
        if (g) {
            mg.a(this, getResources().getString(R.string.str_start_save_log));
        }
        if (l(this)) {
            return true;
        }
        mg.a(this, getResources().getString(R.string.str_no_udisk_save_logs));
        return true;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.v, intentFilter);
    }
}
