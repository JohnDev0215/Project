package com.android.car.developeroptions.network.telephony;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class MobileDataDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    private int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private int mType;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1582;
    }

    public static MobileDataDialogFragment newInstance(int i, int i2) {
        MobileDataDialogFragment mobileDataDialogFragment = new MobileDataDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("dialog_type", i);
        bundle.putInt("subId", i2);
        mobileDataDialogFragment.setArguments(bundle);
        return mobileDataDialogFragment;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSubscriptionManager = (SubscriptionManager) getContext().getSystemService(SubscriptionManager.class);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        String string;
        String string2;
        Bundle arguments = getArguments();
        Context context = getContext();
        this.mType = arguments.getInt("dialog_type");
        int i = arguments.getInt("subId");
        this.mSubId = i;
        int i2 = this.mType;
        if (i2 == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.data_usage_disable_mobile);
            builder.setPositiveButton(android.R.string.ok, this);
            builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
        if (i2 == 1) {
            SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(i);
            SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
            if (defaultDataSubscriptionInfo == null) {
                string = getContext().getResources().getString(R.string.sim_selection_required_pref);
            } else {
                string = defaultDataSubscriptionInfo.getDisplayName().toString();
            }
            if (activeSubscriptionInfo == null) {
                string2 = getContext().getResources().getString(R.string.sim_selection_required_pref);
            } else {
                string2 = activeSubscriptionInfo.getDisplayName().toString();
            }
            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
            builder2.setTitle(context.getString(R.string.sim_change_data_title, string2));
            builder2.setMessage(context.getString(R.string.sim_change_data_message, string2, string));
            builder2.setPositiveButton(context.getString(R.string.sim_change_data_ok, string2), this);
            builder2.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder2.create();
        }
        throw new IllegalArgumentException("unknown type " + this.mType);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.mType;
        if (i2 == 0) {
            MobileNetworkUtils.setMobileDataEnabled(getContext(), this.mSubId, false, false);
            return;
        }
        if (i2 == 1) {
            this.mSubscriptionManager.setDefaultDataSubId(this.mSubId);
            MobileNetworkUtils.setMobileDataEnabled(getContext(), this.mSubId, true, true);
        } else {
            throw new IllegalArgumentException("unknown type " + this.mType);
        }
    }
}
