package com.amazon.music.voice;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public interface AlexaAccessProvider {
    boolean hasAnyNetworkConnection(Context context);

    boolean inImmersiveMode(@Nullable Activity activity);

    boolean isHyperloopFeatureSupported();

    boolean isTextCapabilityFeatureSupported();
}
