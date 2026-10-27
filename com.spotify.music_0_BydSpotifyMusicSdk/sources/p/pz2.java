package p;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class pz2 {
    public final HashMap a = new HashMap();
    public final AtomicBoolean b = new AtomicBoolean(false);

    public final synchronized boolean a() {
        if (!this.b.get()) {
            synchronized (this) {
                this.a.put("assetOnlyUpdates", Boolean.FALSE);
            }
        }
        Object obj = this.a.get("assetOnlyUpdates");
        if (!(obj instanceof Boolean)) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }
}
