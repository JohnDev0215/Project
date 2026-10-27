package com.google.protobuf;

/* JADX INFO: compiled from: WireFormat.java */
/* JADX INFO: loaded from: classes.dex */
public final class n1 {

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
    /* JADX INFO: compiled from: WireFormat.java */
    public static class a {
        public static final b g;
        public static final c h;
        public static final a i;
        public static final a j;
        public static final /* synthetic */ a[] k;
        public final b e;
        public final int f;

        /* JADX INFO: Fake field, exist only in values array */
        a EF0;

        /* JADX INFO: Fake field, exist only in values array */
        a EF1;

        /* JADX INFO: Fake field, exist only in values array */
        a EF2;

        /* JADX INFO: renamed from: com.google.protobuf.n1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: WireFormat.java */
        public enum C0053a extends a {
            public C0053a() {
                super("STRING", 8, b.k, 2);
            }
        }

        /* JADX INFO: compiled from: WireFormat.java */
        public enum b extends a {
            public b(b bVar) {
                super("GROUP", 9, bVar, 3);
            }

            @Override // com.google.protobuf.n1.a
            public final boolean a() {
                return false;
            }
        }

        /* JADX INFO: compiled from: WireFormat.java */
        public enum c extends a {
            public c(b bVar) {
                super("MESSAGE", 10, bVar, 2);
            }

            @Override // com.google.protobuf.n1.a
            public final boolean a() {
                return false;
            }
        }

        /* JADX INFO: compiled from: WireFormat.java */
        public enum d extends a {
            public d(b bVar) {
                super("BYTES", 11, bVar, 2);
            }

            @Override // com.google.protobuf.n1.a
            public final boolean a() {
                return false;
            }
        }

        static {
            a aVar = new a("DOUBLE", 0, b.i, 1);
            a aVar2 = new a("FLOAT", 1, b.h, 5);
            b bVar = b.g;
            a aVar3 = new a("INT64", 2, bVar, 0);
            a aVar4 = new a("UINT64", 3, bVar, 0);
            b bVar2 = b.f;
            a aVar5 = new a("INT32", 4, bVar2, 0);
            a aVar6 = new a("FIXED64", 5, bVar, 1);
            a aVar7 = new a("FIXED32", 6, bVar2, 5);
            a aVar8 = new a("BOOL", 7, b.j, 0);
            C0053a c0053a = new C0053a();
            b bVar3 = b.n;
            b bVar4 = new b(bVar3);
            g = bVar4;
            c cVar = new c(bVar3);
            h = cVar;
            d dVar = new d(b.l);
            a aVar9 = new a("UINT32", 12, bVar2, 0);
            i = aVar9;
            a aVar10 = new a("ENUM", 13, b.m, 0);
            j = aVar10;
            k = new a[]{aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, c0053a, bVar4, cVar, dVar, aVar9, aVar10, new a("SFIXED32", 14, bVar2, 5), new a("SFIXED64", 15, bVar, 1), new a("SINT32", 16, bVar2, 0), new a("SINT64", 17, bVar, 0)};
        }

        public /* synthetic */ a() {
            throw null;
        }

        public a(String str, int i2, b bVar, int i3) {
            this.e = bVar;
            this.f = i3;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) k.clone();
        }

        public boolean a() {
            return !(this instanceof C0053a);
        }
    }

    /* JADX INFO: compiled from: WireFormat.java */
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

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: WireFormat.java */
    public static abstract class c {
        public static final a e;
        public static final b f;
        public static final /* synthetic */ c[] g;

        /* JADX INFO: compiled from: WireFormat.java */
        public enum a extends c {
            public a() {
                super("LOOSE", 0);
            }

            @Override // com.google.protobuf.n1.c
            public final Object a(g gVar) {
                return gVar.D();
            }
        }

        /* JADX INFO: compiled from: WireFormat.java */
        public enum b extends c {
            public b() {
                super("STRICT", 1);
            }

            @Override // com.google.protobuf.n1.c
            public final Object a(g gVar) {
                return gVar.E();
            }
        }

        /* JADX INFO: renamed from: com.google.protobuf.n1$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: WireFormat.java */
        public enum C0054c extends c {
            public C0054c() {
                super("LAZY", 2);
            }

            @Override // com.google.protobuf.n1.c
            public final Object a(g gVar) {
                return gVar.m();
            }
        }

        static {
            a aVar = new a();
            e = aVar;
            b bVar = new b();
            f = bVar;
            g = new c[]{aVar, bVar, new C0054c()};
        }

        public c() {
            throw null;
        }

        public c(String str, int i) {
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) g.clone();
        }

        public abstract Object a(g gVar);
    }

    public static Object a(g gVar, a aVar, c cVar) {
        switch (aVar.ordinal()) {
            case 0:
                return Double.valueOf(gVar.n());
            case 1:
                return Float.valueOf(gVar.r());
            case 2:
                return Long.valueOf(gVar.u());
            case 3:
                return Long.valueOf(gVar.H());
            case 4:
                return Integer.valueOf(gVar.t());
            case 5:
                return Long.valueOf(gVar.q());
            case 6:
                return Integer.valueOf(gVar.p());
            case 7:
                return Boolean.valueOf(gVar.l());
            case 8:
                return cVar.a(gVar);
            case 9:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 10:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 11:
                return gVar.m();
            case 12:
                return Integer.valueOf(gVar.G());
            case 13:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            case 14:
                return Integer.valueOf(gVar.z());
            case 15:
                return Long.valueOf(gVar.A());
            case 16:
                return Integer.valueOf(gVar.B());
            case 17:
                return Long.valueOf(gVar.C());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }
}
