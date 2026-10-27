package defpackage;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: Lk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0208Lk {
    public final ConcurrentHashMap a = new ConcurrentHashMap(16, 0.75f, 10);
    public final ReferenceQueue b = new ReferenceQueue();

    public List a(Throwable th, boolean z) {
        while (true) {
            Reference referencePoll = this.b.poll();
            if (referencePoll == null) {
                break;
            }
            this.a.remove(referencePoll);
        }
        List list = (List) this.a.get(new C0190Kk(th, null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List list2 = (List) this.a.putIfAbsent(new C0190Kk(th, this.b), vector);
        return list2 == null ? vector : list2;
    }
}
