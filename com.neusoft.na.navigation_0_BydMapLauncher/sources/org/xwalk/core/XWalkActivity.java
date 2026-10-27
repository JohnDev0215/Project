package org.xwalk.core;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public abstract class XWalkActivity extends Activity {
    private XWalkActivityDelegate mActivityDelegate;

    protected abstract void onXWalkReady();

    protected void onXWalkFailed() {
        finish();
    }

    protected XWalkDialogManager getDialogManager() {
        return this.mActivityDelegate.getDialogManager();
    }

    public boolean isXWalkReady() {
        return this.mActivityDelegate.isXWalkReady();
    }

    public boolean isSharedMode() {
        return this.mActivityDelegate.isSharedMode();
    }

    public boolean isDownloadMode() {
        return this.mActivityDelegate.isDownloadMode();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivityDelegate = new XWalkActivityDelegate(this, new Runnable() { // from class: org.xwalk.core.XWalkActivity.1
            @Override // java.lang.Runnable
            public void run() {
                XWalkActivity.this.onXWalkFailed();
            }
        }, new Runnable() { // from class: org.xwalk.core.XWalkActivity.2
            @Override // java.lang.Runnable
            public void run() {
                XWalkActivity.this.onXWalkReady();
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mActivityDelegate.onResume();
    }
}
