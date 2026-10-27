package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.utils.FileSizeFormatter;

/* JADX INFO: loaded from: classes.dex */
public class StorageItemPreference extends Preference {
    private ProgressBar mProgressBar;
    private int mProgressPercent;
    public int userHandle;

    public StorageItemPreference(Context context) {
        this(context, null);
    }

    public StorageItemPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgressPercent = -1;
        setLayoutResource(R.layout.storage_item);
        setSummary(R.string.memory_calculating_size);
    }

    public void setStorageSize(long j, long j2) {
        setSummary(FileSizeFormatter.formatFileSize(getContext(), j, getGigabyteSuffix(getContext().getResources()), 1000000000L));
        if (j2 == 0) {
            this.mProgressPercent = 0;
        } else {
            this.mProgressPercent = (int) ((j * 100) / j2);
        }
        updateProgressBar();
    }

    protected void updateProgressBar() {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar == null || this.mProgressPercent == -1) {
            return;
        }
        progressBar.setMax(100);
        this.mProgressBar.setProgress(this.mProgressPercent);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        this.mProgressBar = (ProgressBar) preferenceViewHolder.findViewById(android.R.id.progress);
        updateProgressBar();
        super.onBindViewHolder(preferenceViewHolder);
    }

    private static int getGigabyteSuffix(Resources resources) {
        return resources.getIdentifier("gigabyteShort", "string", "android");
    }
}
