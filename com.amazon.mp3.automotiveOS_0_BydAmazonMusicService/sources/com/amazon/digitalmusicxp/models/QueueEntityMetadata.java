package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.ContentEncodingEnum;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntitySubtypeEnum;
import com.amazon.digitalmusicxp.enums.RatingEnum;
import com.amazon.digitalmusicxp.types.CuePoints;
import com.amazon.digitalmusicxp.types.ImageMetadata;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.digitalmusicxp.types.PlaybackAssetSource;
import com.amazon.digitalmusicxp.types.TrackAlbum;
import com.amazon.digitalmusicxp.types.TrackArtist;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueEntityMetadata.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b6\b\u0086\b\u0018\u00002\u00020\u0001Bÿ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0016\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010&\u001a\u00020$¢\u0006\u0004\b^\u0010_J\u0096\u0002\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u00162\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$HÆ\u0001¢\u0006\u0004\b'\u0010(J\t\u0010)\u001a\u00020\u0002HÖ\u0001J\t\u0010+\u001a\u00020*HÖ\u0001J\u0013\u0010-\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010.\u001a\u0004\b1\u00100R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010.\u001a\u0004\b2\u00100R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010.\u001a\u0004\b3\u00100R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u00104\u001a\u0004\b5\u00106R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u00107\u001a\u0004\b8\u00109R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010:\u001a\u0004\b;\u0010<R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010.\u001a\u0004\b=\u00100R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010.\u001a\u0004\b>\u00100R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010?\u001a\u0004\b@\u0010AR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010B\u001a\u0004\bC\u0010DR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010E\u001a\u0004\bF\u0010GR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010.\u001a\u0004\bH\u00100R\u001f\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010I\u001a\u0004\bJ\u0010KR\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010L\u001a\u0004\bM\u0010NR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010O\u001a\u0004\bP\u0010QR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010R\u001a\u0004\bS\u0010TR\u0019\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006¢\u0006\f\n\u0004\b \u0010U\u001a\u0004\bV\u0010WR\u001f\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\"\u0010I\u001a\u0004\bX\u0010KR\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010.\u001a\u0004\bY\u00100R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010Z\u001a\u0004\b[\u0010\\R\u0017\u0010&\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b&\u0010Z\u001a\u0004\b]\u0010\\¨\u0006`"}, d2 = {"Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "", "", "customerId", "queueId", "id", "identifier", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "identifierType", "Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;", "queueEntitySubtype", "", "durationInMs", "title", "subTitle", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "imageMetadata", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "parentalControls", "Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "album", "concatenatedArtistName", "", "Lcom/amazon/digitalmusicxp/types/TrackArtist;", "artists", "", "hasLyrics", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "cuePoints", "Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "playbackAssetSource", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "rating", "Lcom/amazon/digitalmusicxp/enums/ContentEncodingEnum;", "availableContentEncodings", "playableAsin", "", "createdAt", "updatedAt", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/ImageMetadata;Lcom/amazon/digitalmusicxp/types/ParentalControls;Lcom/amazon/digitalmusicxp/types/TrackAlbum;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Lcom/amazon/digitalmusicxp/types/CuePoints;Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;Lcom/amazon/digitalmusicxp/enums/RatingEnum;Ljava/util/List;Ljava/lang/String;JJ)Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "toString", "", "hashCode", "other", "equals", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "getQueueId", "getId", "getIdentifier", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "getIdentifierType", "()Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;", "getQueueEntitySubtype", "()Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;", "Ljava/lang/Float;", "getDurationInMs", "()Ljava/lang/Float;", "getTitle", "getSubTitle", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "getImageMetadata", "()Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "getParentalControls", "()Lcom/amazon/digitalmusicxp/types/ParentalControls;", "Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "getAlbum", "()Lcom/amazon/digitalmusicxp/types/TrackAlbum;", "getConcatenatedArtistName", "Ljava/util/List;", "getArtists", "()Ljava/util/List;", "Ljava/lang/Boolean;", "getHasLyrics", "()Ljava/lang/Boolean;", "Lcom/amazon/digitalmusicxp/types/CuePoints;", "getCuePoints", "()Lcom/amazon/digitalmusicxp/types/CuePoints;", "Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "getPlaybackAssetSource", "()Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;", "Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "getRating", "()Lcom/amazon/digitalmusicxp/enums/RatingEnum;", "getAvailableContentEncodings", "getPlayableAsin", "J", "getCreatedAt", "()J", "getUpdatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;Lcom/amazon/digitalmusicxp/enums/QueueEntitySubtypeEnum;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/ImageMetadata;Lcom/amazon/digitalmusicxp/types/ParentalControls;Lcom/amazon/digitalmusicxp/types/TrackAlbum;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Lcom/amazon/digitalmusicxp/types/CuePoints;Lcom/amazon/digitalmusicxp/types/PlaybackAssetSource;Lcom/amazon/digitalmusicxp/enums/RatingEnum;Ljava/util/List;Ljava/lang/String;JJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueEntityMetadata {
    private final TrackAlbum album;
    private final List<TrackArtist> artists;
    private final List<ContentEncodingEnum> availableContentEncodings;
    private final String concatenatedArtistName;
    private final long createdAt;
    private final CuePoints cuePoints;
    private final String customerId;
    private final Float durationInMs;
    private final Boolean hasLyrics;
    private final String id;
    private final String identifier;
    private final QueueEntityIdTypeEnum identifierType;
    private final ImageMetadata imageMetadata;
    private final ParentalControls parentalControls;
    private final String playableAsin;
    private final PlaybackAssetSource playbackAssetSource;
    private final QueueEntitySubtypeEnum queueEntitySubtype;
    private final String queueId;
    private final RatingEnum rating;
    private final String subTitle;
    private final String title;
    private final long updatedAt;

    public final QueueEntityMetadata copy(String customerId, String queueId, String id, String identifier, QueueEntityIdTypeEnum identifierType, QueueEntitySubtypeEnum queueEntitySubtype, Float durationInMs, String title, String subTitle, ImageMetadata imageMetadata, ParentalControls parentalControls, TrackAlbum album, String concatenatedArtistName, List<TrackArtist> artists, Boolean hasLyrics, CuePoints cuePoints, PlaybackAssetSource playbackAssetSource, RatingEnum rating, List<? extends ContentEncodingEnum> availableContentEncodings, String playableAsin, long createdAt, long updatedAt) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(identifierType, "identifierType");
        return new QueueEntityMetadata(customerId, queueId, id, identifier, identifierType, queueEntitySubtype, durationInMs, title, subTitle, imageMetadata, parentalControls, album, concatenatedArtistName, artists, hasLyrics, cuePoints, playbackAssetSource, rating, availableContentEncodings, playableAsin, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueEntityMetadata)) {
            return false;
        }
        QueueEntityMetadata queueEntityMetadata = (QueueEntityMetadata) other;
        return Intrinsics.areEqual(this.customerId, queueEntityMetadata.customerId) && Intrinsics.areEqual(this.queueId, queueEntityMetadata.queueId) && Intrinsics.areEqual(this.id, queueEntityMetadata.id) && Intrinsics.areEqual(this.identifier, queueEntityMetadata.identifier) && this.identifierType == queueEntityMetadata.identifierType && this.queueEntitySubtype == queueEntityMetadata.queueEntitySubtype && Intrinsics.areEqual((Object) this.durationInMs, (Object) queueEntityMetadata.durationInMs) && Intrinsics.areEqual(this.title, queueEntityMetadata.title) && Intrinsics.areEqual(this.subTitle, queueEntityMetadata.subTitle) && Intrinsics.areEqual(this.imageMetadata, queueEntityMetadata.imageMetadata) && Intrinsics.areEqual(this.parentalControls, queueEntityMetadata.parentalControls) && Intrinsics.areEqual(this.album, queueEntityMetadata.album) && Intrinsics.areEqual(this.concatenatedArtistName, queueEntityMetadata.concatenatedArtistName) && Intrinsics.areEqual(this.artists, queueEntityMetadata.artists) && Intrinsics.areEqual(this.hasLyrics, queueEntityMetadata.hasLyrics) && Intrinsics.areEqual(this.cuePoints, queueEntityMetadata.cuePoints) && Intrinsics.areEqual(this.playbackAssetSource, queueEntityMetadata.playbackAssetSource) && this.rating == queueEntityMetadata.rating && Intrinsics.areEqual(this.availableContentEncodings, queueEntityMetadata.availableContentEncodings) && Intrinsics.areEqual(this.playableAsin, queueEntityMetadata.playableAsin) && this.createdAt == queueEntityMetadata.createdAt && this.updatedAt == queueEntityMetadata.updatedAt;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.customerId.hashCode() * 31) + this.queueId.hashCode()) * 31) + this.id.hashCode()) * 31) + this.identifier.hashCode()) * 31) + this.identifierType.hashCode()) * 31;
        QueueEntitySubtypeEnum queueEntitySubtypeEnum = this.queueEntitySubtype;
        int iHashCode2 = (iHashCode + (queueEntitySubtypeEnum == null ? 0 : queueEntitySubtypeEnum.hashCode())) * 31;
        Float f = this.durationInMs;
        int iHashCode3 = (iHashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        String str = this.title;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.subTitle;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        ImageMetadata imageMetadata = this.imageMetadata;
        int iHashCode6 = (iHashCode5 + (imageMetadata == null ? 0 : imageMetadata.hashCode())) * 31;
        ParentalControls parentalControls = this.parentalControls;
        int iHashCode7 = (iHashCode6 + (parentalControls == null ? 0 : parentalControls.hashCode())) * 31;
        TrackAlbum trackAlbum = this.album;
        int iHashCode8 = (iHashCode7 + (trackAlbum == null ? 0 : trackAlbum.hashCode())) * 31;
        String str3 = this.concatenatedArtistName;
        int iHashCode9 = (iHashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<TrackArtist> list = this.artists;
        int iHashCode10 = (iHashCode9 + (list == null ? 0 : list.hashCode())) * 31;
        Boolean bool = this.hasLyrics;
        int iHashCode11 = (iHashCode10 + (bool == null ? 0 : bool.hashCode())) * 31;
        CuePoints cuePoints = this.cuePoints;
        int iHashCode12 = (iHashCode11 + (cuePoints == null ? 0 : cuePoints.hashCode())) * 31;
        PlaybackAssetSource playbackAssetSource = this.playbackAssetSource;
        int iHashCode13 = (iHashCode12 + (playbackAssetSource == null ? 0 : playbackAssetSource.hashCode())) * 31;
        RatingEnum ratingEnum = this.rating;
        int iHashCode14 = (iHashCode13 + (ratingEnum == null ? 0 : ratingEnum.hashCode())) * 31;
        List<ContentEncodingEnum> list2 = this.availableContentEncodings;
        int iHashCode15 = (iHashCode14 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str4 = this.playableAsin;
        return ((((iHashCode15 + (str4 != null ? str4.hashCode() : 0)) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "QueueEntityMetadata(customerId=" + this.customerId + ", queueId=" + this.queueId + ", id=" + this.id + ", identifier=" + this.identifier + ", identifierType=" + this.identifierType + ", queueEntitySubtype=" + this.queueEntitySubtype + ", durationInMs=" + this.durationInMs + ", title=" + this.title + ", subTitle=" + this.subTitle + ", imageMetadata=" + this.imageMetadata + ", parentalControls=" + this.parentalControls + ", album=" + this.album + ", concatenatedArtistName=" + this.concatenatedArtistName + ", artists=" + this.artists + ", hasLyrics=" + this.hasLyrics + ", cuePoints=" + this.cuePoints + ", playbackAssetSource=" + this.playbackAssetSource + ", rating=" + this.rating + ", availableContentEncodings=" + this.availableContentEncodings + ", playableAsin=" + this.playableAsin + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public QueueEntityMetadata(String customerId, String queueId, String id, String identifier, QueueEntityIdTypeEnum identifierType, QueueEntitySubtypeEnum queueEntitySubtypeEnum, Float f, String str, String str2, ImageMetadata imageMetadata, ParentalControls parentalControls, TrackAlbum trackAlbum, String str3, List<TrackArtist> list, Boolean bool, CuePoints cuePoints, PlaybackAssetSource playbackAssetSource, RatingEnum ratingEnum, List<? extends ContentEncodingEnum> list2, String str4, long j, long j2) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(identifierType, "identifierType");
        this.customerId = customerId;
        this.queueId = queueId;
        this.id = id;
        this.identifier = identifier;
        this.identifierType = identifierType;
        this.queueEntitySubtype = queueEntitySubtypeEnum;
        this.durationInMs = f;
        this.title = str;
        this.subTitle = str2;
        this.imageMetadata = imageMetadata;
        this.parentalControls = parentalControls;
        this.album = trackAlbum;
        this.concatenatedArtistName = str3;
        this.artists = list;
        this.hasLyrics = bool;
        this.cuePoints = cuePoints;
        this.playbackAssetSource = playbackAssetSource;
        this.rating = ratingEnum;
        this.availableContentEncodings = list2;
        this.playableAsin = str4;
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ QueueEntityMetadata(String str, String str2, String str3, String str4, QueueEntityIdTypeEnum queueEntityIdTypeEnum, QueueEntitySubtypeEnum queueEntitySubtypeEnum, Float f, String str5, String str6, ImageMetadata imageMetadata, ParentalControls parentalControls, TrackAlbum trackAlbum, String str7, List list, Boolean bool, CuePoints cuePoints, PlaybackAssetSource playbackAssetSource, RatingEnum ratingEnum, List list2, String str8, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, queueEntityIdTypeEnum, (i & 32) != 0 ? null : queueEntitySubtypeEnum, (i & 64) != 0 ? null : f, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : imageMetadata, (i & 1024) != 0 ? null : parentalControls, (i & 2048) != 0 ? null : trackAlbum, (i & 4096) != 0 ? null : str7, (i & 8192) != 0 ? null : list, (i & 16384) != 0 ? null : bool, (32768 & i) != 0 ? null : cuePoints, (65536 & i) != 0 ? null : playbackAssetSource, (131072 & i) != 0 ? null : ratingEnum, (262144 & i) != 0 ? null : list2, (i & 524288) != 0 ? null : str8, j, j2);
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final String getId() {
        return this.id;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final QueueEntityIdTypeEnum getIdentifierType() {
        return this.identifierType;
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

    public final ParentalControls getParentalControls() {
        return this.parentalControls;
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

    public final PlaybackAssetSource getPlaybackAssetSource() {
        return this.playbackAssetSource;
    }

    public final List<ContentEncodingEnum> getAvailableContentEncodings() {
        return this.availableContentEncodings;
    }

    public final String getPlayableAsin() {
        return this.playableAsin;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }
}
