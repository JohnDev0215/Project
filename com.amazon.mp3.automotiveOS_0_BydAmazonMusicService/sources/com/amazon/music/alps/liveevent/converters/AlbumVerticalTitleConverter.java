package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.platform.graphql.generated.fragment.AlbumEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumVerticalTitleConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0007R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/AlbumVerticalTitleConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Album;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "getBaseUrl", "baseUrl", "asin", "Landroid/net/Uri;", "getUri", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class AlbumVerticalTitleConverter implements SingleBaseModelConverter<LiveEvent.Album> {
    private final Context context;

    public AlbumVerticalTitleConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(LiveEvent.Album data) {
        AlbumEmbedded.Image image;
        Image image2;
        Intrinsics.checkNotNullParameter(data, "data");
        String baseUrl = getBaseUrl();
        String id = data.getAlbumEmbedded().getId();
        Intrinsics.checkNotNull(id);
        Uri uri = getUri(baseUrl, id);
        String id2 = data.getAlbumEmbedded().getId();
        Intrinsics.checkNotNull(id2);
        AlbumMetadata albumMetadata = new AlbumMetadata(null, uri, null, id2, null, null, null, null, null, null, null, SetsKt__SetsKt.emptySet(), false, null, null, null, null, null, null, null, 1046517, null);
        String title = data.getAlbumEmbedded().getTitle();
        List<AlbumEmbedded.Image> images = data.getAlbumEmbedded().getImages();
        String url = null;
        if (images != null && (image = (AlbumEmbedded.Image) CollectionsKt___CollectionsKt.getOrNull(images, 0)) != null && (image2 = image.getImage()) != null) {
            url = image2.getUrl();
        }
        return new VerticalTileModel(null, title, null, null, new ArtworkFrameModel(null, url, 0, null, null, null, null, null, null, null, null, null, 4089, null), null, 0, albumMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096941, null);
    }

    @VisibleForTesting
    public final String getBaseUrl() {
        return URLConfig.SHARE_BASE_URL.getValue();
    }

    @VisibleForTesting
    public final Uri getUri(String baseUrl, String asin) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Uri uri = Uri.parse(baseUrl + "albums/" + asin);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"${baseUrl}albums/$asin\")");
        return uri;
    }
}
