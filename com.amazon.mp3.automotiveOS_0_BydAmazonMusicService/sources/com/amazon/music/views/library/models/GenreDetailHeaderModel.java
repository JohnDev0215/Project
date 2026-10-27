package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenreDetailHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001Bi\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/models/GenreDetailHeaderModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "primaryTitle", "Ljava/lang/String;", "getPrimaryTitle", "()Ljava/lang/String;", "secondaryTitle", "getSecondaryTitle", "imageUrl", "getImageUrl", "id", "getId", "Lcom/amazon/music/views/library/metadata/GenreMetadata;", "genreMetadata", "Lcom/amazon/music/views/library/metadata/GenreMetadata;", "getGenreMetadata", "()Lcom/amazon/music/views/library/metadata/GenreMetadata;", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "stationMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "getStationMetadata", "()Lcom/amazon/music/views/library/metadata/StationMetadata;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "contentType", "playState", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/metadata/GenreMetadata;Lcom/amazon/music/views/library/metadata/StationMetadata;ILjava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class GenreDetailHeaderModel extends BaseViewContentModel {
    private final GenreMetadata genreMetadata;
    private final String id;
    private final String imageUrl;
    private final String primaryTitle;
    private final String secondaryTitle;
    private final StationMetadata stationMetadata;

    public final String getPrimaryTitle() {
        return this.primaryTitle;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final GenreMetadata getGenreMetadata() {
        return this.genreMetadata;
    }

    public final StationMetadata getStationMetadata() {
        return this.stationMetadata;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenreDetailHeaderModel(String str, String str2, String str3, String str4, String str5, GenreMetadata genreMetadata, StationMetadata stationMetadata, int i, Integer num) {
        super(str, stationMetadata != null ? stationMetadata : genreMetadata, i, null, num);
        Intrinsics.checkNotNullParameter(genreMetadata, "genreMetadata");
        this.primaryTitle = str2;
        this.secondaryTitle = str3;
        this.imageUrl = str4;
        this.id = str5;
        this.genreMetadata = genreMetadata;
        this.stationMetadata = stationMetadata;
    }
}
