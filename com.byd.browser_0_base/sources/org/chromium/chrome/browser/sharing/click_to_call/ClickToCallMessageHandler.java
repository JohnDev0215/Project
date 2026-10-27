package org.chromium.chrome.browser.sharing.click_to_call;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import defpackage.o8;
import defpackage.oj2;
import defpackage.q11;
import defpackage.rl2;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class ClickToCallMessageHandler {

    public static final class PhoneUnlockedReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent != null) {
                "android.intent.action.USER_PRESENT".equals(intent.getAction());
            }
        }
    }

    public static final class TapReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            try {
                xz.a.startActivity(ClickToCallMessageHandler.a(q11.r(intent, "ClickToCallMessageHandler.EXTRA_PHONE_NUMBER")));
                oj2.O("Sharing.ClickToCallDialerPresent", true);
            } catch (ActivityNotFoundException unused) {
                oj2.O("Sharing.ClickToCallDialerPresent", false);
                Context context2 = xz.a;
                rl2.a(17, "ClickToCall", 12, null, null, null, null, context2.getResources().getString(R$string.click_to_call_dialer_absent_notification_title), context2.getResources().getString(R$string.click_to_call_dialer_absent_notification_text), R$drawable.ic_error_outline_red_24dp, R$drawable.ic_dialer_not_found_red_40dp, R$color.google_red_600, false);
            }
        }
    }

    public static Intent a(String str) {
        Intent intent;
        if (TextUtils.isEmpty(str)) {
            intent = new Intent("android.intent.action.DIAL");
        } else {
            intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        }
        intent.addFlags(268435456);
        return intent;
    }

    @CalledByNative
    public static void handleMessage(String str) {
        Context context = xz.a;
        String strDecode = Uri.decode(str);
        Context context2 = xz.a;
        rl2.a(17, "ClickToCall", 9, Build.VERSION.SDK_INT >= 31 ? o8.a(context2, 0, a(str), 134217728) : o8.b(context2, 0, new Intent(context2, (Class<?>) TapReceiver.class).putExtra("ClickToCallMessageHandler.EXTRA_PHONE_NUMBER", str), 134217728, false), null, null, null, strDecode, context.getResources().getString(R$string.click_to_call_notification_text), R$drawable.ic_devices_16dp, R$drawable.ic_dialer_icon_blue_40dp, R$color.default_icon_color_accent1_baseline, true);
    }
}
