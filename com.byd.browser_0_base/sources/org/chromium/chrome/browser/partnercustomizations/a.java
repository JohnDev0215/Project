package org.chromium.chrome.browser.partnercustomizations;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import defpackage.bq;
import defpackage.i9;
import defpackage.o50;
import defpackage.oj2;
import defpackage.qv0;
import defpackage.sk;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* JADX INFO: compiled from: PartnerBrowserCustomizations.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends i9<Void> {
    public boolean h;
    public long i;
    public final /* synthetic */ Context j;
    public final /* synthetic */ PartnerBrowserCustomizations k;

    public a(Context context, PartnerBrowserCustomizations partnerBrowserCustomizations) {
        this.k = partnerBrowserCustomizations;
        this.j = context;
    }

    @Override // defpackage.i9
    public final Void b() {
        try {
            this.i = SystemClock.elapsedRealtime();
            if (((this.j.getApplicationInfo().flags & 1) == 1) && !i()) {
                o50 o50Var = new o50();
                sk skVar = bq.N;
                boolean zA = skVar.a();
                PartnerBrowserCustomizations partnerBrowserCustomizations = this.k;
                if (zA) {
                    if (i()) {
                        return null;
                    }
                    this.h = partnerBrowserCustomizations.f(o50Var);
                }
                if (i()) {
                    return null;
                }
                partnerBrowserCustomizations.g(o50Var);
                if (i()) {
                    return null;
                }
                partnerBrowserCustomizations.e(o50Var);
                if (!skVar.a() && !i()) {
                    this.h = partnerBrowserCustomizations.f(o50Var);
                    return null;
                }
                return null;
            }
            return null;
        } catch (Exception e) {
            Log.w("cr_PartnerCustomize", "Fetching partner customizations failed", e);
            return null;
        }
    }

    @Override // defpackage.i9
    public final void k(Void r1) {
        o();
    }

    @Override // defpackage.i9
    public final void l(Void r1) {
        o();
    }

    public final void o() {
        PartnerBrowserCustomizations.a aVar;
        PartnerBrowserCustomizations partnerBrowserCustomizations = this.k;
        boolean z = !partnerBrowserCustomizations.d;
        partnerBrowserCustomizations.d = true;
        if (z) {
            oj2.b0(SystemClock.elapsedRealtime() - this.i, "Android.PartnerBrowserCustomizationInitDuration");
        }
        ArrayList arrayList = partnerBrowserCustomizations.e;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        arrayList.clear();
        if (this.h && (aVar = partnerBrowserCustomizations.f) != null) {
            ((qv0) aVar).f();
        }
        if (z) {
            oj2.b0(SystemClock.elapsedRealtime() - this.i, "Android.PartnerBrowserCustomizationInitDuration.WithCallbacks");
        }
    }
}
