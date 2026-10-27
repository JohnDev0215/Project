package com.android.settingslib.widget.settingsspinner;

import android.R;
import android.content.Context;
import android.widget.ArrayAdapter;
import com.android.settingslib.widget.R$layout;

/* JADX INFO: loaded from: classes.dex */
public class SettingsSpinnerAdapter<T> extends ArrayAdapter<T> {
    public SettingsSpinnerAdapter(Context context) {
        super(context, R$layout.settings_spinner_view);
        setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
    }
}
