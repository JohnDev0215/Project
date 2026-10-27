package com.android.car.settings.network;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.network.MobileNetworkUpdateManager;

/* JADX INFO: loaded from: classes5.dex */
public class RoamingPreferenceController extends PreferenceController<TwoStatePreference> implements MobileNetworkUpdateManager.MobileNetworkUpdateListener {
    private final CarrierConfigManager mCarrierConfigManager;
    private final ConfirmationDialogFragment.ConfirmListener mConfirmListener;
    private final RoamingStateChangeObserver mRoamingStateChangeObserver;
    private int mSubId;
    private TelephonyManager mTelephonyManager;

    public /* synthetic */ void lambda$new$0$RoamingPreferenceController(Bundle arguments) {
        setRoamingEnabled(true);
    }

    public RoamingPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.network.-$$Lambda$RoamingPreferenceController$MNdKk1u-eSd-OOzEtyfJWfEzX3Y
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$RoamingPreferenceController(bundle);
            }
        };
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mRoamingStateChangeObserver = new RoamingStateChangeObserver(new Handler(Looper.getMainLooper()), new Runnable() { // from class: com.android.car.settings.network.-$$Lambda$EiPchuKjQSqnEPq-GHEH-uWTE60
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.refreshUi();
            }
        });
        this.mSubId = -1;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    public void setSubId(int subId) {
        this.mSubId = subId;
        this.mTelephonyManager = TelephonyManager.from(getContext()).createForSubscriptionId(this.mSubId);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mRoamingStateChangeObserver.register(getContext(), this.mSubId);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG), this.mConfirmListener, null, null);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mRoamingStateChangeObserver.unregister(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setEnabled(this.mSubId != -1);
        TelephonyManager telephonyManager = this.mTelephonyManager;
        preference.setChecked(telephonyManager != null ? telephonyManager.isDataRoamingEnabled() : false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        boolean isEnabled = ((Boolean) newValue).booleanValue();
        if (isEnabled && isDialogNeeded()) {
            getFragmentController().showDialog(getRoamingAlertDialog(), ConfirmationDialogFragment.TAG);
            return false;
        }
        setRoamingEnabled(isEnabled);
        return true;
    }

    @Override // com.android.car.settings.network.MobileNetworkUpdateManager.MobileNetworkUpdateListener
    public void onMobileNetworkUpdated(int subId) {
        setSubId(subId);
        refreshUi();
    }

    private void setRoamingEnabled(boolean enabled) {
        this.mTelephonyManager.setDataRoamingEnabled(enabled);
        refreshUi();
    }

    private boolean isDialogNeeded() {
        boolean isRoamingEnabled = this.mTelephonyManager.isDataRoamingEnabled();
        PersistableBundle carrierConfig = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        if (isRoamingEnabled) {
            return false;
        }
        if (carrierConfig == null || !carrierConfig.getBoolean("disable_charge_indication_bool")) {
            return true;
        }
        return false;
    }

    private ConfirmationDialogFragment getRoamingAlertDialog() {
        return new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.roaming_alert_title).setMessage(R.string.roaming_warning).setPositiveButton(android.R.string.yes, this.mConfirmListener).setNegativeButton(android.R.string.no, (ConfirmationDialogFragment.RejectListener) null).build();
    }

    private static class RoamingStateChangeObserver extends ContentObserver {
        private Runnable mChangeListener;

        RoamingStateChangeObserver(Handler handler, Runnable changeListener) {
            super(handler);
            this.mChangeListener = changeListener;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            this.mChangeListener.run();
        }

        public void register(Context context, int subId) {
            Uri uri = Settings.Global.getUriFor("data_roaming");
            if (TelephonyManager.from(context).getSimCount() != 1) {
                uri = Settings.Global.getUriFor("data_roaming" + subId);
            }
            context.getContentResolver().registerContentObserver(uri, false, this);
        }

        public void unregister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
