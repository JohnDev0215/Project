package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerticalTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0081\u0002\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u001fJ\u000e\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0003R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010'\u001a\u0004\b\u0011\u0010&R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010(R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010'\u001a\u0004\b\u0017\u0010&R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010'\u001a\u0004\b\u0018\u0010&R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010'\u001a\u0004\b)\u0010&R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\n\n\u0002\u0010.\u001a\u0004\b,\u0010-R\u0015\u0010\u001c\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u00101\u001a\u0004\b/\u00100R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010!R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010!\"\u0004\b4\u00105R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010!R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010!¨\u0006<"}, d2 = {"Lcom/amazon/music/views/library/models/VerticalTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "viewId", "", "title", "subTitle", ContextMappingConstants.TERTIARY_TEXT, "artworkFrameModel", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", ContextMappingConstants.CONTENT_ENCODING, "", "contentType", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isCircle", "", "is16x9Tile", "keepOriginalHeight", "viewerCount", "artistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "sourceLogoUrl", "isLiveContent", "isPodcastContent", "metadataList", "rating", "", "ratingCount", ContextMappingConstants.LABEL, "allAccessLabel", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/util/List;ILcom/amazon/music/views/library/metadata/ContentMetadata;ZLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Lcom/amazon/music/views/library/metadata/ArtistMetadata;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getAllAccessLabel", "()Ljava/lang/String;", "getArtistMetadata", "()Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "getContentEncoding", "()Ljava/util/List;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "()Z", "getKeepOriginalHeight", "getLabel", "getMetadataList", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRatingCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSourceLogoUrl", "getSubTitle", "setSubTitle", "(Ljava/lang/String;)V", "getTertiaryText", "getTitle", "getViewerCount", "updatePlaylistVisibility", "", "newVisibility", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class VerticalTileModel extends BaseViewContentModel {
    private final String allAccessLabel;
    private final ArtistMetadata artistMetadata;
    private final List<String> contentEncoding;
    private final Boolean is16x9Tile;
    private final boolean isCircle;
    private final Boolean isLiveContent;
    private final Boolean isPodcastContent;
    private final Boolean keepOriginalHeight;
    private final String label;
    private final List<ContentMetadata> metadataList;
    private final Double rating;
    private final Integer ratingCount;
    private final String sourceLogoUrl;
    private String subTitle;
    private final String tertiaryText;
    private final String title;
    private final String viewerCount;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VerticalTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List<String> list, int i, ContentMetadata metadata, boolean z, Boolean bool, Boolean bool2, String str5, ArtistMetadata artistMetadata, String str6, Boolean bool3, Boolean bool4, List<? extends ContentMetadata> list2, Double d, Integer num) {
        this(str, str2, str3, str4, artworkFrameModel, list, i, metadata, z, bool, bool2, str5, artistMetadata, str6, bool3, bool4, list2, d, num, null, null, 1572864, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final void setSubTitle(String str) {
        this.subTitle = str;
    }

    public final String getTertiaryText() {
        return this.tertiaryText;
    }

    public final List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    /* JADX INFO: renamed from: isCircle, reason: from getter */
    public final boolean getIsCircle() {
        return this.isCircle;
    }

    public /* synthetic */ VerticalTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List list, int i, ContentMetadata contentMetadata, boolean z, Boolean bool, Boolean bool2, String str5, ArtistMetadata artistMetadata, String str6, Boolean bool3, Boolean bool4, List list2, Double d, Integer num, String str7, String str8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, artworkFrameModel, (i2 & 32) != 0 ? null : list, i, contentMetadata, (i2 & 256) != 0 ? false : z, (i2 & 512) != 0 ? Boolean.FALSE : bool, (i2 & 1024) != 0 ? Boolean.FALSE : bool2, (i2 & 2048) != 0 ? null : str5, (i2 & 4096) != 0 ? null : artistMetadata, (i2 & 8192) != 0 ? null : str6, (i2 & 16384) != 0 ? Boolean.FALSE : bool3, (32768 & i2) != 0 ? Boolean.FALSE : bool4, (65536 & i2) != 0 ? null : list2, (131072 & i2) != 0 ? null : d, (262144 & i2) != 0 ? null : num, (524288 & i2) != 0 ? null : str7, (i2 & 1048576) != 0 ? null : str8);
    }

    /* JADX INFO: renamed from: is16x9Tile, reason: from getter */
    public final Boolean getIs16x9Tile() {
        return this.is16x9Tile;
    }

    public final Boolean getKeepOriginalHeight() {
        return this.keepOriginalHeight;
    }

    public final String getViewerCount() {
        return this.viewerCount;
    }

    public final ArtistMetadata getArtistMetadata() {
        return this.artistMetadata;
    }

    public final String getSourceLogoUrl() {
        return this.sourceLogoUrl;
    }

    /* JADX INFO: renamed from: isLiveContent, reason: from getter */
    public final Boolean getIsLiveContent() {
        return this.isLiveContent;
    }

    /* JADX INFO: renamed from: isPodcastContent, reason: from getter */
    public final Boolean getIsPodcastContent() {
        return this.isPodcastContent;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }

    public final Double getRating() {
        return this.rating;
    }

    public final Integer getRatingCount() {
        return this.ratingCount;
    }

    public final String getLabel() {
        return this.label;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public VerticalTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List<String> list, int i, ContentMetadata metadata, boolean z, Boolean bool, Boolean bool2, String str5, ArtistMetadata artistMetadata, String str6, Boolean bool3, Boolean bool4, List<? extends ContentMetadata> list2, Double d, Integer num, String str7, String str8) {
        super(str, metadata, i, artworkFrameModel, null, 16, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.title = str2;
        this.subTitle = str3;
        this.tertiaryText = str4;
        this.contentEncoding = list;
        this.isCircle = z;
        this.is16x9Tile = bool;
        this.keepOriginalHeight = bool2;
        this.viewerCount = str5;
        this.artistMetadata = artistMetadata;
        this.sourceLogoUrl = str6;
        this.isLiveContent = bool3;
        this.isPodcastContent = bool4;
        this.metadataList = list2;
        this.rating = d;
        this.ratingCount = num;
        this.label = str7;
        this.allAccessLabel = str8;
    }

    public final String getAllAccessLabel() {
        return this.allAccessLabel;
    }

    public final void updatePlaylistVisibility(String newVisibility) {
        Intrinsics.checkNotNullParameter(newVisibility, "newVisibility");
        if ((getMetadata() instanceof UserPlaylistMetadata) && ((UserPlaylistMetadata) getMetadata()).getIsOwned()) {
            this.subTitle = newVisibility;
            notifyChanged(4);
        }
    }
}
