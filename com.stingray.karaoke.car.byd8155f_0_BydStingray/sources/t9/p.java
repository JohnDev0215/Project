package t9;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import w9.u;

/* JADX INFO: loaded from: classes.dex */
public final class p implements z9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final char f22575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22576b = 0;
    public final LinkedList c = new LinkedList();

    public p(char c) {
        this.f22575a = c;
    }

    @Override // z9.a
    public final char a() {
        return this.f22575a;
    }

    @Override // z9.a
    public final void b(u uVar, u uVar2, int i7) {
        z9.a aVar;
        LinkedList linkedList = this.c;
        Iterator it = linkedList.iterator();
        while (true) {
            if (!it.hasNext()) {
                aVar = (z9.a) linkedList.getFirst();
                break;
            } else {
                aVar = (z9.a) it.next();
                if (aVar.c() <= i7) {
                    break;
                }
            }
        }
        aVar.b(uVar, uVar2, i7);
    }

    @Override // z9.a
    public final int c() {
        return this.f22576b;
    }

    @Override // z9.a
    public final int d(d dVar, d dVar2) {
        z9.a aVar;
        int i7 = dVar.f22520g;
        LinkedList linkedList = this.c;
        Iterator it = linkedList.iterator();
        while (true) {
            if (!it.hasNext()) {
                aVar = (z9.a) linkedList.getFirst();
                break;
            }
            aVar = (z9.a) it.next();
            if (aVar.c() <= i7) {
                break;
            }
        }
        return aVar.d(dVar, dVar2);
    }

    @Override // z9.a
    public final char e() {
        return this.f22575a;
    }

    public final void f(z9.a aVar) {
        int iC = aVar.c();
        LinkedList linkedList = this.c;
        ListIterator listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            int iC2 = ((z9.a) listIterator.next()).c();
            if (iC > iC2) {
                listIterator.previous();
                listIterator.add(aVar);
                return;
            } else if (iC == iC2) {
                throw new IllegalArgumentException("Cannot add two delimiter processors for char '" + this.f22575a + "' and minimum length " + iC);
            }
        }
        linkedList.add(aVar);
        this.f22576b = iC;
    }
}
