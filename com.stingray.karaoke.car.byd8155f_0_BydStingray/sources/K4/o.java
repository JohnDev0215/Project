package k4;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ArrayDeque f18203b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f18204a;

    static {
        char[] cArr = A4.q.f432a;
        f18203b = new ArrayDeque(0);
    }

    public static o a(Object obj) {
        o oVar;
        ArrayDeque arrayDeque = f18203b;
        synchronized (arrayDeque) {
            oVar = (o) arrayDeque.poll();
        }
        if (oVar == null) {
            oVar = new o();
        }
        oVar.f18204a = obj;
        return oVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        oVar.getClass();
        return this.f18204a.equals(oVar.f18204a);
    }

    public final int hashCode() {
        return this.f18204a.hashCode();
    }
}
