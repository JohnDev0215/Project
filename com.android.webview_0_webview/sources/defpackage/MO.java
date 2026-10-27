package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MO {
    public final Object a;
    public final OO b;
    public final LO c;
    public final NO d = new NO(this) { // from class: KO
    };

    public MO(OO oo, Object obj, LO lo, boolean z) {
        this.b = oo;
        this.a = obj;
        this.c = lo;
        if (z) {
            JO jo = (JO) oo;
            Objects.requireNonNull(jo);
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : jo.b.entrySet()) {
                if (entry.getValue() != null) {
                    arrayList.add((InterfaceC1821yO) entry.getKey());
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.c.a(this.b, this.a, it.next());
            }
        }
        oo.a.f(this.d);
    }
}
