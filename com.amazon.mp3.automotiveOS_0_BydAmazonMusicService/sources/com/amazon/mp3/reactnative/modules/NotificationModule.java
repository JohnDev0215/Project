package com.amazon.mp3.reactnative.modules;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.amazon.mp3.reactnative.GenericReactActivity;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationModule extends ReactContextBaseJavaModule {
    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return "NotificationModule";
    }

    NotificationModule(ReactApplicationContext context) {
        super(context);
    }

    @ReactMethod
    public void notifyToCloseView() {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (currentActivity instanceof GenericReactActivity) {
                currentActivity.finish();
            } else {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.mp3.reactnative.modules.NotificationModule$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        currentActivity.onBackPressed();
                    }
                });
            }
        }
    }
}
