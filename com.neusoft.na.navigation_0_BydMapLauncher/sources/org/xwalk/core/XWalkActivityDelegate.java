package org.xwalk.core;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import org.xwalk.core.XWalkLibraryLoader;
import org.xwalk.core.XWalkUpdater;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkActivityDelegate implements XWalkLibraryLoader.DecompressListener, XWalkLibraryLoader.ActivateListener {
    private static final String TAG = "XWalkLib";
    private Activity mActivity;
    private boolean mBackgroundDecorated;
    private Runnable mCancelCommand;
    private Runnable mCompleteCommand;
    private XWalkDialogManager mDialogManager;
    private boolean mIsXWalkReady;
    private boolean mWillDecompress;
    private XWalkUpdater mXWalkUpdater;

    @Override // org.xwalk.core.XWalkLibraryLoader.ActivateListener
    public void onActivateStarted() {
    }

    public XWalkActivityDelegate(Activity activity, Runnable runnable, Runnable runnable2) {
        this.mActivity = activity;
        this.mCancelCommand = runnable;
        this.mCompleteCommand = runnable2;
        this.mDialogManager = new XWalkDialogManager(this.mActivity);
        XWalkLibraryLoader.prepareToInit(this.mActivity);
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

    public void setXWalkApkUrl(String str) {
        XWalkEnvironment.setXWalkApkUrl(str);
    }

    public XWalkDialogManager getDialogManager() {
        return this.mDialogManager;
    }

    public void onResume() {
        if (this.mIsXWalkReady) {
            return;
        }
        if (XWalkLibraryLoader.isInitializing() || XWalkLibraryLoader.isDownloading()) {
            Log.d(TAG, "Other initialization or download is proceeding");
        } else {
            Log.d(TAG, "Initialize by XWalkActivity");
            XWalkLibraryLoader.startDecompress(this);
        }
    }

    @Override // org.xwalk.core.XWalkLibraryLoader.DecompressListener
    public void onDecompressStarted() {
        this.mDialogManager.showDecompressProgress(new Runnable() { // from class: org.xwalk.core.XWalkActivityDelegate.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d(XWalkActivityDelegate.TAG, "Cancel by XWalkActivity");
                XWalkLibraryLoader.cancelDecompress();
            }
        });
        this.mWillDecompress = true;
    }

    @Override // org.xwalk.core.XWalkLibraryLoader.DecompressListener
    public void onDecompressCancelled() {
        this.mWillDecompress = false;
        this.mCancelCommand.run();
    }

    @Override // org.xwalk.core.XWalkLibraryLoader.DecompressListener
    public void onDecompressCompleted() {
        if (this.mWillDecompress) {
            this.mDialogManager.dismissDialog();
            this.mWillDecompress = false;
        }
        XWalkLibraryLoader.startActivate(this);
    }

    @Override // org.xwalk.core.XWalkLibraryLoader.ActivateListener
    public void onActivateFailed() {
        Window window;
        if (this.mXWalkUpdater == null) {
            if (XWalkEnvironment.isDownloadMode()) {
                this.mXWalkUpdater = new XWalkUpdater(new XWalkUpdater.XWalkBackgroundUpdateListener() { // from class: org.xwalk.core.XWalkActivityDelegate.2
                    @Override // org.xwalk.core.XWalkUpdater.XWalkBackgroundUpdateListener
                    public void onXWalkUpdateProgress(int i) {
                    }

                    @Override // org.xwalk.core.XWalkUpdater.XWalkBackgroundUpdateListener
                    public void onXWalkUpdateStarted() {
                    }

                    @Override // org.xwalk.core.XWalkUpdater.XWalkBackgroundUpdateListener
                    public void onXWalkUpdateCancelled() {
                        XWalkActivityDelegate.this.mCancelCommand.run();
                    }

                    @Override // org.xwalk.core.XWalkUpdater.XWalkBackgroundUpdateListener
                    public void onXWalkUpdateFailed() {
                        XWalkActivityDelegate.this.mCancelCommand.run();
                    }

                    @Override // org.xwalk.core.XWalkUpdater.XWalkBackgroundUpdateListener
                    public void onXWalkUpdateCompleted() {
                        XWalkLibraryLoader.startActivate(XWalkActivityDelegate.this);
                    }
                }, this.mActivity);
            } else {
                this.mXWalkUpdater = new XWalkUpdater(new XWalkUpdater.XWalkUpdateListener() { // from class: org.xwalk.core.XWalkActivityDelegate.3
                    @Override // org.xwalk.core.XWalkUpdater.XWalkUpdateListener
                    public void onXWalkUpdateCancelled() {
                        XWalkActivityDelegate.this.mCancelCommand.run();
                    }
                }, this.mActivity, this.mDialogManager);
            }
        }
        if (!this.mXWalkUpdater.updateXWalkRuntime() || XWalkEnvironment.isDownloadMode() || (window = this.mActivity.getWindow()) == null || window.getDecorView().getBackground() != null) {
            return;
        }
        Log.d(TAG, "Set the background to screen_background_dark");
        window.setBackgroundDrawableResource(android.R.drawable.screen_background_dark);
        this.mBackgroundDecorated = true;
    }

    @Override // org.xwalk.core.XWalkLibraryLoader.ActivateListener
    public void onActivateCompleted() {
        if (this.mDialogManager.isShowingDialog()) {
            this.mDialogManager.dismissDialog();
        }
        if (this.mBackgroundDecorated) {
            Log.d(TAG, "Recover the background");
            this.mActivity.getWindow().setBackgroundDrawable(null);
            this.mBackgroundDecorated = false;
        }
        this.mIsXWalkReady = true;
        XWalkLibraryLoader.finishInit(this.mActivity);
        this.mCompleteCommand.run();
    }
}
