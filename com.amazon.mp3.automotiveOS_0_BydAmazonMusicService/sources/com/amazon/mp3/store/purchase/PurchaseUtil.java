package com.amazon.mp3.store.purchase;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes4.dex */
public class PurchaseUtil {
    public static final String getReferrerPackageName() {
        Context context = AmazonApplication.getContext();
        String string = SettingsUtil.getPrefs(context).getString(context.getString(R.string.setting_key_app_referrer_package), null);
        if (getReferrerExpireTimeout() > 0) {
            return string;
        }
        return null;
    }

    public static final long getReferrerExpireTimeout() {
        Context context = AmazonApplication.getContext();
        return Math.max(0L, AmazonApplication.getConfiguration(context).getLong("app_referral_timeout_ms", 86400000L) - (Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() - SettingsUtil.getPrefs(context).getLong(context.getString(R.string.setting_key_app_referrer_timestamp), 0L)));
    }
}
