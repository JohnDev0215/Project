package defpackage;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Le, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0202Le implements InterfaceC0184Ke {
    public final /* synthetic */ BaseGmsClient a;

    public C0202Le(BaseGmsClient baseGmsClient) {
        this.a = baseGmsClient;
    }

    @Override // defpackage.InterfaceC0184Ke
    public void a(ConnectionResult connectionResult) {
        if (connectionResult.f()) {
            BaseGmsClient baseGmsClient = this.a;
            baseGmsClient.h(null, ((AbstractC0399We) baseGmsClient).A);
        } else {
            InterfaceC0166Je interfaceC0166Je = this.a.s;
            if (interfaceC0166Je != null) {
                ((C1720wf) interfaceC0166Je).a.i(connectionResult);
            }
        }
    }
}
