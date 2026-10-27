package com.amazon.music.find.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.music.find.data.database.entities.SearchQueryData, reason: from toString */
/* JADX INFO: compiled from: SearchQueryData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Entity(primaryKeys = {SearchIntents.EXTRA_QUERY, "profile_id"}, tableName = "search_query")
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0003H\u0016R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/find/data/database/entities/SearchQueryData;", "", SearchIntents.EXTRA_QUERY, "", TetheredMessageKey.timestamp, "", "pid", "(Ljava/lang/String;JLjava/lang/String;)V", "getPid", "()Ljava/lang/String;", "getQuery", "getTimestamp", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchHistory {

    @ColumnInfo(name = "profile_id")
    private final String pid;

    @ColumnInfo(collate = 3)
    private final String query;
    private final long timestamp;

    public static /* synthetic */ SearchHistory copy$default(SearchHistory searchHistory, String str, long j, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchHistory.query;
        }
        if ((i & 2) != 0) {
            j = searchHistory.timestamp;
        }
        if ((i & 4) != 0) {
            str2 = searchHistory.pid;
        }
        return searchHistory.copy(str, j, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    public final SearchHistory copy(String query, long timestamp, String pid) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(pid, "pid");
        return new SearchHistory(query, timestamp, pid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHistory)) {
            return false;
        }
        SearchHistory searchHistory = (SearchHistory) other;
        return Intrinsics.areEqual(this.query, searchHistory.query) && this.timestamp == searchHistory.timestamp && Intrinsics.areEqual(this.pid, searchHistory.pid);
    }

    public int hashCode() {
        return (((this.query.hashCode() * 31) + Long.hashCode(this.timestamp)) * 31) + this.pid.hashCode();
    }

    public SearchHistory(String query, long j, String pid) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(pid, "pid");
        this.query = query;
        this.timestamp = j;
        this.pid = pid;
    }

    public final String getQuery() {
        return this.query;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getPid() {
        return this.pid;
    }

    public String toString() {
        return "SearchHistory(query='" + this.query + "', timestamp='" + this.timestamp + "')";
    }
}
