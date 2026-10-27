package org.chromium.components.crash;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.util.Log;
import defpackage.d10;
import defpackage.iv;
import defpackage.ku2;
import defpackage.l51;
import defpackage.n3;
import defpackage.xz;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.BuildInfo;
import org.chromium.base.CollectionUtil;
import org.chromium.base.PiiElider;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;
import org.chromium.components.crash.PureJavaExceptionHandler;

/* JADX INFO: compiled from: PureJavaExceptionReporter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements PureJavaExceptionHandler.a {
    public boolean a;
    public HashMap b;
    public File c;
    public FileOutputStream d;
    public final String e = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    public final String f = "------------" + UUID.randomUUID() + "\r\n";
    public final boolean g = true;

    @Override // org.chromium.components.crash.PureJavaExceptionHandler.a
    public final void a(Throwable th) {
        ku2 ku2VarC = ku2.C();
        try {
            b(th);
            c();
            File file = this.c;
            if (file != null && this.a) {
                if (this.g) {
                    this.c = n3.n(file, new d10(xz.a.getCacheDir()));
                }
                try {
                    MinidumpUploadServiceImpl.g(this.c);
                } catch (SecurityException e) {
                    Log.w("cr_LogcatExtraction", e.toString());
                }
            }
            ku2VarC.close();
        } finally {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
        }
    }

    @SuppressLint({"WrongConstant"})
    public final void b(Throwable th) {
        String processName = Application.getProcessName();
        if (processName == null || !processName.contains(":")) {
            processName = "browser";
        }
        BuildInfo buildInfo = BuildInfo.a.a;
        HashMap map = new HashMap();
        this.b = map;
        map.put("prod", "Chrome_Android");
        this.b.put("ptype", processName);
        this.b.put("device", Build.DEVICE);
        this.b.put("ver", "4.1.1.0");
        this.b.put("channel", "stable");
        this.b.put("android_build_id", Build.ID);
        this.b.put("model", Build.MODEL);
        this.b.put("brand", Build.BRAND);
        this.b.put("board", Build.BOARD);
        this.b.put("android_build_fp", buildInfo.i);
        HashMap map2 = this.b;
        int i = Build.VERSION.SDK_INT;
        map2.put("sdk", String.valueOf(i));
        this.b.put("android-sdk-int", String.valueOf(i));
        this.b.put("gms_core_version", buildInfo.g);
        this.b.put("installer_package_name", buildInfo.f);
        this.b.put("abi_name", buildInfo.h);
        this.b.put("exception_info", PiiElider.b(Log.getStackTraceString(th)));
        this.b.put("early_java_exception", "true");
        this.b.put("package", String.format("%s v%s (%s)", BuildInfo.o, Long.valueOf(buildInfo.d), buildInfo.e));
        this.b.put("custom_themes", buildInfo.j);
        this.b.put("resources_version", buildInfo.k);
        AtomicReferenceArray<String> atomicReferenceArray = CrashKeys.getInstance().a;
        for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
            String str = atomicReferenceArray.get(i2);
            if (str != null) {
                this.b.put(CrashKeys.c[i2], str);
            }
        }
    }

    public final void c() {
        try {
            String str = "chromium-browser-minidump-" + this.e + ".dmp";
            File file = new File(xz.a.getCacheDir(), "Crash Reports");
            this.a = file.exists();
            String strF = iv.e().f("breakpad-dump-location");
            if (strF != null) {
                file = new File(strF);
                file.mkdirs();
            }
            this.c = new File(file, str);
            this.d = new FileOutputStream(this.c);
            CollectionUtil.b(this.b, new l51(12, this));
            try {
                this.d.write(this.f.getBytes(StandardCharsets.UTF_8));
            } catch (IOException unused) {
            }
            FileOutputStream fileOutputStream = this.d;
            if (fileOutputStream == null) {
                return;
            }
            try {
                try {
                    fileOutputStream.flush();
                    this.d.close();
                } catch (Throwable unused2) {
                    this.c = null;
                }
            } finally {
                this.d = null;
            }
        } catch (FileNotFoundException unused3) {
            this.c = null;
        }
    }
}
