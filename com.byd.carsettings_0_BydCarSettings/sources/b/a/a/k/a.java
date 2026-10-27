package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: ASMSerializerFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f309a = b.a.a.n.b.g(i0.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f310b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f311c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f312d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f313e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f314f;
    public static final String g;
    public static final String h;
    public static final String i;
    public final b.a.a.n.a j = new b.a.a.n.a();
    public final AtomicLong k = new AtomicLong();

    /* JADX INFO: renamed from: b.a.a.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASMSerializerFactory.java */
    public static class C0018a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static int f315a = 6;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static int f316b = 7;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static int f317c = 8;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b.a.a.n.c[] f318d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f319e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final z0 f320f;
        public final boolean g;
        public Map<String, Integer> h = new HashMap();
        public int i = 9;
        public final boolean j;

        public C0018a(b.a.a.n.c[] cVarArr, z0 z0Var, String str, boolean z, boolean z2) {
            this.f318d = cVarArr;
            this.f319e = str;
            this.f320f = z0Var;
            this.g = z;
            this.j = z2 || z0Var.f417a.isEnum();
        }

        public int f(String str) {
            int length = this.f318d.length;
            for (int i = 0; i < length; i++) {
                if (this.f318d[i].f429a.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        public int g(String str) {
            if (this.h.get(str) == null) {
                Map<String, Integer> map = this.h;
                int i = this.i;
                this.i = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return this.h.get(str).intValue();
        }

        public int h(String str, int i) {
            if (this.h.get(str) == null) {
                this.h.put(str, Integer.valueOf(this.i));
                this.i += i;
            }
            return this.h.get(str).intValue();
        }
    }

    static {
        String strG = b.a.a.n.b.g(t0.class);
        f310b = strG;
        f311c = "L" + strG + ";";
        String strG2 = b.a.a.n.b.g(d1.class);
        f312d = strG2;
        f313e = "L" + strG2 + ";";
        f314f = b.a.a.n.b.g(j0.class);
        g = "L" + b.a.a.n.b.g(j0.class) + ";";
        h = b.a.a.n.b.b(y0.class);
        i = b.a.a.n.b.b(c1.class);
    }

    public final void A(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c[] cVarArr, C0018a c0018a) throws Exception {
        String str;
        int i2;
        int i3;
        String str2;
        String str3;
        boolean z;
        char c2;
        a aVar;
        char c3;
        b.a.a.i.f fVar;
        String str4;
        int i4;
        int i5;
        Class<?> cls2;
        int i6;
        b.a.a.i.f fVar2;
        b.a.a.i.f fVar3;
        String str5;
        b.a.a.i.f fVar4;
        b.a.a.i.f fVar5;
        int i7;
        int i8;
        String str6;
        int i9;
        b.a.a.i.f fVar6;
        char c4;
        a aVar2 = this;
        b.a.a.n.c[] cVarArr2 = cVarArr;
        b.a.a.i.f fVar7 = new b.a.a.i.f();
        hVar.k(25, 1);
        hVar.k(25, 0);
        String str7 = f309a;
        hVar.d(182, str7, "hasPropertyFilters", "(" + i + ")Z");
        hVar.b(154, fVar7);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(25, 3);
        hVar.k(25, 4);
        hVar.k(21, 5);
        String str8 = f314f;
        StringBuilder sb = new StringBuilder();
        String str9 = "(L";
        sb.append("(L");
        sb.append(str7);
        String str10 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V";
        sb.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        hVar.d(183, str8, "writeNoneASM", sb.toString());
        hVar.h(177);
        hVar.l(fVar7);
        String str11 = "out";
        hVar.k(25, c0018a.g("out"));
        hVar.k(16, 91);
        String str12 = f312d;
        String str13 = "(I)V";
        hVar.d(182, str12, "write", "(I)V");
        int length = cVarArr2.length;
        if (length == 0) {
            hVar.k(25, c0018a.g("out"));
            hVar.k(16, 93);
            hVar.d(182, str12, "write", "(I)V");
            return;
        }
        int i10 = 0;
        while (i10 < length) {
            int i11 = i10 == length + (-1) ? 93 : 44;
            b.a.a.n.c cVar = cVarArr2[i10];
            Class<?> cls3 = cVar.f433e;
            hVar.i(cVar.f429a);
            hVar.k(58, C0018a.f315a);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str = str11;
                i2 = length;
                int i12 = i11;
                i3 = i10;
                str2 = str9;
                str3 = str13;
                z = false;
                c2 = 25;
                hVar.k(25, c0018a.g(str));
                hVar.h(89);
                aVar = this;
                aVar.i(hVar, c0018a, cVar);
                String str14 = f312d;
                hVar.d(182, str14, "writeInt", str3);
                c3 = 16;
                hVar.k(16, i12);
                hVar.d(182, str14, "write", str3);
            } else {
                if (cls3 == Long.TYPE) {
                    hVar.k(25, c0018a.g(str11));
                    hVar.h(89);
                    aVar2.i(hVar, c0018a, cVar);
                    String str15 = f312d;
                    c4 = 182;
                    hVar.d(182, str15, "writeLong", "(J)V");
                    hVar.k(16, i11);
                    hVar.d(182, str15, "write", str13);
                } else if (cls3 == Float.TYPE) {
                    hVar.k(25, c0018a.g(str11));
                    hVar.h(89);
                    aVar2.i(hVar, c0018a, cVar);
                    hVar.h(4);
                    String str16 = f312d;
                    c4 = 182;
                    hVar.d(182, str16, "writeFloat", "(FZ)V");
                    hVar.k(16, i11);
                    hVar.d(182, str16, "write", str13);
                } else if (cls3 == Double.TYPE) {
                    hVar.k(25, c0018a.g(str11));
                    hVar.h(89);
                    aVar2.i(hVar, c0018a, cVar);
                    hVar.h(4);
                    String str17 = f312d;
                    c4 = 182;
                    hVar.d(182, str17, "writeDouble", "(DZ)V");
                    hVar.k(16, i11);
                    hVar.d(182, str17, "write", str13);
                } else {
                    if (cls3 == Boolean.TYPE) {
                        hVar.k(25, c0018a.g(str11));
                        hVar.h(89);
                        aVar2.i(hVar, c0018a, cVar);
                        String str18 = f312d;
                        hVar.d(182, str18, "write", "(Z)V");
                        hVar.k(16, i11);
                        hVar.d(182, str18, "write", str13);
                    } else if (cls3 == Character.TYPE) {
                        hVar.k(25, c0018a.g(str11));
                        aVar2.i(hVar, c0018a, cVar);
                        hVar.d(184, "java/lang/Character", "toString", "(C)Ljava/lang/String;");
                        hVar.k(16, i11);
                        hVar.d(182, f312d, "writeString", "(Ljava/lang/String;C)V");
                        i2 = length;
                        i3 = i10;
                        str2 = str9;
                        z = false;
                        c2 = 25;
                        aVar = aVar2;
                        str = str11;
                        c3 = 16;
                        str3 = str13;
                    } else if (cls3 == String.class) {
                        hVar.k(25, c0018a.g(str11));
                        aVar2.i(hVar, c0018a, cVar);
                        hVar.k(16, i11);
                        hVar.d(182, f312d, "writeString", "(Ljava/lang/String;C)V");
                        i2 = length;
                        c2 = 25;
                        i3 = i10;
                        str2 = str9;
                        str3 = str13;
                        z = false;
                        c3 = 16;
                        aVar = aVar2;
                        str = str11;
                    } else if (cls3.isEnum()) {
                        hVar.k(25, c0018a.g(str11));
                        hVar.h(89);
                        aVar2.i(hVar, c0018a, cVar);
                        String str19 = f312d;
                        hVar.d(182, str19, "writeEnum", "(Ljava/lang/Enum;)V");
                        hVar.k(16, i11);
                        hVar.d(182, str19, "write", str13);
                    } else if (List.class.isAssignableFrom(cls3)) {
                        Type type = cVar.f434f;
                        Type type2 = type instanceof Class ? Object.class : ((ParameterizedType) type).getActualTypeArguments()[0];
                        if (!(type2 instanceof Class) || (cls2 = (Class) type2) == Object.class) {
                            cls2 = null;
                        }
                        aVar2.i(hVar, c0018a, cVar);
                        i2 = length;
                        hVar.c(192, "java/util/List");
                        i3 = i10;
                        hVar.k(58, c0018a.g("list"));
                        if (cls2 == String.class && c0018a.g) {
                            hVar.k(25, c0018a.g(str11));
                            hVar.k(25, c0018a.g("list"));
                            hVar.d(182, f312d, "write", "(Ljava/util/List;)V");
                            str = str11;
                            i6 = i11;
                            str2 = str9;
                            str6 = str13;
                            i8 = 16;
                            i9 = 182;
                            i7 = 25;
                        } else {
                            b.a.a.i.f fVar8 = new b.a.a.i.f();
                            b.a.a.i.f fVar9 = new b.a.a.i.f();
                            i6 = i11;
                            hVar.k(25, c0018a.g("list"));
                            hVar.b(199, fVar9);
                            hVar.k(25, c0018a.g(str11));
                            String str20 = f312d;
                            Type type3 = type2;
                            String str21 = str10;
                            hVar.d(182, str20, "writeNull", "()V");
                            hVar.b(167, fVar8);
                            hVar.l(fVar9);
                            hVar.k(25, c0018a.g("list"));
                            hVar.d(185, "java/util/List", "size", "()I");
                            hVar.k(54, c0018a.g("size"));
                            hVar.k(25, c0018a.g(str11));
                            hVar.k(16, 91);
                            hVar.d(182, str20, "write", str13);
                            b.a.a.i.f fVar10 = new b.a.a.i.f();
                            b.a.a.i.f fVar11 = new b.a.a.i.f();
                            b.a.a.i.f fVar12 = new b.a.a.i.f();
                            hVar.h(3);
                            String str22 = str9;
                            hVar.k(54, c0018a.g("i"));
                            hVar.l(fVar10);
                            hVar.k(21, c0018a.g("i"));
                            hVar.k(21, c0018a.g("size"));
                            hVar.b(162, fVar12);
                            hVar.k(21, c0018a.g("i"));
                            hVar.b(153, fVar11);
                            hVar.k(25, c0018a.g(str11));
                            hVar.k(16, 44);
                            hVar.d(182, str20, "write", str13);
                            hVar.l(fVar11);
                            hVar.k(25, c0018a.g("list"));
                            hVar.k(21, c0018a.g("i"));
                            hVar.d(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
                            hVar.k(58, c0018a.g("list_item"));
                            b.a.a.i.f fVar13 = new b.a.a.i.f();
                            b.a.a.i.f fVar14 = new b.a.a.i.f();
                            String str23 = str13;
                            hVar.k(25, c0018a.g("list_item"));
                            hVar.b(199, fVar14);
                            hVar.k(25, c0018a.g(str11));
                            String str24 = str11;
                            hVar.d(182, str20, "writeNull", "()V");
                            hVar.b(167, fVar13);
                            hVar.l(fVar14);
                            b.a.a.i.f fVar15 = new b.a.a.i.f();
                            b.a.a.i.f fVar16 = new b.a.a.i.f();
                            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                                fVar2 = fVar10;
                                fVar3 = fVar13;
                                str5 = str20;
                                fVar4 = fVar12;
                                str10 = str21;
                                str2 = str22;
                                fVar5 = fVar16;
                            } else {
                                hVar.k(25, c0018a.g("list_item"));
                                str5 = str20;
                                fVar4 = fVar12;
                                hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
                                hVar.b(166, fVar16);
                                aVar2.k(c0018a, hVar, cVar, cls2);
                                hVar.k(58, c0018a.g("list_item_desc"));
                                b.a.a.i.f fVar17 = new b.a.a.i.f();
                                b.a.a.i.f fVar18 = new b.a.a.i.f();
                                if (c0018a.g) {
                                    hVar.k(25, c0018a.g("list_item_desc"));
                                    String str25 = f314f;
                                    hVar.c(193, str25);
                                    hVar.b(153, fVar17);
                                    hVar.k(25, c0018a.g("list_item_desc"));
                                    hVar.c(192, str25);
                                    hVar.k(25, 1);
                                    hVar.k(25, c0018a.g("list_item"));
                                    if (c0018a.j) {
                                        hVar.h(1);
                                        fVar2 = fVar10;
                                        fVar3 = fVar13;
                                    } else {
                                        hVar.k(21, c0018a.g("i"));
                                        fVar2 = fVar10;
                                        fVar3 = fVar13;
                                        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                    }
                                    hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
                                    hVar.i(Integer.valueOf(cVar.i));
                                    StringBuilder sb2 = new StringBuilder();
                                    str2 = str22;
                                    sb2.append(str2);
                                    sb2.append(f309a);
                                    str10 = str21;
                                    sb2.append(str10);
                                    fVar6 = fVar16;
                                    hVar.d(182, str25, "writeAsArrayNonContext", sb2.toString());
                                    hVar.b(167, fVar18);
                                    hVar.l(fVar17);
                                } else {
                                    fVar2 = fVar10;
                                    fVar3 = fVar13;
                                    str10 = str21;
                                    str2 = str22;
                                    fVar6 = fVar16;
                                }
                                hVar.k(25, c0018a.g("list_item_desc"));
                                hVar.k(25, 1);
                                hVar.k(25, c0018a.g("list_item"));
                                if (c0018a.j) {
                                    hVar.h(1);
                                } else {
                                    hVar.k(21, c0018a.g("i"));
                                    hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                }
                                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
                                hVar.i(Integer.valueOf(cVar.i));
                                hVar.d(185, f310b, "write", str2 + f309a + str10);
                                hVar.l(fVar18);
                                hVar.b(167, fVar15);
                                fVar5 = fVar6;
                            }
                            hVar.l(fVar5);
                            hVar.k(25, 1);
                            hVar.k(25, c0018a.g("list_item"));
                            if (c0018a.j) {
                                hVar.h(1);
                            } else {
                                hVar.k(21, c0018a.g("i"));
                                hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                            }
                            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                                hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                            } else {
                                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b((Class) type3)));
                                hVar.i(Integer.valueOf(cVar.i));
                                hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                            }
                            hVar.l(fVar15);
                            hVar.l(fVar3);
                            hVar.a(c0018a.g("i"), 1);
                            hVar.b(167, fVar2);
                            hVar.l(fVar4);
                            str = str24;
                            i7 = 25;
                            hVar.k(25, c0018a.g(str));
                            i8 = 16;
                            hVar.k(16, 93);
                            str6 = str23;
                            i9 = 182;
                            hVar.d(182, str5, "write", str6);
                            hVar.l(fVar8);
                        }
                        hVar.k(i7, c0018a.g(str));
                        hVar.k(i8, i6);
                        hVar.d(i9, f312d, "write", str6);
                        aVar = this;
                        str3 = str6;
                        z = false;
                        c3 = 16;
                        c2 = 25;
                    } else {
                        String str26 = str11;
                        i2 = length;
                        int i13 = i11;
                        i3 = i10;
                        str2 = str9;
                        String str27 = str13;
                        b.a.a.i.f fVar19 = new b.a.a.i.f();
                        b.a.a.i.f fVar20 = new b.a.a.i.f();
                        i(hVar, c0018a, cVar);
                        hVar.h(89);
                        hVar.k(58, c0018a.g("field_" + cVar.f433e.getName()));
                        hVar.b(199, fVar20);
                        hVar.k(25, c0018a.g(str26));
                        String str28 = f312d;
                        hVar.d(182, str28, "writeNull", "()V");
                        hVar.b(167, fVar19);
                        hVar.l(fVar20);
                        b.a.a.i.f fVar21 = new b.a.a.i.f();
                        b.a.a.i.f fVar22 = new b.a.a.i.f();
                        hVar.k(25, c0018a.g("field_" + cVar.f433e.getName()));
                        hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls3)));
                        hVar.b(166, fVar22);
                        j(c0018a, hVar, cVar);
                        hVar.k(58, c0018a.g("fied_ser"));
                        b.a.a.i.f fVar23 = new b.a.a.i.f();
                        b.a.a.i.f fVar24 = new b.a.a.i.f();
                        if (c0018a.g && Modifier.isPublic(cls3.getModifiers())) {
                            hVar.k(25, c0018a.g("fied_ser"));
                            String str29 = f314f;
                            hVar.c(193, str29);
                            hVar.b(153, fVar23);
                            str4 = "writeWithFieldName";
                            hVar.k(25, c0018a.g("fied_ser"));
                            hVar.c(192, str29);
                            hVar.k(25, 1);
                            hVar.k(25, c0018a.g("field_" + cVar.f433e.getName()));
                            hVar.k(25, C0018a.f315a);
                            hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls3)));
                            hVar.i(Integer.valueOf(cVar.i));
                            fVar = fVar22;
                            hVar.d(182, str29, "writeAsArrayNonContext", str2 + f309a + str10);
                            hVar.b(167, fVar24);
                            hVar.l(fVar23);
                        } else {
                            fVar = fVar22;
                            str4 = "writeWithFieldName";
                        }
                        hVar.k(25, c0018a.g("fied_ser"));
                        hVar.k(25, 1);
                        hVar.k(25, c0018a.g("field_" + cVar.f433e.getName()));
                        hVar.k(25, C0018a.f315a);
                        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls3)));
                        hVar.i(Integer.valueOf(cVar.i));
                        String str30 = f310b;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str2);
                        String str31 = f309a;
                        sb3.append(str31);
                        sb3.append(str10);
                        hVar.d(185, str30, "write", sb3.toString());
                        hVar.l(fVar24);
                        hVar.b(167, fVar21);
                        hVar.l(fVar);
                        String strJ = cVar.j();
                        hVar.k(25, 1);
                        hVar.k(25, c0018a.g("field_" + cVar.f433e.getName()));
                        if (strJ != null) {
                            hVar.i(strJ);
                            i4 = 182;
                            hVar.d(182, str31, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
                        } else {
                            i4 = 182;
                            hVar.k(25, C0018a.f315a);
                            Type type4 = cVar.f434f;
                            if ((type4 instanceof Class) && ((Class) type4).isPrimitive()) {
                                hVar.d(182, str31, str4, "(Ljava/lang/Object;Ljava/lang/Object;)V");
                            } else {
                                z = false;
                                hVar.k(25, 0);
                                hVar.f(180, c0018a.f319e, cVar.f429a + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                                hVar.i(Integer.valueOf(cVar.i));
                                i5 = 182;
                                hVar.d(182, str31, str4, "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                                hVar.l(fVar21);
                                hVar.l(fVar19);
                                str = str26;
                                c2 = 25;
                                hVar.k(25, c0018a.g(str));
                                hVar.k(16, i13);
                                str3 = str27;
                                hVar.d(i5, str28, "write", str3);
                                aVar = this;
                                c3 = 16;
                            }
                        }
                        i5 = i4;
                        z = false;
                        hVar.l(fVar21);
                        hVar.l(fVar19);
                        str = str26;
                        c2 = 25;
                        hVar.k(25, c0018a.g(str));
                        hVar.k(16, i13);
                        str3 = str27;
                        hVar.d(i5, str28, "write", str3);
                        aVar = this;
                        c3 = 16;
                    }
                    i2 = length;
                    i3 = i10;
                    str2 = str9;
                    str3 = str13;
                    z = false;
                    c2 = 25;
                    c3 = 16;
                    aVar = aVar2;
                    str = str11;
                }
                i2 = length;
                i3 = i10;
                str2 = str9;
                str3 = str13;
                z = false;
                c2 = 25;
                c3 = 16;
                aVar = aVar2;
                str = str11;
            }
            str13 = str3;
            length = i2;
            cVarArr2 = cVarArr;
            str9 = str2;
            str11 = str;
            aVar2 = aVar;
            i10 = i3 + 1;
        }
    }

    public final void B(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c[] cVarArr, C0018a c0018a) throws Exception {
        b.a.a.i.f fVar;
        String str;
        int i2;
        String str2;
        int i3;
        Class<?> cls2;
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        int length = cVarArr.length;
        String str3 = "out";
        if (c0018a.g) {
            fVar = fVar2;
        } else {
            b.a.a.i.f fVar3 = new b.a.a.i.f();
            b.a.a.i.f fVar4 = new b.a.a.i.f();
            fVar = fVar2;
            hVar.k(25, c0018a.g("out"));
            hVar.i(Integer.valueOf(SerializerFeature.PrettyFormat.mask));
            hVar.d(182, f312d, "isEnabled", "(I)Z");
            hVar.b(154, fVar4);
            int length2 = cVarArr.length;
            int i4 = 0;
            boolean z = false;
            while (i4 < length2) {
                int i5 = length2;
                if (cVarArr[i4].f430b != null) {
                    z = true;
                }
                i4++;
                length2 = i5;
            }
            if (z) {
                hVar.k(25, c0018a.g("out"));
                hVar.i(Integer.valueOf(SerializerFeature.IgnoreErrorGetter.mask));
                hVar.d(182, f312d, "isEnabled", "(I)Z");
                hVar.b(153, fVar3);
            } else {
                hVar.b(167, fVar3);
            }
            hVar.l(fVar4);
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.k(25, 4);
            hVar.k(21, 5);
            hVar.d(183, f314f, "write", "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.h(177);
            hVar.l(fVar3);
        }
        if (!c0018a.j) {
            b.a.a.i.f fVar5 = new b.a.a.i.f();
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(21, 5);
            hVar.d(182, f314f, "writeReference", "(L" + f309a + ";Ljava/lang/Object;I)Z");
            hVar.b(153, fVar5);
            hVar.h(177);
            hVar.l(fVar5);
        }
        String str4 = c0018a.g ? c0018a.j ? "writeAsArrayNonContext" : "writeAsArray" : "writeAsArrayNormal";
        int i6 = c0018a.f320f.g;
        SerializerFeature serializerFeature = SerializerFeature.BeanToArray;
        if ((i6 & serializerFeature.mask) == 0) {
            b.a.a.i.f fVar6 = new b.a.a.i.f();
            hVar.k(25, c0018a.g("out"));
            hVar.i(Integer.valueOf(serializerFeature.mask));
            hVar.d(182, f312d, "isEnabled", "(I)Z");
            hVar.b(153, fVar6);
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.k(25, 4);
            hVar.k(21, 5);
            hVar.d(182, c0018a.f319e, str4, "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.h(177);
            hVar.l(fVar6);
        } else {
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.k(25, 4);
            hVar.k(21, 5);
            hVar.d(182, c0018a.f319e, str4, "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.h(177);
        }
        if (!c0018a.j) {
            hVar.k(25, 1);
            String str5 = f309a;
            StringBuilder sb = new StringBuilder();
            sb.append("()");
            String str6 = h;
            sb.append(str6);
            hVar.d(182, str5, "getContext", sb.toString());
            hVar.k(58, c0018a.g("parent"));
            hVar.k(25, 1);
            hVar.k(25, c0018a.g("parent"));
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.i(Integer.valueOf(c0018a.f320f.g));
            hVar.d(182, str5, "setContext", "(" + str6 + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean z2 = (c0018a.f320f.g & SerializerFeature.WriteClassName.mask) != 0;
        if (z2 || !c0018a.g) {
            b.a.a.i.f fVar7 = new b.a.a.i.f();
            b.a.a.i.f fVar8 = new b.a.a.i.f();
            b.a.a.i.f fVar9 = new b.a.a.i.f();
            if (z2) {
                str = "parent";
                i2 = 182;
            } else {
                hVar.k(25, 1);
                hVar.k(25, 4);
                hVar.k(25, 2);
                str = "parent";
                i2 = 182;
                hVar.d(182, f309a, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                hVar.b(153, fVar8);
            }
            hVar.k(25, 4);
            hVar.k(25, 2);
            hVar.d(i2, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            hVar.b(165, fVar8);
            hVar.l(fVar9);
            hVar.k(25, c0018a.g("out"));
            hVar.k(16, 123);
            hVar.d(i2, f312d, "write", "(I)V");
            hVar.k(25, 0);
            hVar.k(25, 1);
            if (c0018a.f320f.f419c != null) {
                hVar.i(c0018a.f320f.f419c);
            } else {
                hVar.h(1);
            }
            hVar.k(25, 2);
            hVar.d(182, f314f, "writeClassName", "(L" + f309a + ";Ljava/lang/String;Ljava/lang/Object;)V");
            hVar.k(16, 44);
            hVar.b(167, fVar7);
            hVar.l(fVar8);
            hVar.k(16, 123);
            hVar.l(fVar7);
        } else {
            hVar.k(16, 123);
            str = "parent";
        }
        hVar.k(54, c0018a.g("seperator"));
        if (!c0018a.g) {
            c(hVar, c0018a);
        }
        if (!c0018a.g) {
            hVar.k(25, c0018a.g("out"));
            hVar.d(182, f312d, "isNotWriteDefaultValue", "()Z");
            hVar.k(54, c0018a.g("notWriteDefaultValue"));
            hVar.k(25, 1);
            hVar.k(25, 0);
            String str7 = f309a;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(");
            String str8 = i;
            sb2.append(str8);
            sb2.append(")Z");
            hVar.d(182, str7, "checkValue", sb2.toString());
            hVar.k(54, c0018a.g("checkValue"));
            hVar.k(25, 1);
            hVar.k(25, 0);
            hVar.d(182, str7, "hasNameFilters", "(" + str8 + ")Z");
            hVar.k(54, c0018a.g("hasNameFilters"));
        }
        int i7 = 0;
        while (i7 < length) {
            b.a.a.n.c cVar = cVarArr[i7];
            Class<?> cls3 = cVar.f433e;
            hVar.i(cVar.f429a);
            hVar.k(58, C0018a.f315a);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str2 = str3;
                i3 = i7;
                m(cls, hVar, cVar, c0018a, c0018a.g(cls3.getName()), 'I');
            } else {
                if (cls3 == Long.TYPE) {
                    cls2 = cls;
                    p(cls2, hVar, cVar, c0018a);
                } else {
                    cls2 = cls;
                    if (cls3 == Float.TYPE) {
                        h(cls2, hVar, cVar, c0018a);
                    } else if (cls3 == Double.TYPE) {
                        e(cls2, hVar, cVar, c0018a);
                    } else if (cls3 == Boolean.TYPE) {
                        str2 = str3;
                        i3 = i7;
                        m(cls, hVar, cVar, c0018a, c0018a.g("boolean"), 'Z');
                    } else {
                        str2 = str3;
                        i3 = i7;
                        if (cls3 == Character.TYPE) {
                            m(cls, hVar, cVar, c0018a, c0018a.g("char"), 'C');
                        } else if (cls3 == String.class) {
                            w(cls2, hVar, cVar, c0018a);
                        } else if (cls3 == BigDecimal.class) {
                            d(cls2, hVar, cVar, c0018a);
                        } else if (List.class.isAssignableFrom(cls3)) {
                            o(cls2, hVar, cVar, c0018a);
                        } else if (cls3.isEnum()) {
                            f(cls2, hVar, cVar, c0018a);
                        } else {
                            s(cls2, hVar, cVar, c0018a);
                        }
                    }
                }
                str2 = str3;
                i3 = i7;
            }
            i7 = i3 + 1;
            str3 = str2;
        }
        String str9 = str3;
        if (!c0018a.g) {
            a(hVar, c0018a);
        }
        b.a.a.i.f fVar10 = new b.a.a.i.f();
        b.a.a.i.f fVar11 = new b.a.a.i.f();
        hVar.k(21, c0018a.g("seperator"));
        hVar.g(16, 123);
        hVar.b(160, fVar10);
        hVar.k(25, c0018a.g(str9));
        hVar.k(16, 123);
        String str10 = f312d;
        hVar.d(182, str10, "write", "(I)V");
        hVar.l(fVar10);
        hVar.k(25, c0018a.g(str9));
        hVar.k(16, 125);
        hVar.d(182, str10, "write", "(I)V");
        hVar.l(fVar11);
        hVar.l(fVar);
        if (c0018a.j) {
            return;
        }
        hVar.k(25, 1);
        hVar.k(25, c0018a.g(str));
        hVar.d(182, f309a, "setContext", "(" + h + ")V");
    }

    public final void a(b.a.a.i.h hVar, C0018a c0018a) {
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(21, c0018a.g("seperator"));
        hVar.d(182, f314f, "writeAfter", "(L" + f309a + ";Ljava/lang/Object;C)C");
        hVar.k(54, c0018a.g("seperator"));
    }

    public final void b(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        Class<?> cls = cVar.f433e;
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(25, C0018a.f315a);
        if (cls == Byte.TYPE) {
            hVar.k(21, c0018a.g("byte"));
            hVar.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0018a.g("short"));
            hVar.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0018a.g("int"));
            hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            hVar.k(21, c0018a.g("char"));
            hVar.d(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0018a.h("long", 2));
            hVar.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0018a.g("float"));
            hVar.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0018a.h("double", 2));
            hVar.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            hVar.k(21, c0018a.g("boolean"));
            hVar.d(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            hVar.k(25, c0018a.g("decimal"));
        } else if (cls == String.class) {
            hVar.k(25, c0018a.g("string"));
        } else if (cls.isEnum()) {
            hVar.k(25, c0018a.g("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            hVar.k(25, c0018a.g("list"));
        } else {
            hVar.k(25, c0018a.g("object"));
        }
        hVar.d(182, f314f, "apply", "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    public final void c(b.a.a.i.h hVar, C0018a c0018a) {
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(21, c0018a.g("seperator"));
        hVar.d(182, f314f, "writeBefore", "(L" + f309a + ";Ljava/lang/Object;C)C");
        hVar.k(54, c0018a.g("seperator"));
    }

    public final void d(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(58, c0018a.g("decimal"));
        g(hVar, cVar, c0018a, fVar);
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        b.a.a.i.f fVar3 = new b.a.a.i.f();
        b.a.a.i.f fVar4 = new b.a.a.i.f();
        hVar.l(fVar2);
        hVar.k(25, c0018a.g("decimal"));
        hVar.b(199, fVar3);
        l(hVar, cVar, c0018a);
        hVar.b(167, fVar4);
        hVar.l(fVar3);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        hVar.k(25, C0018a.f315a);
        hVar.k(25, c0018a.g("decimal"));
        hVar.d(182, f312d, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        v(hVar, c0018a);
        hVar.b(167, fVar4);
        hVar.l(fVar4);
        hVar.l(fVar);
    }

    public final void e(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(57, c0018a.h("double", 2));
        g(hVar, cVar, c0018a, fVar);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        hVar.k(25, C0018a.f315a);
        hVar.k(24, c0018a.h("double", 2));
        hVar.d(182, f312d, "writeFieldValue", "(CLjava/lang/String;D)V");
        v(hVar, c0018a);
        hVar.l(fVar);
    }

    public final void f(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        b.a.a.i.f fVar3 = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar3);
        i(hVar, c0018a, cVar);
        hVar.c(192, "java/lang/Enum");
        hVar.k(58, c0018a.g("enum"));
        g(hVar, cVar, c0018a, fVar3);
        hVar.k(25, c0018a.g("enum"));
        hVar.b(199, fVar);
        l(hVar, cVar, c0018a);
        hVar.b(167, fVar2);
        hVar.l(fVar);
        if (c0018a.g) {
            hVar.k(25, c0018a.g("out"));
            hVar.k(21, c0018a.g("seperator"));
            hVar.k(25, C0018a.f315a);
            hVar.k(25, c0018a.g("enum"));
            hVar.d(182, "java/lang/Enum", "name", "()Ljava/lang/String;");
            hVar.d(182, f312d, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            hVar.k(25, c0018a.g("out"));
            hVar.k(21, c0018a.g("seperator"));
            String str = f312d;
            hVar.d(182, str, "write", "(I)V");
            hVar.k(25, c0018a.g("out"));
            hVar.k(25, C0018a.f315a);
            hVar.h(3);
            hVar.d(182, str, "writeFieldName", "(Ljava/lang/String;Z)V");
            hVar.k(25, 1);
            hVar.k(25, c0018a.g("enum"));
            hVar.k(25, C0018a.f315a);
            hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cVar.f433e)));
            hVar.i(Integer.valueOf(cVar.i));
            hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        v(hVar, c0018a);
        hVar.l(fVar2);
        hVar.l(fVar3);
    }

    public final void g(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, b.a.a.i.f fVar) {
        if (cVar.o) {
            hVar.k(25, c0018a.g("out"));
            hVar.i(Integer.valueOf(SerializerFeature.SkipTransientField.mask));
            hVar.d(182, f312d, "isEnabled", "(I)Z");
            hVar.b(154, fVar);
        }
        r(hVar, cVar, c0018a, fVar);
        if (c0018a.g) {
            return;
        }
        b(hVar, cVar, c0018a);
        hVar.b(153, fVar);
        t(hVar, cVar, c0018a);
        u(hVar, cVar, c0018a, fVar);
    }

    public final void h(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(56, c0018a.g("float"));
        g(hVar, cVar, c0018a, fVar);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        hVar.k(25, C0018a.f315a);
        hVar.k(23, c0018a.g("float"));
        hVar.d(182, f312d, "writeFieldValue", "(CLjava/lang/String;F)V");
        v(hVar, c0018a);
        hVar.l(fVar);
    }

    public final void i(b.a.a.i.h hVar, C0018a c0018a, b.a.a.n.c cVar) {
        Method method = cVar.f430b;
        if (method != null) {
            hVar.k(25, c0018a.g("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            hVar.d(declaringClass.isInterface() ? 185 : 182, b.a.a.n.b.g(declaringClass), method.getName(), b.a.a.n.b.c(method));
            if (method.getReturnType().equals(cVar.f433e)) {
                return;
            }
            hVar.c(192, b.a.a.n.b.g(cVar.f433e));
            return;
        }
        hVar.k(25, c0018a.g("entity"));
        Field field = cVar.f431c;
        hVar.f(180, b.a.a.n.b.g(cVar.g), field.getName(), b.a.a.n.b.b(field.getType()));
        if (field.getType().equals(cVar.f433e)) {
            return;
        }
        hVar.c(192, b.a.a.n.b.g(cVar.f433e));
    }

    public final void j(C0018a c0018a, b.a.a.i.h hVar, b.a.a.n.c cVar) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        hVar.k(25, 0);
        String str = c0018a.f319e;
        String str2 = cVar.f429a + "_asm_ser_";
        String str3 = f311c;
        hVar.f(180, str, str2, str3);
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cVar.f433e)));
        hVar.d(182, f309a, "getObjectWriter", "(Ljava/lang/Class;)" + str3);
        hVar.f(181, c0018a.f319e, cVar.f429a + "_asm_ser_", str3);
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0018a.f319e, cVar.f429a + "_asm_ser_", str3);
    }

    public final void k(C0018a c0018a, b.a.a.i.h hVar, b.a.a.n.c cVar, Class<?> cls) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        hVar.k(25, 0);
        String str = c0018a.f319e;
        String str2 = cVar.f429a + "_asm_list_item_ser_";
        String str3 = f311c;
        hVar.f(180, str, str2, str3);
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls)));
        hVar.d(182, f309a, "getObjectWriter", "(Ljava/lang/Class;)" + str3);
        hVar.f(181, c0018a.f319e, cVar.f429a + "_asm_list_item_ser_", str3);
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0018a.f319e, cVar.f429a + "_asm_list_item_ser_", str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0134  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(b.a.a.i.h r17, b.a.a.n.c r18, b.a.a.k.a.C0018a r19) {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.a.l(b.a.a.i.h, b.a.a.n.c, b.a.a.k.a$a):void");
    }

    public final void m(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, int i2, char c2) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(54, i2);
        g(hVar, cVar, c0018a, fVar);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        hVar.k(25, C0018a.f315a);
        hVar.k(21, i2);
        hVar.d(182, f312d, "writeFieldValue", "(CLjava/lang/String;" + c2 + ")V");
        v(hVar, c0018a);
        hVar.l(fVar);
    }

    public final void n(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, b.a.a.i.f fVar) {
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.i(cVar.k);
        hVar.d(182, f314f, "applyLabel", "(L" + f309a + ";Ljava/lang/String;)Z");
        hVar.b(153, fVar);
    }

    public final void o(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar;
        b.a.a.i.f fVar2;
        b.a.a.i.f fVar3;
        String str;
        b.a.a.i.f fVar4;
        b.a.a.i.f fVar5;
        String str2;
        b.a.a.i.f fVar6;
        b.a.a.n.c cVar2;
        int i2;
        int i3;
        int i4;
        b.a.a.i.f fVar7;
        String str3;
        String str4;
        Type typeU = b.a.a.n.l.U(cVar.f434f);
        Class<?> cls2 = null;
        Class<?> cls3 = typeU instanceof Class ? (Class) typeU : null;
        if (cls3 != Object.class && cls3 != Serializable.class) {
            cls2 = cls3;
        }
        b.a.a.i.f fVar8 = new b.a.a.i.f();
        b.a.a.i.f fVar9 = new b.a.a.i.f();
        b.a.a.i.f fVar10 = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar8);
        i(hVar, c0018a, cVar);
        hVar.c(192, "java/util/List");
        hVar.k(58, c0018a.g("list"));
        g(hVar, cVar, c0018a, fVar8);
        hVar.k(25, c0018a.g("list"));
        hVar.b(199, fVar9);
        l(hVar, cVar, c0018a);
        hVar.b(167, fVar10);
        hVar.l(fVar9);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        String str5 = f312d;
        hVar.d(182, str5, "write", "(I)V");
        x(hVar, c0018a);
        hVar.k(25, c0018a.g("list"));
        hVar.d(185, "java/util/List", "size", "()I");
        hVar.k(54, c0018a.g("size"));
        b.a.a.i.f fVar11 = new b.a.a.i.f();
        b.a.a.i.f fVar12 = new b.a.a.i.f();
        hVar.k(21, c0018a.g("size"));
        hVar.h(3);
        hVar.b(160, fVar11);
        hVar.k(25, c0018a.g("out"));
        hVar.i("[]");
        hVar.d(182, str5, "write", "(Ljava/lang/String;)V");
        hVar.b(167, fVar12);
        hVar.l(fVar11);
        if (c0018a.j) {
            fVar = fVar12;
        } else {
            hVar.k(25, 1);
            hVar.k(25, c0018a.g("list"));
            hVar.k(25, C0018a.f315a);
            fVar = fVar12;
            hVar.d(182, f309a, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (typeU == String.class && c0018a.g) {
            hVar.k(25, c0018a.g("out"));
            hVar.k(25, c0018a.g("list"));
            hVar.d(182, str5, "write", "(Ljava/util/List;)V");
            i3 = 25;
            i4 = 182;
            i2 = 1;
        } else {
            hVar.k(25, c0018a.g("out"));
            hVar.k(16, 91);
            hVar.d(182, str5, "write", "(I)V");
            b.a.a.i.f fVar13 = new b.a.a.i.f();
            b.a.a.i.f fVar14 = new b.a.a.i.f();
            b.a.a.i.f fVar15 = new b.a.a.i.f();
            hVar.h(3);
            hVar.k(54, c0018a.g("i"));
            hVar.l(fVar13);
            hVar.k(21, c0018a.g("i"));
            hVar.k(21, c0018a.g("size"));
            hVar.b(162, fVar15);
            hVar.k(21, c0018a.g("i"));
            hVar.b(153, fVar14);
            hVar.k(25, c0018a.g("out"));
            hVar.k(16, 44);
            hVar.d(182, str5, "write", "(I)V");
            hVar.l(fVar14);
            hVar.k(25, c0018a.g("list"));
            hVar.k(21, c0018a.g("i"));
            hVar.d(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
            hVar.k(58, c0018a.g("list_item"));
            b.a.a.i.f fVar16 = new b.a.a.i.f();
            b.a.a.i.f fVar17 = new b.a.a.i.f();
            hVar.k(25, c0018a.g("list_item"));
            hVar.b(199, fVar17);
            hVar.k(25, c0018a.g("out"));
            hVar.d(182, str5, "writeNull", "()V");
            hVar.b(167, fVar16);
            hVar.l(fVar17);
            b.a.a.i.f fVar18 = new b.a.a.i.f();
            b.a.a.i.f fVar19 = new b.a.a.i.f();
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                fVar2 = fVar13;
                fVar3 = fVar16;
                str = "out";
                fVar4 = fVar18;
                fVar5 = fVar19;
                str2 = "write";
                fVar6 = fVar15;
                cVar2 = cVar;
            } else {
                str = "out";
                hVar.k(25, c0018a.g("list_item"));
                fVar6 = fVar15;
                fVar2 = fVar13;
                hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
                hVar.b(166, fVar19);
                cVar2 = cVar;
                k(c0018a, hVar, cVar2, cls2);
                hVar.k(58, c0018a.g("list_item_desc"));
                b.a.a.i.f fVar20 = new b.a.a.i.f();
                b.a.a.i.f fVar21 = new b.a.a.i.f();
                if (c0018a.g) {
                    if (c0018a.j && c0018a.g) {
                        fVar3 = fVar16;
                        str4 = "writeDirectNonContext";
                        fVar5 = fVar19;
                    } else {
                        fVar3 = fVar16;
                        fVar5 = fVar19;
                        str4 = "write";
                    }
                    fVar7 = fVar18;
                    hVar.k(25, c0018a.g("list_item_desc"));
                    String str6 = f314f;
                    hVar.c(193, str6);
                    hVar.b(153, fVar20);
                    str3 = "write";
                    hVar.k(25, c0018a.g("list_item_desc"));
                    hVar.c(192, str6);
                    hVar.k(25, 1);
                    hVar.k(25, c0018a.g("list_item"));
                    if (c0018a.j) {
                        hVar.h(1);
                    } else {
                        hVar.k(21, c0018a.g("i"));
                        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
                    hVar.i(Integer.valueOf(cVar2.i));
                    hVar.d(182, str6, str4, "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    hVar.b(167, fVar21);
                    hVar.l(fVar20);
                } else {
                    fVar3 = fVar16;
                    fVar7 = fVar18;
                    fVar5 = fVar19;
                    str3 = "write";
                }
                hVar.k(25, c0018a.g("list_item_desc"));
                hVar.k(25, 1);
                hVar.k(25, c0018a.g("list_item"));
                if (c0018a.j) {
                    hVar.h(1);
                } else {
                    hVar.k(21, c0018a.g("i"));
                    hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
                hVar.i(Integer.valueOf(cVar2.i));
                str2 = str3;
                hVar.d(185, f310b, str2, "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                hVar.l(fVar21);
                fVar4 = fVar7;
                hVar.b(167, fVar4);
            }
            hVar.l(fVar5);
            hVar.k(25, 1);
            hVar.k(25, c0018a.g("list_item"));
            if (c0018a.j) {
                hVar.h(1);
            } else {
                hVar.k(21, c0018a.g("i"));
                hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b((Class) typeU)));
                hVar.i(Integer.valueOf(cVar2.i));
                hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
            hVar.l(fVar4);
            hVar.l(fVar3);
            i2 = 1;
            hVar.a(c0018a.g("i"), 1);
            hVar.b(167, fVar2);
            hVar.l(fVar6);
            i3 = 25;
            hVar.k(25, c0018a.g(str));
            hVar.k(16, 93);
            i4 = 182;
            hVar.d(182, str5, str2, "(I)V");
        }
        hVar.k(i3, i2);
        hVar.d(i4, f309a, "popContext", "()V");
        hVar.l(fVar);
        v(hVar, c0018a);
        hVar.l(fVar10);
        hVar.l(fVar8);
    }

    public final void p(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(55, c0018a.h("long", 2));
        g(hVar, cVar, c0018a, fVar);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        hVar.k(25, C0018a.f315a);
        hVar.k(22, c0018a.h("long", 2));
        hVar.d(182, f312d, "writeFieldValue", "(CLjava/lang/String;J)V");
        v(hVar, c0018a);
        hVar.l(fVar);
    }

    public final void q(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, b.a.a.i.f fVar) {
        if (!c0018a.g) {
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, C0018a.f315a);
            hVar.d(182, f314f, "applyName", "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            hVar.b(153, fVar);
            n(hVar, cVar, c0018a, fVar);
        }
        if (cVar.f431c == null) {
            hVar.k(25, c0018a.g("out"));
            hVar.i(Integer.valueOf(SerializerFeature.IgnoreNonFieldGetter.mask));
            hVar.d(182, f312d, "isEnabled", "(I)Z");
            hVar.b(154, fVar);
        }
    }

    public final void r(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, b.a.a.i.f fVar) {
        if (c0018a.g) {
            return;
        }
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        hVar.k(21, c0018a.g("notWriteDefaultValue"));
        hVar.b(153, fVar2);
        Class<?> cls = cVar.f433e;
        if (cls == Boolean.TYPE) {
            hVar.k(21, c0018a.g("boolean"));
            hVar.b(153, fVar);
        } else if (cls == Byte.TYPE) {
            hVar.k(21, c0018a.g("byte"));
            hVar.b(153, fVar);
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0018a.g("short"));
            hVar.b(153, fVar);
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0018a.g("int"));
            hVar.b(153, fVar);
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0018a.g("long"));
            hVar.h(9);
            hVar.h(148);
            hVar.b(153, fVar);
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0018a.g("float"));
            hVar.h(11);
            hVar.h(149);
            hVar.b(153, fVar);
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0018a.g("double"));
            hVar.h(14);
            hVar.h(151);
            hVar.b(153, fVar);
        }
        hVar.l(fVar2);
    }

    public final void s(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(58, c0018a.g("object"));
        g(hVar, cVar, c0018a, fVar);
        y(hVar, cVar, c0018a, fVar);
        hVar.l(fVar);
    }

    public final void t(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        hVar.k(21, c0018a.g("hasNameFilters"));
        hVar.b(153, fVar);
        Class<?> cls = cVar.f433e;
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(25, C0018a.f315a);
        if (cls == Byte.TYPE) {
            hVar.k(21, c0018a.g("byte"));
            hVar.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0018a.g("short"));
            hVar.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0018a.g("int"));
            hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            hVar.k(21, c0018a.g("char"));
            hVar.d(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0018a.h("long", 2));
            hVar.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0018a.g("float"));
            hVar.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0018a.h("double", 2));
            hVar.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            hVar.k(21, c0018a.g("boolean"));
            hVar.d(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            hVar.k(25, c0018a.g("decimal"));
        } else if (cls == String.class) {
            hVar.k(25, c0018a.g("string"));
        } else if (cls.isEnum()) {
            hVar.k(25, c0018a.g("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            hVar.k(25, c0018a.g("list"));
        } else {
            hVar.k(25, c0018a.g("object"));
        }
        hVar.d(182, f314f, "processKey", "(L" + f309a + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        hVar.k(58, C0018a.f315a);
        hVar.l(fVar);
    }

    public final void u(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, b.a.a.i.f fVar) {
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        Class<?> cls = cVar.f433e;
        if (cls.isPrimitive()) {
            b.a.a.i.f fVar3 = new b.a.a.i.f();
            hVar.k(21, c0018a.g("checkValue"));
            hVar.b(154, fVar3);
            hVar.h(1);
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
            hVar.k(58, C0018a.f317c);
            hVar.b(167, fVar2);
            hVar.l(fVar3);
        }
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 0);
        hVar.i(Integer.valueOf(c0018a.f(cVar.f429a)));
        String str = f314f;
        hVar.d(182, str, "getBeanContext", "(I)" + b.a.a.n.b.b(j.class));
        hVar.k(25, 2);
        hVar.k(25, C0018a.f315a);
        if (cls == Byte.TYPE) {
            hVar.k(21, c0018a.g("byte"));
            hVar.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0018a.g("short"));
            hVar.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0018a.g("int"));
            hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Character.TYPE) {
            hVar.k(21, c0018a.g("char"));
            hVar.d(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0018a.h("long", 2));
            hVar.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0018a.g("float"));
            hVar.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0018a.h("double", 2));
            hVar.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == Boolean.TYPE) {
            hVar.k(21, c0018a.g("boolean"));
            hVar.d(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            hVar.h(89);
            hVar.k(58, C0018a.f316b);
        } else if (cls == BigDecimal.class) {
            hVar.k(25, c0018a.g("decimal"));
            hVar.k(58, C0018a.f316b);
            hVar.k(25, C0018a.f316b);
        } else if (cls == String.class) {
            hVar.k(25, c0018a.g("string"));
            hVar.k(58, C0018a.f316b);
            hVar.k(25, C0018a.f316b);
        } else if (cls.isEnum()) {
            hVar.k(25, c0018a.g("enum"));
            hVar.k(58, C0018a.f316b);
            hVar.k(25, C0018a.f316b);
        } else if (List.class.isAssignableFrom(cls)) {
            hVar.k(25, c0018a.g("list"));
            hVar.k(58, C0018a.f316b);
            hVar.k(25, C0018a.f316b);
        } else {
            hVar.k(25, c0018a.g("object"));
            hVar.k(58, C0018a.f316b);
            hVar.k(25, C0018a.f316b);
        }
        hVar.d(182, str, "processValue", "(L" + f309a + ";" + b.a.a.n.b.b(j.class) + "Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
        hVar.k(58, C0018a.f317c);
        hVar.k(25, C0018a.f316b);
        hVar.k(25, C0018a.f317c);
        hVar.b(165, fVar2);
        y(hVar, cVar, c0018a, fVar);
        hVar.b(167, fVar);
        hVar.l(fVar2);
    }

    public final void v(b.a.a.i.h hVar, C0018a c0018a) {
        hVar.k(16, 44);
        hVar.k(54, c0018a.g("seperator"));
    }

    public final void w(Class<?> cls, b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        if (cVar.f429a.equals(c0018a.f320f.f419c)) {
            hVar.k(25, 1);
            hVar.k(25, 4);
            hVar.k(25, 2);
            hVar.d(182, f309a, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            hVar.b(154, fVar);
        }
        q(hVar, cVar, c0018a, fVar);
        i(hVar, c0018a, cVar);
        hVar.k(58, c0018a.g("string"));
        g(hVar, cVar, c0018a, fVar);
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        b.a.a.i.f fVar3 = new b.a.a.i.f();
        hVar.k(25, c0018a.g("string"));
        hVar.b(199, fVar2);
        l(hVar, cVar, c0018a);
        hVar.b(167, fVar3);
        hVar.l(fVar2);
        if ("trim".equals(cVar.t)) {
            hVar.k(25, c0018a.g("string"));
            hVar.d(182, "java/lang/String", "trim", "()Ljava/lang/String;");
            hVar.k(58, c0018a.g("string"));
        }
        if (c0018a.g) {
            hVar.k(25, c0018a.g("out"));
            hVar.k(21, c0018a.g("seperator"));
            hVar.k(25, C0018a.f315a);
            hVar.k(25, c0018a.g("string"));
            hVar.d(182, f312d, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            hVar.k(25, c0018a.g("out"));
            hVar.k(21, c0018a.g("seperator"));
            hVar.k(25, C0018a.f315a);
            hVar.k(25, c0018a.g("string"));
            hVar.d(182, f312d, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        v(hVar, c0018a);
        hVar.l(fVar3);
        hVar.l(fVar);
    }

    public final void x(b.a.a.i.h hVar, C0018a c0018a) {
        if (c0018a.g) {
            hVar.k(25, c0018a.g("out"));
            hVar.k(25, C0018a.f315a);
            hVar.d(182, f312d, "writeFieldNameDirect", "(Ljava/lang/String;)V");
        } else {
            hVar.k(25, c0018a.g("out"));
            hVar.k(25, C0018a.f315a);
            hVar.h(3);
            hVar.d(182, f312d, "writeFieldName", "(Ljava/lang/String;Z)V");
        }
    }

    public final void y(b.a.a.i.h hVar, b.a.a.n.c cVar, C0018a c0018a, b.a.a.i.f fVar) {
        Class<?> cls;
        String str;
        b.a.a.i.f fVar2;
        b.a.a.i.f fVar3;
        String strJ = cVar.j();
        Class<?> cls2 = cVar.f433e;
        b.a.a.i.f fVar4 = new b.a.a.i.f();
        if (c0018a.g) {
            hVar.k(25, c0018a.g("object"));
        } else {
            hVar.k(25, C0018a.f317c);
        }
        hVar.h(89);
        hVar.k(58, c0018a.g("object"));
        hVar.b(199, fVar4);
        l(hVar, cVar, c0018a);
        hVar.b(167, fVar);
        hVar.l(fVar4);
        hVar.k(25, c0018a.g("out"));
        hVar.k(21, c0018a.g("seperator"));
        hVar.d(182, f312d, "write", "(I)V");
        x(hVar, c0018a);
        b.a.a.i.f fVar5 = new b.a.a.i.f();
        b.a.a.i.f fVar6 = new b.a.a.i.f();
        if (!Modifier.isPublic(cls2.getModifiers()) || b.a.a.j.h.v(cls2)) {
            cls = String.class;
            str = strJ;
            fVar2 = fVar5;
            fVar3 = fVar6;
        } else {
            hVar.k(25, c0018a.g("object"));
            cls = String.class;
            hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
            hVar.b(166, fVar6);
            j(c0018a, hVar, cVar);
            hVar.k(58, c0018a.g("fied_ser"));
            b.a.a.i.f fVar7 = new b.a.a.i.f();
            b.a.a.i.f fVar8 = new b.a.a.i.f();
            hVar.k(25, c0018a.g("fied_ser"));
            String str2 = f314f;
            hVar.c(193, str2);
            hVar.b(153, fVar7);
            int i2 = cVar.i;
            str = strJ;
            boolean z = (SerializerFeature.DisableCircularReferenceDetect.mask & i2) != 0;
            boolean z2 = (SerializerFeature.BeanToArray.mask & i2) != 0;
            String str3 = (z || (c0018a.j && c0018a.g)) ? z2 ? "writeAsArrayNonContext" : "writeDirectNonContext" : z2 ? "writeAsArray" : "write";
            hVar.k(25, c0018a.g("fied_ser"));
            hVar.c(192, str2);
            hVar.k(25, 1);
            hVar.k(25, c0018a.g("object"));
            hVar.k(25, C0018a.f315a);
            hVar.k(25, 0);
            hVar.f(180, c0018a.f319e, cVar.f429a + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            hVar.i(Integer.valueOf(cVar.i));
            StringBuilder sb = new StringBuilder();
            sb.append("(L");
            String str4 = f309a;
            sb.append(str4);
            sb.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.d(182, str2, str3, sb.toString());
            hVar.b(167, fVar8);
            hVar.l(fVar7);
            hVar.k(25, c0018a.g("fied_ser"));
            hVar.k(25, 1);
            hVar.k(25, c0018a.g("object"));
            hVar.k(25, C0018a.f315a);
            hVar.k(25, 0);
            hVar.f(180, c0018a.f319e, cVar.f429a + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            hVar.i(Integer.valueOf(cVar.i));
            hVar.d(185, f310b, "write", "(L" + str4 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.l(fVar8);
            fVar2 = fVar5;
            hVar.b(167, fVar2);
            fVar3 = fVar6;
        }
        hVar.l(fVar3);
        hVar.k(25, 1);
        if (c0018a.g) {
            hVar.k(25, c0018a.g("object"));
        } else {
            hVar.k(25, C0018a.f317c);
        }
        if (str != null) {
            hVar.i(str);
            hVar.d(182, f309a, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            hVar.k(25, C0018a.f315a);
            Type type = cVar.f434f;
            if ((type instanceof Class) && ((Class) type).isPrimitive()) {
                hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                Class<?> cls3 = cls;
                if (cVar.f433e == cls3) {
                    hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls3)));
                } else {
                    hVar.k(25, 0);
                    hVar.f(180, c0018a.f319e, cVar.f429a + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                hVar.i(Integer.valueOf(cVar.i));
                hVar.d(182, f309a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
        }
        hVar.l(fVar2);
        v(hVar, c0018a);
    }

    public j0 z(z0 z0Var) throws Exception {
        String str;
        String str2;
        boolean z;
        Class<z0> cls;
        String str3;
        boolean z2;
        boolean z3;
        String str4;
        boolean z4;
        boolean z5;
        b.a.a.h.d dVar;
        String str5;
        b.a.a.n.c[] cVarArr;
        boolean z6;
        int i2;
        char c2;
        b.a.a.i.c cVar;
        int i3;
        String str6;
        Method method;
        Class<z0> cls2 = z0.class;
        Class<?> cls3 = z0Var.f417a;
        if (cls3.isPrimitive()) {
            throw new JSONException("unsupportd class " + cls3.getName());
        }
        b.a.a.h.d dVar2 = (b.a.a.h.d) b.a.a.n.l.M(cls3, b.a.a.h.d.class);
        b.a.a.n.c[] cVarArr2 = z0Var.f421e;
        for (b.a.a.n.c cVar2 : cVarArr2) {
            if (cVar2.f431c == null && (method = cVar2.f430b) != null && method.getDeclaringClass().isInterface()) {
                return new j0(z0Var);
            }
        }
        b.a.a.n.c[] cVarArr3 = z0Var.f422f;
        boolean z7 = cVarArr3 == z0Var.f421e;
        if (cVarArr3.length > 256) {
            return new j0(z0Var);
        }
        for (b.a.a.n.c cVar3 : cVarArr3) {
            if (!b.a.a.n.b.a(cVar3.l().getName())) {
                return new j0(z0Var);
            }
        }
        String str7 = "ASMSerializer_" + this.k.incrementAndGet() + "_" + cls3.getSimpleName();
        Package r2 = a.class.getPackage();
        if (r2 != null) {
            String name = r2.getName();
            str = name + "." + str7;
            str2 = name.replace('.', '/') + "/" + str7;
        } else {
            str = str7;
            str2 = str;
        }
        b.a.a.i.c cVar4 = new b.a.a.i.c();
        cVar4.k(49, 33, str2, f314f, new String[]{f310b});
        int length = cVarArr3.length;
        int i4 = 0;
        while (i4 < length) {
            b.a.a.n.c cVar5 = cVarArr3[i4];
            if (cVar5.f433e.isPrimitive() || cVar5.f433e == String.class) {
                i3 = length;
                str6 = str;
            } else {
                StringBuilder sb = new StringBuilder();
                i3 = length;
                sb.append(cVar5.f429a);
                sb.append("_asm_fieldType");
                str6 = str;
                new b.a.a.i.d(cVar4, 1, sb.toString(), "Ljava/lang/reflect/Type;").c();
                if (List.class.isAssignableFrom(cVar5.f433e)) {
                    new b.a.a.i.d(cVar4, 1, cVar5.f429a + "_asm_list_item_ser_", f311c).c();
                }
                new b.a.a.i.d(cVar4, 1, cVar5.f429a + "_asm_ser_", f311c).c();
            }
            i4++;
            length = i3;
            str = str6;
        }
        String str8 = str;
        b.a.a.i.i iVar = new b.a.a.i.i(cVar4, 1, "<init>", "(" + b.a.a.n.b.b(cls2) + ")V", null, null);
        iVar.k(25, 0);
        iVar.k(25, 1);
        iVar.d(183, f314f, "<init>", "(" + b.a.a.n.b.b(cls2) + ")V");
        int i5 = 0;
        while (i5 < cVarArr3.length) {
            b.a.a.n.c cVar6 = cVarArr3[i5];
            if (cVar6.f433e.isPrimitive() || cVar6.f433e == String.class) {
                cVar = cVar4;
            } else {
                iVar.k(25, 0);
                if (cVar6.f430b != null) {
                    iVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cVar6.g)));
                    iVar.i(cVar6.f430b.getName());
                    cVar = cVar4;
                    iVar.d(184, b.a.a.n.b.g(b.a.a.n.b.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
                } else {
                    cVar = cVar4;
                    iVar.k(25, 0);
                    iVar.i(Integer.valueOf(i5));
                    iVar.d(183, f314f, "getFieldType", "(I)Ljava/lang/reflect/Type;");
                }
                iVar.f(181, str2, cVar6.f429a + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            }
            i5++;
            cVar4 = cVar;
        }
        b.a.a.i.c cVar7 = cVar4;
        iVar.h(177);
        iVar.j(4, 4);
        iVar.e();
        if (dVar2 != null) {
            for (SerializerFeature serializerFeature : dVar2.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        int i6 = 0;
        while (true) {
            cls = cls2;
            if (i6 >= 3) {
                break;
            }
            if (i6 == 0) {
                str4 = "write";
                z5 = z;
                z4 = true;
            } else if (i6 == 1) {
                str4 = "writeNormal";
                z5 = z;
                z4 = false;
            } else {
                str4 = "writeDirectNonContext";
                z4 = true;
                z5 = true;
            }
            b.a.a.i.c cVar8 = cVar7;
            String str9 = str8;
            int i7 = i6;
            String str10 = str2;
            C0018a c0018a = new C0018a(cVarArr3, z0Var, str2, z4, z5);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(L");
            String str11 = f309a;
            sb2.append(str11);
            sb2.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            b.a.a.i.i iVar2 = new b.a.a.i.i(cVar8, 1, str4, sb2.toString(), null, new String[]{"java/io/IOException"});
            b.a.a.i.f fVar = new b.a.a.i.f();
            iVar2.k(25, 2);
            iVar2.b(199, fVar);
            iVar2.k(25, 1);
            iVar2.d(182, str11, "writeNull", "()V");
            iVar2.h(177);
            iVar2.l(fVar);
            iVar2.k(25, 1);
            iVar2.f(180, str11, "out", f313e);
            iVar2.k(58, c0018a.g("out"));
            if (z7 || c0018a.g || !(dVar2 == null || dVar2.alphabetic())) {
                dVar = dVar2;
                str5 = str10;
            } else {
                b.a.a.i.f fVar2 = new b.a.a.i.f();
                iVar2.k(25, c0018a.g("out"));
                dVar = dVar2;
                iVar2.d(182, f312d, "isSortField", "()Z");
                iVar2.b(154, fVar2);
                iVar2.k(25, 0);
                iVar2.k(25, 1);
                iVar2.k(25, 2);
                iVar2.k(25, 3);
                iVar2.k(25, 4);
                iVar2.k(21, 5);
                str5 = str10;
                iVar2.d(182, str5, "writeUnsorted", "(L" + str11 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                iVar2.h(177);
                iVar2.l(fVar2);
            }
            if (!c0018a.g || z5) {
                cVarArr = cVarArr2;
                z6 = z7;
                i2 = 177;
                c2 = 4;
            } else {
                b.a.a.i.f fVar3 = new b.a.a.i.f();
                b.a.a.i.f fVar4 = new b.a.a.i.f();
                iVar2.k(25, 0);
                iVar2.k(25, 1);
                String str12 = f314f;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("(L");
                sb3.append(str11);
                cVarArr = cVarArr2;
                sb3.append(";)Z");
                z6 = z7;
                iVar2.d(182, str12, "writeDirect", sb3.toString());
                iVar2.b(154, fVar4);
                iVar2.k(25, 0);
                iVar2.k(25, 1);
                iVar2.k(25, 2);
                iVar2.k(25, 3);
                iVar2.k(25, 4);
                iVar2.k(21, 5);
                iVar2.d(182, str5, "writeNormal", "(L" + str11 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                iVar2.h(177);
                iVar2.l(fVar4);
                iVar2.k(25, c0018a.g("out"));
                iVar2.i(Integer.valueOf(SerializerFeature.DisableCircularReferenceDetect.mask));
                iVar2.d(182, f312d, "isEnabled", "(I)Z");
                iVar2.b(153, fVar3);
                iVar2.k(25, 0);
                iVar2.k(25, 1);
                iVar2.k(25, 2);
                iVar2.k(25, 3);
                c2 = 4;
                iVar2.k(25, 4);
                iVar2.k(21, 5);
                iVar2.d(182, str5, "writeDirectNonContext", "(L" + str11 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                i2 = 177;
                iVar2.h(177);
                iVar2.l(fVar3);
            }
            iVar2.k(25, 2);
            iVar2.c(192, b.a.a.n.b.g(cls3));
            iVar2.k(58, c0018a.g("entity"));
            B(cls3, iVar2, cVarArr3, c0018a);
            iVar2.h(i2);
            iVar2.j(7, c0018a.i + 2);
            iVar2.e();
            int i8 = i7 + 1;
            str2 = str5;
            dVar2 = dVar;
            z7 = z6;
            cls2 = cls;
            cVarArr2 = cVarArr;
            cVar7 = cVar8;
            str8 = str9;
            i6 = i8;
        }
        String str13 = str2;
        b.a.a.n.c[] cVarArr4 = cVarArr2;
        b.a.a.i.c cVar9 = cVar7;
        String str14 = str8;
        if (!z7) {
            C0018a c0018a2 = new C0018a(cVarArr3, z0Var, str13, false, z);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("(L");
            String str15 = f309a;
            sb4.append(str15);
            sb4.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            b.a.a.i.i iVar3 = new b.a.a.i.i(cVar9, 1, "writeUnsorted", sb4.toString(), null, new String[]{"java/io/IOException"});
            iVar3.k(25, 1);
            iVar3.f(180, str15, "out", f313e);
            iVar3.k(58, c0018a2.g("out"));
            iVar3.k(25, 2);
            iVar3.c(192, b.a.a.n.b.g(cls3));
            iVar3.k(58, c0018a2.g("entity"));
            B(cls3, iVar3, cVarArr4, c0018a2);
            iVar3.h(177);
            iVar3.j(7, c0018a2.i + 2);
            iVar3.e();
        }
        int i9 = 0;
        for (int i10 = 3; i9 < i10; i10 = i10) {
            if (i9 == 0) {
                str3 = "writeAsArray";
                z3 = z;
                z2 = true;
            } else if (i9 == 1) {
                str3 = "writeAsArrayNormal";
                z3 = z;
                z2 = false;
            } else {
                str3 = "writeAsArrayNonContext";
                z2 = true;
                z3 = true;
            }
            C0018a c0018a3 = new C0018a(cVarArr3, z0Var, str13, z2, z3);
            StringBuilder sb5 = new StringBuilder();
            sb5.append("(L");
            String str16 = f309a;
            sb5.append(str16);
            sb5.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            b.a.a.i.i iVar4 = new b.a.a.i.i(cVar9, 1, str3, sb5.toString(), null, new String[]{"java/io/IOException"});
            iVar4.k(25, 1);
            iVar4.f(180, str16, "out", f313e);
            iVar4.k(58, c0018a3.g("out"));
            iVar4.k(25, 2);
            iVar4.c(192, b.a.a.n.b.g(cls3));
            iVar4.k(58, c0018a3.g("entity"));
            A(cls3, iVar4, cVarArr3, c0018a3);
            iVar4.h(177);
            iVar4.j(7, c0018a3.i + 2);
            iVar4.e();
            i9++;
        }
        byte[] bArrJ = cVar9.j();
        return (j0) this.j.a(str14, bArrJ, 0, bArrJ.length).getConstructor(cls).newInstance(z0Var);
    }
}
