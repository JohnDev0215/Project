package com.amazon.mp3.library.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadCoordinatorConfigUpdater;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageLocationPreference;
import com.amazon.podcast.downloads.PodcastDownloader;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadSettingDialogFragment extends DialogFragment {
    private static final String TAG = DownloadSettingDialogFragment.class.getSimpleName();
    private boolean mDownloadNonAddedPrimeTracks;
    private Uri mDownloadUri;
    private NotificationInfo mNotificationInfo;
    private String mRequestId;
    private PodcastDownloader podcastDownloader;

    public static DownloadSettingDialogFragment newInstance(String requestId, Uri uri, NotificationInfo info, boolean downloadNonAddedPrimeTracks) {
        DownloadSettingDialogFragment downloadSettingDialogFragment = new DownloadSettingDialogFragment();
        downloadSettingDialogFragment.setDownloadInfo(requestId, uri, info, downloadNonAddedPrimeTracks);
        return downloadSettingDialogFragment;
    }

    private static DownloadSettingDialogFragment newInstance(final PodcastDownloader podcastDownloader) {
        DownloadSettingDialogFragment downloadSettingDialogFragment = new DownloadSettingDialogFragment();
        downloadSettingDialogFragment.setPodcastDownloader(podcastDownloader);
        return downloadSettingDialogFragment;
    }

    public static boolean shouldShowDownloadSettingsDialogFragment(FragmentActivity activity) {
        if (AmazonApplication.getCapabilities().secondaryStoragePresent(activity)) {
            return !activity.getSharedPreferences("com.amazon.mp3_DownloadSettingsDialogFragment", 0).getBoolean("settingsDialogShown", false);
        }
        return false;
    }

    public static void showSettingsDialog(final FragmentActivity activity, final String requestId, final Uri uri, final NotificationInfo notificationInfo, final boolean downloadNonAddedPrimeTracks) {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        if (supportFragmentManager != null) {
            supportFragmentManager.beginTransaction().add(newInstance(requestId, uri, notificationInfo, downloadNonAddedPrimeTracks), "TAG_DOWNLOAD_SETTINGS_DIALOG_FRAGMENT").commitAllowingStateLoss();
        }
    }

    public static void showSettingsDialog(final FragmentActivity activity, final PodcastDownloader downloader) {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        if (supportFragmentManager != null) {
            supportFragmentManager.beginTransaction().add(newInstance(downloader), "TAG_DOWNLOAD_SETTINGS_DIALOG_FRAGMENT").commitAllowingStateLoss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DownloadLocationDialogFragment);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.download_setting_dialog_fragment_layout, container, false);
        selectCurrentDownloadLocation(viewInflate);
        final StorageLocationPreference storageLocationPreference = new StorageLocationPreference(getActivity());
        CheckedTextView checkedTextView = (CheckedTextView) viewInflate.findViewById(R.id.DownloadSettingInternal);
        CheckedTextView checkedTextView2 = (CheckedTextView) viewInflate.findViewById(R.id.DownloadSettingExternal);
        updateSizeStrings(checkedTextView, checkedTextView2);
        checkedTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.DownloadSettingDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$1(storageLocationPreference, view);
            }
        });
        checkedTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.DownloadSettingDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$3(storageLocationPreference, view);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$1(StorageLocationPreference storageLocationPreference, View view) {
        getActivity().getSharedPreferences("com.amazon.mp3_DownloadSettingsDialogFragment", 0).edit().putBoolean("settingsDialogShown", true).apply();
        storageLocationPreference.setPreferredStorageLocation(StorageLocation.DEVICE);
        BaseMetricsRecorder.getMetricsRecorder(getContext().getApplicationContext()).getExternalStorageMetrics().recordDeviceChosen();
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadSettingDialogFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreateView$0();
            }
        });
        getDialog().dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0() {
        DownloadCoordinatorConfigUpdater.INSTANCE.updatePreferredStorageLocation();
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$3(StorageLocationPreference storageLocationPreference, View view) {
        getActivity().getSharedPreferences("com.amazon.mp3_DownloadSettingsDialogFragment", 0).edit().putBoolean("settingsDialogShown", true).apply();
        storageLocationPreference.setPreferredStorageLocation(StorageLocation.SDCARD);
        BaseMetricsRecorder.getMetricsRecorder(getContext().getApplicationContext()).getExternalStorageMetrics().recordSDCardChosen();
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.library.fragment.DownloadSettingDialogFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreateView$2();
            }
        });
        getDialog().dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$2() {
        DownloadCoordinatorConfigUpdater.INSTANCE.updatePreferredStorageLocation();
        startDownload();
    }

    private void selectCurrentDownloadLocation(View layout) {
        StorageLocationPreference storageLocationPreference = new StorageLocationPreference(getActivity());
        CheckedTextView checkedTextView = (CheckedTextView) layout.findViewById(R.id.DownloadSettingInternal);
        CheckedTextView checkedTextView2 = (CheckedTextView) layout.findViewById(R.id.DownloadSettingExternal);
        if (storageLocationPreference.getPreferredStorageLocation().equals(StorageLocation.DEVICE)) {
            checkedTextView.setChecked(true);
            checkedTextView2.setChecked(false);
        } else {
            checkedTextView2.setChecked(true);
            checkedTextView.setChecked(false);
        }
    }

    private void startDownload() {
        PodcastDownloader podcastDownloader = this.podcastDownloader;
        if (podcastDownloader != null) {
            podcastDownloader.startDownload();
        } else if (AmazonApplication.getContext() != null) {
            MusicDownloader.getInstance(AmazonApplication.getContext()).download(this.mRequestId, this.mDownloadUri, this.mNotificationInfo, this.mDownloadNonAddedPrimeTracks, true);
        }
    }

    private void setDownloadInfo(String requestId, Uri uri, NotificationInfo info, boolean downloadNonAddedPrimeTracks) {
        this.mRequestId = requestId;
        this.mDownloadUri = uri;
        this.mNotificationInfo = info;
        this.mDownloadNonAddedPrimeTracks = downloadNonAddedPrimeTracks;
    }

    private void setPodcastDownloader(final PodcastDownloader podcastDownloader) {
        this.podcastDownloader = podcastDownloader;
    }

    private void updateSizeStrings(TextView internalOptionView, TextView externalOptionView) {
        try {
            StorageLocationFileManager storageLocationFileManagerCreate = StorageLocationFileManager.create(getActivity(), Environment.DIRECTORY_MUSIC);
            internalOptionView.setText(getString(R.string.dmusic_setting_download_location_internal, DownloadLocationPreferenceActivity.getGigabytesStringFromBytes(storageLocationFileManagerCreate.getAvailableBytesAtStorageLocation(StorageLocation.DEVICE))));
            externalOptionView.setText(getString(R.string.dmusic_setting_download_location_external, DownloadLocationPreferenceActivity.getGigabytesStringFromBytes(storageLocationFileManagerCreate.getAvailableBytesAtStorageLocation(StorageLocation.SDCARD))));
        } catch (ExternalStorageNotSupportedException unused) {
            Log.warning(TAG, "External Storage not supported on this device.");
        } catch (FileNotFoundException unused2) {
            Log.warning(TAG, "Could not find SDCARD location.");
        }
    }
}
