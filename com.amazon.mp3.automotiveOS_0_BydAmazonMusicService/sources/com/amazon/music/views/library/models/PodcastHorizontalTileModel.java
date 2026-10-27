package com.amazon.music.views.library.models;

import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: PodcastHorizontalTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/models/PodcastHorizontalTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "subTitle", "getSubTitle", "imageUrl", "getImageUrl", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "playMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "getPlayMetadata", "()Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "", "number", "Ljava/lang/Integer;", "getNumber", "()Ljava/lang/Integer;", "", "showPlayOverlay", "Z", "getShowPlayOverlay", "()Z", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class PodcastHorizontalTileModel extends BaseViewContentModel {
    private final String imageUrl;
    private final Integer number;
    private final NavigationMetadata playMetadata;
    private final boolean showPlayOverlay;
    private final String subTitle;
    private final String title;

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final NavigationMetadata getPlayMetadata() {
        return this.playMetadata;
    }

    public final Integer getNumber() {
        return this.number;
    }

    public final boolean getShowPlayOverlay() {
        return this.showPlayOverlay;
    }
}
