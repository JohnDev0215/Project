package com.amazon.mp3.baseviews.model.configuration;

import PlaybackInterface.v1_0.PlaybackEvent;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.playback.capabilities.UpsellReason;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0006\u0010\u0003\u001a\u00020\u0002J!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\t\u001a\u00020\u0002R$\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "", "shouldShowContextMenuAddToPlaylistButton", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "entity", "isInLibrary", "shouldShowContextMenuDownloadUpsellButton", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;Ljava/lang/Boolean;)Z", "shouldShowCommentaryToggle", "entityItem", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "getEntityItem", "()Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "setEntityItem", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;)V", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "cachedActionProviderImpl", "<init>", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumModelConfiguration extends ContainerModelConfiguration {
    private EntityEligibilitiesProvider entityItem;

    public AlbumModelConfiguration() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ AlbumModelConfiguration(EntityEligibilitiesProvider entityEligibilitiesProvider, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : entityEligibilitiesProvider, (i & 2) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    public final EntityEligibilitiesProvider getEntityItem() {
        return this.entityItem;
    }

    public AlbumModelConfiguration(EntityEligibilitiesProvider entityEligibilitiesProvider, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        super(cachedCapabilityActionProviderImpl);
        this.entityItem = entityEligibilitiesProvider;
    }

    public final boolean shouldShowContextMenuAddToPlaylistButton() {
        return Feature.album_context_menu_add_to_playlist.isEnabled();
    }

    public final boolean shouldShowContextMenuDownloadUpsellButton(EntityEligibilitiesProvider entity, Boolean isInLibrary) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        if (!Intrinsics.areEqual(canPerformCapability(PlaybackEvent.UPSELL, entity), new Outcome.Success(UpsellReason.NotOnDemandPlayable)) && !Intrinsics.areEqual(canPerformCapability(PlaybackEvent.UPSELL, entity), new Outcome.Success(UpsellReason.NotDownloadable))) {
            Outcome<?> outcomeCanPerformCapability = canPerformCapability("ON_DEMAND", entity);
            Boolean bool = Boolean.TRUE;
            if (!Intrinsics.areEqual(outcomeCanPerformCapability, new Outcome.Success(bool)) || !Intrinsics.areEqual(isInLibrary, bool)) {
                return false;
            }
        }
        return true;
    }

    public final boolean shouldShowCommentaryToggle() {
        return Feature.artist_commentary.isEnabled();
    }
}
