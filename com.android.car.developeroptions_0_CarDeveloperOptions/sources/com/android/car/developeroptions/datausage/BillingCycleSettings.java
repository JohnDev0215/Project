package com.android.car.developeroptions.datausage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.NetworkPolicy;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class BillingCycleSettings extends DataUsageBaseFragment implements Preference.OnPreferenceChangeListener, DataUsageEditController {
    static final String KEY_SET_DATA_LIMIT = "set_data_limit";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.datausage.BillingCycleSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.billing_cycle;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private Preference mBillingCycle;
    private Preference mDataLimit;
    private DataUsageController mDataUsageController;
    private Preference mDataWarning;
    private SwitchPreference mEnableDataLimit;
    private SwitchPreference mEnableDataWarning;
    NetworkTemplate mNetworkTemplate;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "BillingCycleSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 342;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.billing_cycle;
    }

    void setUpForTest(NetworkPolicyEditor networkPolicyEditor, Preference preference, Preference preference2, Preference preference3, SwitchPreference switchPreference, SwitchPreference switchPreference2) {
        this.services.mPolicyEditor = networkPolicyEditor;
        this.mBillingCycle = preference;
        this.mDataLimit = preference2;
        this.mDataWarning = preference3;
        this.mEnableDataLimit = switchPreference;
        this.mEnableDataWarning = switchPreference2;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mDataUsageController = new DataUsageController(context);
        NetworkTemplate parcelable = getArguments().getParcelable("network_template");
        this.mNetworkTemplate = parcelable;
        if (parcelable == null) {
            this.mNetworkTemplate = DataUsageUtils.getDefaultTemplate(context, DataUsageUtils.getDefaultSubscriptionId(context));
        }
        this.mBillingCycle = findPreference("billing_cycle");
        SwitchPreference switchPreference = (SwitchPreference) findPreference("set_data_warning");
        this.mEnableDataWarning = switchPreference;
        switchPreference.setOnPreferenceChangeListener(this);
        this.mDataWarning = findPreference("data_warning");
        SwitchPreference switchPreference2 = (SwitchPreference) findPreference(KEY_SET_DATA_LIMIT);
        this.mEnableDataLimit = switchPreference2;
        switchPreference2.setOnPreferenceChangeListener(this);
        this.mDataLimit = findPreference("data_limit");
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updatePrefs();
    }

    void updatePrefs() {
        this.mBillingCycle.setSummary((CharSequence) null);
        long policyWarningBytes = this.services.mPolicyEditor.getPolicyWarningBytes(this.mNetworkTemplate);
        if (policyWarningBytes != -1) {
            this.mDataWarning.setSummary(DataUsageUtils.formatDataUsage(getContext(), policyWarningBytes));
            this.mDataWarning.setEnabled(true);
            this.mEnableDataWarning.setChecked(true);
        } else {
            this.mDataWarning.setSummary((CharSequence) null);
            this.mDataWarning.setEnabled(false);
            this.mEnableDataWarning.setChecked(false);
        }
        long policyLimitBytes = this.services.mPolicyEditor.getPolicyLimitBytes(this.mNetworkTemplate);
        if (policyLimitBytes != -1) {
            this.mDataLimit.setSummary(DataUsageUtils.formatDataUsage(getContext(), policyLimitBytes));
            this.mDataLimit.setEnabled(true);
            this.mEnableDataLimit.setChecked(true);
        } else {
            this.mDataLimit.setSummary((CharSequence) null);
            this.mDataLimit.setEnabled(false);
            this.mEnableDataLimit.setChecked(false);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == this.mBillingCycle) {
            CycleEditorFragment.show(this);
            return true;
        }
        if (preference == this.mDataWarning) {
            BytesEditorFragment.show((DataUsageEditController) this, false);
            return true;
        }
        if (preference == this.mDataLimit) {
            BytesEditorFragment.show((DataUsageEditController) this, true);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mEnableDataLimit == preference) {
            if (!((Boolean) obj).booleanValue()) {
                setPolicyLimitBytes(-1L);
                return true;
            }
            ConfirmLimitFragment.show(this);
            return false;
        }
        if (this.mEnableDataWarning != preference) {
            return false;
        }
        if (((Boolean) obj).booleanValue()) {
            setPolicyWarningBytes(this.mDataUsageController.getDefaultWarningLevel());
        } else {
            setPolicyWarningBytes(-1L);
        }
        return true;
    }

    void setPolicyLimitBytes(long j) {
        this.services.mPolicyEditor.setPolicyLimitBytes(this.mNetworkTemplate, j);
        updatePrefs();
    }

    private void setPolicyWarningBytes(long j) {
        this.services.mPolicyEditor.setPolicyWarningBytes(this.mNetworkTemplate, j);
        updatePrefs();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageEditController
    public NetworkPolicyEditor getNetworkPolicyEditor() {
        return this.services.mPolicyEditor;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageEditController
    public NetworkTemplate getNetworkTemplate() {
        return this.mNetworkTemplate;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageEditController
    public void updateDataUsage() {
        updatePrefs();
    }

    public static class BytesEditorFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private View mView;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 550;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static void show(DataUsageEditController dataUsageEditController, boolean z) {
            if (dataUsageEditController instanceof Fragment) {
                Fragment fragment = (Fragment) dataUsageEditController;
                if (fragment.isAdded()) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("template", dataUsageEditController.getNetworkTemplate());
                    bundle.putBoolean("limit", z);
                    BytesEditorFragment bytesEditorFragment = new BytesEditorFragment();
                    bytesEditorFragment.setArguments(bundle);
                    bytesEditorFragment.setTargetFragment(fragment, 0);
                    bytesEditorFragment.show(fragment.getFragmentManager(), "warningEditor");
                }
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            FragmentActivity activity = getActivity();
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(activity);
            boolean z = getArguments().getBoolean("limit");
            View viewInflate = layoutInflaterFrom.inflate(R.layout.data_usage_bytes_editor, (ViewGroup) null, false);
            this.mView = viewInflate;
            setupPicker((EditText) viewInflate.findViewById(R.id.bytes), (Spinner) this.mView.findViewById(R.id.size_spinner));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(z ? R.string.data_usage_limit_editor_title : R.string.data_usage_warning_editor_title);
            builder.setView(this.mView);
            builder.setPositiveButton(R.string.data_usage_cycle_editor_positive, this);
            return builder.create();
        }

        private void setupPicker(EditText editText, Spinner spinner) {
            long policyWarningBytes;
            NetworkPolicyEditor networkPolicyEditor = ((DataUsageEditController) getTargetFragment()).getNetworkPolicyEditor();
            NetworkTemplate networkTemplate = (NetworkTemplate) getArguments().getParcelable("template");
            if (getArguments().getBoolean("limit")) {
                policyWarningBytes = networkPolicyEditor.getPolicyLimitBytes(networkTemplate);
            } else {
                policyWarningBytes = networkPolicyEditor.getPolicyWarningBytes(networkTemplate);
            }
            float f = policyWarningBytes;
            if (f > 1.6106127E9f) {
                String text = formatText(f / 1.0737418E9f);
                editText.setText(text);
                editText.setSelection(0, text.length());
                spinner.setSelection(1);
                return;
            }
            String text2 = formatText(f / 1048576.0f);
            editText.setText(text2);
            editText.setSelection(0, text2.length());
            spinner.setSelection(0);
        }

        private String formatText(float f) {
            return String.valueOf(Math.round(f * 100.0f) / 100.0f);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i != -1) {
                return;
            }
            DataUsageEditController dataUsageEditController = (DataUsageEditController) getTargetFragment();
            NetworkPolicyEditor networkPolicyEditor = dataUsageEditController.getNetworkPolicyEditor();
            NetworkTemplate networkTemplate = (NetworkTemplate) getArguments().getParcelable("template");
            boolean z = getArguments().getBoolean("limit");
            EditText editText = (EditText) this.mView.findViewById(R.id.bytes);
            Spinner spinner = (Spinner) this.mView.findViewById(R.id.size_spinner);
            String string = editText.getText().toString();
            if (string.isEmpty() || string.equals(".")) {
                string = "0";
            }
            long jMin = Math.min(53687091200000L, (long) (Float.valueOf(string).floatValue() * (spinner.getSelectedItemPosition() == 0 ? 1048576L : 1073741824L)));
            if (z) {
                networkPolicyEditor.setPolicyLimitBytes(networkTemplate, jMin);
            } else {
                networkPolicyEditor.setPolicyWarningBytes(networkTemplate, jMin);
            }
            dataUsageEditController.updateDataUsage();
        }
    }

    public static class CycleEditorFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private NumberPicker mCycleDayPicker;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 549;
        }

        public static void show(BillingCycleSettings billingCycleSettings) {
            if (billingCycleSettings.isAdded()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("template", billingCycleSettings.mNetworkTemplate);
                CycleEditorFragment cycleEditorFragment = new CycleEditorFragment();
                cycleEditorFragment.setArguments(bundle);
                cycleEditorFragment.setTargetFragment(billingCycleSettings, 0);
                cycleEditorFragment.show(billingCycleSettings.getFragmentManager(), "cycleEditor");
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            FragmentActivity activity = getActivity();
            NetworkPolicyEditor networkPolicyEditor = ((DataUsageEditController) getTargetFragment()).getNetworkPolicyEditor();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            View viewInflate = LayoutInflater.from(builder.getContext()).inflate(R.layout.data_usage_cycle_editor, (ViewGroup) null, false);
            this.mCycleDayPicker = (NumberPicker) viewInflate.findViewById(R.id.cycle_day);
            int policyCycleDay = networkPolicyEditor.getPolicyCycleDay((NetworkTemplate) getArguments().getParcelable("template"));
            this.mCycleDayPicker.setMinValue(1);
            this.mCycleDayPicker.setMaxValue(31);
            this.mCycleDayPicker.setValue(policyCycleDay);
            this.mCycleDayPicker.setWrapSelectorWheel(true);
            builder.setTitle(R.string.data_usage_cycle_editor_title);
            builder.setView(viewInflate);
            builder.setPositiveButton(R.string.data_usage_cycle_editor_positive, this);
            return builder.create();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            NetworkTemplate parcelable = getArguments().getParcelable("template");
            DataUsageEditController dataUsageEditController = (DataUsageEditController) getTargetFragment();
            NetworkPolicyEditor networkPolicyEditor = dataUsageEditController.getNetworkPolicyEditor();
            this.mCycleDayPicker.clearFocus();
            networkPolicyEditor.setPolicyCycleDay(parcelable, this.mCycleDayPicker.getValue(), TimeZone.getDefault().getID());
            dataUsageEditController.updateDataUsage();
        }
    }

    public static class ConfirmLimitFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        static final String EXTRA_LIMIT_BYTES = "limitBytes";

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 551;
        }

        public static void show(BillingCycleSettings billingCycleSettings) {
            NetworkPolicy policy;
            if (billingCycleSettings.isAdded() && (policy = billingCycleSettings.services.mPolicyEditor.getPolicy(billingCycleSettings.mNetworkTemplate)) != null) {
                billingCycleSettings.getResources();
                long jMax = Math.max(5368709120L, (long) (policy.warningBytes * 1.2f));
                Bundle bundle = new Bundle();
                bundle.putLong(EXTRA_LIMIT_BYTES, jMax);
                ConfirmLimitFragment confirmLimitFragment = new ConfirmLimitFragment();
                confirmLimitFragment.setArguments(bundle);
                confirmLimitFragment.setTargetFragment(billingCycleSettings, 0);
                confirmLimitFragment.show(billingCycleSettings.getFragmentManager(), "confirmLimit");
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.data_usage_limit_dialog_title);
            builder.setMessage(R.string.data_usage_limit_dialog_mobile);
            builder.setPositiveButton(android.R.string.ok, this);
            builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            BillingCycleSettings billingCycleSettings = (BillingCycleSettings) getTargetFragment();
            if (i != -1) {
                return;
            }
            long j = getArguments().getLong(EXTRA_LIMIT_BYTES);
            if (billingCycleSettings != null) {
                billingCycleSettings.setPolicyLimitBytes(j);
            }
            billingCycleSettings.getPreferenceManager().getSharedPreferences().edit().putBoolean(BillingCycleSettings.KEY_SET_DATA_LIMIT, true).apply();
        }
    }
}
