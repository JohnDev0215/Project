package defpackage;

import defpackage.b60;
import java.util.Comparator;

/* JADX INFO: compiled from: DateDividedAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c60 implements Comparator<b60.k> {
    public final /* synthetic */ b60.i e;

    public c60(b60.i iVar) {
        this.e = iVar;
    }

    @Override // java.util.Comparator
    public final int compare(b60.k kVar, b60.k kVar2) {
        b60.k kVar3 = kVar;
        b60.k kVar4 = kVar2;
        this.e.getClass();
        if (!kVar3.b) {
            if (!kVar4.b) {
                long jB = kVar3.b() - kVar4.b();
                if (jB <= 0) {
                    if (jB == 0) {
                        return 0;
                    }
                }
            }
            return 1;
        }
        return -1;
    }
}
