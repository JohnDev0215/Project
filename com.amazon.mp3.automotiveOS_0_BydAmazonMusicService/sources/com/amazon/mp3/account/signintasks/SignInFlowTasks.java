package com.amazon.mp3.account.signintasks;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.account.activity.AcceptTermsOfUseJob;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.music.signin.SignInTask;
import com.amazon.music.signin.SignInTaskCache;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SignInFlowTasks {
    private final Context mContext;
    private final SignInTaskCache mTaskCache;

    public SignInFlowTasks(final Context context) {
        this.mContext = context;
        this.mTaskCache = new SignInTaskCache(context);
    }

    public List<SignInTask> getSignInTasks() {
        return new ArrayList<SignInTask>(12, AccountCredentialStorage.get(this.mContext)) { // from class: com.amazon.mp3.account.signintasks.SignInFlowTasks.1
            final /* synthetic */ AccountCredentialStorage val$accountCredentialStorage;

            {
                this.val$accountCredentialStorage = val$accountCredentialStorage;
                add(new BuildUserAccount(SignInFlowTasks.this.mContext));
                add(new AttemptAccountCreation(SignInFlowTasks.this.mContext));
                add(new GetHomeMarketplace(SignInFlowTasks.this.mContext));
                add(new AcceptTermsOfUseJob());
                add(new UpdatePlayerWidgetService());
                add(new BroadcastCredentialsChanged(SignInFlowTasks.this.mContext, val$accountCredentialStorage.getAccountId(), val$accountCredentialStorage.getLastAccountId()));
                add(new InitializeSubscriptionManager(SignInFlowTasks.this.mContext));
                add(new UpdatePushMessagingDeviceAttributeToSignedIn(SignInFlowTasks.this.mContext.getApplicationContext()));
                add(new UpdatePlaybackConfig(SignInFlowTasks.this.mContext));
                add(new GetRecentActivity(SignInFlowTasks.this.mContext));
                add(new WeblabTreatmentsTask());
                add(new BranchIdentityTask(SignInFlowTasks.this.mContext));
            }
        };
    }

    public boolean allSignInTasksHaveExecuted() throws InvalidSignInCacheStateException {
        SignInTask finalTaskInList = getFinalTaskInList();
        if (TextUtils.isEmpty(this.mTaskCache.getLastExecutedTaskName())) {
            throw new InvalidSignInCacheStateException("Last Executed Task Name is invalid, cannot determine if all sign in tasks have executed.");
        }
        return this.mTaskCache.isLastExecutedTask(finalTaskInList.getName());
    }

    private SignInTask getFinalTaskInList() {
        return getSignInTasks().get(11);
    }

    public void clearExecutedTasks() {
        this.mTaskCache.clear();
    }
}
