package p;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class oy0 {
    public boolean b;
    public final Object a = new Object();
    public final ArrayList c = new ArrayList();

    public oy0(vv vvVar, ov ovVar) {
    }

    public final void a() {
        synchronized (this.a) {
            try {
                this.b = true;
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    ((py0) it.next()).invoke();
                }
                this.c.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
