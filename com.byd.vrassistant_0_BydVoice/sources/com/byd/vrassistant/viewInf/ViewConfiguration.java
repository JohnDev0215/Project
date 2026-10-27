package com.byd.vrassistant.viewInf;

import android.content.Context;
import com.byd.vrassistant.viewInf.container.IContainerManager;
import com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager;

/* JADX INFO: loaded from: classes2.dex */
public class ViewConfiguration {
    IContainerManager containerManager;
    Context context;
    IUsbInstallManager usbInstallManager;
    IViewStateManager viewStateManager;
    IVoiceAreaHelper voiceAreaHelper;

    public Context getContext() {
        return this.context;
    }

    public ViewConfiguration addContext(Context context) {
        this.context = context;
        return this;
    }

    public IUsbInstallManager getUsbInstallManager() {
        return this.usbInstallManager;
    }

    public ViewConfiguration addUsbInstallManager(IUsbInstallManager iUsbInstallManager) {
        this.usbInstallManager = iUsbInstallManager;
        return this;
    }

    public IViewStateManager getViewStateManager() {
        return this.viewStateManager;
    }

    public ViewConfiguration addViewStateManager(IViewStateManager iViewStateManager) {
        this.viewStateManager = iViewStateManager;
        return this;
    }

    public IVoiceAreaHelper getVoiceAreaHelper() {
        return this.voiceAreaHelper;
    }

    public ViewConfiguration addVoiceAreaHelper(IVoiceAreaHelper iVoiceAreaHelper) {
        this.voiceAreaHelper = iVoiceAreaHelper;
        return this;
    }

    public ViewConfiguration addContainerManager(IContainerManager iContainerManager) {
        this.containerManager = iContainerManager;
        return this;
    }

    public IContainerManager getContainerManager() {
        return this.containerManager;
    }
}
