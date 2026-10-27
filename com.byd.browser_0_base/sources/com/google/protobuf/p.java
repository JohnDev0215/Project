package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.j1;
import com.google.protobuf.n;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import com.google.protobuf.x;
import defpackage.nu;
import defpackage.wv1;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DynamicMessage.java */
/* JADX INFO: loaded from: classes.dex */
public final class p extends com.google.protobuf.a {
    public final n.a f;
    public final x<n.f> g;
    public final n.f[] h;
    public final j1 i;
    public int j = -1;

    /* JADX INFO: compiled from: DynamicMessage.java */
    public class a extends c<p> {
        public a() {
        }

        @Override // defpackage.wv1
        public final Object b(g gVar, s sVar) throws d0 {
            b bVar = new b(p.this.f);
            try {
                bVar.L(gVar, sVar);
                return bVar.d();
            } catch (d0 e) {
                e.e = bVar.d();
                throw e;
            } catch (IOException e2) {
                d0 d0Var = new d0(e2);
                d0Var.e = bVar.d();
                throw d0Var;
            }
        }
    }

    /* JADX INFO: compiled from: DynamicMessage.java */
    public static final class b extends a.AbstractC0042a<b> {
        public final n.a e;
        public final n.f[] g;
        public x<n.f> f = new x<>();
        public j1 h = j1.f;

        public b(n.a aVar) {
            this.e = aVar;
            this.g = new n.f[aVar.e.T()];
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public final p build() {
            if (isInitialized()) {
                return d();
            }
            x<n.f> xVar = this.f;
            n.f[] fVarArr = this.g;
            throw a.AbstractC0042a.z(new p(this.e, xVar, (n.f[]) Arrays.copyOf(fVarArr, fVarArr.length), this.h));
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public final p d() {
            n.a aVar = this.e;
            if (aVar.n().l) {
                for (n.f fVar : aVar.k()) {
                    if (fVar.n() && !this.f.n(fVar)) {
                        if (fVar.k.e == n.f.b.n) {
                            this.f.w(fVar, p.E(fVar.k()));
                        } else {
                            this.f.w(fVar, fVar.i());
                        }
                    }
                }
            }
            this.f.t();
            x<n.f> xVar = this.f;
            n.f[] fVarArr = this.g;
            return new p(aVar, xVar, (n.f[]) Arrays.copyOf(fVarArr, fVarArr.length), this.h);
        }

        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public final b clone() {
            b bVar = new b(this.e);
            bVar.f.u(this.f);
            j1 j1Var = this.h;
            j1 j1Var2 = bVar.h;
            j1 j1Var3 = j1.f;
            j1.a aVar = new j1.a();
            aVar.v(j1Var2);
            aVar.v(j1Var);
            bVar.h = aVar.build();
            n.f[] fVarArr = this.g;
            System.arraycopy(fVarArr, 0, bVar.g, 0, fVarArr.length);
            return bVar;
        }

        @Override // com.google.protobuf.a.AbstractC0042a
        /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
        public final b R(q0 q0Var) {
            if (!(q0Var instanceof p)) {
                super.R(q0Var);
                return this;
            }
            p pVar = (p) q0Var;
            if (pVar.f != this.e) {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            x<n.f> xVar = this.f;
            if (xVar.b) {
                this.f = xVar.clone();
            }
            this.f.u(pVar.g);
            j1 j1Var = this.h;
            j1 j1Var2 = j1.f;
            j1.a aVar = new j1.a();
            aVar.v(j1Var);
            aVar.v(pVar.i);
            this.h = aVar.build();
            int i = 0;
            while (true) {
                Object[] objArr = this.g;
                if (i >= objArr.length) {
                    return this;
                }
                Object obj = objArr[i];
                Object[] objArr2 = pVar.h;
                if (obj == null) {
                    objArr[i] = objArr2[i];
                } else {
                    Object obj2 = objArr2[i];
                    if (obj2 != null && obj != obj2) {
                        x<n.f> xVar2 = this.f;
                        AbstractMap abstractMap = xVar2.a;
                        abstractMap.remove(obj);
                        if (abstractMap.isEmpty()) {
                            xVar2.c = false;
                        }
                        objArr[i] = objArr2[i];
                    }
                }
                i++;
            }
        }

        public final void E(n.f fVar) {
            if (fVar.l != this.e) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a a(n.f fVar, Object obj) {
            E(fVar);
            x<n.f> xVar = this.f;
            if (xVar.b) {
                this.f = xVar.clone();
            }
            if (fVar.k == n.f.c.j) {
                if (fVar.c()) {
                    for (Object obj2 : (List) obj) {
                        Charset charset = c0.a;
                        obj2.getClass();
                        if (!(obj2 instanceof n.e)) {
                            throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
                        }
                    }
                } else {
                    Charset charset2 = c0.a;
                    obj.getClass();
                    if (!(obj instanceof n.e)) {
                        throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
                    }
                }
            }
            n.j jVar = fVar.n;
            if (jVar == null) {
                if (fVar.h.j() == 3 && !fVar.c() && fVar.k.e != n.f.b.n && obj.equals(fVar.i())) {
                    x<n.f> xVar2 = this.f;
                    AbstractMap abstractMap = xVar2.a;
                    abstractMap.remove(fVar);
                    if (abstractMap.isEmpty()) {
                        xVar2.c = false;
                    }
                }
                return this;
            }
            Object[] objArr = this.g;
            int i = jVar.e;
            Object obj3 = objArr[i];
            if (obj3 != null && obj3 != fVar) {
                x<n.f> xVar3 = this.f;
                AbstractMap abstractMap2 = xVar3.a;
                abstractMap2.remove(obj3);
                if (abstractMap2.isEmpty()) {
                    xVar3.c = false;
                }
            }
            objArr[i] = fVar;
            this.f.w(fVar, obj);
            return this;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return p.E(this.e);
        }

        @Override // com.google.protobuf.q0.a, com.google.protobuf.t0
        public final n.a c() {
            return this.e;
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a f(n.f fVar, Object obj) {
            E(fVar);
            x<n.f> xVar = this.f;
            if (xVar.b) {
                this.f = xVar.clone();
            }
            this.f.a(fVar, obj);
            return this;
        }

        @Override // defpackage.bh1
        public final boolean isInitialized() {
            return p.F(this.e, this.f);
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a j0(j1 j1Var) {
            this.h = j1Var;
            return this;
        }

        @Override // com.google.protobuf.t0
        public final boolean k(n.f fVar) {
            E(fVar);
            return this.f.n(fVar);
        }

        @Override // com.google.protobuf.t0
        public final j1 n() {
            return this.h;
        }

        @Override // com.google.protobuf.t0
        public final Object o(n.f fVar) {
            E(fVar);
            Object objJ = this.f.j(fVar);
            return objJ == null ? fVar.c() ? Collections.emptyList() : fVar.k.e == n.f.b.n ? p.E(fVar.k()) : fVar.i() : objJ;
        }

        @Override // com.google.protobuf.t0
        public final Map<n.f, Object> s() {
            return this.f.i();
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a w(n.f fVar) {
            E(fVar);
            if (fVar.k.e == n.f.b.n) {
                return new b(fVar.k());
            }
            throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
        }

        @Override // com.google.protobuf.a.AbstractC0042a
        public final void y(j1 j1Var) {
            j1 j1Var2 = this.h;
            j1 j1Var3 = j1.f;
            j1.a aVar = new j1.a();
            aVar.v(j1Var2);
            aVar.v(j1Var);
            this.h = aVar.build();
        }
    }

    public p(n.a aVar, x<n.f> xVar, n.f[] fVarArr, j1 j1Var) {
        this.f = aVar;
        this.g = xVar;
        this.h = fVarArr;
        this.i = j1Var;
    }

    public static p E(n.a aVar) {
        return new p(aVar, x.d, new n.f[aVar.e.T()], j1.f);
    }

    public static boolean F(n.a aVar, x<n.f> xVar) {
        for (n.f fVar : aVar.k()) {
            if (fVar.p() && !xVar.n(fVar)) {
                return false;
            }
        }
        return xVar.p();
    }

    @Override // defpackage.bh1, com.google.protobuf.t0
    public final q0 b() {
        return E(this.f);
    }

    @Override // com.google.protobuf.t0
    public final n.a c() {
        return this.f;
    }

    @Override // com.google.protobuf.r0, com.google.protobuf.q0
    public final q0.a e() {
        return new b(this.f);
    }

    @Override // com.google.protobuf.r0
    public final r0.a h() {
        return new b(this.f).R(this);
    }

    @Override // defpackage.bh1
    public final boolean isInitialized() {
        return F(this.f, this.g);
    }

    @Override // com.google.protobuf.t0
    public final boolean k(n.f fVar) {
        if (fVar.l == this.f) {
            return this.g.n(fVar);
        }
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }

    @Override // com.google.protobuf.r0
    public final int m() {
        int iM;
        int iM2;
        int i = this.j;
        if (i != -1) {
            return i;
        }
        boolean z = this.f.n().i;
        j1 j1Var = this.i;
        x<n.f> xVar = this.g;
        if (z) {
            iM = xVar.k();
            iM2 = j1Var.g();
        } else {
            iM = xVar.m();
            iM2 = j1Var.m();
        }
        int i2 = iM2 + iM;
        this.j = i2;
        return i2;
    }

    @Override // com.google.protobuf.t0
    public final j1 n() {
        return this.i;
    }

    @Override // com.google.protobuf.t0
    public final Object o(n.f fVar) {
        if (fVar.l != this.f) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
        Object objJ = this.g.j(fVar);
        return objJ == null ? fVar.c() ? Collections.emptyList() : fVar.k.e == n.f.b.n ? E(fVar.k()) : fVar.i() : objJ;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.protobuf.r0
    public final void p(nu nuVar) {
        e1<T, Object> e1Var;
        e1<T, Object> e1Var2;
        boolean z = this.f.n().i;
        j1 j1Var = this.i;
        int i = 0;
        x<n.f> xVar = this.g;
        if (z) {
            while (true) {
                e1Var2 = xVar.a;
                if (i >= e1Var2.d()) {
                    break;
                }
                x.B(e1Var2.c(i), nuVar);
                i++;
            }
            Iterator it = e1Var2.e().iterator();
            while (it.hasNext()) {
                x.B((Map.Entry) it.next(), nuVar);
            }
            j1Var.i(nuVar);
            return;
        }
        while (true) {
            e1Var = xVar.a;
            if (i >= e1Var.d()) {
                break;
            }
            Map.Entry entryC = e1Var.c(i);
            x.A((x.b) entryC.getKey(), entryC.getValue(), nuVar);
            i++;
        }
        for (Map.Entry entry : e1Var.e()) {
            x.A((x.b) entry.getKey(), entry.getValue(), nuVar);
        }
        j1Var.p(nuVar);
    }

    @Override // com.google.protobuf.t0
    public final Map<n.f, Object> s() {
        return this.g.i();
    }

    @Override // com.google.protobuf.r0
    public final wv1<p> u() {
        return new a();
    }

    @Override // defpackage.bh1, com.google.protobuf.t0
    public final r0 b() {
        return E(this.f);
    }

    @Override // com.google.protobuf.r0, com.google.protobuf.q0
    public final r0.a e() {
        return new b(this.f);
    }
}
