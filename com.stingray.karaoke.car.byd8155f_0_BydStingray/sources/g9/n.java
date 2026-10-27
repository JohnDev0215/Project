package g9;

import c9.C1040a;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f15088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f9.b f15089b;
    public final e9.f c;
    public final ConcurrentLinkedQueue d;

    public n(f9.c cVar, TimeUnit timeUnit) {
        A7.m.f(cVar, "taskRunner");
        this.f15088a = timeUnit.toNanos(5L);
        this.f15089b = cVar.e();
        this.c = new e9.f(this, android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), d9.b.f13650h, " ConnectionPool"), 2);
        this.d = new ConcurrentLinkedQueue();
    }

    public final boolean a(C1040a c1040a, j jVar, ArrayList arrayList, boolean z6) {
        A7.m.f(jVar, "call");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                return false;
            }
            m mVar = (m) it.next();
            A7.m.e(mVar, "connection");
            synchronized (mVar) {
                if (z6) {
                    if (!(mVar.f15079g != null)) {
                        continue;
                    }
                }
                if (mVar.h(c1040a, arrayList)) {
                    jVar.b(mVar);
                    return true;
                }
            }
        }
    }

    public final int b(m mVar, long j3) {
        byte[] bArr = d9.b.f13645a;
        ArrayList arrayList = mVar.f15086p;
        int i7 = 0;
        while (i7 < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i7);
            if (reference.get() != null) {
                i7++;
            } else {
                String str = "A connection to " + mVar.f15076b.f12528a.f12540h + " was leaked. Did you forget to close a response body?";
                l9.o oVar = l9.o.f18874a;
                l9.o.f18874a.j(((h) reference).f15056a, str);
                arrayList.remove(i7);
                mVar.f15082j = true;
                if (arrayList.isEmpty()) {
                    mVar.f15087q = j3 - this.f15088a;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }
}
