package c.a.d;

import android.os.Environment;
import android.util.Log;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: TipsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1634a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f1635b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1636c = false;

    /* JADX INFO: compiled from: TipsUtil.java */
    public class a extends SimpleFileVisitor<Path> {
        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
            if (iOException != null) {
                throw iOException;
            }
            Files.delete(path);
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            Files.delete(path);
            return FileVisitResult.CONTINUE;
        }
    }

    public static void a() {
        f1634a = Environment.getExternalStorageDirectory() + "/SmartChargeLogs/";
        f1635b = f1634a + "logapp_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINA).format(new Date()) + ".log";
        StringBuilder sb = new StringBuilder();
        sb.append(f1634a);
        sb.append("config.txt");
        f1636c = new File(sb.toString()).exists();
        File file = new File(f1634a);
        try {
            if (file.exists()) {
                Files.walkFileTree(Paths.get(file.getPath(), new String[0]), new a());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(String str) throws Throwable {
        Log.i("smart_charge", str);
        if (f1636c) {
            c(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0084 -> B:40:0x008b). Please report as a decompilation issue!!! */
    public static void c(String str) throws Throwable {
        String str2 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss SSS", Locale.CHINA).format(new Date());
        if (f1634a == null) {
            return;
        }
        File file = new File(f1634a);
        if (!file.exists()) {
            file.mkdirs();
        }
        ?? r2 = 0;
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;
        r2 = 0;
        try {
        } catch (IOException e) {
            Log.e("smart_charge", e.getMessage());
            r2 = r2;
        }
        try {
            try {
                BufferedWriter bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1635b, true)));
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(":");
                    sb.append(str);
                    sb.append(BydAdbHelper.COMMAND_LINE_END);
                    bufferedWriter3.write(sb.toString());
                    bufferedWriter3.close();
                    r2 = sb;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    bufferedWriter = bufferedWriter3;
                    Log.e("smart_charge", e.getMessage());
                    r2 = bufferedWriter;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                        r2 = bufferedWriter;
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedWriter2 = bufferedWriter3;
                    Log.e("smart_charge", e.getMessage());
                    r2 = bufferedWriter2;
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                        r2 = bufferedWriter2;
                    }
                } catch (Throwable th) {
                    th = th;
                    r2 = bufferedWriter3;
                    if (r2 != 0) {
                        try {
                            r2.close();
                        } catch (IOException e4) {
                            Log.e("smart_charge", e4.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
