package com.android.car.developeroptions.deviceinfo;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.os.storage.VolumeInfo;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
public class StorageWizardInit extends StorageWizardBase {
    private Button mInternal;
    private boolean mIsPermittedToAdopt;

    @Override // com.android.car.developeroptions.deviceinfo.StorageWizardBase, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mDisk == null) {
            finish();
            return;
        }
        setContentView(R.layout.storage_wizard_init);
        this.mIsPermittedToAdopt = UserManager.get(this).isAdminUser() && !ActivityManager.isUserAMonkey();
        setHeaderText(R.string.storage_wizard_init_v2_title, getDiskShortDescription());
        this.mInternal = (Button) requireViewById(R.id.storage_wizard_init_internal);
        setBackButtonText(R.string.storage_wizard_init_v2_later, new CharSequence[0]);
        if (!this.mDisk.isAdoptable()) {
            this.mInternal.setEnabled(false);
            onNavigateExternal(null);
        } else {
            if (this.mIsPermittedToAdopt) {
                return;
            }
            this.mInternal.setEnabled(false);
        }
    }

    @Override // com.android.car.developeroptions.deviceinfo.StorageWizardBase
    public void onNavigateBack(View view) {
        finish();
    }

    public void onNavigateExternal(View view) {
        if (view != null) {
            FeatureFactory.getFactory(this).getMetricsFeatureProvider().action(this, 1407, new Pair[0]);
        }
        VolumeInfo volumeInfo = this.mVolume;
        if (volumeInfo != null && volumeInfo.getType() == 0 && this.mVolume.getState() != 6) {
            this.mStorage.setVolumeInited(this.mVolume.getFsUuid(), true);
            Intent intent = new Intent(this, (Class<?>) StorageWizardReady.class);
            intent.putExtra("android.os.storage.extra.DISK_ID", this.mDisk.getId());
            startActivity(intent);
            finish();
            return;
        }
        StorageWizardFormatConfirm.showPublic(this, this.mDisk.getId());
    }

    public void onNavigateInternal(View view) {
        if (view != null) {
            FeatureFactory.getFactory(this).getMetricsFeatureProvider().action(this, 1408, new Pair[0]);
        }
        StorageWizardFormatConfirm.showPrivate(this, this.mDisk.getId());
    }
}
