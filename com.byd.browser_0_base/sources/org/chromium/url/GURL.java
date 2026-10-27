package org.chromium.url;

import J.N;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import defpackage.C0175b;
import defpackage.oj2;
import defpackage.qj3;
import defpackage.xz;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class GURL {
    public String a;
    public boolean b;
    public Parsed c;

    public static class a extends RuntimeException {
    }

    public static class b {
        public static final GURL a = new GURL("");
    }

    @CalledByNative
    public GURL() {
    }

    public GURL(String str) {
        if (TextUtils.isEmpty(str)) {
            this.a = "";
            this.c = new Parsed(0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, false, null);
        } else {
            c();
            N.MWBVWQ0I(str, this);
        }
    }

    public static GURL a(String str) {
        try {
            return b(str);
        } catch (a unused) {
            return new GURL(str.endsWith(Character.toString((char) 0)) ? "" : str.split(Character.toString((char) 0))[r1.length - 1]);
        } catch (Exception e) {
            Log.w("cr_GURL", "Exception while deserializing a GURL: " + str, e);
            return emptyGURL();
        }
    }

    public static GURL b(String str) {
        if (TextUtils.isEmpty(str)) {
            return emptyGURL();
        }
        String[] strArrSplit = str.split(Character.toString((char) 0));
        String str2 = strArrSplit[0];
        if (str.length() != str2.length() + Integer.parseInt(str2) + 1) {
            throw new IllegalArgumentException("Serialized GURL had the wrong length.");
        }
        String str3 = str.endsWith(Character.toString((char) 0)) ? "" : strArrSplit[strArrSplit.length - 1];
        if (Integer.parseInt(strArrSplit[1]) != 1) {
            throw new a();
        }
        boolean z = Boolean.parseBoolean(strArrSplit[2]);
        Parsed parsedA = Parsed.a(strArrSplit, 3);
        GURL gurl = new GURL();
        gurl.init(str3, z, parsedA);
        return gurl;
    }

    public static void c() {
        org.chromium.base.library_loader.a aVar = org.chromium.base.library_loader.a.l;
        if (aVar.d()) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (aVar.h) {
            aVar.e(xz.a.getApplicationInfo(), false);
            aVar.c();
        }
        if (ThreadUtils.g()) {
            oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Startup.Android.GURLEnsureMainDexInitialized");
        }
    }

    @CalledByNative
    public static GURL emptyGURL() {
        return b.a;
    }

    public static boolean l(GURL gurl) {
        return gurl == null || gurl.k() || !gurl.b;
    }

    public final String d(int i, int i2) {
        return i2 <= 0 ? "" : this.a.substring(i, i2 + i);
    }

    public final String e() {
        Parsed parsed = this.c;
        return d(parsed.g, parsed.h);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GURL) {
            return this.a.equals(((GURL) obj).a);
        }
        return false;
    }

    public GURL f() {
        GURL gurl = new GURL();
        N.MNBd3mFA(this.a, this.b, this.c.c(), gurl);
        return gurl;
    }

    public final String g() {
        Parsed parsed = this.c;
        return d(parsed.k, parsed.l);
    }

    public final String h() {
        Parsed parsed = this.c;
        return d(parsed.i, parsed.j);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String i() {
        Parsed parsed = this.c;
        return d(parsed.a, parsed.b);
    }

    @CalledByNative
    public final void init(String str, boolean z, Parsed parsed) {
        this.a = str;
        this.b = z;
        this.c = parsed;
    }

    public final String j() {
        return (this.b || this.a.isEmpty()) ? this.a : "";
    }

    public final boolean k() {
        return this.a.isEmpty();
    }

    public final String m() {
        String str = "1\u0000" + this.b + (char) 0 + this.c.b() + (char) 0 + this.a;
        return C0175b.k(Integer.toString(str.length()), "\u0000", str);
    }

    public final qj3 n() {
        qj3 qj3Var = new qj3(0);
        qj3Var.b = (TextUtils.isEmpty(this.a) || this.a.length() > 2097152 || !this.b) ? "" : this.a;
        return qj3Var;
    }

    @CalledByNative
    public final long toNativeGURL() {
        return N.MnPIH$$1(this.a, this.b, this.c.c());
    }
}
