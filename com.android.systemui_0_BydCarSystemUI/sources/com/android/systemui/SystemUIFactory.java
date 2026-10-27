package com.android.systemui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.statusbar.phone.BackGestureTfClassifierProvider;
import com.android.systemui.util.SystemUIException;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class SystemUIFactory {
    static SystemUIFactory mFactory;

    public static SystemUIFactory getInstance() {
        return mFactory;
    }

    public static void createFromConfig(Context context) {
        String string = context.getString(R.string.config_systemUIFactoryComponent);
        if (string == null || string.length() == 0) {
            throw new SystemUIException("No SystemUIFactory component configured");
        }
        try {
            mFactory = (SystemUIFactory) context.getClassLoader().loadClass(string).newInstance();
        } catch (Throwable th) {
            BydLogHelper.logD("Error creating SystemUIFactory component: " + string + " , t = " + th);
            throw new SystemUIException(th);
        }
    }

    public ScreenshotNotificationSmartActionsProvider createScreenshotNotificationSmartActionsProvider(Context context, Executor executor, Handler uiHandler) {
        return new ScreenshotNotificationSmartActionsProvider();
    }

    public BackGestureTfClassifierProvider createBackGestureTfClassifierProvider(AssetManager am, String modelName) {
        return new BackGestureTfClassifierProvider();
    }
}
