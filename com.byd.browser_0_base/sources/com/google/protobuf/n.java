package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.c0;
import com.google.protobuf.g1;
import com.google.protobuf.m;
import com.google.protobuf.n1;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import com.google.protobuf.x;
import defpackage.C0175b;
import defpackage.gq2;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: Descriptors.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static final int[] a = new int[0];
    public static final a[] b = new a[0];
    public static final f[] c = new f[0];
    public static final d[] d = new d[0];
    public static final k[] e = new k[0];
    public static final j[] f = new j[0];

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class b {
        public final Set<g> a;
        public final HashMap c = new HashMap();
        public final boolean b = true;

        /* JADX INFO: compiled from: Descriptors.java */
        public static final class a extends h {
            public final String e;
            public final String f;
            public final g g;

            public a(String str, String str2, g gVar) {
                this.g = gVar;
                this.f = str2;
                this.e = str;
            }

            @Override // com.google.protobuf.n.h
            public final g a() {
                return this.g;
            }

            @Override // com.google.protobuf.n.h
            public final String e() {
                return this.f;
            }

            @Override // com.google.protobuf.n.h
            public final String f() {
                return this.e;
            }

            @Override // com.google.protobuf.n.h
            public final q0 g() {
                return this.g.e;
            }
        }

        public b(g[] gVarArr) {
            this.a = Collections.newSetFromMap(new IdentityHashMap(gVarArr.length));
            for (g gVar : gVarArr) {
                this.a.add(gVar);
                d(gVar);
            }
            for (g gVar2 : this.a) {
                try {
                    a(gVar2, gVar2.e.T());
                } catch (c e) {
                    throw new AssertionError(e);
                }
            }
        }

        public final void a(g gVar, String str) throws c {
            String strSubstring;
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf == -1) {
                strSubstring = str;
            } else {
                a(gVar, str.substring(0, iLastIndexOf));
                strSubstring = str.substring(iLastIndexOf + 1);
            }
            a aVar = new a(strSubstring, str, gVar);
            HashMap map = this.c;
            h hVar = (h) map.put(str, aVar);
            if (hVar != null) {
                map.put(str, hVar);
                if (hVar instanceof a) {
                    return;
                }
                StringBuilder sbN = C0175b.n("\"", strSubstring, "\" is already defined (as something other than a package) in file \"");
                sbN.append(hVar.a().f());
                sbN.append("\".");
                throw new c(gVar, sbN.toString());
            }
        }

        public final void b(h hVar) throws c {
            String strF = hVar.f();
            if (strF.length() == 0) {
                throw new c(hVar, "Missing name.");
            }
            for (int i = 0; i < strF.length(); i++) {
                char cCharAt = strF.charAt(i);
                if (('a' > cCharAt || cCharAt > 'z') && (('A' > cCharAt || cCharAt > 'Z') && cCharAt != '_' && ('0' > cCharAt || cCharAt > '9' || i <= 0))) {
                    throw new c(hVar, gq2.g("\"", strF, "\" is not a valid identifier."));
                }
            }
            String strE = hVar.e();
            HashMap map = this.c;
            h hVar2 = (h) map.put(strE, hVar);
            if (hVar2 != null) {
                map.put(strE, hVar2);
                if (hVar.a() != hVar2.a()) {
                    StringBuilder sbN = C0175b.n("\"", strE, "\" is already defined in file \"");
                    sbN.append(hVar2.a().f());
                    sbN.append("\".");
                    throw new c(hVar, sbN.toString());
                }
                int iLastIndexOf = strE.lastIndexOf(46);
                if (iLastIndexOf == -1) {
                    throw new c(hVar, gq2.g("\"", strE, "\" is already defined."));
                }
                throw new c(hVar, "\"" + strE.substring(iLastIndexOf + 1) + "\" is already defined in \"" + strE.substring(0, iLastIndexOf) + "\".");
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0036, code lost:
        
            if (((r0 instanceof com.google.protobuf.n.a) || (r0 instanceof com.google.protobuf.n.d) || (r0 instanceof com.google.protobuf.n.b.a) || (r0 instanceof com.google.protobuf.n.k)) != false) goto L27;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.google.protobuf.n.h c(java.lang.String r7, int r8) {
            /*
                r6 = this;
                java.util.HashMap r0 = r6.c
                java.lang.Object r0 = r0.get(r7)
                com.google.protobuf.n$h r0 = (com.google.protobuf.n.h) r0
                r1 = 0
                r2 = 2
                r3 = 1
                r4 = 3
                if (r0 == 0) goto L39
                if (r8 == r4) goto L38
                if (r8 != r3) goto L20
                boolean r5 = r0 instanceof com.google.protobuf.n.a
                if (r5 != 0) goto L1d
                boolean r5 = r0 instanceof com.google.protobuf.n.d
                if (r5 == 0) goto L1b
                goto L1d
            L1b:
                r5 = r1
                goto L1e
            L1d:
                r5 = r3
            L1e:
                if (r5 != 0) goto L38
            L20:
                if (r8 != r2) goto L39
                boolean r5 = r0 instanceof com.google.protobuf.n.a
                if (r5 != 0) goto L35
                boolean r5 = r0 instanceof com.google.protobuf.n.d
                if (r5 != 0) goto L35
                boolean r5 = r0 instanceof com.google.protobuf.n.b.a
                if (r5 != 0) goto L35
                boolean r5 = r0 instanceof com.google.protobuf.n.k
                if (r5 == 0) goto L33
                goto L35
            L33:
                r5 = r1
                goto L36
            L35:
                r5 = r3
            L36:
                if (r5 == 0) goto L39
            L38:
                return r0
            L39:
                java.util.Set<com.google.protobuf.n$g> r6 = r6.a
                java.util.Iterator r6 = r6.iterator()
            L3f:
                boolean r0 = r6.hasNext()
                if (r0 == 0) goto L82
                java.lang.Object r0 = r6.next()
                com.google.protobuf.n$g r0 = (com.google.protobuf.n.g) r0
                com.google.protobuf.n$b r0 = r0.k
                java.util.HashMap r0 = r0.c
                java.lang.Object r0 = r0.get(r7)
                com.google.protobuf.n$h r0 = (com.google.protobuf.n.h) r0
                if (r0 == 0) goto L3f
                if (r8 == r4) goto L81
                if (r8 != r3) goto L69
                boolean r5 = r0 instanceof com.google.protobuf.n.a
                if (r5 != 0) goto L66
                boolean r5 = r0 instanceof com.google.protobuf.n.d
                if (r5 == 0) goto L64
                goto L66
            L64:
                r5 = r1
                goto L67
            L66:
                r5 = r3
            L67:
                if (r5 != 0) goto L81
            L69:
                if (r8 != r2) goto L3f
                boolean r5 = r0 instanceof com.google.protobuf.n.a
                if (r5 != 0) goto L7e
                boolean r5 = r0 instanceof com.google.protobuf.n.d
                if (r5 != 0) goto L7e
                boolean r5 = r0 instanceof com.google.protobuf.n.b.a
                if (r5 != 0) goto L7e
                boolean r5 = r0 instanceof com.google.protobuf.n.k
                if (r5 == 0) goto L7c
                goto L7e
            L7c:
                r5 = r1
                goto L7f
            L7e:
                r5 = r3
            L7f:
                if (r5 == 0) goto L3f
            L81:
                return r0
            L82:
                r6 = 0
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.n.b.c(java.lang.String, int):com.google.protobuf.n$h");
        }

        public final void d(g gVar) {
            for (g gVar2 : Collections.unmodifiableList(Arrays.asList(gVar.j))) {
                if (this.a.add(gVar2)) {
                    d(gVar2);
                }
            }
        }

        public final h e(String str, h hVar) throws c {
            h hVarC;
            String string;
            if (str.startsWith(".")) {
                string = str.substring(1);
                hVarC = c(string, 1);
            } else {
                int iIndexOf = str.indexOf(46);
                String strSubstring = iIndexOf == -1 ? str : str.substring(0, iIndexOf);
                StringBuilder sb = new StringBuilder(hVar.e());
                while (true) {
                    int iLastIndexOf = sb.lastIndexOf(".");
                    if (iLastIndexOf == -1) {
                        hVarC = c(str, 1);
                        string = str;
                        break;
                    }
                    int i = iLastIndexOf + 1;
                    sb.setLength(i);
                    sb.append(strSubstring);
                    h hVarC2 = c(sb.toString(), 2);
                    if (hVarC2 != null) {
                        if (iIndexOf != -1) {
                            sb.setLength(i);
                            sb.append(str);
                            hVarC = c(sb.toString(), 1);
                        } else {
                            hVarC = hVarC2;
                        }
                        string = sb.toString();
                    } else {
                        sb.setLength(iLastIndexOf);
                    }
                }
            }
            if (hVarC != null) {
                return hVarC;
            }
            if (!this.b) {
                throw new c(hVar, gq2.g("\"", str, "\" is not defined."));
            }
            a aVar = new a(string);
            this.a.add(aVar.g);
            return aVar;
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class d extends h {
        public final m.b e;
        public final String f;
        public final g g;
        public final e[] h;
        public final e[] i;
        public final int j;
        public HashMap k = null;
        public ReferenceQueue<e> l = null;

        /* JADX INFO: compiled from: Descriptors.java */
        public static class a extends WeakReference<e> {
            public final int a;

            public a(int i, e eVar) {
                super(eVar);
                this.a = i;
            }
        }

        public d(m.b bVar, g gVar, a aVar) throws c {
            this.e = bVar;
            this.f = n.a(gVar, aVar, bVar.Q());
            this.g = gVar;
            if (bVar.T() == 0) {
                throw new c(this, "Enums must contain at least one value.");
            }
            this.h = new e[bVar.T()];
            int i = 0;
            for (int i2 = 0; i2 < bVar.T(); i2++) {
                this.h[i2] = new e(bVar.i.get(i2), gVar, this);
            }
            e[] eVarArr = (e[]) this.h.clone();
            this.i = eVarArr;
            Arrays.sort(eVarArr, e.h);
            for (int i3 = 1; i3 < bVar.T(); i3++) {
                e[] eVarArr2 = this.i;
                e eVar = eVarArr2[i];
                e eVar2 = eVarArr2[i3];
                if (eVar.e.i != eVar2.e.i) {
                    i++;
                    eVarArr2[i] = eVar2;
                }
            }
            int i4 = i + 1;
            this.j = i4;
            Arrays.fill(this.i, i4, bVar.T(), (Object) null);
            gVar.k.b(this);
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.g;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.f;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.e.Q();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.e;
        }

        public final e h(int i) {
            int i2 = this.j - 1;
            int i3 = 0;
            while (i3 <= i2) {
                int i4 = (i3 + i2) / 2;
                e eVar = this.i[i4];
                int i5 = eVar.e.i;
                if (i < i5) {
                    i2 = i4 - 1;
                } else {
                    if (i <= i5) {
                        return eVar;
                    }
                    i3 = i4 + 1;
                }
            }
            return null;
        }

        public final e i(int i) {
            e eVar;
            e eVarH = h(i);
            if (eVarH != null) {
                return eVarH;
            }
            synchronized (this) {
                if (this.l == null) {
                    this.l = new ReferenceQueue<>();
                    this.k = new HashMap();
                } else {
                    while (true) {
                        a aVar = (a) this.l.poll();
                        if (aVar == null) {
                            break;
                        }
                        this.k.remove(Integer.valueOf(aVar.a));
                    }
                }
                WeakReference weakReference = (WeakReference) this.k.get(Integer.valueOf(i));
                eVar = weakReference == null ? null : (e) weakReference.get();
                if (eVar == null) {
                    eVar = new e(this, Integer.valueOf(i));
                    this.k.put(Integer.valueOf(i), new a(i, eVar));
                }
            }
            return eVar;
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class f extends h implements Comparable<f>, x.b<f> {
        public static final a q = new a();
        public static final n1.a[] r = n1.a.values();
        public final int e;
        public final m.g f;
        public final String g;
        public final g h;
        public final a i;
        public final boolean j;
        public c k;
        public a l;
        public a m;
        public final j n;
        public d o;
        public Object p;

        /* JADX INFO: compiled from: Descriptors.java */
        public static class a {
            public final int a(Object obj) {
                return ((f) obj).f.i;
            }
        }

        /* JADX INFO: compiled from: Descriptors.java */
        public enum b {
            f("INT"),
            g("LONG"),
            h("FLOAT"),
            i("DOUBLE"),
            j("BOOLEAN"),
            k("STRING"),
            l("BYTE_STRING"),
            m("ENUM"),
            n("MESSAGE");

            public final Object e;

            b(String str) {
                this.e = serializable;
            }
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF2' uses external variables
        	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
        	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
         */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX INFO: compiled from: Descriptors.java */
        public static final class c {
            public static final c f;
            public static final c g;
            public static final c h;
            public static final c i;
            public static final c j;
            public static final c[] k;
            public static final /* synthetic */ c[] l;
            public final b e;

            /* JADX INFO: Fake field, exist only in values array */
            c EF0;

            /* JADX INFO: Fake field, exist only in values array */
            c EF1;

            /* JADX INFO: Fake field, exist only in values array */
            c EF2;

            static {
                c cVar = new c("DOUBLE", 0, b.i);
                c cVar2 = new c("FLOAT", 1, b.h);
                b bVar = b.g;
                c cVar3 = new c("INT64", 2, bVar);
                c cVar4 = new c("UINT64", 3, bVar);
                b bVar2 = b.f;
                c cVar5 = new c("INT32", 4, bVar2);
                c cVar6 = new c("FIXED64", 5, bVar);
                c cVar7 = new c("FIXED32", 6, bVar2);
                c cVar8 = new c("BOOL", 7, b.j);
                c cVar9 = new c("STRING", 8, b.k);
                f = cVar9;
                b bVar3 = b.n;
                c cVar10 = new c("GROUP", 9, bVar3);
                g = cVar10;
                c cVar11 = new c("MESSAGE", 10, bVar3);
                h = cVar11;
                c cVar12 = new c("BYTES", 11, b.l);
                i = cVar12;
                c cVar13 = new c("UINT32", 12, bVar2);
                c cVar14 = new c("ENUM", 13, b.m);
                j = cVar14;
                l = new c[]{cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7, cVar8, cVar9, cVar10, cVar11, cVar12, cVar13, cVar14, new c("SFIXED32", 14, bVar2), new c("SFIXED64", 15, bVar), new c("SINT32", 16, bVar2), new c("SINT64", 17, bVar)};
                k = values();
            }

            public c(String str, int i2, b bVar) {
                this.e = bVar;
            }

            public static c valueOf(String str) {
                return (c) Enum.valueOf(c.class, str);
            }

            public static c[] values() {
                return (c[]) l.clone();
            }
        }

        static {
            if (c.k.length != m.g.d.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
            }
        }

        public f(m.g gVar, g gVar2, a aVar, int i, boolean z) throws c {
            this.e = i;
            this.f = gVar;
            this.g = n.a(gVar2, aVar, gVar.U());
            this.h = gVar2;
            if (gVar.b0()) {
                this.k = c.k[(m.g.d.a(gVar.k) == null ? m.g.d.TYPE_DOUBLE : r5).e - 1];
            }
            this.j = gVar.r;
            if (gVar.i <= 0) {
                throw new c(this, "Field numbers must be positive integers.");
            }
            if (z) {
                if (!gVar.Y()) {
                    throw new c(this, "FieldDescriptorProto.extendee not set for extension field.");
                }
                this.l = null;
                if (aVar != null) {
                    this.i = aVar;
                } else {
                    this.i = null;
                }
                if (gVar.Z()) {
                    throw new c(this, "FieldDescriptorProto.oneof_index set for extension field.");
                }
                this.n = null;
            } else {
                if (gVar.Y()) {
                    throw new c(this, "FieldDescriptorProto.extendee set for non-extension field.");
                }
                this.l = aVar;
                if (gVar.Z()) {
                    int i2 = gVar.o;
                    if (i2 < 0 || i2 >= aVar.e.T()) {
                        throw new c(this, "FieldDescriptorProto.oneof_index is out of range for type " + aVar.f());
                    }
                    j jVar = aVar.m().get(gVar.o);
                    this.n = jVar;
                    jVar.j++;
                } else {
                    this.n = null;
                }
                this.i = null;
            }
            gVar2.k.b(this);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static void h(f fVar) throws c {
            m.g gVar = fVar.f;
            boolean zY = gVar.Y();
            g gVar2 = fVar.h;
            if (zY) {
                h hVarE = gVar2.k.e(gVar.S(), fVar);
                if (!(hVarE instanceof a)) {
                    throw new c(fVar, "\"" + gVar.S() + "\" is not a message type.");
                }
                a aVar = (a) hVarE;
                fVar.l = aVar;
                int i = gVar.i;
                int iBinarySearch = Arrays.binarySearch(aVar.n, i);
                if (iBinarySearch < 0) {
                    iBinarySearch = (~iBinarySearch) - 1;
                }
                if (!(iBinarySearch >= 0 && i < aVar.o[iBinarySearch])) {
                    throw new c(fVar, "\"" + fVar.l.f + "\" does not declare " + gVar.i + " as an extension number.");
                }
            }
            if (gVar.c0()) {
                h hVarE2 = gVar2.k.e(gVar.W(), fVar);
                if (!gVar.b0()) {
                    if (hVarE2 instanceof a) {
                        fVar.k = c.h;
                    } else {
                        if (!(hVarE2 instanceof d)) {
                            throw new c(fVar, "\"" + gVar.W() + "\" is not a type.");
                        }
                        fVar.k = c.j;
                    }
                }
                b bVar = fVar.k.e;
                if (bVar == b.n) {
                    if (!(hVarE2 instanceof a)) {
                        throw new c(fVar, "\"" + gVar.W() + "\" is not a message type.");
                    }
                    fVar.m = (a) hVarE2;
                    if (gVar.X()) {
                        throw new c(fVar, "Messages can't have default values.");
                    }
                } else {
                    if (bVar != b.m) {
                        throw new c(fVar, "Field with primitive type has type_name.");
                    }
                    if (!(hVarE2 instanceof d)) {
                        throw new c(fVar, "\"" + gVar.W() + "\" is not an enum type.");
                    }
                    fVar.o = (d) hVarE2;
                }
            } else {
                b bVar2 = fVar.k.e;
                if (bVar2 == b.n || bVar2 == b.m) {
                    throw new c(fVar, "Field with message or enum type missing type_name.");
                }
            }
            if (gVar.V().j && !fVar.o()) {
                throw new c(fVar, "[packed = true] can only be specified for repeated primitive fields.");
            }
            if (gVar.X()) {
                if (fVar.c()) {
                    throw new c(fVar, "Repeated fields cannot have default values.");
                }
                try {
                    switch (fVar.k.ordinal()) {
                        case 0:
                            if (gVar.Q().equals("inf")) {
                                fVar.p = Double.valueOf(Double.POSITIVE_INFINITY);
                            } else if (gVar.Q().equals("-inf")) {
                                fVar.p = Double.valueOf(Double.NEGATIVE_INFINITY);
                            } else if (!gVar.Q().equals("nan")) {
                                fVar.p = Double.valueOf(gVar.Q());
                            } else {
                                fVar.p = Double.valueOf(Double.NaN);
                            }
                            break;
                        case 1:
                            if (gVar.Q().equals("inf")) {
                                fVar.p = Float.valueOf(Float.POSITIVE_INFINITY);
                            } else if (gVar.Q().equals("-inf")) {
                                fVar.p = Float.valueOf(Float.NEGATIVE_INFINITY);
                            } else if (!gVar.Q().equals("nan")) {
                                fVar.p = Float.valueOf(gVar.Q());
                            } else {
                                fVar.p = Float.valueOf(Float.NaN);
                            }
                            break;
                        case 2:
                        case 15:
                        case 17:
                            fVar.p = Long.valueOf(g1.c(gVar.Q(), true, true));
                            break;
                        case 3:
                        case 5:
                            fVar.p = Long.valueOf(g1.c(gVar.Q(), false, true));
                            break;
                        case 4:
                        case 14:
                        case 16:
                            fVar.p = Integer.valueOf((int) g1.c(gVar.Q(), true, false));
                            break;
                        case 6:
                        case 12:
                            fVar.p = Integer.valueOf((int) g1.c(gVar.Q(), false, false));
                            break;
                        case 7:
                            fVar.p = Boolean.valueOf(gVar.Q());
                            break;
                        case 8:
                            fVar.p = gVar.Q();
                            break;
                        case 9:
                        case 10:
                            throw new c(fVar, "Message type had default value.");
                        case 11:
                            try {
                                fVar.p = g1.d(gVar.Q());
                            } catch (g1.a e) {
                                c cVar = new c(fVar, "Couldn't parse default value: " + e.getMessage());
                                cVar.initCause(e);
                                throw cVar;
                            }
                            break;
                        case 13:
                            d dVar = fVar.o;
                            String strQ = gVar.Q();
                            h hVarC = dVar.g.k.c(dVar.f + '.' + strQ, 3);
                            e eVar = hVarC instanceof e ? (e) hVarC : null;
                            fVar.p = eVar;
                            if (eVar == null) {
                                throw new c(fVar, "Unknown enum default value: \"" + gVar.Q() + '\"');
                            }
                            break;
                            break;
                    }
                } catch (NumberFormatException e2) {
                    c cVar2 = new c(fVar, "Could not parse default value: \"" + gVar.Q() + '\"');
                    cVar2.initCause(e2);
                    throw cVar2;
                }
            } else if (fVar.c()) {
                fVar.p = Collections.emptyList();
            } else {
                int iOrdinal = fVar.k.e.ordinal();
                if (iOrdinal == 7) {
                    fVar.p = Collections.unmodifiableList(Arrays.asList(fVar.o.h)).get(0);
                } else if (iOrdinal != 8) {
                    fVar.p = fVar.k.e.e;
                } else {
                    fVar.p = null;
                }
            }
            a aVar2 = fVar.l;
            if (aVar2 == null || !aVar2.n().i) {
                return;
            }
            if (!fVar.l()) {
                throw new c(fVar, "MessageSets cannot have fields, only extensions.");
            }
            if (!fVar.n() || fVar.k != c.h) {
                throw new c(fVar, "Extensions of MessageSets must be optional messages.");
            }
        }

        @Override // com.google.protobuf.x.b
        public final boolean G() {
            if (!o()) {
                return false;
            }
            int iJ = this.h.j();
            m.g gVar = this.f;
            return iJ == 2 ? gVar.V().j : !gVar.V().W() || gVar.V().j;
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.h;
        }

        @Override // com.google.protobuf.x.b
        public final int b() {
            return this.f.i;
        }

        @Override // com.google.protobuf.x.b
        public final boolean c() {
            m.g.c cVarA = m.g.c.a(this.f.j);
            if (cVarA == null) {
                cVarA = m.g.c.LABEL_OPTIONAL;
            }
            return cVarA == m.g.c.LABEL_REPEATED;
        }

        @Override // java.lang.Comparable
        public final int compareTo(f fVar) {
            f fVar2 = fVar;
            if (fVar2.l == this.l) {
                return this.f.i - fVar2.f.i;
            }
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        }

        @Override // com.google.protobuf.x.b
        public final n1.a d() {
            return r[this.k.ordinal()];
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.g;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.f.U();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.f;
        }

        public final Object i() {
            if (this.k.e != b.n) {
                return this.p;
            }
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        }

        public final d j() {
            if (this.k.e == b.m) {
                return this.o;
            }
            throw new UnsupportedOperationException(String.format("This field is not of enum type. (%s)", this.g));
        }

        public final a k() {
            if (this.k.e == b.n) {
                return this.m;
            }
            throw new UnsupportedOperationException(String.format("This field is not of message type. (%s)", this.g));
        }

        public final boolean l() {
            return this.f.Y();
        }

        public final boolean m() {
            return this.k == c.h && c() && k().n().l;
        }

        public final boolean n() {
            m.g.c cVarA = m.g.c.a(this.f.j);
            if (cVarA == null) {
                cVarA = m.g.c.LABEL_OPTIONAL;
            }
            return cVarA == m.g.c.LABEL_OPTIONAL;
        }

        public final boolean o() {
            return c() && d().a();
        }

        public final boolean p() {
            m.g.c cVarA = m.g.c.a(this.f.j);
            if (cVarA == null) {
                cVarA = m.g.c.LABEL_OPTIONAL;
            }
            return cVarA == m.g.c.LABEL_REQUIRED;
        }

        public final boolean q() {
            if (this.k != c.f) {
                return false;
            }
            if (this.l.n().l) {
                return true;
            }
            g gVar = this.h;
            if (gVar.j() == 3) {
                return true;
            }
            return gVar.e.S().m;
        }

        @Override // com.google.protobuf.x.b
        public final r0.a t(r0.a aVar, r0 r0Var) {
            return ((q0.a) aVar).R((q0) r0Var);
        }

        public final String toString() {
            return this.g;
        }

        @Override // com.google.protobuf.x.b
        public final n1.b w() {
            return d().e;
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static abstract class h {
        public abstract g a();

        public abstract String e();

        public abstract String f();

        public abstract q0 g();
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class i extends h {
        public final m.l e;
        public final String f;
        public final g g;

        public i(m.l lVar, g gVar, k kVar) throws c {
            this.e = lVar;
            this.g = gVar;
            this.f = kVar.f + '.' + lVar.S();
            gVar.k.b(this);
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.g;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.f;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.e.S();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.e;
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class j extends h {
        public final int e;
        public final m.n f;
        public final String g;
        public final g h;
        public final a i;
        public int j = 0;
        public f[] k;

        public j(m.n nVar, g gVar, a aVar, int i) {
            this.f = nVar;
            this.g = n.a(gVar, aVar, nVar.Q());
            this.h = gVar;
            this.e = i;
            this.i = aVar;
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.h;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.g;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.f.Q();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.f;
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class k extends h {
        public final m.p e;
        public final String f;
        public final g g;
        public final i[] h;

        public k(m.p pVar, g gVar) throws c {
            this.e = pVar;
            this.f = n.a(gVar, null, pVar.Q());
            this.g = gVar;
            this.h = new i[pVar.i.size()];
            for (int i = 0; i < pVar.i.size(); i++) {
                this.h[i] = new i(pVar.i.get(i), gVar, this);
            }
            gVar.k.b(this);
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.g;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.f;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.e.Q();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.e;
        }
    }

    public static String a(g gVar, a aVar, String str) {
        if (aVar != null) {
            return aVar.f + '.' + str;
        }
        String strT = gVar.e.T();
        if (strT.isEmpty()) {
            return str;
        }
        return strT + '.' + str;
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static class c extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        public final q0 e;

        public c(h hVar, String str) {
            super(hVar.e() + ": " + str);
            hVar.e();
            this.e = hVar.g();
        }

        public c(g gVar, String str) {
            super(gVar.f() + ": " + str);
            gVar.f();
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class e extends h implements c0.a {
        public static final a h = new a();
        public final m.d e;
        public final String f;
        public final d g;

        /* JADX INFO: compiled from: Descriptors.java */
        public static class a implements Comparator<e> {
            @Override // java.util.Comparator
            public final int compare(e eVar, e eVar2) {
                return Integer.compare(eVar.e.i, eVar2.e.i);
            }
        }

        /* JADX INFO: compiled from: Descriptors.java */
        public static class b {
        }

        static {
            new b();
        }

        public e(m.d dVar, g gVar, d dVar2) throws c {
            this.e = dVar;
            this.g = dVar2;
            this.f = dVar2.f + '.' + dVar.Q();
            gVar.k.b(this);
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.g.g;
        }

        @Override // com.google.protobuf.c0.a
        public final int b() {
            return this.e.i;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.f;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.e.Q();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.e;
        }

        public final String toString() {
            return this.e.Q();
        }

        public e(d dVar, Integer num) {
            String str = "UNKNOWN_ENUM_VALUE_" + dVar.f() + "_" + num;
            m.d.b bVarU = m.d.U();
            str.getClass();
            bVarU.g |= 1;
            bVarU.h = str;
            bVarU.H();
            int iIntValue = num.intValue();
            bVarU.g |= 2;
            bVarU.i = iIntValue;
            bVarU.H();
            m.d dVarBuild = bVarU.build();
            this.e = dVarBuild;
            this.g = dVar;
            this.f = dVar.f + '.' + dVarBuild.Q();
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class g extends h {
        public final m.i e;
        public final a[] f;
        public final d[] g;
        public final k[] h;
        public final f[] i;
        public final g[] j;
        public final b k;

        public g(m.i iVar, g[] gVarArr, b bVar) throws c {
            this.k = bVar;
            this.e = iVar;
            HashMap map = new HashMap();
            for (g gVar : gVarArr) {
                map.put(gVar.f(), gVar);
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                c0.c cVar = iVar.k;
                if (i >= ((b0) cVar).g) {
                    g[] gVarArr2 = new g[arrayList.size()];
                    this.j = gVarArr2;
                    arrayList.toArray(gVarArr2);
                    bVar.a(this, this.e.T());
                    this.f = iVar.m.size() > 0 ? new a[iVar.m.size()] : n.b;
                    for (int i2 = 0; i2 < iVar.m.size(); i2++) {
                        this.f[i2] = new a(iVar.m.get(i2), this, null);
                    }
                    this.g = iVar.n.size() > 0 ? new d[iVar.n.size()] : n.d;
                    for (int i3 = 0; i3 < iVar.n.size(); i3++) {
                        this.g[i3] = new d(iVar.n.get(i3), this, null);
                    }
                    this.h = iVar.o.size() > 0 ? new k[iVar.o.size()] : n.e;
                    for (int i4 = 0; i4 < iVar.o.size(); i4++) {
                        this.h[i4] = new k(iVar.o.get(i4), this);
                    }
                    this.i = iVar.p.size() > 0 ? new f[iVar.p.size()] : n.c;
                    for (int i5 = 0; i5 < iVar.p.size(); i5++) {
                        this.i[i5] = new f(iVar.p.get(i5), this, null, i5, true);
                    }
                    return;
                }
                int iD = ((b0) cVar).d(i);
                if (iD < 0 || iD >= iVar.j.size()) {
                    break;
                }
                g gVar2 = (g) map.get((String) iVar.j.get(iD));
                if (gVar2 != null) {
                    arrayList.add(gVar2);
                }
                i++;
            }
            throw new c(this, "Invalid public dependency index.");
        }

        public static g h(m.i iVar, g[] gVarArr) {
            g gVar = new g(iVar, gVarArr, new b(gVarArr));
            for (a aVar : gVar.f) {
                aVar.h();
            }
            for (k kVar : gVar.h) {
                for (i iVar2 : kVar.h) {
                    g gVar2 = iVar2.g;
                    b bVar = gVar2.k;
                    m.l lVar = iVar2.e;
                    h hVarE = bVar.e(lVar.Q(), iVar2);
                    if (!(hVarE instanceof a)) {
                        throw new c(iVar2, "\"" + lVar.Q() + "\" is not a message type.");
                    }
                    h hVarE2 = gVar2.k.e(lVar.U(), iVar2);
                    if (!(hVarE2 instanceof a)) {
                        throw new c(iVar2, "\"" + lVar.U() + "\" is not a message type.");
                    }
                }
            }
            for (f fVar : gVar.i) {
                f.h(fVar);
            }
            return gVar;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.e.Q();
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.e.Q();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.e;
        }

        public final List<a> i() {
            return Collections.unmodifiableList(Arrays.asList(this.f));
        }

        public final int j() {
            return "proto3".equals(this.e.V()) ? 3 : 2;
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this;
        }

        public g(String str, a aVar) throws c {
            b bVar = new b(new g[0]);
            this.k = bVar;
            m.i.b bVarH = m.i.u.h();
            String str2 = aVar.f + ".placeholder.proto";
            str2.getClass();
            bVarH.g |= 1;
            bVarH.h = str2;
            bVarH.H();
            str.getClass();
            bVarH.g |= 2;
            bVarH.i = str;
            bVarH.H();
            bVarH.getClass();
            m.a aVar2 = aVar.e;
            aVar2.getClass();
            if ((bVarH.g & 32) == 0) {
                bVarH.m = new ArrayList(bVarH.m);
                bVarH.g |= 32;
            }
            bVarH.m.add(aVar2);
            bVarH.H();
            m.i iVarD = bVarH.d();
            if (iVarD.isInitialized()) {
                this.e = iVarD;
                this.j = new g[0];
                this.f = new a[]{aVar};
                this.g = n.d;
                this.h = n.e;
                this.i = n.c;
                bVar.a(this, str);
                bVar.b(aVar);
                return;
            }
            throw a.AbstractC0042a.z(iVarD);
        }
    }

    /* JADX INFO: compiled from: Descriptors.java */
    public static final class a extends h {
        public final m.a e;
        public final String f;
        public final g g;
        public final a[] h;
        public final d[] i;
        public final f[] j;
        public final f[] k;
        public final f[] l;
        public final j[] m;
        public final int[] n;
        public final int[] o;

        public a(m.a aVar, g gVar, a aVar2) throws c {
            this.e = aVar;
            this.f = n.a(gVar, aVar2, aVar.S());
            this.g = gVar;
            this.m = aVar.T() > 0 ? new j[aVar.T()] : n.f;
            int i = 0;
            for (int i2 = 0; i2 < aVar.T(); i2++) {
                this.m[i2] = new j(aVar.n.get(i2), gVar, this, i2);
            }
            this.h = aVar.k.size() > 0 ? new a[aVar.k.size()] : n.b;
            for (int i3 = 0; i3 < aVar.k.size(); i3++) {
                this.h[i3] = new a(aVar.k.get(i3), gVar, this);
            }
            this.i = aVar.l.size() > 0 ? new d[aVar.l.size()] : n.d;
            for (int i4 = 0; i4 < aVar.l.size(); i4++) {
                this.i[i4] = new d(aVar.l.get(i4), gVar, this);
            }
            int iQ = aVar.Q();
            f[] fVarArr = n.c;
            this.j = iQ > 0 ? new f[aVar.Q()] : fVarArr;
            for (int i5 = 0; i5 < aVar.Q(); i5++) {
                this.j[i5] = new f(aVar.i.get(i5), gVar, this, i5, false);
            }
            this.k = aVar.Q() > 0 ? (f[]) this.j.clone() : fVarArr;
            this.l = aVar.j.size() > 0 ? new f[aVar.j.size()] : fVarArr;
            for (int i6 = 0; i6 < aVar.j.size(); i6++) {
                this.l[i6] = new f(aVar.j.get(i6), gVar, this, i6, true);
            }
            for (int i7 = 0; i7 < aVar.T(); i7++) {
                j jVar = this.m[i7];
                jVar.k = new f[jVar.j];
                jVar.j = 0;
            }
            for (int i8 = 0; i8 < aVar.Q(); i8++) {
                f fVar = this.j[i8];
                j jVar2 = fVar.n;
                if (jVar2 != null) {
                    f[] fVarArr2 = jVar2.k;
                    int i9 = jVar2.j;
                    jVar2.j = i9 + 1;
                    fVarArr2[i9] = fVar;
                }
            }
            j[] jVarArr = this.m;
            int length = jVarArr.length;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                if (i10 >= length) {
                    int length2 = this.m.length;
                    gVar.k.b(this);
                    if (aVar.m.size() <= 0) {
                        int[] iArr = n.a;
                        this.n = iArr;
                        this.o = iArr;
                        return;
                    }
                    this.n = new int[aVar.m.size()];
                    this.o = new int[aVar.m.size()];
                    for (m.a.c cVar : aVar.m) {
                        this.n[i] = cVar.h;
                        this.o[i] = cVar.i;
                        i++;
                    }
                    Arrays.sort(this.n);
                    Arrays.sort(this.o);
                    return;
                }
                f[] fVarArr3 = jVarArr[i10].k;
                if (fVarArr3.length == 1 && fVarArr3[0].j) {
                    i11++;
                } else if (i11 > 0) {
                    throw new c(this, "Synthetic oneofs must come last.");
                }
                i10++;
            }
        }

        @Override // com.google.protobuf.n.h
        public final g a() {
            return this.g;
        }

        @Override // com.google.protobuf.n.h
        public final String e() {
            return this.f;
        }

        @Override // com.google.protobuf.n.h
        public final String f() {
            return this.e.S();
        }

        @Override // com.google.protobuf.n.h
        public final q0 g() {
            return this.e;
        }

        public final void h() throws c {
            for (a aVar : this.h) {
                aVar.h();
            }
            for (f fVar : this.j) {
                f.h(fVar);
            }
            f[] fVarArr = this.k;
            Arrays.sort(fVarArr);
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i2 >= fVarArr.length) {
                    for (f fVar2 : this.l) {
                        f.h(fVar2);
                    }
                    return;
                }
                f fVar3 = fVarArr[i];
                f fVar4 = fVarArr[i2];
                if (fVar3.f.i == fVar4.f.i) {
                    throw new c(fVar4, "Field number " + fVar4.f.i + " has already been used in \"" + fVar4.l.f + "\" by field \"" + fVar3.f() + "\".");
                }
                i = i2;
            }
        }

        public final f i(String str) {
            h hVarC = this.g.k.c(this.f + '.' + str, 3);
            if (hVarC instanceof f) {
                return (f) hVarC;
            }
            return null;
        }

        public final f j(int i) {
            f[] fVarArr = this.k;
            int length = fVarArr.length;
            f.a aVar = f.q;
            int i2 = length - 1;
            int i3 = 0;
            while (i3 <= i2) {
                int i4 = (i3 + i2) / 2;
                f fVar = fVarArr[i4];
                int iA = aVar.a(fVar);
                if (i < iA) {
                    i2 = i4 - 1;
                } else {
                    if (i <= iA) {
                        return fVar;
                    }
                    i3 = i4 + 1;
                }
            }
            return null;
        }

        public final List<f> k() {
            return Collections.unmodifiableList(Arrays.asList(this.j));
        }

        public final List<a> l() {
            return Collections.unmodifiableList(Arrays.asList(this.h));
        }

        public final List<j> m() {
            return Collections.unmodifiableList(Arrays.asList(this.m));
        }

        public final m.k n() {
            return this.e.U();
        }

        public a(String str) {
            String strSubstring;
            String strSubstring2;
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                strSubstring2 = str.substring(iLastIndexOf + 1);
                strSubstring = str.substring(0, iLastIndexOf);
            } else {
                strSubstring = "";
                strSubstring2 = str;
            }
            m.a.b bVarH = m.a.s.h();
            strSubstring2.getClass();
            bVarH.g |= 1;
            bVarH.h = strSubstring2;
            bVarH.H();
            m.a.c.b bVarH2 = m.a.c.l.h();
            bVarH2.g |= 1;
            bVarH2.h = 1;
            bVarH2.H();
            bVarH2.g |= 2;
            bVarH2.i = 536870912;
            bVarH2.H();
            m.a.c cVarD = bVarH2.d();
            if (cVarD.isInitialized()) {
                bVarH.getClass();
                if ((bVarH.g & 32) == 0) {
                    bVarH.m = new ArrayList(bVarH.m);
                    bVarH.g |= 32;
                }
                bVarH.m.add(cVarD);
                bVarH.H();
                m.a aVarD = bVarH.d();
                if (aVarD.isInitialized()) {
                    this.e = aVarD;
                    this.f = str;
                    this.h = n.b;
                    this.i = n.d;
                    f[] fVarArr = n.c;
                    this.j = fVarArr;
                    this.k = fVarArr;
                    this.l = fVarArr;
                    this.m = n.f;
                    this.g = new g(strSubstring, this);
                    this.n = new int[]{1};
                    this.o = new int[]{536870912};
                    return;
                }
                throw a.AbstractC0042a.z(aVarD);
            }
            throw a.AbstractC0042a.z(cVarD);
        }
    }
}
