package defpackage;

import J.N;
import android.text.TextUtils;
import defpackage.b60;
import java.util.Arrays;
import org.chromium.chrome.R$string;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HistoryItem.java */
/* JADX INFO: loaded from: classes.dex */
public final class ev0 extends b60.k {
    public final GURL c;
    public final String d;
    public final boolean e;
    public final long f;
    public final long[] g;
    public Long h;
    public dv0 i;
    public Boolean j = null;

    public ev0(GURL gurl, String str, long j, long[] jArr, boolean z) {
        this.c = gurl;
        if (z) {
            str = xz.a.getString(R$string.android_history_blocked_site);
        } else if (TextUtils.isEmpty(str)) {
            str = gurl.j();
        }
        this.d = str;
        this.f = j;
        this.g = Arrays.copyOf(jArr, jArr.length);
        this.e = z;
    }

    @Override // b60.k
    public final long a() {
        if (this.h == null) {
            Long lValueOf = Long.valueOf(this.c.hashCode());
            this.h = lValueOf;
            this.h = Long.valueOf((lValueOf.longValue() << 32) + ((-1) & this.f));
        }
        return this.h.longValue();
    }

    @Override // b60.k
    public final long b() {
        return this.f;
    }

    public final boolean c() {
        if (this.j == null) {
            dv0 dv0Var = this.i;
            if ((dv0Var != null ? dv0Var.u : null) != null) {
                long j = (dv0Var != null ? dv0Var.u : null).a;
                this.j = Boolean.valueOf(j == 0 ? false : N.MTTi3vAA(j, this.c));
            }
        }
        Boolean bool = this.j;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
