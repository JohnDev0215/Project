package E8;

import A.C0050y0;
import B2.O;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public class l implements o {
    public static final String d = Q8.h.d1(l.class.getCanonicalName(), "");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f1859e = new b("NO_LOCKS", a.l);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f1860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1861b;
    public final String c;

    public l(String str) {
        this(str, new O(new ReentrantLock()));
    }

    public static void e(AssertionError assertionError) {
        StackTraceElement[] stackTrace = assertionError.getStackTrace();
        int length = stackTrace.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            } else if (!stackTrace[i7].getClassName().startsWith(d)) {
                break;
            } else {
                i7++;
            }
        }
        List listSubList = Arrays.asList(stackTrace).subList(i7, length);
        assertionError.setStackTrace((StackTraceElement[]) listSubList.toArray(new StackTraceElement[listSubList.size()]));
    }

    public final i a(InterfaceC2772a interfaceC2772a) {
        return new i(this, interfaceC2772a);
    }

    public final e b(InterfaceC2782k interfaceC2782k) {
        return new e(this, new ConcurrentHashMap(3, 1.0f, 2), interfaceC2782k, 1);
    }

    public final j c(InterfaceC2782k interfaceC2782k) {
        return new j(this, new ConcurrentHashMap(3, 1.0f, 2), interfaceC2782k);
    }

    public C0050y0 d(Object obj, String str) {
        String str2;
        StringBuilder sb = new StringBuilder("Recursion detected ");
        sb.append(str);
        if (obj == null) {
            str2 = "";
        } else {
            str2 = "on input: " + obj;
        }
        sb.append(str2);
        sb.append(" under ");
        sb.append(this);
        AssertionError assertionError = new AssertionError(sb.toString());
        e(assertionError);
        throw assertionError;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(" (");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, this.c, ")");
    }

    public l(String str, n nVar) {
        a aVar = a.f1849m;
        this.f1860a = nVar;
        this.f1861b = aVar;
        this.c = str;
    }
}
