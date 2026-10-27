package com.amazon.podcast.appsync;

import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.WriteElement;
import SOACoreInterface.v1_0.Method;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.appsync.QueryParameters;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SyncData<T extends WriteElement, M extends WriteElement, D, Q extends QueryParameters> {
    public abstract void deleteOperations(final List<String> ids);

    public abstract ClientStates.Entry getClientState(final String name);

    public abstract D read(final String id);

    public abstract LiveData<D> readState(final String id);

    public abstract LiveData<List<D>> readStates();

    public abstract LiveData<List<D>> readStates(final Set<String> ids);

    public abstract List<Method> reportOperationMethods();

    public abstract void writeMetadata(final M m);

    public abstract boolean writeState(T t, ConditionElement conditionElement, long updatedTime);

    public abstract void writeToOperations(T t, long updatedTime);

    public LiveData<List<D>> readStatesLiveData(final Q q) {
        throw new UnsupportedOperationException();
    }

    public List<D> readStates(final Q q) {
        throw new UnsupportedOperationException();
    }
}
