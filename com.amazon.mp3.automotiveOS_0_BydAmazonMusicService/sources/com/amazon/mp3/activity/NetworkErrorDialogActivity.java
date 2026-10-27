package com.amazon.mp3.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkErrorDialogActivity extends JobSessionActivity implements NetworkErrorDialog.INetworkErrorDialogHandler {
    private boolean mShouldLaunchStoreDialog;
    private boolean mVisible = false;
    public static final int RESULT_RETRY = UniqueCodeUtil.nextUniqueCode();
    public static final String EXTRA_SHOW_STORE_NETWORK_ERROR_DIALOG = NetworkErrorDialogActivity.class.getName() + ".EXTRA_SHOW_STORE_NETWORK_ERROR_DIALOG";
    private static final int MAIN_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();
    private static final int STORE_NETWORK_ERROR_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    public static Intent getStartIntent(Context c) {
        Intent intent = new Intent(c, (Class<?>) NetworkErrorDialogActivity.class);
        intent.setFlags(268435456);
        return intent;
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int id) {
        if (id == STORE_NETWORK_ERROR_DIALOG_ID) {
            return NetworkErrorDialog.createStoreDialog(this, this);
        }
        if (id == MAIN_DIALOG_ID) {
            return NetworkErrorDialog.create(this, this);
        }
        return super.onCreateDialog(id);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_activity);
        boolean z = false;
        this.mShouldLaunchStoreDialog = getIntent().getBooleanExtra(EXTRA_SHOW_STORE_NETWORK_ERROR_DIALOG, false);
        if (savedInstanceState != null && savedInstanceState.getBoolean("com.amazon.mp3.activity.EXTRA_DIALOG_VISIBLE", false)) {
            z = true;
        }
        this.mVisible = z;
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        DialogUtil.hackAroundOtterDialogActivityBug(this);
        if (this.mVisible) {
            return;
        }
        reshow();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("com.amazon.mp3.activity.EXTRA_DIALOG_VISIBLE", this.mVisible);
    }

    protected void hide() {
        if (this.mVisible) {
            if (this.mShouldLaunchStoreDialog) {
                removeDialog(STORE_NETWORK_ERROR_DIALOG_ID);
            } else {
                removeDialog(MAIN_DIALOG_ID);
            }
            this.mVisible = false;
        }
    }

    private void reshow() {
        hide();
        if (this.mShouldLaunchStoreDialog) {
            showDialog(STORE_NETWORK_ERROR_DIALOG_ID);
        } else {
            showDialog(MAIN_DIALOG_ID);
        }
        this.mVisible = true;
    }

    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onConnectionErrorDialogCancel() {
        setResult(0);
        finish();
    }

    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onDialogDismiss() {
        setResult(0);
        finish();
    }

    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onRetryConnection() {
        setResult(RESULT_RETRY);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(0);
        finish();
    }
}
