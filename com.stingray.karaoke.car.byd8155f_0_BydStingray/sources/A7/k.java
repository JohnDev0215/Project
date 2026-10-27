package A7;

import E5.M;
import T.C0761q;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;
import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class k {
    public static H7.k a(Class cls, String str, String str2, int i7, B b10) {
        return b10.f(new p(cls, str, str2, i7));
    }

    public static R6.c b(I5.k kVar, int i7) {
        return R6.a.a(new I5.j(kVar, i7, 0));
    }

    public static c9.E c(File file, String str, c9.y yVar, File file2) {
        m.f(file, str);
        return new c9.E(yVar, file2);
    }

    public static Object d(Class cls, E5.H h10, String str) {
        return M.a(h10, A.b(cls)).b(str);
    }

    public static String e(Class cls, Object obj) {
        E5.q qVarA = A9.i.a().a(cls);
        qVarA.getClass();
        C2319i c2319i = new C2319i();
        try {
            qVarA.d(new E5.w(c2319i), obj);
            return c2319i.Q();
        } catch (IOException e10) {
            throw new AssertionError(e10);
        }
    }

    public static String f(String str) {
        String string = UUID.randomUUID().toString();
        m.e(string, str);
        return string;
    }

    public static String g(String str, int i7, String str2) {
        return str + i7 + str2;
    }

    public static String h(String str, c9.y yVar) {
        return str + yVar;
    }

    public static String i(String str, String str2, int i7, int i10) {
        return str + i7 + str2 + i10;
    }

    public static String j(String str, String str2, String str3) {
        Locale locale = Locale.getDefault();
        m.e(locale, str);
        String lowerCase = str2.toLowerCase(locale);
        m.e(lowerCase, str3);
        return lowerCase;
    }

    public static String k(StringBuilder sb, int i7, String str, String str2) {
        sb.append(i7);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder l(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static ArrayList m(Object obj, String str) {
        ArrayList arrayList = new ArrayList();
        m.d(obj, str);
        return arrayList;
    }

    public static /* synthetic */ void n(int i7) {
        if (i7 != 0) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        m.i(nullPointerException, m.class.getName());
        throw nullPointerException;
    }

    public static void o(int i7, b0.b bVar, C0761q c0761q, boolean z6) {
        bVar.invoke(c0761q, Integer.valueOf(i7));
        c0761q.p(z6);
    }

    public static /* synthetic */ void p(int i7, String str) {
        if (i7 == 0) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = m.class.getName();
            int i10 = 0;
            while (!stackTrace[i10].getClassName().equals(name)) {
                i10++;
            }
            while (stackTrace[i10].getClassName().equals(name)) {
                i10++;
            }
            StackTraceElement stackTraceElement = stackTrace[i10];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
            m.i(nullPointerException, m.class.getName());
            throw nullPointerException;
        }
    }

    public static /* synthetic */ void q(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static boolean r(C0761q c0761q, int i7, m6.b bVar, C0761q c0761q2, boolean z6) {
        c0761q.Q(i7);
        boolean zH = m6.b.h(bVar, c0761q2);
        c0761q.p(z6);
        return zH;
    }

    public static /* synthetic */ void s(int i7, String str) {
        if (i7 != 0) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(android.hardware.bydauto.ac.tsimpl.a.h(str, " must not be null"));
        m.i(nullPointerException, m.class.getName());
        throw nullPointerException;
    }

    public static boolean t(C0761q c0761q, int i7, m6.b bVar, C0761q c0761q2, boolean z6) {
        c0761q.Q(i7);
        boolean zI = m6.b.i(bVar, c0761q2);
        c0761q.p(z6);
        return zI;
    }

    public static boolean u(C0761q c0761q, int i7, m6.b bVar, C0761q c0761q2, boolean z6) {
        c0761q.Q(i7);
        boolean zJ = m6.b.j(bVar, c0761q2);
        c0761q.p(z6);
        return zJ;
    }

    public static /* synthetic */ String v(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? "null" : "Idle" : "LookaheadLayingOut" : "LayingOut" : "LookaheadMeasuring" : "Measuring";
    }

    public static /* synthetic */ String w(int i7) {
        switch (i7) {
            case 1:
                return "BEGIN_ARRAY";
            case 2:
                return "END_ARRAY";
            case 3:
                return "BEGIN_OBJECT";
            case 4:
                return "END_OBJECT";
            case 5:
                return "NAME";
            case 6:
                return "STRING";
            case 7:
                return "NUMBER";
            case 8:
                return "BOOLEAN";
            case 9:
                return "NULL";
            case 10:
                return "END_DOCUMENT";
            default:
                return "null";
        }
    }

    public static /* synthetic */ String x(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? "null" : "OUT_VARIANCE" : "IN_VARIANCE" : "INVARIANT";
    }
}
