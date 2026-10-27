package com.android.car.developeroptions;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ResetNetwork extends InstrumentedFragment {
    private View mContentView;
    CheckBox mEsimCheckbox;
    View mEsimContainer;
    private Button mInitiateButton;
    private final View.OnClickListener mInitiateListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.ResetNetwork.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ResetNetwork.this.runKeyguardConfirmation(55)) {
                return;
            }
            ResetNetwork.this.showFinalConfirmation();
        }
    };
    private Spinner mSubscriptionSpinner;
    private List<SubscriptionInfo> mSubscriptions;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 83;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.reset_network_title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean runKeyguardConfirmation(int i) {
        return new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(i, getActivity().getResources().getText(R.string.reset_network_title));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 55) {
            return;
        }
        if (i2 == -1) {
            showFinalConfirmation();
        } else {
            establishInitialState();
        }
    }

    void showFinalConfirmation() {
        Bundle bundle = new Bundle();
        List<SubscriptionInfo> list = this.mSubscriptions;
        if (list != null && list.size() > 0) {
            bundle.putInt("subscription", this.mSubscriptions.get(this.mSubscriptionSpinner.getSelectedItemPosition()).getSubscriptionId());
        }
        bundle.putBoolean("erase_esim", this.mEsimContainer.getVisibility() == 0 && this.mEsimCheckbox.isChecked());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(ResetNetworkConfirm.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.reset_network_confirm_title);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
    }

    private void establishInitialState() {
        this.mSubscriptionSpinner = (Spinner) this.mContentView.findViewById(R.id.reset_network_subscription);
        this.mEsimContainer = this.mContentView.findViewById(R.id.erase_esim_container);
        this.mEsimCheckbox = (CheckBox) this.mContentView.findViewById(R.id.erase_esim);
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(getActivity()).getActiveSubscriptionInfoList(true);
        this.mSubscriptions = activeSubscriptionInfoList;
        if (activeSubscriptionInfoList != null && activeSubscriptionInfoList.size() > 0) {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultVoiceSubscriptionId();
            }
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultSmsSubscriptionId();
            }
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
            }
            this.mSubscriptions.size();
            ArrayList arrayList = new ArrayList();
            int size = 0;
            for (SubscriptionInfo subscriptionInfo : this.mSubscriptions) {
                if (subscriptionInfo.getSubscriptionId() == defaultDataSubscriptionId) {
                    size = arrayList.size();
                }
                String string = subscriptionInfo.getDisplayName().toString();
                if (TextUtils.isEmpty(string)) {
                    string = subscriptionInfo.getNumber();
                }
                if (TextUtils.isEmpty(string)) {
                    string = subscriptionInfo.getCarrierName().toString();
                }
                if (TextUtils.isEmpty(string)) {
                    string = String.format("MCC:%s MNC:%s Slot:%s Id:%s", Integer.valueOf(subscriptionInfo.getMcc()), Integer.valueOf(subscriptionInfo.getMnc()), Integer.valueOf(subscriptionInfo.getSimSlotIndex()), Integer.valueOf(subscriptionInfo.getSubscriptionId()));
                }
                arrayList.add(string);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.mSubscriptionSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            this.mSubscriptionSpinner.setSelection(size);
            if (this.mSubscriptions.size() > 1) {
                this.mSubscriptionSpinner.setVisibility(0);
            } else {
                this.mSubscriptionSpinner.setVisibility(4);
            }
        } else {
            this.mSubscriptionSpinner.setVisibility(4);
        }
        Button button = (Button) this.mContentView.findViewById(R.id.initiate_reset_network);
        this.mInitiateButton = button;
        button.setOnClickListener(this.mInitiateListener);
        if (showEuiccSettings(getContext())) {
            this.mEsimContainer.setVisibility(0);
            this.mEsimContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.ResetNetwork.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ResetNetwork.this.mEsimCheckbox.toggle();
                }
            });
        } else {
            this.mEsimCheckbox.setChecked(false);
        }
    }

    private boolean showEuiccSettings(Context context) {
        if (!((EuiccManager) context.getSystemService("euicc")).isEnabled()) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        return (Settings.Global.getInt(contentResolver, "euicc_provisioned", 0) == 0 && Settings.Global.getInt(contentResolver, "development_settings_enabled", 0) == 0) ? false : true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        UserManager userManager = UserManager.get(getActivity());
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_network_reset", UserHandle.myUserId());
        if (!userManager.isAdminUser() || RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_network_reset", UserHandle.myUserId())) {
            return layoutInflater.inflate(R.layout.network_reset_disallowed_screen, (ViewGroup) null);
        }
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            AlertDialog.Builder builderPrepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_network_reset", enforcedAdminCheckIfRestrictionEnforced);
            builderPrepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.-$$Lambda$ResetNetwork$Bzyez6Jb7bTT3AcQJI-QIbmANP8
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreateView$0$ResetNetwork(dialogInterface);
                }
            });
            builderPrepareDialogBuilder.show();
            return new View(getContext());
        }
        this.mContentView = layoutInflater.inflate(R.layout.reset_network, (ViewGroup) null);
        establishInitialState();
        return this.mContentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateView$0$ResetNetwork(DialogInterface dialogInterface) {
        getActivity().finish();
    }
}
