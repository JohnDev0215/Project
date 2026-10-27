package com.amazon.mp3.reactnative.modules;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceCacheModule extends ReactContextBaseJavaModule {
    public static String REACT_SHARED_PREFERENCES = "device_cache_shared_preferences";
    private final SharedPreferences sharedPreferences;

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return "DeviceCacheModule";
    }

    DeviceCacheModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.sharedPreferences = getReactApplicationContext().getSharedPreferences(REACT_SHARED_PREFERENCES, 0);
    }

    @ReactMethod
    public void setString(String key, String value) {
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    @ReactMethod
    public void setNumber(String key, int number) {
        this.sharedPreferences.edit().putInt(key, number).apply();
    }

    @ReactMethod
    public void get(String key, Promise promise) {
        promise.resolve(this.sharedPreferences.getString(key, ""));
    }
}
