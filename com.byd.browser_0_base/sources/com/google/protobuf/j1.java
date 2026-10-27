package com.google.protobuf;

import com.google.protobuf.d0;
import com.google.protobuf.g;
import com.google.protobuf.g1;
import com.google.protobuf.r0;
import defpackage.lk;
import defpackage.nu;
import defpackage.wv1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: UnknownFieldSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class j1 implements r0 {
    public static final j1 f = new j1(new TreeMap());
    public static final c g = new c();
    public final TreeMap<Integer, b> e;

    /* JADX INFO: compiled from: UnknownFieldSet.java */
    public static final class a implements r0.a {
        public final TreeMap<Integer, b.a> e = new TreeMap<>();

        @Override // com.google.protobuf.r0.a
        public final r0.a G(r0 r0Var) {
            if (!(r0Var instanceof j1)) {
                throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
            }
            v((j1) r0Var);
            return this;
        }

        @Override // com.google.protobuf.r0.a
        public final r0.a L(g gVar, s sVar) {
            x(gVar);
            return this;
        }

        public final Object clone() {
            j1 j1Var = j1.f;
            a aVar = new a();
            for (Map.Entry<Integer, b.a> entry : this.e.entrySet()) {
                aVar.e.put(entry.getKey(), entry.getValue().clone());
            }
            return aVar;
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        public final r0 d() {
            return build();
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public final j1 build() {
            TreeMap<Integer, b.a> treeMap = this.e;
            if (treeMap.isEmpty()) {
                return j1.f;
            }
            TreeMap treeMap2 = new TreeMap();
            for (Map.Entry<Integer, b.a> entry : treeMap.entrySet()) {
                treeMap2.put(entry.getKey(), entry.getValue().c());
            }
            return new j1(treeMap2);
        }

        public final b.a i(int i) {
            if (i == 0) {
                return null;
            }
            TreeMap<Integer, b.a> treeMap = this.e;
            b.a aVar = treeMap.get(Integer.valueOf(i));
            if (aVar != null) {
                return aVar;
            }
            int i2 = b.f;
            b.a aVar2 = new b.a();
            treeMap.put(Integer.valueOf(i), aVar2);
            return aVar2;
        }

        public final void j(int i, b bVar) {
            if (i <= 0) {
                throw new IllegalArgumentException(i + " is not a valid field number.");
            }
            TreeMap<Integer, b.a> treeMap = this.e;
            if (treeMap.containsKey(Integer.valueOf(i))) {
                i(i).e(bVar);
                return;
            }
            if (i <= 0) {
                throw new IllegalArgumentException(i + " is not a valid field number.");
            }
            Integer numValueOf = Integer.valueOf(i);
            int i2 = b.f;
            b.a aVar = new b.a();
            aVar.e(bVar);
            treeMap.put(numValueOf, aVar);
        }

        public final boolean r(int i, g gVar) throws d0.a {
            int i2 = i >>> 3;
            int i3 = i & 7;
            if (i3 == 0) {
                i(i2).b(gVar.u());
                return true;
            }
            if (i3 == 1) {
                b.a aVarI = i(i2);
                long jQ = gVar.q();
                b bVar = aVarI.a;
                if (bVar.c == null) {
                    bVar.c = new ArrayList();
                }
                aVarI.a.c.add(Long.valueOf(jQ));
                return true;
            }
            if (i3 == 2) {
                i(i2).a(gVar.m());
                return true;
            }
            if (i3 == 3) {
                j1 j1Var = j1.f;
                a aVar = new a();
                gVar.s(i2, aVar, r.e);
                b.a aVarI2 = i(i2);
                j1 j1VarBuild = aVar.build();
                b bVar2 = aVarI2.a;
                if (bVar2.e == null) {
                    bVar2.e = new ArrayList();
                }
                aVarI2.a.e.add(j1VarBuild);
                return true;
            }
            if (i3 == 4) {
                return false;
            }
            if (i3 != 5) {
                int i4 = d0.f;
                throw new d0.a();
            }
            b.a aVarI3 = i(i2);
            int iP = gVar.p();
            b bVar3 = aVarI3.a;
            if (bVar3.b == null) {
                bVar3.b = new ArrayList();
            }
            aVarI3.a.b.add(Integer.valueOf(iP));
            return true;
        }

        @Override // com.google.protobuf.r0.a
        public final r0.a t(byte[] bArr) throws d0 {
            try {
                g.a aVarH = g.h(bArr, 0, bArr.length, false);
                x(aVarH);
                aVarH.a(0);
                return this;
            } catch (d0 e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        public final void v(j1 j1Var) {
            if (j1Var != j1.f) {
                for (Map.Entry<Integer, b> entry : j1Var.e.entrySet()) {
                    j(entry.getKey().intValue(), entry.getValue());
                }
            }
        }

        public final void x(g gVar) {
            int iF;
            do {
                iF = gVar.F();
                if (iF == 0) {
                    return;
                }
            } while (r(iF, gVar));
        }

        public final void y(int i, int i2) {
            if (i > 0) {
                i(i).b(i2);
                return;
            }
            throw new IllegalArgumentException(i + " is not a valid field number.");
        }
    }

    /* JADX INFO: compiled from: UnknownFieldSet.java */
    public static final class b {
        public static final /* synthetic */ int f = 0;
        public List<Long> a;
        public List<Integer> b;
        public List<Long> c;
        public List<lk> d;
        public List<j1> e;

        /* JADX INFO: compiled from: UnknownFieldSet.java */
        public static final class a {
            public b a = new b();

            public final void a(lk lkVar) {
                b bVar = this.a;
                if (bVar.d == null) {
                    bVar.d = new ArrayList();
                }
                this.a.d.add(lkVar);
            }

            public final void b(long j) {
                b bVar = this.a;
                if (bVar.a == null) {
                    bVar.a = new ArrayList();
                }
                this.a.a.add(Long.valueOf(j));
            }

            public final b c() {
                b bVar = new b();
                if (this.a.a == null) {
                    bVar.a = Collections.emptyList();
                } else {
                    bVar.a = Collections.unmodifiableList(new ArrayList(this.a.a));
                }
                if (this.a.b == null) {
                    bVar.b = Collections.emptyList();
                } else {
                    bVar.b = Collections.unmodifiableList(new ArrayList(this.a.b));
                }
                if (this.a.c == null) {
                    bVar.c = Collections.emptyList();
                } else {
                    bVar.c = Collections.unmodifiableList(new ArrayList(this.a.c));
                }
                if (this.a.d == null) {
                    bVar.d = Collections.emptyList();
                } else {
                    bVar.d = Collections.unmodifiableList(new ArrayList(this.a.d));
                }
                if (this.a.e == null) {
                    bVar.e = Collections.emptyList();
                } else {
                    bVar.e = Collections.unmodifiableList(new ArrayList(this.a.e));
                }
                return bVar;
            }

            /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
            public final a clone() {
                b bVar = new b();
                if (this.a.a == null) {
                    bVar.a = null;
                } else {
                    bVar.a = new ArrayList(this.a.a);
                }
                if (this.a.b == null) {
                    bVar.b = null;
                } else {
                    bVar.b = new ArrayList(this.a.b);
                }
                if (this.a.c == null) {
                    bVar.c = null;
                } else {
                    bVar.c = new ArrayList(this.a.c);
                }
                if (this.a.d == null) {
                    bVar.d = null;
                } else {
                    bVar.d = new ArrayList(this.a.d);
                }
                if (this.a.e == null) {
                    bVar.e = null;
                } else {
                    bVar.e = new ArrayList(this.a.e);
                }
                a aVar = new a();
                aVar.a = bVar;
                return aVar;
            }

            public final void e(b bVar) {
                if (!bVar.a.isEmpty()) {
                    b bVar2 = this.a;
                    if (bVar2.a == null) {
                        bVar2.a = new ArrayList();
                    }
                    this.a.a.addAll(bVar.a);
                }
                if (!bVar.b.isEmpty()) {
                    b bVar3 = this.a;
                    if (bVar3.b == null) {
                        bVar3.b = new ArrayList();
                    }
                    this.a.b.addAll(bVar.b);
                }
                if (!bVar.c.isEmpty()) {
                    b bVar4 = this.a;
                    if (bVar4.c == null) {
                        bVar4.c = new ArrayList();
                    }
                    this.a.c.addAll(bVar.c);
                }
                if (!bVar.d.isEmpty()) {
                    b bVar5 = this.a;
                    if (bVar5.d == null) {
                        bVar5.d = new ArrayList();
                    }
                    this.a.d.addAll(bVar.d);
                }
                if (bVar.e.isEmpty()) {
                    return;
                }
                b bVar6 = this.a;
                if (bVar6.e == null) {
                    bVar6.e = new ArrayList();
                }
                this.a.e.addAll(bVar.e);
            }
        }

        static {
            new a().c();
        }

        public final Object[] a() {
            return new Object[]{this.a, this.b, this.c, this.d, this.e};
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                return Arrays.equals(a(), ((b) obj).a());
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(a());
        }
    }

    /* JADX INFO: compiled from: UnknownFieldSet.java */
    public static final class c extends com.google.protobuf.c<j1> {
        @Override // defpackage.wv1
        public final Object b(g gVar, s sVar) throws d0 {
            a aVar = new a();
            try {
                aVar.x(gVar);
                return aVar.build();
            } catch (d0 e) {
                e.e = aVar.build();
                throw e;
            } catch (IOException e2) {
                d0 d0Var = new d0(e2);
                d0Var.e = aVar.build();
                throw d0Var;
            }
        }
    }

    public j1(TreeMap<Integer, b> treeMap) {
        this.e = treeMap;
    }

    @Override // defpackage.bh1, com.google.protobuf.t0
    public final r0 b() {
        return f;
    }

    @Override // com.google.protobuf.r0, com.google.protobuf.q0
    public final r0.a e() {
        return new a();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j1) {
            if (this.e.equals(((j1) obj).e)) {
                return true;
            }
        }
        return false;
    }

    public final int g() {
        int i = 0;
        for (Map.Entry<Integer, b> entry : this.e.entrySet()) {
            b value = entry.getValue();
            int iIntValue = entry.getKey().intValue();
            Iterator<lk> it = value.d.iterator();
            int iK = 0;
            while (it.hasNext()) {
                iK += nu.K(3, it.next()) + nu.j0(2, iIntValue) + (nu.i0(1) * 2);
            }
            i += iK;
        }
        return i;
    }

    @Override // com.google.protobuf.r0
    public final r0.a h() {
        a aVar = new a();
        aVar.v(this);
        return aVar;
    }

    public final int hashCode() {
        TreeMap<Integer, b> treeMap = this.e;
        if (treeMap.isEmpty()) {
            return 0;
        }
        return treeMap.hashCode();
    }

    public final void i(nu nuVar) {
        for (Map.Entry<Integer, b> entry : this.e.entrySet()) {
            b value = entry.getValue();
            int iIntValue = entry.getKey().intValue();
            Iterator<lk> it = value.d.iterator();
            while (it.hasNext()) {
                nuVar.J0(iIntValue, it.next());
            }
        }
    }

    @Override // defpackage.bh1
    public final boolean isInitialized() {
        return true;
    }

    public final void j(i iVar) {
        nu nuVar;
        iVar.getClass();
        for (Map.Entry<Integer, b> entry : this.e.entrySet()) {
            b value = entry.getValue();
            int iIntValue = entry.getKey().intValue();
            iVar.m(iIntValue, value.a, false);
            iVar.f(iIntValue, value.b, false);
            iVar.h(iIntValue, value.c, false);
            List<lk> list = value.d;
            int i = 0;
            while (true) {
                int size = list.size();
                nuVar = iVar.a;
                if (i >= size) {
                    break;
                }
                nuVar.r0(iIntValue, list.get(i));
                i++;
            }
            for (int i2 = 0; i2 < value.e.size(); i2++) {
                nuVar.M0(iIntValue, 3);
                value.e.get(i2).j(iVar);
                nuVar.M0(iIntValue, 4);
            }
        }
    }

    @Override // com.google.protobuf.r0
    public final lk.f l() {
        try {
            int iM = m();
            lk.f fVar = lk.f;
            byte[] bArr = new byte[iM];
            boolean z = nu.l;
            nu.a aVar = new nu.a(bArr, iM);
            p(aVar);
            if (aVar.R0() == 0) {
                return new lk.f(bArr);
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.r0
    public final int m() {
        TreeMap<Integer, b> treeMap = this.e;
        if (treeMap.isEmpty()) {
            return 0;
        }
        int i = 0;
        for (Map.Entry<Integer, b> entry : treeMap.entrySet()) {
            b value = entry.getValue();
            int iIntValue = entry.getKey().intValue();
            Iterator<Long> it = value.a.iterator();
            int iM = 0;
            while (it.hasNext()) {
                iM += nu.m0(iIntValue, it.next().longValue());
            }
            Iterator<Integer> it2 = value.b.iterator();
            while (it2.hasNext()) {
                it2.next().intValue();
                iM += nu.O(iIntValue);
            }
            Iterator<Long> it3 = value.c.iterator();
            while (it3.hasNext()) {
                it3.next().longValue();
                iM += nu.P(iIntValue);
            }
            Iterator<lk> it4 = value.d.iterator();
            while (it4.hasNext()) {
                iM += nu.K(iIntValue, it4.next());
            }
            Iterator<j1> it5 = value.e.iterator();
            while (it5.hasNext()) {
                iM += it5.next().m() + (nu.i0(iIntValue) * 2);
            }
            i += iM;
        }
        return i;
    }

    @Override // com.google.protobuf.r0
    public final void p(nu nuVar) {
        for (Map.Entry<Integer, b> entry : this.e.entrySet()) {
            b value = entry.getValue();
            int iIntValue = entry.getKey().intValue();
            Iterator<Long> it = value.a.iterator();
            while (it.hasNext()) {
                nuVar.P0(iIntValue, it.next().longValue());
            }
            Iterator<Integer> it2 = value.b.iterator();
            while (it2.hasNext()) {
                nuVar.u0(iIntValue, it2.next().intValue());
            }
            Iterator<Long> it3 = value.c.iterator();
            while (it3.hasNext()) {
                nuVar.w0(iIntValue, it3.next().longValue());
            }
            Iterator<lk> it4 = value.d.iterator();
            while (it4.hasNext()) {
                nuVar.r0(iIntValue, it4.next());
            }
            Iterator<j1> it5 = value.e.iterator();
            while (it5.hasNext()) {
                nuVar.y0(iIntValue, it5.next());
            }
        }
    }

    @Override // com.google.protobuf.r0
    public final byte[] q() {
        try {
            int iM = m();
            byte[] bArr = new byte[iM];
            boolean z = nu.l;
            nu.a aVar = new nu.a(bArr, iM);
            p(aVar);
            if (aVar.R0() == 0) {
                return bArr;
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public final String toString() {
        int i = g1.a;
        g1.b.b.getClass();
        try {
            StringBuilder sb = new StringBuilder();
            g1.b.d(this, new g1.c(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.protobuf.r0
    public final wv1 u() {
        return g;
    }
}
