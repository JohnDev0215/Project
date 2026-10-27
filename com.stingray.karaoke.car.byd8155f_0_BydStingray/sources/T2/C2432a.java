package t2;

import P2.h;
import R2.c;
import e2.C1208p;
import l9.d;

/* JADX INFO: renamed from: t2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2432a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2432a f22444a = new C2432a();

    public final d a(C1208p c1208p) {
        String str = c1208p.f14316n;
        if (str != null) {
            switch (str) {
                case "application/vnd.dvb.ait":
                    return new L2.b(0);
                case "application/x-icy":
                    return new O2.a();
                case "application/id3":
                    return new h(null);
                case "application/x-emsg":
                    return new L2.b(1);
                case "application/x-scte35":
                    return new c();
            }
        }
        throw new IllegalArgumentException(M5.b.g("Attempted to create decoder for unsupported MIME type: ", str));
    }

    public final boolean b(C1208p c1208p) {
        String str = c1208p.f14316n;
        return "application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-icy".equals(str) || "application/vnd.dvb.ait".equals(str);
    }
}
