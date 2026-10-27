package b.a.a.j.j;

import b.a.a.j.a;
import com.alibaba.fastjson.parser.Feature;
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
    public static final String f259a = b.a.a.n.b.g(b.a.a.j.a.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f260b = b.a.a.n.b.g(b.a.a.j.c.class);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.a.a.n.a f261c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicLong f262d = new AtomicLong();

    /* JADX INFO: renamed from: b.a.a.j.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASMDeserializerFactory.java */
    public static class C0017a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f263a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, Integer> f264b = new HashMap();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Class<?> f265c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b.a.a.n.g f266d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f267e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.a.a.n.c[] f268f;

        public C0017a(String str, b.a.a.j.h hVar, b.a.a.n.g gVar, int i) {
            this.f263a = -1;
            this.f267e = str;
            this.f265c = gVar.f448a;
            this.f263a = i;
            this.f266d = gVar;
            this.f268f = gVar.h;
        }

        public Class<?> g() {
            Class<?> cls = this.f266d.f449b;
            return cls == null ? this.f265c : cls;
        }

        public int h(String str) {
            if (this.f264b.get(str) == null) {
                Map<String, Integer> map = this.f264b;
                int i = this.f263a;
                this.f263a = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return this.f264b.get(str).intValue();
        }

        public int i(String str, int i) {
            if (this.f264b.get(str) == null) {
                this.f264b.put(str, Integer.valueOf(this.f263a));
                this.f263a += i;
            }
            return this.f264b.get(str).intValue();
        }
    }

    public a(ClassLoader classLoader) {
        this.f261c = classLoader instanceof b.a.a.n.a ? (b.a.a.n.a) classLoader : new b.a.a.n.a(classLoader);
    }

    public final void a(C0017a c0017a, b.a.a.i.h hVar) {
        b(c0017a, hVar, true);
    }

    public final void b(C0017a c0017a, b.a.a.i.h hVar, boolean z) {
        int length = c0017a.f268f.length;
        for (int i = 0; i < length; i++) {
            b.a.a.i.f fVar = new b.a.a.i.f();
            if (z) {
                n(hVar, c0017a, i, fVar);
            }
            o(c0017a, hVar, c0017a.f268f[i]);
            if (z) {
                hVar.l(fVar);
            }
        }
    }

    public final void c(b.a.a.i.c cVar, C0017a c0017a) {
        if (Modifier.isPublic(c0017a.f266d.f450c.getModifiers())) {
            b.a.a.i.i iVar = new b.a.a.i.i(cVar, 1, "createInstance", "(L" + f259a + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;", null, null);
            iVar.c(187, b.a.a.n.b.g(c0017a.g()));
            iVar.h(89);
            iVar.d(183, b.a.a.n.b.g(c0017a.g()), "<init>", "()V");
            iVar.h(176);
            iVar.j(3, 3);
            iVar.e();
        }
    }

    public final void d(C0017a c0017a, b.a.a.i.h hVar) {
        Constructor<?> constructor = c0017a.f266d.f450c;
        if (Modifier.isPublic(constructor.getModifiers())) {
            hVar.c(187, b.a.a.n.b.g(c0017a.g()));
            hVar.h(89);
            hVar.d(183, b.a.a.n.b.g(constructor.getDeclaringClass()), "<init>", "()V");
            hVar.k(58, c0017a.h("instance"));
            return;
        }
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.k(25, 0);
        hVar.f(180, b.a.a.n.b.g(o.class), "clazz", "Ljava/lang/Class;");
        hVar.d(183, b.a.a.n.b.g(o.class), "createInstance", "(L" + f259a + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;");
        hVar.c(192, b.a.a.n.b.g(c0017a.g()));
        hVar.k(58, c0017a.h("instance"));
    }

    public final void e(C0017a c0017a, b.a.a.i.h hVar, b.a.a.n.c cVar, Class<?> cls, int i) {
        int i2;
        l(c0017a, hVar, cVar);
        b.a.a.i.f fVar = new b.a.a.i.f();
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        if ((cVar.j & Feature.SupportArrayToBean.mask) != 0) {
            hVar.h(89);
            hVar.c(193, b.a.a.n.b.g(o.class));
            hVar.b(153, fVar);
            hVar.c(192, b.a.a.n.b.g(o.class));
            hVar.k(25, 1);
            if (cVar.f434f instanceof Class) {
                hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cVar.f433e)));
            } else {
                hVar.k(25, 0);
                hVar.i(Integer.valueOf(i));
                hVar.d(182, b.a.a.n.b.g(o.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            }
            hVar.i(cVar.f429a);
            hVar.i(Integer.valueOf(cVar.j));
            hVar.d(182, b.a.a.n.b.g(o.class), "deserialze", "(L" + f259a + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
            hVar.c(192, b.a.a.n.b.g(cls));
            hVar.k(58, c0017a.h(cVar.f429a + "_asm"));
            hVar.b(167, fVar2);
            hVar.l(fVar);
            i2 = 1;
        } else {
            i2 = 1;
        }
        hVar.k(25, i2);
        if (cVar.f434f instanceof Class) {
            hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cVar.f433e)));
        } else {
            hVar.k(25, 0);
            hVar.i(Integer.valueOf(i));
            hVar.d(182, b.a.a.n.b.g(o.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
        }
        hVar.i(cVar.f429a);
        hVar.d(185, b.a.a.n.b.g(t.class), "deserialze", "(L" + f259a + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        hVar.c(192, b.a.a.n.b.g(cls));
        hVar.k(58, c0017a.h(cVar.f429a + "_asm"));
        hVar.l(fVar2);
    }

    public final void f(C0017a c0017a, b.a.a.i.h hVar, b.a.a.i.f fVar) {
        hVar.g(21, c0017a.h("matchedCount"));
        hVar.b(158, fVar);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, f260b, "token", "()I");
        hVar.i(13);
        hVar.b(160, fVar);
        r(c0017a, hVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0e3b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0e5c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(b.a.a.i.c r30, b.a.a.j.j.a.C0017a r31) {
        /*
            Method dump skipped, instruction units count: 4175
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.j.a.g(b.a.a.i.c, b.a.a.j.j.a$a):void");
    }

    public final void h(b.a.a.i.c cVar, C0017a c0017a) {
        int i;
        b.a.a.i.i iVar;
        Class<o> cls;
        int i2;
        char c2;
        char c3;
        char c4;
        int i3;
        char c5;
        Class<o> cls2 = o.class;
        StringBuilder sb = new StringBuilder();
        sb.append("(L");
        String str = f259a;
        sb.append(str);
        sb.append(";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        b.a.a.i.i iVar2 = new b.a.a.i.i(cVar, 1, "deserialzeArrayMapping", sb.toString(), null, null);
        w(c0017a, iVar2);
        iVar2.k(25, c0017a.h("lexer"));
        iVar2.k(25, 1);
        iVar2.d(182, str, "getSymbolTable", "()" + b.a.a.n.b.b(b.a.a.j.i.class));
        iVar2.d(182, f260b, "scanTypeName", "(" + b.a.a.n.b.b(b.a.a.j.i.class) + ")Ljava/lang/String;");
        iVar2.k(58, c0017a.h("typeName"));
        b.a.a.i.f fVar = new b.a.a.i.f();
        iVar2.k(25, c0017a.h("typeName"));
        iVar2.b(198, fVar);
        iVar2.k(25, 1);
        iVar2.d(182, str, "getConfig", "()" + b.a.a.n.b.b(b.a.a.j.h.class));
        iVar2.k(25, 0);
        iVar2.f(180, b.a.a.n.b.g(cls2), "beanInfo", b.a.a.n.b.b(b.a.a.n.g.class));
        iVar2.k(25, c0017a.h("typeName"));
        iVar2.d(184, b.a.a.n.b.g(cls2), "getSeeAlso", "(" + b.a.a.n.b.b(b.a.a.j.h.class) + b.a.a.n.b.b(b.a.a.n.g.class) + "Ljava/lang/String;)" + b.a.a.n.b.b(cls2));
        iVar2.k(58, c0017a.h("userTypeDeser"));
        iVar2.k(25, c0017a.h("userTypeDeser"));
        iVar2.c(193, b.a.a.n.b.g(cls2));
        iVar2.b(153, fVar);
        iVar2.k(25, c0017a.h("userTypeDeser"));
        iVar2.k(25, 1);
        iVar2.k(25, 2);
        iVar2.k(25, 3);
        iVar2.k(25, 4);
        iVar2.d(182, b.a.a.n.b.g(cls2), "deserialzeArrayMapping", "(L" + str + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        iVar2.h(176);
        iVar2.l(fVar);
        d(c0017a, iVar2);
        b.a.a.n.c[] cVarArr = c0017a.f266d.i;
        int length = cVarArr.length;
        int i4 = 0;
        while (i4 < length) {
            boolean z = i4 == length + (-1);
            int i5 = z ? 93 : 44;
            b.a.a.n.c cVar2 = cVarArr[i4];
            Class<?> cls3 = cVar2.f433e;
            Type type = cVar2.f434f;
            int i6 = length;
            b.a.a.n.c[] cVarArr2 = cVarArr;
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                i = i4;
                iVar = iVar2;
                cls = cls2;
                i2 = i6;
                c2 = ':';
                c3 = 184;
                iVar.k(25, c0017a.h("lexer"));
                iVar.k(16, i5);
                iVar.d(182, f260b, "scanInt", "(C)I");
                iVar.k(54, c0017a.h(cVar2.f429a + "_asm"));
            } else {
                boolean z2 = z;
                int i7 = i4;
                if (cls3 == Byte.class) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    String str2 = f260b;
                    iVar2.d(182, str2, "scanInt", "(C)I");
                    iVar2.d(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    b.a.a.i.f fVar2 = new b.a.a.i.f();
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.f(180, str2, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar2);
                    iVar2.h(1);
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    iVar2.l(fVar2);
                } else if (cls3 == Short.class) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    String str3 = f260b;
                    iVar2.d(182, str3, "scanInt", "(C)I");
                    iVar2.d(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    b.a.a.i.f fVar3 = new b.a.a.i.f();
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.f(180, str3, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar3);
                    iVar2.h(1);
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    iVar2.l(fVar3);
                } else if (cls3 == Integer.class) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    String str4 = f260b;
                    iVar2.d(182, str4, "scanInt", "(C)I");
                    iVar2.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    b.a.a.i.f fVar4 = new b.a.a.i.f();
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.f(180, str4, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar4);
                    iVar2.h(1);
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    iVar2.l(fVar4);
                } else if (cls3 == Long.TYPE) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    iVar2.d(182, f260b, "scanLong", "(C)J");
                    iVar2.k(55, c0017a.i(cVar2.f429a + "_asm", 2));
                } else if (cls3 == Long.class) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    String str5 = f260b;
                    iVar2.d(182, str5, "scanLong", "(C)J");
                    iVar2.d(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    b.a.a.i.f fVar5 = new b.a.a.i.f();
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.f(180, str5, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar5);
                    iVar2.h(1);
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    iVar2.l(fVar5);
                } else if (cls3 == Boolean.TYPE) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    iVar2.d(182, f260b, "scanBoolean", "(C)Z");
                    iVar2.k(54, c0017a.h(cVar2.f429a + "_asm"));
                } else if (cls3 == Float.TYPE) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    iVar2.d(182, f260b, "scanFloat", "(C)F");
                    iVar2.k(56, c0017a.h(cVar2.f429a + "_asm"));
                } else if (cls3 == Float.class) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    String str6 = f260b;
                    iVar2.d(182, str6, "scanFloat", "(C)F");
                    iVar2.d(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    b.a.a.i.f fVar6 = new b.a.a.i.f();
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.f(180, str6, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar6);
                    iVar2.h(1);
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    iVar2.l(fVar6);
                } else if (cls3 == Double.TYPE) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    iVar2.d(182, f260b, "scanDouble", "(C)D");
                    iVar2.k(57, c0017a.i(cVar2.f429a + "_asm", 2));
                } else if (cls3 == Double.class) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    String str7 = f260b;
                    iVar2.d(182, str7, "scanDouble", "(C)D");
                    iVar2.d(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    b.a.a.i.f fVar7 = new b.a.a.i.f();
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.f(180, str7, "matchStat", "I");
                    iVar2.i(5);
                    iVar2.b(160, fVar7);
                    iVar2.h(1);
                    iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    iVar2.l(fVar7);
                } else if (cls3 == Character.TYPE) {
                    iVar2.k(25, c0017a.h("lexer"));
                    iVar2.k(16, i5);
                    iVar2.d(182, f260b, "scanString", "(C)Ljava/lang/String;");
                    iVar2.h(3);
                    iVar2.d(182, "java/lang/String", "charAt", "(I)C");
                    iVar2.k(54, c0017a.h(cVar2.f429a + "_asm"));
                } else {
                    if (cls3 == String.class) {
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.k(16, i5);
                        iVar2.d(182, f260b, "scanString", "(C)Ljava/lang/String;");
                        c4 = ':';
                        iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    } else if (cls3 == BigDecimal.class) {
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.k(16, i5);
                        iVar2.d(182, f260b, "scanDecimal", "(C)Ljava/math/BigDecimal;");
                        c4 = ':';
                        iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    } else if (cls3 == Date.class) {
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.k(16, i5);
                        iVar2.d(182, f260b, "scanDate", "(C)Ljava/util/Date;");
                        c4 = ':';
                        iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    } else if (cls3 == UUID.class) {
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.k(16, i5);
                        iVar2.d(182, f260b, "scanUUID", "(C)Ljava/util/UUID;");
                        c4 = ':';
                        iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                    } else if (cls3.isEnum()) {
                        b.a.a.i.f fVar8 = new b.a.a.i.f();
                        b.a.a.i.f fVar9 = new b.a.a.i.f();
                        b.a.a.i.f fVar10 = new b.a.a.i.f();
                        b.a.a.i.f fVar11 = new b.a.a.i.f();
                        cls = cls2;
                        iVar2.k(25, c0017a.h("lexer"));
                        String str8 = f260b;
                        iVar2.d(182, str8, "getCurrent", "()C");
                        iVar2.h(89);
                        iVar2.k(54, c0017a.h("ch"));
                        iVar2.i(110);
                        iVar2.b(159, fVar11);
                        iVar2.k(21, c0017a.h("ch"));
                        iVar2.i(34);
                        iVar2.b(160, fVar8);
                        iVar2.l(fVar11);
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.i(b.a.a.i.j.g(b.a.a.n.b.b(cls3)));
                        iVar2.k(25, 1);
                        iVar2.d(182, f259a, "getSymbolTable", "()" + b.a.a.n.b.b(b.a.a.j.i.class));
                        iVar2.k(16, i5);
                        iVar2.d(182, str8, "scanEnum", "(Ljava/lang/Class;" + b.a.a.n.b.b(b.a.a.j.i.class) + "C)Ljava/lang/Enum;");
                        iVar2.b(167, fVar10);
                        iVar2.l(fVar8);
                        iVar2.k(21, c0017a.h("ch"));
                        iVar2.i(48);
                        iVar2.b(161, fVar9);
                        iVar2.k(21, c0017a.h("ch"));
                        iVar2.i(57);
                        iVar2.b(163, fVar9);
                        l(c0017a, iVar2, cVar2);
                        iVar2.c(192, b.a.a.n.b.g(h.class));
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.k(16, i5);
                        iVar2.d(182, str8, "scanInt", "(C)I");
                        iVar2.d(182, b.a.a.n.b.g(h.class), "valueOf", "(I)Ljava/lang/Enum;");
                        iVar2.b(167, fVar10);
                        iVar2.l(fVar9);
                        iVar2.k(25, 0);
                        iVar2.k(25, c0017a.h("lexer"));
                        iVar2.k(16, i5);
                        iVar2.d(182, b.a.a.n.b.g(cls), "scanEnum", "(L" + str8 + ";C)Ljava/lang/Enum;");
                        iVar2.l(fVar10);
                        iVar2.c(192, b.a.a.n.b.g(cls3));
                        iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                        c2 = ':';
                        iVar = iVar2;
                        i2 = i6;
                        i = i7;
                        c3 = 184;
                    } else {
                        cls = cls2;
                        if (Collection.class.isAssignableFrom(cls3)) {
                            Class<?> clsR = b.a.a.n.l.R(type);
                            if (clsR == String.class) {
                                if (cls3 == List.class || cls3 == Collections.class || cls3 == ArrayList.class) {
                                    iVar2.c(187, b.a.a.n.b.g(ArrayList.class));
                                    iVar2.h(89);
                                    iVar2.d(183, b.a.a.n.b.g(ArrayList.class), "<init>", "()V");
                                } else {
                                    iVar2.i(b.a.a.i.j.g(b.a.a.n.b.b(cls3)));
                                    iVar2.d(184, b.a.a.n.b.g(b.a.a.n.l.class), "createCollection", "(Ljava/lang/Class;)Ljava/util/Collection;");
                                }
                                iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.k(25, c0017a.h(cVar2.f429a + "_asm"));
                                iVar2.k(16, i5);
                                String str9 = f260b;
                                iVar2.d(182, str9, "scanStringArray", "(Ljava/util/Collection;C)V");
                                b.a.a.i.f fVar12 = new b.a.a.i.f();
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.f(180, str9, "matchStat", "I");
                                iVar2.i(5);
                                iVar2.b(160, fVar12);
                                iVar2.h(1);
                                iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                                iVar2.l(fVar12);
                                i3 = i7;
                                c5 = 184;
                            } else {
                                b.a.a.i.f fVar13 = new b.a.a.i.f();
                                iVar2.k(25, c0017a.h("lexer"));
                                String str10 = f260b;
                                iVar2.d(182, str10, "token", "()I");
                                iVar2.k(54, c0017a.h("token"));
                                iVar2.k(21, c0017a.h("token"));
                                int i8 = i7 == 0 ? 14 : 16;
                                iVar2.i(Integer.valueOf(i8));
                                iVar2.b(159, fVar13);
                                iVar2.k(25, 1);
                                iVar2.i(Integer.valueOf(i8));
                                String str11 = f259a;
                                iVar2.d(182, str11, "throwException", "(I)V");
                                iVar2.l(fVar13);
                                b.a.a.i.f fVar14 = new b.a.a.i.f();
                                b.a.a.i.f fVar15 = new b.a.a.i.f();
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.d(182, str10, "getCurrent", "()C");
                                iVar2.k(16, 91);
                                iVar2.b(160, fVar14);
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.d(182, str10, "next", "()C");
                                iVar2.h(87);
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.i(14);
                                iVar2.d(182, str10, "setToken", "(I)V");
                                iVar2.b(167, fVar15);
                                iVar2.l(fVar14);
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.i(14);
                                iVar2.d(182, str10, "nextToken", "(I)V");
                                iVar2.l(fVar15);
                                i3 = i7;
                                p(iVar2, cls3, i3, false);
                                iVar2.h(89);
                                iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                                k(c0017a, iVar2, cVar2, clsR);
                                iVar2.k(25, 1);
                                iVar2.i(b.a.a.i.j.g(b.a.a.n.b.b(clsR)));
                                iVar2.k(25, 3);
                                c5 = 184;
                                iVar2.d(184, b.a.a.n.b.g(cls), "parseArray", "(Ljava/util/Collection;" + b.a.a.n.b.b(t.class) + "L" + str11 + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                            }
                            i = i3;
                            iVar = iVar2;
                            c3 = c5;
                            i2 = i6;
                            c2 = ':';
                        } else if (cls3.isArray()) {
                            iVar2.k(25, c0017a.h("lexer"));
                            iVar2.i(14);
                            iVar2.d(182, f260b, "nextToken", "(I)V");
                            iVar2.k(25, 1);
                            iVar2.k(25, 0);
                            iVar2.i(Integer.valueOf(i7));
                            iVar2.d(182, b.a.a.n.b.g(cls), "getFieldType", "(I)Ljava/lang/reflect/Type;");
                            iVar2.d(182, f259a, "parseObject", "(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
                            iVar2.c(192, b.a.a.n.b.g(cls3));
                            c4 = ':';
                            iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                            i = i7;
                            iVar = iVar2;
                            c3 = (char) 184;
                            i2 = i6;
                            c2 = c4;
                        } else {
                            b.a.a.i.f fVar16 = new b.a.a.i.f();
                            b.a.a.i.f fVar17 = new b.a.a.i.f();
                            if (cls3 == Date.class) {
                                iVar2.k(25, c0017a.h("lexer"));
                                String str12 = f260b;
                                iVar2.d(182, str12, "getCurrent", "()C");
                                iVar2.i(49);
                                iVar2.b(160, fVar16);
                                iVar2.c(187, b.a.a.n.b.g(Date.class));
                                iVar2.h(89);
                                iVar2.k(25, c0017a.h("lexer"));
                                iVar2.k(16, i5);
                                iVar2.d(182, str12, "scanLong", "(C)J");
                                iVar2.d(183, b.a.a.n.b.g(Date.class), "<init>", "(J)V");
                                c2 = ':';
                                iVar2.k(58, c0017a.h(cVar2.f429a + "_asm"));
                                iVar2.b(167, fVar17);
                            } else {
                                c2 = ':';
                            }
                            iVar2.l(fVar16);
                            q(c0017a, iVar2, 14);
                            i = i7;
                            i2 = i6;
                            c3 = 184;
                            iVar = iVar2;
                            e(c0017a, iVar2, cVar2, cls3, i);
                            iVar.k(25, c0017a.h("lexer"));
                            iVar.d(182, f260b, "token", "()I");
                            iVar.i(15);
                            iVar.b(159, fVar17);
                            iVar.k(25, 0);
                            iVar.k(25, c0017a.h("lexer"));
                            if (z2) {
                                iVar.i(15);
                            } else {
                                iVar.i(16);
                            }
                            iVar.d(183, b.a.a.n.b.g(cls), "check", "(" + b.a.a.n.b.b(b.a.a.j.b.class) + "I)V");
                            iVar.l(fVar17);
                        }
                    }
                    iVar = iVar2;
                    cls = cls2;
                    i2 = i6;
                    i = i7;
                    c3 = 184;
                    c2 = c4;
                }
                iVar = iVar2;
                cls = cls2;
                i2 = i6;
                i = i7;
                c2 = ':';
                c3 = 184;
            }
            i4 = i + 1;
            length = i2;
            iVar2 = iVar;
            cVarArr = cVarArr2;
            cls2 = cls;
        }
        b.a.a.i.i iVar3 = iVar2;
        b(c0017a, iVar3, false);
        b.a.a.i.f fVar18 = new b.a.a.i.f();
        b.a.a.i.f fVar19 = new b.a.a.i.f();
        b.a.a.i.f fVar20 = new b.a.a.i.f();
        b.a.a.i.f fVar21 = new b.a.a.i.f();
        iVar3.k(25, c0017a.h("lexer"));
        String str13 = f260b;
        iVar3.d(182, str13, "getCurrent", "()C");
        iVar3.h(89);
        iVar3.k(54, c0017a.h("ch"));
        iVar3.k(16, 44);
        iVar3.b(160, fVar19);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.d(182, str13, "next", "()C");
        iVar3.h(87);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.i(16);
        iVar3.d(182, str13, "setToken", "(I)V");
        iVar3.b(167, fVar21);
        iVar3.l(fVar19);
        iVar3.k(21, c0017a.h("ch"));
        iVar3.k(16, 93);
        iVar3.b(160, fVar20);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.d(182, str13, "next", "()C");
        iVar3.h(87);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.i(15);
        iVar3.d(182, str13, "setToken", "(I)V");
        iVar3.b(167, fVar21);
        iVar3.l(fVar20);
        iVar3.k(21, c0017a.h("ch"));
        iVar3.k(16, 26);
        iVar3.b(160, fVar18);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.d(182, str13, "next", "()C");
        iVar3.h(87);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.i(20);
        iVar3.d(182, str13, "setToken", "(I)V");
        iVar3.b(167, fVar21);
        iVar3.l(fVar18);
        iVar3.k(25, c0017a.h("lexer"));
        iVar3.i(16);
        iVar3.d(182, str13, "nextToken", "(I)V");
        iVar3.l(fVar21);
        iVar3.k(25, c0017a.h("instance"));
        iVar3.h(176);
        iVar3.j(5, c0017a.f263a);
        iVar3.e();
    }

    public final void i(C0017a c0017a, b.a.a.i.h hVar, b.a.a.i.f fVar, b.a.a.n.c cVar, Class<?> cls, Class<?> cls2, int i) {
        String str;
        String str2;
        String str3;
        String str4;
        b.a.a.i.f fVar2;
        String str5;
        int i2;
        b.a.a.i.f fVar3 = new b.a.a.i.f();
        String str6 = f260b;
        hVar.d(182, str6, "matchField", "([C)Z");
        hVar.b(153, fVar3);
        u(hVar, c0017a, i);
        b.a.a.i.f fVar4 = new b.a.a.i.f();
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(8);
        hVar.b(160, fVar4);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(16);
        hVar.d(182, str6, "nextToken", "(I)V");
        hVar.b(167, fVar3);
        hVar.l(fVar4);
        b.a.a.i.f fVar5 = new b.a.a.i.f();
        b.a.a.i.f fVar6 = new b.a.a.i.f();
        b.a.a.i.f fVar7 = new b.a.a.i.f();
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(21);
        hVar.b(160, fVar6);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(14);
        hVar.d(182, str6, "nextToken", "(I)V");
        p(hVar, cls, i, true);
        hVar.b(167, fVar5);
        hVar.l(fVar6);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(14);
        hVar.b(159, fVar7);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str6, "token", "()I");
        hVar.i(12);
        hVar.b(160, fVar);
        p(hVar, cls, i, false);
        hVar.k(58, c0017a.h(cVar.f429a + "_asm"));
        k(c0017a, hVar, cVar, cls2);
        hVar.k(25, 1);
        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
        hVar.h(3);
        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        String strG = b.a.a.n.b.g(t.class);
        StringBuilder sb = new StringBuilder();
        sb.append("(L");
        String str7 = f259a;
        sb.append(str7);
        sb.append(";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        hVar.d(185, strG, "deserialze", sb.toString());
        hVar.k(58, c0017a.h("list_item_value"));
        hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
        hVar.k(25, c0017a.h("list_item_value"));
        if (cls.isInterface()) {
            str = "list_item_value";
            hVar.d(185, b.a.a.n.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            str = "list_item_value";
            hVar.d(182, b.a.a.n.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        }
        hVar.h(87);
        hVar.b(167, fVar3);
        hVar.l(fVar7);
        p(hVar, cls, i, false);
        hVar.l(fVar5);
        hVar.k(58, c0017a.h(cVar.f429a + "_asm"));
        boolean zV = b.a.a.j.h.v(cVar.f433e);
        k(c0017a, hVar, cVar, cls2);
        if (zV) {
            hVar.d(185, b.a.a.n.b.g(t.class), "getFastMatchToken", "()I");
            hVar.k(54, c0017a.h("fastMatchToken"));
            hVar.k(25, c0017a.h("lexer"));
            hVar.k(21, c0017a.h("fastMatchToken"));
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
            hVar.k(54, c0017a.h("fastMatchToken"));
            q(c0017a, hVar, 12);
        }
        hVar.k(25, 1);
        String str8 = str4;
        hVar.d(182, str7, "getContext", "()" + b.a.a.n.b.b(b.a.a.j.g.class));
        hVar.k(58, c0017a.h("listContext"));
        hVar.k(25, 1);
        hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
        hVar.i(cVar.f429a);
        hVar.d(182, str7, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + b.a.a.n.b.b(b.a.a.j.g.class));
        hVar.h(87);
        b.a.a.i.f fVar8 = new b.a.a.i.f();
        b.a.a.i.f fVar9 = new b.a.a.i.f();
        hVar.h(3);
        String str9 = str2;
        hVar.k(54, c0017a.h("i"));
        hVar.l(fVar8);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str3, "token", "()I");
        hVar.i(15);
        hVar.b(159, fVar9);
        hVar.k(25, 0);
        hVar.f(180, c0017a.f267e, cVar.f429a + "_asm_list_item_deser__", b.a.a.n.b.b(t.class));
        hVar.k(25, 1);
        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls2)));
        hVar.k(21, c0017a.h("i"));
        hVar.d(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        hVar.d(185, b.a.a.n.b.g(t.class), "deserialze", "(L" + str7 + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        String str10 = str;
        hVar.k(58, c0017a.h(str10));
        hVar.a(c0017a.h("i"), 1);
        hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
        hVar.k(25, c0017a.h(str10));
        if (cls.isInterface()) {
            hVar.d(185, b.a.a.n.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            hVar.d(182, b.a.a.n.b.g(cls), "add", "(Ljava/lang/Object;)Z");
        }
        hVar.h(87);
        hVar.k(25, 1);
        hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
        hVar.d(182, str7, "checkListResolve", "(Ljava/util/Collection;)V");
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str3, "token", "()I");
        hVar.i(16);
        hVar.b(160, fVar8);
        if (zV) {
            hVar.k(25, c0017a.h("lexer"));
            hVar.k(21, c0017a.h("fastMatchToken"));
            hVar.d(182, str3, str9, str8);
            i2 = 167;
            str5 = str7;
        } else {
            str5 = str7;
            q(c0017a, hVar, 12);
            i2 = 167;
        }
        hVar.b(i2, fVar8);
        hVar.l(fVar9);
        hVar.k(25, 1);
        hVar.k(25, c0017a.h("listContext"));
        hVar.d(182, str5, "setContext", "(" + b.a.a.n.b.b(b.a.a.j.g.class) + ")V");
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str3, "token", "()I");
        hVar.i(15);
        hVar.b(160, fVar);
        r(c0017a, hVar);
        hVar.l(fVar2);
    }

    public final void j(C0017a c0017a, b.a.a.i.h hVar, b.a.a.i.f fVar, b.a.a.n.c cVar, Class<?> cls, int i) {
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        b.a.a.i.f fVar3 = new b.a.a.i.f();
        hVar.k(25, c0017a.h("lexer"));
        hVar.k(25, 0);
        hVar.f(180, c0017a.f267e, cVar.f429a + "_asm_prefix__", "[C");
        hVar.d(182, f260b, "matchField", "([C)Z");
        hVar.b(154, fVar2);
        hVar.h(1);
        hVar.k(58, c0017a.h(cVar.f429a + "_asm"));
        hVar.b(167, fVar3);
        hVar.l(fVar2);
        u(hVar, c0017a, i);
        hVar.k(21, c0017a.h("matchedCount"));
        hVar.h(4);
        hVar.h(96);
        hVar.k(54, c0017a.h("matchedCount"));
        e(c0017a, hVar, cVar, cls, i);
        hVar.k(25, 1);
        String str = f259a;
        hVar.d(182, str, "getResolveStatus", "()I");
        hVar.i(1);
        hVar.b(160, fVar3);
        hVar.k(25, 1);
        hVar.d(182, str, "getLastResolveTask", "()" + b.a.a.n.b.b(a.C0016a.class));
        hVar.k(58, c0017a.h("resolveTask"));
        hVar.k(25, c0017a.h("resolveTask"));
        hVar.k(25, 1);
        hVar.d(182, str, "getContext", "()" + b.a.a.n.b.b(b.a.a.j.g.class));
        hVar.f(181, b.a.a.n.b.g(a.C0016a.class), "ownerContext", b.a.a.n.b.b(b.a.a.j.g.class));
        hVar.k(25, c0017a.h("resolveTask"));
        hVar.k(25, 0);
        hVar.i(cVar.f429a);
        hVar.d(182, b.a.a.n.b.g(o.class), "getFieldDeserializer", "(Ljava/lang/String;)" + b.a.a.n.b.b(l.class));
        hVar.f(181, b.a.a.n.b.g(a.C0016a.class), "fieldDeserializer", b.a.a.n.b.b(l.class));
        hVar.k(25, 1);
        hVar.i(0);
        hVar.d(182, str, "setResolveStatus", "(I)V");
        hVar.l(fVar3);
    }

    public final void k(C0017a c0017a, b.a.a.i.h hVar, b.a.a.n.c cVar, Class<?> cls) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        hVar.k(25, 0);
        hVar.f(180, c0017a.f267e, cVar.f429a + "_asm_list_item_deser__", b.a.a.n.b.b(t.class));
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.d(182, f259a, "getConfig", "()" + b.a.a.n.b.b(b.a.a.j.h.class));
        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cls)));
        hVar.d(182, b.a.a.n.b.g(b.a.a.j.h.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + b.a.a.n.b.b(t.class));
        hVar.f(181, c0017a.f267e, cVar.f429a + "_asm_list_item_deser__", b.a.a.n.b.b(t.class));
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0017a.f267e, cVar.f429a + "_asm_list_item_deser__", b.a.a.n.b.b(t.class));
    }

    public final void l(C0017a c0017a, b.a.a.i.h hVar, b.a.a.n.c cVar) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        hVar.k(25, 0);
        hVar.f(180, c0017a.f267e, cVar.f429a + "_asm_deser__", b.a.a.n.b.b(t.class));
        hVar.b(199, fVar);
        hVar.k(25, 0);
        hVar.k(25, 1);
        hVar.d(182, f259a, "getConfig", "()" + b.a.a.n.b.b(b.a.a.j.h.class));
        hVar.i(b.a.a.i.j.g(b.a.a.n.b.b(cVar.f433e)));
        hVar.d(182, b.a.a.n.b.g(b.a.a.j.h.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + b.a.a.n.b.b(t.class));
        hVar.f(181, c0017a.f267e, cVar.f429a + "_asm_deser__", b.a.a.n.b.b(t.class));
        hVar.l(fVar);
        hVar.k(25, 0);
        hVar.f(180, c0017a.f267e, cVar.f429a + "_asm_deser__", b.a.a.n.b.b(t.class));
    }

    public final void m(b.a.a.i.c cVar, C0017a c0017a) {
        int length = c0017a.f268f.length;
        for (int i = 0; i < length; i++) {
            new b.a.a.i.d(cVar, 1, c0017a.f268f[i].f429a + "_asm_prefix__", "[C").c();
        }
        int length2 = c0017a.f268f.length;
        for (int i2 = 0; i2 < length2; i2++) {
            b.a.a.n.c cVar2 = c0017a.f268f[i2];
            Class<?> cls = cVar2.f433e;
            if (!cls.isPrimitive()) {
                if (Collection.class.isAssignableFrom(cls)) {
                    new b.a.a.i.d(cVar, 1, cVar2.f429a + "_asm_list_item_deser__", b.a.a.n.b.b(t.class)).c();
                } else {
                    new b.a.a.i.d(cVar, 1, cVar2.f429a + "_asm_deser__", b.a.a.n.b.b(t.class)).c();
                }
            }
        }
        b.a.a.i.i iVar = new b.a.a.i.i(cVar, 1, "<init>", "(" + b.a.a.n.b.b(b.a.a.j.h.class) + b.a.a.n.b.b(b.a.a.n.g.class) + ")V", null, null);
        iVar.k(25, 0);
        iVar.k(25, 1);
        iVar.k(25, 2);
        iVar.d(183, b.a.a.n.b.g(o.class), "<init>", "(" + b.a.a.n.b.b(b.a.a.j.h.class) + b.a.a.n.b.b(b.a.a.n.g.class) + ")V");
        int length3 = c0017a.f268f.length;
        for (int i3 = 0; i3 < length3; i3++) {
            b.a.a.n.c cVar3 = c0017a.f268f[i3];
            iVar.k(25, 0);
            iVar.i("\"" + cVar3.f429a + "\":");
            iVar.d(182, "java/lang/String", "toCharArray", "()[C");
            iVar.f(181, c0017a.f267e, cVar3.f429a + "_asm_prefix__", "[C");
        }
        iVar.h(177);
        iVar.j(4, 4);
        iVar.e();
    }

    public final void n(b.a.a.i.h hVar, C0017a c0017a, int i, b.a.a.i.f fVar) {
        hVar.k(21, c0017a.h("_asm_flag_" + (i / 32)));
        hVar.i(Integer.valueOf(1 << i));
        hVar.h(126);
        hVar.b(153, fVar);
    }

    public final void o(C0017a c0017a, b.a.a.i.h hVar, b.a.a.n.c cVar) {
        Class<?> cls = cVar.f433e;
        Type type = cVar.f434f;
        if (cls == Boolean.TYPE) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(21, c0017a.h(cVar.f429a + "_asm"));
            s(c0017a, hVar, cVar);
            return;
        }
        if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Character.TYPE) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(21, c0017a.h(cVar.f429a + "_asm"));
            s(c0017a, hVar, cVar);
            return;
        }
        if (cls == Long.TYPE) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(22, c0017a.i(cVar.f429a + "_asm", 2));
            if (cVar.f430b == null) {
                hVar.f(181, b.a.a.n.b.g(cVar.g), cVar.f431c.getName(), b.a.a.n.b.b(cVar.f433e));
                return;
            }
            hVar.d(182, b.a.a.n.b.g(c0017a.g()), cVar.f430b.getName(), b.a.a.n.b.c(cVar.f430b));
            if (cVar.f430b.getReturnType().equals(Void.TYPE)) {
                return;
            }
            hVar.h(87);
            return;
        }
        if (cls == Float.TYPE) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(23, c0017a.h(cVar.f429a + "_asm"));
            s(c0017a, hVar, cVar);
            return;
        }
        if (cls == Double.TYPE) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(24, c0017a.i(cVar.f429a + "_asm", 2));
            s(c0017a, hVar, cVar);
            return;
        }
        if (cls == String.class) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
            s(c0017a, hVar, cVar);
            return;
        }
        if (cls.isEnum()) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
            s(c0017a, hVar, cVar);
            return;
        }
        if (!Collection.class.isAssignableFrom(cls)) {
            hVar.k(25, c0017a.h("instance"));
            hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
            s(c0017a, hVar, cVar);
            return;
        }
        hVar.k(25, c0017a.h("instance"));
        if (b.a.a.n.l.R(type) == String.class) {
            hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
            hVar.c(192, b.a.a.n.b.g(cls));
        } else {
            hVar.k(25, c0017a.h(cVar.f429a + "_asm"));
        }
        s(c0017a, hVar, cVar);
    }

    public final void p(b.a.a.i.h hVar, Class<?> cls, int i, boolean z) {
        if (cls.isAssignableFrom(ArrayList.class) && !z) {
            hVar.c(187, "java/util/ArrayList");
            hVar.h(89);
            hVar.d(183, "java/util/ArrayList", "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedList.class) && !z) {
            hVar.c(187, b.a.a.n.b.g(LinkedList.class));
            hVar.h(89);
            hVar.d(183, b.a.a.n.b.g(LinkedList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(HashSet.class)) {
            hVar.c(187, b.a.a.n.b.g(HashSet.class));
            hVar.h(89);
            hVar.d(183, b.a.a.n.b.g(HashSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(TreeSet.class)) {
            hVar.c(187, b.a.a.n.b.g(TreeSet.class));
            hVar.h(89);
            hVar.d(183, b.a.a.n.b.g(TreeSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
            hVar.c(187, b.a.a.n.b.g(LinkedHashSet.class));
            hVar.h(89);
            hVar.d(183, b.a.a.n.b.g(LinkedHashSet.class), "<init>", "()V");
        } else if (z) {
            hVar.c(187, b.a.a.n.b.g(HashSet.class));
            hVar.h(89);
            hVar.d(183, b.a.a.n.b.g(HashSet.class), "<init>", "()V");
        } else {
            hVar.k(25, 0);
            hVar.i(Integer.valueOf(i));
            hVar.d(182, b.a.a.n.b.g(o.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            hVar.d(184, b.a.a.n.b.g(b.a.a.n.l.class), "createCollection", "(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
        }
        hVar.c(192, b.a.a.n.b.g(cls));
    }

    public final void q(C0017a c0017a, b.a.a.i.h hVar, int i) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        hVar.k(25, c0017a.h("lexer"));
        String str = f260b;
        hVar.d(182, str, "getCurrent", "()C");
        if (i == 12) {
            hVar.k(16, 123);
        } else {
            if (i != 14) {
                throw new IllegalStateException();
            }
            hVar.k(16, 91);
        }
        hVar.b(160, fVar);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(Integer.valueOf(i));
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar2);
        hVar.l(fVar);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(Integer.valueOf(i));
        hVar.d(182, str, "nextToken", "(I)V");
        hVar.l(fVar2);
    }

    public final void r(C0017a c0017a, b.a.a.i.h hVar) {
        b.a.a.i.f fVar = new b.a.a.i.f();
        b.a.a.i.f fVar2 = new b.a.a.i.f();
        b.a.a.i.f fVar3 = new b.a.a.i.f();
        b.a.a.i.f fVar4 = new b.a.a.i.f();
        b.a.a.i.f fVar5 = new b.a.a.i.f();
        hVar.k(25, c0017a.h("lexer"));
        String str = f260b;
        hVar.d(182, str, "getCurrent", "()C");
        hVar.h(89);
        hVar.k(54, c0017a.h("ch"));
        hVar.k(16, 44);
        hVar.b(160, fVar2);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(16);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar2);
        hVar.k(21, c0017a.h("ch"));
        hVar.k(16, 125);
        hVar.b(160, fVar3);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(13);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar3);
        hVar.k(21, c0017a.h("ch"));
        hVar.k(16, 93);
        hVar.b(160, fVar4);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str, "next", "()C");
        hVar.h(87);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(15);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar4);
        hVar.k(21, c0017a.h("ch"));
        hVar.k(16, 26);
        hVar.b(160, fVar);
        hVar.k(25, c0017a.h("lexer"));
        hVar.i(20);
        hVar.d(182, str, "setToken", "(I)V");
        hVar.b(167, fVar5);
        hVar.l(fVar);
        hVar.k(25, c0017a.h("lexer"));
        hVar.d(182, str, "nextToken", "()V");
        hVar.l(fVar5);
    }

    public final void s(C0017a c0017a, b.a.a.i.h hVar, b.a.a.n.c cVar) {
        Method method = cVar.f430b;
        if (method == null) {
            hVar.f(181, b.a.a.n.b.g(cVar.g), cVar.f431c.getName(), b.a.a.n.b.b(cVar.f433e));
            return;
        }
        hVar.d(method.getDeclaringClass().isInterface() ? 185 : 182, b.a.a.n.b.g(cVar.g), method.getName(), b.a.a.n.b.c(method));
        if (cVar.f430b.getReturnType().equals(Void.TYPE)) {
            return;
        }
        hVar.h(87);
    }

    public final void t(C0017a c0017a, b.a.a.i.h hVar) {
        hVar.k(25, 1);
        hVar.k(25, c0017a.h("context"));
        hVar.d(182, f259a, "setContext", "(" + b.a.a.n.b.b(b.a.a.j.g.class) + ")V");
        b.a.a.i.f fVar = new b.a.a.i.f();
        hVar.k(25, c0017a.h("childContext"));
        hVar.b(198, fVar);
        hVar.k(25, c0017a.h("childContext"));
        hVar.k(25, c0017a.h("instance"));
        hVar.f(181, b.a.a.n.b.g(b.a.a.j.g.class), "object", "Ljava/lang/Object;");
        hVar.l(fVar);
    }

    public final void u(b.a.a.i.h hVar, C0017a c0017a, int i) {
        String str = "_asm_flag_" + (i / 32);
        hVar.k(21, c0017a.h(str));
        hVar.i(Integer.valueOf(1 << i));
        hVar.h(128);
        hVar.k(54, c0017a.h(str));
    }

    public t v(b.a.a.j.h hVar, b.a.a.n.g gVar) throws Exception {
        String str;
        Class<?> cls = gVar.f448a;
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("not support type :" + cls.getName());
        }
        String str2 = "FastjsonASMDeserializer_" + this.f262d.incrementAndGet() + "_" + cls.getSimpleName();
        Package r1 = a.class.getPackage();
        if (r1 != null) {
            String name = r1.getName();
            String str3 = name.replace('.', '/') + "/" + str2;
            str = name + "." + str2;
            str2 = str3;
        } else {
            str = str2;
        }
        b.a.a.i.c cVar = new b.a.a.i.c();
        cVar.k(49, 33, str2, b.a.a.n.b.g(o.class), null);
        m(cVar, new C0017a(str2, hVar, gVar, 3));
        c(cVar, new C0017a(str2, hVar, gVar, 3));
        g(cVar, new C0017a(str2, hVar, gVar, 5));
        h(cVar, new C0017a(str2, hVar, gVar, 4));
        byte[] bArrJ = cVar.j();
        return (t) this.f261c.a(str, bArrJ, 0, bArrJ.length).getConstructor(b.a.a.j.h.class, b.a.a.n.g.class).newInstance(hVar, gVar);
    }

    public final void w(C0017a c0017a, b.a.a.i.h hVar) {
        hVar.k(25, 1);
        hVar.f(180, f259a, "lexer", b.a.a.n.b.b(b.a.a.j.b.class));
        hVar.c(192, f260b);
        hVar.k(58, c0017a.h("lexer"));
    }
}
