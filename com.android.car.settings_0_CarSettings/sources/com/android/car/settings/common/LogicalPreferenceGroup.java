package com.android.car.settings.common;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public class LogicalPreferenceGroup extends PreferenceGroup {
    public LogicalPreferenceGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLayoutResource(R.layout.logical_preference_group);
    }

    public LogicalPreferenceGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LogicalPreferenceGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogicalPreferenceGroup(Context context) {
        this(context, null);
    }
}
