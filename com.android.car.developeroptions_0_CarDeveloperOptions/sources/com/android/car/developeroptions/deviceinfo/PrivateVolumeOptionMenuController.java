package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.storage.VolumeInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;
import com.android.settingslib.core.lifecycle.events.OnOptionsItemSelected;
import com.android.settingslib.core.lifecycle.events.OnPrepareOptionsMenu;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class PrivateVolumeOptionMenuController implements LifecycleObserver, OnCreateOptionsMenu, OnPrepareOptionsMenu, OnOptionsItemSelected {
    private Context mContext;
    private PackageManager mPm;
    private VolumeInfo mVolumeInfo;

    public PrivateVolumeOptionMenuController(Context context, VolumeInfo volumeInfo, PackageManager packageManager) {
        this.mContext = context;
        this.mVolumeInfo = volumeInfo;
        this.mPm = packageManager;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 100, 0, R.string.storage_menu_migrate);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPrepareOptionsMenu
    public void onPrepareOptionsMenu(Menu menu) {
        if (this.mVolumeInfo == null) {
            return;
        }
        VolumeInfo primaryStorageCurrentVolume = this.mPm.getPrimaryStorageCurrentVolume();
        MenuItem menuItemFindItem = menu.findItem(100);
        if (menuItemFindItem != null) {
            menuItemFindItem.setVisible(primaryStorageCurrentVolume != null && primaryStorageCurrentVolume.getType() == 1 && !Objects.equals(this.mVolumeInfo, primaryStorageCurrentVolume) && primaryStorageCurrentVolume.isMountedWritable());
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnOptionsItemSelected
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return false;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) StorageWizardMigrateConfirm.class);
        intent.putExtra("android.os.storage.extra.VOLUME_ID", this.mVolumeInfo.getId());
        this.mContext.startActivity(intent);
        return true;
    }
}
