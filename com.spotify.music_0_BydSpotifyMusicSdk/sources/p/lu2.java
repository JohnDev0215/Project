package p;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class lu2 {
    public final LinkedHashMap a = new LinkedHashMap();

    public final void a() {
        for (iu2 iu2Var : this.a.values()) {
            HashMap map = iu2Var.a;
            if (map != null) {
                synchronized (map) {
                    try {
                        for (Object obj : iu2Var.a.values()) {
                            if (obj instanceof Closeable) {
                                try {
                                    ((Closeable) obj).close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
            LinkedHashSet linkedHashSet = iu2Var.b;
            if (linkedHashSet != null) {
                synchronized (linkedHashSet) {
                    try {
                        for (Closeable closeable : iu2Var.b) {
                            if (closeable instanceof Closeable) {
                                try {
                                    closeable.close();
                                } catch (IOException e2) {
                                    throw new RuntimeException(e2);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
            iu2Var.a();
        }
        this.a.clear();
    }
}
