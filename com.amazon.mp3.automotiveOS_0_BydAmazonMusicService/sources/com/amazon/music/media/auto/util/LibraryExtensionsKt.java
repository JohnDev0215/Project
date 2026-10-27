package com.amazon.music.media.auto.util;

import android.net.Uri;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryExtensions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"identifier", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getIdentifier", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)Ljava/lang/String;", "imageUrl", "Landroid/net/Uri;", "getImageUrl", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)Landroid/net/Uri;", "DMMAutoMediaBrowser_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class LibraryExtensionsKt {
    public static final String getIdentifier(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "<this>");
        if (contentMetadata instanceof TrackMetadata) {
            return ((TrackMetadata) contentMetadata).getAsin();
        }
        if (contentMetadata instanceof AlbumMetadata) {
            return ((AlbumMetadata) contentMetadata).getAsin();
        }
        if (contentMetadata instanceof ArtistMetadata) {
            return ((ArtistMetadata) contentMetadata).getAsin();
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            return ((PlaylistMetadata) contentMetadata).getAsin();
        }
        if (contentMetadata instanceof UserPlaylistMetadata) {
            return ((UserPlaylistMetadata) contentMetadata).getPlaylistId();
        }
        if (contentMetadata instanceof StationMetadata) {
            return ((StationMetadata) contentMetadata).getKey();
        }
        if (contentMetadata instanceof GenreMetadata) {
            return ((GenreMetadata) contentMetadata).getStationKey();
        }
        return null;
    }

    public static final Uri getImageUrl(ContentMetadata contentMetadata) {
        String imageUrl;
        Intrinsics.checkNotNullParameter(contentMetadata, "<this>");
        if (contentMetadata instanceof TrackMetadata) {
            imageUrl = ((TrackMetadata) contentMetadata).getImageUrl();
        } else if (contentMetadata instanceof AlbumMetadata) {
            imageUrl = ((AlbumMetadata) contentMetadata).getImageUrl();
        } else if (contentMetadata instanceof ArtistMetadata) {
            imageUrl = ((ArtistMetadata) contentMetadata).getImageUrl();
        } else if (contentMetadata instanceof PlaylistMetadata) {
            imageUrl = ((PlaylistMetadata) contentMetadata).getImageUrl();
        } else if (contentMetadata instanceof UserPlaylistMetadata) {
            imageUrl = ((UserPlaylistMetadata) contentMetadata).getImageUrl();
        } else if (contentMetadata instanceof StationMetadata) {
            imageUrl = ((StationMetadata) contentMetadata).getImageUrl();
        } else {
            imageUrl = contentMetadata instanceof GenreMetadata ? ((GenreMetadata) contentMetadata).getImageUrl() : null;
        }
        if (imageUrl == null) {
            return null;
        }
        return Uri.parse(imageUrl);
    }
}
