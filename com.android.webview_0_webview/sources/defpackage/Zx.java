package defpackage;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Zx {
    public final InterfaceC0633cy a = new C0688dy();

    public final void a(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", String.format("multipart/form-data; boundary=%s", str));
    }

    public final String b(File file) {
        FileReader fileReader = new FileReader(file);
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String line = bufferedReader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    throw new RuntimeException("File does not have a MIME boundary");
                }
                String strTrim = line.trim();
                if (!strTrim.startsWith("--") || strTrim.length() < 10) {
                    throw new RuntimeException("File does not have a MIME boundary");
                }
                if (strTrim.matches("^[a-zA-Z0-9-]*$")) {
                    String strSubstring = strTrim.substring(2);
                    bufferedReader.close();
                    fileReader.close();
                    return strSubstring;
                }
                throw new RuntimeException("File has an illegal MIME boundary: " + strTrim);
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileReader.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public final void c(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        int i = inputStream.read(bArr);
        while (i >= 0) {
            outputStream.write(bArr, 0, i);
            i = inputStream.read(bArr);
        }
        inputStream.close();
        outputStream.close();
    }

    public Yx d(File file) {
        HttpURLConnection httpURLConnection;
        if (file != null) {
            try {
                try {
                    if (file.exists()) {
                        Objects.requireNonNull((C0688dy) this.a);
                        String string = null;
                        try {
                            httpURLConnection = (HttpURLConnection) new URL("https://clients2.google.com/cr/report").openConnection();
                        } catch (IOException unused) {
                            httpURLConnection = null;
                        }
                        if (httpURLConnection == null) {
                            return Yx.a("Failed to create connection");
                        }
                        a(httpURLConnection, b(file));
                        try {
                            InputStream fileInputStream = new FileInputStream(file);
                            try {
                                OutputStream gZIPOutputStream = new GZIPOutputStream(httpURLConnection.getOutputStream());
                                try {
                                    c(fileInputStream, gZIPOutputStream);
                                    int responseCode = httpURLConnection.getResponseCode();
                                    if (!(responseCode == 200 || responseCode == 201 || responseCode == 202)) {
                                        Yx yx = new Yx(responseCode, httpURLConnection.getResponseMessage());
                                        gZIPOutputStream.close();
                                        fileInputStream.close();
                                        return yx;
                                    }
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    c(httpURLConnection.getInputStream(), byteArrayOutputStream);
                                    if (byteArrayOutputStream.size() != 0) {
                                        string = byteArrayOutputStream.toString();
                                    }
                                    if (string == null) {
                                        string = "unknown";
                                    }
                                    Yx yx2 = new Yx(0, string);
                                    gZIPOutputStream.close();
                                    fileInputStream.close();
                                    return yx2;
                                } finally {
                                }
                            } finally {
                            }
                        } finally {
                            httpURLConnection.disconnect();
                        }
                    }
                } catch (RuntimeException e) {
                    e = e;
                    return Yx.a(e.toString());
                }
            } catch (IOException e2) {
                e = e2;
                return Yx.a(e.toString());
            }
        }
        return Yx.a("Crash report does not exist");
    }
}
