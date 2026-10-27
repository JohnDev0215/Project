package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.EligibilityMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.ContentEligibilityData;
import com.amazon.music.views.library.models.base.Eligible;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\u0012\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b^\u0010)J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000e\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0001H\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u0014\u0010\u0019\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0013\u0010\u001c\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\u0012\u0010\u001f\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0016R\u001c\u0010 \u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010!\u001a\u0004\b%\u0010#R$\u0010&\u001a\u0004\u0018\u00010\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b&\u0010!\u001a\u0004\b'\u0010#\"\u0004\b(\u0010)R\u001c\u0010*\u001a\u0004\u0018\u00010\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001c\u0010.\u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b.\u0010!\u001a\u0004\b/\u0010#R\u001c\u00100\u001a\u0004\u0018\u00010\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u0010+\u001a\u0004\b1\u0010-R\u001c\u00102\u001a\u0004\u0018\u00010\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b2\u0010+\u001a\u0004\b3\u0010-R$\u00104\u001a\u0004\u0018\u00010\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b4\u0010!\u001a\u0004\b5\u0010#\"\u0004\b6\u0010)R\u001c\u00107\u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b7\u0010!\u001a\u0004\b8\u0010#R$\u00109\u001a\u0004\u0018\u00010\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b9\u0010!\u001a\u0004\b:\u0010#\"\u0004\b;\u0010)R\u001a\u0010<\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R\u001c\u0010A\u001a\u0004\u0018\u00010@8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010K\u001a\u0004\u0018\u00010J8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR\u001c\u0010O\u001a\u0004\u0018\u00010\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bO\u0010+\u001a\u0004\bP\u0010-R\"\u0010Q\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u0010=\u001a\u0004\bQ\u0010?\"\u0004\bR\u0010SR\"\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168&@&X¦\u000e¢\u0006\f\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0016\u0010\\\u001a\u0004\u0018\u00010Y8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[¨\u0006_"}, d2 = {"Lcom/amazon/music/views/library/models/PageHeaderModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/Eligible;", "Lcom/amazon/music/views/library/models/base/ContentEligibilityData;", "eligibilityData", "", "updateEligibilityData", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "updateDownloadState", "", "title", "updateTitle", "value", "updateText4", "newModelState", "checkForChanges", "", "isFollowed", "updateFollowState", "getFollowState", "()Ljava/lang/Boolean;", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "actionIconModels", "updateActionIconModels", "", "other", "equals", "", "hashCode", "areItemsTheSame", "labelBadgeText", "Ljava/lang/String;", "getLabelBadgeText", "()Ljava/lang/String;", "labelText", "getLabelText", "headlineTitle", "getHeadlineTitle", "setHeadlineTitle", "(Ljava/lang/String;)V", "headlineTitleId", "Ljava/lang/Integer;", "getHeadlineTitleId", "()Ljava/lang/Integer;", "primaryTitle", "getPrimaryTitle", "primaryTitleViewId", "getPrimaryTitleViewId", "secondaryTextViewId", "getSecondaryTextViewId", "secondaryTitle", "getSecondaryTitle", "setSecondaryTitle", "tertiaryTitle", "getTertiaryTitle", "text4", "getText4", "setText4", "overrideTitleCaps", "Z", "getOverrideTitleCaps", "()Z", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getDownloadStateModel", "()Lcom/amazon/music/views/library/models/DownloadStateModel;", "setDownloadStateModel", "(Lcom/amazon/music/views/library/models/DownloadStateModel;)V", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "fontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getFontStyle", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "badgeBackground", "getBadgeBackground", "isSonicRushEnabled", "setSonicRushEnabled", "(Z)V", "getActionIcons", "()Ljava/util/List;", "setActionIcons", "(Ljava/util/List;)V", "actionIcons", "Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "getEligibilityMetadata", "()Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "eligibilityMetadata", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class PageHeaderModel extends BaseViewModel implements Eligible {
    private final Integer badgeBackground;
    private DownloadStateModel downloadStateModel;
    private final FontStyleKey fontStyle;
    private String headlineTitle;
    private final Integer headlineTitleId;
    private boolean isSonicRushEnabled;
    private final String labelBadgeText;
    private final String labelText;
    private final ContentMetadata metadata;
    private final boolean overrideTitleCaps;
    private final String primaryTitle;
    private final Integer primaryTitleViewId;
    private final Integer secondaryTextViewId;
    private String secondaryTitle;
    private final String tertiaryTitle;
    private String text4;

    public PageHeaderModel() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public abstract List<PageHeaderActionIconModel> getActionIcons();

    public abstract void setActionIcons(List<PageHeaderActionIconModel> list);

    public /* synthetic */ PageHeaderModel(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    public PageHeaderModel(String str) {
        super(str, null, 2, 0 == true ? 1 : 0);
    }

    public String getLabelBadgeText() {
        return this.labelBadgeText;
    }

    public String getLabelText() {
        return this.labelText;
    }

    public String getHeadlineTitle() {
        return this.headlineTitle;
    }

    public void setHeadlineTitle(String str) {
        this.headlineTitle = str;
    }

    public Integer getHeadlineTitleId() {
        return this.headlineTitleId;
    }

    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    public Integer getPrimaryTitleViewId() {
        return this.primaryTitleViewId;
    }

    public Integer getSecondaryTextViewId() {
        return this.secondaryTextViewId;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public void setSecondaryTitle(String str) {
        this.secondaryTitle = str;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }

    public String getText4() {
        return this.text4;
    }

    public void setText4(String str) {
        this.text4 = str;
    }

    public boolean getOverrideTitleCaps() {
        return this.overrideTitleCaps;
    }

    public ContentMetadata getMetadata() {
        return this.metadata;
    }

    public DownloadStateModel getDownloadStateModel() {
        return this.downloadStateModel;
    }

    public void setDownloadStateModel(DownloadStateModel downloadStateModel) {
        this.downloadStateModel = downloadStateModel;
    }

    public FontStyleKey getFontStyle() {
        return this.fontStyle;
    }

    public Integer getBadgeBackground() {
        return this.badgeBackground;
    }

    public final void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    @Override // com.amazon.music.views.library.models.base.Eligible
    public EligibilityMetadata getEligibilityMetadata() {
        if (!(getMetadata() instanceof EligibilityMetadata)) {
            return null;
        }
        ContentMetadata metadata = getMetadata();
        if (metadata != null) {
            return (EligibilityMetadata) metadata;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.EligibilityMetadata");
    }

    @Override // com.amazon.music.views.library.models.base.Eligible
    public void updateEligibilityData(ContentEligibilityData eligibilityData) {
        Integer numUpdateEligibilityData;
        Intrinsics.checkNotNullParameter(eligibilityData, "eligibilityData");
        EligibilityMetadata eligibilityMetadata = getEligibilityMetadata();
        if (eligibilityMetadata == null || (numUpdateEligibilityData = eligibilityMetadata.updateEligibilityData(eligibilityData)) == null) {
            return;
        }
        numUpdateEligibilityData.intValue();
        notifyChanged(numUpdateEligibilityData.intValue());
    }

    public final void updateDownloadState(DownloadStateModel downloadStateModel) {
        Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
        if (Intrinsics.areEqual(downloadStateModel, getDownloadStateModel())) {
            return;
        }
        setDownloadStateModel(downloadStateModel);
        notifyChanged(12);
    }

    public final void updateTitle(String title) {
        if (Intrinsics.areEqual(title, getHeadlineTitle())) {
            return;
        }
        setHeadlineTitle(title);
        notifyChanged(16);
    }

    public final void updateText4(String value) {
        if (Intrinsics.areEqual(value, getText4())) {
            return;
        }
        setText4(value);
        notifyChanged(16);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public void checkForChanges(BaseViewModel newModelState) {
        ContentMetadata metadata;
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
        super.checkForChanges(newModelState);
        PageHeaderModel pageHeaderModel = newModelState instanceof PageHeaderModel ? (PageHeaderModel) newModelState : null;
        if (pageHeaderModel == null) {
            return;
        }
        DownloadStateModel downloadStateModel = pageHeaderModel.getDownloadStateModel();
        if (downloadStateModel != null) {
            updateDownloadState(downloadStateModel);
        }
        updateTitle(((PageHeaderModel) newModelState).getHeadlineTitle());
        Boolean followState = getFollowState();
        if (followState != null) {
            updateFollowState(followState.booleanValue());
        }
        if (!(getMetadata() instanceof UpdatableContentMetadata) || (metadata = pageHeaderModel.getMetadata()) == null) {
            return;
        }
        ContentMetadata metadata2 = getMetadata();
        if (metadata2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.UpdatableContentMetadata");
        }
        ((UpdatableContentMetadata) metadata2).update(metadata);
    }

    public void updateFollowState(boolean isFollowed) {
        ContentMetadata metadata = getMetadata();
        if (metadata == null) {
            return;
        }
        if (metadata instanceof PlaylistMetadata) {
            ((PlaylistMetadata) metadata).setFollowed(Boolean.valueOf(isFollowed));
            notifyChanged(2);
        } else if (metadata instanceof AlbumMetadata) {
            ((AlbumMetadata) metadata).setFollowed(Boolean.valueOf(isFollowed));
            notifyChanged(2);
        }
    }

    public final Boolean getFollowState() {
        Boolean isFollowed;
        ContentMetadata metadata = getMetadata();
        if (metadata == null) {
            return null;
        }
        if (metadata instanceof PlaylistMetadata) {
            isFollowed = ((PlaylistMetadata) metadata).getIsFollowed();
        } else {
            if (!(metadata instanceof AlbumMetadata)) {
                return null;
            }
            isFollowed = ((AlbumMetadata) metadata).getIsFollowed();
        }
        return isFollowed;
    }

    public final void updateActionIconModels(List<PageHeaderActionIconModel> actionIconModels) {
        Intrinsics.checkNotNullParameter(actionIconModels, "actionIconModels");
        setActionIcons(actionIconModels);
        notifyChanged(18);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            PageHeaderModel pageHeaderModel = (PageHeaderModel) other;
            return Intrinsics.areEqual(getLabelText(), pageHeaderModel.getLabelText()) && Intrinsics.areEqual(getHeadlineTitle(), pageHeaderModel.getHeadlineTitle()) && Intrinsics.areEqual(getPrimaryTitle(), pageHeaderModel.getPrimaryTitle()) && Intrinsics.areEqual(getPrimaryTitleViewId(), pageHeaderModel.getPrimaryTitleViewId()) && Intrinsics.areEqual(getSecondaryTitle(), pageHeaderModel.getSecondaryTitle()) && Intrinsics.areEqual(getTertiaryTitle(), pageHeaderModel.getTertiaryTitle()) && getOverrideTitleCaps() == pageHeaderModel.getOverrideTitleCaps() && Intrinsics.areEqual(getMetadata(), pageHeaderModel.getMetadata()) && Intrinsics.areEqual(getDownloadStateModel(), pageHeaderModel.getDownloadStateModel()) && Intrinsics.areEqual(getActionIcons(), pageHeaderModel.getActionIcons());
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.PageHeaderModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        ContentMetadata metadata = getMetadata();
        return iHashCode + (metadata == null ? 0 : metadata.hashCode());
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean areItemsTheSame(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            PageHeaderModel pageHeaderModel = (PageHeaderModel) other;
            if (getMetadata() instanceof UpdatableContentMetadata) {
                ContentMetadata metadata = getMetadata();
                UpdatableContentMetadata updatableContentMetadata = metadata instanceof UpdatableContentMetadata ? (UpdatableContentMetadata) metadata : null;
                return updatableContentMetadata != null && updatableContentMetadata.areItemsTheSame(pageHeaderModel.getMetadata());
            }
            String headlineTitle = getHeadlineTitle();
            return !(headlineTitle == null || headlineTitle.length() == 0) && Intrinsics.areEqual(getHeadlineTitle(), pageHeaderModel.getHeadlineTitle());
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.PageHeaderModel");
    }
}
