package com.byd.logtools.apkinstall.c;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.HttpUrl;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.ws.RealWebSocket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ApkInstallManager.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f3060a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.logtools.apkinstall.b.a> f3062c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3063d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.byd.logtools.apkinstall.a.a f3064e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ConcurrentLinkedQueue<com.byd.logtools.apkinstall.b.a> f3065f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ExecutorService f3066g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final com.byd.logtools.apkinstall.b.b f3067h;
    private final com.byd.logtools.apkinstall.b.b i;
    private int k;
    private int l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3061b = HttpUrl.FRAGMENT_ENCODE_SET;
    private String j = "BYD8155F";
    private int m = 47;

    /* JADX INFO: compiled from: ApkInstallManager.java */
    class a extends PackageInstaller.SessionCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ PackageInstaller f3068a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3069b;

        a(PackageInstaller packageInstaller, String str) {
            this.f3068a = packageInstaller;
            this.f3069b = str;
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onActiveChanged(int i, boolean z) {
            BydLogHelper.logD("==onActiveChanged==sessionId: " + i);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onBadgingChanged(int i) {
            BydLogHelper.logD("==onBadgingChanged==sessionId: " + i);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onCreated(int i) {
            BydLogHelper.logD("==onCreated==sessionId: " + i);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onFinished(int i, boolean z) {
            BydLogHelper.logD("===onFinished=sessionId: " + i + "==success:" + z);
            this.f3068a.unregisterSessionCallback(this);
            e.this.h(this.f3069b, z ? 0 : 3);
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onProgressChanged(int i, float f2) {
            BydLogHelper.logD("===onProgressChanged=sessionId: " + i + "===progress: " + f2);
        }
    }

    private e(Context context) {
        this.f3063d = context.getApplicationContext();
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.f3066g = executorServiceNewSingleThreadExecutor;
        this.f3067h = new com.byd.logtools.apkinstall.b.b(false, true, new String[0]);
        this.i = new com.byd.logtools.apkinstall.b.b(true, true, new String[0]);
        executorServiceNewSingleThreadExecutor.execute(new Runnable() { // from class: com.byd.logtools.apkinstall.c.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f3056b.s();
            }
        });
    }

    private String e(String str) {
        PackageManager packageManager = this.f3063d.getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 1);
        if (packageArchiveInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        String str2 = applicationInfo.packageName;
        applicationInfo.loadLabel(packageManager).toString();
        return str2;
    }

    public static e f(Context context) {
        if (f3060a == null) {
            f3060a = new e(context);
        }
        return f3060a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(String str, int i) {
        BydLogHelper.logD("handleInstallResult, apkName:" + str + ", resultCode:" + i);
        com.byd.logtools.apkinstall.a.a aVar = this.f3064e;
        if (aVar != null) {
            aVar.a(str, i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3063d.getFilesDir());
        String str2 = File.separator;
        sb.append(str2);
        sb.append("Application Installation ");
        sb.append(str2);
        sb.append(str);
        File file = new File(sb.toString());
        if (file.exists()) {
            file.delete();
        }
        this.f3065f.poll();
        if (this.f3065f.isEmpty()) {
            return;
        }
        k(this.f3065f.peek());
    }

    private boolean i(com.byd.logtools.apkinstall.b.a aVar, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            BydLogHelper.logD("whiteList:null, hasPermission:false");
            return true;
        }
        String strB = aVar.b();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3063d.getFilesDir());
        String str = File.separator;
        sb.append(str);
        sb.append("Application Installation ");
        sb.append(str);
        sb.append(aVar.a());
        String string = sb.toString();
        if (!com.byd.logtools.apkinstall.d.a.a(strB, string)) {
            BydLogHelper.logD("copyFile failed, hasPermission:false");
            return false;
        }
        String strE = e(string);
        boolean z = false;
        for (String str2 : strArr) {
            if (!TextUtils.isEmpty(str2) && str2.equals(strE)) {
                z = true;
            }
        }
        BydLogHelper.logD("getApkPkgName-----pkgName:" + strE + "-------hasPermission:" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public void s() {
        BufferedReader bufferedReader;
        StringBuilder sb;
        String strA = com.byd.bydcommonlib.a.a(this.f3063d);
        if (strA != null && !strA.isEmpty()) {
            this.m = Integer.parseInt(strA);
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.f3063d.getAssets().open("ApkInstallWhiteList.json")));
            try {
                sb = new StringBuilder();
            } finally {
            }
        } catch (IOException | JSONException e2) {
            e2.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            } else {
                sb.append(line);
            }
            e2.printStackTrace();
            BydLogHelper.logE("initCountryInfo countryCode:" + strA + ", mOfflineInstallConfig:" + this.f3067h.toString() + ", mOnlineInstallConfig:" + this.i.toString());
        }
        JSONArray jSONArray = new JSONArray(sb.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getInt("countryCode") == this.m) {
                if (jSONObject.has("offlineApkList")) {
                    this.f3067h.e((String[]) new com.google.gson.d().i(jSONObject.getString("offlineApkList"), String[].class));
                    this.f3067h.d(false);
                }
                if (jSONObject.has("onlineApkList")) {
                    this.i.e((String[]) new com.google.gson.d().i(jSONObject.getString("onlineApkList"), String[].class));
                    this.i.d(false);
                }
                if (jSONObject.has("isOfflineInstallSupport")) {
                    this.f3067h.f(jSONObject.getBoolean("isOfflineInstallSupport"));
                }
                if (jSONObject.has("isOnlineInstallSupport")) {
                    this.i.f(jSONObject.getBoolean("isOnlineInstallSupport"));
                }
                if (jSONObject.has("password")) {
                    this.j = jSONObject.getString("password");
                }
            }
        }
        bufferedReader.close();
        BydLogHelper.logE("initCountryInfo countryCode:" + strA + ", mOfflineInstallConfig:" + this.f3067h.toString() + ", mOnlineInstallConfig:" + this.i.toString());
    }

    private void k(final com.byd.logtools.apkinstall.b.a aVar) {
        this.f3066g.execute(new Runnable() { // from class: com.byd.logtools.apkinstall.c.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f3057b.q(aVar);
            }
        });
    }

    private boolean l() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = (statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong()) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        BydLogHelper.logD("isLocalMemoryEnough:" + blockSizeLong);
        return blockSizeLong >= 2097152;
    }

    private boolean m() {
        BydLogHelper.logW("ApkInstallManager", "isNeedToCheckPermission: mVerifyType:" + this.k + " mScanType:" + this.l + " " + this.f3067h.toString() + " " + this.i.toString());
        if (this.k != 1 || this.f3067h.b()) {
            return this.k == 2 && this.l == 2 && !this.i.b();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q(com.byd.logtools.apkinstall.b.a aVar) {
        BydLogHelper.logD("installApk:" + aVar);
        boolean z = true;
        if (!l()) {
            h(aVar.a(), 1);
            return;
        }
        String strB = aVar.b();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3063d.getFilesDir());
        String str = File.separator;
        sb.append(str);
        sb.append("Application Installation ");
        sb.append(str);
        sb.append(aVar.a());
        String string = sb.toString();
        if (m()) {
            if (!i(aVar, (this.k == 1 ? this.f3067h : this.i).a())) {
                z = false;
            }
        }
        if (!z) {
            h(aVar.a(), 2);
            return;
        }
        String strA = aVar.a();
        if (m()) {
            strB = string;
        }
        t(strA, strB);
    }

    private void t(String str, String str2) {
        PackageInstaller.Session session = null;
        try {
            PackageInstaller packageInstaller = this.f3063d.getPackageManager().getPackageInstaller();
            packageInstaller.registerSessionCallback(new a(packageInstaller, str), new Handler(Looper.getMainLooper()));
            PackageInstaller.Session sessionOpenSession = packageInstaller.openSession(packageInstaller.createSession(new PackageInstaller.SessionParams(1)));
            OutputStream outputStreamOpenWrite = sessionOpenSession.openWrite("package", 0L, -1L);
            try {
                FileInputStream fileInputStream = new FileInputStream(str2);
                try {
                    byte[] bArr = new byte[Http2.INITIAL_MAX_FRAME_SIZE];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i < 0) {
                            break;
                        } else {
                            outputStreamOpenWrite.write(bArr, 0, i);
                        }
                    }
                    fileInputStream.close();
                    if (outputStreamOpenWrite != null) {
                        outputStreamOpenWrite.close();
                    }
                    sessionOpenSession.commit(PendingIntent.getBroadcast(this.f3063d, 0, new Intent(), 134217728).getIntentSender());
                } finally {
                }
            } catch (Throwable th) {
                if (outputStreamOpenWrite != null) {
                    try {
                        outputStreamOpenWrite.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (RuntimeException e3) {
            if (0 != 0) {
                session.abandon();
            }
            e3.printStackTrace();
            h(str, 3);
        }
    }

    public void b() {
        BydLogHelper.logD("cancelAllInstallTask");
        ConcurrentLinkedQueue<com.byd.logtools.apkinstall.b.a> concurrentLinkedQueue = this.f3065f;
        if (concurrentLinkedQueue != null) {
            int size = concurrentLinkedQueue.size();
            for (int i = 0; i < size; i++) {
                this.f3065f.poll();
            }
        }
    }

    public void c(com.byd.logtools.apkinstall.b.a aVar) {
        BydLogHelper.logD("createInstallTask:" + aVar.a());
        if (this.f3065f == null) {
            this.f3065f = new ConcurrentLinkedQueue<>();
        }
        if (!this.f3065f.isEmpty()) {
            this.f3065f.offer(aVar);
        } else {
            this.f3065f.offer(aVar);
            k(aVar);
        }
    }

    public List<com.byd.logtools.apkinstall.b.a> d(int i) {
        File[] fileArrListFiles;
        if (this.f3062c == null) {
            this.f3062c = new ArrayList();
        }
        this.f3062c.clear();
        StringBuilder sb = new StringBuilder();
        sb.append(i == 1 ? "Third Party Apps " : "Application Installation ");
        sb.append(this.m);
        this.f3061b = sb.toString();
        File file = new File(f.e(this.f3063d).f(), this.f3061b);
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.getName().endsWith(".apk")) {
                    this.f3062c.add(new com.byd.logtools.apkinstall.b.a(file2.getName(), file2.getPath()));
                }
            }
        }
        BydLogHelper.logE("getApkListFromUsb, path: " + file.getPath() + ", list: " + this.f3062c);
        return this.f3062c;
    }

    public String g() {
        return this.j;
    }

    public boolean n() {
        return this.f3067h.c();
    }

    public boolean o() {
        return this.i.c();
    }

    public void u() {
        this.f3064e = null;
    }

    public void v(com.byd.logtools.apkinstall.a.a aVar) {
        this.f3064e = aVar;
    }

    public void w(int i) {
        this.l = i;
    }

    public void x(int i) {
        this.k = i;
    }
}
