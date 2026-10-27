package com.amazon.music.find.model.search;

import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.search.SpellCorrections;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: SearchRequest.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u0012J\\\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0010R\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/amazon/music/find/model/search/SearchRequest;", "", SearchIntents.EXTRA_QUERY, "", "enableSpellCorrections", "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "isOffline", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "pageSize", "", "(Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;ZLjava/lang/String;Ljava/lang/Integer;)V", "getEnableSpellCorrections", "()Z", "getPageSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getQuery", "()Ljava/lang/String;", "getQueryMetadata", "getSearchFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "getSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;ZLjava/lang/String;Ljava/lang/Integer;)Lcom/amazon/music/find/model/search/SearchRequest;", "equals", "other", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchRequest {
    private final boolean enableSpellCorrections;
    private final boolean isOffline;
    private final Integer pageSize;
    private final String query;
    private final String queryMetadata;
    private final SearchFilterSelections searchFilterSelections;
    private final SpellCorrections spellCorrections;

    public SearchRequest() {
        this(null, false, null, null, false, null, null, WorkQueueKt.MASK, null);
    }

    public static /* synthetic */ SearchRequest copy$default(SearchRequest searchRequest, String str, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean z2, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchRequest.query;
        }
        if ((i & 2) != 0) {
            z = searchRequest.enableSpellCorrections;
        }
        boolean z3 = z;
        if ((i & 4) != 0) {
            spellCorrections = searchRequest.spellCorrections;
        }
        SpellCorrections spellCorrections2 = spellCorrections;
        if ((i & 8) != 0) {
            searchFilterSelections = searchRequest.searchFilterSelections;
        }
        SearchFilterSelections searchFilterSelections2 = searchFilterSelections;
        if ((i & 16) != 0) {
            z2 = searchRequest.isOffline;
        }
        boolean z4 = z2;
        if ((i & 32) != 0) {
            str2 = searchRequest.queryMetadata;
        }
        String str3 = str2;
        if ((i & 64) != 0) {
            num = searchRequest.pageSize;
        }
        return searchRequest.copy(str, z3, spellCorrections2, searchFilterSelections2, z4, str3, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getEnableSpellCorrections() {
        return this.enableSpellCorrections;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SearchFilterSelections getSearchFilterSelections() {
        return this.searchFilterSelections;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsOffline() {
        return this.isOffline;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getQueryMetadata() {
        return this.queryMetadata;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getPageSize() {
        return this.pageSize;
    }

    public final SearchRequest copy(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean isOffline, String queryMetadata, Integer pageSize) {
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        return new SearchRequest(query, enableSpellCorrections, spellCorrections, searchFilterSelections, isOffline, queryMetadata, pageSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchRequest)) {
            return false;
        }
        SearchRequest searchRequest = (SearchRequest) other;
        return Intrinsics.areEqual(this.query, searchRequest.query) && this.enableSpellCorrections == searchRequest.enableSpellCorrections && Intrinsics.areEqual(this.spellCorrections, searchRequest.spellCorrections) && Intrinsics.areEqual(this.searchFilterSelections, searchRequest.searchFilterSelections) && this.isOffline == searchRequest.isOffline && Intrinsics.areEqual(this.queryMetadata, searchRequest.queryMetadata) && Intrinsics.areEqual(this.pageSize, searchRequest.pageSize);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        String str = this.query;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        boolean z = this.enableSpellCorrections;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        SpellCorrections spellCorrections = this.spellCorrections;
        int iHashCode2 = (((i + (spellCorrections == null ? 0 : spellCorrections.hashCode())) * 31) + this.searchFilterSelections.hashCode()) * 31;
        boolean z2 = this.isOffline;
        int i2 = (iHashCode2 + (z2 ? 1 : z2)) * 31;
        String str2 = this.queryMetadata;
        int iHashCode3 = (i2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.pageSize;
        return iHashCode3 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "SearchRequest(query=" + ((Object) this.query) + ", enableSpellCorrections=" + this.enableSpellCorrections + ", spellCorrections=" + this.spellCorrections + ", searchFilterSelections=" + this.searchFilterSelections + ", isOffline=" + this.isOffline + ", queryMetadata=" + ((Object) this.queryMetadata) + ", pageSize=" + this.pageSize + ')';
    }

    public SearchRequest(String str, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean z2, String str2, Integer num) {
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        this.query = str;
        this.enableSpellCorrections = z;
        this.spellCorrections = spellCorrections;
        this.searchFilterSelections = searchFilterSelections;
        this.isOffline = z2;
        this.queryMetadata = str2;
        this.pageSize = num;
    }

    public final String getQuery() {
        return this.query;
    }

    public final boolean getEnableSpellCorrections() {
        return this.enableSpellCorrections;
    }

    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    public /* synthetic */ SearchRequest(String str, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean z2, String str2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? true : z, (i & 4) != 0 ? null : spellCorrections, (i & 8) != 0 ? SearchFilterSelections.INSTANCE.getEMPTY_SELECTION() : searchFilterSelections, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : num);
    }

    public final SearchFilterSelections getSearchFilterSelections() {
        return this.searchFilterSelections;
    }

    public final boolean isOffline() {
        return this.isOffline;
    }

    public final String getQueryMetadata() {
        return this.queryMetadata;
    }

    public final Integer getPageSize() {
        return this.pageSize;
    }
}
