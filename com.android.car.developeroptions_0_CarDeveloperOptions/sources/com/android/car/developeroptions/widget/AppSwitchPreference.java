package com.android.car.developeroptions.widget;

import android.content.Context;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class AppSwitchPreference extends SwitchPreference {
    public AppSwitchPreference(Context context) {
        super(context);
        setLayoutResource(R.layout.preference_app);
    }
}
