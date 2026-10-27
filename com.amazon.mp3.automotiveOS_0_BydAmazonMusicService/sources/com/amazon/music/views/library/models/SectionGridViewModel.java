package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.util.Constants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SectionGridViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003Bã\u0001\b\u0007\u0012\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\b\u0012\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00020E\u0012\u0006\u0010G\u001a\u00020!\u0012\u0010\b\u0002\u0010H\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010E\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\b\b\u0002\u0010(\u001a\u00020\u001a\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\u0018\b\u0002\u00108\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u000107\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\bI\u0010JJ\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\fR\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\"\u001a\u0004\u0018\u00010!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0017\u0010(\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b(\u0010\u001c\u001a\u0004\b(\u0010\u001eR$\u0010*\u001a\u0004\u0018\u00010)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R2\u00108\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u0001078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R$\u0010>\u001a\u0004\u0018\u00010\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C¨\u0006K"}, d2 = {"Lcom/amazon/music/views/library/models/SectionGridViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/HotspotSupportedModel;", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "hotspotModel", "", "setHotspotModel", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "moreText", "getMoreText", "moreTarget", "getMoreTarget", "Lcom/amazon/music/views/library/models/enums/TargetType;", "buttonTargetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "getButtonTargetType", "()Lcom/amazon/music/views/library/models/enums/TargetType;", "nextPageToken", "getNextPageToken", "", "shouldShowDividerLine", "Z", "getShouldShowDividerLine", "()Z", "setShouldShowDividerLine", "(Z)V", "", "overrideDisplayCount", "Ljava/lang/Integer;", "getOverrideDisplayCount", "()Ljava/lang/Integer;", "setOverrideDisplayCount", "(Ljava/lang/Integer;)V", Constants.ENABLE_DISABLE, "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "containerImage", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getContainerImage", "()Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "setContainerImage", "(Lcom/amazon/music/views/library/models/ArtworkFrameModel;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "containerMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContainerMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "setContainerMetadata", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)V", "", ContextMappingConstants.CONTENT_INFO, "Ljava/util/Map;", "getContentInfo", "()Ljava/util/Map;", "setContentInfo", "(Ljava/util/Map;)V", "hotspotTooltipModel", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "getHotspotTooltipModel", "()Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "setHotspotTooltipModel", "(Lcom/amazon/music/views/library/models/HotspotTooltipModel;)V", "viewId", "", "models", "tileType", "tags", "<init>", "(Ljava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/enums/TargetType;Ljava/lang/String;ZLjava/lang/Integer;ZLcom/amazon/music/views/library/models/ArtworkFrameModel;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/Map;Lcom/amazon/music/views/library/models/HotspotTooltipModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class SectionGridViewModel extends BaseViewContainerModel<BaseViewModel> implements HotspotSupportedModel {
    private final TargetType buttonTargetType;
    private ArtworkFrameModel containerImage;
    private ContentMetadata containerMetadata;
    private Map<String, String> contentInfo;
    private HotspotTooltipModel hotspotTooltipModel;
    private final boolean isEnabled;
    private final String moreTarget;
    private final String moreText;
    private final String nextPageToken;
    private Integer overrideDisplayCount;
    private boolean shouldShowDividerLine;
    private final String subtitle;
    private final String title;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SectionGridViewModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5, TargetType targetType) {
        this(str, models, i, list, str2, str3, str4, str5, targetType, null, false, null, false, null, null, null, null, 130560, null);
        Intrinsics.checkNotNullParameter(models, "models");
    }

    public /* synthetic */ SectionGridViewModel(String str, List list, int i, List list2, String str2, String str3, String str4, String str5, TargetType targetType, String str6, boolean z, Integer num, boolean z2, ArtworkFrameModel artworkFrameModel, ContentMetadata contentMetadata, Map map, HotspotTooltipModel hotspotTooltipModel, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, list, i, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : targetType, (i2 & 512) != 0 ? null : str6, (i2 & 1024) != 0 ? true : z, (i2 & 2048) != 0 ? null : num, (i2 & 4096) != 0 ? true : z2, (i2 & 8192) != 0 ? null : artworkFrameModel, (i2 & 16384) != 0 ? null : contentMetadata, (32768 & i2) != 0 ? null : map, (i2 & 65536) != 0 ? null : hotspotTooltipModel);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getMoreText() {
        return this.moreText;
    }

    public final String getMoreTarget() {
        return this.moreTarget;
    }

    public final TargetType getButtonTargetType() {
        return this.buttonTargetType;
    }

    public final String getNextPageToken() {
        return this.nextPageToken;
    }

    public final boolean getShouldShowDividerLine() {
        return this.shouldShowDividerLine;
    }

    public final void setShouldShowDividerLine(boolean z) {
        this.shouldShowDividerLine = z;
    }

    public final Integer getOverrideDisplayCount() {
        return this.overrideDisplayCount;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final ArtworkFrameModel getContainerImage() {
        return this.containerImage;
    }

    public final ContentMetadata getContainerMetadata() {
        return this.containerMetadata;
    }

    public final Map<String, String> getContentInfo() {
        return this.contentInfo;
    }

    public final void setContentInfo(Map<String, String> map) {
        this.contentInfo = map;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedModel
    public void setHotspotTooltipModel(HotspotTooltipModel hotspotTooltipModel) {
        this.hotspotTooltipModel = hotspotTooltipModel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SectionGridViewModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5, TargetType targetType, String str6, boolean z, Integer num, boolean z2, ArtworkFrameModel artworkFrameModel, ContentMetadata contentMetadata, Map<String, String> map, HotspotTooltipModel hotspotTooltipModel) {
        super(str, models, i, list);
        Intrinsics.checkNotNullParameter(models, "models");
        this.title = str2;
        this.subtitle = str3;
        this.moreText = str4;
        this.moreTarget = str5;
        this.buttonTargetType = targetType;
        this.nextPageToken = str6;
        this.shouldShowDividerLine = z;
        this.overrideDisplayCount = num;
        this.isEnabled = z2;
        this.containerImage = artworkFrameModel;
        this.containerMetadata = contentMetadata;
        this.contentInfo = map;
        this.hotspotTooltipModel = hotspotTooltipModel;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedModel
    public void setHotspotModel(HotspotTooltipModel hotspotModel) {
        Intrinsics.checkNotNullParameter(hotspotModel, "hotspotModel");
        setHotspotTooltipModel(hotspotModel);
    }
}
