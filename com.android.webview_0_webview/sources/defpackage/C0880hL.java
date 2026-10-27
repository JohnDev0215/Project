package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: renamed from: hL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0880hL extends BroadcastReceiver {
    public final /* synthetic */ AbstractC0936iL a;

    public C0880hL(AbstractC0936iL abstractC0936iL) {
        this.a = abstractC0936iL;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.b();
    }
}
