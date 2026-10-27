package com.amazon.mp3.offline;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.GrantStoragePermissionActivity;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OfflineStoragePermissionHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/offline/OfflineStoragePermissionHandler;", "", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "displayPermissionRequestIfNeeded", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class OfflineStoragePermissionHandler {
    public static final OfflineStoragePermissionHandler INSTANCE = new OfflineStoragePermissionHandler();

    private OfflineStoragePermissionHandler() {
    }

    public final void displayPermissionRequestIfNeeded(final FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        GrantStoragePermissionActivity.requestIfNotGranted(activity, MusicDownloader.PERMISSIONS_FOR_DOWNLOAD, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.offline.OfflineStoragePermissionHandler$displayPermissionRequestIfNeeded$resultReceiver$1
            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onGranted() {
                SharedPreferences sharedPreferences = activity.getSharedPreferences(OfflineStoragePermissionHandler.class.getSimpleName(), 0);
                if (sharedPreferences.getBoolean("first_time_offline_permission_granted_key", true)) {
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putBoolean("first_time_offline_permission_granted_key", false);
                    editorEdit.apply();
                    SyncService.startSync(activity, 667);
                }
            }

            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onDenied(String[] permissions) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                Log.warning(OfflineStoragePermissionHandler.class.getSimpleName(), "Do not have permission to view offline files!");
            }
        });
    }
}
