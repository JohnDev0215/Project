package com.amazon.mp3.library.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.Log;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public abstract class LibraryBaseActivity extends BaseActivity {
    private static final IntentFilter GENERAL_ACTION_FILTER;
    private static final IntentFilter MEDIA_ACTION_FILTER;
    private static final String TAG = "LibraryBaseActivity";
    private static final HashSet<String> VALID_REMEMBERED_SOURCE_IDS;
    private ProgressDialog mProgressDialog;
    private String mProgressMessage;
    private boolean mSaveSourceOnResume;
    private String mSourceId;
    protected Handler mHandler = new Handler();
    private Runnable mShowProgressRunnable = new Runnable() { // from class: com.amazon.mp3.library.activity.LibraryBaseActivity.1
        @Override // java.lang.Runnable
        public void run() {
            if (LibraryBaseActivity.this.isPaused() || LibraryBaseActivity.this.mProgressDialog == null) {
                return;
            }
            LibraryBaseActivity.this.mProgressDialog.show();
            LibraryBaseActivity libraryBaseActivity = LibraryBaseActivity.this;
            libraryBaseActivity.mHandler.postDelayed(libraryBaseActivity.mAllowProgressCancelRunnable, 10000L);
        }
    };
    private Runnable mAllowProgressCancelRunnable = new Runnable() { // from class: com.amazon.mp3.library.activity.LibraryBaseActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (LibraryBaseActivity.this.mProgressDialog != null) {
                LibraryBaseActivity.this.mProgressDialog.setCancelable(true);
            }
        }
    };
    private BroadcastReceiver mGeneralBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.activity.LibraryBaseActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.amazon.mp3.ACTION_CACHE_CLEARED") && LibraryBaseActivity.this.finishOnCirrusDatabaseCleared() && (intent.getIntExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", -108809) & 8) != 0) {
                LibraryBaseActivity.this.finish();
            }
        }
    };
    private BroadcastReceiver mMediaActionReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.activity.LibraryBaseActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                LibraryBaseActivity.this.onMediaMounted(intent.getData());
            } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                LibraryBaseActivity.this.onMediaUnmounted(intent.getData());
            }
        }
    };

    protected void onMediaMounted(Uri mountpoint) {
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle instanceState) {
        setVolumeControlStream(3);
        super.onCreate(instanceState);
        registerReceiver(this.mGeneralBroadcastReceiver, GENERAL_ACTION_FILTER);
        registerReceiver(this.mMediaActionReceiver, MEDIA_ACTION_FILTER);
        this.mSourceId = getIntent().getStringExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        this.mSaveSourceOnResume = getIntent().getBooleanExtra("com.amazon.mp3.library.EXTRA_SAVE_SOURCE_ON_RESUME_ID", true);
    }

    public static boolean isValidLibrarySource(String sourceId) {
        return VALID_REMEMBERED_SOURCE_IDS.contains(sourceId);
    }

    protected String getSourceId() {
        return this.mSourceId;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        unregisterReceiver(this.mGeneralBroadcastReceiver);
        unregisterReceiver(this.mMediaActionReceiver);
        super.onDestroy();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        super.onPause();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        storeLastViewedPrefs();
        super.onResume();
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_PROGRESS_DIALOG", this.mProgressDialog != null);
        outState.putString("com.amazon.mp3.library.EXTRA_PROGRESS_DIALOG_MESSAGE", this.mProgressMessage);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            this.mProgressMessage = savedInstanceState.getString("com.amazon.mp3.library.EXTRA_PROGRESS_DIALOG_MESSAGE");
            if (savedInstanceState.getBoolean("com.amazon.mp3.library.EXTRA_SHOW_PROGRESS_DIALOG", false)) {
                ProgressDialog progressDialog = new ProgressDialog(this);
                this.mProgressDialog = progressDialog;
                progressDialog.setMessage(this.mProgressMessage);
            }
        }
    }

    protected void onMediaUnmounted(Uri mountpoint) {
        if ("cirrus-local".equals(getSourceId())) {
            finish();
        }
    }

    protected boolean finishOnCirrusDatabaseCleared() {
        return "cirrus".equals(getSourceId()) && getParent() == null;
    }

    protected void showProgressDialog(int messageId, int delay) {
        showProgressDialog(messageId != 0 ? getString(messageId) : "", delay);
    }

    protected void showProgressDialog(String message, int delay) {
        if (this.mProgressDialog != null) {
            return;
        }
        this.mProgressMessage = message;
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mProgressDialog = progressDialog;
        progressDialog.setMessage(this.mProgressMessage);
        this.mProgressDialog.setCancelable(false);
        DialogUtil.installDefaultKeyListener(this.mProgressDialog);
        if (delay > 0) {
            this.mHandler.removeCallbacks(this.mShowProgressRunnable);
            this.mHandler.postDelayed(this.mShowProgressRunnable, delay);
        } else if (!isFinishing()) {
            this.mProgressDialog.show();
            this.mHandler.postDelayed(this.mAllowProgressCancelRunnable, 10000L);
        } else {
            Log.warning(TAG, "Progress dialog is not shown because the activity is finishing.");
        }
    }

    protected void hideProgressDialog() {
        this.mHandler.removeCallbacks(this.mShowProgressRunnable);
        this.mHandler.removeCallbacks(this.mAllowProgressCancelRunnable);
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity
    public boolean shouldDisplayBluemoonExceptions() {
        return "cirrus".equals(this.mSourceId);
    }

    private void storeLastViewedPrefs() {
        if (this.mSaveSourceOnResume) {
            String sourceId = getSourceId();
            if (isValidLibrarySource(sourceId)) {
                LastViewedScreenUtil.setLastViewed(this, LastViewedScreenUtil.LastViewedScreen.LIBRARY, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(sourceId));
            } else {
                LastViewedScreenUtil.setLastViewed(this, LastViewedScreenUtil.LastViewedScreen.LIBRARY);
            }
        }
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        VALID_REMEMBERED_SOURCE_IDS = hashSet;
        hashSet.add("cirrus");
        hashSet.add("cirrus-local");
        IntentFilter intentFilter = new IntentFilter();
        GENERAL_ACTION_FILTER = intentFilter;
        intentFilter.addAction("com.amazon.mp3.ACTION_CACHE_CLEARED");
        IntentFilter intentFilter2 = new IntentFilter();
        MEDIA_ACTION_FILTER = intentFilter2;
        intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addDataScheme("file");
    }
}
