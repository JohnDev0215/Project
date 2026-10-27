package com.android.internal.widget;

import android.os.AsyncTask;
import com.android.internal.widget.LockPatternUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class LockPatternChecker {

    public interface OnCheckCallback {
        default void onCancelled() {
        }

        void onChecked(boolean z, int i);

        default void onEarlyMatched() {
        }
    }

    public interface OnVerifyCallback {
        void onVerified(byte[] bArr, int i);
    }

    public static AsyncTask<?, ?, ?> verifyCredential(final LockPatternUtils lockPatternUtils, LockscreenCredential lockscreenCredential, final long j, final int i, final OnVerifyCallback onVerifyCallback) {
        final LockscreenCredential lockscreenCredentialDuplicate = lockscreenCredential.duplicate();
        AsyncTask<Void, Void, byte[]> asyncTask = new AsyncTask<Void, Void, byte[]>() { // from class: com.android.internal.widget.LockPatternChecker.1
            private int mThrottleTimeout;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public byte[] doInBackground(Void... voidArr) {
                try {
                    return lockPatternUtils.verifyCredential(lockscreenCredentialDuplicate, j, i);
                } catch (LockPatternUtils.RequestThrottledException e) {
                    this.mThrottleTimeout = e.getTimeoutMs();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(byte[] bArr) {
                onVerifyCallback.onVerified(bArr, this.mThrottleTimeout);
                lockscreenCredentialDuplicate.zeroize();
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                lockscreenCredentialDuplicate.zeroize();
            }
        };
        asyncTask.execute(new Void[0]);
        return asyncTask;
    }

    public static AsyncTask<?, ?, ?> checkCredential(final LockPatternUtils lockPatternUtils, LockscreenCredential lockscreenCredential, final int i, final OnCheckCallback onCheckCallback) {
        final LockscreenCredential lockscreenCredentialDuplicate = lockscreenCredential.duplicate();
        AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() { // from class: com.android.internal.widget.LockPatternChecker.2
            private int mThrottleTimeout;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                try {
                    LockPatternUtils lockPatternUtils2 = lockPatternUtils;
                    LockscreenCredential lockscreenCredential2 = lockscreenCredentialDuplicate;
                    int i2 = i;
                    final OnCheckCallback onCheckCallback2 = onCheckCallback;
                    Objects.requireNonNull(onCheckCallback2);
                    return Boolean.valueOf(lockPatternUtils2.checkCredential(lockscreenCredential2, i2, new LockPatternUtils.CheckCredentialProgressCallback() { // from class: com.android.internal.widget.-$$Lambda$TTC7hNz7BTsLwhNRb2L5kl-7mdU
                        @Override // com.android.internal.widget.LockPatternUtils.CheckCredentialProgressCallback
                        public final void onEarlyMatched() {
                            onCheckCallback2.onEarlyMatched();
                        }
                    }));
                } catch (LockPatternUtils.RequestThrottledException e) {
                    this.mThrottleTimeout = e.getTimeoutMs();
                    return false;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                onCheckCallback.onChecked(bool.booleanValue(), this.mThrottleTimeout);
                lockscreenCredentialDuplicate.zeroize();
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                onCheckCallback.onCancelled();
                lockscreenCredentialDuplicate.zeroize();
            }
        };
        asyncTask.execute(new Void[0]);
        return asyncTask;
    }

    public static AsyncTask<?, ?, ?> verifyTiedProfileChallenge(final LockPatternUtils lockPatternUtils, LockscreenCredential lockscreenCredential, final long j, final int i, final OnVerifyCallback onVerifyCallback) {
        final LockscreenCredential lockscreenCredentialDuplicate = lockscreenCredential.duplicate();
        AsyncTask<Void, Void, byte[]> asyncTask = new AsyncTask<Void, Void, byte[]>() { // from class: com.android.internal.widget.LockPatternChecker.3
            private int mThrottleTimeout;

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public byte[] doInBackground(Void... voidArr) {
                try {
                    return lockPatternUtils.verifyTiedProfileChallenge(lockscreenCredentialDuplicate, j, i);
                } catch (LockPatternUtils.RequestThrottledException e) {
                    this.mThrottleTimeout = e.getTimeoutMs();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(byte[] bArr) {
                onVerifyCallback.onVerified(bArr, this.mThrottleTimeout);
                lockscreenCredentialDuplicate.zeroize();
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                lockscreenCredentialDuplicate.zeroize();
            }
        };
        asyncTask.execute(new Void[0]);
        return asyncTask;
    }
}
