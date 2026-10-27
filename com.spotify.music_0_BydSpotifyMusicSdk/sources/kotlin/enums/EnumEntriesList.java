package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import p.fe0;
import p.hf2;
import p.p1;
import p.py0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class EnumEntriesList<T extends Enum<T>> extends p1 implements fe0, Serializable {
    private volatile T[] _entries;
    private final py0 entriesProvider;

    public EnumEntriesList(py0 py0Var) {
        this.entriesProvider = py0Var;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(b());
    }

    @Override // kotlin.collections.a
    public final int a() {
        return b().length;
    }

    public final Enum[] b() {
        T[] tArr = this._entries;
        if (tArr != null) {
            return tArr;
        }
        T[] tArr2 = (T[]) ((Enum[]) this.entriesProvider.invoke());
        this._entries = tArr2;
        return tArr2;
    }

    @Override // kotlin.collections.a, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r6 = (Enum) obj;
        v03.j(r6, "element");
        Enum[] enumArrB = b();
        int iOrdinal = r6.ordinal();
        v03.j(enumArrB, "<this>");
        return ((iOrdinal < 0 || iOrdinal > enumArrB.length - 1) ? null : enumArrB[iOrdinal]) == r6;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Enum[] enumArrB = b();
        int length = enumArrB.length;
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", length));
        }
        return enumArrB[i];
    }

    @Override // p.p1, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r5 = (Enum) obj;
        v03.j(r5, "element");
        int iOrdinal = r5.ordinal();
        Enum[] enumArrB = b();
        v03.j(enumArrB, "<this>");
        if (((iOrdinal < 0 || iOrdinal > enumArrB.length + (-1)) ? null : enumArrB[iOrdinal]) == r5) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // p.p1, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r2 = (Enum) obj;
        v03.j(r2, "element");
        return indexOf(r2);
    }
}
