package defpackage;

import J.N;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import defpackage.xp;
import defpackage.ym;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.notifications.NotificationSettingsBridge;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: SiteChannelsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xn2 {
    public final so1 a;

    /* JADX INFO: compiled from: SiteChannelsManager.java */
    public static class a {
        public static final xn2 a = new xn2(new NotificationManagerProxyImpl(xz.a));
    }

    public xn2(NotificationManagerProxyImpl notificationManagerProxyImpl) {
        this.a = notificationManagerProxyImpl;
    }

    public static boolean e(String str) {
        return str.startsWith("web:") && str.substring(4).contains(";");
    }

    public final NotificationSettingsBridge.SiteChannel a(long j, String str, boolean z) {
        NotificationSettingsBridge.SiteChannel siteChannelC = c(str);
        if (siteChannelC != null) {
            return siteChannelC;
        }
        ym.b bVar = xp.a.a.get("sites");
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(bVar.a, xz.a.getResources().getString(bVar.b));
        so1 so1Var = this.a;
        so1Var.i(notificationChannelGroup);
        String str2 = "web:" + ts3.e(str).h() + ";" + j;
        int i = !z ? 1 : 0;
        NotificationSettingsBridge.SiteChannel siteChannel = new NotificationSettingsBridge.SiteChannel(i, str2, str, j);
        NotificationChannel notificationChannel = new NotificationChannel(str2, N.MR6Af3ZS(str, 1), i == 1 ? 0 : 3);
        notificationChannel.setGroup("sites");
        so1Var.e(notificationChannel);
        return siteChannel;
    }

    public final String b(String str) {
        NotificationSettingsBridge.SiteChannel siteChannelC = c(str);
        boolean z = siteChannelC == null;
        if (z) {
            oj2.O("Notifications.Android.SitesChannel", true);
        }
        return z ? "sites" : siteChannelC.getId();
    }

    public final NotificationSettingsBridge.SiteChannel c(String str) {
        String strH = ts3.e(str).h();
        for (NotificationSettingsBridge.SiteChannel siteChannel : d()) {
            if (siteChannel.getOrigin().equals(strH)) {
                return siteChannel;
            }
        }
        return null;
    }

    public final NotificationSettingsBridge.SiteChannel[] d() {
        List<NotificationChannel> listB = this.a.b();
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : listB) {
            if (e(notificationChannel.getId())) {
                String[] strArrSplit = notificationChannel.getId().substring(4).split(";");
                arrayList.add(new NotificationSettingsBridge.SiteChannel(notificationChannel.getImportance() != 0 ? 0 : 1, notificationChannel.getId(), strArrSplit[0], Long.parseLong(strArrSplit[1])));
            }
        }
        return (NotificationSettingsBridge.SiteChannel[]) arrayList.toArray(new NotificationSettingsBridge.SiteChannel[arrayList.size()]);
    }
}
