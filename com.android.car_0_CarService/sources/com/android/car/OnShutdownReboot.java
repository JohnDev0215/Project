package com.android.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
class OnShutdownReboot {
    private final Context mContext;
    private final Object mLock = new Object();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.OnShutdownReboot.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            for (BiConsumer<Context, Intent> action : OnShutdownReboot.this.mActions) {
                action.accept(context, intent);
            }
        }
    };
    private final CopyOnWriteArrayList<BiConsumer<Context, Intent>> mActions = new CopyOnWriteArrayList<>();

    OnShutdownReboot(Context context) {
        this.mContext = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ACTION_SHUTDOWN");
        filter.addAction("android.intent.action.REBOOT");
        this.mContext.registerReceiver(this.mReceiver, filter);
    }

    OnShutdownReboot addAction(BiConsumer<Context, Intent> action) {
        this.mActions.add(action);
        return this;
    }

    void clearActions() {
        this.mActions.clear();
    }
}
