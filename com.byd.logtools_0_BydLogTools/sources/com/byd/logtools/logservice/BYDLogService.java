package com.byd.logtools.logservice;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import android.util.Log;
import com.byd.logtools.R;
import com.byd.logtools.application.BydLogApplication;
import com.byd.logtools.broadcast.BydLogReceiver;
import com.byd.logtools.d.g;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BYDLogService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3174b = "/data/data/com.byd.logtools/upLogs/";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3175c = "/data/data/com.byd.logtools/logs/";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f3176d = "/data/data/com.byd.logtools/HistoryTempLogs/";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @SuppressLint({"SdCardPath"})
    private static String f3177e = "/data/data/com.byd.logtools/tempLogs/";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f3178f = "/mnt/media_rw/u_disk";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static String f3179g = "usbotg";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f3180h = "sdcard";
    private static int i;
    private static String j;
    private static HashMap<String, HashMap<String, HashMap<Integer, Integer>>> k = new HashMap<>();
    public static boolean l = false;
    public static boolean m = false;
    private static final JSONArray n = new JSONArray();
    private static boolean o = false;
    private static boolean p = false;
    private static boolean q = false;
    private static boolean r = false;
    private com.byd.logtools.c.a A;
    public long B;
    com.byd.logtools.logservice.a E0;
    private String q0;
    private List<File> s;
    public List<File> t;
    private ConnectivityManager u;
    private BroadcastReceiver v;
    private IntentFilter w;
    private q x;
    private r y;
    private com.byd.logtools.c.b z;
    private final n C = new n(Looper.getMainLooper());
    private s D = null;
    private n E = null;
    private final p F = new p();
    private final com.byd.logtools.tools.c G = new com.byd.logtools.tools.c(this);
    private final com.byd.logtools.tools.h H = new com.byd.logtools.tools.h(this);
    private final int I = 100;
    private final int J = 24;
    private final long K = 3600000;
    private final long L = 300000;
    private boolean M = true;
    private boolean N = true;
    private boolean O = true;
    private final BroadcastReceiver P = new l(this, null);
    private final int Q = 1;
    private final int R = 2;
    private int S = 30;
    private final int T = 100;
    public String U = "saveUploadLogsTime";
    public String V = "saveUploadCrashInfoTime";
    private final String W = "crash";
    private String X = "crash";
    private boolean Y = true;
    private boolean Z = true;
    public int a0 = 24;
    public boolean b0 = false;
    public boolean c0 = false;
    public boolean d0 = false;
    private boolean e0 = false;
    private boolean f0 = false;
    private boolean g0 = true;
    private boolean h0 = true;
    private boolean i0 = false;
    private boolean j0 = false;
    private boolean k0 = false;
    public int l0 = 0;
    private String m0 = "/data/data/com.byd.logtools/crashlogs/";
    private String n0 = "/data/system/dropbox";
    private String o0 = "/log";
    private final int p0 = 15;
    private String r0 = null;
    private String s0 = "/data/tombstones/";
    private String t0 = "/data/vendor/nfs/nfs_ota/";
    private String u0 = "/data/vendor/nfs/nfs_ota/update/result/";
    private String v0 = "/vr_log";
    private String w0 = "/data/vendor/nfs/nfs_ota/eculog/";
    private String x0 = "/data/misc/radio";
    private String y0 = "/data/vendor/nfs/nfs_ota/";
    private String z0 = "/sdcard/blockcanary/";
    private String A0 = "/data/vendor/nfs/nfs_log/qnx_dump/ethlog";
    Comparator<File> B0 = new a();
    Comparator<File> C0 = new b();
    Comparator<File> D0 = new c();

    class a implements Comparator<File> {
        a() {
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

    class b implements Comparator<File> {
        b() {
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

    class c implements Comparator<File> {
        c() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            if (BYDLogService.this.X(file.getName()) > BYDLogService.this.X(file2.getName())) {
                return 1;
            }
            return BYDLogService.this.X(file.getName()) == BYDLogService.this.X(file2.getName()) ? 0 : -1;
        }
    }

    class d implements FileFilter {
        d() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile() && file.getPath().endsWith(".gcore.gz");
        }
    }

    class e implements FileFilter {
        e() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3186b;

        f(String str) {
            this.f3186b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.byd.logtools.tools.a.a().c(this.f3186b);
        }
    }

    class g implements FileFilter {
        g() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return !file.isDirectory();
        }
    }

    class h implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3189b;

        h(String str) {
            this.f3189b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            BYDLogService.this.k0(this.f3189b);
            BYDLogService.this.u0();
            BYDLogService.i--;
            Log.d("BYDLogService", "crashLogThreadNum - 1 =  " + BYDLogService.i);
        }
    }

    public class i extends Thread {
        public i() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public synchronized void run() {
            BydLogHelper.logD("BYDLogService clear all log");
            boolean unused = BYDLogService.p = true;
            BYDLogService.this.f0();
            boolean unused2 = BYDLogService.p = false;
        }
    }

    public class j extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3192b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3193c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f3194d;

        public j(String str, String str2, String str3) {
            this.f3193c = str;
            this.f3192b = str2;
            this.f3194d = str3;
        }

        private void a(int i) {
            String parent = new File(this.f3193c).getParent();
            Bundle bundle = new Bundle();
            bundle.putString("module", this.f3192b);
            bundle.putString("type", this.f3194d);
            bundle.putString("accStatus", "NONEED");
            if (SystemProperties.get("persist.sys.onlyTrace.enable", "0").equals("1")) {
                bundle.putString("filePath", this.f3193c);
            } else {
                bundle.putString("filePath", parent);
            }
            bundle.putInt("hash", i);
            BYDLogService.this.E.sendMessage(BYDLogService.this.E.obtainMessage(4, bundle));
        }

        private boolean b(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("/data/data/com.byd.logtools/crashLog/");
            sb.append(str);
            return new File(sb.toString()).exists();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String str = this.f3193c;
            int iHashCode = str != null ? str.hashCode() : 0;
            Log.d("BYDLogService", "moudle name = " + this.f3192b + " crashType = " + this.f3194d + " hashcode = ");
            synchronized (BYDLogService.k) {
                if (BYDLogService.k.isEmpty()) {
                    HashMap map = new HashMap();
                    map.put(Integer.valueOf(iHashCode), 0);
                    HashMap map2 = new HashMap();
                    map2.put(this.f3194d, map);
                    BYDLogService.k.put(this.f3192b, map2);
                    a(iHashCode);
                    String strB = BYDLogService.this.G.b(BYDLogService.k);
                    Log.d("BYDLogService", "CrashHashHandle mCrashModuleDataList is Empty json = " + strB);
                    BYDLogService.this.K0(strB);
                    return;
                }
                if (BYDLogService.k.containsKey(this.f3192b)) {
                    HashMap map3 = (HashMap) BYDLogService.k.get(this.f3192b);
                    if (map3.containsKey(this.f3194d)) {
                        HashMap map4 = (HashMap) map3.get(this.f3194d);
                        if (map4.containsKey(Integer.valueOf(iHashCode))) {
                            if (((Integer) map4.get(Integer.valueOf(iHashCode))).intValue() != 0 && iHashCode != 0) {
                                Log.d("BYDLogService", "CrashHashHandle has the same crash hashcode,do nothing");
                            }
                            if (!b(this.f3192b + "_" + this.f3194d + "_" + iHashCode + ".zip")) {
                                a(iHashCode);
                            }
                        } else {
                            map4.put(Integer.valueOf(iHashCode), 0);
                            a(iHashCode);
                            String strB2 = BYDLogService.this.G.b(BYDLogService.k);
                            Log.d("BYDLogService", "CrashHashHandle is new hashcode crashmodule json = " + strB2);
                            BYDLogService.this.K0(strB2);
                        }
                    } else {
                        HashMap map5 = new HashMap();
                        map5.put(Integer.valueOf(iHashCode), 0);
                        map3.put(this.f3194d, map5);
                        a(iHashCode);
                        String strB3 = BYDLogService.this.G.b(BYDLogService.k);
                        Log.d("BYDLogService", "CrashHashHandle is new crashType crashmodule json = " + strB3);
                        BYDLogService.this.K0(strB3);
                    }
                } else {
                    HashMap map6 = new HashMap();
                    map6.put(Integer.valueOf(iHashCode), 0);
                    HashMap map7 = new HashMap();
                    map7.put(this.f3194d, map6);
                    BYDLogService.k.put(this.f3192b, map7);
                    a(iHashCode);
                    String strB4 = BYDLogService.this.G.b(BYDLogService.k);
                    Log.d("BYDLogService", "CrashHashHandle is new crashmodule json = " + strB4);
                    BYDLogService.this.K0(strB4);
                }
            }
        }
    }

    public class k extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<String> f3196b;

        public k(ArrayList<String> arrayList) {
            this.f3196b = arrayList;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public synchronized void run() {
            Log.d("BYDLogService", "LogService running for History log to upload");
            BYDLogService.this.Q0(this.f3196b);
        }
    }

    private class l extends BroadcastReceiver {
        /* synthetic */ l(BYDLogService bYDLogService, a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String strB = com.byd.logtools.tools.e.b("persist.service.host.name");
            if ("android.net.conn.CONNECTIVITY_CHANGE_SPEC".equals(intent.getAction())) {
                String str = SystemProperties.get("net.lte.apn1.real_state");
                Log.d("BYDLogService", "get apn1 net state is " + str);
                if (!"connect".equals(str)) {
                    BYDLogService.this.M = false;
                    return;
                }
                BYDLogService.this.M = true;
                if (strB == null || strB.equals("116.63.88.150")) {
                    return;
                }
                BYDLogService.this.L0();
                BYDLogService.this.M0();
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                BYDLogService.this.u = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkInfo activeNetworkInfo = BYDLogService.this.u.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    Log.d("BYDLogService", "networkInfo == null");
                    BYDLogService.this.O = false;
                    return;
                }
                if (activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    Log.d("BYDLogService", "userNet state is connected");
                    BYDLogService.this.O = true;
                    if (strB != null && strB.equals("116.63.88.150")) {
                        BYDLogService.this.L0();
                        BYDLogService.this.M0();
                    }
                    if (!"user".equals(BYDLogService.this.G.g())) {
                        Log.d("BYDLogService", "userdebug version so not judge the car state");
                        return;
                    } else {
                        Log.d("BYDLogService", "user version so judge the car state");
                        BYDLogService.this.E.sendEmptyMessage(16);
                        return;
                    }
                }
                return;
            }
            if (!"android.net.conn.CONNECTIVITY_CHANGE_FUNCTION".equals(intent.getAction())) {
                if ("action.upload.Log".equals(intent.getAction())) {
                    Log.d("BYDLogService", "get alarm action to upload log on frequence");
                    BYDLogService.this.E.sendEmptyMessage(12);
                    return;
                } else {
                    if ("action.upload.crash.log.info".equals(intent.getAction())) {
                        Log.d("BYDLogService", "get alarm action to upload crash info");
                        BYDLogService.this.E.sendEmptyMessage(6);
                        return;
                    }
                    return;
                }
            }
            String str2 = SystemProperties.get(BYDAutoSysPropertiesDevice.APN_STATE, "disconnected");
            Log.d("BYDLogService", "get apn3State is " + str2);
            if (!"connect".equals(str2)) {
                BYDLogService.this.N = false;
                return;
            }
            BYDLogService.this.N = true;
            if (strB != null && strB.equals("116.63.88.150")) {
                BYDLogService.this.L0();
                BYDLogService.this.M0();
            }
            if (!"user".equals(BYDLogService.this.G.g())) {
                Log.d("BYDLogService", "userdebug version so not judge the car state");
            } else {
                Log.d("BYDLogService", "user version so judge the car state");
                BYDLogService.this.E.sendEmptyMessage(16);
            }
        }

        private l() {
        }
    }

    public class m extends Thread {
        public m() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public synchronized void run() {
            Log.d("BYDLogService", "LogService running for onekey to save and upload log");
            BYDLogService.this.N0();
        }
    }

    public class n extends Handler {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    boolean unused = BYDLogService.q = true;
                    boolean zS0 = BYDLogService.this.s0(1002, 2);
                    Intent intent = new Intent();
                    intent.putExtra("USB_MOUNT_COPY_ALL_LOG_UPDATE_TOAST", zS0);
                    BYDLogService.this.E.sendMessage(BYDLogService.this.E.obtainMessage(8, intent));
                    boolean unused2 = BYDLogService.q = false;
                    boolean unused3 = BYDLogService.o = false;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    BYDLogService.this.G("history");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        public n(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00da A[Catch: all -> 0x00de, TRY_LEAVE, TryCatch #8 {all -> 0x00de, blocks: (B:36:0x00d4, B:38:0x00da), top: B:63:0x00d4 }] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r10v7 */
        /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.io.FileReader] */
        /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileReader] */
        /* JADX WARN: Type inference failed for: r1v5 */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r1v9, types: [java.io.FileReader, java.io.Reader] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void a(java.lang.String r10) {
            /*
                Method dump skipped, instruction units count: 256
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.logtools.logservice.BYDLogService.n.a(java.lang.String):void");
        }

        private void b(Intent intent) {
            synchronized (Intent.class) {
                String stringExtra = intent.getStringExtra("crashType");
                String strN = BYDLogService.this.G.n(intent.getStringExtra("crashPath"));
                Log.i("BYDLogService", "collectCrashInfoTODB name = " + strN + ", type = " + stringExtra);
                if (strN != null && stringExtra != null) {
                    if (BYDLogService.this.Y) {
                        BYDLogService.this.e0(intent.getStringExtra("crashPath"), strN, stringExtra);
                    }
                    if (BYDLogService.this.Z || com.byd.logtools.d.a.b().a("UploadCrashInfo", false)) {
                        BYDLogService.this.G.o(stringExtra, strN);
                        Log.i("BYDLogService", "CrashInfoUploadFreq = " + BYDLogService.this.a0);
                        if (BYDLogService.this.a0 == 0) {
                            Log.d("BYDLogService", "upload crashinfo immediately!");
                            BYDLogService.this.P0();
                        }
                    }
                }
            }
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d("BYDLogService", "RUNNING_TO_SAVE_ALL_LOG");
                    Log.d("BYDLogService", "BYDLogService save all log");
                    boolean unused = BYDLogService.o = true;
                    BYDLogService.this.g0(1002);
                    boolean unused2 = BYDLogService.o = false;
                    break;
                case 2:
                    Log.d("BYDLogService", "DropBox_CRASH_LOG_SAVE");
                    if (BYDLogService.this.g0 || com.byd.logtools.d.a.b().a("save_crashlog_switch", false)) {
                        BYDLogService.this.v0((Intent) message.obj);
                    }
                    break;
                case 3:
                    Log.d("BYDLogService", "INSERT_CRASH_TO_DB");
                    if (BYDLogService.this.Y || BYDLogService.this.Z || com.byd.logtools.d.a.b().a("UploadCrashInfo", false)) {
                        b((Intent) message.obj);
                    }
                    break;
                case 4:
                    Log.d("BYDLogService", "COMPRESS_CRASH_LOG");
                    BYDLogService.this.L((Bundle) message.obj);
                    break;
                case 5:
                    Log.d("BYDLogService", "REBOOT_CHECK_CRASH");
                    if (BYDLogService.this.Y || BYDLogService.this.Z || com.byd.logtools.d.a.b().a("UploadCrashInfo", false)) {
                        a("/sdcard/crashlogs");
                    } else {
                        Log.d("BYDLogService", "apr and crashInfo switch closed, so delete data/logs/crashinfo");
                        File file = new File("/sdcard/crashlogs");
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    break;
                case 6:
                    BydLogHelper.logD("UPLOAD_CRASH_INFO");
                    if (!BYDLogService.this.H()) {
                        BydLogHelper.logD("!canUpload");
                        BYDLogService.this.E.removeMessages(6);
                        BYDLogService.this.E.sendEmptyMessageDelayed(6, 300000L);
                    } else {
                        BydLogHelper.logD("sumRestTimeToSetAlarm uploadCrashInfo = " + new Date());
                        BYDLogService.this.P0();
                    }
                    break;
                case 7:
                    Log.d("BYDLogService", "USB_MOUNT_COPY_ALL_LOG");
                    if (!BYDLogService.o) {
                        boolean zL0 = BYDLogService.this.l0(2);
                        Log.d("BYDLogService", "isUDisk = " + zL0 + ", mTargetPath = " + BYDLogService.j + ", mUSBCopyLogTryTimes = " + BYDLogService.this.S);
                        if (!zL0 && BYDLogService.j == null && BYDLogService.this.S > 0) {
                            BYDLogService.this.S--;
                            BYDLogService.this.E.sendEmptyMessageDelayed(7, 1000L);
                        } else {
                            if (BYDLogService.j != null && BYDLogService.j.contains("/data/data/com.byd.logtools/logs/") && !BYDLogService.q) {
                                Log.d("BYDLogService", "isUDisk =  " + zL0 + ", mTargetPath = " + BYDLogService.j + ", uDiskPath = " + BYDLogService.f3178f);
                                if (new File(BYDLogService.f3178f, "BYD@AutoCopyLog!#%@$^").exists()) {
                                    boolean unused3 = BYDLogService.o = true;
                                    Log.d("BYDLogService", "checkAutoCopyFile: file.exists() = true");
                                    BYDLogService.this.o0("U盘识别到，正在拷贝日志！");
                                    new Thread(new a()).start();
                                } else {
                                    Log.d("BYDLogService", "checkAutoCopyFile: file.exists() = false");
                                }
                            }
                            BYDLogService.this.S = 30;
                        }
                    } else {
                        Log.e("BYDLogService", "USB_MOUNT_COPY_ALL_LOG saveLogFlags is true");
                    }
                    break;
                case 8:
                    Log.d("BYDLogService", "USB_MOUNT_COPY_ALL_LOG_UPDATE_TOAST");
                    if (!((Intent) message.obj).getBooleanExtra("USB_MOUNT_COPY_ALL_LOG_UPDATE_TOAST", false)) {
                        BYDLogService bYDLogService = BYDLogService.this;
                        BYDLogService.this.o0(bYDLogService.n0(bYDLogService) ? "U盘已移除，拷贝失败! " : "U盘拷贝失败!");
                    } else {
                        BYDLogService.this.o0("U盘拷贝中完成！");
                    }
                    break;
                case 9:
                    BYDLogService bYDLogService2 = BYDLogService.this;
                    boolean zN0 = bYDLogService2.n0(bYDLogService2);
                    Log.d("BYDLogService", "USB_UNMOUNT_STOP_COPY_LOG mTargetPath = " + BYDLogService.j + ", saveLogFlags = " + BYDLogService.o + ", isUDiskMount = " + zN0);
                    if (BYDLogService.j != null && BYDLogService.j.contains("/storage") && !zN0) {
                        com.byd.logtools.tools.g.f3243a = false;
                        com.byd.logtools.d.e.f3166e = false;
                        if (BYDLogService.o) {
                            BYDLogService bYDLogService3 = BYDLogService.this;
                            bYDLogService3.o0(bYDLogService3.getString(R.string.str_udisk_remove));
                        }
                        break;
                    }
                    break;
                case 10:
                    Log.d("BYDLogService", "CMD_SAVE_ALL_LOG");
                    BYDLogService.this.E.sendMessage(BYDLogService.this.E.obtainMessage(10));
                    break;
                case 11:
                    Log.d("BYDLogService", "VR_SAVE_LOG");
                    Intent intent = (Intent) message.obj;
                    String stringExtra = intent.getStringExtra("paths");
                    intent.getBooleanExtra("needAp", true);
                    boolean booleanExtra = intent.getBooleanExtra("needResult", true);
                    Log.i("BYDLogService", "CMD_SAVE_ALL_LOG, vrPaths = " + stringExtra + ", needAp = false, needResult = " + booleanExtra);
                    boolean unused4 = BYDLogService.r = true;
                    BYDLogService.this.h0(stringExtra, false, booleanExtra);
                    boolean unused5 = BYDLogService.r = false;
                    break;
                case 12:
                    Log.d("BYDLogService", "AUTO_SAVE_LOG_ANYWAY");
                    new Thread(new b()).start();
                    break;
            }
        }
    }

    public class o extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3203b;

        public o(int i) {
            this.f3203b = 1002;
            this.f3203b = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public synchronized void run() {
            BydLogHelper.logD("BYDLogService save all log");
            boolean unused = BYDLogService.o = true;
            BYDLogService.this.g0(this.f3203b);
            boolean unused2 = BYDLogService.o = false;
        }
    }

    public class p extends Binder {
        public p() {
        }

        public BYDLogService a() {
            return BYDLogService.this;
        }
    }

    public interface q {
        void a(boolean z, String str);

        void d(List<File> list);
    }

    public interface r {
        void e(String str);
    }

    public static class s extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Object f3206b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Looper f3207c;

        public s(String str) {
            Object obj = new Object();
            this.f3206b = obj;
            this.f3207c = null;
            new Thread(null, this, str).start();
            synchronized (obj) {
                while (this.f3207c == null) {
                    try {
                        this.f3206b.wait();
                    } catch (InterruptedException unused) {
                        Log.d("BYDLogService", " create WorkThread failed");
                    }
                }
            }
        }

        public Looper a() {
            return this.f3207c;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            synchronized (this.f3206b) {
                Looper.prepare();
                this.f3207c = Looper.myLooper();
                this.f3206b.notifyAll();
            }
            Looper.loop();
        }
    }

    static {
        System.loadLibrary("logtools");
    }

    private long E0(String str, int i2) {
        long jC = com.byd.logtools.d.a.b().c(str, 0L);
        long jV = V();
        long j2 = (jV - jC) / 3600000;
        Log.d("BYDLogService", "timeDifference " + str + " = " + jC + " cloudTime = " + jV);
        StringBuilder sb = new StringBuilder();
        sb.append("timeDifference  = ");
        sb.append(j2);
        Log.d("BYDLogService", sb.toString());
        return j2;
    }

    private void F(File file, String str) {
        q qVar;
        List<File> list = "history".equals(str) ? this.t : this.s;
        Log.d("BYDLogService", "addLogFile");
        Log.d("BYDLogService", "logList.size() = " + list.size() + ", logCountMax = 100");
        StringBuilder sb = new StringBuilder();
        sb.append("addLogFile  file : ");
        sb.append(file.getName());
        Log.d("BYDLogService", sb.toString());
        list.add(file);
        if (!"history".equals(str) || (qVar = this.x) == null) {
            return;
        }
        qVar.d(this.t);
    }

    private static String F0(long j2) {
        return new SimpleDateFormat("yy/MM/dd HH:mm:ss").format(new Date(j2));
    }

    private boolean J(String str) {
        long jT = T(str);
        Log.i("BYDLogService", "checkSdcardLogs path = " + str + ", dataAvailSpace = " + jT + ", defaultAvailSpace = 1073741824");
        return jT > 1073741824;
    }

    private boolean M(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        BydLogHelper.logD(" copyCrashLog src path = " + file.getPath() + ",dest patch = " + this.r0 + "/" + file.getName());
        try {
            com.byd.logtools.d.f.c(file.getPath(), this.r0 + "/" + file.getName());
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void N() {
        if (this.D == null) {
            this.D = new s("allLogWork");
        }
        if (this.E == null) {
            this.E = new n(this.D.a());
        }
    }

    private boolean P(int i2, String str) {
        List<File> list;
        String str2;
        q qVar;
        if ("history".equals(str)) {
            list = this.t;
            str2 = f3175c;
        } else {
            list = this.s;
            str2 = f3174b;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            File file = list.get(0);
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(file == null ? "null" : file.getName());
            String string = sb.toString();
            Log.d("BYDLogService", "will deleteOldLog: " + string);
            File file2 = new File(string);
            if (file2.exists()) {
                boolean zDelete = file2.delete();
                File fileRemove = list.remove(0);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("deleteOldLog: ");
                sb2.append(fileRemove != null ? fileRemove.getName() : "null");
                sb2.append(", ");
                sb2.append(zDelete);
                Log.d("BYDLogService", sb2.toString());
                if ("history".equals(str) && (qVar = this.x) != null) {
                    qVar.d(this.t);
                }
            } else {
                Log.d("BYDLogService", file2 + " is not exist");
            }
        }
        return true;
    }

    private void Q(File file) {
        Log.d("BYDLogService", "deleteUploadLog  file :" + file.getName());
        if (new File(f3174b + file.getName()).delete()) {
            Log.d("BYDLogService", "deleteUploadLog  file delete success.");
            Log.d("BYDLogService", "deleteUploadLog  allLogList.indexOf(file) = " + this.s.indexOf(file));
            this.s.remove(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(ArrayList<String> arrayList) {
        Log.d("BYDLogService", "uploadHistoryLogs: " + arrayList.toString() + ", UploadHistoryLogFlag = " + m);
        if (m) {
            Log.d("BYDLogService", "uploadHistoryLogs is running so wait.");
            r rVar = this.y;
            if (rVar != null) {
                rVar.e("action.upload.all.log.running");
                return;
            }
            return;
        }
        m = true;
        r rVar2 = this.y;
        if (rVar2 != null) {
            rVar2.e("action.upload.all.log.running");
        }
        boolean zE = this.H.e(this.G.a(null), arrayList);
        q qVar = this.x;
        if (qVar != null) {
            qVar.a(zE, "msg_upload_result");
        }
        if (zE) {
            Log.d("BYDLogService", "uploadSpecificFile: success");
            S0(this.U);
            O(arrayList);
        }
        m = false;
        Log.d("BYDLogService", "uploadSpecificFile failed, do not delete zip file and database");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.ArrayList<java.lang.String> R(java.lang.String r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r6.o0
            r1.<init>(r2)
            boolean r2 = r1.exists()
            java.lang.String r3 = ""
            r4 = 1
            if (r2 == 0) goto L2f
            java.util.ArrayList r1 = r6.W(r1)
            int r2 = r1.size()
            if (r2 <= 0) goto L2f
            int r2 = r1.size()
            int r2 = r2 - r4
            java.lang.Object r1 = r1.get(r2)
            java.io.File r1 = (java.io.File) r1
            java.lang.String r1 = r1.getPath()
            goto L30
        L2f:
            r1 = r3
        L30:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "getAllNewestLogPathList android log  androidUplogsPath = "
            r2.append(r5)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r2)
            java.util.ArrayList r1 = r6.Y(r1, r4, r7)
            r0.addAll(r1)
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "/data/vendor/nfs/nfs_log/qnx_log/"
            r1.<init>(r2)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L71
            java.util.ArrayList r1 = r6.W(r1)
            int r2 = r1.size()
            if (r2 <= 0) goto L71
            int r2 = r1.size()
            int r2 = r2 - r4
            java.lang.Object r1 = r1.get(r2)
            java.io.File r1 = (java.io.File) r1
            java.lang.String r3 = r1.getPath()
        L71:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "getAllNewestLogPathList qnxUplogsPath = "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r1)
            java.util.ArrayList r7 = r6.Y(r3, r4, r7)
            r0.addAll(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.logtools.logservice.BYDLogService.R(java.lang.String):java.util.ArrayList");
    }

    private long T(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static long V() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = jCurrentTimeMillis - Long.parseLong(SystemProperties.get("persist.sys.timeoff", "0"));
        Log.d("BYDLogService", "getCloudTime nowRtc    = " + jCurrentTimeMillis + ", = " + F0(jCurrentTimeMillis) + "----cloudTime = " + j2 + ", = " + F0(j2));
        return j2;
    }

    private ArrayList<File> W(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] fileArrListFiles = file.listFiles(new e());
        if (fileArrListFiles == null) {
            Log.e("BYDLogService", "getComparatorFileList: There is no log at " + this.o0);
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().contains("cycle_")) {
                Log.e("BYDLogService", "getComparatorFileList: add file to list =  " + file2.getName());
                arrayList.add(file2);
            }
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, this.D0);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int X(String str) {
        if (str == null) {
            return 0;
        }
        try {
            String[] strArrSplit = str.split("_");
            if (strArrSplit.length == 2 && m0(strArrSplit[1])) {
                return Integer.parseInt(strArrSplit[1]);
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private ArrayList<String> Y(String str, boolean z, String str2) {
        File[] fileArrListFiles;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        File file = new File(str);
        if (file.exists() && (fileArrListFiles = file.listFiles(new g())) != null && fileArrListFiles.length >= 1) {
            BydLogHelper.logD("files.length = " + fileArrListFiles.length + ", firstPath =  " + z + ", autoSaveLogAnywayFlag = , persist.log.tag.BydHistoryLogPath = " + SystemProperties.get("persist.log.tag.BydHistoryLogPath", HttpUrl.FRAGMENT_ENCODE_SET));
            boolean z2 = z && "history".equals(str2) && str.contains("/data/vendor/nfs/nfs_log/qnx_log/");
            if (fileArrListFiles.length < 34) {
                if (z2) {
                    arrayList2.addAll(Arrays.asList(fileArrListFiles));
                    Collections.sort(arrayList2, this.C0);
                    boolean z3 = false;
                    for (int size = arrayList2.size() - 1; size >= 0; size--) {
                        String path = ((File) arrayList2.get(size)).getPath();
                        arrayList.add(path);
                        if ((path.endsWith("zip") || path.endsWith(".gz")) && !z3) {
                            BydLogHelper.logD("set filePath = " + path);
                            SystemProperties.set("persist.log.tag.BydHistoryLogPath", path);
                            z3 = true;
                        }
                    }
                } else {
                    for (File file2 : fileArrListFiles) {
                        arrayList.add(file2.getPath());
                    }
                }
                if (z) {
                    BydLogHelper.logD("getSecondPath getSecondPath(path) = " + a0(str));
                    arrayList.addAll(Y(a0(str), false, str2));
                }
            } else {
                arrayList2.addAll(Arrays.asList(fileArrListFiles));
                Collections.sort(arrayList2, this.C0);
                if (z2) {
                    String str3 = SystemProperties.get("persist.log.tag.BydHistoryLogPath", HttpUrl.FRAGMENT_ENCODE_SET);
                    boolean z4 = false;
                    for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                        String path2 = ((File) arrayList2.get(size2)).getPath();
                        arrayList.add(path2);
                        BydLogHelper.logD("add filePath = " + path2);
                        if ((!TextUtils.isEmpty(str3) && str3.equals(path2)) || arrayList2.size() - size2 >= 100) {
                            BydLogHelper.logD("filePath  break");
                            break;
                        }
                        if ((path2.endsWith("zip") || path2.endsWith(".gz")) && !z4) {
                            BydLogHelper.logD("set filePath = " + path2);
                            SystemProperties.set("persist.log.tag.BydHistoryLogPath", path2);
                            z4 = true;
                        }
                    }
                } else {
                    for (int size3 = arrayList2.size() - 1; size3 >= arrayList2.size() - 34; size3--) {
                        arrayList.add(((File) arrayList2.get(size3)).getPath());
                    }
                }
            }
        }
        return arrayList;
    }

    private String a0(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (TextUtils.isEmpty(str)) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (str.length() <= 2) {
            return str;
        }
        String strSubstring = str.substring(str.length() - 1);
        if (m0(strSubstring)) {
            int i2 = Integer.parseInt(strSubstring);
            if (i2 >= 1) {
                i2--;
            }
            sb.replace(str.length() - 1, str.length(), String.valueOf(i2));
        }
        return sb.toString();
    }

    private void j0() {
        SystemProperties.set("persist.log.tag.BydHistoryLogPath", HttpUrl.FRAGMENT_ENCODE_SET);
        File file = new File(this.o0);
        if (file.exists()) {
            ArrayList<File> arrayListW = W(file);
            if (arrayListW.size() > 0) {
                this.E.sendEmptyMessage(12);
                Log.d("BYDLogService", "FileChangeObserver androidLogList.get(androidLogList.size() -1 ) = " + arrayListW.get(arrayListW.size() - 1));
                com.byd.logtools.logservice.a aVar = new com.byd.logtools.logservice.a(arrayListW.get(arrayListW.size() + (-1)));
                this.E0 = aVar;
                aVar.startWatching();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k0(String str) {
        if (com.byd.logtools.d.c.c(this.m0)) {
            BydLogHelper.logE("mkDir crash root  path false, crashRootPath = " + this.m0);
            return;
        }
        String strB = com.byd.logtools.d.c.b(this.m0 + "currentcrashlog");
        int i2 = (TextUtils.isEmpty(strB) ? -1 : Integer.parseInt(strB)) + 1;
        if (i2 >= 15) {
            i2 = 0;
        }
        File file = new File(this.m0 + "/crashlog" + i2);
        if (file.exists() && file.canExecute() && file.isFile()) {
            com.byd.logtools.d.c.a(file);
        }
        if (com.byd.logtools.d.c.c(file.getPath())) {
            BydLogHelper.logE("mkDir crash log path false, currentCrashLogPath = " + this.r0);
            return;
        }
        this.r0 = file.getPath();
        BydLogHelper.logD(" currentCrashLogPath = " + this.r0 + ",crashRootPath = " + this.m0);
        StringBuilder sb = new StringBuilder();
        sb.append(this.m0);
        sb.append("currentcrashlog");
        com.byd.logtools.d.c.d(sb.toString(), String.valueOf(i2));
        M(str);
        String str2 = SystemProperties.get("persist.storage.index");
        BydLogHelper.logD("initUniqueIdentifier android log cycle number = " + str2);
        try {
            new File(this.r0 + "/android_log_cycle" + str2).createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l0(int i2) {
        boolean zEquals = Environment.getExternalStorageState().equals("mounted");
        boolean zN0 = n0(this);
        Log.i("BYDLogService", "mediaMounted = " + zEquals + "  isUDiskMount = " + zN0 + ", type = " + i2);
        if ((i2 & 2) != 0 && zN0) {
            j = f3178f + "/logs/All";
            return true;
        }
        if ((i2 & 1) == 0 || !zEquals) {
            f3178f = null;
            j = null;
            return false;
        }
        j = f3175c + "All";
        return true;
    }

    private boolean m0(String str) {
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (Character.isDigit(str.charAt(length)));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o0(String str) {
        this.C.post(new f(str));
    }

    private void p0() {
        IntentFilter intentFilter = new IntentFilter();
        this.w = intentFilter;
        intentFilter.addAction("android.intent.action.DROPBOX_ENTRY_ADDED");
        this.w.addAction("android.intent.action.CMD_SAVE_ALL_LOG");
        this.w.addAction("bydlogtool.action.savelog");
        BydLogReceiver bydLogReceiver = new BydLogReceiver();
        this.v = bydLogReceiver;
        registerReceiver(bydLogReceiver, this.w);
    }

    private boolean q0(int i2, String str) {
        String strSubstring;
        String str2;
        File file;
        File file2;
        Log.d("BYDLogService", "saveAlarmLogs");
        if (!l0(i2)) {
            return false;
        }
        File file3 = "history".equals(str) ? new File(f3175c) : new File(f3174b);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        String str3 = SystemProperties.get("persist.vendor.bosch.cfg.diag.vin", "00000000000123456");
        if (str3 == null || str3.length() < 6) {
            strSubstring = "123456";
        } else {
            strSubstring = str3.substring(str3.length() - 6);
            Log.i("BYDLogService", "saveAllLogsAndCompress vinCode = " + strSubstring);
        }
        if (str == null || "history".equals(str)) {
            File file4 = new File(f3176d);
            if (!file4.exists()) {
                boolean zMkdirs = file4.mkdirs();
                Log.e("BYDLogService", "saveAlarmLogs historyTempLogs mkdirs is " + zMkdirs);
                if (!zMkdirs) {
                    return false;
                }
            }
            String str4 = "All" + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + ".zip";
            str2 = "All" + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + "_000000_" + com.byd.logtools.tools.e.a("yyyyMMddHH").format(new Date()) + "_encryp" + strSubstring + ".zip";
            file = new File(f3176d + str4);
            file2 = new File(f3175c + str2);
        } else {
            String str5 = "oneKey_All" + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + ".zip";
            str2 = "oneKey_All" + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + "_000000_" + com.byd.logtools.tools.e.a("yyyyMMddHH").format(new Date()) + "_encryp" + strSubstring + ".zip";
            file = new File(f3174b + str5);
            file2 = new File(f3174b + str2);
        }
        ArrayList<String> arrayListR = R(str);
        if (arrayListR.size() < 1) {
            Log.e("BYDLogService", "saveAlarmLogs alarmLogPathList.size() =  0 ");
            return false;
        }
        try {
            Log.w("BYDLogService", "current begin compress zipFileTemp.getPath = " + file.getPath() + " alarmLogPathList.size() = " + arrayListR.size());
            com.byd.logtools.tools.g.c(null, file, arrayListR);
            Log.w("BYDLogService", "saveAlarmLogs aesEncryptFile alarmLogPath logs zipFileTemp.getName() = " + file.getName() + ", fileSize = " + file.length());
            com.byd.logtools.d.e.a(file.getPath(), file2.getPath(), strSubstring + strSubstring, com.byd.logtools.d.e.f3168g);
            Log.w("BYDLogService", "saveAllLogsAndCompress alarmLogPath zipFile.getName()= " + file2.getName() + ", zipFile.getPath()= " + file2.getPath() + ", zipFile fileSize = " + file2.length());
            com.byd.logtools.d.f.b(file);
            F(new File(str2), str);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void t0(String str, String str2) {
        g.a aVarA = com.byd.logtools.d.g.a(str, false, true);
        StringBuilder sb = new StringBuilder();
        File file = new File(this.r0 + "/" + str2 + ".txt");
        String str3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            sb.append("execute command: ");
            sb.append(str);
            sb.append(" , ");
            sb.append(str3);
            sb.append("\n\n\n");
            sb.append(aVarA.f3171b);
            printWriter.println(sb.toString());
            printWriter.println();
            printWriter.close();
            sb.delete(0, sb.length());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u0() {
        t0("cat /proc/meminfo", "catMeminfo");
        t0("cat /proc/*/status", "catProc");
        t0(" dumpsys cpuinfo", "dumpsysCpuinfo");
        t0("top -m 100 -n 1", "top");
        t0("ps -A", "ps");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v0(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("crashTag");
            String stringExtra2 = intent.getStringExtra("crashTime");
            if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.equals(this.q0)) {
                BydLogHelper.logD(" onReceive crash message again, crashTag= " + stringExtra + ", crashTime = " + stringExtra2 + ", crashTimeTemp = " + this.q0);
                return;
            }
            this.q0 = stringExtra2;
            File file = new File(this.n0);
            BydLogHelper.logD(" crashTag= " + stringExtra + ", crashTime = " + stringExtra2 + ", file.exists() = " + file.exists() + ", crashTimeTemp = " + this.q0);
            if (!file.exists() || stringExtra == null || stringExtra2 == null) {
                return;
            }
            File[] fileArrListFiles = file.listFiles();
            String path = null;
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                int length = fileArrListFiles.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    File file2 = fileArrListFiles[i2];
                    if (file2.getName().contains(stringExtra + "@" + stringExtra2)) {
                        path = file2.getPath();
                        BydLogHelper.logD(" crashPath= " + path + ", f getName= " + file2.getName());
                        break;
                    }
                    i2++;
                }
            }
            if (!J(this.n0)) {
                BydLogHelper.logE(" saveDropBoxCrashLogFile this no enough space to save crash logs");
                return;
            }
            if (path != null) {
                int i3 = i;
                if (i3 >= 10) {
                    Log.e("BYDLogService", "too much crash, crashLogThreadNum over 5 ");
                    return;
                }
                i = i3 + 1;
                Log.d("BYDLogService", "crashLogThreadNum + 1 =  " + i);
                new Thread(new h(path)).start();
            }
        }
    }

    private void x0(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles == null ? 0 : fileArrListFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                String absolutePath = fileArrListFiles[i2].getAbsolutePath();
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(new File(absolutePath)));
                sendBroadcast(intent);
            }
        }
    }

    public void A0(com.byd.logtools.c.b bVar) {
        this.z = bVar;
    }

    public void B0(q qVar) {
        this.x = qVar;
    }

    public void C0(r rVar) {
        this.y = rVar;
    }

    public void D0(String str) {
        String str2;
        int i2;
        BydLogHelper.logD("sumRestTimeToSetAlarm", str);
        if (str.equals(this.U)) {
            i2 = 24;
            this.E.removeMessages(111);
            str2 = "action.upload.Log";
        } else if (str.equals(this.V)) {
            i2 = this.a0;
            this.E.removeMessages(6);
            if (this.a0 == 0) {
                BydLogHelper.logD("sumRestTimeToSetAlarm  mCrashInfoUploadFreq == 0");
                this.E.sendEmptyMessageDelayed(6, 5000L);
                return;
            }
            str2 = "action.upload.crash.log.info";
        } else {
            str2 = null;
            i2 = -1;
        }
        if (HttpUrl.FRAGMENT_ENCODE_SET.equals(str2) || i2 <= 0) {
            return;
        }
        this.B = E0(str, i2);
        BydLogHelper.logD("sumRestTimeToSetAlarm timeDifference = " + this.B + " freq = " + i2);
        long j2 = this.B;
        if (j2 >= 0) {
            long j3 = i2;
            if (j2 < j3) {
                BydLogHelper.logD("sumRestTimeToSetAlarm 0 <= timeDifference < freq");
                y0((j3 - this.B) * 3600000, i2, str2);
                return;
            }
        }
        BydLogHelper.logD("sumRestTimeToSetAlarm timeDifference < 0 || timeDifference > freq , setAlarm = " + new Date());
        y0(300000L, i2, str2);
    }

    public boolean G(String str) {
        List<File> list;
        File file;
        boolean zMkdirs;
        i0();
        if ("history".equals(str)) {
            Log.d("BYDLogService", "autoSaveHistoryLogs");
            list = this.t;
            file = new File(f3175c);
        } else {
            Log.d("BYDLogService", "autoSaveAlarmLogs");
            list = this.s;
            file = new File(f3174b);
        }
        Log.d("BYDLogService", "autoSaveAlarmLogs logList.size() = " + list.size() + " LogCountMax = 100");
        if (file.exists()) {
            zMkdirs = true;
        } else {
            zMkdirs = file.mkdirs();
            Log.e("BYDLogService", "alarmLogFile mkdirs is " + zMkdirs);
        }
        if (!zMkdirs) {
            if ("history".equals(str)) {
                Log.e("BYDLogService", "create file failed, please check " + f3175c);
            } else {
                Log.e("BYDLogService", "create file failed, please check " + f3174b);
                o0("日志保存失败:路径[" + f3174b + "]创建失败！");
            }
            return false;
        }
        try {
            long availableBytes = ("history".equals(str) ? new StatFs(f3175c) : new StatFs(f3174b)).getAvailableBytes();
            Log.d("BYDLogService", "getAvailableBytes is  = " + availableBytes);
            if (availableBytes >= 52428800 && list.size() < 100) {
                boolean zQ0 = q0(1, str);
                Log.d("BYDLogService", "auto save log saveResult = " + zQ0);
                return zQ0;
            }
            if (P((list.size() - 100) + 1, str)) {
                Log.d("BYDLogService", "AUTO_SAVE_LOG delete the old log then save");
                return q0(1, str);
            }
            Log.d("BYDLogService", "AUTO_SAVE_LOG delete the old log fail");
            Log.d("BYDLogService", "autoSaveAlarmLogs end");
            return false;
        } catch (Exception e2) {
            if (!"history".equals(str)) {
                o0("日志保存失败:捕获异常[" + e2.getMessage() + "]");
            }
            e2.printStackTrace();
            return false;
        }
    }

    public void G0(com.byd.logtools.c.a aVar) {
        this.A = null;
    }

    public boolean H() {
        String strB = com.byd.logtools.tools.e.b("116.63.88.150");
        Log.d("BYDLogService", "apn1 state is " + this.M + ", apn2 state is " + this.O + ", apn3 state is " + this.N);
        if (strB == null || strB.equals("116.63.88.150")) {
            return this.O || this.N;
        }
        Log.d("BYDLogService", "host name is not default, so return apn1 state");
        return this.M;
    }

    public void H0(com.byd.logtools.c.b bVar) {
        this.z = null;
    }

    public void I(String str) {
        Log.d("BYDLogService", "cancelAlarm actionIntent = " + str);
        ((AlarmManager) getSystemService("alarm")).cancel(PendingIntent.getBroadcast(this, 0, new Intent(str), 134217728));
        if (str.equals("action.upload.Log")) {
            this.b0 = false;
        }
        if (str.equals("action.upload.crash.log.info")) {
            this.c0 = false;
        }
    }

    public void I0(q qVar) {
        this.x = null;
    }

    public void J0(r rVar) {
        this.y = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean K() {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.logtools.logservice.BYDLogService.K():boolean");
    }

    public void K0(String str) {
        this.G.q("/data/data/com.byd.logtools/", "crashRecord", str);
    }

    public void L(Bundle bundle) {
        String str;
        String strN;
        String strK = this.G.k();
        Log.d("BYDLogService", "compressCrashLogFile getInternalSwVersion:" + strK);
        Matcher matcher = Pattern.compile("S([a-zA-Z]|[0-9]){4}_[0-9]*").matcher(strK);
        String strGroup = matcher.find() ? matcher.group(0) : null;
        if (bundle != null) {
            String string = bundle.getString("type");
            String string2 = bundle.getString("filePath");
            String string3 = bundle.getString("module");
            String string4 = bundle.getString("accStatus");
            if (string3 == null) {
                Log.e("BYDLogService", "compressCrashLogFile module = null ");
                return;
            }
            if (string3.contains("/")) {
                string3 = string3.replace("/", "#");
            }
            int i2 = bundle.getInt("hash");
            if (string.equals("KERNELPANIC")) {
                str = this.G.j() + "_" + string + "_" + i2 + "@" + strGroup + "_" + this.G.i() + ".zip";
            } else {
                str = string3 + "_" + string + "_" + i2 + "@" + strGroup + ".zip";
            }
            Log.d("BYDLogService", "compressCrashLogFile path = " + string2 + " zipName = " + str);
            if (string2 == null) {
                Log.e("BYDLogService", "compressCrashLogFile path = null ");
                return;
            }
            try {
                if (SystemProperties.get("persist.sys.onlyTrace.enable", "0").equals("1")) {
                    strN = com.byd.logtools.tools.g.o(new File(string2), new File("/data/data/com.byd.logtools/crashLog/" + str));
                } else {
                    strN = com.byd.logtools.tools.g.n(new File(string2), new File("/data/data/com.byd.logtools/crashLog/"), str);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("logName", strN);
                jSONObject.put("accStatus", string4);
                n.put(jSONObject);
            } catch (Exception unused) {
            }
            Log.d("BYDLogService", "LogScope is crashmReportTime is 24");
            "crash".equals(this.X);
        }
    }

    public void L0() {
        if (SystemProperties.get("persist.service.ota.enable", "0").equals("1")) {
            ArrayList arrayList = new ArrayList();
            com.byd.logtools.tools.g.e(new File("/data/data/com.byd.logtools/recoveryLog/"), arrayList);
            if (arrayList.size() <= 0) {
                Log.d("BYDLogService", "recoveryLogFolder needUploadFile is null!");
            } else {
                this.E.sendEmptyMessage(18);
                this.l0 = 0;
            }
        }
    }

    public void M0() {
        ArrayList arrayList = new ArrayList();
        com.byd.logtools.tools.g.e(new File("/data/data/com.byd.logtools/requestZipLog/"), arrayList);
        if (arrayList.size() > 0) {
            this.E.sendEmptyMessage(20);
        } else {
            Log.d("BYDLogService", "requestZipLogFolder needUploadFile is null!");
        }
    }

    public void N0() {
        Log.d("BYDLogService", "uploadAllLogsForOneKey oneKeyUploadFlag = " + l);
        if (l) {
            Log.d("BYDLogService", "uploadAllLogsForOneKey is running so wait.");
            r rVar = this.y;
            if (rVar != null) {
                rVar.e("action.upload.all.log.running");
                return;
            }
            return;
        }
        l = true;
        r rVar2 = this.y;
        if (rVar2 != null) {
            rVar2.e("action.upload.all.log.running");
        }
        if (G("oneKey")) {
            ArrayList arrayList = new ArrayList();
            List<File> list = this.s;
            File file = list.get(list.size() - 1);
            arrayList.add(file);
            boolean zC = this.H.c(this.G.a(n), arrayList);
            Log.d("BYDLogService", "uploadAllLogsForOneKey uploadRst = " + zC);
            if (zC) {
                r rVar3 = this.y;
                if (rVar3 != null) {
                    rVar3.e("action.upload.all.log.finish");
                }
                S0(this.U);
                Q(file);
            } else {
                r rVar4 = this.y;
                if (rVar4 != null) {
                    rVar4.e("action.upload.all.log.error");
                }
            }
            Q(file);
        } else {
            r rVar5 = this.y;
            if (rVar5 != null) {
                rVar5.e("action.upload.all.log.error");
            }
        }
        l = false;
    }

    public void O(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.d("BYDLogService", "deleteFilesByName list null or empty");
            return;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            File file = new File(f3175c + it.next());
            if (file.exists()) {
                file.delete();
            }
        }
        i0();
    }

    public void O0() {
        Log.d("BYDLogService", "uploadAllLogsOneKey begin");
        if (H()) {
            Log.d("BYDLogService", "new thread to compress and upload log");
            new m().start();
            return;
        }
        Log.d("BYDLogService", "!canUpload");
        r rVar = this.y;
        if (rVar != null) {
            rVar.e("action.conn.network.error");
        }
    }

    public void P0() {
        Log.d("BYDLogService", "uploadCrashInfo");
        com.byd.logtools.tools.c cVar = this.G;
        cVar.q("/data/data/com.byd.logtools/", "json", cVar.c().toString());
        com.byd.logtools.tools.g.o(new File("/data/data/com.byd.bydlogtool/json"), new File("/data/data/com.byd.bydlogtool/jsonZip.zip"));
        if (!this.H.b(this.G.a(null), "/data/data/com.byd.bydlogtool/jsonZip.zip")) {
            Log.d("BYDLogService", "upload failed, do not delete zip file and database");
            return;
        }
        this.G.e("jsonZip.zip");
        this.G.f();
        S0(this.V);
    }

    public void R0(ArrayList<String> arrayList) {
        Log.d("BYDLogService", "uploadSpecificFile begin");
        ArrayList arrayList2 = new ArrayList(arrayList);
        arrayList.clear();
        if (H()) {
            Log.d("BYDLogService", "new thread to compress and upload log");
            new k(arrayList2).start();
            return;
        }
        Log.d("BYDLogService", "uploadSpecificFile !canUpload");
        r rVar = this.y;
        if (rVar != null) {
            rVar.e("action.conn.network.error");
        }
    }

    public boolean S() {
        return this.k0;
    }

    public void S0(String str) {
        long jV = V();
        com.byd.logtools.d.a.b().e(str, jV);
        Log.d("BYDLogService", "writeCloudTimeToSP : " + str + " currTime = " + jV);
    }

    public boolean U() {
        return this.Z;
    }

    public boolean Z() {
        return this.g0;
    }

    public void a() {
        if (!p) {
            new i().start();
            BydLogHelper.logD("BYDLogService ClearLogThread send message to clear log");
            return;
        }
        BydLogHelper.logD("BYDLogService ClearAllLog again");
        com.byd.logtools.c.a aVar = this.A;
        if (aVar != null) {
            aVar.b(6);
        }
    }

    public boolean b0() {
        return this.f0;
    }

    public boolean c0() {
        return this.i0;
    }

    public boolean d0() {
        return this.j0;
    }

    public void e0(String str, String str2, String str3) {
        if (str != null) {
            new j(str, str2, str3).start();
        }
    }

    public void f0() {
        com.byd.logtools.c.a aVar = this.A;
        if (aVar != null) {
            aVar.b(1);
        }
        if (K()) {
            com.byd.logtools.c.a aVar2 = this.A;
            if (aVar2 != null) {
                aVar2.b(2);
            }
            Log.e("BYDLogService", "BYDLogService finish to clear log");
            return;
        }
        com.byd.logtools.c.a aVar3 = this.A;
        if (aVar3 != null) {
            aVar3.b(3);
        }
        Log.d("BYDLogService", "LogService save log error");
    }

    public void g0(int i2) {
        com.byd.logtools.c.b bVar = this.z;
        if (bVar != null) {
            bVar.f(1);
        }
        if (!s0(i2, 2)) {
            com.byd.logtools.c.b bVar2 = this.z;
            if (bVar2 != null) {
                bVar2.f(3);
            }
            Log.e("BYDLogService", "LogService save log error");
            return;
        }
        com.byd.logtools.c.b bVar3 = this.z;
        if (bVar3 != null) {
            bVar3.f(2);
        } else {
            o0(getString(R.string.str_save_log_to_udisk));
        }
        Log.e("BYDLogService", "BYDLogService finish to save log");
    }

    public void h0(String str, boolean z, boolean z2) {
        String strW0 = w0(2, z);
        if (strW0 != null) {
            Log.e("BYDLogService", "BYDLogService finish to save vr  log");
            o0(getString(R.string.str_save_finish));
        } else {
            o0(getString(R.string.str_save_fail));
            Log.e("BYDLogService", "LogService save vr log error");
        }
        if (z2) {
            if (com.byd.logtools.d.h.a().b(getApplicationContext(), "com.byd.vrsettings")) {
                StringBuilder sb = new StringBuilder();
                sb.append("handleToSaveVrLog needResult, result = ");
                sb.append(strW0 != null);
                sb.append(", path = ");
                sb.append(strW0);
                Log.d("BYDLogService", sb.toString());
                Intent intent = new Intent();
                intent.setAction("bydlogtool.action.savelogresult");
                intent.setPackage("com.byd.vrsettings");
                intent.putExtra("result", strW0 != null);
                intent.putExtra("path", strW0);
                sendBroadcast(intent);
                return;
            }
            if (!com.byd.logtools.d.h.a().b(getApplicationContext(), "com.byd.vrassistant.xf")) {
                Log.e("BYDLogService", "no vr app install");
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("handleToSaveVrLog needResult, result = ");
            sb2.append(strW0 != null);
            sb2.append(", path = ");
            sb2.append(strW0);
            Log.d("BYDLogService", sb2.toString());
            Intent intent2 = new Intent();
            intent2.setAction("bydlogtool.action.savelogresult");
            intent2.setPackage("com.byd.vrassistant.xf");
            intent2.putExtra("result", strW0 != null);
            intent2.putExtra("path", strW0);
            sendBroadcast(intent2);
        }
    }

    public void i0() {
        if (this.s == null) {
            this.s = new ArrayList();
        }
        if (this.t == null) {
            this.t = new ArrayList();
        }
        this.s.clear();
        this.t.clear();
        File file = new File(f3174b);
        File file2 = new File(f3175c);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                Log.d("BYDLogService", "initAllLogList: There is no log at " + f3174b);
                return;
            }
            for (File file3 : fileArrListFiles) {
                this.s.add(file3);
            }
            Collections.sort(this.s, this.B0);
        }
        if (file2.exists()) {
            File[] fileArrListFiles2 = file2.listFiles();
            if (fileArrListFiles2 == null) {
                Log.d("BYDLogService", "initHistoryLogList: There is no log at " + f3175c);
                return;
            }
            for (File file4 : fileArrListFiles2) {
                this.t.add(file4);
            }
            Collections.sort(this.t, this.B0);
            q qVar = this.x;
            if (qVar != null) {
                qVar.d(this.t);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.util.Iterator] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public boolean n0(Context context) {
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
                        BydLogHelper.logD("addDisk volumeInfo = " + volumeInfo + ", getDeviceType = " + volumeInfo.getDeviceType() + ", volumeInfo.isMountedReadable() = " + volumeInfo.isMountedReadable());
                        if (f3179g.equals(volumeInfo.getDeviceType()) && volumeInfo.isMountedReadable()) {
                            f3178f = volumeInfo.path;
                            BydLogHelper.logD("USBOTG uDiskPath = " + f3178f);
                            it = 1;
                            return true;
                        }
                        if (f3180h.equals(volumeInfo.getDeviceType())) {
                            BydLogHelper.logD("SDCARD uDiskPath ");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            BydLogHelper.logD("initVolume failed = " + th2);
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.F;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        N();
        p0();
        f3174b = BydLogApplication.d().e();
        f3175c = BydLogApplication.d().c();
        f3177e = BydLogApplication.d().f();
        f3176d = BydLogApplication.d().f();
        if (com.byd.logtools.d.f.d(f3174b)) {
            BydLogHelper.logD("file mkDir success or exists, alarmLogPath = " + f3174b);
        }
        if (com.byd.logtools.d.f.d(f3175c)) {
            BydLogHelper.logD("file mkDir success or exists, historyLogPath = " + f3175c);
        }
        if (com.byd.logtools.d.f.d(f3177e)) {
            BydLogHelper.logD("file mkDir success or exists, tempLogPath = " + f3177e);
        }
        if (com.byd.logtools.d.f.d(f3176d)) {
            BydLogHelper.logD("file mkDir success or exists, historyTempLogs = " + f3176d);
        }
        i0();
        i = 0;
        j0();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.d("BYDLogService", "service onDestory");
        unregisterReceiver(this.v);
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i2) {
        if (intent != null && "android.intent.action.DROPBOX_ENTRY_ADDED".equals(intent.getAction())) {
            n nVar = this.E;
            nVar.sendMessage(nVar.obtainMessage(2, intent));
            return;
        }
        if (intent == null || !"android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            if (intent == null || !"android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                if (intent != null && "android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                    this.E.sendEmptyMessage(9);
                    return;
                }
                if (intent == null || !"android.intent.action.CMD_SAVE_ALL_LOG".equals(intent.getAction())) {
                    if (intent != null && "bydlogtool.action.savelog".equals(intent.getAction())) {
                        n nVar2 = this.E;
                        nVar2.sendMessage(nVar2.obtainMessage(11, intent));
                    } else {
                        if (intent == null || !"action.auto.save.log.anyway".equals(intent.getAction())) {
                            return;
                        }
                        this.E.sendEmptyMessage(12);
                    }
                }
            }
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }

    public void r0(int i2) {
        if (!o) {
            new o(i2).start();
            BydLogHelper.logD("BYDLogService SaveLogThread send message to save log");
            return;
        }
        BydLogHelper.logD("BYDLogService saveAllLog again");
        com.byd.logtools.c.b bVar = this.z;
        if (bVar != null) {
            bVar.f(6);
        }
    }

    public boolean s0(int i2, int i3) {
        String strSubstring;
        if (!l0(i3)) {
            com.byd.logtools.c.b bVar = this.z;
            if (bVar != null) {
                bVar.f(10);
            }
            return false;
        }
        File file = new File(f3177e);
        if (!file.exists()) {
            boolean zMkdirs = file.mkdirs();
            Log.e("BYDLogService", "saveAllLogsAndCompress mkdirs is " + zMkdirs);
            if (!zMkdirs) {
                return false;
            }
        }
        File file2 = new File(f3177e + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + "androidLog.zip");
        Log.i("BYDLogService", "saveAllLogsAndCompress path = " + file2.getPath() + ", path.getParentFile().getPath()) = " + file2.getParentFile().getPath());
        if (!J(file2.getParentFile().getPath())) {
            Log.e("BYDLogService", "saveAllLogsAndCompress this no space ");
            this.z.f(9);
            return false;
        }
        String str = SystemProperties.get("persist.vendor.bosch.cfg.diag.vin", "00000000000123456");
        if (str == null || str.length() < 6) {
            strSubstring = "123456";
        } else {
            strSubstring = str.substring(str.length() - 6);
            Log.i("BYDLogService", "saveAllLogsAndCompress vinCode = " + strSubstring);
        }
        File file3 = new File(j + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + "androidLog_encryp" + strSubstring + ".zip");
        StringBuilder sb = new StringBuilder();
        sb.append("saveAllLogsAndCompress encrypFilePath = ");
        sb.append(file3.getPath());
        sb.append(", encrypFilePath.getParentFile().getPath()) = ");
        sb.append(file3.getParentFile().getPath());
        Log.i("BYDLogService", sb.toString());
        if (!file3.getParentFile().exists()) {
            boolean zMkdirs2 = file3.getParentFile().mkdirs();
            Log.i("BYDLogService", "encrypFilePath.getParentFile().mkdirs is " + zMkdirs2);
            if (!zMkdirs2) {
                return false;
            }
        }
        if (!J(file3.getParentFile().getPath())) {
            Log.e("BYDLogService", "saveAllLogsAndCompress this no space ");
            this.z.f(9);
            return false;
        }
        try {
            com.byd.logtools.tools.g.d(i2, file2, this.x0, "/data/vendor/nfs/nfs_log/qnx_log/", "/data/vendor/nfs/nfs_log/qnx_dump", "/storage/self/primary/tcpdump/", this.m0, "/data/anr/", this.s0, this.u0, this.t0, this.w0, this.n0, this.o0, this.z0);
            Log.w("BYDLogService", "saveAllLogsAndCompress aesEncryptFile zip logs path.getName() = " + file2.getName() + ", fileSize = " + file2.length());
            com.byd.logtools.d.e.a(file2.getPath(), file3.getPath(), strSubstring + strSubstring, com.byd.logtools.d.e.f3167f);
            Log.w("BYDLogService", "saveAllLogsAndCompress encrypFilePath.getName()= " + file3.getName() + ", encrypFilePath fileSize = " + file3.length());
            com.byd.logtools.d.f.b(file);
            if (j.contains(f3175c)) {
                Log.i("BYDLogService", "saveAllLogsAndCompress copy all log to  /sdcard/logs. ");
                com.byd.logtools.c.b bVar2 = this.z;
                if (bVar2 != null) {
                    bVar2.f(7);
                }
                F(file2, "history");
            }
            x0(file2);
            return true;
        } catch (Exception e2) {
            Log.e("BYDLogService", "compress file error");
            e2.printStackTrace();
            com.byd.logtools.d.f.b(file);
            return false;
        }
    }

    public String w0(int i2, boolean z) {
        if (!l0(i2)) {
            return null;
        }
        File file = new File(j + com.byd.logtools.tools.e.a("yyyy-MM-dd-HH-mm-ssZ").format(new Date()) + "VrLog.zip");
        Log.i("BYDLogService", "saveAllLogsAndCompress path = " + file.getPath() + ", path.getParentFile().getPath()) = " + file.getParentFile().getPath());
        if (!file.getParentFile().exists()) {
            boolean zMkdirs = file.getParentFile().mkdirs();
            Log.i("BYDLogService", "path.getParentFile().mkdirs is " + zMkdirs);
            if (!zMkdirs) {
                return null;
            }
        }
        if (!J(file.getParentFile().getPath())) {
            Log.e("BYDLogService", "saveAllLogsAndCompress this no space ");
            this.z.f(9);
            return null;
        }
        try {
            if (z) {
                com.byd.logtools.tools.g.d(-1, file, "/data/vendor/nfs/nfs_log/qnx_log/", "/data/vendor/nfs/nfs_log/qnx_dump", this.o0, this.v0);
            } else {
                com.byd.logtools.tools.g.d(-1, file, this.v0);
            }
            return file.getAbsolutePath();
        } catch (Exception e2) {
            Log.e("BYDLogService", "compress file error");
            e2.printStackTrace();
            return null;
        }
    }

    public void y0(long j2, int i2, String str) {
        if (this.d0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            BydLogHelper.logV("setAlarm firstTime = " + jElapsedRealtime + ", actionIntent = " + str);
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, new Intent(str), 134217728);
            AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
            alarmManager.cancel(broadcast);
            alarmManager.setRepeating(2, jElapsedRealtime + j2, ((long) i2) * 3600000, broadcast);
            if (str.equals("action.upload.Log")) {
                this.b0 = true;
            }
            if (str.equals("action.upload.crash.log.info")) {
                this.c0 = true;
            }
        }
    }

    public void z0(com.byd.logtools.c.a aVar) {
        this.A = aVar;
    }
}
