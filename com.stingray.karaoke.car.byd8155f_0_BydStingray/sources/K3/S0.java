package k3;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.concurrent.atomic.AtomicReference;
import l3.AbstractServiceC1765v;
import l3.C1755k;
import l3.C1756l;
import l3.C1758n;
import l3.C1759o;

/* JADX INFO: loaded from: classes.dex */
public final class S0 extends AbstractServiceC1765v {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final l3.U f17855u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final I0 f17856v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final B8.t f17857w;

    public S0(I0 i02) {
        this.f17855u = l3.U.a(i02.f17799f);
        this.f17856v = i02;
        this.f17857w = new B8.t(i02);
    }

    @Override // l3.AbstractServiceC1765v
    public final C1755k a(Bundle bundle) {
        l3.T t10;
        C1759o c1759o = this.l;
        c1759o.getClass();
        AbstractServiceC1765v abstractServiceC1765v = c1759o.f18591g;
        C1756l c1756l = abstractServiceC1765v.f18613q;
        if (c1756l == null) {
            throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }
        if (c1756l == abstractServiceC1765v.f18611n) {
            C1758n c1758n = (C1758n) c1759o.f18586b;
            c1758n.getClass();
            t10 = new l3.T(c1758n.getCurrentBrowserInfo());
        } else {
            t10 = c1756l.d;
        }
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        Bundle bundle2 = bundle;
        boolean zB = this.f17855u.b(t10);
        d5.M m10 = AbstractC1663q.f18029a;
        Math.max(0, bundle2.getInt("androidx.media.utils.MediaBrowserCompat.extras.CUSTOM_BROWSER_ACTION_LIMIT", 0));
        C1679y0 c1679y0 = new C1679y0(t10, 0, 0, zB, null, bundle2);
        AtomicReference atomicReference = new AtomicReference();
        B2.P p10 = new B2.P();
        AbstractC1343A.K(this.f17856v.l, new io.sentry.android.replay.p(this, atomicReference, c1679y0, p10, 5));
        try {
            p10.a();
            C1675w0 c1675w0 = (C1675w0) atomicReference.get();
            c1675w0.getClass();
            this.f17857w.v(t10, c1679y0, c1675w0.f18149a, c1675w0.f18150b);
            return n1.f18018a;
        } catch (InterruptedException e10) {
            AbstractC1346c.q("MSSLegacyStub", "Couldn't get a result from onConnect", e10);
            return null;
        }
    }

    public final void b(l3.O o) {
        attachBaseContext(this.f17856v.f17799f);
        onCreate();
        if (o == null) {
            throw new IllegalArgumentException("Session token may not be null");
        }
        if (this.f18615s != null) {
            throw new IllegalStateException("The session token has already been set");
        }
        this.f18615s = o;
        C1759o c1759o = this.l;
        c1759o.getClass();
        ((AbstractServiceC1765v) c1759o.d).f18614r.c(new io.sentry.android.core.M(c1759o, 2, o));
    }
}
