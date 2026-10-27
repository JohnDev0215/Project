package com.android.car.developeroptions.dream;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dream.CurrentDreamPicker;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.widget.CandidateInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public final class CurrentDreamPicker extends RadioButtonPickerFragment {
    private DreamBackend mBackend;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 47;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.current_dream_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mBackend = DreamBackend.getInstance(context);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        Map<String, ComponentName> dreamComponentsMap = getDreamComponentsMap();
        if (dreamComponentsMap.get(str) == null) {
            return false;
        }
        this.mBackend.setActiveDream(dreamComponentsMap.get(str));
        return true;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        return this.mBackend.getActiveDream().flattenToString();
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        return (List) this.mBackend.getDreamInfos().stream().map(new Function() { // from class: com.android.car.developeroptions.dream.-$$Lambda$wKVzmme7bKNW0vOMwPmwfmrBidU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new CurrentDreamPicker.DreamCandidateInfo((DreamBackend.DreamInfo) obj);
            }
        }).collect(Collectors.toList());
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void onSelectionPerformed(boolean z) {
        super.onSelectionPerformed(z);
        getActivity().finish();
    }

    private Map<String, ComponentName> getDreamComponentsMap() {
        final HashMap map = new HashMap();
        this.mBackend.getDreamInfos().forEach(new Consumer() { // from class: com.android.car.developeroptions.dream.-$$Lambda$CurrentDreamPicker$efDuX6uRN8QLXl1bq3Vk1O50zBw
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DreamBackend.DreamInfo dreamInfo = (DreamBackend.DreamInfo) obj;
                map.put(dreamInfo.componentName.flattenToString(), dreamInfo.componentName);
            }
        });
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class DreamCandidateInfo extends CandidateInfo {
        private final Drawable icon;
        private final String key;
        private final CharSequence name;

        DreamCandidateInfo(DreamBackend.DreamInfo dreamInfo) {
            super(true);
            this.name = dreamInfo.caption;
            this.icon = dreamInfo.icon;
            this.key = dreamInfo.componentName.flattenToString();
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.name;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return this.icon;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.key;
        }
    }
}
