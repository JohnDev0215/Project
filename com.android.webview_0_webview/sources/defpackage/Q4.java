package defpackage;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Q4 {
    public final HashMap a = new HashMap();

    public final void a() {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            ((O4) it.next()).a();
        }
        this.a.clear();
    }
}
