package y5;

import java.util.HashMap;

/* JADX INFO: renamed from: y5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2744b extends AbstractC2745c {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static HashMap f24120u;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f24121q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f24122r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f24123s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f24124t;

    public C2744b(long j3, long j10, int i7, int i10, int i11, int i12) {
        super(j3, j10);
        this.f24121q = i7 & 15;
        this.f24122r = i10 & 15;
        this.f24123s = i11 & 255;
        this.f24124t = i12 & 255;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f  */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int compareTo(java.lang.Object r6) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y5.C2744b.compareTo(java.lang.Object):int");
    }
}
