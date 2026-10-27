package org.chromium.chrome.browser.crash;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;
import defpackage.a8;
import defpackage.d10;
import defpackage.f10;
import defpackage.fa1;
import defpackage.v42;
import defpackage.vh1;
import defpackage.xz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.base.SplitCompatIntentService;
import org.chromium.components.minidump_uploader.MinidumpUploadJobService;

/* JADX INFO: loaded from: classes.dex */
public class MinidumpUploadServiceImpl extends SplitCompatIntentService.a {
    public static final AtomicBoolean b = new AtomicBoolean();
    public static final AtomicBoolean c = new AtomicBoolean();
    public static final String[] d = {"Browser", "Renderer", "GPU", "Other"};

    @CalledByNative
    public static boolean browserCrashMetricsInitialized() {
        return b.get();
    }

    public static String c(String str) throws Throwable {
        BufferedReader bufferedReader;
        IOException e;
        String line;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
        } catch (IOException e2) {
            bufferedReader = null;
            e = e2;
        } catch (Throwable th) {
            th = th;
            a8.s(bufferedReader2);
            throw th;
        }
        do {
            try {
                try {
                    line = bufferedReader.readLine();
                    if (line != null) {
                    }
                } catch (IOException e3) {
                    e = e3;
                    fa1.l("MinidmpUploadService", "Error while reading crash file %s: %s", str, e.toString());
                }
                a8.s(bufferedReader);
                return "Other";
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                a8.s(bufferedReader2);
                throw th;
            }
        } while (!line.equals("Content-Disposition: form-data; name=\"ptype\""));
        bufferedReader.readLine();
        String line2 = bufferedReader.readLine();
        if (line2 == null) {
            a8.s(bufferedReader);
            return "Other";
        }
        if (line2.equals("browser")) {
            a8.s(bufferedReader);
            return "Browser";
        }
        if (line2.equals("renderer")) {
            a8.s(bufferedReader);
            return "Renderer";
        }
        if (line2.equals("gpu-process")) {
            a8.s(bufferedReader);
            return "GPU";
        }
        a8.s(bufferedReader);
        return "Other";
        a8.s(bufferedReader);
        return "Other";
    }

    public static void d(String str) {
        String strC = c(str);
        if ("Browser".equals(strC)) {
            c.set(true);
        }
        f10 f10Var = f10.b;
        f10Var.getClass();
        f10Var.a.c(f10.a(strC));
    }

    @CalledByNative
    public static boolean didBrowserCrashRecently() {
        return c.get();
    }

    public static void e(String str) {
        String strC = c(str.replace("dmp", "up").replace("forced", "up"));
        if ("Browser".equals(strC)) {
            c.set(true);
        }
        f10 f10Var = f10.b;
        f10Var.getClass();
        f10Var.a.c(f10.b(strC));
    }

    @SuppressLint({"NewApi"})
    public static void f() {
        v42 v42VarG = v42.g();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putBoolean("isClientInMetricsSample", v42VarG.f());
        persistableBundle.putBoolean("isUploadEnabledForTests", v42VarG.e());
        JobInfo.Builder requiredNetworkType = new JobInfo.Builder(43, new ComponentName(xz.a, (Class<?>) ChromeMinidumpUploadJobService.class)).setExtras(persistableBundle).setRequiredNetworkType(2);
        int i = MinidumpUploadJobService.j;
        Log.i("cr_MinidumpJobService", "Scheduling upload of all pending minidumps.");
        ((JobScheduler) xz.a.getSystemService("jobscheduler")).schedule(requiredNetworkType.setBackoffCriteria(1800000L, 1).build());
    }

    public static void g(File file) {
        d10 d10Var = new d10(xz.a.getCacheDir());
        Intent intent = new Intent(xz.a, (Class<?>) MinidumpUploadService.class);
        intent.setAction("com.google.android.apps.chrome.crash.ACTION_UPLOAD");
        intent.putExtra("minidump_file", file.getAbsolutePath());
        intent.putExtra("upload_log", new File(d10Var.d(), "uploads.log").getAbsolutePath());
        xz.a.startService(intent);
    }

    @CalledByNative
    public static void tryUploadCrashDumpWithLocalId(String str) {
        File file;
        if (str == null || str.isEmpty()) {
            Log.w("cr_MinidmpUploadService", "Cannot force crash upload since local crash id is absent.");
            return;
        }
        File file2 = null;
        File[] fileArrG = new d10(xz.a.getCacheDir()).g(null);
        int length = fileArrG.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                file = null;
                break;
            }
            file = fileArrG[i];
            if ((file.getName().contains(".dmp") || file.getName().contains(".skipped") || file.getName().contains(".forced")) && file.getName().split("\\.")[0].endsWith(str)) {
                break;
            } else {
                i++;
            }
        }
        if (file == null) {
            Log.w("cr_MinidmpUploadService", "Could not find a crash dump with local ID ".concat(str));
            return;
        }
        if (file.getName().contains(".up")) {
            Log.w("cr_CrashFileManager", "Refusing to reset upload attempt state for a file that has already been successfully uploaded: " + file.getName());
        } else {
            String path = file.getPath();
            int iH = d10.h(path);
            int i2 = iH >= 0 ? iH : 0;
            if (i2 > 0) {
                path = path.replace(".try" + i2, ".try0");
            }
            File file3 = new File(path.replace(".skipped", ".forced").replace(".dmp", ".forced"));
            if (file.renameTo(file3)) {
                file2 = file3;
            }
        }
        if (file2 != null) {
            f();
            return;
        }
        Log.w("cr_MinidmpUploadService", "Could not rename the file " + file.getName() + " for re-upload");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatIntentService.a
    public final void a(Intent intent) {
        if (intent == null) {
            return;
        }
        if (!"com.google.android.apps.chrome.crash.ACTION_UPLOAD".equals(intent.getAction())) {
            Log.w("cr_MinidmpUploadService", "Got unknown action from intent: " + intent.getAction());
            return;
        }
        String stringExtra = intent.getStringExtra("minidump_file");
        if (stringExtra == null || stringExtra.isEmpty()) {
            Log.w("cr_MinidmpUploadService", "Cannot upload crash data since minidump is absent.");
            return;
        }
        File file = new File(stringExtra);
        if (!file.isFile()) {
            Log.w("cr_MinidmpUploadService", "Cannot upload crash data since specified minidump " + stringExtra + " is not present.");
            return;
        }
        int iH = d10.h(stringExtra);
        if (iH < 0) {
            iH = 0;
        }
        if (iH >= 3 || iH < 0) {
            Log.e("cr_MinidmpUploadService", "Giving up on trying to upload " + stringExtra + " after failing to read a valid attempt number.");
            return;
        }
        int iIntValue = new vh1(file, new File(intent.getStringExtra("upload_log")), v42.g()).call().intValue();
        if (iIntValue == 0) {
            e(stringExtra);
            return;
        }
        if (iIntValue == 1) {
            int i = iH + 1;
            if (i == 3) {
                d(stringExtra);
            }
            if (d10.j(file) == null) {
                Log.w("cr_MinidmpUploadService", "Failed to rename minidump ".concat(stringExtra));
            } else if (i < 3) {
                f();
            }
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatIntentService.a
    public final void b() {
        this.a.setIntentRedelivery(true);
    }
}
