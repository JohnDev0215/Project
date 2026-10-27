package androidx.slice.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.lifecycle.LiveData;
import androidx.slice.Slice;
import androidx.slice.SliceSpec;
import androidx.slice.SliceSpecs;
import androidx.slice.SliceViewManager;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class SliceLiveData {
    public static final SliceSpec OLD_BASIC = new SliceSpec("androidx.app.slice.BASIC", 1);
    public static final SliceSpec OLD_LIST = new SliceSpec("androidx.app.slice.LIST", 1);
    public static final Set<SliceSpec> SUPPORTED_SPECS = new ArraySet(Arrays.asList(SliceSpecs.BASIC, SliceSpecs.LIST, SliceSpecs.LIST_V2, OLD_BASIC, OLD_LIST));

    public interface OnErrorListener {
        void onSliceError(int i, Throwable th);
    }

    public static LiveData<Slice> fromUri(Context context, Uri uri) {
        return new SliceLiveDataImpl(context.getApplicationContext(), uri, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SliceLiveDataImpl extends LiveData<Slice> {
        final OnErrorListener mListener;
        final SliceViewManager mSliceViewManager;
        Uri mUri;
        private final Runnable mUpdateSlice = new Runnable() { // from class: androidx.slice.widget.SliceLiveData.SliceLiveDataImpl.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Slice sliceBindSlice = SliceLiveDataImpl.this.mUri != null ? SliceLiveDataImpl.this.mSliceViewManager.bindSlice(SliceLiveDataImpl.this.mUri) : SliceLiveDataImpl.this.mSliceViewManager.bindSlice(SliceLiveDataImpl.this.mIntent);
                    if (sliceBindSlice == null) {
                        SliceLiveDataImpl.this.onSliceError(2, null);
                        return;
                    }
                    if (SliceLiveDataImpl.this.mUri == null) {
                        SliceLiveDataImpl.this.mUri = sliceBindSlice.getUri();
                        SliceLiveDataImpl.this.mSliceViewManager.registerSliceCallback(SliceLiveDataImpl.this.mUri, SliceLiveDataImpl.this.mSliceCallback);
                    }
                    SliceLiveDataImpl.this.postValue(sliceBindSlice);
                } catch (IllegalArgumentException e) {
                    SliceLiveDataImpl.this.onSliceError(3, e);
                } catch (Exception e2) {
                    SliceLiveDataImpl.this.onSliceError(0, e2);
                }
            }
        };
        final SliceViewManager.SliceCallback mSliceCallback = new SliceViewManager.SliceCallback() { // from class: androidx.slice.widget.-$$Lambda$SliceLiveData$SliceLiveDataImpl$R4N7L73501Pav2ashjY94Bexi9s
            @Override // androidx.slice.SliceViewManager.SliceCallback
            public final void onSliceUpdated(Slice slice) {
                this.f$0.postValue(slice);
            }
        };
        final Intent mIntent = null;

        SliceLiveDataImpl(Context context, Uri uri, OnErrorListener onErrorListener) {
            this.mSliceViewManager = SliceViewManager.getInstance(context);
            this.mUri = uri;
            this.mListener = onErrorListener;
        }

        @Override // androidx.lifecycle.LiveData
        protected void onActive() {
            AsyncTask.execute(this.mUpdateSlice);
            Uri uri = this.mUri;
            if (uri != null) {
                this.mSliceViewManager.registerSliceCallback(uri, this.mSliceCallback);
            }
        }

        @Override // androidx.lifecycle.LiveData
        protected void onInactive() {
            Uri uri = this.mUri;
            if (uri != null) {
                this.mSliceViewManager.unregisterSliceCallback(uri, this.mSliceCallback);
            }
        }

        void onSliceError(int i, Throwable th) {
            Uri uri = this.mUri;
            if (uri != null) {
                this.mSliceViewManager.unregisterSliceCallback(uri, this.mSliceCallback);
            }
            OnErrorListener onErrorListener = this.mListener;
            if (onErrorListener != null) {
                onErrorListener.onSliceError(i, th);
                return;
            }
            if (th != null) {
                Log.e("SliceLiveData", "Error binding slice", th);
                return;
            }
            Log.e("SliceLiveData", "Error binding slice, error code: " + i);
        }
    }
}
