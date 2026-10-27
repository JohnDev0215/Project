package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HorizontalTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002Bß\u0002\b\u0007\u0012\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010]\u001a\u00020\\\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\b\b\u0002\u0010 \u001a\u00020\u001f\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010^\u001a\u00020\u001f\u0012\b\b\u0002\u0010_\u001a\u00020\u001f\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010(\u0012\b\b\u0002\u0010-\u001a\u00020\u001f\u0012\b\b\u0002\u00101\u001a\u00020\u001f\u0012\b\b\u0002\u00105\u001a\u000204\u0012\u0006\u0010`\u001a\u00020\u0018\u0012\u0006\u0010a\u001a\u00020;\u0012\u0010\b\u0002\u0010<\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010(\u0012\b\b\u0002\u0010>\u001a\u00020\u001f\u0012\b\b\u0002\u0010?\u001a\u00020\u001f\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010F\u001a\u00020\u001f\u0012\u0010\b\u0002\u0010I\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010(\u0012\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010b\u001a\u00020\u001f\u0012\b\b\u0002\u0010c\u001a\u00020\u001f\u0012\b\b\u0002\u0010T\u001a\u00020\u001f\u0012\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\n¢\u0006\u0004\bd\u0010eJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011R$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010$\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b$\u0010\u001a\u001a\u0004\b%\u0010\u001cR\u0019\u0010&\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b&\u0010\u000f\u001a\u0004\b'\u0010\u0011R\u001f\u0010)\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\"\u0010-\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010!\u001a\u0004\b.\u0010#\"\u0004\b/\u00100R\"\u00101\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010!\u001a\u0004\b2\u0010#\"\u0004\b3\u00100R\"\u00105\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001f\u0010<\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b<\u0010*\u001a\u0004\b=\u0010,R\u0017\u0010>\u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b>\u0010!\u001a\u0004\b>\u0010#R\u0017\u0010?\u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b?\u0010!\u001a\u0004\b@\u0010#R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\"\u0010F\u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010!\u001a\u0004\bG\u0010#\"\u0004\bH\u00100R*\u0010I\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bI\u0010*\u001a\u0004\bJ\u0010,\"\u0004\bK\u0010LR$\u0010M\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bM\u0010\u000f\u001a\u0004\bN\u0010\u0011\"\u0004\bO\u0010PR$\u0010Q\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u0010\u000f\u001a\u0004\bR\u0010\u0011\"\u0004\bS\u0010PR\u0017\u0010T\u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\bT\u0010!\u001a\u0004\bT\u0010#R$\u0010U\u001a\u0004\u0018\u00010\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010Z¨\u0006f"}, d2 = {"Lcom/amazon/music/views/library/models/HorizontalTileModel;", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "Lcom/amazon/music/views/library/models/base/HotspotSupportedModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newModelState", "", "checkForChanges", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "updateDownloadState", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "hotspotModel", "setHotspotModel", "", ContextMappingConstants.LABEL, "Ljava/lang/String;", "getLabel", "()Ljava/lang/String;", "title", "getTitle", "subTitle", "getSubTitle", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "", "number", "Ljava/lang/Integer;", "getNumber", "()Ljava/lang/Integer;", "setNumber", "(Ljava/lang/Integer;)V", "", "shouldShowNumber", "Z", "getShouldShowNumber", "()Z", "brandingImageResource", "getBrandingImageResource", "durationText", "getDurationText", "", ContextMappingConstants.CONTENT_ENCODING, "Ljava/util/List;", "getContentEncoding", "()Ljava/util/List;", "showNavigationIcon", "getShowNavigationIcon", "setShowNavigationIcon", "(Z)V", "shouldShowOverflowMenu", "getShouldShowOverflowMenu", "setShouldShowOverflowMenu", "Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "horizontalTileType", "Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "getHorizontalTileType", "()Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "setHorizontalTileType", "(Lcom/amazon/music/views/library/models/enums/HorizontalTileType;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "getMetadataList", "is16x9Tile", "shouldEnableOrdinal", "getShouldEnableOrdinal", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getDownloadStateModel", "()Lcom/amazon/music/views/library/models/DownloadStateModel;", "setDownloadStateModel", "(Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "shouldDisplayPlaybackIcon", "getShouldDisplayPlaybackIcon", "setShouldDisplayPlaybackIcon", "labels", "getLabels", "setLabels", "(Ljava/util/List;)V", "rank", "getRank", "setRank", "(Ljava/lang/String;)V", "rankMovement", "getRankMovement", "setRankMovement", "isImageCircle", "hotspotTooltipModel", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "getHotspotTooltipModel", "()Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "setHotspotTooltipModel", "(Lcom/amazon/music/views/library/models/HotspotTooltipModel;)V", "viewId", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "artworkFrameModel", "enableAdd", "isInLibrary", "contentType", "metadata", "isLiked", "isSuggestion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/lang/Integer;ZLjava/lang/Integer;Ljava/lang/String;ZZLjava/util/List;ZZLcom/amazon/music/views/library/models/enums/HorizontalTileType;ILcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;ZZLcom/amazon/music/views/library/models/DownloadStateModel;ZLjava/util/List;Ljava/lang/String;Ljava/lang/String;ZZZLcom/amazon/music/views/library/models/HotspotTooltipModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class HorizontalTileModel extends LibraryAddableModel implements HotspotSupportedModel {
    private final Integer brandingImageResource;
    private final List<String> contentEncoding;
    private DownloadStateModel downloadStateModel;
    private final String durationText;
    private HorizontalTileType horizontalTileType;
    private HotspotTooltipModel hotspotTooltipModel;
    private final boolean is16x9Tile;
    private final boolean isImageCircle;
    private final String label;
    private List<String> labels;
    private final List<ContentMetadata> metadataList;
    private Integer number;
    private String rank;
    private String rankMovement;
    private boolean shouldDisplayPlaybackIcon;
    private final boolean shouldEnableOrdinal;
    private final boolean shouldShowNumber;
    private boolean shouldShowOverflowMenu;
    private boolean showNavigationIcon;
    private final String subTitle;
    private final String tertiaryText;
    private final String title;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public HorizontalTileModel(String str, String str2, String str3, String str4, String str5, ArtworkFrameModel artworkFrameModel, Integer num, boolean z, Integer num2, String str6, boolean z2, boolean z3, List<String> list, boolean z4, boolean z5, HorizontalTileType horizontalTileType, int i, ContentMetadata metadata, List<? extends ContentMetadata> list2, boolean z6) {
        this(str, str2, str3, str4, str5, artworkFrameModel, num, z, num2, str6, z2, z3, list, z4, z5, horizontalTileType, i, metadata, list2, z6, false, null, false, null, null, null, false, false, false, null, 1072693248, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(horizontalTileType, "horizontalTileType");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final String getTertiaryText() {
        return this.tertiaryText;
    }

    public final Integer getNumber() {
        return this.number;
    }

    public final void setNumber(Integer num) {
        this.number = num;
    }

    public final boolean getShouldShowNumber() {
        return this.shouldShowNumber;
    }

    public final Integer getBrandingImageResource() {
        return this.brandingImageResource;
    }

    public final String getDurationText() {
        return this.durationText;
    }

    public final List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public final boolean getShowNavigationIcon() {
        return this.showNavigationIcon;
    }

    public final void setShowNavigationIcon(boolean z) {
        this.showNavigationIcon = z;
    }

    public final boolean getShouldShowOverflowMenu() {
        return this.shouldShowOverflowMenu;
    }

    public final void setShouldShowOverflowMenu(boolean z) {
        this.shouldShowOverflowMenu = z;
    }

    public /* synthetic */ HorizontalTileModel(String str, String str2, String str3, String str4, String str5, ArtworkFrameModel artworkFrameModel, Integer num, boolean z, Integer num2, String str6, boolean z2, boolean z3, List list, boolean z4, boolean z5, HorizontalTileType horizontalTileType, int i, ContentMetadata contentMetadata, List list2, boolean z6, boolean z7, DownloadStateModel downloadStateModel, boolean z8, List list3, String str7, String str8, boolean z9, boolean z10, boolean z11, HotspotTooltipModel hotspotTooltipModel, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, artworkFrameModel, (i2 & 64) != 0 ? null : num, (i2 & 128) != 0 ? false : z, (i2 & 256) != 0 ? null : num2, (i2 & 512) != 0 ? null : str6, (i2 & 1024) != 0 ? false : z2, (i2 & 2048) != 0 ? false : z3, (i2 & 4096) != 0 ? null : list, (i2 & 8192) != 0 ? false : z4, (i2 & 16384) != 0 ? true : z5, (32768 & i2) != 0 ? HorizontalTileType.REGULAR : horizontalTileType, i, contentMetadata, (262144 & i2) != 0 ? null : list2, (524288 & i2) != 0 ? false : z6, (1048576 & i2) != 0 ? false : z7, (2097152 & i2) != 0 ? null : downloadStateModel, (4194304 & i2) != 0 ? false : z8, (8388608 & i2) != 0 ? null : list3, (16777216 & i2) != 0 ? null : str7, (33554432 & i2) != 0 ? null : str8, (67108864 & i2) != 0 ? false : z9, (134217728 & i2) != 0 ? false : z10, (268435456 & i2) != 0 ? false : z11, (i2 & 536870912) != 0 ? null : hotspotTooltipModel);
    }

    public final HorizontalTileType getHorizontalTileType() {
        return this.horizontalTileType;
    }

    public final void setHorizontalTileType(HorizontalTileType horizontalTileType) {
        Intrinsics.checkNotNullParameter(horizontalTileType, "<set-?>");
        this.horizontalTileType = horizontalTileType;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }

    /* JADX INFO: renamed from: is16x9Tile, reason: from getter */
    public final boolean getIs16x9Tile() {
        return this.is16x9Tile;
    }

    public final boolean getShouldEnableOrdinal() {
        return this.shouldEnableOrdinal;
    }

    public final DownloadStateModel getDownloadStateModel() {
        return this.downloadStateModel;
    }

    public final void setDownloadStateModel(DownloadStateModel downloadStateModel) {
        this.downloadStateModel = downloadStateModel;
    }

    public final boolean getShouldDisplayPlaybackIcon() {
        return this.shouldDisplayPlaybackIcon;
    }

    public final List<String> getLabels() {
        return this.labels;
    }

    public final String getRank() {
        return this.rank;
    }

    public final String getRankMovement() {
        return this.rankMovement;
    }

    /* JADX INFO: renamed from: isImageCircle, reason: from getter */
    public final boolean getIsImageCircle() {
        return this.isImageCircle;
    }

    public HotspotTooltipModel getHotspotTooltipModel() {
        return this.hotspotTooltipModel;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedModel
    public void setHotspotTooltipModel(HotspotTooltipModel hotspotTooltipModel) {
        this.hotspotTooltipModel = hotspotTooltipModel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public HorizontalTileModel(String str, String str2, String str3, String str4, String str5, ArtworkFrameModel artworkFrameModel, Integer num, boolean z, Integer num2, String str6, boolean z2, boolean z3, List<String> list, boolean z4, boolean z5, HorizontalTileType horizontalTileType, int i, ContentMetadata metadata, List<? extends ContentMetadata> list2, boolean z6, boolean z7, DownloadStateModel downloadStateModel, boolean z8, List<String> list3, String str7, String str8, boolean z9, boolean z10, boolean z11, HotspotTooltipModel hotspotTooltipModel) {
        super(str, artworkFrameModel, i, metadata, z3, z10, z2, z9);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(horizontalTileType, "horizontalTileType");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.label = str2;
        this.title = str3;
        this.subTitle = str4;
        this.tertiaryText = str5;
        this.number = num;
        this.shouldShowNumber = z;
        this.brandingImageResource = num2;
        this.durationText = str6;
        this.contentEncoding = list;
        this.showNavigationIcon = z4;
        this.shouldShowOverflowMenu = z5;
        this.horizontalTileType = horizontalTileType;
        this.metadataList = list2;
        this.is16x9Tile = z6;
        this.shouldEnableOrdinal = z7;
        this.downloadStateModel = downloadStateModel;
        this.shouldDisplayPlaybackIcon = z8;
        this.labels = list3;
        this.rank = str7;
        this.rankMovement = str8;
        this.isImageCircle = z11;
        this.hotspotTooltipModel = hotspotTooltipModel;
    }

    @Override // com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public void checkForChanges(BaseViewModel newModelState) {
        DownloadStateModel downloadStateModel;
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
        super.checkForChanges(newModelState);
        if (!(newModelState instanceof HorizontalTileModel) || (downloadStateModel = ((HorizontalTileModel) newModelState).downloadStateModel) == null) {
            return;
        }
        updateDownloadState(downloadStateModel);
    }

    public final void updateDownloadState(DownloadStateModel downloadStateModel) {
        Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
        if (Intrinsics.areEqual(this.downloadStateModel, downloadStateModel)) {
            return;
        }
        this.downloadStateModel = downloadStateModel;
        notifyChanged(12);
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedModel
    public void setHotspotModel(HotspotTooltipModel hotspotModel) {
        Intrinsics.checkNotNullParameter(hotspotModel, "hotspotModel");
        setHotspotTooltipModel(hotspotModel);
    }
}
