package defpackage;

import android.accounts.AccountManagerCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BK extends BroadcastReceiver {
    public final /* synthetic */ Context a;
    public final /* synthetic */ CK b;

    public BK(CK ck, Context context) {
        this.b = ck;
        this.a = context;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.unregisterReceiver(this);
        CK ck = this.b;
        DK dk = ck.a;
        dk.b.getAuthToken(dk.e, dk.d, dk.c, true, (AccountManagerCallback<Bundle>) new CK(ck.b, dk), (Handler) null);
    }
}
