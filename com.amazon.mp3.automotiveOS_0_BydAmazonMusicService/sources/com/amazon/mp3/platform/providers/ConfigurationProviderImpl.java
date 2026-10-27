package com.amazon.mp3.platform.providers;

import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.music.platform.providers.ConfigurationProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfigurationProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/platform/providers/ConfigurationProviderImpl;", "Lcom/amazon/music/platform/providers/ConfigurationProvider;", "()V", "getBooleanForKey", "", "key", "", "getIntForKey", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConfigurationProviderImpl implements ConfigurationProvider {
    @Override // com.amazon.music.platform.providers.ConfigurationProvider
    public boolean getBooleanForKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return FeatureFlagsProvider.getFeatureFlagProvider().getBooleanForKey(key);
    }

    @Override // com.amazon.music.platform.providers.ConfigurationProvider
    public int getIntForKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return FeatureFlagsProvider.getFeatureFlagProvider().getIntForKey(key);
    }
}
