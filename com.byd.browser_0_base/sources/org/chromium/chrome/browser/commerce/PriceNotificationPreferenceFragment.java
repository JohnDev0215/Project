package org.chromium.chrome.browser.commerce;

import J.N;
import android.app.NotificationChannel;
import android.os.Bundle;
import androidx.preference.d;
import defpackage.gx;
import defpackage.i62;
import defpackage.iq2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.qj2;
import defpackage.so;
import defpackage.un1;
import defpackage.xz;
import defpackage.z7;
import org.chromium.chrome.browser.commerce.PriceNotificationPreferenceFragment;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.TextMessagePreference;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: loaded from: classes.dex */
public class PriceNotificationPreferenceFragment extends d implements i62 {
    public static final /* synthetic */ int n0 = 0;
    public final PrefChangeRegistrar h0 = new PrefChangeRegistrar();
    public PrefService i0;
    public Profile j0;
    public TextMessagePreference k0;
    public ChromeSwitchPreference l0;
    public NotificationManagerProxyImpl m0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        this.i0 = oj2.k(this.j0);
        this.m0 = new NotificationManagerProxyImpl(xz.a);
        qj2.a(this, R$xml.price_notification_preferences);
        D().setTitle(R$string.price_notifications_settings_detailed_page_title);
        this.k0 = (TextMessagePreference) f("mobile_notifications_text");
        M0();
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("send_email_switch");
        this.l0 = chromeSwitchPreference;
        chromeSwitchPreference.setOnPreferenceChangeListener(new gx(7, this));
        z7 z7VarM = z7.m();
        Profile profile = this.j0;
        z7VarM.getClass();
        this.l0.R(P(R$string.price_notifications_settings_email_description, z7.v(profile).a(1).getEmail()));
        this.h0.a("price_tracking.email_notifications_enabled", new PrefChangeRegistrar.a() { // from class: a42
            @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
            public final void f() {
                int i = PriceNotificationPreferenceFragment.n0;
                PriceNotificationPreferenceFragment priceNotificationPreferenceFragment = this.e;
                boolean zA = priceNotificationPreferenceFragment.i0.a("price_tracking.email_notifications_enabled");
                ChromeSwitchPreference chromeSwitchPreference2 = priceNotificationPreferenceFragment.l0;
                if (chromeSwitchPreference2.S != zA) {
                    chromeSwitchPreference2.Y(zA);
                }
            }
        });
        boolean zA = this.i0.a("price_tracking.email_notifications_enabled");
        ChromeSwitchPreference chromeSwitchPreference2 = this.l0;
        if (chromeSwitchPreference2.S != zA) {
            chromeSwitchPreference2.Y(zA);
        }
    }

    public final void M0() {
        if (this.k0 == null) {
            return;
        }
        String strO = O(R$string.chrome_notification_settings_for_price_tracking);
        NotificationChannel notificationChannelG = this.m0.g("shopping_price_drop_alerts_default");
        String strP = this.m0.f() && notificationChannelG != null && notificationChannelG.getImportance() != 0 ? P(R$string.price_notifications_settings_mobile_description_on, strO) : P(R$string.price_notifications_settings_mobile_description_off, strO);
        iq2.a aVar = new iq2.a(new un1(K(), new so(14, this)), "<link>", "</link>");
        iq2.a(strP, aVar);
        this.k0.R(iq2.a(strP, aVar));
    }

    @Override // defpackage.i62
    public final void m(Profile profile) {
        this.j0 = profile;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void o0() {
        super.o0();
        M0();
        ShoppingService shoppingServiceY = n3.Y(this.j0);
        long j = shoppingServiceY.a;
        if (j == 0) {
            return;
        }
        N.M7Fzuv$w(j, shoppingServiceY);
    }
}
