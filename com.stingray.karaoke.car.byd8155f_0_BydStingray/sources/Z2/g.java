package Z2;

import Y2.i;

/* JADX INFO: loaded from: classes.dex */
public final class g extends i implements Comparable {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f10879v;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        g gVar = (g) obj;
        if (e(4) == gVar.e(4)) {
            long j3 = this.f18957r - gVar.f18957r;
            if (j3 == 0) {
                j3 = this.f10879v - gVar.f10879v;
                if (j3 == 0) {
                    return 0;
                }
            }
            if (j3 <= 0) {
                return -1;
            }
        } else if (!e(4)) {
            return -1;
        }
        return 1;
    }
}
