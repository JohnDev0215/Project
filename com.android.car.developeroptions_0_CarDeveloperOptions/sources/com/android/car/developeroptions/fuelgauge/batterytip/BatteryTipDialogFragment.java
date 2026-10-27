package com.android.car.developeroptions.fuelgauge.batterytip;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.BatteryTipAction;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.HighUsageTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.RestrictAppTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.UnrestrictAppTip;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BatteryTipDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    BatteryTip mBatteryTip;
    int mMetricsKey;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1323;
    }

    public static BatteryTipDialogFragment newInstance(BatteryTip batteryTip, int i) {
        BatteryTipDialogFragment batteryTipDialogFragment = new BatteryTipDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(BatteryTipPreferenceController.PREF_NAME, batteryTip);
        bundle.putInt("metrics_key", i);
        batteryTipDialogFragment.setArguments(bundle);
        return batteryTipDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        Context context = getContext();
        this.mBatteryTip = (BatteryTip) arguments.getParcelable(BatteryTipPreferenceController.PREF_NAME);
        this.mMetricsKey = arguments.getInt("metrics_key");
        int type = this.mBatteryTip.getType();
        if (type == 1) {
            RestrictAppTip restrictAppTip = (RestrictAppTip) this.mBatteryTip;
            List<AppInfo> restrictAppList = restrictAppTip.getRestrictAppList();
            int size = restrictAppList.size();
            CharSequence applicationLabel = Utils.getApplicationLabel(context, restrictAppList.get(0).packageName);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getResources().getQuantityString(R.plurals.battery_tip_restrict_app_dialog_title, size, Integer.valueOf(size)));
            builder.setPositiveButton(R.string.battery_tip_restrict_app_dialog_ok, this);
            builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
            if (size == 1) {
                builder.setMessage(getString(R.string.battery_tip_restrict_app_dialog_message, applicationLabel));
            } else if (size <= 5) {
                builder.setMessage(getString(R.string.battery_tip_restrict_apps_less_than_5_dialog_message));
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.recycler_view, (ViewGroup) null);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new HighUsageAdapter(context, restrictAppList));
                builder.setView(recyclerView);
            } else {
                builder.setMessage(context.getString(R.string.battery_tip_restrict_apps_more_than_5_dialog_message, restrictAppTip.getRestrictAppsString(context)));
            }
            return builder.create();
        }
        if (type == 2) {
            HighUsageTip highUsageTip = (HighUsageTip) this.mBatteryTip;
            RecyclerView recyclerView2 = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.recycler_view, (ViewGroup) null);
            recyclerView2.setLayoutManager(new LinearLayoutManager(context));
            recyclerView2.setAdapter(new HighUsageAdapter(context, highUsageTip.getHighUsageAppList()));
            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
            builder2.setMessage(getString(R.string.battery_tip_dialog_message, Integer.valueOf(highUsageTip.getHighUsageAppList().size())));
            builder2.setView(recyclerView2);
            builder2.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            return builder2.create();
        }
        if (type == 6) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
            builder3.setMessage(R.string.battery_tip_dialog_summary_message);
            builder3.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            return builder3.create();
        }
        if (type == 7) {
            Utils.getApplicationLabel(context, ((UnrestrictAppTip) this.mBatteryTip).getPackageName());
            AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
            builder4.setTitle(getString(R.string.battery_tip_unrestrict_app_dialog_title));
            builder4.setMessage(R.string.battery_tip_unrestrict_app_dialog_message);
            builder4.setPositiveButton(R.string.battery_tip_unrestrict_app_dialog_ok, this);
            builder4.setNegativeButton(R.string.battery_tip_unrestrict_app_dialog_cancel, (DialogInterface.OnClickListener) null);
            return builder4.create();
        }
        throw new IllegalArgumentException("unknown type " + this.mBatteryTip.getType());
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        BatteryTipPreferenceController.BatteryTipListener batteryTipListener = (BatteryTipPreferenceController.BatteryTipListener) getTargetFragment();
        if (batteryTipListener == null) {
            return;
        }
        BatteryTipAction actionForBatteryTip = BatteryTipUtils.getActionForBatteryTip(this.mBatteryTip, (SettingsActivity) getActivity(), (InstrumentedPreferenceFragment) getTargetFragment());
        if (actionForBatteryTip != null) {
            actionForBatteryTip.handlePositiveAction(this.mMetricsKey);
        }
        batteryTipListener.onBatteryTipHandled(this.mBatteryTip);
    }
}
