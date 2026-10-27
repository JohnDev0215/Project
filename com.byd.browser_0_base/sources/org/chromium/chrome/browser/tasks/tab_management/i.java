package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.content.Intent;
import defpackage.k42;
import defpackage.oj2;
import defpackage.u32;
import defpackage.xl2;
import defpackage.y32;
import defpackage.z32;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.price_tracking.PriceDropNotificationManagerImpl;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.b0;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.g;
import org.chromium.chrome.browser.tasks.tab_management.i;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.tasks.tab_management.u0;

/* JADX INFO: compiled from: PriceMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i extends g {
    public static final int k;
    public final c g;
    public final d h;
    public final u32 i;
    public b j;

    /* JADX INFO: compiled from: PriceMessageService.java */
    public class a implements g.a {
        public final int a;
        public final xl2.c b;
        public final MessageCardView.c c;
        public final MessageCardView.a d;

        public a(int i, b bVar, y32 y32Var, z32 z32Var) {
            this.a = i;
            this.b = bVar == null ? null : bVar.b;
            this.c = y32Var;
            this.d = z32Var;
        }
    }

    /* JADX INFO: compiled from: PriceMessageService.java */
    public static class b {
        public final int a;
        public final xl2.c b;

        public b(int i, xl2.c cVar) {
            this.a = i;
            this.b = cVar;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && this.b.equals(bVar.b);
        }

        public final int hashCode() {
            int i = (527 + this.a) * 31;
            xl2.c cVar = this.b;
            return i + (cVar == null ? 0 : cVar.hashCode());
        }
    }

    /* JADX INFO: compiled from: PriceMessageService.java */
    public interface c {
    }

    /* JADX INFO: compiled from: PriceMessageService.java */
    public interface d {
    }

    static {
        k = oj2.H() ? 2 : 1;
    }

    public i(c0 c0Var, s1 s1Var, PriceDropNotificationManagerImpl priceDropNotificationManagerImpl) {
        super(3);
        this.j = null;
        this.g = c0Var;
        this.h = s1Var;
        this.i = priceDropNotificationManagerImpl;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [y32] */
    /* JADX WARN: Type inference failed for: r2v2, types: [z32] */
    public final boolean i(final int i, b bVar) {
        int i2 = k;
        if (i == 0) {
            SharedPreferencesManager sharedPreferencesManager = k42.a;
            sharedPreferencesManager.m(sharedPreferencesManager.d(0, "Chrome.PriceTracking.PriceWelcomeShowCount") + 1, "Chrome.PriceTracking.PriceWelcomeShowCount");
            if (sharedPreferencesManager.d(0, "Chrome.PriceTracking.PriceWelcomeShowCount") > i2 * 10) {
                g.d(3, "PriceWelcomeMessageCard");
                sharedPreferencesManager.l("Chrome.PriceTracking.PriceWelcome", false);
                return false;
            }
            if (k42.a()) {
                sharedPreferencesManager.m(sharedPreferencesManager.d(0, "Chrome.PriceTracking.PriceAlertsShowCount") - 1, "Chrome.PriceTracking.PriceAlertsShowCount");
            }
        } else if (i == 1) {
            SharedPreferencesManager sharedPreferencesManager2 = k42.a;
            sharedPreferencesManager2.m(sharedPreferencesManager2.d(0, "Chrome.PriceTracking.PriceAlertsShowCount") + 1, "Chrome.PriceTracking.PriceAlertsShowCount");
            if (sharedPreferencesManager2.d(0, "Chrome.PriceTracking.PriceAlertsShowCount") > i2 * 10) {
                g.d(3, "PriceAlertsMessageCard");
                sharedPreferencesManager2.l("Chrome.PriceTracking.PriceAlerts", false);
                return false;
            }
        }
        this.j = null;
        g();
        this.j = bVar;
        e(new a(i, bVar, new MessageCardView.c() { // from class: y32
            @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.c
            public final void a() {
                i iVar = this.a;
                int i3 = i;
                if (i3 == 0) {
                    int i4 = iVar.j.a;
                    c0 c0Var = (c0) iVar.g;
                    int iF = c0Var.k.F(i4);
                    s1 s1Var = (s1) iVar.h;
                    s1Var.getClass();
                    s1Var.i.p(b0.d, Integer.valueOf(iF));
                    c0Var.k.get(iF).b.l(u0.B, true);
                    g.d(1, "PriceWelcomeMessageCard");
                    k42.a.l("Chrome.PriceTracking.PriceWelcome", false);
                    iVar.j = null;
                    n3.X0("Commerce.PriceWelcomeMessageCard.Reviewed");
                    return;
                }
                if (i3 != 1) {
                    iVar.getClass();
                    return;
                }
                PriceDropNotificationManagerImpl priceDropNotificationManagerImpl = (PriceDropNotificationManagerImpl) iVar.i;
                if (priceDropNotificationManagerImpl.a()) {
                    priceDropNotificationManagerImpl.b();
                } else {
                    priceDropNotificationManagerImpl.b();
                    Intent intent = new Intent();
                    boolean zA = priceDropNotificationManagerImpl.a();
                    Context context = priceDropNotificationManagerImpl.a;
                    if (zA) {
                        intent.setAction("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
                        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
                        intent.putExtra("android.provider.extra.CHANNEL_ID", "shopping_price_drop_alerts_default");
                    } else {
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
                    }
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                    k42.a.l("Chrome.PriceTracking.PriceAlerts", false);
                }
                g.d(1, "PriceAlertsMessageCard");
                k42.a.l("Chrome.PriceTracking.PriceAlerts", false);
                n3.X0("Commerce.PriceAlertsMessageCard.Reviewed");
            }
        }, new MessageCardView.a() { // from class: z32
            @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.a
            public final void a(int i3) {
                i iVar = this.a;
                int i4 = i;
                if (i4 == 0) {
                    iVar.getClass();
                    g.d(2, "PriceWelcomeMessageCard");
                    k42.a.l("Chrome.PriceTracking.PriceWelcome", false);
                    iVar.j = null;
                    n3.X0("Commerce.PriceWelcomeMessageCard.Dismissed");
                    return;
                }
                if (i4 != 1) {
                    iVar.getClass();
                    return;
                }
                iVar.getClass();
                g.d(2, "PriceAlertsMessageCard");
                k42.a.l("Chrome.PriceTracking.PriceAlerts", false);
                n3.X0("Commerce.PriceAlertsMessageCard.Dismissed");
            }
        }));
        return true;
    }
}
