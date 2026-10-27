package org.chromium.chrome.browser.feature_guide.notifications;

import android.content.Intent;
import android.net.Uri;
import defpackage.gq2;
import defpackage.m70;
import defpackage.n3;
import defpackage.uk0;
import defpackage.xz;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FeatureNotificationGuideBridge extends a {
    @CalledByNative
    public static FeatureNotificationGuideBridge create(long j) {
        return new FeatureNotificationGuideBridge();
    }

    @CalledByNative
    public final void closeNotification(String str) {
        int iHashCode = str.hashCode();
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(xz.a);
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.cancel(id)", null);
        try {
            notificationManagerProxyImpl.a.b.cancel(null, iHashCode);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @CalledByNative
    public final String getNotificationMessage(int i) {
        return xz.a.getResources().getString(i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? 0 : R$string.feature_notification_guide_notification_message_voice_search : R$string.feature_notification_guide_notification_message_ntp_suggestion_card : R$string.feature_notification_guide_notification_message_incognito_tab : R$string.feature_notification_guide_notification_message_sign_in : R$string.feature_notification_guide_notification_message_default_browser);
    }

    @CalledByNative
    public final String getNotificationParamGuidForFeature(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "guid_voice_search" : "guid_ntp_suggestion_card" : "guid_incognito_tab" : "guid_sign_in" : "guid_default_browser";
    }

    @CalledByNative
    public final String getNotificationTitle(int i) {
        return xz.a.getResources().getString(R$string.feature_notification_guide_notification_title);
    }

    @CalledByNative
    public final void onNotificationClick(int i) {
        ((uk0) a.a).getClass();
        Object obj = ThreadUtils.a;
        boolean z = i == 3 || i == 5 || i == 4;
        Intent intent = new Intent(xz.a, (Class<?>) ((i == 1 || i != 2) ? ChromeTabbedActivity.class : SettingsActivity.class));
        if (z) {
            intent.setData(Uri.parse("chrome-native://newtab/"));
            intent.putExtra("create_new_tab", true);
        }
        intent.addFlags(268435456);
        intent.putExtra("feature_notification_guide_feature_type", i);
        b.A(null, intent, null);
    }

    @CalledByNative
    public final boolean shouldSkipFeature(int i) {
        boolean zL;
        if (i == 1) {
            if (m70.b == null) {
                m70.b = new m70();
            }
            zL = n3.k1(m70.b, xz.a, true);
        } else {
            if (i != 4) {
                return false;
            }
            zL = gq2.l("ntp_snippets.list_visible");
        }
        return true ^ zL;
    }

    @CalledByNative
    public final void clearNativePtr() {
    }
}
