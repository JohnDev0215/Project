package org.chromium.url;

import J.N;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.C1569tt;
import defpackage.It;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GURL {
    public String a;
    public boolean b;
    public Parsed c;

    public GURL(String str) {
        if (TextUtils.isEmpty(str)) {
            this.a = "";
            this.c = new Parsed(0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, false, null);
        } else {
            a();
            N.MWBVWQ0I(str, this);
        }
    }

    public static void a() {
        if (C1569tt.l.f()) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        C1569tt.l.c();
        if (ThreadUtils.j()) {
            It.e("Startup.Android.GURLEnsureMainDexInitialized", SystemClock.elapsedRealtime() - jElapsedRealtime);
        }
    }

    private void init(String str, boolean z, Parsed parsed) {
        this.a = str;
        this.b = z;
        this.c = parsed;
    }

    private long toNativeGURL() {
        return N.MnPIH$$1(this.a, this.b, this.c.a());
    }

    public String b() {
        Parsed parsed = this.c;
        int i = parsed.g;
        int i2 = parsed.h;
        return i2 <= 0 ? "" : this.a.substring(i, i2 + i);
    }

    public String c() {
        return (this.b || this.a.isEmpty()) ? this.a : "";
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

    public final int hashCode() {
        return this.a.hashCode();
    }

    public GURL() {
    }
}
