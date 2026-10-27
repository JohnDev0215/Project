package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.music.ContentAccessType;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.VideoStoryMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VideoStoryTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.AssetQuality;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.VideoStory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: VideoStoryTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\b\u0016\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b!\u0010\"J*\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0002J7\u0010\u0013\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0002H\u0016J*\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\t\u001a\u00020\bH\u0016J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\fJ\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e¨\u0006$"}, d2 = {"Lcom/amazon/mp3/brush/converters/VideoStoryTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/VideoStory;", "item", "", "Lcom/amazon/music/views/library/metadata/VideoStoryMetadata;", "metaDataList", "", "index", "Lcom/amazon/music/views/library/models/VideoStoryTileModel;", "convertStory", "", "blockRef", "imageUrl", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/views/library/metadata/ContentMetadata;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getPlayIconType", "(Ljava/lang/Integer;)Ljava/lang/Integer;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "asins", "asin", "Landroid/net/Uri;", "getUri", "", "entityList", "getVideoStoriesMetadata", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class VideoStoryTileConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert$default(this, data, CollectionsKt__CollectionsKt.emptyList(), 0, 4, null);
    }

    public static /* synthetic */ BaseViewModel convert$default(VideoStoryTileConverter videoStoryTileConverter, Entity entity, List list, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convert");
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return videoStoryTileConverter.convert(entity, list, i);
    }

    public BaseViewModel convert(Entity data, List<VideoStoryMetadata> asins, int index) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(asins, "asins");
        if (data instanceof VideoStory) {
            return convertStory((VideoStory) data, asins, index);
        }
        return null;
    }

    static /* synthetic */ VideoStoryTileModel convertStory$default(VideoStoryTileConverter videoStoryTileConverter, VideoStory videoStory, List list, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertStory");
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return videoStoryTileConverter.convertStory(videoStory, list, i);
    }

    private final VideoStoryTileModel convertStory(VideoStory item, List<VideoStoryMetadata> metaDataList, int index) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        String url = item.getImage().getUrl();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String blockRef2 = item.getBlockRef();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<AssetQuality> assetQualities = item.getAssetQualities();
        if (assetQualities == null) {
            assetQualities = CollectionsKt__CollectionsKt.emptyList();
        }
        List<com.amazon.music.models.AssetQuality> listConvertAssetQuality = brushConverterUtils.convertAssetQuality(assetQualities);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        Long duration = item.getDuration();
        int millis = (int) timeUnit.toMillis(duration == null ? 0L : duration.longValue());
        ParentalControls parentalControls = item.getParentalControls();
        VideoStoryMetadata videoStoryMetadata = new VideoStoryMetadata(blockRef, getUri(""), null, asin, title, blockRef2, url, setConvertTiers, parentalControls == null ? false : Intrinsics.areEqual(parentalControls.isHasExplicitLanguage(), Boolean.TRUE), metaDataList, listConvertAssetQuality, millis, null, null, 12292, null);
        return new VideoStoryTileModel(item.getBlockRef(), item.getPrimaryTitle(), item.getSecondaryTitle(), null, createArtworkFrameModel(item.getBlockRef(), url, 14, videoStoryMetadata), null, 7, videoStoryMetadata, item.getExpirationTime(), asin, item.getLabel(), index, 40, null);
    }

    public final Uri getUri(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        String string = HttpUrl.INSTANCE.get("https://music.amazon.com").newBuilder().addEncodedPathSegment("videos").addPathSegment(ParserUtil.STORY_ID_QUERY_PARAM_NAME).addPathSegment(asin).build().uri().toString();
        Intrinsics.checkNotNullExpressionValue(string, "BASEURL.toHttpUrl().newB…uild().toUri().toString()");
        Uri uri = Uri.parse(string);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(urlString)");
        return uri;
    }

    public final List<VideoStoryMetadata> getVideoStoriesMetadata(List<Entity> entityList) {
        Intrinsics.checkNotNullParameter(entityList, "entityList");
        ArrayList arrayList = new ArrayList();
        Iterator<Entity> it = entityList.iterator();
        while (it.hasNext()) {
            VideoStoryTileModel videoStoryTileModelConvertStory$default = convertStory$default(this, (VideoStory) it.next(), CollectionsKt__CollectionsKt.emptyList(), 0, 4, null);
            ContentMetadata metadata = videoStoryTileModelConvertStory$default == null ? null : videoStoryTileModelConvertStory$default.getMetadata();
            if (metadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.VideoStoryMetadata");
            }
            arrayList.add((VideoStoryMetadata) metadata);
        }
        return arrayList;
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, Integer contentType, ContentMetadata contentMetadata) {
        return new ArtworkFrameModel(blockRef, imageUrl, contentType, null, null, getPlayIconType(contentType), contentMetadata, null, null, null, null, null, 3992, null);
    }

    private final Integer getPlayIconType(Integer contentType) {
        if (contentType != null && contentType.intValue() == 3) {
            return 1;
        }
        return (contentType != null && contentType.intValue() == 2) ? 0 : null;
    }
}
