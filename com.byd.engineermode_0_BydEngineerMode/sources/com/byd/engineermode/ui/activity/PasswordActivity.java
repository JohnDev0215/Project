package com.byd.engineermode.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.IUpdateListener;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.R;
import c.a.b.a;
import com.byd.engineermode.DiagnoseApplication;
import com.byd.engineermode.utils.UsbCheckReceiver;
import java.io.File;
import java.io.FileFilter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PasswordActivity extends BaseActivity implements View.OnClickListener, IUpdateListener {
    private static String A = "usbotg";
    private static String B = "sdcard";
    private static String C = null;
    private static final String D = Environment.getExternalStorageDirectory().getPath() + "/TBoxLogs";
    public static String v = "/data/cache/localTboxFile/";
    private static String w = "BYD_AG35EVD_UpdatePackage_";
    private static String x = "NEUSOFT_TBOX_";
    private static String y = "TBOX_";
    private static String z;
    private BYDAutoTBoxDevice B0;
    EditText E;
    LinearLayout F;
    private c.a.b.a F0;
    ScrollView G;
    TextView H;
    TextView I;
    TextView J;
    TextView K;
    TextView L;
    TextView M;
    TextView N;
    Button O;
    private List<File> d0;
    private List<String> e0;
    private List<File> f0;
    i g0;
    Dialog h0;
    Dialog i0;
    OtaUpgradeManager j0;
    BYDAutoBodyworkDevice k0;
    BYDAutoGearboxDevice l0;
    BYDAutoOtaDevice m0;
    List<File> o0;
    File p0;
    com.byd.engineermode.ui.dialog.a q0;
    com.byd.engineermode.ui.dialog.b r0;
    com.byd.engineermode.ui.dialog.b s0;
    File t0;
    private String P = "";
    private final int Q = 6;
    private final int R = 0;
    private final int S = -2147482648;
    private final int T = -2147482647;
    private final int U = -2147482646;
    private final int V = -2147482645;
    private final String W = "tp_1";
    private final String X = "tp_2";
    private final String Y = "tp_3";
    private final String Z = "disp_1";
    private final String a0 = "disp_2";
    private final String b0 = "disp_3";
    private final String c0 = "PA_IC2034";
    boolean n0 = true;
    private final int u0 = 1;
    private final int v0 = 2;
    private final int w0 = 3;
    private final int x0 = 4;
    private final int y0 = 5;
    private final int z0 = 6;
    private boolean A0 = false;
    private final int C0 = -1;
    private final int D0 = 0;
    private final int E0 = 1;
    private int G0 = 2;
    private String H0 = "暗码-U盘升级";
    private final int I0 = 1;
    private final int J0 = 0;
    private final BroadcastReceiver K0 = new UsbCheckReceiver();
    private String L0 = "0B8B5164CAD9CC7A070F5242ABE5A215";
    Comparator<File> M0 = new h();

    class a implements TextWatcher {
        a() {
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

    class b extends AbsBYDAutoGearboxListener {
        b() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeTypeChanged(int i) {
            super.onGearboxAutoModeTypeChanged(i);
            if (i == 1) {
                return;
            }
            PasswordActivity.this.g0.sendEmptyMessage(6);
        }
    }

    class c extends AbsBYDAutoBodyworkListener {
        c() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i) {
            super.onPowerLevelChanged(i);
            if (i == 2) {
                return;
            }
            PasswordActivity.this.g0.sendEmptyMessage(6);
        }
    }

    class d implements ServiceConnection {
        d() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PasswordActivity.this.F0 = a.AbstractBinderC0057a.j(iBinder);
            PasswordActivity.this.g0.sendEmptyMessage(13);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.byd.engineermode.utils.b.a().e("TBoxUpgrade");
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.byd.engineermode.utils.b.a().e("IVIUpgrade");
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.byd.engineermode.utils.i.c("PasswordActivity", " BydSPHelper.getInstance().saveMessage(TBoxLogExport); TBoxLogExport ");
            com.byd.engineermode.utils.b.a().e("TBoxLogExport");
        }
    }

    class h implements Comparator<File> {
        h() {
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

    private class i extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<PasswordActivity> f2349a;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.byd.engineermode.utils.h.a(new File(PasswordActivity.v));
                    if (com.byd.engineermode.utils.h.e(PasswordActivity.v)) {
                        File file = new File(PasswordActivity.v, PasswordActivity.this.t0.getName());
                        PasswordActivity.this.g0.sendEmptyMessageDelayed(3, 100L);
                        com.byd.engineermode.utils.h.c(PasswordActivity.this.t0, file);
                        if (file.exists()) {
                            com.byd.engineermode.utils.i.a("PasswordActivity", " BEGIN_COPY_TBOX_UPGRADE_FILE start Update Tbox = " + file.getPath());
                            PasswordActivity.this.j0.startUpdateTbox(file.getPath());
                        } else {
                            com.byd.engineermode.utils.i.b("PasswordActivity", " BEGIN_COPY_TBOX_UPGRADE_FILE destFile = " + file.getPath() + " is no exists");
                            PasswordActivity.this.h0.dismiss();
                        }
                    } else {
                        com.byd.engineermode.utils.i.b("PasswordActivity", " BEGIN_COPY_TBOX_UPGRADE_FILE TBOX_UPGRADE_ROOT_DIR = " + PasswordActivity.v + " is no exists");
                        PasswordActivity.this.h0.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        class b implements FileFilter {
            b() {
            }

            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".bin");
            }
        }

        i(PasswordActivity passwordActivity) {
            this.f2349a = new WeakReference<>(passwordActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2349a.get() == null) {
                com.byd.engineermode.utils.a.c("outer is null");
            }
            switch (message.what) {
                case 1:
                    com.byd.engineermode.utils.i.a("PasswordActivity", " BEGIN_CHECK_TBOX_UPGRADE_STATUS");
                    if (PasswordActivity.this.g0()) {
                        PasswordActivity.this.d0.clear();
                        File file = new File(PasswordActivity.z);
                        if (file.exists()) {
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles == null) {
                                com.byd.engineermode.utils.i.a("PasswordActivity", "BEGIN_CHECK_TBOX_UPGRADE_STATUS : There is no TBox upgrade file at " + PasswordActivity.z);
                                PasswordActivity.this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                            } else {
                                for (File file2 : fileArrListFiles) {
                                    if ((file2.getName().contains(PasswordActivity.w) || file2.getName().contains(PasswordActivity.x) || file2.getName().startsWith(PasswordActivity.y)) && file2.getName().endsWith(".bin")) {
                                        PasswordActivity.this.d0.add(file2);
                                    }
                                }
                                if (PasswordActivity.this.d0.size() == 1) {
                                    com.byd.engineermode.utils.i.a("PasswordActivity", "BEGIN_CHECK_TBOX_UPGRADE_STATUS mTBoxUpgradeFilePath = " + ((File) PasswordActivity.this.d0.get(0)).getPath());
                                    Message message2 = new Message();
                                    message2.what = 2;
                                    message2.obj = PasswordActivity.this.d0.get(0);
                                    PasswordActivity.this.g0.sendMessage(message2);
                                } else if (PasswordActivity.this.d0.size() > 1) {
                                    PasswordActivity.this.q0.b(R.string.str_udisk_check_one_upgrade_files);
                                } else {
                                    PasswordActivity.this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    PasswordActivity.this.t0 = (File) message.obj;
                    com.byd.engineermode.utils.i.a("PasswordActivity", "BEGIN_COPY_TBOX_UPGRADE_FILE mUpgradeFile = " + PasswordActivity.this.t0.getPath());
                    PasswordActivity passwordActivity = PasswordActivity.this;
                    passwordActivity.h0 = passwordActivity.r0.e(R.string.str_update_tbox_waitting);
                    new Thread(new a()).start();
                    break;
                case 3:
                    com.byd.engineermode.utils.i.a("PasswordActivity", "UPDAGE_TBOX_UPGRADE_Progress");
                    if (PasswordActivity.this.t0 != null) {
                        File file3 = new File(PasswordActivity.v, PasswordActivity.this.t0.getName());
                        if (!file3.exists() || !PasswordActivity.this.t0.exists()) {
                            PasswordActivity.this.g0.removeMessages(3);
                        } else if (PasswordActivity.this.r0 != null) {
                            com.byd.engineermode.utils.a.a(" UPDAGE_TBOX_UPGRADE_Progress destFile = " + com.byd.engineermode.utils.h.d(file3) + ", destFile path = " + file3.getPath() + ", mUpgradeFile= " + com.byd.engineermode.utils.h.d(PasswordActivity.this.t0) + ", destFile path = " + PasswordActivity.this.t0.getPath());
                            int iD = (int) ((com.byd.engineermode.utils.h.d(file3) / com.byd.engineermode.utils.h.d(PasswordActivity.this.t0)) * 100.0f);
                            i = iD >= 0 ? iD > 100 ? 100 : iD : 0;
                            PasswordActivity.this.r0.d(i);
                            if (i != 100) {
                                PasswordActivity.this.g0.sendEmptyMessageDelayed(3, 100L);
                            }
                        }
                        break;
                    }
                    break;
                case 4:
                    if (com.byd.engineermode.utils.i.e()) {
                        com.byd.engineermode.utils.a.a(" UPDATE_TBOX_UPGRADE_FILE_ERROR");
                    }
                    Dialog dialog = PasswordActivity.this.h0;
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    int iIntValue = ((Integer) message.obj).intValue();
                    String string = PasswordActivity.this.getString(R.string.str_udisk_upgrade_fail_msg);
                    if (iIntValue == 1) {
                        string = PasswordActivity.this.getResources().getString(R.string.str_tobx_upgrade_tbox_timeout);
                    } else if (iIntValue == 2) {
                        string = PasswordActivity.this.getResources().getString(R.string.str_tobx_upgrade_verification_error);
                    } else if (iIntValue == 3) {
                        string = PasswordActivity.this.getResources().getString(R.string.str_tobx_upgrade_upgrade_failed);
                    } else if (iIntValue == 4) {
                        string = PasswordActivity.this.getResources().getString(R.string.str_tobx_upgrade_ftp_timeout);
                    } else if (iIntValue == 5) {
                        string = PasswordActivity.this.getResources().getString(R.string.str_tobx_upgrade_ftp_request_timeout);
                    } else if (iIntValue == 6) {
                        string = PasswordActivity.this.getResources().getString(R.string.str_tobx_upgrade_tbox_link_exception);
                    }
                    com.byd.engineermode.ui.dialog.b.b(PasswordActivity.this).f(PasswordActivity.this.getString(R.string.str_udisk_upgrade_tbox), string, 2);
                    com.byd.engineermode.utils.h.b(PasswordActivity.v);
                    break;
                case 5:
                    if (com.byd.engineermode.utils.i.e()) {
                        com.byd.engineermode.utils.a.a(" UPDATE_TBOX_UPGRADE_FILE_SUCCESS");
                    }
                    Dialog dialog2 = PasswordActivity.this.h0;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                    }
                    com.byd.engineermode.ui.dialog.b.b(PasswordActivity.this).g(PasswordActivity.this.getString(R.string.str_udisk_upgrade_tbox), PasswordActivity.this.getString(R.string.str_udisk_upgrade_pass_msg), false);
                    com.byd.engineermode.utils.h.b(PasswordActivity.v);
                    break;
                case 6:
                    com.byd.engineermode.utils.a.a(" POWER_STATUS_CHANGE_STOP_UPGRADE");
                    break;
                case 7:
                    com.byd.engineermode.utils.i.a("PasswordActivity", " BEGIN_CHECK_USB_UPGRADE_STATUS");
                    if (PasswordActivity.this.g0()) {
                        File file4 = new File(PasswordActivity.z, "all_images");
                        if (!file4.exists() || !file4.isDirectory()) {
                            com.byd.engineermode.utils.i.c("PasswordActivity", "BEGIN_CHECK_USB_UPGRADE_STATUS : There is no TBox upgrade file at " + PasswordActivity.z);
                            PasswordActivity.this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                        } else if (com.byd.engineermode.utils.e.d().b()) {
                            com.byd.engineermode.utils.i.b("PasswordActivity", "BEGIN_CHECK_USB_UPGRADE_STATUS vendor.bosch.recovery.mode = 1 ");
                            SystemProperties.set("vendor.bosch.recovery.mode", "1");
                        } else if (!com.byd.engineermode.utils.e.d().a()) {
                            PasswordActivity.this.q0.b(R.string.str_udiskupgrade_check_images);
                        } else if (PasswordActivity.this.d0()) {
                            com.byd.engineermode.utils.i.b("PasswordActivity", "BEGIN_CHECK_USB_UPGRADE_STATUS vendor.bosch.recovery.mode = 1 ");
                            SystemProperties.set("vendor.bosch.recovery.mode", "1");
                        }
                    }
                    break;
                case 8:
                    com.byd.engineermode.utils.a.a(" CHECK_SDCARD_TBOX_LOGS mTBoxUpgradeLogTargetPath = " + PasswordActivity.C);
                    if (!PasswordActivity.this.m0() || PasswordActivity.C == null) {
                        if (PasswordActivity.C != null && PasswordActivity.C.contains(PasswordActivity.D)) {
                            com.byd.engineermode.ui.dialog.a.a(PasswordActivity.this).b(R.string.str_udisk_no_exists);
                        }
                        com.byd.engineermode.utils.a.c("CHECK_SDCARD_TBOX_LOGS exportTBoxLogStatus = " + PasswordActivity.this.A0);
                        PasswordActivity.this.A0 = false;
                    } else {
                        File file5 = new File(PasswordActivity.C);
                        if (!file5.exists()) {
                            com.byd.engineermode.utils.a.c(" CHECK_SDCARD_TBOX_LOGS dir.exists() = false, mkdirs = " + file5.mkdirs());
                        }
                        if (file5.isFile()) {
                            file5.delete();
                            com.byd.engineermode.utils.a.c(" CHECK_SDCARD_TBOX_LOGS dir.isFile(), mkdirs = " + file5.mkdirs());
                        }
                        PasswordActivity.this.q0.b(R.string.str_export_tbox_upgrade_log);
                        PasswordActivity passwordActivity2 = PasswordActivity.this;
                        passwordActivity2.i0 = passwordActivity2.s0.e(R.string.str_export_tbox_upgrade_log_content);
                        PasswordActivity.this.s0.c(R.string.defaultText);
                        PasswordActivity.this.s0.d(0);
                        if (PasswordActivity.C.contains("/storage/emulated/0")) {
                            PasswordActivity.this.e0();
                        } else if (PasswordActivity.this.f0(PasswordActivity.C)) {
                            com.byd.engineermode.utils.a.j(" checkUDiskLogs usb no spaces exportTBoxLogStatus = " + PasswordActivity.this.A0);
                            Dialog dialog3 = PasswordActivity.this.i0;
                            if (dialog3 != null) {
                                dialog3.dismiss();
                            }
                            PasswordActivity.this.q0.b(R.string.export_log_fail_no_space);
                            PasswordActivity.this.A0 = false;
                        }
                        PasswordActivity.this.g0.sendEmptyMessage(9);
                    }
                    break;
                case 9:
                    com.byd.engineermode.utils.a.a(" EXPORT_TBOX_LOGS mTBoxUpgradeLogTargetPath = " + PasswordActivity.C);
                    boolean tBoxUpgradeLog = PasswordActivity.this.m0.getTBoxUpgradeLog(PasswordActivity.C);
                    com.byd.engineermode.utils.i.c("PasswordActivity", "getTBoxUpgradeLog =  " + tBoxUpgradeLog);
                    if (!tBoxUpgradeLog) {
                        PasswordActivity.this.g0.sendEmptyMessage(10);
                    }
                    break;
                case 10:
                    com.byd.engineermode.utils.a.a(" EXPORT_TBOX_LOGS_ERROR exportTBoxLogStatus = " + PasswordActivity.this.A0);
                    Dialog dialog4 = PasswordActivity.this.i0;
                    if (dialog4 != null) {
                        dialog4.dismiss();
                    }
                    PasswordActivity.this.q0.b(R.string.str_export_tbox_log_fail_msg);
                    PasswordActivity.this.A0 = false;
                    break;
                case 11:
                    com.byd.engineermode.utils.a.a(" EXPORT_TBOX_LOGS_SUCCESS exportTBoxLogStatus = " + PasswordActivity.this.A0);
                    Dialog dialog5 = PasswordActivity.this.i0;
                    if (dialog5 != null) {
                        dialog5.dismiss();
                    }
                    PasswordActivity.this.q0.b(R.string.str_export_tbox_log_success_msg);
                    PasswordActivity.this.A0 = false;
                    break;
                case 12:
                    com.byd.engineermode.utils.a.a(" UPGRADE_TP_OR_SCREEN_FW_CHECK");
                    if (PasswordActivity.this.g0()) {
                        PasswordActivity.this.e0.clear();
                        File file6 = new File(PasswordActivity.z, "all_images/display");
                        if (file6.exists() && file6.isDirectory()) {
                            File[] fileArrListFiles2 = file6.listFiles(new b());
                            if (fileArrListFiles2 == null || fileArrListFiles2.length == 0) {
                                com.byd.engineermode.utils.i.a("PasswordActivity", "UPGRADE_TP_OR_SCREEN_FW_CHECK : There is no tp  and screen fw bin file at " + PasswordActivity.z);
                                PasswordActivity.this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                            } else {
                                int length = fileArrListFiles2.length;
                                while (i < length) {
                                    File file7 = fileArrListFiles2[i];
                                    if (file7.getName().contains("tp_1")) {
                                        PasswordActivity.this.e0.add("tp_1");
                                    }
                                    if (file7.getName().contains("tp_2")) {
                                        PasswordActivity.this.e0.add("tp_2");
                                    }
                                    if (file7.getName().contains("tp_3")) {
                                        PasswordActivity.this.e0.add("tp_3");
                                    }
                                    if (file7.getName().contains("disp_1")) {
                                        PasswordActivity.this.e0.add("disp_1");
                                    }
                                    if (file7.getName().contains("disp_2")) {
                                        PasswordActivity.this.e0.add("disp_2");
                                    }
                                    if (file7.getName().contains("disp_3")) {
                                        PasswordActivity.this.e0.add("disp_3");
                                    }
                                    i++;
                                }
                                if (PasswordActivity.this.e0.size() >= 1) {
                                    com.byd.engineermode.utils.i.a("PasswordActivity", "BEGIN_CHECK_TBOX_UPGRADE_STATUS allTPFWFileList.size() = " + PasswordActivity.this.e0.size());
                                    HashMap map = new HashMap();
                                    for (String str : PasswordActivity.this.e0) {
                                        if (map.get(str) != null) {
                                            com.byd.engineermode.utils.i.a("PasswordActivity", "check fail: There is one more  tp  and screen fw file");
                                            PasswordActivity.this.q0.b(R.string.str_udisk_check_one_upgrade_files);
                                        } else {
                                            map.put(str, 1);
                                        }
                                        break;
                                    }
                                    com.byd.engineermode.utils.i.a("PasswordActivity", "There is only one type tp and screen fw file, map = " + map.toString());
                                    com.byd.engineermode.utils.i.b("PasswordActivity", "BEGIN_CHECK_USB_UPGRADE_STATUS vendor.bosch.recovery.mode = 1 ");
                                    SystemProperties.set("vendor.bosch.recovery.mode", "1");
                                } else {
                                    com.byd.engineermode.utils.i.a("PasswordActivity", "There is no tp  and screen fw file");
                                    PasswordActivity.this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                                }
                            }
                        } else {
                            com.byd.engineermode.utils.i.c("PasswordActivity", "BEGIN_CHECK_USB_UPGRADE_STATUS : There is no TBox upgrade file at " + PasswordActivity.z);
                            PasswordActivity.this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                        }
                    }
                    break;
                case 13:
                    com.byd.engineermode.utils.a.a(" REQUEST_AUTH");
                    break;
                case 14:
                    com.byd.engineermode.utils.a.a(" CHECK_AUTH_SUCCESS");
                    PasswordActivity.this.F.setVisibility(8);
                    PasswordActivity.this.G.setVisibility(0);
                    break;
            }
        }
    }

    private void M() {
        this.o0.clear();
        File[] fileArrListFiles = this.p0.listFiles();
        if (fileArrListFiles == null) {
            Log.d("PasswordActivity", "initTBoxUpgradeLog: There is no log at " + D);
            return;
        }
        for (File file : fileArrListFiles) {
            this.o0.add(file);
        }
        Collections.sort(this.o0, this.M0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d0() {
        this.f0.clear();
        File file = new File(z, "all_images");
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                com.byd.engineermode.utils.i.b("PasswordActivity", "checkDeSayImage : There is no TBox upgrade file at " + z + "/all_images");
                this.q0.b(R.string.str_udisk_check_no_upgrade_file);
                return false;
            }
            boolean z2 = false;
            for (File file2 : fileArrListFiles) {
                if (file2.getName().startsWith("PA_IC2034") && file2.getName().endsWith("_ALL.zip")) {
                    this.f0.add(file2);
                }
                if (file2.getName().equals("mcu.zip")) {
                    z2 = true;
                }
            }
            if (this.f0.size() == 1 && !z2) {
                com.byd.engineermode.utils.i.a("PasswordActivity", "allDeSayUpgradeFileNameList.size() == 1 ,  isMcuFile is false");
                return true;
            }
            if (this.f0.size() == 0 && z2) {
                com.byd.engineermode.utils.i.a("PasswordActivity", "allDeSayUpgradeFileNameList.size() == 0 ,  isMcuFile is true");
                return true;
            }
            if (this.f0.size() >= 1) {
                this.q0.b(R.string.str_udisk_check_one_upgrade_files);
            } else {
                this.q0.b(R.string.str_udisk_check_no_upgrade_file);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0() {
        if (this.o0 == null) {
            this.o0 = new ArrayList();
        }
        File file = new File(D);
        this.p0 = file;
        if (file.exists()) {
            M();
            while (this.o0.size() > 10) {
                Log.i("PasswordActivity", "file length size more over 10");
                h0(this.o0.get(0));
                M();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f0(String str) {
        long jI0 = i0(str);
        Log.w("PasswordActivity", "checkUDiskLogs path = " + str + ", dataAvailSpace = " + jI0 + ", defaultAvailSpace = 2147483648");
        return jI0 < 2147483648L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g0() {
        int powerLevel = this.k0.getPowerLevel();
        int gearboxAutoModeLevel = this.l0.getGearboxAutoModeLevel();
        com.byd.engineermode.utils.i.c("PasswordActivity", " BEGIN_CHECK_USB_UPGRADE_STATUS checkUpgradeStatus mAutoGearbox = " + gearboxAutoModeLevel);
        if (this.n0 || (powerLevel == 2 && gearboxAutoModeLevel == 1)) {
            if (o0(this)) {
                return true;
            }
            this.q0.b(R.string.str_udisk_no_exists);
            return false;
        }
        if (powerLevel == 2) {
            com.byd.engineermode.utils.i.c("PasswordActivity", " BEGIN_CHECK_USB_UPGRADE_STATUS mAutoGearbox = " + gearboxAutoModeLevel);
            this.q0.b(R.string.str_check_park_status_msg);
        } else if (gearboxAutoModeLevel == 1) {
            com.byd.engineermode.utils.i.c("PasswordActivity", " BEGIN_CHECK_USB_UPGRADE_STATUS mPowerStatus = " + powerLevel);
            this.q0.b(R.string.str_check_power_on_status_msg);
        } else {
            com.byd.engineermode.utils.i.c("PasswordActivity", " BEGIN_CHECK_USB_UPGRADE_STATUS mPowerStatus = " + powerLevel + ", mAutoGearbox = " + gearboxAutoModeLevel);
            this.q0.b(R.string.str_check_power_park_status);
        }
        return false;
    }

    private void h0(File file) {
        if (file != null) {
            try {
                if (!file.isDirectory()) {
                    com.byd.engineermode.utils.a.a("delet file");
                    file.delete();
                    return;
                }
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        com.byd.engineermode.utils.a.a(file2.delete() + " : delet, fileson = " + file2.getName());
                    }
                }
                com.byd.engineermode.utils.a.a("delet dectory = " + file.getName());
                file.delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private long i0(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    private void j0(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    private void k0() {
        this.m0 = BYDAutoOtaDevice.getInstance(DiagnoseApplication.e());
        this.g0 = new i(this);
        OtaUpgradeManager otaUpgradeManager = OtaUpgradeManager.getInstance(DiagnoseApplication.e());
        this.j0 = otaUpgradeManager;
        otaUpgradeManager.registerListener(this);
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(DiagnoseApplication.e());
        this.l0 = bYDAutoGearboxDevice;
        bYDAutoGearboxDevice.registerListener((AbsBYDAutoGearboxListener) new b());
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(DiagnoseApplication.e());
        this.k0 = bYDAutoBodyworkDevice;
        bYDAutoBodyworkDevice.registerListener((AbsBYDAutoBodyworkListener) new c());
        if (this.d0 == null) {
            this.d0 = new ArrayList();
        }
        if (this.e0 == null) {
            this.e0 = new ArrayList();
        }
        if (this.f0 == null) {
            this.f0 = new ArrayList();
        }
        this.q0 = com.byd.engineermode.ui.dialog.a.a(this);
        this.r0 = com.byd.engineermode.ui.dialog.b.b(this);
        this.s0 = com.byd.engineermode.ui.dialog.b.b(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        registerReceiver(this.K0, intentFilter);
        try {
            d.a.a.a.a aVarH = d.a.a.a.a.h();
            if (aVarH != null) {
                this.P = aVarH.c();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        if (this.P != null) {
            this.N.setText(getString(R.string.imei) + this.P);
        }
        this.B0 = BYDAutoTBoxDevice.getInstance(DiagnoseApplication.e());
        this.F.setVisibility(0);
        this.N.setVisibility(0);
        this.E.setVisibility(0);
        this.H0 = getString(R.string.business_name);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.logtools", "com.byd.logtools.auth.AuthService"));
        bindService(intent, new d(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m0() {
        boolean zEquals = Environment.getExternalStorageState().equals("mounted");
        boolean zO0 = o0(this);
        Log.i("PasswordActivity", "isFileSystemAvailable mediaMounted = " + zEquals + "  isUDiskMount = " + zO0);
        if (!zO0) {
            if (!zEquals) {
                return false;
            }
            C = D;
            return false;
        }
        C = z + "/TBoxLogs";
        return true;
    }

    private boolean n0(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return motionEvent.getX() <= ((float) i2) || motionEvent.getX() >= ((float) (view.getWidth() + i2)) || motionEvent.getY() <= ((float) i3) || motionEvent.getY() >= ((float) (view.getHeight() + i3));
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void G(int i2) {
        com.byd.engineermode.utils.i.c("PasswordActivity", " beginUpgradeOnClickListener dialogType = " + i2);
        if (i2 == 1 || i2 == 2) {
            this.g0.sendEmptyMessage(1);
        } else if (i2 == 3) {
            this.g0.sendEmptyMessage(7);
        } else if (i2 == 5) {
            this.g0.sendEmptyMessage(12);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (n0(currentFocus, motionEvent)) {
                j0(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void l0() {
        this.H = (TextView) findViewById(R.id.tbox_upgrade);
        this.I = (TextView) findViewById(R.id.ivi_upgrade);
        this.J = (TextView) findViewById(R.id.export_tbox_upgrade_log);
        this.K = (TextView) findViewById(R.id.clear_tbox_log);
        this.L = (TextView) findViewById(R.id.tbox_reboot);
        this.M = (TextView) findViewById(R.id.desay_engmode);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        findViewById(R.id.tp_or_screen_fw_upgrade).setOnClickListener(this);
        this.E = (EditText) findViewById(R.id.et_password);
        this.F = (LinearLayout) findViewById(R.id.ll_password);
        this.G = (ScrollView) findViewById(R.id.sv_secretview);
        this.N = (TextView) findViewById(R.id.imei_tv);
        Button button = (Button) findViewById(R.id.ok_btn);
        this.O = button;
        button.setOnClickListener(this);
        this.E.addTextChangedListener(new a());
        if (com.byd.engineermode.utils.e.d().a()) {
            this.M.setVisibility(0);
        } else {
            this.M.setVisibility(8);
        }
    }

    public boolean o0(Context context) {
        try {
            List<VolumeInfo> volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            boolean z2 = SystemProperties.getBoolean("sys.byd.isUSBExist", false);
            com.byd.engineermode.utils.a.a("isUDiskMount isUSBExist = " + z2);
            if (volumes != null && z2) {
                com.byd.engineermode.utils.a.a("volumes size = " + volumes.size());
                for (VolumeInfo volumeInfo : volumes) {
                    try {
                        com.byd.engineermode.utils.a.a("addDisk volumeInfo = " + volumeInfo);
                        if (A.equals(volumeInfo.getDeviceType())) {
                            z = volumeInfo.path;
                            com.byd.engineermode.utils.a.a("USBOTG mUDiskFilePath = " + z);
                            if (z != null) {
                                return true;
                            }
                            com.byd.engineermode.utils.a.c("USBOTG mUDiskFilePath is null ");
                            return false;
                        }
                        if (B.equals(volumeInfo.getDeviceType())) {
                            com.byd.engineermode.utils.a.a("SDCARD mUDiskFilePath ");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            com.byd.engineermode.utils.a.d("initVolume failed", th2);
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        String strSubstring;
        switch (view.getId()) {
            case R.id.clear_tbox_log /* 2131296437 */:
                com.byd.engineermode.utils.i.c("PasswordActivity", " export_tbox_upgrade_log exportTBoxLogStatus = " + this.A0);
                int iClearTboxLog = this.B0.clearTboxLog();
                com.byd.engineermode.utils.i.c("PasswordActivity", " clearTboxLogType " + iClearTboxLog);
                if (iClearTboxLog == -1) {
                    this.q0.b(R.string.str_command_sending_error_msg);
                } else if (iClearTboxLog == 0) {
                    this.q0.b(R.string.str_unable_to_delete_Tbox_logs_msg);
                } else if (iClearTboxLog == 1) {
                    this.q0.b(R.string.str_can_delete_tbox_logs_msg);
                }
                break;
            case R.id.desay_engmode /* 2131296462 */:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.desay.engmode", "com.desay.engmode.ui.MainActivity"));
                startActivity(intent);
                break;
            case R.id.export_tbox_upgrade_log /* 2131296496 */:
                com.byd.engineermode.utils.i.c("PasswordActivity", " export_tbox_upgrade_log exportTBoxLogStatus = " + this.A0);
                if (!this.A0) {
                    this.A0 = true;
                    this.g0.sendEmptyMessage(8);
                    new Thread(new g()).start();
                } else {
                    com.byd.engineermode.ui.dialog.a.a(this).b(R.string.str_export_tbox_upgrade_log_content);
                }
                break;
            case R.id.ivi_upgrade /* 2131296537 */:
                com.byd.engineermode.ui.dialog.b.b(this).f(getString(R.string.str_udisk_upgrade_ivi), getString(R.string.str_udisk_upgrade_ivi_msg), 3);
                new Thread(new f()).start();
                break;
            case R.id.ok_btn /* 2131296605 */:
                String upperCase = this.E.getText().toString().trim().toUpperCase();
                if (upperCase != null && upperCase.isEmpty()) {
                    com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
                } else if (upperCase.length() != 6) {
                    com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
                } else {
                    if (TextUtils.isEmpty(this.P) || this.P.length() < 6) {
                        strSubstring = "112233";
                    } else {
                        String str = this.P;
                        strSubstring = str.substring(str.length() - 6, this.P.length());
                    }
                    boolean zJ = c.a.a.a.k().j(strSubstring, upperCase);
                    com.byd.engineermode.utils.a.b("PasswordActivity", "onTextChanged check =" + zJ + ",imei =" + strSubstring);
                    if (!zJ) {
                        com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
                    } else {
                        this.E.setText("");
                        this.F.setVisibility(8);
                        this.G.setVisibility(0);
                    }
                }
                break;
            case R.id.tbox_reboot /* 2131296708 */:
                int restartTBox = BYDAutoTestDevice.getInstance(getApplicationContext()).setRestartTBox(1);
                if (restartTBox != 0) {
                    com.byd.engineermode.utils.i.b("PasswordActivity", " TBox restart failed , result = " + restartTBox);
                    this.q0.b(R.string.str_reboot_fail_msg);
                } else {
                    this.q0.b(R.string.str_reboot_success_msg);
                }
                break;
            case R.id.tbox_upgrade /* 2131296709 */:
                com.byd.engineermode.ui.dialog.b.b(this).f(getString(R.string.str_udisk_upgrade_tbox), getString(R.string.str_udisk_upgrade_tbox_msg), 1);
                new Thread(new e()).start();
                break;
            case R.id.tp_or_screen_fw_upgrade /* 2131296761 */:
                com.byd.engineermode.ui.dialog.b.b(this).f(getString(R.string.str_usb_upgrade_tp_or_screen_fw), getString(R.string.str_usb_upgrade_tp_or_screen_fw_msg), 5);
                break;
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_passwor);
        l0();
        k0();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        com.byd.engineermode.utils.a.i("onDestroy i = ");
        this.j0.unregisterCallback(this);
        unregisterReceiver(this.K0);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onDownloadBackupFail(int i2) {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onDownloadBackupProgress(int i2) {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onDownloadBackupSuccess() {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onNotify(Bundle bundle) {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTBOXConnectState(int i2) {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxExportLogProgress(int i2) {
        com.byd.engineermode.utils.a.i("onTboxExportLogProgress i = " + i2);
        this.s0.c(R.string.export_completed);
        this.s0.d(i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxExportLogResult(int i2) {
        com.byd.engineermode.utils.a.i("onTboxExportLogResult i = " + i2);
        if (i2 == 0) {
            this.g0.sendEmptyMessage(11);
        } else {
            this.g0.sendEmptyMessage(10);
        }
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxUpdateProgress(int i2) {
        com.byd.engineermode.utils.a.i("onTboxUpdateProgress i = " + i2);
        this.r0.c(R.string.upgrade_Completed);
        this.r0.d(i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxUpdateResult(int i2) {
        com.byd.engineermode.utils.i.c("PasswordActivity", " onTboxUpdateResult i = " + i2);
        if (i2 == 0) {
            this.g0.sendEmptyMessage(5);
            return;
        }
        Message message = new Message();
        message.what = 4;
        message.obj = Integer.valueOf(i2);
        this.g0.sendMessage(message);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onUpdateFail(int i2) {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onUpdateProgress(int i2) {
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onUpdateSuccess() {
    }
}
