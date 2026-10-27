package com.amazon.mp3.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class DialogActivity extends JobSessionActivity {
    private static final int MAIN_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();
    private static final DialogInterface.OnClickListener sOnOkClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.DialogActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };
    private String mText;
    private String mTitle;
    private boolean mVisible = false;
    private final DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.activity.DialogActivity.1
        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            DialogActivity.this.onDialogCancel();
        }
    };

    protected boolean installDefaultDialogKeyListener() {
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    public static Intent getStartIntent(Context c, String title, String text) {
        Intent intent = new Intent(c, (Class<?>) DialogActivity.class);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TITLE", title);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TEXT", text);
        return intent;
    }

    public static Intent getStartIntent(Context c, int title_resource, int text_resource) {
        return getStartIntent(c, c.getString(title_resource), c.getString(text_resource));
    }

    protected Dialog createDialog() {
        AlertDialog.Builder builderNewAlertDialogBuilder = newAlertDialogBuilder();
        builderNewAlertDialogBuilder.setIcon(R.drawable.dialog_icn_warning);
        builderNewAlertDialogBuilder.setTitle(this.mTitle);
        builderNewAlertDialogBuilder.setMessage(this.mText);
        builderNewAlertDialogBuilder.setNegativeButton(getString(R.string.dmusic_button_ok), sOnOkClickListener);
        return builderNewAlertDialogBuilder.create();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_activity);
        boolean z = false;
        if (savedInstanceState != null && savedInstanceState.getBoolean("com.amazon.mp3.activity.EXTRA_DIALOG_VISIBLE", false)) {
            z = true;
        }
        this.mVisible = z;
        init();
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

    protected Spanned getDialogText() {
        String str = this.mText;
        if (str == null) {
            return null;
        }
        return Html.fromHtml(str);
    }

    protected String getDialogTitle() {
        return this.mTitle;
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int id) {
        if (id == MAIN_DIALOG_ID) {
            Dialog dialogCreateDialog = createDialog();
            dialogCreateDialog.setOnCancelListener(this.mOnCancelListener);
            if (installDefaultDialogKeyListener()) {
                DialogUtil.installDefaultKeyListener(dialogCreateDialog);
            }
            return dialogCreateDialog;
        }
        return super.onCreateDialog(id);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        init();
        reshow();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putBoolean("com.amazon.mp3.activity.EXTRA_DIALOG_VISIBLE", this.mVisible);
        }
    }

    protected AlertDialog.Builder newAlertDialogBuilder() {
        return new AlertDialog.Builder(this);
    }

    protected void hide() {
        if (this.mVisible) {
            removeDialog(MAIN_DIALOG_ID);
            this.mVisible = false;
        }
    }

    protected void init() {
        this.mTitle = getIntent().getStringExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TITLE");
        this.mText = getIntent().getStringExtra("com.amazon.mp3.activity.EXTRA_DIALOG_TEXT");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDialogCancel() {
        finish();
    }

    private void reshow() {
        hide();
        showDialog(MAIN_DIALOG_ID);
        this.mVisible = true;
    }
}
