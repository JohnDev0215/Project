package p;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class l40 {
    public static final Charset d = Charset.forName("UTF-8");
    public final Context a;
    public final cm1 b;
    public final gu0 c;

    public l40(Context context, cm1 cm1Var, gu0 gu0Var) {
        this.a = context;
        this.b = cm1Var;
        this.c = gu0Var;
    }

    public static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
                    try {
                        gZIPOutputStream.write(byteArray);
                        gZIPOutputStream.finish();
                        String strEncodeToString = Base64.getEncoder().encodeToString(byteArrayOutputStream2.toByteArray());
                        gZIPOutputStream.close();
                        byteArrayOutputStream2.close();
                        return strEncodeToString;
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static File c(File file, String str) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().endsWith(str)) {
                return file2;
            }
        }
        return null;
    }

    public static void g(gu0 gu0Var, String str, String str2, String str3) throws Throwable {
        BufferedWriter bufferedWriter;
        File file = new File(gu0Var.j(str), str3);
        BufferedWriter bufferedWriter2 = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), d));
        } catch (IOException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedWriter.write(str2);
            zu.q(bufferedWriter, "Failed to close " + file);
        } catch (IOException unused2) {
            bufferedWriter2 = bufferedWriter;
            zu.q(bufferedWriter2, "Failed to close " + file);
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            zu.q(bufferedWriter2, "Failed to close " + file);
            throw th;
        }
    }

    public final wb2 b(String str) {
        File file;
        File file2;
        File file3;
        File file4;
        File file5;
        gu0 gu0Var = this.c;
        File fileJ = gu0Var.j(str);
        File file6 = new File(fileJ, "pending");
        file6.getAbsolutePath();
        Log.isLoggable("FirebaseCrashlytics", 2);
        File fileC = c(file6, ".dmp");
        if (fileC != null) {
            fileC.exists();
        }
        Log.isLoggable("FirebaseCrashlytics", 2);
        ww1 ww1Var = null;
        String strA = null;
        dhVarB = null;
        dh dhVarB = null;
        if (fileJ.exists() && file6.exists()) {
            File fileC2 = c(file6, ".dmp");
            if (Build.VERSION.SDK_INT >= 31) {
                List historicalProcessExitReasons = ((ActivityManager) this.a.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 0);
                long jLastModified = gu0Var.l(str, "start-time").lastModified();
                ArrayList arrayList = new ArrayList();
                Iterator it = historicalProcessExitReasons.iterator();
                while (it.hasNext()) {
                    ApplicationExitInfo applicationExitInfoD = s2.d(it.next());
                    if (applicationExitInfoD.getReason() == 5 && applicationExitInfoD.getTimestamp() >= jLastModified) {
                        arrayList.add(applicationExitInfoD);
                    }
                }
                if (!arrayList.isEmpty()) {
                    ApplicationExitInfo applicationExitInfoD2 = s2.d(arrayList.get(0));
                    uy2 uy2Var = new uy2();
                    uy2Var.d = Integer.valueOf(applicationExitInfoD2.getImportance());
                    String processName = applicationExitInfoD2.getProcessName();
                    if (processName == null) {
                        throw new NullPointerException("Null processName");
                    }
                    uy2Var.b = processName;
                    uy2Var.c = Integer.valueOf(applicationExitInfoD2.getReason());
                    uy2Var.g = Long.valueOf(applicationExitInfoD2.getTimestamp());
                    uy2Var.a = Integer.valueOf(applicationExitInfoD2.getPid());
                    uy2Var.e = Long.valueOf(applicationExitInfoD2.getPss());
                    uy2Var.f = Long.valueOf(applicationExitInfoD2.getRss());
                    try {
                        strA = a(applicationExitInfoD2.getTraceInputStream());
                    } catch (IOException unused) {
                    }
                    uy2Var.h = strA;
                    dhVarB = uy2Var.b();
                }
            }
            ww1 ww1Var2 = new ww1(fileC2, 11, dhVarB);
            File fileC3 = c(fileJ, ".device_info");
            file2 = new File(fileJ, "session.json");
            file3 = new File(fileJ, "app.json");
            file4 = new File(fileJ, "device.json");
            file5 = new File(fileJ, "os.json");
            ww1Var = ww1Var2;
            file = fileC3;
        } else {
            file = null;
            file2 = null;
            file3 = null;
            file4 = null;
            file5 = null;
        }
        wb2 wb2Var = new wb2();
        wb2Var.a = ww1Var;
        wb2Var.b = file;
        wb2Var.c = file2;
        wb2Var.d = file3;
        wb2Var.e = file4;
        wb2Var.f = file5;
        return wb2Var;
    }

    public final void d(String str, String str2, long j) throws Throwable {
        HashMap map = new HashMap();
        map.put("session_id", str);
        map.put("generator", str2);
        map.put("started_at_seconds", Long.valueOf(j));
        g(this.c, str, new JSONObject(map).toString(), "session.json");
    }

    public final void e(String str, si siVar) throws Throwable {
        String str2 = siVar.a;
        g90 g90Var = siVar.f;
        if (((v4) g90Var.k) == null) {
            g90Var.k = new v4(g90Var, 0);
        }
        Object obj = g90Var.k;
        String str3 = (String) ((v4) obj).e;
        if (((v4) obj) == null) {
            g90Var.k = new v4(g90Var, 0);
        }
        String str4 = (String) ((v4) g90Var.k).k;
        HashMap map = new HashMap();
        map.put("app_identifier", str2);
        map.put("version_code", siVar.b);
        map.put("version_name", siVar.c);
        map.put("install_uuid", siVar.d);
        map.put("delivery_mechanism", Integer.valueOf(siVar.e));
        if (str3 == null) {
            str3 = "";
        }
        map.put("development_platform", str3);
        if (str4 == null) {
            str4 = "";
        }
        map.put("development_platform_version", str4);
        g(this.c, str, new JSONObject(map).toString(), "app.json");
    }

    public final void f(String str, ti tiVar) throws Throwable {
        int i = tiVar.a;
        HashMap map = new HashMap();
        map.put("arch", Integer.valueOf(i));
        map.put("build_model", tiVar.b);
        map.put("available_processors", Integer.valueOf(tiVar.c));
        map.put("total_ram", Long.valueOf(tiVar.d));
        map.put("disk_space", Long.valueOf(tiVar.e));
        map.put("is_emulator", Boolean.valueOf(tiVar.f));
        map.put("state", Integer.valueOf(tiVar.g));
        map.put("build_manufacturer", tiVar.h);
        map.put("build_product", tiVar.i);
        g(this.c, str, new JSONObject(map).toString(), "device.json");
    }

    public final void h(String str, ui uiVar) throws Throwable {
        String str2 = uiVar.a;
        HashMap map = new HashMap();
        map.put("version", str2);
        map.put("build_version", uiVar.b);
        map.put("is_rooted", Boolean.valueOf(uiVar.c));
        g(this.c, str, new JSONObject(map).toString(), "os.json");
    }
}
