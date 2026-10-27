package com.android.car.developeroptions.accounts;

import android.R;
import android.util.Log;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/* JADX INFO: loaded from: classes.dex */
public class AccountPreference extends Preference {
    private boolean mShowTypeIcon;
    private int mStatus;
    private ImageView mSyncStatusIcon;

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mShowTypeIcon) {
            return;
        }
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.icon);
        this.mSyncStatusIcon = imageView;
        imageView.setImageResource(getSyncStatusIcon(this.mStatus));
        this.mSyncStatusIcon.setContentDescription(getSyncContentDescription(this.mStatus));
    }

    private int getSyncStatusIcon(int i) {
        if (i != 0) {
            if (i == 1) {
                return com.android.car.developeroptions.R.drawable.ic_sync_grey_holo;
            }
            if (i == 2) {
                return com.android.car.developeroptions.R.drawable.ic_sync_red_holo;
            }
            if (i != 3) {
                Log.e("AccountPreference", "Unknown sync status: " + i);
                return com.android.car.developeroptions.R.drawable.ic_sync_red_holo;
            }
        }
        return com.android.car.developeroptions.R.drawable.ic_settings_sync;
    }

    private String getSyncContentDescription(int i) {
        if (i == 0) {
            return getContext().getString(com.android.car.developeroptions.R.string.accessibility_sync_enabled);
        }
        if (i == 1) {
            return getContext().getString(com.android.car.developeroptions.R.string.accessibility_sync_disabled);
        }
        if (i == 2) {
            return getContext().getString(com.android.car.developeroptions.R.string.accessibility_sync_error);
        }
        if (i == 3) {
            return getContext().getString(com.android.car.developeroptions.R.string.accessibility_sync_in_progress);
        }
        Log.e("AccountPreference", "Unknown sync status: " + i);
        return getContext().getString(com.android.car.developeroptions.R.string.accessibility_sync_error);
    }
}
