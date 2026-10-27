package com.byd.vrassistant.viewInf;

import android.content.Context;
import com.byd.vrassistant.viewInf.container.IContainerManager;
import com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IViewMangerInitCenter {
    protected abstract IContainerManager containerManager();

    protected abstract Context context();

    protected abstract IUsbInstallManager usbInstallManager();

    protected abstract IViewStateManager viewStateManager();

    protected abstract IVoiceAreaHelper voiceAreaHelper();

    public ViewConfiguration getConfiguration() {
        return new ViewConfiguration().addContext(context()).addUsbInstallManager(usbInstallManager()).addViewStateManager(viewStateManager()).addVoiceAreaHelper(voiceAreaHelper()).addContainerManager(containerManager());
    }
}
