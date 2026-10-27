package com.amazon.mp3.reactnative.modules;

import android.net.Uri;
import android.util.Log;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.router.UnknownDestinationException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes4.dex */
public class DeeplinkModule extends ReactContextBaseJavaModule {
    private final String TAG;
    private final DeeplinksManager deeplinksManager;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "DeeplinkModule";
    }

    DeeplinkModule(ReactApplicationContext context) {
        super(context);
        this.TAG = DeeplinkModule.class.getSimpleName();
        this.deeplinksManager = DeeplinksManager.get(context);
    }

    @ReactMethod
    public void goToDeeplink(String deeplink) {
        try {
            this.deeplinksManager.handleInternal(Uri.parse(deeplink));
        } catch (UnknownDeeplinkException | UnknownDestinationException | UnsupportedEncodingException | MalformedURLException | URISyntaxException e) {
            Log.e(this.TAG, "Exception handling deeplink", e);
        }
    }
}
