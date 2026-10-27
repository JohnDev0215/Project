package com.amazon.music.views.library.models;

import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LivestreamBarkerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001Bo\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0006R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/views/library/models/LivestreamBarkerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "imageUrl", "getImageUrl", "primaryTitle", "getPrimaryTitle", "secondaryTitle", "getSecondaryTitle", "tertiaryTitle", "getTertiaryTitle", "viewerCount", "getViewerCount", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "artistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "getArtistMetadata", "()Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "sourceLogoUrl", "getSourceLogoUrl", "", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/String;Lcom/amazon/music/views/library/metadata/ArtistMetadata;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class LivestreamBarkerModel extends BaseViewContentModel {
    private final ArtistMetadata artistMetadata;
    private final String imageUrl;
    private final String primaryTitle;
    private final String secondaryTitle;
    private final String sourceLogoUrl;
    private final String tertiaryTitle;
    private final String viewId;
    private final String viewerCount;

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getPrimaryTitle() {
        return this.primaryTitle;
    }

    public final String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public final String getTertiaryTitle() {
        return this.tertiaryTitle;
    }

    public final String getViewerCount() {
        return this.viewerCount;
    }

    public final ArtistMetadata getArtistMetadata() {
        return this.artistMetadata;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LivestreamBarkerModel(String str, String imageUrl, String primaryTitle, String str2, String str3, int i, ContentMetadata metadata, String str4, ArtistMetadata artistMetadata, String str5) {
        super(str, metadata, i, null, null);
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(primaryTitle, "primaryTitle");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.viewId = str;
        this.imageUrl = imageUrl;
        this.primaryTitle = primaryTitle;
        this.secondaryTitle = str2;
        this.tertiaryTitle = str3;
        this.viewerCount = str4;
        this.artistMetadata = artistMetadata;
        this.sourceLogoUrl = str5;
    }

    public final String getSourceLogoUrl() {
        return this.sourceLogoUrl;
    }
}
