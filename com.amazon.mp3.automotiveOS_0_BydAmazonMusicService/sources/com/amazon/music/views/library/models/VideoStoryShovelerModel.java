package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryShovelerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u00ad\u0001\b\u0007\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00020%\u0012\u0006\u0010/\u001a\u00020.\u0012\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010%\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010%¢\u0006\u0004\b1\u00102R$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\u0007R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\u0005\u001a\u0004\b\r\u0010\u0007R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0005\u001a\u0004\b\u000f\u0010\u0007R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0005\u001a\u0004\b\u0011\u0010\u0007R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R*\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u00063"}, d2 = {"Lcom/amazon/music/views/library/models/VideoStoryShovelerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "moreText", "getMoreText", "nextPageToken", "getNextPageToken", "moreTarget", "getMoreTarget", "", "showChildrenSubtitle", "Z", "getShowChildrenSubtitle", "()Z", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "getImage", "()Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "setImage", "(Lcom/amazon/music/views/library/models/ArtworkFrameModel;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "containerMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContainerMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "setContainerMetadata", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)V", "", "stringsToHighlight", "Ljava/util/List;", "getStringsToHighlight", "()Ljava/util/List;", "setStringsToHighlight", "(Ljava/util/List;)V", "viewId", "models", "", "tileType", "tags", "<init>", "(Ljava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/views/library/models/ArtworkFrameModel;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryShovelerModel extends BaseViewContainerModel<BaseViewModel> {
    private ContentMetadata containerMetadata;
    private ArtworkFrameModel image;
    private final String moreTarget;
    private final String moreText;
    private final String nextPageToken;
    private final boolean showChildrenSubtitle;
    private List<String> stringsToHighlight;
    private final String subtitle;
    private String title;

    public /* synthetic */ VideoStoryShovelerModel(String str, List list, int i, List list2, String str2, String str3, String str4, String str5, String str6, boolean z, ArtworkFrameModel artworkFrameModel, ContentMetadata contentMetadata, List list3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, list, i, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6, (i2 & 512) != 0 ? true : z, (i2 & 1024) != 0 ? null : artworkFrameModel, (i2 & 2048) != 0 ? null : contentMetadata, (i2 & 4096) != 0 ? null : list3);
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VideoStoryShovelerModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5, String str6, boolean z, ArtworkFrameModel artworkFrameModel, ContentMetadata contentMetadata, List<String> list2) {
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
    }

    public final List<String> getStringsToHighlight() {
        return this.stringsToHighlight;
    }
}
