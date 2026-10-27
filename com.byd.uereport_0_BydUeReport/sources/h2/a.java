package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byd.systemsettings.system.BydPowerManagerDevice;
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
    public static final String f5129a = l2.b.g(i0.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f5130b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f5131c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f5132d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f5133e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f5134f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f5135g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f5136h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f5137i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final l2.a f5138j = new l2.a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicLong f5139k = new AtomicLong();

    /* JADX INFO: renamed from: h2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASMSerializerFactory.java */
    public static class C0051a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static int f5140a = 6;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static int f5141b = 7;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static int f5142c = 8;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l2.c[] f5143d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f5144e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final z0 f5145f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f5146g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Map<String, Integer> f5147h = new HashMap();

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f5148i = 9;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final boolean f5149j;

        public C0051a(l2.c[] cVarArr, z0 z0Var, String str, boolean z7, boolean z8) {
            this.f5143d = cVarArr;
            this.f5144e = str;
            this.f5145f = z0Var;
            this.f5146g = z7;
            this.f5149j = z8 || z0Var.f5320a.isEnum();
        }

        public int f(String str) {
            int length = this.f5143d.length;
            for (int i8 = 0; i8 < length; i8++) {
                if (this.f5143d[i8].f6169d.equals(str)) {
                    return i8;
                }
            }
            return -1;
        }

        public int g(String str) {
            if (this.f5147h.get(str) == null) {
                Map<String, Integer> map = this.f5147h;
                int i8 = this.f5148i;
                this.f5148i = i8 + 1;
                map.put(str, Integer.valueOf(i8));
            }
            return this.f5147h.get(str).intValue();
        }

        public int h(String str, int i8) {
            if (this.f5147h.get(str) == null) {
                this.f5147h.put(str, Integer.valueOf(this.f5148i));
                this.f5148i += i8;
            }
            return this.f5147h.get(str).intValue();
        }
    }

    static {
        String strG = l2.b.g(t0.class);
        f5130b = strG;
        f5131c = "L" + strG + ";";
        String strG2 = l2.b.g(d1.class);
        f5132d = strG2;
        f5133e = "L" + strG2 + ";";
        f5134f = l2.b.g(j0.class);
        f5135g = "L" + l2.b.g(j0.class) + ";";
        f5136h = l2.b.b(y0.class);
        f5137i = l2.b.b(c1.class);
    }

    public final void A(Class<?> cls, e2.h hVar, l2.c[] cVarArr, C0051a c0051a) throws Exception {
        String str;
        int i8;
        int i9;
        String str2;
        String str3;
        boolean z7;
        char c8;
        a aVar;
        char c9;
        e2.f fVar;
        String str4;
        int i10;
        int i11;
        Class<?> cls2;
        int i12;
        e2.f fVar2;
        e2.f fVar3;
        String str5;
        e2.f fVar4;
        e2.f fVar5;
        int i13;
        int i14;
        String str6;
        int i15;
        e2.f fVar6;
        char c10;
        a aVar2 = this;
        l2.c[] cVarArr2 = cVarArr;
        e2.f fVar7 = new e2.f();
        hVar.k(25, 1);
        hVar.k(25, 0);
        String str7 = f5129a;
        hVar.d(182, str7, "hasPropertyFilters", "(" + f5137i + ")Z");
        hVar.b(154, fVar7);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(25, 3);
        hVar.k(25, 4);
        hVar.k(21, 5);
        String str8 = f5134f;
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
        hVar.k(25, c0051a.g("out"));
        hVar.k(16, 91);
        String str12 = f5132d;
        String str13 = "(I)V";
        hVar.d(182, str12, "write", "(I)V");
        int length = cVarArr2.length;
        if (length == 0) {
            hVar.k(25, c0051a.g("out"));
            hVar.k(16, 93);
            hVar.d(182, str12, "write", "(I)V");
            return;
        }
        int i16 = 0;
        while (i16 < length) {
            int i17 = i16 == length + (-1) ? 93 : 44;
            l2.c cVar = cVarArr2[i16];
            Class<?> cls3 = cVar.f6173h;
            hVar.i(cVar.f6169d);
            hVar.k(58, C0051a.f5140a);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str = str11;
                i8 = length;
                int i18 = i17;
                i9 = i16;
                str2 = str9;
                str3 = str13;
                z7 = false;
                c8 = 25;
                hVar.k(25, c0051a.g(str));
                hVar.h(89);
                aVar = this;
                aVar.i(hVar, c0051a, cVar);
                String str14 = f5132d;
                hVar.d(182, str14, "writeInt", str3);
                c9 = 16;
                hVar.k(16, i18);
                hVar.d(182, str14, "write", str3);
            } else {
                if (cls3 == Long.TYPE) {
                    hVar.k(25, c0051a.g(str11));
                    hVar.h(89);
                    aVar2.i(hVar, c0051a, cVar);
                    String str15 = f5132d;
                    c10 = 182;
                    hVar.d(182, str15, "writeLong", "(J)V");
                    hVar.k(16, i17);
                    hVar.d(182, str15, "write", str13);
                } else if (cls3 == Float.TYPE) {
                    hVar.k(25, c0051a.g(str11));
                    hVar.h(89);
                    aVar2.i(hVar, c0051a, cVar);
                    hVar.h(4);
                    String str16 = f5132d;
                    c10 = 182;
                    hVar.d(182, str16, "writeFloat", "(FZ)V");
                    hVar.k(16, i17);
                    hVar.d(182, str16, "write", str13);
                } else if (cls3 == Double.TYPE) {
                    hVar.k(25, c0051a.g(str11));
                    hVar.h(89);
                    aVar2.i(hVar, c0051a, cVar);
                    hVar.h(4);
                    String str17 = f5132d;
                    c10 = 182;
                    hVar.d(182, str17, "writeDouble", "(DZ)V");
                    hVar.k(16, i17);
                    hVar.d(182, str17, "write", str13);
                } else {
                    if (cls3 == Boolean.TYPE) {
                        hVar.k(25, c0051a.g(str11));
                        hVar.h(89);
                        aVar2.i(hVar, c0051a, cVar);
                        String str18 = f5132d;
                        hVar.d(182, str18, "write", "(Z)V");
                        hVar.k(16, i17);
                        hVar.d(182, str18, "write", str13);
                    } else if (cls3 == Character.TYPE) {
                        hVar.k(25, c0051a.g(str11));
                        aVar2.i(hVar, c0051a, cVar);
                        hVar.d(184, "java/lang/Character", "toString", "(C)Ljava/lang/String;");
                        hVar.k(16, i17);
                        hVar.d(182, f5132d, "writeString", "(Ljava/lang/String;C)V");
                        i8 = length;
                        i9 = i16;
                        str2 = str9;
                        z7 = false;
                        c8 = 25;
                        aVar = aVar2;
                        str = str11;
                        c9 = 16;
                        str3 = str13;
                    } else if (cls3 == String.class) {
                        hVar.k(25, c0051a.g(str11));
                        aVar2.i(hVar, c0051a, cVar);
                        hVar.k(16, i17);
                        hVar.d(182, f5132d, "writeString", "(Ljava/lang/String;C)V");
                        i8 = length;
                        c8 = 25;
                        i9 = i16;
                        str2 = str9;
                        str3 = str13;
                        z7 = false;
                        c9 = 16;
                        aVar = aVar2;
                        str = str11;
                    } else if (cls3.isEnum()) {
                        hVar.k(25, c0051a.g(str11));
                        hVar.h(89);
                        aVar2.i(hVar, c0051a, cVar);
                        String str19 = f5132d;
                        hVar.d(182, str19, "writeEnum", "(Ljava/lang/Enum;)V");
                        hVar.k(16, i17);
                        hVar.d(182, str19, "write", str13);
                    } else if (List.class.isAssignableFrom(cls3)) {
                        Type type = cVar.f6174i;
                        Type type2 = type instanceof Class ? Object.class : ((ParameterizedType) type).getActualTypeArguments()[0];
                        if (!(type2 instanceof Class) || (cls2 = (Class) type2) == Object.class) {
                            cls2 = null;
                        }
                        aVar2.i(hVar, c0051a, cVar);
                        i8 = length;
                        hVar.c(192, "java/util/List");
                        i9 = i16;
                        hVar.k(58, c0051a.g("list"));
                        if (cls2 == String.class && c0051a.f5146g) {
                            hVar.k(25, c0051a.g(str11));
                            hVar.k(25, c0051a.g("list"));
                            hVar.d(182, f5132d, "write", "(Ljava/util/List;)V");
                            str = str11;
                            i12 = i17;
                            str2 = str9;
                            str6 = str13;
                            i14 = 16;
                            i15 = 182;
                            i13 = 25;
                        } else {
                            e2.f fVar8 = new e2.f();
                            e2.f fVar9 = new e2.f();
                            i12 = i17;
                            hVar.k(25, c0051a.g("list"));
                            hVar.b(199, fVar9);
                            hVar.k(25, c0051a.g(str11));
                            String str20 = f5132d;
                            Type type3 = type2;
                            String str21 = str10;
                            hVar.d(182, str20, "writeNull", "()V");
                            hVar.b(167, fVar8);
                            hVar.l(fVar9);
                            hVar.k(25, c0051a.g("list"));
                            hVar.d(185, "java/util/List", "size", "()I");
                            hVar.k(54, c0051a.g("size"));
                            hVar.k(25, c0051a.g(str11));
                            hVar.k(16, 91);
                            hVar.d(182, str20, "write", str13);
                            e2.f fVar10 = new e2.f();
                            e2.f fVar11 = new e2.f();
                            e2.f fVar12 = new e2.f();
                            hVar.h(3);
                            String str22 = str9;
                            hVar.k(54, c0051a.g("i"));
                            hVar.l(fVar10);
                            hVar.k(21, c0051a.g("i"));
                            hVar.k(21, c0051a.g("size"));
                            hVar.b(162, fVar12);
                            hVar.k(21, c0051a.g("i"));
                            hVar.b(153, fVar11);
                            hVar.k(25, c0051a.g(str11));
                            hVar.k(16, 44);
                            hVar.d(182, str20, "write", str13);
                            hVar.l(fVar11);
                            hVar.k(25, c0051a.g("list"));
                            hVar.k(21, c0051a.g("i"));
                            hVar.d(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
                            hVar.k(58, c0051a.g("list_item"));
                            e2.f fVar13 = new e2.f();
                            e2.f fVar14 = new e2.f();
                            String str23 = str13;
                            hVar.k(25, c0051a.g("list_item"));
                            hVar.b(199, fVar14);
                            hVar.k(25, c0051a.g(str11));
                            String str24 = str11;
                            hVar.d(182, str20, "writeNull", "()V");
                            hVar.b(167, fVar13);
                            hVar.l(fVar14);
                            e2.f fVar15 = new e2.f();
                            e2.f fVar16 = new e2.f();
                            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                                fVar2 = fVar10;
                                fVar3 = fVar13;
                                str5 = str20;
                                fVar4 = fVar12;
                                str10 = str21;
                                str2 = str22;
                                fVar5 = fVar16;
                            } else {
                                hVar.k(25, c0051a.g("list_item"));
                                str5 = str20;
                                fVar4 = fVar12;
                                hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                                hVar.i(e2.j.g(l2.b.b(cls2)));
                                hVar.b(166, fVar16);
                                aVar2.k(c0051a, hVar, cVar, cls2);
                                hVar.k(58, c0051a.g("list_item_desc"));
                                e2.f fVar17 = new e2.f();
                                e2.f fVar18 = new e2.f();
                                if (c0051a.f5146g) {
                                    hVar.k(25, c0051a.g("list_item_desc"));
                                    String str25 = f5134f;
                                    hVar.c(193, str25);
                                    hVar.b(153, fVar17);
                                    hVar.k(25, c0051a.g("list_item_desc"));
                                    hVar.c(192, str25);
                                    hVar.k(25, 1);
                                    hVar.k(25, c0051a.g("list_item"));
                                    if (c0051a.f5149j) {
                                        hVar.h(1);
                                        fVar2 = fVar10;
                                        fVar3 = fVar13;
                                    } else {
                                        hVar.k(21, c0051a.g("i"));
                                        fVar2 = fVar10;
                                        fVar3 = fVar13;
                                        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                    }
                                    hVar.i(e2.j.g(l2.b.b(cls2)));
                                    hVar.i(Integer.valueOf(cVar.f6177l));
                                    StringBuilder sb2 = new StringBuilder();
                                    str2 = str22;
                                    sb2.append(str2);
                                    sb2.append(f5129a);
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
                                hVar.k(25, c0051a.g("list_item_desc"));
                                hVar.k(25, 1);
                                hVar.k(25, c0051a.g("list_item"));
                                if (c0051a.f5149j) {
                                    hVar.h(1);
                                } else {
                                    hVar.k(21, c0051a.g("i"));
                                    hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                }
                                hVar.i(e2.j.g(l2.b.b(cls2)));
                                hVar.i(Integer.valueOf(cVar.f6177l));
                                hVar.d(185, f5130b, "write", str2 + f5129a + str10);
                                hVar.l(fVar18);
                                hVar.b(167, fVar15);
                                fVar5 = fVar6;
                            }
                            hVar.l(fVar5);
                            hVar.k(25, 1);
                            hVar.k(25, c0051a.g("list_item"));
                            if (c0051a.f5149j) {
                                hVar.h(1);
                            } else {
                                hVar.k(21, c0051a.g("i"));
                                hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                            }
                            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                                hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                            } else {
                                hVar.i(e2.j.g(l2.b.b((Class) type3)));
                                hVar.i(Integer.valueOf(cVar.f6177l));
                                hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                            }
                            hVar.l(fVar15);
                            hVar.l(fVar3);
                            hVar.a(c0051a.g("i"), 1);
                            hVar.b(167, fVar2);
                            hVar.l(fVar4);
                            str = str24;
                            i13 = 25;
                            hVar.k(25, c0051a.g(str));
                            i14 = 16;
                            hVar.k(16, 93);
                            str6 = str23;
                            i15 = 182;
                            hVar.d(182, str5, "write", str6);
                            hVar.l(fVar8);
                        }
                        hVar.k(i13, c0051a.g(str));
                        hVar.k(i14, i12);
                        hVar.d(i15, f5132d, "write", str6);
                        aVar = this;
                        str3 = str6;
                        z7 = false;
                        c9 = 16;
                        c8 = 25;
                    } else {
                        String str26 = str11;
                        i8 = length;
                        int i19 = i17;
                        i9 = i16;
                        str2 = str9;
                        String str27 = str13;
                        e2.f fVar19 = new e2.f();
                        e2.f fVar20 = new e2.f();
                        i(hVar, c0051a, cVar);
                        hVar.h(89);
                        hVar.k(58, c0051a.g("field_" + cVar.f6173h.getName()));
                        hVar.b(199, fVar20);
                        hVar.k(25, c0051a.g(str26));
                        String str28 = f5132d;
                        hVar.d(182, str28, "writeNull", "()V");
                        hVar.b(167, fVar19);
                        hVar.l(fVar20);
                        e2.f fVar21 = new e2.f();
                        e2.f fVar22 = new e2.f();
                        hVar.k(25, c0051a.g("field_" + cVar.f6173h.getName()));
                        hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                        hVar.i(e2.j.g(l2.b.b(cls3)));
                        hVar.b(166, fVar22);
                        j(c0051a, hVar, cVar);
                        hVar.k(58, c0051a.g("fied_ser"));
                        e2.f fVar23 = new e2.f();
                        e2.f fVar24 = new e2.f();
                        if (c0051a.f5146g && Modifier.isPublic(cls3.getModifiers())) {
                            hVar.k(25, c0051a.g("fied_ser"));
                            String str29 = f5134f;
                            hVar.c(193, str29);
                            hVar.b(153, fVar23);
                            str4 = "writeWithFieldName";
                            hVar.k(25, c0051a.g("fied_ser"));
                            hVar.c(192, str29);
                            hVar.k(25, 1);
                            hVar.k(25, c0051a.g("field_" + cVar.f6173h.getName()));
                            hVar.k(25, C0051a.f5140a);
                            hVar.i(e2.j.g(l2.b.b(cls3)));
                            hVar.i(Integer.valueOf(cVar.f6177l));
                            fVar = fVar22;
                            hVar.d(182, str29, "writeAsArrayNonContext", str2 + f5129a + str10);
                            hVar.b(167, fVar24);
                            hVar.l(fVar23);
                        } else {
                            fVar = fVar22;
                            str4 = "writeWithFieldName";
                        }
                        hVar.k(25, c0051a.g("fied_ser"));
                        hVar.k(25, 1);
                        hVar.k(25, c0051a.g("field_" + cVar.f6173h.getName()));
                        hVar.k(25, C0051a.f5140a);
                        hVar.i(e2.j.g(l2.b.b(cls3)));
                        hVar.i(Integer.valueOf(cVar.f6177l));
                        String str30 = f5130b;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str2);
                        String str31 = f5129a;
                        sb3.append(str31);
                        sb3.append(str10);
                        hVar.d(185, str30, "write", sb3.toString());
                        hVar.l(fVar24);
                        hVar.b(167, fVar21);
                        hVar.l(fVar);
                        String strJ = cVar.j();
                        hVar.k(25, 1);
                        hVar.k(25, c0051a.g("field_" + cVar.f6173h.getName()));
                        if (strJ != null) {
                            hVar.i(strJ);
                            i10 = 182;
                            hVar.d(182, str31, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
                        } else {
                            i10 = 182;
                            hVar.k(25, C0051a.f5140a);
                            Type type4 = cVar.f6174i;
                            if ((type4 instanceof Class) && ((Class) type4).isPrimitive()) {
                                hVar.d(182, str31, str4, "(Ljava/lang/Object;Ljava/lang/Object;)V");
                            } else {
                                z7 = false;
                                hVar.k(25, 0);
                                hVar.f(180, c0051a.f5144e, cVar.f6169d + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                                hVar.i(Integer.valueOf(cVar.f6177l));
                                i11 = 182;
                                hVar.d(182, str31, str4, "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                                hVar.l(fVar21);
                                hVar.l(fVar19);
                                str = str26;
                                c8 = 25;
                                hVar.k(25, c0051a.g(str));
                                hVar.k(16, i19);
                                str3 = str27;
                                hVar.d(i11, str28, "write", str3);
                                aVar = this;
                                c9 = 16;
                            }
                        }
                        i11 = i10;
                        z7 = false;
                        hVar.l(fVar21);
                        hVar.l(fVar19);
                        str = str26;
                        c8 = 25;
                        hVar.k(25, c0051a.g(str));
                        hVar.k(16, i19);
                        str3 = str27;
                        hVar.d(i11, str28, "write", str3);
                        aVar = this;
                        c9 = 16;
                    }
                    i8 = length;
                    i9 = i16;
                    str2 = str9;
                    str3 = str13;
                    z7 = false;
                    c8 = 25;
                    c9 = 16;
                    aVar = aVar2;
                    str = str11;
                }
                i8 = length;
                i9 = i16;
                str2 = str9;
                str3 = str13;
                z7 = false;
                c8 = 25;
                c9 = 16;
                aVar = aVar2;
                str = str11;
            }
            str13 = str3;
            length = i8;
            cVarArr2 = cVarArr;
            str9 = str2;
            str11 = str;
            aVar2 = aVar;
            i16 = i9 + 1;
        }
    }

    public final void B(Class<?> cls, e2.h hVar, l2.c[] cVarArr, C0051a c0051a) throws Exception {
        e2.f fVar;
        String str;
        int i8;
        String str2;
        int i9;
        Class<?> cls2;
        e2.f fVar2 = new e2.f();
        int length = cVarArr.length;
        String str3 = "out";
        if (c0051a.f5146g) {
            fVar = fVar2;
        } else {
            e2.f fVar3 = new e2.f();
            e2.f fVar4 = new e2.f();
            fVar = fVar2;
            hVar.k(25, c0051a.g("out"));
            hVar.i(Integer.valueOf(SerializerFeature.PrettyFormat.mask));
            hVar.d(182, f5132d, "isEnabled", "(I)Z");
            hVar.b(154, fVar4);
            int length2 = cVarArr.length;
            int i10 = 0;
            boolean z7 = false;
            while (i10 < length2) {
                int i11 = length2;
                if (cVarArr[i10].f6170e != null) {
                    z7 = true;
                }
                i10++;
                length2 = i11;
            }
            if (z7) {
                hVar.k(25, c0051a.g("out"));
                hVar.i(Integer.valueOf(SerializerFeature.IgnoreErrorGetter.mask));
                hVar.d(182, f5132d, "isEnabled", "(I)Z");
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
            hVar.d(183, f5134f, "write", "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.h(177);
            hVar.l(fVar3);
        }
        if (!c0051a.f5149j) {
            e2.f fVar5 = new e2.f();
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(21, 5);
            hVar.d(182, f5134f, "writeReference", "(L" + f5129a + ";Ljava/lang/Object;I)Z");
            hVar.b(153, fVar5);
            hVar.h(177);
            hVar.l(fVar5);
        }
        String str4 = c0051a.f5146g ? c0051a.f5149j ? "writeAsArrayNonContext" : "writeAsArray" : "writeAsArrayNormal";
        int i12 = c0051a.f5145f.f5326g;
        SerializerFeature serializerFeature = SerializerFeature.BeanToArray;
        if ((i12 & serializerFeature.mask) == 0) {
            e2.f fVar6 = new e2.f();
            hVar.k(25, c0051a.g("out"));
            hVar.i(Integer.valueOf(serializerFeature.mask));
            hVar.d(182, f5132d, "isEnabled", "(I)Z");
            hVar.b(153, fVar6);
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.k(25, 4);
            hVar.k(21, 5);
            hVar.d(182, c0051a.f5144e, str4, "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.h(177);
            hVar.l(fVar6);
        } else {
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.k(25, 4);
            hVar.k(21, 5);
            hVar.d(182, c0051a.f5144e, str4, "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.h(177);
        }
        if (!c0051a.f5149j) {
            hVar.k(25, 1);
            String str5 = f5129a;
            StringBuilder sb = new StringBuilder();
            sb.append("()");
            String str6 = f5136h;
            sb.append(str6);
            hVar.d(182, str5, "getContext", sb.toString());
            hVar.k(58, c0051a.g("parent"));
            hVar.k(25, 1);
            hVar.k(25, c0051a.g("parent"));
            hVar.k(25, 2);
            hVar.k(25, 3);
            hVar.i(Integer.valueOf(c0051a.f5145f.f5326g));
            hVar.d(182, str5, "setContext", "(" + str6 + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean z8 = (c0051a.f5145f.f5326g & SerializerFeature.WriteClassName.mask) != 0;
        if (z8 || !c0051a.f5146g) {
            e2.f fVar7 = new e2.f();
            e2.f fVar8 = new e2.f();
            e2.f fVar9 = new e2.f();
            if (z8) {
                str = "parent";
                i8 = 182;
            } else {
                hVar.k(25, 1);
                hVar.k(25, 4);
                hVar.k(25, 2);
                str = "parent";
                i8 = 182;
                hVar.d(182, f5129a, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                hVar.b(153, fVar8);
            }
            hVar.k(25, 4);
            hVar.k(25, 2);
            hVar.d(i8, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            hVar.b(165, fVar8);
            hVar.l(fVar9);
            hVar.k(25, c0051a.g("out"));
            hVar.k(16, 123);
            hVar.d(i8, f5132d, "write", "(I)V");
            hVar.k(25, 0);
            hVar.k(25, 1);
            if (c0051a.f5145f.f5322c != null) {
                hVar.i(c0051a.f5145f.f5322c);
            } else {
                hVar.h(1);
            }
            hVar.k(25, 2);
            hVar.d(182, f5134f, "writeClassName", "(L" + f5129a + ";Ljava/lang/String;Ljava/lang/Object;)V");
            hVar.k(16, 44);
            hVar.b(167, fVar7);
            hVar.l(fVar8);
            hVar.k(16, 123);
            hVar.l(fVar7);
        } else {
            hVar.k(16, 123);
            str = "parent";
        }
        hVar.k(54, c0051a.g("seperator"));
        if (!c0051a.f5146g) {
            c(hVar, c0051a);
        }
        if (!c0051a.f5146g) {
            hVar.k(25, c0051a.g("out"));
            hVar.d(182, f5132d, "isNotWriteDefaultValue", "()Z");
            hVar.k(54, c0051a.g("notWriteDefaultValue"));
            hVar.k(25, 1);
            hVar.k(25, 0);
            String str7 = f5129a;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(");
            String str8 = f5137i;
            sb2.append(str8);
            sb2.append(")Z");
            hVar.d(182, str7, "checkValue", sb2.toString());
            hVar.k(54, c0051a.g("checkValue"));
            hVar.k(25, 1);
            hVar.k(25, 0);
            hVar.d(182, str7, "hasNameFilters", "(" + str8 + ")Z");
            hVar.k(54, c0051a.g("hasNameFilters"));
        }
        int i13 = 0;
        while (i13 < length) {
            l2.c cVar = cVarArr[i13];
            Class<?> cls3 = cVar.f6173h;
            hVar.i(cVar.f6169d);
            hVar.k(58, C0051a.f5140a);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str2 = str3;
                i9 = i13;
                m(cls, hVar, cVar, c0051a, c0051a.g(cls3.getName()), 'I');
            } else {
                if (cls3 == Long.TYPE) {
                    cls2 = cls;
                    p(cls2, hVar, cVar, c0051a);
                } else {
                    cls2 = cls;
                    if (cls3 == Float.TYPE) {
                        h(cls2, hVar, cVar, c0051a);
                    } else if (cls3 == Double.TYPE) {
                        e(cls2, hVar, cVar, c0051a);
                    } else if (cls3 == Boolean.TYPE) {
                        str2 = str3;
                        i9 = i13;
                        m(cls, hVar, cVar, c0051a, c0051a.g("boolean"), 'Z');
                    } else {
                        str2 = str3;
                        i9 = i13;
                        if (cls3 == Character.TYPE) {
                            m(cls, hVar, cVar, c0051a, c0051a.g("char"), 'C');
                        } else if (cls3 == String.class) {
                            w(cls2, hVar, cVar, c0051a);
                        } else if (cls3 == BigDecimal.class) {
                            d(cls2, hVar, cVar, c0051a);
                        } else if (List.class.isAssignableFrom(cls3)) {
                            o(cls2, hVar, cVar, c0051a);
                        } else if (cls3.isEnum()) {
                            f(cls2, hVar, cVar, c0051a);
                        } else {
                            s(cls2, hVar, cVar, c0051a);
                        }
                    }
                }
                str2 = str3;
                i9 = i13;
            }
            i13 = i9 + 1;
            str3 = str2;
        }
        String str9 = str3;
        if (!c0051a.f5146g) {
            a(hVar, c0051a);
        }
        e2.f fVar10 = new e2.f();
        e2.f fVar11 = new e2.f();
        hVar.k(21, c0051a.g("seperator"));
        hVar.g(16, 123);
        hVar.b(160, fVar10);
        hVar.k(25, c0051a.g(str9));
        hVar.k(16, 123);
        String str10 = f5132d;
        hVar.d(182, str10, "write", "(I)V");
        hVar.l(fVar10);
        hVar.k(25, c0051a.g(str9));
        hVar.k(16, 125);
        hVar.d(182, str10, "write", "(I)V");
        hVar.l(fVar11);
        hVar.l(fVar);
        if (c0051a.f5149j) {
            return;
        }
        hVar.k(25, 1);
        hVar.k(25, c0051a.g(str));
        hVar.d(182, f5129a, "setContext", "(" + f5136h + ")V");
    }

    public final void a(e2.h hVar, C0051a c0051a) {
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(21, c0051a.g("seperator"));
        hVar.d(182, f5134f, "writeAfter", "(L" + f5129a + ";Ljava/lang/Object;C)C");
        hVar.k(54, c0051a.g("seperator"));
    }

    public final void b(e2.h hVar, l2.c cVar, C0051a c0051a) {
        Class<?> cls = cVar.f6173h;
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(25, C0051a.f5140a);
        if (cls == Byte.TYPE) {
            hVar.k(21, c0051a.g("byte"));
            hVar.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0051a.g("short"));
            hVar.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0051a.g("int"));
            hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            hVar.k(21, c0051a.g("char"));
            hVar.d(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0051a.h("long", 2));
            hVar.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0051a.g("float"));
            hVar.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0051a.h("double", 2));
            hVar.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            hVar.k(21, c0051a.g("boolean"));
            hVar.d(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            hVar.k(25, c0051a.g("decimal"));
        } else if (cls == String.class) {
            hVar.k(25, c0051a.g("string"));
        } else if (cls.isEnum()) {
            hVar.k(25, c0051a.g("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            hVar.k(25, c0051a.g("list"));
        } else {
            hVar.k(25, c0051a.g("object"));
        }
        hVar.d(182, f5134f, "apply", "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    public final void c(e2.h hVar, C0051a c0051a) {
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(21, c0051a.g("seperator"));
        hVar.d(182, f5134f, "writeBefore", "(L" + f5129a + ";Ljava/lang/Object;C)C");
        hVar.k(54, c0051a.g("seperator"));
    }

    public final void d(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(58, c0051a.g("decimal"));
        g(hVar, cVar, c0051a, fVar);
        e2.f fVar2 = new e2.f();
        e2.f fVar3 = new e2.f();
        e2.f fVar4 = new e2.f();
        hVar.l(fVar2);
        hVar.k(25, c0051a.g("decimal"));
        hVar.b(199, fVar3);
        l(hVar, cVar, c0051a);
        hVar.b(167, fVar4);
        hVar.l(fVar3);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        hVar.k(25, C0051a.f5140a);
        hVar.k(25, c0051a.g("decimal"));
        hVar.d(182, f5132d, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        v(hVar, c0051a);
        hVar.b(167, fVar4);
        hVar.l(fVar4);
        hVar.l(fVar);
    }

    public final void e(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(57, c0051a.h("double", 2));
        g(hVar, cVar, c0051a, fVar);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        hVar.k(25, C0051a.f5140a);
        hVar.k(24, c0051a.h("double", 2));
        hVar.d(182, f5132d, "writeFieldValue", "(CLjava/lang/String;D)V");
        v(hVar, c0051a);
        hVar.l(fVar);
    }

    public final void f(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        e2.f fVar2 = new e2.f();
        e2.f fVar3 = new e2.f();
        q(hVar, cVar, c0051a, fVar3);
        i(hVar, c0051a, cVar);
        hVar.c(192, "java/lang/Enum");
        hVar.k(58, c0051a.g("enum"));
        g(hVar, cVar, c0051a, fVar3);
        hVar.k(25, c0051a.g("enum"));
        hVar.b(199, fVar);
        l(hVar, cVar, c0051a);
        hVar.b(167, fVar2);
        hVar.l(fVar);
        if (c0051a.f5146g) {
            hVar.k(25, c0051a.g("out"));
            hVar.k(21, c0051a.g("seperator"));
            hVar.k(25, C0051a.f5140a);
            hVar.k(25, c0051a.g("enum"));
            hVar.d(182, "java/lang/Enum", "name", "()Ljava/lang/String;");
            hVar.d(182, f5132d, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            hVar.k(25, c0051a.g("out"));
            hVar.k(21, c0051a.g("seperator"));
            String str = f5132d;
            hVar.d(182, str, "write", "(I)V");
            hVar.k(25, c0051a.g("out"));
            hVar.k(25, C0051a.f5140a);
            hVar.h(3);
            hVar.d(182, str, "writeFieldName", "(Ljava/lang/String;Z)V");
            hVar.k(25, 1);
            hVar.k(25, c0051a.g("enum"));
            hVar.k(25, C0051a.f5140a);
            hVar.i(e2.j.g(l2.b.b(cVar.f6173h)));
            hVar.i(Integer.valueOf(cVar.f6177l));
            hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        v(hVar, c0051a);
        hVar.l(fVar2);
        hVar.l(fVar3);
    }

    public final void g(e2.h hVar, l2.c cVar, C0051a c0051a, e2.f fVar) {
        if (cVar.f6183r) {
            hVar.k(25, c0051a.g("out"));
            hVar.i(Integer.valueOf(SerializerFeature.SkipTransientField.mask));
            hVar.d(182, f5132d, "isEnabled", "(I)Z");
            hVar.b(154, fVar);
        }
        r(hVar, cVar, c0051a, fVar);
        if (c0051a.f5146g) {
            return;
        }
        b(hVar, cVar, c0051a);
        hVar.b(153, fVar);
        t(hVar, cVar, c0051a);
        u(hVar, cVar, c0051a, fVar);
    }

    public final void h(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(56, c0051a.g("float"));
        g(hVar, cVar, c0051a, fVar);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        hVar.k(25, C0051a.f5140a);
        hVar.k(23, c0051a.g("float"));
        hVar.d(182, f5132d, "writeFieldValue", "(CLjava/lang/String;F)V");
        v(hVar, c0051a);
        hVar.l(fVar);
    }

    public final void i(e2.h hVar, C0051a c0051a, l2.c cVar) {
        Method method = cVar.f6170e;
        if (method != null) {
            hVar.k(25, c0051a.g("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            hVar.d(declaringClass.isInterface() ? 185 : 182, l2.b.g(declaringClass), method.getName(), l2.b.c(method));
            if (method.getReturnType().equals(cVar.f6173h)) {
                return;
            }
            hVar.c(192, l2.b.g(cVar.f6173h));
            return;
        }
        hVar.k(25, c0051a.g("entity"));
        Field field = cVar.f6171f;
        hVar.f(180, l2.b.g(cVar.f6175j), field.getName(), l2.b.b(field.getType()));
        if (field.getType().equals(cVar.f6173h)) {
            return;
        }
        hVar.c(192, l2.b.g(cVar.f6173h));
    }

    public final void j(C0051a c0051a, e2.h hVar, l2.c cVar) {
        e2.f fVar = new e2.f();
        hVar.k(25, 0);
        String str = c0051a.f5144e;
        String str2 = cVar.f6169d + "_asm_ser_";
        String str3 = f5131c;
        hVar.f(180, str, str2, str3);
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.i(e2.j.g(l2.b.b(cVar.f6173h)));
        hVar.d(182, f5129a, "getObjectWriter", "(Ljava/lang/Class;)" + str3);
        hVar.f(181, c0051a.f5144e, cVar.f6169d + "_asm_ser_", str3);
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0051a.f5144e, cVar.f6169d + "_asm_ser_", str3);
    }

    public final void k(C0051a c0051a, e2.h hVar, l2.c cVar, Class<?> cls) {
        e2.f fVar = new e2.f();
        hVar.k(25, 0);
        String str = c0051a.f5144e;
        String str2 = cVar.f6169d + "_asm_list_item_ser_";
        String str3 = f5131c;
        hVar.f(180, str, str2, str3);
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.i(e2.j.g(l2.b.b(cls)));
        hVar.d(182, f5129a, "getObjectWriter", "(Ljava/lang/Class;)" + str3);
        hVar.f(181, c0051a.f5144e, cVar.f6169d + "_asm_list_item_ser_", str3);
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0051a.f5144e, cVar.f6169d + "_asm_list_item_ser_", str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(e2.h r17, l2.c r18, h2.a.C0051a r19) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.a.l(e2.h, l2.c, h2.a$a):void");
    }

    public final void m(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a, int i8, char c8) {
        e2.f fVar = new e2.f();
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(54, i8);
        g(hVar, cVar, c0051a, fVar);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        hVar.k(25, C0051a.f5140a);
        hVar.k(21, i8);
        hVar.d(182, f5132d, "writeFieldValue", "(CLjava/lang/String;" + c8 + ")V");
        v(hVar, c0051a);
        hVar.l(fVar);
    }

    public final void n(e2.h hVar, l2.c cVar, C0051a c0051a, e2.f fVar) {
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.i(cVar.f6179n);
        hVar.d(182, f5134f, "applyLabel", "(L" + f5129a + ";Ljava/lang/String;)Z");
        hVar.b(153, fVar);
    }

    public final void o(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar;
        e2.f fVar2;
        e2.f fVar3;
        String str;
        e2.f fVar4;
        e2.f fVar5;
        String str2;
        e2.f fVar6;
        l2.c cVar2;
        int i8;
        int i9;
        int i10;
        e2.f fVar7;
        String str3;
        String str4;
        Type typeU = l2.l.U(cVar.f6174i);
        Class<?> cls2 = null;
        Class<?> cls3 = typeU instanceof Class ? (Class) typeU : null;
        if (cls3 != Object.class && cls3 != Serializable.class) {
            cls2 = cls3;
        }
        e2.f fVar8 = new e2.f();
        e2.f fVar9 = new e2.f();
        e2.f fVar10 = new e2.f();
        q(hVar, cVar, c0051a, fVar8);
        i(hVar, c0051a, cVar);
        hVar.c(192, "java/util/List");
        hVar.k(58, c0051a.g("list"));
        g(hVar, cVar, c0051a, fVar8);
        hVar.k(25, c0051a.g("list"));
        hVar.b(199, fVar9);
        l(hVar, cVar, c0051a);
        hVar.b(167, fVar10);
        hVar.l(fVar9);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        String str5 = f5132d;
        hVar.d(182, str5, "write", "(I)V");
        x(hVar, c0051a);
        hVar.k(25, c0051a.g("list"));
        hVar.d(185, "java/util/List", "size", "()I");
        hVar.k(54, c0051a.g("size"));
        e2.f fVar11 = new e2.f();
        e2.f fVar12 = new e2.f();
        hVar.k(21, c0051a.g("size"));
        hVar.h(3);
        hVar.b(160, fVar11);
        hVar.k(25, c0051a.g("out"));
        hVar.i("[]");
        hVar.d(182, str5, "write", "(Ljava/lang/String;)V");
        hVar.b(167, fVar12);
        hVar.l(fVar11);
        if (c0051a.f5149j) {
            fVar = fVar12;
        } else {
            hVar.k(25, 1);
            hVar.k(25, c0051a.g("list"));
            hVar.k(25, C0051a.f5140a);
            fVar = fVar12;
            hVar.d(182, f5129a, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (typeU == String.class && c0051a.f5146g) {
            hVar.k(25, c0051a.g("out"));
            hVar.k(25, c0051a.g("list"));
            hVar.d(182, str5, "write", "(Ljava/util/List;)V");
            i9 = 25;
            i10 = 182;
            i8 = 1;
        } else {
            hVar.k(25, c0051a.g("out"));
            hVar.k(16, 91);
            hVar.d(182, str5, "write", "(I)V");
            e2.f fVar13 = new e2.f();
            e2.f fVar14 = new e2.f();
            e2.f fVar15 = new e2.f();
            hVar.h(3);
            hVar.k(54, c0051a.g("i"));
            hVar.l(fVar13);
            hVar.k(21, c0051a.g("i"));
            hVar.k(21, c0051a.g("size"));
            hVar.b(162, fVar15);
            hVar.k(21, c0051a.g("i"));
            hVar.b(153, fVar14);
            hVar.k(25, c0051a.g("out"));
            hVar.k(16, 44);
            hVar.d(182, str5, "write", "(I)V");
            hVar.l(fVar14);
            hVar.k(25, c0051a.g("list"));
            hVar.k(21, c0051a.g("i"));
            hVar.d(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
            hVar.k(58, c0051a.g("list_item"));
            e2.f fVar16 = new e2.f();
            e2.f fVar17 = new e2.f();
            hVar.k(25, c0051a.g("list_item"));
            hVar.b(199, fVar17);
            hVar.k(25, c0051a.g("out"));
            hVar.d(182, str5, "writeNull", "()V");
            hVar.b(167, fVar16);
            hVar.l(fVar17);
            e2.f fVar18 = new e2.f();
            e2.f fVar19 = new e2.f();
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
                hVar.k(25, c0051a.g("list_item"));
                fVar6 = fVar15;
                fVar2 = fVar13;
                hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                hVar.i(e2.j.g(l2.b.b(cls2)));
                hVar.b(166, fVar19);
                cVar2 = cVar;
                k(c0051a, hVar, cVar2, cls2);
                hVar.k(58, c0051a.g("list_item_desc"));
                e2.f fVar20 = new e2.f();
                e2.f fVar21 = new e2.f();
                if (c0051a.f5146g) {
                    if (c0051a.f5149j && c0051a.f5146g) {
                        fVar3 = fVar16;
                        str4 = "writeDirectNonContext";
                        fVar5 = fVar19;
                    } else {
                        fVar3 = fVar16;
                        fVar5 = fVar19;
                        str4 = "write";
                    }
                    fVar7 = fVar18;
                    hVar.k(25, c0051a.g("list_item_desc"));
                    String str6 = f5134f;
                    hVar.c(193, str6);
                    hVar.b(153, fVar20);
                    str3 = "write";
                    hVar.k(25, c0051a.g("list_item_desc"));
                    hVar.c(192, str6);
                    hVar.k(25, 1);
                    hVar.k(25, c0051a.g("list_item"));
                    if (c0051a.f5149j) {
                        hVar.h(1);
                    } else {
                        hVar.k(21, c0051a.g("i"));
                        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    hVar.i(e2.j.g(l2.b.b(cls2)));
                    hVar.i(Integer.valueOf(cVar2.f6177l));
                    hVar.d(182, str6, str4, "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    hVar.b(167, fVar21);
                    hVar.l(fVar20);
                } else {
                    fVar3 = fVar16;
                    fVar7 = fVar18;
                    fVar5 = fVar19;
                    str3 = "write";
                }
                hVar.k(25, c0051a.g("list_item_desc"));
                hVar.k(25, 1);
                hVar.k(25, c0051a.g("list_item"));
                if (c0051a.f5149j) {
                    hVar.h(1);
                } else {
                    hVar.k(21, c0051a.g("i"));
                    hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                hVar.i(e2.j.g(l2.b.b(cls2)));
                hVar.i(Integer.valueOf(cVar2.f6177l));
                str2 = str3;
                hVar.d(185, f5130b, str2, "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                hVar.l(fVar21);
                fVar4 = fVar7;
                hVar.b(167, fVar4);
            }
            hVar.l(fVar5);
            hVar.k(25, 1);
            hVar.k(25, c0051a.g("list_item"));
            if (c0051a.f5149j) {
                hVar.h(1);
            } else {
                hVar.k(21, c0051a.g("i"));
                hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                hVar.i(e2.j.g(l2.b.b((Class) typeU)));
                hVar.i(Integer.valueOf(cVar2.f6177l));
                hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
            hVar.l(fVar4);
            hVar.l(fVar3);
            i8 = 1;
            hVar.a(c0051a.g("i"), 1);
            hVar.b(167, fVar2);
            hVar.l(fVar6);
            i9 = 25;
            hVar.k(25, c0051a.g(str));
            hVar.k(16, 93);
            i10 = 182;
            hVar.d(182, str5, str2, "(I)V");
        }
        hVar.k(i9, i8);
        hVar.d(i10, f5129a, "popContext", "()V");
        hVar.l(fVar);
        v(hVar, c0051a);
        hVar.l(fVar10);
        hVar.l(fVar8);
    }

    public final void p(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(55, c0051a.h("long", 2));
        g(hVar, cVar, c0051a, fVar);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        hVar.k(25, C0051a.f5140a);
        hVar.k(22, c0051a.h("long", 2));
        hVar.d(182, f5132d, "writeFieldValue", "(CLjava/lang/String;J)V");
        v(hVar, c0051a);
        hVar.l(fVar);
    }

    public final void q(e2.h hVar, l2.c cVar, C0051a c0051a, e2.f fVar) {
        if (!c0051a.f5146g) {
            hVar.k(25, 0);
            hVar.k(25, 1);
            hVar.k(25, 2);
            hVar.k(25, C0051a.f5140a);
            hVar.d(182, f5134f, "applyName", "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            hVar.b(153, fVar);
            n(hVar, cVar, c0051a, fVar);
        }
        if (cVar.f6171f == null) {
            hVar.k(25, c0051a.g("out"));
            hVar.i(Integer.valueOf(SerializerFeature.IgnoreNonFieldGetter.mask));
            hVar.d(182, f5132d, "isEnabled", "(I)Z");
            hVar.b(154, fVar);
        }
    }

    public final void r(e2.h hVar, l2.c cVar, C0051a c0051a, e2.f fVar) {
        if (c0051a.f5146g) {
            return;
        }
        e2.f fVar2 = new e2.f();
        hVar.k(21, c0051a.g("notWriteDefaultValue"));
        hVar.b(153, fVar2);
        Class<?> cls = cVar.f6173h;
        if (cls == Boolean.TYPE) {
            hVar.k(21, c0051a.g("boolean"));
            hVar.b(153, fVar);
        } else if (cls == Byte.TYPE) {
            hVar.k(21, c0051a.g("byte"));
            hVar.b(153, fVar);
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0051a.g("short"));
            hVar.b(153, fVar);
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0051a.g("int"));
            hVar.b(153, fVar);
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0051a.g("long"));
            hVar.h(9);
            hVar.h(148);
            hVar.b(153, fVar);
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0051a.g("float"));
            hVar.h(11);
            hVar.h(149);
            hVar.b(153, fVar);
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0051a.g("double"));
            hVar.h(14);
            hVar.h(151);
            hVar.b(153, fVar);
        }
        hVar.l(fVar2);
    }

    public final void s(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(58, c0051a.g("object"));
        g(hVar, cVar, c0051a, fVar);
        y(hVar, cVar, c0051a, fVar);
        hVar.l(fVar);
    }

    public final void t(e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        hVar.k(21, c0051a.g("hasNameFilters"));
        hVar.b(153, fVar);
        Class<?> cls = cVar.f6173h;
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 2);
        hVar.k(25, C0051a.f5140a);
        if (cls == Byte.TYPE) {
            hVar.k(21, c0051a.g("byte"));
            hVar.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0051a.g("short"));
            hVar.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0051a.g("int"));
            hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            hVar.k(21, c0051a.g("char"));
            hVar.d(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0051a.h("long", 2));
            hVar.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0051a.g("float"));
            hVar.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0051a.h("double", 2));
            hVar.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            hVar.k(21, c0051a.g("boolean"));
            hVar.d(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            hVar.k(25, c0051a.g("decimal"));
        } else if (cls == String.class) {
            hVar.k(25, c0051a.g("string"));
        } else if (cls.isEnum()) {
            hVar.k(25, c0051a.g("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            hVar.k(25, c0051a.g("list"));
        } else {
            hVar.k(25, c0051a.g("object"));
        }
        hVar.d(182, f5134f, "processKey", "(L" + f5129a + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        hVar.k(58, C0051a.f5140a);
        hVar.l(fVar);
    }

    public final void u(e2.h hVar, l2.c cVar, C0051a c0051a, e2.f fVar) {
        e2.f fVar2 = new e2.f();
        Class<?> cls = cVar.f6173h;
        if (cls.isPrimitive()) {
            e2.f fVar3 = new e2.f();
            hVar.k(21, c0051a.g("checkValue"));
            hVar.b(154, fVar3);
            hVar.h(1);
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
            hVar.k(58, C0051a.f5142c);
            hVar.b(167, fVar2);
            hVar.l(fVar3);
        }
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 0);
        hVar.i(Integer.valueOf(c0051a.f(cVar.f6169d)));
        String str = f5134f;
        hVar.d(182, str, "getBeanContext", "(I)" + l2.b.b(j.class));
        hVar.k(25, 2);
        hVar.k(25, C0051a.f5140a);
        if (cls == Byte.TYPE) {
            hVar.k(21, c0051a.g("byte"));
            hVar.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Short.TYPE) {
            hVar.k(21, c0051a.g("short"));
            hVar.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Integer.TYPE) {
            hVar.k(21, c0051a.g("int"));
            hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Character.TYPE) {
            hVar.k(21, c0051a.g("char"));
            hVar.d(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Long.TYPE) {
            hVar.k(22, c0051a.h("long", 2));
            hVar.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Float.TYPE) {
            hVar.k(23, c0051a.g("float"));
            hVar.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Double.TYPE) {
            hVar.k(24, c0051a.h("double", 2));
            hVar.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == Boolean.TYPE) {
            hVar.k(21, c0051a.g("boolean"));
            hVar.d(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            hVar.h(89);
            hVar.k(58, C0051a.f5141b);
        } else if (cls == BigDecimal.class) {
            hVar.k(25, c0051a.g("decimal"));
            hVar.k(58, C0051a.f5141b);
            hVar.k(25, C0051a.f5141b);
        } else if (cls == String.class) {
            hVar.k(25, c0051a.g("string"));
            hVar.k(58, C0051a.f5141b);
            hVar.k(25, C0051a.f5141b);
        } else if (cls.isEnum()) {
            hVar.k(25, c0051a.g("enum"));
            hVar.k(58, C0051a.f5141b);
            hVar.k(25, C0051a.f5141b);
        } else if (List.class.isAssignableFrom(cls)) {
            hVar.k(25, c0051a.g("list"));
            hVar.k(58, C0051a.f5141b);
            hVar.k(25, C0051a.f5141b);
        } else {
            hVar.k(25, c0051a.g("object"));
            hVar.k(58, C0051a.f5141b);
            hVar.k(25, C0051a.f5141b);
        }
        hVar.d(182, str, "processValue", "(L" + f5129a + ";" + l2.b.b(j.class) + "Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
        hVar.k(58, C0051a.f5142c);
        hVar.k(25, C0051a.f5141b);
        hVar.k(25, C0051a.f5142c);
        hVar.b(165, fVar2);
        y(hVar, cVar, c0051a, fVar);
        hVar.b(167, fVar);
        hVar.l(fVar2);
    }

    public final void v(e2.h hVar, C0051a c0051a) {
        hVar.k(16, 44);
        hVar.k(54, c0051a.g("seperator"));
    }

    public final void w(Class<?> cls, e2.h hVar, l2.c cVar, C0051a c0051a) {
        e2.f fVar = new e2.f();
        if (cVar.f6169d.equals(c0051a.f5145f.f5322c)) {
            hVar.k(25, 1);
            hVar.k(25, 4);
            hVar.k(25, 2);
            hVar.d(182, f5129a, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            hVar.b(154, fVar);
        }
        q(hVar, cVar, c0051a, fVar);
        i(hVar, c0051a, cVar);
        hVar.k(58, c0051a.g("string"));
        g(hVar, cVar, c0051a, fVar);
        e2.f fVar2 = new e2.f();
        e2.f fVar3 = new e2.f();
        hVar.k(25, c0051a.g("string"));
        hVar.b(199, fVar2);
        l(hVar, cVar, c0051a);
        hVar.b(167, fVar3);
        hVar.l(fVar2);
        if ("trim".equals(cVar.f6188w)) {
            hVar.k(25, c0051a.g("string"));
            hVar.d(182, "java/lang/String", "trim", "()Ljava/lang/String;");
            hVar.k(58, c0051a.g("string"));
        }
        if (c0051a.f5146g) {
            hVar.k(25, c0051a.g("out"));
            hVar.k(21, c0051a.g("seperator"));
            hVar.k(25, C0051a.f5140a);
            hVar.k(25, c0051a.g("string"));
            hVar.d(182, f5132d, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            hVar.k(25, c0051a.g("out"));
            hVar.k(21, c0051a.g("seperator"));
            hVar.k(25, C0051a.f5140a);
            hVar.k(25, c0051a.g("string"));
            hVar.d(182, f5132d, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        v(hVar, c0051a);
        hVar.l(fVar3);
        hVar.l(fVar);
    }

    public final void x(e2.h hVar, C0051a c0051a) {
        if (c0051a.f5146g) {
            hVar.k(25, c0051a.g("out"));
            hVar.k(25, C0051a.f5140a);
            hVar.d(182, f5132d, "writeFieldNameDirect", "(Ljava/lang/String;)V");
        } else {
            hVar.k(25, c0051a.g("out"));
            hVar.k(25, C0051a.f5140a);
            hVar.h(3);
            hVar.d(182, f5132d, "writeFieldName", "(Ljava/lang/String;Z)V");
        }
    }

    public final void y(e2.h hVar, l2.c cVar, C0051a c0051a, e2.f fVar) {
        Class<?> cls;
        String str;
        e2.f fVar2;
        e2.f fVar3;
        String strJ = cVar.j();
        Class<?> cls2 = cVar.f6173h;
        e2.f fVar4 = new e2.f();
        if (c0051a.f5146g) {
            hVar.k(25, c0051a.g("object"));
        } else {
            hVar.k(25, C0051a.f5142c);
        }
        hVar.h(89);
        hVar.k(58, c0051a.g("object"));
        hVar.b(199, fVar4);
        l(hVar, cVar, c0051a);
        hVar.b(167, fVar);
        hVar.l(fVar4);
        hVar.k(25, c0051a.g("out"));
        hVar.k(21, c0051a.g("seperator"));
        hVar.d(182, f5132d, "write", "(I)V");
        x(hVar, c0051a);
        e2.f fVar5 = new e2.f();
        e2.f fVar6 = new e2.f();
        if (!Modifier.isPublic(cls2.getModifiers()) || f2.h.v(cls2)) {
            cls = String.class;
            str = strJ;
            fVar2 = fVar5;
            fVar3 = fVar6;
        } else {
            hVar.k(25, c0051a.g("object"));
            cls = String.class;
            hVar.d(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            hVar.i(e2.j.g(l2.b.b(cls2)));
            hVar.b(166, fVar6);
            j(c0051a, hVar, cVar);
            hVar.k(58, c0051a.g("fied_ser"));
            e2.f fVar7 = new e2.f();
            e2.f fVar8 = new e2.f();
            hVar.k(25, c0051a.g("fied_ser"));
            String str2 = f5134f;
            hVar.c(193, str2);
            hVar.b(153, fVar7);
            int i8 = cVar.f6177l;
            str = strJ;
            boolean z7 = (SerializerFeature.DisableCircularReferenceDetect.mask & i8) != 0;
            boolean z8 = (SerializerFeature.BeanToArray.mask & i8) != 0;
            String str3 = (z7 || (c0051a.f5149j && c0051a.f5146g)) ? z8 ? "writeAsArrayNonContext" : "writeDirectNonContext" : z8 ? "writeAsArray" : "write";
            hVar.k(25, c0051a.g("fied_ser"));
            hVar.c(192, str2);
            hVar.k(25, 1);
            hVar.k(25, c0051a.g("object"));
            hVar.k(25, C0051a.f5140a);
            hVar.k(25, 0);
            hVar.f(180, c0051a.f5144e, cVar.f6169d + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            hVar.i(Integer.valueOf(cVar.f6177l));
            StringBuilder sb = new StringBuilder();
            sb.append("(L");
            String str4 = f5129a;
            sb.append(str4);
            sb.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.d(182, str2, str3, sb.toString());
            hVar.b(167, fVar8);
            hVar.l(fVar7);
            hVar.k(25, c0051a.g("fied_ser"));
            hVar.k(25, 1);
            hVar.k(25, c0051a.g("object"));
            hVar.k(25, C0051a.f5140a);
            hVar.k(25, 0);
            hVar.f(180, c0051a.f5144e, cVar.f6169d + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            hVar.i(Integer.valueOf(cVar.f6177l));
            hVar.d(185, f5130b, "write", "(L" + str4 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            hVar.l(fVar8);
            fVar2 = fVar5;
            hVar.b(167, fVar2);
            fVar3 = fVar6;
        }
        hVar.l(fVar3);
        hVar.k(25, 1);
        if (c0051a.f5146g) {
            hVar.k(25, c0051a.g("object"));
        } else {
            hVar.k(25, C0051a.f5142c);
        }
        if (str != null) {
            hVar.i(str);
            hVar.d(182, f5129a, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            hVar.k(25, C0051a.f5140a);
            Type type = cVar.f6174i;
            if ((type instanceof Class) && ((Class) type).isPrimitive()) {
                hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                Class<?> cls3 = cls;
                if (cVar.f6173h == cls3) {
                    hVar.i(e2.j.g(l2.b.b(cls3)));
                } else {
                    hVar.k(25, 0);
                    hVar.f(180, c0051a.f5144e, cVar.f6169d + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                hVar.i(Integer.valueOf(cVar.f6177l));
                hVar.d(182, f5129a, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
        }
        hVar.l(fVar2);
        v(hVar, c0051a);
    }

    public j0 z(z0 z0Var) throws Exception {
        String str;
        String str2;
        boolean z7;
        Class<z0> cls;
        String str3;
        boolean z8;
        boolean z9;
        String str4;
        boolean z10;
        boolean z11;
        d2.d dVar;
        String str5;
        l2.c[] cVarArr;
        boolean z12;
        int i8;
        char c8;
        e2.c cVar;
        int i9;
        String str6;
        Method method;
        Class<z0> cls2 = z0.class;
        Class<?> cls3 = z0Var.f5320a;
        if (cls3.isPrimitive()) {
            throw new JSONException("unsupportd class " + cls3.getName());
        }
        d2.d dVar2 = (d2.d) l2.l.M(cls3, d2.d.class);
        l2.c[] cVarArr2 = z0Var.f5324e;
        for (l2.c cVar2 : cVarArr2) {
            if (cVar2.f6171f == null && (method = cVar2.f6170e) != null && method.getDeclaringClass().isInterface()) {
                return new j0(z0Var);
            }
        }
        l2.c[] cVarArr3 = z0Var.f5325f;
        boolean z13 = cVarArr3 == z0Var.f5324e;
        if (cVarArr3.length > 256) {
            return new j0(z0Var);
        }
        for (l2.c cVar3 : cVarArr3) {
            if (!l2.b.a(cVar3.l().getName())) {
                return new j0(z0Var);
            }
        }
        String str7 = "ASMSerializer_" + this.f5139k.incrementAndGet() + BydPowerManagerDevice.SEPARATOR_K_V + cls3.getSimpleName();
        Package r22 = a.class.getPackage();
        if (r22 != null) {
            String name = r22.getName();
            str = name + "." + str7;
            str2 = name.replace('.', '/') + "/" + str7;
        } else {
            str = str7;
            str2 = str;
        }
        e2.c cVar4 = new e2.c();
        cVar4.k(49, 33, str2, f5134f, new String[]{f5130b});
        int length = cVarArr3.length;
        int i10 = 0;
        while (i10 < length) {
            l2.c cVar5 = cVarArr3[i10];
            if (cVar5.f6173h.isPrimitive() || cVar5.f6173h == String.class) {
                i9 = length;
                str6 = str;
            } else {
                StringBuilder sb = new StringBuilder();
                i9 = length;
                sb.append(cVar5.f6169d);
                sb.append("_asm_fieldType");
                str6 = str;
                new e2.d(cVar4, 1, sb.toString(), "Ljava/lang/reflect/Type;").c();
                if (List.class.isAssignableFrom(cVar5.f6173h)) {
                    new e2.d(cVar4, 1, cVar5.f6169d + "_asm_list_item_ser_", f5131c).c();
                }
                new e2.d(cVar4, 1, cVar5.f6169d + "_asm_ser_", f5131c).c();
            }
            i10++;
            length = i9;
            str = str6;
        }
        String str8 = str;
        e2.i iVar = new e2.i(cVar4, 1, "<init>", "(" + l2.b.b(cls2) + ")V", null, null);
        iVar.k(25, 0);
        iVar.k(25, 1);
        iVar.d(183, f5134f, "<init>", "(" + l2.b.b(cls2) + ")V");
        int i11 = 0;
        while (i11 < cVarArr3.length) {
            l2.c cVar6 = cVarArr3[i11];
            if (cVar6.f6173h.isPrimitive() || cVar6.f6173h == String.class) {
                cVar = cVar4;
            } else {
                iVar.k(25, 0);
                if (cVar6.f6170e != null) {
                    iVar.i(e2.j.g(l2.b.b(cVar6.f6175j)));
                    iVar.i(cVar6.f6170e.getName());
                    cVar = cVar4;
                    iVar.d(184, l2.b.g(l2.b.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
                } else {
                    cVar = cVar4;
                    iVar.k(25, 0);
                    iVar.i(Integer.valueOf(i11));
                    iVar.d(183, f5134f, "getFieldType", "(I)Ljava/lang/reflect/Type;");
                }
                iVar.f(181, str2, cVar6.f6169d + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            }
            i11++;
            cVar4 = cVar;
        }
        e2.c cVar7 = cVar4;
        iVar.h(177);
        iVar.j(4, 4);
        iVar.e();
        if (dVar2 != null) {
            for (SerializerFeature serializerFeature : dVar2.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    z7 = true;
                    break;
                }
            }
            z7 = false;
        } else {
            z7 = false;
        }
        int i12 = 0;
        while (true) {
            cls = cls2;
            if (i12 >= 3) {
                break;
            }
            if (i12 == 0) {
                str4 = "write";
                z11 = z7;
                z10 = true;
            } else if (i12 == 1) {
                str4 = "writeNormal";
                z11 = z7;
                z10 = false;
            } else {
                str4 = "writeDirectNonContext";
                z10 = true;
                z11 = true;
            }
            e2.c cVar8 = cVar7;
            String str9 = str8;
            int i13 = i12;
            String str10 = str2;
            C0051a c0051a = new C0051a(cVarArr3, z0Var, str2, z10, z11);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(L");
            String str11 = f5129a;
            sb2.append(str11);
            sb2.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            e2.i iVar2 = new e2.i(cVar8, 1, str4, sb2.toString(), null, new String[]{"java/io/IOException"});
            e2.f fVar = new e2.f();
            iVar2.k(25, 2);
            iVar2.b(199, fVar);
            iVar2.k(25, 1);
            iVar2.d(182, str11, "writeNull", "()V");
            iVar2.h(177);
            iVar2.l(fVar);
            iVar2.k(25, 1);
            iVar2.f(180, str11, "out", f5133e);
            iVar2.k(58, c0051a.g("out"));
            if (z13 || c0051a.f5146g || !(dVar2 == null || dVar2.alphabetic())) {
                dVar = dVar2;
                str5 = str10;
            } else {
                e2.f fVar2 = new e2.f();
                iVar2.k(25, c0051a.g("out"));
                dVar = dVar2;
                iVar2.d(182, f5132d, "isSortField", "()Z");
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
            if (!c0051a.f5146g || z11) {
                cVarArr = cVarArr2;
                z12 = z13;
                i8 = 177;
                c8 = 4;
            } else {
                e2.f fVar3 = new e2.f();
                e2.f fVar4 = new e2.f();
                iVar2.k(25, 0);
                iVar2.k(25, 1);
                String str12 = f5134f;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("(L");
                sb3.append(str11);
                cVarArr = cVarArr2;
                sb3.append(";)Z");
                z12 = z13;
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
                iVar2.k(25, c0051a.g("out"));
                iVar2.i(Integer.valueOf(SerializerFeature.DisableCircularReferenceDetect.mask));
                iVar2.d(182, f5132d, "isEnabled", "(I)Z");
                iVar2.b(153, fVar3);
                iVar2.k(25, 0);
                iVar2.k(25, 1);
                iVar2.k(25, 2);
                iVar2.k(25, 3);
                c8 = 4;
                iVar2.k(25, 4);
                iVar2.k(21, 5);
                iVar2.d(182, str5, "writeDirectNonContext", "(L" + str11 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                i8 = 177;
                iVar2.h(177);
                iVar2.l(fVar3);
            }
            iVar2.k(25, 2);
            iVar2.c(192, l2.b.g(cls3));
            iVar2.k(58, c0051a.g("entity"));
            B(cls3, iVar2, cVarArr3, c0051a);
            iVar2.h(i8);
            iVar2.j(7, c0051a.f5148i + 2);
            iVar2.e();
            int i14 = i13 + 1;
            str2 = str5;
            dVar2 = dVar;
            z13 = z12;
            cls2 = cls;
            cVarArr2 = cVarArr;
            cVar7 = cVar8;
            str8 = str9;
            i12 = i14;
        }
        String str13 = str2;
        l2.c[] cVarArr4 = cVarArr2;
        e2.c cVar9 = cVar7;
        String str14 = str8;
        if (!z13) {
            C0051a c0051a2 = new C0051a(cVarArr3, z0Var, str13, false, z7);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("(L");
            String str15 = f5129a;
            sb4.append(str15);
            sb4.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            e2.i iVar3 = new e2.i(cVar9, 1, "writeUnsorted", sb4.toString(), null, new String[]{"java/io/IOException"});
            iVar3.k(25, 1);
            iVar3.f(180, str15, "out", f5133e);
            iVar3.k(58, c0051a2.g("out"));
            iVar3.k(25, 2);
            iVar3.c(192, l2.b.g(cls3));
            iVar3.k(58, c0051a2.g("entity"));
            B(cls3, iVar3, cVarArr4, c0051a2);
            iVar3.h(177);
            iVar3.j(7, c0051a2.f5148i + 2);
            iVar3.e();
        }
        int i15 = 0;
        for (int i16 = 3; i15 < i16; i16 = i16) {
            if (i15 == 0) {
                str3 = "writeAsArray";
                z9 = z7;
                z8 = true;
            } else if (i15 == 1) {
                str3 = "writeAsArrayNormal";
                z9 = z7;
                z8 = false;
            } else {
                str3 = "writeAsArrayNonContext";
                z8 = true;
                z9 = true;
            }
            C0051a c0051a3 = new C0051a(cVarArr3, z0Var, str13, z8, z9);
            StringBuilder sb5 = new StringBuilder();
            sb5.append("(L");
            String str16 = f5129a;
            sb5.append(str16);
            sb5.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            e2.i iVar4 = new e2.i(cVar9, 1, str3, sb5.toString(), null, new String[]{"java/io/IOException"});
            iVar4.k(25, 1);
            iVar4.f(180, str16, "out", f5133e);
            iVar4.k(58, c0051a3.g("out"));
            iVar4.k(25, 2);
            iVar4.c(192, l2.b.g(cls3));
            iVar4.k(58, c0051a3.g("entity"));
            A(cls3, iVar4, cVarArr3, c0051a3);
            iVar4.h(177);
            iVar4.j(7, c0051a3.f5148i + 2);
            iVar4.e();
            i15++;
        }
        byte[] bArrJ = cVar9.j();
        return (j0) this.f5138j.a(str14, bArrJ, 0, bArrJ.length).getConstructor(cls).newInstance(z0Var);
    }
}
