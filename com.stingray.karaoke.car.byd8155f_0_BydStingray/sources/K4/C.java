package k4;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import z4.C2768d;

/* JADX INFO: loaded from: classes.dex */
public final class C implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f18177b = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "content", "android.resource")));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f18178a;

    public C(B b10) {
        this.f18178a = b10;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        com.bumptech.glide.load.data.e aVar;
        Uri uri = (Uri) obj;
        C2768d c2768d = new C2768d(uri);
        B b10 = (B) this.f18178a;
        switch (b10.l) {
            case 0:
                aVar = new com.bumptech.glide.load.data.a(b10.f18176m, uri, 0);
                break;
            case 1:
                aVar = new com.bumptech.glide.load.data.a(b10.f18176m, uri, 1);
                break;
            default:
                aVar = new com.bumptech.glide.load.data.n(1, uri, b10.f18176m);
                break;
        }
        return new p(c2768d, aVar);
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        return f18177b.contains(((Uri) obj).getScheme());
    }
}
