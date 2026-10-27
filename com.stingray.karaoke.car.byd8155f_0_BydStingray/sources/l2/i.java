package l2;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public abstract class i implements Comparable {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f18478m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f18479n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final File f18480p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f18481q;

    public i(String str, long j3, long j10, long j11, File file) {
        this.l = str;
        this.f18478m = j3;
        this.f18479n = j10;
        this.o = file != null;
        this.f18480p = file;
        this.f18481q = j11;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(i iVar) {
        String str = iVar.l;
        String str2 = this.l;
        if (!str2.equals(str)) {
            return str2.compareTo(iVar.l);
        }
        long j3 = this.f18478m - iVar.f18478m;
        if (j3 == 0) {
            return 0;
        }
        return j3 < 0 ? -1 : 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.f18478m);
        sb.append(", ");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f18479n, "]", sb);
    }
}
