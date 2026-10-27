package com.amazon.music.find.model.search;

import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SpellCorrections;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SeeMoreRequest.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b \b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010%\u001a\u00020\u000bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010\u0017Jp\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\u00072\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0010HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0013R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00060"}, d2 = {"Lcom/amazon/music/find/model/search/SeeMoreRequest;", "", SearchIntents.EXTRA_QUERY, "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "enableSpellCorrections", "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "pageToken", "isOffline", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "pageSize", "", "(Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;)V", "getEnableSpellCorrections", "()Z", "getEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "getPageSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPageToken", "()Ljava/lang/String;", "getQuery", "getQueryMetadata", "getSearchFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "getSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;)Lcom/amazon/music/find/model/search/SeeMoreRequest;", "equals", "other", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SeeMoreRequest {
    private final boolean enableSpellCorrections;
    private final EntityType entityType;
    private final boolean isOffline;
    private final Integer pageSize;
    private final String pageToken;
    private final String query;
    private final String queryMetadata;
    private final SearchFilterSelections searchFilterSelections;
    private final SpellCorrections spellCorrections;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final EntityType getEntityType() {
        return this.entityType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEnableSpellCorrections() {
        return this.enableSpellCorrections;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final SearchFilterSelections getSearchFilterSelections() {
        return this.searchFilterSelections;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPageToken() {
        return this.pageToken;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsOffline() {
        return this.isOffline;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getQueryMetadata() {
        return this.queryMetadata;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getPageSize() {
        return this.pageSize;
    }

    public final SeeMoreRequest copy(String query, EntityType entityType, boolean enableSpellCorrections, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, String pageToken, boolean isOffline, String queryMetadata, Integer pageSize) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        return new SeeMoreRequest(query, entityType, enableSpellCorrections, spellCorrections, searchFilterSelections, pageToken, isOffline, queryMetadata, pageSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeeMoreRequest)) {
            return false;
        }
        SeeMoreRequest seeMoreRequest = (SeeMoreRequest) other;
        return Intrinsics.areEqual(this.query, seeMoreRequest.query) && this.entityType == seeMoreRequest.entityType && this.enableSpellCorrections == seeMoreRequest.enableSpellCorrections && Intrinsics.areEqual(this.spellCorrections, seeMoreRequest.spellCorrections) && Intrinsics.areEqual(this.searchFilterSelections, seeMoreRequest.searchFilterSelections) && Intrinsics.areEqual(this.pageToken, seeMoreRequest.pageToken) && this.isOffline == seeMoreRequest.isOffline && Intrinsics.areEqual(this.queryMetadata, seeMoreRequest.queryMetadata) && Intrinsics.areEqual(this.pageSize, seeMoreRequest.pageSize);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    public int hashCode() {
        int iHashCode = ((this.query.hashCode() * 31) + this.entityType.hashCode()) * 31;
        boolean z = this.enableSpellCorrections;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        SpellCorrections spellCorrections = this.spellCorrections;
        int iHashCode2 = (((i + (spellCorrections == null ? 0 : spellCorrections.hashCode())) * 31) + this.searchFilterSelections.hashCode()) * 31;
        String str = this.pageToken;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        boolean z2 = this.isOffline;
        int i2 = (iHashCode3 + (z2 ? 1 : z2)) * 31;
        String str2 = this.queryMetadata;
        int iHashCode4 = (i2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.pageSize;
        return iHashCode4 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "SeeMoreRequest(query=" + this.query + ", entityType=" + this.entityType + ", enableSpellCorrections=" + this.enableSpellCorrections + ", spellCorrections=" + this.spellCorrections + ", searchFilterSelections=" + this.searchFilterSelections + ", pageToken=" + ((Object) this.pageToken) + ", isOffline=" + this.isOffline + ", queryMetadata=" + ((Object) this.queryMetadata) + ", pageSize=" + this.pageSize + ')';
    }

    public SeeMoreRequest(String query, EntityType entityType, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, String str, boolean z2, String str2, Integer num) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        this.query = query;
        this.entityType = entityType;
        this.enableSpellCorrections = z;
        this.spellCorrections = spellCorrections;
        this.searchFilterSelections = searchFilterSelections;
        this.pageToken = str;
        this.isOffline = z2;
        this.queryMetadata = str2;
        this.pageSize = num;
    }

    public final String getQuery() {
        return this.query;
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final boolean getEnableSpellCorrections() {
        return this.enableSpellCorrections;
    }

    public final SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    public /* synthetic */ SeeMoreRequest(String str, EntityType entityType, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, String str2, boolean z2, String str3, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, entityType, (i & 4) != 0 ? true : z, (i & 8) != 0 ? null : spellCorrections, (i & 16) != 0 ? SearchFilterSelections.INSTANCE.getEMPTY_SELECTION() : searchFilterSelections, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? false : z2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : num);
    }

    public final SearchFilterSelections getSearchFilterSelections() {
        return this.searchFilterSelections;
    }

    public final String getPageToken() {
        return this.pageToken;
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
