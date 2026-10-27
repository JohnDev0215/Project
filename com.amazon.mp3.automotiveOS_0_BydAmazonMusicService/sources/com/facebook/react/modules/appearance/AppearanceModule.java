package com.facebook.react.modules.appearance;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.fbreact.specs.NativeAppearanceSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/* JADX INFO: loaded from: classes6.dex */
@ReactModule(name = AppearanceModule.NAME)
public class AppearanceModule extends NativeAppearanceSpec {
    private static final String APPEARANCE_CHANGED_EVENT_NAME = "appearanceChanged";
    public static final String NAME = "Appearance";
    private String mColorScheme;

    @Nullable
    private final OverrideColorScheme mOverrideColorScheme;

    public interface OverrideColorScheme {
        String getScheme();
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void removeListeners(double d) {
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null);
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext, @Nullable OverrideColorScheme overrideColorScheme) {
        super(reactApplicationContext);
        this.mColorScheme = "light";
        this.mOverrideColorScheme = overrideColorScheme;
        this.mColorScheme = colorSchemeForCurrentConfiguration(reactApplicationContext);
    }

    private String colorSchemeForCurrentConfiguration(Context context) {
        OverrideColorScheme overrideColorScheme = this.mOverrideColorScheme;
        if (overrideColorScheme != null) {
            return overrideColorScheme.getScheme();
        }
        return (context.getResources().getConfiguration().uiMode & 48) != 32 ? "light" : "dark";
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public String getColorScheme() {
        Context currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            currentActivity = getReactApplicationContext();
        }
        String strColorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(currentActivity);
        this.mColorScheme = strColorSchemeForCurrentConfiguration;
        return strColorSchemeForCurrentConfiguration;
    }

    public void onConfigurationChanged(Context context) {
        String strColorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(context);
        if (this.mColorScheme.equals(strColorSchemeForCurrentConfiguration)) {
            return;
        }
        this.mColorScheme = strColorSchemeForCurrentConfiguration;
        emitAppearanceChanged(strColorSchemeForCurrentConfiguration);
    }

    public void emitAppearanceChanged(String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("colorScheme", str);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(APPEARANCE_CHANGED_EVENT_NAME, writableMapCreateMap);
        }
    }
}
