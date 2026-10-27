package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.internal.widget.SubtitleView;

/* JADX INFO: loaded from: classes.dex */
public class PresetPreference extends ListDialogPreference {
    private final CaptioningManager mCaptioningManager;

    public PresetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.grid_picker_dialog);
        setListItemLayoutResource(R.layout.preset_picker_item);
        this.mCaptioningManager = (CaptioningManager) context.getSystemService("captioning");
    }

    @Override // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return getValue() != -1 || super.shouldDisableDependents();
    }

    @Override // com.android.car.developeroptions.accessibility.ListDialogPreference
    protected void onBindListItem(View view, int i) {
        View viewFindViewById = view.findViewById(R.id.preview_viewport);
        SubtitleView subtitleViewFindViewById = view.findViewById(R.id.preview);
        CaptionPropertiesFragment.applyCaptionProperties(this.mCaptioningManager, subtitleViewFindViewById, viewFindViewById, getValueAt(i));
        subtitleViewFindViewById.setTextSize(getContext().getResources().getDisplayMetrics().density * 32.0f);
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(R.id.summary)).setText(titleAt);
        }
    }
}
