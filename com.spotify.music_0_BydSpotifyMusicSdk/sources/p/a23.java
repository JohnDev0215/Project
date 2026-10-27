package p;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public final class a23 {
    public static final tz2 d = new tz2("PackMetadataManager", 0);
    public final com.google.android.play.core.assetpacks.c a;
    public final b23 b;
    public final pz2 c;

    public a23(com.google.android.play.core.assetpacks.c cVar, b23 b23Var, pz2 pz2Var) {
        this.a = cVar;
        this.b = b23Var;
        this.c = pz2Var;
    }

    public final String a(String str) {
        if (this.c.a()) {
            com.google.android.play.core.assetpacks.c cVar = this.a;
            cVar.getClass();
            try {
                if (cVar.l(str) != null) {
                    int iA = this.b.a();
                    File file = new File(new File(cVar.i(iA, com.google.android.play.core.assetpacks.c.b(new File(new File(cVar.d(), str), String.valueOf((int) com.google.android.play.core.assetpacks.c.b(new File(cVar.d(), str), true))), true), str), "_metadata"), "properties.dat");
                    try {
                        if (!file.exists()) {
                            return String.valueOf(iA);
                        }
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            Properties properties = new Properties();
                            properties.load(fileInputStream);
                            fileInputStream.close();
                            String property = properties.getProperty("moduleVersionTag");
                            return property == null ? String.valueOf(iA) : property;
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable unused) {
                            }
                            throw th;
                        }
                    } catch (IOException unused2) {
                        d.c("Failed to read pack version tag for pack %s", str);
                    }
                }
            } catch (IOException unused3) {
            }
        }
        return "";
    }

    public final void b(String str, int i, long j, String str2) throws IOException {
        if (str2 == null || str2.isEmpty()) {
            str2 = String.valueOf(i);
        }
        Properties properties = new Properties();
        properties.put("moduleVersionTag", str2);
        com.google.android.play.core.assetpacks.c cVar = this.a;
        cVar.getClass();
        File file = new File(new File(cVar.i(i, j, str), "_metadata"), "properties.dat");
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
