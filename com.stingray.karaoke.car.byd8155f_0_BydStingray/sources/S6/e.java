package S6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f8072a;

    public e(Map map) {
        this.f8072a = map;
    }

    public e(c cVar) {
        this.f8072a = Collections.unmodifiableMap(new HashMap(cVar.f8070a));
    }
}
