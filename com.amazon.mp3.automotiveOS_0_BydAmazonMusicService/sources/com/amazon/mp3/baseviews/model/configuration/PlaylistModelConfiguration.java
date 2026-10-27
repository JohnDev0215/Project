package com.amazon.mp3.baseviews.model.configuration;

import PlaybackInterface.v1_0.PlaybackEvent;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.playback.capabilities.UpsellReason;
import com.amazon.music.platform.playback.data.ShuffleType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\bJ\u0010\u0010\u0014\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "(Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "getCachedActionProviderImpl", "()Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "canPlayOnDemand", "", "entity", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "shouldDisplayFollowerCount", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/AbstractItem;", "shouldDisplayFollowerCountCatalogPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "shouldDisplayFollowerCountUserPlaylist", "Lcom/amazon/mp3/library/item/Playlist;", "shouldShowContextMenuDownloadUpsellButton", "isCatalog", "shouldShowHeaderFilterContextMenuButton", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistModelConfiguration extends ContainerModelConfiguration {
    private final CachedCapabilityActionProviderImpl cachedActionProviderImpl;

    public PlaylistModelConfiguration() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public PlaylistModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        super(cachedCapabilityActionProviderImpl);
        this.cachedActionProviderImpl = cachedCapabilityActionProviderImpl;
    }

    public /* synthetic */ PlaylistModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    @Override // com.amazon.mp3.baseviews.model.configuration.ModelConfiguration
    public CachedCapabilityActionProviderImpl getCachedActionProviderImpl() {
        return this.cachedActionProviderImpl;
    }

    public final boolean shouldShowContextMenuDownloadUpsellButton(EntityEligibilitiesProvider entity, boolean isCatalog) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (!Feature.playlist_context_menu_download.isEnabled()) {
            return false;
        }
        AbstractItem abstractItem = (AbstractItem) entity;
        if (abstractItem.isInLibrary()) {
            return Intrinsics.areEqual(canPerformCapability("SHUFFLE", entity), new Outcome.Success(ShuffleType.MIXED)) && Intrinsics.areEqual(canPerformCapability(PlaybackEvent.UPSELL, entity), new Outcome.Success(UpsellReason.NotOnDemandPlayable));
        }
        if (isCatalog || abstractItem.isAllOwned() || abstractItem.isNotOwned()) {
            return Intrinsics.areEqual(canPerformCapability(PlaybackEvent.UPSELL, entity), new Outcome.Success(UpsellReason.NotOnDemandPlayable));
        }
        return false;
    }

    public final boolean shouldShowHeaderFilterContextMenuButton(EntityEligibilitiesProvider entity) {
        if (entity == null) {
            return false;
        }
        return Intrinsics.areEqual(canPerformCapability("ON_DEMAND", entity), new Outcome.Success(Boolean.TRUE));
    }

    public final boolean shouldDisplayFollowerCount(AbstractItem playlist) {
        boolean zShouldDisplayFollowerCountCatalogPlaylist;
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        if (playlist instanceof Playlist) {
            zShouldDisplayFollowerCountCatalogPlaylist = shouldDisplayFollowerCountUserPlaylist((Playlist) playlist);
        } else {
            zShouldDisplayFollowerCountCatalogPlaylist = playlist instanceof CatalogPlaylist ? shouldDisplayFollowerCountCatalogPlaylist((CatalogPlaylist) playlist) : false;
        }
        return zShouldDisplayFollowerCountCatalogPlaylist && Feature.pdp_follower_count.isEnabled();
    }

    private final boolean shouldDisplayFollowerCountUserPlaylist(Playlist playlist) {
        return playlist.getVisibility() == MediaProvider.Playlists.VisibilityState.PUBLIC;
    }

    private final boolean shouldDisplayFollowerCountCatalogPlaylist(CatalogPlaylist playlist) {
        return playlist.isSharedUserPlaylist() && playlist.getVisibility() == MediaProvider.Playlists.VisibilityState.PUBLIC;
    }

    public final boolean canPlayOnDemand(EntityEligibilitiesProvider entity) {
        if (entity == null) {
            return false;
        }
        return Intrinsics.areEqual(canPerformCapability("ON_DEMAND", entity), new Outcome.Success(Boolean.TRUE));
    }
}
