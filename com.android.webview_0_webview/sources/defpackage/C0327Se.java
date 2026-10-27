package defpackage;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Objects;

/* JADX INFO: renamed from: Se, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0327Se extends AbstractC0219Me {
    public final /* synthetic */ BaseGmsClient g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0327Se(BaseGmsClient baseGmsClient, int i) {
        super(baseGmsClient, i, null);
        this.g = baseGmsClient;
    }

    @Override // defpackage.AbstractC0219Me
    public final boolean c() {
        this.g.m.a(ConnectionResult.w);
        return true;
    }

    @Override // defpackage.AbstractC0219Me
    public final void d(ConnectionResult connectionResult) {
        Objects.requireNonNull(this.g);
        this.g.m.a(connectionResult);
        this.g.n(connectionResult);
    }
}
