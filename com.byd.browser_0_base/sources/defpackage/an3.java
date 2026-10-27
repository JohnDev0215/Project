package defpackage;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: ViewModelStore.kt */
/* JADX INFO: loaded from: classes.dex */
public final class an3 {
    public final LinkedHashMap a = new LinkedHashMap();

    public final void a() {
        for (ym3 ym3Var : this.a.values()) {
            HashMap map = ym3Var.a;
            if (map != null) {
                synchronized (map) {
                    for (Object obj : ym3Var.a.values()) {
                        if (obj instanceof Closeable) {
                            try {
                                ((Closeable) obj).close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            LinkedHashSet linkedHashSet = ym3Var.b;
            if (linkedHashSet != null) {
                synchronized (linkedHashSet) {
                    for (Closeable closeable : ym3Var.b) {
                        if (closeable instanceof Closeable) {
                            try {
                                closeable.close();
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        }
                    }
                }
            }
            ym3Var.a();
        }
        this.a.clear();
    }
}
