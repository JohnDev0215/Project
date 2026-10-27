package com.android.settingslib.widget;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceDialogFragmentCompat;
import com.android.car.settings.common.PreferenceXmlParser;
import com.android.settingslib.core.instrumentation.Instrumentable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
public class UpdatableListPreferenceDialogFragment extends PreferenceDialogFragmentCompat implements Instrumentable {
    private static final String METRICS_CATEGORY_KEY = "metrics_category_key";
    private static final String SAVE_STATE_ENTRIES = "UpdatableListPreferenceDialogFragment.entries";
    private static final String SAVE_STATE_ENTRY_VALUES = "UpdatableListPreferenceDialogFragment.entryValues";
    private static final String SAVE_STATE_INDEX = "UpdatableListPreferenceDialogFragment.index";
    private ArrayAdapter mAdapter;
    private int mClickedDialogEntryIndex;
    private ArrayList<CharSequence> mEntries;
    private CharSequence[] mEntryValues;
    private int mMetricsCategory = 0;

    public static UpdatableListPreferenceDialogFragment newInstance(String key, int metricsCategory) {
        UpdatableListPreferenceDialogFragment fragment = new UpdatableListPreferenceDialogFragment();
        Bundle args = new Bundle(1);
        args.putString(PreferenceXmlParser.METADATA_KEY, key);
        args.putInt(METRICS_CATEGORY_KEY, metricsCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.mMetricsCategory = bundle.getInt(METRICS_CATEGORY_KEY, 0);
        if (savedInstanceState == null) {
            this.mEntries = new ArrayList<>();
            setPreferenceData(getListPreference());
        } else {
            this.mClickedDialogEntryIndex = savedInstanceState.getInt(SAVE_STATE_INDEX, 0);
            this.mEntries = savedInstanceState.getCharSequenceArrayList(SAVE_STATE_ENTRIES);
            this.mEntryValues = savedInstanceState.getCharSequenceArray(SAVE_STATE_ENTRY_VALUES);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_INDEX, this.mClickedDialogEntryIndex);
        outState.putCharSequenceArrayList(SAVE_STATE_ENTRIES, this.mEntries);
        outState.putCharSequenceArray(SAVE_STATE_ENTRY_VALUES, this.mEntryValues);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult && this.mClickedDialogEntryIndex >= 0) {
            ListPreference preference = getListPreference();
            String value = this.mEntryValues[this.mClickedDialogEntryIndex].toString();
            if (preference.callChangeListener(value)) {
                preference.setValue(value);
            }
        }
    }

    void setAdapter(ArrayAdapter adapter) {
        this.mAdapter = adapter;
    }

    void setEntries(ArrayList<CharSequence> entries) {
        this.mEntries = entries;
    }

    ArrayAdapter getAdapter() {
        return this.mAdapter;
    }

    void setMetricsCategory(Bundle bundle) {
        this.mMetricsCategory = bundle.getInt(METRICS_CATEGORY_KEY, 0);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        TypedArray a = getContext().obtainStyledAttributes(null, com.android.internal.R.styleable.AlertDialog, android.R.attr.alertDialogStyle, 0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), a.getResourceId(21, android.R.layout.select_dialog_singlechoice), this.mEntries);
        this.mAdapter = arrayAdapter;
        builder.setSingleChoiceItems(arrayAdapter, this.mClickedDialogEntryIndex, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.widget.-$$Lambda$UpdatableListPreferenceDialogFragment$AOz45Qsiv_Tk7V-zDyhpcuZlE0g
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onPrepareDialogBuilder$0$UpdatableListPreferenceDialogFragment(dialogInterface, i);
            }
        });
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
        a.recycle();
    }

    public /* synthetic */ void lambda$onPrepareDialogBuilder$0$UpdatableListPreferenceDialogFragment(DialogInterface dialog, int which) {
        this.mClickedDialogEntryIndex = which;
        onClick(dialog, -1);
        dialog.dismiss();
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return this.mMetricsCategory;
    }

    ListPreference getListPreference() {
        return (ListPreference) getPreference();
    }

    private void setPreferenceData(ListPreference preference) {
        this.mEntries.clear();
        this.mClickedDialogEntryIndex = preference.findIndexOfValue(preference.getValue());
        for (CharSequence entry : preference.getEntries()) {
            this.mEntries.add(entry);
        }
        this.mEntryValues = preference.getEntryValues();
    }

    public void onListPreferenceUpdated(ListPreference preference) {
        if (this.mAdapter != null) {
            setPreferenceData(preference);
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
