package com.spotify.cosmos.cosmonautatoms;

import java.lang.annotation.Annotation;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class IndexedAnnotation<T extends Annotation> {
    private final T annot;
    private final int index;

    public IndexedAnnotation(T t, int i) {
        v03.j(t, "annot");
        this.annot = t;
        this.index = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IndexedAnnotation copy$default(IndexedAnnotation indexedAnnotation, Annotation annotation, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            annotation = indexedAnnotation.annot;
        }
        if ((i2 & 2) != 0) {
            i = indexedAnnotation.index;
        }
        return indexedAnnotation.copy(annotation, i);
    }

    public final T component1() {
        return this.annot;
    }

    public final int component2() {
        return this.index;
    }

    public final IndexedAnnotation<T> copy(T t, int i) {
        v03.j(t, "annot");
        return new IndexedAnnotation<>(t, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndexedAnnotation)) {
            return false;
        }
        IndexedAnnotation indexedAnnotation = (IndexedAnnotation) obj;
        return v03.b(this.annot, indexedAnnotation.annot) && this.index == indexedAnnotation.index;
    }

    public final T getAnnot() {
        return this.annot;
    }

    public final int getIndex() {
        return this.index;
    }

    public int hashCode() {
        return Integer.hashCode(this.index) + (this.annot.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IndexedAnnotation(annot=");
        sb.append(this.annot);
        sb.append(", index=");
        return u4.k(sb, this.index, ')');
    }
}
