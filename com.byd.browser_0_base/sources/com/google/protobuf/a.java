package com.google.protobuf;

import com.google.protobuf.b;
import com.google.protobuf.c0;
import com.google.protobuf.g;
import com.google.protobuf.g1;
import com.google.protobuf.j1;
import com.google.protobuf.n;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import com.google.protobuf.u0;
import defpackage.lk;
import defpackage.si3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: AbstractMessage.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends com.google.protobuf.b implements q0 {
    public int e = -1;

    /* JADX INFO: renamed from: com.google.protobuf.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbstractMessage.java */
    public static abstract class AbstractC0042a<BuilderType extends AbstractC0042a<BuilderType>> extends b.a implements q0.a {
        public static si3 z(q0 q0Var) {
            ArrayList arrayList = new ArrayList();
            u0.a(q0Var, "", arrayList);
            return new si3(arrayList);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.b.a
        public final b.a i(com.google.protobuf.b bVar) {
            return R((q0) bVar);
        }

        @Override // com.google.protobuf.b.a
        /* JADX INFO: renamed from: j */
        public final b.a t(byte[] bArr) throws d0 {
            r(bArr, bArr.length);
            return this;
        }

        @Override // com.google.protobuf.b.a
        public final b.a r(byte[] bArr, int i) throws d0 {
            try {
                g.a aVarH = g.h(bArr, 0, i, false);
                L(aVarH, r.e);
                aVarH.a(0);
                return this;
            } catch (d0 e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(g("byte array"), e2);
            }
        }

        @Override // com.google.protobuf.b.a, com.google.protobuf.r0.a
        public final r0.a t(byte[] bArr) throws d0 {
            r(bArr, bArr.length);
            return this;
        }

        public final String toString() {
            int i = g1.a;
            g1.b bVar = g1.b.b;
            bVar.getClass();
            try {
                StringBuilder sb = new StringBuilder();
                bVar.a(this, new g1.c(sb));
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.protobuf.r0.a
        /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
        public BuilderType L(g gVar, s sVar) {
            int iF;
            gVar.getClass();
            j1 j1VarN = n();
            j1 j1Var = j1.f;
            j1.a aVar = new j1.a();
            aVar.v(j1VarN);
            do {
                iF = gVar.F();
                if (iF == 0) {
                    break;
                }
            } while (u0.c(gVar, aVar, sVar, c(), new u0.a(this), iF));
            j0(aVar.build());
            return this;
        }

        @Override // com.google.protobuf.q0.a
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public BuilderType R(q0 q0Var) {
            Map<n.f, Object> mapS = q0Var.s();
            if (q0Var.c() != c()) {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            for (Map.Entry<n.f, Object> entry : mapS.entrySet()) {
                n.f key = entry.getKey();
                if (key.c()) {
                    Iterator it = ((List) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        f(key, it.next());
                    }
                } else if (key.k.e == n.f.b.n) {
                    q0 q0Var2 = (q0) o(key);
                    if (q0Var2 == q0Var2.b()) {
                        a(key, entry.getValue());
                    } else {
                        a(key, q0Var2.e().R(q0Var2).R((q0) entry.getValue()).build());
                    }
                } else {
                    a(key, entry.getValue());
                }
            }
            y(q0Var.n());
            return this;
        }

        public void y(j1 j1Var) {
            j1 j1VarN = n();
            j1 j1Var2 = j1.f;
            j1.a aVar = new j1.a();
            aVar.v(j1VarN);
            aVar.v(j1Var);
            j0(aVar.build());
        }
    }

    /* JADX INFO: compiled from: AbstractMessage.java */
    public interface b {
        void a();
    }

    public static boolean B(Object obj, Object obj2) {
        lk lkVarD;
        Object objD;
        boolean z = obj instanceof byte[];
        if (z && (obj2 instanceof byte[])) {
            return Arrays.equals((byte[]) obj, (byte[]) obj2);
        }
        if (z) {
            byte[] bArr = (byte[]) obj;
            lk.f fVar = lk.f;
            lkVarD = lk.d(bArr, 0, bArr.length);
        } else {
            lkVarD = (lk) obj;
        }
        if (obj2 instanceof byte[]) {
            byte[] bArr2 = (byte[]) obj2;
            lk.f fVar2 = lk.f;
            objD = lk.d(bArr2, 0, bArr2.length);
        } else {
            objD = (lk) obj2;
        }
        return lkVarD.equals(objD);
    }

    public static Map C(List list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        q0 q0Var = (q0) it.next();
        n.a aVarC = q0Var.c();
        n.f fVarI = aVarC.i("key");
        n.f fVarI2 = aVarC.i("value");
        Object objO = q0Var.o(fVarI2);
        if (objO instanceof n.e) {
            objO = Integer.valueOf(((n.e) objO).e.i);
        }
        map.put(q0Var.o(fVarI), objO);
        while (it.hasNext()) {
            q0 q0Var2 = (q0) it.next();
            Object objO2 = q0Var2.o(fVarI2);
            if (objO2 instanceof n.e) {
                objO2 = Integer.valueOf(((n.e) objO2).e.i);
            }
            map.put(q0Var2.o(fVarI), objO2);
        }
        return map;
    }

    public static int D(int i, Map<n.f, Object> map) {
        int i2;
        int iB;
        for (Map.Entry<n.f, Object> entry : map.entrySet()) {
            n.f key = entry.getKey();
            Object value = entry.getValue();
            int i3 = (i * 37) + key.f.i;
            if (key.m()) {
                i2 = i3 * 53;
                iB = m0.b(C((List) value));
            } else if (key.k != n.f.c.j) {
                i2 = i3 * 53;
                iB = value.hashCode();
            } else if (key.c()) {
                int i4 = i3 * 53;
                Iterator it = ((List) value).iterator();
                int iB2 = 1;
                while (it.hasNext()) {
                    iB2 = (iB2 * 31) + ((c0.a) it.next()).b();
                }
                i = i4 + iB2;
            } else {
                i2 = i3 * 53;
                iB = ((c0.a) value).b();
            }
            i = iB + i2;
        }
        return i;
    }

    @Override // com.google.protobuf.b
    public final void A(int i) {
        this.e = i;
    }

    public boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof q0)) {
            return false;
        }
        q0 q0Var = (q0) obj;
        if (c() != q0Var.c()) {
            return false;
        }
        Map<n.f, Object> mapS = s();
        Map<n.f, Object> mapS2 = q0Var.s();
        if (mapS.size() != mapS2.size()) {
            z = false;
            break;
        }
        loop0: for (n.f fVar : mapS.keySet()) {
            if (mapS2.containsKey(fVar)) {
                Object obj2 = mapS.get(fVar);
                Object obj3 = mapS2.get(fVar);
                if (fVar.k == n.f.c.i) {
                    if (fVar.c()) {
                        List list = (List) obj2;
                        List list2 = (List) obj3;
                        if (list.size() == list2.size()) {
                            for (int i = 0; i < list.size(); i++) {
                                if (B(list.get(i), list2.get(i))) {
                                }
                            }
                        }
                    } else if (!B(obj2, obj3)) {
                    }
                } else if (fVar.m()) {
                    if (!m0.l(C((List) obj2), C((List) obj3))) {
                    }
                } else if (!obj2.equals(obj3)) {
                }
            }
            z = false;
        }
        z = true;
        return z && n().equals(q0Var.n());
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int iD = (D(c().hashCode() + 779, s()) * 29) + n().hashCode();
        this.memoizedHashCode = iD;
        return iD;
    }

    public final String toString() {
        int i = g1.a;
        g1.b bVar = g1.b.b;
        bVar.getClass();
        try {
            StringBuilder sb = new StringBuilder();
            bVar.a(this, new g1.c(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.protobuf.b
    public final int x() {
        return this.e;
    }

    @Override // com.google.protobuf.b
    public final si3 z() {
        return AbstractC0042a.z(this);
    }
}
