package com.amazon.mp3.activity.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.DiscoverCustomerHomeJob;
import com.amazon.mp3.account.activity.AccountWebViewActivity;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.library.fragment.ArtistListFragment;
import com.amazon.mp3.library.job.AuthorizedDeviceJob;
import com.amazon.mp3.library.job.CheckAccountIsValidJob;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.JobSessionActivity;
import com.amazon.mp3.service.job.JobSessionFragment;
import com.amazon.mp3.service.job.Session;
import com.amazon.mp3.service.job.activity.ActivityJobWrapper;
import com.amazon.mp3.service.job.activity.ActivityJobWrapperExecutor;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.WebViewUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class AccountJobController {
    private static final int ACCOUNT_VALIDATION_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final int AUTHORIZATION_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final RequestThrottler REQUEST_THROTTLER = new RequestThrottler(600000);
    private static final String TAG = "AccountJobController";
    private Activity mActivity;
    private ArrayList<WeakReference<JobFinishedCallback>> mCallbacks;
    private Fragment mFragment;
    private final ActivityJobWrapperExecutor mJobExecutor;

    public interface JobFinishedCallback {
        void onFailure();

        void onSuccess();
    }

    public AccountJobController(final JobSessionActivity activity) {
        this.mJobExecutor = new ActivityJobWrapperExecutor();
        this.mCallbacks = new ArrayList<>();
        this.mActivity = activity;
        this.mFragment = null;
    }

    public AccountJobController(final JobSessionFragment fragment, final Activity activity) {
        this.mJobExecutor = new ActivityJobWrapperExecutor();
        this.mCallbacks = new ArrayList<>();
        this.mActivity = activity;
        this.mFragment = fragment;
    }

    public void check(Session session) {
        AccountCredentialUtil accountCredentialUtil = AccountCredentialUtil.get(this.mActivity);
        accountCredentialUtil.synchronizeWithDeviceRegistration(this.mActivity, false);
        if (accountCredentialUtil.isSignedIn()) {
            if (shouldSkipNetworkRequests()) {
                return;
            }
            startJobs(session);
            return;
        }
        this.mActivity.startActivity(new Intent(this.mActivity, (Class<?>) MusicLauncherActivity.class));
    }

    public void checkImmediate(Session session, JobFinishedCallback callback) {
        AccountCredentialUtil accountCredentialUtil = AccountCredentialUtil.get(this.mActivity);
        accountCredentialUtil.synchronizeWithDeviceRegistration(this.mActivity, false);
        if (accountCredentialUtil.isSignedIn()) {
            startJobs(session);
            if (callback != null) {
                this.mCallbacks.add(new WeakReference<>(callback));
                return;
            }
            return;
        }
        this.mActivity.startActivity(new Intent(this.mActivity, (Class<?>) MusicLauncherActivity.class));
    }

    public void cancel() {
        this.mJobExecutor.dropAll();
    }

    private void onJobControllerFinished(final boolean success) {
        Iterator<WeakReference<JobFinishedCallback>> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            JobFinishedCallback jobFinishedCallback = it.next().get();
            if (jobFinishedCallback == null) {
                Log.error(TAG, "JobFinishedCallback is null");
            } else if (success) {
                jobFinishedCallback.onSuccess();
            } else {
                jobFinishedCallback.onFailure();
            }
            it.remove();
        }
    }

    public void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        this.mJobExecutor.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    public void onActivityResult(Session session, final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == SignInFlowLauncherActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                startJobs(session);
            } else {
                onQueuedJobsFinished(false);
            }
        }
        this.mJobExecutor.onActivityResult(requestCode, resultCode, data);
    }

    private boolean shouldSkipNetworkRequests() {
        if (AccountCredentialStorage.get(AmazonApplication.getContext()).shouldPerformSignInTasks()) {
            return !REQUEST_THROTTLER.shouldSendNextRequest() && WebViewUtil.isAccountValidationValid(this.mActivity) && DeviceAuthorizationManager.getInstance().isAuthorized();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startActivityForResult(Intent intent, int requestCode) {
        Fragment fragment = this.mFragment;
        if (fragment == null) {
            this.mActivity.startActivityForResult(intent, requestCode);
        } else {
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    private void enqueueAccountValidationJob(Session session) {
        this.mJobExecutor.add(new ActivityJobWrapper(new CheckAccountIsValidJob(this.mActivity), session, ACCOUNT_VALIDATION_REQUEST_CODE) { // from class: com.amazon.mp3.activity.util.AccountJobController.1
            private boolean mWaitForActivityResult;

            @Override // com.amazon.mp3.service.job.activity.JobWrapper
            public void onJobFinished(final int resultCode, final Bundle resultBundle) {
                if (resultCode == 1 || AccountJobController.this.mActivity == null) {
                    return;
                }
                this.mWaitForActivityResult = true;
                AccountJobController accountJobController = AccountJobController.this;
                accountJobController.startActivityForResult(AccountWebViewActivity.getIntent(accountJobController.mActivity), AccountJobController.ACCOUNT_VALIDATION_REQUEST_CODE);
            }

            @Override // com.amazon.mp3.service.job.activity.ActivityJobWrapper
            public boolean waitForActivityResult() {
                return this.mWaitForActivityResult;
            }

            @Override // com.amazon.mp3.service.job.activity.ActivityJobWrapper
            public void onActivityResult(final int resultCode, final Intent data) {
                if (resultCode == -1 || AccountJobController.this.mActivity == null) {
                    return;
                }
                AccountJobController.this.onQueuedJobsFinished(false);
            }
        });
    }

    private void enqueueAuthorizationJob(final Session session) {
        this.mJobExecutor.add(new ActivityJobWrapper(new AuthorizedDeviceJob(), session, AUTHORIZATION_REQUEST_CODE) { // from class: com.amazon.mp3.activity.util.AccountJobController.2
            private boolean mWaitForActivityResult;

            @Override // com.amazon.mp3.service.job.activity.JobWrapper
            public void onJobFinished(final int resultCode, final Bundle resultBundle) {
                if (resultCode == 1 || AccountJobController.this.mActivity == null) {
                    AccountJobController.this.onQueuedJobsFinished(true);
                    return;
                }
                if (!AmazonApplication.getCapabilities().shouldRemoveBMWV()) {
                    Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(AccountJobController.this.mActivity);
                    if (deviceAuthorizationIntent == null) {
                        AccountJobController.this.onQueuedJobsFinished(true);
                        return;
                    } else {
                        this.mWaitForActivityResult = true;
                        AccountJobController.this.startActivityForResult(deviceAuthorizationIntent, AccountJobController.AUTHORIZATION_REQUEST_CODE);
                        return;
                    }
                }
                AccountJobController.this.startDiscoverCustomerHomeJob(session);
            }

            @Override // com.amazon.mp3.service.job.activity.ActivityJobWrapper
            public boolean waitForActivityResult() {
                return this.mWaitForActivityResult;
            }

            @Override // com.amazon.mp3.service.job.activity.ActivityJobWrapper
            public void onActivityResult(final int resultCode, final Intent data) {
                if (resultCode != -1) {
                    AccountJobController.this.onQueuedJobsFinished(false);
                } else {
                    AccountJobController.this.onQueuedJobsFinished(true);
                }
            }
        });
    }

    public void startDiscoverCustomerHomeJob(Session session) {
        this.mJobExecutor.add(new ActivityJobWrapper(new DiscoverCustomerHomeJob(), session, AUTHORIZATION_REQUEST_CODE) { // from class: com.amazon.mp3.activity.util.AccountJobController.3
            @Override // com.amazon.mp3.service.job.activity.ActivityJobWrapper
            public boolean waitForActivityResult() {
                return false;
            }

            @Override // com.amazon.mp3.service.job.activity.JobWrapper
            public void onJobFinished(int resultCode, Bundle resultBundle) {
                AccountJobController.this.onQueuedJobsFinished(true);
            }

            @Override // com.amazon.mp3.service.job.activity.ActivityJobWrapper
            public void onActivityResult(int resultCode, Intent data) {
                if (resultCode != -1) {
                    AccountJobController.this.onQueuedJobsFinished(false);
                } else {
                    AccountJobController.this.onQueuedJobsFinished(true);
                }
            }
        });
    }

    private void startJobs(Session session) {
        enqueueJobs(session);
        this.mJobExecutor.runAllAsync();
    }

    private void enqueueJobs(Session session) {
        enqueueAccountValidationJob(session);
        enqueueAuthorizationJob(session);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onQueuedJobsFinished(final boolean success) {
        this.mJobExecutor.dropAll();
        onJobControllerFinished(success);
        if (success) {
            REQUEST_THROTTLER.recordSuccess();
            return;
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            Intent startIntent = MusicHomeActivity.getStartIntent(activity);
            startIntent.putExtra("com.amazon.mp3.fragment.extra", ArtistListFragment.class.getSimpleName());
            startIntent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus-local");
            this.mActivity.startActivity(startIntent);
        }
    }

    public void destroy() {
        this.mFragment = null;
        this.mActivity = null;
        cancel();
    }

    public void removeCallback(final JobFinishedCallback callback) {
        this.mCallbacks.remove(callback);
    }

    private static class RequestThrottler {
        private long mNextRunTimeMs;
        private final long mQuietPeriod;

        public RequestThrottler(final long quietPeriod) {
            this.mQuietPeriod = quietPeriod;
        }

        public synchronized boolean shouldSendNextRequest() {
            return SystemClock.elapsedRealtime() >= this.mNextRunTimeMs;
        }

        public synchronized void recordSuccess() {
            this.mNextRunTimeMs = SystemClock.elapsedRealtime() + this.mQuietPeriod;
        }
    }
}
