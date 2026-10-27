package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: PopupController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class v22 implements lk3 {
    public final ArrayList e = new ArrayList();

    /* JADX INFO: compiled from: PopupController.java */
    public interface a {
        void b();
    }

    /* JADX INFO: compiled from: PopupController.java */
    public static final class b {
        public static final wd0 a = new wd0(25);
    }

    public final void a() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((a) it.next()).b();
        }
    }
}
