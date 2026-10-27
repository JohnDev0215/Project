package defpackage;

import android.text.TextUtils;
import android.util.Log;
import defpackage.yk3;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/* JADX INFO: compiled from: VariationsSeedFetcher.java */
/* JADX INFO: loaded from: classes3.dex */
public final class al3 {
    public static final Object b;
    public static al3 c;
    public final i83 a = new i83(20);

    /* JADX INFO: compiled from: VariationsSeedFetcher.java */
    public static class a {
        public int a;
        public b b;
    }

    /* JADX INFO: compiled from: VariationsSeedFetcher.java */
    public static class b {
        public String a;
        public String b;
        public long c;
        public boolean d;
        public byte[] e;

        public static byte[] a(byte[] bArr, byte[] bArr2, boolean z) throws yk3.a {
            if (z) {
                try {
                    bArr = yk3.d(bArr);
                } catch (IOException e) {
                    Log.w("cr_VariationsSeedFetch", "Failed to resolve delta patch.", e);
                    throw new yk3.a("Error resolving delta patch");
                }
            }
            byte[] bArrA = yk3.a(bArr2, bArr);
            bl3.O(bArrA);
            return bArrA;
        }

        public final String toString() {
            return super.toString();
        }
    }

    static {
        new yl1("chrome_variations_android");
        b = new Object();
    }

    public static ArrayList b() {
        ArrayList arrayList = new ArrayList();
        if (iv.e().g("enable-finch-seed-delta-compression")) {
            arrayList.add("x-bm");
        }
        arrayList.add("gzip");
        return arrayList;
    }

    public static byte[] c(HttpURLConnection httpURLConnection) throws Throwable {
        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        inputStream.close();
                        return byteArray;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public static HttpURLConnection d(String str, String str2, String str3) {
        String strF = iv.e().f("variations-server-url");
        if (TextUtils.isEmpty(strF)) {
            strF = "https://clientservices.googleapis.com/chrome-variations/seed";
        }
        String strF2 = gq2.f(gq2.f(strF, "?osname="), "android");
        if (str != null && !str.isEmpty()) {
            strF2 = C0175b.k(strF2, "&restrict=", str);
        }
        if (str2 != null && !str2.isEmpty()) {
            strF2 = C0175b.k(strF2, "&milestone=", str2);
        }
        String strF3 = iv.e().f("fake-variations-channel");
        if (strF3 != null) {
            str3 = strF3;
        }
        if (str3 != null && !str3.isEmpty()) {
            strF2 = C0175b.k(strF2, "&channel=", str3);
        }
        return (HttpURLConnection) new URL(strF2).openConnection();
    }

    public static void e(int i) {
        oj2.a0(i, "Variations.FirstRun.SeedFetchResult");
    }

    public static void f(boolean z, boolean z2) {
        if (z && z2) {
            oj2.U(0, 4, "Variations.FirstRun.DeltaCompression");
            return;
        }
        if (z && !z2) {
            oj2.U(1, 4, "Variations.FirstRun.DeltaCompression");
        } else if (z || !z2) {
            oj2.U(3, 4, "Variations.FirstRun.DeltaCompression");
        } else {
            oj2.U(2, 4, "Variations.FirstRun.DeltaCompression");
        }
    }

    public static void g(long j) {
        Log.i("cr_VariationsSeedFetch", "Fetched first run seed in " + j + " ms");
        oj2.b0(j, "Variations.FirstRun.SeedFetchTime");
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x012e, code lost:
    
        if (r3 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0131, code lost:
    
        if (r3 == null) goto L70;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final al3.a a(java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.al3.a(java.lang.String, java.lang.String, java.lang.String):al3$a");
    }
}
