package g2;

import com.alibaba.fastjson.parser.Feature;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import f2.a;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: ASMDeserializerFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5013a = l2.b.g(f2.a.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f5014b = l2.b.g(f2.c.class);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l2.a f5015c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicLong f5016d = new AtomicLong();

    /* JADX INFO: renamed from: g2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASMDeserializerFactory.java */
    public static class C0045a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5017a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, Integer> f5018b = new HashMap();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Class<?> f5019c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l2.g f5020d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f5021e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public l2.c[] f5022f;

        public C0045a(String str, f2.h hVar, l2.g gVar, int i8) {
            this.f5017a = -1;
            this.f5021e = str;
            this.f5019c = gVar.f6215a;
            this.f5017a = i8;
            this.f5020d = gVar;
            this.f5022f = gVar.f6222h;
        }

        public Class<?> g() {
            Class<?> cls = this.f5020d.f6216b;
            return cls == null ? this.f5019c : cls;
        }

        public int h(String str) {
            if (this.f5018b.get(str) == null) {
                Map<String, Integer> map = this.f5018b;
                int i8 = this.f5017a;
                this.f5017a = i8 + 1;
                map.put(str, Integer.valueOf(i8));
            }
            return this.f5018b.get(str).intValue();
        }

        public int i(String str, int i8) {
            if (this.f5018b.get(str) == null) {
                this.f5018b.put(str, Integer.valueOf(this.f5017a));
                this.f5017a += i8;
            }
            return this.f5018b.get(str).intValue();
        }
    }

    public a(ClassLoader classLoader) {
        this.f5015c = classLoader instanceof l2.a ? (l2.a) classLoader : new l2.a(classLoader);
    }

    public final void a(C0045a c0045a, e2.h hVar) {
        b(c0045a, hVar, true);
    }

    public final void b(C0045a c0045a, e2.h hVar, boolean z7) {
        int length = c0045a.f5022f.length;
        for (int i8 = 0; i8 < length; i8++) {
            e2.f fVar = new e2.f();
            if (z7) {
                n(hVar, c0045a, i8, fVar);
            }
            o(c0045a, hVar, c0045a.f5022f[i8]);
            if (z7) {
                hVar.l(fVar);
            }
        }
    }

    public final void c(e2.c cVar, C0045a c0045a) {
        if (Modifier.isPublic(c0045a.f5020d.f6217c.getModifiers())) {
            e2.i iVar = new e2.i(cVar, 1, "createInstance", "(L" + f5013a + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;", null, null);
            iVar.c(187, l2.b.g(c0045a.g()));
            iVar.h(89);
            iVar.d(183, l2.b.g(c0045a.g()), "<init>", "()V");
            iVar.h(176);
            iVar.j(3, 3);
            iVar.e();
        }
    }

    public final void d(C0045a c0045a, e2.h hVar) {
        Constructor<?> constructor = c0045a.f5020d.f6217c;
        if (Modifier.isPublic(constructor.getModifiers())) {
            hVar.c(187, l2.b.g(c0045a.g()));
            hVar.h(89);
            hVar.d(183, l2.b.g(constructor.getDeclaringClass()), "<init>", "()V");
            hVar.k(58, c0045a.h("instance"));
            return;
        }
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 0);
        hVar.f(180, l2.b.g(o.class), "clazz", "Ljava/lang/Class;");
        hVar.d(183, l2.b.g(o.class), "createInstance", "(L" + f5013a + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;");
        hVar.c(192, l2.b.g(c0045a.g()));
        hVar.k(58, c0045a.h("instance"));
    }

    public final void e(C0045a c0045a, e2.h hVar, l2.c cVar, Class<?> cls, int i8) {
        int i9;
        l(c0045a, hVar, cVar);
        e2.f fVar = new e2.f();
        e2.f fVar2 = new e2.f();
        if ((cVar.f6178m & Feature.SupportArrayToBean.mask) != 0) {
            hVar.h(89);
            hVar.c(193, l2.b.g(o.class));
            hVar.b(153, fVar);
            hVar.c(192, l2.b.g(o.class));
            hVar.k(25, 1);
            if (cVar.f6174i instanceof Class) {
                hVar.i(e2.j.g(l2.b.b(cVar.f6173h)));
            } else {
                hVar.k(25, 0);
                hVar.i(Integer.valueOf(i8));
                hVar.d(182, l2.b.g(o.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            }
            hVar.i(cVar.f6169d);
            hVar.i(Integer.valueOf(cVar.f6178m));
            hVar.d(182, l2.b.g(o.class), "deserialze", "(L" + f5013a + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
            hVar.c(192, l2.b.g(cls));
            hVar.k(58, c0045a.h(cVar.f6169d + "_asm"));
            hVar.b(167, fVar2);
            hVar.l(fVar);
            i9 = 1;
        } else {
            i9 = 1;
        }
        hVar.k(25, i9);
        if (cVar.f6174i instanceof Class) {
            hVar.i(e2.j.g(l2.b.b(cVar.f6173h)));
        } else {
            hVar.k(25, 0);
            hVar.i(Integer.valueOf(i8));
            hVar.d(182, l2.b.g(o.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
        }
        hVar.i(cVar.f6169d);
        hVar.d(185, l2.b.g(t.class), "deserialze", "(L" + f5013a + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        hVar.c(192, l2.b.g(cls));
        hVar.k(58, c0045a.h(cVar.f6169d + "_asm"));
        hVar.l(fVar2);
    }

    public final void f(C0045a c0045a, e2.h hVar, e2.f fVar) {
        hVar.g(21, c0045a.h("matchedCount"));
        hVar.b(158, fVar);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, f5014b, "token", "()I");
        hVar.i(13);
        hVar.b(160, fVar);
        r(c0045a, hVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0e3a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0e5b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(e2.c r30, g2.a.C0045a r31) {
        /*
            Method dump skipped, instruction units count: 4174
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g2.a.g(e2.c, g2.a$a):void");
    }

    public final void h(e2.c cVar, C0045a c0045a) {
        int i8;
        e2.i iVar;
        Class<o> cls;
        int i9;
        char c8;
        char c9;
        char c10;
        int i10;
        char c11;
        Class<o> cls2 = o.class;
        StringBuilder sb = new StringBuilder();
        sb.append("(L");
        String str = f5013a;
        sb.append(str);
        sb.append(";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        e2.i iVar2 = new e2.i(cVar, 1, "deserialzeArrayMapping", sb.toString(), null, null);
        w(c0045a, iVar2);
        iVar2.k(25, c0045a.h("lexer"));
        iVar2.k(25, 1);
        iVar2.d(182, str, "getSymbolTable", "()" + l2.b.b(f2.i.class));
        iVar2.d(182, f5014b, "scanTypeName", "(" + l2.b.b(f2.i.class) + ")Ljava/lang/String;");
        iVar2.k(58, c0045a.h("typeName"));
        e2.f fVar = new e2.f();
        iVar2.k(25, c0045a.h("typeName"));
        iVar2.b(198, fVar);
        iVar2.k(25, 1);
        iVar2.d(182, str, "getConfig", "()" + l2.b.b(f2.h.class));
        iVar2.k(25, 0);
        iVar2.f(180, l2.b.g(cls2), "beanInfo", l2.b.b(l2.g.class));
        iVar2.k(25, c0045a.h("typeName"));
        iVar2.d(184, l2.b.g(cls2), "getSeeAlso", "(" + l2.b.b(f2.h.class) + l2.b.b(l2.g.class) + "Ljava/lang/String;)" + l2.b.b(cls2));
        iVar2.k(58, c0045a.h("userTypeDeser"));
        iVar2.k(25, c0045a.h("userTypeDeser"));
        iVar2.c(193, l2.b.g(cls2));
        iVar2.b(153, fVar);
        iVar2.k(25, c0045a.h("userTypeDeser"));
        iVar2.k(25, 1);
        iVar2.k(25, 2);
        iVar2.k(25, 3);
        iVar2.k(25, 4);
        iVar2.d(182, l2.b.g(cls2), "deserialzeArrayMapping", "(L" + str + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        iVar2.h(176);
        iVar2.l(fVar);
        d(c0045a, iVar2);
        l2.c[] cVarArr = c0045a.f5020d.f6223i;
        int length = cVarArr.length;
        int i11 = 0;
        while (i11 < length) {
            boolean z7 = i11 == length + (-1);
            int i12 = z7 ? 93 : 44;
            l2.c cVar2 = cVarArr[i11];
            Class<?> cls3 = cVar2.f6173h;
            Type type = cVar2.f6174i;
            int i13 = length;
            l2.c[] cVarArr2 = cVarArr;
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                i8 = i11;
                iVar = iVar2;
                cls = cls2;
                i9 = i13;
                c8 = ':';
                c9 = 184;
                iVar.k(25, c0045a.h("lexer"));
                iVar.k(16, i12);
                iVar.d(182, f5014b, "scanInt", "(C)I");
                iVar.k(54, c0045a.h(cVar2.f6169d + "_asm"));
            } else {
                boolean z8 = z7;
                int i14 = i11;
                if (cls3 == Byte.class) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    String str2 = f5014b;
                    iVar2.d(182, str2, "scanInt", "(C)I");
                    iVar2.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    e2.f fVar2 = new e2.f();
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.f(180, str2, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar2);
                    iVar2.h(1);
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    iVar2.l(fVar2);
                } else if (cls3 == Short.class) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    String str3 = f5014b;
                    iVar2.d(182, str3, "scanInt", "(C)I");
                    iVar2.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    e2.f fVar3 = new e2.f();
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.f(180, str3, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar3);
                    iVar2.h(1);
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    iVar2.l(fVar3);
                } else if (cls3 == Integer.class) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    String str4 = f5014b;
                    iVar2.d(182, str4, "scanInt", "(C)I");
                    iVar2.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    e2.f fVar4 = new e2.f();
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.f(180, str4, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar4);
                    iVar2.h(1);
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    iVar2.l(fVar4);
                } else if (cls3 == Long.TYPE) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    iVar2.d(182, f5014b, "scanLong", "(C)J");
                    iVar2.k(55, c0045a.i(cVar2.f6169d + "_asm", 2));
                } else if (cls3 == Long.class) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    String str5 = f5014b;
                    iVar2.d(182, str5, "scanLong", "(C)J");
                    iVar2.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    e2.f fVar5 = new e2.f();
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.f(180, str5, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar5);
                    iVar2.h(1);
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    iVar2.l(fVar5);
                } else if (cls3 == Boolean.TYPE) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    iVar2.d(182, f5014b, "scanBoolean", "(C)Z");
                    iVar2.k(54, c0045a.h(cVar2.f6169d + "_asm"));
                } else if (cls3 == Float.TYPE) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    iVar2.d(182, f5014b, "scanFloat", "(C)F");
                    iVar2.k(56, c0045a.h(cVar2.f6169d + "_asm"));
                } else if (cls3 == Float.class) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    String str6 = f5014b;
                    iVar2.d(182, str6, "scanFloat", "(C)F");
                    iVar2.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    e2.f fVar6 = new e2.f();
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.f(180, str6, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar6);
                    iVar2.h(1);
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    iVar2.l(fVar6);
                } else if (cls3 == Double.TYPE) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    iVar2.d(182, f5014b, "scanDouble", "(C)D");
                    iVar2.k(57, c0045a.i(cVar2.f6169d + "_asm", 2));
                } else if (cls3 == Double.class) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    String str7 = f5014b;
                    iVar2.d(182, str7, "scanDouble", "(C)D");
                    iVar2.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    e2.f fVar7 = new e2.f();
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.f(180, str7, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar7);
                    iVar2.h(1);
                    iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    iVar2.l(fVar7);
                } else if (cls3 == Character.TYPE) {
                    iVar2.k(25, c0045a.h("lexer"));
                    iVar2.k(16, i12);
                    iVar2.d(182, f5014b, "scanString", "(C)Ljava/lang/String;");
                    iVar2.h(3);
                    iVar2.d(182, "java/lang/String", "charAt", "(I)C");
                    iVar2.k(54, c0045a.h(cVar2.f6169d + "_asm"));
                } else {
                    if (cls3 == String.class) {
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.k(16, i12);
                        iVar2.d(182, f5014b, "scanString", "(C)Ljava/lang/String;");
                        c10 = ':';
                        iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    } else if (cls3 == BigDecimal.class) {
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.k(16, i12);
                        iVar2.d(182, f5014b, "scanDecimal", "(C)Ljava/math/BigDecimal;");
                        c10 = ':';
                        iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    } else if (cls3 == Date.class) {
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.k(16, i12);
                        iVar2.d(182, f5014b, "scanDate", "(C)Ljava/util/Date;");
                        c10 = ':';
                        iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    } else if (cls3 == UUID.class) {
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.k(16, i12);
                        iVar2.d(182, f5014b, "scanUUID", "(C)Ljava/util/UUID;");
                        c10 = ':';
                        iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                    } else if (cls3.isEnum()) {
                        e2.f fVar8 = new e2.f();
                        e2.f fVar9 = new e2.f();
                        e2.f fVar10 = new e2.f();
                        e2.f fVar11 = new e2.f();
                        cls = cls2;
                        iVar2.k(25, c0045a.h("lexer"));
                        String str8 = f5014b;
                        iVar2.d(182, str8, "getCurrent", "()C");
                        iVar2.h(89);
                        iVar2.k(54, c0045a.h("ch"));
                        iVar2.i(110);
                        iVar2.b(159, fVar11);
                        iVar2.k(21, c0045a.h("ch"));
                        iVar2.i(34);
                        iVar2.b(160, fVar8);
                        iVar2.l(fVar11);
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.i(e2.j.g(l2.b.b(cls3)));
                        iVar2.k(25, 1);
                        iVar2.d(182, f5013a, "getSymbolTable", "()" + l2.b.b(f2.i.class));
                        iVar2.k(16, i12);
                        iVar2.d(182, str8, "scanEnum", "(Ljava/lang/Class;" + l2.b.b(f2.i.class) + "C)Ljava/lang/Enum;");
                        iVar2.b(167, fVar10);
                        iVar2.l(fVar8);
                        iVar2.k(21, c0045a.h("ch"));
                        iVar2.i(48);
                        iVar2.b(161, fVar9);
                        iVar2.k(21, c0045a.h("ch"));
                        iVar2.i(57);
                        iVar2.b(163, fVar9);
                        l(c0045a, iVar2, cVar2);
                        iVar2.c(192, l2.b.g(h.class));
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.k(16, i12);
                        iVar2.d(182, str8, "scanInt", "(C)I");
                        iVar2.d(182, l2.b.g(h.class), "valueOf", "(I)Ljava/lang/Enum;");
                        iVar2.b(167, fVar10);
                        iVar2.l(fVar9);
                        iVar2.k(25, 0);
                        iVar2.k(25, c0045a.h("lexer"));
                        iVar2.k(16, i12);
                        iVar2.d(182, l2.b.g(cls), "scanEnum", "(L" + str8 + ";C)Ljava/lang/Enum;");
                        iVar2.l(fVar10);
                        iVar2.c(192, l2.b.g(cls3));
                        iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                        c8 = ':';
                        iVar = iVar2;
                        i9 = i13;
                        i8 = i14;
                        c9 = 184;
                    } else {
                        cls = cls2;
                        if (Collection.class.isAssignableFrom(cls3)) {
                            Class<?> clsR = l2.l.R(type);
                            if (clsR == String.class) {
                                if (cls3 == List.class || cls3 == Collections.class || cls3 == ArrayList.class) {
                                    iVar2.c(187, l2.b.g(ArrayList.class));
                                    iVar2.h(89);
                                    iVar2.d(183, l2.b.g(ArrayList.class), "<init>", "()V");
                                } else {
                                    iVar2.i(e2.j.g(l2.b.b(cls3)));
                                    iVar2.d(184, l2.b.g(l2.l.class), "createCollection", "(Ljava/lang/Class;)Ljava/util/Collection;");
                                }
                                iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.k(25, c0045a.h(cVar2.f6169d + "_asm"));
                                iVar2.k(16, i12);
                                String str9 = f5014b;
                                iVar2.d(182, str9, "scanStringArray", "(Ljava/util/Collection;C)V");
                                e2.f fVar12 = new e2.f();
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.f(180, str9, "matchStat", "I");
                                iVar2.i(5);
                                iVar2.b(160, fVar12);
                                iVar2.h(1);
                                iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                                iVar2.l(fVar12);
                                i10 = i14;
                                c11 = 184;
                            } else {
                                e2.f fVar13 = new e2.f();
                                iVar2.k(25, c0045a.h("lexer"));
                                String str10 = f5014b;
                                iVar2.d(182, str10, "token", "()I");
                                iVar2.k(54, c0045a.h("token"));
                                iVar2.k(21, c0045a.h("token"));
                                int i15 = i14 == 0 ? 14 : 16;
                                iVar2.i(Integer.valueOf(i15));
                                iVar2.b(159, fVar13);
                                iVar2.k(25, 1);
                                iVar2.i(Integer.valueOf(i15));
                                String str11 = f5013a;
                                iVar2.d(182, str11, "throwException", "(I)V");
                                iVar2.l(fVar13);
                                e2.f fVar14 = new e2.f();
                                e2.f fVar15 = new e2.f();
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.d(182, str10, "getCurrent", "()C");
                                iVar2.k(16, 91);
                                iVar2.b(160, fVar14);
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.d(182, str10, "next", "()C");
                                iVar2.h(87);
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.i(14);
                                iVar2.d(182, str10, "setToken", "(I)V");
                                iVar2.b(167, fVar15);
                                iVar2.l(fVar14);
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.i(14);
                                iVar2.d(182, str10, "nextToken", "(I)V");
                                iVar2.l(fVar15);
                                i10 = i14;
                                p(iVar2, cls3, i10, false);
                                iVar2.h(89);
                                iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                                k(c0045a, iVar2, cVar2, clsR);
                                iVar2.k(25, 1);
                                iVar2.i(e2.j.g(l2.b.b(clsR)));
                                iVar2.k(25, 3);
                                c11 = 184;
                                iVar2.d(184, l2.b.g(cls), "parseArray", "(Ljava/util/Collection;" + l2.b.b(t.class) + "L" + str11 + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                            }
                            i8 = i10;
                            iVar = iVar2;
                            c9 = c11;
                            i9 = i13;
                            c8 = ':';
                        } else if (cls3.isArray()) {
                            iVar2.k(25, c0045a.h("lexer"));
                            iVar2.i(14);
                            iVar2.d(182, f5014b, "nextToken", "(I)V");
                            iVar2.k(25, 1);
                            iVar2.k(25, 0);
                            iVar2.i(Integer.valueOf(i14));
                            iVar2.d(182, l2.b.g(cls), "getFieldType", "(I)Ljava/lang/reflect/Type;");
                            iVar2.d(182, f5013a, "parseObject", "(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
                            iVar2.c(192, l2.b.g(cls3));
                            c10 = ':';
                            iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                            i8 = i14;
                            iVar = iVar2;
                            c9 = (char) 184;
                            i9 = i13;
                            c8 = c10;
                        } else {
                            e2.f fVar16 = new e2.f();
                            e2.f fVar17 = new e2.f();
                            if (cls3 == Date.class) {
                                iVar2.k(25, c0045a.h("lexer"));
                                String str12 = f5014b;
                                iVar2.d(182, str12, "getCurrent", "()C");
                                iVar2.i(49);
                                iVar2.b(160, fVar16);
                                iVar2.c(187, l2.b.g(Date.class));
                                iVar2.h(89);
                                iVar2.k(25, c0045a.h("lexer"));
                                iVar2.k(16, i12);
                                iVar2.d(182, str12, "scanLong", "(C)J");
                                iVar2.d(183, l2.b.g(Date.class), "<init>", "(J)V");
                                c8 = ':';
                                iVar2.k(58, c0045a.h(cVar2.f6169d + "_asm"));
                                iVar2.b(167, fVar17);
                            } else {
                                c8 = ':';
                            }
                            iVar2.l(fVar16);
                            q(c0045a, iVar2, 14);
                            i8 = i14;
                            i9 = i13;
                            c9 = 184;
                            iVar = iVar2;
                            e(c0045a, iVar2, cVar2, cls3, i8);
                            iVar.k(25, c0045a.h("lexer"));
                            iVar.d(182, f5014b, "token", "()I");
                            iVar.i(15);
                            iVar.b(159, fVar17);
                            iVar.k(25, 0);
                            iVar.k(25, c0045a.h("lexer"));
                            if (z8) {
                                iVar.i(15);
                            } else {
                                iVar.i(16);
                            }
                            iVar.d(183, l2.b.g(cls), "check", "(" + l2.b.b(f2.b.class) + "I)V");
                            iVar.l(fVar17);
                        }
                    }
                    iVar = iVar2;
                    cls = cls2;
                    i9 = i13;
                    i8 = i14;
                    c9 = 184;
                    c8 = c10;
                }
                iVar = iVar2;
                cls = cls2;
                i9 = i13;
                i8 = i14;
                c8 = ':';
                c9 = 184;
            }
            i11 = i8 + 1;
            length = i9;
            iVar2 = iVar;
            cVarArr = cVarArr2;
            cls2 = cls;
        }
        e2.i iVar3 = iVar2;
        b(c0045a, iVar3, false);
        e2.f fVar18 = new e2.f();
        e2.f fVar19 = new e2.f();
        e2.f fVar20 = new e2.f();
        e2.f fVar21 = new e2.f();
        iVar3.k(25, c0045a.h("lexer"));
        String str13 = f5014b;
        iVar3.d(182, str13, "getCurrent", "()C");
        iVar3.h(89);
        iVar3.k(54, c0045a.h("ch"));
        iVar3.k(16, 44);
        iVar3.b(160, fVar19);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.d(182, str13, "next", "()C");
        iVar3.h(87);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.i(16);
        iVar3.d(182, str13, "setToken", "(I)V");
        iVar3.b(167, fVar21);
        iVar3.l(fVar19);
        iVar3.k(21, c0045a.h("ch"));
        iVar3.k(16, 93);
        iVar3.b(160, fVar20);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.d(182, str13, "next", "()C");
        iVar3.h(87);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.i(15);
        iVar3.d(182, str13, "setToken", "(I)V");
        iVar3.b(167, fVar21);
        iVar3.l(fVar20);
        iVar3.k(21, c0045a.h("ch"));
        iVar3.k(16, 26);
        iVar3.b(160, fVar18);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.d(182, str13, "next", "()C");
        iVar3.h(87);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.i(20);
        iVar3.d(182, str13, "setToken", "(I)V");
        iVar3.b(167, fVar21);
        iVar3.l(fVar18);
        iVar3.k(25, c0045a.h("lexer"));
        iVar3.i(16);
        iVar3.d(182, str13, "nextToken", "(I)V");
        iVar3.l(fVar21);
        iVar3.k(25, c0045a.h("instance"));
        iVar3.h(176);
        iVar3.j(5, c0045a.f5017a);
        iVar3.e();
    }

    public final void i(C0045a c0045a, e2.h hVar, e2.f fVar, l2.c cVar, Class<?> cls, Class<?> cls2, int i8) {
        String str;
        String str2;
        String str3;
        String str4;
        e2.f fVar2;
        String str5;
        int i9;
        e2.f fVar3 = new e2.f();
        String str6 = f5014b;
        hVar.d(182, str6, "matchField", "([C)Z");
        hVar.b(153, fVar3);
        u(hVar, c0045a, i8);
        e2.f fVar4 = new e2.f();
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(8);
        hVar.b(160, fVar4);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(16);
        hVar.d(182, str6, "nextToken", "(I)V");
        hVar.b(167, fVar3);
        hVar.l(fVar4);
        e2.f fVar5 = new e2.f();
        e2.f fVar6 = new e2.f();
        e2.f fVar7 = new e2.f();
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(21);
        hVar.b(160, fVar6);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(14);
        hVar.d(182, str6, "nextToken", "(I)V");
        p(hVar, cls, i8, true);
        hVar.b(167, fVar5);
        hVar.l(fVar6);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(14);
        hVar.b(159, fVar7);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(12);
        hVar.b(160, fVar);
        p(hVar, cls, i8, false);
        hVar.k(58, c0045a.h(cVar.f6169d + "_asm"));
        k(c0045a, hVar, cVar, cls2);
        hVar.k(25, 1);
        hVar.i(e2.j.g(l2.b.b(cls2)));
        hVar.h(3);
        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        String strG = l2.b.g(t.class);
        StringBuilder sb = new StringBuilder();
        sb.append("(L");
        String str7 = f5013a;
        sb.append(str7);
        sb.append(";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        hVar.d(185, strG, "deserialze", sb.toString());
        hVar.k(58, c0045a.h("list_item_value"));
        hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
        hVar.k(25, c0045a.h("list_item_value"));
        if (cls.isInterface()) {
            str = "list_item_value";
            hVar.d(185, l2.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            str = "list_item_value";
            hVar.d(182, l2.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        }
        hVar.h(87);
        hVar.b(167, fVar3);
        hVar.l(fVar7);
        p(hVar, cls, i8, false);
        hVar.l(fVar5);
        hVar.k(58, c0045a.h(cVar.f6169d + "_asm"));
        boolean zV = f2.h.v(cVar.f6173h);
        k(c0045a, hVar, cVar, cls2);
        if (zV) {
            hVar.d(185, l2.b.g(t.class), "getFastMatchToken", "()I");
            hVar.k(54, c0045a.h("fastMatchToken"));
            hVar.k(25, c0045a.h("lexer"));
            hVar.k(21, c0045a.h("fastMatchToken"));
            str2 = "nextToken";
            str3 = str6;
            str4 = "(I)V";
            hVar.d(182, str3, str2, str4);
            fVar2 = fVar3;
        } else {
            str2 = "nextToken";
            str3 = str6;
            str4 = "(I)V";
            hVar.h(87);
            hVar.i(12);
            fVar2 = fVar3;
            hVar.k(54, c0045a.h("fastMatchToken"));
            q(c0045a, hVar, 12);
        }
        hVar.k(25, 1);
        String str8 = str4;
        hVar.d(182, str7, "getContext", "()" + l2.b.b(f2.g.class));
        hVar.k(58, c0045a.h("listContext"));
        hVar.k(25, 1);
        hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
        hVar.i(cVar.f6169d);
        hVar.d(182, str7, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + l2.b.b(f2.g.class));
        hVar.h(87);
        e2.f fVar8 = new e2.f();
        e2.f fVar9 = new e2.f();
        hVar.h(3);
        String str9 = str2;
        hVar.k(54, c0045a.h("i"));
        hVar.l(fVar8);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str3, "token", "()I");
        hVar.i(15);
        hVar.b(159, fVar9);
        hVar.k(25, 0);
        hVar.f(180, c0045a.f5021e, cVar.f6169d + "_asm_list_item_deser__", l2.b.b(t.class));
        hVar.k(25, 1);
        hVar.i(e2.j.g(l2.b.b(cls2)));
        hVar.k(21, c0045a.h("i"));
        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        hVar.d(185, l2.b.g(t.class), "deserialze", "(L" + str7 + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        String str10 = str;
        hVar.k(58, c0045a.h(str10));
        hVar.a(c0045a.h("i"), 1);
        hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
        hVar.k(25, c0045a.h(str10));
        if (cls.isInterface()) {
            hVar.d(185, l2.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            hVar.d(182, l2.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        }
        hVar.h(87);
        hVar.k(25, 1);
        hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
        hVar.d(182, str7, "checkListResolve", "(Ljava/util/Collection;)V");
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str3, "token", "()I");
        hVar.i(16);
        hVar.b(160, fVar8);
        if (zV) {
            hVar.k(25, c0045a.h("lexer"));
            hVar.k(21, c0045a.h("fastMatchToken"));
            hVar.d(182, str3, str9, str8);
            i9 = 167;
            str5 = str7;
        } else {
            str5 = str7;
            q(c0045a, hVar, 12);
            i9 = 167;
        }
        hVar.b(i9, fVar8);
        hVar.l(fVar9);
        hVar.k(25, 1);
        hVar.k(25, c0045a.h("listContext"));
        hVar.d(182, str5, "setContext", "(" + l2.b.b(f2.g.class) + ")V");
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str3, "token", "()I");
        hVar.i(15);
        hVar.b(160, fVar);
        r(c0045a, hVar);
        hVar.l(fVar2);
    }

    public final void j(C0045a c0045a, e2.h hVar, e2.f fVar, l2.c cVar, Class<?> cls, int i8) {
        e2.f fVar2 = new e2.f();
        e2.f fVar3 = new e2.f();
        hVar.k(25, c0045a.h("lexer"));
        hVar.k(25, 0);
        hVar.f(180, c0045a.f5021e, cVar.f6169d + "_asm_prefix__", "[C");
        hVar.d(182, f5014b, "matchField", "([C)Z");
        hVar.b(154, fVar2);
        hVar.h(1);
        hVar.k(58, c0045a.h(cVar.f6169d + "_asm"));
        hVar.b(167, fVar3);
        hVar.l(fVar2);
        u(hVar, c0045a, i8);
        hVar.k(21, c0045a.h("matchedCount"));
        hVar.h(4);
        hVar.h(96);
        hVar.k(54, c0045a.h("matchedCount"));
        e(c0045a, hVar, cVar, cls, i8);
        hVar.k(25, 1);
        String str = f5013a;
        hVar.d(182, str, "getResolveStatus", "()I");
        hVar.i(1);
        hVar.b(160, fVar3);
        hVar.k(25, 1);
        hVar.d(182, str, "getLastResolveTask", "()" + l2.b.b(a.C0039a.class));
        hVar.k(58, c0045a.h("resolveTask"));
        hVar.k(25, c0045a.h("resolveTask"));
        hVar.k(25, 1);
        hVar.d(182, str, "getContext", "()" + l2.b.b(f2.g.class));
        hVar.f(181, l2.b.g(a.C0039a.class), "ownerContext", l2.b.b(f2.g.class));
        hVar.k(25, c0045a.h("resolveTask"));
        hVar.k(25, 0);
        hVar.i(cVar.f6169d);
        hVar.d(182, l2.b.g(o.class), "getFieldDeserializer", "(Ljava/lang/String;)" + l2.b.b(l.class));
        hVar.f(181, l2.b.g(a.C0039a.class), "fieldDeserializer", l2.b.b(l.class));
        hVar.k(25, 1);
        hVar.i(0);
        hVar.d(182, str, "setResolveStatus", "(I)V");
        hVar.l(fVar3);
    }

    public final void k(C0045a c0045a, e2.h hVar, l2.c cVar, Class<?> cls) {
        e2.f fVar = new e2.f();
        hVar.k(25, 0);
        hVar.f(180, c0045a.f5021e, cVar.f6169d + "_asm_list_item_deser__", l2.b.b(t.class));
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.d(182, f5013a, "getConfig", "()" + l2.b.b(f2.h.class));
        hVar.i(e2.j.g(l2.b.b(cls)));
        hVar.d(182, l2.b.g(f2.h.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + l2.b.b(t.class));
        hVar.f(181, c0045a.f5021e, cVar.f6169d + "_asm_list_item_deser__", l2.b.b(t.class));
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0045a.f5021e, cVar.f6169d + "_asm_list_item_deser__", l2.b.b(t.class));
    }

    public final void l(C0045a c0045a, e2.h hVar, l2.c cVar) {
        e2.f fVar = new e2.f();
        hVar.k(25, 0);
        hVar.f(180, c0045a.f5021e, cVar.f6169d + "_asm_deser__", l2.b.b(t.class));
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.d(182, f5013a, "getConfig", "()" + l2.b.b(f2.h.class));
        hVar.i(e2.j.g(l2.b.b(cVar.f6173h)));
        hVar.d(182, l2.b.g(f2.h.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + l2.b.b(t.class));
        hVar.f(181, c0045a.f5021e, cVar.f6169d + "_asm_deser__", l2.b.b(t.class));
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0045a.f5021e, cVar.f6169d + "_asm_deser__", l2.b.b(t.class));
    }

    public final void m(e2.c cVar, C0045a c0045a) {
        int length = c0045a.f5022f.length;
        for (int i8 = 0; i8 < length; i8++) {
            new e2.d(cVar, 1, c0045a.f5022f[i8].f6169d + "_asm_prefix__", "[C").c();
        }
        int length2 = c0045a.f5022f.length;
        for (int i9 = 0; i9 < length2; i9++) {
            l2.c cVar2 = c0045a.f5022f[i9];
            Class<?> cls = cVar2.f6173h;
            if (!cls.isPrimitive()) {
                if (Collection.class.isAssignableFrom(cls)) {
                    new e2.d(cVar, 1, cVar2.f6169d + "_asm_list_item_deser__", l2.b.b(t.class)).c();
                } else {
                    new e2.d(cVar, 1, cVar2.f6169d + "_asm_deser__", l2.b.b(t.class)).c();
                }
            }
        }
        e2.i iVar = new e2.i(cVar, 1, "<init>", "(" + l2.b.b(f2.h.class) + l2.b.b(l2.g.class) + ")V", null, null);
        iVar.k(25, 0);
        iVar.k(25, 1);
        iVar.k(25, 2);
        iVar.d(183, l2.b.g(o.class), "<init>", "(" + l2.b.b(f2.h.class) + l2.b.b(l2.g.class) + ")V");
        int length3 = c0045a.f5022f.length;
        for (int i10 = 0; i10 < length3; i10++) {
            l2.c cVar3 = c0045a.f5022f[i10];
            iVar.k(25, 0);
            iVar.i("\"" + cVar3.f6169d + "\":");
            iVar.d(182, "java/lang/String", "toCharArray", "()[C");
            iVar.f(181, c0045a.f5021e, cVar3.f6169d + "_asm_prefix__", "[C");
        }
        iVar.h(177);
        iVar.j(4, 4);
        iVar.e();
    }

    public final void n(e2.h hVar, C0045a c0045a, int i8, e2.f fVar) {
        hVar.k(21, c0045a.h("_asm_flag_" + (i8 / 32)));
        hVar.i(Integer.valueOf(1 << i8));
        hVar.h(126);
        hVar.b(153, fVar);
    }

    public final void o(C0045a c0045a, e2.h hVar, l2.c cVar) {
        Class<?> cls = cVar.f6173h;
        Type type = cVar.f6174i;
        if (cls == Boolean.TYPE) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(21, c0045a.h(cVar.f6169d + "_asm"));
            s(c0045a, hVar, cVar);
            return;
        }
        if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Character.TYPE) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(21, c0045a.h(cVar.f6169d + "_asm"));
            s(c0045a, hVar, cVar);
            return;
        }
        if (cls == Long.TYPE) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(22, c0045a.i(cVar.f6169d + "_asm", 2));
            if (cVar.f6170e == null) {
                hVar.f(181, l2.b.g(cVar.f6175j), cVar.f6171f.getName(), l2.b.b(cVar.f6173h));
                return;
            }
            hVar.d(182, l2.b.g(c0045a.g()), cVar.f6170e.getName(), l2.b.c(cVar.f6170e));
            if (cVar.f6170e.getReturnType().equals(Void.TYPE)) {
                return;
            }
            hVar.h(87);
            return;
        }
        if (cls == Float.TYPE) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(23, c0045a.h(cVar.f6169d + "_asm"));
            s(c0045a, hVar, cVar);
            return;
        }
        if (cls == Double.TYPE) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(24, c0045a.i(cVar.f6169d + "_asm", 2));
            s(c0045a, hVar, cVar);
            return;
        }
        if (cls == String.class) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
            s(c0045a, hVar, cVar);
            return;
        }
        if (cls.isEnum()) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
            s(c0045a, hVar, cVar);
            return;
        }
        if (!Collection.class.isAssignableFrom(cls)) {
            hVar.k(25, c0045a.h("instance"));
            hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
            s(c0045a, hVar, cVar);
            return;
        }
        hVar.k(25, c0045a.h("instance"));
        if (l2.l.R(type) == String.class) {
            hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
            hVar.c(192, l2.b.g(cls));
        } else {
            hVar.k(25, c0045a.h(cVar.f6169d + "_asm"));
        }
        s(c0045a, hVar, cVar);
    }

    public final void p(e2.h hVar, Class<?> cls, int i8, boolean z7) {
        if (cls.isAssignableFrom(ArrayList.class) && !z7) {
            hVar.c(187, "java/util/ArrayList");
            hVar.h(89);
            hVar.d(183, "java/util/ArrayList", "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedList.class) && !z7) {
            hVar.c(187, l2.b.g(LinkedList.class));
            hVar.h(89);
            hVar.d(183, l2.b.g(LinkedList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(HashSet.class)) {
            hVar.c(187, l2.b.g(HashSet.class));
            hVar.h(89);
            hVar.d(183, l2.b.g(HashSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(TreeSet.class)) {
            hVar.c(187, l2.b.g(TreeSet.class));
            hVar.h(89);
            hVar.d(183, l2.b.g(TreeSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
            hVar.c(187, l2.b.g(LinkedHashSet.class));
            hVar.h(89);
            hVar.d(183, l2.b.g(LinkedHashSet.class), "<init>", "()V");
        } else if (z7) {
            hVar.c(187, l2.b.g(HashSet.class));
            hVar.h(89);
            hVar.d(183, l2.b.g(HashSet.class), "<init>", "()V");
        } else {
            hVar.k(25, 0);
            hVar.i(Integer.valueOf(i8));
            hVar.d(182, l2.b.g(o.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            hVar.d(184, l2.b.g(l2.l.class), "createCollection", "(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
        }
        hVar.c(192, l2.b.g(cls));
    }

    public final void q(C0045a c0045a, e2.h hVar, int i8) {
        e2.f fVar = new e2.f();
        e2.f fVar2 = new e2.f();
        hVar.k(25, c0045a.h("lexer"));
        String str = f5014b;
        hVar.d(182, str, "getCurrent", "()C");
        if (i8 == 12) {
            hVar.k(16, 123);
        } else {
            if (i8 != 14) {
                throw new IllegalStateException();
            }
            hVar.k(16, 91);
        }
        hVar.b(160, fVar);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(Integer.valueOf(i8));
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar2);
        hVar.l(fVar);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(Integer.valueOf(i8));
        hVar.d(182, str, "nextToken", "(I)V");
        hVar.l(fVar2);
    }

    public final void r(C0045a c0045a, e2.h hVar) {
        e2.f fVar = new e2.f();
        e2.f fVar2 = new e2.f();
        e2.f fVar3 = new e2.f();
        e2.f fVar4 = new e2.f();
        e2.f fVar5 = new e2.f();
        hVar.k(25, c0045a.h("lexer"));
        String str = f5014b;
        hVar.d(182, str, "getCurrent", "()C");
        hVar.h(89);
        hVar.k(54, c0045a.h("ch"));
        hVar.k(16, 44);
        hVar.b(160, fVar2);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(16);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar2);
        hVar.k(21, c0045a.h("ch"));
        hVar.k(16, 125);
        hVar.b(160, fVar3);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(13);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar3);
        hVar.k(21, c0045a.h("ch"));
        hVar.k(16, 93);
        hVar.b(160, fVar4);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(15);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar4);
        hVar.k(21, c0045a.h("ch"));
        hVar.k(16, 26);
        hVar.b(160, fVar);
        hVar.k(25, c0045a.h("lexer"));
        hVar.i(20);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar);
        hVar.k(25, c0045a.h("lexer"));
        hVar.d(182, str, "nextToken", "()V");
        hVar.l(fVar5);
    }

    public final void s(C0045a c0045a, e2.h hVar, l2.c cVar) {
        Method method = cVar.f6170e;
        if (method == null) {
            hVar.f(181, l2.b.g(cVar.f6175j), cVar.f6171f.getName(), l2.b.b(cVar.f6173h));
            return;
        }
        hVar.d(method.getDeclaringClass().isInterface() ? 185 : 182, l2.b.g(cVar.f6175j), method.getName(), l2.b.c(method));
        if (cVar.f6170e.getReturnType().equals(Void.TYPE)) {
            return;
        }
        hVar.h(87);
    }

    public final void t(C0045a c0045a, e2.h hVar) {
        hVar.k(25, 1);
        hVar.k(25, c0045a.h("context"));
        hVar.d(182, f5013a, "setContext", "(" + l2.b.b(f2.g.class) + ")V");
        e2.f fVar = new e2.f();
        hVar.k(25, c0045a.h("childContext"));
        hVar.b(198, fVar);
        hVar.k(25, c0045a.h("childContext"));
        hVar.k(25, c0045a.h("instance"));
        hVar.f(181, l2.b.g(f2.g.class), "object", "Ljava/lang/Object;");
        hVar.l(fVar);
    }

    public final void u(e2.h hVar, C0045a c0045a, int i8) {
        String str = "_asm_flag_" + (i8 / 32);
        hVar.k(21, c0045a.h(str));
        hVar.i(Integer.valueOf(1 << i8));
        hVar.h(128);
        hVar.k(54, c0045a.h(str));
    }

    public t v(f2.h hVar, l2.g gVar) throws Exception {
        String str;
        Class<?> cls = gVar.f6215a;
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("not support type :" + cls.getName());
        }
        String str2 = "FastjsonASMDeserializer_" + this.f5016d.incrementAndGet() + BydPowerManagerDevice.SEPARATOR_K_V + cls.getSimpleName();
        Package r12 = a.class.getPackage();
        if (r12 != null) {
            String name = r12.getName();
            String str3 = name.replace('.', '/') + "/" + str2;
            str = name + "." + str2;
            str2 = str3;
        } else {
            str = str2;
        }
        e2.c cVar = new e2.c();
        cVar.k(49, 33, str2, l2.b.g(o.class), null);
        m(cVar, new C0045a(str2, hVar, gVar, 3));
        c(cVar, new C0045a(str2, hVar, gVar, 3));
        g(cVar, new C0045a(str2, hVar, gVar, 5));
        h(cVar, new C0045a(str2, hVar, gVar, 4));
        byte[] bArrJ = cVar.j();
        return (t) this.f5015c.a(str, bArrJ, 0, bArrJ.length).getConstructor(f2.h.class, l2.g.class).newInstance(hVar, gVar);
    }

    public final void w(C0045a c0045a, e2.h hVar) {
        hVar.k(25, 1);
        hVar.f(180, f5013a, "lexer", l2.b.b(f2.b.class));
        hVar.c(192, f5014b);
        hVar.k(58, c0045a.h("lexer"));
    }
}
