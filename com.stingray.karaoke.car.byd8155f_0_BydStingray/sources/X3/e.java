package X3;

import N0.q;
import N1.C0420a;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f10037a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final O3.a f10038b;
    public final String c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f10039e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f10040f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f10041g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f10042h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final V3.d f10043i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f10044j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f10045k;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f10046m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f10047n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f10048p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final V3.a f10049q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f10050r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final V3.b f10051s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final List f10052t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f10053u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f10054v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0420a f10055w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final q f10056x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f10057y;

    public e(List list, O3.a aVar, String str, long j3, int i7, long j10, String str2, List list2, V3.d dVar, int i10, int i11, int i12, float f10, float f11, float f12, float f13, V3.a aVar2, io.sentry.internal.debugmeta.c cVar, List list3, int i13, V3.b bVar, boolean z6, C0420a c0420a, q qVar, int i14) {
        this.f10037a = list;
        this.f10038b = aVar;
        this.c = str;
        this.d = j3;
        this.f10039e = i7;
        this.f10040f = j10;
        this.f10041g = str2;
        this.f10042h = list2;
        this.f10043i = dVar;
        this.f10044j = i10;
        this.f10045k = i11;
        this.l = i12;
        this.f10046m = f10;
        this.f10047n = f11;
        this.o = f12;
        this.f10048p = f13;
        this.f10049q = aVar2;
        this.f10050r = cVar;
        this.f10052t = list3;
        this.f10053u = i13;
        this.f10051s = bVar;
        this.f10054v = z6;
        this.f10055w = c0420a;
        this.f10056x = qVar;
        this.f10057y = i14;
    }

    public final String a(String str) {
        int i7;
        StringBuilder sbI = M5.b.i(str);
        sbI.append(this.c);
        sbI.append("\n");
        O3.a aVar = this.f10038b;
        e eVar = (e) aVar.f5075i.b(this.f10040f);
        if (eVar != null) {
            sbI.append("\t\tParents: ");
            sbI.append(eVar.c);
            for (e eVar2 = (e) aVar.f5075i.b(eVar.f10040f); eVar2 != null; eVar2 = (e) aVar.f5075i.b(eVar2.f10040f)) {
                sbI.append("->");
                sbI.append(eVar2.c);
            }
            sbI.append(str);
            sbI.append("\n");
        }
        List list = this.f10042h;
        if (!list.isEmpty()) {
            sbI.append(str);
            sbI.append("\tMasks: ");
            sbI.append(list.size());
            sbI.append("\n");
        }
        int i10 = this.f10044j;
        if (i10 != 0 && (i7 = this.f10045k) != 0) {
            sbI.append(str);
            sbI.append("\tBackground: ");
            sbI.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(i10), Integer.valueOf(i7), Integer.valueOf(this.l)));
        }
        List list2 = this.f10037a;
        if (!list2.isEmpty()) {
            sbI.append(str);
            sbI.append("\tShapes:\n");
            for (Object obj : list2) {
                sbI.append(str);
                sbI.append("\t\t");
                sbI.append(obj);
                sbI.append("\n");
            }
        }
        return sbI.toString();
    }

    public final String toString() {
        return a("");
    }
}
