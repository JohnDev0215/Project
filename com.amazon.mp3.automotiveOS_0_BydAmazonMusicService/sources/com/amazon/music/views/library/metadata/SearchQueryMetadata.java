package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchQueryMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0015\u0012\u0006\u0010\u001c\u001a\u00020\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\"\u0010\u000f\u001a\u00020\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R*\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00158\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u000b\u001a\u0004\b\u001d\u0010\r¨\u0006 "}, d2 = {"Lcom/amazon/music/views/library/metadata/SearchQueryMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "toString", "", "hashCode", "", "other", "", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", SearchIntents.EXTRA_QUERY, "getQuery", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SearchQueryMetadata implements ContentMetadata {
    private final String query;
    private List<String> tags;
    private Uri uri;
    private final String viewId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchQueryMetadata)) {
            return false;
        }
        SearchQueryMetadata searchQueryMetadata = (SearchQueryMetadata) other;
        return Intrinsics.areEqual(getViewId(), searchQueryMetadata.getViewId()) && Intrinsics.areEqual(getUri(), searchQueryMetadata.getUri()) && Intrinsics.areEqual(getTags(), searchQueryMetadata.getTags()) && Intrinsics.areEqual(this.query, searchQueryMetadata.query);
    }

    public int hashCode() {
        return ((((((getViewId() == null ? 0 : getViewId().hashCode()) * 31) + getUri().hashCode()) * 31) + (getTags() != null ? getTags().hashCode() : 0)) * 31) + this.query.hashCode();
    }

    public String toString() {
        return "SearchQueryMetadata(viewId=" + ((Object) getViewId()) + ", uri=" + getUri() + ", tags=" + getTags() + ", query=" + this.query + ')';
    }

    public SearchQueryMetadata(String str, Uri uri, List<String> list, String query) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(query, "query");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.query = query;
    }

    public /* synthetic */ SearchQueryMetadata(String str, Uri uri, List list, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, str2);
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

    public final String getQuery() {
        return this.query;
    }
}
