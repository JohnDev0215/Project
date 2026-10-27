package p8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: p8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2132g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f20978b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f20979a;

    static {
        new C2132g(0);
    }

    public C2132g() {
        this.f20979a = new HashMap();
    }

    public final void a(C2138m c2138m) {
        this.f20979a.put(new C2131f(c2138m.d.l, c2138m.f20986a), c2138m);
    }

    public C2132g(int i7) {
        this.f20979a = Collections.emptyMap();
    }
}
