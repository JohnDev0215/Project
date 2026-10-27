package l2;

import java.util.ArrayList;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18484a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f18485b;
    public final TreeSet c = new TreeSet();
    public final ArrayList d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f18486e;

    public k(int i7, String str, o oVar) {
        this.f18484a = i7;
        this.f18485b = str;
        this.f18486e = oVar;
    }

    public final boolean a(long j3, long j10) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.d;
            if (i7 >= arrayList.size()) {
                return false;
            }
            j jVar = (j) arrayList.get(i7);
            long j11 = jVar.f18483b;
            long j12 = jVar.f18482a;
            if (j11 == -1) {
                if (j3 >= j12) {
                    return true;
                }
            } else if (j10 != -1 && j12 <= j3 && j3 + j10 <= j12 + j11) {
                return true;
            }
            i7++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return this.f18484a == kVar.f18484a && this.f18485b.equals(kVar.f18485b) && this.c.equals(kVar.c) && this.f18486e.equals(kVar.f18486e);
    }

    public final int hashCode() {
        return this.f18486e.hashCode() + M5.b.c(this.f18485b, this.f18484a * 31, 31);
    }
}
