package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.graphql.generated.fragment.PlaylistEmbedded;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistVerticalTileConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0007R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/PlaylistVerticalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Playlist;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "getBaseUrl", "baseUrl", "asin", "Landroid/net/Uri;", "getUri", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class PlaylistVerticalTileConverter implements SingleBaseModelConverter<LiveEvent.Playlist> {
    private final Context context;

    public PlaylistVerticalTileConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(LiveEvent.Playlist data) {
        PlaylistEmbedded.Image image;
        Image image2;
        Intrinsics.checkNotNullParameter(data, "data");
        String baseUrl = getBaseUrl();
        String id = data.getPlaylistEmbedded().getId();
        Intrinsics.checkNotNull(id);
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(null, getUri(baseUrl, id), null, id, null, null, null, null, null, null, null, SetsKt__SetsKt.emptySet(), CollectionsKt__CollectionsJVMKt.listOf("AUDIO"), false, null, null, null, null, false, null, false, null, null, false, null, 33548277, null);
        String title = data.getPlaylistEmbedded().getTitle();
        List<PlaylistEmbedded.Image> images = data.getPlaylistEmbedded().getImages();
        String url = null;
        if (images != null && (image = (PlaylistEmbedded.Image) CollectionsKt___CollectionsKt.getOrNull(images, 0)) != null && (image2 = image.getImage()) != null) {
            url = image2.getUrl();
        }
        return new VerticalTileModel(null, title, null, null, new ArtworkFrameModel(null, url, 1, null, null, null, null, null, null, null, null, null, 4089, null), null, 1, playlistMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096941, null);
    }

    @VisibleForTesting
    public final String getBaseUrl() {
        return URLConfig.SHARE_BASE_URL.getValue();
    }

    @VisibleForTesting
    public final Uri getUri(String baseUrl, String asin) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Uri uri = Uri.parse(baseUrl + "playlists/" + asin);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"${baseUrl}playlists/$asin\")");
        return uri;
    }
}
