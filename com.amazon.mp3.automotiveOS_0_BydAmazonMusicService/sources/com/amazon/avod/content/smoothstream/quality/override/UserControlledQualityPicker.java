package com.amazon.avod.content.smoothstream.quality.override;

import android.R;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;

/* JADX INFO: loaded from: classes2.dex */
public class UserControlledQualityPicker extends OverrideQualityPickerBase {
    private final Spinner mQualityPickerSpinner;

    public UserControlledQualityPicker(QualityLevelClamperFactory qualityLevelClamperFactory, Spinner spinner) {
        super(qualityLevelClamperFactory);
        this.mQualityPickerSpinner = spinner;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.override.OverrideQualityPickerBase, com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        super.initialize(contentSessionContext);
        this.mQualityPickerSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this.mQualityPickerSpinner.getContext(), R.layout.simple_spinner_dropdown_item, this.mFilteredSortedVideoQualityLevels));
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        return (QualityLevel) this.mQualityPickerSpinner.getSelectedItem();
    }
}
