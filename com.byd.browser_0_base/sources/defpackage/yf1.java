package defpackage;

import J.N;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.webkit.URLUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.merchant_viewer.MerchantTrustSignalsEvent;
import org.chromium.chrome.browser.merchant_viewer.MerchantTrustSignalsEventStorage;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.components.commerce.core.ShoppingService;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class yf1 implements Callback {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;
    public final /* synthetic */ Object j;

    public /* synthetic */ yf1(zf1 zf1Var, ShoppingService.MerchantInfo merchantInfo, rf1 rf1Var, boolean z, MerchantTrustSignalsEventStorage merchantTrustSignalsEventStorage) {
        this.g = zf1Var;
        this.h = merchantInfo;
        this.i = rf1Var;
        this.f = z;
        this.j = merchantTrustSignalsEventStorage;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        boolean z = this.f;
        Object obj2 = this.j;
        Object obj3 = this.i;
        Object obj4 = this.h;
        Object obj5 = this.g;
        switch (i) {
            case 0:
                zf1 zf1Var = (zf1) obj5;
                ShoppingService.MerchantInfo merchantInfo = (ShoppingService.MerchantInfo) obj4;
                rf1 rf1Var = (rf1) obj3;
                MerchantTrustSignalsEventStorage merchantTrustSignalsEventStorage = (MerchantTrustSignalsEventStorage) obj2;
                MerchantTrustSignalsEvent merchantTrustSignalsEvent = (MerchantTrustSignalsEvent) obj;
                if (merchantTrustSignalsEvent != null) {
                    zf1Var.getClass();
                    long jCurrentTimeMillis = System.currentTimeMillis() - merchantTrustSignalsEvent.b;
                    int millis = (int) TimeUnit.DAYS.toMillis(365L);
                    if (a8.P()) {
                        sk skVar = bq.a;
                        millis = N.M37SqSAy("CommerceMerchantViewer", "trust_signals_message_window_duration_ms", millis);
                    }
                    if (jCurrentTimeMillis > millis) {
                        N.MlUFx9GQ(merchantTrustSignalsEventStorage.a, merchantTrustSignalsEvent.a, null);
                        zf1Var.d(rf1Var, merchantInfo, z);
                    }
                } else {
                    zf1Var.d(rf1Var, merchantInfo, z);
                }
                break;
            default:
                zw2 zw2Var = (zw2) obj5;
                List list = (List) obj4;
                List list2 = (List) obj3;
                s62 s62Var = (s62) obj2;
                zw2Var.getClass();
                if (!((Boolean) obj).booleanValue()) {
                    s62Var.e(null);
                } else {
                    if (z) {
                        list.addAll(list2);
                    } else {
                        list.removeAll(list2);
                    }
                    hp1 hp1Var = zw2Var.b;
                    hp1Var.getClass();
                    if (!list2.isEmpty()) {
                        int i2 = 0;
                        if (z) {
                            ArrayList arrayList = new ArrayList();
                            StatusBarNotification[] activeNotifications = hp1Var.c.getActiveNotifications();
                            int length = activeNotifications.length;
                            while (i2 < length) {
                                StatusBarNotification statusBarNotification = activeNotifications[i2];
                                if (statusBarNotification.getId() == -1) {
                                    String tag = statusBarNotification.getTag();
                                    String strC = NotificationPlatformBridge.c(tag);
                                    if ((URLUtil.isHttpUrl(strC) || URLUtil.isHttpsUrl(strC)) && list2.contains(Uri.parse(strC).getHost())) {
                                        arrayList.add(new lp1(statusBarNotification.getNotification(), new o8(7, -1, tag)));
                                    }
                                }
                                i2++;
                            }
                            hp1Var.a(arrayList);
                        } else if (!list2.isEmpty()) {
                            String[] strArr = new String[list2.size() * 2];
                            while (i2 < list2.size()) {
                                int i3 = i2 * 2;
                                strArr[i3 + 0] = "http://" + ((String) list2.get(i2));
                                strArr[i3 + 1] = "https://" + ((String) list2.get(i2));
                                i2++;
                            }
                            N.MkLq9R0C(hp1Var.a, strArr);
                        }
                    }
                    s62Var.b(null);
                }
                break;
        }
    }

    public /* synthetic */ yf1(List list, List list2, s62 s62Var, zw2 zw2Var, boolean z) {
        this.g = zw2Var;
        this.f = z;
        this.h = list;
        this.i = list2;
        this.j = s62Var;
    }
}
