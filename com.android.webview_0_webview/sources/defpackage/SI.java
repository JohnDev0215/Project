package defpackage;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SI implements LI {
    public final Set s = Collections.newSetFromMap(new WeakHashMap());

    @Override // defpackage.LI
    public void I(WJ wj) {
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            ((LI) it.next()).I(wj);
        }
    }
}
