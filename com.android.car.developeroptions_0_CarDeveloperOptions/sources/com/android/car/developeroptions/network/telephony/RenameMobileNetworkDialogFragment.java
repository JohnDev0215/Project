package com.android.car.developeroptions.network.telephony;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.DeviceInfoUtils;

/* JADX INFO: loaded from: classes.dex */
public class RenameMobileNetworkDialogFragment extends InstrumentedDialogFragment {
    private EditText mNameView;
    private int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1642;
    }

    public static RenameMobileNetworkDialogFragment newInstance(int i) {
        Bundle bundle = new Bundle(1);
        bundle.putInt("subscription_id", i);
        RenameMobileNetworkDialogFragment renameMobileNetworkDialogFragment = new RenameMobileNetworkDialogFragment();
        renameMobileNetworkDialogFragment.setArguments(bundle);
        return renameMobileNetworkDialogFragment;
    }

    protected TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    protected SubscriptionManager getSubscriptionManager(Context context) {
        return (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
    }

    protected EditText getNameView() {
        return this.mNameView;
    }

    @Override // com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment, com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mTelephonyManager = getTelephonyManager(context);
        this.mSubscriptionManager = getSubscriptionManager(context);
        this.mSubId = getArguments().getInt("subscription_id");
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View viewInflate = ((LayoutInflater) builder.getContext().getSystemService(LayoutInflater.class)).inflate(R.layout.dialog_mobile_network_rename, (ViewGroup) null);
        populateView(viewInflate);
        builder.setTitle(R.string.mobile_network_sim_name);
        builder.setView(viewInflate);
        builder.setPositiveButton(R.string.mobile_network_sim_name_rename, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$RenameMobileNetworkDialogFragment$uPD95IZzoKOVTsMapguLnO6O92E
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$RenameMobileNetworkDialogFragment(dialogInterface, i);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$RenameMobileNetworkDialogFragment(DialogInterface dialogInterface, int i) {
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        String string = this.mNameView.getText().toString();
        if (activeSubscriptionInfo != null) {
            activeSubscriptionInfo.getDisplayName().equals(string);
        }
    }

    protected void populateView(View view) {
        this.mNameView = (EditText) view.findViewById(R.id.edittext);
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        if (activeSubscriptionInfo == null) {
            Log.w("RenameMobileNetwork", "got null SubscriptionInfo for mSubId:" + this.mSubId);
            return;
        }
        CharSequence displayName = activeSubscriptionInfo.getDisplayName();
        this.mNameView.setText(displayName);
        if (!TextUtils.isEmpty(displayName)) {
            this.mNameView.setSelection(displayName.length());
        }
        ((TextView) view.findViewById(R.id.operator_name_value)).setText(this.mTelephonyManager.getServiceStateForSubscriber(this.mSubId).getOperatorAlphaLong());
        ((TextView) view.findViewById(R.id.number_value)).setText(BidiFormatter.getInstance().unicodeWrap(DeviceInfoUtils.getFormattedPhoneNumber(getContext(), activeSubscriptionInfo), TextDirectionHeuristics.LTR));
    }
}
