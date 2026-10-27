package com.android.car.developeroptions.widget;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceDialogFragmentCompat;
import com.android.internal.R;
import com.android.settingslib.core.instrumentation.Instrumentable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class UpdatableListPreferenceDialogFragment extends PreferenceDialogFragmentCompat implements Instrumentable {
    private ArrayAdapter mAdapter;
    private int mClickedDialogEntryIndex;
    private ArrayList<CharSequence> mEntries;
    private CharSequence[] mEntryValues;
    private int mMetricsCategory = 0;

    public static UpdatableListPreferenceDialogFragment newInstance(String str, int i) {
        UpdatableListPreferenceDialogFragment updatableListPreferenceDialogFragment = new UpdatableListPreferenceDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", str);
        bundle.putInt("metrics_category_key", i);
        updatableListPreferenceDialogFragment.setArguments(bundle);
        return updatableListPreferenceDialogFragment;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMetricsCategory = getArguments().getInt("metrics_category_key", 0);
        if (bundle == null) {
            this.mEntries = new ArrayList<>();
            setPreferenceData(getListPreference());
        } else {
            this.mClickedDialogEntryIndex = bundle.getInt("UpdatableListPreferenceDialogFragment.index", 0);
            this.mEntries = bundle.getCharSequenceArrayList("UpdatableListPreferenceDialogFragment.entries");
            this.mEntryValues = bundle.getCharSequenceArray("UpdatableListPreferenceDialogFragment.entryValues");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("UpdatableListPreferenceDialogFragment.index", this.mClickedDialogEntryIndex);
        bundle.putCharSequenceArrayList("UpdatableListPreferenceDialogFragment.entries", this.mEntries);
        bundle.putCharSequenceArray("UpdatableListPreferenceDialogFragment.entryValues", this.mEntryValues);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public void onDialogClosed(boolean z) {
        if (!z || this.mClickedDialogEntryIndex < 0) {
            return;
        }
        ListPreference listPreference = getListPreference();
        String string = this.mEntryValues[this.mClickedDialogEntryIndex].toString();
        if (listPreference.callChangeListener(string)) {
            listPreference.setValue(string);
        }
    }

    void setAdapter(ArrayAdapter arrayAdapter) {
        this.mAdapter = arrayAdapter;
    }

    void setEntries(ArrayList<CharSequence> arrayList) {
        this.mEntries = arrayList;
    }

    ArrayAdapter getAdapter() {
        return this.mAdapter;
    }

    void setMetricsCategory(Bundle bundle) {
        this.mMetricsCategory = bundle.getInt("metrics_category_key", 0);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.AlertDialog, android.R.attr.alertDialogStyle, 0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), typedArrayObtainStyledAttributes.getResourceId(21, android.R.layout.select_dialog_singlechoice), this.mEntries);
        this.mAdapter = arrayAdapter;
        builder.setSingleChoiceItems(arrayAdapter, this.mClickedDialogEntryIndex, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$UpdatableListPreferenceDialogFragment$24d-Axa85hPVgWdJ7iL-TMttiCY
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onPrepareDialogBuilder$0$UpdatableListPreferenceDialogFragment(dialogInterface, i);
            }
        });
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onPrepareDialogBuilder$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onPrepareDialogBuilder$0$UpdatableListPreferenceDialogFragment(DialogInterface dialogInterface, int i) {
        this.mClickedDialogEntryIndex = i;
        onClick(dialogInterface, -1);
        dialogInterface.dismiss();
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return this.mMetricsCategory;
    }

    ListPreference getListPreference() {
        return (ListPreference) getPreference();
    }

    private void setPreferenceData(ListPreference listPreference) {
        this.mEntries.clear();
        this.mClickedDialogEntryIndex = listPreference.findIndexOfValue(listPreference.getValue());
        for (CharSequence charSequence : listPreference.getEntries()) {
            this.mEntries.add(charSequence);
        }
        this.mEntryValues = listPreference.getEntryValues();
    }

    public void onListPreferenceUpdated(ListPreference listPreference) {
        if (this.mAdapter != null) {
            setPreferenceData(listPreference);
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
