package E3;

import android.net.Uri;
import m7.n;

/* JADX INFO: loaded from: classes.dex */
public final class i implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f1729a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f1730b;
    public final boolean c;

    public i(n nVar, n nVar2, boolean z6) {
        this.f1729a = nVar;
        this.f1730b = nVar2;
        this.c = z6;
    }

    @Override // E3.f
    public final g a(Object obj, K3.m mVar) {
        Uri uri = (Uri) obj;
        if (A7.m.a(uri.getScheme(), "http") || A7.m.a(uri.getScheme(), "https")) {
            return new l(uri.toString(), mVar, this.f1729a, this.f1730b, this.c);
        }
        return null;
    }
}
