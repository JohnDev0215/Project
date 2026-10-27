package com.android.car.developeroptions.slices;

import android.content.Context;
import android.util.AttributeSet;
import androidx.slice.Slice;
import androidx.slice.widget.SliceView;
import com.android.car.developeroptions.R;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class SlicePreference extends LayoutPreference {
    private SliceView mSliceView;

    public SlicePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.slicePreferenceStyle);
        init();
    }

    public SlicePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        SliceView sliceView = (SliceView) findViewById(R.id.slice_view);
        this.mSliceView = sliceView;
        sliceView.showTitleItems(true);
        this.mSliceView.setScrollable(false);
    }

    public void onSliceUpdated(Slice slice) {
        this.mSliceView.onChanged(slice);
        notifyChanged();
    }
}
