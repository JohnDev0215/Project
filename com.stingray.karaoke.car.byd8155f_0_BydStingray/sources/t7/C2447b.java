package t7;

import A7.k;
import A7.m;
import java.io.Serializable;
import n7.AbstractC1989d;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: t7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2447b extends AbstractC1989d implements InterfaceC2446a, Serializable {
    public final Enum[] l;

    public C2447b(Enum[] enumArr) {
        m.f(enumArr, "entries");
        this.l = enumArr;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r32 = (Enum) obj;
        m.f(r32, "element");
        return ((Enum) AbstractC1996k.S(r32.ordinal(), this.l)) == r32;
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.l.length;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        Enum[] enumArr = this.l;
        int length = enumArr.length;
        if (i7 < 0 || i7 >= length) {
            throw new IndexOutOfBoundsException(k.i("index: ", ", size: ", i7, length));
        }
        return enumArr[i7];
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r32 = (Enum) obj;
        m.f(r32, "element");
        int iOrdinal = r32.ordinal();
        if (((Enum) AbstractC1996k.S(iOrdinal, this.l)) == r32) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r22 = (Enum) obj;
        m.f(r22, "element");
        return indexOf(r22);
    }
}
