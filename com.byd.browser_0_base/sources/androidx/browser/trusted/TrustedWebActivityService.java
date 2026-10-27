package androidx.browser.trusted;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.StatusBarNotification;
import android.support.customtabs.trusted.ITrustedWebActivityCallback;
import android.support.customtabs.trusted.ITrustedWebActivityService;
import defpackage.dg3;
import defpackage.nb3;
import defpackage.ro1;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class TrustedWebActivityService extends Service {
    public NotificationManager e;
    public int f = -1;
    public final a g = new a();

    public class a extends ITrustedWebActivityService.Stub {
        public a() {
        }

        public final void A() {
            TrustedWebActivityService trustedWebActivityService = TrustedWebActivityService.this;
            int i = trustedWebActivityService.f;
            if (i != -1) {
                if (i != Binder.getCallingUid()) {
                    throw new SecurityException("Caller is not verified as Trusted Web Activity provider.");
                }
            } else {
                trustedWebActivityService.getPackageManager().getPackagesForUid(Binder.getCallingUid());
                trustedWebActivityService.c();
                throw null;
            }
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final Bundle areNotificationsEnabled(Bundle bundle) {
            NotificationChannel notificationChannel;
            A();
            dg3.a(bundle, "android.support.customtabs.trusted.CHANNEL_NAME");
            String string = bundle.getString("android.support.customtabs.trusted.CHANNEL_NAME");
            TrustedWebActivityService trustedWebActivityService = TrustedWebActivityService.this;
            trustedWebActivityService.b();
            boolean z = false;
            if (new ro1(trustedWebActivityService).a() && ((notificationChannel = trustedWebActivityService.e.getNotificationChannel(TrustedWebActivityService.a(string))) == null || notificationChannel.getImportance() != 0)) {
                z = true;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS", z);
            return bundle2;
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final void cancelNotification(Bundle bundle) {
            A();
            dg3.a(bundle, "android.support.customtabs.trusted.PLATFORM_TAG");
            dg3.a(bundle, "android.support.customtabs.trusted.PLATFORM_ID");
            String string = bundle.getString("android.support.customtabs.trusted.PLATFORM_TAG");
            int i = bundle.getInt("android.support.customtabs.trusted.PLATFORM_ID");
            TrustedWebActivityService trustedWebActivityService = TrustedWebActivityService.this;
            trustedWebActivityService.b();
            trustedWebActivityService.e.cancel(string, i);
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final Bundle extraCommand(String str, Bundle bundle, IBinder iBinder) {
            A();
            if (iBinder != null) {
                ITrustedWebActivityCallback.Stub.asInterface(iBinder);
            }
            TrustedWebActivityService.this.getClass();
            return null;
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final Bundle getActiveNotifications() {
            A();
            TrustedWebActivityService trustedWebActivityService = TrustedWebActivityService.this;
            trustedWebActivityService.b();
            StatusBarNotification[] activeNotifications = trustedWebActivityService.e.getActiveNotifications();
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("android.support.customtabs.trusted.ACTIVE_NOTIFICATIONS", activeNotifications);
            return bundle;
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final Bundle getSmallIconBitmap() {
            A();
            TrustedWebActivityService trustedWebActivityService = TrustedWebActivityService.this;
            int iD = trustedWebActivityService.d();
            Bundle bundle = new Bundle();
            if (iD != -1) {
                bundle.putParcelable("android.support.customtabs.trusted.SMALL_ICON_BITMAP", BitmapFactory.decodeResource(trustedWebActivityService.getResources(), iD));
            }
            return bundle;
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final int getSmallIconId() {
            A();
            return TrustedWebActivityService.this.d();
        }

        @Override // android.support.customtabs.trusted.ITrustedWebActivityService
        public final Bundle notifyNotificationWithChannel(Bundle bundle) {
            Notification notificationBuild;
            A();
            dg3.a(bundle, "android.support.customtabs.trusted.PLATFORM_TAG");
            dg3.a(bundle, "android.support.customtabs.trusted.PLATFORM_ID");
            dg3.a(bundle, "android.support.customtabs.trusted.NOTIFICATION");
            dg3.a(bundle, "android.support.customtabs.trusted.CHANNEL_NAME");
            String string = bundle.getString("android.support.customtabs.trusted.PLATFORM_TAG");
            int i = bundle.getInt("android.support.customtabs.trusted.PLATFORM_ID");
            Notification notification = (Notification) bundle.getParcelable("android.support.customtabs.trusted.NOTIFICATION");
            String string2 = bundle.getString("android.support.customtabs.trusted.CHANNEL_NAME");
            TrustedWebActivityService trustedWebActivityService = TrustedWebActivityService.this;
            trustedWebActivityService.b();
            boolean z = false;
            if (new ro1(trustedWebActivityService).a()) {
                String strA = TrustedWebActivityService.a(string2);
                NotificationManager notificationManager = trustedWebActivityService.e;
                notificationManager.createNotificationChannel(new NotificationChannel(strA, string2, 3));
                if (notificationManager.getNotificationChannel(strA).getImportance() == 0) {
                    notificationBuild = null;
                } else {
                    Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(trustedWebActivityService, notification);
                    builderRecoverBuilder.setChannelId(strA);
                    notificationBuild = builderRecoverBuilder.build();
                }
                NotificationChannel notificationChannel = trustedWebActivityService.e.getNotificationChannel(strA);
                if (notificationChannel == null || notificationChannel.getImportance() != 0) {
                    trustedWebActivityService.e.notify(string, i, notificationBuild);
                    z = true;
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS", z);
            return bundle2;
        }
    }

    public static String a(String str) {
        return str.toLowerCase(Locale.ROOT).replace(' ', '_') + "_channel_id";
    }

    public final void b() {
        if (this.e == null) {
            throw new IllegalStateException("TrustedWebActivityService has not been properly initialized. Did onCreate() call super.onCreate()?");
        }
    }

    public abstract nb3 c();

    public final int d() {
        try {
            Bundle bundle = getPackageManager().getServiceInfo(new ComponentName(this, getClass()), 128).metaData;
            if (bundle == null) {
                return -1;
            }
            return bundle.getInt("android.support.customtabs.trusted.SMALL_ICON", -1);
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.g;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.e = (NotificationManager) getSystemService("notification");
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        this.f = -1;
        return super.onUnbind(intent);
    }
}
