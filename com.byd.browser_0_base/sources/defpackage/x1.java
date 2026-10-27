package defpackage;

import android.content.Context;
import defpackage.iq2;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AccountSelectionViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x1 {
    public static iq2.a a(Context context, String str, GURL gurl, Runnable runnable) {
        if (GURL.l(gurl)) {
            return null;
        }
        return new iq2.a(new un1(context, new yo1(context, 5, gurl, runnable)), gq2.g("<", str, ">"), gq2.g("</", str, ">"));
    }
}
