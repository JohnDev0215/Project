package com.amazon.music.views.library.models;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleHorizontalTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\b\b\u0002\u0010!\u001a\u00020 \u0012\b\b\u0002\u0010\"\u001a\u00020 \u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010#\u001a\u00020\t\u0012\u0006\u0010$\u001a\u00020\u0018\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\u0012\b\b\u0002\u0010%\u001a\u00020 ¢\u0006\u0004\b&\u0010'R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001f\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006("}, d2 = {"Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "subTitle", "getSubTitle", "", "ordinal", "Ljava/lang/Integer;", "getOrdinal", "()Ljava/lang/Integer;", "setOrdinal", "(Ljava/lang/Integer;)V", "Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "horizontalTileType", "Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "getHorizontalTileType", "()Lcom/amazon/music/views/library/models/enums/HorizontalTileType;", "setHorizontalTileType", "(Lcom/amazon/music/views/library/models/enums/HorizontalTileType;)V", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "Ljava/util/List;", "getMetadataList", "()Ljava/util/List;", "viewId", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "artworkFrameModel", "", "enableAdd", "isInLibrary", "contentType", "metadata", "isSuggestion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/views/library/models/ArtworkFrameModel;ZZLcom/amazon/music/views/library/models/enums/HorizontalTileType;ILcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/util/List;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class SimpleHorizontalTileModel extends LibraryAddableModel {
    private HorizontalTileType horizontalTileType;
    private final List<ContentMetadata> metadataList;
    private Integer ordinal;
    private final String subTitle;
    private final String title;

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final Integer getOrdinal() {
        return this.ordinal;
    }

    public final void setOrdinal(Integer num) {
        this.ordinal = num;
    }

    public /* synthetic */ SimpleHorizontalTileModel(String str, String str2, String str3, Integer num, ArtworkFrameModel artworkFrameModel, boolean z, boolean z2, HorizontalTileType horizontalTileType, int i, ContentMetadata contentMetadata, List list, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : num, artworkFrameModel, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? false : z2, (i2 & 128) != 0 ? HorizontalTileType.REGULAR : horizontalTileType, i, contentMetadata, (i2 & 1024) != 0 ? null : list, (i2 & 2048) != 0 ? false : z3);
    }

    public final HorizontalTileType getHorizontalTileType() {
        return this.horizontalTileType;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SimpleHorizontalTileModel(String str, String str2, String str3, Integer num, ArtworkFrameModel artworkFrameModel, boolean z, boolean z2, HorizontalTileType horizontalTileType, int i, ContentMetadata metadata, List<? extends ContentMetadata> list, boolean z3) {
        super(str, artworkFrameModel, i, metadata, z2, z3, z, false, 128, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(horizontalTileType, "horizontalTileType");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.title = str2;
        this.subTitle = str3;
        this.ordinal = num;
        this.horizontalTileType = horizontalTileType;
        this.metadataList = list;
    }
}
