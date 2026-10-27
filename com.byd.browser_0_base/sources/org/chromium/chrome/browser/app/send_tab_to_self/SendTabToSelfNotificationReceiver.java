package org.chromium.chrome.browser.app.send_tab_to_self;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import defpackage.n3;
import defpackage.q11;
import defpackage.sp;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.send_tab_to_self.NotificationManager;

/* JADX INFO: loaded from: classes.dex */
public class SendTabToSelfNotificationReceiver extends BroadcastReceiver {

    public class a extends n3 {
        public final /* synthetic */ Intent H;

        public a(Intent intent) {
            this.H = intent;
        }

        @Override // defpackage.n3, defpackage.nj
        public final void K() {
            Intent intent;
            String strR;
            Profile profileD;
            intent = this.H;
            String action = intent.getAction();
            strR = q11.r(intent, "send_tab_to_self.notification.guid");
            profileD = Profile.d();
            action.getClass();
            switch (action) {
                case "send_tab_to_self.dismiss":
                    NotificationManager.hideNotification(strR);
                    N.Mewze4cT(profileD, strR);
                    N.MUxGHiVB();
                    break;
                case "send_tab_to_self.tap":
                    Uri data = intent.getData();
                    Context context = xz.a;
                    Intent intentPutExtra = new Intent().setAction("android.intent.action.VIEW").setData(data).setClass(context, ChromeLauncherActivity.class).addFlags(268435456).putExtra("com.android.browser.application_id", context.getPackageName()).putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
                    q11.a(intentPutExtra);
                    context.startActivity(intentPutExtra);
                    NotificationManager.hideNotification(strR);
                    N.MOtCIw4g(profileD, strR);
                    N.MOuilZ8F();
                    break;
                case "send_tab_to_self.timeout":
                    N.Mewze4cT(profileD, strR);
                    N.MU30U26Y();
                    break;
            }
        }
    }

    @CalledByNative
    public static Class<?> getSendTabToSelfNotificationReciever() {
        return SendTabToSelfNotificationReceiver.class;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        a aVar = new a(intent);
        sp.a().c(aVar);
        sp.a().b(true, aVar);
    }
}
