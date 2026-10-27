package k2;

import android.content.Context;
import android.net.Uri;
import c5.C1033j;
import c5.InterfaceC1031h;
import h2.AbstractC1346c;
import h2.InterfaceC1345b;
import h5.C1369A;
import io.sentry.CallableC1462e1;

/* JADX INFO: loaded from: classes.dex */
public final class i implements InterfaceC1345b {
    public static final InterfaceC1031h o = com.bumptech.glide.d.H(new C1033j(1));
    public final h5.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final M.t f17661m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f17662n;

    public i(Context context) {
        h5.z zVar = (h5.z) o.get();
        AbstractC1346c.k(zVar);
        M.t tVar = new M.t(context);
        this.l = zVar;
        this.f17661m = tVar;
        this.f17662n = -1;
    }

    @Override // h2.InterfaceC1345b
    public final h5.y p(byte[] bArr) {
        return ((C1369A) this.l).a(new CallableC1462e1(this, 3, bArr));
    }

    @Override // h2.InterfaceC1345b
    public final h5.y z(Uri uri) {
        return ((C1369A) this.l).a(new CallableC1462e1(this, 4, uri));
    }
}
