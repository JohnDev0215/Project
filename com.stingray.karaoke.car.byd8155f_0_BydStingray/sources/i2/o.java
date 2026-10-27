package i2;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Comparable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f15715m = -9223372036854775807L;
    public final ArrayList l = new ArrayList();

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f15715m, ((o) obj).f15715m);
    }
}
