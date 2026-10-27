package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u0002BÁ\u0001\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0007\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"\u0012\b\b\u0002\u0010+\u001a\u00020*\u0012\u0010\b\u0002\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0013\u0012\u0010\b\u0002\u00101\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010\u0013\u0012\b\b\u0002\u00105\u001a\u000204\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u000104\u0012\u0010\b\u0002\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013¢\u0006\u0004\bA\u0010BJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u00020\f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R*\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00138\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001a\u0010\t\u001a\u0004\b\u001b\u0010\u000bR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001c\u0010\t\u001a\u0004\b\u001d\u0010\u000bR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001f\u0010\u000bR\u0019\u0010 \u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b \u0010\t\u001a\u0004\b!\u0010\u000bR(\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0017\u0010+\u001a\u00020*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b+\u0010-R\u001f\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b.\u0010\u0015\u001a\u0004\b/\u0010\u0017R*\u00101\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010\u0015\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\"\u00105\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0019\u0010;\u001a\u0004\u0018\u0001048\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u001f\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b?\u0010\u0015\u001a\u0004\b@\u0010\u0017¨\u0006C"}, d2 = {"Lcom/amazon/music/views/library/metadata/VideoStoryMetadata;", "Lcom/amazon/music/views/library/metadata/UpdatableContentMetadata;", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "update", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "asin", "getAsin", "title", "getTitle", "blockRef", "getBlockRef", "imageUrl", "getImageUrl", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", "", BrushV2Converter.KEY_IS_EXPLICIT, "Z", "()Z", "storiesMetadata", "getStoriesMetadata", "Lcom/amazon/music/models/AssetQuality;", "assetQuality", "getAssetQuality", "setAssetQuality", "", "duration", "I", "getDuration", "()I", "setDuration", "(I)V", "trackCount", "Ljava/lang/Integer;", "getTrackCount", "()Ljava/lang/Integer;", "contentEncodings", "getContentEncodings", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;ZLjava/util/List;Ljava/util/List;ILjava/lang/Integer;Ljava/util/List;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryMetadata implements UpdatableContentMetadata, Refinable {
    private final String asin;
    private List<AssetQuality> assetQuality;
    private final String blockRef;
    private final List<String> contentEncodings;
    private int duration;
    private final String imageUrl;
    private final boolean isExplicit;
    private final List<VideoStoryMetadata> storiesMetadata;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private final Integer trackCount;
    private Uri uri;
    private final String viewId;

    public VideoStoryMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, String str5, Set<? extends ContentAccessType> supportedTiers, boolean z, List<VideoStoryMetadata> list2, List<AssetQuality> list3, int i, Integer num, List<String> list4) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(supportedTiers, "supportedTiers");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.asin = str2;
        this.title = str3;
        this.blockRef = str4;
        this.imageUrl = str5;
        this.supportedTiers = supportedTiers;
        this.isExplicit = z;
        this.storiesMetadata = list2;
        this.assetQuality = list3;
        this.duration = i;
        this.trackCount = num;
        this.contentEncodings = list4;
    }

    public /* synthetic */ VideoStoryMetadata(String str, Uri uri, List list, String str2, String str3, String str4, String str5, Set set, boolean z, List list2, List list3, int i, Integer num, List list4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, uri, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : str5, set, (i2 & 256) != 0 ? false : z, (i2 & 512) != 0 ? null : list2, (i2 & 1024) != 0 ? null : list3, (i2 & 2048) != 0 ? 0 : i, (i2 & 4096) != 0 ? null : num, (i2 & 8192) != 0 ? null : list4);
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public boolean areItemsTheSame(Object obj) {
        return UpdatableContentMetadata.DefaultImpls.areItemsTheSame(this, obj);
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return this.viewId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<set-?>");
        this.uri = uri;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return this.tags;
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getBlockRef() {
        return this.blockRef;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    public final List<VideoStoryMetadata> getStoriesMetadata() {
        return this.storiesMetadata;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final Integer getTrackCount() {
        return this.trackCount;
    }

    @Override // com.amazon.music.views.library.metadata.UpdatableContentMetadata
    public void update(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        VideoStoryMetadata videoStoryMetadata = metadata instanceof VideoStoryMetadata ? (VideoStoryMetadata) metadata : null;
        if (videoStoryMetadata == null) {
            return;
        }
        setUri(videoStoryMetadata.getUri());
    }
}
