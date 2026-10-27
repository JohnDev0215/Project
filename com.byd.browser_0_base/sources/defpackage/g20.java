package defpackage;

import J.N;
import java.util.Locale;

/* JADX INFO: compiled from: CurrencyFormatter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g20 {
    public long a;

    public g20(String str, Locale locale) {
        this.a = N.MRlatcKK(this, str, locale.toString());
    }

    public final void a() {
        long j = this.a;
        if (j != 0) {
            N.MkBG391d(j, this);
            this.a = 0L;
        }
    }

    public final String b(String str) {
        return N.MgDia2D2(this.a, this, str);
    }
}
