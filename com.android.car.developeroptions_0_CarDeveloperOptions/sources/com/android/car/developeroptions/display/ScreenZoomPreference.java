package com.android.car.developeroptions.display;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.settingslib.display.DisplayDensityUtils;

/* JADX INFO: loaded from: classes.dex */
public class ScreenZoomPreference extends Preference {
    public ScreenZoomPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
        DisplayDensityUtils displayDensityUtils = new DisplayDensityUtils(context);
        if (displayDensityUtils.getCurrentIndex() < 0) {
            setVisible(false);
            setEnabled(false);
        } else if (TextUtils.isEmpty(getSummary())) {
            setSummary(displayDensityUtils.getEntries()[displayDensityUtils.getCurrentIndex()]);
        }
    }
}
