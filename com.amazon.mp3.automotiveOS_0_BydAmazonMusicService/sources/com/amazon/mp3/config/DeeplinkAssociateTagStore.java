package com.amazon.mp3.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.util.Clock;
import com.amazon.mp3.util.Log;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class DeeplinkAssociateTagStore {
    private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
    private static final String TAG = "DeeplinkAssociateTagStore";
    private final Clock mClock = new Clock();
    private final Context mContext;

    DeeplinkAssociateTagStore(Context context) {
        this.mContext = context;
    }

    public void save(String tag) {
        storeTag(tag);
    }

    public String loadOrDefault() {
        if (tagIsValid()) {
            return loadTagValue();
        }
        return null;
    }

    private void storeTag(String tag) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().putString("deeplink_associate_tag_value", tag).putLong("deeplink_associate_tag_date_created", this.mClock.nowInUTC().getTime()).apply();
        }
    }

    private SharedPreferences getSharedPreferences() {
        try {
            return this.mContext.getSharedPreferences("com.amazon.mp3.config.DeeplinkAssociateTag", 0);
        } catch (IllegalStateException unused) {
            Log.error(TAG, "SharedPreferences in credential encrypted storage are not available until after user is unlocked");
            return null;
        }
    }

    private boolean tagIsValid() {
        return isDateWithinLast24Hours(loadTagCreatedTime());
    }

    private String loadTagValue() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            return sharedPreferences.getString("deeplink_associate_tag_value", null);
        }
        return null;
    }

    private Date loadTagCreatedTime() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return new Date(sharedPreferences != null ? sharedPreferences.getLong("deeplink_associate_tag_date_created", 0L) : 0L);
    }

    private boolean isDateWithinLast24Hours(Date date) {
        return date.getTime() > this.mClock.nowInUTC().getTime() - DAY_IN_MILLIS;
    }
}
