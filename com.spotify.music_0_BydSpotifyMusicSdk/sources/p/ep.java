package p;

import android.graphics.Bitmap;
import java.util.Date;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.internal.http.DatesKt;

/* JADX INFO: loaded from: classes.dex */
public final class ep {
    public final Request a;
    public final coil.network.a b;
    public final Date c;
    public final String d;
    public final Date e;
    public final String f;
    public final Date g;
    public final long h;
    public final long i;
    public final String j;
    public final int k;

    public ep(Request request, coil.network.a aVar) {
        int i;
        this.a = request;
        this.b = aVar;
        this.k = -1;
        if (aVar != null) {
            this.h = aVar.c;
            this.i = aVar.d;
            Headers headers = aVar.f;
            int size = headers.size();
            for (int i2 = 0; i2 < size; i2++) {
                String strB = headers.b(i2);
                if (ng2.b0(strB, "Date", true)) {
                    String strA = headers.a("Date");
                    this.c = strA != null ? DatesKt.a(strA) : null;
                    this.d = headers.d(i2);
                } else if (ng2.b0(strB, "Expires", true)) {
                    String strA2 = headers.a("Expires");
                    this.g = strA2 != null ? DatesKt.a(strA2) : null;
                } else if (ng2.b0(strB, "Last-Modified", true)) {
                    String strA3 = headers.a("Last-Modified");
                    this.e = strA3 != null ? DatesKt.a(strA3) : null;
                    this.f = headers.d(i2);
                } else if (ng2.b0(strB, "ETag", true)) {
                    this.j = headers.d(i2);
                } else if (ng2.b0(strB, "Age", true)) {
                    String strD = headers.d(i2);
                    Bitmap.Config[] configArr = i.a;
                    Long lZ = mg2.Z(strD);
                    if (lZ != null) {
                        long jLongValue = lZ.longValue();
                        i = jLongValue > 2147483647L ? Integer.MAX_VALUE : jLongValue < 0 ? 0 : (int) jLongValue;
                    } else {
                        i = -1;
                    }
                    this.k = i;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.fp a() {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ep.a():p.fp");
    }
}
