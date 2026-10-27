package com.android.car.settings.security;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.android.car.settings.common.Logger;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes5.dex */
public class SaveLockWorker extends Fragment {
    private static final Logger LOG = new Logger((Class<?>) SaveLockWorker.class);
    private LockscreenCredential mCurrentCredential;
    private LockscreenCredential mEnteredCredential;
    private boolean mFinished;
    private boolean mIsSaveSuccessful;
    private Listener mListener;
    private int mRequestedQuality;
    private int mUserId;
    private LockPatternUtils mUtils;

    interface Listener {
        void onChosenLockSaveFinished(boolean z);
    }

    final LockPatternUtils getUtils() {
        return this.mUtils;
    }

    final int getUserId() {
        return this.mUserId;
    }

    final boolean isFinished() {
        return this.mFinished;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        PasswordHelper.zeroizeCredentials(this.mEnteredCredential, this.mCurrentCredential);
    }

    final void init(int userId) {
        this.mUtils = new LockPatternUtils(getContext());
        this.mUserId = userId;
    }

    void start(int userId, LockscreenCredential enteredCredential, LockscreenCredential currentCredential) {
        init(userId);
        this.mEnteredCredential = enteredCredential;
        this.mCurrentCredential = currentCredential != null ? currentCredential : LockscreenCredential.createNone();
        start();
    }

    private void start() {
        this.mFinished = false;
        new Task().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void setListener(Listener listener) {
        if (this.mListener != listener) {
            this.mListener = listener;
            if (this.mFinished && listener != null) {
                listener.onChosenLockSaveFinished(this.mIsSaveSuccessful);
            }
        }
    }

    boolean saveAndVerifyInBackground(Void... params) {
        try {
            saveLock();
            return true;
        } catch (Exception e) {
            LOG.e("Save lock exception", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(boolean isSaveSuccessful) {
        this.mFinished = true;
        this.mIsSaveSuccessful = isSaveSuccessful;
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onChosenLockSaveFinished(isSaveSuccessful);
        }
    }

    void saveLock() {
        int userId = getUserId();
        boolean isPatternEverChosen = getUtils().isPatternEverChosen(userId);
        getUtils().setLockCredential(this.mEnteredCredential, this.mCurrentCredential, userId);
        if (this.mEnteredCredential.isPattern() && !isPatternEverChosen) {
            getUtils().setVisiblePatternEnabled(true, userId);
        }
    }

    private class Task extends AsyncTask<Void, Void, Boolean> {
        private Task() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... params) {
            return Boolean.valueOf(SaveLockWorker.this.saveAndVerifyInBackground(new Void[0]));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean isSaveSuccessful) {
            SaveLockWorker.this.finish(isSaveSuccessful.booleanValue());
        }
    }
}
