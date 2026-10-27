package defpackage;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import defpackage.sf3;

/* JADX INFO: compiled from: TrustedWebActivityClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class nf3 implements sf3.a {
    public final /* synthetic */ String e;
    public final /* synthetic */ sf3.b f;
    public final /* synthetic */ sf3 g;

    public nf3(sf3 sf3Var, String str, cp1 cp1Var) {
        this.g = sf3Var;
        this.e = str;
        this.f = cp1Var;
    }

    @Override // sf3.a
    public final void c() {
        this.f.c();
    }

    @Override // sf3.a
    public final void f(ft1 ft1Var, final vf3 vf3Var) {
        Bundle bundle = new Bundle();
        bundle.putString("notificationChannelName", this.e);
        Bundle bundleA = vf3Var.a("getNotificationPermissionRequestPendingIntent", bundle, null);
        boolean z = bundleA == null ? false : bundleA.getBoolean("success");
        PendingIntent pendingIntent = z ? (PendingIntent) bundleA.getParcelable("notificationPermissionRequestPendingIntent") : null;
        jg3 jg3Var = this.g.c;
        boolean z2 = z && pendingIntent != null;
        jg3Var.getClass();
        oj2.O("TrustedWebActivity.ExtraCommandSuccess.".concat("getNotificationPermissionRequestPendingIntent"), z2);
        final sf3.b bVar = this.f;
        if (!z || pendingIntent == null) {
            bVar.a(vf3Var.a.b, 2);
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: mf3
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2 = message.getData().getInt("permissionStatus", 1);
                if (i2 == 0) {
                    i = 1;
                } else {
                    i = 2;
                    if (i2 == 2) {
                        i = 3;
                    }
                }
                bVar.a(((vf3) vf3Var).a.b, i);
                return true;
            }
        });
        Intent intent = new Intent();
        intent.putExtra("messenger", new Messenger(handler));
        try {
            pendingIntent.send(xz.a, 0, intent);
        } catch (PendingIntent.CanceledException e) {
            Log.e("cr_TWAClient", "The PendingIntent was canceled.", e);
        }
    }
}
