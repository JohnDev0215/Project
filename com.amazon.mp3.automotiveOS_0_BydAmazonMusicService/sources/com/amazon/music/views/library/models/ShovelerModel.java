package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedModel;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShovelerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003BÃ\u0001\b\u0007\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\b\u0012\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020*\u0012\u0006\u0010>\u001a\u00020=\u0012\u0010\b\u0002\u0010?\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010*\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010*\u0012\b\b\u0002\u00101\u001a\u00020\u0017\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b@\u0010AJ\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R$\u0010\t\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010$\u001a\u0004\u0018\u00010#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R*\u0010+\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010*8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u00101\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010\u0019\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u00104R$\u00105\u001a\u0004\u0018\u00010\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006B"}, d2 = {"Lcom/amazon/music/views/library/models/ShovelerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/base/HotspotSupportedModel;", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "hotspotModel", "", "setHotspotModel", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "moreText", "getMoreText", "nextPageToken", "getNextPageToken", "moreTarget", "getMoreTarget", "", "showChildrenSubtitle", "Z", "getShowChildrenSubtitle", "()Z", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getImage", "()Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "setImage", "(Lcom/amazon/music/views/library/models/ArtworkFrameModel;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "containerMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContainerMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "setContainerMetadata", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)V", "", "stringsToHighlight", "Ljava/util/List;", "getStringsToHighlight", "()Ljava/util/List;", "setStringsToHighlight", "(Ljava/util/List;)V", "shouldDisplayEmptyTextButton", "getShouldDisplayEmptyTextButton", "setShouldDisplayEmptyTextButton", "(Z)V", "hotspotTooltipModel", "Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "getHotspotTooltipModel", "()Lcom/amazon/music/views/library/models/HotspotTooltipModel;", "setHotspotTooltipModel", "(Lcom/amazon/music/views/library/models/HotspotTooltipModel;)V", "viewId", "models", "", "tileType", "tags", "<init>", "(Ljava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/views/library/models/ArtworkFrameModel;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;ZLcom/amazon/music/views/library/models/HotspotTooltipModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ShovelerModel extends BaseViewContainerModel<BaseViewModel> implements HotspotSupportedModel {
    private ContentMetadata containerMetadata;
    private HotspotTooltipModel hotspotTooltipModel;
    private ArtworkFrameModel image;
    private final String moreTarget;
    private final String moreText;
    private final String nextPageToken;
    private boolean shouldDisplayEmptyTextButton;
    private final boolean showChildrenSubtitle;
    private List<String> stringsToHighlight;
    private final String subtitle;
    private String title;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ShovelerModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this(str, models, i, list, str2, str3, str4, str5, str6, z, null, null, null, false, null, 31744, null);
        Intrinsics.checkNotNullParameter(models, "models");
    }

    public /* synthetic */ ShovelerModel(String str, List list, int i, List list2, String str2, String str3, String str4, String str5, String str6, boolean z, ArtworkFrameModel artworkFrameModel, ContentMetadata contentMetadata, List list3, boolean z2, HotspotTooltipModel hotspotTooltipModel, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, list, i, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? null : artworkFrameModel, (i2 & 2048) != 0 ? null : contentMetadata, (i2 & 4096) != 0 ? null : list3, (i2 & 8192) != 0 ? false : z2, (i2 & 16384) != 0 ? null : hotspotTooltipModel);
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

    public final String getNextPageToken() {
        return this.nextPageToken;
    }

    public final String getMoreTarget() {
        return this.moreTarget;
    }

    public final boolean getShowChildrenSubtitle() {
        return this.showChildrenSubtitle;
    }

    public final ArtworkFrameModel getImage() {
        return this.image;
    }

    public final ContentMetadata getContainerMetadata() {
        return this.containerMetadata;
    }

    public final List<String> getStringsToHighlight() {
        return this.stringsToHighlight;
    }

    public final boolean getShouldDisplayEmptyTextButton() {
        return this.shouldDisplayEmptyTextButton;
    }

    public HotspotTooltipModel getHotspotTooltipModel() {
        return this.hotspotTooltipModel;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedModel
    public void setHotspotTooltipModel(HotspotTooltipModel hotspotTooltipModel) {
        this.hotspotTooltipModel = hotspotTooltipModel;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ShovelerModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5, String str6, boolean z, ArtworkFrameModel artworkFrameModel, ContentMetadata contentMetadata, List<String> list2, boolean z2, HotspotTooltipModel hotspotTooltipModel) {
        super(str, models, i, list);
        Intrinsics.checkNotNullParameter(models, "models");
        this.title = str2;
        this.subtitle = str3;
        this.moreText = str4;
        this.nextPageToken = str5;
        this.moreTarget = str6;
        this.showChildrenSubtitle = z;
        this.image = artworkFrameModel;
        this.containerMetadata = contentMetadata;
        this.stringsToHighlight = list2;
        this.shouldDisplayEmptyTextButton = z2;
        this.hotspotTooltipModel = hotspotTooltipModel;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedModel
    public void setHotspotModel(HotspotTooltipModel hotspotModel) {
        Intrinsics.checkNotNullParameter(hotspotModel, "hotspotModel");
        setHotspotTooltipModel(hotspotModel);
    }
}
