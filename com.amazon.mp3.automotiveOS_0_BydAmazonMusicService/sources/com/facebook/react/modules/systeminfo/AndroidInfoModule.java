package com.facebook.react.modules.systeminfo;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.Nullable;
import androidx.core.os.EnvironmentCompat;
import com.amazon.music.skyfire.core.template.OwnerIdGenerator;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = AndroidInfoModule.NAME)
@SuppressLint({"HardwareIds"})
public class AndroidInfoModule extends NativePlatformConstantsAndroidSpec {
    private static final String IS_TESTING = "IS_TESTING";
    public static final String NAME = "PlatformConstants";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
    }

    public AndroidInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private String uiMode() {
        int currentModeType = ((UiModeManager) getReactApplicationContext().getSystemService("uimode")).getCurrentModeType();
        return currentModeType != 1 ? currentModeType != 2 ? currentModeType != 3 ? currentModeType != 4 ? currentModeType != 6 ? currentModeType != 7 ? EnvironmentCompat.MEDIA_UNKNOWN : "vrheadset" : OwnerIdGenerator.watch : "tv" : "car" : "desk" : "normal";
    }

    @Override // com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec
    @Nullable
    public Map<String, Object> getTypedExportedConstants() {
        HashMap map = new HashMap();
        map.put(JsonDocumentFields.VERSION, Integer.valueOf(Build.VERSION.SDK_INT));
        map.put("Release", Build.VERSION.RELEASE);
        map.put("Serial", Build.SERIAL);
        map.put("Fingerprint", Build.FINGERPRINT);
        map.put("Model", Build.MODEL);
        map.put("Manufacturer", Build.MANUFACTURER);
        map.put("Brand", Build.BRAND);
        map.put("isTesting", Boolean.valueOf("true".equals(System.getProperty(IS_TESTING)) || isRunningScreenshotTest().booleanValue()));
        map.put("reactNativeVersion", ReactNativeVersion.VERSION);
        map.put("uiMode", uiMode());
        return map;
    }

    @Override // com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec
    public String getAndroidID() {
        return Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "android_id");
    }

    private Boolean isRunningScreenshotTest() {
        try {
            Class.forName("com.facebook.testing.react.screenshots.ReactAppScreenshotTestActivity");
            return Boolean.TRUE;
        } catch (ClassNotFoundException unused) {
            return Boolean.FALSE;
        }
    }
}
