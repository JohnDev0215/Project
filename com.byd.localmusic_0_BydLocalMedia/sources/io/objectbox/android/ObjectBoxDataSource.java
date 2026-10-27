package io.objectbox.android;

import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ObjectBoxDataSource<T> extends PositionalDataSource<T> {
    private final DataObserver<List<T>> observer = new DataObserver() { // from class: io.objectbox.android.-$$Lambda$ObjectBoxDataSource$n5bMjmsev_8vrN5m76LqBE4MMRQ
        @Override // io.objectbox.reactive.DataObserver
        public final void onData(Object obj) {
            this.f$0.lambda$new$0$ObjectBoxDataSource((List) obj);
        }
    };
    private final Query<T> query;

    public static class Factory<Item> extends DataSource.Factory<Integer, Item> {
        private final Query<Item> query;

        public Factory(Query<Item> query) {
            this.query = query;
        }

        public DataSource<Integer, Item> create() {
            return new ObjectBoxDataSource(this.query);
        }
    }

    public ObjectBoxDataSource(Query<T> query) {
        this.query = query;
        query.subscribe().onlyChanges().weak().observer(this.observer);
    }

    public /* synthetic */ void lambda$new$0$ObjectBoxDataSource(List list) {
        invalidate();
    }

    public void loadInitial(PositionalDataSource.LoadInitialParams loadInitialParams, PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        int iCount = (int) this.query.count();
        if (iCount == 0) {
            loadInitialCallback.onResult(Collections.emptyList(), 0, 0);
            return;
        }
        int iComputeInitialLoadPosition = computeInitialLoadPosition(loadInitialParams, iCount);
        int iComputeInitialLoadSize = computeInitialLoadSize(loadInitialParams, iComputeInitialLoadPosition, iCount);
        List<T> listLoadRange = loadRange(iComputeInitialLoadPosition, iComputeInitialLoadSize);
        if (listLoadRange.size() == iComputeInitialLoadSize) {
            loadInitialCallback.onResult(listLoadRange, iComputeInitialLoadPosition, iCount);
        } else {
            invalidate();
        }
    }

    public void loadRange(PositionalDataSource.LoadRangeParams loadRangeParams, PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        loadRangeCallback.onResult(loadRange(loadRangeParams.startPosition, loadRangeParams.loadSize));
    }

    private List<T> loadRange(int i, int i2) {
        return this.query.find(i, i2);
    }
}
