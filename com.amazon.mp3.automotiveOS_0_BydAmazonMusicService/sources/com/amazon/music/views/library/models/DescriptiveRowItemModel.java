package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.google.firebase.perf.util.Constants;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u009f\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\f\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\f\u0012\b\b\u0002\u0010\u001e\u001a\u00020\f\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\b\b\u0002\u0010!\u001a\u00020\f\u0012\b\b\u0002\u0010\"\u001a\u00020\f\u0012\b\b\u0002\u0010#\u001a\u00020\f\u0012\b\b\u0002\u0010$\u001a\u00020\f¢\u0006\u0002\u0010%J\u0013\u0010L\u001a\u00020\f2\b\u0010M\u001a\u0004\u0018\u00010NH\u0096\u0002J\b\u0010O\u001a\u00020\u0004H\u0016J\u0010\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020\fH\u0016R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R$\u0010)\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0016\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010'R\u001a\u0010\u0011\u001a\u00020\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\n\n\u0002\u00105\u001a\u0004\b3\u00104R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\n\n\u0002\u00105\u001a\u0004\b6\u00104R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010'\"\u0004\b8\u00109R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u0010\n\u0002\u0010>\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010'\"\u0004\b@\u00109R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010'\"\u0004\bB\u00109R\u0018\u0010\r\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\n\n\u0002\u00105\u001a\u0004\bC\u00104R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\n\n\u0002\u00105\u001a\u0004\bD\u00104R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010>\u001a\u0004\bE\u0010;R\u0011\u0010\u0016\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\bF\u0010+R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010'R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010'R\u0016\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010'¨\u0006S"}, d2 = {"Lcom/amazon/music/views/library/models/DescriptiveRowItemModel;", "Lcom/amazon/music/views/library/models/TableRowItemModel;", "Lcom/amazon/music/views/library/models/CommentaryToggleStateObserver;", "rowNumber", "", ContextMappingConstants.PRIMARY_TEXT, "", "secondaryText1", "secondaryText2", "date", "time", "hasLyricsAvailable", "", "shouldShowBadging", "stereoBadgeText", "spatialBadgeText", "hasBadgeError", "displayState", "Lcom/amazon/music/views/library/models/DisplayState;", "showExplicitBadge", "slotNumber", "Lcom/amazon/music/views/library/models/SlotNumber;", "showStationIcon", "showRowCta", "commentaryText", "viewRefId", "contentType", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isInLibrary", "enableAdd", "downloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "showSDCardIcon", "insetHorizontally", "isLiked", "isSuggestion", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lcom/amazon/music/views/library/models/DisplayState;Ljava/lang/Boolean;Lcom/amazon/music/views/library/models/SlotNumber;ZLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;ILcom/amazon/music/views/library/metadata/ContentMetadata;ZZLcom/amazon/music/views/library/models/DownloadStateModel;ZZZZ)V", "getCommentaryText", "()Ljava/lang/String;", "value", "commentaryVisible", "getCommentaryVisible", "()Z", "setCommentaryVisible", "(Z)V", "getDate", "getDisplayState", "()Lcom/amazon/music/views/library/models/DisplayState;", "setDisplayState", "(Lcom/amazon/music/views/library/models/DisplayState;)V", "getHasBadgeError", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getHasLyricsAvailable", "getPrimaryText", "setPrimaryText", "(Ljava/lang/String;)V", "getRowNumber", "()Ljava/lang/Integer;", "setRowNumber", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSecondaryText1", "setSecondaryText1", "getSecondaryText2", "setSecondaryText2", "getShouldShowBadging", "getShowExplicitBadge", "getShowRowCta", "getShowStationIcon", "getSlotNumber", "()Lcom/amazon/music/views/library/models/SlotNumber;", "getSpatialBadgeText", "getStereoBadgeText", "getTime", "equals", "other", "", "hashCode", "updateCommentaryToggleState", "", Constants.ENABLE_DISABLE, "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveRowItemModel extends TableRowItemModel implements CommentaryToggleStateObserver {
    private final String commentaryText;
    private boolean commentaryVisible;
    private final String date;
    private DisplayState displayState;
    private final Boolean hasBadgeError;
    private final Boolean hasLyricsAvailable;
    private String primaryText;
    private Integer rowNumber;
    private String secondaryText1;
    private String secondaryText2;
    private final Boolean shouldShowBadging;
    private final Boolean showExplicitBadge;
    private final Integer showRowCta;
    private final boolean showStationIcon;
    private final SlotNumber slotNumber;
    private final String spatialBadgeText;
    private final String stereoBadgeText;
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

    public /* synthetic */ DescriptiveRowItemModel(Integer num, String str, String str2, String str3, String str4, String str5, Boolean bool, Boolean bool2, String str6, String str7, Boolean bool3, DisplayState displayState, Boolean bool4, SlotNumber slotNumber, boolean z, Integer num2, String str8, String str9, int i, ContentMetadata contentMetadata, boolean z2, boolean z3, DownloadStateModel downloadStateModel, boolean z4, boolean z5, boolean z6, boolean z7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : num, str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, bool, bool2, str6, str7, bool3, (i2 & 2048) != 0 ? DisplayState.NORMAL : displayState, (i2 & 4096) != 0 ? Boolean.TRUE : bool4, slotNumber, (i2 & 16384) != 0 ? false : z, (32768 & i2) != 0 ? null : num2, (65536 & i2) != 0 ? null : str8, (131072 & i2) != 0 ? null : str9, i, contentMetadata, (1048576 & i2) != 0 ? false : z2, (2097152 & i2) != 0 ? true : z3, (4194304 & i2) != 0 ? null : downloadStateModel, (8388608 & i2) != 0 ? false : z4, (16777216 & i2) != 0 ? false : z5, (33554432 & i2) != 0 ? false : z6, (i2 & voOSType.VOOSMP_SRC_FFAUDIO_MIDI) != 0 ? false : z7);
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

    public final SlotNumber getSlotNumber() {
        return this.slotNumber;
    }

    public final boolean getShowStationIcon() {
        return this.showStationIcon;
    }

    public final Integer getShowRowCta() {
        return this.showRowCta;
    }

    public final String getCommentaryText() {
        return this.commentaryText;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptiveRowItemModel(Integer num, String primaryText, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, String str5, String str6, Boolean bool3, DisplayState displayState, Boolean bool4, SlotNumber slotNumber, boolean z, Integer num2, String str7, String str8, int i, ContentMetadata metadata, boolean z2, boolean z3, DownloadStateModel downloadStateModel, boolean z4, boolean z5, boolean z6, boolean z7) {
        super(str8, null, i, metadata, z2, z7, z3, z6, downloadStateModel, z4, null, z5, false, 5120, null);
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        Intrinsics.checkNotNullParameter(displayState, "displayState");
        Intrinsics.checkNotNullParameter(slotNumber, "slotNumber");
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
        this.slotNumber = slotNumber;
        this.showStationIcon = z;
        this.showRowCta = num2;
        this.commentaryText = str7;
    }

    public final boolean getCommentaryVisible() {
        return this.commentaryVisible;
    }

    public final void setCommentaryVisible(boolean z) {
        if (z == this.commentaryVisible) {
            return;
        }
        this.commentaryVisible = z;
        notifyChanged(20);
    }

    @Override // com.amazon.music.views.library.models.CommentaryToggleStateObserver
    public void updateCommentaryToggleState(boolean isEnabled) {
        setCommentaryVisible(isEnabled);
    }

    @Override // com.amazon.music.views.library.models.TableRowItemModel, com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(DescriptiveRowItemModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            DescriptiveRowItemModel descriptiveRowItemModel = (DescriptiveRowItemModel) other;
            if (!Intrinsics.areEqual(getRowNumber(), descriptiveRowItemModel.getRowNumber()) || !Intrinsics.areEqual(getPrimaryText(), descriptiveRowItemModel.getPrimaryText()) || !Intrinsics.areEqual(getSecondaryText1(), descriptiveRowItemModel.getSecondaryText1()) || !Intrinsics.areEqual(getSecondaryText2(), descriptiveRowItemModel.getSecondaryText2()) || !Intrinsics.areEqual(getDate(), descriptiveRowItemModel.getDate()) || !Intrinsics.areEqual(getTime(), descriptiveRowItemModel.getTime()) || !Intrinsics.areEqual(getHasLyricsAvailable(), descriptiveRowItemModel.getHasLyricsAvailable()) || !Intrinsics.areEqual(getShouldShowBadging(), descriptiveRowItemModel.getShouldShowBadging()) || !Intrinsics.areEqual(getStereoBadgeText(), descriptiveRowItemModel.getStereoBadgeText()) || !Intrinsics.areEqual(getSpatialBadgeText(), descriptiveRowItemModel.getSpatialBadgeText()) || !Intrinsics.areEqual(getHasBadgeError(), descriptiveRowItemModel.getHasBadgeError()) || getDisplayState() != descriptiveRowItemModel.getDisplayState() || this.slotNumber != descriptiveRowItemModel.slotNumber || this.showStationIcon != descriptiveRowItemModel.showStationIcon || getShowSDCardIcon() != descriptiveRowItemModel.getShowSDCardIcon()) {
                return false;
            }
            DownloadStateModel downloadStateModel = getDownloadStateModel();
            return (downloadStateModel != null && downloadStateModel.equals(descriptiveRowItemModel.getDownloadStateModel())) && Intrinsics.areEqual(getShowExplicitBadge(), descriptiveRowItemModel.getShowExplicitBadge());
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.DescriptiveRowItemModel");
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
        int iHashCode5 = (iHashCode4 + (time == null ? 0 : time.hashCode())) * 31;
        Boolean hasLyricsAvailable = getHasLyricsAvailable();
        int iHashCode6 = (iHashCode5 + (hasLyricsAvailable == null ? 0 : hasLyricsAvailable.hashCode())) * 31;
        Boolean shouldShowBadging = getShouldShowBadging();
        int iHashCode7 = (iHashCode6 + (shouldShowBadging == null ? 0 : shouldShowBadging.hashCode())) * 31;
        String stereoBadgeText = getStereoBadgeText();
        int iHashCode8 = (iHashCode7 + (stereoBadgeText == null ? 0 : stereoBadgeText.hashCode())) * 31;
        String spatialBadgeText = getSpatialBadgeText();
        int iHashCode9 = (iHashCode8 + (spatialBadgeText == null ? 0 : spatialBadgeText.hashCode())) * 31;
        Boolean hasBadgeError = getHasBadgeError();
        int iHashCode10 = (((((((((iHashCode9 + (hasBadgeError == null ? 0 : hasBadgeError.hashCode())) * 31) + getDisplayState().hashCode()) * 31) + this.slotNumber.hashCode()) * 31) + Boolean.hashCode(this.showStationIcon)) * 31) + Boolean.hashCode(getShowSDCardIcon())) * 31;
        DownloadStateModel downloadStateModel = getDownloadStateModel();
        int iHashCode11 = (iHashCode10 + (downloadStateModel == null ? 0 : downloadStateModel.hashCode())) * 31;
        Boolean showExplicitBadge = getShowExplicitBadge();
        return iHashCode11 + (showExplicitBadge != null ? showExplicitBadge.hashCode() : 0);
    }
}
