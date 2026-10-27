package k4;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class D implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f18179b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f18180a;

    public D(q qVar) {
        this.f18180a = qVar;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        return this.f18180a.a(new g(((Uri) obj).toString()), i7, i10, hVar);
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        return f18179b.contains(((Uri) obj).getScheme());
    }
}
