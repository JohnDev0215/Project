package defpackage;

import java.util.LinkedList;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: DeferredStartupHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class g80 {
    public static g80 b;
    public final LinkedList a = new LinkedList();

    public static g80 b() {
        Object obj = ThreadUtils.a;
        if (b == null) {
            b = new g80();
        }
        return b;
    }

    public final void a(Runnable runnable) {
        Object obj = ThreadUtils.a;
        this.a.add(runnable);
    }
}
