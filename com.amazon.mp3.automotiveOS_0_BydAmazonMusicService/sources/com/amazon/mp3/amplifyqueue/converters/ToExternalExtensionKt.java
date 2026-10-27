package com.amazon.mp3.amplifyqueue.converters;

import com.amazon.digitalmusicxp.enums.ActionStrategyTypeEnum;
import com.amazon.digitalmusicxp.enums.DeviceFamilyEnum;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.PlaybackAssetSourceTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.enums.UpsellTypeEnum;
import com.amazon.digitalmusicxp.types.AccountSeed;
import com.amazon.digitalmusicxp.types.AdContext;
import com.amazon.digitalmusicxp.types.CuePoints;
import com.amazon.digitalmusicxp.types.FixedSizeImage;
import com.amazon.digitalmusicxp.types.IdentityContext;
import com.amazon.digitalmusicxp.types.ImageManifest;
import com.amazon.digitalmusicxp.types.ImageMetadata;
import com.amazon.digitalmusicxp.types.ImageSizeUrlTuple;
import com.amazon.digitalmusicxp.types.LibraryAlbumSeed;
import com.amazon.digitalmusicxp.types.LibraryArtistSeed;
import com.amazon.digitalmusicxp.types.OpaqueTrackSearchSeed;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.digitalmusicxp.types.PlayableEntitySeed;
import com.amazon.digitalmusicxp.types.PlaybackAssetSource;
import com.amazon.digitalmusicxp.types.PlaybackInstruction;
import com.amazon.digitalmusicxp.types.QueueCapability;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import com.amazon.digitalmusicxp.types.QueueIdHistoricalPlaybackSeed;
import com.amazon.digitalmusicxp.types.QueueIdTransferSeed;
import com.amazon.digitalmusicxp.types.QueueMetadata;
import com.amazon.digitalmusicxp.types.SearchSeed;
import com.amazon.digitalmusicxp.types.SmartLibraryPlaylistSeed;
import com.amazon.digitalmusicxp.types.StationSeed;
import com.amazon.digitalmusicxp.types.TrackAlbum;
import com.amazon.digitalmusicxp.types.TrackArtist;
import com.amazon.digitalmusicxp.types.TrackListSeed;
import com.amazon.mp3.amplifyqueue.model.ActionStrategy;
import com.amazon.mp3.amplifyqueue.model.ArtistRole;
import com.amazon.mp3.amplifyqueue.model.CompositeActionStrategy;
import com.amazon.mp3.amplifyqueue.model.CompositeActionStrategyActionTypeEnum;
import com.amazon.mp3.amplifyqueue.model.ImageManifestTuple;
import com.amazon.mp3.amplifyqueue.model.QueueSeed;
import com.amazon.mp3.amplifyqueue.model.SingleActionStrategy;
import com.amazon.mp3.amplifyqueue.model.SingleActionStrategyActionTypeEnum;
import com.amazon.mp3.amplifyqueue.model.SkipLimitIOTAction;
import com.amazon.mp3.amplifyqueue.model.StartOffsetPlaybackInstruction;
import com.amazon.mp3.amplifyqueue.model.UpsellAction;
import com.amplifyframework.core.model.temporal.Temporal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToExternalExtension.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000´\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\n\u0010\u0000\u001a\u00020\u0003*\u00020\u0004\u001a\f\u0010\u0000\u001a\u00020\u0005*\u00020\u0006H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0007*\u00020\bH\u0000\u001a\f\u0010\u0000\u001a\u00020\t*\u00020\nH\u0000\u001a\f\u0010\u0000\u001a\u00020\u000b*\u00020\fH\u0000\u001a\f\u0010\u0000\u001a\u00020\r*\u00020\u000eH\u0000\u001a\f\u0010\u0000\u001a\u00020\u000f*\u00020\u0010H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0011*\u00020\u0012H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0013*\u00020\u0014H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0015*\u00020\u0016H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0017*\u00020\u0018H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0019*\u00020\u001aH\u0000\u001a\f\u0010\u0000\u001a\u00020\u001b*\u00020\u001cH\u0000\u001a\n\u0010\u0000\u001a\u00020\u001d*\u00020\u001e\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u001f*\u00020 H\u0000\u001a\f\u0010\u0000\u001a\u00020!*\u00020\"H\u0000\u001a\f\u0010\u0000\u001a\u00020#*\u00020$H\u0000\u001a\f\u0010\u0000\u001a\u00020%*\u00020&H\u0000\u001a\f\u0010\u0000\u001a\u00020'*\u00020(H\u0000\u001a\f\u0010\u0000\u001a\u00020)*\u00020*H\u0000\u001a\f\u0010\u0000\u001a\u00020+*\u00020,H\u0000¨\u0006-"}, d2 = {"toExternal", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "Lcom/amazon/mp3/amplifyqueue/model/ActionStrategy;", "Lcom/amazon/digitalmusicxp/types/AdContext;", "Lcom/amazon/mp3/amplifyqueue/model/AdContext;", "Lcom/amazon/digitalmusicxp/types/CompositeActionStrategy;", "Lcom/amazon/mp3/amplifyqueue/model/CompositeActionStrategy;", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "Lcom/amazon/mp3/amplifyqueue/model/CuePoints;", "Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "Lcom/amazon/mp3/amplifyqueue/model/FixedSizeImage;", "Lcom/amazon/digitalmusicxp/types/ImageManifest;", "Lcom/amazon/mp3/amplifyqueue/model/ImageManifest;", "Lcom/amazon/digitalmusicxp/types/ImageManifestTuple;", "Lcom/amazon/mp3/amplifyqueue/model/ImageManifestTuple;", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "Lcom/amazon/mp3/amplifyqueue/model/ImageMetadata;", "Lcom/amazon/digitalmusicxp/types/ImageSizeUrlTuple;", "Lcom/amazon/mp3/amplifyqueue/model/ImageSizeUrlTuple;", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "Lcom/amazon/mp3/amplifyqueue/model/ParentalControls;", "Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "Lcom/amazon/mp3/amplifyqueue/model/PlaybackAssetSource;", "Lcom/amazon/digitalmusicxp/types/PlaybackInstruction;", "Lcom/amazon/mp3/amplifyqueue/model/PlaybackInstruction;", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "Lcom/amazon/mp3/amplifyqueue/model/QueueCapability;", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "Lcom/amazon/mp3/amplifyqueue/model/QueueEntityCapability;", "Lcom/amazon/digitalmusicxp/types/QueueMetadata;", "Lcom/amazon/mp3/amplifyqueue/model/QueueMetadata;", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "Lcom/amazon/mp3/amplifyqueue/model/QueueSeed;", "Lcom/amazon/digitalmusicxp/types/SingleActionStrategy;", "Lcom/amazon/mp3/amplifyqueue/model/SingleActionStrategy;", "Lcom/amazon/digitalmusicxp/types/SkipLimitIOTAction;", "Lcom/amazon/mp3/amplifyqueue/model/SkipLimitIOTAction;", "Lcom/amazon/digitalmusicxp/types/StartOffsetPlaybackInstruction;", "Lcom/amazon/mp3/amplifyqueue/model/StartOffsetPlaybackInstruction;", "Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "Lcom/amazon/mp3/amplifyqueue/model/TrackAlbum;", "Lcom/amazon/digitalmusicxp/types/TrackArtist;", "Lcom/amazon/mp3/amplifyqueue/model/TrackArtist;", "Lcom/amazon/digitalmusicxp/types/UpsellAction;", "Lcom/amazon/mp3/amplifyqueue/model/UpsellAction;", "amplifyqueue_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ToExternalExtensionKt {
    public static final AdContext toExternal(com.amazon.mp3.amplifyqueue.model.AdContext adContext) {
        Intrinsics.checkNotNullParameter(adContext, "<this>");
        String userAgent = adContext.getUserAgent();
        Intrinsics.checkNotNullExpressionValue(userAgent, "this.userAgent");
        String deviceFamilyEnumValue = adContext.getDeviceFamilyEnumValue();
        Enum r3 = DeviceFamilyEnum.MOBILE;
        if (deviceFamilyEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(DeviceFamilyEnum.class, deviceFamilyEnumValue);
                if (enumValueOf != null) {
                    r3 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new AdContext(userAgent, (DeviceFamilyEnum) r3, adContext.getAdvertiserId());
    }

    public static final CuePoints toExternal(com.amazon.mp3.amplifyqueue.model.CuePoints cuePoints) {
        Intrinsics.checkNotNullParameter(cuePoints, "<this>");
        Integer startOffsetInMs = cuePoints.getStartOffsetInMs();
        Intrinsics.checkNotNullExpressionValue(startOffsetInMs, "this.startOffsetInMs");
        int iIntValue = startOffsetInMs.intValue();
        Integer endOffsetInMs = cuePoints.getEndOffsetInMs();
        Intrinsics.checkNotNullExpressionValue(endOffsetInMs, "this.endOffsetInMs");
        return new CuePoints(iIntValue, endOffsetInMs.intValue());
    }

    public static final FixedSizeImage toExternal(com.amazon.mp3.amplifyqueue.model.FixedSizeImage fixedSizeImage) {
        Intrinsics.checkNotNullParameter(fixedSizeImage, "<this>");
        String fileFormat = fixedSizeImage.getFileFormat();
        Integer heightInPixels = fixedSizeImage.getHeightInPixels();
        Integer widthInPixels = fixedSizeImage.getWidthInPixels();
        String imageUrl = fixedSizeImage.getImageUrl();
        Intrinsics.checkNotNullExpressionValue(imageUrl, "this.imageUrl");
        return new FixedSizeImage(fileFormat, heightInPixels, widthInPixels, imageUrl);
    }

    public static final ImageManifest toExternal(com.amazon.mp3.amplifyqueue.model.ImageManifest imageManifest) {
        Intrinsics.checkNotNullParameter(imageManifest, "<this>");
        List<ImageManifestTuple> urls = imageManifest.getUrls();
        Intrinsics.checkNotNullExpressionValue(urls, "this.urls");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(urls, 10));
        Iterator<T> it = urls.iterator();
        while (it.hasNext()) {
            arrayList.add(toExternal((ImageManifestTuple) it.next()));
        }
        return new ImageManifest(arrayList);
    }

    public static final com.amazon.digitalmusicxp.types.ImageManifestTuple toExternal(ImageManifestTuple imageManifestTuple) {
        Intrinsics.checkNotNullParameter(imageManifestTuple, "<this>");
        String imageSize = imageManifestTuple.getImageSize();
        Intrinsics.checkNotNullExpressionValue(imageSize, "this.imageSize");
        String url = imageManifestTuple.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "this.url");
        return new com.amazon.digitalmusicxp.types.ImageManifestTuple(imageSize, url);
    }

    public static final TrackArtist toExternal(com.amazon.mp3.amplifyqueue.model.TrackArtist trackArtist) {
        Intrinsics.checkNotNullParameter(trackArtist, "<this>");
        String asin = trackArtist.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "this.asin");
        String contributorAsin = trackArtist.getContributorAsin();
        String name = trackArtist.getName();
        Intrinsics.checkNotNullExpressionValue(name, "this.name");
        List<ArtistRole> roles = trackArtist.getRoles();
        Intrinsics.checkNotNullExpressionValue(roles, "this.roles");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = roles.iterator();
        while (it.hasNext()) {
            com.amazon.digitalmusicxp.types.ArtistRole artistRole = (com.amazon.digitalmusicxp.types.ArtistRole) AmplifyModelConverter.INSTANCE.convertFromAmplify((ArtistRole) it.next(), com.amazon.digitalmusicxp.types.ArtistRole.class);
            if (artistRole != null) {
                arrayList.add(artistRole);
            }
        }
        return new TrackArtist(asin, contributorAsin, name, arrayList);
    }

    public static final TrackAlbum toExternal(com.amazon.mp3.amplifyqueue.model.TrackAlbum trackAlbum) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(trackAlbum, "<this>");
        String artistName = trackAlbum.getArtistName();
        List<com.amazon.mp3.amplifyqueue.model.TrackArtist> artists = trackAlbum.getArtists();
        if (artists == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(artists, 10));
            Iterator<T> it = artists.iterator();
            while (it.hasNext()) {
                arrayList2.add(toExternal((com.amazon.mp3.amplifyqueue.model.TrackArtist) it.next()));
            }
            arrayList = arrayList2;
        }
        String asin = trackAlbum.getAsin();
        String name = trackAlbum.getName();
        Intrinsics.checkNotNullExpressionValue(name, "this.name");
        return new TrackAlbum(artistName, arrayList, asin, name);
    }

    public static final ImageMetadata toExternal(com.amazon.mp3.amplifyqueue.model.ImageMetadata imageMetadata) {
        Intrinsics.checkNotNullParameter(imageMetadata, "<this>");
        String clickUrl = imageMetadata.getClickUrl();
        com.amazon.mp3.amplifyqueue.model.FixedSizeImage fixedSizeImage = imageMetadata.getFixedSizeImage();
        FixedSizeImage external = fixedSizeImage == null ? null : toExternal(fixedSizeImage);
        com.amazon.mp3.amplifyqueue.model.ImageManifest imageManifest = imageMetadata.getImageManifest();
        return new ImageMetadata(clickUrl, external, imageManifest != null ? toExternal(imageManifest) : null, imageMetadata.getAdChoiceClickUrl());
    }

    public static final PlaybackInstruction toExternal(com.amazon.mp3.amplifyqueue.model.PlaybackInstruction playbackInstruction) {
        Intrinsics.checkNotNullParameter(playbackInstruction, "<this>");
        StartOffsetPlaybackInstruction startOffsetInstruction = playbackInstruction.getStartOffsetInstruction();
        return new PlaybackInstruction(startOffsetInstruction == null ? null : toExternal(startOffsetInstruction));
    }

    public static final com.amazon.digitalmusicxp.types.StartOffsetPlaybackInstruction toExternal(StartOffsetPlaybackInstruction startOffsetPlaybackInstruction) {
        Intrinsics.checkNotNullParameter(startOffsetPlaybackInstruction, "<this>");
        Integer startOffsetMs = startOffsetPlaybackInstruction.getStartOffsetMs();
        Intrinsics.checkNotNullExpressionValue(startOffsetMs, "this.startOffsetMs");
        return new com.amazon.digitalmusicxp.types.StartOffsetPlaybackInstruction(startOffsetMs.intValue());
    }

    public static final ImageSizeUrlTuple toExternal(com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple imageSizeUrlTuple) {
        Intrinsics.checkNotNullParameter(imageSizeUrlTuple, "<this>");
        String size = imageSizeUrlTuple.getSize();
        Intrinsics.checkNotNullExpressionValue(size, "this.size");
        String url = imageSizeUrlTuple.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "this.url");
        return new ImageSizeUrlTuple(size, url);
    }

    public static final QueueEntityCapability toExternal(com.amazon.mp3.amplifyqueue.model.QueueEntityCapability queueEntityCapability) {
        Intrinsics.checkNotNullParameter(queueEntityCapability, "<this>");
        ActionStrategy next = queueEntityCapability.getNext();
        com.amazon.digitalmusicxp.types.ActionStrategy external = next == null ? null : toExternal(next);
        ActionStrategy previous = queueEntityCapability.getPrevious();
        com.amazon.digitalmusicxp.types.ActionStrategy external2 = previous == null ? null : toExternal(previous);
        ActionStrategy scrubForward = queueEntityCapability.getScrubForward();
        com.amazon.digitalmusicxp.types.ActionStrategy external3 = scrubForward == null ? null : toExternal(scrubForward);
        ActionStrategy scrubBackward = queueEntityCapability.getScrubBackward();
        com.amazon.digitalmusicxp.types.ActionStrategy external4 = scrubBackward == null ? null : toExternal(scrubBackward);
        ActionStrategy rate = queueEntityCapability.getRate();
        com.amazon.digitalmusicxp.types.ActionStrategy external5 = rate == null ? null : toExternal(rate);
        ActionStrategy displayable = queueEntityCapability.getDisplayable();
        com.amazon.digitalmusicxp.types.ActionStrategy external6 = displayable == null ? null : toExternal(displayable);
        ActionStrategyTypeEnum actionStrategyTypeEnum = ActionStrategyTypeEnum.SINGLE;
        SingleActionStrategy loopOne = queueEntityCapability.getLoopOne();
        return new QueueEntityCapability(external, external2, external3, external4, external5, external6, new com.amazon.digitalmusicxp.types.ActionStrategy(actionStrategyTypeEnum, loopOne == null ? null : toExternal(loopOne), null));
    }

    public static final QueueCapability toExternal(com.amazon.mp3.amplifyqueue.model.QueueCapability queueCapability) {
        Intrinsics.checkNotNullParameter(queueCapability, "<this>");
        ActionStrategy hideUpcomingEntities = queueCapability.getHideUpcomingEntities();
        Intrinsics.checkNotNullExpressionValue(hideUpcomingEntities, "this.hideUpcomingEntities");
        com.amazon.digitalmusicxp.types.ActionStrategy external = toExternal(hideUpcomingEntities);
        ActionStrategy blockQueueMutations = queueCapability.getBlockQueueMutations();
        Intrinsics.checkNotNullExpressionValue(blockQueueMutations, "this.blockQueueMutations");
        com.amazon.digitalmusicxp.types.ActionStrategy external2 = toExternal(blockQueueMutations);
        ActionStrategy shuffle = queueCapability.getShuffle();
        Intrinsics.checkNotNullExpressionValue(shuffle, "this.shuffle");
        com.amazon.digitalmusicxp.types.ActionStrategy external3 = toExternal(shuffle);
        ActionStrategy loopAll = queueCapability.getLoopAll();
        Intrinsics.checkNotNullExpressionValue(loopAll, "this.loopAll");
        com.amazon.digitalmusicxp.types.ActionStrategy external4 = toExternal(loopAll);
        ActionStrategy djMode = queueCapability.getDjMode();
        return new QueueCapability(external, external2, external3, external4, djMode == null ? null : toExternal(djMode));
    }

    public static final com.amazon.digitalmusicxp.types.ActionStrategy toExternal(ActionStrategy actionStrategy) {
        Intrinsics.checkNotNullParameter(actionStrategy, "<this>");
        com.amazon.mp3.amplifyqueue.model.ActionStrategyTypeEnum strategyType = actionStrategy.getStrategyType();
        Intrinsics.checkNotNullExpressionValue(strategyType, "this.strategyType");
        ActionStrategyTypeEnum external = EnumsTransformerKt.toExternal(strategyType);
        SingleActionStrategy singleActionStrategy = actionStrategy.getSingleActionStrategy();
        com.amazon.digitalmusicxp.types.SingleActionStrategy external2 = singleActionStrategy == null ? null : toExternal(singleActionStrategy);
        CompositeActionStrategy compositeActionStrategy = actionStrategy.getCompositeActionStrategy();
        return new com.amazon.digitalmusicxp.types.ActionStrategy(external, external2, compositeActionStrategy != null ? toExternal(compositeActionStrategy) : null);
    }

    public static final com.amazon.digitalmusicxp.types.SingleActionStrategy toExternal(SingleActionStrategy singleActionStrategy) {
        Intrinsics.checkNotNullParameter(singleActionStrategy, "<this>");
        SingleActionStrategyActionTypeEnum actionType = singleActionStrategy.getActionType();
        Intrinsics.checkNotNullExpressionValue(actionType, "this.actionType");
        com.amazon.digitalmusicxp.enums.SingleActionStrategyActionTypeEnum external = EnumsTransformerKt.toExternal(actionType);
        UpsellAction upsellAction = singleActionStrategy.getUpsellAction();
        return new com.amazon.digitalmusicxp.types.SingleActionStrategy(external, upsellAction == null ? null : toExternal(upsellAction), singleActionStrategy.getBooleanAction());
    }

    public static final com.amazon.digitalmusicxp.types.CompositeActionStrategy toExternal(CompositeActionStrategy compositeActionStrategy) {
        Intrinsics.checkNotNullParameter(compositeActionStrategy, "<this>");
        CompositeActionStrategyActionTypeEnum actionType = compositeActionStrategy.getActionType();
        Intrinsics.checkNotNullExpressionValue(actionType, "this.actionType");
        com.amazon.digitalmusicxp.enums.CompositeActionStrategyActionTypeEnum external = EnumsTransformerKt.toExternal(actionType);
        SkipLimitIOTAction skipLimitIotAction = compositeActionStrategy.getSkipLimitIotAction();
        return new com.amazon.digitalmusicxp.types.CompositeActionStrategy(external, skipLimitIotAction == null ? null : toExternal(skipLimitIotAction));
    }

    public static final com.amazon.digitalmusicxp.types.SkipLimitIOTAction toExternal(SkipLimitIOTAction skipLimitIOTAction) {
        Intrinsics.checkNotNullParameter(skipLimitIOTAction, "<this>");
        SingleActionStrategy noSkipsRemainingAction = skipLimitIOTAction.getNoSkipsRemainingAction();
        Intrinsics.checkNotNullExpressionValue(noSkipsRemainingAction, "this.noSkipsRemainingAction");
        com.amazon.digitalmusicxp.types.SingleActionStrategy external = toExternal(noSkipsRemainingAction);
        SingleActionStrategy skipsRemainingActionStrategy = skipLimitIOTAction.getSkipsRemainingActionStrategy();
        Intrinsics.checkNotNullExpressionValue(skipsRemainingActionStrategy, "this.skipsRemainingActionStrategy");
        return new com.amazon.digitalmusicxp.types.SkipLimitIOTAction(external, toExternal(skipsRemainingActionStrategy));
    }

    public static final com.amazon.digitalmusicxp.types.UpsellAction toExternal(UpsellAction upsellAction) {
        Intrinsics.checkNotNullParameter(upsellAction, "<this>");
        String upsellTypeEnumValue = upsellAction.getUpsellTypeEnumValue();
        Enum r1 = UpsellTypeEnum.UNLIMITED_SKIPS;
        if (upsellTypeEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(UpsellTypeEnum.class, upsellTypeEnumValue);
                if (enumValueOf != null) {
                    r1 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new com.amazon.digitalmusicxp.types.UpsellAction((UpsellTypeEnum) r1);
    }

    public static final PlaybackAssetSource toExternal(com.amazon.mp3.amplifyqueue.model.PlaybackAssetSource playbackAssetSource) {
        Intrinsics.checkNotNullParameter(playbackAssetSource, "<this>");
        String typeEnumValue = playbackAssetSource.getTypeEnumValue();
        Enum r2 = PlaybackAssetSourceTypeEnum.URL;
        if (typeEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(PlaybackAssetSourceTypeEnum.class, typeEnumValue);
                if (enumValueOf != null) {
                    r2 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String value = playbackAssetSource.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "this.value");
        return new PlaybackAssetSource((PlaybackAssetSourceTypeEnum) r2, value);
    }

    public static final ParentalControls toExternal(com.amazon.mp3.amplifyqueue.model.ParentalControls parentalControls) {
        Intrinsics.checkNotNullParameter(parentalControls, "<this>");
        Boolean hasExplicitLanguage = parentalControls.getHasExplicitLanguage();
        Intrinsics.checkNotNullExpressionValue(hasExplicitLanguage, "this.hasExplicitLanguage");
        return new ParentalControls(hasExplicitLanguage.booleanValue());
    }

    public static final QueueMetadata toExternal(com.amazon.mp3.amplifyqueue.model.QueueMetadata queueMetadata) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(queueMetadata, "<this>");
        String customerId = queueMetadata.getCustomerId();
        Intrinsics.checkNotNullExpressionValue(customerId, "this.customerId");
        String queueId = queueMetadata.getQueueId();
        Intrinsics.checkNotNullExpressionValue(queueId, "this.queueId");
        Temporal.DateTime createdAt = queueMetadata.getCreatedAt();
        Intrinsics.checkNotNullExpressionValue(createdAt, "this.createdAt");
        long milliSeconds = ModelsTransformerKt.toMilliSeconds(createdAt);
        String serviceTierEnumValue = queueMetadata.getServiceTierEnumValue();
        Enum r6 = ServiceTierEnum.LIBRARY;
        if (serviceTierEnumValue != null) {
            try {
                Enum enumValueOf = Enum.valueOf(ServiceTierEnum.class, serviceTierEnumValue);
                if (enumValueOf != null) {
                    r6 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ServiceTierEnum serviceTierEnum = (ServiceTierEnum) r6;
        String title = queueMetadata.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "this.title");
        List<com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple> images = queueMetadata.getImages();
        ArrayList arrayList2 = null;
        if (images == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(images, 10));
            Iterator<T> it = images.iterator();
            while (it.hasNext()) {
                arrayList.add(toExternal((com.amazon.mp3.amplifyqueue.model.ImageSizeUrlTuple) it.next()));
            }
        }
        List<QueueSeed> queueSeeds = queueMetadata.getQueueSeeds();
        if (queueSeeds != null) {
            arrayList2 = new ArrayList();
            Iterator<T> it2 = queueSeeds.iterator();
            while (it2.hasNext()) {
                com.amazon.digitalmusicxp.types.QueueSeed external = toExternal((QueueSeed) it2.next());
                if (external != null) {
                    arrayList2.add(external);
                }
            }
        }
        ArrayList arrayList3 = arrayList2;
        com.amazon.mp3.amplifyqueue.model.QueueCapability capabilities = queueMetadata.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "this.capabilities");
        return new QueueMetadata(customerId, queueId, milliSeconds, serviceTierEnum, title, arrayList, arrayList3, toExternal(capabilities), queueMetadata.getThingShadowName(), (IdentityContext) AmplifyModelConverter.INSTANCE.convertFromAmplify(queueMetadata.getIdentityContext(), IdentityContext.class));
    }

    public static final com.amazon.digitalmusicxp.types.QueueSeed toExternal(QueueSeed queueSeed) {
        Enum enumValueOf;
        Intrinsics.checkNotNullParameter(queueSeed, "<this>");
        String queueSeedTypeEnumValue = queueSeed.getQueueSeedTypeEnumValue();
        Enum enumValueOf2 = null;
        if (queueSeedTypeEnumValue == null) {
            enumValueOf = null;
        } else {
            try {
                enumValueOf = Enum.valueOf(QueueSeedTypeEnum.class, queueSeedTypeEnumValue);
            } catch (Exception e) {
                e.printStackTrace();
                enumValueOf = null;
            }
        }
        QueueSeedTypeEnum queueSeedTypeEnum = (QueueSeedTypeEnum) enumValueOf;
        if (queueSeedTypeEnum == null) {
            return null;
        }
        String playModeEnumValue = queueSeed.getPlayModeEnumValue();
        if (playModeEnumValue != null) {
            try {
                enumValueOf2 = Enum.valueOf(PlayModeEnum.class, playModeEnumValue);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        AmplifyModelConverter amplifyModelConverter = AmplifyModelConverter.INSTANCE;
        return new com.amazon.digitalmusicxp.types.QueueSeed((PlayModeEnum) enumValueOf2, queueSeedTypeEnum, (QueueIdTransferSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getQueueIdTransfer(), QueueIdTransferSeed.class), (QueueIdHistoricalPlaybackSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getQueueIdHistoricalPlayback(), QueueIdHistoricalPlaybackSeed.class), (PlayableEntitySeed) amplifyModelConverter.convertFromAmplify(queueSeed.getPlayableEntity(), PlayableEntitySeed.class), (TrackListSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getTrackList(), TrackListSeed.class), (LibraryAlbumSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getLibraryAlbum(), LibraryAlbumSeed.class), (LibraryArtistSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getLibraryArtist(), LibraryArtistSeed.class), (OpaqueTrackSearchSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getOpaqueTrackSearch(), OpaqueTrackSearchSeed.class), (StationSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getStation(), StationSeed.class), (SearchSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getSearch(), SearchSeed.class), (AccountSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getAccount(), AccountSeed.class), (SmartLibraryPlaylistSeed) amplifyModelConverter.convertFromAmplify(queueSeed.getSmartLibraryPlaylist(), SmartLibraryPlaylistSeed.class));
    }
}
