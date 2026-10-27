package org.chromium.content.browser.sms;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.api.Status;
import defpackage.C0221Mg;
import defpackage.C0257Og;
import defpackage.C1316pD;
import defpackage.C1371qD;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SmsVerificationReceiver extends BroadcastReceiver {
    public final long a;
    public C1371qD c;
    public boolean b = false;
    public C1316pD d = new C1316pD(Is.a);

    public SmsVerificationReceiver(long j) {
        this.a = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
        this.d.registerReceiver(this, intentFilter);
    }

    public static SmsVerificationReceiver create(long j) {
        return new SmsVerificationReceiver(j);
    }

    private void destroy() {
        this.b = true;
        this.d.unregisterReceiver(this);
    }

    private void listen() {
        C1371qD c1371qD = this.c;
        if (c1371qD == null) {
            c1371qD = new C1371qD(new C0221Mg(this.d));
            this.c = c1371qD;
        }
        ((C0221Mg) c1371qD.a).c(new C0257Og());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.b || !"com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(intent.getAction()) || intent.getExtras() == null) {
            return;
        }
        try {
            int i = ((Status) intent.getParcelableExtra("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).t;
            if (i == 0) {
                N.MMIjHDTs(this.a, intent.getExtras().getString("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE"));
            } else {
                if (i != 15) {
                    return;
                }
                N.MDhulaC0(this.a);
            }
        } catch (Throwable unused) {
        }
    }
}
