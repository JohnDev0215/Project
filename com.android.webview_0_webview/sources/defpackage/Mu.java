package defpackage;

import android.os.Handler;
import java.util.Iterator;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Mu implements Ku {
    public Object c;
    public final Thread a = Thread.currentThread();
    public final Handler b = new Handler();
    public final Ys d = new Ys();

    public void a(Object obj) {
        if (obj == this.c) {
            return;
        }
        this.c = obj;
        Iterator it = this.d.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Callback) ws.next()).a(this.c);
            }
        }
    }

    @Override // defpackage.Nu
    public Object get() {
        return this.c;
    }
}
