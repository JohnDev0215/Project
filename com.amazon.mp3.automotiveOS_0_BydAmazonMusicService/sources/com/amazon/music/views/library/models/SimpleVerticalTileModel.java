package com.amazon.music.views.library.models;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleVerticalTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001BI\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f¢\u0006\u0002\u0010\rR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/models/SimpleVerticalTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "viewId", "", "title", "artworkFrameModel", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "contentType", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;ILcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;)V", "getMetadataList", "()Ljava/util/List;", "getTitle", "()Ljava/lang/String;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SimpleVerticalTileModel extends BaseViewContentModel {
    private final List<ContentMetadata> metadataList;
    private final String title;

    public /* synthetic */ SimpleVerticalTileModel(String str, String str2, ArtworkFrameModel artworkFrameModel, int i, ContentMetadata contentMetadata, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, artworkFrameModel, i, contentMetadata, (i2 & 32) != 0 ? null : list);
    }

    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public SimpleVerticalTileModel(String str, String str2, ArtworkFrameModel artworkFrameModel, int i, ContentMetadata metadata, List<? extends ContentMetadata> list) {
        super(str, metadata, i, artworkFrameModel, null, 16, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.title = str2;
        this.metadataList = list;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }
}
