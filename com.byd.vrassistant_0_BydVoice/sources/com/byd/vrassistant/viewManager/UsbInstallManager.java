package com.byd.vrassistant.viewManager;

import android.content.Context;
import android.view.WindowManager;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.view.CopyDataProgressBar;
import com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager;

/* JADX INFO: loaded from: classes2.dex */
public class UsbInstallManager implements IUsbInstallManager {
    private static final String TAG = "UsbInstallManager";
    private WindowManager.LayoutParams mLayoutHardkeyParams;
    private WindowManager mWindowManager;
    public CopyDataProgressBar pb;

    static class InstanceHolder {
        static UsbInstallManager INSTANCE = new UsbInstallManager();

        InstanceHolder() {
        }
    }

    public static UsbInstallManager get() {
        return InstanceHolder.INSTANCE;
    }

    @Override // com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager
    public CopyDataProgressBar getPb() {
        return this.pb;
    }

    @Override // com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager
    public void showHardBar() {
        CopyDataProgressBar copyDataProgressBar = this.pb;
        if (copyDataProgressBar == null || copyDataProgressBar.getParent() != null) {
            return;
        }
        this.mWindowManager.addView(this.pb, this.mLayoutHardkeyParams);
    }

    @Override // com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager
    public void closeHardBar() {
        CopyDataProgressBar copyDataProgressBar = this.pb;
        if (copyDataProgressBar == null || copyDataProgressBar.getParent() == null) {
            return;
        }
        this.mWindowManager.removeViewImmediate(this.pb);
    }

    @Override // com.byd.vrassistant.viewInf.common.IController
    public void init(Context context) {
        if (this.pb != null) {
            RLog.i(TAG, "init: CopyDataProgressBar is not null, so won't init");
            return;
        }
        this.mWindowManager = (WindowManager) ContextHolder.getApplicationContext().getSystemService("window");
        this.pb = new CopyDataProgressBar(context.getApplicationContext());
        this.mLayoutHardkeyParams = getHardkeyViewParams();
    }

    @Override // com.byd.vrassistant.viewInf.common.IController
    public void unInit() {
        closeHardBar();
        this.pb = null;
    }

    private WindowManager.LayoutParams getHardkeyViewParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = -3;
        layoutParams.type = 2038;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 17;
        layoutParams.flags = 393248;
        return layoutParams;
    }
}
