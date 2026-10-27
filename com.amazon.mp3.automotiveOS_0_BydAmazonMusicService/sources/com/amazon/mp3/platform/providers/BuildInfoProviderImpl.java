package com.amazon.mp3.platform.providers;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.providers.BuildInfoProvider;
import kotlin.Metadata;

/* JADX INFO: compiled from: BuildInfoProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/platform/providers/BuildInfoProviderImpl;", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "()V", Splash.PARAMS_APP_VERSION, "", "getAppVersion", "()Ljava/lang/String;", "isDebug", "", "()Z", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BuildInfoProviderImpl implements BuildInfoProvider {
    private final String appVersion = "23.11.0";
    private final boolean isDebug;

    @Override // com.amazon.music.platform.providers.BuildInfoProvider
    /* JADX INFO: renamed from: isDebug, reason: from getter */
    public boolean getIsDebug() {
        return this.isDebug;
    }

    @Override // com.amazon.music.platform.providers.BuildInfoProvider
    public String getAppVersion() {
        return this.appVersion;
    }
}
