package org.chromium.chrome.browser.notifications;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import defpackage.kp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.oj2;
import defpackage.q11;
import defpackage.xz;

/* JADX INFO: loaded from: classes2.dex */
public final class NotificationIntentInterceptor {

    public static final class Receiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            NotificationIntentInterceptor.a(intent);
        }
    }

    public static class TrampolineActivity extends Activity {
        @Override // android.app.Activity
        public final void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            getApplicationContext();
            NotificationIntentInterceptor.a(getIntent());
            finish();
        }
    }

    public static void a(Intent intent) {
        int intExtra = intent.getIntExtra("notifications.NotificationIntentInterceptor.EXTRA_INTENT_TYPE", -1);
        int intExtra2 = intent.getIntExtra("notifications.NotificationIntentInterceptor.EXTRA_NOTIFICATION_TYPE", -1);
        long longExtra = intent.getLongExtra("notifications.NotificationIntentInterceptor.EXTRA_CREATE_TIME", -1L);
        if (intExtra == 0) {
            kp1.a.a.getClass();
            if (intExtra2 != -1) {
                oj2.U(intExtra2, 39, "Mobile.SystemNotification.Content.Click");
                if (intExtra2 == 0) {
                    n3.X0("Mobile.SystemNotification.Content.Click.Downloads_Files");
                }
                kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age");
                if (intExtra2 == 15) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age.SendTabToSelf");
                } else if (intExtra2 == 29) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age.SmsFetcher");
                } else if (intExtra2 == 17) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age.ClickToCall");
                } else if (intExtra2 == 18) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age.SharedClipboard");
                } else if (intExtra2 == 32) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age.PriceDropChromeManaged");
                } else if (intExtra2 == 33) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Content.Click.Age.PriceDropUserManaged");
                }
            }
        } else if (intExtra == 1) {
            int intExtra3 = intent.getIntExtra("notifications.NotificationIntentInterceptor.EXTRA_ACTION_TYPE", -1);
            kp1.a.a.getClass();
            if (intExtra3 != -1) {
                oj2.U(intExtra3, 29, "Mobile.SystemNotification.Action.Click");
                kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age");
                if (intExtra2 == 15) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age.SendTabToSelf");
                } else if (intExtra2 == 29) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age.SmsFetcher");
                } else if (intExtra2 == 17) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age.ClickToCall");
                } else if (intExtra2 == 18) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age.SharedClipboard");
                } else if (intExtra2 == 32) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age.PriceDropChromeManaged");
                } else if (intExtra2 == 33) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Action.Click.Age.PriceDropUserManaged");
                }
            }
        } else if (intExtra == 2) {
            kp1.a.a.getClass();
            if (intExtra2 != -1) {
                oj2.U(intExtra2, 39, "Mobile.SystemNotification.Dismiss");
                kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age");
                if (intExtra2 == 15) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age.SendTabToSelf");
                } else if (intExtra2 == 29) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age.SmsFetcher");
                } else if (intExtra2 == 17) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age.ClickToCall");
                } else if (intExtra2 == 18) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age.SharedClipboard");
                } else if (intExtra2 == 32) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age.PriceDropChromeManaged");
                } else if (intExtra2 == 33) {
                    kp1.c(longExtra, "Mobile.SystemNotification.Dismiss.Age.PriceDropUserManaged");
                }
            }
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("notifications.NotificationIntentInterceptor.EXTRA_PENDING_INTENT");
        if (pendingIntent == null) {
            return;
        }
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            Log.e("cr_IntentInterceptor", "The PendingIntent to fire is canceled: " + e.toString());
        }
    }

    public static PendingIntent b(int i, int i2, o8 o8Var, o8 o8Var2) {
        int i3;
        PendingIntent pendingIntent;
        int iD = q11.d(false);
        if (o8Var2 != null) {
            pendingIntent = (PendingIntent) o8Var2.c;
            i3 = o8Var2.a;
        } else {
            i3 = iD;
            pendingIntent = null;
        }
        boolean z = i == 2;
        Context context = xz.a;
        Intent intent = z ? new Intent(context, (Class<?>) Receiver.class) : new Intent(context, (Class<?>) TrampolineActivity.class);
        intent.setAction("notifications.NotificationIntentInterceptor.INTENT_ACTION");
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_PENDING_INTENT", pendingIntent);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_INTENT_TYPE", i);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_NOTIFICATION_TYPE", o8Var.a);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_CREATE_TIME", System.currentTimeMillis());
        if (i == 1) {
            intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_ACTION_TYPE", i2);
        }
        if (!z && Build.VERSION.SDK_INT >= 31) {
            intent.addFlags(262144);
        }
        if (z) {
            intent.addFlags(268435456);
        }
        int i4 = o8Var2 != null ? o8Var2.b : 0;
        int i5 = ((((o8Var.a * 31) + i) * 31) + i2) * 31;
        String str = (String) o8Var.c;
        int iHashCode = ((((i5 + (str != null ? str.hashCode() : 0)) * 31) + o8Var.b) * 31) + i4;
        return z ? PendingIntent.getBroadcast(context, iHashCode, intent, i3) : PendingIntent.getActivity(context, iHashCode, intent, i3);
    }
}
