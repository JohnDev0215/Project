package defpackage;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import defpackage.rp3;
import defpackage.sf3;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;

/* JADX INFO: compiled from: NotificationPermissionUpdater.java */
/* JADX INFO: loaded from: classes.dex */
public final class cp1 implements sf3.b {
    public boolean e;
    public final /* synthetic */ ft1 f;
    public final /* synthetic */ long g;
    public final /* synthetic */ String h;
    public final /* synthetic */ dp1 i;

    public cp1(dp1 dp1Var, ft1 ft1Var, long j, String str) {
        this.i = dp1Var;
        this.f = ft1Var;
        this.g = j;
        this.h = str;
    }

    @Override // sf3.b
    public final void a(ComponentName componentName, int i) {
        if (this.e) {
            return;
        }
        this.e = true;
        oj2.U(i, 6, "TrustedWebActivity.Notification.PermissionRequestResult");
        this.i.a(this.f, this.g, componentName.getPackageName(), i);
    }

    @Override // sf3.b
    public final void c() {
        if (this.e) {
            return;
        }
        this.e = true;
        final ft1 ft1Var = this.f;
        final long j = this.g;
        Callback callback = new Callback() { // from class: bp1
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                ft1 ft1Var2 = ft1Var;
                long j2 = j;
                String str = (String) obj;
                dp1 dp1Var = this.e.i;
                dp1Var.getClass();
                if (TextUtils.isEmpty(str)) {
                    dp1Var.a.d(5, ft1Var2);
                    InstalledWebappBridge.b(2, j2);
                    return;
                }
                rp3 rp3VarA = rp3.a();
                final xo1 xo1Var = new xo1(dp1Var, ft1Var2, j2, str);
                rp3VarA.getClass();
                if (!yj.a()) {
                    Log.w("cr_WebApkServiceClient", "Requesting notification permission is not supported before T.");
                    return;
                }
                rp3.a aVar = new rp3.a() { // from class: op3
                    @Override // rp3.a
                    public final void b(pw0 pw0Var) {
                        PendingIntent pendingIntentZ = pw0Var.z(xz.a.getString(R$string.webapk_notification_channel_name));
                        final Callback callback2 = xo1Var;
                        if (pendingIntentZ == null) {
                            callback2.onResult(3);
                            return;
                        }
                        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: pp3
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
                                callback2.onResult(Integer.valueOf(i));
                                return true;
                            }
                        });
                        Intent intent = new Intent();
                        intent.putExtra("messenger", new Messenger(handler));
                        try {
                            pendingIntentZ.send(xz.a, 0, intent);
                        } catch (PendingIntent.CanceledException e) {
                            Log.e("cr_WebApkServiceClient", "The PendingIntent was canceled.", e);
                        }
                    }
                };
                rp3VarA.a.a(xz.a, str, aVar);
            }
        };
        this.i.getClass();
        String strD = fq3.d(xz.a, this.h);
        if (strD == null) {
            callback.onResult(null);
        } else {
            zs.a(strD, new z70(callback, 3, strD));
        }
    }
}
