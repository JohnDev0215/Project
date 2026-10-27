package defpackage;

import J.N;
import android.app.NotificationChannel;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.price_tracking.PriceDropNotificationManagerImpl;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: CommerceSubscriptionsService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jv implements hy1 {
    public final /* synthetic */ kv e;

    public jv(kv kvVar) {
        this.e = kvVar;
    }

    @Override // defpackage.hy1
    public final void f() {
        kv kvVar = this.e;
        kvVar.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferencesManager sharedPreferencesManager = kvVar.a;
        long jE = jCurrentTimeMillis - sharedPreferencesManager.e(-1L, "Chrome.CommerceSubscriptions.ChromeManagedTimestamp");
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int seconds = (int) TimeUnit.DAYS.toSeconds(1L);
        if (a8.P()) {
            sk skVar = bq.a;
            seconds = N.M37SqSAy("CommercePriceTracking", "price_tracking_stale_tab_lower_bound_seconds", seconds);
        }
        if (jE < timeUnit.toMillis(seconds)) {
            return;
        }
        sharedPreferencesManager.n(System.currentTimeMillis(), "Chrome.CommerceSubscriptions.ChromeManagedTimestamp");
        if (z7.P()) {
            PriceDropNotificationManagerImpl priceDropNotificationManagerImpl = (PriceDropNotificationManagerImpl) kvVar.b;
            priceDropNotificationManagerImpl.getClass();
            if (z7.P()) {
                boolean zA = priceDropNotificationManagerImpl.a();
                oj2.O("Commerce.PriceDrop.SystemNotificationEnabled", zA);
                if (zA) {
                    NotificationChannel notificationChannelG = priceDropNotificationManagerImpl.b.g("shopping_price_drop_alerts_default");
                    boolean z = notificationChannelG != null;
                    oj2.O("Commerce.PriceDrop.NotificationChannelCreated", z);
                    if (z) {
                        oj2.O("Commerce.PriceDrop.NotificationChannelBlocked", notificationChannelG.getImportance() == 0);
                    }
                }
            }
            oj2.Q(priceDropNotificationManagerImpl.d(32, false), "Commerce.PriceDrops.ChromeManaged.NotificationCount");
            oj2.Q(priceDropNotificationManagerImpl.d(33, false), "Commerce.PriceDrops.UserManaged.NotificationCount");
            ox0 ox0Var = kvVar.c;
            if (ox0Var != null) {
                HashSet hashSet = new HashSet();
                TabModel tabModelG = ox0Var.a.g(false);
                for (int i = 0; i < tabModelG.getCount(); i++) {
                    Tab tabAt = tabModelG.getTabAt(i);
                    int i2 = 4;
                    xl2.o(new t02(new yo1(ox0Var, i2, tabAt, hashSet), i2), tabAt);
                }
            }
        }
    }

    @Override // defpackage.hy1
    public final void h() {
    }
}
