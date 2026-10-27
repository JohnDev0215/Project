package com.byd.vrassistant.viewManager;

import android.content.Context;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.view.ViewStateManger;
import com.byd.vrassistant.viewInf.IViewMangerInitCenter;
import com.byd.vrassistant.viewInf.IViewStateManager;
import com.byd.vrassistant.viewInf.IVoiceAreaHelper;
import com.byd.vrassistant.viewInf.container.IContainerManager;
import com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager;

/* JADX INFO: loaded from: classes2.dex */
public class ViewManagerInitCenter extends IViewMangerInitCenter {
    @Override // com.byd.vrassistant.viewInf.IViewMangerInitCenter
    protected Context context() {
        return ContextHolder.getApplicationContext();
    }

    @Override // com.byd.vrassistant.viewInf.IViewMangerInitCenter
    protected IUsbInstallManager usbInstallManager() {
        return UsbInstallManager.get();
    }

    @Override // com.byd.vrassistant.viewInf.IViewMangerInitCenter
    protected IViewStateManager viewStateManager() {
        return ViewStateManger.get();
    }

    @Override // com.byd.vrassistant.viewInf.IViewMangerInitCenter
    protected IVoiceAreaHelper voiceAreaHelper() {
        return new VoiceAreaHelper();
    }

    @Override // com.byd.vrassistant.viewInf.IViewMangerInitCenter
    protected IContainerManager containerManager() {
        return ContainerManager.get();
    }
}
