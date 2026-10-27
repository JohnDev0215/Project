package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.ContentEncodingEnum;
import com.amazon.digitalmusicxp.enums.MetricsSpecEnum;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntitySubtypeEnum;
import com.amazon.digitalmusicxp.enums.RatingEnum;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericQueueEntity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001BÏ\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010#\u001a\u00020\"\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u000103\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010=\u0012\u0006\u0010C\u001a\u00020B\u0012\n\b\u0002\u0010H\u001a\u0004\u0018\u00010G\u0012\u0010\b\u0002\u0010M\u001a\n\u0012\u0004\u0012\u00020L\u0018\u000103\u0012\n\b\u0002\u0010P\u001a\u0004\u0018\u00010O\u0012\n\b\u0002\u0010U\u001a\u0004\u0018\u00010T\u0012\u0010\b\u0002\u0010Z\u001a\n\u0012\u0004\u0012\u00020Y\u0018\u000103\u0012\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010^\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u000103\u0012\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010d\u001a\u0004\u0018\u00010c\u0012\u0010\b\u0002\u0010i\u001a\n\u0012\u0004\u0012\u00020h\u0018\u000103¢\u0006\u0004\bk\u0010lJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\fR\u0019\u0010 \u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b \u0010\n\u001a\u0004\b!\u0010\fR\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010(\u001a\u0004\u0018\u00010'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0019\u0010-\u001a\u0004\u0018\u00010,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u0019\u00101\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b1\u0010\n\u001a\u0004\b2\u0010\fR\u001f\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u0001038\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0019\u00109\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0019\u0010>\u001a\u0004\u0018\u00010=8\u0006¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\u0017\u0010C\u001a\u00020B8\u0006¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u0019\u0010H\u001a\u0004\u0018\u00010G8\u0006¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u001f\u0010M\u001a\n\u0012\u0004\u0012\u00020L\u0018\u0001038\u0006¢\u0006\f\n\u0004\bM\u00106\u001a\u0004\bN\u00108R\u0019\u0010P\u001a\u0004\u0018\u00010O8\u0006¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\u0019\u0010U\u001a\u0004\u0018\u00010T8\u0006¢\u0006\f\n\u0004\bU\u0010V\u001a\u0004\bW\u0010XR\u001f\u0010Z\u001a\n\u0012\u0004\u0012\u00020Y\u0018\u0001038\u0006¢\u0006\f\n\u0004\bZ\u00106\u001a\u0004\b[\u00108R\u0019\u0010\\\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\\\u0010\n\u001a\u0004\b]\u0010\fR\u001f\u0010^\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001038\u0006¢\u0006\f\n\u0004\b^\u00106\u001a\u0004\b_\u00108R\u0019\u0010`\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b`\u0010:\u001a\u0004\b`\u0010<R\u0019\u0010a\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\ba\u0010:\u001a\u0004\ba\u0010<R\u0019\u0010b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\bb\u0010:\u001a\u0004\bb\u0010<R\u0019\u0010d\u001a\u0004\u0018\u00010c8\u0006¢\u0006\f\n\u0004\bd\u0010e\u001a\u0004\bf\u0010gR\u001f\u0010i\u001a\n\u0012\u0004\u0012\u00020h\u0018\u0001038\u0006¢\u0006\f\n\u0004\bi\u00106\u001a\u0004\bj\u00108¨\u0006m"}, d2 = {"Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "", "", "toString", "", "hashCode", "other", "", "equals", "entityReferenceId", "Ljava/lang/String;", "getEntityReferenceId", "()Ljava/lang/String;", "identifier", "getIdentifier", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "identifierType", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "getIdentifierType", "()Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;", "queueEntitySubtype", "Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;", "getQueueEntitySubtype", "()Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;", "", "durationInMs", "Ljava/lang/Float;", "getDurationInMs", "()Ljava/lang/Float;", "title", "getTitle", "subTitle", "getSubTitle", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "imageMetadata", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "getImageMetadata", "()Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "rating", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "getRating", "()Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "album", "Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "getAlbum", "()Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "concatenatedArtistName", "getConcatenatedArtistName", "", "Lcom/amazon/digitalmusicxp/types/TrackArtist;", "artists", "Ljava/util/List;", "getArtists", "()Ljava/util/List;", "hasLyrics", "Ljava/lang/Boolean;", "getHasLyrics", "()Ljava/lang/Boolean;", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "cuePoints", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "getCuePoints", "()Lcom/amazon/digitalmusicxp/types/CuePoints;", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "capabilities", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "getCapabilities", "()Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "playbackAssetSource", "Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "getPlaybackAssetSource", "()Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "Lcom/amazon/digitalmusicxp/types/MetricsContextTuple;", "metricsContext", "getMetricsContext", "Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "clientMetricsInfo", "Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "getClientMetricsInfo", "()Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "Lcom/amazon/digitalmusicxp/enums/MetricsSpecEnum;", "metricsSpec", "Lcom/amazon/digitalmusicxp/enums/MetricsSpecEnum;", "getMetricsSpec", "()Lcom/amazon/digitalmusicxp/enums/MetricsSpecEnum;", "Lcom/amazon/digitalmusicxp/enums/ContentEncodingEnum;", "availableContentEncodings", "getAvailableContentEncodings", "playableAsin", "getPlayableAsin", "libraryObjectIds", "getLibraryObjectIds", "isPurchased", "isInstantImport", "isUploaded", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "parentalControls", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "getParentalControls", "()Lcom/amazon/digitalmusicxp/types/ParentalControls;", "Lcom/amazon/digitalmusicxp/types/FixedSizeImage;", "backgroundArtistImages", "getBackgroundArtistImages", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/ImageMetadata;Lcom/amazon/digitalmusicxp/enums/RatingEnum;Lcom/amazon/digitalmusicxp/types/TrackAlbum;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Lcom/amazon/digitalmusicxp/types/CuePoints;Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;Ljava/util/List;Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;Lcom/amazon/digitalmusicxp/enums/MetricsSpecEnum;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/digitalmusicxp/types/ParentalControls;Ljava/util/List;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GenericQueueEntity {
    private final TrackAlbum album;
    private final List<TrackArtist> artists;
    private final List<ContentEncodingEnum> availableContentEncodings;
    private final List<FixedSizeImage> backgroundArtistImages;
    private final QueueEntityCapability capabilities;
    private final ClientMetricsInfo clientMetricsInfo;
    private final String concatenatedArtistName;
    private final CuePoints cuePoints;
    private final Float durationInMs;
    private final String entityReferenceId;
    private final Boolean hasLyrics;
    private final String identifier;
    private final QueueEntityIdTypeEnum identifierType;
    private final ImageMetadata imageMetadata;
    private final Boolean isInstantImport;
    private final Boolean isPurchased;
    private final Boolean isUploaded;
    private final List<String> libraryObjectIds;
    private final List<MetricsContextTuple> metricsContext;
    private final MetricsSpecEnum metricsSpec;
    private final ParentalControls parentalControls;
    private final String playableAsin;
    private final PlaybackAssetSource playbackAssetSource;
    private final QueueEntitySubtypeEnum queueEntitySubtype;
    private final RatingEnum rating;
    private final String subTitle;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GenericQueueEntity)) {
            return false;
        }
        GenericQueueEntity genericQueueEntity = (GenericQueueEntity) other;
        return Intrinsics.areEqual(this.entityReferenceId, genericQueueEntity.entityReferenceId) && Intrinsics.areEqual(this.identifier, genericQueueEntity.identifier) && this.identifierType == genericQueueEntity.identifierType && this.queueEntitySubtype == genericQueueEntity.queueEntitySubtype && Intrinsics.areEqual((Object) this.durationInMs, (Object) genericQueueEntity.durationInMs) && Intrinsics.areEqual(this.title, genericQueueEntity.title) && Intrinsics.areEqual(this.subTitle, genericQueueEntity.subTitle) && Intrinsics.areEqual(this.imageMetadata, genericQueueEntity.imageMetadata) && this.rating == genericQueueEntity.rating && Intrinsics.areEqual(this.album, genericQueueEntity.album) && Intrinsics.areEqual(this.concatenatedArtistName, genericQueueEntity.concatenatedArtistName) && Intrinsics.areEqual(this.artists, genericQueueEntity.artists) && Intrinsics.areEqual(this.hasLyrics, genericQueueEntity.hasLyrics) && Intrinsics.areEqual(this.cuePoints, genericQueueEntity.cuePoints) && Intrinsics.areEqual(this.capabilities, genericQueueEntity.capabilities) && Intrinsics.areEqual(this.playbackAssetSource, genericQueueEntity.playbackAssetSource) && Intrinsics.areEqual(this.metricsContext, genericQueueEntity.metricsContext) && Intrinsics.areEqual(this.clientMetricsInfo, genericQueueEntity.clientMetricsInfo) && this.metricsSpec == genericQueueEntity.metricsSpec && Intrinsics.areEqual(this.availableContentEncodings, genericQueueEntity.availableContentEncodings) && Intrinsics.areEqual(this.playableAsin, genericQueueEntity.playableAsin) && Intrinsics.areEqual(this.libraryObjectIds, genericQueueEntity.libraryObjectIds) && Intrinsics.areEqual(this.isPurchased, genericQueueEntity.isPurchased) && Intrinsics.areEqual(this.isInstantImport, genericQueueEntity.isInstantImport) && Intrinsics.areEqual(this.isUploaded, genericQueueEntity.isUploaded) && Intrinsics.areEqual(this.parentalControls, genericQueueEntity.parentalControls) && Intrinsics.areEqual(this.backgroundArtistImages, genericQueueEntity.backgroundArtistImages);
    }

    public int hashCode() {
        int iHashCode = ((((this.entityReferenceId.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.identifierType.hashCode()) * 31;
        QueueEntitySubtypeEnum queueEntitySubtypeEnum = this.queueEntitySubtype;
        int iHashCode2 = (iHashCode + (queueEntitySubtypeEnum == null ? 0 : queueEntitySubtypeEnum.hashCode())) * 31;
        Float f = this.durationInMs;
        int iHashCode3 = (((iHashCode2 + (f == null ? 0 : f.hashCode())) * 31) + this.title.hashCode()) * 31;
        String str = this.subTitle;
        int iHashCode4 = (((iHashCode3 + (str == null ? 0 : str.hashCode())) * 31) + this.imageMetadata.hashCode()) * 31;
        RatingEnum ratingEnum = this.rating;
        int iHashCode5 = (iHashCode4 + (ratingEnum == null ? 0 : ratingEnum.hashCode())) * 31;
        TrackAlbum trackAlbum = this.album;
        int iHashCode6 = (iHashCode5 + (trackAlbum == null ? 0 : trackAlbum.hashCode())) * 31;
        String str2 = this.concatenatedArtistName;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<TrackArtist> list = this.artists;
        int iHashCode8 = (iHashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        Boolean bool = this.hasLyrics;
        int iHashCode9 = (iHashCode8 + (bool == null ? 0 : bool.hashCode())) * 31;
        CuePoints cuePoints = this.cuePoints;
        int iHashCode10 = (((iHashCode9 + (cuePoints == null ? 0 : cuePoints.hashCode())) * 31) + this.capabilities.hashCode()) * 31;
        PlaybackAssetSource playbackAssetSource = this.playbackAssetSource;
        int iHashCode11 = (iHashCode10 + (playbackAssetSource == null ? 0 : playbackAssetSource.hashCode())) * 31;
        List<MetricsContextTuple> list2 = this.metricsContext;
        int iHashCode12 = (iHashCode11 + (list2 == null ? 0 : list2.hashCode())) * 31;
        ClientMetricsInfo clientMetricsInfo = this.clientMetricsInfo;
        int iHashCode13 = (iHashCode12 + (clientMetricsInfo == null ? 0 : clientMetricsInfo.hashCode())) * 31;
        MetricsSpecEnum metricsSpecEnum = this.metricsSpec;
        int iHashCode14 = (iHashCode13 + (metricsSpecEnum == null ? 0 : metricsSpecEnum.hashCode())) * 31;
        List<ContentEncodingEnum> list3 = this.availableContentEncodings;
        int iHashCode15 = (iHashCode14 + (list3 == null ? 0 : list3.hashCode())) * 31;
        String str3 = this.playableAsin;
        int iHashCode16 = (iHashCode15 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<String> list4 = this.libraryObjectIds;
        int iHashCode17 = (iHashCode16 + (list4 == null ? 0 : list4.hashCode())) * 31;
        Boolean bool2 = this.isPurchased;
        int iHashCode18 = (iHashCode17 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.isInstantImport;
        int iHashCode19 = (iHashCode18 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.isUploaded;
        int iHashCode20 = (iHashCode19 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        ParentalControls parentalControls = this.parentalControls;
        int iHashCode21 = (iHashCode20 + (parentalControls == null ? 0 : parentalControls.hashCode())) * 31;
        List<FixedSizeImage> list5 = this.backgroundArtistImages;
        return iHashCode21 + (list5 != null ? list5.hashCode() : 0);
    }

    public String toString() {
        return "GenericQueueEntity(entityReferenceId=" + this.entityReferenceId + ", identifier=" + this.identifier + ", identifierType=" + this.identifierType + ", queueEntitySubtype=" + this.queueEntitySubtype + ", durationInMs=" + this.durationInMs + ", title=" + this.title + ", subTitle=" + this.subTitle + ", imageMetadata=" + this.imageMetadata + ", rating=" + this.rating + ", album=" + this.album + ", concatenatedArtistName=" + this.concatenatedArtistName + ", artists=" + this.artists + ", hasLyrics=" + this.hasLyrics + ", cuePoints=" + this.cuePoints + ", capabilities=" + this.capabilities + ", playbackAssetSource=" + this.playbackAssetSource + ", metricsContext=" + this.metricsContext + ", clientMetricsInfo=" + this.clientMetricsInfo + ", metricsSpec=" + this.metricsSpec + ", availableContentEncodings=" + this.availableContentEncodings + ", playableAsin=" + this.playableAsin + ", libraryObjectIds=" + this.libraryObjectIds + ", isPurchased=" + this.isPurchased + ", isInstantImport=" + this.isInstantImport + ", isUploaded=" + this.isUploaded + ", parentalControls=" + this.parentalControls + ", backgroundArtistImages=" + this.backgroundArtistImages + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GenericQueueEntity(String entityReferenceId, String identifier, QueueEntityIdTypeEnum identifierType, QueueEntitySubtypeEnum queueEntitySubtypeEnum, Float f, String title, String str, ImageMetadata imageMetadata, RatingEnum ratingEnum, TrackAlbum trackAlbum, String str2, List<TrackArtist> list, Boolean bool, CuePoints cuePoints, QueueEntityCapability capabilities, PlaybackAssetSource playbackAssetSource, List<MetricsContextTuple> list2, ClientMetricsInfo clientMetricsInfo, MetricsSpecEnum metricsSpecEnum, List<? extends ContentEncodingEnum> list3, String str3, List<String> list4, Boolean bool2, Boolean bool3, Boolean bool4, ParentalControls parentalControls, List<FixedSizeImage> list5) {
        Intrinsics.checkNotNullParameter(entityReferenceId, "entityReferenceId");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(identifierType, "identifierType");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(imageMetadata, "imageMetadata");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        this.entityReferenceId = entityReferenceId;
        this.identifier = identifier;
        this.identifierType = identifierType;
        this.queueEntitySubtype = queueEntitySubtypeEnum;
        this.durationInMs = f;
        this.title = title;
        this.subTitle = str;
        this.imageMetadata = imageMetadata;
        this.rating = ratingEnum;
        this.album = trackAlbum;
        this.concatenatedArtistName = str2;
        this.artists = list;
        this.hasLyrics = bool;
        this.cuePoints = cuePoints;
        this.capabilities = capabilities;
        this.playbackAssetSource = playbackAssetSource;
        this.metricsContext = list2;
        this.clientMetricsInfo = clientMetricsInfo;
        this.metricsSpec = metricsSpecEnum;
        this.availableContentEncodings = list3;
        this.playableAsin = str3;
        this.libraryObjectIds = list4;
        this.isPurchased = bool2;
        this.isInstantImport = bool3;
        this.isUploaded = bool4;
        this.parentalControls = parentalControls;
        this.backgroundArtistImages = list5;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final QueueEntityIdTypeEnum getIdentifierType() {
        return this.identifierType;
    }

    public final QueueEntitySubtypeEnum getQueueEntitySubtype() {
        return this.queueEntitySubtype;
    }

    public final Float getDurationInMs() {
        return this.durationInMs;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final ImageMetadata getImageMetadata() {
        return this.imageMetadata;
    }

    public final RatingEnum getRating() {
        return this.rating;
    }

    public final TrackAlbum getAlbum() {
        return this.album;
    }

    public final String getConcatenatedArtistName() {
        return this.concatenatedArtistName;
    }

    public final List<TrackArtist> getArtists() {
        return this.artists;
    }

    public final Boolean getHasLyrics() {
        return this.hasLyrics;
    }

    public final CuePoints getCuePoints() {
        return this.cuePoints;
    }

    public final QueueEntityCapability getCapabilities() {
        return this.capabilities;
    }

    public final PlaybackAssetSource getPlaybackAssetSource() {
        return this.playbackAssetSource;
    }

    public final List<MetricsContextTuple> getMetricsContext() {
        return this.metricsContext;
    }

    public final ClientMetricsInfo getClientMetricsInfo() {
        return this.clientMetricsInfo;
    }

    public final List<ContentEncodingEnum> getAvailableContentEncodings() {
        return this.availableContentEncodings;
    }

    public final String getPlayableAsin() {
        return this.playableAsin;
    }
}
