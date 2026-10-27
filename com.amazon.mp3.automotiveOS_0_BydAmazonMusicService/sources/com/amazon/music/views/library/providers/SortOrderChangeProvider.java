package com.amazon.music.views.library.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: sortOrderChangeProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/providers/SortOrderChangeProvider;", "", "onSortOrderChanged", "", "sortOrder", "Lcom/amazon/music/views/library/providers/SortOrderChangeProvider$SortOrder;", "SortOrder", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SortOrderChangeProvider {

    /* JADX INFO: compiled from: sortOrderChangeProvider.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/views/library/providers/SortOrderChangeProvider$SortOrder;", "", "(Ljava/lang/String;I)V", "ASCENDING", "DESCENDING", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SortOrder {
        ASCENDING,
        DESCENDING
    }

    void onSortOrderChanged(SortOrder sortOrder);
}
