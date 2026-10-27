package com.android.bluetooth.pbapclient;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class PhonebookPullRequest extends PullRequest {
    private static final int MAX_OPS = 250;
    private static final String TAG = "PbapPbPullRequest";
    private static final boolean VDBG = false;
    public boolean complete = false;
    private final Account mAccount;
    private final Context mContext;

    public PhonebookPullRequest(Context context, Account account) {
        this.mContext = context;
        this.mAccount = account;
        this.path = PbapClientConnectionHandler.PB_PATH;
    }

    @Override // com.android.bluetooth.pbapclient.PullRequest
    public void onPullComplete() {
        if (this.mEntries == null) {
            Log.e(TAG, "onPullComplete entries is null.");
        } else {
            this.complete = true;
        }
    }
}
