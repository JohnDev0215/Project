package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002Bé\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010-\u001a\u00020,\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u00106\u001a\u000205\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010I\u001a\u00020\u0004\u0012\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010K\u001a\u00020\u0006\u0012\u0006\u0010M\u001a\u00020L\u0012\b\b\u0002\u0010N\u001a\u00020\u0004\u0012\b\b\u0002\u0010O\u001a\u00020\u0004\u0012\b\b\u0002\u0010P\u001a\u00020\u0004\u0012\n\b\u0002\u0010R\u001a\u0004\u0018\u00010Q\u0012\b\b\u0002\u0010S\u001a\u00020\u0004\u0012\b\b\u0002\u0010T\u001a\u00020\u0004¢\u0006\u0004\bU\u0010VJ\u0013\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0000H\u0016R$\u0010\t\u001a\u0004\u0018\u00010\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0004\u0018\u00010\u000f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R$\u0010\u0019\u001a\u0004\u0018\u00010\u000f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001f\u0010\u0013R\u001c\u0010 \u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010!\u001a\u0004\b%\u0010#R\u001c\u0010&\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010\u0011\u001a\u0004\b'\u0010\u0013R\u001c\u0010(\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010\u0011\u001a\u0004\b)\u0010\u0013R\u001c\u0010*\u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010!\u001a\u0004\b+\u0010#R\"\u0010-\u001a\u00020,8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b3\u0010!\u001a\u0004\b4\u0010#R\u0017\u00106\u001a\u0002058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0019\u0010:\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b:\u0010\n\u001a\u0004\b;\u0010\fR\u0019\u0010<\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b<\u0010\n\u001a\u0004\b=\u0010\fR\u0019\u0010>\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b>\u0010\u0011\u001a\u0004\b?\u0010\u0013R\u0019\u0010@\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b@\u0010!\u001a\u0004\bA\u0010#R\u0019\u0010B\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\bB\u0010!\u001a\u0004\bB\u0010#R\u0019\u0010C\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\bC\u0010!\u001a\u0004\bD\u0010#R\u0019\u0010E\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\bE\u0010!\u001a\u0004\bF\u0010#R\u0019\u0010G\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\bG\u0010!\u001a\u0004\bH\u0010#¨\u0006W"}, d2 = {"Lcom/amazon/music/views/library/models/VisualRowItemModel;", "Lcom/amazon/music/views/library/models/TableRowItemModel;", "", "other", "", "equals", "", "hashCode", "copy", "rowNumber", "Ljava/lang/Integer;", "getRowNumber", "()Ljava/lang/Integer;", "setRowNumber", "(Ljava/lang/Integer;)V", "", ContextMappingConstants.PRIMARY_TEXT, "Ljava/lang/String;", "getPrimaryText", "()Ljava/lang/String;", "setPrimaryText", "(Ljava/lang/String;)V", "secondaryText1", "getSecondaryText1", "setSecondaryText1", "secondaryText2", "getSecondaryText2", "setSecondaryText2", "date", "getDate", "time", "getTime", "hasLyricsAvailable", "Ljava/lang/Boolean;", "getHasLyricsAvailable", "()Ljava/lang/Boolean;", "shouldShowBadging", "getShouldShowBadging", "stereoBadgeText", "getStereoBadgeText", "spatialBadgeText", "getSpatialBadgeText", "hasBadgeError", "getHasBadgeError", "Lcom/amazon/music/views/library/models/DisplayState;", "displayState", "Lcom/amazon/music/views/library/models/DisplayState;", "getDisplayState", "()Lcom/amazon/music/views/library/models/DisplayState;", "setDisplayState", "(Lcom/amazon/music/views/library/models/DisplayState;)V", "showExplicitBadge", "getShowExplicitBadge", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "rowImage", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getRowImage", "()Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "rowImageId", "getRowImageId", "primaryTextId", "getPrimaryTextId", "target", "getTarget", "canDrag", "getCanDrag", "is16x9Tile", "showShuffleIcon", "getShowShuffleIcon", "showStationIcon", "getShowStationIcon", "showOverflow", "getShowOverflow", "insetHorizontally", "viewRefId", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "isInLibrary", "isSuggestion", "enableAdd", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "showSDCardIcon", "isLiked", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lcom/amazon/music/views/library/models/DisplayState;Ljava/lang/Boolean;Lcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;ZLjava/lang/String;ILcom/amazon/music/views/library/metadata/ContentMetadata;ZZZLcom/amazon/music/views/library/models/DownloadStateModel;ZZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class VisualRowItemModel extends TableRowItemModel {
    private final Boolean canDrag;
    private final String date;
    private DisplayState displayState;
    private final Boolean hasBadgeError;
    private final Boolean hasLyricsAvailable;
    private final Boolean is16x9Tile;
    private String primaryText;
    private final Integer primaryTextId;
    private final ArtworkFrameModel rowImage;
    private final Integer rowImageId;
    private Integer rowNumber;
    private String secondaryText1;
    private String secondaryText2;
    private final Boolean shouldShowBadging;
    private final Boolean showExplicitBadge;
    private final Boolean showOverflow;
    private final Boolean showShuffleIcon;
    private final Boolean showStationIcon;
    private final String spatialBadgeText;
    private final String stereoBadgeText;
    private final String target;
    private final String time;

    @Override // com.amazon.music.views.library.models.TableRowItem
    public Integer getRowNumber() {
        return this.rowNumber;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public void setRowNumber(Integer num) {
        this.rowNumber = num;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public String getPrimaryText() {
        return this.primaryText;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public void setPrimaryText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.primaryText = str;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public String getSecondaryText1() {
        return this.secondaryText1;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public void setSecondaryText1(String str) {
        this.secondaryText1 = str;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public String getSecondaryText2() {
        return this.secondaryText2;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public void setSecondaryText2(String str) {
        this.secondaryText2 = str;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public String getDate() {
        return this.date;
    }

    @Override // com.amazon.music.views.library.models.TableRowItem
    public String getTime() {
        return this.time;
    }

    public /* synthetic */ VisualRowItemModel(Integer num, String str, String str2, String str3, String str4, String str5, Boolean bool, Boolean bool2, String str6, String str7, Boolean bool3, DisplayState displayState, Boolean bool4, ArtworkFrameModel artworkFrameModel, Integer num2, Integer num3, String str8, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, boolean z, String str9, int i, ContentMetadata contentMetadata, boolean z2, boolean z3, boolean z4, DownloadStateModel downloadStateModel, boolean z5, boolean z6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : num, str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? Boolean.FALSE : bool, (i2 & 128) != 0 ? Boolean.FALSE : bool2, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? null : str7, (i2 & 1024) != 0 ? Boolean.FALSE : bool3, (i2 & 2048) != 0 ? DisplayState.NORMAL : displayState, (i2 & 4096) != 0 ? Boolean.TRUE : bool4, artworkFrameModel, (i2 & 16384) != 0 ? null : num2, (32768 & i2) != 0 ? null : num3, (65536 & i2) != 0 ? null : str8, (131072 & i2) != 0 ? Boolean.FALSE : bool5, (262144 & i2) != 0 ? Boolean.FALSE : bool6, (524288 & i2) != 0 ? Boolean.FALSE : bool7, (1048576 & i2) != 0 ? Boolean.FALSE : bool8, (2097152 & i2) != 0 ? Boolean.TRUE : bool9, (4194304 & i2) != 0 ? false : z, (8388608 & i2) != 0 ? null : str9, i, contentMetadata, (67108864 & i2) != 0 ? false : z2, (134217728 & i2) != 0 ? false : z3, (268435456 & i2) != 0 ? true : z4, (536870912 & i2) != 0 ? null : downloadStateModel, (1073741824 & i2) != 0 ? false : z5, (i2 & Integer.MIN_VALUE) != 0 ? false : z6);
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public Boolean getHasLyricsAvailable() {
        return this.hasLyricsAvailable;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public Boolean getShouldShowBadging() {
        return this.shouldShowBadging;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public String getStereoBadgeText() {
        return this.stereoBadgeText;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public String getSpatialBadgeText() {
        return this.spatialBadgeText;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public Boolean getHasBadgeError() {
        return this.hasBadgeError;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public DisplayState getDisplayState() {
        return this.displayState;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public void setDisplayState(DisplayState displayState) {
        Intrinsics.checkNotNullParameter(displayState, "<set-?>");
        this.displayState = displayState;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel
    public Boolean getShowExplicitBadge() {
        return this.showExplicitBadge;
    }

    public final ArtworkFrameModel getRowImage() {
        return this.rowImage;
    }

    public final Integer getRowImageId() {
        return this.rowImageId;
    }

    public final Integer getPrimaryTextId() {
        return this.primaryTextId;
    }

    public final String getTarget() {
        return this.target;
    }

    public final Boolean getCanDrag() {
        return this.canDrag;
    }

    /* JADX INFO: renamed from: is16x9Tile, reason: from getter */
    public final Boolean getIs16x9Tile() {
        return this.is16x9Tile;
    }

    public final Boolean getShowShuffleIcon() {
        return this.showShuffleIcon;
    }

    public final Boolean getShowStationIcon() {
        return this.showStationIcon;
    }

    public final Boolean getShowOverflow() {
        return this.showOverflow;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisualRowItemModel(Integer num, String primaryText, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, String str5, String str6, Boolean bool3, DisplayState displayState, Boolean bool4, ArtworkFrameModel rowImage, Integer num2, Integer num3, String str7, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, boolean z, String str8, int i, ContentMetadata metadata, boolean z2, boolean z3, boolean z4, DownloadStateModel downloadStateModel, boolean z5, boolean z6) {
        super(str8, rowImage, i, metadata, z2, z3, z4, z6, downloadStateModel, z5, null, z, false, 5120, null);
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        Intrinsics.checkNotNullParameter(displayState, "displayState");
        Intrinsics.checkNotNullParameter(rowImage, "rowImage");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.rowNumber = num;
        this.primaryText = primaryText;
        this.secondaryText1 = str;
        this.secondaryText2 = str2;
        this.date = str3;
        this.time = str4;
        this.hasLyricsAvailable = bool;
        this.shouldShowBadging = bool2;
        this.stereoBadgeText = str5;
        this.spatialBadgeText = str6;
        this.hasBadgeError = bool3;
        this.displayState = displayState;
        this.showExplicitBadge = bool4;
        this.rowImage = rowImage;
        this.rowImageId = num2;
        this.primaryTextId = num3;
        this.target = str7;
        this.canDrag = bool5;
        this.is16x9Tile = bool6;
        this.showShuffleIcon = bool7;
        this.showStationIcon = bool8;
        this.showOverflow = bool9;
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel, com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(VisualRowItemModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            VisualRowItemModel visualRowItemModel = (VisualRowItemModel) other;
            if (!Intrinsics.areEqual(getRowNumber(), visualRowItemModel.getRowNumber()) || !Intrinsics.areEqual(getPrimaryText(), visualRowItemModel.getPrimaryText()) || !Intrinsics.areEqual(getSecondaryText1(), visualRowItemModel.getSecondaryText1()) || !Intrinsics.areEqual(getSecondaryText2(), visualRowItemModel.getSecondaryText2()) || !Intrinsics.areEqual(getDate(), visualRowItemModel.getDate()) || !Intrinsics.areEqual(getTime(), visualRowItemModel.getTime()) || !Intrinsics.areEqual(getHasLyricsAvailable(), visualRowItemModel.getHasLyricsAvailable()) || !Intrinsics.areEqual(getShouldShowBadging(), visualRowItemModel.getShouldShowBadging()) || !Intrinsics.areEqual(getStereoBadgeText(), visualRowItemModel.getStereoBadgeText()) || !Intrinsics.areEqual(getSpatialBadgeText(), visualRowItemModel.getSpatialBadgeText()) || !Intrinsics.areEqual(getHasBadgeError(), visualRowItemModel.getHasBadgeError()) || getDisplayState() != visualRowItemModel.getDisplayState() || !Intrinsics.areEqual(this.rowImage, visualRowItemModel.rowImage) || !Intrinsics.areEqual(this.target, visualRowItemModel.target) || !Intrinsics.areEqual(this.canDrag, visualRowItemModel.canDrag) || !Intrinsics.areEqual(this.is16x9Tile, visualRowItemModel.is16x9Tile) || !Intrinsics.areEqual(this.showShuffleIcon, visualRowItemModel.showShuffleIcon) || !Intrinsics.areEqual(this.showStationIcon, visualRowItemModel.showStationIcon) || !Intrinsics.areEqual(this.showOverflow, visualRowItemModel.showOverflow) || getInsetHorizontally() != visualRowItemModel.getInsetHorizontally() || getShowSDCardIcon() != visualRowItemModel.getShowSDCardIcon()) {
                return false;
            }
            DownloadStateModel downloadStateModel = getDownloadStateModel();
            return (downloadStateModel != null && downloadStateModel.equals(visualRowItemModel.getDownloadStateModel())) && Intrinsics.areEqual(getShowExplicitBadge(), visualRowItemModel.getShowExplicitBadge()) && getIsLiked() == visualRowItemModel.getIsLiked();
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.VisualRowItemModel");
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel, com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = ((super.hashCode() * 31) + getPrimaryText().hashCode()) * 31;
        String secondaryText1 = getSecondaryText1();
        int iHashCode2 = (iHashCode + (secondaryText1 == null ? 0 : secondaryText1.hashCode())) * 31;
        String secondaryText2 = getSecondaryText2();
        int iHashCode3 = (iHashCode2 + (secondaryText2 == null ? 0 : secondaryText2.hashCode())) * 31;
        String date = getDate();
        int iHashCode4 = (iHashCode3 + (date == null ? 0 : date.hashCode())) * 31;
        String time = getTime();
        int iHashCode5 = (((iHashCode4 + (time == null ? 0 : time.hashCode())) * 31) + this.rowImage.hashCode()) * 31;
        String str = this.target;
        int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.canDrag;
        int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.is16x9Tile;
        int iHashCode8 = (iHashCode7 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.showShuffleIcon;
        int iHashCode9 = (iHashCode8 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.showStationIcon;
        int iHashCode10 = (iHashCode9 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.showOverflow;
        int iHashCode11 = (((((iHashCode10 + (bool5 == null ? 0 : bool5.hashCode())) * 31) + Boolean.hashCode(getInsetHorizontally())) * 31) + Boolean.hashCode(getShowSDCardIcon())) * 31;
        DownloadStateModel downloadStateModel = getDownloadStateModel();
        int iHashCode12 = (iHashCode11 + (downloadStateModel == null ? 0 : downloadStateModel.hashCode())) * 31;
        Boolean showExplicitBadge = getShowExplicitBadge();
        return ((iHashCode12 + (showExplicitBadge != null ? showExplicitBadge.hashCode() : 0)) * 31) + Boolean.hashCode(getIsLiked());
    }

    public VisualRowItemModel copy() {
        ContentMetadata metadata = getMetadata();
        ArtworkFrameModel artworkFrameModel = this.rowImage;
        String primaryText = getPrimaryText();
        String secondaryText1 = getSecondaryText1();
        String secondaryText2 = getSecondaryText2();
        Integer rowNumber = getRowNumber();
        String date = getDate();
        String time = getTime();
        Boolean hasLyricsAvailable = getHasLyricsAvailable();
        Boolean shouldShowBadging = getShouldShowBadging();
        String stereoBadgeText = getStereoBadgeText();
        String spatialBadgeText = getSpatialBadgeText();
        Boolean hasBadgeError = getHasBadgeError();
        DisplayState displayState = getDisplayState();
        int contentType = getContentType();
        boolean isInLibrary = getIsInLibrary();
        boolean isLiked = getIsLiked();
        DownloadStateModel downloadStateModel = getDownloadStateModel();
        Boolean bool = this.canDrag;
        Boolean bool2 = this.showOverflow;
        return new VisualRowItemModel(rowNumber, primaryText, secondaryText1, secondaryText2, date, time, hasLyricsAvailable, shouldShowBadging, stereoBadgeText, spatialBadgeText, hasBadgeError, displayState, getShowExplicitBadge(), artworkFrameModel, null, null, null, bool, null, this.showShuffleIcon, this.showStationIcon, bool2, getInsetHorizontally(), null, contentType, metadata, isInLibrary, false, false, downloadStateModel, false, isLiked, 1485160448, null);
    }
}
