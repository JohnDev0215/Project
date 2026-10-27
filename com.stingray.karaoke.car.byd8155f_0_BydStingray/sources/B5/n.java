package B5;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes.dex */
public final class n implements Closeable {
    public final HttpURLConnection l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InputStream f726m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final OutputStream f727n;
    public final /* synthetic */ int o;

    public n(HttpURLConnection httpURLConnection, InputStream inputStream, OutputStream outputStream, int i7) {
        this.o = i7;
        this.l = httpURLConnection;
        this.f726m = inputStream;
        this.f727n = outputStream;
    }

    public final void b() {
        this.l.disconnect();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        String string;
        switch (this.o) {
            case 0:
                OutputStream outputStream = this.f727n;
                HttpURLConnection httpURLConnection = this.l;
                try {
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode >= 300) {
                        InputStream errorStream = null;
                        try {
                            try {
                                try {
                                    errorStream = httpURLConnection.getInputStream();
                                } catch (IOException e10) {
                                    string = "Could not read response body for rejected message: " + e10.toString();
                                    if (errorStream != null) {
                                    }
                                    throw new o(httpURLConnection.getResponseMessage(), responseCode, string);
                                }
                                break;
                            } catch (IOException unused) {
                                errorStream = httpURLConnection.getErrorStream();
                            }
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    string = sb.toString();
                                    throw new o(httpURLConnection.getResponseMessage(), responseCode, string);
                                }
                                sb.append(line);
                                throw new o(httpURLConnection.getResponseMessage(), responseCode, string);
                            }
                        } finally {
                            if (errorStream != null) {
                                errorStream.close();
                            }
                        }
                    }
                    return;
                } finally {
                    b();
                    outputStream.close();
                }
            default:
                b();
                this.f726m.close();
                return;
        }
    }
}
