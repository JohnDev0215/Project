package org.xwalk.core;

import android.content.Context;
import android.util.Log;
import org.xwalk.core.XWalkLibraryLoader;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkInitializer {
    private static final String TAG = "XWalkLib";
    private Context mContext;
    private XWalkInitListener mInitListener;
    private boolean mIsXWalkReady;

    public interface XWalkInitListener {
        void onXWalkInitCancelled();

        void onXWalkInitCompleted();

        void onXWalkInitFailed();

        void onXWalkInitStarted();
    }

    public XWalkInitializer(XWalkInitListener xWalkInitListener, Context context) {
        this.mInitListener = xWalkInitListener;
        this.mContext = context;
        XWalkLibraryLoader.prepareToInit(context);
    }

    public boolean initAsync() {
        if (this.mIsXWalkReady) {
            return false;
        }
        if (XWalkLibraryLoader.isInitializing() || XWalkLibraryLoader.isDownloading()) {
            Log.d(TAG, "Other initialization or download is proceeding");
            return false;
        }
        Log.d(TAG, "Initialized by XWalkInitializer");
        XWalkLibraryLoader.startDecompress(new XWalkLibraryListener());
        this.mInitListener.onXWalkInitStarted();
        return true;
    }

    public boolean cancelInit() {
        Log.d(TAG, "Cancel by XWalkInitializer");
        return XWalkLibraryLoader.cancelDecompress();
    }

    public boolean isXWalkReady() {
        return this.mIsXWalkReady;
    }

    public boolean isSharedMode() {
        return this.mIsXWalkReady && XWalkLibraryLoader.isSharedLibrary();
    }

    public boolean isDownloadMode() {
        return this.mIsXWalkReady && XWalkEnvironment.isDownloadMode();
    }

    private class XWalkLibraryListener implements XWalkLibraryLoader.DecompressListener, XWalkLibraryLoader.ActivateListener {
        @Override // org.xwalk.core.XWalkLibraryLoader.ActivateListener
        public void onActivateStarted() {
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DecompressListener
        public void onDecompressStarted() {
        }

        private XWalkLibraryListener() {
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DecompressListener
        public void onDecompressCancelled() {
            XWalkInitializer.this.mInitListener.onXWalkInitCancelled();
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DecompressListener
        public void onDecompressCompleted() {
            XWalkLibraryLoader.startActivate(this);
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.ActivateListener
        public void onActivateFailed() {
            XWalkInitializer.this.mInitListener.onXWalkInitFailed();
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.ActivateListener
        public void onActivateCompleted() {
            XWalkInitializer.this.mIsXWalkReady = true;
            XWalkLibraryLoader.finishInit(XWalkInitializer.this.mContext);
            XWalkInitializer.this.mInitListener.onXWalkInitCompleted();
        }
    }
}
