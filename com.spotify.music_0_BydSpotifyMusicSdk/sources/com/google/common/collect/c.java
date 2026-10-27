package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p.oq2;
import p.uw1;

/* JADX INFO: loaded from: classes.dex */
public final class c extends oq2 {
    public AbstractIterator$State c = AbstractIterator$State.e;
    public Object e;
    public final /* synthetic */ Iterator k;
    public final /* synthetic */ uw1 l;

    public c(Iterator it, uw1 uw1Var) {
        this.k = it;
        this.l = uw1Var;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final boolean hasNext() {
        AbstractIterator$State abstractIterator$State;
        Object next;
        AbstractIterator$State abstractIterator$State2 = this.c;
        AbstractIterator$State abstractIterator$State3 = AbstractIterator$State.l;
        if (abstractIterator$State2 == abstractIterator$State3) {
            throw new IllegalStateException();
        }
        int iOrdinal = abstractIterator$State2.ordinal();
        if (iOrdinal == 0) {
            return true;
        }
        if (iOrdinal == 2) {
            return false;
        }
        this.c = abstractIterator$State3;
        while (true) {
            Iterator it = this.k;
            boolean zHasNext = it.hasNext();
            abstractIterator$State = AbstractIterator$State.k;
            if (!zHasNext) {
                this.c = abstractIterator$State;
                next = null;
                break;
            }
            next = it.next();
            if (this.l.mo348apply(next)) {
                break;
            }
        }
        this.e = next;
        if (this.c == abstractIterator$State) {
            return false;
        }
        this.c = AbstractIterator$State.c;
        return true;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.c = AbstractIterator$State.e;
        Object obj = this.e;
        this.e = null;
        return obj;
    }
}
