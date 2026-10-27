package com.android.car.developeroptions.dream;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.widget.CandidateInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WhenToDreamPicker extends RadioButtonPickerFragment {
    private DreamBackend mBackend;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 47;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.when_to_dream_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mBackend = DreamBackend.getInstance(context);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        String[] strArrEntries = entries();
        String[] strArrKeys = keys();
        ArrayList arrayList = new ArrayList();
        if (strArrEntries == null || strArrEntries.length <= 0) {
            return null;
        }
        if (strArrKeys == null || strArrKeys.length != strArrEntries.length) {
            throw new IllegalArgumentException("Entries and values must be of the same length.");
        }
        for (int i = 0; i < strArrEntries.length; i++) {
            arrayList.add(new WhenToDreamCandidateInfo(this, strArrEntries[i], strArrKeys[i]));
        }
        return arrayList;
    }

    private String[] entries() {
        return getResources().getStringArray(R.array.when_to_start_screensaver_entries);
    }

    private String[] keys() {
        return getResources().getStringArray(R.array.when_to_start_screensaver_values);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        return DreamSettings.getKeyFromSetting(this.mBackend.getWhenToDreamSetting());
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        this.mBackend.setWhenToDream(DreamSettings.getSettingFromPrefKey(str));
        return true;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void onSelectionPerformed(boolean z) {
        super.onSelectionPerformed(z);
        getActivity().finish();
    }

    private final class WhenToDreamCandidateInfo extends CandidateInfo {
        private final String key;
        private final String name;

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return null;
        }

        WhenToDreamCandidateInfo(WhenToDreamPicker whenToDreamPicker, String str, String str2) {
            super(true);
            this.name = str;
            this.key = str2;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.name;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.key;
        }
    }
}
