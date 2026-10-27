package defpackage;

import java.lang.ref.Reference;
import java.util.List;
import java.util.Vector;

/* JADX INFO: renamed from: mi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1174mi extends AbstractC1119li {
    public final C1063ki a = new C1063ki();

    @Override // defpackage.AbstractC1119li
    public final void a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        C1063ki c1063ki = this.a;
        for (Reference referencePoll = c1063ki.b.poll(); referencePoll != null; referencePoll = c1063ki.b.poll()) {
            c1063ki.a.remove(referencePoll);
        }
        List vector = (List) c1063ki.a.get(new C1229ni(th, null));
        if (vector == null) {
            vector = new Vector(2);
            List list = (List) c1063ki.a.putIfAbsent(new C1229ni(th, c1063ki.b), vector);
            if (list != null) {
                vector = list;
            }
        }
        vector.add(th2);
    }
}
