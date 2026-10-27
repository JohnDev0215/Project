package com.android.car.developeroptions.display;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.settingslib.widget.CandidateInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VrDisplayPreferencePicker extends RadioButtonPickerFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 921;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.vr_display_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<VrCandidateInfo> getCandidates() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        arrayList.add(new VrCandidateInfo(context, 0, R.string.display_vr_pref_low_persistence));
        arrayList.add(new VrCandidateInfo(context, 1, R.string.display_vr_pref_off));
        return arrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        return "vr_display_pref_" + Settings.Secure.getIntForUser(getContext().getContentResolver(), "vr_display_mode", 0, this.mUserId);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case 1581655828:
                if (str.equals("vr_display_pref_0")) {
                    b = 0;
                }
                break;
            case 1581655829:
                if (str.equals("vr_display_pref_1")) {
                    b = 1;
                }
                break;
        }
        if (b == 0) {
            return Settings.Secure.putIntForUser(getContext().getContentResolver(), "vr_display_mode", 0, this.mUserId);
        }
        if (b != 1) {
            return false;
        }
        return Settings.Secure.putIntForUser(getContext().getContentResolver(), "vr_display_mode", 1, this.mUserId);
    }

    static class VrCandidateInfo extends CandidateInfo {
        public final String label;
        public final int value;

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return null;
        }

        public VrCandidateInfo(Context context, int i, int i2) {
            super(true);
            this.value = i;
            this.label = context.getString(i2);
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.label;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return "vr_display_pref_" + this.value;
        }
    }
}
