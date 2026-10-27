package com.amazon.music.find.service;

import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.platform.find.common.EntityType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExecutionContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J<\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0006\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/find/service/SearchPageContextData;", "", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "isOffline", "", "isLibrary", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getMode", "()Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "getSeeMoreEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "component1", "component2", "component3", "component4", "copy", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)Lcom/amazon/music/find/service/SearchPageContextData;", "equals", "other", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchPageContextData {
    private final Boolean isLibrary;
    private final Boolean isOffline;
    private final SearchExperienceMode mode;
    private final EntityType seeMoreEntityType;

    public static /* synthetic */ SearchPageContextData copy$default(SearchPageContextData searchPageContextData, SearchExperienceMode searchExperienceMode, Boolean bool, Boolean bool2, EntityType entityType, int i, Object obj) {
        if ((i & 1) != 0) {
            searchExperienceMode = searchPageContextData.mode;
        }
        if ((i & 2) != 0) {
            bool = searchPageContextData.isOffline;
        }
        if ((i & 4) != 0) {
            bool2 = searchPageContextData.isLibrary;
        }
        if ((i & 8) != 0) {
            entityType = searchPageContextData.seeMoreEntityType;
        }
        return searchPageContextData.copy(searchExperienceMode, bool, bool2, entityType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SearchExperienceMode getMode() {
        return this.mode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getIsOffline() {
        return this.isOffline;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Boolean getIsLibrary() {
        return this.isLibrary;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final EntityType getSeeMoreEntityType() {
        return this.seeMoreEntityType;
    }

    public final SearchPageContextData copy(SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, EntityType seeMoreEntityType) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        return new SearchPageContextData(mode, isOffline, isLibrary, seeMoreEntityType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchPageContextData)) {
            return false;
        }
        SearchPageContextData searchPageContextData = (SearchPageContextData) other;
        return this.mode == searchPageContextData.mode && Intrinsics.areEqual(this.isOffline, searchPageContextData.isOffline) && Intrinsics.areEqual(this.isLibrary, searchPageContextData.isLibrary) && this.seeMoreEntityType == searchPageContextData.seeMoreEntityType;
    }

    public int hashCode() {
        int iHashCode = this.mode.hashCode() * 31;
        Boolean bool = this.isOffline;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.isLibrary;
        int iHashCode3 = (iHashCode2 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        EntityType entityType = this.seeMoreEntityType;
        return iHashCode3 + (entityType != null ? entityType.hashCode() : 0);
    }

    public String toString() {
        return "SearchPageContextData(mode=" + this.mode + ", isOffline=" + this.isOffline + ", isLibrary=" + this.isLibrary + ", seeMoreEntityType=" + this.seeMoreEntityType + ')';
    }

    public SearchPageContextData(SearchExperienceMode mode, Boolean bool, Boolean bool2, EntityType entityType) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.mode = mode;
        this.isOffline = bool;
        this.isLibrary = bool2;
        this.seeMoreEntityType = entityType;
    }

    public final SearchExperienceMode getMode() {
        return this.mode;
    }

    public final Boolean isOffline() {
        return this.isOffline;
    }

    public final Boolean isLibrary() {
        return this.isLibrary;
    }

    public final EntityType getSeeMoreEntityType() {
        return this.seeMoreEntityType;
    }
}
