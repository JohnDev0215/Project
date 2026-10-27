package defpackage;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Me, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0219Me extends AbstractC0255Oe {
    public final int d;
    public final Bundle e;
    public final /* synthetic */ BaseGmsClient f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0219Me(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
        super(baseGmsClient, Boolean.TRUE);
        this.f = baseGmsClient;
        this.d = i;
        this.e = bundle;
    }

    @Override // defpackage.AbstractC0255Oe
    public final void b(Object obj) {
        int i = this.d;
        if (i == 0) {
            if (c()) {
                return;
            }
            this.f.q(1, null);
            d(new ConnectionResult(8, null));
            return;
        }
        if (i == 10) {
            this.f.q(1, null);
            throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", getClass().getSimpleName(), this.f.k(), this.f.j()));
        }
        this.f.q(1, null);
        Bundle bundle = this.e;
        d(new ConnectionResult(this.d, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null));
    }

    public abstract boolean c();

    public abstract void d(ConnectionResult connectionResult);
}
