package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.internal.widget.SubtitleView;

/* JADX INFO: loaded from: classes.dex */
public class EdgeTypePreference extends ListDialogPreference {
    public EdgeTypePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        setValues(resources.getIntArray(R.array.captioning_edge_type_selector_values));
        setTitles(resources.getStringArray(R.array.captioning_edge_type_selector_titles));
        setDialogLayoutResource(R.layout.grid_picker_dialog);
        setListItemLayoutResource(R.layout.preset_picker_item);
    }

    @Override // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return getValue() == 0 || super.shouldDisableDependents();
    }

    @Override // com.android.car.developeroptions.accessibility.ListDialogPreference
    protected void onBindListItem(View view, int i) {
        SubtitleView subtitleViewFindViewById = view.findViewById(R.id.preview);
        subtitleViewFindViewById.setForegroundColor(-1);
        subtitleViewFindViewById.setBackgroundColor(0);
        subtitleViewFindViewById.setTextSize(getContext().getResources().getDisplayMetrics().density * 32.0f);
        subtitleViewFindViewById.setEdgeType(getValueAt(i));
        subtitleViewFindViewById.setEdgeColor(-16777216);
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(R.id.summary)).setText(titleAt);
        }
    }
}
