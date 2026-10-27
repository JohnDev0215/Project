package com.android.car.settings.sound;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.car.ui.preference.CarUiPreference;
import com.android.internal.R;

/* JADX INFO: loaded from: classes5.dex */
public class RingtonePreference extends CarUiPreference {
    private int mRingtoneType;
    private boolean mShowSilent;

    public RingtonePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RingtonePreference, 0, 0);
        this.mRingtoneType = a.getInt(0, 1);
        this.mShowSilent = a.getBoolean(2, true);
        setIntent(new Intent("android.intent.action.RINGTONE_PICKER"));
        a.recycle();
    }

    public int getRingtoneType() {
        return this.mRingtoneType;
    }

    public void setRingtoneType(int type) {
        this.mRingtoneType = type;
    }

    public boolean getShowSilent() {
        return this.mShowSilent;
    }

    public void setShowSilent(boolean showSilent) {
        this.mShowSilent = showSilent;
    }
}
