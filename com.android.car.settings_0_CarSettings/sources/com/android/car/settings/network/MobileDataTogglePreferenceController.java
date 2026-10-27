package com.android.car.settings.network;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.network.MobileNetworkUpdateManager;

/* JADX INFO: loaded from: classes5.dex */
public class MobileDataTogglePreferenceController extends PreferenceController<TwoStatePreference> implements MobileNetworkUpdateManager.MobileNetworkUpdateListener {
    static final String DISABLE_DIALOG_TAG = "ConfirmationDialogFragment_DisableData";
    static final String ENABLE_MULTISIM_DIALOG_TAG = "ConfirmationDialogFragment_EnableMultisim";
    private final ConfirmationDialogFragment.ConfirmListener mConfirmDisableListener;
    private final ConfirmationDialogFragment.ConfirmListener mConfirmMultiSimListener;
    private final ContentObserver mMobileDataChangeObserver;
    private final ConfirmationDialogFragment.RejectListener mRejectRefreshListener;
    private int mSubId;
    private final SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    public /* synthetic */ void lambda$new$0$MobileDataTogglePreferenceController(Bundle arguments) {
        setMobileDataEnabled(false, false);
    }

    public /* synthetic */ void lambda$new$1$MobileDataTogglePreferenceController(Bundle arguments) {
        setMobileDataEnabled(true, true);
    }

    public /* synthetic */ void lambda$new$2$MobileDataTogglePreferenceController(Bundle arguments) {
        refreshUi();
    }

    public MobileDataTogglePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mMobileDataChangeObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.settings.network.MobileDataTogglePreferenceController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                MobileDataTogglePreferenceController.this.refreshUi();
            }
        };
        this.mConfirmDisableListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.network.-$$Lambda$MobileDataTogglePreferenceController$mvxqVxS2u0LgS9mKdBCMcjwbJFg
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$MobileDataTogglePreferenceController(bundle);
            }
        };
        this.mConfirmMultiSimListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.network.-$$Lambda$MobileDataTogglePreferenceController$9bAiAJfNOt-EnikshZSFHY6d3Io
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$1$MobileDataTogglePreferenceController(bundle);
            }
        };
        this.mRejectRefreshListener = new ConfirmationDialogFragment.RejectListener() { // from class: com.android.car.settings.network.-$$Lambda$MobileDataTogglePreferenceController$jXjl38oJPgTN6CXtfSOSA-xRrdc
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.RejectListener
            public final void onReject(Bundle bundle) {
                this.f$0.lambda$new$2$MobileDataTogglePreferenceController(bundle);
            }
        };
        this.mSubscriptionManager = (SubscriptionManager) getContext().getSystemService(SubscriptionManager.class);
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
    protected int getAvailabilityStatus() {
        return this.mSubId != -1 ? 0 : 1;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(DISABLE_DIALOG_TAG), this.mConfirmDisableListener, this.mRejectRefreshListener, null);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(ENABLE_MULTISIM_DIALOG_TAG), this.mConfirmMultiSimListener, this.mRejectRefreshListener, null);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        if (this.mSubId != -1) {
            getContext().getContentResolver().registerContentObserver(getObservableUri(this.mSubId), false, this.mMobileDataChangeObserver);
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        if (this.mSubId != -1) {
            getContext().getContentResolver().unregisterContentObserver(this.mMobileDataChangeObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setEnabled(!isOpportunistic());
        TelephonyManager telephonyManager = this.mTelephonyManager;
        preference.setChecked(telephonyManager != null ? telephonyManager.isDataEnabled() : false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        boolean newToggleValue = ((Boolean) newValue).booleanValue();
        boolean isMultiSim = this.mTelephonyManager.getSimCount() > 1;
        int defaultSubId = SubscriptionManager.getDefaultDataSubscriptionId();
        boolean needToDisableOthers = this.mSubscriptionManager.isActiveSubscriptionId(defaultSubId) && this.mSubId != defaultSubId;
        if (!newToggleValue && !isMultiSim) {
            getFragmentController().showDialog(getConfirmDataDisableDialog(), DISABLE_DIALOG_TAG);
        } else if (newToggleValue && isMultiSim && needToDisableOthers) {
            getFragmentController().showDialog(getConfirmMultisimEnableDialog(), ENABLE_MULTISIM_DIALOG_TAG);
        } else {
            setMobileDataEnabled(newToggleValue, false);
        }
        return false;
    }

    @Override // com.android.car.settings.network.MobileNetworkUpdateManager.MobileNetworkUpdateListener
    public void onMobileNetworkUpdated(int subId) {
        setSubId(subId);
        refreshUi();
    }

    private void setMobileDataEnabled(boolean enabled, boolean disableOtherSubscriptions) {
        NetworkUtils.setMobileDataEnabled(getContext(), this.mSubId, enabled, disableOtherSubscriptions);
        refreshUi();
    }

    private boolean isOpportunistic() {
        SubscriptionInfo info = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        return info != null && info.isOpportunistic();
    }

    private Uri getObservableUri(int subId) {
        Uri uri = Settings.Global.getUriFor("mobile_data");
        if (TelephonyManager.from(getContext()).getSimCount() != 1) {
            Uri uri2 = Settings.Global.getUriFor("mobile_data" + subId);
            return uri2;
        }
        return uri;
    }

    private ConfirmationDialogFragment getConfirmDataDisableDialog() {
        return new ConfirmationDialogFragment.Builder(getContext()).setMessage(R.string.confirm_mobile_data_disable).setPositiveButton(android.R.string.ok, this.mConfirmDisableListener).setNegativeButton(android.R.string.cancel, this.mRejectRefreshListener).build();
    }

    private ConfirmationDialogFragment getConfirmMultisimEnableDialog() {
        String previousName;
        String newName;
        SubscriptionInfo previousSubInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        SubscriptionInfo newSubInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        if (previousSubInfo == null) {
            previousName = getContext().getResources().getString(R.string.sim_selection_required_pref);
        } else {
            previousName = previousSubInfo.getDisplayName().toString();
        }
        if (newSubInfo == null) {
            newName = getContext().getResources().getString(R.string.sim_selection_required_pref);
        } else {
            newName = newSubInfo.getDisplayName().toString();
        }
        return new ConfirmationDialogFragment.Builder(getContext()).setTitle(getContext().getString(R.string.sim_change_data_title, newName)).setMessage(getContext().getString(R.string.sim_change_data_message, newName, previousName)).setPositiveButton(getContext().getString(R.string.sim_change_data_ok, newName), this.mConfirmMultiSimListener).setNegativeButton(android.R.string.cancel, this.mRejectRefreshListener).build();
    }
}
