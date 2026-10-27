package com.amazon.mp3.account.signout;

import android.app.IntentService;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.download.DownloadCoordinatorProvider;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;

/* JADX INFO: compiled from: RemoveAllDownloadsService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/account/signout/RemoveAllDownloadsService;", "Landroid/app/IntentService;", "()V", "onCreate", "", "onHandleIntent", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RemoveAllDownloadsService extends IntentService {
    private static final String TAG = RemoveAllDownloadsService.class.getSimpleName();

    public RemoveAllDownloadsService() {
        super(TAG);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        ServiceUtil.startNotification(getApplicationContext(), this);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String str = TAG;
        Log.info(str, "Handling intent for RemoveAllDownloadsService.");
        if (intent == null) {
            return;
        }
        DownloadCoordinatorProvider downloadCoordinatorProvider = DownloadCoordinatorProvider.INSTANCE;
        if (downloadCoordinatorProvider.getDownloadCoordinatorWrapper().removeAllDRMDownloads()) {
            Log.info(str, "Successfully deleted all DRM downloads.");
        } else {
            Log.error(str, "Error while deleting all DRM downloads.");
        }
        downloadCoordinatorProvider.resetDownloadCoordinator();
    }
}
