package com.amazon.mp3.activity.settings;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.events.NavigationAppEvent;

/* JADX INFO: loaded from: classes3.dex */
public class ClearCacheActivity extends DialogActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private int mFlags;
    private boolean mShutdownOnCompletion = false;
    private int mBackPressCount = 0;
    private boolean mReceiverRegistered = false;
    private DialogInterface.OnKeyListener mDialogOnKeyListener = new DialogInterface.OnKeyListener() { // from class: com.amazon.mp3.activity.settings.ClearCacheActivity.1
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode != 4) {
                return keyCode == 84;
            }
            if (event.getAction() == 0 && ClearCacheActivity.access$004(ClearCacheActivity.this) > 10 && !ActivityManager.isUserAMonkey()) {
                Dialog dialog2 = (Dialog) dialog;
                dialog2.setCancelable(true);
                dialog2.cancel();
            }
            return true;
        }
    };
    private final BroadcastReceiver mCacheClearedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.settings.ClearCacheActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ClearCacheActivity.this.finish();
        }
    };

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void checkClearCache() {
    }

    static /* synthetic */ int access$004(ClearCacheActivity clearCacheActivity) {
        int i = clearCacheActivity.mBackPressCount + 1;
        clearCacheActivity.mBackPressCount = i;
        return i;
    }

    public static Intent getStartIntent(Context context) {
        return getStartIntent(context, -108809);
    }

    public static Intent getStartIntent(Context context, int flags) {
        return getStartIntent(context, flags, false);
    }

    public static Intent getStartIntent(Context context, int flags, boolean shutdownOnCompletion) {
        Intent intent = new Intent();
        intent.setClass(context, ClearCacheActivity.class);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", flags);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_SHUTDOWN_ON_COMPLETION", shutdownOnCompletion);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        AlertDialog.Builder builderNewAlertDialogBuilder = newAlertDialogBuilder();
        builderNewAlertDialogBuilder.setView(View.inflate(this, R.layout.common_progress_dialog, null));
        builderNewAlertDialogBuilder.setCancelable(false);
        builderNewAlertDialogBuilder.setOnKeyListener(this.mDialogOnKeyListener);
        builderNewAlertDialogBuilder.setIcon(android.R.drawable.ic_dialog_info);
        builderNewAlertDialogBuilder.setTitle(R.string.dmusic_clear_cache_title);
        return builderNewAlertDialogBuilder.create();
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFlags = getIntent().getIntExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", -108809);
        this.mShutdownOnCompletion = getIntent().getBooleanExtra("com.amazon.mp3.activity.EXTRA_SHUTDOWN_ON_COMPLETION", false);
        if (!ClearCacheService.isRunning()) {
            ClearCacheService.startClearCache(this, this.mFlags);
        }
        NavigationAppEvent.builder("settingsMusicCache").withOrientation(ScreenUtil.getScreenOrientation(this)).publish();
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createSessionIfNecessary();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        registerReceiver(this.mCacheClearedReceiver, new IntentFilter("com.amazon.mp3.ACTION_CACHE_CLEARED"));
        this.mReceiverRegistered = true;
        if (ClearCacheService.isRunning()) {
            return;
        }
        finish();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mReceiverRegistered) {
            this.mReceiverRegistered = false;
            unregisterReceiver(this.mCacheClearedReceiver);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mShutdownOnCompletion) {
            AmazonApplication.setExiting();
            Process.killProcess(Process.myPid());
        }
    }
}
