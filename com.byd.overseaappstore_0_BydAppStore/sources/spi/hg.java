package spi;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class hg {
    public final Map<ig, j8<?>> a = new HashMap();
    public final Map<ig, j8<?>> b = new HashMap();

    public final Map<ig, j8<?>> a(boolean z) {
        return z ? this.b : this.a;
    }
}
