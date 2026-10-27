package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$CustomActionResultReceiver extends ResultReceiver {
    private final String mAction;
    private final MediaBrowserCompat$CustomActionCallback mCallback;
    private final Bundle mExtras;

    @Override // android.support.v4.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        if (this.mCallback == null) {
            return;
        }
        MediaSessionCompat.ensureClassLoader(bundle);
        if (i == -1) {
            this.mCallback.onError(this.mAction, this.mExtras, bundle);
            return;
        }
        if (i == 0) {
            this.mCallback.onResult(this.mAction, this.mExtras, bundle);
            return;
        }
        if (i == 1) {
            this.mCallback.onProgressUpdate(this.mAction, this.mExtras, bundle);
            return;
        }
        Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.mExtras + ", resultData=" + bundle + ")");
    }
}
