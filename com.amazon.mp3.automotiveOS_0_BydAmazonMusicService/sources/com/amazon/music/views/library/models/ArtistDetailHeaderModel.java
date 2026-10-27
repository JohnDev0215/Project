package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistDetailHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B¡\u0001\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010$\u001a\u00020\u0002\u0012\u0006\u0010(\u001a\u00020'\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,\u0012\u0010\b\u0002\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u00010,¢\u0006\u0004\b9\u0010:J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u0011R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u000b\u001a\u0004\b\u001f\u0010\rR\u0019\u0010 \u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u0019\u0010\"\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\"\u0010\u000b\u001a\u0004\b#\u0010\rR\"\u0010$\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010\u0013\u001a\u0004\b$\u0010\u0015\"\u0004\b%\u0010&R\u001a\u0010(\u001a\u00020'8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R(\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R*\u00105\u001a\n\u0012\u0004\u0012\u000204\u0018\u00010,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u0010/\u001a\u0004\b6\u00101\"\u0004\b7\u00103¨\u0006;"}, d2 = {"Lcom/amazon/music/views/library/models/ArtistDetailHeaderModel;", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "", "isFollowed", "", "updateFollowState", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newModelState", "checkForChanges", "", "labelText", "Ljava/lang/String;", "getLabelText", "()Ljava/lang/String;", "headlineTitle", "getHeadlineTitle", "setHeadlineTitle", "(Ljava/lang/String;)V", "overrideTitleCaps", "Z", "getOverrideTitleCaps", "()Z", "secondaryTitle", "getSecondaryTitle", "setSecondaryTitle", "", "secondaryTextViewId", "Ljava/lang/Integer;", "getSecondaryTextViewId", "()Ljava/lang/Integer;", "heroImageUrl", "getHeroImageUrl", "imageUrl", "getImageUrl", "name", "getName", "isFollowing", "setFollowing", "(Z)V", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "actionIcons", "Ljava/util/List;", "getActionIcons", "()Ljava/util/List;", "setActionIcons", "(Ljava/util/List;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "getMetadataList", "setMetadataList", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/views/library/metadata/ArtistMetadata;Ljava/util/List;Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ArtistDetailHeaderModel extends PageHeaderModel {
    private List<PageHeaderActionIconModel> actionIcons;
    private String headlineTitle;
    private final String heroImageUrl;
    private final String imageUrl;
    private boolean isFollowing;
    private final String labelText;
    private final ArtistMetadata metadata;
    private List<? extends ContentMetadata> metadataList;
    private final String name;
    private final boolean overrideTitleCaps;
    private final Integer secondaryTextViewId;
    private String secondaryTitle;

    public /* synthetic */ ArtistDetailHeaderModel(String str, String str2, String str3, boolean z, String str4, Integer num, String str5, String str6, String str7, boolean z2, ArtistMetadata artistMetadata, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, z2, artistMetadata, list, (i & 4096) != 0 ? null : list2);
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getLabelText() {
        return this.labelText;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getHeadlineTitle() {
        return this.headlineTitle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setHeadlineTitle(String str) {
        this.headlineTitle = str;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public boolean getOverrideTitleCaps() {
        return this.overrideTitleCaps;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setSecondaryTitle(String str) {
        this.secondaryTitle = str;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public Integer getSecondaryTextViewId() {
        return this.secondaryTextViewId;
    }

    public final String getHeroImageUrl() {
        return this.heroImageUrl;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: isFollowing, reason: from getter */
    public final boolean getIsFollowing() {
        return this.isFollowing;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public ArtistMetadata getMetadata() {
        return this.metadata;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public List<PageHeaderActionIconModel> getActionIcons() {
        return this.actionIcons;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void setActionIcons(List<PageHeaderActionIconModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.actionIcons = list;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArtistDetailHeaderModel(String str, String str2, String str3, boolean z, String str4, Integer num, String str5, String str6, String str7, boolean z2, ArtistMetadata metadata, List<PageHeaderActionIconModel> actionIcons, List<? extends ContentMetadata> list) {
        super(str);
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(actionIcons, "actionIcons");
        this.labelText = str2;
        this.headlineTitle = str3;
        this.overrideTitleCaps = z;
        this.secondaryTitle = str4;
        this.secondaryTextViewId = num;
        this.heroImageUrl = str5;
        this.imageUrl = str6;
        this.name = str7;
        this.isFollowing = z2;
        this.metadata = metadata;
        this.actionIcons = actionIcons;
        this.metadataList = list;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }

    public final void setMetadataList(List<? extends ContentMetadata> list) {
        this.metadataList = list;
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel
    public void updateFollowState(boolean isFollowed) {
        if (this.isFollowing != isFollowed) {
            this.isFollowing = isFollowed;
            notifyChanged(2);
        }
    }

    @Override // com.amazon.music.views.library.models.PageHeaderModel, com.amazon.music.views.library.models.base.BaseViewModel
    public void checkForChanges(BaseViewModel newModelState) {
        Intrinsics.checkNotNullParameter(newModelState, "newModelState");
        super.checkForChanges(newModelState);
        ArtistDetailHeaderModel artistDetailHeaderModel = newModelState instanceof ArtistDetailHeaderModel ? (ArtistDetailHeaderModel) newModelState : null;
        if (artistDetailHeaderModel == null) {
            return;
        }
        updateFollowState(artistDetailHeaderModel.getIsFollowing());
    }
}
