package com.google.gson.internal.bind;

import com.google.gson.ToNumberPolicy;
import com.google.gson.b;
import com.google.gson.internal.LinkedTreeMap;
import java.io.IOException;
import java.util.ArrayList;
import p.am2;
import p.lo2;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectTypeAdapter extends b {
    public static final lo2 c = new AnonymousClass1(ToNumberPolicy.c);
    public final com.google.gson.a a;
    public final am2 b;

    /* JADX INFO: renamed from: com.google.gson.internal.bind.ObjectTypeAdapter$1, reason: invalid class name */
    class AnonymousClass1 implements lo2 {
        public final /* synthetic */ am2 c;

        public AnonymousClass1(am2 am2Var) {
            this.c = am2Var;
        }

        @Override // p.lo2
        public final b a(com.google.gson.a aVar, qo2 qo2Var) {
            if (qo2Var.a == Object.class) {
                return new ObjectTypeAdapter(aVar, this.c);
            }
            return null;
        }
    }

    public ObjectTypeAdapter(com.google.gson.a aVar, am2 am2Var) {
        this.a = aVar;
        this.b = am2Var;
    }

    public static lo2 d(am2 am2Var) {
        return am2Var == ToNumberPolicy.c ? c : new AnonymousClass1(am2Var);
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        int iOrdinal = t71Var.K0().ordinal();
        if (iOrdinal == 0) {
            ArrayList arrayList = new ArrayList();
            t71Var.b();
            while (t71Var.hasNext()) {
                arrayList.add(b(t71Var));
            }
            t71Var.E();
            return arrayList;
        }
        if (iOrdinal == 2) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
            t71Var.c();
            while (t71Var.hasNext()) {
                linkedTreeMap.put(t71Var.r0(), b(t71Var));
            }
            t71Var.J();
            return linkedTreeMap;
        }
        if (iOrdinal == 5) {
            return t71Var.u();
        }
        if (iOrdinal == 6) {
            return this.b.a(t71Var);
        }
        if (iOrdinal == 7) {
            return Boolean.valueOf(t71Var.w0());
        }
        if (iOrdinal != 8) {
            throw new IllegalStateException();
        }
        t71Var.z0();
        return null;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        if (obj == null) {
            z71Var.E0();
            return;
        }
        Class<?> cls = obj.getClass();
        com.google.gson.a aVar = this.a;
        aVar.getClass();
        b bVarD = aVar.d(new qo2(cls));
        if (!(bVarD instanceof ObjectTypeAdapter)) {
            bVarD.c(z71Var, obj);
        } else {
            z71Var.i();
            z71Var.J();
        }
    }
}
