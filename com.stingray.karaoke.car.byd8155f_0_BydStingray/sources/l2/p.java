package l2;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        i iVar = (i) obj;
        i iVar2 = (i) obj2;
        long j3 = iVar.f18481q;
        long j10 = iVar2.f18481q;
        return j3 - j10 == 0 ? iVar.compareTo(iVar2) : j3 < j10 ? -1 : 1;
    }
}
