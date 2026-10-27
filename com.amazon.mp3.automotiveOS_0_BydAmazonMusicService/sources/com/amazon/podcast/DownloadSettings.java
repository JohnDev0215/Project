package com.amazon.podcast;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.DownloadDialogActivity;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.GrantStoragePermissionActivity;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.podcast.bootstrap.DownloadSettingsProvider;
import com.amazon.podcast.downloads.PodcastDownloader;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadSettings extends DownloadSettingsProvider {
    private final WeakReference<FragmentActivity> weakFragmentActivity;

    public DownloadSettings(final FragmentActivity fragmentActivity) {
        this.weakFragmentActivity = new WeakReference<>(fragmentActivity);
    }

    @Override // com.amazon.podcast.bootstrap.DownloadSettingsProvider
    public boolean shouldShowDownloadSettings() {
        FragmentActivity fragmentActivity = this.weakFragmentActivity.get();
        boolean z = false;
        if (!ActivityKt.isAvailable(fragmentActivity)) {
            return false;
        }
        Context applicationContext = fragmentActivity.getApplicationContext();
        if (ConnectivityUtil.isWifiRequiredForDownloads(applicationContext) && !ConnectivityUtil.isWifiConnected(applicationContext)) {
            z = true;
        }
        if (z) {
            return true;
        }
        return DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(fragmentActivity);
    }

    @Override // com.amazon.podcast.bootstrap.DownloadSettingsProvider
    public void showDownloadSettings(final PodcastDownloader downloader) {
        FragmentActivity fragmentActivity = this.weakFragmentActivity.get();
        if (ActivityKt.isAvailable(fragmentActivity)) {
            Context applicationContext = fragmentActivity.getApplicationContext();
            if (ConnectivityUtil.isWifiRequiredForDownloads(applicationContext) && !ConnectivityUtil.isWifiConnected(applicationContext)) {
                Intent startIntent = DownloadDialogActivity.getStartIntent(applicationContext, 1);
                startIntent.setFlags(268435456);
                applicationContext.startActivity(startIntent);
                return;
            }
            DownloadSettingDialogFragment.showSettingsDialog(fragmentActivity, downloader);
        }
    }

    @Override // com.amazon.podcast.bootstrap.DownloadSettingsProvider
    public boolean shouldRequestStoragePermission() {
        FragmentActivity fragmentActivity = this.weakFragmentActivity.get();
        if (!ActivityKt.isAvailable(fragmentActivity)) {
            return false;
        }
        Context applicationContext = fragmentActivity.getApplicationContext();
        for (String str : MusicDownloader.PERMISSIONS_FOR_DOWNLOAD) {
            if (!ContextKt.isPermissionGranted(applicationContext, str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.podcast.bootstrap.DownloadSettingsProvider
    public void requestStoragePermission(final PodcastDownloader downloader) {
        FragmentActivity fragmentActivity = this.weakFragmentActivity.get();
        if (ActivityKt.isAvailable(fragmentActivity)) {
            GrantStoragePermissionActivity.requestIfNotGranted(fragmentActivity.getApplicationContext(), MusicDownloader.PERMISSIONS_FOR_DOWNLOAD, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.podcast.DownloadSettings.1
                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onDenied(String[] permissions) {
                }

                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onGranted() {
                    downloader.startDownload();
                }
            });
        }
    }
}
