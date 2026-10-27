package android.service.autofill;

import android.content.IntentSender;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class SaveCallback {
    private static final String TAG = "SaveCallback";
    private final ISaveCallback mCallback;
    private boolean mCalled;

    SaveCallback(ISaveCallback iSaveCallback) {
        this.mCallback = iSaveCallback;
    }

    public void onSuccess() {
        onSuccessInternal(null);
    }

    public void onSuccess(IntentSender intentSender) {
        onSuccessInternal((IntentSender) Preconditions.checkNotNull(intentSender));
    }

    private void onSuccessInternal(IntentSender intentSender) {
        assertNotCalled();
        this.mCalled = true;
        try {
            this.mCallback.onSuccess(intentSender);
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    public void onFailure(CharSequence charSequence) {
        Log.w(TAG, "onFailure(): " + ((Object) charSequence));
        assertNotCalled();
        this.mCalled = true;
        try {
            this.mCallback.onFailure(charSequence);
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    private void assertNotCalled() {
        if (this.mCalled) {
            throw new IllegalStateException("Already called");
        }
    }
}
