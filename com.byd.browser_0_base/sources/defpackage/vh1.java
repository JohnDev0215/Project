package defpackage;

import android.util.Log;
import defpackage.wh1;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: compiled from: MinidumpUploadCallable.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vh1 implements Callable<Integer> {
    public final File a;
    public final File b;
    public final e10 c;
    public final wh1 d;

    public vh1(File file, File file2, e10 e10Var) {
        wh1 wh1Var = new wh1();
        this.a = file;
        this.b = file2;
        this.d = wh1Var;
        this.c = e10Var;
    }

    public final void a(String str, String str2) throws IOException {
        FileWriter fileWriter = new FileWriter(this.b, true);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        sb.append(",");
        sb.append(str2);
        if (str != null) {
            sb.append(",");
            sb.append(str);
        }
        sb.append('\n');
        try {
            fileWriter.write(sb.toString());
        } finally {
            fileWriter.close();
        }
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Integer call() {
        wh1.a aVar;
        HttpURLConnection httpURLConnection;
        wh1.a aVar2;
        e10 e10Var = this.c;
        boolean zE = e10Var.e();
        File file = this.a;
        if (zE) {
            Log.i("cr_MDUploadCallable", "Minidump upload enabled for tests, skipping other checks.");
        } else {
            Pattern pattern = d10.b;
            if (!file.getName().contains(".forced")) {
                if (!e10Var.a()) {
                    Log.i("cr_MDUploadCallable", "Minidump upload is not permitted. Marking file as skipped for cleanup to prevent future uploads.");
                    d10.i(file, ".skipped");
                    return 2;
                }
                if (!e10Var.f()) {
                    Log.i("cr_MDUploadCallable", "Minidump upload skipped due to sampling.  Marking file as skipped for cleanup to prevent future uploads.");
                    d10.i(file, ".skipped");
                    return 3;
                }
                if (!e10Var.d()) {
                    Log.i("cr_MDUploadCallable", "Minidump cannot currently be uploaded due to network constraints.");
                    return 1;
                }
            }
        }
        wh1 wh1Var = this.d;
        wh1Var.getClass();
        if (file != null) {
            try {
                if (file.exists()) {
                    ((yv0) wh1Var.a).getClass();
                    try {
                        httpURLConnection = (HttpURLConnection) new URL("https://clients2.google.com/cr/report").openConnection();
                    } catch (IOException unused) {
                        httpURLConnection = null;
                    }
                    if (httpURLConnection == null) {
                        aVar = new wh1.a(-1, "Failed to create connection");
                    } else {
                        wh1.a(httpURLConnection, wh1.b(file));
                        try {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            try {
                                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(httpURLConnection.getOutputStream());
                                try {
                                    byte[] bArr = new byte[4096];
                                    for (int i = fileInputStream.read(bArr); i >= 0; i = fileInputStream.read(bArr)) {
                                        gZIPOutputStream.write(bArr, 0, i);
                                    }
                                    fileInputStream.close();
                                    gZIPOutputStream.close();
                                    int responseCode = httpURLConnection.getResponseCode();
                                    if (responseCode == 200 || responseCode == 201 || responseCode == 202) {
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        InputStream inputStream = httpURLConnection.getInputStream();
                                        byte[] bArr2 = new byte[4096];
                                        for (int i2 = inputStream.read(bArr2); i2 >= 0; i2 = inputStream.read(bArr2)) {
                                            byteArrayOutputStream.write(bArr2, 0, i2);
                                        }
                                        inputStream.close();
                                        byteArrayOutputStream.close();
                                        String string = byteArrayOutputStream.size() == 0 ? null : byteArrayOutputStream.toString();
                                        if (string == null) {
                                            string = "unknown";
                                        }
                                        aVar2 = new wh1.a(0, string);
                                        gZIPOutputStream.close();
                                        fileInputStream.close();
                                    } else {
                                        aVar2 = new wh1.a(responseCode, httpURLConnection.getResponseMessage());
                                        gZIPOutputStream.close();
                                        fileInputStream.close();
                                    }
                                    aVar = aVar2;
                                } finally {
                                    try {
                                        gZIPOutputStream.close();
                                    } catch (Throwable unused2) {
                                    }
                                }
                            } catch (Throwable th) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable unused3) {
                                }
                                throw th;
                            }
                        } finally {
                            httpURLConnection.disconnect();
                        }
                    }
                } else {
                    aVar = new wh1.a(-1, "Crash report does not exist");
                }
            } catch (IOException | RuntimeException e) {
                aVar = new wh1.a(-1, e.toString());
            }
        } else {
            aVar = new wh1.a(-1, "Crash report does not exist");
        }
        int i3 = aVar.a;
        boolean z = i3 == 0;
        String str = aVar.b;
        if (!z) {
            if (i3 > 0) {
                Log.i("cr_MDUploadCallable", String.format(Locale.US, "Failed to upload %s with code: %d (%s).", file.getName(), Integer.valueOf(i3), str));
            } else {
                Log.e("cr_MDUploadCallable", "Local error while uploading " + file.getName() + ": " + str);
            }
            return 1;
        }
        String name = file.getName();
        Log.i("cr_MDUploadCallable", "Minidump " + name + " uploaded successfully, id: " + str);
        d10.i(file, ".up");
        try {
            Matcher matcher = d10.b.matcher(name);
            a(matcher.find() ? matcher.group(1) : null, str);
        } catch (IOException unused4) {
            Log.e("cr_MDUploadCallable", "Fail to write uploaded entry to log file");
        }
        return 0;
    }
}
