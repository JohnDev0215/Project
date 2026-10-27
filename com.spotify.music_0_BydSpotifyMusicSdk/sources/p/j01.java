package p;

import com.google.protobuf.WireFormat$FieldType;

/* JADX INFO: loaded from: classes.dex */
public final class j01 implements Comparable {
    public final int c;
    public final WireFormat$FieldType e;
    public final boolean k;

    public j01(o51 o51Var, int i, WireFormat$FieldType wireFormat$FieldType, boolean z, boolean z2) {
        this.c = i;
        this.e = wireFormat$FieldType;
        this.k = z;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.c - ((j01) obj).c;
    }
}
