package com.amazon.music.views.library.models;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import com.amazon.music.views.library.models.base.Insettable;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.providers.AvailabilityState;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TableRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 Y2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001YB\u0085\u0001\u0012\b\u0010M\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010O\u001a\u0004\u0018\u00010N\u0012\u0006\u0010P\u001a\u00020\u0005\u0012\u0006\u0010R\u001a\u00020Q\u0012\b\b\u0002\u0010S\u001a\u00020\n\u0012\b\b\u0002\u0010T\u001a\u00020\n\u0012\b\b\u0002\u0010U\u001a\u00020\n\u0012\b\b\u0002\u0010V\u001a\u00020\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010)\u001a\u00020\n\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u00104\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\bW\u0010XJ\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012J\u001e\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0012J\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0018J\u0010\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0005H\u0016J\u0013\u0010\"\u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010 H\u0096\u0002J\b\u0010#\u001a\u00020\u0005H\u0016R$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u00104\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b4\u0010*\u001a\u0004\b5\u0010,\"\u0004\b6\u0010.R\"\u0010\u000b\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010*\u001a\u0004\b7\u0010,\"\u0004\b8\u0010.R\u0016\u0010;\u001a\u0004\u0018\u00010\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0016\u0010=\u001a\u0004\u0018\u00010\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010:R\u0016\u0010@\u001a\u0004\u0018\u00010\u00128&X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0016\u0010B\u001a\u0004\u0018\u00010\u00128&X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010?R\u0016\u0010D\u001a\u0004\u0018\u00010\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\bC\u0010:R\u001c\u0010J\u001a\u00020E8&@&X¦\u000e¢\u0006\f\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0016\u0010L\u001a\u0004\u0018\u00010\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\bK\u0010:¨\u0006Z"}, d2 = {"Lcom/amazon/music/views/library/models/TableRowItemModel;", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "Lcom/amazon/music/views/library/models/base/Insettable;", "Lcom/amazon/music/views/library/models/base/Hideable;", "Lcom/amazon/music/views/library/models/TableRowItem;", "", "index", "", "updateRowNumber", "(Ljava/lang/Integer;)V", "", "hidden", "updateVisibility", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "updateDownloadState", "show", "updateSDCardVisibility", "", "updatedText", "updatePrimaryText", "secondaryText1", "secondaryText2", "updateSecondaryText", "Lcom/amazon/music/views/library/providers/AvailabilityState;", "availabilityState", "updateAvailabilityState", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newModelState", "checkForChanges", "newPlayState", "updatePlayState", "", "other", "equals", "hashCode", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getDownloadStateModel", "()Lcom/amazon/music/views/library/models/DownloadStateModel;", "setDownloadStateModel", "(Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "showSDCardIcon", "Z", "getShowSDCardIcon", "()Z", "setShowSDCardIcon", "(Z)V", "Lcom/amazon/music/views/library/providers/AvailabilityState;", "getAvailabilityState", "()Lcom/amazon/music/views/library/providers/AvailabilityState;", "setAvailabilityState", "(Lcom/amazon/music/views/library/providers/AvailabilityState;)V", "insetHorizontally", "getInsetHorizontally", "setInsetHorizontally", "getHidden", "setHidden", "getHasLyricsAvailable", "()Ljava/lang/Boolean;", "hasLyricsAvailable", "getShouldShowBadging", "shouldShowBadging", "getStereoBadgeText", "()Ljava/lang/String;", "stereoBadgeText", "getSpatialBadgeText", "spatialBadgeText", "getHasBadgeError", "hasBadgeError", "Lcom/amazon/music/views/library/models/DisplayState;", "getDisplayState", "()Lcom/amazon/music/views/library/models/DisplayState;", "setDisplayState", "(Lcom/amazon/music/views/library/models/DisplayState;)V", "displayState", "getShowExplicitBadge", "showExplicitBadge", "viewRefId", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "rowImage", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "isInLibrary", "isSuggestion", "enableAdd", "isLiked", "<init>", "(Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;ILcom/amazon/music/views/library/metadata/ContentMetadata;ZZZZLcom/amazon/music/views/library/models/DownloadStateModel;ZLcom/amazon/music/views/library/providers/AvailabilityState;ZZ)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class TableRowItemModel extends LibraryAddableModel implements Insettable, Hideable, TableRowItem {
    private static final Map<Integer, DisplayState> playStateToDisplayStateMapping = MapsKt__MapsKt.mutableMapOf(TuplesKt.to(2, DisplayState.PLAYING), TuplesKt.to(1, DisplayState.PAUSED), TuplesKt.to(0, DisplayState.NORMAL));
    private AvailabilityState availabilityState;
    private DownloadStateModel downloadStateModel;
    private boolean hidden;
    private boolean insetHorizontally;
    private boolean showSDCardIcon;

    public abstract DisplayState getDisplayState();

    public abstract Boolean getHasBadgeError();

    public abstract Boolean getHasLyricsAvailable();

    public abstract Boolean getShouldShowBadging();

    public abstract Boolean getShowExplicitBadge();

    public abstract String getSpatialBadgeText();

    public abstract String getStereoBadgeText();

    public abstract void setDisplayState(DisplayState displayState);

    public final DownloadStateModel getDownloadStateModel() {
        return this.downloadStateModel;
    }

    public final void setDownloadStateModel(DownloadStateModel downloadStateModel) {
        this.downloadStateModel = downloadStateModel;
    }

    public final boolean getShowSDCardIcon() {
        return this.showSDCardIcon;
    }

    public /* synthetic */ TableRowItemModel(String str, ArtworkFrameModel artworkFrameModel, int i, ContentMetadata contentMetadata, boolean z, boolean z2, boolean z3, boolean z4, DownloadStateModel downloadStateModel, boolean z5, AvailabilityState availabilityState, boolean z6, boolean z7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, artworkFrameModel, i, contentMetadata, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2, (i2 & 64) != 0 ? true : z3, (i2 & 128) != 0 ? false : z4, downloadStateModel, (i2 & 512) != 0 ? false : z5, (i2 & 1024) != 0 ? AvailabilityState.AVAILABLE : availabilityState, (i2 & 2048) != 0 ? false : z6, (i2 & 4096) != 0 ? false : z7);
    }

    public boolean getInsetHorizontally() {
        return this.insetHorizontally;
    }

    @Override // com.amazon.music.views.library.models.base.Insettable
    public void setInsetHorizontally(boolean z) {
        this.insetHorizontally = z;
    }

    @Override // com.amazon.music.views.library.models.base.Hideable
    public boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(boolean z) {
        this.hidden = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TableRowItemModel(String str, ArtworkFrameModel artworkFrameModel, int i, ContentMetadata metadata, boolean z, boolean z2, boolean z3, boolean z4, DownloadStateModel downloadStateModel, boolean z5, AvailabilityState availabilityState, boolean z6, boolean z7) {
        super(str, artworkFrameModel, i, metadata, z, z2, z3, z4);
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(availabilityState, "availabilityState");
        this.downloadStateModel = downloadStateModel;
        this.showSDCardIcon = z5;
        this.availabilityState = availabilityState;
        this.insetHorizontally = z6;
        this.hidden = z7;
    }

    public final void updateRowNumber(Integer index) {
        if (Intrinsics.areEqual(getRowNumber(), index)) {
            return;
        }
        setRowNumber(index);
        if (getMetadata() instanceof TrackMetadata) {
            ((TrackMetadata) getMetadata()).setRowNumber(index);
        }
    }

    @Override // com.amazon.music.views.library.models.base.Hideable
    public void updateVisibility(boolean hidden) {
        if (getHidden() == hidden) {
            return;
        }
        setHidden(hidden);
        notifyChanged(5);
    }

    public final void updateDownloadState(DownloadStateModel downloadStateModel) {
        Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
        if (Intrinsics.areEqual(this.downloadStateModel, downloadStateModel)) {
            return;
        }
        this.downloadStateModel = downloadStateModel;
        notifyChanged(12);
    }

    public final void updateSDCardVisibility(boolean show) {
        if (this.showSDCardIcon == show) {
            return;
        }
        this.showSDCardIcon = show;
        notifyChanged(14);
    }

    public final void updatePrimaryText(String updatedText) {
        Intrinsics.checkNotNullParameter(updatedText, "updatedText");
        if (Intrinsics.areEqual(getPrimaryText(), updatedText)) {
            return;
        }
        setPrimaryText(updatedText);
        notifyChanged(16);
    }

    public final void updateSecondaryText(String secondaryText1, String secondaryText2) {
        if (Intrinsics.areEqual(getSecondaryText1(), secondaryText1) && Intrinsics.areEqual(getSecondaryText2(), secondaryText2)) {
            return;
        }
        setSecondaryText1(secondaryText1);
        setSecondaryText2(secondaryText2);
        notifyChanged(16);
    }

    public final void updateAvailabilityState(AvailabilityState availabilityState) {
        Intrinsics.checkNotNullParameter(availabilityState, "availabilityState");
        if (this.availabilityState != availabilityState) {
            this.availabilityState = availabilityState;
            notifyChanged(19);
        }
    }

    @Override // com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public void checkForChanges(BaseViewModel newModelState) {
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
        super.checkForChanges(newModelState);
        if (newModelState instanceof TableRowItemModel) {
            TableRowItemModel tableRowItemModel = (TableRowItemModel) newModelState;
            DownloadStateModel downloadStateModel = tableRowItemModel.downloadStateModel;
            if (downloadStateModel != null) {
                updateDownloadState(downloadStateModel);
            }
            updateRowNumber(tableRowItemModel.getRowNumber());
            updateSDCardVisibility(tableRowItemModel.showSDCardIcon);
            updatePrimaryText(tableRowItemModel.getPrimaryText());
            updateSecondaryText(tableRowItemModel.getSecondaryText1(), tableRowItemModel.getSecondaryText2());
        }
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContentModel
    public void updatePlayState(int newPlayState) {
        if (getDisplayState() != DisplayState.DISABLED) {
            DisplayState displayState = playStateToDisplayStateMapping.get(Integer.valueOf(newPlayState));
            if (displayState == null) {
                displayState = DisplayState.NORMAL;
            }
            setDisplayState(displayState);
        }
        super.updatePlayState(newPlayState);
    }

    @Override // com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            TableRowItemModel tableRowItemModel = (TableRowItemModel) other;
            return Intrinsics.areEqual(this.downloadStateModel, tableRowItemModel.downloadStateModel) && this.showSDCardIcon == tableRowItemModel.showSDCardIcon && Intrinsics.areEqual(getHasLyricsAvailable(), tableRowItemModel.getHasLyricsAvailable()) && Intrinsics.areEqual(getShouldShowBadging(), tableRowItemModel.getShouldShowBadging()) && Intrinsics.areEqual(getStereoBadgeText(), tableRowItemModel.getStereoBadgeText()) && Intrinsics.areEqual(getSpatialBadgeText(), tableRowItemModel.getSpatialBadgeText()) && Intrinsics.areEqual(getHasBadgeError(), tableRowItemModel.getHasBadgeError()) && getDisplayState() == tableRowItemModel.getDisplayState() && Intrinsics.areEqual(getShowExplicitBadge(), tableRowItemModel.getShowExplicitBadge()) && getHidden() == tableRowItemModel.getHidden();
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.TableRowItemModel");
    }

    @Override // com.amazon.music.views.library.models.base.LibraryAddableModel, com.amazon.music.views.library.models.base.BaseViewContentModel, com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        DownloadStateModel downloadStateModel = this.downloadStateModel;
        int iHashCode2 = (((iHashCode + (downloadStateModel == null ? 0 : downloadStateModel.hashCode())) * 31) + Boolean.hashCode(this.showSDCardIcon)) * 31;
        Boolean hasLyricsAvailable = getHasLyricsAvailable();
        int iHashCode3 = (iHashCode2 + (hasLyricsAvailable == null ? 0 : hasLyricsAvailable.hashCode())) * 31;
        Boolean shouldShowBadging = getShouldShowBadging();
        int iHashCode4 = (iHashCode3 + (shouldShowBadging == null ? 0 : shouldShowBadging.hashCode())) * 31;
        String stereoBadgeText = getStereoBadgeText();
        int iHashCode5 = (iHashCode4 + (stereoBadgeText == null ? 0 : stereoBadgeText.hashCode())) * 31;
        String spatialBadgeText = getSpatialBadgeText();
        int iHashCode6 = (iHashCode5 + (spatialBadgeText == null ? 0 : spatialBadgeText.hashCode())) * 31;
        Boolean hasBadgeError = getHasBadgeError();
        int iHashCode7 = (((iHashCode6 + (hasBadgeError == null ? 0 : hasBadgeError.hashCode())) * 31) + getDisplayState().hashCode()) * 31;
        Boolean showExplicitBadge = getShowExplicitBadge();
        return ((iHashCode7 + (showExplicitBadge != null ? showExplicitBadge.hashCode() : 0)) * 31) + Boolean.hashCode(getHidden());
    }
}
