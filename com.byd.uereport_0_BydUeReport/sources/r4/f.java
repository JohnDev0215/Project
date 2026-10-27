package r4;

import android.os.Environment;
import android.util.Log;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: TipsUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f7815a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f7816b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f7817c = false;

    public static void a() {
        f7815a = Environment.getExternalStorageDirectory() + "/SmartChargeLogs/";
        f7816b = f7815a + "logServer_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINA).format(new Date()) + ".log";
        StringBuilder sb = new StringBuilder();
        sb.append(f7815a);
        sb.append("config.txt");
        f7817c = new File(sb.toString()).exists();
    }

    public static void b(String str) throws Throwable {
        Log.i("smart_charge", str);
        if (f7817c) {
            c(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0082 -> B:40:0x0089). Please report as a decompilation issue!!! */
    public static void c(String str) throws Throwable {
        String str2 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss SSS", Locale.CHINA).format(new Date());
        if (f7815a == null) {
            return;
        }
        File file = new File(f7815a);
        if (!file.exists()) {
            file.mkdirs();
        }
        ?? r12 = 0;
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;
        r12 = 0;
        try {
        } catch (IOException e8) {
            BydLogHelper.logE(e8.getMessage());
            r12 = r12;
        }
        try {
            try {
                BufferedWriter bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f7816b, true)));
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(":");
                    sb.append(str);
                    sb.append(BydAdbHelper.COMMAND_LINE_END);
                    bufferedWriter3.write(sb.toString());
                    bufferedWriter3.close();
                    r12 = sb;
                } catch (FileNotFoundException e9) {
                    e = e9;
                    bufferedWriter = bufferedWriter3;
                    BydLogHelper.logE(e.getMessage());
                    r12 = bufferedWriter;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                        r12 = bufferedWriter;
                    }
                } catch (IOException e10) {
                    e = e10;
                    bufferedWriter2 = bufferedWriter3;
                    BydLogHelper.logE(e.getMessage());
                    r12 = bufferedWriter2;
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                        r12 = bufferedWriter2;
                    }
                } catch (Throwable th) {
                    th = th;
                    r12 = bufferedWriter3;
                    if (r12 != 0) {
                        try {
                            r12.close();
                        } catch (IOException e11) {
                            BydLogHelper.logE(e11.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e12) {
                e = e12;
            } catch (IOException e13) {
                e = e13;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
