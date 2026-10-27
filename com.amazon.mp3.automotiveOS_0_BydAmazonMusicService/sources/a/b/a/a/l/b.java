package a.b.a.a.l;

import android.os.AsyncTask;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import kotlin.TypeCastException;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends AsyncTask<String, Integer, Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f83a;
    public final a.b.a.a.i.b b;
    public String c;
    public String d;
    public HashMap<String, String> e;
    public final int f;

    @JvmOverloads
    public b(Map<String, String> map, int i) {
        this.f = i;
        this.f83a = "HttpTask";
        this.b = a.b.a.a.i.b.d.a();
        this.e = new HashMap<>();
        this.e = (HashMap) map;
    }

    public /* synthetic */ b(Map map, int i, int i2) {
        this((i2 & 1) != 0 ? new HashMap() : null, i);
    }

    public final int a(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        if (a(responseCode)) {
            return -1;
        }
        if (responseCode == 200) {
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
                this.c = a(inputStream);
            } catch (IOException unused) {
                responseCode = 1;
            } catch (Throwable th) {
                inputStream.close();
                throw th;
            }
            inputStream.close();
        } else {
            this.b.a(this.f83a, "#### response code=" + responseCode);
        }
        return responseCode;
    }

    public final int a(HttpURLConnection httpURLConnection, String str) throws IOException {
        boolean z;
        httpURLConnection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        if (str != null) {
            try {
                z = StringsKt__StringsJVMKt.isBlank(str);
            } finally {
            }
        }
        if (!z) {
            Charset charsetForName = Charset.forName("utf-8");
            Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(charsetName)");
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = str.getBytes(charsetForName);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            dataOutputStream.write(bytes);
            dataOutputStream.flush();
        }
        CloseableKt.closeFinally(dataOutputStream, null);
        int responseCode = httpURLConnection.getResponseCode();
        if (a(responseCode)) {
            return -1;
        }
        this.d = httpURLConnection.getResponseMessage();
        if (responseCode == 200) {
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
                this.c = a(inputStream);
                this.b.a(this.f83a, "post response is: " + this.c);
            } catch (IOException unused) {
                return 1;
            } finally {
                inputStream.close();
            }
        }
        this.b.a(this.f83a, "#### response code=" + responseCode);
        return responseCode;
    }

    public final String a(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                String string = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "stringBuilder.toString()");
                return string;
            }
            sb.append(line);
        }
    }

    public final HttpURLConnection a(URL url, String str) throws ProtocolException {
        URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection());
        if (uRLConnection == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
        httpURLConnection.setInstanceFollowRedirects(true);
        HttpURLConnection.setFollowRedirects(true);
        httpURLConnection.setConnectTimeout(this.f);
        httpURLConnection.setReadTimeout(this.f);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("CharSet", "utf-8");
        httpURLConnection.setRequestMethod(str);
        for (Map.Entry<String, String> entry : this.e.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        return httpURLConnection;
    }

    public abstract void a(String str, int i);

    public final boolean a(int i) {
        return i != 200 && (i == 302 || i == 301 || i == 303 || i == 307 || i == 308);
    }

    @Override // android.os.AsyncTask
    public Integer doInBackground(String[] strArr) {
        int iValueOf;
        int iA;
        String[] strArr2 = strArr;
        String str = strArr2[0];
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = str.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        String str2 = strArr2[1];
        this.b.c(this.f83a, "serverUrl=" + str2);
        try {
            URL url = new URL(str2);
            HttpURLConnection httpURLConnectionA = null;
            try {
                try {
                    httpURLConnectionA = a(url, upperCase);
                    int iHashCode = upperCase.hashCode();
                    if (iHashCode == 70454) {
                        if (upperCase.equals(FirebasePerformance.HttpMethod.GET)) {
                            iA = a(httpURLConnectionA);
                            if (iA == -1) {
                                String headerField = httpURLConnectionA.getHeaderField(HttpHeader.LOCATION);
                                httpURLConnectionA.disconnect();
                                httpURLConnectionA = a(new URL(headerField), upperCase);
                                iA = a(httpURLConnectionA);
                            }
                            iValueOf = Integer.valueOf(iA);
                        }
                        iValueOf = 3;
                    } else if (iHashCode == 2461856 && upperCase.equals(FirebasePerformance.HttpMethod.POST)) {
                        String str3 = strArr2[2];
                        int iA2 = a(httpURLConnectionA, str3);
                        if (iA2 == -1) {
                            String headerField2 = httpURLConnectionA.getHeaderField(HttpHeader.LOCATION);
                            httpURLConnectionA.disconnect();
                            httpURLConnectionA = a(new URL(headerField2), upperCase);
                            iA = a(httpURLConnectionA, str3);
                            iValueOf = Integer.valueOf(iA);
                        } else {
                            iValueOf = Integer.valueOf(iA2);
                        }
                    } else {
                        iValueOf = 3;
                    }
                } catch (Exception unused) {
                    this.b.b(this.f83a, "Error connecting to backend ");
                    iValueOf = 1;
                    if (0 == 0) {
                        return 1;
                    }
                }
                httpURLConnectionA.disconnect();
                return iValueOf;
            } catch (Throwable th) {
                if (0 != 0) {
                    httpURLConnectionA.disconnect();
                }
                throw th;
            }
        } catch (MalformedURLException e) {
            String message = e.getMessage();
            if (message != null) {
                this.b.b(this.f83a, message);
            }
            return 2;
        }
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(Integer num) {
        int iIntValue = num.intValue();
        this.b.a(this.f83a, "response: " + iIntValue + " msg:" + this.d);
        if (this.c != null) {
            a.b.a.a.i.b bVar = this.b;
            String str = this.f83a;
            StringBuilder sb = new StringBuilder();
            sb.append("content: ");
            String str2 = this.c;
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(str2);
            bVar.a(str, sb.toString());
        }
        if (iIntValue < 100) {
            a(this.c, -1);
        } else {
            a(this.c, iIntValue);
        }
    }
}
