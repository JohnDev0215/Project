package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.app.Application;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.homepage.contextualcards.slices.NotificationChannelSlice;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.applications.ApplicationsState;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelSlice implements CustomSliceable {

    @VisibleForTesting
    static final int DEFAULT_EXPANDED_ROW_COUNT = 3;

    @VisibleForTesting
    static final int MIN_NOTIFICATION_SENT_COUNT = 10;
    protected final Context mContext;

    @VisibleForTesting
    NotificationBackend mNotificationBackend = new NotificationBackend();

    @VisibleForTesting
    static final long DURATION_START_DAYS = TimeUnit.DAYS.toMillis(7);

    @VisibleForTesting
    static final long DURATION_END_DAYS = TimeUnit.DAYS.toMillis(3);

    /* JADX INFO: Access modifiers changed from: private */
    static class NotificationChannelState {
        public abstract NotificationChannel getNotificationChannel();

        public abstract NotificationBackend.NotificationsSentState getNotificationsSentState();
    }

    static {
        $$Lambda$NotificationChannelSlice$HN6Pp8r3LbK2f4338utBROMIf5Y __lambda_notificationchannelslice_hn6pp8r3lbk2f4338utbromif5y = new Comparator() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$NotificationChannelSlice$HN6Pp8r3LbK2f4338utBROMIf5Y
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NotificationChannelSlice.lambda$static$0((NotificationChannelSlice.NotificationChannelState) obj, (NotificationChannelSlice.NotificationChannelState) obj2);
            }
        };
    }

    static /* synthetic */ int lambda$static$0(NotificationChannelState notificationChannelState, NotificationChannelState notificationChannelState2) {
        NotificationBackend.NotificationsSentState notificationsSentState = notificationChannelState.getNotificationsSentState();
        int i = notificationChannelState2.getNotificationsSentState().avgSentWeekly;
        int i2 = notificationsSentState.avgSentWeekly;
        if (i != i2) {
            return i - i2;
        }
        NotificationChannel notificationChannel = notificationChannelState.getNotificationChannel();
        NotificationChannel notificationChannel2 = notificationChannelState2.getNotificationChannel();
        if (TextUtils.equals(notificationChannel.getId(), "miscellaneous")) {
            return 1;
        }
        if (TextUtils.equals(notificationChannel2.getId(), "miscellaneous")) {
            return -1;
        }
        return notificationChannel.getId().compareTo(notificationChannel2.getId());
    }

    public NotificationChannelSlice(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", false);
        String stringExtra = intent.getStringExtra("package_name");
        int intExtra = intent.getIntExtra("package_uid", -1);
        NotificationChannel channel = this.mNotificationBackend.getChannel(stringExtra, intExtra, intent.getStringExtra("channel_id"));
        channel.setImportance(booleanExtra ? 2 : 0);
        channel.lockFields(4);
        this.mNotificationBackend.updateChannel(stringExtra, intExtra, channel);
    }

    @VisibleForTesting
    IconCompat getApplicationIcon(String str) {
        try {
            return Utils.createIconWithDrawable(this.mContext.getPackageManager().getApplicationIcon(str));
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("NotifChannelSlice", "No such package to get application icon.");
            return null;
        }
    }

    @VisibleForTesting
    int getApplicationUid(String str) {
        return ApplicationsState.getInstance((Application) this.mContext.getApplicationContext()).getEntry(str, UserHandle.myUserId()).info.uid;
    }
}
