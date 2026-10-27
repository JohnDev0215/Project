package com.android.car.developeroptions.password;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Pair;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
abstract class SaveChosenLockWorkerBase extends Fragment {
    private boolean mBlocking;
    protected long mChallenge;
    private boolean mFinished;
    protected boolean mHasChallenge;
    private Listener mListener;
    private Intent mResultData;
    protected int mUserId;
    protected LockPatternUtils mUtils;
    protected boolean mWasSecureBefore;

    interface Listener {
        void onChosenLockSaveFinished(boolean z, Intent intent);
    }

    protected abstract Pair<Boolean, Intent> saveAndVerifyInBackground();

    SaveChosenLockWorkerBase() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void setListener(Listener listener) {
        if (this.mListener == listener) {
            return;
        }
        this.mListener = listener;
        if (!this.mFinished || listener == null) {
            return;
        }
        listener.onChosenLockSaveFinished(this.mWasSecureBefore, this.mResultData);
    }

    protected void prepare(LockPatternUtils lockPatternUtils, boolean z, boolean z2, long j, int i) {
        this.mUtils = lockPatternUtils;
        this.mUserId = i;
        this.mHasChallenge = z2;
        this.mChallenge = j;
        this.mWasSecureBefore = lockPatternUtils.isSecure(i);
        Context context = getContext();
        if (context == null || UserManager.get(context).getUserInfo(this.mUserId).isPrimary()) {
            this.mUtils.setCredentialRequiredToDecrypt(z);
        }
        this.mFinished = false;
        this.mResultData = null;
    }

    protected void start() {
        if (this.mBlocking) {
            finish((Intent) saveAndVerifyInBackground().second);
        } else {
            new Task().execute(new Void[0]);
        }
    }

    protected void finish(Intent intent) {
        this.mFinished = true;
        this.mResultData = intent;
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onChosenLockSaveFinished(this.mWasSecureBefore, intent);
        }
    }

    public void setBlocking(boolean z) {
        this.mBlocking = z;
    }

    private class Task extends AsyncTask<Void, Void, Pair<Boolean, Intent>> {
        private Task() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Pair<Boolean, Intent> doInBackground(Void... voidArr) {
            return SaveChosenLockWorkerBase.this.saveAndVerifyInBackground();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Pair<Boolean, Intent> pair) {
            if (!((Boolean) pair.first).booleanValue()) {
                Toast.makeText(SaveChosenLockWorkerBase.this.getContext(), R.string.lockpassword_credential_changed, 1).show();
            }
            SaveChosenLockWorkerBase.this.finish((Intent) pair.second);
        }
    }
}
