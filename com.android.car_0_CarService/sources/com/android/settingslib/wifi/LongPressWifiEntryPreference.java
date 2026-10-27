package com.android.settingslib.wifi;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceViewHolder;
import com.android.wifitrackerlib.WifiEntry;

/* JADX INFO: loaded from: classes3.dex */
public class LongPressWifiEntryPreference extends WifiEntryPreference {
    private final Fragment mFragment;

    public LongPressWifiEntryPreference(Context context, WifiEntry wifiEntry, Fragment fragment) {
        super(context, wifiEntry);
        this.mFragment = fragment;
    }

    @Override // com.android.settingslib.wifi.WifiEntryPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        if (this.mFragment != null) {
            view.itemView.setOnCreateContextMenuListener(this.mFragment);
            view.itemView.setTag(this);
            view.itemView.setLongClickable(true);
        }
    }
}
