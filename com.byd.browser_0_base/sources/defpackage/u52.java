package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.app.bluetooth.BluetoothNotificationService;
import org.chromium.chrome.browser.app.usb.UsbNotificationService;
import org.chromium.chrome.browser.media.MediaCaptureNotificationService;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class u52 implements Runnable {
    public final /* synthetic */ b62 e;

    public u52(b62 b62Var) {
        this.e = b62Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Set<String> setI;
        Set<String> setI2 = SharedPreferencesManager.getInstance().i("WebRTCNotificationIds", null);
        if (setI2 != null && !setI2.isEmpty()) {
            Context context = xz.a;
            context.startService(new Intent(context, (Class<?>) MediaCaptureNotificationService.class));
        }
        if (N.Mudil8Bg("WebBluetoothNewPermissionsBackend") && (setI = SharedPreferencesManager.getInstance().i("Chrome.Bluetooth.NotificationIds", null)) != null && !setI.isEmpty()) {
            Context context2 = xz.a;
            context2.startService(new Intent(context2, (Class<?>) BluetoothNotificationService.class));
        }
        Set<String> setI3 = SharedPreferencesManager.getInstance().i("Chrome.USB.NotificationIds", null);
        if (setI3 != null && !setI3.isEmpty()) {
            Context context3 = xz.a;
            context3.startService(new Intent(context3, (Class<?>) UsbNotificationService.class));
        }
        b62 b62Var = this.e;
        b62Var.getClass();
        if (!SysUtils.isLowEndDevice()) {
            Context context4 = xz.a;
            o90 o90Var = ChildProcessLauncherHelperImpl.m;
            LauncherThread.a(new wn(context4, N.M0MXVnOj("BindingManagerConnectionLimit", "max_connections", -1)));
        }
        b62Var.getClass();
        InputMethodManager inputMethodManager = (InputMethodManager) xz.a.getSystemService("input_method");
        List<InputMethodInfo> enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        ArrayList arrayList = new ArrayList();
        Iterator<InputMethodInfo> it = enabledInputMethodList.iterator();
        while (it.hasNext()) {
            for (InputMethodSubtype inputMethodSubtype : inputMethodManager.getEnabledInputMethodSubtypeList(it.next(), true)) {
                if (inputMethodSubtype.getMode().equals("keyboard")) {
                    String str = inputMethodSubtype.getLocale().split("_")[0];
                    if (!arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        oj2.R(arrayList.size(), "InputMethod.ActiveCount");
    }
}
