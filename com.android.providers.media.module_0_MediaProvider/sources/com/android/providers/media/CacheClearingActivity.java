package com.android.providers.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.BidiFormatter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.providers.media.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class CacheClearingActivity extends Activity implements DialogInterface.OnClickListener {
    private static final Long LEAST_SHOW_PROGRESS_TIME_MS = 300L;
    private static final float MAX_APP_NAME_SIZE_PX = 500.0f;
    private static final String TAG = "CacheClearingActivity";
    private static final float TEXT_SIZE = 42.0f;
    private AlertDialog mActionDialog;
    private Dialog mLoadingDialog;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String callingPackage = getCallingPackage();
        setResult(0);
        if (callingPackage == null) {
            finish();
            return;
        }
        PackageManager packageManager = getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(callingPackage, 0);
            if (!MediaProvider.hasPermissionToClearCaches(this, applicationInfo)) {
                Log.i(TAG, "Calling package " + callingPackage + " has no permission clear app caches");
                finish();
                return;
            }
            createActionDialog(applicationInfo.loadSafeLabel(packageManager, MAX_APP_NAME_SIZE_PX, 2).toString());
            this.mActionDialog.show();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "unable to look up package name", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dismissDialogs(this.mActionDialog, this.mLoadingDialog);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dismissDialogs(this.mActionDialog);
        if (i == -1) {
            new CacheClearingTask().execute(new Void[0]);
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CacheClearingTask extends AsyncTask<Void, Void, Integer> {
        private long mStartTime;

        private CacheClearingTask() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            CacheClearingActivity.dismissDialogs(CacheClearingActivity.this.mActionDialog);
            CacheClearingActivity.this.createLoadingDialog();
            CacheClearingActivity.this.mLoadingDialog.show();
            this.mStartTime = System.currentTimeMillis();
        }

        @Override // android.os.AsyncTask
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(FileUtils.clearAppCacheDirectories());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            int iIntValue = num.intValue();
            if (num.intValue() == 0) {
                CacheClearingActivity.this.setResult(-1);
            } else {
                CacheClearingActivity.this.setResult(iIntValue);
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartTime;
            if (jCurrentTimeMillis > CacheClearingActivity.LEAST_SHOW_PROGRESS_TIME_MS.longValue()) {
                CacheClearingActivity.dismissDialogs(CacheClearingActivity.this.mLoadingDialog);
                CacheClearingActivity.this.finish();
            } else {
                new Handler(CacheClearingActivity.this.getMainLooper()).postDelayed(new Runnable() { // from class: com.android.providers.media.-$$Lambda$CacheClearingActivity$CacheClearingTask$y2j2cUexlmOw8NRuJ7xb-C-om3Q
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onPostExecute$0$CacheClearingActivity$CacheClearingTask();
                    }
                }, CacheClearingActivity.LEAST_SHOW_PROGRESS_TIME_MS.longValue() - jCurrentTimeMillis);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onPostExecute$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onPostExecute$0$CacheClearingActivity$CacheClearingTask() {
            CacheClearingActivity.dismissDialogs(CacheClearingActivity.this.mLoadingDialog);
            CacheClearingActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createLoadingDialog() {
        String string = getString(R.string.cache_clearing_in_progress_title);
        View viewInflate = View.inflate(this, R.layout.cache_clearing_dialog, null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.dialog_title);
        ProgressBar progressBar = new ProgressBar(this);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.dialog_space);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, dimensionPixelOffset / 2, 0, dimensionPixelOffset);
        textView.setText(string);
        this.mLoadingDialog = new AlertDialog.Builder(this).setCustomTitle(viewInflate).setView(progressBar).setCancelable(false).create();
        viewInflate.findViewById(R.id.dialog_icon).setVisibility(8);
        this.mLoadingDialog.create();
        setDialogOverlaySettings(this.mActionDialog);
    }

    private void createActionDialog(CharSequence charSequence) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(TEXT_SIZE);
        String string = getString(R.string.cache_clearing_dialog_text, new Object[]{BidiFormatter.getInstance().unicodeWrap(TextUtils.ellipsize(charSequence, textPaint, MAX_APP_NAME_SIZE_PX, TextUtils.TruncateAt.END).toString())});
        String string2 = getString(R.string.cache_clearing_dialog_title);
        View viewInflate = View.inflate(this, R.layout.cache_clearing_dialog, null);
        ((TextView) viewInflate.findViewById(R.id.dialog_title)).setText(string2);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setCustomTitle(viewInflate).setMessage(string).setPositiveButton(R.string.clear, this).setNegativeButton(android.R.string.cancel, this).setCancelable(false).create();
        this.mActionDialog = alertDialogCreate;
        alertDialogCreate.create();
        this.mActionDialog.getButton(-1).setFilterTouchesWhenObscured(true);
        setDialogOverlaySettings(this.mActionDialog);
    }

    private static void setDialogOverlaySettings(Dialog dialog) {
        dialog.getWindow().addSystemFlags(LocalCallingIdentity.PERMISSION_WRITE_AUDIO);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dismissDialogs(Dialog... dialogArr) {
        for (Dialog dialog : dialogArr) {
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }
}
