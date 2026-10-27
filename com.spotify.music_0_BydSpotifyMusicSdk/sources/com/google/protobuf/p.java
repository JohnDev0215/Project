package com.google.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class p extends n {
    @Override // com.google.protobuf.n
    public final o a(Object obj) {
        i iVar = (i) obj;
        o oVar = iVar.unknownFields;
        if (oVar != o.f) {
            return oVar;
        }
        o oVar2 = new o();
        iVar.unknownFields = oVar2;
        return oVar2;
    }

    @Override // com.google.protobuf.n
    public final void b(Object obj) {
        ((i) obj).unknownFields.e = false;
    }

    @Override // com.google.protobuf.n
    public final void d(Object obj, Object obj2) {
        ((i) obj).unknownFields = (o) obj2;
    }
}
