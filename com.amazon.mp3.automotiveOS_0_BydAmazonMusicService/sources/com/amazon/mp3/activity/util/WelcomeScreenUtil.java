package com.amazon.mp3.activity.util;

import android.content.Context;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.subscription.UserSubscription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WelcomeScreenUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/activity/util/WelcomeScreenUtil;", "", "()V", "getLoadingMessageIdForAccountStatus", "", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WelcomeScreenUtil {
    public final String getLoadingMessageIdForAccountStatus(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        if (userSubscription.isHawkfireAllDevices() || userSubscription.isKatana()) {
            String string = context.getString(R.string.dmusic_progress_message_unlimited_full);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            context.ge…unlimited_full)\n        }");
            return string;
        }
        if (!userSubscription.isPrime() || !Branding.shouldShowPrimeBranding()) {
            return "";
        }
        String string2 = context.getString(R.string.dmusic_progress_message_prime, Branding.getPrimeMusicBranding(context));
        Intrinsics.checkNotNullExpressionValue(string2, "{\n            // Note: P…nding(context))\n        }");
        return string2;
    }
}
