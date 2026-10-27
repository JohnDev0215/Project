package com.android.permissioncontroller.role.ui;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes8.dex */
public abstract class TwoTargetPreference extends Preference {

    public interface OnSecondTargetClickListener {
        void onSecondTargetClick(TwoTargetPreference twoTargetPreference);
    }

    public abstract void setOnSecondTargetClickListener(OnSecondTargetClickListener onSecondTargetClickListener);

    public TwoTargetPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TwoTargetPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TwoTargetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TwoTargetPreference(Context context) {
        super(context);
    }
}
