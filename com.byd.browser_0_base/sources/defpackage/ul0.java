package defpackage;

import defpackage.sw2;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: FeedSurfaceTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class ul0 implements sw2.a {
    public static ul0 d;
    public boolean a;
    public final b<a> b = new b<>();
    public HashSet<sw2> c;

    /* JADX INFO: compiled from: FeedSurfaceTracker.java */
    public interface a {
        void a();
    }

    public static ul0 b() {
        if (d == null) {
            d = new ul0();
        }
        return d;
    }

    @Override // sw2.a
    public final void a() {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }
}
