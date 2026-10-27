package com.airbnb.lottie.u;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;
import com.airbnb.lottie.w.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f904b;

    b(Context context, String str) {
        this.f903a = context.getApplicationContext();
        this.f904b = str;
    }

    private static String b(String str, a aVar, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("lottie_cache_");
        sb.append(str.replaceAll("\\W+", ""));
        sb.append(z ? aVar.a() : aVar.d);
        return sb.toString();
    }

    @Nullable
    private File c(String str) {
        File file = new File(this.f903a.getCacheDir(), b(str, a.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(this.f903a.getCacheDir(), b(str, a.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        return null;
    }

    @Nullable
    @WorkerThread
    Pair<a, InputStream> a() {
        try {
            File fileC = c(this.f904b);
            if (fileC == null) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(fileC);
            a aVar = fileC.getAbsolutePath().endsWith(".zip") ? a.ZIP : a.JSON;
            d.a("Cache hit for " + this.f904b + " at " + fileC.getAbsolutePath());
            return new Pair<>(aVar, fileInputStream);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    void d(a aVar) {
        File file = new File(this.f903a.getCacheDir(), b(this.f904b, aVar, true));
        File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean zRenameTo = file.renameTo(file2);
        d.a("Copying temp file to real file (" + file2 + ")");
        if (zRenameTo) {
            return;
        }
        d.b("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + ".");
    }

    File e(InputStream inputStream, a aVar) throws IOException {
        File file = new File(this.f903a.getCacheDir(), b(this.f904b, aVar, true));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        fileOutputStream.flush();
                        return file;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
            } finally {
                fileOutputStream.close();
            }
        } finally {
            inputStream.close();
        }
    }
}
