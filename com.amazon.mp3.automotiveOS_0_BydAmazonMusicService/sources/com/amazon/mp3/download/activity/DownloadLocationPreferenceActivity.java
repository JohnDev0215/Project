package com.amazon.mp3.download.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadCoordinatorConfigUpdater;
import com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity;
import com.amazon.mp3.library.provider.source.local.SDCardUtil;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.util.Log;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageLocationPreference;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadLocationPreferenceActivity extends BaseActivity {
    private static final String TAG = "DownloadLocationPreferenceActivity";
    ScrollView mContentView;
    CheckedTextView mDownloadExternal;
    CheckedTextView mDownloadInternal;
    View.OnClickListener mDownloadLocationSelected = new AnonymousClass1();
    StorageLocationFileManager mFileManager;
    ProgressBar mProgressBar;
    StorageLocationPreference mStorageLocationPreference;

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_settings_download_location_prefs);
        initialize();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mStorageLocationPreference = null;
        this.mFileManager = null;
        super.onDestroy();
    }

    public void initialize() {
        this.mStorageLocationPreference = new StorageLocationPreference(this);
        this.mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        this.mContentView = (ScrollView) findViewById(R.id.content_view);
        this.mDownloadInternal = (CheckedTextView) findViewById(R.id.DownloadLocationInternal);
        this.mDownloadExternal = (CheckedTextView) findViewById(R.id.DownloadLocationExternal);
        this.mDownloadInternal.setOnClickListener(this.mDownloadLocationSelected);
        this.mDownloadExternal.setOnClickListener(this.mDownloadLocationSelected);
    }

    private void updateView() {
        updateSizeStrings();
        StorageLocation preferredStorageLocation = this.mStorageLocationPreference.getPreferredStorageLocation();
        if (StorageLocation.DEVICE.equals(preferredStorageLocation)) {
            this.mDownloadInternal.setChecked(true);
            this.mDownloadExternal.setChecked(false);
        } else {
            if (StorageLocation.SDCARD.equals(preferredStorageLocation)) {
                this.mDownloadInternal.setChecked(false);
                this.mDownloadExternal.setChecked(true);
                return;
            }
            throw new IllegalArgumentException("Unknown streaming network pref value");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context applicationContext = view.getContext().getApplicationContext();
            DownloadLocationPreferenceActivity downloadLocationPreferenceActivity = DownloadLocationPreferenceActivity.this;
            if (view == downloadLocationPreferenceActivity.mDownloadInternal) {
                downloadLocationPreferenceActivity.mStorageLocationPreference.setPreferredStorageLocation(StorageLocation.DEVICE);
                BaseMetricsRecorder.getMetricsRecorder(applicationContext).getExternalStorageMetrics().recordDeviceChosen();
            } else if (view == downloadLocationPreferenceActivity.mDownloadExternal) {
                if (SDCardUtil.isSdCardReadOnly(downloadLocationPreferenceActivity)) {
                    SDCardUtil.showSdCardReadOnlyMessage(downloadLocationPreferenceActivity);
                }
                DownloadLocationPreferenceActivity.this.mStorageLocationPreference.setPreferredStorageLocation(StorageLocation.SDCARD);
                BaseMetricsRecorder.getMetricsRecorder(applicationContext).getExternalStorageMetrics().recordSDCardChosen();
            }
            DownloadLocationPreferenceActivity.this.getSharedPreferences("com.amazon.mp3_DownloadSettingsDialogFragment", 0).edit().putBoolean("settingsDialogShown", true).apply();
            AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DownloadLocationPreferenceActivity.AnonymousClass1.lambda$onClick$0();
                }
            });
            DownloadLocationPreferenceActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onClick$0() {
            DownloadCoordinatorConfigUpdater.INSTANCE.updatePreferredStorageLocation();
        }
    }

    private void updateSizeStrings() {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateSizeStrings$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSizeStrings$0() {
        try {
            updateTextViews(getGigabytesStringFromBytes(Long.valueOf(getFileManager().getAvailableBytesAtStorageLocation(StorageLocation.DEVICE)).longValue()), getGigabytesStringFromBytes(Long.valueOf(getFileManager().getAvailableBytesAtStorageLocation(StorageLocation.SDCARD)).longValue()));
        } catch (ExternalStorageNotSupportedException unused) {
            Log.warning(TAG, "External Storage not supported on this device.");
        } catch (FileNotFoundException unused2) {
            Log.warning(TAG, "Could not find SDCARD location.");
        }
    }

    private void updateTextViews(final String internalGigabytes, final String externalGigabytes) {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.download.activity.DownloadLocationPreferenceActivity.2
            @Override // java.lang.Runnable
            public void run() {
                DownloadLocationPreferenceActivity.this.mDownloadInternal.setText(DownloadLocationPreferenceActivity.this.getString(R.string.dmusic_setting_download_location_internal, internalGigabytes));
                DownloadLocationPreferenceActivity.this.mDownloadExternal.setText(DownloadLocationPreferenceActivity.this.getString(R.string.dmusic_setting_download_location_external, externalGigabytes));
                DownloadLocationPreferenceActivity.this.mProgressBar.setVisibility(8);
                DownloadLocationPreferenceActivity.this.mContentView.setVisibility(0);
            }
        });
    }

    private StorageLocationFileManager getFileManager() {
        if (this.mFileManager == null) {
            this.mFileManager = StorageLocationFileManager.create(this, Environment.DIRECTORY_MUSIC);
        }
        return this.mFileManager;
    }

    public static String getGigabytesStringFromBytes(long bytes) {
        return String.format("%.1f GB", Float.valueOf(bytes / 1.0737418E9f));
    }
}
