package defpackage;

import android.app.Notification;
import android.os.Bundle;
import androidx.core.app.NotificationCompat$Builder;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class lo1 extends no1 {
    public CharSequence d;

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {
        public static Notification.BigTextStyle a(Notification.BigTextStyle bigTextStyle, CharSequence charSequence) {
            return bigTextStyle.bigText(charSequence);
        }

        public static Notification.BigTextStyle b(Notification.Builder builder) {
            return new Notification.BigTextStyle(builder);
        }

        public static Notification.BigTextStyle c(Notification.BigTextStyle bigTextStyle, CharSequence charSequence) {
            return bigTextStyle.setBigContentTitle(charSequence);
        }

        public static Notification.BigTextStyle d(Notification.BigTextStyle bigTextStyle, CharSequence charSequence) {
            return bigTextStyle.setSummaryText(charSequence);
        }
    }

    public lo1() {
    }

    @Override // defpackage.no1
    public final void a(Bundle bundle) {
        super.a(bundle);
    }

    @Override // defpackage.no1
    public final void b(oo1 oo1Var) {
        Notification.BigTextStyle bigTextStyleA = a.a(a.c(a.b(oo1Var.b), null), this.d);
        if (this.c) {
            a.d(bigTextStyleA, this.b);
        }
    }

    @Override // defpackage.no1
    public final String c() {
        return "androidx.core.app.NotificationCompat$BigTextStyle";
    }

    public lo1(NotificationCompat$Builder notificationCompat$Builder) {
        f(notificationCompat$Builder);
    }
}
