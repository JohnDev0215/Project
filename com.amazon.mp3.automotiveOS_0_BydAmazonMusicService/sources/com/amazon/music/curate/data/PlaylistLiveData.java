package com.amazon.music.curate.data;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserPlaylistRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u00070\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003HÆ\u0003J\u001b\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u00070\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003JK\u0010\u0013\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u00070\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\bHÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R#\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistLiveData;", "", ContextMappingConstants.ITEMS, "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "dataSource", "Landroidx/paging/DataSource;", "", "recsLoaded", "", "(Landroidx/lifecycle/LiveData;Landroidx/lifecycle/LiveData;Landroidx/lifecycle/LiveData;)V", "getDataSource", "()Landroidx/lifecycle/LiveData;", "getItems", "getRecsLoaded", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class PlaylistLiveData {
    private final LiveData<DataSource<Integer, VisualModel>> dataSource;
    private final LiveData<PagedList<VisualModel>> items;
    private final LiveData<Boolean> recsLoaded;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PlaylistLiveData copy$default(PlaylistLiveData playlistLiveData, LiveData liveData, LiveData liveData2, LiveData liveData3, int i, Object obj) {
        if ((i & 1) != 0) {
            liveData = playlistLiveData.items;
        }
        if ((i & 2) != 0) {
            liveData2 = playlistLiveData.dataSource;
        }
        if ((i & 4) != 0) {
            liveData3 = playlistLiveData.recsLoaded;
        }
        return playlistLiveData.copy(liveData, liveData2, liveData3);
    }

    public final LiveData<PagedList<VisualModel>> component1() {
        return this.items;
    }

    public final LiveData<DataSource<Integer, VisualModel>> component2() {
        return this.dataSource;
    }

    public final LiveData<Boolean> component3() {
        return this.recsLoaded;
    }

    public final PlaylistLiveData copy(LiveData<PagedList<VisualModel>> items, LiveData<DataSource<Integer, VisualModel>> dataSource, LiveData<Boolean> recsLoaded) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        Intrinsics.checkNotNullParameter(recsLoaded, "recsLoaded");
        return new PlaylistLiveData(items, dataSource, recsLoaded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaylistLiveData)) {
            return false;
        }
        PlaylistLiveData playlistLiveData = (PlaylistLiveData) other;
        return Intrinsics.areEqual(this.items, playlistLiveData.items) && Intrinsics.areEqual(this.dataSource, playlistLiveData.dataSource) && Intrinsics.areEqual(this.recsLoaded, playlistLiveData.recsLoaded);
    }

    public int hashCode() {
        return (((this.items.hashCode() * 31) + this.dataSource.hashCode()) * 31) + this.recsLoaded.hashCode();
    }

    public String toString() {
        return "PlaylistLiveData(items=" + this.items + ", dataSource=" + this.dataSource + ", recsLoaded=" + this.recsLoaded + ')';
    }

    public PlaylistLiveData(LiveData<PagedList<VisualModel>> items, LiveData<DataSource<Integer, VisualModel>> dataSource, LiveData<Boolean> recsLoaded) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        Intrinsics.checkNotNullParameter(recsLoaded, "recsLoaded");
        this.items = items;
        this.dataSource = dataSource;
        this.recsLoaded = recsLoaded;
    }

    public final LiveData<PagedList<VisualModel>> getItems() {
        return this.items;
    }

    public final LiveData<DataSource<Integer, VisualModel>> getDataSource() {
        return this.dataSource;
    }

    public final LiveData<Boolean> getRecsLoaded() {
        return this.recsLoaded;
    }
}
