package com.amazon.mp3.reactnative.modules;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.actionbar.OverflowMenuPresenterKt;
import com.amazon.mp3.util.BauhausToastUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/* JADX INFO: loaded from: classes4.dex */
public class ToastModule extends ReactContextBaseJavaModule {
    private final FragmentActivity hostActivity;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ToastModule";
    }

    ToastModule(ReactApplicationContext context, FragmentActivity hostActivity) {
        super(context);
        this.hostActivity = hostActivity;
    }

    @ReactMethod
    public void displayToast(String message) {
        Activity currentActivity = this.hostActivity;
        if (currentActivity == null) {
            currentActivity = getCurrentActivity();
        }
        BauhausToastUtils.showTextToast(currentActivity, message, 0);
    }

    @ReactMethod
    public void displayAppContextMenu(double x, double y, double width, final double height) {
        final Activity currentActivity = this.hostActivity;
        if (currentActivity == null) {
            currentActivity = getCurrentActivity();
        }
        if (currentActivity == null) {
            return;
        }
        currentActivity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.reactnative.modules.ToastModule.1
            @Override // java.lang.Runnable
            public void run() {
                OverflowMenuPresenterKt.showOverflowMenu(currentActivity, (int) height);
            }
        });
    }
}
