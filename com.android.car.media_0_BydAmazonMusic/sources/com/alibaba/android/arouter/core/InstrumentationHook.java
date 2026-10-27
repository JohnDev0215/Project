package com.alibaba.android.arouter.core;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import java.lang.reflect.Field;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class InstrumentationHook extends Instrumentation {
    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String[] stringArrayExtra;
        Class<?> clsLoadClass = classLoader.loadClass(str);
        Object objNewInstance = clsLoadClass.newInstance();
        if (ARouter.canAutoInject() && (stringArrayExtra = intent.getStringArrayExtra(ARouter.AUTO_INJECT)) != null && stringArrayExtra.length > 0) {
            for (String str2 : stringArrayExtra) {
                Object obj = intent.getExtras().get(TextUtils.getLeft(str2));
                if (obj != null) {
                    try {
                        Field declaredField = clsLoadClass.getDeclaredField(TextUtils.getLeft(str2));
                        declaredField.setAccessible(true);
                        declaredField.set(objNewInstance, obj);
                    } catch (Exception e) {
                        ILogger iLogger = ARouter.logger;
                        StringBuilder sbK = c.k("Inject values for activity error! [");
                        sbK.append(e.getMessage());
                        sbK.append("]");
                        iLogger.error("ARouter::", sbK.toString());
                    }
                }
            }
        }
        return (Activity) objNewInstance;
    }
}
