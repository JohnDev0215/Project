package com.amazon.mp3.catalog.fragment.converter;

import android.net.Uri;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.TrackPlaceHolder;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryContentMetadataConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b&\u0010'J%\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002J#\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J \u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00192\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H\u0002J\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001f\u0010 R\u001b\u0010%\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryContentMetadataConverter;", "", "Lcom/amazon/mp3/library/item/AbstractItem;", "data", "", "position", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "convert", "(Lcom/amazon/mp3/library/item/AbstractItem;Ljava/lang/Integer;)Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/mp3/library/item/Album;", "album", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "convertAlbum", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "convertTrack", "(Lcom/amazon/mp3/library/item/MusicTrack;Ljava/lang/Integer;)Lcom/amazon/music/views/library/metadata/TrackMetadata;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "convertPlaylist", "Lcom/amazon/mp3/library/item/Artist;", "artist", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "convertArtist", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", ContextMappingConstants.CONTENT_ENCODING, "", "getContentEncodingStrings", "", "getCachedImageId", "(Lcom/amazon/mp3/library/item/MusicTrack;)Ljava/lang/Long;", "placeHolderTrackMetadata$delegate", "Lkotlin/Lazy;", "getPlaceHolderTrackMetadata", "()Lcom/amazon/music/views/library/metadata/TrackMetadata;", "placeHolderTrackMetadata", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryContentMetadataConverter {
    public static final LibraryContentMetadataConverter INSTANCE = new LibraryContentMetadataConverter();

    /* JADX INFO: renamed from: placeHolderTrackMetadata$delegate, reason: from kotlin metadata */
    private static final Lazy placeHolderTrackMetadata = LazyKt__LazyJVMKt.lazy(new Function0<TrackMetadata>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryContentMetadataConverter$placeHolderTrackMetadata$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TrackMetadata invoke() {
            Uri EMPTY = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            return new TrackMetadata(null, EMPTY, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1048579, 15, null);
        }
    });

    private LibraryContentMetadataConverter() {
    }

    public final TrackMetadata getPlaceHolderTrackMetadata() {
        return (TrackMetadata) placeHolderTrackMetadata.getValue();
    }

    public static /* synthetic */ ContentMetadata convert$default(AbstractItem abstractItem, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        return convert(abstractItem, num);
    }

    @JvmStatic
    public static final ContentMetadata convert(AbstractItem data, Integer position) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return INSTANCE.convertAlbum((Album) data);
        }
        if (data instanceof TrackPlaceHolder) {
            return INSTANCE.getPlaceHolderTrackMetadata();
        }
        if (data instanceof MusicTrack) {
            return INSTANCE.convertTrack((MusicTrack) data, position);
        }
        if (data instanceof Playlist ? true : data instanceof CatalogPlaylist) {
            return INSTANCE.convertPlaylist(data);
        }
        if (data instanceof Artist) {
            return INSTANCE.convertArtist((Artist) data);
        }
        return null;
    }

    private final AlbumMetadata convertAlbum(Album album) {
        if (album.getContentUri() == null) {
            Uri EMPTY = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            return new AlbumMetadata(null, EMPTY, null, "", null, null, null, null, null, null, null, SetsKt__SetsKt.emptySet(), false, null, null, null, null, null, null, null, 1046517, null);
        }
        Uri contentUri = album.getContentUri();
        String asin = album.getAsin();
        String title = album.getTitle();
        String id = album.getId();
        Long lValueOf = id == null ? null : Long.valueOf(Long.parseLong(id));
        String artistAsin = album.getArtistAsin();
        String artistName = album.getArtistName();
        boolean zIsInLibrary = album.isInLibrary();
        boolean zIsAllOwned = album.isAllOwned();
        boolean zIsExplicit = album.isExplicit();
        Set<ContentAccessType> supportedTiers = album.getSupportedTiers();
        List<String> contentEncodingStrings = getContentEncodingStrings(album.getContentEncodings());
        PlaymodeEligibility playmodeEligibility = album.getPlaymodeEligibility();
        Set<SubscriptionType> onDemandTiers = album.getOnDemandTiers();
        boolean z = album.getDownloadState() == 0;
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        Boolean boolValueOf = Boolean.valueOf(zIsInLibrary);
        Boolean boolValueOf2 = Boolean.valueOf(zIsAllOwned);
        Intrinsics.checkNotNullExpressionValue(supportedTiers, "supportedTiers");
        return new AlbumMetadata(null, contentUri, null, asin, title, null, lValueOf, artistAsin, artistName, boolValueOf, boolValueOf2, supportedTiers, zIsExplicit, null, null, contentEncodingStrings, playmodeEligibility, onDemandTiers, Boolean.valueOf(z), null, 548901, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.metadata.TrackMetadata convertTrack(com.amazon.mp3.library.item.MusicTrack r82, java.lang.Integer r83) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.converter.LibraryContentMetadataConverter.convertTrack(com.amazon.mp3.library.item.MusicTrack, java.lang.Integer):com.amazon.music.views.library.metadata.TrackMetadata");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.metadata.PlaylistMetadata convertPlaylist(com.amazon.mp3.library.item.AbstractItem r55) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.converter.LibraryContentMetadataConverter.convertPlaylist(com.amazon.mp3.library.item.AbstractItem):com.amazon.music.views.library.metadata.PlaylistMetadata");
    }

    private final ArtistMetadata convertArtist(Artist artist) {
        boolean z = artist.getFollowStatus() == 1;
        if (artist.getContentUri() == null) {
            Uri EMPTY = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            return new ArtistMetadata(null, EMPTY, null, null, "", null, null, null, null, null, null, false, null, null, false, null, null, false, false, null, 1048557, null);
        }
        Uri contentUri = artist.getContentUri();
        String asin = artist.getAsin();
        String name = artist.getName();
        String name2 = artist.getName();
        String id = artist.getId();
        long trackCount = artist.getTrackCount();
        PlaymodeEligibility playmodeEligibility = artist.getPlaymodeEligibility();
        boolean z2 = artist.getDownloadState() == 0;
        Set<SubscriptionType> onDemandTiers = artist.getOnDemandTiers();
        boolean zIsAllOwned = artist.isAllOwned();
        Set<ContentAccessType> supportedTiers = artist.getSupportedTiers();
        long dateFollowed = artist.getDateFollowed();
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        Long lValueOf = Long.valueOf(trackCount);
        Intrinsics.checkNotNullExpressionValue(onDemandTiers, "onDemandTiers");
        return new ArtistMetadata(null, contentUri, null, null, asin, name, name2, null, null, null, supportedTiers, z, id, lValueOf, zIsAllOwned, playmodeEligibility, onDemandTiers, z2, false, Long.valueOf(dateFollowed), 263053, null);
    }

    private final List<String> getContentEncodingStrings(List<? extends ContentEncoding> contentEncoding) {
        if (contentEncoding == null || contentEncoding.isEmpty()) {
            return null;
        }
        return ContentEncodingDecoder.parseFrom(contentEncoding);
    }

    private final Long getCachedImageId(MusicTrack track) {
        track.getAlbumArtId();
        if (((int) track.getAlbumArtId()) == 0) {
            return Long.valueOf(track.getAlbumId());
        }
        return Long.valueOf(track.getAlbumArtId());
    }
}
