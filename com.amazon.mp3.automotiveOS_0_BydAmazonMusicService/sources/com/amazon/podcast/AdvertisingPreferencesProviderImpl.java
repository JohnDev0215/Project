package com.amazon.podcast;

import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.platform.providers.AdvertisingPreferencesProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdvertisingPreferencesProviderImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/podcast/AdvertisingPreferencesProviderImpl;", "Lcom/amazon/music/platform/providers/AdvertisingPreferencesProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "defaultAdPrefsValue", "", "isBehavioralAdsEnabled", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AdvertisingPreferencesProviderImpl implements AdvertisingPreferencesProvider {
    private final String TAG = AdvertisingPreferencesProviderImpl.class.getSimpleName();
    private final boolean defaultAdPrefsValue;

    @Override // com.amazon.music.platform.providers.AdvertisingPreferencesProvider
    public boolean isBehavioralAdsEnabled() {
        try {
            Intrinsics.checkNotNullExpressionValue(AccountManagerSingleton.get().getUser(), "get().user");
            return !r0.isCCBAOptOut();
        } catch (DataNotReadyException unused) {
            Log.error(this.TAG, "User data not ready. Returning false as the default opt-out value.");
            return this.defaultAdPrefsValue;
        } catch (MusicAccountNotCreatedException unused2) {
            Log.error(this.TAG, "Music account not created. Returning false as the default opt-out value.");
            return this.defaultAdPrefsValue;
        }
    }
}
