package com.android.car.developeroptions.deviceinfo;

import android.R;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.deviceinfo.StorageSettings;
import com.android.settingslib.Utils;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class StorageVolumePreference extends Preference {
    private static final String TAG = StorageVolumePreference.class.getSimpleName();
    private ColorStateList mColorTintList;
    private final StorageManager mStorageManager;
    private final View.OnClickListener mUnmountListener;
    private int mUsedPercent;
    private final VolumeInfo mVolume;

    public StorageVolumePreference(Context context, VolumeInfo volumeInfo, long j) {
        Drawable drawable;
        long totalSpace;
        long freeSpace;
        long j2;
        super(context);
        this.mUsedPercent = -1;
        this.mUnmountListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.deviceinfo.StorageVolumePreference.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new StorageSettings.UnmountTask(StorageVolumePreference.this.getContext(), StorageVolumePreference.this.mVolume).execute(new Void[0]);
            }
        };
        this.mStorageManager = (StorageManager) context.getSystemService(StorageManager.class);
        this.mVolume = volumeInfo;
        this.mColorTintList = Utils.getColorAttr(context, R.attr.colorControlNormal);
        setLayoutResource(com.android.car.developeroptions.R.layout.storage_volume);
        setKey(volumeInfo.getId());
        setTitle(this.mStorageManager.getBestVolumeDescription(volumeInfo));
        if ("private".equals(volumeInfo.getId())) {
            drawable = context.getDrawable(com.android.car.developeroptions.R.drawable.ic_storage);
        } else {
            drawable = context.getDrawable(com.android.car.developeroptions.R.drawable.ic_sim_sd);
        }
        if (volumeInfo.isMountedReadable()) {
            File path = volumeInfo.getPath();
            if (volumeInfo.getType() == 1) {
                StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService(StorageStatsManager.class);
                try {
                    totalSpace = storageStatsManager.getTotalBytes(volumeInfo.getFsUuid());
                    try {
                        freeSpace = storageStatsManager.getFreeBytes(volumeInfo.getFsUuid());
                    } catch (IOException e) {
                        e = e;
                        Log.w(TAG, e);
                        freeSpace = 0;
                        j2 = 0;
                    }
                } catch (IOException e2) {
                    e = e2;
                    totalSpace = j;
                }
            } else {
                totalSpace = j <= 0 ? path.getTotalSpace() : j;
                freeSpace = path.getFreeSpace();
            }
            j2 = totalSpace - freeSpace;
            setSummary(context.getString(com.android.car.developeroptions.R.string.storage_volume_summary, Formatter.formatFileSize(context, j2), Formatter.formatFileSize(context, totalSpace)));
            if (totalSpace > 0) {
                this.mUsedPercent = (int) ((j2 * 100) / totalSpace);
            }
            if (freeSpace < this.mStorageManager.getStorageLowBytes(path)) {
                this.mColorTintList = Utils.getColorAttr(context, R.attr.colorError);
                Drawable drawable2 = context.getDrawable(com.android.car.developeroptions.R.drawable.ic_warning_24dp);
                drawable2.mutate();
                drawable2.setTintList(this.mColorTintList);
                drawable = drawable2;
            }
        } else {
            setSummary(volumeInfo.getStateDescription());
            this.mUsedPercent = -1;
        }
        setIcon(drawable);
        if (volumeInfo.getType() == 0 && volumeInfo.isMountedReadable()) {
            setWidgetLayoutResource(com.android.car.developeroptions.R.layout.preference_storage_action);
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(com.android.car.developeroptions.R.id.unmount);
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(Color.parseColor("#8a000000")));
            imageView.setOnClickListener(this.mUnmountListener);
        }
        ProgressBar progressBar = (ProgressBar) preferenceViewHolder.findViewById(R.id.progress);
        if (this.mVolume.getType() == 1 && this.mUsedPercent != -1) {
            progressBar.setVisibility(0);
            progressBar.setProgress(this.mUsedPercent);
            progressBar.setProgressTintList(this.mColorTintList);
        } else {
            progressBar.setVisibility(8);
        }
        super.onBindViewHolder(preferenceViewHolder);
    }
}
