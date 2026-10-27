package com.android.settingslib.deviceinfo;

import android.content.Context;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.ims.ImsMmTelManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.R;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AbstractImsStatusPreferenceController extends AbstractConnectivityPreferenceController {
    private static final String[] CONNECTIVITY_INTENTS = {"android.bluetooth.adapter.action.STATE_CHANGED", "android.net.conn.CONNECTIVITY_CHANGE", "android.net.wifi.LINK_CONFIGURATION_CHANGED", "android.net.wifi.STATE_CHANGE"};
    static final String KEY_IMS_REGISTRATION_STATE = "ims_reg_state";
    private static final String LOG_TAG = "AbstractImsPrefController";
    private static final long MAX_THREAD_BLOCKING_TIME_MS = 2000;
    private Preference mImsStatus;

    public AbstractImsStatusPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        CarrierConfigManager configManager = (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class);
        int subId = SubscriptionManager.getDefaultDataSubscriptionId();
        PersistableBundle config = null;
        if (configManager != null) {
            config = configManager.getConfigForSubId(subId);
        }
        return config != null && config.getBoolean("show_ims_registration_status_bool");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController, com.android.settingslib.core.ConfirmationDialogController
    public String getPreferenceKey() {
        return KEY_IMS_REGISTRATION_STATE;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        this.mImsStatus = screen.findPreference(KEY_IMS_REGISTRATION_STATE);
        updateConnectivity();
    }

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected String[] getConnectivityIntents() {
        return CONNECTIVITY_INTENTS;
    }

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected void updateConnectivity() {
        if (this.mImsStatus == null) {
            return;
        }
        int subId = SubscriptionManager.getDefaultDataSubscriptionId();
        if (!SubscriptionManager.isValidSubscriptionId(subId)) {
            this.mImsStatus.setSummary(R.string.ims_reg_status_not_registered);
            return;
        }
        ExecutorService executors = Executors.newSingleThreadExecutor();
        StateCallback stateCallback = new StateCallback();
        ImsMmTelManager imsMmTelManager = ImsMmTelManager.createForSubscriptionId(subId);
        try {
            imsMmTelManager.getRegistrationState(executors, stateCallback);
        } catch (Exception e) {
        }
        this.mImsStatus.setSummary(stateCallback.waitUntilResult() ? R.string.ims_reg_status_registered : R.string.ims_reg_status_not_registered);
        try {
            executors.shutdownNow();
        } catch (Exception e2) {
        }
    }

    private final class StateCallback extends AtomicBoolean implements Consumer<Integer> {
        private final Semaphore mSemaphore;

        private StateCallback() {
            super(false);
            this.mSemaphore = new Semaphore(0);
        }

        @Override // java.util.function.Consumer
        public void accept(Integer state) {
            set(state.intValue() == 2);
            try {
                this.mSemaphore.release();
            } catch (Exception e) {
            }
        }

        public boolean waitUntilResult() {
            try {
                if (!this.mSemaphore.tryAcquire(AbstractImsStatusPreferenceController.MAX_THREAD_BLOCKING_TIME_MS, TimeUnit.MILLISECONDS)) {
                    Log.w(AbstractImsStatusPreferenceController.LOG_TAG, "IMS registration state query timeout");
                    return false;
                }
            } catch (Exception e) {
            }
            return get();
        }
    }
}
