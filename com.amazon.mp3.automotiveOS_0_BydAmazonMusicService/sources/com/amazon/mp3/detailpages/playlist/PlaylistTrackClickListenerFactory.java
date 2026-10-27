package com.amazon.mp3.detailpages.playlist;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListenerFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistTrackClickListenerFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B¤\u0001\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012S\u0010&\u001aO\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$0\u001dj\u0002`%\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b'\u0010(J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistTrackClickListenerFactory;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerFactory;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener;", "getCollectionTrackClickListenerForTrack", "Landroidx/lifecycle/Observer;", "", "suggestionsAddedObserver", "Landroidx/lifecycle/Observer;", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "", "isCatalog", "Z", "playlistLuid", "Ljava/lang/String;", "Landroid/net/Uri;", "playlistUri", "Landroid/net/Uri;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "collectionInfoFetcher", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "entityAsin", ContextMappingConstants.ENTITY_ID, "", "entityPos", "", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerOnClickCallback;", "onClickCallback", "<init>", "(Landroidx/lifecycle/Observer;Landroidx/fragment/app/FragmentActivity;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lkotlin/jvm/functions/Function3;ZLjava/lang/String;Landroid/net/Uri;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistTrackClickListenerFactory extends CollectionTrackClickListenerFactory {
    private final boolean isCatalog;
    private final LifecycleOwner lifecycleOwner;
    private final String playlistLuid;
    private final Uri playlistUri;
    private final Observer<String> suggestionsAddedObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistTrackClickListenerFactory(Observer<String> suggestionsAddedObserver, FragmentActivity activity, LifecycleOwner lifecycleOwner, CollectionTrackClickListener.CollectionInformationFetcher collectionInfoFetcher, PlaybackPageType playbackPageType, Function3<? super String, ? super String, ? super Integer, Unit> onClickCallback, boolean z, String playlistLuid, Uri uri) {
        super(activity, collectionInfoFetcher, playbackPageType, onClickCallback, null, 16, null);
        Intrinsics.checkNotNullParameter(suggestionsAddedObserver, "suggestionsAddedObserver");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(collectionInfoFetcher, "collectionInfoFetcher");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(onClickCallback, "onClickCallback");
        Intrinsics.checkNotNullParameter(playlistLuid, "playlistLuid");
        this.suggestionsAddedObserver = suggestionsAddedObserver;
        this.lifecycleOwner = lifecycleOwner;
        this.isCatalog = z;
        this.playlistLuid = playlistLuid;
        this.playlistUri = uri;
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListenerFactory
    public CollectionTrackClickListener getCollectionTrackClickListenerForTrack(ContentMetadata contentMetadata) {
        Uri uri;
        Uri uriAppendTracksToUri;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
        String asin = trackMetadata.getAsin();
        Uri uri2 = contentMetadata.getUri();
        Uri uri3 = this.playlistUri;
        if (trackMetadata.getIsSuggestion()) {
            Context applicationContext = getActivity().getApplicationContext();
            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
            String asin2 = trackMetadata.getAsin();
            Intrinsics.checkNotNull(asin2);
            Uri trackUri = contentPlaybackUtils.getTrackUri(asin2);
            uriAppendTracksToUri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.UdoPlaylists.getContentUriFromLuid(applicationContext, "cirrus", this.playlistLuid));
            uri = trackUri;
        } else {
            uri = uri2;
            uriAppendTracksToUri = uri3;
        }
        return new PlaylistTrackClickListener(this.suggestionsAddedObserver, getActivity(), this.lifecycleOwner, getCollectionInfoFetcher(), getPlaybackPageType(), asin, uri, getOnClickCallback(), uriAppendTracksToUri, this.isCatalog, trackMetadata.getIsSuggestion());
    }
}
