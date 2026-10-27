package com.amazon.mp3.voice;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.voice.AlexaAccessProvider;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaAccessDelegator implements AlexaAccessProvider {
    @Override // com.amazon.music.voice.AlexaAccessProvider
    public boolean hasAnyNetworkConnection(final Context context) {
        return ConnectivityUtil.hasAnyInternetConnection(context);
    }

    @Override // com.amazon.music.voice.AlexaAccessProvider
    public boolean isHyperloopFeatureSupported() {
        return AmazonApplication.getCapabilities().isHyperloopCapabilityEnabled();
    }

    @Override // com.amazon.music.voice.AlexaAccessProvider
    public boolean isTextCapabilityFeatureSupported() {
        return AmazonApplication.getCapabilities().isAOMTextCapabilityEnabled();
    }

    @Override // com.amazon.music.voice.AlexaAccessProvider
    public boolean inImmersiveMode(@Nullable final Activity activity) {
        return ActivityKt.inImmersiveMode(activity);
    }
}
