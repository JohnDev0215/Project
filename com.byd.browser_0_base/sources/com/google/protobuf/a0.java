package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.c0;
import com.google.protobuf.e0;
import com.google.protobuf.j1;
import com.google.protobuf.m;
import com.google.protobuf.n;
import com.google.protobuf.n1;
import com.google.protobuf.p;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import com.google.protobuf.u0;
import com.google.protobuf.x;
import com.google.protobuf.z;
import defpackage.C0175b;
import defpackage.gq2;
import defpackage.lk;
import defpackage.nu;
import defpackage.wv1;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: GeneratedMessageV3.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a0 extends com.google.protobuf.a implements Serializable {
    private static final long serialVersionUID = 1;
    public j1 f;

    /* JADX INFO: compiled from: GeneratedMessageV3.java */
    public static abstract class a<BuilderType extends a<BuilderType>> extends a.AbstractC0042a<BuilderType> {
        public boolean e;
        public j1 f;

        public a() {
            this(null);
        }

        @Override // com.google.protobuf.q0.a
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public BuilderType f(n.f fVar, Object obj) {
            e.b(D(), fVar).a(this, obj);
            return this;
        }

        @Override // 
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public BuilderType clone() {
            BuilderType buildertype = (BuilderType) b().e();
            buildertype.R(d());
            return buildertype;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.TreeMap C() {
            /*
                r9 = this;
                java.util.TreeMap r0 = new java.util.TreeMap
                r0.<init>()
                com.google.protobuf.a0$e r1 = r9.D()
                com.google.protobuf.n$a r1 = r1.a
                java.util.List r1 = r1.k()
                r2 = 0
                r3 = r2
            L11:
                int r4 = r1.size()
                if (r3 >= r4) goto La2
                java.lang.Object r4 = r1.get(r3)
                com.google.protobuf.n$f r4 = (com.google.protobuf.n.f) r4
                com.google.protobuf.n$j r5 = r4.n
                r6 = 1
                if (r5 == 0) goto L7b
                int r4 = r5.j
                int r4 = r4 - r6
                int r3 = r3 + r4
                com.google.protobuf.a0$e r4 = r9.D()
                com.google.protobuf.a0$e$c r4 = com.google.protobuf.a0.e.a(r4, r5)
                com.google.protobuf.n$f r7 = r4.d
                if (r7 == 0) goto L37
                boolean r4 = r9.k(r7)
                goto L4a
            L37:
                java.lang.Object[] r7 = new java.lang.Object[r2]
                java.lang.reflect.Method r4 = r4.c
                java.lang.Object r4 = com.google.protobuf.a0.F(r9, r4, r7)
                com.google.protobuf.c0$a r4 = (com.google.protobuf.c0.a) r4
                int r4 = r4.b()
                if (r4 != 0) goto L49
                r4 = r2
                goto L4a
            L49:
                r4 = r6
            L4a:
                if (r4 != 0) goto L4d
                goto L9f
            L4d:
                com.google.protobuf.a0$e r4 = r9.D()
                com.google.protobuf.a0$e$c r4 = com.google.protobuf.a0.e.a(r4, r5)
                com.google.protobuf.n$f r5 = r4.d
                r7 = 0
                if (r5 == 0) goto L62
                boolean r4 = r9.k(r5)
                if (r4 == 0) goto L79
                r4 = r5
                goto L98
            L62:
                java.lang.Object[] r5 = new java.lang.Object[r2]
                java.lang.reflect.Method r8 = r4.c
                java.lang.Object r5 = com.google.protobuf.a0.F(r9, r8, r5)
                com.google.protobuf.c0$a r5 = (com.google.protobuf.c0.a) r5
                int r5 = r5.b()
                if (r5 <= 0) goto L79
                com.google.protobuf.n$a r4 = r4.a
                com.google.protobuf.n$f r4 = r4.j(r5)
                goto L98
            L79:
                r4 = r7
                goto L98
            L7b:
                boolean r5 = r4.c()
                if (r5 == 0) goto L91
                java.lang.Object r5 = r9.o(r4)
                java.util.List r5 = (java.util.List) r5
                boolean r7 = r5.isEmpty()
                if (r7 != 0) goto L9f
                r0.put(r4, r5)
                goto L9f
            L91:
                boolean r5 = r9.k(r4)
                if (r5 != 0) goto L98
                goto L9f
            L98:
                java.lang.Object r5 = r9.o(r4)
                r0.put(r4, r5)
            L9f:
                int r3 = r3 + r6
                goto L11
            La2:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.a0.a.C():java.util.TreeMap");
        }

        public abstract e D();

        @Override // com.google.protobuf.a.AbstractC0042a
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public BuilderType y(j1 j1Var) {
            j1 j1Var2 = this.f;
            j1 j1Var3 = j1.f;
            j1.a aVar = new j1.a();
            aVar.v(j1Var2);
            aVar.v(j1Var);
            return (BuilderType) j0(aVar.build());
        }

        @Override // com.google.protobuf.q0.a
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public BuilderType a(n.f fVar, Object obj) {
            e.b(D(), fVar).f(this, obj);
            return this;
        }

        @Override // com.google.protobuf.q0.a
        /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
        public BuilderType j0(j1 j1Var) {
            this.f = j1Var;
            H();
            return this;
        }

        @Override // com.google.protobuf.q0.a, com.google.protobuf.t0
        public n.a c() {
            return D().a;
        }

        @Override // com.google.protobuf.t0
        public boolean k(n.f fVar) {
            return e.b(D(), fVar).h(this);
        }

        @Override // com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.t0
        public Object o(n.f fVar) {
            Object objG = e.b(D(), fVar).g(this);
            return fVar.c() ? Collections.unmodifiableList((List) objG) : objG;
        }

        @Override // com.google.protobuf.t0
        public Map<n.f, Object> s() {
            return Collections.unmodifiableMap(C());
        }

        @Override // com.google.protobuf.q0.a
        public q0.a w(n.f fVar) {
            return e.b(D(), fVar).b();
        }

        public a(b bVar) {
            this.f = j1.f;
        }

        public final void F() {
        }

        public final void H() {
        }
    }

    /* JADX INFO: compiled from: GeneratedMessageV3.java */
    public interface b extends a.b {
    }

    /* JADX INFO: compiled from: GeneratedMessageV3.java */
    public static abstract class c<MessageType extends d, BuilderType extends c<MessageType, BuilderType>> extends a<BuilderType> implements t0 {
        public x.a<n.f> g;

        private void O(n.f fVar) {
            if (fVar.l != c()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public BuilderType K(n.f fVar, Object obj) {
            List arrayList;
            if (!fVar.l()) {
                super.f(fVar, obj);
                return this;
            }
            O(fVar);
            if (this.g == null) {
                x xVar = x.d;
                this.g = new x.a<>(0);
            }
            x.a<n.f> aVar = this.g;
            if (!aVar.c) {
                aVar.a = x.c(aVar.a, true);
                aVar.c = true;
            }
            if (!fVar.c()) {
                throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
            }
            aVar.d = aVar.d || (obj instanceof r0.a);
            x.a.e(fVar, obj);
            Object objA = aVar.a(fVar);
            if (objA == null) {
                arrayList = new ArrayList();
                aVar.a.put(fVar, arrayList);
            } else {
                arrayList = (List) objA;
            }
            arrayList.add(obj);
            H();
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
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
        public final void M(d dVar) {
            e1<T, Object> e1Var;
            if (dVar.g != null) {
                int i = 0;
                if (this.g == null) {
                    x xVar = x.d;
                    this.g = new x.a<>(0);
                }
                x.a<n.f> aVar = this.g;
                x<n.f> xVar2 = dVar.g;
                if (!aVar.c) {
                    aVar.a = x.c(aVar.a, true);
                    aVar.c = true;
                }
                while (true) {
                    int iD = xVar2.a.d();
                    e1Var = xVar2.a;
                    if (i >= iD) {
                        break;
                    }
                    aVar.b(e1Var.c(i));
                    i++;
                }
                Iterator it = e1Var.e().iterator();
                while (it.hasNext()) {
                    aVar.b((Map.Entry) it.next());
                }
                H();
            }
        }

        public BuilderType N(n.f fVar, Object obj) {
            if (!fVar.l()) {
                super.a(fVar, obj);
                return this;
            }
            O(fVar);
            if (this.g == null) {
                x xVar = x.d;
                this.g = new x.a<>(0);
            }
            x.a<n.f> aVar = this.g;
            if (!aVar.c) {
                aVar.a = x.c(aVar.a, true);
                aVar.c = true;
            }
            if (!fVar.c()) {
                x.a.e(fVar, obj);
            } else {
                if (!(obj instanceof List)) {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
                ArrayList arrayList = new ArrayList((List) obj);
                for (Object obj2 : arrayList) {
                    x.a.e(fVar, obj2);
                    aVar.d = aVar.d || (obj2 instanceof r0.a);
                }
                obj = arrayList;
            }
            if (obj instanceof e0) {
                aVar.b = true;
            }
            aVar.d = aVar.d || (obj instanceof r0.a);
            aVar.a.put(fVar, obj);
            H();
            return this;
        }

        @Override // com.google.protobuf.a0.a, com.google.protobuf.t0
        public final boolean k(n.f fVar) {
            if (!fVar.l()) {
                return super.k(fVar);
            }
            O(fVar);
            x.a<n.f> aVar = this.g;
            if (aVar == null) {
                return false;
            }
            aVar.getClass();
            if (fVar.c()) {
                throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
            }
            return aVar.a.get(fVar) != null;
        }

        @Override // com.google.protobuf.a0.a, com.google.protobuf.t0
        public final Object o(n.f fVar) {
            if (!fVar.l()) {
                return super.o(fVar);
            }
            O(fVar);
            x.a<n.f> aVar = this.g;
            Object objA = aVar == null ? null : aVar.a(fVar);
            return objA == null ? fVar.k.e == n.f.b.n ? p.E(fVar.k()) : fVar.i() : objA;
        }

        @Override // com.google.protobuf.a0.a, com.google.protobuf.t0
        public final Map<n.f, Object> s() {
            Object obj;
            TreeMap treeMapC = C();
            x.a<n.f> aVar = this.g;
            if (aVar != null) {
                if (aVar.b) {
                    d1 d1VarC = x.c(aVar.a, false);
                    if (aVar.a.h) {
                        d1VarC.g();
                        obj = d1VarC;
                    } else {
                        x.a.d(d1VarC);
                        obj = d1VarC;
                    }
                } else {
                    e1<T, Object> e1Var = aVar.a;
                    boolean z = e1Var.h;
                    Object objUnmodifiableMap = e1Var;
                    if (!z) {
                        objUnmodifiableMap = Collections.unmodifiableMap(e1Var);
                    }
                    obj = objUnmodifiableMap;
                }
                treeMapC.putAll(obj);
            }
            return Collections.unmodifiableMap(treeMapC);
        }

        @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
        public final q0.a w(n.f fVar) {
            return fVar.l() ? new p.b(fVar.k()) : super.w(fVar);
        }
    }

    /* JADX INFO: compiled from: GeneratedMessageV3.java */
    public static final class e {
        public final n.a a;
        public final a[] b;
        public String[] c;
        public final c[] d;
        public volatile boolean e = false;

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public interface a {
            void a(a aVar, Object obj);

            q0.a b();

            Object c(a0 a0Var);

            Object d(a0 a0Var);

            boolean e(a0 a0Var);

            void f(a aVar, Object obj);

            Object g(a aVar);

            boolean h(a aVar);
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static class b implements a {
            public final n.f a;

            public b(n.f fVar, Class cls) {
                this.a = fVar;
                j((a0) a0.F(null, a0.E(cls, "getDefaultInstance", new Class[0]), new Object[0]));
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final void a(a aVar, Object obj) {
                k(aVar);
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final q0.a b() {
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final Object c(a0 a0Var) {
                d(a0Var);
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final Object d(a0 a0Var) {
                new ArrayList();
                j(a0Var);
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final boolean e(a0 a0Var) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            @Override // com.google.protobuf.a0.e.a
            public final void f(a aVar, Object obj) {
                k(aVar);
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final Object g(a aVar) {
                new ArrayList();
                i(aVar);
                throw null;
            }

            @Override // com.google.protobuf.a0.e.a
            public final boolean h(a aVar) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            public final void i(a aVar) {
                int i = this.a.f.i;
                aVar.getClass();
                throw new RuntimeException("No map fields found in ".concat(aVar.getClass().getName()));
            }

            public final void j(a0 a0Var) {
                int i = this.a.f.i;
                a0Var.getClass();
                throw new RuntimeException("No map fields found in ".concat(a0Var.getClass().getName()));
            }

            public final void k(a aVar) {
                int i = this.a.f.i;
                aVar.getClass();
                throw new RuntimeException("No map fields found in ".concat(aVar.getClass().getName()));
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static class c {
            public final n.a a;
            public final Method b;
            public final Method c;
            public final n.f d;

            public c(n.a aVar, int i, String str, Class<? extends a0> cls, Class<? extends a> cls2) {
                this.a = aVar;
                n.f[] fVarArr = aVar.m().get(i).k;
                if (fVarArr.length == 1 && fVarArr[0].j) {
                    this.b = null;
                    this.c = null;
                    this.d = (n.f) Collections.unmodifiableList(Arrays.asList(fVarArr)).get(0);
                } else {
                    this.b = a0.E(cls, gq2.g("get", str, "Case"), new Class[0]);
                    this.c = a0.E(cls2, gq2.g("get", str, "Case"), new Class[0]);
                    this.d = null;
                }
                a0.E(cls2, C0175b.j("clear", str), new Class[0]);
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static final class d extends C0043e {
            public final n.d c;
            public final Method d;
            public final Method e;
            public final boolean f;
            public final Method g;
            public final Method h;
            public final Method i;

            public d(n.f fVar, String str, Class<? extends a0> cls, Class<? extends a> cls2) {
                super(str, cls, cls2);
                this.c = fVar.j();
                this.d = a0.E(this.a, "valueOf", new Class[]{n.e.class});
                this.e = a0.E(this.a, "getValueDescriptor", new Class[0]);
                boolean z = fVar.h.j() == 3;
                this.f = z;
                if (z) {
                    String strG = gq2.g("get", str, "Value");
                    Class cls3 = Integer.TYPE;
                    this.g = a0.E(cls, strG, new Class[]{cls3});
                    this.h = a0.E(cls2, gq2.g("get", str, "Value"), new Class[]{cls3});
                    a0.E(cls2, gq2.g("set", str, "Value"), new Class[]{cls3, cls3});
                    this.i = a0.E(cls2, gq2.g("add", str, "Value"), new Class[]{cls3});
                }
            }

            @Override // com.google.protobuf.a0.e.C0043e, com.google.protobuf.a0.e.a
            public final void a(a aVar, Object obj) {
                if (this.f) {
                    a0.F(aVar, this.i, new Object[]{Integer.valueOf(((n.e) obj).e.i)});
                } else {
                    super.a(aVar, a0.F(null, this.d, new Object[]{obj}));
                }
            }

            @Override // com.google.protobuf.a0.e.C0043e, com.google.protobuf.a0.e.a
            public final Object d(a0 a0Var) {
                Object objF;
                ArrayList arrayList = new ArrayList();
                C0043e.a aVar = this.b;
                int iIntValue = ((Integer) a0.F(a0Var, aVar.f, new Object[0])).intValue();
                for (int i = 0; i < iIntValue; i++) {
                    if (this.f) {
                        objF = this.c.i(((Integer) a0.F(a0Var, this.g, new Object[]{Integer.valueOf(i)})).intValue());
                    } else {
                        aVar.getClass();
                        objF = a0.F(a0.F(a0Var, aVar.c, new Object[]{Integer.valueOf(i)}), this.e, new Object[0]);
                    }
                    arrayList.add(objF);
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.a0.e.C0043e, com.google.protobuf.a0.e.a
            public final Object g(a aVar) {
                Object objF;
                ArrayList arrayList = new ArrayList();
                C0043e.a aVar2 = this.b;
                int iIntValue = ((Integer) a0.F(aVar, aVar2.g, new Object[0])).intValue();
                for (int i = 0; i < iIntValue; i++) {
                    if (this.f) {
                        objF = this.c.i(((Integer) a0.F(aVar, this.h, new Object[]{Integer.valueOf(i)})).intValue());
                    } else {
                        aVar2.getClass();
                        objF = a0.F(a0.F(aVar, aVar2.d, new Object[]{Integer.valueOf(i)}), this.e, new Object[0]);
                    }
                    arrayList.add(objF);
                }
                return Collections.unmodifiableList(arrayList);
            }
        }

        /* JADX INFO: renamed from: com.google.protobuf.a0$e$e, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static class C0043e implements a {
            public final Class a;
            public final a b;

            /* JADX INFO: renamed from: com.google.protobuf.a0$e$e$a */
            /* JADX INFO: compiled from: GeneratedMessageV3.java */
            public static final class a {
                public final Method a;
                public final Method b;
                public final Method c;
                public final Method d;
                public final Method e;
                public final Method f;
                public final Method g;
                public final Method h;

                public a(String str, Class cls, Class cls2) {
                    this.a = a0.E(cls, gq2.g("get", str, "List"), new Class[0]);
                    this.b = a0.E(cls2, gq2.g("get", str, "List"), new Class[0]);
                    String strJ = C0175b.j("get", str);
                    Class cls3 = Integer.TYPE;
                    Method methodE = a0.E(cls, strJ, new Class[]{cls3});
                    this.c = methodE;
                    this.d = a0.E(cls2, C0175b.j("get", str), new Class[]{cls3});
                    Class<?> returnType = methodE.getReturnType();
                    a0.E(cls2, C0175b.j("set", str), new Class[]{cls3, returnType});
                    this.e = a0.E(cls2, C0175b.j("add", str), new Class[]{returnType});
                    this.f = a0.E(cls, gq2.g("get", str, "Count"), new Class[0]);
                    this.g = a0.E(cls2, gq2.g("get", str, "Count"), new Class[0]);
                    this.h = a0.E(cls2, C0175b.j("clear", str), new Class[0]);
                }
            }

            public C0043e(String str, Class cls, Class cls2) {
                a aVar = new a(str, cls, cls2);
                this.a = aVar.c.getReturnType();
                this.b = aVar;
            }

            @Override // com.google.protobuf.a0.e.a
            public void a(a aVar, Object obj) {
                a aVar2 = this.b;
                aVar2.getClass();
                a0.F(aVar, aVar2.e, new Object[]{obj});
            }

            @Override // com.google.protobuf.a0.e.a
            public q0.a b() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.a0.e.a
            public final Object c(a0 a0Var) {
                return d(a0Var);
            }

            @Override // com.google.protobuf.a0.e.a
            public Object d(a0 a0Var) {
                return a0.F(a0Var, this.b.a, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.a
            public final boolean e(a0 a0Var) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override // com.google.protobuf.a0.e.a
            public final void f(a aVar, Object obj) {
                a0.F(aVar, this.b.h, new Object[0]);
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    a(aVar, it.next());
                }
            }

            @Override // com.google.protobuf.a0.e.a
            public Object g(a aVar) {
                return a0.F(aVar, this.b.b, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.a
            public final boolean h(a aVar) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static final class f extends C0043e {
            public final Method c;

            public f(String str, Class cls, Class cls2) {
                super(str, cls, cls2);
                this.c = a0.E(this.a, "newBuilder", new Class[0]);
                a0.E(cls2, gq2.g("get", str, "Builder"), new Class[]{Integer.TYPE});
            }

            @Override // com.google.protobuf.a0.e.C0043e, com.google.protobuf.a0.e.a
            public final void a(a aVar, Object obj) {
                if (!this.a.isInstance(obj)) {
                    obj = ((q0.a) a0.F(null, this.c, new Object[0])).R((q0) obj).build();
                }
                super.a(aVar, obj);
            }

            @Override // com.google.protobuf.a0.e.C0043e, com.google.protobuf.a0.e.a
            public final q0.a b() {
                return (q0.a) a0.F(null, this.c, new Object[0]);
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static final class g extends h {
            public final n.d f;
            public final Method g;
            public final Method h;
            public final boolean i;
            public final Method j;
            public final Method k;
            public final Method l;

            public g(n.f fVar, String str, Class<? extends a0> cls, Class<? extends a> cls2, String str2) {
                super(fVar, str, cls, cls2, str2);
                this.f = fVar.j();
                this.g = a0.E(this.a, "valueOf", new Class[]{n.e.class});
                this.h = a0.E(this.a, "getValueDescriptor", new Class[0]);
                boolean z = fVar.h.j() == 3;
                this.i = z;
                if (z) {
                    this.j = a0.E(cls, gq2.g("get", str, "Value"), new Class[0]);
                    this.k = a0.E(cls2, gq2.g("get", str, "Value"), new Class[0]);
                    this.l = a0.E(cls2, gq2.g("set", str, "Value"), new Class[]{Integer.TYPE});
                }
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final Object d(a0 a0Var) {
                if (this.i) {
                    return this.f.i(((Integer) a0.F(a0Var, this.j, new Object[0])).intValue());
                }
                return a0.F(super.d(a0Var), this.h, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final void f(a aVar, Object obj) {
                if (this.i) {
                    a0.F(aVar, this.l, new Object[]{Integer.valueOf(((n.e) obj).e.i)});
                } else {
                    super.f(aVar, a0.F(null, this.g, new Object[]{obj}));
                }
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final Object g(a aVar) {
                if (this.i) {
                    return this.f.i(((Integer) a0.F(aVar, this.k, new Object[0])).intValue());
                }
                return a0.F(super.g(aVar), this.h, new Object[0]);
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static class h implements a {
            public final Class<?> a;
            public final n.f b;
            public final boolean c;
            public final boolean d;
            public final a e;

            /* JADX INFO: compiled from: GeneratedMessageV3.java */
            public static final class a {
                public final Method a;
                public final Method b;
                public final Method c;
                public final Method d;
                public final Method e;
                public final Method f;
                public final Method g;

                public a(String str, Class cls, Class cls2, String str2, boolean z, boolean z2) {
                    Method methodE = a0.E(cls, C0175b.j("get", str), new Class[0]);
                    this.a = methodE;
                    this.b = a0.E(cls2, C0175b.j("get", str), new Class[0]);
                    this.c = a0.E(cls2, C0175b.j("set", str), new Class[]{methodE.getReturnType()});
                    this.d = z2 ? a0.E(cls, C0175b.j("has", str), new Class[0]) : null;
                    this.e = z2 ? a0.E(cls2, C0175b.j("has", str), new Class[0]) : null;
                    a0.E(cls2, C0175b.j("clear", str), new Class[0]);
                    this.f = z ? a0.E(cls, gq2.g("get", str2, "Case"), new Class[0]) : null;
                    this.g = z ? a0.E(cls2, gq2.g("get", str2, "Case"), new Class[0]) : null;
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x004e  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public h(com.google.protobuf.n.f r14, java.lang.String r15, java.lang.Class<? extends com.google.protobuf.a0> r16, java.lang.Class<? extends com.google.protobuf.a0.a> r17, java.lang.String r18) {
                /*
                    r13 = this;
                    r0 = r13
                    r1 = r14
                    r13.<init>()
                    com.google.protobuf.n$j r2 = r1.n
                    r3 = 1
                    r4 = 0
                    if (r2 == 0) goto L1d
                    com.google.protobuf.n$f[] r5 = r2.k
                    int r6 = r5.length
                    if (r6 != r3) goto L18
                    r5 = r5[r4]
                    boolean r5 = r5.j
                    if (r5 == 0) goto L18
                    r5 = r3
                    goto L19
                L18:
                    r5 = r4
                L19:
                    if (r5 != 0) goto L1d
                    r11 = r3
                    goto L1e
                L1d:
                    r11 = r4
                L1e:
                    r0.c = r11
                    com.google.protobuf.n$g r5 = r1.h
                    int r6 = r5.j()
                    r7 = 2
                    if (r6 == r7) goto L4e
                    boolean r6 = r1.j
                    if (r6 != 0) goto L3e
                    int r5 = r5.j()
                    if (r5 != r7) goto L3c
                    boolean r5 = r14.n()
                    if (r5 == 0) goto L3c
                    if (r2 != 0) goto L3c
                    goto L3e
                L3c:
                    r2 = r4
                    goto L3f
                L3e:
                    r2 = r3
                L3f:
                    if (r2 != 0) goto L4e
                    if (r11 != 0) goto L4c
                    com.google.protobuf.n$f$c r2 = r1.k
                    com.google.protobuf.n$f$b r2 = r2.e
                    com.google.protobuf.n$f$b r5 = com.google.protobuf.n.f.b.n
                    if (r2 != r5) goto L4c
                    goto L4e
                L4c:
                    r12 = r4
                    goto L4f
                L4e:
                    r12 = r3
                L4f:
                    r0.d = r12
                    com.google.protobuf.a0$e$h$a r2 = new com.google.protobuf.a0$e$h$a
                    r6 = r2
                    r7 = r15
                    r8 = r16
                    r9 = r17
                    r10 = r18
                    r6.<init>(r7, r8, r9, r10, r11, r12)
                    r0.b = r1
                    java.lang.reflect.Method r1 = r2.a
                    java.lang.Class r1 = r1.getReturnType()
                    r0.a = r1
                    r0.e = r2
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.a0.e.h.<init>(com.google.protobuf.n$f, java.lang.String, java.lang.Class, java.lang.Class, java.lang.String):void");
            }

            @Override // com.google.protobuf.a0.e.a
            public final void a(a aVar, Object obj) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override // com.google.protobuf.a0.e.a
            public q0.a b() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.a0.e.a
            public Object c(a0 a0Var) {
                return d(a0Var);
            }

            @Override // com.google.protobuf.a0.e.a
            public Object d(a0 a0Var) {
                return a0.F(a0Var, this.e.a, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.a
            public final boolean e(a0 a0Var) {
                boolean z = this.d;
                a aVar = this.e;
                if (z) {
                    return ((Boolean) a0.F(a0Var, aVar.d, new Object[0])).booleanValue();
                }
                boolean z2 = this.c;
                n.f fVar = this.b;
                if (z2) {
                    return ((c0.a) a0.F(a0Var, aVar.f, new Object[0])).b() == fVar.f.i;
                }
                return !d(a0Var).equals(fVar.i());
            }

            @Override // com.google.protobuf.a0.e.a
            public void f(a aVar, Object obj) {
                a aVar2 = this.e;
                aVar2.getClass();
                a0.F(aVar, aVar2.c, new Object[]{obj});
            }

            @Override // com.google.protobuf.a0.e.a
            public Object g(a aVar) {
                return a0.F(aVar, this.e.b, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.a
            public final boolean h(a aVar) {
                boolean z = this.d;
                a aVar2 = this.e;
                if (z) {
                    return ((Boolean) a0.F(aVar, aVar2.e, new Object[0])).booleanValue();
                }
                boolean z2 = this.c;
                n.f fVar = this.b;
                if (z2) {
                    return ((c0.a) a0.F(aVar, aVar2.g, new Object[0])).b() == fVar.f.i;
                }
                return !g(aVar).equals(fVar.i());
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static final class i extends h {
            public final Method f;

            public i(n.f fVar, String str, Class<? extends a0> cls, Class<? extends a> cls2, String str2) {
                super(fVar, str, cls, cls2, str2);
                this.f = a0.E(this.a, "newBuilder", new Class[0]);
                a0.E(cls2, gq2.g("get", str, "Builder"), new Class[0]);
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final q0.a b() {
                return (q0.a) a0.F(null, this.f, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final void f(a aVar, Object obj) {
                if (!this.a.isInstance(obj)) {
                    obj = ((q0.a) a0.F(null, this.f, new Object[0])).R((q0) obj).d();
                }
                super.f(aVar, obj);
            }
        }

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public static final class j extends h {
            public final Method f;
            public final Method g;

            public j(n.f fVar, String str, Class<? extends a0> cls, Class<? extends a> cls2, String str2) {
                super(fVar, str, cls, cls2, str2);
                this.f = a0.E(cls, gq2.g("get", str, "Bytes"), new Class[0]);
                a0.E(cls2, gq2.g("get", str, "Bytes"), new Class[0]);
                this.g = a0.E(cls2, gq2.g("set", str, "Bytes"), new Class[]{lk.class});
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final Object c(a0 a0Var) {
                return a0.F(a0Var, this.f, new Object[0]);
            }

            @Override // com.google.protobuf.a0.e.h, com.google.protobuf.a0.e.a
            public final void f(a aVar, Object obj) {
                if (obj instanceof lk) {
                    a0.F(aVar, this.g, new Object[]{obj});
                } else {
                    super.f(aVar, obj);
                }
            }
        }

        public e(n.a aVar, String[] strArr) {
            this.a = aVar;
            this.c = strArr;
            this.b = new a[aVar.k().size()];
            this.d = new c[aVar.m().size()];
        }

        public static c a(e eVar, n.j jVar) {
            eVar.getClass();
            if (jVar.i == eVar.a) {
                return eVar.d[jVar.e];
            }
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }

        public static a b(e eVar, n.f fVar) {
            eVar.getClass();
            if (fVar.l != eVar.a) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
            if (fVar.l()) {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
            return eVar.b[fVar.e];
        }

        public final void c(Class cls, Class cls2) {
            if (this.e) {
                return;
            }
            synchronized (this) {
                if (this.e) {
                    return;
                }
                int length = this.b.length;
                for (int i2 = 0; i2 < length; i2++) {
                    n.f fVar = this.a.k().get(i2);
                    n.j jVar = fVar.n;
                    String str = jVar != null ? this.c[jVar.e + length] : null;
                    if (fVar.c()) {
                        n.f.b bVar = fVar.k.e;
                        if (bVar == n.f.b.n) {
                            if (fVar.m()) {
                                String str2 = this.c[i2];
                                new b(fVar, cls);
                                throw null;
                            }
                            this.b[i2] = new f(this.c[i2], cls, cls2);
                        } else if (bVar == n.f.b.m) {
                            this.b[i2] = new d(fVar, this.c[i2], cls, cls2);
                        } else {
                            this.b[i2] = new C0043e(this.c[i2], cls, cls2);
                        }
                    } else {
                        n.f.b bVar2 = fVar.k.e;
                        if (bVar2 == n.f.b.n) {
                            this.b[i2] = new i(fVar, this.c[i2], cls, cls2, str);
                        } else if (bVar2 == n.f.b.m) {
                            this.b[i2] = new g(fVar, this.c[i2], cls, cls2, str);
                        } else if (bVar2 == n.f.b.k) {
                            this.b[i2] = new j(fVar, this.c[i2], cls, cls2, str);
                        } else {
                            this.b[i2] = new h(fVar, this.c[i2], cls, cls2, str);
                        }
                    }
                }
                int length2 = this.d.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    this.d[i3] = new c(this.a, i3, this.c[i3 + length], cls, cls2);
                }
                this.e = true;
                this.c = null;
            }
        }
    }

    public a0() {
        this.f = j1.f;
    }

    public static Method E(Class cls, String str, Class[] clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e2);
        }
    }

    public static Object F(Object obj, Method method, Object[] objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static int H(int i, Object obj) {
        return obj instanceof String ? nu.f0(i, (String) obj) : nu.K(i, (lk) obj);
    }

    public static int I(Object obj) {
        return obj instanceof String ? nu.g0((String) obj) : nu.L((lk) obj);
    }

    public static b0 N(c0.c cVar) {
        b0 b0Var = (b0) cVar;
        int i = b0Var.g;
        int i2 = i == 0 ? 10 : i * 2;
        if (i2 >= i) {
            return new b0(b0Var.g, Arrays.copyOf(b0Var.f, i2));
        }
        throw new IllegalArgumentException();
    }

    public static void P(nu nuVar, int i, Object obj) {
        if (obj instanceof String) {
            nuVar.K0(i, (String) obj);
        } else {
            nuVar.r0(i, (lk) obj);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.TreeMap J(boolean r10) {
        /*
            r9 = this;
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            com.google.protobuf.a0$e r1 = r9.M()
            com.google.protobuf.n$a r1 = r1.a
            java.util.List r1 = r1.k()
            r2 = 0
            r3 = r2
        L11:
            int r4 = r1.size()
            if (r3 >= r4) goto Lbd
            java.lang.Object r4 = r1.get(r3)
            com.google.protobuf.n$f r4 = (com.google.protobuf.n.f) r4
            com.google.protobuf.n$j r5 = r4.n
            r6 = 1
            if (r5 == 0) goto L7c
            int r4 = r5.j
            int r4 = r4 - r6
            int r3 = r3 + r4
            com.google.protobuf.a0$e r4 = r9.M()
            com.google.protobuf.a0$e$c r4 = com.google.protobuf.a0.e.a(r4, r5)
            com.google.protobuf.n$f r7 = r4.d
            if (r7 == 0) goto L37
            boolean r4 = r9.k(r7)
            goto L4a
        L37:
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.reflect.Method r4 = r4.b
            java.lang.Object r4 = F(r9, r4, r7)
            com.google.protobuf.c0$a r4 = (com.google.protobuf.c0.a) r4
            int r4 = r4.b()
            if (r4 != 0) goto L49
            r4 = r2
            goto L4a
        L49:
            r4 = r6
        L4a:
            if (r4 != 0) goto L4e
            goto Lba
        L4e:
            com.google.protobuf.a0$e r4 = r9.M()
            com.google.protobuf.a0$e$c r4 = com.google.protobuf.a0.e.a(r4, r5)
            com.google.protobuf.n$f r5 = r4.d
            r7 = 0
            if (r5 == 0) goto L63
            boolean r4 = r9.k(r5)
            if (r4 == 0) goto L7a
            r4 = r5
            goto L99
        L63:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.reflect.Method r8 = r4.b
            java.lang.Object r5 = F(r9, r8, r5)
            com.google.protobuf.c0$a r5 = (com.google.protobuf.c0.a) r5
            int r5 = r5.b()
            if (r5 <= 0) goto L7a
            com.google.protobuf.n$a r4 = r4.a
            com.google.protobuf.n$f r4 = r4.j(r5)
            goto L99
        L7a:
            r4 = r7
            goto L99
        L7c:
            boolean r5 = r4.c()
            if (r5 == 0) goto L92
            java.lang.Object r5 = r9.o(r4)
            java.util.List r5 = (java.util.List) r5
            boolean r7 = r5.isEmpty()
            if (r7 != 0) goto Lba
            r0.put(r4, r5)
            goto Lba
        L92:
            boolean r5 = r9.k(r4)
            if (r5 != 0) goto L99
            goto Lba
        L99:
            if (r10 == 0) goto Lb3
            com.google.protobuf.n$f$c r5 = r4.k
            com.google.protobuf.n$f$b r5 = r5.e
            com.google.protobuf.n$f$b r7 = com.google.protobuf.n.f.b.k
            if (r5 != r7) goto Lb3
            com.google.protobuf.a0$e r5 = r9.M()
            com.google.protobuf.a0$e$a r5 = com.google.protobuf.a0.e.b(r5, r4)
            java.lang.Object r5 = r5.c(r9)
            r0.put(r4, r5)
            goto Lba
        Lb3:
            java.lang.Object r5 = r9.o(r4)
            r0.put(r4, r5)
        Lba:
            int r3 = r3 + r6
            goto L11
        Lbd:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.a0.J(boolean):java.util.TreeMap");
    }

    public Map<n.f, Object> K() {
        return Collections.unmodifiableMap(J(true));
    }

    public abstract e M();

    public Object O() {
        throw new UnsupportedOperationException("This method must be overridden by the subclass.");
    }

    @Override // com.google.protobuf.t0
    public final n.a c() {
        return M().a;
    }

    @Override // defpackage.bh1
    public boolean isInitialized() {
        for (n.f fVar : c().k()) {
            if (fVar.p() && !k(fVar)) {
                return false;
            }
            if (fVar.k.e == n.f.b.n) {
                if (fVar.c()) {
                    Iterator it = ((List) o(fVar)).iterator();
                    while (it.hasNext()) {
                        if (!((q0) it.next()).isInitialized()) {
                            return false;
                        }
                    }
                } else if (k(fVar) && !((q0) o(fVar)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.google.protobuf.t0
    public boolean k(n.f fVar) {
        return e.b(M(), fVar).e(this);
    }

    @Override // com.google.protobuf.r0
    public int m() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        int iB = u0.b(this, K());
        this.e = iB;
        return iB;
    }

    @Override // com.google.protobuf.t0
    public j1 n() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    @Override // com.google.protobuf.t0
    public Object o(n.f fVar) {
        return e.b(M(), fVar).d(this);
    }

    @Override // com.google.protobuf.r0
    public void p(nu nuVar) {
        u0.e(this, K(), nuVar);
    }

    @Override // com.google.protobuf.t0
    public Map<n.f, Object> s() {
        return Collections.unmodifiableMap(J(false));
    }

    @Override // com.google.protobuf.r0
    public wv1<? extends a0> u() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public Object writeReplace() {
        return new z.g(this);
    }

    /* JADX INFO: compiled from: GeneratedMessageV3.java */
    public static abstract class d<MessageType extends d> extends a0 implements t0 {
        private static final long serialVersionUID = 1;
        public final x<n.f> g;

        /* JADX INFO: compiled from: GeneratedMessageV3.java */
        public class a {
            public final Iterator<Map.Entry<n.f, Object>> a;
            public Map.Entry<n.f, Object> b;
            public final boolean c;

            public a(d dVar) {
                Iterator itS = dVar.g.s();
                this.a = itS;
                if (itS.hasNext()) {
                    this.b = (Map.Entry) itS.next();
                }
                this.c = false;
            }

            public final void a(nu nuVar) {
                while (true) {
                    Map.Entry<n.f, Object> entry = this.b;
                    if (entry == null || entry.getKey().f.i >= 536870912) {
                        return;
                    }
                    n.f key = this.b.getKey();
                    if (this.c && key.w() == n1.b.n && !key.c()) {
                        Map.Entry<n.f, Object> entry2 = this.b;
                        boolean z = entry2 instanceof e0.a;
                        m.g gVar = key.f;
                        if (z) {
                            nuVar.J0(gVar.i, ((e0.a) entry2).e.getValue().b());
                        } else {
                            nuVar.I0(gVar.i, (q0) entry2.getValue());
                        }
                    } else {
                        x.A(key, this.b.getValue(), nuVar);
                    }
                    Iterator<Map.Entry<n.f, Object>> it = this.a;
                    if (it.hasNext()) {
                        this.b = it.next();
                    } else {
                        this.b = null;
                    }
                }
            }
        }

        public d() {
            this.g = new x<>();
        }

        @Override // com.google.protobuf.a0
        public final Map<n.f, Object> K() {
            TreeMap treeMapJ = J(false);
            treeMapJ.putAll(T());
            return Collections.unmodifiableMap(treeMapJ);
        }

        public final boolean Q() {
            return this.g.p();
        }

        public final int S() {
            return this.g.m();
        }

        public final Map<n.f, Object> T() {
            return this.g.i();
        }

        public final void U() {
            this.g.t();
        }

        public final boolean V(g gVar, j1.a aVar, s sVar, int i) {
            gVar.getClass();
            return u0.c(gVar, aVar, sVar, c(), new u0.b(this.g), i);
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public boolean isInitialized() {
            return super.isInitialized() && Q();
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final boolean k(n.f fVar) {
            if (!fVar.l()) {
                return super.k(fVar);
            }
            if (fVar.l == c()) {
                return this.g.n(fVar);
            }
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final Object o(n.f fVar) {
            if (!fVar.l()) {
                return super.o(fVar);
            }
            if (fVar.l != c()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
            Object objJ = this.g.j(fVar);
            return objJ == null ? fVar.c() ? Collections.emptyList() : fVar.k.e == n.f.b.n ? p.E(fVar.k()) : fVar.i() : objJ;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final Map<n.f, Object> s() {
            TreeMap treeMapJ = J(false);
            treeMapJ.putAll(T());
            return Collections.unmodifiableMap(treeMapJ);
        }

        public d(c<MessageType, ?> cVar) {
            x<n.f> xVar;
            super(cVar);
            x.a<n.f> aVar = cVar.g;
            if (aVar == null || aVar.a.isEmpty()) {
                xVar = x.d;
            } else {
                aVar.c = false;
                e1 e1Var = aVar.a;
                e1 e1Var2 = e1Var;
                if (aVar.d) {
                    d1 d1VarC = x.c(e1Var, false);
                    x.a.d(d1VarC);
                    e1Var2 = d1VarC;
                }
                x<n.f> xVar2 = new x<>(e1Var2);
                xVar2.c = aVar.b;
                xVar = xVar2;
            }
            this.g = xVar;
        }
    }

    public a0(a<?> aVar) {
        this.f = aVar.f;
    }
}
