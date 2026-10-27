package com.byd.vrassistant.viewInf.usbInstall;

import com.byd.vrassistant.view.CopyDataProgressBar;
import com.byd.vrassistant.viewInf.common.IController;
import com.byd.vrassistant.viewInf.common.IViewCommon;

/* JADX INFO: loaded from: classes2.dex */
public interface IUsbInstallManager extends IUsbInstall, IController, IViewCommon {
    void closeHardBar();

    CopyDataProgressBar getPb();

    void showHardBar();
}
