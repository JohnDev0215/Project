package com.android.car.developeroptions.deviceinfo;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class StorageWizardMoveProgress extends StorageWizardBase {
    private final PackageManager.MoveCallback mCallback = new PackageManager.MoveCallback() { // from class: com.android.car.developeroptions.deviceinfo.StorageWizardMoveProgress.1
        public void onStatusChanged(int i, int i2, long j) {
            if (StorageWizardMoveProgress.this.mMoveId != i) {
                return;
            }
            if (PackageManager.isMoveStatusFinished(i2)) {
                Log.d("StorageSettings", "Finished with status " + i2);
                if (i2 != -100) {
                    StorageWizardMoveProgress storageWizardMoveProgress = StorageWizardMoveProgress.this;
                    Toast.makeText(storageWizardMoveProgress, storageWizardMoveProgress.moveStatusToMessage(i2), 1).show();
                }
                StorageWizardMoveProgress.this.finishAffinity();
                return;
            }
            StorageWizardMoveProgress.this.setCurrentProgress(i2);
        }
    };
    private int mMoveId;

    @Override // com.android.car.developeroptions.deviceinfo.StorageWizardBase, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mVolume == null) {
            finish();
            return;
        }
        setContentView(R.layout.storage_wizard_progress);
        this.mMoveId = getIntent().getIntExtra("android.content.pm.extra.MOVE_ID", -1);
        String stringExtra = getIntent().getStringExtra("android.intent.extra.TITLE");
        String bestVolumeDescription = this.mStorage.getBestVolumeDescription(this.mVolume);
        setIcon(R.drawable.ic_swap_horiz);
        setHeaderText(R.string.storage_wizard_move_progress_title, stringExtra);
        setBodyText(R.string.storage_wizard_move_progress_body, bestVolumeDescription, stringExtra);
        getPackageManager().registerMoveCallback(this.mCallback, new Handler());
        this.mCallback.onStatusChanged(this.mMoveId, getPackageManager().getMoveStatus(this.mMoveId), -1L);
    }

    @Override // com.android.car.developeroptions.deviceinfo.StorageWizardBase, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        getPackageManager().unregisterMoveCallback(this.mCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CharSequence moveStatusToMessage(int i) {
        if (i == -8) {
            return getString(R.string.move_error_device_admin);
        }
        if (i == -5) {
            return getString(R.string.invalid_location);
        }
        if (i == -3) {
            return getString(R.string.system_package);
        }
        if (i == -2) {
            return getString(R.string.does_not_exist);
        }
        if (i == -1) {
            return getString(R.string.insufficient_storage);
        }
        return getString(R.string.insufficient_storage);
    }
}
