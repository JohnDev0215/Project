package com.amazon.mp3.baseviews.model.configuration;

import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserPlaylistModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/UserPlaylistModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "(Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "TAG", "", "kotlin.jvm.PlatformType", "getCachedActionProviderImpl", "()Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "shouldShowContextMenuDownloadUpsellButton", "", "userPlaylist", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistModelConfiguration extends ContainerModelConfiguration {
    private final String TAG;
    private final CachedCapabilityActionProviderImpl cachedActionProviderImpl;

    public UserPlaylistModelConfiguration() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public UserPlaylistModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        super(cachedCapabilityActionProviderImpl);
        this.cachedActionProviderImpl = cachedCapabilityActionProviderImpl;
        this.TAG = UserPlaylistModelConfiguration.class.getName();
    }

    public /* synthetic */ UserPlaylistModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    @Override // com.amazon.mp3.baseviews.model.configuration.ModelConfiguration
    public CachedCapabilityActionProviderImpl getCachedActionProviderImpl() {
        return this.cachedActionProviderImpl;
    }

    public final boolean shouldShowContextMenuDownloadUpsellButton(PrimePlaylist userPlaylist) {
        Intrinsics.checkNotNullParameter(userPlaylist, "userPlaylist");
        try {
            String playlistId = userPlaylist.getPlaylistId();
            if (!(playlistId == null || playlistId.length() == 0)) {
                return userPlaylist.getOwnership() ? FindFeatureGating.USER_PLAYLIST_SHOW_UPSELL_ENABLED.isEnabled() : super.shouldShowContextMenuDownloadUpsellButton((EntityEligibilitiesProvider) userPlaylist);
            }
            Log.error(this.TAG, "playlistId should not be empty");
            return true;
        } catch (Exception e) {
            Log.error(this.TAG, e.getMessage());
            return true;
        }
    }
}
