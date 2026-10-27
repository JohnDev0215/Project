package org.chromium.chrome.browser.price_tracking;

import android.content.Context;
import defpackage.ax0;
import defpackage.so1;
import java.util.ArrayList;
import java.util.List;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: PriceDropNotifier.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final Context a;
    public ax0 b;
    public final so1 c;
    public final PriceDropNotificationManagerImpl d;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.price_tracking.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PriceDropNotifier.java */
    public static class C0108a {
        public final String a;
        public final String b;

        public C0108a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    /* JADX INFO: compiled from: PriceDropNotifier.java */
    public static class b {
        public final CharSequence a;
        public final CharSequence b;
        public final String c;
        public final String d;
        public final String e;
        public final List<C0108a> f;

        public b(String str, String str2, String str3, String str4, String str5, String str6, ArrayList arrayList) {
            this.a = str;
            this.b = str2;
            this.c = str4;
            this.d = str5;
            this.e = str6;
            this.f = arrayList;
        }
    }

    public a(Context context, NotificationManagerProxyImpl notificationManagerProxyImpl) {
        this.a = context;
        this.c = notificationManagerProxyImpl;
        this.d = new PriceDropNotificationManagerImpl(context, notificationManagerProxyImpl);
    }
}
