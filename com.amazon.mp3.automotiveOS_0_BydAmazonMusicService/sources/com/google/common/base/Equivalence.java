package com.google.common.base;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class Equivalence<T> {

    public static final class Wrapper<T> implements Serializable {
    }

    protected abstract boolean doEquivalent(T t, T t2);

    protected abstract int doHash(T t);

    protected Equivalence() {
    }

    public final boolean equivalent(@CheckForNull T t, @CheckForNull T t2) {
        if (t == t2) {
            return true;
        }
        if (t == null || t2 == null) {
            return false;
        }
        return doEquivalent(t, t2);
    }

    public final int hash(@CheckForNull T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();

        Equals() {
        }

        @Override // com.google.common.base.Equivalence
        protected boolean doEquivalent(Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        @Override // com.google.common.base.Equivalence
        protected int doHash(Object obj) {
            return obj.hashCode();
        }
    }

    static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity INSTANCE = new Identity();

        @Override // com.google.common.base.Equivalence
        protected boolean doEquivalent(Object obj, Object obj2) {
            return false;
        }

        Identity() {
        }

        @Override // com.google.common.base.Equivalence
        protected int doHash(Object obj) {
            return System.identityHashCode(obj);
        }
    }
}
