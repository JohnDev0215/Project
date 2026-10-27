package com.android.systemui;

import android.app.Notification;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class SystemUI implements SysUiServiceProvider {
    public Map<Class<?>, Object> mComponents;
    public Context mContext;

    public abstract void dump(FileDescriptor fd, PrintWriter pw, String[] args);

    protected abstract void onBootCompleted();

    protected abstract void onConfigurationChanged(Configuration newConfig);

    public abstract void start();

    @Override // com.android.systemui.SysUiServiceProvider
    public <T> T getComponent(Class<T> cls) {
        Map<Class<?>, Object> map = this.mComponents;
        if (map != null) {
            return (T) map.get(cls);
        }
        return null;
    }

    public <T, C extends T> void putComponent(Class<T> interfaceType, C component) {
        Map<Class<?>, Object> map = this.mComponents;
        if (map != null) {
            map.put(interfaceType, component);
        }
    }

    public static void overrideNotificationAppName(Context context, Notification.Builder n, boolean system) {
        String string;
        Bundle bundle = new Bundle();
        if (system) {
            string = context.getString(android.R.string.js_dialog_before_unload_negative_button);
        } else {
            string = context.getString(android.R.string.js_dialog_before_unload);
        }
        bundle.putString("android.substName", string);
        n.addExtras(bundle);
    }
}
