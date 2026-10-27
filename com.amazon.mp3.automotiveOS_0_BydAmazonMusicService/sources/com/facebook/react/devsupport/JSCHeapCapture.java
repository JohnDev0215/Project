package com.facebook.react.devsupport;

import androidx.annotation.Nullable;
import com.facebook.fbreact.specs.NativeJSCHeapCaptureSpec;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = JSCHeapCapture.TAG, needsEagerInit = true)
public class JSCHeapCapture extends NativeJSCHeapCaptureSpec {
    public static final String TAG = "JSCHeapCapture";

    @Nullable
    private CaptureCallback mCaptureInProgress;

    public interface CaptureCallback {
        void onFailure(CaptureException captureException);

        void onSuccess(File file);
    }

    public interface HeapCapture extends JavaScriptModule {
        void captureHeap(String str);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    public static class CaptureException extends Exception {
        CaptureException(String str) {
            super(str);
        }
    }

    public JSCHeapCapture(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCaptureInProgress = null;
    }

    public synchronized void captureHeap(String str, CaptureCallback captureCallback) {
        if (this.mCaptureInProgress != null) {
            captureCallback.onFailure(new CaptureException("Heap capture already in progress."));
            return;
        }
        File file = new File(str + "/capture.json");
        file.delete();
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            HeapCapture heapCapture = (HeapCapture) reactApplicationContextIfActiveOrWarn.getJSModule(HeapCapture.class);
            if (heapCapture == null) {
                captureCallback.onFailure(new CaptureException("Heap capture js module not registered."));
            } else {
                this.mCaptureInProgress = captureCallback;
                heapCapture.captureHeap(file.getPath());
            }
        }
    }

    @Override // com.facebook.fbreact.specs.NativeJSCHeapCaptureSpec
    public synchronized void captureComplete(String str, String str2) {
        CaptureCallback captureCallback = this.mCaptureInProgress;
        if (captureCallback != null) {
            if (str2 == null) {
                captureCallback.onSuccess(new File(str));
            } else {
                captureCallback.onFailure(new CaptureException(str2));
            }
            this.mCaptureInProgress = null;
        }
    }
}
