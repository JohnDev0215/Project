package Y;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Iterator, B7.a {
    public final n[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f10588m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f10589n = true;

    public d(m mVar, n[] nVarArr) {
        this.l = nVarArr;
        nVarArr[0].b(mVar.d, Integer.bitCount(mVar.f10601a) * 2, 0);
        this.f10588m = 0;
        b();
    }

    public final void b() {
        int i7 = this.f10588m;
        n[] nVarArr = this.l;
        n nVar = nVarArr[i7];
        if (nVar.f10604n < nVar.f10603m) {
            return;
        }
        while (-1 < i7) {
            int iC = c(i7);
            if (iC == -1) {
                n nVar2 = nVarArr[i7];
                int i10 = nVar2.f10604n;
                Object[] objArr = nVar2.l;
                if (i10 < objArr.length) {
                    int length = objArr.length;
                    nVar2.f10604n = i10 + 1;
                    iC = c(i7);
                }
            }
            if (iC != -1) {
                this.f10588m = iC;
                return;
            }
            if (i7 > 0) {
                n nVar3 = nVarArr[i7 - 1];
                int i11 = nVar3.f10604n;
                int length2 = nVar3.l.length;
                nVar3.f10604n = i11 + 1;
            }
            nVarArr[i7].b(m.f10600e.d, 0, 0);
            i7--;
        }
        this.f10589n = false;
    }

    public final int c(int i7) {
        n[] nVarArr = this.l;
        n nVar = nVarArr[i7];
        int i10 = nVar.f10604n;
        if (i10 < nVar.f10603m) {
            return i7;
        }
        Object[] objArr = nVar.l;
        if (i10 >= objArr.length) {
            return -1;
        }
        int length = objArr.length;
        Object obj = objArr[i10];
        A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator>");
        m mVar = (m) obj;
        if (i7 == 6) {
            n nVar2 = nVarArr[i7 + 1];
            Object[] objArr2 = mVar.d;
            nVar2.b(objArr2, objArr2.length, 0);
        } else {
            nVarArr[i7 + 1].b(mVar.d, Integer.bitCount(mVar.f10601a) * 2, 0);
        }
        return c(i7 + 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f10589n;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!this.f10589n) {
            throw new NoSuchElementException();
        }
        Object next = this.l[this.f10588m].next();
        b();
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
