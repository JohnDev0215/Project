package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: Ee, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0076Ee implements InterfaceC0780fe {
    public final /* synthetic */ C0040Ce a;

    public C0076Ee(C0040Ce c0040Ce, AbstractRunnableC0022Be abstractRunnableC0022Be) {
        this.a = c0040Ce;
    }

    @Override // defpackage.InterfaceC0780fe
    public final void a(ConnectionResult connectionResult) {
        this.a.l.lock();
        try {
            this.a.i = connectionResult;
            C0040Ce.d(this.a);
        } finally {
            this.a.l.unlock();
        }
    }

    @Override // defpackage.InterfaceC0780fe
    public final void b(int i, boolean z) {
        this.a.l.lock();
        try {
            if (!this.a.k && this.a.j != null && this.a.j.f()) {
                this.a.k = true;
                this.a.d.d(i);
                return;
            }
            this.a.k = false;
            C0040Ce c0040Ce = this.a;
            c0040Ce.b.b(i, z);
            c0040Ce.j = null;
            c0040Ce.i = null;
        } finally {
            this.a.l.unlock();
        }
    }

    @Override // defpackage.InterfaceC0780fe
    public final void c(Bundle bundle) {
        this.a.l.lock();
        try {
            C0040Ce c0040Ce = this.a;
            Bundle bundle2 = c0040Ce.h;
            if (bundle2 == null) {
                c0040Ce.h = bundle;
            } else if (bundle != null) {
                bundle2.putAll(bundle);
            }
            this.a.i = ConnectionResult.w;
            C0040Ce.d(this.a);
        } finally {
            this.a.l.unlock();
        }
    }
}
