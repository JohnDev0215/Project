package com.android.settingslib.deviceinfo;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractImsStatusPreferenceController extends AbstractConnectivityPreferenceController {

    @VisibleForTesting
    static final String KEY_IMS_REGISTRATION_STATE = "ims_reg_state";
    private static final String[] e = {"android.bluetooth.adapter.action.STATE_CHANGED", "android.net.conn.CONNECTIVITY_CHANGE", "android.net.wifi.LINK_CONFIGURATION_CHANGED", "android.net.wifi.STATE_CHANGE"};

    /* JADX INFO: compiled from: caradapter-bug */
    private final class StateCallback extends AtomicBoolean implements Consumer<Integer> {
        private final Semaphore mSemaphore;

        public boolean waitUntilResult() {
            try {
                if (!this.mSemaphore.tryAcquire(2000L, TimeUnit.MILLISECONDS)) {
                    Log.w("AbstractImsPrefController", "IMS registration state query timeout");
                    return false;
                }
            } catch (Exception unused) {
            }
            return get();
        }

        private StateCallback() {
            super(false);
            this.mSemaphore = new Semaphore(0);
        }

        @Override // java.util.function.Consumer
        public void accept(Integer num) {
            set(num.intValue() == 2);
            try {
                this.mSemaphore.release();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected String[] n() {
        return e;
    }
}
