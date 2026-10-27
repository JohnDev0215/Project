package com.amazon.mp3.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.mp3.util.ExitCheck;

/* JADX INFO: loaded from: classes3.dex */
public class ReportCrashDialogActivity extends JobSessionActivity {
    private static CallBack defaultCallBack;
    private boolean mKillOnResume = false;

    public interface CallBack {
        void action();
    }

    public static Intent getStartIntent(Context c, CallBack callBack) {
        defaultCallBack = callBack;
        Intent intent = new Intent(c, (Class<?>) ReportCrashDialogActivity.class);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog().show();
    }

    private Dialog initDialog() {
        final String string = getString(R.string.dmusic_status_crash);
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(string);
        bauhausDialogBuilder.setMessage(getString(R.string.dmusic_app_crash_message));
        bauhausDialogBuilder.setPositiveButton(getString(R.string.dmusic_button_send_report), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.ReportCrashDialogActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                EmailUtil.reportError(ReportCrashDialogActivity.this.getApplicationContext(), string);
                ReportCrashDialogActivity.this.mKillOnResume = true;
            }
        });
        bauhausDialogBuilder.setNegativeButton(getString(R.string.dmusic_button_cancel), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.ReportCrashDialogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                ReportCrashDialogActivity.this.killApplication();
            }
        });
        return bauhausDialogBuilder.create();
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mKillOnResume) {
            killApplication();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killApplication() throws RuntimeException {
        ExitCheck.clearActivityCheck(this, true);
        AmazonApplication.setExiting();
        CallBack callBack = defaultCallBack;
        if (callBack != null) {
            callBack.action();
        } else {
            Process.killProcess(Process.myPid());
        }
    }
}
