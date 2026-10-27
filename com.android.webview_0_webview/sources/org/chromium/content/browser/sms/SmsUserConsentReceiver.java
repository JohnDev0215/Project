package org.chromium.content.browser.sms;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import defpackage.AbstractC0311Rg;
import defpackage.C0204Lg;
import defpackage.C0221Mg;
import defpackage.C1169md;
import defpackage.C1280oe;
import defpackage.C1316pD;
import defpackage.C1371qD;
import defpackage.Cif;
import defpackage.InterfaceC0492aO;
import defpackage.Is;
import java.util.Objects;
import org.chromium.content.browser.sms.SmsUserConsentReceiver;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SmsUserConsentReceiver extends BroadcastReceiver {
    public final long a;
    public C1371qD c;
    public WindowAndroid e;
    public boolean b = false;
    public C1316pD d = new C1316pD(Is.a);

    public SmsUserConsentReceiver(long j) {
        this.a = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
        this.d.registerReceiver(this, intentFilter);
    }

    public static SmsUserConsentReceiver create(long j) {
        return new SmsUserConsentReceiver(j);
    }

    private void destroy() {
        this.b = true;
        this.d.unregisterReceiver(this);
    }

    private void listen(WindowAndroid windowAndroid) {
        this.e = windowAndroid;
        C1371qD c1371qD = this.c;
        if (c1371qD == null) {
            c1371qD = new C1371qD(new C0221Mg(this.d));
            this.c = c1371qD;
        }
        C0221Mg c0221Mg = (C0221Mg) c1371qD.a;
        Objects.requireNonNull(c0221Mg);
        C1169md c1169md = new C1169md(null);
        c1169md.a = new C0204Lg(c0221Mg, null);
        c1169md.c = new Feature[]{AbstractC0311Rg.b};
        Cif.b(true, "execute parameter required");
        c0221Mg.c(new C1280oe(c1169md, c1169md.c, true));
    }

    public final void a(int i, Intent intent) {
        if (i == -1) {
            N.MZyp7uap(this.a, intent.getStringExtra("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE"));
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.b || !"com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(intent.getAction()) || intent.getExtras() == null) {
            return;
        }
        try {
            int i = ((Status) intent.getParcelableExtra("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).t;
            if (i == 0) {
                this.e.Y((Intent) intent.getExtras().getParcelable("com.google.android.gms.auth.api.phone.EXTRA_CONSENT_INTENT"), new InterfaceC0492aO(this) { // from class: oD
                    public final SmsUserConsentReceiver a;

                    {
                        this.a = this;
                    }

                    @Override // defpackage.InterfaceC0492aO
                    public void a(WindowAndroid windowAndroid, int i2, Intent intent2) {
                        this.a.a(i2, intent2);
                    }
                }, null);
            } else {
                if (i != 15) {
                    return;
                }
                N.MgxlMMg$(this.a);
            }
        } catch (ActivityNotFoundException unused) {
        }
    }
}
