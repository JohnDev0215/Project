package com.amazon.mp3.baseviews.model.configuration;

import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackModelConfiguration.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0006\u0010\f\u001a\u00020\u0006J\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006J\u001f\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/baseviews/model/configuration/TrackModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "(Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "shouldShowAlbumTrackNumber", "", "parentMetadata", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "shouldShowContextMenuAddToMyMusicButton", "contextMenuPageType", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;", "shouldShowContextMenuCustomersAlsoListenedToButton", "shouldShowLibrarySongsPageHeader", "shouldShowPlaylistTrackNumber", "shouldShowRowCta", "", "trackMetadata", "(Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;)Ljava/lang/Integer;", "shouldShowRowStationIcon", "isOwned", "shouldShowTrackNumber", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TrackModelConfiguration extends ModelConfiguration {

    /* JADX INFO: compiled from: TrackModelConfiguration.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContextMenuPageType.values().length];
            iArr[ContextMenuPageType.LONG_PRESS.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TrackModelConfiguration() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TrackModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        super(cachedCapabilityActionProviderImpl);
    }

    public /* synthetic */ TrackModelConfiguration(CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    public final boolean shouldShowTrackNumber() {
        if (Feature.sonic_rush.isEnabled(true)) {
            return Feature.track_number_capability.isEnabled(true);
        }
        return true;
    }

    public final boolean shouldShowPlaylistTrackNumber() {
        return shouldShowTrackNumber() && Feature.sonic_rush.isEnabled(true) && Feature.hide_track_numbers_playlists.isEnabled(true);
    }

    public final Integer shouldShowRowCta(EntityEligibilitiesProvider trackMetadata, EntityEligibilitiesProvider parentMetadata) {
        Intrinsics.checkNotNullParameter(trackMetadata, "trackMetadata");
        if (Feature.row_cta_visibility.isEnabled(true)) {
            return new EligibilityUtil(getCachedActionProviderImpl()).getPlayIconType(trackMetadata, parentMetadata);
        }
        return null;
    }

    public final boolean shouldShowAlbumTrackNumber(EntityEligibilitiesProvider parentMetadata) {
        Boolean onDemand;
        if (shouldShowTrackNumber()) {
            return true;
        }
        if (parentMetadata == null || (onDemand = new EligibilityUtil(getCachedActionProviderImpl()).getOnDemand(parentMetadata)) == null) {
            return false;
        }
        return onDemand.booleanValue();
    }

    @JvmOverloads
    public final boolean shouldShowContextMenuAddToMyMusicButton(ContextMenuPageType contextMenuPageType) {
        Intrinsics.checkNotNullParameter(contextMenuPageType, "contextMenuPageType");
        if (WhenMappings.$EnumSwitchMapping$0[contextMenuPageType.ordinal()] == 1) {
            return Feature.not_library_or_detail_track_context_menu_add_to_my_music.isEnabled();
        }
        return Feature.track_context_menu_add_to_my_music.isEnabled();
    }

    public final boolean shouldShowRowStationIcon(boolean isOwned) {
        return (isOwned || !Feature.horizontal_row_album_detail_page_station_icon.isEnabled(true) || Feature.sonic_rush.isEnabled(true)) ? false : true;
    }

    public final boolean shouldShowContextMenuCustomersAlsoListenedToButton() {
        return Feature.not_library_or_detail_track_context_menu_customers_also_listened_to.isEnabled();
    }

    public final boolean shouldShowLibrarySongsPageHeader() {
        return Feature.library_songs_page_header_visibility.isEnabled();
    }
}
