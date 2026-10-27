package com.amazon.mp3.amplifyqueue.converters;

import com.amazon.digitalmusicxp.types.ImageManifestTuple;
import com.amazon.mp3.amplifyqueue.model.AccountSeed;
import com.amazon.mp3.amplifyqueue.model.AdContext;
import com.amazon.mp3.amplifyqueue.model.DeviceFamilyEnum;
import com.amazon.mp3.amplifyqueue.model.FixedSizeImage;
import com.amazon.mp3.amplifyqueue.model.ImageManifest;
import com.amazon.mp3.amplifyqueue.model.ImageMetadata;
import com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple;
import com.amazon.mp3.amplifyqueue.model.LibraryAlbumSeed;
import com.amazon.mp3.amplifyqueue.model.LibraryArtistSeed;
import com.amazon.mp3.amplifyqueue.model.OpaqueTrackSearchSeed;
import com.amazon.mp3.amplifyqueue.model.ParentalControls;
import com.amazon.mp3.amplifyqueue.model.PlayModeEnum;
import com.amazon.mp3.amplifyqueue.model.PlayableEntitySeed;
import com.amazon.mp3.amplifyqueue.model.QueueIdHistoricalPlaybackSeed;
import com.amazon.mp3.amplifyqueue.model.QueueIdTransferSeed;
import com.amazon.mp3.amplifyqueue.model.QueueSeed;
import com.amazon.mp3.amplifyqueue.model.QueueSeedTypeEnum;
import com.amazon.mp3.amplifyqueue.model.SearchSeed;
import com.amazon.mp3.amplifyqueue.model.SmartLibraryPlaylistSeed;
import com.amazon.mp3.amplifyqueue.model.StationSeed;
import com.amazon.mp3.amplifyqueue.model.TrackListSeed;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToAmplifyExtension.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000\u001a\f\u0010\u0002\u001a\u00020\u0004*\u00020\u0003H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0006*\u00020\u0005H\u0000\u001a\f\u0010\u0002\u001a\u00020\b*\u00020\u0007H\u0000\u001a\f\u0010\u0002\u001a\u00020\n*\u00020\tH\u0000\u001a\f\u0010\u0002\u001a\u00020\f*\u00020\u000bH\u0000\u001a\f\u0010\u0002\u001a\u00020\u000e*\u00020\rH\u0000\u001a\f\u0010\u0002\u001a\u00020\u0010*\u00020\u000fH\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/digitalmusicxp/types/AdContext;", "Lcom/amazon/mp3/amplifyqueue/model/AdContext;", "toAmplify", "Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "Lcom/amazon/mp3/amplifyqueue/model/FixedSizeImage;", "Lcom/amazon/digitalmusicxp/types/ImageManifest;", "Lcom/amazon/mp3/amplifyqueue/model/ImageManifest;", "Lcom/amazon/digitalmusicxp/types/ImageManifestTuple;", "Lcom/amazon/mp3/amplifyqueue/model/ImageManifestTuple;", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "Lcom/amazon/mp3/amplifyqueue/model/ImageMetadata;", "Lcom/amazon/digitalmusicxp/types/ImageSizeUrlTuple;", "Lcom/amazon/mp3/amplifyqueue/model/ImageSizeUrlTuple;", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "Lcom/amazon/mp3/amplifyqueue/model/ParentalControls;", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "Lcom/amazon/mp3/amplifyqueue/model/QueueSeed;", "amplifyqueue_release"}, k = 2, mv = {1, 6, 0})
public final class ToAmplifyExtensionKt {
    public static final AdContext toAmplify(com.amazon.digitalmusicxp.types.AdContext adContext) {
        Intrinsics.checkNotNullParameter(adContext, "<this>");
        AdContext adContextBuild = AdContext.builder().userAgent(adContext.getUserAgent()).deviceFamily((DeviceFamilyEnum) AmplifyModelConverter.INSTANCE.getDummyEnumValue(DeviceFamilyEnum.class, adContext.getDeviceFamily().name())).deviceFamilyEnumValue(adContext.getDeviceFamily().name()).advertiserId(adContext.getAdvertiserId()).build();
        Intrinsics.checkNotNullExpressionValue(adContextBuild, "builder()\n            .u…rId)\n            .build()");
        return adContextBuild;
    }

    public static final FixedSizeImage toAmplify(com.amazon.digitalmusicxp.types.FixedSizeImage fixedSizeImage) {
        Intrinsics.checkNotNullParameter(fixedSizeImage, "<this>");
        FixedSizeImage fixedSizeImageBuild = FixedSizeImage.builder().imageUrl(fixedSizeImage.getImageUrl()).fileFormat(fixedSizeImage.getFileFormat()).heightInPixels(fixedSizeImage.getHeightInPixels()).widthInPixels(fixedSizeImage.getWidthInPixels()).build();
        Intrinsics.checkNotNullExpressionValue(fixedSizeImageBuild, "builder()\n            .i…els)\n            .build()");
        return fixedSizeImageBuild;
    }

    public static final ImageManifest toAmplify(com.amazon.digitalmusicxp.types.ImageManifest imageManifest) {
        Intrinsics.checkNotNullParameter(imageManifest, "<this>");
        ImageManifest.UrlsStep urlsStepBuilder = ImageManifest.builder();
        List<ImageManifestTuple> urls = imageManifest.getUrls();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(urls, 10));
        Iterator<T> it = urls.iterator();
        while (it.hasNext()) {
            arrayList.add(toAmplify((ImageManifestTuple) it.next()));
        }
        ImageManifest imageManifestBuild = urlsStepBuilder.urls(arrayList).build();
        Intrinsics.checkNotNullExpressionValue(imageManifestBuild, "builder()\n            .u…fy))\n            .build()");
        return imageManifestBuild;
    }

    public static final com.amazon.mp3.amplifyqueue.model.ImageManifestTuple toAmplify(ImageManifestTuple imageManifestTuple) {
        Intrinsics.checkNotNullParameter(imageManifestTuple, "<this>");
        com.amazon.mp3.amplifyqueue.model.ImageManifestTuple imageManifestTupleBuild = com.amazon.mp3.amplifyqueue.model.ImageManifestTuple.builder().imageSize(imageManifestTuple.getImageSize()).url(imageManifestTuple.getUrl()).build();
        Intrinsics.checkNotNullExpressionValue(imageManifestTupleBuild, "builder()\n            .i…url)\n            .build()");
        return imageManifestTupleBuild;
    }

    public static final ImageMetadata toAmplify(com.amazon.digitalmusicxp.types.ImageMetadata imageMetadata) {
        Intrinsics.checkNotNullParameter(imageMetadata, "<this>");
        ImageMetadata.BuildStep buildStepClickUrl = ImageMetadata.builder().clickUrl(imageMetadata.getClickUrl());
        com.amazon.digitalmusicxp.types.FixedSizeImage fixedSizeImage = imageMetadata.getFixedSizeImage();
        ImageMetadata.BuildStep buildStepFixedSizeImage = buildStepClickUrl.fixedSizeImage(fixedSizeImage == null ? null : toAmplify(fixedSizeImage));
        com.amazon.digitalmusicxp.types.ImageManifest imageManifest = imageMetadata.getImageManifest();
        ImageMetadata imageMetadataBuild = buildStepFixedSizeImage.imageManifest(imageManifest != null ? toAmplify(imageManifest) : null).adChoiceClickUrl(imageMetadata.getAdChoiceClickUrl()).build();
        Intrinsics.checkNotNullExpressionValue(imageMetadataBuild, "builder()\n            .c…Url)\n            .build()");
        return imageMetadataBuild;
    }

    public static final ImageSizeUrlTuple toAmplify(com.amazon.digitalmusicxp.types.ImageSizeUrlTuple imageSizeUrlTuple) {
        Intrinsics.checkNotNullParameter(imageSizeUrlTuple, "<this>");
        ImageSizeUrlTuple imageSizeUrlTupleBuild = ImageSizeUrlTuple.builder().size(imageSizeUrlTuple.getSize()).url(imageSizeUrlTuple.getUrl()).build();
        Intrinsics.checkNotNullExpressionValue(imageSizeUrlTupleBuild, "builder()\n            .s…url)\n            .build()");
        return imageSizeUrlTupleBuild;
    }

    public static final ParentalControls toAmplify(com.amazon.digitalmusicxp.types.ParentalControls parentalControls) {
        Intrinsics.checkNotNullParameter(parentalControls, "<this>");
        ParentalControls parentalControlsBuild = ParentalControls.builder().hasExplicitLanguage(Boolean.valueOf(parentalControls.getHasExplicitLanguage())).build();
        Intrinsics.checkNotNullExpressionValue(parentalControlsBuild, "builder()\n            .h…age)\n            .build()");
        return parentalControlsBuild;
    }

    public static final QueueSeed toAmplify(com.amazon.digitalmusicxp.types.QueueSeed queueSeed) {
        Intrinsics.checkNotNullParameter(queueSeed, "<this>");
        QueueSeed.QueueSeedTypeStep queueSeedTypeStepBuilder = QueueSeed.builder();
        AmplifyModelConverter amplifyModelConverter = AmplifyModelConverter.INSTANCE;
        QueueSeed.BuildStep buildStepPlayMode = queueSeedTypeStepBuilder.queueSeedType((QueueSeedTypeEnum) AmplifyModelConverter.getDummyEnumValue$default(amplifyModelConverter, QueueSeedTypeEnum.class, null, 2, null)).queueSeedTypeEnumValue(queueSeed.getQueueSeedType().name()).playMode((PlayModeEnum) AmplifyModelConverter.getDummyEnumValue$default(amplifyModelConverter, PlayModeEnum.class, null, 2, null));
        com.amazon.digitalmusicxp.enums.PlayModeEnum playMode = queueSeed.getPlayMode();
        QueueSeed queueSeedBuild = buildStepPlayMode.playModeEnumValue(playMode != null ? playMode.name() : null).queueIdTransfer((QueueIdTransferSeed) amplifyModelConverter.convertToAmplify(queueSeed.getQueueIdTransfer(), QueueIdTransferSeed.class)).queueIdHistoricalPlayback((QueueIdHistoricalPlaybackSeed) amplifyModelConverter.convertToAmplify(queueSeed.getQueueIdHistoricalPlayback(), QueueIdHistoricalPlaybackSeed.class)).playableEntity((PlayableEntitySeed) amplifyModelConverter.convertToAmplify(queueSeed.getPlayableEntity(), PlayableEntitySeed.class)).trackList((TrackListSeed) amplifyModelConverter.convertToAmplify(queueSeed.getTrackList(), TrackListSeed.class)).libraryAlbum((LibraryAlbumSeed) amplifyModelConverter.convertToAmplify(queueSeed.getLibraryAlbum(), LibraryAlbumSeed.class)).libraryArtist((LibraryArtistSeed) amplifyModelConverter.convertToAmplify(queueSeed.getLibraryArtist(), LibraryArtistSeed.class)).opaqueTrackSearch((OpaqueTrackSearchSeed) amplifyModelConverter.convertToAmplify(queueSeed.getOpaqueTrackSearch(), OpaqueTrackSearchSeed.class)).station((StationSeed) amplifyModelConverter.convertToAmplify(queueSeed.getStation(), StationSeed.class)).search((SearchSeed) amplifyModelConverter.convertToAmplify(queueSeed.getSearch(), SearchSeed.class)).account((AccountSeed) amplifyModelConverter.convertToAmplify(queueSeed.getAccount(), AccountSeed.class)).smartLibraryPlaylist((SmartLibraryPlaylistSeed) amplifyModelConverter.convertToAmplify(queueSeed.getSmartLibraryPlaylist(), SmartLibraryPlaylistSeed.class)).build();
        Intrinsics.checkNotNullExpressionValue(queueSeedBuild, "builder()\n            .q…va))\n            .build()");
        return queueSeedBuild;
    }
}
