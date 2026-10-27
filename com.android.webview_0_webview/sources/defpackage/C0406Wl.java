package defpackage;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: Wl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0406Wl implements InterfaceC0388Vl {
    public int a(int i, Object obj, Object obj2) {
        C0370Ul c0370Ul = (C0370Ul) obj;
        if (c0370Ul.isEmpty()) {
            return 0;
        }
        Iterator it = c0370Ul.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public Object b(Object obj, Object obj2) {
        C0370Ul c0370Ul = (C0370Ul) obj;
        C0370Ul c0370Ul2 = (C0370Ul) obj2;
        if (!c0370Ul2.isEmpty()) {
            if (!c0370Ul.s) {
                c0370Ul = c0370Ul.isEmpty() ? new C0370Ul() : new C0370Ul(c0370Ul);
            }
            c0370Ul.a();
            if (!c0370Ul2.isEmpty()) {
                c0370Ul.putAll(c0370Ul2);
            }
        }
        return c0370Ul;
    }

    public Object c(Object obj) {
        C0370Ul c0370Ul = C0370Ul.t;
        return c0370Ul.isEmpty() ? new C0370Ul() : new C0370Ul(c0370Ul);
    }
}
