package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.JobSessionActivity;

/* JADX INFO: loaded from: classes3.dex */
public class AccountStateCheckActivity extends JobSessionActivity {
    private static AccountJobController.JobFinishedCallback mJobFinishedCallback;
    private AccountJobController mAccountJobController;
    private final AccountJobController.JobFinishedCallback mClassJobFinishedListener = new AccountJobController.JobFinishedCallback() { // from class: com.amazon.mp3.activity.AccountStateCheckActivity.1
        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onSuccess() {
            if (AccountStateCheckActivity.mJobFinishedCallback != null) {
                AccountStateCheckActivity.mJobFinishedCallback.onSuccess();
                AccountStateCheckActivity.this.finish();
            }
        }

        @Override // com.amazon.mp3.activity.util.AccountJobController.JobFinishedCallback
        public void onFailure() {
            if (AccountStateCheckActivity.mJobFinishedCallback != null) {
                AccountStateCheckActivity.mJobFinishedCallback.onFailure();
                AccountStateCheckActivity.this.finish();
            }
        }
    };

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountJobController = new AccountJobController(this);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mAccountJobController.checkImmediate(getSession(), this.mClassJobFinishedListener);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        this.mAccountJobController.onActivityResult(getSession(), requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        this.mAccountJobController.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    public static Intent getStartIntent(final Context context, final AccountJobController.JobFinishedCallback jobFinishedCallback) {
        Intent intent = new Intent(context, (Class<?>) AccountStateCheckActivity.class);
        mJobFinishedCallback = jobFinishedCallback;
        return intent;
    }
}
