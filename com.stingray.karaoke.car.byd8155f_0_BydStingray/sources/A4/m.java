package A4;

import android.os.SystemClock;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import p2.v;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f427b;
    public Serializable c;

    public m(long j3) {
        this.c = new LinkedHashMap(100, 0.75f, true);
        this.f426a = j3;
    }

    public synchronized Object a(Object obj) {
        l lVar;
        lVar = (l) ((LinkedHashMap) this.c).get(obj);
        return lVar != null ? lVar.f424a : null;
    }

    public int b(Object obj) {
        return 1;
    }

    public void c(Object obj, Object obj2) {
    }

    public synchronized Object d(Object obj, Object obj2) {
        int iB = b(obj2);
        long j3 = iB;
        if (j3 >= this.f426a) {
            c(obj, obj2);
            return null;
        }
        if (obj2 != null) {
            this.f427b += j3;
        }
        l lVar = (l) ((LinkedHashMap) this.c).put(obj, obj2 == null ? null : new l(obj2, iB));
        if (lVar != null) {
            this.f427b -= (long) lVar.f425b;
            if (!lVar.f424a.equals(obj2)) {
                c(obj, lVar.f424a);
            }
        }
        f(this.f426a);
        return lVar != null ? lVar.f424a : null;
    }

    public void e(Exception exc) {
        boolean z6;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (((Exception) this.c) == null) {
            this.c = exc;
        }
        if (this.f426a == -9223372036854775807L) {
            synchronized (v.f20776j0) {
                z6 = v.f20778l0 > 0;
            }
            if (!z6) {
                this.f426a = 200 + jElapsedRealtime;
            }
        }
        long j3 = this.f426a;
        if (j3 == -9223372036854775807L || jElapsedRealtime < j3) {
            this.f427b = jElapsedRealtime + 50;
            return;
        }
        Exception exc2 = (Exception) this.c;
        if (exc2 != exc) {
            exc2.addSuppressed(exc);
        }
        Exception exc3 = (Exception) this.c;
        this.c = null;
        this.f426a = -9223372036854775807L;
        this.f427b = -9223372036854775807L;
        throw exc3;
    }

    public synchronized void f(long j3) {
        while (this.f427b > j3) {
            Iterator it = ((LinkedHashMap) this.c).entrySet().iterator();
            Map.Entry entry = (Map.Entry) it.next();
            l lVar = (l) entry.getValue();
            this.f427b -= (long) lVar.f425b;
            Object key = entry.getKey();
            it.remove();
            c(key, lVar.f424a);
        }
    }

    public m() {
        this.f426a = -9223372036854775807L;
        this.f427b = -9223372036854775807L;
    }
}
