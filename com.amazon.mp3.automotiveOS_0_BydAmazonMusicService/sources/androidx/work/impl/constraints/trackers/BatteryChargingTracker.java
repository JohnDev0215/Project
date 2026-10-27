package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BatteryChargingTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    private static final String TAG = Logger.tagWithPrefix("BatteryChrgTracker");

    public BatteryChargingTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean getInitialState() {
        Intent intentRegisterReceiver = this.mAppContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            Logger.get().error(TAG, "getInitialState - null intent received", new Throwable[0]);
            return null;
        }
        return Boolean.valueOf(isBatteryChangedIntentCharging(intentRegisterReceiver));
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.CHARGING");
        intentFilter.addAction("android.os.action.DISCHARGING");
        return intentFilter;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Context context, @NonNull Intent intent) {
        String action = intent.getAction();
        if (action == null) {
        }
        Logger.get().debug(TAG, String.format("Received %s", action), new Throwable[0]);
        switch (action) {
            case "android.intent.action.ACTION_POWER_DISCONNECTED":
                setState(Boolean.FALSE);
                break;
            case "android.os.action.DISCHARGING":
                setState(Boolean.FALSE);
                break;
            case "android.os.action.CHARGING":
                setState(Boolean.TRUE);
                break;
            case "android.intent.action.ACTION_POWER_CONNECTED":
                setState(Boolean.TRUE);
                break;
        }
    }

    private boolean isBatteryChangedIntentCharging(Intent intent) {
        int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        return intExtra == 2 || intExtra == 5;
    }
}
