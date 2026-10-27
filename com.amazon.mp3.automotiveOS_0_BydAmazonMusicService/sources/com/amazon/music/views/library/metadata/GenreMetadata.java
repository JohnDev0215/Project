package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.amazon.music.ContentAccessType;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenreMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001Bk\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d¢\u0006\u0004\b%\u0010&R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\b\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006R\u0017\u0010\u0017\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0006R*\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/amazon/music/views/library/metadata/GenreMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "stationKey", "getStationKey", "browseId", "getBrowseId", "title", "getTitle", "imageUrl", "getImageUrl", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class GenreMetadata implements ContentMetadata {
    private final String browseId;
    private final String imageUrl;
    private final String stationKey;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private Uri uri;
    private final String viewId;

    public GenreMetadata(String str, Uri uri, List<String> list, String str2, String browseId, String str3, String str4, Set<? extends ContentAccessType> set) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(browseId, "browseId");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.stationKey = str2;
        this.browseId = browseId;
        this.title = str3;
        this.imageUrl = str4;
        this.supportedTiers = set;
    }

    public /* synthetic */ GenreMetadata(String str, Uri uri, List list, String str2, String str3, String str4, String str5, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : set);
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return this.viewId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return this.tags;
    }

    public final String getStationKey() {
        return this.stationKey;
    }

    public final String getBrowseId() {
        return this.browseId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }
}
