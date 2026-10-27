package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowTileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001Bi\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/models/FollowTileModel;", "Lcom/amazon/music/views/library/models/VerticalTileModel;", "viewId", "", "title", "subTitle", ContextMappingConstants.TERTIARY_TEXT, "artworkFrameModel", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", ContextMappingConstants.CONTENT_ENCODING, "", "contentType", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isCircle", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/models/ArtworkFrameModel;Ljava/util/List;ILcom/amazon/music/views/library/metadata/ContentMetadata;Z)V", "updateFollowState", "", "isFollowing", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FollowTileModel extends VerticalTileModel {
    public /* synthetic */ FollowTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List list, int i, ContentMetadata contentMetadata, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, artworkFrameModel, (i2 & 32) != 0 ? null : list, i, contentMetadata, (i2 & 256) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FollowTileModel(String str, String str2, String str3, String str4, ArtworkFrameModel artworkFrameModel, List<String> list, int i, ContentMetadata metadata, boolean z) {
        super(str, str2, str3, str4, artworkFrameModel, list, i, metadata, z, null, null, null, null, null, null, null, null, null, null, null, null, 2096640, null);
        Intrinsics.checkNotNullParameter(artworkFrameModel, "artworkFrameModel");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    public final void updateFollowState(boolean isFollowing) {
        if (!(getMetadata() instanceof ArtistMetadata) || ((ArtistMetadata) getMetadata()).getIsFollowing() == isFollowing) {
            return;
        }
        ((ArtistMetadata) getMetadata()).setFollowing(isFollowing);
        notifyChanged(2);
    }
}
