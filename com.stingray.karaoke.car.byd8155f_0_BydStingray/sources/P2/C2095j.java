package p2;

import e2.C1208p;

/* JADX INFO: renamed from: p2.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2095j extends Exception {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f20713m;

    /* JADX WARN: Illegal instructions before constructor call */
    public C2095j(int i7, int i10, int i11, int i12, C1208p c1208p, boolean z6, RuntimeException runtimeException) {
        StringBuilder sbL = M5.b.l("AudioTrack init failed ", " Config(", i7, i10, ", ");
        sbL.append(i11);
        sbL.append(", ");
        sbL.append(i12);
        sbL.append(") ");
        sbL.append(c1208p);
        sbL.append(z6 ? " (recoverable)" : "");
        super(sbL.toString(), runtimeException);
        this.l = i7;
        this.f20713m = z6;
    }
}
