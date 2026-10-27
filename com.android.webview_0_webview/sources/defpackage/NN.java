package defpackage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NN extends Mu {
    public final Set e = new HashSet();
    public final Callback f = new Callback(this) { // from class: MN
        public final NN a;

        {
            this.a = this;
        }

        @Override // org.chromium.base.Callback
        public void a(Object obj) {
            this.a.b();
        }
    };

    public NN() {
        super.a(0);
    }

    public final void b() {
        Iterator it = this.e.iterator();
        int iMax = 0;
        while (it.hasNext()) {
            Object obj = ((Mu) ((Ku) it.next())).c;
            iMax = Math.max(iMax, obj == null ? 0 : ((Integer) obj).intValue());
        }
        super.a(Integer.valueOf(iMax));
    }
}
