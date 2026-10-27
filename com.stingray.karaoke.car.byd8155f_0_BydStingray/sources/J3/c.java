package J3;

import Q8.o;
import android.graphics.Bitmap;
import c9.u;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H0.b f3069a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f3070b;
    public final Date c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Date f3071e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f3072f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Date f3073g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f3074h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f3075i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f3076j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f3077k;

    public c(H0.b bVar, b bVar2) {
        int i7;
        this.f3069a = bVar;
        this.f3070b = bVar2;
        this.f3077k = -1;
        if (bVar2 != null) {
            this.f3074h = bVar2.c;
            this.f3075i = bVar2.d;
            u uVar = bVar2.f3068f;
            int size = uVar.size();
            for (int i10 = 0; i10 < size; i10++) {
                String strL = uVar.l(i10);
                if (o.t0(strL, "Date", true)) {
                    String strD = uVar.d("Date");
                    this.c = strD != null ? h9.b.a(strD) : null;
                    this.d = uVar.s(i10);
                } else if (o.t0(strL, "Expires", true)) {
                    String strD2 = uVar.d("Expires");
                    this.f3073g = strD2 != null ? h9.b.a(strD2) : null;
                } else if (o.t0(strL, "Last-Modified", true)) {
                    String strD3 = uVar.d("Last-Modified");
                    this.f3071e = strD3 != null ? h9.b.a(strD3) : null;
                    this.f3072f = uVar.s(i10);
                } else if (o.t0(strL, "ETag", true)) {
                    this.f3076j = uVar.s(i10);
                } else if (o.t0(strL, "Age", true)) {
                    String strS = uVar.s(i10);
                    Bitmap.Config config = N3.d.f4575a;
                    Long lB0 = o.B0(strS);
                    if (lB0 != null) {
                        long jLongValue = lB0.longValue();
                        i7 = jLongValue > 2147483647L ? Integer.MAX_VALUE : jLongValue < 0 ? 0 : (int) jLongValue;
                    } else {
                        i7 = -1;
                    }
                    this.f3077k = i7;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x015c  */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, m7.f] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final J3.d a() {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: J3.c.a():J3.d");
    }
}
