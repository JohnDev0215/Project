package p;

import com.google.protobuf.WireFormat$FieldType;

/* JADX INFO: loaded from: classes.dex */
public final class oe1 {
    public final gh2 a;
    public final Object b;
    public final Object c;

    public oe1(WireFormat$FieldType wireFormat$FieldType, WireFormat$FieldType wireFormat$FieldType2, Object obj) {
        this.a = new gh2(wireFormat$FieldType, wireFormat$FieldType2, obj);
        this.c = obj;
    }

    public static int a(gh2 gh2Var, Object obj, Object obj2) {
        return zt0.a((WireFormat$FieldType) gh2Var.l, 2, obj2) + zt0.a((WireFormat$FieldType) gh2Var.e, 1, obj);
    }

    public static void b(com.google.protobuf.h hVar, gh2 gh2Var, Object obj, Object obj2) {
        zt0.f(hVar, (WireFormat$FieldType) gh2Var.e, 1, obj);
        zt0.f(hVar, (WireFormat$FieldType) gh2Var.l, 2, obj2);
    }
}
