package com.byd.vrassistant.view;

import android.content.Context;
import android.util.Log;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.eventprocessor.DialogEventProcessor;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewInf.IViewStateManager;
import com.byd.vrassistant.viewInf.IVoiceAreaHelper;
import com.byd.vrassistant.viewInf.ViewConfiguration;
import com.byd.vrassistant.viewInf.common.IViewManager;
import com.byd.vrassistant.viewInf.container.IContainerManager;
import com.byd.vrassistant.viewInf.usbInstall.IUsbInstallManager;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ViewManager implements IViewManager {
    private static final String TAG = "NEU_ViewManager";
    static IContainerManager containerManager;
    static IUsbInstallManager usbInstallManager;
    static IViewStateManager viewStateManager;
    static IVoiceAreaHelper voiceAreaHelper;
    CommonViewModel commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
    ViewManagerViewModel viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);

    private static final class InstanceHolder {
        static final ViewManager instance = new ViewManager();

        private InstanceHolder() {
        }
    }

    public static ViewManager get() {
        return InstanceHolder.instance;
    }

    @Override // com.byd.vrassistant.viewInf.common.IViewManager
    public void init(ViewConfiguration viewConfiguration) {
        viewStateManager = viewConfiguration.getViewStateManager();
        usbInstallManager = viewConfiguration.getUsbInstallManager();
        voiceAreaHelper = viewConfiguration.getVoiceAreaHelper();
        containerManager = viewConfiguration.getContainerManager();
        init(viewConfiguration.getContext());
    }

    public static IUsbInstallManager getUsbInstallManager() {
        return usbInstallManager;
    }

    public static IViewStateManager getViewStateManager() {
        return viewStateManager;
    }

    public static IVoiceAreaHelper getVoiceAreaHelper() {
        return voiceAreaHelper;
    }

    public static IContainerManager getContainerManager() {
        return containerManager;
    }

    @Override // com.byd.vrassistant.viewInf.common.IController
    public void init(Context context) {
        RLog.i(TAG, "init: ");
        getUsbInstallManager().init(context);
        getContainerManager().init(context);
    }

    @Override // com.byd.vrassistant.viewInf.common.IController
    public void unInit() {
        RLog.i(TAG, "unInit: ");
        getUsbInstallManager().unInit();
        getContainerManager().unInit();
    }

    public void updateLocale(Locale locale) {
        Log.i(TAG, "updateLocale: " + locale);
        ContextHolder.updateLocale(locale);
        this.commonViewModel.setVrLocale(locale);
        this.commonViewModel.setLanguageContext(ContextHolder.getApplicationContext());
        getViewStateManager().setContext(ContextHolder.getApplicationContext());
    }

    @Override // com.byd.vrassistant.viewInf.common.IViewManager
    public void removeAllView(boolean z) {
        this.viewManagerViewModel.setConversationState(true);
        ViewStateManger.get().setVrWaitToShow(false);
        DialogEventProcessor.getInstance().setInConversationSession(false);
        this.viewManagerViewModel.setDismissWithAnim(z);
        this.viewManagerViewModel.updateVoiceBarType(VoiceBarType.NONE);
        this.viewManagerViewModel.removeViewBlew();
    }
}
