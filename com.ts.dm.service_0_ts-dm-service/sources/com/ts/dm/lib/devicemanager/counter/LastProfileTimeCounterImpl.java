package com.ts.dm.lib.devicemanager.counter;

import android.content.Context;
import android.content.SharedPreferences;
import com.ts.car.sdk.dm.DmConsumer;
import com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter;
import com.ts.dm.lib.util.DmListenerManager;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class LastProfileTimeCounterImpl implements ILastProfileTimeCounter {
    private static final int LAST_PROFILE_TIME_MAX = 20;
    private static final String SP_COUNTER_KEY = "last_profile_time_max";
    private static final String TAG = "LastProfileTimeCounter";
    private AtomicInteger mCounter = new AtomicInteger();
    private DmListenerManager<ILastProfileTimeCounter.OnLastProfileTimeListenr> mDmListenerManager = new DmListenerManager<>();
    private SharedPreferences mSp;

    public LastProfileTimeCounterImpl(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("last_profile_counter", 0);
        this.mSp = sharedPreferences;
        this.mCounter.set(sharedPreferences.getInt(SP_COUNTER_KEY, 0));
    }

    @Override // com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter
    public void addOnLastProfileTimeListener(ILastProfileTimeCounter.OnLastProfileTimeListenr listener) {
        this.mDmListenerManager.registerListener(listener);
    }

    @Override // com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter
    public void removeOnLastProfileTimeListener(ILastProfileTimeCounter.OnLastProfileTimeListenr listener) {
        this.mDmListenerManager.unregisterDmListener(listener);
    }

    @Override // com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter
    public int generateLastProfileTime() {
        int result = this.mCounter.incrementAndGet();
        try {
            saveMax();
            return result;
        } finally {
            if (result >= 19) {
                this.mDmListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.devicemanager.counter.-$$Lambda$LastProfileTimeCounterImpl$PmIIVzz4U3MFbIt6Ub47PKNaKSc
                    public final void accept(Object obj) {
                        ((ILastProfileTimeCounter.OnLastProfileTimeListenr) obj).onLastProfileTimeMax();
                    }
                });
            }
        }
    }

    @Override // com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter
    public void updateLastProfileMax(int max) {
        this.mCounter.set(max);
        saveMax();
    }

    private void saveMax() {
        this.mSp.edit().putInt(SP_COUNTER_KEY, this.mCounter.get()).commit();
    }
}
