package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.kp1;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.reengagement.ReengagementActivity;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: ReengagementNotificationController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ca2 {
    public final Context a;
    public final xe3 b;

    public ca2(AppCompatActivity appCompatActivity, xe3 xe3Var) {
        this.a = appCompatActivity;
        this.b = xe3Var;
    }

    public final boolean a(String str) {
        int i;
        int i2;
        int i3;
        if (TextUtils.equals(str, "IPH_ChromeReengagementNotification1")) {
            i = R$string.chrome_reengagement_notification_1_title;
            i2 = R$string.chrome_reengagement_notification_1_description;
            i3 = 25;
        } else if (TextUtils.equals(str, "IPH_ChromeReengagementNotification2")) {
            i = R$string.chrome_reengagement_notification_2_title;
            i2 = R$string.chrome_reengagement_notification_2_description;
            i3 = 26;
        } else {
            if (!TextUtils.equals(str, "IPH_ChromeReengagementNotification3")) {
                return false;
            }
            i = R$string.chrome_reengagement_notification_3_title;
            i2 = R$string.chrome_reengagement_notification_3_description;
            i3 = 27;
        }
        xe3 xe3Var = this.b;
        if (!xe3Var.shouldTriggerHelpUI(str)) {
            return false;
        }
        xe3Var.dismissed(str);
        qq qqVarD = n3.D("updates", new o8(i3, 200, "reengagement_notification"));
        Context context = this.a;
        Intent intent = new Intent(context, (Class<?>) ReengagementActivity.class);
        intent.addFlags(268435456);
        intent.setAction("launch_ntp");
        o8 o8VarA = o8.a(context, 0, intent, 134217728);
        qqVarD.k(context.getString(i));
        qqVarD.j(context.getString(i2));
        qqVarD.p(R$drawable.ic_chrome);
        qqVarD.c(o8VarA);
        qqVarD.h(true);
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(context);
        lp1 lp1VarF = qqVarD.f();
        notificationManagerProxyImpl.c(lp1VarF);
        kp1.a.a.b(i3, lp1VarF.a);
        return true;
    }
}
