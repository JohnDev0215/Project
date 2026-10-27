package p;

import com.google.protobuf.WireFormat$FieldType;

/* JADX INFO: loaded from: classes.dex */
public final class k01 extends pl0 {
    public final Object a;

    public k01(wj1 wj1Var, Object obj, wj1 wj1Var2, j01 j01Var) {
        if (wj1Var == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
        if (j01Var.e == WireFormat$FieldType.l && wj1Var2 == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        }
        this.a = obj;
    }
}
