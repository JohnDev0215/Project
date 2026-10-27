package com.amazon.mp3.catalog.fragment;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PrimePlaylistDetailMetadataClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u0011\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/PrimePlaylistDetailMetadataClickListener;", "Lcom/amazon/mp3/catalog/fragment/DefaultMetadataClickListener;", "fragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "isPlaylistFree", "", "isPlaylistFreeOnDemand", "displayExplicitContentFilteredDialog", "", "metadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "onContentClicked", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "onContentListClicked", "contentMetadataList", "", "positionToStart", "", "contentListTitle", "", "updatePlaylistInfo", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PrimePlaylistDetailMetadataClickListener extends DefaultMetadataClickListener {
    private boolean isPlaylistFree;
    private boolean isPlaylistFreeOnDemand;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimePlaylistDetailMetadataClickListener(Fragment fragment, PageType pageType) {
        super(fragment, pageType, null, 4, null);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
    }

    public final void updatePlaylistInfo(CatalogPlaylist playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        this.isPlaylistFree = playlist.isFree();
        this.isPlaylistFreeOnDemand = playlist.isFreeOnDemand();
    }

    @Override // com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener, com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        boolean z = false;
        if (positionToStart >= 0 && positionToStart < contentMetadataList.size()) {
            z = true;
        }
        if (z) {
            if (contentMetadataList.get(positionToStart) instanceof TrackMetadata) {
                onContentClicked(contentMetadataList.get(positionToStart));
            } else {
                super.onContentListClicked(contentMetadataList, positionToStart, contentListTitle);
            }
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener, com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentClicked(ContentMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        boolean z = contentMetadata instanceof TrackMetadata;
        if (!z || !UserSubscriptionUtil.isNightwingOnly()) {
            if (z) {
                beginPlayback(CollectionsKt__CollectionsJVMKt.listOf(contentMetadata), 0);
                return;
            } else {
                super.onContentClicked(contentMetadata);
                return;
            }
        }
        TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
        if (Intrinsics.areEqual(trackMetadata.getIsExplicit(), Boolean.TRUE) && AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            displayExplicitContentFilteredDialog(trackMetadata);
        } else if (this.isPlaylistFree && this.isPlaylistFreeOnDemand) {
            FreeTierPlaybackUtil.playCloudStation(getMFragment().getContext(), new PlayableEntityIdentifier(trackMetadata.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST);
        } else {
            UpsellManager.getInstance().raiseNonBlockingUpsell(getMFragment().getContext(), UpsellUtil.getUpsellInformation(trackMetadata.getAsin(), UpsellReason.PLAY, UpsellSourceEntity.PLAYLIST_DETAIL), MediaCollectionType.AMF_STATION_SEED);
        }
    }

    private final void displayExplicitContentFilteredDialog(TrackMetadata metadata) {
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(getMFragment().getContext(), ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED, null, null, metadata, ContentAccessUtil.ContentAccessOperation.STREAM);
        if (dialogForDisabledReason == null) {
            return;
        }
        dialogForDisabledReason.show(getMFragment().getChildFragmentManager(), (String) null);
    }
}
