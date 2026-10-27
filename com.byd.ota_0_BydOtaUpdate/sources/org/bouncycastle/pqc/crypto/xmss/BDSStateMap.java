package org.bouncycastle.pqc.crypto.xmss;

import g.a.e.b.e.f;
import g.a.e.b.e.l;
import g.a.e.b.e.q;
import g.a.e.b.e.t;
import g.a.f.d;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class BDSStateMap implements Serializable {
    private final Map<Integer, BDS> bdsState = new TreeMap();

    public BDSStateMap() {
    }

    public BDSStateMap(l lVar, long j, byte[] bArr, byte[] bArr2) {
        for (long j2 = 0; j2 < j; j2++) {
            a(lVar, j2, bArr, bArr2);
        }
    }

    public BDSStateMap(BDSStateMap bDSStateMap, l lVar, long j, byte[] bArr, byte[] bArr2) {
        for (Integer num : bDSStateMap.bdsState.keySet()) {
            this.bdsState.put(num, bDSStateMap.bdsState.get(num));
        }
        a(lVar, j, bArr, bArr2);
    }

    public final void a(l lVar, long j, byte[] bArr, byte[] bArr2) {
        q qVarG = lVar.g();
        int iD = qVarG.d();
        long j2 = t.j(j, iD);
        int i2 = t.i(j, iD);
        f fVar = (f) new f.b().h(j2).p(i2).l();
        int i3 = (1 << iD) - 1;
        if (i2 < i3) {
            if (get(0) == null || i2 == 0) {
                put(0, new BDS(qVarG, bArr, bArr2, fVar));
            }
            update(0, bArr, bArr2, fVar);
        }
        for (int i4 = 1; i4 < lVar.d(); i4++) {
            int i5 = t.i(j2, iD);
            j2 = t.j(j2, iD);
            f fVar2 = (f) new f.b().g(i4).h(j2).p(i5).l();
            if (i5 < i3 && t.m(j, iD, i4)) {
                if (get(i4) == null) {
                    put(i4, new BDS(lVar.g(), bArr, bArr2, fVar2));
                }
                update(i4, bArr, bArr2, fVar2);
            }
        }
    }

    public BDS get(int i2) {
        return this.bdsState.get(d.b(i2));
    }

    public boolean isEmpty() {
        return this.bdsState.isEmpty();
    }

    public void put(int i2, BDS bds) {
        this.bdsState.put(d.b(i2), bds);
    }

    public void setXMSS(q qVar) {
        Iterator<Integer> it = this.bdsState.keySet().iterator();
        while (it.hasNext()) {
            BDS bds = this.bdsState.get(it.next());
            bds.setXMSS(qVar);
            bds.validate();
        }
    }

    public BDS update(int i2, byte[] bArr, byte[] bArr2, f fVar) {
        return this.bdsState.put(d.b(i2), this.bdsState.get(d.b(i2)).getNextState(bArr, bArr2, fVar));
    }
}
