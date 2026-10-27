package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;

/* JADX INFO: loaded from: classes.dex */
class DccMessageWrapper<T> {
    private final IDCCallback<T> mCallback;
    private final CancellationSignal mCancellationSignal;
    private final String mContentType;

    public DccMessageWrapper(String str, CancellationSignal cancellationSignal, IDCCallback<T> iDCCallback) {
        this.mContentType = str;
        this.mCancellationSignal = cancellationSignal;
        this.mCallback = iDCCallback;
    }

    public String getContentType() {
        return this.mContentType;
    }

    public CancellationSignal getCancellationSignal() {
        return this.mCancellationSignal;
    }

    public IDCCallback<T> getCallback() {
        return this.mCallback;
    }
}
