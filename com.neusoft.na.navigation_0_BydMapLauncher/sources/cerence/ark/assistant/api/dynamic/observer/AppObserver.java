package cerence.ark.assistant.api.dynamic.observer;

import android.car.Car;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class AppObserver extends BaseObserver {
    private static final String TAG = "AppObserver";
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: cerence.ark.assistant.api.dynamic.observer.AppObserver.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Logger.info(AppObserver.TAG, "package changed: " + intent.getAction());
            AppObserver.this.onContentChanged(ContentType.APP);
        }
    };

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        Logger.info(TAG, "activate: DCC_APP");
        onContentChanged(ContentType.APP);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(Car.PACKAGE_SERVICE);
        if (Build.VERSION.SDK_INT >= 34) {
            Utils.getContext().registerReceiver(this.mReceiver, intentFilter, 2);
        } else {
            Utils.getContext().registerReceiver(this.mReceiver, intentFilter);
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        Utils.getContext().unregisterReceiver(this.mReceiver);
    }
}
