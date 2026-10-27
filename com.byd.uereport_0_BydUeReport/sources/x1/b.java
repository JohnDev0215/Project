package x1;

import android.content.Context;
import com.airbnb.lottie.network.FileExtension;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipInputStream;
import n0.d;
import o1.e;
import o1.f;
import o1.m;

/* JADX INFO: compiled from: NetworkFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9075a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f9076b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f9077c;

    public b(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        this.f9075a = applicationContext;
        this.f9076b = str;
        if (str2 == null) {
            this.f9077c = null;
        } else {
            this.f9077c = new a(applicationContext);
        }
    }

    public static m<e> e(Context context, String str, String str2) {
        return new b(context, str, str2).d();
    }

    public final e a() {
        d<FileExtension, InputStream> dVarA;
        a aVar = this.f9077c;
        if (aVar == null || (dVarA = aVar.a(this.f9076b)) == null) {
            return null;
        }
        FileExtension fileExtension = dVarA.f6506a;
        InputStream inputStream = dVarA.f6507b;
        m<e> mVarR = fileExtension == FileExtension.ZIP ? f.r(new ZipInputStream(inputStream), this.f9076b) : f.h(inputStream, this.f9076b);
        if (mVarR.b() != null) {
            return mVarR.b();
        }
        return null;
    }

    public final m<e> b() {
        try {
            return c();
        } catch (IOException e8) {
            return new m<>((Throwable) e8);
        }
    }

    public final m<e> c() throws IOException {
        a2.d.a("Fetching " + this.f9076b);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f9076b).openConnection();
        httpURLConnection.setRequestMethod("GET");
        try {
            httpURLConnection.connect();
            if (httpURLConnection.getErrorStream() == null && httpURLConnection.getResponseCode() == 200) {
                m<e> mVarG = g(httpURLConnection);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(mVarG.b() != null);
                a2.d.a(sb.toString());
                return mVarG;
            }
            return new m<>((Throwable) new IllegalArgumentException("Unable to fetch " + this.f9076b + ". Failed with " + httpURLConnection.getResponseCode() + BydAdbHelper.COMMAND_LINE_END + f(httpURLConnection)));
        } catch (Exception e8) {
            return new m<>((Throwable) e8);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public m<e> d() {
        e eVarA = a();
        if (eVarA != null) {
            return new m<>(eVarA);
        }
        a2.d.a("Animation for " + this.f9076b + " not found in cache. Fetching from network.");
        return b();
    }

    public final String f(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line);
                        sb.append('\n');
                    } else {
                        try {
                            break;
                        } catch (Exception unused) {
                        }
                    }
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused2) {
                    }
                }
            } catch (Exception e8) {
                throw e8;
            }
        }
        return sb.toString();
    }

    public final m<e> g(HttpURLConnection httpURLConnection) throws IOException {
        FileExtension fileExtension;
        m<e> mVarH;
        String contentType = httpURLConnection.getContentType();
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip")) {
            a2.d.a("Handling zip response.");
            fileExtension = FileExtension.ZIP;
            a aVar = this.f9077c;
            mVarH = aVar == null ? f.r(new ZipInputStream(httpURLConnection.getInputStream()), null) : f.r(new ZipInputStream(new FileInputStream(aVar.f(this.f9076b, httpURLConnection.getInputStream(), fileExtension))), this.f9076b);
        } else {
            a2.d.a("Received json response.");
            fileExtension = FileExtension.JSON;
            a aVar2 = this.f9077c;
            mVarH = aVar2 == null ? f.h(httpURLConnection.getInputStream(), null) : f.h(new FileInputStream(new File(aVar2.f(this.f9076b, httpURLConnection.getInputStream(), fileExtension).getAbsolutePath())), this.f9076b);
        }
        if (this.f9077c != null && mVarH.b() != null) {
            this.f9077c.e(this.f9076b, fileExtension);
        }
        return mVarH;
    }
}
