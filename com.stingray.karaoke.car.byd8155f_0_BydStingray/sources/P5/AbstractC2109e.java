package p5;

import p5.C2105a;
import p5.C2106b;

/* JADX INFO: renamed from: p5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2109e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f20906a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2105a.C0001a f20907b;
    public static final C2106b.a c;
    public static final C2107c d;

    static {
        boolean z6;
        try {
            Class.forName("java.sql.Date");
            z6 = true;
        } catch (ClassNotFoundException unused) {
            z6 = false;
        }
        f20906a = z6;
        if (z6) {
            f20907b = C2105a.f20900b;
            c = C2106b.f20902b;
            d = C2108d.f20904b;
        } else {
            f20907b = null;
            c = null;
            d = null;
        }
    }
}
