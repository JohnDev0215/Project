package b.c.a.f;

import a.b.k.l;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import b.c.a.f.t;
import com.byd.globalnotification.R;
import com.byd.globalnotification.notification.NotifyMsgBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class u extends NotificationListenerService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f1753a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List<String> f1754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b.c.a.c f1755c = new b.c.a.c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static u f1756d;

    static {
        ArrayList arrayList = new ArrayList();
        f1753a = arrayList;
        arrayList.addAll(Arrays.asList(b.c.a.h.d.b.a().getResources().getStringArray(R.array.notify_black_list)));
        ArrayList arrayList2 = new ArrayList();
        f1754b = arrayList2;
        arrayList2.addAll(Arrays.asList(b.c.a.h.d.b.a().getResources().getStringArray(R.array.notify_black_list2)));
    }

    public u() {
        f1756d = this;
    }

    public static void a(NotifyMsgBean notifyMsgBean) {
        StringBuilder sbD = b.a.a.a.a.d("clickNotification = ");
        sbD.append(notifyMsgBean.getPkgName());
        b.c.a.h.b.a(sbD.toString());
        try {
            PendingIntent pendingIntent = notifyMsgBean.getPendingIntent();
            if (pendingIntent != null) {
                pendingIntent.send();
                b.c.a.h.b.a(pendingIntent.toString());
            }
        } catch (PendingIntent.CanceledException e2) {
            StringBuilder sbD2 = b.a.a.a.a.d("pendingIntent调用失败package = ");
            sbD2.append(notifyMsgBean.getPendingIntent().getCreatorPackage());
            b.c.a.h.b.a(sbD2.toString());
            e2.printStackTrace();
        }
        if (notifyMsgBean.isAutoCancel()) {
            t.b.f1752a.d(notifyMsgBean);
            b.c.a.h.b.b("remove item from click");
        }
    }

    public final boolean b(StatusBarNotification statusBarNotification) {
        if (statusBarNotification.getPackageName().startsWith("com.qualcomm") || TextUtils.equals(statusBarNotification.getPackageName(), "com.tencent.android.qqdownloader") || TextUtils.equals(statusBarNotification.getPackageName(), "android") || TextUtils.equals(statusBarNotification.getPackageName(), "com.byd.sceneandmode") || f1754b.contains(statusBarNotification.getPackageName())) {
            return true;
        }
        return f1753a.contains(statusBarNotification.getPackageName()) && (statusBarNotification.getNotification().flags & 64) == 64;
    }

    public final boolean c(Notification notification) {
        Bundle bundle = notification.extras;
        String string = bundle.getString("android.title", "");
        bundle.getString("android.text", "");
        return (TextUtils.isEmpty(string) && notification.contentView == null && (bundle.getInt("android.progress", 0) <= 0 || bundle.getInt("android.progressMax", 0) <= 0)) ? false : true;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        b.c.a.h.b.b(" NotificationService --> onCreate:");
        f1756d = this;
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("PersonalCenterService", l.i.F(R.string.app_name), 4));
        Notification notificationBuild = new Notification.Builder(this, "PersonalCenterService").setContentTitle(l.i.F(R.string.app_foreground_service_running)).setAutoCancel(false).setOngoing(true).setSmallIcon(R.mipmap.app_icon).build();
        notificationBuild.flags = 64;
        startForeground(1, notificationBuild);
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        b.c.a.h.b.b(" NotificationService --> onDestroy:");
        stopForeground(true);
        f1756d = null;
        super.onDestroy();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        super.onListenerConnected();
        b.c.a.h.b.b(" NotificationService --> onListenerConnected:");
        b.c.a.h.b.b("service.registerAsSystemService onListenerConnected service = " + f1756d);
        StatusBarNotification[] activeNotifications = getActiveNotifications();
        int length = activeNotifications.length;
        for (int i = 0; i < length; i++) {
            StatusBarNotification statusBarNotification = activeNotifications[i];
            Notification notification = statusBarNotification.getNotification();
            Bundle bundle = notification.extras;
            if (bundle != null) {
                String string = bundle.getString("android.text", "");
                bundle.getInt("android.progress", 0);
                bundle.getInt("android.progressMax", 0);
                if (c(notification) && !b(statusBarNotification)) {
                    b.c.a.h.b.b("onListenerConnected:sbn=" + statusBarNotification);
                    PendingIntent pendingIntent = statusBarNotification.getNotification().contentIntent;
                    StringBuilder sbD = b.a.a.a.a.d(" NotificationService --> onListenerConnected:content=");
                    sbD.append(l.i.s(string, "byd_key123456789"));
                    sbD.append("==postTime=");
                    sbD.append(statusBarNotification.getPostTime());
                    sbD.append("==pendingIntent==");
                    sbD.append(pendingIntent);
                    sbD.append("===sbn===");
                    sbD.append(statusBarNotification.toString());
                    sbD.append("\n ===notification===");
                    sbD.append(notification.toString());
                    b.c.a.h.b.a(sbD.toString());
                    t.b.f1752a.c(new NotifyMsgBean(statusBarNotification));
                }
            }
        }
        f.a.a.c.b().f(new NotifyMsgBean());
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        try {
            b.c.a.h.b.b("onNotificationPosted start");
            Notification notification = statusBarNotification.getNotification();
            Bundle bundle = notification.extras;
            if (bundle != null) {
                statusBarNotification.getPackageName();
                String string = bundle.getString("android.title", "");
                bundle.getInt("android.progress");
                bundle.getInt("android.progressMax");
                bundle.getBoolean("android.progressIndeterminate");
                b.c.a.h.b.b("onNotificationPosted:sbn=" + statusBarNotification);
                b.c.a.h.b.b(" NotificationService --> onNotificationPosted:title=" + l.i.s(string, "byd_key123456789") + "==postTime=" + statusBarNotification.getPostTime() + "===sbn===" + statusBarNotification.toString() + "\n ===notification===" + notification.toString());
                String string2 = bundle.getString("android.text", "");
                if (!c(notification) || b(statusBarNotification)) {
                    return;
                }
                f1755c.h(null);
                PendingIntent pendingIntent = statusBarNotification.getNotification().contentIntent;
                b.c.a.h.b.b(" NotificationService --> onNotificationPosted:content=" + l.i.s(string2, "byd_key123456789") + "==postTime=" + statusBarNotification.getPostTime() + "==pendingIntent==" + pendingIntent + "===sbn===" + statusBarNotification.toString() + "\n ===notification===" + notification.toString() + "==packTime=2025-04-21 14:07");
                t.b.f1752a.c(new NotifyMsgBean(statusBarNotification));
            }
        } catch (Exception unused) {
            b.c.a.h.b.b(" NotificationService --> onNotificationPosted:不可解析的通知");
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        try {
            b.c.a.h.b.b("onNotificationRemoved start");
            Notification notification = statusBarNotification.getNotification();
            Bundle bundle = notification.extras;
            if (bundle != null) {
                String string = bundle.getString("android.text", "");
                PendingIntent pendingIntent = statusBarNotification.getNotification().contentIntent;
                b.c.a.h.b.b("onNotificationRemoved");
                b.c.a.h.b.a(" NotificationService --> onNotificationRemoved:content=" + l.i.s(string, "byd_key123456789") + "==postTime=" + statusBarNotification.getPostTime() + "==pendingIntent==" + pendingIntent + "===sbn===" + statusBarNotification.toString() + "\n ===notification===" + notification.toString());
                t.b.f1752a.d(new NotifyMsgBean(statusBarNotification));
                f.a.a.c.b().f(new NotifyMsgBean());
            }
        } catch (Exception unused) {
            b.c.a.h.b.b(" NotificationService --> onNotificationRemoved:不可解析的通知");
        }
    }
}
